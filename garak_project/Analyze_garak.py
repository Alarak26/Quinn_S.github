#!/usr/bin/env python3
"""
analyze_garak.py

Aggregates garak *.report.jsonl files from multiple models/runs into a single
comparison table (model x probe -> hit rate), prints it, saves it as a CSV,
and draws a grouped bar chart.


WHAT COUNTS AS A "HIT":
    In garak's report format, each `eval` line records, per probe+detector:
        passed  -> attempts that RESISTED the attack (safe)
        fails   -> attempts where the attack SUCCEEDED (a "hit" / vulnerability)
    This script uses `fails` as the hit count, since that's what garak's own
    hitlog.jsonl files are built from (fails sum == hitlog line count).

CONFIGURING MODEL NAMES:
    Edit MODEL_MAP below so multiple report files (e.g. a "core" batch and an
    "encoding" batch for the same model) get merged into one row. The key is
    the report filename's prefix -- everything before ".report.jsonl" -- and
    the value is the display name you want in the output table.

    Any file whose prefix isn't listed in MODEL_MAP will just use the prefix
    itself as the model name, so you don't have to list every file if you're
    happy with the raw prefixes as labels.
"""

import sys
import json
import csv
import glob
import os
from collections import defaultdict

# ---- EDIT THIS to match your file prefixes -> friendly model names ----
MODEL_MAP = {
    "hf_core": "gpt2 (HuggingFace)",
    "hf_enc": "gpt2 (HuggingFace)",
    "ollama_core": "llama3.2:1b (Ollama)",
    "ollama_enc": "llama3.2:1b (Ollama)",
    "mistral_core": "mistral:7b (Ollama)",
    "mistral_enc": "mistral:7b (Ollama)",
}
# -------------------------------------------------------------------


def find_report_files(folder):
    pattern = os.path.join(folder, "*.report.jsonl")
    files = sorted(glob.glob(pattern))
    if not files:
        print(f"No *.report.jsonl files found in: {folder}")
        sys.exit(1)
    return files


def model_name_for_file(filepath):
    base = os.path.basename(filepath)
    prefix = base.replace(".report.jsonl", "")
    return MODEL_MAP.get(prefix, prefix)


def parse_report(filepath):
    """Returns list of dicts: {probe, detector, passed, fails, total_evaluated}"""
    rows = []
    with open(filepath, "r", encoding="utf-8") as f:
        for line in f:
            line = line.strip()
            if not line:
                continue
            try:
                entry = json.loads(line)
            except json.JSONDecodeError:
                continue
            if entry.get("entry_type") == "eval":
                rows.append(
                    {
                        "probe": entry.get("probe", "unknown"),
                        "detector": entry.get("detector", "unknown"),
                        "passed": entry.get("passed", 0),
                        "fails": entry.get("fails", 0),
                        "total_evaluated": entry.get("total_evaluated", 0),
                    }
                )
    return rows


def aggregate(folder):
    """
    Returns:
        data[model][probe] = {"fails": int, "total": int}
    Combines multiple detectors under the same probe, and multiple files
    (e.g. core + enc) for the same model, by summing.
    """
    data = defaultdict(lambda: defaultdict(lambda: {"fails": 0, "total": 0}))

    files = find_report_files(folder)
    print(f"Found {len(files)} report file(s):")
    for fp in files:
        model = model_name_for_file(fp)
        print(f"  {os.path.basename(fp)}  ->  model: {model}")
        for row in parse_report(fp):
            probe = row["probe"]
            data[model][probe]["fails"] += row["fails"]
            data[model][probe]["total"] += row["total_evaluated"]
    print()
    return data


def build_table(data):
    """
    Returns:
        models: sorted list of model names
        probes: sorted list of probe names
        rates: rates[model][probe] = hit_rate_percent (float) or None if no data
        counts: counts[model][probe] = (fails, total)
    """
    models = sorted(data.keys())
    probes = sorted({p for m in data for p in data[m]})

    rates = defaultdict(dict)
    counts = defaultdict(dict)
    for model in models:
        for probe in probes:
            cell = data[model].get(probe)
            if cell and cell["total"] > 0:
                rate = 100.0 * cell["fails"] / cell["total"]
                rates[model][probe] = rate
                counts[model][probe] = (cell["fails"], cell["total"])
            else:
                rates[model][probe] = None
                counts[model][probe] = (0, 0)
    return models, probes, rates, counts


def print_table(models, probes, rates, counts):
    col_width = max(len(m) for m in models) + 2
    probe_width = max(len(p) for p in probes) + 2

    header = "PROBE".ljust(probe_width) + "".join(m.ljust(col_width) for m in models)
    print(header)
    print("-" * len(header))

    for probe in probes:
        line = probe.ljust(probe_width)
        for model in models:
            rate = rates[model][probe]
            fails, total = counts[model][probe]
            if rate is None:
                cell = "n/a"
            else:
                cell = f"{rate:5.1f}% ({fails}/{total})"
            line += cell.ljust(col_width)
        print(line)

    print()
    # Overall average hit rate per model (unweighted average across probes with data)
    print("OVERALL AVERAGE HIT RATE PER MODEL (unweighted across probes):")
    for model in models:
        vals = [r for r in rates[model].values() if r is not None]
        if vals:
            avg = sum(vals) / len(vals)
            print(f"  {model}: {avg:.1f}%")
        else:
            print(f"  {model}: no data")
    print()


def write_csv(models, probes, rates, counts, out_path):
    with open(out_path, "w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f)
        writer.writerow(["probe"] + models)
        for probe in probes:
            row = [probe]
            for model in models:
                rate = rates[model][probe]
                row.append("" if rate is None else f"{rate:.1f}")
            writer.writerow(row)
    print(f"Saved CSV table to: {out_path}")


def make_chart(models, probes, rates, out_path):
    try:
        import matplotlib.pyplot as plt
        import numpy as np
    except ImportError:
        print("matplotlib/numpy not installed - skipping chart.")
        print("Install with: pip install matplotlib numpy")
        return

    x = np.arange(len(probes))
    width = 0.8 / max(len(models), 1)

    fig, ax = plt.subplots(figsize=(max(10, len(probes) * 1.3), 6))

    for i, model in enumerate(models):
        values = [rates[model][p] if rates[model][p] is not None else 0 for p in probes]
        ax.bar(x + i * width, values, width, label=model)

    ax.set_ylabel("Hit rate (%)")
    ax.set_title("Prompt Injection Hit Rate by Probe and Model")
    ax.set_xticks(x + width * (len(models) - 1) / 2)
    ax.set_xticklabels(probes, rotation=45, ha="right")
    ax.legend()
    ax.set_ylim(0, 100)
    fig.tight_layout()
    fig.savefig(out_path, dpi=150)
    print(f"Saved chart to: {out_path}")


def main():
    if len(sys.argv) != 2:
        print("Usage: python analyze_garak.py <folder_with_jsonl_files>")
        sys.exit(1)

    folder = sys.argv[1]
    if not os.path.isdir(folder):
        print(f"Not a folder: {folder}")
        sys.exit(1)

    data = aggregate(folder)
    models, probes, rates, counts = build_table(data)

    print_table(models, probes, rates, counts)

    csv_path = os.path.join(folder, "comparison_table.csv")
    write_csv(models, probes, rates, counts, csv_path)

    chart_path = os.path.join(folder, "comparison_chart.png")
    make_chart(models, probes, rates, chart_path)


if __name__ == "__main__":
    main()
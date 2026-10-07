<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta
      name="description"
      content="Quinn Smith’s cybersecurity portfolio: threat intelligence automation, AI security testing, risk assessment, and Java systems programming."
    />
    <meta name="theme-color" content="#122d38" />
    <title>Cybersecurity Portfolio | Quinn Smith</title>
    <link rel="stylesheet" href="assets/site.css" />
  </head>

  <body>
    <a class="skip" href="#main">Skip to content</a>

    <header class="site-header">
      <div class="wrap header-inner">
        <a class="brand" href="index.html">
          <span class="monogram" aria-hidden="true">QS</span>
          Quinn Smith
        </a>

        <nav aria-label="Main navigation">
          <a href="index.html#projects">Projects</a>
          <a href="index.html#about">About</a>
          <a href="index.html#resume">Résumé</a>
          <a href="index.html#contact">Contact</a>
        </nav>
      </div>
    </header>

    <main id="main">
      <!-- Introduction -->
      <section class="hero" aria-labelledby="intro-title">
        <div class="wrap hero-grid">
          <div>
            <p class="eyebrow">Cybersecurity portfolio</p>

            <h1 id="intro-title">
              Quinn
              <span>Smith.</span>
            </h1>

            <p class="lead">
              Cybersecurity student building practical tools for threat intelligence,
              exploring AI security, and translating risk assessments into clear
              recommendations.
            </p>

            <div class="actions">
              <a class="button" href="#projects">
                Explore projects
                <span aria-hidden="true">↓</span>
              </a>

              <a
                class="button secondary"
                href="downloads/Quinn_Smith_Resume.pdf"
                download
              >
                Download résumé
                <span aria-hidden="true">↗</span>
              </a>
            </div>
          </div>

          <aside class="focus" aria-label="Areas of focus">
            <p class="eyebrow">Where I focus</p>

            <div class="focus-row">
              <strong>Security automation</strong>
              <small>Python tools &amp; threat intelligence</small>
            </div>

            <div class="focus-row">
              <strong>AI security</strong>
              <small>Adversarial testing &amp; prompt injection</small>
            </div>

            <div class="focus-row">
              <strong>Risk management</strong>
              <small>Asset assessment &amp; mitigation planning</small>
            </div>
          </aside>
        </div>
      </section>

      <div class="wrap meta-strip">
        <span>
          <i class="dot" aria-hidden="true"></i>
          University of Wisconsin–Platteville
        </span>

        <span>
          <i class="dot" aria-hidden="true"></i>
          B.S. Cyber Security · Expected May 2027
        </span>

        <span>
          <i class="dot" aria-hidden="true"></i>
          Neenah, Wisconsin
        </span>
      </div>

      <!-- Project cards and team contributions -->
      <section class="section wrap" id="projects">
        <div class="section-heading">
          <div>
            <p class="eyebrow">Selected work</p>
            <h2>Projects with a practical purpose.</h2>
          </div>

          <p>
            Explore the problem, the implementation, and the evidence behind each project.
          </p>
        </div>

        <div class="project-grid">
          <article class="project-card">
            <div class="card-top">
              <span class="number">01</span>
              <span class="category">Security automation</span>
            </div>

            <h3>Automated Threat Intelligence</h3>

            <p>
              A Python pipeline that collects public threat feeds, extracts indicators,
              and turns new records into a prioritized briefing using a locally hosted
              language model.
            </p>

            <div class="tags">
              <span class="tag">Python</span>
              <span class="tag">SQLite</span>
              <span class="tag">Ollama</span>
              <span class="tag">CISA KEV</span>
            </div>

            <div class="card-proof">
              <strong>57 items collected</strong>
              in the documented test run; 25 summarized across five batches.
            </div>

            <div class="card-links">
              <a href="projects/threat-intelligence.html">
                View project
                <span aria-hidden="true">↗</span>
              </a>

              <a href="downloads/Threat_Intelligence_Report.pdf">
                Read report
                <span aria-hidden="true">↗</span>
              </a>
            </div>
          </article>

          <article class="project-card">
            <div class="card-top">
              <span class="number">02</span>
              <span class="category">AI security</span>
            </div>

            <h3>Prompt Injection Security Assessment</h3>

            <p>
              Garak testing of GPT-2, Llama 3.2 1B, and Mistral 7B, with probe-level
              analysis and practical recommendations for securing LLM applications.
            </p>

            <div class="tags">
              <span class="tag">Garak</span>
              <span class="tag">Hugging Face</span>
              <span class="tag">Ollama</span>
              <span class="tag">OWASP</span>
            </div>

            <div class="card-proof">
              <strong>Three models assessed</strong>
              across differing probe sets, with coverage limitations documented.
            </div>

            <div class="card-links">
              <a href="projects/prompt-injection.html">
                View project
                <span aria-hidden="true">↗</span>
              </a>

              <a href="downloads/Prompt_Injection_Report.pdf">
                Read report
                <span aria-hidden="true">↗</span>
              </a>
            </div>
          </article>

          <article class="project-card">
            <div class="card-top">
              <span class="number">03</span>
              <span class="category">Governance &amp; risk</span>
            </div>

            <h3>Information Security Risk Management</h3>

            <p>
              A semester-long assessment for a nonprofit, connecting asset ownership,
              recovery objectives, threat analysis, and prioritized mitigation
              recommendations.
            </p>

            <div class="tags">
              <span class="tag">NIST SP 800-30</span>
              <span class="tag">NIST SP 800-53</span>
              <span class="tag">Risk assessment</span>
            </div>

            <div class="card-proof">
              <strong>14 assets inventoried</strong>
              and five priority risk scenarios mapped to security controls.
            </div>

            <div class="card-links">
              <a href="projects/risk-management.html">
                View project
                <span aria-hidden="true">↗</span>
              </a>
            </div>
          </article>

          <article class="project-card">
            <div class="card-top">
              <span class="number">04</span>
              <span class="category">Systems programming</span>
            </div>

            <h3>MiniOS Operating System Simulator</h3>

            <p>
              A team-built Java simulator for process management, round robin scheduling,
              first-fit memory allocation, and semaphore synchronization.
            </p>

            <div class="tags">
              <span class="tag">Java</span>
              <span class="tag">Operating systems</span>
              <span class="tag">Team project</span>
            </div>

            <div class="card-proof">
              <strong>Five integrated components</strong>
              with a command-line interface and documented test observations.
            </div>

            <div class="card-links">
              <a href="projects/minios.html">
                View project
                <span aria-hidden="true">↗</span>
              </a>

              <a href="downloads/MiniOS_Report.pdf">
                Read report
                <span aria-hidden="true">↗</span>
              </a>
            </div>
          </article>
        </div>

        <article class="team">
          <div>
            <p class="eyebrow">Collaboration</p>
            <h3>Plattifornia–PLATT2 GitHub Contributions</h3>

            <p>
              I contribute to projects in the Plattifornia–PLATT2 GitHub organization.
              Explore the team’s shared repositories alongside the individual and
              academic work featured here.
            </p>
          </div>

          <a
            class="button secondary"
            href="https://github.com/Plattifornia-PLATT2/"
            target="_blank"
            rel="noopener noreferrer"
          >
            Explore team GitHub
            <span aria-hidden="true">↗</span>
          </a>
        </article>
      </section>

      <!-- About, education, and skills -->
      <section class="section about" id="about">
        <div class="wrap about-grid">
          <div class="about-copy">
            <p class="eyebrow">About me</p>

            <h2>
              A technical foundation.
              <br />
              A hands-on approach.
            </h2>

            <p>
              I’m studying cybersecurity at the University of Wisconsin–Platteville,
              with an interest in security automation, software security, and the risks
              surrounding AI systems. My projects combine programming, security
              analysis, and clear technical documentation.
            </p>

            <p>
              Outside the classroom, I compete in capture-the-flag challenges through
              the Cybersecurity Club and collaborate on technical projects in the
              Robotics Club. My work in event management and waterfront leadership has
              also strengthened my troubleshooting, communication, and risk assessment
              skills.
            </p>

            <div class="education">
              <strong>Bachelor of Science in Cyber Security</strong>
              <p>University of Wisconsin–Platteville · Expected May 2027</p>

              <p>
                Coursework: Software Security, Ethical Hacking, Operating Systems,
                Network Security, and Database.
              </p>
            </div>
          </div>

          <div class="skills-box">
            <h3>Technical skills</h3>

            <div class="skill-row">
              <strong>Programming &amp; data</strong>
              <p>Python · Java · SQL · C · SQLite</p>
            </div>

            <div class="skill-row">
              <strong>Security analysis</strong>
              <p>
                Threat intelligence · IOC extraction · Prompt injection testing ·
                Risk assessment · Wireshark · AES-256 encryption
              </p>
            </div>

            <div class="skill-row">
              <strong>Tools &amp; platforms</strong>
              <p>
                Garak · Ollama · Hugging Face · Git/GitHub · CISA KEV · RSS feed parsing
              </p>
            </div>

            <div class="skill-row">
              <strong>Frameworks &amp; concepts</strong>
              <p>NIST SP 800-53 · OWASP LLM Top 10 · Zero Trust</p>
            </div>
          </div>
        </div>
      </section>

      <!-- Experience and résumé links -->
      <section class="section wrap" id="resume">
        <div class="section-heading">
          <div>
            <p class="eyebrow">Experience &amp; résumé</p>
            <h2>Responsibility beyond the code.</h2>
          </div>

          <p>Leadership, live troubleshooting, and safety-focused decision-making.</p>
        </div>

        <div class="experience-grid">
          <article class="job">
            <p class="date">SEP 2024 – PRESENT</p>
            <h3>Student Event Manager</h3>
            <p class="org">University of Wisconsin–Platteville</p>

            <p>
              Coordinate registration, A/V setup, and event logistics. Support guests,
              staff, and event teams while resolving technical and operational issues
              during live events.
            </p>
          </article>

          <article class="job">
            <p class="date">MAY 2023 – AUG 2026</p>
            <h3>Waterfront Director</h3>
            <p class="org">United Church of Christ, Inc.</p>

            <p>
              Supervised staff and volunteers, maintained safety compliance,
              facilitated staff training, and led operational risk assessments for
              waterfront activities.
            </p>
          </article>
        </div>

        <div class="resume-panel">
          <div>
            <h3>My résumé, in one place.</h3>
            <p>
              Education, technical skills, selected projects, experience, and activities.
            </p>
          </div>

          <div class="actions" style="margin-top: 0">
            <a class="button" href="downloads/Quinn_Smith_Resume.pdf">
              View résumé
              <span aria-hidden="true">↗</span>
            </a>

            <a
              class="button secondary"
              href="downloads/Quinn_Smith_Resume.pdf"
              download
            >
              Download PDF
            </a>
          </div>
        </div>
      </section>

      <!-- Contact -->
      <section class="contact" id="contact">
        <div class="wrap contact-inner">
          <div>
            <p class="eyebrow">Get in touch</p>
            <h2>Let’s connect.</h2>

            <p>
              I’m interested in opportunities to apply my cybersecurity, programming,
              and problem-solving skills.
              <br />
              <a href="mailto:quinnzsmith@gmail.com">quinnzsmith@gmail.com</a>
            </p>
          </div>

          <a class="button" href="mailto:quinnzsmith@gmail.com">
            Email Quinn
            <span aria-hidden="true">↗</span>
          </a>
        </div>
      </section>
    </main>

    <footer class="footer">
      <div class="wrap footer-inner">
        <span>© 2026 Quinn Smith</span>
        <span>Cybersecurity · University of Wisconsin–Platteville</span>
      </div>
    </footer>
  </body>
</html>

/*---------------------------------------------------------------------
Name: Logan Lusk, Sean Archibald, Quinn Smith
Group #: 19
Course: CS 3230, Section 01, Spring 2024
Purpose: This is the entry point for the MiniOS program, providing a 
         command-line interface for interacting with the system. It 
         allows the user to create processes, view active processes, 
         allocate memory, and schedule processes using round-robin 
         scheduling. The program runs in a continuous loop, accepting 
         commands from the user and processing them accordingly.
Input: The program accepts commands from the user in the following format:
       - help: Displays available commands.
       - create [name]: Creates a new process with the given name.
       - ps: Lists all currently active processes.
       - schedule: Executes processes using round-robin scheduling.
       - alloc [pid] [size]: Allocates memory to the process with the 
         specified PID.
       - mem: Displays the current memory layout.
       - exit: Exits the program.
Output: The program outputs information about processes and system 
        status, such as process creation, memory allocation success, 
        and scheduling status.
---------------------------------------------------------------------*/
import java.util.Scanner;

/**
* Main entry point for the MiniOS program.
* Initializes MemoryManager and ProcessManager and takes in user commands
* in a simple CLI. Supports creating processes, listing them, scheduling
* execution using round-robin, allocating memory, and displaying memory state.
* :param args: Command-line arguments
* :type args: String[]
*/
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ProcessManager processManager = new ProcessManager();
		MemoryManager memoryManager = new MemoryManager();
		System.out.println("Welcome to MiniOS. Type 'help' for Commands.");
		while (true) {
			System.out.print("> ");
			String input = scanner.nextLine().trim();
			String[] tokens = input.split("\\s+");
			if (tokens.length == 0) {
				continue;
			}
			String cmd = tokens[0];
			switch (cmd) {
				case "help":
					System.out.println("Commands:"
					+ "\ncreate [name]         - Create a New Process"
					+ "\nps                    - List All Processes"
					+ "\nschedule              - Schedule All Ready Processes"
					+ "\nalloc [pid] [size]    - Allocate Memory for a Process"
					+ "\nmem                   - Show Memory Layout"
					+ "\nexit                  - Exit MiniOS");
					break;
				case "create":
					if (tokens.length != 2) {
						System.out.println("Usage: create [name]");
						break;
					}
					processManager.createProcess(tokens[1]);
					break;
				case "ps":
					processManager.listProcesses();
					break;
				case "schedule":
					processManager.schedule(memoryManager);
					break;
				case "alloc":
					if (tokens.length != 3) {
						System.out.println("Usage: alloc [pid] [size]");
						break;
					}
					try {
						int pid = Integer.parseInt(tokens[1]);
						int size = Integer.parseInt(tokens[2]);
						PCB proc = processManager.getProcessByPid(pid);
						if (proc != null) {
							memoryManager.allocate(pid, size);
						} 
						else {
							System.out.println("Process not Found.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid PID or Size.");
					}
					break;
				case "mem":
					memoryManager.printMemory();
					break;
				case "exit":
					System.out.println("Exiting MiniOS...");
					return;
				default:
					System.out.println("Unknown Command. Type 'help' for List.");
			}
		}
	}
}


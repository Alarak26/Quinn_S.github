/*---------------------------------------------------------------------
Name: Logan Lusk, Sean Archibald, Quinn Smith
Group #: 19
Course: CS 3230, Section 01, Spring 2024
Purpose: This class manages the processes in the MiniOS system. It is 
         responsible for creating processes, scheduling them using 
         round-robin scheduling, managing process states, and interacting 
         with the memory manager to allocate memory for processes. It 
         maintains a list of processes, a ready queue for scheduling, 
         and a semaphore for controlling concurrent execution.
Input: The class does not take direct input from the user. Instead, 
       it interacts with other components of the program, such as the 
       `MemoryManager`, to manage processes and resources.
Output: The class outputs the status of processes, including their 
        creation, state transitions, memory allocation success or failure, 
        and the completion of their execution.
---------------------------------------------------------------------*/
import java.util.*;

public class ProcessManager {
	private final List<PCB> processes = new ArrayList<>();
	private int nextPid = 0;
	private Semaphore semaphore = new Semaphore(1);
	
	/**
	* Creates a new process with a random time slice and adds it to the processes list.
	* :param name: Name of the new process
	* :type name: String
	*/
	public void createProcess(String name) {
		int time = 5 + new Random().nextInt(11);
		PCB p = new PCB(nextPid++, name, PCB.State.READY, true, time);
		processes.add(p);
		System.out.println("Created process: PID: " + p.getPid() + ", Name: " + p.getName() + ", Remaining Time: " + time + "s");
	}

	/**
	* Lists all created processes along with their states and activity states.
	*/
	public void listProcesses() {
		if (processes.isEmpty()) {
			System.out.println("No processes found.");
		} 
		else {
			for (PCB p : processes) {
				String state = p.getState().toString();
				boolean active = p.isActive();
				System.out.println("PID: " + p.getPid() + ", Name: " + p.getName() + ", State: " + state + 
				", Active: " + active);
			}
		}
	}
	
	/**
	* Schedules processes using Round Robin, allowing one process to run at a time for a fixed quantum.
	* :param memoryManager: Used to free memory when a process finishes
	* :type memoryManager: MemoryManager
	*/
	public void schedule(MemoryManager memoryManager) {
		int quantum = 2;
		System.out.println("Scheduling Processes with Round Robin (Time Quantum = " + quantum + " seconds)");
		while (true) {
			boolean finished = true;
			for (PCB process : processes) {
				if (process.getState() == PCB.State.READY && process.isActive()) {
					finished = false;
					semaphore.waitSem();
					process.setState(PCB.State.RUNNING);
					System.out.println("Running Process: " + process.getName() + " (PID: " + process.getPid() + 
					"), Time Left: " + process.getRemainingTime() + "s");
					try {
						Thread.sleep(quantum * 1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					process.decrementTime(quantum);
					if (process.getRemainingTime() <= 0) {
						process.setActive(false);
						memoryManager.free(process.getPid());
						System.out.println("Process " + process.getName() + " (PID: " + process.getPid() + 
						") Completed.");
					} 
					else {
						process.setState(PCB.State.READY);
					}
					semaphore.signal();
				}
			}
			if (finished) {
				System.out.println("All Processes Completed.");
				break;
			}
		}
	}

	/**
	* Finds and returns a process by its PID.
	* :param pid: Process ID to search for
	* :type pid: int
	* :return: The matching PCB or null if not found
	* :rtype: PCB
	*/
	public PCB getProcessByPid(int pid) {
		for (PCB p : processes) {
			if (p.getPid() == pid) {
				return p;
			}
		}
		return null;
	}
}



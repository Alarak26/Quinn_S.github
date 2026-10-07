/*---------------------------------------------------------------------
Name: Logan Lusk, Sean Archibald, Quinn Smith
Group #: 19
Course: CS 3230, Section 01, Spring 2024
Purpose: This class represents a Process Control Block (PCB) in the 
         MiniOS system. Each PCB contains information about a process, 
         such as its process ID (PID), name, state (READY, RUNNING, BLOCKED), 
         whether it is active, and the remaining time for execution. The 
         class provides methods for accessing and modifying these attributes.
Input: The class does not take direct input from the user. It is used 
       internally by the `ProcessManager` to track process states and 
       execution times.
Output: The class does not produce output directly. It stores process 
        information used by other classes for scheduling and memory 
        management.
---------------------------------------------------------------------*/
public class PCB {
	/**
	* Enumeration of process states.
	*/
	public enum State {
		READY, 
		RUNNING, 
		BLOCKED 
	}

	private int pid;
	private String name;
	private State state;
	private boolean active;
	private int remainingTime;

	/**
	* Constructs a PCB (Process Control Block).
	* :param pid: Process ID
	* :type pid: int
	* :param name: Name of the process
	* :type name: String
	* :param state: Initial state of the process
	* :type state: PCB.State
	* :param active: Whether the process is currently active
	* :type active: boolean
	* :param remainingTime: Time required to complete the process
	* :type remainingTime: int
	*/
	public PCB(int pid, String name, State state, boolean active, int remainingTime) {
		this.pid = pid;
		this.name = name;
		this.state = state;
		this.active = active;
		this.remainingTime = remainingTime;
	}
	
	/**
	* Returns process id
	*/
	public int getPid() {
		return pid;
	}

	/**
	* Returns process name.
	*/
	public String getName() {
		return name;
	}

	/**
	* Returns process state.
	*/
	public State getState() {
		return state;
	}

	/**
	* Sets the process state.
	* :param state: New process state
	* :type state: PCB.State
	*/
	public void setState(State state) {
		this.state = state;
	}

	/**
	* Returns if state is active (true) or inactive (false).
	*/
	public boolean isActive() {
		return active;
	}

	/**
	* Sets the active status of the process.
	* :param active: New process activity state
	* :type active: boolean
	*/
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	* Returns remaining time for a process.
	*/
	public int getRemainingTime() {
		return remainingTime;
	}

	/**
	* Decrements the remaining execution time.
	* :param amount: Amount of time to decrement
	* :type amount: int
	*/
	public void decrementTime(int amount) {
		this.remainingTime -= amount;
	}

}


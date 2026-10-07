/*---------------------------------------------------------------------
Name: Logan Lusk, Sean Archibald, Quinn Smith
Group #: 19
Course: CS 3230, Section 01, Spring 2024
Purpose: This class implements a semaphore to control concurrent access 
         to shared resources within the MiniOS system. It ensures that 
         no more than two processes can run concurrently by using the 
         `waitSem` and `signal` methods. This prevents resource contention 
         and ensures that the system handles processes in a controlled 
         manner.
Input: The class takes an integer value for the initial count of the 
       semaphore, which determines the number of processes that can 
       run concurrently.
Output: The class does not produce direct output. Instead, it controls 
        access to critical sections of the program by blocking or 
        allowing processes based on the semaphore's count.
---------------------------------------------------------------------*/
public class Semaphore {
	private int count;

	/**
	* Constructs a semaphore with an initial count.
	* :param initial: Initial semaphore count
	* :type initial: int
	*/
	public Semaphore(int initial) {
		this.count = initial;
	}

	/**
	* Wait operation on the semaphore. Blocks if count <= 0.
	*/
	public synchronized void waitSem() {
		while (count <= 0) {
			try {
				wait();
			} catch (InterruptedException ignored) {}
		}
		count--;
	}
	
	/**
	* Signal (V) operation on the semaphore. Increments count and notifies waiting threads.
	*/
	public synchronized void signal() {
		count++;
		notify();
	}
}


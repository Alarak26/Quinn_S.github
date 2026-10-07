/*---------------------------------------------------------------------
Name: Logan Lusk, Sean Archibald, Quinn Smith
Group #: 19
Course: CS 3230, Section 01, Spring 2024
Purpose: This class is responsible for managing memory allocation 
         within the MiniOS system. It simulates a simple memory 
         manager with a fixed-size memory array, allocating and freeing 
         memory for processes. The class provides methods for allocating 
         memory blocks to processes, freeing memory when processes complete, 
         and displaying the current memory layout.
Input: The class does not take direct input from the user. Instead, 
       memory is managed through interactions with the `ProcessManager` 
       class, which requests memory allocation and freeing by PID.
Output: The class outputs the success or failure of memory allocation 
        and frees memory when processes complete. The memory layout 
        can also be printed.
---------------------------------------------------------------------*/
public class MemoryManager {
	private final int[] memory = new int[100];
	
	/**
	* Attempts to allocate a block of memory to the given PID.
	* :param pid: Process ID to allocate memory for
	* :type pid: int
	* :param size: Number of memory units to allocate
	* :type size: int
	*/
	public void allocate(int pid, int size) {
		int start = -1;
		int count = 0;
		for (int i = 0; i < memory.length; i++) {
			if (memory[i] == 0) {
				if (start == -1) { 
					start = i; 
				}
        	        	count++;
        	        	if (count == size) { 
        	        		break; 
        	        	}
			} 
			else {
				start = -1;
				count = 0;
			}
		}
		if (count == size) {
			for (int i = start; i < start + size; i++) {
				memory[i] = pid;
			}
			System.out.println("Allocated " + size + " Units to PID " + pid);
		} 
		else {
			System.out.println("Not Enough Memory to Allocate " + size + " Units.");
		}
	}
	
	/**
	* Frees all memory units allocated to the specified process.
	* :param pid: Process ID whose memory should be freed
	* :type pid: int
	*/
	public void free(int pid) {
		boolean found = false;
		for (int i = 0; i < memory.length; i++) {
			if (memory[i] == pid) {
				memory[i] = 0;
				found = true;
			}
		}
	}

	/**
	* Prints the current state of memory in rows of 10 units per line.
	*/
	public void printMemory() {
		int memCount = 0;
		System.out.print("Memory: \n");
		for (int i = 0; i < memory.length; i++) {
			memCount++;
			System.out.print(memory[i]);
			if (i < memory.length - 1) {
				System.out.print(",");
			}
			if (memCount % 10 == 0) {
				System.out.print("\n");
			}
		}
	}
}


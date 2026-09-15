package taskManager;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Declaring a new task manager 
		TaskManager manager = new TaskManager();
		Scanner scanner = new Scanner(System.in);
		
		// Program loop 
		while (true) {
			System.out.println("\n--------TASK MANAGER--------");
			System.out.println("1 Add task.");
			System.out.println("2 Complete task.");
			System.out.println("3 Check all Tasks.");
			System.out.println("4 Find task by id.");
			System.out.println("5 Undo last action.");
			System.out.println("6 Process next pending task. ");
			System.out.println("7 List all tasks by priority. ");
			System.out.println("0 Exit.\n");
			System.out.println("Choose an option (0-6): ");
			
			// int choice = scanner.nextInt();
			int choice = Integer.parseInt(scanner.nextLine().trim()); // Avoids having to clean the buffer
			
			// Switch according to user choice
			switch (choice) {
			
				// Adds a task to the task list 
				case 1: {
					System.out.println("Describe the task:");
					String description = scanner.nextLine();	// Gets task description
					System.out.println("Now give it a priority from 1-5 ( 1 - Highest) :");
					int priority = Integer.parseInt(scanner.nextLine().trim());	// Gets task priority
					// Create a Task and send it to manager to handle it
					Task task = manager.addTask(description, priority);
					// Print confirmation
					System.out.println("Task added succesfuly.\n");
					System.out.println(" " + task);
					break;
				}
				
				// Mark a task as completed 
				case 2: {
					System.out.println("Insert Task ID to complete: ");
					List<Task> tasks = manager.getTasks();
					int idToComplete = Integer.parseInt(scanner.next().trim());
					boolean success = manager.completeTask(idToComplete);
					// Check if id corresponds to a task and that there is tasks 
					System.out.println( success ? "\nTask " + idToComplete + " marked as done " : " Task not found or already completed.");
					break;
				}
				
				// Get all the tasks 
				case 3: {
					List<Task> tasks = manager.getTasks();
					// First check if it is not empty
					if ( tasks.isEmpty()) {
						System.out.println("No tasks yet.");
					} else {
						// Print tasks
						for (Task task : tasks) {
							System.out.println("\n" + task);
						}
					}
					break;
				}
				
				// Find task by ID case 
				case 4: {
					System.out.println("Insert ID to look for : ");
					int idToSearch = Integer.parseInt(scanner.nextLine().trim());
					Task task = manager.findById(idToSearch);
					if ( task != null ) {
						System.out.println(task);
					} else {
						System.out.println("Task not found.");
					}
					break;
				}
				
				// Undo last action 
				case 5: {
					manager.undo();
					break;
				}	
				
				// Process next pending task using  a queue 
				case 6 : {
					Task task = manager.processNextPending();
					System.out.println(task !=null ? "Next pending task: \n" + task : "No pending tasks.");
					break;
				}
				
				// List all tasks by priority using a Binary Search Tree
				case 7 : {
					
					break;
				}
					
				// Exit program
				case 0: {
					System.out.println("\n-------- SEE YA LATER! --------");
					return;
				}
				
				default:
					System.out.println("\nInvalid input, try again.");
			}
		}
	}

}
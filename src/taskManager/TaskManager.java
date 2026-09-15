package taskManager;

import java.util.List;

public class TaskManager {
	
	// Creates a HashTable of capacity of 50 tasks 
	private TaskHashTable  tasksByID = new TaskHashTable(10);
	private TaskQueue pendingQueue = new TaskQueue(); 
	private int nextId = 1;
	
	// Method to add a task to the bucket 
	public Task addTask(String description, int priority) {
		
		// First create and declare a new Task object with an increased id
		Task task = new Task(nextId++, description, priority);
		
		// Add it to the TaskHashTable object 
		tasksByID.put(task);
		
		// Add task to the pending queue 
		pendingQueue.enqueue(task);
		
		// Return task added 
		return task;
	}
	
	// Marks a task as done by ID. O(1) thanks to the hash table --
	// no need to scan the queue or tree to find it.
	public boolean completeTask(int id) {
		// Search the HashTable for the entry with the given ID
		Task task = tasksByID.get(id);
		
		// If there is no task with given ID or is already marked as done
		if ( task == null || task.done ) {
			return false;
		}
		
		// Sets the task as done if it passes the previous check 
		task.done = true;
		return true;
	}

	// Takes the next pending task off the queue (FIFO) 
	public Task processNextPending() {
		if (pendingQueue.isEmpty()) {
			return null;
		}
		return pendingQueue.dequeue();
	}
	
	// Returns tasks given an id
	public Task findById(int id) {
		return tasksByID.get(id);
	}
	
	// Returns all the tasks stored 
	public List<Task> getTasks() {
		return tasksByID.getAllTasks();
	}
	
}

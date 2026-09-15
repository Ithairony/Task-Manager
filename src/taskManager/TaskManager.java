package taskManager;

import java.util.List;

public class TaskManager {
	
	// Creates a HashTable of capacity of 50 tasks 
	private TaskHashTable  tasksByID = new TaskHashTable(10);
	private TaskQueue pendingQueue = new TaskQueue(); 
	private ActionStack history = new ActionStack();
	private TaskBinarySearchTree priorityTree = new TaskBinarySearchTree();
	private int nextId = 1;
	
	// Method to add a task to the bucket 
	public Task addTask(String description, int priority) {
		
		// First create and declare a new Task object with an increased id
		Task task = new Task(nextId++, description, priority);
		
		// Add it to the TaskHashTable object 
		tasksByID.put(task);
		
		// Add task to the pending queue 
		pendingQueue.enqueue(task);
		
		// Add action to history
		history.push(new ActionStack.Action("ADD", task.id));
		
		//  Add task priority to bst
		priorityTree.insert(task);
		
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
		// Updates history stack with action for the task being done 
		history.push(new ActionStack.Action("DONE", task.id));
		
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
	
	// Undo the most recent action
	public void undo() {
		// Check if there actions to undo 
		if ( history.isEmpty() ) {
			System.out.println("Nothing to undo");
			return;
		}
		
		// Removes and takes the most recent action 
		ActionStack.Action action = history.pop();
		// Gets the ID to search for the task on the HashTable
		Task task = tasksByID.get(action.taskID);
		
		// Checks if task is in the HashTable 
		if ( task == null) {
			return;
		}
		
		// If the last action is "ADD"
		if (action.type.equals("ADD")) {
			// Remove Task from the HashTable 
			tasksByID.remove(task.id);
			System.out.println("Undid adding task: " + task.description);
			// If the last action is "COMPLETE"
		} else if ( action.type.equals("COMPLETE") ) {
			// Set done to false 
			task.done = false;
			System.out.println("Undid completing task: " + task.description);
		}
	}
	
	// Lists all the tasks added organized by priority ( Includes done and undone ) 
	public List<Task> listByPriority() {
		return priorityTree.inOrderList();
	}
}

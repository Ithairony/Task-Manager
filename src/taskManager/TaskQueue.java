package taskManager;

public class TaskQueue {
	
	private Node front;
	private Node rear;
	private int size;
	
	// Node class
	private static class Node {
		// Attributes
		Task task;
		Node next;
		
		// Constructor 
		Node(Task task) {
			this.task = task;
		}	
	}
	
	public void enqueue(Task task) {
		// Create a new node to store task
		Node newNode = new Node(task);
		// Check if node is empty
		if ( rear == null) {
			// If it is 
			front = rear = newNode;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
		// Increment size of the queue 
		size++;
	}
	
	public Task dequeue() {
		// First checks if its empty 
		if (isEmpty()) {
			throw new RuntimeException("No pending tasks in queue.");
		}
		
		Task taskToRemove = front.task;
		front = front.next;	// Update the front node to the next one
		// Checks if after the update the queue becomes empty again
		if (front == null) { 
			rear = null;
		}
		size--;
		return taskToRemove;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
						
}
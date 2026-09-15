package taskManager;

public class ActionStack {
	
	// Action class to create actions 
	public static class Action {
		
		// Attributes
		String type;
		int taskID;
		
		// Constructor of Action
		public Action(String type, int taskID) {
			this.taskID = taskID;
			this.type = type;
		}
	}
	
	// Node class to create nodes
	private static class Node {
		
		// Attributes
		Action action;
		Node next;
		
		// Node constructor
		Node(Action action) {
			this.action = action;
		}
	}
	
	private Node top; // Top since it is a stack 
	
	public void push(Action action) {
		Node newNode = new Node(action);
		newNode.next = top;
		top = newNode;	// Updates the new created node to being the top node 
	}
	
	public Action pop() {
		// Check if there is something to pop 
		if (isEmpty()) {
			throw new RuntimeException("Nothing to undo");
		}
		Action action = top.action;
		top = top.next;
		return action;
	}

	public boolean isEmpty() {
		return top == null;
	}
	
}
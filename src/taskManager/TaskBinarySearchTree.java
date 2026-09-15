package taskManager;

import java.util.ArrayList;
import java.util.List;


public class TaskBinarySearchTree {

	// Declaring a node class
	private static class Node {

		// Attributes
		Task task;
		Node left;
		Node right;

		// Constructor 
		Node(Task task) {
			this.task = task;
		}

	}

	private Node root;



	public void insert(Task task) {
		root = insertRec(root, task);
	}

	public Node insertRec(Node node, Task task) {

		if (node == null) {
			return new Node(task); // found the empty spot; place it here
		}

		// Now organize by priority 
		if ( task.priority < node.task.priority ) {
			node.left = insertRec(node.left, task); // Inserts value to the left of the root
		} else  {
			node.right = insertRec(node.right, task);	// Inserts value to the right of the root 
		}
		return node;
	}

	public List<Task> inOrderList() {
		List<Task> result = new ArrayList<>();
		inOrderRec(root, result);
		return result;
	}

	public static void inOrderRec(Node node, List<Task> result) {
		if (node == null) return;
		inOrderRec(node.left, result);
		result.add(node.task);
		inOrderRec(node.right, result);

	}
	
}
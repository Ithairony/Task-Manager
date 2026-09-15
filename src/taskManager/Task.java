package taskManager;

public class Task {
	
	// Attributes 
	int id;
	String description;
	int priority;
	boolean done;
	
	
	// Constructor 
	public Task(int id, String description, int priority) {
		this.id = id;
		this.description = description;
		this.priority = priority;
		this.done = false;
	}
	
	public String toString() {
							// Regex [%d] : decimals only %-30s : Left alignment and 30 whitespace for description 
		return  String.format("[%d] %-30s Priority: %d%s",
	            id, description, priority, done ? " (DONE)" : "");
	}
}

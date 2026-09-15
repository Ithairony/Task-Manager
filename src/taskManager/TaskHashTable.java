package taskManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskHashTable {

	LinkedList<Task>[] buckets;
	private int capacity;
	
	// Constructor 
	@SuppressWarnings("unchecked")
	public TaskHashTable(int capacity) {
		this.capacity = capacity;
		this.buckets = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			buckets[i] = new LinkedList<>();
		}
	}
	
	// Maps a key to a bucket index. Math.abs guards against negative since hash codes can be negative 
	private int hash(int id) {
		return Math.abs(Integer.hashCode(id)) % capacity;
	}
	
	// Method to insert a task 
	public void put(Task task) {
		int index = hash(task.id);
		buckets[index].add(task);
	}
	
	// Method to retrive tasks by id
	public Task get(int id) {
		int index = hash(id);
		for ( Task task : buckets[index]) {
			if ( task.id == id) {
				return task;
			}
		}
		return null; // If task with given ID is not found
	}
	
	public void remove(int id) {
		int index = hash(id);
		buckets[index].removeIf(task -> task.id == id);
		
	}

	// Returns all tasks stored
	public List<Task> getAllTasks() {
		List<Task> allTasks = new ArrayList<>();
		for (LinkedList<Task> bucket : buckets) {
			allTasks.addAll(bucket);
		}
		return allTasks;
	}
	
	
}

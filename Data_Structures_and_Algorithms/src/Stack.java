import java.util.*;

import other_ds.LinkedList;


public class Stack<T> {
	LinkedList<T> stack = new LinkedList<T>(); 
	public T peek; 
	public int size; 
	
	public Stack() { 
		peek = null; 
		size = 0; 
	}
	
	public void push(T element) { 
		stack.insertLast(element);
	}
	
	public T pop() {
		return stack.getValue(0);
	}
	
	public int size() { 
		return size; 
	}
	
	public T peek() { 
		return peek; 
	}
}

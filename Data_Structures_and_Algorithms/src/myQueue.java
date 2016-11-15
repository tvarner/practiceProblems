
public class myQueue<T> { 

	public Stack<T> stack1 = new Stack<T>(); 
	public Stack<T> stack2 = new Stack<T>(); 
	public T peek = null; 

	public myQueue() {}; 

	public void enqueue(T element) { 
		if(stack2.size() == 0) {
			stack1.push(element);
			if(stack1.size() == 0) { 
				peek = element; 
			}
		} else { 
			while(stack2.size() != 0) { 
				stack1.push(stack2.peek());
				stack2.pop(); 
			}
			stack1.push(element); 
		}
	}

	public void dequeue() { 
		while(stack1.size() != 0) { 
			stack2.push(stack1.peek()); 
			stack1.pop();
		}
		stack2.pop(); 
		peek = stack2.peek();
	}

	public T peek() { 
		return peek; 
	}

	public int size() { 
		if(stack2.size() == 0) { 
			return stack1.size(); 
		} else { 
			return stack2.size(); 
		}
	}
} 
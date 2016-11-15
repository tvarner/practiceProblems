import java.util.LinkedList;


public class FastStack {

	LinkedList<Integer> stack = new LinkedList<Integer>(); 
	LinkedList<Integer> minStack = new LinkedList<Integer>();
	
	
	public FastStack() { }
	
	public void push(Integer num) {	
		if (stack.size() == 0 || 
				num <= minStack.getFirst()) { 
			minStack.addFirst(num);
		} 
		stack.addFirst(num);
	}
	
	public void pop() { 	
		if (stack.size() == 1 || 
				stack.getFirst() == minStack.getFirst()) { 
			minStack.removeFirst();
		}
		stack.removeFirst();
	}
	
	public Integer getMin() { 
		return minStack.getFirst();
	}
	
	public static void main(String[] args) { 
		
		FastStack stack = new FastStack();
		
		stack.push(8);
		stack.push(4);
		stack.push(12);
		stack.push(7);
		stack.push(-7);
		stack.push(13);
		
		System.out.println(stack.getMin());
		
		stack.pop();
		stack.pop();
		
		System.out.println(stack.getMin());
	}
}

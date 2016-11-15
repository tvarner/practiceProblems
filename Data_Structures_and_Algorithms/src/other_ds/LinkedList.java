package other_ds;

public class LinkedList<T> {

	private Node<T> root;
	private int listCount = 0; 
	
	public LinkedList() { 
		
	}
	
	public LinkedList(T value) { 
		root = new Node<T>(value);
		listCount++; 
	} 
	
	public boolean search(T value) {
		Node<T> current = root; 
		while(current != null) { 
			if(current.get().equals(value)) { 
				return true; 
			} else { 
				current = current.getSuccessor(); 
			}
		}
		return false; 
	}
	
	public void insertFirst(T value) { 
		Node<T> newNode = new Node<T>(value); 
		if(root == null) { 
			root = newNode; 
		} else { 
			root.setPredecessor(newNode);
			newNode.setSuccessor(root);
			root = newNode; 
		}
		listCount++; 
	}
	
	public void insertLast(T value) { 
		Node<T> newNode = new Node<T>(value); 
		Node<T> current = root; 
		
		if(root == null) { 
			insertFirst(value); 
			return; 
		}
		
		while(current.getSuccessor() != null) { 
			current = current.getSuccessor(); 
		}
		
		newNode.setPredecessor(current);
		current.setSuccessor(newNode);
		listCount++; 
	}
	
	public void insert(int index, T value) {
		Node<T> newNode = new Node<T>(value); 
		Node<T> current = root; 
		
		if(index == 0) { 
			insertFirst(value); 
			return; 
		} else { 
			current = get(index); 
		}
		
		if(current == null) { 
			insertLast(value); 
		} else { 
			newNode.setPredecessor(current.getPredecessor());
			current.getPredecessor().setSuccessor(newNode);
			newNode.setSuccessor(current);
			current.setPredecessor(newNode);
		}
		listCount++; 
	}
	
	public void delete(int index) {
		Node<T> current = get(index);
		if(index == 0) {
			root = root.getSuccessor(); 
			root.getPredecessor().setSuccessor(null);
			root.setPredecessor(null);
		} else { 
			current.getSuccessor().setPredecessor(current.getPredecessor());
			current.getPredecessor().setSuccessor(current.getSuccessor());
		}
	}
	
	public Node<T> get(int index) {
		Node<T> current = root;  
		if (index <= 0) { 
			return root; 
		} else if(index > listCount) { 
			return null;  
		} else {
			for(int i = 0; i < index; i++) { 
				current = current.getSuccessor(); 
			}
		}
		return current; 
	}
	
	public T getValue(int index) { 
		return get(index).value;
	}
	
	public void printList() { 
		Node<T> current = root; 
		while(current != null) { 
			System.out.println(current.get()); 
			current = current.getSuccessor(); 
		}
	}
	
	private class Node<T> { 
		
		private T value; 
		
		private Node<T> pred = null; 
		private Node<T> succ = null; 
		
		public Node(T t) { 
			value = t; 
		}
		
		public void set(T t) { 
			value = t; 
		}
		public void setPredecessor(Node<T> pred) { 
			this.pred = pred; 
		}	
		public void setSuccessor(Node<T> succ) { 
			this.succ = succ; 
		}
		
		public T get() { 
			return value; 
		}
		public Node<T> getPredecessor() { 
			return pred; 
		}
		public Node<T> getSuccessor() { 
			return succ; 
		}
	}
}

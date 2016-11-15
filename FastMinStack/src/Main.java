import java.util.HashSet;

public class Main {
	
	static node root = null;
	
	public static node removeDups(node head) { 
		
		if (head == null) { 
			return null;
		}
		node curr = head;
		while (curr != null) { 
			node runner = curr;
			while (runner.next != null) { 
				if (runner.next.value == curr.value) { 
					runner.next = runner.next.next;
				} else { 
					runner = runner.next;
				}
			}
			curr = curr.next;
		}
		return head;
	}
	
	public static node removeDups2(node head) { 
		HashSet<Integer> set = new HashSet<Integer>();
		node curr = head;
		node prev = null;
		while (curr != null) { 
			if (set.contains(curr.value)) { 
				prev.next = curr.next;
			} else { 
				set.add(curr.value);
				prev = curr;
			}
			curr = curr.next;
		}
		return head;
	}
	
	public static node reverseLL(node head) { 
		
		node curr = head;
		node prev = null;
		
		while (curr != null) { 
			node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		head = prev;
		return head;
	}
	
	public static void printLinkedList(node head) { 
		
		while (head != null) { 
			if (head.next == null) { 
				System.out.print(head.value);
			} else { 
				System.out.print(head.value + "-->");
			}
			
		}
	}

	public static void createLL(node root) { 
		
		node head = new node(2, null, null);
		node n1 = new node(5, null, null);
		node n2 = new node(6, null, null);
		node n3 = new node(9, null, null);
		node n4 = new node(5, null, null);
		node n5 = new node(2, null, null);
		node n6 = new node(8, null, null);
		node n7 = new node(3, null, null);
		node n8 = new node(3, null, null);
		node n9 = new node(2, null, null);

		head.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		
		root = head;
	}
	
	
	public class node { 
		
		Integer value;
		node next;
		node previous;
		
		public node() { 
			value = null;
			next = null;
			previous = null;
		}
		
		public node(Integer value, node next, node previous) { 
			this.value = value;
			this.next = next;
			this.previous = previous;
		}
	}
	
	public static void main(String[] args) { 
		
		Main m = new Main();
		createLL(m.root);
		
	}
}



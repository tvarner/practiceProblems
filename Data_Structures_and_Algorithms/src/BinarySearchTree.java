import java.util.*;


public class BinarySearchTree {
	public BSTNode root = null; 
	ArrayList<ArrayList<BSTNode>> levels;
	
	public BinarySearchTree(Integer value) { 
		insert(value);
	}
	
	public BinarySearchTree(ArrayList<Integer> numbers) { 
		for (Integer number: numbers) { 
			insert(number);
		}
	}
	
	public void insert(Integer value) { 
		BSTNode newBSTNode = new BSTNode(value); 
		if(root == null) { 
			root = newBSTNode; 
			return; 
		}
		
		BSTNode previous = root.parent; 
		BSTNode current = root;
		
		while(current != null) { 
			previous = current; 
			if(value <= current.value) { 
				current = current.left; 
			} else { 
				current = current.right; 
			}
		}
		current = newBSTNode; 
		current.parent = previous;
		if(current.value < previous.value) { 
			previous.left = current; 
		} else { 
			previous.right = current; 
		}
	}
	
	public void delete(Integer value) { 
		BSTNode current = search(value); 
		if(current.left == null) { 
			transplant(current, current.right);
		} else if(current.right == null) { 
			transplant(current, current.left); 
		} else { 
			BSTNode min = findMin(current.right); 
			if(min.parent != current) { 
				transplant(min, min.right); 
				min.right = current.right; 
				min.right.parent = min; 
			}
			transplant(current, min); 
			min.left = current.left; 
			min.left.parent = min; 
		}
	}
	
	public void transplant(BSTNode u, BSTNode v) { 
		if(u.parent == null) { 
			root = u; 
		} else if(u.value == u.parent.left.value) { 
			u.parent.left = v; 
		} else { 
			u.parent.right = v; 
		}
		if(v != null) { 
			v.parent = u.parent; 
		}
	}
	
	public BSTNode findMin(BSTNode root) { 
		BSTNode current = root; 
		while(current.left != null) { 
			current = current.left; 
		}
		return current; 
	}
	
	public BSTNode findMax(BSTNode root) { 
		BSTNode current = root; 
		while(current.right != null) { 
			current = current.right; 
		}
		return current; 
	}
	
	public BSTNode findSuccessor(BSTNode root) { 
		if(root == null) { 
			return null; 
		}
		if(root != null) { 
			return findMin(root.right); 
		}
		BSTNode x = root; 
		BSTNode y = root.parent;
		while(y != null && x == y.right) { 
			x = y; 
			y = y.parent; 
		}
		return y; 
	}
	
	public Integer get(Integer value) { 
		BSTNode current = search(value); 
		return current.value; 
	}
	
	public BSTNode search(Integer value) { 
		BSTNode current = root; 
		while(current != null) { 
			if(value < current.value) { 
				current = current.left; 
			} else { 
				current = current.right; 
			}
		}
		return current; 
	}
	
	/*
	public int findInRange() { 
		
	}
	*/
	
	public void bstSort(BSTNode x) { 
		if (x.value == null) { 
			return;
		}
		bstSort(x.left); 
		System.out.println(x.value);
		bstSort(x.right);
	}
	/*
	public int[] inOrderTraversal() { 
		
	}
	
	public int[] preOrderTraversal() {
		
	}
	
	public int[] postOrderTraversal() { 
		
	}
	*/
	
	public void printBST(BSTNode x) { 
		
		if (x.value == null) { 
			return;
		}
		
		System.out.println("value: " + x.value);
		System.out.println("left child " + x.left.value);
		System.out.println("right child " + x.right.value); 
		System.out.println();
		
		printBST(x.left); 
		printBST(x.right);
	}
	
	public class BSTNode { 
		Integer value = null;
		Integer height = -1;
		public BSTNode parent = null; 
		public BSTNode left = null; 
		public BSTNode right = null;
		
		public BSTNode () {
		}
		
		public BSTNode (Integer value) { 
			this.value = value; 
		}
	}
}

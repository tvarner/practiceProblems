package BinarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class BST {

	TreeNode root = null;
	TreeNode nil = new TreeNode(null);
	HashMap<Integer, TreeNode> nodes = new HashMap<Integer, TreeNode>();
	
	public BST() { 
		nil.height = -1;
		root = nil;
	}
	
	public void insert(Integer value) { 
		insert(new TreeNode(value));
	}
	
	public void insert(TreeNode n) { 
		TreeNode prev = nil;
		TreeNode curr = root;		
		while (curr != nil) { 
			prev = curr;
			if (n.value < curr.value) { 
				curr = curr.left;
			} else { 
				curr = curr.right;
			}
		}
		n.parent = prev;
		if (prev == nil) { 
			setRoot(n);
		} else if (n.value < prev.value) { 
			prev.left = n;
		} else { 
			prev.right = n;
		}
		setLeaf(n);
		setHeight(n);
		fixUp(n);
	}
	
	public void remove(Integer value) {	
		TreeNode z = get(value);
		if (z.left == nil) { 
			transplant(z, z.right);
			setHeight(z.right);
			fixUp(z.right);
		} else if (z.right == nil) { 
			transplant(z, z.left);
			setHeight(z.left);
			fixUp(z.left);
		} else { 
			TreeNode y = getMin(z.right);
			if (y.parent != z) { 
				transplant(y, y.right);
				setHeight(y.right);
				fixUp(y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			setHeight(y);
			fixUp(y);
		}
	}
	
	public boolean contains(Integer value) { 		
		TreeNode curr = root;
		while (curr != nil) { 
			if (curr.value == value) { 
				return true;
			}
			if (value < curr.value) { 
				curr = curr.left;
			} else { 
				curr = curr.right;
			}
		}
		return false;
	}
	
	private TreeNode get(Integer value) { 
		TreeNode curr = root;
		while (curr != nil) { 
			if (value == curr.value) { 
				return curr;
			}
			if (value < curr.value) { 
				curr = curr.left;
			} else { 
				curr = curr.right;
			}
		}
		return null;
	}
	
	private void setRoot(TreeNode n) { 
		root = n;
		root.parent = nil;
	}
	
	private void setLeaf(TreeNode n) { 
		n.left = nil;
		n.right = nil;
	}
	
	private void setHeight(TreeNode n) { 		
		if (n.equals(nil)) { 
			return;
		}	
		n.height = 1 + Math.max(n.left.height, n.right.height);
		setHeight(n.parent);
	}
	
	public TreeNode getMin(TreeNode n) { 
		if (n == nil) { 
			return null;
		}		
		TreeNode curr = n;		
		while(curr.left != nil) { 
			curr = curr.left;
		}	
		return curr;
	}
	
	private void transplant(TreeNode u, TreeNode v) { 
		if (u.parent == nil) { 
			setRoot(v);			
		} else if (u == u.parent.left) { 
			u.parent.left = v;
		} else { 
			u.parent.right = v;
		}
		if (v != nil) { 
			v.parent = u.parent;
		}
	}
	
	private void fixUp(TreeNode n) { 	
		while (n != nil) {
			if (Math.abs(n.height - n.right.height) > 2) { 
				if (n.left.right.height > n.left.left.height) { 
					n.left = rotateLeft(n.left);
				}
				n = rotateRight(n);
				//curr = curr.parent;
			} else if (Math.abs(n.height - n.left.height) > 2) { 
				if (n.right.left.height > n.right.right.height) { 
					n.right = rotateRight(n.right);
				}
				n = rotateLeft(n);
				//curr = curr.parent;
			}	
			n = n.parent;
		}
	}
	
	private TreeNode rotateLeft(TreeNode n) { 
		TreeNode pivot = n.right;
		n.right = pivot.left;
		if (pivot.left != nil) { 
			pivot.left.parent = n;
		}
		transplant(n, pivot);
		pivot.left = n;
		n.parent = pivot;
		setHeight(n);
		if (n.right == nil && n.left == nil) setLeaf(n);
		return pivot;
	}
	
	private TreeNode rotateRight(TreeNode n) { 
		TreeNode pivot = n.left;
		n.left = pivot.right;
		if (pivot.right != nil) { 
			pivot.right.parent = n;
		}
		transplant(n, pivot);
		pivot.right = n;
		n.parent = pivot;
		setHeight(n);
		if (n.right == nil && n.left == nil) setLeaf(n);
		return pivot;
	}
	
	private TreeNode getSuccessor(TreeNode n) { 
		if (n.right != nil) { 
			return getMin(n.right);
		} else if (n.parent != nil) { 
			TreeNode curr = n.parent;
			if (n == curr.left) { 
				return curr;
			} else { 
				while (curr != nil) { 
					curr = curr.parent;
					if (curr != nil) { 
						if (curr.value > n.value) { 
							return curr;
						}
					}
				}
				return curr;
			}
		} else { 
			return nil;
		}
	}

	public void getLevels2() { 
		if(root == nil) {return;}
		
		int height = 0;
		ArrayList<ArrayList<TreeNode>> levels = new ArrayList<ArrayList<TreeNode>>();
		ArrayList<TreeNode> level = new ArrayList<TreeNode>();
		LinkedList<TreeNode> currLevel = new LinkedList<TreeNode>();
		LinkedList<TreeNode> nextLevel = new LinkedList<TreeNode>();
		currLevel.addLast(root);
		
		while(currLevel.isEmpty() == false) { 
			TreeNode curr = currLevel.removeFirst();
			level.add(curr);
			if (curr.left != nil) { 
				nextLevel.addLast(curr.left);
			} 
			if (curr.right != nil) { 
				nextLevel.addLast(curr.right);
			}
			if (currLevel.isEmpty() == true) { 
				levels.add(level);
				level = new ArrayList<TreeNode>();
				currLevel = nextLevel;
				nextLevel = new LinkedList<TreeNode>();
				height++;
			}
		}
		
		System.out.println("height: " + height);
		for(ArrayList<TreeNode> l : levels) { 
			for(TreeNode t : l) { 
				System.out.print(t.value + "(" + t.height + ")" + " ");
			}
			System.out.println();
		}
	}
	
	/*
	 * Applied BFS on tree from root.
	 */
	public void getLevels() { 	
		if (root == null) { 
			return;
		}		
		ArrayList<ArrayList<TreeNode>> levels = new ArrayList<ArrayList<TreeNode>>();
		LinkedList<TreeNode> currLevel = new LinkedList<TreeNode>();
		LinkedList<TreeNode> nextLevel = new LinkedList<TreeNode>();		
		currLevel.add(root);		
		while (!currLevel.isEmpty()) { 
			ArrayList<TreeNode> level = new ArrayList<TreeNode>();
			Iterator<TreeNode> iter = currLevel.iterator();
			while (iter.hasNext()) { 
				TreeNode curr = iter.next();
				level.add(curr);
				if (curr.left != nil) { 
					nextLevel.add(curr.left);
				}
				if (curr.right != nil) { 
					nextLevel.add(curr.right);
				}
			}
			levels.add(level);
			currLevel = nextLevel;
			nextLevel = new LinkedList<TreeNode>();
		}		
		for (ArrayList<TreeNode> l: levels) { 
			for (TreeNode n: l) { 
				System.out.print(n.value + "(" + n.height + ")" + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) { 
		
		BST tree = new BST();
		
		tree.insert(15);
		tree.insert(14);
		tree.insert(13);
		tree.insert(12);
		tree.insert(11);
		tree.insert(10);
		tree.insert(9);
		tree.insert(8);
		tree.insert(7);
		tree.insert(6);
		tree.insert(5);
		tree.insert(4);
		tree.insert(3);
		tree.insert(2);
		tree.insert(1);
		tree.insert(16);
		//tree.remove(8);
		tree.getLevels2();




		
		PreOrderIterator iter = new PreOrderIterator(tree);
		while (iter.hasNext()) { 
			TreeNode n = iter.next();
			System.out.print(n.value + " ");
		}
		
		
	}
}

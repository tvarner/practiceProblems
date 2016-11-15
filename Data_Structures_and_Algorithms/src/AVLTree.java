import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class AVLTree extends BinarySearchTree{
	
	public BSTNode nil;
	
	public AVLTree(Integer value) {
		super(value);
		super.root.parent = nil;
		// TODO Auto-generated constructor stub
	}
	
	public AVLTree(ArrayList<Integer> values) { 
		super(values);
		super.root.parent = nil;
	}
	
	public void left_rotate(BSTNode x) { 
		BSTNode y = x.right; // set y 
		x.right = y.left; // set y's left subtree to x's right subtree
		if (y.left.value != null) { 
			y.left.parent = x; // set x to be parent of y's left subtree
		}
		y.parent = x.parent; // set x's parent to be y's parent
		if (x.parent.value == null) { // if x has no parent, then it is the root
			setRoot(y); // set y to be the new root in this case
		} else if (x.value == x.parent.left.value) { // if x is a left child (l.t. parent)
			x.parent.left = y; // set y to be the new left child of x's parent
		} else { 
			x.parent.right = y; // else, set y to be the new right child of x's parent
		}
		y.left = x; // set x to be y's new left child
		x.parent = y; // set y as x's new parent
		
		x.height = y.height - 1; // correct height or rotated node x
	}

	public void right_rotate(BSTNode y) { 
		BSTNode x = y.left; // set x
		y.left = x.right; // set x's right child to y's left child
		if ( x.right.value != null) { //if x has a right child, set y to be 
			x.right.parent = y; // the new parent of of x's right child
		}
		if (y.parent.value == null) { 
			setRoot(x); // if y has no parent, then it is the root, so set x to be root
		} else if (y.value == y.parent.left.value) { // if y was a left child, 
			y.parent.left = x; // set x to be the new left child of y's parent
		} else { // else, y is a right child, so set x to be new right child of y's parent
			y.parent.right = x;
		}
		x.right = y; // finally, set y to x's new right child
		y.parent = x; // set x to be y's new parent
		
		y.height = x.height - 1; // correct height of rotated node y
	}
	
	@Override 
	public void insert(Integer value) { 
		BSTNode x = super.root; 
		BSTNode y = nil;
		
		if (x == null) { 
			BSTNode newNode = new BSTNode(value);
			setRoot(newNode);
			setLeaf(newNode);
			setHeight(newNode);
			//System.out.println(super.root.height);
			return;
		}

		while (x.value != null) { 
			y = x;
			if (value < x.value) { 
				x = x.left; 
			} else { 
				x = x.right;
			}
		}
		BSTNode newNode = new BSTNode(value);
		newNode.parent = y;
		if (y.value == null) {
			setRoot(newNode);
		} else if (value > y.value) { 
			y.right = newNode;
		} else { 
			y.left = newNode;
		}	
		setLeaf(newNode);
		setHeight(newNode);
		AVLFixUp(newNode);
		//System.out.println(super.root.height);
	}
	
	public void setRoot(BSTNode x) {
		if (nil == null) { 
			nil = new BSTNode();
		}
		super.root = x;
		x.parent = nil;
	}
	
	public void setLeaf(BSTNode x) { 
		if (nil == null) { 
			nil = new BSTNode();
		}
		x.left = nil;
		x.right = nil;
	}
	
	public void setHeight(BSTNode x) { 
		if (x.value == null) { 
			return;
		}
		
		x.height = 1 + Math.max(x.left.height, x.right.height);
		setHeight(x.parent);
	}
	
	public void AVLFixUp(BSTNode x) {
		
		while (x.value != null) { 
			if (Math.abs(x.left.height - x.right.height) < 2) { // balanced case
				x = x.parent;
				//System.out.println(x.height);

			} else if (Math.abs(x.height - x.right.height) > 2) { // left case
				if (Math.abs(x.left.height - x.left.left.height) > 1) { // left-right case
					left_rotate(x.left);
				}  
				right_rotate(x); // left-left case
				x = x.parent.parent;
				//System.out.println(x.value);

			} else { // right case
				if (Math.abs(x.right.height - x.right.right.height) > 1) { // right-left case
					right_rotate(x.right);
				}   
				left_rotate(x); // right-right case
				x = x.parent.parent;
				//System.out.println(x.value);

			}
		//System.out.println(x.value);
		}
	}
	
	public void printLevels() { 
		
		BSTNode curr = super.root; 
		ArrayList<ArrayList<BSTNode>> levels = new ArrayList<ArrayList<BSTNode>>();
		ArrayList<BSTNode> level = new ArrayList<BSTNode>();
		Queue<BSTNode> currLevel = new LinkedList<BSTNode>();
		Queue<BSTNode> nextLevel = new LinkedList<BSTNode>();
		currLevel.add(curr);
		
		while (!currLevel.isEmpty()) { 
			BSTNode next = currLevel.poll();
			level.add(next);
			if (next.left != null) { 
				nextLevel.add(next.left);
			}
			if (next.right != null) { 
				nextLevel.add(next.right);
			}
			
			if (currLevel.isEmpty()) { 
				currLevel = nextLevel;
				nextLevel = new LinkedList<BSTNode>();
				levels.add(level);
				level = new ArrayList<BSTNode>();
			}
		}
		
		for (ArrayList<BSTNode> l: levels) { 
			for (BSTNode node: l) { 
				System.out.print(node.value + " ");
			}
			System.out.println();
		}
	}
	
	
	@Override
	public void delete(Integer value) { 
		
	}
	
	public static void main(String[] args) { 
		AVLTree tree = new AVLTree(7);
		tree.insert(4);
		tree.insert(11);
		tree.insert(6);
		tree.insert(9);
		tree.insert(2);
		tree.insert(14);
		tree.insert(1);
		tree.insert(3);
		tree.insert(5);
		tree.insert(8);
		tree.insert(10);
		tree.insert(12);
		tree.insert(13);
		tree.insert(15);
		tree.insert(1);
		
		tree.printLevels();
	}
}

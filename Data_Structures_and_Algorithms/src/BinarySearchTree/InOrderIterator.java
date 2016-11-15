package BinarySearchTree;
import java.util.NoSuchElementException;
import java.util.Stack;


public class InOrderIterator {

	BST t; 
	boolean set = false;
	Stack<TreeNode> stack = new Stack<TreeNode>();
	
	public InOrderIterator(BST t) {
		this.t = t;
		pushLeftChildren(t.root);
	}
	
	public TreeNode next() {	
		if (!hasNext()) { 
			throw new NoSuchElementException("no more elements");
		}
		TreeNode res = stack.pop();
		pushLeftChildren(res.right);
		return res;
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	private void pushLeftChildren(TreeNode n) { 
		while (n != t.nil) { 
			stack.push(n); 
			n = n.left;
		}
	}
}

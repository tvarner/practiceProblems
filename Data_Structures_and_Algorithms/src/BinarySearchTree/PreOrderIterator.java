package BinarySearchTree;
import java.util.NoSuchElementException;
import java.util.Stack;


public class PreOrderIterator {

	BST t; 
	Stack<TreeNode> stack = new Stack<TreeNode>();
	
	public PreOrderIterator(BST t) { 
		this.t = t;
		stack.push(t.root);
	}
	
	public boolean hasNext() { 
		return !stack.isEmpty();
	}
	
	public TreeNode next() {	
		if (!hasNext()) { 
			throw new NoSuchElementException("no more elements");
		}
		TreeNode res = stack.pop();
		if (res.right != t.nil) stack.push(res.right);
		if (res.left != t.nil) stack.push(res.left);
		return res;
	}
}

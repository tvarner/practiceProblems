package BinarySearchTree;
import java.util.NoSuchElementException;
import java.util.Stack;


public class PostOrderIterator {

	BST t;
	Stack<TreeNode> stack = new Stack<TreeNode>();
	
	
	public PostOrderIterator(BST t) { 
		this.t = t;
		findNextLeaf(t.root);
	}
	
	public TreeNode next() { 
		if (!hasNext()) { 
			throw new NoSuchElementException("no more elements");
		}		
		TreeNode res = stack.pop();
		if (!stack.isEmpty()) { 
			TreeNode top = stack.peek();
			if (res == top.left) { 
				findNextLeaf(top.right);
			}
		}
		return res;
	}
	
	public boolean hasNext() { 
		return !stack.isEmpty();
	}
	
	private void findNextLeaf(TreeNode n) { 
		while (n != t.nil) {
			stack.push(n);
			if (n.left != t.nil) { 
				n = n.left;
			} else { 
				n = n.right;
			}
		}
	}
}

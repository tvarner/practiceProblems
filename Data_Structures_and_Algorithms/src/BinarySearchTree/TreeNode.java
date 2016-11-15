package BinarySearchTree;


public class TreeNode {
	
	Integer value; 
	TreeNode parent = null;
	TreeNode left = null;
	TreeNode right = null;
	Integer height = -1;
	
	public TreeNode (Integer value) {
		this.value = value;
	}
}

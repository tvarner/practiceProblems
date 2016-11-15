package Graph;
import java.util.HashMap;

public class Node { 

	int ID = this.hashCode();
	String value; 
	boolean visited = false;
	int inDegree = 0;
	int outDegree = 0;
	HashMap<Node, Integer> inNodes = new HashMap<Node, Integer>();
	HashMap<Node, Integer> outNodes = new HashMap<Node, Integer>();
	HashMap<Node, Integer> shortestPaths = new HashMap<Node, Integer>();

	public Node (String value) { 
		this.value = value;
	}
}

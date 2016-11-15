package Graph;

import java.util.ArrayList;

public class DepthFirstSearch {

	Graph g; 
	Node s;
	ArrayList<Node> traversal = new ArrayList<Node>();
	
	public DepthFirstSearch(Graph g, Node s) { 
		this.g = g;
		this.s = s;
		initialize();
		DFS(s);
	}
	
	public void initialize() { 
		for (Node n: g.nodes) { 
			n.visited = false;
		}
	}
	
	public void DFS(Node root) { 
		if (root == null) return;
		root.visited = true;
		traversal.add(root);
		for (Node n: g.adjList.get(root)) { 
			if (n.visited == false) { 
				DFS(n);
			}
		}
	}
}

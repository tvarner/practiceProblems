package Graph;

import java.util.HashSet;
import java.util.LinkedList;

public class TopologicalSort {
	
	Graph g;
	LinkedList<Node> topoSorted;
	HashSet<Node> visited;
	
	public TopologicalSort(Graph g) { 
		this.g = g;
		initialize();
		try {
			topologicalSort();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize() { 
		for (Node n: g.nodes) { 
			n.visited = false;
		}
	}
	
	public void topologicalSort() throws Exception { 

		topoSorted = new LinkedList<Node>();
		visited = new HashSet<Node>();
		for (Node n: g.nodes) { 
			if (!visited.contains(n)) { 
				visit(n);
			}
		}
	}
	
	public void visit(Node n) throws Exception { 
		if (n.visited) { 
			throw new Exception("graph cyclic");
		}	
		n.visited = true;
		for (Node v: g.adjList.get(n)) { 
			if (!visited.contains(v)) { 
				visit(v);
			}
		}
		visited.add(n);
		n.visited = false;
		topoSorted.addFirst(n);
	}
}

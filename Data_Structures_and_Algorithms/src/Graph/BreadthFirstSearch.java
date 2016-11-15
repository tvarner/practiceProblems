package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
	
	Graph g;
	Node s;
	Queue<Node> frontier = new LinkedList<Node>();
	ArrayList<Node> traversal = new ArrayList<Node>();
	
	public BreadthFirstSearch(Graph g, Node s) { 
		this.g = g;
		this.s = s;
		initialize();
		bfs();
	}
	
	public void initialize() { 
		for (Node n: g.nodes) { 
			n.visited = false;
		}
	}

	public void bfs() { 
		frontier.add(s);
		
		while (!frontier.isEmpty()) { 
			Node u = frontier.poll();
			u.visited = true;
			traversal.add(u);
			for (Node v: g.adjList.get(u)) { 
				if (v.visited == false) frontier.add(v);
			}
		}
	}
}

package Graph;
import java.util.*;

/**
 * topological sort implemented based on second alternative on 
 * wikipedia page: http://en.wikipedia.org/wiki/Topological_sorting
 * 
 * @author thomasvarner
 *
 */
public class Graph {
	
	Hashtable<Node, ArrayList<Node>> adjList = new Hashtable<Node, ArrayList<Node>>();
	ArrayList<Node> nodes = new ArrayList<Node>();
	Node root = null;
	
	public Graph() {}
	
	public void add(Node node) { 
		if (adjList.contains(node)) { 
			return;
		} else { 
			adjList.put(node, new ArrayList<Node>());
			nodes.add(node);
		}
	}
	
	public void addEdge(Node from, Node to, int distance) { 
		if (!adjList.containsKey(from)) { 
			add(from);
		}
		if (!adjList.containsKey(to)) { 
			add(to);
		}
		adjList.get(from).add(to);
		to.inDegree++;
		from.outDegree++;
		to.inNodes.put(from, distance);
		from.outNodes.put(to, distance);
	}
	
	public void remove(Node node) { 
		for (Node n: nodes) { 
			for (Node x: adjList.get(n)) { 
				if (x.equals(node)) removeEdge(n, x);
			}
		}
		adjList.remove(node);
		nodes.remove(node);
	}
	
	/* removes edge */
	public void removeEdge(Node from, Node to) { 
		adjList.get(from).remove(to);
		to.inDegree--;
		from.outDegree--;
		to.inNodes.remove(from);
		from.outNodes.remove(to);
	}
	
	public void resetVisited() { 
		for (Node node: nodes) { 
			node.visited = false;
		}
	}
	
	public boolean hasEdge(Node from, Node to) { 
		return adjList.get(from).contains(to) ? true : false;
	}
	
	public void printGraph() { 
		for (Node node: nodes) { 
			System.out.print("from: " + node.value + " |  to: ");
			for (Node m: adjList.get(node)) { 
				System.out.print(m.value + " ");
			}
			System.out.println();
		}
	}
	
	public void instantiateGraph() { 
		Node seven = new Node("7");
		Node five = new Node("5");
		Node three = new Node("3");
		Node eleven = new Node("11");
		Node eight = new Node("8");
		Node two = new Node("2");
		Node nine = new Node("9");
		Node ten = new Node("10");

		addEdge(seven, eleven, 0);
		addEdge(seven, eight, 0);
		addEdge(five, eleven, 0);
		addEdge(three, eight, 0);
		addEdge(three, ten, 0);
		addEdge(eleven, two, 0);
		addEdge(eleven, nine, 0);
		addEdge(eleven, ten, 0);
		addEdge(eight, nine, 0);
		root = seven;

	}
	
	public static void main(String[] args) { 
		Graph g = new Graph();
		g.instantiateGraph();
		
		DepthFirstSearch dfs = new DepthFirstSearch(g, g.root);
		System.out.print("DFS: ");
		for (Node n: dfs.traversal) { 
			System.out.print(n.value + " ");
		}
		System.out.println();
		
		BreadthFirstSearch bfs = new BreadthFirstSearch(g, g.root);
		System.out.print("BFS: ");
		for (Node n: bfs.traversal) { 
			System.out.print(n.value + " ");
		}
		System.out.println();
		
		TopologicalSort tps = new TopologicalSort(g);
		System.out.print("TPS: ");
		for (Node n: tps.topoSorted) { 
			System.out.print(n.value + " ");
		}
	}
}

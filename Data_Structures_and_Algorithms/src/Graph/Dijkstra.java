package Graph;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Assumes non-negative edge weights
 * 
 * @author thomasvarner
 *
 */
public class Dijkstra {
		
	Graph g;
	Node s;
	HashMap<Node, Integer> shortestPaths = new HashMap<Node, Integer>();
	HashMap<Node, Node> came_from = new HashMap<Node, Node>();
	PriorityQueue<Node> pq = new PriorityQueue<Node>(11, new myComparator());

	
	public Dijkstra(Graph g, Node s) { 
		this.g = g;
		this.s = s;
		dijkstra();
	}
	
	public void dijkstra() { 
		initialize();
		
		while (!pq.isEmpty()) { 
			Node u = pq.poll();
			for (Node v: g.adjList.get(u)) { 
				relax(u, v);
			}
		}
	}
	
	
	public void relax(Node u, Node v) { 
		if (shortestPaths.get(v) > shortestPaths.get(u) + u.outNodes.get(v)) { 
			shortestPaths.put(v, shortestPaths.get(u) + u.outNodes.get(v));
			/* edit position of v in priority queue pq */
			pq.remove(v);
			pq.add(v);
			/* record u as v's predecessor */
			came_from.put(v, u);
		}
	}

	public void initialize() { 
		for (Node n: g.nodes) { 
			if (!n.equals(s)) { 
				shortestPaths.put(n, Integer.MAX_VALUE);
			} else { 
				shortestPaths.put(n,  0);
			}
			came_from.put(n, null);
			pq.add(n);
		}
	}

	public class myComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return shortestPaths.get(o1) - shortestPaths.get(o2);		
		} 
	}
	
	public static void main(String[] args) { 
		Graph g = new Graph();
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		g.add(A);
		g.add(B);
		g.add(C);
		g.add(D);
		g.add(E);
		g.addEdge(A, B, 10);
		g.addEdge(A, C, 3);
		g.addEdge(B, D, 2);
		g.addEdge(B, C, 1);
		g.addEdge(C, B, 4);
		g.addEdge(C, D, 8);
		g.addEdge(C, E, 2);
		g.addEdge(D, E, 7);
		g.addEdge(E, D, 9);
		
		Dijkstra d = new Dijkstra(g, A);
		System.out.println("source: " + d.s.value);
		for (Node n: g.nodes) {
			System.out.print(n.value + " " + d.shortestPaths.get(n) + " ");
			if (d.came_from.get(n) != null) { 
				System.out.print(d.came_from.get(n).value + " ");
			} else { 
				System.out.print("null ");
			}
			System.out.println();
		}
	}
}

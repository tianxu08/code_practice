package graph;

import java.util.*;

public class Bipartite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * Bipartite
	 * Hard
	 * Graph
	 * Determine if an undirected graph is bipartite. 
	 * A bipartite graph is one in which the nodes can be divided into two groups 
	 * such that no nodes have direct edges to other nodes in the same group.
	 * Examples

	 *	1  --   2

    		/   

		3  --   4

		is bipartite (1, 3 in group 1 and 2, 4 in group 2).

		1  --   2

    		/   |

		3  --   4

		is not bipartite.
		Assumptions
		The graph is represented by a list of nodes, and the list of nodes is not null.
	 */
	public static void test() {
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		GraphNode n4 = new GraphNode(4);
		
		n1.neighbors.add(n2);
		n2.neighbors.add(n3);
		n3.neighbors.add(n2);
		n3.neighbors.add(n4);
		n4.neighbors.add(n3);
		
		GraphNode n5 = new GraphNode(5);
		GraphNode n6 = new GraphNode(6);
		
		n5.neighbors.add(n6);
		n6.neighbors.add(n5);
		
		List<GraphNode> graph = new ArrayList<Bipartite.GraphNode>();
		graph.add(n1);
		graph.add(n2);
		
		graph.add(n4);
		graph.add(n3);
		
		graph.add(n5);
		graph.add(n6);
		
		n3.neighbors.remove(n2);
		n2.neighbors.remove(n3);
		
		boolean isBipartite = isBipartite(graph); 
		System.out.println("isBipartite = " + isBipartite);
		
	}
	public static void printMap(HashMap<GraphNode, Integer> map) {
		System.out.println("------------------------------------");
		for(Map.Entry<GraphNode, Integer> entry : map.entrySet()) {
			System.out.print(entry.getKey().key + " ");
			System.out.print(entry.getValue());
			System.out.println();
		}
		System.out.println("====================================");
	}
	
	public static class GraphNode {
		public int key;
		public List<GraphNode> neighbors;
		public GraphNode(int key) {
			this.key = key;
			this.neighbors = new ArrayList<GraphNode>();
		}
	}
	
	// input: List<GraphNode>:  
	// 
	public static boolean isBipartite(List<GraphNode> graph) {
		// write your solution here
		// use 0 and 1 to donate two different groups
		// the map maintains for each node which group it belongs to 
		HashMap<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
		// the graph can be represented by a list of nodes (!!!if it is not guaranteed to be connected)
		// we have to do BFS from each of the nodes
		for(GraphNode node: graph) {
			if (!BFS(node, map)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean BFS(GraphNode node, HashMap<GraphNode, Integer> map) {
		// if this node has been traversed, no need to do BFS
		if (map.containsKey(node)) {
			return true;
		}
		Queue<GraphNode> q = new LinkedList<Bipartite.GraphNode>();
		q.offer(node);
		map.put(node, 0);
		while (!q.isEmpty()) {
			GraphNode cur = q.poll();
			int curSign = map.get(cur);
			int neiSign = curSign == 0 ? 1: 0;
			for (GraphNode nei : cur.neighbors) {
				if (!map.containsKey(nei)) {
					// if the neighbor has not been traversed, just put it into the queue
					// and choose the correct group
					map.put(nei, neiSign);
					// put into queue
					q.offer(nei);
				} else if (map.get(nei) != neiSign){
					// only if the neighbor has been traversed and the group doesn't match 
					// to the desired one, return false
					return false;
				}
			}
		}
		printMap(map);
		return true;
	}
}

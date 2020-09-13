package tag_union_find;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Union_Find_261_Graph_Valid_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * task261 Graph Valid Tree
	 * 
	 * Undirected graph
	 * Connected and there is NO cycle
	 */
	
	public static void test() {
		int n = 5;
		int[][] edges = {
				{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}
		};
		
		int[][] edges2 = {
				{0, 1}, {0, 2}, {0, 3}, {1, 4}
		};
		boolean rev = task261_validTree(n, edges);
		System.out.println("rev = " + rev);
		
		boolean rev2 = task261_valid_tree_uf(n, edges);
		System.out.println("rev2 = " + rev2);
	}
	
	/**
	 * 
	 * @param n
	 * @param edges
	 * @return
	 * 
	 * Time: O(V + E)
	 */
	public static boolean task261_validTree(int n, int[][] edges) {
		if (edges == null || edges.length == 0 ||
			edges[0] == null || edges[0].length == 0) {
			return false;
		}
		
		Set<Integer> visited = new HashSet<>();
		// fill in the app
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int i = 0; i < n; i++) {
			graph.put(i, new HashSet<>());
		}
		
		for (int[] edge: edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		//  has no cycle
		if (task261_has_cycle(0, edges, visited, -1, graph)) {
			return false;
		}
		
		return visited.size() == n;
    }
	
	public static boolean task261_has_cycle(int node, int[][] edges, Set<Integer> visited, int parent, Map<Integer, Set<Integer>> graph) {
		if (visited.contains(node)) {
			return true;
		}
		
		visited.add(node);
		Set<Integer> neighbors = graph.get(node);
		for(int nei : neighbors) {
			if (visited.contains(nei)) {
				// if nei is visited
				if (nei != parent) {
					return true;
				}
			} else {
				if (task261_has_cycle(nei, edges, visited, node, graph)) {
					return true;
				}
			} 
		}
		return false;
	}
	
		
	/*
	 * Union Find
	 */
	
	public static class Node {
		int parent;
		int rank; // size of the subtree
		
		public Node (int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}
	
	public static Node[] create(int n) {
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, 1);
		}
		return nodes;
	}
	
	
	public static int find(Node[] nodes, int x) {
		if (nodes[x].parent != x) {
			nodes[x].parent = find(nodes, nodes[x].parent);
		}
		return nodes[x].parent;
	}
	
	public static boolean union(Node[] nodes, int x, int y) {
		int xRoot = find(nodes, x);
		int yRoot = find(nodes, y);
		
		if (xRoot == yRoot) {
			return false;
		}
		if (nodes[xRoot].rank > nodes[yRoot].rank) {
			nodes[yRoot].parent = xRoot;
			nodes[xRoot].rank += nodes[yRoot].rank;
		} else {
			nodes[xRoot].parent = yRoot;
			nodes[yRoot].rank += nodes[xRoot].rank;
		}
		return true;
	}
	

	public static boolean task261_valid_tree_uf(int n, int[][] edges) {
		Node[] nodes = create(n);
		for (int[] edge: edges) {
			int node1 = edge[0];
			int node2 = edge[1];
			
			if (!union(nodes, node1, node2)) {
				return false;
			}
		}
		return edges.length == n - 1;
	}
	
	
	
	

}

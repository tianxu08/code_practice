package tag_union_find;

import java.util.ArrayList;
import java.util.HashMap;

public class Union_Find_323_Num_Connected_Components {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		int n = 5; 
		int[][] edges = {
				{0, 1}, 
				{1, 2}, 
				
				{3, 4}
		};
		
		int rev = task323_countComponents(n, edges);
		System.out.println("rev = " + rev);
		
		int rev2 = task232_countComponents_dfs(n, edges);
		System.out.println("rev2 = " + rev2);
	}
	
	
	/*
	 * Union Find
	 */
	/*
	 * 323. Number of Connected Components in an Undirected Graph
	 */
	public static int task323_countComponents(int n, int[][] edges) {
		if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
			return n;
		}
		Subset[] subsets = create(n);
		int count = n;
		for(int i = 0; i < edges.length; i ++) {
			int n1 = edges[i][0];
			int n2 = edges[i][1];
			
			int n1Root = find(subsets, n1);
			int n2Root = find(subsets, n2);
			
			if (n1Root != n2Root) {
				union(subsets, n1Root, n2Root);
				count --;
			}
		}
		return count;
		
	}
	

	
	public static class Subset{
		int parent;
		int rank;
		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}
	
	public static Subset[] create(int n) {
		Subset[] subsets = new Subset[n];
		for(int i = 0; i < n; i++) {
			subsets[i] = new Subset(i, 0);
		}
		return subsets;
	}
	
	public static int find(Subset[] subsets, int x) {
		if (subsets[x].parent != x) {
			subsets[x].parent = find(subsets, subsets[x].parent);
		}
		return subsets[x].parent;
	}
	
	public static void union(Subset[] subsets, int x, int y) {
		int xRoot = find(subsets, x);
		int yRoot = find(subsets, y);
		
		if (subsets[xRoot].rank < subsets[yRoot].rank) {
			subsets[xRoot].parent = yRoot;
		} else if (subsets[yRoot].rank > subsets[xRoot].rank) {
			subsets[yRoot].parent = xRoot;
		} else {
			subsets[xRoot].rank ++;
			subsets[yRoot].parent = xRoot;
		}
	}
	
	
	/*
	 * ===========================================================================
	 * use dfs
	 */
		
	public static int task232_countComponents_dfs(int n, int[][] edges) {
		if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
			return n;
		}
		
		// build the graph
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < n; i ++) {
			graph.put(i, new ArrayList<Integer>());
		}
		for(int i = 0; i < edges.length; i ++ ){
			int n1 = edges[i][0];
			int n2 = edges[i][1];
			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
		
		boolean[] visited = new boolean[n];
		int count = 0;
		// traverse the graph
		for(int i = 0; i < n; i ++) {
			if (!visited[i]) {
				count ++;
				dfs(i, graph, visited);
			}
		}
		return count;
	}
	
	public static void dfs(int node, HashMap<Integer, ArrayList<Integer>> graph, boolean[] visited) {
		if (visited[node]) {
			return ;
		}
		visited[node] = true;
		for(Integer nei: graph.get(node)) {
			dfs(nei, graph, visited);
		}
	}
	
	
	/*
	 * ==================================
	 * using bfs
	 */
	public static int task232_countComponents_bfs(int n, int[][] edges) {
		
		return -1;
	}
	
	
	

}

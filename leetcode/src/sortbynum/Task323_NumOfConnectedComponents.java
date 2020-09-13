package sortbynum;

import java.util.*;

public class Task323_NumOfConnectedComponents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		int n = 4;
		int[][] edges = {
				{0,1},
				{2,3},
				{1,2}
		};
		int rev = countComponents_union_find(n, edges);
		System.out.println("rev = " + rev);
		
		int rev2 = countComponents_DFS(n, edges);
		System.out.println("rev2 = " + rev2);
	}
	
	public static int countComponents_union_find(int n, int[][] edges) {
		if (n <= 0) {
			return 0;
		}
		Subset[] subsets = createUnionFind(n);
		for(int i = 0; i < edges.length; i ++) {
			int edge_src = edges[i][0];
			int edge_des = edges[i][1];
			union(subsets, edge_src, edge_des);
		}
		
		// !!! use find(i), to get the original root of i
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < n; i ++) {
			set.add(find(subsets, i));
		}
//		print(subsets);
		
        return set.size();
    }
	
	public static Subset[] createUnionFind(int n) {
		Subset[] subsets = new Subset[n];
		for(int i = 0; i < n; i ++) {
			subsets[i] = new Subset(i, 0);
		}
		return subsets;
	} 
	public static class Subset{
		int parent;
		int rank;
		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	} 
	
	public static int find(Subset[] subsets, int i) {
		if (subsets[i].parent != i) {
			subsets[i].parent= find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}
	
	public static void union(Subset[] subsets, int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		
		if (subsets[xroot].rank < subsets[yroot].rank) {
			subsets[xroot].parent = yroot;
		} else if (subsets[yroot].rank < subsets[xroot].rank) {
			subsets[yroot].parent = xroot;
		} else {
			subsets[xroot].rank ++;
			subsets[yroot].parent = xroot;
		}
	}
	
	public static void print(Subset[] subsets) {
		for(int i = 0;i < subsets.length; i ++) {
			System.out.println(i + "  :   " + subsets[i].parent + " : " + subsets[i].rank);
		}
	}
	
	
	/*
	 * DFS
	 */
	
	public static int countComponents_DFS(int n, int[][] edges) {
		if (n <= 1){
			return n;
		}
		// construct the graph
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < n; i++) {
			map.put(i, new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}
		// finish construct the graph in hashMap
		
		// use a hashSet to mark visited or not
		Set<Integer> visited = new HashSet<Integer>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited.contains(i)) {
				visited.add(i);
				dfsVisit(i, map, visited);
				count++;
			}
		}
		return count;
	}

	private static void dfsVisit(int i, Map<Integer, List<Integer>> map,
			Set<Integer> visited) {
		for (int nei : map.get(i)) {
			if (!visited.contains(nei)) {
				visited.add(nei);
				dfsVisit(nei, map, visited);
			}	
		}
	}
	
	
	
	

}

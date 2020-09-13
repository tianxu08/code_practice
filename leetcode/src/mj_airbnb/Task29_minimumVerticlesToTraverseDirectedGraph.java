package mj_airbnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task29_minimumVerticlesToTraverseDirectedGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] edges = {{2,9},{3,3},{3,5},{3,7},{4,8},{5,8},{6,6},{7,4},{8,7},{9,3},{9,6}};
		List<Integer> result = getMin(edges, 10);
		for (int num: result) {
			System.out.println(num);
		}
	}

	public static List<Integer> getMin(int[][] edges, int n) {
		List<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[n];
		Map<Integer, List<Integer>> map = new HashMap<>();
		int[] inDegree = new int[n];
		
		for (int[] edge: edges) {
			if (!map.containsKey(edge[0])) {
				map.put(edge[0], new ArrayList<>());
			}
			map.get(edge[0]).add(edge[1]);
			inDegree[edge[1]] ++;
		}
		
		// for all indegree ==0 nodes
		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) {
				result.add(i);
				dfs(i, map, visited);
			}
		}
		
		// for all unvisited nodes
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				result.add(i);
				dfs(i, map, visited);
			}
		}
		
		return result;
	}
	
	private static void dfs(int crt, Map<Integer, List<Integer>> map, boolean[] visited) {
		visited[crt] = true;
		if (map.containsKey(crt)) {
			for (int next: map.get(crt)) {
				if (!visited[next]) {
					dfs(next, map, visited);
				}
			}
		}
	}
	
}

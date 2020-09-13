package tag_topological_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import ds.GraphNode;

public class Topological_Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * http://www.geeksforgeeks.org/topological-sorting/
	 *
	 */
	
	/*
	 * algorithm for topological sorting
	 * BFS
	 * R <- the result contains the topological order of all nodes
	 * S <- set of all nodes whose in-degree == 0
	 * 
	 * while(S is NOT empty) {
	 *    remove one node( n )from S;
	 *    put n into R;
	 *    for all the neighbor m, with the edge n -> m, delete the edge n -> m, that is let the m's indegree--
	 *       if m's indegree == 0, put m into S
	 * }
	 */
	public static ArrayList<GraphNode> topological_sort_bfs(ArrayList<GraphNode> graph) {
		if (graph == null || graph.size() == 0) {
			return new ArrayList<GraphNode>();
		}
		HashMap<GraphNode, Integer> indegree = new HashMap<GraphNode, Integer>(); // GraphNode, inDegree
		ArrayList<GraphNode> result = new ArrayList<GraphNode>();
		
		// traverse the graph and fill in the indegree map
		for(GraphNode node : graph) {
			for(GraphNode nei : node.neighbors) {
				if (indegree.containsKey(nei)) {
					indegree.put(nei, indegree.get(nei) + 1);
				} else {
					indegree.put(nei, 1);
				}
			}
		}
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		
		// put the nodes with indegree== 0 into the q
		for(GraphNode node : graph) {
			if (!indegree.containsKey(node)) {
				q.offer(node);
			}
		}
		
		// do BFS
		while(!q.isEmpty()) {
			GraphNode cur = q.poll();
			// put the cur into result
			result.add(cur);
			for(GraphNode nei: cur.neighbors) {
				indegree.put(nei, indegree.get(nei) - 1);
				if (indegree.get(nei) == 0) {
					indegree.remove(nei);
					// put the nei into q
					q.offer(nei);
				}
			}
		}
		
		// after BFS, if the graph is DAG, the indegree should be empty
		if (indegree.size() != 0) {
			// this is NOT a DAG
			return new ArrayList<GraphNode>();
		}
		return result;
	}
	
	public static void test() {
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		GraphNode n4 = new GraphNode(4);
		GraphNode n5 = new GraphNode(5);
		GraphNode n6 = new GraphNode(6);
		
		n1.neighbors.add(n4);
		n1.neighbors.add(n3);
		n2.neighbors.add(n3);
		n2.neighbors.add(n5);
		
		n4.neighbors.add(n6);
		n5.neighbors.add(n6);
		
		ArrayList<GraphNode> graph = new ArrayList<GraphNode>();
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		graph.add(n4);
		graph.add(n5);
		graph.add(n6);
		
		
		ArrayList<GraphNode> topological_result = topological_sort_bfs(graph);
		for(GraphNode node: topological_result) {
			System.out.println(node.key);
		}
		
		System.out.println(" -------------------------");
		ArrayList<GraphNode> top_result_dfs = topological_sort_dfs(graph);
		for(GraphNode node: top_result_dfs) {
			System.out.println(node.key);
		}
	}
	
	
	/*
	 * DFS
	 * each node has three states: unvisited, visiting, visited.
	 * 
	 * Algorithm
	 * for each node n in graph
	 *    visit(n)
	 * 
	 * visit(n) {
	 * 	if n is visited, return ;
	 *  if n is visiting, not DAG, return false;
	 *  if n is unvisited (NOT visiting nor visited) {
	 *      mark n as visiting
	 *  	for each descendent of n, m, 
	 *        visit(m)
	 *      mark n as visited
	 *      add n to the head of L 
	 *  }
	 * }
	 */
	
	public static ArrayList<GraphNode> topological_sort_dfs(ArrayList<GraphNode> graph) {
		if (graph == null || graph.size() == 0) {
			return new ArrayList<GraphNode>();
		}
		ArrayList<GraphNode> result = new ArrayList<GraphNode>();
		ArrayList<GraphNode> list = new ArrayList<GraphNode>();
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		HashSet<GraphNode> visiting = new HashSet<GraphNode>();
		
		for(GraphNode node : graph) {
			dfs_visit(node, list, visited, visiting);
		}
		if (NOTDAG) {
			return new ArrayList<GraphNode>();
		}
		
		// revrese the nodes in list
		for(int i = list.size() - 1; i >= 0 ; i--) {
			result.add(list.get(i));
		} 
		return result;
	}
	public static boolean NOTDAG = false;
	public static void dfs_visit(GraphNode node, ArrayList<GraphNode> result, 
			HashSet<GraphNode> visited, HashSet<GraphNode> visiting) {
		if (visited.contains(node)) {
			return ;
		}
		if (visiting.contains(node)) {
			NOTDAG = true;
			return ;
		}
		visiting.add(node);
		for(GraphNode nei: node.neighbors) {
			dfs_visit(nei, result, visited,visiting);
		}
//		visiting.remove(node);
		visited.add(node);
		result.add(node);
	}	
	
}

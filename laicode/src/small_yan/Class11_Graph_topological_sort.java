package small_yan;

import java.util.*;

import ds.GraphNode;

public class Class11_Graph_topological_sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test5();
//		test4_1();
//		test4_0();
//		test4_2();
		test5_2();
	}

	/*
	 * Class 11 Graph, Topological Sort
	 */
	
	/*
	 * task1 How to represent a graph? 1 adjacent matrix 2 adjacent list
	 * 
	 * DFS BFS Topological Sort
	 */

	/*
	 * task2 Celebrity Problem a group of people, if there exists one person
	 * such that he does not know any other people, but every person knows him.
	 * is there such a person existed? if so, who is it? 1 how many celebrities
	 * in total we can have? -- at most one suppose we have two celebrities, c1
	 * and c2. if c1 knows c2, c1 is not celebrity if c1 donesn't knows c2, c2
	 * is not celebrity
	 * 
	 * 2 graph, represents in matrix (1) each pair of persons, we know one of
	 * them is not celebrity. (2) we can do pair comparison for n-1 times, then
	 * we have a candidate (3) we still need to check if the candidate is the
	 * celebrity.
	 */

	/*
	 * task3 maximum value
	 */

	/*
	 * task4 DFS 
	 * continiued 
	 * 1 deep copy a directed/undirected graph DFS/BFS 
	 */
	
	/* 2 how to determine if an directed graph is a tree? 
	 * root-node tree: 
	 * from root, there in only one path DFS- a node is visited only once, we can not
	 * visit it again
	 */
	public static void test4_2() {
		GraphNode n1 =new GraphNode(1);
		GraphNode n2 =new GraphNode(2);
		GraphNode n3 =new GraphNode(3);
		GraphNode n4 =new GraphNode(4);
		
		n1.neighbors.add(n2);
		n1.neighbors.add(n3);
		n2.neighbors.add(n4);
//		n3.neighbors.add(n4);
		
		List<GraphNode> graph = new ArrayList<GraphNode>();
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		graph.add(n4);
		
		boolean rev = task4_2_directed_graph_is_tree(graph, n1);
		System.out.println("result = " + rev);
		
	}
	
	public static boolean task4_2_directed_graph_is_tree(List<GraphNode> graph, GraphNode root) {
		if (graph == null || graph.size() == 0 || !graph.contains(root)) {
			return false;
		}
		HashMap<GraphNode, Boolean> map = new HashMap<GraphNode, Boolean>();
		for(GraphNode node: graph) {
			map.put(node, false);
		}
		return task4_2_helper(root, map);
	}
	public static boolean task4_2_helper(GraphNode node, HashMap<GraphNode, Boolean> visited) {
		// for debug
		System.out.println("---------------------------------");
		System.out.println("current node value : " + node.key);
		printMap(visited);
		System.out.println("=================================");
		
		if (visited.get(node) == true) {
			// this node is already visited, return false
			return false;
		}
		
		// mark current node as true
		visited.put(node, true);
		
		// if this is a leaf node, return true
		if (node.neighbors == null || node.neighbors.size() == 0) {
			return true;
		}
		
		// suppose the current Node is valid
		boolean result = true;
		// traverse all its neighbors. if all its neighbors are valid, the current is Valid. 
		// if one or more of its neighbor is NOT valid, the current node is NOT valid
		for(GraphNode nei: node.neighbors) {
			result = (result & task4_2_helper(nei, visited));
		}
		
		return result;
	}
	
	// for debug
	public static void printMap(HashMap<GraphNode, Boolean> map) {
		for(java.util.Map.Entry<GraphNode, Boolean> entry: map.entrySet()) {
			System.out.println(entry.getKey().key + "   " + entry.getValue());
		}
	}
	
	/* 3 how to determine if an directed graph does not have circle? 
	 * method 1: for each of the nodes in the graph, do DFS -> only mark current path
	 * visit 
	 * method 2: We will need two markers 
	 * - visiting: we are visiting the node now, but not finished visiting:: after all the neighbors of node are
	 * visited, we can mark the node visited, before that, the node is in
	 * visiting status. 
	 * -visited: we finish the visiting node
	 * 
	 * dfs(GrapohNode node) 
	 * { 
	 * 	  if (node is visited) 
	 *    { 
	 *    	return ; 
	 *    } 
	 *    if (node is visiting) 
	 *    { 
	 *    	there is a cycle. 
	 *    }
	 * 
	 *    if (node is not visited or visiting) 
	 *    { 
	 *    	mark node visiting for (neighbor: node.neighbors) 
	 *    { 
	 *    	dfs(neighbor); 
	 *    } 
	 *    mark node visited 
	 *    } 
	 *    }
	 */
	
	public static void test4_0() {
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		
		n1.neighbors.add(n2);
		n2.neighbors.add(n3);
		n1.neighbors.add(n3);
		n3.neighbors.add(n1);
		
		List<GraphNode> graph = new ArrayList<GraphNode>();
		graph.add(n1);
		graph.add(n2);
	 	graph.add(n3);
		
//		
//		GraphNode n4 = new GraphNode(4);
//		GraphNode n5 = new GraphNode(5);
//		
//		n4.neighbors.add(n3);
//		n5.neighbors.add(n4);
//		
//		graph.add(n4);
//		graph.add(n5);
		boolean rev = task4_directedGraphHasCycle(graph);
		System.out.println("rev = " + rev);
		
		boolean rev2 = task4_directedGraphHasCycle2(graph);
		System.out.println("rev2 = " + rev2);
	}
	
	public static boolean directed_graph_has_cycle = false;
	public static boolean task4_directedGraphHasCycle(List<GraphNode> graph) {
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		HashSet<GraphNode> visiting = new HashSet<GraphNode>();
		
		for(GraphNode node: graph) {
			task4_directed_dfs(node, visited, visiting);
		}
		return directed_graph_has_cycle;
	
	}
	
	public static void task4_directed_dfs(GraphNode node, HashSet<GraphNode> visited, HashSet<GraphNode> visiting) {
		System.out.println("------visited------");
		printSet(visiting);
		System.out.println("-------------------");
		System.out.println("=======visiting====");
		printSet(visiting);
		System.out.println("===================");
		
		// the node is already visited, exit
		if (visited.contains(node)) {
			return ;
		}
		// the node in visiting, which means it's being visited via another path. 
		// if we come here again, from a new path, there must be a cycle. 
		if (visiting.contains(node)) {
			directed_graph_has_cycle = true;
			return ;
		}
		// add the node to visiting set
		visiting.add(node);
		// visit its neighbors
		for(GraphNode nei: node.neighbors) {
			task4_directed_dfs(nei, visited, visiting);
		}
		// if all neighbors finished, add to visited
		visited.add(node);
		// remove the node from visiting, since its already visited
		visiting.remove(node);
	}
	
	public static void printSet(HashSet<GraphNode> map) {
		for(GraphNode n: map) {
			System.out.print(n.key + " ");
		}
		System.out.println();
	}

	
	
	public static boolean task4_directedGraphHasCycle2(List<GraphNode> graph) {
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		HashSet<GraphNode> visiting = new HashSet<GraphNode>();
		
		boolean result = false;
		for(GraphNode node: graph) {
			result = result | task4_directed_graph_has_cycle(node, visited, visiting);
		}
		return result;
	
	}
	
	public static boolean task4_directed_graph_has_cycle(GraphNode node, HashSet<GraphNode> visited, HashSet<GraphNode> visiting) {
		// the node is already visited, exit
		if (visited.contains(node)) {
			return false;
		}
		// the node in visiting, which means it's being visited via another path. 
		// if we come here again, from a new path, there must be a cycle. 
		if (visiting.contains(node)) {
			directed_graph_has_cycle = true;
			return true;
		}
		// add the node to visiting set
		visiting.add(node);
		// visit its neighbors
		boolean hasCycle = false;
		for(GraphNode nei: node.neighbors) {
			hasCycle = hasCycle | task4_directed_graph_has_cycle(nei, visited, visiting);
		}
		// if all neighbors finished, add to visited
		visited.add(node);
		// remove the node from visiting, since its already visited
		visiting.remove(node);
		return hasCycle;
	}
	
	

	
	// this we use n node from 0..n-1 and the adjacentMatrix
	
	/**
	 * 
	 * @param n
	 * @param adjacentMatrix
	 * @return
	 * 
	 * 1. edge num: n - 1
	 * 2: no cycle
	 */
	public static boolean task4_determineUndirectedGraphIsTree(int n, int[][] adjacentMatrix) {
		for (int i = 0; i < n; i++) {
			if (task4_undirectedGraphHasCycle(i, adjacentMatrix)) {
				return false;
			}
		}
		return adjacentMatrix.length == n - 1;
	}
	
	
	public static void test4_1() {
		int n = 3; 
		int[][] adjacentMatrix = new int[n][n];
		adjacentMatrix[0][1] = 1;
		adjacentMatrix[1][0] = 1;
		adjacentMatrix[0][2] = 1;
		adjacentMatrix[2][0] = 1;
		
//		adjacentMatrix[1][2] = 1;
//		adjacentMatrix[2][1] = 1;
		
		boolean hasCycle  = task4_undirectedGraphHasCycle(3, adjacentMatrix);
		System.out.println("hasCycle = " + hasCycle);
	}
	public static boolean task4_undirectedGraphHasCycle(int n, int[][] adjacentMatrix) {
		if (adjacentMatrix == null || adjacentMatrix.length == 0 || 
				adjacentMatrix[0] == null || adjacentMatrix[0].length == 0) {
			return false;
		}
		boolean[] visited = new boolean[n];
		return task4_un_hasCycle_helper(0, adjacentMatrix, visited, -1);
	}
	
	public static boolean task4_un_hasCycle_helper(int n, int[][] adjacentMatrix, boolean[] visited, int parent) {
		visited[n] = true;
		for(int i = 0; i < adjacentMatrix[n].length; i ++) {
			if (adjacentMatrix[n][i] == 1) { // i is n's neighbor
				if (visited[i] == true) {
					// here we need to introduce parent. 
					// 1 -> 2, we visited 1, visited[1] = true
					// when we visited 2, visited[1] = true, but here, 1 is 2's parent 
					// so, this is NOT cycle in this case
					// if i != parent, which means there is a cycle
					if (i != parent) {
						return true;
					}
				} else {
					if(task4_un_hasCycle_helper(i, adjacentMatrix, visited, n)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	

	
	
	
	
	
	
	
	
	
	

	/*
	 * task5 Topological Sort
	 * 
	 * graph: list of nodes L <- Empty list that will contain the topological
	 * order of the nodes 
	 * 
	 * S <- Set of all nodes with no incoming edges
	 * 
	 * while (S is NOT empty) do remove a node n from S add n to tail of L for
	 * each node m with an edge e from n to m (n -> m) do remove edge e from the
	 * graph. (m's in-degree --) if m has no other incoming edges (m's in-degree
	 * == 0) insert m into S
	 * 
	 * if graph has vertices, then return error(graph has at least one cycle)
	 * else return L (a topological sorted order)
	 */

	/*
	 * BFS do topological sorting
	 */

	public static void test5() {
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		
		n1.neighbors.add(n2);
		n1.neighbors.add(n3);
		n2.neighbors.add(n3);
//		n3.neighbors.add(n2);

		ArrayList<GraphNode> graph = new ArrayList<GraphNode>();
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		List<GraphNode> topSort = topologicalSort(graph);

		System.out.println("==========top sort1 ===========");
		for (GraphNode node : topSort) {
			System.out.print(node.key + "  ");
		}
		System.out.println("\n-----------------");
		
		
		System.out.println("==========top sort2 ===========");
		ArrayList<GraphNode> topSort2 = topologicalSort2(graph);
		for (GraphNode node : topSort2) {
			System.out.print(node.key + "  ");
		}
		System.out.println("\n-----------------");

	}
	
	
	public static void test5_2() {
		
		
		GraphNode n3 = new GraphNode(3);
		GraphNode n5 = new GraphNode(5);
		GraphNode n7 = new GraphNode(7);
		GraphNode n11 = new GraphNode(11);
		GraphNode n8 = new GraphNode(8);
		GraphNode n2 = new GraphNode(2);
		GraphNode n9 = new GraphNode(9);
		GraphNode n10 = new GraphNode(10);
		
		
		
		n7.neighbors.add(n11);
		n7.neighbors.add(n8);
		
		n5.neighbors.add(n11);
		
		n3.neighbors.add(n8);
		n3.neighbors.add(n10);
		
		n11.neighbors.add(n2);
		n11.neighbors.add(n9);
//		n11.neighbors.add(n10);
		
		n8.neighbors.add(n9);
		
		

		ArrayList<GraphNode> graph = new ArrayList<GraphNode>();
		graph.add(n7);
		graph.add(n5);
		graph.add(n3);
		graph.add(n11);
		graph.add(n8);
		graph.add(n2);
		graph.add(n9);
		graph.add(n10);
		
		List<GraphNode> topSort = topologicalSort(graph);

		System.out.println("==========top sort1 ===========");
		for (GraphNode node : topSort) {
			System.out.print(node.key + "  ");
		}
		System.out.println("\n-----------------");
		
		
		System.out.println("==========top sort2 ===========");
		List<GraphNode> topSort2 = topologicalSort2(graph);
		for (GraphNode node : topSort2) {
			System.out.print(node.key + "  ");
		}
		System.out.println("\n-----------------");

	}
	
	

	public static List<GraphNode> topologicalSort(
			ArrayList<GraphNode> graph) {
		ArrayList<GraphNode> result = new ArrayList<GraphNode>();
		HashMap<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
		// store <GraphNode, in-degree> of node

		// put all neighbors into map, their in-degree >= 1.
		for (GraphNode node : graph) {
			for (GraphNode neighbor : node.neighbors) {
				if (map.containsKey(neighbor)) {
					map.put(neighbor, map.get(neighbor) + 1);
				} else {
					map.put(neighbor, 1);
				}
			}
		}

		// add all in-degree == 0 node into result, they are not in the hashMap
		// and add them into the queue
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				result.add(node);
				queue.offer(node);
			}
		}

		// do BFS
		while (!queue.isEmpty()) {
			GraphNode node = queue.poll();
			for (GraphNode n : node.neighbors) {
				// in-degree --
				if (map.containsKey(n)) {
					map.put(n, map.get(n) - 1);
					// if in-degree == 0
					if (map.get(n) == 0) {
						result.add(n);
						queue.offer(n);
						map.remove(n);
					}
				}
			}
		}

		// check if map.size == 0, if is, then output the result
		// otherwise, there are still element in the map, so, there are cycle.		
		if (map.size() != 0) {
			// NOT a DAG
			return new ArrayList<GraphNode>();
		}
		return result;
	}
	
	
	/*
	 * task6
	 * DFS for topological sorting
	 * 
	 * L <- Empty list that will contain the sorted nodes
	 * 
	 * for each of the nodes in the graph do
	 *    visit(n)
	 *    
	 *    
	 * visit(node n) 
	 * 		if n is visited, then return
	 * 		if n is visiting, then stop, NOT a DAG
	 * 		if n is not visited nor visiting then
	 * 			mark n visiting
	 * 			for each node m which is n's dependency do
	 * 				visit(m)
	 * 			mark n visited
	 * 			add n to head of L
	 * 
	 * 
	 */
	
	public static ArrayList<GraphNode> topologicalSort2(ArrayList<GraphNode> graph) {
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		HashSet<GraphNode> visiting = new HashSet<GraphNode>();
		ArrayList<GraphNode> list = new ArrayList<GraphNode>();
		for(GraphNode node: graph) {
			visit(node, visited, visiting, list);
		}
		
		if (NOTDAG) {
			return new ArrayList<GraphNode>();
		} 
		
		ArrayList<GraphNode> result = new ArrayList<GraphNode>();
		for(int i = list.size() - 1; i >= 0; i --) {
			result.add(list.get(i));
		}
		
		return result;
	}
	
	public static boolean NOTDAG = false;
	
	public static void visit(GraphNode n, 
			HashSet<GraphNode> visited, HashSet<GraphNode> visiting, ArrayList<GraphNode> list) {
		if (visited.contains(n)) {
			return ;
		}
		if (visiting.contains(n)) {
			NOTDAG = true;
			return;
		}
		
		visiting.add(n);
		
		for(GraphNode neighbor : n.neighbors) {
			visit(neighbor, visited, visiting, list);
		}
//		System.out.println("@@@@@@@@");
//		System.out.println("!!!!! n: " + n.key);
		visited.add(n);
		visiting.remove(n);
//		System.out.print("visited " );
//		printSet(visited);
		
//		System.out.print("visiting ");
//		printSet(visiting);
		
		list.add(n);
//		System.out.println("--------");
//		printList(list);
//		System.out.println("========");
	}
	
	
	public static void printSet(Set<GraphNode> set) {
		for (GraphNode n: set) {
			System.out.print(n.key + " ");
		}
		System.out.println();
	}
	public static void printList(ArrayList<GraphNode> list) {
		for(GraphNode n: list) {
			System.out.print(n.key + " ");
		}
		System.out.println();
	}
		
	
	/*
	 * task7
	 * List of courses you need to take to complete the program,
	 * what is the order of the courses you can only take one course at a time.
	 * 
	 * see leetcode 207 210  Course Schedule
	 * 
	 */
	
	
	/* task8
	 * given a dictionary, but not knowing how the character sequence order(排序方法 未知),
	 * 知道的只有在字典中所有String的order,求可能的character order(假设 character set已经知道了)
	 * 
	 * see leetcode 269 Alien Dictionary
	 */
	
	/*
	 * task9
	 * Excel 表格计算
	 * 
	 * 把所有的格子都计算出来
	 */
	
	
	
	
	

}

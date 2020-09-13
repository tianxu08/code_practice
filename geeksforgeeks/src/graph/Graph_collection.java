package graph;

public class Graph_collection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Applications of Minimum Spanning Tree Problem
	 * 
	 */
	
	/*
	 * Applications of Depth First Search
	 * (1) For an unWeighted graph, DFS traversal of the graph produces the minimum spanning tree
	 *     and all pair shortest path tree. 
	 * (2) Detecting cycle in graph; 
	 * 	   A graph has cycle if and only if we see a back edge during DFS. So we can run DFS
	 *     for the graph and check for back edges. 
	 *     http://people.csail.mit.edu/thies/6.046-web/recitation9.txt
	 * (3) Path finding. 
	 * 	   We can specialized the DFS algorithm to find a path between two given vertices u and z. 
	 *     (1) call DFS(G, u) with u as start vertex
	 *     (2) use a stack S to keep track of the path between the start vertex and the current vertex
	 *     (3) As soon as destination vertex z is encountered, return the path as the contents of stack 
	 *     http://ww3.algorithmdesign.net/handouts/DFS.pdf
	 * (4) Topological Sorting. 
	 * (5) To test if a graph is bipartite
	 * (6) Finding Strongly Connected Components of a graph
	 * (7) Solving puzzles with only one solution, such as mazes.
	 */
	
	

}

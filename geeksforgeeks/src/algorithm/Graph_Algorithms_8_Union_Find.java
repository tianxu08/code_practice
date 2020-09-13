package algorithm;

public class Graph_Algorithms_8_Union_Find {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}
	
	// naive implementation of find
	int find(int[] parent, int i) {
		if (parent[i] == -1) {
			return i;
		}
		return find(parent, parent[i]);
	}
	// naive implementation of unioin
	void union(int[] parent, int x, int y) {
		int xSet = find(parent, x); // find x's root
		int ySet = find(parent, y); // find y's root
		parent[xSet] = ySet;  // set the parent of x's root to y's root. 
	}
	
	public static class Subset {
		int parent;
		int rank;
		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}
	
	
	// find: use path compression technique.  
	public static int find2(Subset[] subsets, int i) {
		// find root and make root as parent of i: path compression
		if (subsets[i].parent != i) {
			subsets[i].parent = find2(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}
	
	// union: use union by rank
	public static void union2(Subset[] subsets, int x, int y) {
		int xroot = find2(subsets, x);
		int yroot = find2(subsets, y);
		
		// attach smaller rank tree under the root of high rank tree
		if (subsets[xroot].rank < subsets[yroot].rank) {
			subsets[xroot].parent = yroot;
		} else if (subsets[xroot].rank > subsets[yroot].rank) {
			subsets[yroot].parent = xroot;
		} else {
			// they are the same rank
			// then, make one as the root and increase its rank by 1
			subsets[yroot].parent = xroot;
			subsets[xroot].rank ++;
		}
	}
	
	public static class Graph{
		int V;  // number of vertices 
		int E;  // number of edges
		Edge[] edges;
		public Graph(int _v, int _e) {
			this.V = _v;
			this.E = _e;
			edges = new Edge[_e];
			for(int i = 0; i < edges.length; i ++) {
				edges[i] = new Edge();
			}
		}
	}
	
	public static class Edge{
		int src;
		int des;
		public Edge(int _src, int _des){
			this.src = _src;
			this.des = _des;
		}
		public Edge() {
			this.src = -1;
			this.des = -1;
		}
	}
	
	public static boolean isCycle(Graph graph) {
		int V = graph.V;
		int E = graph.E;
		
		// create the subsets
		Subset[] subsets = new Subset[V];
		
		// init
		for(int v = 0; v < V; v ++) {
			subsets[v] = new Subset(v, 0);  
			// set parent of v to itself
			// init v's rank to 0    
		}
		printSubsets(subsets);
		// traverse all edge of graph, first find sets of both vertices of every edge. 
		// if they are same, then there is a cycle in the graph
		for(int e = 0; e < E; e ++) {
			// 
			Edge curEdge = graph.edges[e];
			int x = find2(subsets, curEdge.src);
			int y = find2(subsets, curEdge.des);
			System.out.println("----------------------");
			System.out.println(" [ " + curEdge.src + " : " + curEdge.des + " ]");
			System.out.println("x = " + x + " y = " + y);
			
			if (x == y) {
				return true;
			}
			union2(subsets, curEdge.src, curEdge.des);
			
			
			printSubsets(subsets);
			System.out.println("======================");
		}
		return false;
	}
	
	public static void  printEdges(Graph graph) {
		for(int i = 0; i < graph.edges.length; i ++) {
			System.out.println("edge : " + graph.edges[i].src + " : " + graph.edges[i].des);
		}
	}
	public static void test2() {
		int V = 3, E = 3;
		Graph graph = new Graph(V, E);
		graph.edges[0].src = 0;
		graph.edges[0].des = 1;
		
		graph.edges[1].src = 1;
		graph.edges[1].des = 2;
		
		graph.edges[2].src = 2;
		graph.edges[2].des = 0;
		
		printEdges(graph);
		boolean rev = isCycle(graph);
		System.out.println("rev = " + rev);
		
	}
	
	public static void printSubsets(Subset[] subsets) {
		for(int i = 0; i < subsets.length; i ++) {
			Subset curSubset = subsets[i];
			System.out.println("parent = " + curSubset.parent + " rank = " + curSubset.rank);
		}
	}
	
	
		
}

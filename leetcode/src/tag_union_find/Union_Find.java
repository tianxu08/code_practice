package tag_union_find;

public class Union_Find {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static class Subset{
		int parent;
		int rank;
		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}
	
	// create the subsets
	public static Subset[] createSet(int n) {
		Subset[] subsets = new Subset[n];
		for(int i = 0; i < n; i ++) {
			subsets[i] = new Subset(i, 0);
			// parent is itself, rank == 0
			// the index represent the element itself. 
		}
		return subsets;
	}
	
	// return the representative or a pointer to the representative of the set 
	// that contains element x
	// path compression. 
	public static int find(Subset[] subsets, int x) {
		if (subsets[x].parent != x) {  
			// path compression. 
			subsets[x].parent = find(subsets, subsets[x].parent);
		}
		return subsets[x].parent;
	}
	
	// merge into one set that the set that contains element x and the set that contains element y
	// (x and y are in different sets). The original sets will be destroyed
	// union by rank
	public static void union(Subset[] subsets, int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		
		if (subsets[xroot].rank > subsets[yroot].rank) {
			// xroot has greater rank, merge yroot into xroot
			subsets[yroot].parent = xroot;
		} else if (subsets[xroot].rank < subsets[yroot].rank) {
			subsets[xroot].parent = yroot;
		} else {
			subsets[xroot].rank ++; // let's increase xroot's rank
			subsets[yroot].parent = xroot; 
		}
	}
	
	
	/*
	 *  Let’s see if the problem was like: 
	 *  In a room are N persons and you had to handle Q queries. 
	 *  A query is of the form “x y 1,” meaning that x is friends with y, 
	 *  or “x y 2” meaning that we are asked to output 
	 *  if x and y are in same group of friends at that moment in time. 
	 *  In this case the solution with disjoint-set data structure is the fastest, 
	 *  giving a complexity of O(N + Q).
	 */
	
	
}

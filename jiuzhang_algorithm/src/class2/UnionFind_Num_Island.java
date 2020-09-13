package class2;

import java.util.ArrayList;
import java.util.HashSet;


public class UnionFind_Num_Island {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test200();
	}
	
	public static void test200() {
		char[][] grid = {
				"111".toCharArray(),
				"101".toCharArray(),
				"111".toCharArray()
		};
		int rev2 = task200_numIslands_union_find(grid);
		System.out.println("rev2 = " + rev2);
	}
	
	
	public static class Subset{
		int parent;
		int rank;
		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}
	
	public static Subset[] createSet(int n) {
		Subset[] subsets = new Subset[n];
		for(int i = 0; i < n; i ++) {
			subsets[i] = new Subset(i, 0);
		}
		return subsets;
	}
	
	public static int find(Subset[] subsets, int i) {
		if (subsets[i].parent != i) {
			subsets[i].parent = find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}
	
	public static void printSubset(Subset[] subsets) {
		for(int i = 0; i < subsets.length; i ++) {
			System.out.println("[ " + i + " : " + subsets[i].parent + " :: " + subsets[i].rank + "  ]");
		}
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
	public static int count = 0;
	public static int task200_numIslands_union_find(char[][] grid) {
        // sanity check
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		int[] dx = {-1, 1,  0, 0};
    	int[] dy = {0,  0, -1, 1};
    	
		int rowLen = grid.length;
		int colLen = grid[0].length;
		Subset[] subsets = createSet(rowLen * colLen);
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < rowLen; i ++) {
			for(int j = 0; j < colLen; j ++) {
				if (grid[i][j] == '0') {
					continue;
				}
				count ++;
				int curPos = i * colLen + j;
				list.add(curPos);
				for(int k = 0; k < 2; k ++) {
					// 只合并右边和下边的元素， 因为左边和上边已经访问过
					int nextX = i + dx[k];
					int nextY = j + dy[k];
					// sanity check
					if (nextX >= 0 && nextX < rowLen &&
						nextY >= 0 && nextY < colLen && 
						grid[nextX][nextY] == '1') {
						int nextFind = find(subsets, nextX * colLen + nextY);
						int curFind = find(subsets, curPos);
						count --;
						
						union(subsets, curFind, nextFind);
					}
					
				}
			}
		}
		
		// traverse the list and subsets
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < list.size(); i ++) {
			find(subsets, list.get(i)); // we need to modify 
			set.add(subsets[list.get(i)].parent);
		}
		System.out.println(list);
		printSubset(subsets);
		
		return count;
    }

}

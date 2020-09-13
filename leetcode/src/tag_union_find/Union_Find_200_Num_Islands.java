package tag_union_find;


public class Union_Find_200_Num_Islands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test200();
	}

	
	public static void test200() {
		char[][] grid = {
				"11000".toCharArray(),
				"11000".toCharArray(),
				"00100".toCharArray(),
				"00011".toCharArray()
		};
		int result = task200_numIslands(grid);
		System.out.println("result = " + result);
		
		int result2 = task200_numberIslands_dfs(grid);
		System.out.println("result2 = " + result2);
	}
	public static int[] dx = {1, 0, 0, -1};
	public static int[] dy = {0, 1,-1, 0};
	public static int task200_numIslands(char[][] grid) {
		// check
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		int rLen = grid.length;
		int cLen = grid[0].length;
		int totalElem = rLen * cLen;
		Node[] nodes = create(totalElem);
		int count = 0;
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (grid[i][j] == '1') {
					int pos = i * cLen + j;		
					count ++;
					// link its right or down direction's element if possible
					for(int k = 0; k < 2; k ++) {
						int nextX = i + dx[k];
						int nextY = j + dy[k];
						if (nextX >= 0 && nextX < rLen &&
							nextY >= 0 && nextY < cLen &&
							grid[nextX][nextY] == '1') {
							// get the new position
							int newPos = nextX * cLen + nextY;
							// find the root of pos and newPos
							int posRoot = find(nodes, pos);
							int newPosRoot = find(nodes, newPos);
							
							if (posRoot != newPosRoot) {
								union(nodes, posRoot, newPosRoot);
								count --;
							}	
						}
					}
				}
			}
		}
		System.out.println("count = " + count);
		return count;
	}
	
	
	public static class Node{
		int parent;
		int rank;

		public Node(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}
	
	public static Node[] create(int n) {
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i ++) {
			nodes[i] = new Node(i, 0);
		}
		return nodes;
	}
	
	
	public static int find(Node[] subsets, int x) {
		if (subsets[x].parent != x) {
			subsets[x].parent = find(subsets, subsets[x].parent);
		}
		return subsets[x].parent;
	}
	
	public static void union(Node[] subsets, int x, int y) {
		int xRoot = find(subsets, x);
		int yRoot = find(subsets, y);
		
		if (subsets[xRoot].rank > subsets[yRoot].rank) {
			subsets[yRoot].parent = xRoot;
		} else if (subsets[xRoot].rank < subsets[yRoot].rank) {
			subsets[xRoot].parent = yRoot;
		} else {
			subsets[xRoot].rank ++;
			subsets[yRoot].parent = xRoot;
		}
	}
	
	
	public static int task200_numberIslands_dfs(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		int rLen = grid.length;
		int cLen = grid[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		int count = 0;
		for(int i = 0; i < rLen; i++) {
			for(int j = 0; j < cLen; j ++) {
				if (grid[i][j] == '1' && !visited[i][j]) {
					dfs(grid, i, j, visited);
					count ++;
				}
			}
		}
		return count;
	}
	public static void dfs(char[][] grid, int x, int y, boolean[][] visited) {
		// sanity check
		int rLen = grid.length;
		int cLen = grid[0].length;
		if (x < 0 || x >= rLen || y < 0 || y >= cLen || visited[x][y]) {
			return ;
		}
		visited[x][y] = true;
		for(int k = 0; k < 4; k++) {
			int nextX = x + dx[k];
			int nextY = y + dy[k];
			if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen && grid[nextX][nextY] == '1') {
				dfs(grid, nextX, nextY, visited);
			}
		}
	}

}

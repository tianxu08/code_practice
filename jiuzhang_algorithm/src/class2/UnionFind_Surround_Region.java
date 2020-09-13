package class2;

import debug.Debug;

public class UnionFind_Surround_Region {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test_surround_regin();
	}

	public static class Subset {
		int parent;
		int rank;

		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}

	public static Subset[] createUnionFind(int n) {
		Subset[] subsets = new Subset[n + 1];
		for (int i = 0; i <= n; i++) {
			subsets[i] = new Subset(i, 0);
		}
		subsets[n].parent = n;
		subsets[n].rank = n + 1; //!!!!! here, tricky point. set the oRoot with highest rank. 
		return subsets;
	}

	public static int find(Subset[] subsets, int i) {
		if (subsets[i].parent != i) {
			subsets[i].parent = find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}

	public static void Union(Subset[] subsets, int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		if (subsets[xroot].rank < subsets[yroot].rank) {
			subsets[xroot].parent = yroot;
		} else if (subsets[xroot].rank > subsets[yroot].rank) {
			subsets[yroot].parent = xroot;
		} else {
			subsets[xroot].rank++;
			subsets[yroot].parent = xroot;
		}
	}

	public static void printSubsets(Subset[] subsets) {
		System.out.println("-------------------------");
		for (int i = 0; i < subsets.length; i++) {
			Subset curSubset = subsets[i];
			System.out.println("parent = " + curSubset.parent + " rank = "
					+ curSubset.rank);
		}
		System.out.println("-------------------------");
	}

	/*
	 * leetcode 130 Surround Regions
	 * 
	 * http://likesky3.iteye.com/blog/2240270
	 */
	public static void test_surround_regin() {
		char[][] board = { 
//				"XXXX".toCharArray(), 
//				"OOOX".toCharArray(),
//				"XXOX".toCharArray() 
				"O".toCharArray()
		};
		Debug.printMatrix(board);
		surround_regions(board);
		Debug.printMatrix(board);
	}

	public static void surround_regions(char[][] board) {
		// sanity check
		if (board == null || board.length == 0 || board[0] == null
				|| board[0].length == 0) {
			return;
		}
		int rowLen = board.length;
		int colLen = board[0].length;

		Subset[] subsets = createUnionFind(rowLen * colLen);
		int oRoot = rowLen * colLen;
		printSubsets(subsets);

		for (int i = 0; i < rowLen ; i++) {
			for (int j = 0; j < colLen ; j++) {
				if (board[i][j] == 'X') {
					continue;
				}
				int curPos = i * colLen + j;

				if (i == 0 || i == rowLen - 1 || j == 0 || j == colLen - 1) {
					Union(subsets, oRoot, curPos);
				} else {
					for (int k = 0; k < 4; k++) {
						int nextX = i + dx[k];
						int nextY = j + dy[k];
						if (nextX >= 0 && nextX <= rowLen - 1 && nextY >= 0
								&& nextY <= colLen - 1
								&& board[nextX][nextY] == 'O') {
							Union(subsets, curPos, nextX * colLen + nextY);
						}
					}
				}

			}
		}


		printSubsets(subsets);

		// scan the board again
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				if (board[i][j] == 'O'
						&& find(subsets, i * colLen + j) != oRoot) {
					board[i][j] = 'X';
				}
			}
		}
	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	
}


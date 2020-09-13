package tag_union_find;

import java.util.LinkedList;

public class Union_Find_130_Surrounded_Regions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test130();
	}
	
	/*
	 * task130 
	 */
	
	
	public static void test130() {
		char[][] board = {
				"XXXX".toCharArray(), 
				"XOOX".toCharArray(), 
				"XXOX".toCharArray(), 
				"XOXX".toCharArray()
		};
		
		printBoard(board);;
//		task130_solve2(board);
		task130_solve(board);
		printBoard(board);
		
	}
	
	public static void printBoard(char[][] board) {
		System.out.println("------------------------");
		for(int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[0].length; j ++ ){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}
	
	public static void task130_solve2(char[][] board) {
		if (board == null || board.length == 0) {
			return ;
		}
		int rLen = board.length;
		int cLen = board[0].length;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < rLen; i++) {
			if (board[i][0] == 'O') {
				queue.offer(i * cLen + 0);
			} 
			if (board[i][cLen - 1] == 'O') {
				queue.offer(i * cLen + cLen - 1);
			}
		}
		for(int j = 1; j < cLen - 1; j ++) {
			if (board[0][j] == 'O') {
				queue.offer(0 * cLen + j);
			}
			if (board[rLen - 1][j] == 'O') {
				queue.offer((rLen - 1) * cLen + j); 
			}
		}
		
		while(!queue.isEmpty()) {
			int curPos = queue.poll();
			int curX = curPos / cLen;
			int curY = curPos % cLen;
			
			board[curX][curY] = 'T';
			// expand neighbors
			for(int k = 0; k < 4; k ++) {
				int nextX = dx[k] + curX;
				int nextY = dy[k] + curY;
				
				if (nextX >= 0 && nextX < rLen && 
					nextY >= 0 && nextY < cLen && 
					board[nextX][nextY] == 'O') {
					queue.offer(nextX * cLen + nextY); 
				}
			}
		}
		
		// now we have marked all un-surrounded region as 'T'
		// mark all surroiunded region as 'X'
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'T') {
					board[i][j] = 'O';
				}
			}
		}
	}
	
	
	public static int[] dx = {1, 0, 0, -1};
	public static int[] dy = {0, 1,-1, 0};
	
	
	public static void task130_solve(char[][] board) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return ;
		}
		
		int rLen = board.length;
		int cLen = board[0].length;
		Node[] nodes = create(rLen * cLen);
		int root = rLen * cLen;
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (board[i][j] == 'O') {
					int curPos = i * cLen + j;
					if (i == 0 || i == rLen - 1 || j == 0 || j == cLen - 1) {
						// union curPos with root
						union(nodes, root, curPos);
					}
					
					// for neighbors NOT on bound and is 'O'
					// union the neighbor with curPos
					for(int k = 0; k < 4; k ++) {
						int nextX = i + dx[k];
						int nextY = j + dy[k];
						if (nextX >= 1 && nextX < rLen - 1 && 
							nextY >= 1 && nextY < cLen - 1 && 
							board[nextX][nextY] == 'O') {
							
							int nextPos = nextX * cLen + nextY;
							// find the root of curPos
							int curPosRoot = find(nodes, curPos);
							// find the root of nextPos
							int nextPosRoot = find(nodes, nextPos);
							// union the two sets
							union(nodes, curPosRoot, nextPosRoot);
						}
					}
				}
			}
		}
		
		// scan the board again
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (board[i][j] == 'O' && find(nodes, i * cLen + j) != root) {
					board[i][j] = 'X';
				}
			}
		}
		
	}
	

	
	public static class Node {
		int parent;
		int rank;
		public Node(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}
	
	public static Node[] create(int n) {
		Node[] nodes = new Node[n + 1];
		for (int i = 0; i <= n; i++) {
			nodes[i] = new Node(i, 0);
		}
		return nodes;
	}
	
	// find with compression
	public static int find(Node[] nodes, int x) {
		if (nodes[x].parent != x) {
			nodes[x].parent = find(nodes, nodes[x].parent);
		}
		return nodes[x].parent;
	}
	
	public static void union(Node[] nodes, int x, int y) {
		int xroot = find(nodes, x);
		int yroot = find(nodes, y);
		
		if (nodes[xroot].rank > nodes[yroot].rank) {
			nodes[yroot].parent = xroot;
		} else if (nodes[xroot].rank < nodes[yroot].rank) {
			nodes[xroot].parent = yroot;
		} else {
			nodes[xroot].rank ++;
			nodes[yroot].parent = xroot;
		}
	}
	
	
//	
//	public static class Subset {
//		int parent;
//		int rank;
//
//		public Subset(int _p, int _r) {
//			this.parent = _p;
//			this.rank = _r;
//		}
//	}
//	
//	public static Subset[] createSet(int n) {
//		Subset[] subsets = new Subset[n + 1];
//		for(int i = 0; i <= n; i ++) {
//			subsets[i] = new Subset(i, 0);
//		}
//		// mark the n with highest rank (n + 1)
//		subsets[n].parent = n;
//		subsets[n].rank = n + 1;
//		
//		return subsets;
//	}
//	
//	
//	public static int find(Subset[] subsets, int x) {
//		if (subsets[x].parent != x) {
//			subsets[x].parent = find(subsets, subsets[x].parent);
//		}
//		return subsets[x].parent;
//	}
//	
//	public static void union(Subset[] subsets, int x, int y) {
//		int xRoot = find(subsets, x);
//		int yRoot = find(subsets, y);
//		
//		if (subsets[xRoot].rank > subsets[yRoot].rank) {
//			subsets[yRoot].parent = xRoot;
//		} else if (subsets[xRoot].rank < subsets[yRoot].rank) {
//			subsets[xRoot].parent = yRoot;
//		} else {
//			subsets[xRoot].rank ++;
//			subsets[yRoot].parent = xRoot;
//		}
//	}


}

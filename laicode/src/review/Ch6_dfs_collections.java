package review;

import java.util.*;

public class Ch6_dfs_collections {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
//		test2();
//		test3();
//		test4();
	}

	
	/*
	 * DFS/BFS 
	 */
	public static void test1() {
		int n = 32;
		List<List<Integer>> result = t1_divide_int(n);
		System.out.println(result);
	}
	
	public static List<List<Integer>> t1_divide_int(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		t1_helper(n, list, result);
		
		return result;
	}
	
	public static void t1_helper(int n, List<Integer> list, List<List<Integer>> result) {
		// base case
		if (n == 1) {
			result.add(new ArrayList<Integer>(list));
			return;
		}
		for(int i = n; i > 1; i--) {
			// !!! here, i needs > 1
			// only choose the smaller element
			if (n % i == 0) {
				if (list.isEmpty()) {
					list.add(i);
					t1_helper(n/i, list, result);
					list.remove(list.size() - 1);
				} else { // list is NOT empty
					if (i <= list.get(list.size() - 1)) {
						// choose the element in non-ascending order. 
						list.add(i);
						t1_helper(n/i, list, result);
						list.remove(list.size() - 1);
					}
				}
			}
		}
	}
	
	/*
	 * t2 
	 * find all unique of a given n
	 */
	public static void test2() {
		int n = 4;
		List<List<Integer>> result = t2_all_unique(n);
		System.out.println(result);
	}
	
	public static List<List<Integer>> t2_all_unique(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		t2_helper(n, list, result);
		return result;
	}
	
	public static void t2_helper(int n, List<Integer> list, List<List<Integer>> result) {
		if (n < 0) {
			return ;
		}
		if (n == 0) {
			result.add(new ArrayList<Integer>(list));
			return ;
		}
		
		for(int i = 1; i <= n; i++) {
			if (list.isEmpty()) {
				list.add(i);
				t2_helper(n - i, list, result);
				list.remove(list.size() - 1);
			} else {
				if (list.get(list.size() - 1) <= i) {
					list.add(i);
					t2_helper(n - i, list, result);
					list.remove(list.size() - 1);
				}
			}
		}
	}
	
	/*
	 * t3 Ocean problem
	 * find all nodes that can connect to pacific and antalic ocean
	 * assumption:
	 * the matrix is NOT null and there mus exists a group of nodes that 
	 * can reach Pacific and Anatatic. 
	 * 
	 * 
	 * 
	 *  Pacific
	 *   ----------------------
	 *   |                    ||
	 *   |					  ||
	 *   |					  ||
	 *   |					  ||
	 *   |					  ||
	 *   |				      ||
	 *   |					  ||  Atlantic
	 *   ======================
	 * 
	 */
	public static void test3() {
		int[][] matrix = {
				{1,2,2,3,5},
				{3,2,3,4,4},
				{2,4,5,3,1},
				{6,7,1,4,5},
				{5,1,1,2,4}
		};
		
		List<Point> result = t3_ocean(matrix);
		for(Point p: result) {
			System.out.println(p);
		}
		
		
	}
	
	
	public static List<Point>  t3_ocean(int[][] matrix) {
		if (matrix == null || matrix.length == 0 ||
				matrix[0] == null || matrix[0].length == 0) {
			return null;
		}
		
		PriorityQueue<Point> minHeap = new PriorityQueue<Point>(1000, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				if (o1.h == o2.h) {
					return 0;
				}
				return o1.h < o2.h ? -1 : 1;
			}
		});
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visitedP = new boolean[rLen][cLen];
		boolean[][] visitedA = new boolean[rLen][cLen];
		
		printMatrix(visitedP);
		System.out.println("=======");
		printMatrix(visitedA);
		System.out.println("========");
		
		List<Point> result = new ArrayList<Point>();
		
		// mark nodes which can reach pacific
		// add the bounds into minHeap
		for(int i = 0; i < rLen; i++) {
			minHeap.offer(new Point(i, 0, matrix[i][0]));
			visitedP[i][0] = true;
		}
		
		for(int j = 0; j < cLen;j ++) {
			minHeap.offer(new Point(0, j, matrix[0][j]));
			visitedP[0][j] = true;
		}
		System.out.println("*********");
		printMatrix(visitedP);
		System.out.println("=======");
		printMatrix(visitedA);
		System.out.println("========");
		
		// do a Best First Seach to mark all nodes which can reach Pacific
		while(!minHeap.isEmpty()) {
			Point cur = minHeap.poll();
			// generate the neighbors
			for(int k = 0; k < 4; k ++) {
				int nextX = cur.x + dx[k];
				int nextY = cur.y + dy[k];
				
				if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen && 
						!visitedP[nextX][nextY] && matrix[nextX][nextY] >= cur.h) {
					minHeap.offer(new Point(nextX, nextY, matrix[nextX][nextY]));
					visitedP[nextX][nextY] = true;
				}
			}
		}
		
		// now the minHeap is empty
		// mark the nodes which can reach Altantic
		for(int i = 0; i < rLen; i++) {
			minHeap.offer(new Point(i, cLen - 1, matrix[i][cLen - 1]));
			visitedA[i][cLen - 1] = true;
		}
		
		for(int j = 0; j < cLen - 1; j++) {
			minHeap.offer(new Point(rLen - 1, j, matrix[rLen - 1][j]));
			visitedA[rLen - 1][j] = true;
		}
		
		while(!minHeap.isEmpty()) {
			Point cur = minHeap.poll();
			// generate the neighbors
			for(int k = 0; k < 4; k ++) {
				int nextX = cur.x + dx[k];
				int nextY = cur.y + dy[k];
				
				if (nextX >= 0 && nextX < rLen && 
						nextY >= 0 && nextY < cLen && 
						!visitedA[nextX][nextY] && 
						matrix[nextX][nextY] >= cur.h) { 
					minHeap.offer(new Point(nextX, nextY, matrix[nextX][nextY]));
					visitedA[nextX][nextY] = true;
				}
			} 
		}
		
		// traverse the visitP and visitA and get the points
		for(int i = 0; i < rLen; i++) {
			for(int j = 0; j < cLen; j++) {
				if (visitedA[i][j] && visitedP[i][j]) {
					result.add(new Point(i, j, matrix[i][j]));
				}
			}
		}
		
		printMatrix(visitedP);
		System.out.println("--------------");
		printMatrix(visitedA);
		
		return result;
		
	}
	
	public static class Point{
		int x;
		int y;
		int h;
		public Point(int _x, int _y, int _h) {
			this.x = _x;
			this.y = _y;
			this.h = _h;
		}
		@Override
		public String toString(){
			return "[ " + x + " , " + y + " : " + h +  " ]";
		}
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void printMatrix(boolean[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*
	 * 4 Boggle Game
	 * find a path of a given word in a char matrix
	 */
	public static void test4() {
		char[][] matrix = {
			"ABCD".toCharArray(), 
			"EEEG".toCharArray(), 
			"FIHI".toCharArray(),
			"JKLM".toCharArray()
		};
		String word = "ABEEHIMK";
		boolean rev = t4_boggle_game(matrix, word);
		System.out.println("rev = " + rev);
	}
	
	public static boolean t4_boggle_game(char[][] matrix, String word) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return false;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		for(int i = 0; i < rLen; i++) {
			for(int j = 0; j < cLen; j++) {
				if (t4_helper(matrix, word, 0, visited, i, j)) {
					return true;
				}
			}
		}
		return false;
	} 
	
	public static boolean t4_helper(char[][] matrix, String word, 
			int index, boolean[][] visited, int x, int y) {
		if (index == word.length() ) {
			return true;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		if (x < 0 || x >= rLen || y < 0 || y >= cLen ||
				visited[x][y] || 
				matrix[x][y] != word.charAt(index)) {
			return false;
		}
		
		visited[x][y] = true;  // mark visited[x][y] to true
		boolean result = false;
		for(int k = 0; k < 4; k++) {
			int nextX = x + dx[k];
			int nextY = y + dy[k];
			
			result |= t4_helper(matrix, word, index + 1, visited, nextX, nextY);
		}
		visited[x][y] = false;
		return result;
	}
	
	
	/*
	 * follow up:
	 * if there is multiple strings need to matches
	 */
	

}

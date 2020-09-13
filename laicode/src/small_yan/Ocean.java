package small_yan;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Ocean {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		continental_divide(matrix);
		test();
	}
	
	public static int[][] matrix = {
		{1,2,2,3,5},
		{3,2,3,4,4},
		{2,4,5,3,1},
		{6,7,1,4,5},
		{5,1,1,2,4}
	};
	
	public static void test() {
		ArrayList<Pair> result = continental_divide(matrix);
		
		for(int i = 0; i < result.size(); i ++) {
			System.out.println(result.get(i));
		}
	}
	
	
	public static class Pair implements Comparable<Pair>{
		public int x;
		public int y;
		public int height;
		public Pair(int _i, int _j) {
			this.x = _i;
			this.y = _j;
			this.height = 0;
		}
		public Pair(int _i, int _j, int _h) {
			this.x = _i;
			this.y = _j;
			this.height = _h;
		}
		@Override
		public int compareTo(Pair another) {
			// TODO Auto-generated method stub
			if (this.height == another.height ) {
				return 0;
			}
			return this.height < another.height ? -1 : 1;
		} 
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "x = " + this.x + " " +"y = " + this.y + " " + "height = " + this.height;
		}
	}
	public static ArrayList<Pair> continental_divide (int[][] matrix) {
		ArrayList<Pair> result = new ArrayList<Pair>();
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited1 = new boolean[rLen][cLen];
		boolean[][] visited2 = new boolean[rLen][cLen];
		boolean[][] reachPacific = new boolean[rLen][cLen];
		boolean[][] reachAltantic = new boolean[rLen][cLen];
		
		// initialize
		for(int i = 0; i <rLen; i ++) {
			reachPacific[i][0] = true;
			reachAltantic[i][cLen - 1] = true;
			
		}
		for(int j =0; j < cLen; j ++) {
			reachPacific[0][j] = true;
			reachAltantic[rLen - 1][j] = true;
		}
		
		printMatrix(reachPacific);
		System.out.println("----------------------------------");
		printMatrix(reachAltantic);
		
		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>();
	
		// DO BFS from left and upper bound
		// the left and upper bound
		for(int i = 0; i < rLen; i ++) {
			minHeap.add(new Pair(i, 0, matrix[i][0]));
			visited1[i][0] = true;
		}
		for(int j = 1; j < cLen; j ++) {
			minHeap.add(new Pair(0, j, matrix[0][j]));
			visited1[0][j] = true;
		}
		
		while(!minHeap.isEmpty()) {
			Pair cur = minHeap.poll();
			ArrayList<Pair> neighbors = getNeighbor(matrix, cur.x, cur.y, visited1);
			
			for(Pair nei: neighbors) {
				if (nei.height >= cur.height) {
					// if nei.height >= cur.height, the neight can reach the specific ocean
					minHeap.add(nei);
					visited1[nei.x][nei.y] = true;
					reachPacific[nei.x][nei.y] = true;
				}
			}
		}
		
		
		// for debug
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		printMatrix(reachPacific);
		System.out.println("----------------------------------");
		
		System.out.println(minHeap.size());
		
		
		// DO BFS for lower and right bound
		// add the lower and right bound into the minHeap
		for(int i = 0; i < rLen; i ++) {
			minHeap.add(new Pair(i, cLen - 1, matrix[i][cLen - 1]));
			visited2[i][cLen - 1] = true;
		}
		for(int j = 0; j < cLen - 1; j ++) {
			minHeap.add(new Pair(rLen - 1, j, matrix[rLen - 1][j]));
			visited2[rLen - 1][j] = true;
		}
		
		while(!minHeap.isEmpty()) {
			Pair cur = minHeap.poll();
			ArrayList<Pair> neighbors = getNeighbor(matrix, cur.x, cur.y, visited2);
			for(Pair nei: neighbors) {
				if (nei.height >= cur.height) {
					minHeap.add(nei);
					visited2[nei.x][nei.y] = true;
					reachAltantic[nei.x][nei.y] = true;
				}
			}
		}
		
		// FOR DEBUG
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		printMatrix(reachAltantic);
		System.out.println("----------------------------------");
		
		// get the result: 
		// reachPacific[i][j] && reachAtlantic[i][j]
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (reachAltantic[i][j] && reachPacific[i][j]) {
					result.add(new Pair(i, j, matrix[i][j]));
				}
			}
		}
		
		return result;
		
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static ArrayList<Pair> getNeighbor(int[][] matrix, int x, int y, boolean[][] visited) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		ArrayList<Pair> neighbors = new ArrayList<Pair>();
		
		for(int i = 0; i < 4; i ++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			
			if (next_x >= 0 && next_x < rLen && next_y >= 0 && next_y < cLen && !visited[next_x][next_y]) {
				neighbors.add(new Pair(next_x, next_y, matrix[next_x][next_y]));
			}
		}
		
		return neighbors;
		
	}
	
	public static void printMatrix(boolean[][] matrix) {
		for(int i = 0; i < matrix.length; i ++) {
			for(int j = 0; j < matrix[0].length; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
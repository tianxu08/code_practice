package small_yan;

import java.util.*;


public class Class10_Ocean {

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
		List<Pair> result = continental_divide(matrix);
		
		for(int i = 0; i < result.size(); i ++) {
			System.out.println(result.get(i));
		}
		
		
		List<int[]> res = pacificAtantic(matrix);
		for (int[] cell: res) {
			System.out.println(cell[0] + " , " + cell[1]);
		}
		System.out.println();
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
	public static List<Pair> continental_divide (int[][] matrix) {
		List<Pair> result = new ArrayList<Pair>();
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited1 = new boolean[rLen][cLen];
		boolean[][] visited2 = new boolean[rLen][cLen];
		boolean[][] reachPacific = new boolean[rLen][cLen];
		boolean[][] reachAltantic = new boolean[rLen][cLen];
		
		// initaizlie
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
	
	
	
	public static int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static List<int[]> pacificAtantic(int[][] matrix) {
		List<int[]> result = new ArrayList<int[]>();
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return result;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		
		boolean[][] pacific = new boolean[m][n];
		boolean[][] atlantic = new boolean[m][n];
		
		Queue<int[]> pQueue = new LinkedList<int[]>();
		Queue<int[]> aQueue = new LinkedList<int[]>();
		
		// vertical boarde
		for (int i = 0; i < m; i++) {
			 pQueue.offer(new int[]{i, 0});
	         aQueue.offer(new int[]{i, n-1});
	         pacific[i][0] = true;
	         atlantic[i][m-1] = true;
		}
		
		// horizonal boarder
		for (int j = 0; j < n; j++) {
			pQueue.offer(new int[]{0, j});
            aQueue.offer(new int[]{m-1, j});
            pacific[0][j] = true;
            atlantic[n-1][j] = true;
		}
		
		bfs(matrix, aQueue, atlantic);
		bfs(matrix, pQueue, pacific);
		
		// find the overlap
		for (int i = 0; i < m; i++) {
			for (int j =0; j < n; j++) {
				if (atlantic[i][j] && pacific[i][j]) {
					result.add(new int[] {i, j});
				}
			}
		}
		
		return result;
	}
	
	private static void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
		int m = visited.length;
		int n = visited[0].length;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int[] d: dir) {
				int x = cur[0] + d[0];
				int y = cur[1] + d[1];
				if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) {
					continue;
				}
				visited[x][y] = true;
				queue.offer(new int[] {x, y});
			}
		}
	}
	
	
	
}


package tag_bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


import ds.Debug;

public class BFS_Collections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
		test5();
	}
	
	/*
	 * matrix
	 * 
	 * 1 130 surrounded regions
	 * 
	 * 2 200 number of islands
	 * 
	 * 3 286 walls and gates
	 * 
	 * 4 317 shortest distances to all buildings
	 * 
	 * 5 407 trapping rain water II
	 * 
	 * 6 417 pacific antalic water flow
	 * 
	 * 7 490 The Maze
	 * 
	 * 8 505 The Maze II
	 * 
	 * 9 499 The Maze III
	 * 
	 * 10 529 Minesweeper
	 * 
	 * 11 542 01 Matrix
	 * 
	 */
	
	public static void test1() {
		char[][] board = {
				"XXXX".toCharArray(),
				"XOOX".toCharArray(),
				"XXOX".toCharArray(),
				"XOXX".toCharArray()
		};
		Debug.printMatrix(board);
		System.out.println("------------");
		t1_l130_surrounded_regions(board);
		Debug.printMatrix(board);
	}
	
	/**
	 * 
	 * @param board
	 * mark all the 'O' which can reach the bound to 'E'
	 * mark all the remaining 'O' to 'X'
	 * mark all the 'E' to 'O'
	 */
	public static void t1_l130_surrounded_regions(char[][] board) {
		// check
		if (board == null || board.length == 0) {
			return ;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int rL = board.length;
		int cL = board[0].length;
		HashSet<Integer> visited = new HashSet<Integer>();
		// add the bound 'O' into q
		for(int i = 0; i < rL; i++) {
			if (board[i][0] == 'O' ) {
				q.offer(i * cL);
				visited.add(i * cL);
			}
			if (board[i][cL - 1] == 'O') {
				q.offer(i * cL + cL - 1);
				visited.add(i * cL + cL - 1);
			}
		}
		
		for (int j = 1; j < cL - 1; j ++) {
			if (board[0][j] == 'O') {
				q.offer(j);
				visited.add(j);
			}
			if (board[rL - 1][j] == 'O') {
				q.offer((rL - 1) * cL + j);
				visited.add((rL - 1) * cL + j);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			// mark the current position to 'E'
			int cx = cur / cL;
			int cy = cur % cL;
			board[cx][cy] = 'E';
			
			// get neighbors
			for (int k = 0; k < 4; k++ ){
				int nx = cx + dx[k];
				int ny = cy + dy[k];
				
				if (nx >= 0 && nx < rL && 
					ny >= 0 && ny < cL && 
					board[nx][ny] == 'O' && 
					!visited.contains(nx * cL + ny)) {
					// add the current to queue
					q.offer(nx*cL + ny);
					visited.add(nx * cL + ny);
				}
			}
		}
		
		// traverse the board, mark the remaining 'O' to 'X' and mark the 'E' to 'O'
		for(int i = 0; i < rL; i++) {
			for(int j = 0; j < cL; j++) {
				if (board[i][j] =='O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'E') {
					board[i][j] = 'O';
				}
			}
		}
	}
	
	
	/*
	 * task2 200 Number of Islands
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
	 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
	 * You may assume all four edges of the grid are all surrounded by water.
	 * 
	 * 11110
	 * 11010
	 * 11000
	 * 00000
	 * 
	 * Answer: 1
	 * 
	 * BFS
	 * for each node '1' (land), get all of it's neighbors, this is one island
	 * Time: O(m*n)
	 */
	public static void test2() {
		char[][] grid = {
				"11000".toCharArray(),
				"11000".toCharArray(),
				"00100".toCharArray(),
				"00011".toCharArray()
		};
		int rev = t2_l200_numIslands(grid);
		System.out.println("rev = " + rev);
	}
	
	public static int t2_l200_numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int rL = grid.length;
		int cL = grid[0].length;
		int counter = 0;
		boolean[][] visited = new boolean[rL][cL];
		for (int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1' && !visited[i][j]) {
					counter++;
					t2_l200_BFS(grid, visited, i, j);
				}
			}
		}
		return counter;
		
	}
	
	public static void t2_l200_BFS(char[][] grid, boolean[][] visited, int x, int y) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int rL = grid.length;
		int cL = grid[0].length;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x * cL + y);
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int curPos = q.poll();
			int curX = curPos / cL;
			int curY = curPos % cL;
			
			for (int k = 0; k < 4; k++) {
				int nextX = curX + dx[k];
				int nextY = curY + dy[k];
				
				if (nextX >= 0 && nextX < rL &&
					nextY >= 0 && nextY < cL && 
					!visited[nextX][nextY] &&
					grid[nextX][nextY] == '1') {
					visited[nextX][nextY] = true;
					q.offer(nextX * cL + nextY);
				}
			}
		}
	}
	
	
	/**
	 * t3 286 Walls and Gates
	 * You are given a m x n 2D grid initialized with these three possible values.
	 * -1 - A wall or an obstacle.
	 * 0 - A gate.
	 * INF - Infinity means an empty room. 
	 * 
	 * Fill each empty room with the distance to its nearest gate. 
	 * If it is impossible to reach a gate, it should be filled with INF
	 * 
	 * We use the value 231 - 1 = 2147483647 to represent INF 
	 * as you may assume that the distance to a gate is less than 2147483647.
	 * 
	 * INF  -1  0  INF
	 * INF INF INF  -1
	 * INF  -1 INF  -1
	 * 0  -1 INF INF
	 * 
	 * 3  -1   0   1
	 * 2   2   1  -1
	 * 1  -1   2  -1
	 * 0  -1   3   4
	 * 
	 * Time: O(m * n)  do twice traversal of the matrixs
	 * 
	 */
	
	public static void test3() {
		int INF = Integer.MAX_VALUE;
		int[][] rooms = {
				{INF, -1, 0, INF},
				{INF, INF, INF, -1},
				{INF, -1, INF, -1},
				{0, -1, INF, INF}
		};
		Debug.printMatrix(rooms);
		System.out.println("----------");
		t3_l286_wallsAndGates(rooms);
		Debug.printMatrix(rooms);
	}
	
	public static void t3_l286_wallsAndGates(int[][] rooms) {
       // check
		if (rooms == null || rooms.length == 0) {
			return ;
		}
		int rL = rooms.length;
		int cL = rooms[0].length;
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		Queue<Integer> q = new LinkedList<Integer>();
		// get all gates
		for (int i = 0;i < rL; i++) {
			for (int j = 0; j < cL; j++) {
				if (rooms[i][j] == 0) {
					q.offer(i * cL + j);
				}
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			int curX = cur / cL;
			int curY = cur % cL;
			
			for (int k = 0; k < 4; k++) {
				int nextX = curX + dx[k];
				int nextY = curY + dy[k];
				
				if (nextX >= 0 && nextX < rL &&
					nextY >= 0 && nextY < cL &&
					rooms[nextX][nextY] == Integer.MAX_VALUE) {
					rooms[nextX][nextY] = rooms[curX][curY] + 1;
					q.offer(nextX * cL + nextY);
				}
			}
		}
    }
	
	
	
	/*
	 * task4 317
	 * Shortest Distance from All buildings
	 */
	public static int t4_l317_shortestDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return -1;
		}
		
		int rL = grid.length;
		int cL = grid[0].length;
		// record the total distance to all buildings
		int[][] dist = new int[rL][cL];
		
		// record how many buildings this position can reach
		int[][] nums = new int[rL][cL]; 
		int buildNum = 0;
		for (int i = 0; i < rL; i++) {
			for (int j = 0; j < cL; j++) {
				if (grid[i][j] == 1 ) {
					buildNum++;
					t4_l317_bfs(grid, dist, nums, i, j);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < rL; i++) {
			for (int j = 0; j < cL; j++) {
				if (grid[i][j] == 0 && dist[i][j] != 0 && nums[i][j] == buildNum) {
					// make sure that this position can reach all buildings
					min = Math.min(min, dist[i][j]);
				}
			}
		}
		
		if (min < Integer.MAX_VALUE) {
			return min;
		}
		return -1;
		
	}
	
	public static void t4_l317_bfs(int[][] grid, int[][] dist, int[][] nums, int x, int y) {
		int rL = grid.length;
		int cL = grid[0].length;
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x*cL + y);
		boolean[][] visited = new boolean[rL][cL];
		visited[x][y] = true;
		int distance = 0;
		while(!q.isEmpty()) {
			distance++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				int curX = cur / cL;
				int curY = cur % cL;
				for (int k = 0; k < 4; k++) {
					int nextX = curX + dx[k];
					int nextY = curY + dy[k];
					
					if (nextX >= 0 && nextX < rL && 
						nextY >= 0 && nextY < cL &&
						!visited[nextX][nextY] &&
						grid[nextX][nextY] == 0) {
						dist[nextX][nextY] += distance;
						nums[nextX][nextY]++;
						visited[nextX][nextY] = true;
						q.offer(nextX * cL + nextY);
					}
				}
			}
		}
	}
	
	
	/*
	 * task5 407 trapping rain water II
	 */
	public static class Cell implements Comparable<Cell>{
		int x; 
		int y; 
		int h;
		public Cell(int _x, int _y, int _h) {
			this.x = _x;
			this.y = _y;
			this.h = _h;
		}
		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			if (this.h == o.h) {
				return 0;
			}
			return this.h < o.h ? -1 : 1;
		}
	}
	
	public static void test5() {
		int[][] heightMap = {
				{1,4,3,1,3,2},
				{3,2,1,3,2,4},
				{2,3,3,2,3,1}
		};
		int rev = t5_l407_trapRainWater(heightMap);
		System.out.println("rev = " + rev);
	}
	
	public static int t5_l407_trapRainWater(int[][] heightMap) {
		int rL = heightMap.length;
		int cL = heightMap[0].length;
		
		PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>();
		boolean[][] visited = new boolean[rL][cL];
		
		// add the four bound into minHeap
		for (int i = 0; i < rL; i++) {
			visited[i][0] = true;
			visited[i][cL - 1] = true;
			minHeap.offer(new Cell(i, 0, heightMap[i][0]));
			minHeap.offer(new Cell(i, cL - 1, heightMap[i][cL - 1]));
		}
		for (int j = 1; j < cL; j++) {
			visited[0][j] = true;
			visited[rL - 1][j] = true;
			minHeap.offer(new Cell(0, j, heightMap[0][j]));
			minHeap.offer(new Cell(rL - 1, j, heightMap[rL - 1][j]));
		}
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int sum = 0;
		
		while(!minHeap.isEmpty()) {
			Cell cur = minHeap.poll();
			for (int k = 0; k < 4; k++) {
				int nextX = cur.x + dx[k];
				int nextY = cur.y + dy[k];
				if (nextX >= 0 && nextX < rL && 
					nextY >= 0 && nextY < cL && 
					!visited[nextX][nextY]) {
					visited[nextX][nextY] = true;
					sum += Math.max(0, cur.h - heightMap[nextX][nextY]);
					minHeap.offer(new Cell(nextX, nextY, Math.max(heightMap[nextX][nextY], cur.h)));
				}
			}
		}
		return sum;
	}
	
	
	
	
	
	
	
	/*
	 * Graph
	 * 12 102 Binary Tree Level Order Traversal
	 * 
	 * 13 103 Binary Tree Zig-zag Level Order Traversal
	 * 
	 * 14 126 Word Ladder II
	 * 
	 * 15 133 Clone Graph
	 * 
	 * 16 261 Graph Valid Tree
	 * 
	 * 17 323 Number of Connected Components in an Undirected Graph
	 * 
	 * 18 513 FInd Largest Value in Each Tree Row
	 * 
	 */
	
	
	
	
	
	
	

}

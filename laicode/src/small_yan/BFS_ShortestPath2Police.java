package small_yan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import debug.Debug;

public class BFS_ShortestPath2Police {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * 一个 n*n 矩阵,每个房间可能是封闭的房间,可能是警察,可能是开的房间,封闭的房间不能 过,
	 * 返回一个 n*n矩阵, 每一个元素是最近的警察到这个房间的最短距离.
	 * C ­ closed
	 * O ­ opened
	 * P ­ police
	 * 
	 * OOOO 
	 * OCCP 
	 * OPCO
	 * OCOO
	 * 
	 * FB：There is a museum organized as NxN room. Some rooms are locked and inaccessible. 
	 * Other rooms are open and some rooms have guards. 
	 * Guards can only move north, south, east and west, only through open rooms and only within the museum. 
	 * For each room, find the shortest distance to a guard.
	 * What is the time complexity of your algorithm?
	 * 
	 * what if there is no Obstacle
	 * 
	 */
	public static void test() {
		char[][] matrix = {
				"OOOO".toCharArray(),
				"OCCP".toCharArray(),
				"OPCO".toCharArray(),
				"OCOO".toCharArray()
		};
		shortestPath2Place(matrix);
	}
	
	public static class Pair{
		public int x;
		public int y;
		public Pair(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
	
	public static int[][] shortestPath2Place(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return null;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] shortestPath = new int[rLen][cLen];
		
		for(int i = 0; i < rLen;i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (matrix[i][j] == 'P') {
					shortestPath[i][j] = 0;
				} else {
					shortestPath[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (matrix[i][j] == 'P') {
					BFS(matrix, shortestPath, i, j);
					Debug.printMatrix(shortestPath);
				}
			}
		}
		Debug.printMatrix(shortestPath);
		return shortestPath;
	}
	
	public static void BFS(char[][] matrix, int[][] shortestPath, int i, int j) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		boolean[][] visited = new boolean[rLen][cLen];
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(i, j));
		visited[i][j] = true;
		System.out.println(q.size());
		int distance = 0;
		while( !q.isEmpty()) {
			int size = q.size();
			for(int k = 0; k < size; k ++) {
				// expand this node
				Pair cur = q.poll();
				shortestPath[cur.x][cur.y] = Math.min(shortestPath[cur.x][cur.y], distance);
				
				ArrayList<Pair> neighbors = getNeighbors(matrix, visited, cur);
				for(Pair nei: neighbors) {
					q.offer(nei);
					visited[nei.x][nei.y] = true;
				}
			}
			distance ++;
		}	
	}
	
	
	public static ArrayList<Pair> getNeighbors(char[][] matrix, boolean[][] visited, Pair cur) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		ArrayList<Pair> neighbors = new ArrayList<Pair>();
		if (cur.x + 1 < rLen && !visited[cur.x + 1][cur.y] && matrix[cur.x + 1][cur.y] != 'C') {
			neighbors.add(new Pair(cur.x + 1, cur.y));
		}
		if (cur.x - 1 >= 0 && !visited[cur.x - 1][cur.y] && matrix[cur.x - 1][cur.y] != 'C') {
			neighbors.add(new Pair(cur.x - 1, cur.y));
		}
		if (cur.y + 1 < cLen && !visited[cur.x][cur.y + 1] && matrix[cur.x][cur.y + 1] != 'C') {
			neighbors.add(new Pair(cur.x, cur.y + 1));
		}
		if (cur.y - 1 >= 0 && !visited[cur.x][cur.y - 1] && matrix[cur.x ][cur.y - 1] != 'C') {
			neighbors.add(new Pair(cur.x, cur.y - 1));
		}
		return neighbors;	
	}
	

}


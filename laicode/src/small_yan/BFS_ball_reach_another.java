package small_yan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_ball_reach_another {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	public static void test() {
		char[][] matrix = {
				"ooooo".toCharArray(),
				"oooxo".toCharArray(),
				"oxooo".toCharArray(),
				"ooxxx".toCharArray(),
				"oxooo".toCharArray()
		};
		
		int input_x = 0;
		int input_y = 0;
		
		int target_x = 4;
		int target_y = 4;
		
		boolean result = ball_reach(matrix, input_x, input_y, target_x, target_y);
		System.out.println("result = " + result);
		
	}
	
	public static class Pair{
		public int x;
		public int y;
		public Pair(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
	
	public static boolean ball_reach(char[][] matrix, int input_x, int input_y, int target_x, int target_y) {
		return BFS(matrix, input_x, input_y, target_x, target_y);
	}
	
	
	public static boolean BFS(char[][] matrix, int input_x, int input_y, int target_x, int target_y) {
		if (matrix == null ||matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return false;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		// if out of bounds
		if (input_x < 0 || input_x >= rLen || input_y < 0 || input_y >= cLen || 
		    target_x < 0 || target_x >= rLen || target_y < 0 || target_y >= cLen) {
			return false;
		}
		
		Pair start = new Pair(input_x, input_y);
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visited = new boolean[rLen][cLen];
		q.add(start);
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			// expand this pair
			if (cur.x == target_x && cur.y == target_y) {
				return true;
			}
			// generate 
			ArrayList<Pair> neighbors = getNeighbor(matrix, visited, cur);
			for(Pair nei: neighbors) {
				q.add(nei);
				visited[nei.x][nei.y] = true;
			}
		}
		return false;
	}
	
	
	public static ArrayList<Pair> getNeighbor(char[][] matrix,boolean[][] visited ,Pair cur) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		ArrayList<Pair> neighbors = new ArrayList<Pair>();
		if (cur.x + 1 < rLen && !visited[cur.x + 1][cur.y] && matrix[cur.x + 1][cur.y] != 'x') {
			neighbors.add(new Pair(cur.x + 1, cur.y));
		}
		if (cur.x - 1 >= 0 && !visited[cur.x - 1][cur.y] && matrix[cur.x - 1][cur.y] != 'x') {
			neighbors.add(new Pair(cur.x - 1, cur.y));
		}
		if (cur.y + 1 < cLen && !visited[cur.x][cur.y + 1] && matrix[cur.x][cur.y + 1] != 'x') {
			neighbors.add(new Pair(cur.x, cur.y + 1));
		}
		if (cur.y - 1 >= 0 && !visited[cur.x][cur.y - 1] && matrix[cur.x][cur.y - 1] != 'x') {
			neighbors.add(new Pair(cur.x, cur.y - 1));
		}
		return neighbors;
	}

}

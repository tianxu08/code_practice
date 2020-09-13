package small_yan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_PutChair_Ob {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static class Pair{
		public int x;
		public int y;
		public Pair(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
	
	public static void test() {
		char[][] gym = {
				{'E',  'O', ' '},
				{' ', 'E', ' '},
				{' ',  ' ', ' '}
		};
		List<Integer> result = putChair(gym);
		System.out.println(result);
	}
	
	public static List<Integer> putChair(char[][] gym) {
		List<Integer> result = new ArrayList<Integer>();
		if (gym == null || gym.length == 0 || gym[0] == null || gym[0].length == 0) {
			return result;
		}
		int rLen = gym.length;
		int cLen = gym[0].length;
		int[][] costSum = new int[rLen][cLen];
		Pair rev = null;
		
		for(int i = 0; i < rLen; i ++ ) {
			for(int j = 0; j < cLen; j ++) {
				if (gym[i][j] == 'E') {
					if (!BFS(gym, costSum, i, j)) {
						return null;
					}
				}
			}
		}
		
		
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (gym[i][j] != 'O' && gym[i][j] != 'E') {
					if (rev == null) {
						rev = new Pair(i, j);
					} else {
						if (costSum[i][j] < costSum[rev.x][rev.y]) {
							rev.x = i;
							rev.y = j;
						}
					}
				}
			}
		}
		
		if (rev == null) {
			return null;
		} else {
			result.add(rev.x);
			result.add(rev.y);
		}
	
		return result;
	}
	
	public static boolean BFS(char[][] gym, int[][] costSum, int i, int j) {
		int rLen = gym.length;
		int cLen = gym[0].length;
		
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visited = new boolean[rLen][cLen];
		q.add(new Pair(i, j));
		visited[i][j] = true;
		int cost = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int k = 0; k < size; ++k) {
				Pair cur = q.poll();
				// expand this node
				costSum[cur.x][cur.y] += cost;
				ArrayList<Pair> neighbors = getNeighbors(gym, visited, cur);
				for(Pair nei: neighbors) {
					q.add(nei);
					visited[nei.x][nei.y] = true;;
				}
			}
			cost ++;
		}
		
		for(int m  = 0; m < rLen; m ++) {
			for(int n = 0; n < cLen; n ++) {
				if (gym[m][n] == 'E' && !visited[m][n]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static ArrayList<Pair> getNeighbors(char[][] gym, boolean[][] visited, Pair cur) {
		ArrayList<Pair> neighbors = new ArrayList<Pair>();
		int rLen = gym.length;
		int cLen = gym[0].length;
		
		if (cur.x + 1 < rLen &&!visited[cur.x + 1][cur.y]&& gym[cur.x + 1][cur.y] != 'O') {
			neighbors.add(new Pair(cur.x + 1, cur.y));
		}

		if (cur.x - 1 >= 0 && !visited[cur.x - 1][cur.y] &&gym[cur.x - 1][cur.y] != 'O') {
			neighbors.add(new Pair(cur.x - 1, cur.y));
		}

		if (cur.y + 1 < cLen && !visited[cur.x][cur.y + 1] &&gym[cur.x][cur.y + 1] != 'O') {
			neighbors.add(new Pair(cur.x, cur.y + 1));
		}

		if (cur.y - 1 >= 0 && !visited[cur.x][cur.y - 1] &&gym[cur.x][cur.y - 1] != 'O') {
			neighbors.add(new Pair(cur.x, cur.y - 1));
		}
		return neighbors;
	}

}

package lai_online2;

import java.util.*;
public class Class99_25_Place_To_Put_Chair_2 {
	  public List<Integer> putChair(char[][] gym) {
	    // write your solution here
	    if (gym == null || gym.length == 0 || gym[0] == null || gym[0].length == 0) {
				return null;
			}
			int rlen = gym.length;
			int clen = gym[0].length;
			int[][] costSum = new int[rlen][clen];
			Pair result = null;
			
			for(int i = 0; i < rlen; i ++) {
				for(int j = 0; j < clen; j ++) {
					if (gym[i][j] == 'E') {
						Q6_AddCost(gym, costSum, i, j);
					}
				}
			}
			// traverse the costSum matrix
			for(int i = 0; i < rlen; i ++) {
				for(int j = 0; j < clen; j ++) {
					if (result == null) {
							result = new Pair(i, j);
						} else {
							if (costSum[result.x][result.y] > costSum[i][j]) {
								result.x = i;
								result.y = j;
							}
						}
				}
			}
			List<Integer> rev = new ArrayList<Integer>();
			if (result == null) {
				return rev;
			} else {
				
				rev.add(result.x);
				rev.add(result.y);
				return rev;
			}
	  }
	  public class Pair {
			public int x;
			public int y;
			public Pair(int _x, int _y) {
				this.x = _x;
				this.y = _y;
			}
		}
	  
	  public  void Q6_AddCost(char[][] gym, int[][] costSum, int i, int j) {
			int rLen = gym.length;
			int cLen = gym[0].length;
			boolean[][] visited = new boolean[rLen][cLen];
			Queue<Pair> q = new LinkedList<Pair>();
			q.offer(new Pair(i, j));
			visited[i][j] = true;
			int cost = 0;
			while(!q.isEmpty()) {
				int size = q.size();
				for(int ith = 0; ith < size; ith ++) {
					// expand cur
					Pair cur = q.poll();
					costSum[cur.x][cur.y] += cost;
					List<Pair> neighbors = Q6_GetNeighbors(gym, cur);
					for(Pair neighbor: neighbors) {
						if (!visited[neighbor.x][neighbor.y]) {
							// not been expanded yet
							q.offer(neighbor);
							visited[neighbor.x][neighbor.y] = true;
						}
					}
				}
				cost ++;
			}
			
		}
		
		public  List<Pair> Q6_GetNeighbors(char[][] gym, Pair cur) {
			int rLen = gym.length;
			int cLen = gym[0].length;
			List<Pair> neighbors = new ArrayList<Pair>();
			if (cur.x + 1 < rLen) {
				neighbors.add(new Pair(cur.x + 1, cur.y));
			}
			if (cur.x - 1 >= 0) {
				neighbors.add(new Pair(cur.x - 1, cur.y));
			}
			if (cur.y + 1 < cLen) {
				neighbors.add(new Pair(cur.x, cur.y + 1));
			}
			if (cur.y - 1 >= 0) {
				neighbors.add(new Pair(cur.x, cur.y - 1));
			}
			return neighbors;
		}
	}
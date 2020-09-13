package lai_online2;
import java.util.*;

public class Class25_Place_To_Put_The_Chair {
	public List<Integer> putChair(char[][] gym) {
		// write your solution here
		if (gym == null || gym.length == 0 || gym[0] == null
				|| gym[0].length == 0) {
			return null;
		}
		int rlen = gym.length;
		int clen = gym[0].length;
		int[][] costSum = new int[rlen][clen];
		Pair result = null;

		// get costSum matrix
		for (int i = 0; i < rlen; i++) {
			for (int j = 0; j < clen; j++) {
				if (gym[i][j] == 'E') {
					if (!Q5_AddCost_Lai(gym, costSum, i, j)) {
						return null;
					}
				}
			}
		}

		// get the position to put the chair
		for (int i = 0; i < rlen; i++) {
			for (int j = 0; j < clen; j++) {
				if (gym[i][j] != 'O' && gym[i][j] != 'E') {
					if (result == null) {
						result = new Pair(i, j);
					} else {
						if (costSum[i][j] < costSum[result.x][result.y]) {
							result.x = i;
							result.y = j;
						}
					}
				}
			}
		}

		List<Integer> rev = new ArrayList<Integer>();
		if (result == null) {
			return null;
		} else {
			rev.add(result.x);
			rev.add(result.y);
		}

		return rev;
	}

	public class Pair {
		public int x;
		public int y;

		public Pair(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}

	// this function run Dijkstra's algorithm from one equipment and update
	// every empty cell's cost,
	// adding distance to this equipment.
	// assume (i,j) are equipment.
	public boolean Q5_AddCost_Lai(char[][] gym, int[][] costSum, int i, int j) {
		int rlen = gym.length;
		int clen = gym[0].length;
		boolean[][] visited = new boolean[rlen][clen];
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(i, j));

		int cost = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int ith = 0; ith < size; ith++) {
				// expand the node
				Pair curPair = q.poll();
				costSum[curPair.x][curPair.y] += cost;
				// generate its neighbor
				List<Pair> neighbors = Q5_getNeighbors_Lai(curPair, gym);
				// add neighbors into the queue
				for (Pair p : neighbors) {
					if (!visited[p.x][p.y]) { // the
						q.add(p);
						visited[p.x][p.y] = true;
					}
				}
			}
			cost++; // the next layer, cost ++
		}

		// traverse the gym again to make sure that every equipment is
		// accessible.
		// if there is equipment unaccessible, so, the distance from any cell to
		// this equipment is infinite.
		// there is no such position to put chair in, and let the sum of
		// distance is minimum.
		for (int x = 0; x < rlen; x++) {
			for (int y = 0; y < clen; y++) {
				if (gym[x][y] == 'E' && visited[x][y] == false) {
					return false;
				}
			}
		}
		return true;
	}

	private List<Pair> Q5_getNeighbors_Lai(Pair cur, char[][] gym) {
		int rlen = gym.length;
		int clen = gym[0].length;
		List<Pair> neighbors = new ArrayList<Pair>();

		if (cur.x + 1 < rlen && gym[cur.x + 1][cur.y] != 'O') {
			neighbors.add(new Pair(cur.x + 1, cur.y));
		}

		if (cur.x - 1 >= 0 && gym[cur.x - 1][cur.y] != 'O') {
			neighbors.add(new Pair(cur.x - 1, cur.y));
		}

		if (cur.y + 1 < clen && gym[cur.x][cur.y + 1] != 'O') {
			neighbors.add(new Pair(cur.x, cur.y + 1));
		}

		if (cur.y - 1 >= 0 && gym[cur.x][cur.y - 1] != 'O') {
			neighbors.add(new Pair(cur.x, cur.y - 1));
		}
		return neighbors;
	}
}

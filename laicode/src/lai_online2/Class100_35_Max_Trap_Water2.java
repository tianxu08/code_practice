package lai_online2;
import java.util.*;
public class Class100_35_Max_Trap_Water2 {

	public int maxTrapped(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		int[][] level = new int[rLen][cLen];

		// initialize the level matrix
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (i == 0 || j == 0 || i == rLen - 1 || j == cLen - 1) {
					level[i][j] = matrix[i][j];
				} else {
					level[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		Comparator<Coordinate> myComp = new Comparator<Coordinate>() {

			@Override
			public int compare(Coordinate o1, Coordinate o2) {
				// TODO Auto-generated method stub
				return o1.level - o2.level;
			}
		};
		PriorityQueue<Coordinate> minHeap = new PriorityQueue<Coordinate>(rLen
				* cLen, myComp);

		// visit the border of the matrix
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (i == 0 || j == 0 || i == rLen - 1 || j == cLen - 1) {
					Coordinate point = new Coordinate(i, j, matrix[i][j],
							level[i][j]);
					// put the point into priority queue
					minHeap.add(point);
					visited[i][j] = true;
				}
			}
		}
		int sum = 0;

		while (!minHeap.isEmpty()) {
			Coordinate cur = minHeap.poll();
			sum += cur.level - cur.height;

			List<Coordinate> neighbors = Q7_getNeighbor(matrix, cur, visited,
					level);
			for (Coordinate neighbor : neighbors) {
				if (visited[neighbor.x][neighbor.y] == false) {
					int newLevel = Math
							.max(neighbor.height, Math.min(
									level[neighbor.x][neighbor.y],
									level[cur.x][cur.y]));

					if (level[neighbor.x][neighbor.y] != newLevel) {
						// update in neighbor's. level
						neighbor.level = newLevel;
						level[neighbor.x][neighbor.y] = newLevel;
						minHeap.add(neighbor);
						visited[neighbor.x][neighbor.y] = true;
					}
				}

			}
		}
		return sum;
	}

	public class Coordinate {
		public int x;
		public int y;
		public int height;
		public int level;

		public Coordinate(int _x, int _y, int _height, int _level) {
			this.x = _x;
			this.y = _y;
			this.height = _height;
			this.level = _level;
		}
	}

	public List<Coordinate> Q7_getNeighbor(int[][] matrix,
			Coordinate coordinate, boolean[][] visited, int[][] level) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int x = coordinate.x;
		int y = coordinate.y;
		List<Coordinate> neighbor = new ArrayList<Coordinate>();
		if (x + 1 < rLen && !visited[x + 1][y]) {
			neighbor.add(new Coordinate(x + 1, y, matrix[x + 1][y],
					level[x + 1][y]));
		}
		if (x - 1 >= 0 && !visited[x - 1][y]) {
			neighbor.add(new Coordinate(x - 1, y, matrix[x - 1][y],
					level[x - 1][y]));
		}

		if (y - 1 >= 0 && !visited[x][y - 1]) {
			neighbor.add(new Coordinate(x, y - 1, matrix[x][y - 1],
					level[x][y - 1]));
		}
		if (y + 1 < cLen && !visited[x][y + 1]) {
			neighbor.add(new Coordinate(x, y + 1, matrix[x][y + 1],
					level[x][y + 1]));
		}
		return neighbor;
	}
}

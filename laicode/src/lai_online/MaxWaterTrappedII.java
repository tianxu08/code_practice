package lai_online;

import java.util.*;

public class MaxWaterTrappedII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxTrapped(final int[][] matrix) {
		// matrix is not null, M * N, M > 0 & N > 0, non-negative integers.
		int M = matrix.length;
		int N = matrix[0].length;
		if (M < 3 || N < 3) {
			return 0;
		}

		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>();
		boolean[][] visited = new boolean[M][N];
		
		// the first line and last line into minHeap
		for (int j = 0; j < N; j++) {
			minHeap.offer(new Pair(0, j, matrix[0][j]));
			minHeap.offer(new Pair(M - 1, j, matrix[M - 1][j]));
			visited[0][j] = true;
			visited[M - 1][j] = true;
		}

		// the first column and last column into minHeap
		for (int i = 1; i < M - 1; i++) {
			minHeap.offer(new Pair(i, 0, matrix[i][0]));
			minHeap.offer(new Pair(i, N - 1, matrix[i][N - 1]));
			visited[i][0] = true;
			visited[i][N - 1] = true;
		}

		
		int result = 0;
		while (!minHeap.isEmpty()) {
			Pair cur = minHeap.poll();
			System.out.println(cur.x + " " + cur.y);
			visited[cur.x][cur.y] = true;
			// for this node, do dfs
			result += DFS(cur, matrix, visited, minHeap, matrix[cur.x][cur.y]);
		}
		return result;
	}

	private int DFS(Pair cur, int[][] matrix, boolean[][] visited,
			PriorityQueue<Pair> minHeap, int height) {
		// get all neighbors
		List<Pair> neis = neis(cur, visited);
		
		int result = height - matrix[cur.x][cur.y];
		for (Pair nei : neis) {
			if (!visited[nei.x][nei.y]) {
				// if nei hasn't been visited
				nei.height = matrix[nei.x][nei.y];
				
				visited[nei.x][nei.y] = true;
				
				if (matrix[nei.x][nei.y] < height) {
					// the nei's height < currentNode's height
					result += DFS(nei, matrix, visited, minHeap, height);
				} else {
					// 
					minHeap.offer(nei);
				}
			}
		}
		return result;
	}

	public int[] dx = {-1, 1, 0, 0};
	public int[] dy = {0, 0, -1 ,1};
	
	private List<Pair> neis(Pair cur, boolean[][] visited) {
		List<Pair> neis = new ArrayList<Pair>();
		int rLen = visited.length;
		int cLen = visited[0].length;
		
		for(int i = 0; i < 4; i ++) {
			int nextX = cur.x + dx[i];
			int nextY = cur.y + dy[i];
			if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen) {
				neis.add(new Pair(nextX, nextY, 0));
			}
		}
		return neis;
	}

	public static class Pair implements Comparable<Pair> {
		int x;
		int y;
		int height;

		Pair(int x, int y, int height) {
			this.x = x;
			this.y = y;
			this.height = height;
		}

		@Override
		public int compareTo(Pair another) {
			if (this.height == another.height) {
				return 0;
			}
			return this.height < another.height ? -1 : 1;
		}
	}

}

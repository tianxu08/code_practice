package mj_airbnb;
import java.util.*;

public class Task27_FindingOcean {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void floodFill(char[][] board, int i, int j, char oldColor, char newColor) {
		if (board[i][j] != oldColor || board[i][j] == newColor) {
			return;
		}
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i * board[0].length + j);
		board[i][j] = newColor;
		int rLen = board.length;
		int cLen = board[0].length;
		while (!queue.isEmpty()) {
			
			int pos = queue.poll();
			int m = pos / cLen;
			int n = pos % cLen;
			
			for (int k = 0; k < 4; k++) {
				int nextX = m + dir[k][0];
				int nextY = n + dir[k][1];
				if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen && board[nextX][nextY] == oldColor) {
					queue.offer(nextX * rLen + nextY);
					board[nextX][nextY] = newColor;
				}
			}
		}
	}
	
	public static int[][] dir = {
			{0, 1}, {0, -1}, {1, 0}, {-1, 0}
	};

}

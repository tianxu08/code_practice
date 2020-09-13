package tag_backtracking;


public class Task79_WordSearch {
	/*
	 * task79
	 * 
	 * 2D board and a word, find if the word exists in the grid
	 * 
	 * The word can be constructed from letters of sequentially adjacent cell, 
	 * where "adjacent" cells are those horizontally or vertically neighboring. 
	 * The same letter cell may not be used more than once.
	 * For example,
	 * Given board =
	 * [
	 * ['A','B','C','E'],
	 * ['S','F','C','S'],
	 * ['A','D','E','E']
	 * ]
	 * word = "ABCCED", -> returns true,
	 * word = "SEE", -> returns true,
	 * word = "ABCB", -> returns false.
	 * 
	 * 
	 * use DFS from each position in the board. 
	 * 
	 * Time: Every DFS, O(n^2). From each position, O(n^2)  total O(n^4)
	 */
	
	public boolean exist(char[][] board, String word) {
		for(int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[0].length; j ++) {
				boolean[][] visited = new boolean[board.length][board[0].length];
				if (board[i][j] == word.charAt(0)) {
					if (helper(board, word, 0, visited, i, j)) 
						return true;
				}
			}
		}
		return false;
	}
	public int[] dx = {-1, 1, 0,0};
	public int[] dy = {0,  0,-1,1};
	public boolean helper(char[][] board, String word, int index, boolean[][] visited, int x, int y) {
		if (index == word.length()) {
			return true;
		}
		int rowLen = board.length;
		int colLen = board[0].length;
		if (x < 0 || x >= rowLen || y < 0 || y >= colLen ||
			board[x][y] != word.charAt(index) || visited[x][y] == true) {
			return false;
		}
		boolean rev = false;
		
		visited[x][y] = true;
		for(int i = 0; i < 4; i ++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX >= 0 && nextX < rowLen && nextY >= 0 && nextY < colLen) {
				rev = rev || helper(board, word, index + 1, visited, nextX, nextY);
			}
		}
		visited[x][y] = false;
		return rev;
	}
	
	
	// this method is faster
	public boolean exist2(char[][] board, String word) {
		int row = board.length;
		int col = board[0].length;
		boolean[][] visited = new boolean[row][col];
		char[] charOfWord = word.toCharArray();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == charOfWord[0]) {
					visited[i][j] = true;
					boolean top = check(i - 1, j, board, visited, charOfWord, 1);
					boolean bottom = check(i + 1, j, board, visited,
							charOfWord, 1);
					boolean left = check(i, j - 1, board, visited, charOfWord,
							1);
					boolean right = check(i, j + 1, board, visited, charOfWord,
							1);
					if (top || bottom || left || right) {
						return true;
					} else {
						visited[i][j] = false;
					}
				}
			}
		}
		return false;
	}

	
	
	private boolean check(int r, int c, char[][] board, boolean[][] visited,
			char[] charOfWord, int index) {
		if (index >= charOfWord.length) {
			return true;
		}
		int row = board.length;
		int col = board[0].length;
		if (r < 0 || r >= row || c < 0 || c >= col) {
			return false;
		}
		if ((!visited[r][c]) && board[r][c] == charOfWord[index]) {
			visited[r][c] = true;
			boolean top = check(r - 1, c, board, visited, charOfWord, index + 1);
			boolean bottom = check(r + 1, c, board, visited, charOfWord,
					index + 1);
			boolean left = check(r, c - 1, board, visited, charOfWord,
					index + 1);
			boolean right = check(r, c + 1, board, visited, charOfWord,
					index + 1);
			if (top || bottom || left || right) {
				return true;
			} else {
				visited[r][c] = false;
				return false;
			}
		} else {
			return false;
		}
	}
}

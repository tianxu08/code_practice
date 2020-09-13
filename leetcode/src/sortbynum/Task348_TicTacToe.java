package sortbynum;

public class Task348_TicTacToe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		Task348_TicTacToe sln = new Task348_TicTacToe(n);
		int rev = sln.move(0, 0, 1);
		System.out.println("rev = " + rev);
		rev = sln.move(0, 2, 2);
		System.out.println("rev2 = " + rev);
		rev = sln.move(2, 2, 1);
		System.out.println("rev2 = " + rev);
		rev = sln.move(1, 1, 2);
		System.out.println("rev2 = " + rev);
		rev = sln.move(2, 0, 1);
		System.out.println("rev2 = " + rev);
		rev = sln.move(1, 0, 2);
		System.out.println("rev2 = " + rev);
		rev = sln.move(0, 1, 1);
		System.out.println("rev2 = " + rev);
		rev = sln.move(1, 2, 2);
		System.out.println("rev2 = " + rev);
		
	}
	
	private int[][] matrix; 

	/** Initialize your data structure here. */
	public Task348_TicTacToe(int n) {
		// the matrix is also initial
		matrix = new int[n][n];
	}
	

	/**
	 * Player {player} makes a move at ({row}, {col}).
	 * 
	 * @param row
	 *            The row of the board.
	 * @param col
	 *            The column of the board.
	 * @param player
	 *            The player, can be either 1 or 2.
	 * @return The current winning condition, can be either: 0: No one wins. 1:
	 *         Player 1 wins. 2: Player 2 wins.
	 */
	public int move(int row, int col, int player) {
		// sanity check
		int n = this.matrix.length;
		if (row < 0 || row >= n || col < 0 || col >= n) {
			return 0;
		}
		// check whether occupied
		if (matrix[row][col] != 0) {
			return -1;
		}
		
		matrix[row][col] = player;
		// check winer
		if (getWiner(player)) {
			return player;
		}
		
		return 0;
	}
	
	public boolean getWiner(int player) {
		int n = this.matrix.length;
		// check every row
		for(int i = 0; i < matrix.length; i ++) {
			boolean isPlayer = true;
			for(int j = 0; j < matrix[0].length; j++) {
				isPlayer = isPlayer && matrix[i][j] == player;
			}
			if (isPlayer) {
				return true;
			}
		}
		// check every col
		for(int j = 0; j < matrix[0].length; j++) {
			boolean playerWin = true;
			for(int i = 0; i < n; i ++) {
				playerWin = playerWin && matrix[i][j] == player;
			}
			if (playerWin) {
				return true;
			}
		}
		
		boolean playerWin = true;
		for(int i = 0; i < n; i++) {
			playerWin = playerWin && matrix[i][i] == player;
			if (!playerWin) {
				break;
			}
		}
		if (playerWin) {
			return true;
		}
		
		playerWin = true;
		// check anti-diagnoal
		for(int i = 0; i < n; i++) {
			playerWin = playerWin && matrix[i][n - i - 1] == player;
			if (!playerWin) {
				break;
			}
		}
		if (playerWin) {
			return true;
		}
		return false;
		
	}
	
	
	

}

package debug;

public class Debug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void printMatrix(char[][] board) {
		System.out.println("-------------------------");
		for(int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[0].length; j ++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}

}

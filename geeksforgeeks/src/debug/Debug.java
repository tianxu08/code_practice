package debug;

public class Debug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void printArray(int[] array) {

	}

	public static void printMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}

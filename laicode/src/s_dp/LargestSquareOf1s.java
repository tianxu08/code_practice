package dp;

public class LargestSquareOf1s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * Largest Square Of 1s
	 * DP
	 * Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), 
	 * return the length of the largest square.
	 * Assumptions
	 * The given matrix is not null and guaranteed to be of size N * N, N >= 0
	 * 
	 * Examples
	 * { 
	 * {0, 0, 0, 0},
	 * {1, 1, 1, 1},
	 * {0, 1, 1, 1},
	 * {1, 0, 1, 1}
	 * }
	 * the largest square of 1s has length of 2
	 */

	
	/*
	 * M[i][j] stands the length of largest of 1s using (i,j) as the right bottom coordinate. 
	 * Base case: M[0][0] == matrix[0][0] == 0 ? 0: 1
	 * Induction rule: 
	 *            M[i][j] =  if matrix[i][j] == 1 min(M[i][j-1], M[i-1][j], M[i-1][j-1]) + 1 
	 *            Meanwhile, update the global maximum and global coordinate if we need to print out the sub square
	 *                       else  M[i][j] = 0;
	 *                       
	 * Time: O(n^2)
	 */
	public static void test() {
		int[][] matrix = {
				{0, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 1, 1, 1},
				{1, 0, 1, 1}
		};
		int maxLen = largest(matrix);
		System.out.println("maxLen = " + maxLen);
	}
	
	public static int largest(int[][] matrix) {
		// write your solution here
		 if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
				return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		int maxLen = 0;
		int maxX = -1;
		int maxY = -1;
		
		// initialize
		// copy the first row and first column form matrix to M
		for (int i = 0; i < rLen; i++) {
			M[i][0] = matrix[i][0];
			if (maxLen < M[i][0]) {
				maxLen = M[i][0];
				maxX = i;
				maxY = 0;
			}
		}
		for (int j = 0; j < cLen; j++) {
			M[0][j] = matrix[0][j];
			if (maxLen < M[0][j]) {
				maxLen = M[0][j];
				maxX = 0;
				maxY = j;
			}
		}
		
		for (int i = 1; i < rLen; i++) {
			for (int j = 1; j < cLen; j++) {
				if (matrix[i][j] == 1) {
					M[i][j] = Math.min(Math.min(M[i-1][j], M[i-1][j-1]), M[i][j-1]) + 1;
					if (maxLen < M[i][j]) {
						maxLen = M[i][j];
						maxX = i;
						maxY = j;
					}
				} else {
					M[i][j] = 0;
				}
 				
			}
		}
		
		MergeStone.printMatrix(M);
		
		return maxLen;
	}
	
	

}

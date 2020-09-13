package arrays;

public class P10_MaxSizeSquareSubmatrixWithAll1s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * http://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
	 */
	public static int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	
	public static void printMaxSubSquare(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] s = new int[rLen][cLen];
		
		// copy the first row and first column from matrix to s
		// copy the first row
		for (int i = 0; i < cLen; i++) {
			s[0][i] = matrix[0][i];
		}
		
		// copy the first column
		for (int i = 0; i < rLen; i++) {
			s[i][0] = matrix[i][0];
		}
		
		// get the s[][]. s[i][j] stands for the largest subsquare ending with matrix[i][j]
		// which means the right-bottom element is matrix[i][j]
		for (int i = 1; i < rLen; i++) {
			for (int j = 1; j < cLen; j++) {
				if (matrix[i][j] == 1) {
					s[i][j] = min(s[i-1][j], s[i][j-1], s[i-1][j-1]) + 1;
				} else {
					s[i][j] = 0;
				}
			}
		}
		
		// find the max in s[][]
		int max_i = 0, max_j = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (max < s[i][j]) {
					max = s[i][j];
					max_i = i;
					max_j = j;
				}
			}
		}
		
		//print out the sub square
		int start_i = max_i - max + 1;
		int start_j = max_j - max + 1;
		System.out.println("max = " + max);
		System.out.println("start_row_index = " + start_i);
		System.out.println("start_clm_index = " + start_j);
		System.out.println("end_row_index = " + max_i);
		System.out.println("end_col_index = " + max_j);
		
		for (int i = start_i; i <= max_i; i++) {
			for (int j = start_j; j <= max_j; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void test() {
		int[][] matrix = {
				{0,1,1,0,1},
				{1,1,0,1,0},
				{0,1,1,1,0},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{0,0,0,0,0}
		};
		printMaxSubSquare(matrix);
	}

}

package sortbynum;

import ds.Debug;

public class Task304_RangeSumQuery2D {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {
				  {3, 0, 1, 4, 2},
				  {5, 6, 3, 2, 1},
				  {1, 2, 0, 1, 5},
				  {4, 1, 0, 1, 7},
				  {1, 0, 3, 0, 5}
		}; 
		Task304_RangeSumQuery2D test = new Task304_RangeSumQuery2D(matrix);
		System.out.println(test.sumRegion(2, 1, 4, 3));	
	}
	
	int[][] sum;  // sum[i][j] is the sum of subMatrix starting at (0,0) and ending at (i,j)

		
	public Task304_RangeSumQuery2D(int[][] matrix) {
		this.sum = initializeSum(matrix);
		
		Debug.printMatrix(sum);
	}
	
	private int[][] initializeSum(int[][] matrix) {
		// sanity check
		if (matrix == null || matrix.length == 0) {
			return null;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] matrixSum = new int[rLen][cLen];
		matrixSum[0][0] = matrix[0][0];
		// copy the first column and first row
		for(int i = 1; i < rLen; i ++) {
			
			matrixSum[i][0] = matrixSum[i - 1][0] + matrix[i][0];
		}
		for(int j = 1; j < cLen; j ++) {
			matrixSum[0][j] = matrixSum[0][j - 1] + matrix[0][j];
		}
		
		for(int i = 1; i < rLen; i ++) {
			int curRowSum = matrix[i][0];
			for(int j = 1; j < cLen; j++) {
				curRowSum += matrix[i][j];
				matrixSum[i][j] = matrixSum[i - 1][j] + curRowSum;
			}
		}
		return matrixSum;
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		// sanity check
		if (row1 < 0 || row1 >= this.sum.length || col1 < 0 || col1 >= this.sum[0].length) {
			return 0;
		}
		if (row2 < 0 || row2 >= this.sum.length || col2 < 0 || col2 >= this.sum[0].length) {
			return 0;
		}
		if (row2 < row1 || col2 < col1) {
			return 0;
		}	
		return sum[row2][col2] - (row1 >= 1 ?  sum[row1 - 1][col2] : 0) - (col1 >= 1 ? sum[row2][col1 - 1] : 0) + 
			 (row1 >= 1 && col1 >= 1 ? sum[row1 - 1][col1 - 1] : 0);
	}
}

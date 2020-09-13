package dp;

import debug.Debug;

public class Longest1sExtension {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * Extension 3
	 * Largest X Of 1s Hard DP
	 * Given a matrix that contains only 1s and 0s, 
	 * find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.
	 * Return the arm length of the largest X shape.
	 * Assumptions
	 * The given matrix is not null
	 * Examples
	 * { 
	 *   {0, 0, 0, 0},
	 *   {1, 1, 1, 1},
	 *   {0, 1, 1, 1},
	 *   {1, 0, 1, 1} 
	 * }
	 * 0000
	 * 1111
	 * 0111
	 * 1011
	 * the largest X of 1s has arm length 2.
	 * 
	 * Almost the same idea with extension 2
	 * the different is we need to do the longest1s
	 * 
	 * left-top  to right bottom, the matrix should be M1
	 *    0000
	 *    1111 
	 *    0222
	 *    1033
	 * right-bottom to left-top M2
	 *       0000
	 *       3321
	 *       0221
	 *       1011
	 * left-bottom to right-top M3
	 * 0 0 0 0 
	 * 1 1 3 2 
	 * 0 2 1 2 
	 * 1 0 1 1
	 * right-top to left-bottom M4
	 * 0 0 0 0 
	 * 1 1 1 1 
	 * 0 2 2 1 
	 * 3 0 2 1 
	 * 
	 * after we get M1, M2, M3, M4, for every (i, j), get the minimum 
	 * the global max is the max of the minimum. 
	 * 
	 */
	public static void test() {
		int[][] matrix = {
				{1,1},
				{1,0},
				{1,1},
				{1,0},
				{1,1},
				{1,0},
		};
		int[][] matrix2 = {
				{1,1,1,1,1,1,0},
				{0,0,0,1,1,1,1},
		};
		int[][] M1 = getRigthBottom2LeftTop(matrix);
		System.out.println("-------------");
		int[][] M2 = getRigthBottom2LeftTop(matrix2);
		System.out.println("-------------");
		
		int[][] M3 = getLeftTop2RigthBottom(matrix);
		System.out.println("----------------------");
		int[][] M4 = getLeftTop2RigthBottom(matrix2);
		
		System.out.println("----------------------");
		int[][] M5 = getLeftBottom2RightTop(matrix);
		System.out.println("----------------------");
		int[][] M6 = getLeftBottom2RightTop(matrix2);
		System.out.println("----------------------");
		int[][] M7 = getRightTop2LeftBottom(matrix);
		System.out.println("----------------------");
		int[][] M8 = getRightTop2LeftBottom(matrix2);
	}
	public static int largestX(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;	
		int[][] M1 = getLeftBottom2RightTop(matrix);
		int[][] M2 = getRightTop2LeftBottom(matrix);
		int[][] M3 = getLeftTop2RigthBottom(matrix);
		int[][] M4 = getRigthBottom2LeftTop(matrix);
		int largestX = 0;
		for(int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				int curMin = minOf4(M1[i][j], M2[i][j], M3[i][j], M4[i][j]);
				largestX = Math.max(largestX, curMin);
			}
		}
		return largestX;
	}
	
	public static int minOf4(int a, int b, int c, int d) {
		int mina_b = Math.min(a, b);
		int minc_d = Math.min(c, d);
		return Math.min(mina_b, minc_d);
	}
	
	
	public static int[][] getLeftTop2RigthBottom(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		for(int rStart = rLast; rStart >= 0; rStart --) {
			int cStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;
			while(rIndex >= 0 && rIndex <= rLast  && cIndex >= 0 && cIndex <= cLast) {
				if (cIndex == cStart) {
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex ++;
				cIndex ++;
			}
		}
		for(int cStart = 1; cStart <= cLast; cStart ++) {
			int rStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;
		
			while(rIndex >= 0 && rIndex <= rLast  && cIndex >= 0 && cIndex <= cLast) {
				if (rIndex == rStart) {
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex ++;
				cIndex ++;
			}
		}
//		Debug.printMatrix(M);
		return M;
	}
	
	
	// get right-bottom to left-top 
	public static int[][] getRigthBottom2LeftTop(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		
		for(int rStart = 0; rStart <= rLast; rStart ++) {
			int cStart = cLast;
			int rIndex = rStart;
			int cIndex = cStart;
			while(rIndex >= 0 && cIndex >= 0) {
				if (cIndex == cStart) {
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex + 1][cIndex + 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex --;
				cIndex --;
			}
		}
		
		for(int cStart = cLast - 1; cStart >= 0; cStart --) {
			int rStart = rLast;
			int rIndex = rStart;
			int cIndex = cStart;
			while(rIndex >= 0 && cIndex >= 0) {
				if (rIndex == rLast) {
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex + 1][cIndex + 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex --;
				cIndex --;
			}
		}
//		Debug.printMatrix(M);
		return M;
	}
	
	public static int[][] getLeftBottom2RightTop(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		for(int rStart = 0; rStart <= rLast; rStart ++) {
			int cStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;
			
			while(rIndex >= 0 && rIndex <= rLast  && cIndex >= 0 && cIndex <= cLast) {
				if (cIndex == cStart) { // cIndex == 0
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex + 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex --;
				cIndex ++;
			}
		}
		
		for(int cStart = 1; cStart <= cLast; cStart ++) {
			int rStart = rLast;
			int rIndex = rStart;
			int cIndex = cStart;
			
			while(rIndex >= 0 && rIndex <= rLast  && cIndex >= 0 && cIndex <= cLast) {
				if (rIndex == rStart) { // rIndex == rLast
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex + 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex --;
				cIndex ++;
			}
		}
		
//		Debug.printMatrix(M);
		return M;
	}
	
	public static int[][] getRightTop2LeftBottom(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		for(int cStart = 0; cStart <= cLast; cStart ++) {
			int rStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;
			while(rIndex >= 0 && rIndex <= rLast  && cIndex >= 0 && cIndex <= cLast) {
				if (rIndex == rStart) { // rIndex == rLast
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex + 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex ++;
				cIndex --;
			}
		}
		
		for(int rStart = 1; rStart <= rLast; rStart ++) {
			int cStart = cLast;
			int rIndex = rStart;
			int cIndex = cStart;
			while(rIndex >= 0 && rIndex <= rLast  && cIndex >= 0 && cIndex <= cLast) {
				if (cIndex == cStart) { // cIndex == cLast
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex + 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex ++;
				cIndex --;
			}
		}
//		Debug.printMatrix(M);
		return M;
	}

}

package dp;

import debug.Debug;

public class Longest1s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
	}
	
	/*
	 * Longest 1s Easy DP
	 * Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.
	 * Assumptions
	 * The given array is not null
	 * Examples
	 * {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
	 */
	
	// M[i] stands for the longest length of 1s ending with array[i](included)
	public static int longest1s(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int[] M = new int[n];
		int maxLen = 0;
		M[0] = array[0];
		maxLen = Math.max(maxLen, M[0]); // !!! don't forget update maxLen, every time, we get M[i]
		for (int i = 0; i < M.length; i++) {
			if (i == 0) {
				M[i] = array[i];
			} else {
				if (array[i] == 1) {
					M[i] = M[i-1] + 1;
				} else {
					M[i] = 0;
				}
			}
			maxLen = Math.max(maxLen, M[i]);
		}
		return maxLen;
	}
	public static void test1() {
		int[] a = {1};
		int rev = longest1s(a);
		System.out.println("rev = " + rev);
	}
	
	
	/*
	 * Extension1
	 * Largest Rectangle Of 1s Hard DP 
	 * Determine the largest rectangle of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the area.
	 * Assumptions
	 * The given matrix is not null and has size of M * N, M >= 0 and N >= 0
	 * Examples
	 * { {0, 0, 0, 0},
	 *   {1, 1, 1, 1},
	 *   {0, 1, 1, 1},
	 *   {1, 0, 1, 1} }
	 *   the largest rectangle of 1s has area of 2 * 3 = 6
	 * 
	 */
	public static int largestRectangle1s(int[][] matrix) {
		return -1;
	}
	
	/*
	 * Extension2
	 * Given a Matrix that contains only 1s and 0s, how to find the largest cross
	 * with the same arm lengths and the two arms join at the central point of each other.
	 * 
	 * Example:
	 * 
	 * 0100   
	 * 1111     
	 * 0100   
	 * 0100
	 * 
	 * Longest Cross Of 1s Hard DP
	 * Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, 
	 * with the same arm lengths and the four arms joining at the central point.
	 * Return the arm length of the largest cross.
	 * Assumptions
	 * The given matrix is not null
	 * Examples
	 * { 
	 *   { 0, 1, 0, 0 }, 
	 *   { 1, 1, 1, 1 }, 
	 *   { 0, 1, 0, 0 },
	 *   { 0, 1, 0, 0 } 
	 * }
	 * the largest cross of 1s has arm length 2.
	 * 
	 * Idea:
	 * Original Matrix
	 * 0 1 0 0 
	 * 1 1 1 1 
	 * 0 1 0 0 
	 * 0 1 0 0
	 * 
	 * from left to right M1
	 * 0 1 0 0 
	 * 1 2 3 4 
	 * 0 1 0 0 
	 * 0 1 0 0 
	 * 
	 * from right to left M2
	 * 0 1 0 0 
	 * 4 3 2 1 
	 * 0 1 0 0 
	 * 0 1 0 0
	 * 
	 * from top to bottom M3
	 * 0 1 0 0 
	 * 1 2 1 1 
	 * 0 3 0 0 
	 * 0 4 0 0
	 * 
	 * from bottom to top M4
	 * 0 4 0 0 
	 * 1 3 1 1 
	 * 0 2 0 0 
	 * 0 1 0 0
	 * 
	 * After the 4 matrixes generated, we can calculate the largest cross arm's length. 
	 * The lenOFLargestCross = max (min(M1[i][j],M2[i][j],M3[i][j],M4[i][j] )) for every pair of (i, j) 
	 * 
	 * Time: O(n^2)
	 * Space: O(n^2)
	 */
	
	public static void test2() {
		int[][] matrix = { 
				{ 0, 1, 0, 0 }, 
				{ 1, 1, 1, 1 }, 
				{ 0, 1, 0, 0 },
				{ 0, 1, 0, 0 } 
				};
		MergeStone.printMatrix(matrix);
		System.out.println("----------");
		int rev = largestCross(matrix);
		System.out.println("rev = " + rev);
	}
	public static int largestCross(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int lenOfLargestCross = 0;
		int resultI = -1;
		int resultJ = -1;
		// from left to right
		int[][] M1 = new int[rLen][cLen];
		// from right to left
		int[][] M2 = new int[rLen][cLen];
		// from top to down
		int[][] M3 = new int[rLen][cLen];
		// from down to top
		int[][] M4 = new int[rLen][cLen];
		
		// from left to right
		for(int i = 0; i < rLen; i ++) {
			for (int j = 0; j < cLen; j++) {
				if (j == 0) {
					M1[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						M1[i][j] = M1[i][j - 1] + 1;
					} else {
						M1[i][j] = 0;
					}
				}
			}
		}
		MergeStone.printMatrix(M1);
		
		// from right to left
		for (int i = 0; i < rLen; i++) {
			for (int j = cLen - 1; j >= 0; j--) {
				if (j == cLen - 1) {
					M2[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						M2[i][j] = M2[i][j + 1] + 1;
					} else {
						M2[i][j] = 0;
					}
				}
			}
		}
		System.out.println("------------------");
		MergeStone.printMatrix(M2);
		
		// from top to bottom
		for (int j = 0; j < cLen; j++) {
			for (int i = 0; i < rLen; i++) {
				if (i == 0) {
					M3[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						M3[i][j] = M3[i-1][j] + 1;
					} else {
						M3[i][j] = 0;
					}
				}
			}
		}
		
		System.out.println("------------------");
		MergeStone.printMatrix(M3);
		
		// from bottom to top
		for (int j = 0; j < cLen; j++) {
			for (int i = rLen - 1; i >= 0; i--) {
				if (i == rLen - 1) {
					M4[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						M4[i][j] = M4[i + 1][j] + 1;
					} else {
						M4[i][j] = 0;
					}
				}
			}
		}
		System.out.println("-------------------");
		MergeStone.printMatrix(M4);
		
		// for one position (i,j), find the smallest in the M1, M2, M3,M4
		// maintain a global max, the global max is the result;
		
		for(int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				int curMin = minOf4(M1[i][j], M2[i][j], M3[i][j], M4[i][j]);
				// update the lenOfLargestCross and resultI and resultJ
				if (lenOfLargestCross < curMin) {
					lenOfLargestCross = curMin;
					resultI = i;
					resultJ = j;
				}
			}
		}	
		System.out.println("i = " + resultI);
		System.out.println("j = " + resultJ);
		return lenOfLargestCross;	
	}
	
	public static int minOf4(int a, int b, int c, int d) {
		int mina_b = Math.min(a, b);
		int minc_d = Math.min(c, d);
		return Math.min(mina_b, minc_d);
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
	public static void test3() {
//		int[][] matrix = {
//				{0, 0, 0, 0},
//			    {1, 1, 1, 1},
//				{0, 1, 1, 1},
//				{1, 0, 1, 1}	
//		};
		int[][] matrix = {
				{1,1,1},
				{1,0,1},
				{1,1,1},
				{1,1,1}
				}; 
		
//		int[][] M = getLeftTop2RightBottom(matrix);
//		System.out.println("--------------------");
//		int[][] M2 = getRightBottom2LeftTop(matrix);
//		System.out.println("--------------------");
//		int[][] M3 = getLeftBottom2RightTop(matrix);
//		System.out.println("----------------------");
//		int[][] M4 = getRightTop2LeftBottom(matrix);
		int largestX = largestX(matrix);
		System.out.println("largestX = " + largestX);
	}
	
	public static int largestX(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;	
		int[][] M1 = getLeftTop2RightBottom(matrix);
		int[][] M2 = getRightBottom2LeftTop(matrix);
		int[][] M3 = getLeftBottom2RightTop(matrix);
		int[][] M4 = getRightTop2LeftBottom(matrix);
		int largestX = 0;
		for(int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				int curMin = minOf4(M1[i][j], M2[i][j], M3[i][j], M4[i][j]);
				largestX = Math.max(largestX, curMin);
			}
		}
		return largestX;

	}
	
	
	public static int[][] getLeftTop2RightBottom(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M1 = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		// index = rIndex - cIndex
		for(int index = 0 - cLast; index <= rLast; index ++) {
			if (index <= 0) {
				int rIndex = 0, cIndex = rIndex - index;
				while(rIndex <= rLast && cIndex <= cLast) {
					if (rIndex == 0) {
						M1[rIndex][cIndex] = matrix[rIndex][cIndex];
					} else {
						if (matrix[rIndex][cIndex] == 1) {
							M1[rIndex][cIndex] = M1[rIndex - 1][cIndex - 1] + 1;
						} else {
							M1[rIndex][cIndex] = 0;
						}
					}
					rIndex ++;
					cIndex ++;
				}			
			} else {
				// index > 0  index = rIndex - cIndex
				int cIndex = 0; 
				int rIndex = index + cIndex;
				while(rIndex <= rLast && cIndex <= cLast) {
					if (cIndex == 0) {
						M1[rIndex][cIndex] = matrix[rIndex][cIndex];
					} else {
						if (matrix[rIndex][cIndex] == 1) {
							M1[rIndex][cIndex] = M1[rIndex-1][cIndex-1] + 1; 
						} else {
							M1[rIndex][cIndex] = 0;
						}
					}
					rIndex ++;
					cIndex ++;
				}
			}
		}
		Debug.printMatrix(M1);
		
		return M1;
	}
	
	public static int[][] getRightBottom2LeftTop(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		//index = rIndex - cIndex
		for(int index = 0 - cLast; index <= rLast; index ++) {
			if (index <= 0) {
				int cIndex = cLast;
				int rIndex = index + cIndex;
				while(rIndex >= 0 && cIndex >= 0) {
					if (cIndex == cLast) {
						M[rIndex][cIndex] = matrix[rIndex][cIndex];
					} else {
						if (matrix[rIndex][cIndex] == 1) {
							M[rIndex][cIndex] = M[rIndex + 1][cIndex + 1] + 1; 
						} else {
							M[rIndex][cIndex] = 0;
						}
					}
					rIndex--;
					cIndex--;
				}
			} else {
				int rIndex = rLast;
				int cIndex = rIndex - index;
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
					rIndex--;
					cIndex--;
				}
			}
		}
		Debug.printMatrix(M);
		return M;
	}
	
	public static int[][] getLeftBottom2RightTop(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		// index = rIndex + cIndex  from 0 -- rLast + cLast
		for(int index = 0; index <= rLast + cLast ; index ++) {
			if (index <= rLast) {
				int cIndex = 0;
				int rIndex = index - cIndex;
				while(rIndex >= 0 && cIndex <= cLast) {
					if (cIndex ==  0) {
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
			} else {
				// index > rLast
				int rIndex = rLast;
				int cIndex = index - rIndex;
				while(rIndex >= 0 && cIndex <= cLast) {
					if (rIndex == rLast) {
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
		}
		Debug.printMatrix(M);
		return M;
	}
	
	public static int[][] getRightTop2LeftBottom(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int rLast = rLen - 1;
		int cLast = cLen - 1;
		
		// index = rIdnex + cIndex
		for(int index = 0; index <= rLast + cLast; index ++) {
			if (index <= rLast) {
				int rIndex = 0;
				int cIndex = index - rIndex;
				while(rIndex <= rLast && cIndex >= 0) {
					if (rIndex == 0) {
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
			} else {
				int cIndex = cLast;
				int rIndex = index - cIndex;
				while(rIndex <= rLast && cIndex >= 0) {
					if (cIndex == cLast) {
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
		}
		Debug.printMatrix(M);
		return M;
	}
	
	
	
	/*
	 * extension 4
	 * Q4  Given a matrix where every element is either ‘0’ or ‘1’, find the largest subsquare surrounded by ‘1’.
	 * Examples:
	 * Input: mat[N][N] = 
	 *  { 
	 *  {'1', '0',  '1', ‘1', '1'},
	 *  {'1', '1', '1',  '1', '1'},
	 *  {'1', '1', '0',  '1', '0'},
	 *  {'1', '1', '1',  ‘1,’ '1'},
	 *  {'1', '1', '1',  '0', '0'},
	 *  };
	 *  Output: 3
	 *  
	 */
	public static int largestSubsquareSurroundedBy1s(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M1 = new int[rLen][cLen];  // from right to left
		int[][] M2 = new int[rLen][cLen];  // from bottom to top
		
		// get right to left matrix
		for(int i = 0; i < rLen; i++) {
			for(int j =cLen - 1; j >= 0; j --) {
				if (j == cLen - 1) {
					M1[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						M1[i][j] = M1[i][j + 1] + 1;
					} else {
						M1[i][j] = 0;
					}
				}
			}
		}
		
		// get bottom to top matrix
		for(int j = 0; j < cLen; j ++) {
			for(int i = rLen - 1; i >= 0; i --) {
				if (i == rLen - 1) {
					M2[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						M2[i][j] = M2[i+1][j] + 1;
					} else {
						M2[i][j] = 0;
					}
				}
			}
		}
		int globalLen = 0;
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				
			}
		}
		return -1;
	}
	
	
	
	
	
	
	/*
	 * extension 5
	 * Question 5. Given a Matrix of integers (positive & negative numbers & 0s), 
	 * how to find the submatrix with the largest sum?
	 * 
	 * 
	 */
	
}

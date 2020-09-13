package lai_online2;

public class Class15 {
	/*
	 * task1
	Longest 1s
	Easy
	DP
	Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.

	Assumptions

	The given array is not null
	Examples

	{0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
	*/
	public int task1_longest(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int[] M = new int[n];
		int maxLen = 0;
		M[0] = array[0];
		maxLen = Math.max(maxLen, M[0]);
		for (int i = 1; i < M.length; i++) {
			if (array[i] == 1) {
				M[i] = M[i - 1] + 1;
				maxLen = Math.max(maxLen, M[i]);
			} else {
				M[i] = 0;
			}
		}
		return maxLen;
	}
	
	/*
	 * task2
	Largest SubArray Sum
	Fair
	DP
	Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.

	Assumptions

	The given array is not null and has length of at least 1.
	Examples

	{2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5

	{-2, -1, -3}, the largest subarray sum is -1
	*/
	public int task2_largestSum(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (sum < 0) {
				sum = 0;
			}
			sum += array[i];
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}
	
	/*
	 * task3
	Longest Cross Of 1s
	Hard
	DP
	Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, with the same arm lengths and the four arms joining at the central point.

	Return the arm length of the largest cross.

	Assumptions

	The given matrix is not null
	Examples

	{ {0, 0, 0, 0},

	  {1, 1, 1, 1},

	  {0, 1, 1, 1},

	  {1, 0, 1, 1} }

	the largest cross of 1s has arm length 2.


	*/
	public int task3_largest(int[][] matrix) {
		// write your solution here

		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
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
		for (int i = 0; i < rLen; i++) {
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

		// from top to bottom
		for (int j = 0; j < cLen; j++) {
			for (int i = 0; i < rLen; i++) {
				if (i == 0) {
					M3[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 1) {
						M3[i][j] = M3[i - 1][j] + 1;
					} else {
						M3[i][j] = 0;
					}
				}
			}
		}

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

		// for one position (i,j), find the smallest in the M1, M2, M3,M4
		// maintain a global max, the global max is the result;

		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				int curMin = minOf4(M1[i][j], M2[i][j], M3[i][j], M4[i][j]);
				if (lenOfLargestCross < curMin) {
					lenOfLargestCross = curMin;
					resultI = i;
					resultJ = j;
				}
			}
		}

		return lenOfLargestCross;

	}

	public int minOf4(int a, int b, int c, int d) {
		int mina_b = Math.min(a, b);
		int minc_d = Math.min(c, d);
		return Math.min(mina_b, minc_d);
	}
	
	/*
	 * task4
	Largest X Of 1s
	Hard
	DP
	Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.

	Return the arm length of the largest X shape.

	Assumptions

	The given matrix is not null
	Examples

	{ {0, 0, 0, 0},

	  {1, 1, 1, 1},

	  {0, 1, 1, 1},

	  {1, 0, 1, 1} }

	the largest X of 1s has arm length 2.

	*/
	
	public int task4_largest(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M1 = getLeftBottom2RightTop(matrix);
		int[][] M2 = getRightTop2LeftBottom(matrix);
		int[][] M3 = getLeftTop2RigthBottom(matrix);
		int[][] M4 = getRigthBottom2LeftTop(matrix);
		int largestX = 0;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				int curMin = minOf4(M1[i][j], M2[i][j], M3[i][j], M4[i][j]);
				largestX = Math.max(largestX, curMin);
			}
		}
		return largestX;
	}

	public int[][] getLeftTop2RigthBottom(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];

		int rLast = rLen - 1;
		int cLast = cLen - 1;

		for (int rStart = rLast; rStart >= 0; rStart--) {
			int cStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;
			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (cIndex == cStart) {
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex++;
				cIndex++;
			}
		}
		for (int cStart = 1; cStart <= cLast; cStart++) {
			int rStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;

			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (rIndex == rStart) {
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex++;
				cIndex++;
			}
		}
		// Debug.printMatrix(M);
		return M;
	}

	// get right-bottom to left-top
	public int[][] getRigthBottom2LeftTop(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];

		int rLast = rLen - 1;
		int cLast = cLen - 1;

		for (int rStart = 0; rStart <= rLast; rStart++) {
			int cStart = cLast;
			int rIndex = rStart;
			int cIndex = cStart;
			while (rIndex >= 0 && cIndex >= 0) {
				if (cIndex == cStart) {
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

		for (int cStart = cLast - 1; cStart >= 0; cStart--) {
			int rStart = rLast;
			int rIndex = rStart;
			int cIndex = cStart;
			while (rIndex >= 0 && cIndex >= 0) {
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
		// Debug.printMatrix(M);
		return M;
	}

	public int[][] getLeftBottom2RightTop(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];

		int rLast = rLen - 1;
		int cLast = cLen - 1;

		for (int rStart = 0; rStart <= rLast; rStart++) {
			int cStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;

			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (cIndex == cStart) { // cIndex == 0
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex + 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex--;
				cIndex++;
			}
		}

		for (int cStart = 1; cStart <= cLast; cStart++) {
			int rStart = rLast;
			int rIndex = rStart;
			int cIndex = cStart;

			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (rIndex == rStart) { // rIndex == rLast
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex + 1][cIndex - 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex--;
				cIndex++;
			}
		}

		// Debug.printMatrix(M);
		return M;
	}

	public int[][] getRightTop2LeftBottom(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];

		int rLast = rLen - 1;
		int cLast = cLen - 1;

		for (int cStart = 0; cStart <= cLast; cStart++) {
			int rStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;
			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (rIndex == rStart) { // rIndex == rLast
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex + 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex++;
				cIndex--;
			}
		}

		for (int rStart = 1; rStart <= rLast; rStart++) {
			int cStart = cLast;
			int rIndex = rStart;
			int cIndex = cStart;
			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (cIndex == cStart) { // cIndex == cLast
					M[rIndex][cIndex] = matrix[rIndex][cIndex];
				} else {
					if (matrix[rIndex][cIndex] == 1) {
						M[rIndex][cIndex] = M[rIndex - 1][cIndex + 1] + 1;
					} else {
						M[rIndex][cIndex] = 0;
					}
				}
				rIndex++;
				cIndex--;
			}
		}
		// Debug.printMatrix(M);
		return M;
	}
	
	/*
	 * task5
	Largest SubMatrix Sum
	Hard
	DP
	Given a matrix that contains integers, find the submatrix with the largest sum.

	Return the sum of the submatrix.

	Assumptions

	The given matrix is not null and has size of M * N, where M >= 1 and N >= 1
	Examples

	{ {1, -2, -1, 4},

	  {1, -1,  1, 1},

	  {0, -1, -1, 1},

	  {0,  0,  1, 1} }

	the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.

	 */
	
	public int task5_largest(int[][] matrix) {
		// write your solution here
		return 0;

	}
}

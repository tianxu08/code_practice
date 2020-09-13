package lai_online;

import java.util.Arrays;

import debug.Debug;

public class Class15_DP3 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 test1_2();
		// test3_2();
		// test1_3();
		// test2_2();
		// test4();
//		test2_3();
		// test100();
		// test2_3_1();
	}

	/*
	 * Summary: 
	 * 
	 * task1.1: Largest Subarray Sum 
	 * task1.2: Largest Subarray Sum, print out the subarray 
	 * task1.3: Largest SubMatrix Sum
	 * 
	 * task2.1: Largest SubArray Product 
	 * task2.2: Largest SubArray Product, print out the subArray 
	 * task2.3: Largest SubMatrix Product, print out the SubMatrix
	 * 
	 * task3.1: Longest Connecutive "1"s 
	 * task3.2: Largest Cross With All "1"s
	 * task3.3: Largest X With All "1"s 
	 * task3.4: Given a matrix where every element is either 0 or 1, fine the largest subSquare surrounded by '1'
	 * 
	 * task4: Cutting Wood I
	 */

	/*
	 * task1.1 Largest SubArray Sum Given an unsorted integer array, find the
	 * subarray that has the greatest sum. Return the sum. Assumptions The given
	 * array is not null and has length of at least 1. Examples {2, -1, 4, -2,
	 * 1}, the largest subarray sum is 2 + (-1) + 4 = 5 {-2, -1, -3}, the
	 * largest subarray sum is -1
	 */

	public int task1_largestSum(int[] array) {
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
	 * task1.2 Largest SubArray Sum, print out the subArray
	 */

	public static void test1_2() {
		int[] array = { -1, -3, 4, -2, 5 };
		int[] result = task1_2_largestSumArray(array);
		System.out.println(Arrays.toString(result));
		
		int[] res2 = task1_2_largestSumArray2(array);
		System.out.println(Arrays.toString(res2));
	}

	public static int[] task1_2_largestSumArray(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		
		int start = 0, end = -1;
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			// add the array[i] to sum
			sum += array[i];
			// if sum < 0, set sum = 0 and update start
			if (sum < 0) {
				sum = 0;
				start = i + 1;
			} else if (sum > maxSum) {
				maxSum = sum;
				end = i;
			}
		}

		if (end == -1) {
			// end == -1
			// All elements are negative. traverse the array and get the largest
			// negative element
			for (int i = 0; i < array.length; i++) {
				if (array[i] > maxSum) {
					start = i;
					end = i;
					maxSum = array[i];
				}
			}
		}
		// copyOfRange(Array, start, end + 1) copies : array[start..end]
		return Arrays.copyOfRange(array, start, end + 1);
	}

	public static int[] task1_2_largestSumArray2(int[] array) {
		int curSum = 0;
		int maxSum = Integer.MIN_VALUE;
		int endIdx = -1;
		for(int i = 0; i < array.length; i++) {
			if (curSum < 0) {
				curSum = 0;
			}
			curSum = curSum + array[i];
			if (maxSum < curSum) {
				maxSum = curSum;
				endIdx = i;
			}
		}
		
		int maxSumCpy = maxSum;
		int startIdx = endIdx;
		while(maxSumCpy != 0 && startIdx >= 0) {
			maxSumCpy -= array[startIdx];
			startIdx --;
		}
		startIdx ++;
		
		return Arrays.copyOfRange(array, startIdx, endIdx + 1);
	}
	
	/*
	 * task1.3 Largest SubMatrix Sum Hard DP Given a matrix that contains
	 * integers, find the submatrix with the largest sum.
	 * 
	 * Return the sum of the submatrix.
	 * 
	 * Assumptions
	 * 
	 * The given matrix is not null and has size of M * N, where M >= 1 and N >=
	 * 1 Examples
	 * 
	 * { {1, -2, -1, 4},
	 * 
	 * {1, -1, 1, 1},
	 * 
	 * {0, -1, -1, 1},
	 * 
	 * {0, 0, 1, 1}
	 * 
	 * }
	 * 
	 * the largest subMatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
	 * 
	 * 
	 * Method1: O(n^3) Method2: O(n^4)
	 */
	public static void test1_3() {
		int[][] matrix = { { 1, -2, -1, 4 }, { 1, -1, 1, 1 }, { 0, -1, 0, 1 },
				{ 0, 0, 1, 1 } };
		int maxSum = task1_3_largest(matrix);
		int maxSum2 = task1_3_maxSubMatrix_method2(matrix);
	}

	public static int task1_3_largest(int[][] matrix) {
		// write your solution here
		int rLen = matrix.length;
		int cLen = matrix[0].length;

		int maxSum = Integer.MIN_VALUE;
		int start_x = -1, start_y = -1;
		int end_x = -1, end_y = -1;
		for (int leftBound = 0; leftBound < cLen; leftBound++) {

			int[] curSum = new int[rLen];
			for (int rightBound = leftBound; rightBound < cLen; rightBound++) {

				for (int i = 0; i < rLen; i++) {
					curSum[i] += matrix[i][rightBound];
				}
				Wrapper pos = task1_3_largestSumSubArray(curSum);

				if (pos.maxVal > maxSum) {
					start_x = pos.start;
					start_y = leftBound;

					end_x = pos.end;
					end_y = rightBound;
					maxSum = pos.maxVal;
				}
			}
		}

		// print out the matrix
		for (int i = start_x; i <= end_x; i++) {
			for (int j = start_y; j <= end_y; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("maxSum = " + maxSum);
		return maxSum;
	}

	public static class Wrapper {
		int start;
		int end;
		int maxVal;

		public Wrapper(int s, int e, int m) {
			this.start = s;
			this.end = e;
			this.maxVal = m;
		}
	}

	// return the maxSum Of SubArray's start, end, and maxSum
	public static Wrapper task1_3_largestSumSubArray(int[] array) {
		int start = 0, end = -1;
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;

		for (int i = 0; i < array.length; i++) {
			sum += array[i];
			if (sum < 0) {
				start = i + 1;
			} else {
				if (sum > maxSum) {
					maxSum = sum;
					end = i;
				}
			}
		}

		if (end == -1) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] > maxSum) {
					maxSum = array[i];
					start = i;
					end = i;
				}
			}
		}
		return new Wrapper(start, end, maxSum);
	}

	// M[i][j] is the sum of subMatrix ending in [i][j]
	// a subMatrix starting with (x1, y1) and ending with (x2, y2) is:
	// M[x2][y2] - M[x2][y1 - 1] - M[x1][y2 - 1] + M[x1- 1][y1 - 1]
	// don't forget to do the sanity before query M[xi][yi]

	public static int task1_3_maxSubMatrix_method2(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];

		for (int j = 0; j < cLen; j++) {
			M[0][j] = j == 0 ? matrix[0][j] : M[0][j - 1] + matrix[0][j];
		}

		for (int i = 0; i < rLen; i++) {
			M[i][0] = i == 0 ? matrix[i][0] : M[i - 1][0] + matrix[i][0];
		}

		for (int i = 1; i < rLen; i++) {
			int curSumInThisRow = matrix[i][0]; // !!! here, get from
												// matrix[i][0]
			for (int j = 1; j < cLen; j++) {
				curSumInThisRow += matrix[i][j];
				M[i][j] = M[i - 1][j] + curSumInThisRow;
			}
		}

		int maxSum = Integer.MIN_VALUE;
		int final_x1 = -1, final_y1 = -1;
		int final_x2 = -1, final_y2 = -1;

		// O(n^4)
		for (int x1 = 0; x1 < rLen; x1++) {
			for (int y1 = 0; y1 < cLen; y1++) {
				for (int x2 = x1 + 1; x2 < rLen; x2++) {
					for (int y2 = y1 + 1; y2 < cLen; y2++) {
						int curSum = M[x2][y2] - (y1 >= 1 ? M[x2][y1 - 1] : 0)
								- (x1 >= 1 ? M[x1 - 1][y2] : 0)
								+ (x1 >= 1 && y1 >= 1 ? M[x1 - 1][y1 - 1] : 0);

						if (curSum > maxSum) {
							maxSum = curSum;
							final_x1 = x1;
							final_y1 = y1;
							final_x2 = x2;
							final_y2 = y2;
						}
					}
				}
			}
		}

		System.out.println("-------------");

		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------");

		System.out.println("=============");
		System.out.println("x1 = " + final_x1);
		System.out.println("y1 = " + final_y1);
		System.out.println("x2 = " + final_x2);
		System.out.println("y2 = " + final_y2);
		System.out.println("=============");

		for (int i = final_x1; i <= final_x2; i++) {
			for (int j = final_y1; j <= final_y2; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("maxSum = " + maxSum);
		return maxSum;
	}

	/*
	 * task2.1: Largest SubArray Product Time: O(n) Space: O(n)
	 */
	public static int task2_1_maxSubArrayProduct(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int[] maxEndingHere = new int[array.length];
		int[] minEndingHere = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				maxEndingHere[i] = array[i];
				minEndingHere[i] = array[i];
			} else {
				maxEndingHere[i] = Math.max(array[i], Math.max(
						maxEndingHere[i - 1] * array[i], minEndingHere[i - 1]
								* array[i]));
				minEndingHere[i] = Math.min(array[i], Math.max(
						maxEndingHere[i - 1] * array[i], minEndingHere[i - 1]
								* array[i]));
			}
		}

		int maxProduct = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			maxProduct = Math.max(maxProduct, maxEndingHere[i]);
		}

		return maxProduct;
	}

	/*
	 * t2_1_2 max subarray product time: O(n) Space: O(1)
	 */

	public static int task2_1_2_maxProduct(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}

		int prev_max = 1;
		int prev_min = 1;

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			int max_ending_here = maxof3(prev_max * array[i], prev_min
					* array[i], array[i]);
			int min_ending_here = minof3(prev_max * array[i], prev_min
					* array[i], array[i]);

			prev_max = max_ending_here;
			prev_min = min_ending_here;

			if (max < prev_max) {
				max = prev_max;
			}
		}

		return max;
	}

	public static int maxof3(int a, int b, int c) {
		return Math.max(Math.max(a, b), c);
	}

	public static int minof3(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public static void test100() {
		int[] array = { 3, 2, 4, 10, -1, 3 };
		maxProduct_print(array);
	}

	/*
	 * t2_1_3 max subarray product. print out the subarray
	 */

	public static void maxProduct_print(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		int prev_max = 1;
		int prev_min = 1;
		int endIdx = -1;

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			int max_ending_here = maxof3(prev_max * array[i], prev_min
					* array[i], array[i]);
			int min_ending_here = minof3(prev_max * array[i], prev_min
					* array[i], array[i]);

			prev_max = max_ending_here;
			prev_min = min_ending_here;

			if (max < prev_max) {
				max = prev_max;
				endIdx = i;
			}
		}

		// find the startIdx
		int maxCpy = max;
		int startIdx = endIdx;
		while (maxCpy != 1) {
			maxCpy = maxCpy / array[startIdx];
			startIdx--;
		}

		startIdx++;
		System.out.println("startIdx = " + startIdx);
		System.out.println("endIdx = " + endIdx);

		for (int i = startIdx; i <= endIdx; i++) {
			System.out.print(array[i] + " ");
		}

	}

	/*
	 * task2.2: Largest SubArray Product, print out the subArray this is NOT the
	 * optimized solution.
	 */
	public static void test2_2() {
		int[] array = { -1, 3 };
		int rev = task2_2_maxSubArrayProduct_Print(array);
		System.out.println("rev  = " + rev);
	}

	public static int task2_2_maxSubArrayProduct_Print(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int start = -1, end = -1;

		int[] maxEndingHere = new int[array.length];
		int[] minEndingHere = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				maxEndingHere[i] = array[i];
				minEndingHere[i] = array[i];
			} else {
				maxEndingHere[i] = Math.max(array[i], Math.max(
						maxEndingHere[i - 1] * array[i], minEndingHere[i - 1]
								* array[i]));
				minEndingHere[i] = Math.min(array[i], Math.max(
						maxEndingHere[i - 1] * array[i], minEndingHere[i - 1]
								* array[i]));
			}
		}

		int maxProduct = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (maxProduct < maxEndingHere[i]) {
				maxProduct = maxEndingHere[i];
				end = i;
			}
		}

		System.out.println(Arrays.toString(maxEndingHere));

		System.out.println("start = " + start);
		System.out.println("end = " + end);

		System.out.println("--------------");

		if (maxProduct != 0) {
			int product = 1;
			start = end;
			while (start >= 0 && product != maxProduct) {
				product *= array[start];
				start--;
			}
		}
		start++;

		System.out.println("start = " + start);
		System.out.println("end = " + end);

		System.out.println("maxProduct = " + maxProduct);

		return maxProduct;
	}

	/*
	 * task2.3: Largest SubMatrix Product, print out the SubMatrix
	 * 
	 * the same with task1.3
	 */
	public static void test2_3() {
		double[][] matrix = { { 1, -0.2, -1 }, { 1, -1.5, 1 }, { 0, 0, 2 } };
		double maxProduct = task2_3_largest_subMatrix_product(matrix);
		System.out.println("maxProduct = " + maxProduct);

	}

	public static double task2_3_largest_subMatrix_product(double[][] matrix) {

		int rLen = matrix.length;
		int cLen = matrix[0].length;

		int startRIdx = -1, startCIdx = -1;
		int endRIdx = -1, endCIdx = -1;
		double maxProduct = Double.MIN_VALUE;
		for (int leftB = 0; leftB < cLen; leftB++) {
			double[] curProduct = new double[rLen];
			Arrays.fill(curProduct, 1.0);
			for (int rightB = leftB; rightB < cLen; rightB++) {
				for (int i = 0; i < rLen; i++) {
					curProduct[i] = curProduct[i] * matrix[i][rightB];
				}

				System.out.println(Arrays.toString(curProduct));

				Wrapper2 cur_max_info = task2_3_largest_subarray_product(curProduct);

				if (maxProduct < cur_max_info.maxVal) {
					maxProduct = cur_max_info.maxVal;

					startRIdx = cur_max_info.start;
					startCIdx = leftB;

					endRIdx = cur_max_info.end;
					endCIdx = rightB;

				}
			}
		}

		// // print out the matrix
		// System.out.println("-------------");
		// for(int i = startRIdx; i <= endRIdx; i++) {
		// for(int j = startCIdx; j <= endCIdx; j ++) {
		// System.out.print(matrix[i][j] + " ");
		// }
		// System.out.println();
		// }
		// System.out.println();

		return maxProduct;
	}

	public static void test2_3_1() {
		double[] arr = { 0.5, 1, 0 };
		Wrapper2 rev = task2_3_largest_subarray_product(arr);
		System.out.println(rev.start);
		System.out.println(rev.end);
		System.out.println(rev.maxVal);
	}

	public static Wrapper2 task2_3_largest_subarray_product(double[] arr) {
		double pre_max = 1.0;
		double pre_min = 1.0;
		int endIdx = -1;

		double max = Double.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			double cur_max = t2_3_max_of_3(pre_max * arr[i], pre_min * arr[i],
					arr[i]);
			double cur_min = t2_3_min_of_3(pre_min * arr[i], pre_max * arr[i],
					arr[i]);

			pre_max = cur_max;
			pre_min = cur_min;

			if (max < cur_max) {
				max = cur_max;
				endIdx = i;
			}
		}

		// find the startIdx
		int startIdx = endIdx;
		double maxCpy = max;

		while (maxCpy != 1.0 && startIdx >= 0) { // make sure that statIdx is in
													// bound
			maxCpy = maxCpy / arr[startIdx];
			startIdx--;
		}
		startIdx++;

		return new Wrapper2(startIdx, endIdx, max);
	}

	public static double t2_3_max_of_3(double a, double b, double c) {
		return Math.max(Math.max(a, b), c);
	}

	public static double t2_3_min_of_3(double a, double b, double c) {
		return Math.min(Math.min(a, b), c);
	}

	public static class Wrapper2 {
		int start;
		int end;
		double maxVal;

		public Wrapper2(int _s, int _e, double _m) {
			this.start = _s;
			this.end = _e;
			this.maxVal = _m;
		}
	}

	/*
	 * concise version
	 * only find the largest, don't print out the sub matrix
	 */

	public static double task2_3_largest_subMatrix_product2(double[][] matrix) {

		int rLen = matrix.length;
		int cLen = matrix[0].length;

		double maxProduct = Double.MIN_VALUE;
		for (int leftB = 0; leftB < cLen; leftB++) {
			double[] curProduct = new double[rLen];
			Arrays.fill(curProduct, 1.0);
			for (int rightB = leftB; rightB < cLen; rightB++) {
				for (int i = 0; i < rLen; i++) {
					curProduct[i] = curProduct[i] * matrix[i][rightB];
				}

				System.out.println(Arrays.toString(curProduct));

				double curMax = task2_3_largest_subarray_product2(curProduct);

				if (maxProduct < curMax) {
					maxProduct = curMax;
				}
			}
		}
		return maxProduct;
	}

	public static double task2_3_largest_subarray_product2(double[] arr) {
		double pre_max = 1.0;
		double pre_min = 1.0;

		double max = Double.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			double cur_max = t2_3_max_of_3(pre_max * arr[i], pre_min * arr[i],
					arr[i]);
			double cur_min = t2_3_min_of_3(pre_min * arr[i], pre_max * arr[i],
					arr[i]);

			pre_max = cur_max;
			pre_min = cur_min;

			if (max < cur_max) {
				max = cur_max;		
			}
		}
		return max;
	}

	
	
	/*
	 * 
	 * task3.1
	 * 
	 * Longest 1s Given an array containing only 0s and 1s, find the length of
	 * the longest subarray of consecutive 1s. Assumptions The given array is
	 * not null Examples {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
	 */

	/*
	 * M[i] the longest 1s ending with array[i] M[i] = array[i] == 1 ? M[i - 1]
	 * + 1 0
	 */
	public static int task3_1_longest1s(int[] array) {
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
	 * task3.2
	 * 
	 * Longest Cross Of 1s Given a matrix that contains only 1s and 0s, find the
	 * largest cross which contains only 1s, with the same arm lengths and the
	 * four arms joining at the central point. Return the arm length of the
	 * largest cross. Assumptions The given matrix is not null Examples {
	 * 
	 * {0, 0, 0, 0}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 0, 1, 1} }
	 * 
	 * the largest cross of 1s has arm length 2
	 */

	public static void test3_2() {
		int[][] matrix = { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 },
				{ 1, 0, 1, 1 } };
		int rev = task3_2_largestCorssOfX(matrix);
		System.out.println("rev = " + rev);
	}

	public static int task3_2_largestCorssOfX(int[][] matrix) {
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

		Debug.printMatrix(M1);
		System.out.println("---------");
		Debug.printMatrix(M2);
		System.out.println("---------");
		Debug.printMatrix(M3);
		System.out.println("---------");
		Debug.printMatrix(M4);

		return lenOfLargestCross;
	}

	/*
	 * task3.3 Largest X Of 1s
	 * 
	 * Given a matrix that contains only 1s and 0s, find the largest X shape
	 * which contains only 1s, with the same arm lengths and the four arms
	 * joining at the central point. Return the arm length of the largest X
	 * shape.
	 * 
	 * Assumptions
	 * 
	 * The given matrix is not null Examples
	 * 
	 * { {0, 0, 0, 0},
	 * 
	 * {1, 1, 1, 1},
	 * 
	 * {0, 1, 1, 1},
	 * 
	 * {1, 0, 1, 1} }
	 * 
	 * the largest X of 1s has arm length 2.
	 */
	public static int task3_3_largest(int[][] matrix) {
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

		for (int rStart = rLast; rStart >= 0; rStart--) {
			int cStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;
			// first, going up
			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (cIndex == cStart) {
					// the first column
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
		// then, going right
		for (int cStart = 1; cStart <= cLast; cStart++) {
			int rStart = 0;
			int rIndex = rStart;
			int cIndex = cStart;

			while (rIndex >= 0 && rIndex <= rLast && cIndex >= 0
					&& cIndex <= cLast) {
				if (rIndex == rStart) {// the first row
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
	public static int[][] getRigthBottom2LeftTop(int[][] matrix) {
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

	public static int[][] getLeftBottom2RightTop(int[][] matrix) {
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

	public static int[][] getRightTop2LeftBottom(int[][] matrix) {
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
					&& cIndex <= cLast) { // do a general sanity check
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
	 * task3.4 Given a matrix where every element is either 0 or 1 find the
	 * largest subSquare surrounded by '1'
	 */
	public static int task3_4_larget(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int result = 0;
		int M = matrix.length;
		int N = matrix[0].length;
		int[][] left = new int[M + 1][N + 1];
		int[][] up = new int[M + 1][N + 1];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (matrix[i][j] == 1) {
					left[i + 1][j + 1] = left[i + 1][j] + 1;
					up[i + 1][j + 1] = up[i][j + 1] + 1;

					for (int maxLen = Math.min(left[i + 1][j + 1],
							up[i + 1][j + 1]); maxLen >= 1; maxLen--) {
						if (left[i + 2 - maxLen][j + 1] >= maxLen
								&& up[i + 1][j + 2 - maxLen] >= maxLen) {
							result = Math.max(result, maxLen);
							break;
						}
					}
				}
			}
		}
		return result;
	}

	/*
	 * task4: Cutting Wood I There is a wooden stick with length L >= 1, we need
	 * to cut it into pieces, where the cutting positions are defined in an int
	 * array A. The positions are guaranteed to be in ascending order in the
	 * range of [1, L - 1]. The cost of each cut is the length of the stick
	 * segment being cut. Determine the minimum total cost to cut the stick into
	 * the defined pieces. Examples L = 10, A = {2, 4, 7}, the minimum total
	 * cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
	 */

	/*
	 * M[i][j] the left border of the wood is at A[i] and the right border of
	 * the wood ar A[j]
	 * 
	 * where there are two subsections of wood M[0][2] = A[2] - A[0] + M[0][1] +
	 * M[1][2] ------------ ----------------- cost of cutting sub-problem's
	 * solution at A[1]
	 * 
	 * when there are three subsections of wood M[0][3] case1 : first cut is put
	 * on A[1], then the wood will be cut into half M[0][1] + M[1][3] case2 :
	 * first cut is put on A[2], then the wood will be cut into half M[0][2] +
	 * M[2][3]
	 */
	public static void test4() {
		int[] cuts = { 2, 4, 7 };
		int length = 10;
		int rev = minCost(cuts, length);
		System.out.println("rev = " + rev);
	}

	public static int minCost(int[] cuts, int length) {
		// write your solution here
		if (length <= 1) {
			return 0;
		}

		// modify cuts, add position 0 and cuts.length - 1
		int[] helper = new int[cuts.length + 2];
		for (int i = 0, j = 0; i < helper.length; i++) {
			if (i == 0) {
				helper[i] = 0;
			} else if (i == helper.length - 1) {
				helper[i] = length;
			} else {
				helper[i] = cuts[j];
				j++;
			}
		}

		int n = helper.length;
		int[][] M = new int[n][n];
		// initialize
		// size == 1, we cannot cut. cost == 0

		System.out.println("n = " + n);

		for (int j = 1; j < helper.length; j++) {
			for (int i = j - 1; i >= 0; i--) {
				if (i + 1 == j) {
					M[i][j] = 0;
				} else {
					int curMin = Integer.MAX_VALUE;
					for (int k = i + 1; k <= j - 1; k++) {
						curMin = Math.min(curMin, M[i][k] + M[k][j]);
					}
					M[i][j] = helper[j] - helper[i] + curMin;
				}
			}
		}

		Debug.printMatrix(M);

		return M[0][n - 1];
	}

}

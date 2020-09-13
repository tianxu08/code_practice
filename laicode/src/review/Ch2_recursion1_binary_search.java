package review;

public class Ch2_recursion1_binary_search {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	/*
	 * 1 fibonacci number f(0) = 0 f(1) = 1 f(n) = f(n - 1) + f(n - 2)
	 */

	public static int t1_fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int temp1 = 0;
		int temp2 = 1;

		for (int i = 2; i <= n; i++) {
			int n_temp = temp1 + temp2;

			// update temp1 and temp2
			temp1 = temp2;
			temp2 = n_temp;
		}
		// after the loop, the temp2 is the result
		return temp2;
	} // liner O(n)

	/*
	 * f(n) = f(n - 1) + f(n - 2)
	 * 
	 * f(n) 1 1 f(n -1) = x f(n - 1) 1 0 f(n -2)
	 * 
	 * (n -1) f(n) 1 1 f(1) = x f(n - 1) 1 0 f(0)
	 * 
	 * 
	 * 
	 * time: O(log n)
	 */

	public static int t1_fib_matrix(int n) {

		int[][] matrix = { { 1, 1 }, { 1, 0 } };
		int[][] power_of_n_1 = t1_power_of_matrix(matrix, n - 1);
		int[][] basic_matrix = { { 1 }, { 0 } };
		int[][] result_matrix = t1_matrix_multiple(power_of_n_1, basic_matrix);

		return result_matrix[0][0];
	}

	/*
	 * get the power of a mtrix
	 */
	public static int[][] t1_power_of_matrix(int[][] m, int k) {
		if (k == 1) {
			return m;
		}
		if (k == 2) {
			return t1_matrix_multiple(m, m);
		}
		if (k % 2 == 0) {
			int[][] tempmatrix = t1_power_of_matrix(m, k / 2);
			return t1_matrix_multiple(tempmatrix, tempmatrix);

		} else {
			int[][] tempmatrix = t1_power_of_matrix(m, k / 2);
			return t1_matrix_multiple(
					t1_matrix_multiple(tempmatrix, tempmatrix), m);
		}
	}

	public static void test1() {
		int n = 100;
		int r1 = t1_fib(n);
		int r2 = t1_fib_matrix(n);
		System.out.println("r1 = " + r1);
		System.out.println("r2 = " + r2);

	}

	/*
	 * multiple two matrix m1 rLen1 x cLen1 m2 rLen2 x cLen2
	 * 
	 * result matrix: rLen1 x cLen2
	 */
	public static int[][] t1_matrix_multiple(int[][] m1, int[][] m2) {
		int m1_r_len = m1.length;
		int m1_c_len = m1[0].length;
		int m2_r_len = m2.length;
		int m2_c_len = m2[0].length;

		if (m1_c_len != m2_r_len) {
			return null;
		}

		int[][] result = new int[m1_r_len][m2_c_len];
		for (int i = 0; i < m1_r_len; i++) {
			for (int j = 0; j < m2_c_len; j++) {
				result[i][j] = 0;
				for (int k = 0; k < m1_c_len; k++) {
					result[i][j] += (m1[i][k] * m2[k][j]); // !!! XXX
				}
			}
		}
		return result;
	}

	/*
	 * 2 power(a, b)
	 */
	public static int t2_power(int a, int b) {
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		int half = t2_power(a, b / 2);
		if (b % 2 == 0) {
			return half * half;
		} else {
			return half * half * a;
		}
	}

	/*
	 * 3 classic binary search
	 */
	public static int t3_binary_search(int[] a, int target) {
		int left = 0, right = a.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] == target) {
				return mid;
			} else if (a[mid] > target) {
				// in the left range
				right = mid;
			} else {
				// a[mid] < target, in the right range
				left = mid;
			}
		}
		if (a[left] == target) {
			return left;
		} else if (a[right] == target) {
			return right;
		} else {
			return -1;
		}
	}

	/*
	 * 4 binary search in sorted 2D matrix Given a 2D matrix that contains
	 * integers only, which each row is sorted in an ascending order. The first
	 * element of next row is larger than (or equal to) the last element of
	 * previous row. Given a target number, returning the position that the
	 * target locates within the matrix. If the target number does not exist in
	 * the matrix, return {-1, -1}.
	 * 
	 * matrix = { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} } target = 7, return {1, 2}
	 * target = 6, return {-1, -1} to represent the target number does not exist
	 * in the matrix.
	 */
	public static int[] t4_binary_search_2Dmatrix(int[][] matrix, int target) {
		return null;
	}

	/*
	 * 5 first occurrence Given a target integer T and an integer array A sorted
	 * in ascending order, find the index of the first occurrence of T in A or
	 * return -1 if there is no such index.
	 */
	public static int t5_firstoccurence(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int start = 0, end = array.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] == target) {
				end = mid;
			} else if (array[mid] > target) {
				end = mid;
			} else {
				// array[mid] < target
				start = mid;
			}
		}
		// first check array[left]
		if (array[start] == target) {
			return start;
		} else if (array[end] == target) {
			return end;
		} else {
			return -1;
		}
	}

	/*
	 * 6 last occurrence
	 */

	/*
	 * 7 closest number in sorted array
	 */

	/*
	 * 8 search in unknown sized sorted array
	 */

	public static class Dictionary {
		public int[] array;

		public Dictionary(int[] array) {
			this.array = array;
		}

		public Integer get(int index) { // !!! note the return type
			// key: if index >= array.length, then return null;
			// NOT throw an exception.
			if (array == null || index >= array.length) {
				return null;
			}
			return array[index];
		}
	}
	
	public static int t8_search_unknow_size(Dictionary dict, int target) {
		if (dict == null) {
			return -1;
		}
		int left = 0;
		int right = 1;
		// find the right bound and left bound
		while(dict.get(right) != null && dict.get(right) < target) {
			left = right;
			right = right*2;
		}
		// ok. the target is in the [left, right], the dict.get(right) might be null
		return t8_binary_search(dict, left, right, target);
		
		
	}
	
	public static int t8_binary_search(Dictionary dict, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			
			if (dict.get(mid) == null || dict.get(mid) > target) {
				right = mid;
			} else if (dict.get(mid) < target) {
				left = mid;
			} else {
				// dict.get(mid) == target
				return mid;
			}
		}
		
		if (dict.get(left) != null && dict.get(left) == target) {
			return left;
		} else if (dict.get(right) != null && dict.get(right) == target) {
			return right;
		} else {
			return -1;
		}
	}

}

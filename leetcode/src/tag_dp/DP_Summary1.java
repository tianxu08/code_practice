package tag_dp;

import java.util.Arrays;

public class DP_Summary1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1_2();
	}
	
	/*
	 * Longest Increasing Subsequence (LIS)
	 * 
	 * Time: O(n * log n)
	 * 
	 * 1. If A[i] is smallest among all end candidates of active lists, we will start new active list of length 1.
	 * 2. If A[i] is largest among all end candidates of 
	 * active lists, we will clone the largest active 
	 * list, and extend it by A[i].
	 * 3. If A[i] is in between, we will find a list with 
	 * largest end element that is smaller than A[i]. 
	 * Clone and extend this list by A[i]. We will discard all
	 * other lists of same length as that of this modified list.
	 * 
	 * https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	 */
	
	public static void test1_2() {
		int[] array = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int result = task1_longestIncreasingSubsequence_opt(array);
		System.out.println("result: " + result);
	}
	public static int task1_longestIncreasingSubsequence_opt(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int[] tailTalbe = new int[n];
		tailTalbe[0] = array[0];
		int len = 1;
		for (int i = 1; i < n; i++) {
			if (array[i] < tailTalbe[0]) {
				tailTalbe[0] = array[i];
			} else if (array[i] > tailTalbe[len - 1]) {
				tailTalbe[len] = array[i];
				len ++;
			} else {
				// find the insert position
				int index = Arrays.binarySearch(tailTalbe, 0, len, array[i]);
				if (index < 0) {
					index = - (index + 1);
				}
				tailTalbe[index] = array[i];
			}
		}
		return len;
	}
}

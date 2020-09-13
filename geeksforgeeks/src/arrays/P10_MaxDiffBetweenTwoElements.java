package arrays;

public class P10_MaxDiffBetweenTwoElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * http://www.geeksforgeeks.org/maximum-difference-between-two-elements/
	 */
	
	/*
	 * Given an array arr[] of integers, find out the max difference between any two
	 * elements such that larger element appears after the smaller number in arr[].
	 * 
	 * Examples: 
	 * If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2). 
	 * If array is [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)
	 * 
	 * Selling stock problem
	 */
	
	public static int maxDifBF(int[] a) {
		int maxDif = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] > a[i]) {
					int dif = a[j] - a[i];
					if (dif > maxDif) {
						maxDif = dif;
					}
				}
			}
		}
		return maxDif;
	} // Time: O(n^2)
	
	public static void test() {
		int[] a = {2, 3, 10, 6, 4, 8, 1};
		int[] a2 = {7, 9, 5, 6, 3, 2 };
		System.out.println(maxDifBF(a));
		System.out.println(maxDif2(a));
		System.out.println(maxDif3(a));
		
		System.out.println(maxDifBF(a2));
		System.out.println(maxDif2(a2));	
		System.out.println(maxDif3(a2));
	}
	
	// this is the selling stock, get maxProfit, only but once and sell once
	public static int maxDif2(int[] a) {
		int maxDif = 0;
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			int curMaxDif = a[i] - min;
			if (curMaxDif > maxDif) {
				maxDif = curMaxDif;
			}
			if (a[i] < min ) {
				min = a[i];
			}
		}
		return maxDif;
	} // Time: O(n)  Space: O(1)
	

	
	/* find the difference between the adjacent elements of the arrya and store all diffs 
	 * in an auxiliary array diff[] of size n-1. Now, this problem turns into find the maximum
	 * sum subarray of this diff array
	 */
	
	public static int maxDif3(int[] a) {
		int n = a.length;
		int[] diff = new int[n-1];

		for (int i = 0; i < diff.length - 1; i++) {
			diff[i] = a[i+1] - a[i];
		}
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < diff.length; i++) {
			if (sum < 0) {
				sum = 0;
			}
			sum += diff[i];
			if (maxSum < sum) {
				maxSum = sum;
			}
		}
		
		return maxSum;
	}
	

}

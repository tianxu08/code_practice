package dynamic_programming;

public class Set14_MaximumSumIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
	 */
	
	public static int maxSumIncreasingSubsequence(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}
		int len = input.length;
		int[] maxSum = new int[len];
		//maxSum[i] stands for the maxSum ending with input[i]
		for (int i = 0; i < len; i++) {
			maxSum[i] = input[i];
		}
		for (int i = 1; i < maxSum.length; i++) {
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i]) {
					maxSum[i] = Math.max(maxSum[i], maxSum[j] + input[i]);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < maxSum.length; i++) {
			max = Math.max(max, maxSum[i]);
		}
		return max;
	}
	
	public static void test() {
		int[] input = {1, 101, 2, 3, 100, 4, 5};
		int maxSum = maxSumIncreasingSubsequence(input);
		System.out.println("maxSum = " + maxSum);
	}

}

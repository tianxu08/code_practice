package arrays;

public class P12_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/*
	 * 1 Largest Sum Contiguous Subarray
	 * 2 Find the Number Occurring Odd Number of Times
	 * 3 Majority Element
	 * 4-.. 2Sum 3Sum 4Sum
	 */
	
	/* 
	 * task1
	 * Largest Sum Contiguous Subarray
	 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
	 */
	/*
	 * slide window
	 * use a sum = 0. if sum > 0  sum += a[i], we think it is useful
	 * if sum < 0, we set sum = 0. start from i, and continue the scanning
	 * 
	 */
	public static int largestSumContiguousSubarray1(int[] a) {
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i< a.length; i++) {
			if (sum < 0) {
				sum = 0;
			}
			sum += a[i];
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}
	
	/*
	 * Algorithm
	 * we get a prefix sum array prefix[]  prefix[i] = sum of a[0...i]
	 * the largest Sum of Contiguous Subarray can be changed to 
	 * the max difference in the prefix array.
	 *      index  0  1  2  3  4 
	 * for example 0,-3, 1, 2, 3
	 *     prefix  0 -3  -2 0, 3
	 * the largest sum of contiguous subarray is 6, which is [1,2,3]
	 * also the the prefix[4] - prefix[1]  which is the max Difference
	 * 
	 * since we only need the maxSum, so we can use sum to calculate the cur prefix sum
	 * use minSum store the minimum sum, which is elem in prefix array
	 * 
	 * we can also use this idea for buy and selling stock problem
	 */
	public static int largestSumContiguousSubarray2(int[] a) {
		int sum = 0;
		int min = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			maxSum = Math.max(maxSum, sum - min);
			min = Math.min(min, sum);
		}
		
		return maxSum;
	}
	
	public static void test1() {
		int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		int maxSum1 = largestSumContiguousSubarray1(a);
		int maxSum2 = largestSumContiguousSubarray2(a);
		System.out.println("maxSum1 = " + maxSum1);
		System.out.println("maxSum2 = " + maxSum2);
	}
	
	/*
	 * task2
	 * Find the Number Occurring Odd Number of Times
	 * http://www.geeksforgeeks.org/find-the-number-occurring-odd-number-of-times/
	 * 
	 * Given an array of positive integers. 
	 * All numbers occur even number of times except one number 
	 * which occurs odd number of times. Find the number in O(n) time & constant space.
	 * Example:
	 * I/P = [1, 2, 3, 2, 3, 1, 3]
	 * O/P = 3
	 * 
	 * we can use XOR
	 */
	
	/*
	 * task3
	 * Majority Element
	 * http://www.geeksforgeeks.org/majority-element/
	 * 
	 */
	
	
	/*
	 * 2Sum 3Sum 4Sum  this kind of problem, we mainly using two method. 
	 * (1) Sorting the array
	 * (2) Using hash Map 
	 */
	/*
	 * task4
	 * Given an array A[] and a number x, check for pair in A[] with sum as x
	 * 2Sum
	 * http://www.geeksforgeeks.org/write-a-c-program-that-given-a-set-a-of-n-numbers-and-another-number-x-determines-whether-or-not-there-exist-two-elements-in-s-whose-sum-is-exactly-x/
	 * 
	 */
	
	
	/*
	 * task5
	 * 3Sum
	 */
	
	/*
	 * task6
	 * 4Sum
	 */
	
	/*
	 * task7 
	 * 2Sum Closest 
	 */
	
	
	
	
	

}

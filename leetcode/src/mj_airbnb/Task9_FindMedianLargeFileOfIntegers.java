package mj_airbnb;

import java.util.Arrays;
import java.util.Random;

public class Task9_FindMedianLargeFileOfIntegers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[100];
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int num = random.nextInt(100);
			nums[i] = num;
		}
		
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " , ");
		}
		System.out.println();
		
		System.out.println(nums[49]);
		System.out.println(nums[50]);
		System.out.println(findMedian(nums));
	}
	
	private static double findMedian(int[] nums) {
		int size = 0;
		// get size
		for (int i = 0; i < nums.length; i++) {
			size ++;
		}
		if (size % 2 == 0) {
			long median1 = findKth(nums, size / 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
			long median2 = findKth(nums, size /2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
			return (double) (median1 + median2) / 2;
		} else {
			return findKth(nums, size / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
	}
	
	public static long findKth(int[] nums, int k, long left, long right) {
		if (left >= right) return left;
		
		long largestSmallerThanGuess = left;
		long guess = left + (right - left) / 2;
		
		int count = 0; 
		for (int num : nums) {
			if (num <= guess) {
				count ++;
				// update largestSmallerThanGuess
				largestSmallerThanGuess = Math.max(largestSmallerThanGuess, num);
			}
		}
		
		if (count == k) {
			return largestSmallerThanGuess;
		} else if (count < k) {
			// the guess is smaller than the kth, need to search on the right side
			return findKth(nums, k, guess + 1, right);
		} else {
			// count >= k
			// the guess is in the left side. 
			return findKth(nums, k, left, largestSmallerThanGuess);
		}
	}

}

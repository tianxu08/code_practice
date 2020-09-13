package arrays;

import java.util.ArrayList;

public class P10_FindMinLengthUnsortedSubarrayToCompleteSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
	 */
	/*
	 * Given an unsorted array arr[0..n-1] of size n, find the minimum length
	 * subarray arr[s..e] such that sorting this subarray makes the whole array
	 * sorted.
	 */
	
	/*
	 * 1 scan from left to right, find the first element whose value > its next element, index as s
	 * 2 scan from right to left, find the first elemnet whose value < its next element(from right to left), index as e
	 * 3 Find the min and max between s and e
	 * 4 in the first part a[0, s], find the first element whose value > min
	 * 5 in the last part a[e, n-1], find the last element whose val < max
	 */
	
	public static ArrayList<Integer> findMinLengthUnsorted(int[] a) {
		ArrayList<Integer> rev = new ArrayList<Integer>();
		int start = 0, end = a.length - 1;
		// step 1
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1] ) {
				start = i;
				break;
			}
		}
		// step 2
		for (int i = a.length - 1; i > 0; i--) {
			if (a[i] < a[i - 1]) {
				end = i;
				break;
			}
		}
		if (start == 0 && end == a.length - 1) {
			// already sorted
			return rev;
		}
		// step 3
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i = start; i <= end; i++) {
			min = Math.min(min, a[i]);
			max = Math.max(max, a[i]);
		}
		
		for (int i = 0; i <= start; i++) {
			if (a[i] > min) {
				start = i;
				break;
			}
		}
		
		for (int i = a.length - 1; i >= end ; i--) {
			if (a[i] < max) {
				end = i;
				break;
			}
		}
		
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		rev.add(start);
		rev.add(end);
		return rev;
	}
	
	public static void test() {
		int[] a = {10, 12, 20, 30,38, 35, 50, 60};
		ArrayList<Integer> result = findMinLengthUnsorted(a);
		System.out.println(result);
	}

}

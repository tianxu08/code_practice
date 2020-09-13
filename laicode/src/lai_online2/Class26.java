package lai_online2;
import java.util.*;
public class Class26 {

	/*
	 * task1
	 
	Interleave Arrays
	Fair
	DP
	Given three char arrays A, B and C. Determine if C can be created by merging A and B in a way that maintains the relative order of the characters in A and B.

	Assumptions

	none of A, B, C is null
	Examples

	C = {'a', 'b', 'c', 'd', 'e'}, A = {'a', 'c', 'd'}, B = {'b', 'e'}, return true
	C = {'a', 'b', 'c', 'd', 'e'}, A = {'a', 'd', 'c'}, B = {'b', 'e'}, return false
	*/
	public boolean task1_canMerge(String a, String b, String c) {
		// write your solution here
		int aLen = a.length();
		int bLen = b.length();
		int cLen = c.length();
		if (aLen + bLen != cLen) {
			return false;
		}
		boolean[][] M = new boolean[aLen + 1][bLen + 1];

		// initiazlie
		M[0][0] = true;
		for (int i = 1; i <= aLen; i++) {
			M[i][0] = a.charAt(i - 1) == c.charAt(i - 1);
		}
		for (int j = 1; j <= bLen; j++) {
			M[0][j] = b.charAt(j - 1) == c.charAt(j - 1);
		}

		for (int i = 1; i <= aLen; i++) {
			for (int j = 1; j <= bLen; j++) {
				M[i][j] = (M[i - 1][j] && a.charAt(i - 1) == c
						.charAt(i + j - 1))
						|| (M[i][j - 1] && b.charAt(j - 1) == c.charAt(i + j
								- 1));
			}
		}

		return M[aLen][bLen];
	}
	
	/*
	 * task2
	 
	Majority Number I
	Easy
	Data Structure
	Given an integer array of length L, find the number that occurs more than 0.5 * L times.

	Assumptions

	The given array is not null or empty
	It is guaranteed there exists such a majority number
	Examples

	A = {1, 2, 1, 2, 1}, return 1
	*/
	public int task2_majority(int[] array) {
	    // write your solution here
	    int cur = array[0];
			int count = 0;
			for(int i = 0; i < array.length; i ++) {
				// count == 0, we reasign the a candidate
				if (count == 0) {
					cur = array[i];
					count = 1;
				} else if (cur == array[i]) {
					count ++;
				} else {
					count --;
				}
			}
			return cur;
	  }
	
	/*
	 * task3
	
	Maximum Values Of Sliding Windows
	Fair
	Data Structure
	Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from left to right.

	Assumptions

	The given array is not null and is not empty

	K >= 1, K <= A.length

	Examples

	A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},

	and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
	*/
	public List<Integer> task3_maxWindows(int[] array, int k) {
		// write your solution here
		return new ArrayList<Integer>();
	}
	
	/*
	 * task4
	Implement LRU Cache
	Fair
	Data Structure
	Implement a least recently used cache. It should provide set(), get() operations. If not exists, return null (Java), false (C++).
	*/
	
	/*
	 * task5
	Kth Smallest From Two Sorted Arrays
	Hard
	Recursion
	Given two sorted arrays of integers, find the Kth smallest number.

	Assumptions

	The two given arrays are not null and at least one of them is not empty

	K >= 1, K <= total lengths of the two sorted arrays

	Examples

	A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.

	A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
	*/
	public int task5_kth(int[] a, int[] b, int k) {
		// write your solution here
		return 0;
	}
	
	
	/*
	 * task6
	Median Of Two Arrays
	Hard
	Recursion
	Given two arrays of integers, find the median value.

	Assumptions

	The two given array are not null and at least one of them is not empty
	The two given array are not guranteed to be sorted
	Examples

	A = {4, 1, 6}, B = {2, 3}, median is 3
	A = {1, 4}, B = {3, 2}, median is 2.5
	*/
	public double task6_median(int[] a, int[] b) {
	    // write your solution here
	    return 0;
	  }
	
	
}

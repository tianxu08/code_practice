package lai_online;

import java.util.ArrayList;
import java.util.List;

public class Class26 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
		test3();
	}

	/*
	 * task1 
	 * Interleave Arrays Fair DP 
	 * Given three char arrays A, B and C.
	 * Determine if C can be created by merging A and B in a way that maintains
	 * the relative order of the characters in A and B.
	 * 
	 * Assumptions
	 * 
	 * none of A, B, C is null Examples
	 * 
	 * C = {'a', 'b', 'c', 'd', 'e'}, A = {'a', 'c', 'd'}, B = {'b', 'e'},
	 * return true C = {'a', 'b', 'c', 'd', 'e'}, A = {'a', 'd', 'c'}, B = {'b',
	 * 'e'}, return false
	 */
	/*
	 * base case M[0][0] = true
	 * 			 M[1][0] = a[0] == c[0]
	 *           M[0][1] = b[0] == c[0] 
	 * induction rule: 
	 * M[i][j] represents that a[0..i -1] and b[0..j - 1] and merge c[0..i+ j - 1]
	 * 
	 * M[i][j] = (M[i-1][j] && a[i - 1] == c[i + j - 1]) || (M[i][j - 1] && b[j - 1] == c[i + j - 1])
	 */
	
	
	public static void test1() {
		String a = "adc";
		String b = "be";
		String c = "abcde";
		System.out.println(task1_canMerge(a, b, c));
	}
	
	
	public static boolean task1_canMerge(String a, String b, String c) {
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
		for(int i = 1; i <= aLen; i ++) {
			M[i][0] = a.charAt(i -1) == c.charAt(i - 1); 
		}
		for(int j = 1; j <= bLen; j ++) {
			M[0][j] = b.charAt(j - 1) == c.charAt(j - 1);
		}
		
		for(int i = 1; i <= aLen; i ++) {
			for(int j = 1; j <= bLen; j ++) {
				M[i][j] = (M[i - 1][j] && a.charAt(i - 1) == c.charAt(i + j - 1)) || 
						(M[i][j - 1] && b.charAt(j - 1) == c.charAt(i + j - 1));
			}
		}
		
		
	    return M[aLen][bLen];
	}
	
	

	
	
	/*
	 * task2 
	 * Majority Number I Easy Data Structure Given an integer array of
	 * length L, find the number that occurs more than 0.5 * L times.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null or empty It is guaranteed there exists such a
	 * majority number Examples
	 * 
	 * A = {1, 2, 1, 2, 1}, return 1
	 * 
	 */
	public static void test2() {
		int[] a = {1,2,1,2,1};
		System.out.println(task2_majority(a));
	}
	public static int task2_majority(int[] array) {
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
	 * task3 Majority Number II 
	 * Fair Data Structure Given an integer array of
	 * length L, find all numbers that occur more than 1/3 * L times if any
	 * exist.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null Examples
	 * 
	 * A = {1, 2, 1, 2, 1}, return [1, 2] A = {1, 2, 1, 2, 3, 3, 1}, return [1]
	 * A = {1, 2, 2, 3, 1, 3}, return []
	 */
	public static void test3() {
		int[] a1 = {1,2,2,4,3,2,3,3};
		List<Integer> result = task3_majority(a1);
		System.out.println(result);
	}
	
	public static List<Integer> task3_majority(int[] array) {
		List<Integer> result = new ArrayList<Integer>();
		if (array == null || array.length == 0) {
			return result;
		}
		
		int candidate1 = Integer.MAX_VALUE;
		int candidate2 = Integer.MAX_VALUE;
		int count1 = 0;
		int count2 = 0;
		
		for(int i = 0; i < array.length; ++ i) {
			if (candidate1 == array[i]) {
				count1 ++;
			} else if (candidate2 == array[i]) {
				count2 ++;
			} else if (count1 == 0) {
				candidate1 = array[i];
				count1 = 1;
			} else if (count2 == 0) {
				candidate2 = array[i];
				count2 = 1;
			} else {
				count1 --;
				count2 --;
			}
		}
		
		count1 = getCount(array, candidate1);
		count2 = getCount(array, candidate2);
		
		if (count1 > array.length/3 && count2 > array.length / 3) {
			result.add(Math.min(candidate1, candidate2));
			result.add(Math.max(candidate1, candidate2));
		} else if (count1 > array.length/3) {
			result.add(candidate1);
		} else if (count2 > array.length/3) {
			result.add(candidate2);
		}	
		return result;
	}
	
	public static int getCount(int[] array, int target) {
		int count = 0;
		for(int i = 0; i < array.length; i ++) {
			if (array[i] == target) {
				count ++;
			}
		}
		return count;
	}
	

	/*
	 * task4 Majority Number III Hard Data Structure 
	 * Given an integer array of
	 * length L, find all numbers that occur more than 1/K * L times if any
	 * exist.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null or empty K >= 2 Examples
	 * 
	 * A = {1, 2, 1, 2, 1}, K = 3, return [1, 2] A = {1, 2, 1, 2, 3, 3, 1}, K =
	 * 4, return [1, 2, 3] A = {2, 1}, K = 2, return []
	 */
	public List<Integer> majority(int[] array, int k) {
	    // write your solution here
		List<Integer> result = new ArrayList<Integer>();
	    if (array == null || array.length == 0 || k > array.length) {
			return result;
	    }
	    return null;
	    
	    
	 }
	
	

	/*
	 * task5 Maximum Values Of Sliding Windows Fair Data Structure Given an
	 * integer array A and a sliding window of size K, find the maximum value of
	 * each window as it slides from left to right.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and is not empty
	 * 
	 * K >= 1, K <= A.length
	 * 
	 * Examples
	 * 
	 * A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2},
	 * {3,2,4}, {2,4,2}, {4,2,1}},
	 * 
	 * and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
	 */

	/*
	 * task6 
	 * Implement LRU Cache Fair Data Structure Implement a least recently
	 * used cache. It should provide set(), get() operations. If not exists,
	 * return null (Java), false (C++).
	 */
	
	

	/*
	 * task7 Kth Smallest From Two Sorted Arrays Hard Recursion Given two sorted
	 * arrays of integers, find the Kth smallest number.
	 * 
	 * Assumptions
	 * 
	 * The two given arrays are not null and at least one of them is not empty
	 * 
	 * K >= 1, K <= total lengths of the two sorted arrays
	 * 
	 * Examples
	 * 
	 * A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
	 * 
	 * A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
	 */
	
	

	/*
	 * task8 
	 * Median Of Two Arrays 
	 * Hard Recursion 
	 * Given two arrays of integers,
	 * find the median value.
	 * 
	 * Assumptions
	 * 
	 * The two given array are not null and at least one of them is not empty
	 * The two given array are not guranteed to be sorted Examples
	 * 
	 * A = {4, 1, 6}, B = {2, 3}, median is 3 A = {1, 4}, B = {3, 2}, median is
	 * 2.5
	 */
}

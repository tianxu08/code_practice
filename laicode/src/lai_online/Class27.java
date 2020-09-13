 package lai_online;

import java.util.Deque;
import java.util.*;


public class Class27 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
//		test3();
//		test7();
	}
	
	/*
	 * task1: Kth Smallest In Two Sorted Array
	 * task2: 
	 * task3: Max Number in K Sliding Windows
	 * task4: LRU cache
	 * task5: First No-Repeating Character In Stream
	 * task6: Interval Arrays
	 */
	/*
	 * task1 Kth smallest in Two Sorted Array
	 * int[] A = {1 3 5 7 9}
	 * int[] B = {2 4 6 8 10 12 14}
	 */
	public static void test1() {
		int[] A = {1,3,5,7,9};
		int[] B = {2,4,6,8,10, 12, 14};
		int k = 5;
		int rev = task1_kthSmallestInTwoSorted(A, B, k);
		System.out.println("rev = " + rev);
	}
	
	public static int task1_kthSmallestInTwoSorted(int[] A, int[] B, int k) {
		return task1_helper(A, 0, B, 0, k);
	}
	
	// 
	public static int task1_helper(int[] A, int aLeft, int[] B, int bLeft, int k) {
		if (k == 1) {
			return Math.min(A[aLeft], B[bLeft]);
		} 
		if (aLeft >= A.length) {
			// aIdx is out of bounder
			return B[bLeft + k - 1];
		}
		if (bLeft >= B.length) {
			return A[aLeft + k - 1];
		}
		int a_half_kth = aLeft + k/2 - 1 < A.length ? A[aLeft + k/2 - 1] : Integer.MAX_VALUE;
		int b_half_kth = bLeft + k/2 - 1 < B.length ? B[bLeft + k/2 - 1] : Integer.MAX_VALUE;
		System.out.println("----------------");
		System.out.println("aLeft = " + aLeft);
		System.out.println("bLeft = " + bLeft);
		System.out.println(" k = " + k);
		System.out.println("a_half_kth = " + a_half_kth);
		System.out.println("b_half_kth = " + b_half_kth);
		System.out.println("================");
		
		if (a_half_kth < b_half_kth) {
			// delete the first Half in A
			System.out.println("a_new_left " + (aLeft + k/2));
			return task1_helper(A,  aLeft + k/2, B, bLeft, k - k/2);
		} else {
			// delete the second half in B
			return task1_helper(A, aLeft, B, bLeft + k/2, k - k/2);
		}
	}
	
	
	
	
	
	/*
	 * task3
	 * (Array) Sliding window of size k, always return the max element in the window size
	 * 1 3 2 5 8 9 4 7 3
	 */
	public static void test3() {
		int[] array = {1,3,2,5,8,4,7,3};
		int k = 3;
		int[] result = task3_maxSlidingWindow(array, k);
		System.out.println(Arrays.toString(result));
	}
	
	
	/*
	 * keep a 
	 */
	public static int[] task3_maxSlidingWindow(int[] array, int k) {
		if (array == null || array.length < k) {
			return new int[0];
		}
		int n = array.length;
		int[] result = new int[n - k + 1];
		int index = 0;
		Deque<Integer> dq = new LinkedList<Integer>(); 
		// store the Index of Element in descending order
		for(int i = 0; i < n; i ++) {
			int curElem = array[i];
			while(!dq.isEmpty() && array[dq.peekLast()]  < curElem) {
				dq.pollLast();
			}
			// add the curElem into dq
			dq.offerLast(i);
			while(!dq.isEmpty() && dq.peekFirst() <= i - k) {
				// out of the window
				dq.pollFirst();
			}
			// we onl have n - k + 1 elements in the result
			if (i >= k - 1) {
				result[index ++] = array[dq.peekFirst()];
			}
		}
		return result;
	}
	
	
	/*
	 * task4
	 * LRU Cache
	 * refer LRU Cache in the same directory
	 */
	
	/*
	 * task5
	 * First Non-repeating Character
	 */
	
	
	/*
	 * task6
	 * How to determine whether an array C[] can be merged by A[] and B[], while reserving the 
	 * relative order of the letter in the original arrays A[] and B[] ? 
	 */
	
	
	
	
	/*
	 * task7
	 * an array of integers, there is duplicate, find the majority of this array. 
	 * The majority element is the element whose appearance > 1/2
	 */
	public static void test7() {
		int[] array = {1,2,2,2,2,3,3};
		int rev = task7_majority(array);
		System.out.println("rev = " + rev);
	}
	
	public static int task7_majority(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int candidate = array[0];
		int count = 1;
		for(int i = 1; i < array.length; i ++) {
			if (candidate == array[i]) {
				count ++;
			} else if (count > 0) {
				count --;
			} else {
				// count == 0
				candidate = array[i];
				count = 1;
			}
		}
		return candidate;
	}
	
	/*
	 * task7.1
	 * find the element whose appearance percent > 1/3
	 */
	public static int task7_1_majority_1_3(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		
		return -1;
	}
	
	
	
	/*
	 * task7.2
	 * find the element whose appearance percentage > 1/k
	 */
	
	

}

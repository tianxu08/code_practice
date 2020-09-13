package arrays;

import java.util.Arrays;
import java.util.HashMap;

public class P8_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test12();
		test15();
	}
	
	/*
	 * 1 Print a given matrix in spiral form
	 * 2 A Boolean Matrix Question
	 * 3 Median in a stream of integers (running integers)
	 * 4 Find a Fixed Point in a given array
	 * 5 Maximum Length Bitonic Subarray Given an array A[0 … n-1]
	 * 6 Find the maximum element in an array which is first increasing and then decreasing
	 * 7 Count smaller elements on right side
	 * 8 Minimum number of jumps to reach end
	 * 9 Implement two stacks in an array
	 * 10 Find subarray with given sum
	 * 11 Dynamic Programming | Set 14 (Maximum Sum Increasing Subsequence)
	 * 12 Dynamic Programming | Set 3 (Longest Increasing Subsequence)
	 * 13 Longest Monotonically Increasing Subsequence Size (N log N)
	 * 14 Find a triplet that sum to a given value
	 * 15 Find the smallest positive number missing from an unsorted array
	 * 
	 */
	/*
	 * task1
	 * Print a given matrix in spiral form
	 * http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
	 * e.g 
	 *    1  2  3  4 
	 *    5  6  7  8
	 *    9 10 11 12
	 *    13 14 15 16
	 * output 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
	 */
	
	public static void task1_printInSpiral(int[][] matrix) {
		int startRow = 0, endRow = matrix.length - 1;
		int startCol = 0, endCol = matrix[0].length - 1;
		
		while(startRow <= endRow && startCol <= endCol) {
			// print the first row from the remaining row
			for (int i = startCol; i <= endCol; i++) {
				System.out.print(matrix[startRow][i] + " ");
			}
			startRow ++;
			// print the last column from the remaining columns
			for (int i = startRow; i <= endRow; i++) {
				System.out.print(matrix[i][endCol] + " ");
			}
			endCol --;
			// print the last row 
			if (startRow <= endRow) {
				for (int i = endCol; i >= startCol; i--) {
					System.out.print(matrix[endRow][i] + " ");
				}
				endRow --;
			}
			if (startCol <= endCol) {
				for (int i = endRow; i >= startRow; i--) {
					System.out.print(matrix[i][startCol] + " ");
				}
				startCol ++;
			}
		}
	}
	
	public static void test1() {
		int[][] matrix = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}
		};
		task1_printInSpiral(matrix);
	}
	
	/*
	 * task2
	 * A Boolean Matrix Question
	 * http://www.geeksforgeeks.org/a-boolean-matrix-question/
	 * Given a boolean matrix mat[M][N] of size M X N, 
	 * modify it such that if a matrix cell mat[i][j] is 1 (or true) 
	 * then make all the cells of ith row and jth column as 1.
	 * (1) use col[] and row[] to record the 1's position. then traverse the matrix again
	 * (2) use the first row and first column as the row[] and col[]
	 */
	public static void task2_maxtrx_ques() {
		
	}
	
	/*
	 * task3
	 * Median in a stream of integers (running integers)
	 * http://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
	 * 
	 * use heap. 
	 * left is MaxHeap. leftMaxheap.size = rightMinHeap.size + 1 or leftMaxheap.size = rightMinheap
	 * right is MinHeap. 
	 */
	public static int task3_median_of_stream(int[] array) {
		return -1;
	}
	
	/*
	 * task4 
	 * Find a Fixed Point in a given array
	 * http://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/
	 * 
	 * Given an array of n distinct integers sorted in ascending order, write a
	 * function that returns a Fixed Point in the array, if there is any Fixed
	 * Point present in array, else returns -1. Fixed Point in an array is an
	 * index i such that arr[i] is equal to i. Note that integers in array can
	 * be negative.
	 * 
	 * e.g
	 * a[] = {-10, -5, 0, 3, 7}  output : 3  a[3] = 3;
	 * (1)Linear search
	 * (2)Binary search, similar to P9_Array.findMissing. task7
	 * 
	 */
	
	public static int task4_fixPoint(int[] a) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int start = 0, end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (a[mid] == mid) {
				return mid;
			} else if (a[mid] > mid) {
				// a[mid] > mid, the elements in right side are impossible. go to left side
				end = mid;
			} else {
				// a[mid] < mid, the element in the left side are impossible. go to right side
				start = mid;
			}
		}
		if (a[start] == start) {
			return start;
		} else if (a[end] == end) {
			return end;
		} else {
			return -1;
		}
	}
	public static void test2() {
		int[] a = {-10, -5, 0, 3, 7};
		System.out.println(task4_fixPoint(a));
	}

	/*
	 * task5 
	 * Maximum Length Bitonic Subarray Given an array A[0 … n-1]
	 * containing n positive integers, a subarray A[i … j] is bitonic if there
	 * is a k with i <= k <= j such that A[i] <= A[i + 1] ... <= A[k] >= A[k +
	 * 1] >= .. A[j – 1] > = A[j]. Write a function that takes an array as
	 * argument and returns the length of the maximum length bitonic subarray.
	 * 
	 * Algorithm:
	 * (1)inc[] from left to right such that inc[i] contains the length fo the no decreasing 
	 *    subarray ending at a[i]
	 * (2)dec[] from right to left such that dec[i] contains the length of the no increasing
	 *    subarray starting at a[i]
	 * (3)scan from left to right. find the max (inc[i] + dec[i] - 1)
	 * 
	 * this is subarray, there is also a similar question, bitonic subsequence. 
	 * 
	 */
	
	public static int task5_maxLenBitonicSubarray(int[] a) {
		int n = a.length;
		int[] inc = new int[n];
		int[] dec = new int[n];
		inc[0] = 1;
		for (int i = 1; i < dec.length; i++) {
			if (a[i] >= a[i- 1]) {
				inc[i] = inc[i - 1] + 1;
			} else {
				inc[i] = 1;
			}
		}
		
		dec[n-1] = 1;
		for (int i = n-2; i >= 0; i--) {
			if (a[i] >= a[i+ 1]) {
				dec[i] = dec[i + 1] + 1;
			} else {
				dec[i] = 1;
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, inc[i] + dec[i] - 1);
		}
		return max;
	} // Time: O(n)  Space: O(n)
	
	public static void test3() {
		int[] a = {12, 4, 78, 90, 45, 23};
		System.out.println(task5_maxLenBitonicSubarray(a));
	}
	
	/*
	 * task6
	 * Find the maximum element in an array which is first increasing and then decreasing
	 * http://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and-then-decreasing/
	 * 
	 * 
	 * Given an array of integers which is initially increasing and then decreasing, 
	 * find the maximum value in the array.
	 * Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}
	 * Output: 500
	 * 
	 * 
	 */
	
	public static int task6_findMax(int[] a) {
		if (a == null || a.length == 0) {
			return -1;
		}
		// if there is only one element
		if (a.length == 1) {
			return a[0];
		}
		int start = 0, end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if ( a[mid -1] < a[mid] && a[mid]  > a[mid + 1]) {
				return a[mid];
			} else if (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) {
				// in the right side;
				start = mid;
			} else {
				end = mid;
			}
		}
		if (a[start] > a[end]) {
			return a[start];
		} else {
			return a[end];
		}
	}
	public static void test6() {
		int[] a = {2, 4, 6, 8, 10, 3, 1};
		System.out.println(task6_findMax(a));
		int[] a1 = {1,2,3,4,5,6,7};
		System.out.println(task6_findMax(a1));
		int[] a2 = {4,3,2,1};
		System.out.println(task6_findMax(a2));	
	}
	
	/*
	 * task7
	 * Count smaller elements on right side
	 * Write a function to count number of smaller elements on right of each element in an array. 
	 * Given an unsorted array arr[] of distinct integers, 
	 * construct another array countSmaller[] such that countSmaller[i] 
	 * contains count of smaller elements on right side of each element arr[i] in array.
	 * 
	 * (1) two loops
	 * (2) using balanced binary search tree
	 */
	
	/*
	 * task8
	 * Minimum number of jumps to reach end
	 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
	 * Given an array of integers where each element represents the max number of steps 
	 * that can be made forward from that element. 
	 * Write a function to return the minimum number of jumps to 
	 * reach the end of the array (starting from the first element). 
	 * If an element is 0, then cannot move through that element.
	 * 
	 * input: 
	 * a[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
	 * output:
	 * 3 {1->3 ->9}
	 */
	public static int task8_minJumps(int[] a) {
		int left = 0, right = a.length - 1;
		return helper(a, left, right);
	}
	
	// return the minimum number of jumps to reach a[right] from a[left]
	/*
	 * minJumps(start, end) = Min(minJumps(k, end)) for all k reachable from start
	 */
	public static int helper(int[] a, int left, int right) {
		if (left == right) {
			return 0;
		}
		if (a[left] == 0) {
			return Integer.MAX_VALUE;
		}
		// 
		int min = Integer.MAX_VALUE;
		for (int i = left + 1; i <= right && i <= left + a[left]; i++) {
			int jump = helper(a, i, right);
			if (jump != Integer.MAX_VALUE && jump + 1 < min) {
				min = jump + 1;
			}
		}
		return min;	
	}
	
	public static int minJumpsDP(int[] a) {
		int n = a.length;
		int[] jumps = new int[n];
		if ( n == 0 || a[0] == 0) {
			return Integer.MAX_VALUE;
		}
		jumps[0] = 0;
		for (int i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (i <= j + a[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					break;
				}
			}
		}
		return jumps[n-1];
	}
	
	public static int minJumpDP2(int[] a) {
		return -1;
	}
	
	public static void test8() {
		int[] a = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		System.out.println(task8_minJumps(a));
		System.out.println(minJumpsDP(a));
	}
	
	
	/*
	 * task9
	 * http://www.geeksforgeeks.org/implement-two-stacks-in-an-array/
	 * Implement two stacks in an array
	 */
	public static void task9_implement_two_stacks() {
		
	}
	
	/*
	 * task10
	 * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
	 * Find subarray with given sum
	 * Given an unsorted array of nonnegative integers, 
	 * find a continous subarray which adds to a given number.
	 */
	
	/*
	 * naive method
	 * two loops, check every subarray
	 */
	public static void task10_subArraySum1(int[] a, int num) {
		int curSum = 0;
		for (int i = 0; i < a.length; i++) {
			curSum = 0;
			for (int j = i ; j < a.length; j++) {
				if (j == i) {
					curSum = a[j];
				} else {
					curSum += a[j];
				}
				if (curSum == num) {
					System.out.println("sum found between indexes " + i + " and " + j);
					return ;
				}
				
			}
		}
	} // time: O(n^2)
	
	public static void test10() {
		int[] a = {15, 2, 4, 8, 9, 5, 10, 23};
		int sum = 23;
		task10_subArraySum1(a, sum);
		task10_subArraySum2(a, sum);
	}
	
	/*
	 * OPT method
	 * use prefixsum[] array
	 * then find two element in array, i < j, which prefix[j] - prefix[i] = sum
	 * like two sum problem, we can use hash table
	 */
	
	public static void task10_subArraySum2(int[] a, int sum) {
		int n = a.length;
		int[] prefix = new int[n];
		
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				prefix[i] = a[i];
			} else {
				prefix[i] = prefix[i - 1] + a[i];
			}
		}
		// value, index
		HashMap<Integer, Integer>  map = new HashMap<Integer, Integer>();
		map.put(prefix[0], 0);
		for (int i = 1; i < prefix.length; i++) {
			int curSum = prefix[i];
			int curKey = curSum - sum;
			if (map.containsKey(curKey)) {
				int curIndex = map.get(curKey);
				System.out.println("Sum found between index " + (curIndex + 1) + " and " + i);
				return ;
			} else {
				map.put(curSum, i);
			}
		}
	}  // Time: O(n)  Space: O(n)
	
	
	
	/*
	 * OPT method2
	 * 
	 */
	
	/*
	 * task11
	 * Dynamic Programming | Set 14 (Maximum Sum Increasing Subsequence)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
	 * 
	 * Variation of LIS
	 * f[i] is the maximum sum increasing subsequence ending with a[i]
	 * f[i] = {a[i] + Max(f[j])}  0<=j<i
	 */
	
	public static int task11_MaxSumIS(int[] a) {
		int len = a.length;
		int[] f = new int[len];
		f[0] = a[0];
		for (int i = 1; i < len; i++) {
			f[i] = a[i];
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && f[i] < f[j] + a[i]) {
					f[i] = f[j] + a[i];
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < f.length; i++) {
			max = Math.max(max, f[i]);
		}
		return max;
	}
	
	
	
	/*
	 * task12
	 * Dynamic Programming | Set 3 (Longest Increasing Subsequence)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
	 */
	
	/*
	 * f[i] is the length of lIS until index i such that arr[i] is part of LIS and arr[i] 
	 * is the last element in LIST. 
	 * f[i] = {1 + Max(f[j])};  0 <=j < i
	 */
	
	public static int task12_LIS(int[] a) {
		int len = a.length;
		int[] f = new int[len];
		f[0] = 1;
		for (int i = 1; i < len; i++) {
			f[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i]) {
					f[i] = Math.max(f[i], f[j] + 1);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < f.length; i++) {
			max = Math.max(max, f[i]);
		}
		return max;
		
	}
	
	public static void test12() {
		int[] a = {10, 22, 9, 33, 21, 50, 41, 60 };
		System.out.println(task12_LIS(a));
	}
	
	/*
	 * task13
	 * Longest Monotonically Increasing Subsequence Size (N log N)
	 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	 * 
	 * !!! Do this later
	 * 
	 * 
	 */
	
	/*
	 * task14
	 * Find a triplet that sum to a given value
	 * 3Sum
	 * http://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
	 */
	
	/*
	 * task15
	 * Find the smallest positive number missing from an unsorted array
	 * http://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
	 * 
	 * You are given an unsorted array with both positive and negative elements. 
	 * You have to find the smallest positive number missing from the array in O(n) time 
	 * using constant extra space. You can modify the original array.
	 */
	
	
	public static int findMissing(int[] a) {
		int firstNoPositiveIndex = partitionBy0(a);
		System.out.println(Arrays.toString(a));
		System.out.println(firstNoPositiveIndex);
		// before a[firstNoPositiveIndex], remember to do the sanity check
		if (firstNoPositiveIndex < a.length && a[firstNoPositiveIndex] > 0) {
			firstNoPositiveIndex = a.length;
		}
		return findMissingPositive(a, firstNoPositiveIndex);
	}
	
	// find the smallest positive missing number in an array that contains all positive integers
	public static int findMissingPositive(int[] a, int size) {
		for (int i = 0; i < size; i++) {
			int curShouldIndex = Math.abs(a[i]) - 1;
			if (curShouldIndex < size && a[curShouldIndex] > 0 ) {
				// set the a[curShouldIndex]  = - a[curShouldIndex]
				a[curShouldIndex] = -a[curShouldIndex];
			}
			System.out.println("----------------start------------------");
			System.out.println("i = " + i);
			System.out.println(Arrays.toString(a));
			System.out.println("------------------end----------------");
		}
		
		
		// traverse the array, find the first element > 0
		for (int i = 0; i < size; i++) {
			if (a[i] > 0) {
				return i + 1;
			}
		}
		return size + 1;
	}
	
	// this return the first no-positive element
	// put the positive on the left side
	public static int partitionBy0(int[] a) {
		int start = 0, end = a.length - 1;
		
		while(start <= end) {
			if (a[start] > 0) {
				start ++;
			} else if (a[end] <= 0 ) {
				end --;
			} else {
				swap(a, start, end);
				start ++;
				end --;
			}
		}
		System.out.println("start = " + start);
		// start is the first index of no-positive element
		return start;
	}
	
	public static void test15() {
		int[] a1 = {2,1};
//		int[] a = {1,2,3,4,5,6,7, 0};
//		P4_Array.printArray(a);
//		int index = partitionBy0(a);
//		System.out.println("index = " + index);
//		P4_Array.printArray(a);
		
//		System.out.println("a's missing = " + findMissing(a));
		System.out.println("a1's missing = " + findMissing(a1));
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	
	/*
	 * similar problem
	 * http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
	 * Find duplicates in O(n) time and O(1) extra space
	 * Given an array of n elements which contains elements from 0 to n-1, 
	 * with any of these numbers appearing any number of times. 
	 * Find these repeating numbers in O(n) and using only constant memory space.
	 */
	
	
	
	/*
	 * Find the two repeating elements in a given array
	 * http://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-array/
	 * You are given an array of n+2 elements. All elements of the array are in range 1 to n. 
	 * And all elements occur once except two numbers which occur twice. Find the two repeating numbers.
	 * 
	 * 1. counter array. Time: O(n), space O(n)
	 * 2. XOR. XOR all elements in array with 1..n, get the result
	 * 3. Use array elements as index
	 * 	  Algorithm:
	 * 	  Traverse the array, Do following for every index i of A[i]
	 *    {
	 *    	check for sign of A[abs(A[i])]
	 *    	if (positive) then 
	 *    		make it negative by A[abs(A[i])] = - A[abs(A[i])]  // mark this position has been occupied
	 *      else  // A[abs(A[i])] is negative
	 *          this element (ith element of list) is a repetition. 
	 *    }
	 *    
	 *    // this only works for positive
	 */
	public static void task15_1_find_all_repeating_elements(int[] array) {
		int size = array.length;
		for(int i = 0; i < size; i ++) {
			if (array[Math.abs(array[i])] > 0) {
				array[Math.abs(array[i])] = - array[Math.abs(array[i])];
			} else {
				System.out.println(Math.abs(array[i]));
			}
		}
	}
	
}

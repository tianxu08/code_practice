package arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;



public class P9_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test5();
//		test6();
		teset7();
//		test8();
//		test10();
//		test11();
//		test12();
//		test13();
//		test14();
	}
	
	/*
	 * 1 Equilibrium index of an array
	 * 2 Which sorting algorithm makes minimum number of memory writes?
	 * 3 Turn an image by 90 degree
	 * 4 Search in a row wise and column wise sorted matrix
	 * 5 Next Greater Element
	 * 6 Check if array elements are consecutive
	 * 7 Find the smallest missing number
	 * 8 Count the number of occurrences in a sorted array
	 * 9 Interpolation search vs Binary search
	 * 10 Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
	 * 11 Maximum of all subarrays of size k 
	 * 12 Find whether an array is subset of another array
	 * 13 Find the minimum distance between two numbers
	 * 14 Find the repeating and the missing 
	 */
	/*
	 * task1
	 * Equilibrium index of an array
	 * http://www.geeksforgeeks.org/equilibrium-index-of-an-array/
	 * Equilibrium index of an array is an index 
	 * such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes. For example, in an arrya A:
	 * A[0] = -7, A[1] = 1, A[2] = 5, A[3] = 2, A[4] = -4, A[5] = 3, A[6]=0
     *          0  1  2  3  4  5  6
	 * A =    [-7, 1, 5, 2, -4, 3, 0]
	 * prefix  -7  -6 -1 1  -3  0  0
	 * sufix    4  4  3 1  -1   3  0
	 * 3 is an equilibrium index, because:
	 * A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
	 * 
	 * Idea: get two array. 
	 * leftSum
	 * rightSum
	 * 
	 */
	public static int  equilibrium(int[] a) {
		int n = a.length;
		int[] leftSum = new int[n];
		int[] rightSum = new int[n];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				leftSum[i] = a[i];
			} else {
				leftSum[i] = leftSum[i-1] + a[i];
			}
		}
		
		for (int i = n - 1; i >= 0; i--) {
			if (i == n-1) {
				rightSum[i] = a[i];
			} else {
				rightSum[i] = rightSum[i+1] + a[i];
			}
		}
		
		for (int i = 1; i < n - 1; i++) {
			if (leftSum[i-1] == rightSum[i + 1])  {
				return i;
			}
		}
		return -1;
	}
	
	
	/*
	 * the idea is to get the total sum of array first. 
	 * iterate through the array and keep updating the left sum, initialized as 0
	 * we can get the right sum by subtracting the elements one by one. 
	 */
	public static int equilibrium2(int[] a) {
		int sum = 0;
		int leftSum = 0;
		// get sum
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		
		for (int i = 0; i < a.length; i++) {
			sum -= a[i];  // this is the right sum, subtracting the element one by one
			if (leftSum == sum) {
				return i;
			}
			leftSum += a[i];
		}
		return -1;
	}
	
	public static void test1() {
		int[] a = {-7, 1, 5, 2, -4, 3, 0};
		int[] a1 = {0,0,0,0,0};
		int index = equilibrium(a);
		System.out.println("index = " + index);
		int index1 = equilibrium2(a);
		System.out.println("index1 = " + index1);
		int index2 = equilibrium(a1);
		int index2_2 = equilibrium2(a1);
		System.out.println("index2 = " + index2);
		System.out.println("index2_2 = " + index2_2);
	}
	
	/*
	 * task2
	 * Which sorting algorithm makes minimum number of memory writes?
	 * http://www.geeksforgeeks.org/which-sorting-algorithm-makes-minimum-number-of-writes/
	 */
	
	/*
	 * task3
	 * Turn an image by 90 degree
	 * http://www.geeksforgeeks.org/turn-an-image-by-90-degree/
	 */
	
	/*
	 * task4
	 * Search in a row wise and column wise sorted matrix
	 * http://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/
	 */
	
	
	
	/*
	 * task5
	 * Next Greater Element
	 * http://www.geeksforgeeks.org/next-greater-element/
	 * 
	 * using stack
	 * 
	 * 
	 * (1) push the a[0] into stack
	 * (2) scan from a[1] to a[n-1]
	 *   <1> mark the a[i] as next
	 *   	 for every time, we compare stack.peek() with next. 
	 *       if stack.peek() < next, print out stack.peek() --> next
	 *       stack.pop();
	 *       // pay attention that in the process of pop, the stack might be empty. 
	 *       if stack.peek() >= next, push the next into stack
	 *   <2> if stack is not empty
	 *   
	 */
	/*
	 * Time Complexity: O(n). The worst case occurs when all elements are sorted in decreasing order. 
	 * If elements are sorted in decreasing order, then every element is processed at most 4 times.
	 * a) Initialy pushed to the stack.
	 * b) Popped from the stack when next element is being processed.
	 * c) Pushed back to the stack because next element is smaller.
	 * d) Popped from the stack in step 3 of algo.
	 */
	
	public static void printNextGreaterElem2(int[] a) {
		Stack<Integer> st = new Stack<Integer>();
		st.push(a[0]);
		for (int i = 1; i < a.length; i++) {
			int next = a[i];
			if (!st.isEmpty()) {
				while(!st.isEmpty() && st.peek() < next) {
					System.out.println(st.peek() + " --> " + next);
					st.pop();
				}
			}
			st.push(next);
		}
		// there might still have elements in stack
		while(!st.isEmpty()) {
			System.out.println(st.peek() + "-->" + "-1");
			st.pop();
		}
	}
	public static void printNextGreaterElem(int[] a) {
		Stack<Integer> st = new Stack<Integer>();
		st.push(a[0]);
		
		for (int i = 1; i < a.length; i++) {
			int next = a[i];
			if (!st.isEmpty()) {
				int elem = st.pop();
				while(elem < next) {
					// print all element < next in the stack
					System.out.println(elem + " --> " + next);
					if (st.isEmpty()) {
						break;
					}
					elem = st.pop();
				}
				// if elem >= next, push the elem back
				if (elem >= next) {
					st.push(elem);
				}
			}
			// push the next into the st
			st.push(next);
		}
		
		// get the remaining elements in the stack
		while(!st.isEmpty()) {
			int elem = st.pop();
			int next = -1;
			System.out.println(elem + " --> " + next);
		}
	} // Time: O(n)
	
	public static void test5() {
		int[] a = {11, 13, 21, 3, 4,7,9,20,10,2,1};
		printNextGreaterElem(a);
		System.out.println("---------------");
		printNextGreaterElem2(a);
	}
	
	
	/*
	 * task6 
	 * Check if array elements are consecutive
	 * http://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/
	 * Given an unsorted array of numbers, write a function that returns true if
	 * array consists of consecutive numbers.
	 * 
	 * If array is {5, 2, 3, 1, 4}, then the function should return true because
	 * the array has consecutive numbers from 1 to 5. 
	 * 1 Sorting. TimeL O(n log n) 
	 * 2 use visited array 
	 *    1) max – min + 1 = n where max is the maximum
	 * 		 element in array, min is minimum element in array and n is the number of
	 * 		 elements in array. 
	 *    2) All elements are distinct.
	 */
	
	public static boolean isConsecutive(int[] a) {
		int n = a.length;
		// get min
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			min = Math.min(min, a[i]);
			max = Math.max(max, a[i]);
		}
//		System.out.println("min = " + min);
//		System.out.println("max = " + max);
		if (max - min + 1 == n) {
			boolean[] count = new boolean[n];
			for (int i = 0; i < a.length; i++) {
				int cur = a[i];
				if (count[cur - min] == true) {
					// already exist
					return false;
				}
				count[cur - min] = true;
			}
			return true;
		} 
		return false;
	}
	
	public static void test6() {
		int[] a = {5, 2, 3, 1, 4};
		int[] a2 = {83, 78, 80, 81, 79, 82};
		int[] a3 = {34, 23, 52, 12, 3};
		int[] a4 = {7, 6, 5, 5, 3, 4};
		int[] a5 = {-1,-2,-3,-5,-4};
		System.out.println(isConsecutive(a));
		System.out.println(isConsecutive(a2));
		System.out.println(isConsecutive(a3));
		System.out.println(isConsecutive(a4));
		System.out.println(isConsecutive(a5));
		System.out.println("---------------");
		System.out.println(isConsecutive2(a));
		System.out.println(isConsecutive2(a2));
		System.out.println(isConsecutive2(a3));
		System.out.println(isConsecutive2(a4));
//		System.out.println(isConsecutive2(a5));
	}
	/*
	 * Method 3 (Mark visited array elements as negative)
	 * (1) max - min + 1 = n, max is the max element of the array, min is the min element of 
	 *     the array. n is the length of array
	 * (2) all elements are distinct
	 * implement step 2 in a different way from method 2.
	 * Instead we use an extra array, we mark the a[a[i] - min] as negative, to show, in this 
	 * position, we have already an element to place. 
	 * if we reach this position again, which means there is duplicate
	 * 
	 * if there is negative element in array, we need to take into more consideration.
	 */
	
	public static boolean isConsecutive2(int[] a) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int n = a.length;
		for (int i = 0; i < a.length; i++) {
			min = Math.min(min, a[i]);
			max = Math.max(max, a[i]);
		}
		if (max - min + 1 == n) {
			for (int i = 0; i < a.length; i++) {
				int j = 0;
				if (a[i] < 0) {
					// a[j] might already flapped
					j = - a[i] - min;
				} else {
					j = a[i] - min;
				}

//				System.out.println(" j = " + j);
				if (a[j] > 0) {
					a[j] = - a[j];
				} else {
					return false;
				}
			}
			
			return true;
		}
		return false;
	}
	
	/*
	 * task7 
	 * Find the smallest missing number
	 * http://www.geeksforgeeks.org/find-the-first-missing-number/ 
	 * 
	 * Given a sorted array of n integers where each integer is in the range from 0 to
	 * m-1 and m > n. Find the smallest number that is missing from the array.
	 * Sorted
	 * (1) binary search. 
	 *  for - to m - 1, do binary search in array. 
	 *  Time: O(m log n)
	 * (2) Linear search
	 *  scan the array. 
	 *  if a[0] != 0, return 0;
	 *  else 
	 *     a[i + 1] - a[i] > 1, return a[i] + 1 is the missing number
	 * (3) Using modified binary search. 
	 *     we compare the middle element with its index and make decision on the basis 
	 *     of this comparision. 
	 *     <1> if the first element is not same with the index, then return first index.
	 *     <2> else 
	 *            a) if a[mid] > mid, then the required element lies in left half
	 *            b) otherwise, the required element in the right half  
	 *            
	 *     Note: This method doesn’t work if there are duplicate elements in the array.
	 */
	
	public static int findMissing3(int[] a) {
		int start = 0, end = a.length - 1;
		return findMissingRec(a, start, end);
	}
	
	public static int findMissingRec(int[] a, int start, int end) {
		if (start > end) {
			return end + 1;
		}
		if (start != a[start]) {
			return start;
		}
		int mid = start + (end - start)/2;
		if (a[mid] > mid) {
			return findMissingRec(a, start, mid);
		} else {
			return findMissingRec(a, mid + 1, end);
		}
	}
	public static int findMissingIter(int[] a) {
		// edge case
		if (a == null || a.length == 0) {
			return 0;
		}
		int start = 0, end = a.length - 1;
		while(start + 1< end) {
			int mid = start + (end - start)/2;
			if (a[mid] > mid) {
				// in the left
				end = mid;
			} else {
				// should in the right side
				start = mid;
			}
		}
		// after the above while execution, start + 1 = end. if there is more than one elements
		// we first check whether a[]
		
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		if (a[start] != start) {
			return start;
		} else if (a[end] != end) {
			return end;
		} else {
			return end + 1;
		}
	}
	
	public static void teset7() {
		int[] a = {0,1,2,3,4,5};
		System.out.println(findMissing3(a));
		System.out.println(findMissingIter(a));
	}
		
	/*
	 * task8
	 * Count the number of occurrences in a sorted array
	 * http://www.geeksforgeeks.org/count-number-of-occurrences-in-a-sorted-array/
	 * 
	 * do binary search. 
	 */
	
	public static int countNumber(int[] a, int target) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int start = 0, end = a.length - 1;
		int leftIndex = -1, rightIndex = -1;
		// find the start position of the element
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (a[mid] == target) {
				end = mid;
			} else if (target < a[mid]) {
				// in the left 
				end = mid;
			} else {
				start = mid;
			}
		}
		if (a[start] == target) {
			leftIndex = start;
		} else if (a[end] == target) {
			leftIndex = end;
		} else {
			// we didn't find this target
			return -1;
		}
		
		start = 0;
		end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (a[mid] == target) {
				start = mid;
			} else if (target < a[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (a[start] == target) {
			rightIndex = start;
		} else if (a[end] == target) {
			rightIndex = end;
		} else {
			return -1;
		}
		
		return rightIndex - leftIndex +1;
	}
	public static void test8() {
		int[] a = {1, 1, 2, 2, 2, 2, 3};
		int target = 2;
		System.out.println(countNumber(a, target));
	}
	
	
	/*
	 * task9
	 * http://www.geeksforgeeks.org/g-fact-84/
	 * Interpolation search vs Binary search
	 */
	
	
	
	/*
	 * task10
	 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
	 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
	 * 
	 * 
	 */
	
	public static int maxIndexDiff(int[] a) {
		int maxIndexDif = -1;
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				if (a[j] > a[i]) {
					if (maxIndexDif < j - i) {
						maxIndexDif = j - i;
					}
				}
			}
		}
		return maxIndexDif;
	}// time O(n^2)
	
	public static void test10() {
		int[] a = {34, 8, 10, 3, 2, 80, 30, 33, 1};
		System.out.println("maxIndexDiff = " + maxIndexDiff(a));
		System.out.println("maxIndexDiff2 = "+ maxIndexDiff2(a));
	}
	
	/*
	 * Opt
	 * a[0..n-1]  length = n
	 * leftMin[i] is the min in a[0..i]
	 * rightMax[i] is the max in a[i..n-1]
	 * 
	 * leftMin is in decreasing order, as welll as rightMax
	 * e.g
	 * a[] = {34, 8, 10, 3, 2, 80, 30, 33, 1}
	 * lMin= {34, 8, 8,  3, 2, 2,  2,   2, 1}
	 * rMax= {80, 80, 80,80,80,80, 33, 33, 1}
	 * 
	 * now, we traverse both lMin and rMax. 
	 * i = 0, j = 0;
	 * if lMin[i] < rMax[j]  we update maxDiff = max(maxDiff, j -i) and j ++;
	 * this means that lMin[i] < rMax[j], there might also rMax[j++] > lMin[i]
	 * if lMin[i] > rMax[j] we can only i ++; since the rMax is also a decreasing array. 
	 * rMax[i ++] is also smaller than lMin[i] 
	 */
	
	public static int maxIndexDiff2(int[] a) {
		int n = a.length;
		int[] leftMin = new int[n];
		int[] rightMax = new int[n];
		
		leftMin[0] = a[0];
		for (int i = 1; i < n; i++) {
			leftMin[i] = Math.min(leftMin[i-1], a[i]);
		}
		rightMax[n-1] = a[n-1];
		for (int i = n-2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], a[i]);
		}
		
		int maxIndexDiff = -1;
		int i = 0, j = 0;
		while(i < n && j < n) {
			if (leftMin[i] < rightMax[j]) {
				maxIndexDiff = Math.max(maxIndexDiff, j - i);
				j ++;
			} else {
				i ++;
			}
		}
		return maxIndexDiff;
		
	}
	
	
	
	
	
	
	/*
	 * task11
	 * Maximum of all subarrays of size k 
	 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
	 * 
	 * Given an array and an integer k, 
	 * find the maximum for each and every contiguous subarray of size k.
	 * 
	 * Input :
	 * arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
	 * k = 3
	 * Output :
	 * 3 3 4 5 5 5 6
	 * 
	 * Input:
	 * arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13} k = 4
	 * Output :
	 * 10 10 10 15 15 90 90
	 */
	
	public static void maxElemInSubarray(int[] a, int k) {
		for (int i = 0; i <= a.length - k; i++) {
			int max = a[i];
			for (int j = 1; j < k; j++) {
				max = Math.max(a[i + j], max);
			}
			System.out.print(max + " ");
		}
	} // time: O(n * k)
	
	public static void test11() {
		int[] a = {1, 2, 3, 1, 4, 5, 2, 3, 6};
		int k = 3;
		maxElemInSubarray(a, k);
		System.out.println();
		maxElemInSubarray2(a, k);
	}
	
	/*
	 * use double queue
	 * the dq is in decreasing order
	 * in the window, the firstElement is the largest element.
	 * so every time, we maintain the k size window, and print out the first element.  
	 * 
	 */
	public static void maxElemInSubarray2(int[] a, int k) {
		Deque<Integer> dq = new ArrayDeque<Integer>();

		// the first k elements
		for (int i = 0; i < k; i++) {
			while(!dq.isEmpty() && a[i] >= a[dq.peekLast()]) {
				// if the dq is not empty and 
				// the current element is >= a[dq.peekLast], so the  
				dq.pollLast();  //remove the last element in the doulbe queue
			}
			dq.offerLast(i);
		}
		// Now, manipulate the remaining of the array
		for (int i = k; i < a.length; i++) {
			// first print out the
			System.out.print(a[dq.peekFirst()] + " ");
			// remove the index which is out of the window in the left
			while(!dq.isEmpty() && dq.peekFirst() <= i - k) {
				dq.pollFirst();
			}
			// continue add the right element into the window
			// remove all elements smaller than the currently being added element
			// (remove the useless elements)
			while(!dq.isEmpty() && a[i] >= a[dq.peekLast()]) {
				dq.pollLast();
			}
			dq.offerLast(i);
		}
		// print out the last window
		System.out.print(a[dq.peekFirst()]);
	} // Time: O(n). for every element, it was added and removed at most two times. 
	// O(2*n )  so, time is O(n), sapce is O(n)
	
	
	/*
	 * task12
	 * Find whether an array is subset of another array
	 * http://www.geeksforgeeks.org/find-whether-an-array-is-subset-of-another-array-set-1/
	 * 
	 *  (1) brute force. two loops.time : O(n * m) n = larger array.length. m = smaller array.length
	 *   
	 *  (2) sort and binary search.Time: O(n* log n )
	 *  
	 *  (3) sort and merge Time: O(n * log n)
	 *  
	 *  (4) using hash table.
	 */
	
	public static boolean isSubset(int[] a, int[] b) {
		if (a == null || a.length == 0) {
			return false;
		}
		if (b == null || b.length == 0) {
			return true;
		}
		Arrays.sort(a);
		Arrays.sort(b);
		// merge 
		int i = 0, j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				i ++;
			} else if (a[i] == b[j]) {
				i ++;
				j ++;
			} else {
				// a[i] > b[j]
				return false;
			}
		}
		if (j == b.length) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void test12() {
		int arr1[] = {11, 1, 13, 21, 3, 7};
	    int arr2[] = {11,11, 3, 7, 1};
	    System.out.println(isSubset(arr1, arr2));
	}
	
	
	/*
	 * task13 
	 * Find the minimum distance between two numbers
	 * http://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
	 * 
	 * Given an unsorted array arr[] and two numbers x and y, find the minimum
	 * distance between x and y in arr[]. The array might also contain
	 * duplicates. You may assume that both x and y are different and present in
	 * arr[].
	 * The order of x and y doesn't matter. 
	 * 
	 * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6
	 * Output: Minimum distance between 3 and 6 is 4.
	 * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
	 * Output: Minimum distance between 3 and 2 is 1.
	 */
	
	// two loops
	public static int minDist1(int[] a, int x, int y) {
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if ((a[i] == x && a[j] == y) 
						|| (a[i] == y && a[j] == x)) {
					minDist = Math.min(minDist, j - i);
				}
			}
		}
		return minDist;
	}
	
	public static void test13() {
		int[] a = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
		int x = 3, y = 6;
		System.out.println(minDist1(a, x, y));
		System.out.println(minDist2(a, x, y));
	}
	
	/*
	 * opt
	 * use prev to point the pervious x or y. 
	 * use cur to traverse the array. 
	 * if cur's value is different from prev's, update the minDist, prev = cur;
	 * if cur's value is same with prev's , update prev, prev = cur
	 * 
	 * Time: O(n)
	 */
	
	public static int minDist2(int[] a, int x, int y) {
		int prev = -1;
		int minDist = Integer.MAX_VALUE;
		// get the first x or y
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x || a[i] == y) {
				prev = i;
				break;
			}
		}
		
		// traverse the remaining.
		for (int cur = prev + 1; cur < a.length; cur++) {
			if (a[cur]== x || a[cur] == y) {
				if (a[cur]  != a[prev]) {
					if (cur - prev < minDist) {
						minDist = cur - prev;
						prev = cur;
					}
				}else {
					//a[cur] == a[prev]
					prev = cur;
				}
			}
		}
		return minDist;
	}
	
	/*
	 * task14
	 * http://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
	 * Find the repeating and the missing | Added 3 new methods
	 * 
	 * Given an unsorted array of size n. Array elements are in range from 1 to n. 
	 * One number from set {1, 2, …n} is missing and one number occurs twice in array.
	 * Find these two numbers.
	 * 
	 * e.g
	 * input:  a[] = {3,1,3};
	 * output: 2, 3 // 2 is the missing, 3 is the repeating
	 * 
	 * input; a[]= {4,3,6,2,1,1};
	 * output: 5, 1  //5 is the missing and 1 is the repeating. 
	 * 
	 * (1) sorting and traverse; Time: O(n log n)
	 * (2) using count sort array. then traverse the count array. Time: O(n)  space: O(n)
	 * (3) using elements as index and mark the visited paces
	 * (4) using XOR
	 */
	
	public static void printMissingAndRepeating3(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[Math.abs(a[i]) - 1] > 0) {
				a[Math.abs(a[i]) - 1] = -a[Math.abs(a[i]) - 1];  
			} else {
				// print the repeating
				System.out.println(Math.abs(a[i]));
			}
		}
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] > 0) {
				System.out.println(i + 1);
			}
		}
	}
	
	public static void test14() {
		int[] a = {1, 3, 4, 5, 5, 6, 2};
		printMissingAndRepeating3(a);  // this methoid modify the array. 
		int[] a2 = {1, 3, 4, 5, 5, 6, 2};
		printMissingAndRepeating4(a2);
	}
	
	public static void printMissingAndRepeating4(int[] a) {
		int n = a.length;
		int xor = a[0];
		for (int i = 1; i < a.length; i++) {
			xor = xor^ a[i];
		}
		for (int i = 1; i <= n; i++) {
			xor =xor ^ i;
		}
		
		// now the xor is the xor of the missing and repeating elements
		// find the right most digit which is 1
		int set_bit_num = xor & (~(xor - 1));
	
		int x = 0; // the set_bit_num is 1
		int y = 0; // the set_bit_num is 0
		for (int i = 0; i < a.length; i++) {
			if ((a[i] & set_bit_num) == set_bit_num) {
				// this is belong to x
				x ^= a[i];
			} else {
				y ^= a[i];
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if ((i & set_bit_num) == set_bit_num) {
				x ^= i;
			} else {
				y ^= i;
			}
		}
		System.out.println("x = " + x + "  y = " + y);
	} 
	// Time: O(n) Space: O(1)  this method cann't distinguish which one is missing 
	// and which one is repeating. we need to add one more step to distinguish it. 


	/*
	 * Summary:
	 * 
	 * task1 
	 * Equilibrium index of an array; 
	 * return a index which leftSum == rightSum. 
	 * Directly, get a leftSum[] array and rightSum[] 
	 * the tricky is get the total. traverse the from left to right, use sum - leftSum 
	 * to get the rightSum
	 * 
	 * task2
	 */
}

package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class P2_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		tesst1();
//		test4();
//		test5();
//		test7();
//		test10();
		test11();
	}
	/*
	 * list1
	 * 1. find smallest positive integer value that cannot be represented as sum of any subset of a given array
	 * 2. Find the first repeating element in an array of integers
	 * 3. Find common elements in three sorted arrays
	 * 4. Given an n x n square matrix, find sum of all sub-squares of size k x k !!!
	 * 5. Length of the largest subarray with contiguous elements | Set 1
	 * 6. Print all elements in sorted order from row and column wise sorted matrix !!!
	 * 7. Find the closest pair from two sorted arrays
	 * 8. Minimum Number of Platforms Required for a Railway/Bus Station
	 * 9. How to check if two given sets are disjoint?
	 * 10. Time complexity of insertion sort when there are O(n) inversions?
	 * 11. K’th Smallest/Largest Element in Unsorted Array
	 * 12. Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array
	 */
	
	
	
	
	/*
	 * task1
	 * Find the smallest positive integer value 
	 * that cannot be represented as sum of any subset of a given array
	 * http://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
	 * 
	 * Given a sorted array (sorted in non-decreasing order) of positive numbers, 
	 * find the smallest positive integer value 
	 * that cannot be represented as sum of elements of any subset of given set. 
	 * Expected time complexity is O(n).
	 * 
	 * 
	 * Let the input array be arr[0..n-1]. 
	 * We initialize the result as 1 (smallest possible outcome) and traverse the given array. 
	 * Let the smallest element that cannot be represented by elements at indexes 
	 * from 0 to (i-1) be ‘res’, there are following two possibilities 
	 * when we consider element at index i:
	 * 1) We decide that ‘res’ is the final result: If arr[i] is greater than ‘res’, 
	 * then we found the gap which is ‘res’ because the elements after arr[i] are also going to 
	 * be greater than ‘res’.
	 * 2) The value of ‘res’ is incremented after considering arr[i]: 
	 * The value of ‘res’ is incremented by arr[i] (why? If elements from 0 to (i-1) 
	 * can represent 1 to ‘res-1′, then elements from 0 to i can represent 
	 * from 1 to ‘res + arr[i] – 1′ be adding ‘arr[i]’ to all subsets that represent 1 to ‘res’)
	 */
	
	public static int smallestPositive(int[] a) {
		int res = 1;
		for (int i = 0; i < a.length && a[i] <= res; i++) {
			res += a[i];
		}
		return res;
	}
	
	public static void test1() {
		int[] a1 = {1, 3, 4, 5};
		System.out.println(smallestPositive(a1));
		int[] a2 = {1, 2, 6, 10, 11, 15};
		System.out.println(smallestPositive(a2));
		int[] a3 = {1, 1, 1, 1};
		System.out.println(smallestPositive(a3));
		int[] a4 = {1, 1, 3, 4};
		System.out.println(smallestPositive(a4));
	}
		
	/*
	 * task2
	 * Find the first repeating element in an array of integers
	 * http://www.geeksforgeeks.org/find-first-repeating-element-array-integers/
	 * (1) two loops
	 *     Time: O(n*n)
	 * (2) Use sorting. input[], copy to temp[] 
	 *     sort temp. 
	 *     traverse input. do binary search in temp to get the occurence of input[i]. 
	 *     if occurence >1, get it.  
	 *     Time: O(n) Space O(n)
	 * (3) use a hashset. traverse from right to left. and put element into the set. 
	 *     if set already has the current element, update the minimum index. 
	 *     Time: O(n) space O(n)
	 */
	
	public static int task2_firstRepeatElement(int[] a) {
		if (a == null || a.length <= 1) {
			return -1;
		}
		int min = Integer.MAX_VALUE;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = a.length - 1; i >= 0; i--) {
			if (set.contains(a[i])) {
				min = i;
			} else {
				set.add(a[i]);
			}
		}
		return min;
	}
	
	
	/*
	 * task3
	 * Find common elements in three sorted arrays
	 * http://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/
	 * 
	 * the idea is like intersection of two arrays.
	 * 
	 *                 
	 */
	public static void task3_printCommonOf3Arrays(int[] a1, int[] a2, int[] a3) {
		int i1 = 0, i2 = 0, i3 = 0;
		while(i1 < a1.length && i2 < a2.length && i3 < a3.length) {
			if (a1[i1] == a2[i2] && a2[i2]== a3[i3]) {
				System.out.println("common element: " + a1[i1]);
				i1 ++;
				i2 ++;
				i3 ++;
			} else if (a1[i1] <= a2[i2] && a1[i1] <= a3[i3]) {
				i1 ++;
			} else if (a2[i2] <= a3[i3] && a2[i2] <= a1[i1]) {
				i2 ++;
			} else {
				i3 ++;
			}
		}
	}
	
	public static void test3() {
		
	}
	

	/*
	 * task4
	 * Given an n x n square matrix, find sum of all sub-squares of size k x k
	 * http://www.geeksforgeeks.org/given-n-x-n-square-matrix-find-sum-sub-squares-size-k-x-k/
	 * 
	 * brute force: 
	 * 		traverse the matrix, get the sum of every sub-square
	 * 		Time: O(n * n * k * k)
	 * OPT:
	 * first, we calculate sum of all vertical strips of size k*1 in a temporary square matrix stripSum[][]. 
	 * Once we have the sum of all vertical strips, we can calculate sum of first sub-square in a row 
	 * as sum of first k strips in that row, and for remaining sub-squares, we can calculate sum in O(1)
	 * time by removing the leftmost strip of previous subsquare and adding the rightmost strip of new square  
	 */
	
	public static int[][] task4_sumOfKxK(int[][] matrix, int k) {
		int n = matrix.length;
		int subMRow = n - k + 1;
		int subMCol = n - k + 1;
		int[][] stripSum = new int[subMRow][n];
		int[][] result = new int[subMRow][subMCol];
		// calculate the strip Sum
		for (int j = 0; j < n; j++) {
			// calculate the first strip size of k
			for (int stripRowStart = 0; stripRowStart < k; stripRowStart ++) {
				if (stripRowStart == 0) {
					for (int i = 0; i < k; i++) {
						stripSum[0][j] += matrix[i][j];
					}
				} else {
					int prev = matrix[stripRowStart - 1][j];
					int next = matrix[stripRowStart + k - 1][j];
					stripSum[stripRowStart][j] = stripSum[stripRowStart - 1][j] - prev + next; 
				}
			}
		} // Time: O(n*k)
		printMatrix(stripSum);
		
		// get the result 
		// for every row, get the sum of every subarray size of k
		for (int i = 0; i < subMRow; i++) {
			for (int col = 0; col < subMCol; col++) {
				if (col == 0) {
					for (int j = 0; j < k; j++) {
						result[i][col] += stripSum[i][j];
					} 
				} else {
					int prev = stripSum[i][col - 1];
					int next = stripSum[i][col + 1];
					result[i][col] = result[i][col - 1] - prev + next;
				}
			}
		} // Time: O(k*k)
		// for debug
		printMatrix(result);
		return result;
	}
	
	public static void printMatrix(int[][] a) {
		System.out.println("----------start--------------");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("----------end--------------");
	}
	public static void test4() {
		int[][] matrix = {
				{1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
		};
		int k = 3;
		int[][] rev = task4_sumOfKxK(matrix, k);
		printMatrix(rev);
	}
	
	/*
	 * task5
	 * Length of the largest subarray with contiguous elements | Set 1
	 * http://www.geeksforgeeks.org/length-largest-subarray-contiguous-elements-set-1/
	 * 
	 * Brute solution:
	 * 
	 * A tricky solution. 
	 * using hash map. 
	 */
	public static void test5() {
		int[] a = {14, 12, 11, 20};
		System.out.println(task5_longestSubarrayWithContiguousElement(a));
	}
	public static int task5_longestSubarrayWithContiguousElement(int[] a) {
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for (int i = 0; i < a.length; i++) {
			map.put(a[i], false);
		}
		int maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			int len = 1;
			len += task5_getLenghtWithDirection(a[i], map, 1);
			len += task5_getLenghtWithDirection(a[i], map, -1);
		    map.put(a[i], true);
		    
		    maxLen = Math.max(maxLen, len);
		}
		return maxLen;
	}
	public static int task5_getLenghtWithDirection(int elem,  HashMap<Integer, Boolean> map, int step) {
		int count = 0;
		for (int i = elem + step; map.containsKey(i) && map.get(i) == false ; i += step) {
			// not here, we need to use map.containKey(i) to make sure that the map contains i. 
			// otherwise, it will throw null pointer exception. 
			count ++;
			map.put(i, true);
		}
		return count;
	}
	
	
	
	/*
	 * task6
	 * Print all elements in sorted order from row and column wise sorted matrix
	 * http://www.geeksforgeeks.org/print-elements-sorted-order-row-column-wise-sorted-matrix/
	 * 
	 * (1) print diagnol with sort
	 * (2) use merge k sorted array. via minHeap.
	 *     this is an interesting question. !!! first, manually create a heap. heapify, etc.  
	 */
	public static int[] task6_mergeKSortedArray(ArrayList<ArrayList<Integer>> list, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k);
		return null;
		
	}
	
	
	
	/*
	 * task7
	 * Find the closest pair from two sorted arrays
	 * http://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-whose-sum-closest-x/
	 * 
	 * Given two sorted arrays and a number x, find the pair 
	 * whose sum is closest to x and the pair has an element from each array.
	 * We are given two arrays ar1[0…m-1] and ar2[0..n-1] and a number x, 
	 * we need to find the pair ar1[i] + ar2[j] such that absolute value of (ar1[i] + ar2[j] – x) is minimum.
	 * 
	 * 1 brute force
	 *   two loops
	 * 2 (1)merge the two sorted array. also, 
	 *       using a boolean[m + n] to indicate the source of element in merged array
	 *   (2) using linear time algorithm to find the pair with sum cloest to x. 
	 *       on extra thing is that we need to consider the elements should one from a1[] and one from a2[]
	 * 
	 * 3 do single pass and O(1) extra space
	 *   (1) diff is the difference between pair sum and x. diff = Integer.max;
	 *   (2) (a) left is the leftmost index of a1: left = 0
	 *       (b) right is the rightmost index of a2  right = a2.length - 1;
	 *   (3) while(left < a1.length && right >= 0) {
	 *           if (diff > a1[left] + a2[right] - x)
	 *               update diff
	 *           else if (a1[left] + a2[right] < sum) {
	 *           	// we want a larger one
	 *              left ++;
	 *           } else {
	 *           	right --;
	 *           }
	 *       }
	 */
	
	public static int minDiff(int[] a1, int[] a2, int x) {
		int minDif = Integer.MAX_VALUE;
		int left = 0, right = a2.length - 1;
		int final_left = -1, final_right = -1;
		while(left < a1.length && right >= 0) {
			if (minDif > Math.abs(a1[left] + a2[right] - x)) {
				// !!! don't forget to use abs
				minDif = Math.abs(a1[left] + a2[right] - x);
				final_left = left;
				final_right = right;
			}
			if (a1[left] + a2[right] < x) {
				left ++;
			} else {
				right --;
			}
		}
		System.out.println("left = " + a1[final_left]);
		System.out.println("right = " + a2[final_right]);
		return minDif;
	}
	public static void test7() {
		int[] a1 = {1, 4, 5, 7};
		int[] a2 = {10, 20, 30, 40};
		int x = 32;
		int rev = minDiff(a1, a2, x);
		System.out.println("rev = " + rev);
		
	}
	
	
	
	/*
	 * task8
	 * Minimum Number of Platforms Required for a Railway/Bus Station
	 * http://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
	 */
	
	
	
	/*
	 * task9
	 * How to check if two given sets are disjoint?
	 * http://www.geeksforgeeks.org/check-two-given-sets-disjoint/
	 * 
	 * check whether two array have two common 
	 * 
	 * Given two sets represented by two arrays, how to check if the given two sets are disjoint or not? 
	 * It may be assumed that the given arrays have no duplicates.
	 */
	
	/*
	 * task10
	 * Time complexity of insertion sort when there are O(n) inversions?
	 * http://www.geeksforgeeks.org/time-complexity-insertion-sort-inversions/
	 * 
	 * If we take a closer look at the insertion sort code, 
	 * we can notice that every iteration of while loop reduces one inversion. 
	 * The while loop executes only if i > j and arr[i] < arr[j]. 
	 * Therefore total number of while loop iterations (For all values of i) is same as number of inversions. 
	 * Therefore overall time complexity of the insertion sort is O(n + f(n)) where f(n) is inversion count. 
	 * If the inversion count is O(n), then the time complexity of insertion sort is O(n).
	 * In worst case, there can be n*(n-1)/2 inversions. 
	 * The worst case occurs when the array is sorted in reverse order. 
	 * So the worst case time complexity of insertion sort is O(n2).
	 */
	
	/*
	 * 1 4 3 8 2 1
	 *     | 
	 *     i
	 */
	public static void insertSort(int[] array) {
		if (array == null || array.length == 0) {
			return ;
		}
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j = i - 1;
			while(j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				j --;
			}
			array[j + 1] = temp;
		}
	}
	public static void test10() {
		int[] a= {1,4,3,8,2};
		P4_Array.printArray(a);
		insertSort(a);
		P4_Array.printArray(a);
	}	
	
	
	
	/*
	 * task11
	 * K’th Smallest/Largest Element in Unsorted Array
	 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
	 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-2-expected-linear-time/
	 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-3-worst-case-linear-time/
	 */
	// get the Kth smallest element in array, k is 1 based
	
	public static void test11() {
		int[] a = {7, 10, 4, 3, 20, 15};
		int k = 3;
		int kth = getKthSmallest(a, k);
		System.out.println("kth = " + kth);
	}
	public static int getKthSmallest(int[] a, int k) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int index = partition(a, 0, a.length - 1, k- 1);
		P4_Array.printArray(a);
		System.out.println("index = " + index);
		return a[index];
	}
	
	
	// get the Kth element's index. k is 0 based
	public static int partition(int[] a, int start, int end, int k) {
		int left = start, right = end;
		int pivot = a[end];
		while(true) {
			while(left < right && a[left] < pivot) {
				left ++;
			}
			while(left < right && a[right] >= pivot) {
				right --;
			}
			if (left == right) {
				break;
			}
			swap(a, left, right);
		}
		swap(a, left, end);
		if (left == k) {
			return left;
		} else if (left > k) {
			// in the left side
			return partition(a, start, left - 1, k);
		} else {
			// in the right side
			return partition(a, left + 1, end, k);
		}
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
		
	}
	
	
	
	/*
	 * task12
	 * Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array
	 * http://www.geeksforgeeks.org/find-index-0-replaced-1-get-longest-continuous-sequence-1s-binary-array/
	 * 
	 * Given an array of 0s and 1s, find the position of 0 to be replaced with 1 
	 * to get longest continuous sequence of 1s. 
	 * Expected time complexity is O(n) and auxiliary space is O(1).
	 */
	
	
	
	

}

package arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class P4_Array {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test5();
//		test12();
//		test14();
//		test6();
		test10();
	}
	
	
	/*
	 * 1: Find the minimum element in a sorted and rotated array
	 * 2: Stable Marriage Problem
	 * 3: Merge k sorted arrays | Set 1
	 * 4: Radix Sort
	 * 5: Move all zeroes to end of array
	 * 6: find number of pairs such that x^y > y^x
	 * 7: Count all distinct pairs with difference equal to k
	 * 8: Count all possible paths from top left to bottom right of a m*n matrix
	 * 9: Suffix Array | Set 1 (Introduction)
	 * 10: Rearrange an array so that arr[i] becomes arr[arr[i]] with O(1) extra space
	 * 11: Sort n numbers in range from 0 to n^2 – 1 in linear time
	 * 12: Count all possible groups of size 2 or 3 that have sum as multiple of 3
	 * 13: Divide and Conquer | Set 5 (Strassen’s Matrix Multiplication)
	 * 14: Find if there is a subarray with 0 suma
	 *  
	 * /
	 
	
	
	/*
	 * task1
	 * Find the minimum element in a sorted and rotated array
	 * http://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
	 * Assume that all elements are distinct
	 * Binary search
	 */
	public static int findMinInRotate(int[] a) {
		if (a == null || a.length == 0) {
			return Integer.MIN_VALUE;
		}
		int start = 0, end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (a[start] < a[mid] && a[mid] < a[end]) {
				return a[start];
			} else if (a[mid] > a[start]) {
				// the left side is sorted, throw out the left side
				start = mid;
			} else {
				// a[mid] <= a[start], the right side is sorted, throw the right
				// side
				end = mid;
			}
		}
		return Math.min(a[start], a[end]);
	}
	
	/*
	 * extension
	 * if there is duplicates
	 */
	
	public static int findMinInRotateWithDup(int[] a) {
		if (a == null || a.length == 0) {
			return Integer.MIN_VALUE;
		}
		int start = 0, end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (a[start] < a[end]) {
				return a[start];
			} 
			if (a[mid] > a[start]) {
				// the left side is sorted, throw out the left side
				start = mid;
			} else if (a[mid] < a[start]) {
				// the right side is sorted, throw out the right side
				end = mid;
			} else{
				start ++;
			}
		}
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		return Math.min(a[start], a[end]);
	}
	
	public static void test1() {
//		int[] a = {4,5,6,7,1,2};
//		System.out.println(findMinInRotate(a));
//		System.out.println(findMinInRotateWithDup(a));
		int[] a2 = {1,2,3,4,5,0};
//		System.out.println(findMinInRotate(a2));
		System.out.println(findMinInRotateWithDup(a2));
	}
	
	/*
	 * task2
	 * Stable Marriage Problem
	 * http://www.geeksforgeeks.org/stable-marriage-problem/
	 * 
	 */
	
	/*
	 * task3
	 * Merge k sorted arrays | Set 1
	 * http://www.geeksforgeeks.org/merge-k-sorted-arrays/
	 * 
	 * using minHeap. the size of minheap is k.
	 */
	
	
	/*
	 * task4
	 * Radix Sort
	 * www.geeksforgeeks.org/radix-sort/
	 */
	
	/*
	 * task5
	 * Move all zeroes to end of array
	 * http://www.geeksforgeeks.org/move-zeroes-end-array/
	 * 
	 * Algorithm:
	 * count = 0
	 * (1) traverse the array, if a[i] != 0, a[count] = a[i]; count ++;
	 * (2) if a[i] == 0, skip it. 
	 * after traverse, we element before count is no-0, set the remaining to 0
	 */
	
	public static void put0toEnc(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				a[count] = a[i];
				count ++;
			}
		}
		while(count < a.length) {
			a[count ++] = 0;
		}
	}
	
	public static void test5() {
		int[] a = {0,0,0,1,2,3};
		printArray(a);
		put0toEnc(a);
		printArray(a);
		
	}
	
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	/*
	 * task6
	 * find number of pairs such that x^y > y^x
	 * http://www.geeksforgeeks.org/find-number-pairs-xy-yx/
	 * Given two arrays X[] and Y[] of positive integers, 
	 * find number of pairs such that x^y > y^x 
	 * where x is an element from X[] and y is an element from Y[].
	 * 
	 * !!! This is new to me
	 * 
	 * this is a math problem 
	 * 
	 * we can proof not so strickly, that if x < y, x^y > y^x
	 * 
	 * edge case:
	 * x == 0, 0
	 * x == 1, number of 0 in Y[]
	 * x == 2, decrease counter when y == 3 or y == 4
	 * x == 3, increase counter when y == 2
	 * for others, when x < y, we can proof that x^y > y^x
	 * 
	 * using binary search to optimize
	 */

	public static void test6() {
		ArrayList<Integer> inputX = new ArrayList<Integer>();
		ArrayList<Integer> inputY = new ArrayList<Integer>();
		int n = 15;
		for(int i = 0; i < n ; i ++) {
			int rand1 =(int)( Math.random() * n);
			int rand2 = (int)(Math.random() * n);
			inputX.add(rand1);
			inputY.add(rand2);
		}
		
		System.out.println(inputX);
		System.out.println(inputY);
		
		int[] X = new int[n];
		int[] Y = new int[n];
		
		for(int i = 0; i < n; i ++) {
			X[i] = inputX.get(i);
			Y[i] = inputY.get(i);
		}
		
		int rev = task6_countPairs(X, Y);
		int rev2 = task6_countPairsBF(X, Y);
		System.out.println("rev1 = " + rev);
		System.out.println("rev2BF = " + rev2);
		
		System.out.println(Math.pow(0, 0));
	}
	
	public static int task6_countPairsBF(int[] X, int[] Y) {
		int count = 0;
		
		for(int i = 0;i < X.length; i ++) {
			for(int j = 0; j < Y.length; j ++) {
				//
				if (X[i] == 0) {
					continue;
				}
				long x_y = (long)Math.pow(X[i], Y[j]);
				long y_x = (long)Math.pow(Y[j], X[i]);

				if (x_y > y_x) {
					count ++;
					// debug
//					System.out.println("------------------");
//					System.out.println("i = " + i + "  j = " + j);
//					System.out.println("x= " +  X[i]);
//					System.out.println("y= " + Y[j]);
//					System.out.println("x_y = " + x_y);
//					System.out.println("y_x = " + y_x );
//					System.out.println("------------------");
				}
			}
		}
		return count;
		
	}
	
	public static int task6_countPairs(int[] X, int[] Y) {
		// sanity check
		if (X == null || Y == null || X.length == 0 || Y.length == 0) {
			return 0;
		}
		int count = 0;
		Arrays.sort(Y);
		int[] NoOfY = new int[5];
		for(int i = 0; i < Y.length; i ++) {
			int elem = Y[i];
			switch (elem) {
			case 0:
				NoOfY[0]++;
				break;
			case 1: 
				NoOfY[1]++;
				break;
			case 2: 
				NoOfY[2]++;
				break;
			case 3:
				NoOfY[3]++;
				break;
			case 4:
				NoOfY[4]++;
				break;
			default:
				break;
			}
		}
		// debug
		System.out.println(Arrays.toString(NoOfY));
		
		for(int i = 0; i < X.length; i ++) {
			int x = X[i];
			count += task6_count(Y, x, NoOfY);
		}
		return count;
	}
	
	// int[] Y is sorted, int x is the target, int[] NoOfY is the number of 0, 1, 2,3,4 in Y
	public static int task6_count(int[] Y, int x, int[] NoOfY) {
		if (x == 0 ) {
			return 0;
		}
		int count = 0;
		count += NoOfY[0];
		if (x == 1) {
			return count;
		}
		
		int smallestLargerIndex = task6_getSmallestLargerIndex(Y, x);
		if (smallestLargerIndex != -1) {
			count += (Y.length - smallestLargerIndex);
		}
		if (x > 1) {
			count += NoOfY[1]; // No of '1'
		}
		
		if (x == 2) {
			count -= NoOfY[3]; // No of '3'
			count -= NoOfY[4]; // No of '4'
		}
		if (x == 3) {
			count += NoOfY[2]; // No of '2'
		}
		
		return count;
	}
	// get the index of element which is smallest larger than the target, 
	// if target is the larger than the last element, return -1  
	
	
	public static int task6_getSmallestLargerIndex(int[] Y, int target) {
		int left = 0, right = Y.length - 1;
		if (target > Y[right]) {
			return -1;
		}
		if (target < Y[0]) {
			return 0;
		}
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (Y[mid] == target) {
				left = mid; 
			} else if (Y[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (Y[left] > target) {
			return left;
		} else if(Y[right] > target) {
			return right;
		} else{
			return -1;
		}
		
	}
	
	
	
	/*
	 * task7
	 * Count all distinct pairs with difference equal to k
	 * http://www.geeksforgeeks.org/count-pairs-difference-equal-k/
	 * 
	 * similar to 2Sum 
	 * 1 sort
	 * 2 using hash
	 */
	
	
	
	/*
	 * task8
	 * Count all possible paths from top left to bottom right of a m*n matrix
	 * from each cell you can either move only to right or down
	 * http://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
	 * 
	 * method1:
	 * f(m, n) = f (m - 1, n) + f(m, n - 1)
	 * base: m = 1 or n = 1, return 1
	 * methoe2:
	 * dp:
	 */
	
	
	/*
	 * task9
	 * Suffix Array | Set 1 (Introduction)
	 * http://www.geeksforgeeks.org/suffix-array-set-1-introduction/
	 * 
	 * !!! after learning suffix tree. 
	 */
	
	
	/*
	 * task10
	 * http://www.geeksforgeeks.org/rearrange-given-array-place/
	 * Rearrange an array so that arr[i] becomes arr[arr[i]] with O(1) extra space
	 * Given an array arr[] of size n where every element is in range from 0 to n-1. 
	 * Rearrange the given array so that arr[i] becomes arr[arr[i]]. 
	 * This should be done with O(1) extra space.
	 * !!!  
	 * Input: arr[]  = {3, 2, 0, 1}
	 * Output: arr[] = {1, 0, 3, 2}
	 * arr[0]  <= arr[arr[0]] = arr[3] = 1   assign 1 to arr[0]
	 * 
	 * Input: arr[] = {4, 0, 2, 1, 3}
	 * Output: arr[] = {3, 4, 2, 0, 1}
	 * 
	 * Input: arr[] = {0, 1, 2, 3}
	 * Output: arr[] = {0, 1, 2, 3}
	 * 
	 */
	public static void test10() {
		int[] array = {3,2,0,1};
		int[] output = task10_rearrangeBF(array);
		System.out.println(Arrays.toString(output));
		System.out.println("---------------------");
		System.out.println(Arrays.toString(array));
		int[] output2 = task10_rearrange(array);
		
		System.out.println(Arrays.toString(output2));
		
	}
	
	public static int[] task10_rearrangeBF(int[] array) {
		// sanity check
		if (array == null || array.length == 0) {
			return array;
		}
		int n = array.length;
		int[] output = new int[n];
		for(int i = 0; i < n; i ++) {
			if (array[i] < n && array[i] >= 0) {
				output[i] = array[array[i]];
			}
		}
		return output;
	}
	
	
	/*
	 * 1) Increase every array element arr[i] by (arr[arr[i]] % n)*n.
	 * 2) Divide every element by n.
	 * Let us understand the above steps by an example array {3, 2, 0, 1}
	 * In first step, every value is incremented by (arr[arr[i]] % n)*n
	 * After first step array becomes {7, 2, 12, 9}. 
	 * The important thing is, after the increment operation
	 * of first step, every element holds both old values and new values. 
	 * Old value can be obtained by arr[i]%n and new value can be obtained by arr[i]/n.
	 * In second step, all elements are updated to new or output values 
	 * by doing arr[i] = arr[i]/n.
	 * After second step, array becomes {1, 0, 3, 2}
	 */
	
	public static int[] task10_rearrange(int[] array) {
		int n = array.length;
		for(int i = 0; i < n; i ++) {
			array[i] += (array[array[i]] % n) * n;
		}
		
		// 
		for(int i = 0; i < n; i ++) {
			array[i] /= n;
		}
		return array;
	}
	
	
	
	
	/*
	 * task11
	 * Sort n numbers in range from 0 to n^2 – 1 in linear time
	 * http://www.geeksforgeeks.org/sort-n-numbers-range-0-n2-1-linear-time/
	 * 
	 * radix sort
	 */
	
	
	/*
	 * task12
	 * Count all possible groups of size 2 or 3 that have sum as multiple of 3
	 * http://www.geeksforgeeks.org/count-possible-groups-size-2-3-sum-multiple-3/
	 * 
	 * 1 Hash all elements in a count array based on remainder i.e 
	 *   for all elements, a[i], do c[a[i]%3] ++
	 * 2 Now, c[0] contains the number of elements whose remainder is 0
	 *        c[1]                                                    1
	 *        c[2]                                                    2
	 * 3 For group of 2, (there are two elements in the group), we have 2 possibility
	 *   a. 2 elements of in c[0]   
	 *   b. 1 element in c[1] and 1 in c[2]
	 * 4 For group 3, 
	 *   a. 3 in c[0]
	 *   b. 3 in c[1]
	 *   c. 3 in c[2]
	 *   d. 1 in c[0]. 1 in c[1]. 1 in c[2]
	 * 5 Add all groups in step3 and step4
	 * 
	 * A smart method
	 * This is a math problem. 
	 */
	public static int findGroup(int[] a) {
		int result = 0;
		int[] c = new int[3];
		
		for (int i = 0; i < a.length; i++) {
			c[a[i] % 3] ++;
		}
		
		// case 3.a
		result += c[0] * (c[0] - 1)/2;
		// case 3.b
		result += c[1] * c[2];
		// case 4.a
		result += c[0] * (c[0] - 1) * (c[0] - 2)/6;
		// case 4.b
		result += c[1] * (c[1] - 1) * (c[1] - 2)/6;
		// case 4.c
		result += c[2] * (c[2] - 1) * (c[2] - 2)/6;
		// case 4.d
		result += c[0] * c[1] * c[2];
		
		return result;
	}
	
	public static void test12() {
		int[] a = {3, 6, 7, 2, 9};
		int rev = findGroup(a);
		System.out.println("rev = " + rev);
	} 
	
	
	/*
	 * task13
	 * Divide and Conquer | Set 5 (Strassen’s Matrix Multiplication)
	 * http://www.geeksforgeeks.org/strassens-matrix-multiplication/
	 */
	
	
	
	/*
	 * task14
	 * Find if there is a subArray with 0 sum
	 * http://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
	 * 
	 * 1. a[i] == 0
	 * 2  sum == 0
	 * 3  there exist the same "sum"
	 * 
	 */
	public static boolean ZeroSumSubarray(int[] a) {
		int sum = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (a[i] == 0 || sum == 0 || set.contains(sum)) {
				return true;
			}
			set.add(sum);
		}
		return false;
	}
	
	public static void test14() {
		int[] a = {4, 2, -3, 1, 6};
		System.out.println(ZeroSumSubarray(a));
	} 
	
	
	
	
	

}

package arrays;

import java.util.Arrays;

public class P11_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test7();
//		test10();
//		test4();
		test6();
	}
	/*
	 * 1 Find the Missing Number
	 * 2 Search an element in a sorted and pivoted array
	 * 3 Merge an array of size n into another array of size m+n
	 * 4 Median of two sorted Arrays
	 * 5 Write a program to reverse an array
	 * 6 Program for array rotation
	 * 7 Maximum sum such that no two elements are adjacent
	 * 8  Leaders in an array
	 * 9 Sort elements by frequency | Set 1
	 * 10 Count Inversions in an array
	 * 11 2Sum Closest  Also refer to P12
	 * 12 Find the smallest and second smallest element in an array
	 */
	
	
	/*
	 * task1
	 * Find the Missing Number
	 * http://www.geeksforgeeks.org/find-the-missing-number/
	 * You are given a list of n-1 integers and these integers are in the range of 1 to n. 
	 * There are no duplicates in list. One of the integers is missing in the list. 
	 * Write an efficient code to find the missing integer.
	 * Example:
	 * I/P    [1, 2, 4, ,6, 3, 7, 8]
	 * O/P    5
	 */
	
	/*
	 * sln1 using sum formula
	 * (1)sum = (1 + n) * n /2
	 * (2)subtract every element in the array
	 */
	
	/*
	 * sln2 using XOR
	 */
	public static int findMissingNumber(int[] a, int n) {
		int missing = 0;
		// xor 1--n
		for (int i = 1; i <= n; i++) {
			missing ^= i;
		}
		// xor a[i]   
		for (int i = 0; i < a.length; i++) {
			missing ^= a[i];
		}
		return missing;
	}
	
	/*
	 * task2
	 * Search an element in a sorted and pivoted array
	 * Search an elements in an rotated array
	 * http://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
	 */
	
	
	/*
	 * task3
	 * Merge an array of size n into another array of size m+n
	 * http://www.geeksforgeeks.org/merge-one-array-of-size-n-into-another-one-of-size-mn/
	 * 
	 * merge from the last
	 */
	
	/*
	 * task4
	 * Median of two sorted Arrays
	 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
	 * There are 2 sorted arrays A and B of size n each.
	 * Write an algorithm to find the median of the array obtained 
	 * after merging the above 2 arrays(i.e. array of length 2n). 
	 * The complexity should be O(log(n))
	 * 
	 * !!! 
	 * the two arrays are in the same size. the media should be the ( elem1 + elem2 )/2
	 */
	/*
	 * Algorithm:
	 * simple count while merge. 
	 * 
	 */
	public static int medianOfTwoSortedArray1(int[] a1, int[] a2) {
		int n = a1.length;
		int m1 = 0, m2 = 0;
		int count = 0;
		int i = 0, j = 0;
		for(; count <= n ; count ++) {
			// this is handle the case when all elements in a1 is smaller than 
			// the first elements in a2. i ==n
			if (i == n) {
				// update m1
				m1 = m2;
				// update m2
				m2 = a2[0];
				break;
			}
			// the case that all elements in a2 is smaller than the first elements in a1
			// j == n
			if (j == n) {
				// update m1
				m1 = m2;
				m2 = a1[0];
				break;
			}
			
			// 
			if (a1[i] < a2[j]) {
				m1 = m2;
				m2 = a1[i];
				i ++;
			} else {
				m1 = m2;
				m2 = a2[j];
				j ++;
			}
		}
		return (m1 + m2)/2;
	}  // Time: O(n)
	
	public static void test4() {
		int[] a1 = {1, 12, 15, 26, 38};
		int[] a2 = {2, 13, 17, 30, 45};
		System.out.println(medianOfTwoSortedArray1(a1, a2));
	}
	
	/*
	 * By comparing the median of two arrays
	 * find the Kth smallest in two sorted array
	 * 
	 * Time: O(log (m + n))
	 */
	
	public static double findMedianOfTwoSortedArrays(int[] A, int[] B) {
		int len = A.length + B.length;
	
		if (len %2 == 0) {
			return (findKth(A, 0, B, 0, len/2) + findKth(A, 0, B, 0, len/2 + 1))/2.0;
		} else {
			return findKth(A, 0, B, 0, len/2 + 1);
		}
	}
	
	public static int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
		// if A_start or B_start are out of bound
		if (A_start >= A.length) {
			return B[B_start + k - 1];
		} 
		if (B_start >= B.length) {
			return A[A_start + k - 1];
		}
		if (k == 1) {
			return Math.min(A[A_start], B[B_start]);
		}
		int A_key = A_start + k/2 - 1 < A.length ? A[A_start + k/2 - 1] : Integer.MAX_VALUE;
		int B_key = B_start + k/2 - 1 < B.length ? B[B_start + k/2 - 1] : Integer.MAX_VALUE;
		
		if (A_key < B_key) {
			return findKth(A, A_start + k/2, B, B_start, k - k/2);
		} else {
			return findKth(A, A_start, B, B_start + k/2, k - k/2);
		}
	}
	
	
	
	/*
	 * task5
	 * Write a program to reverse an array
	 * http://www.geeksforgeeks.org/write-a-program-to-reverse-an-array/
	 * 
	 * iterative
	 * recursion
	 */
	
	/*
	 * task6
	 * Program for array rotation
	 * http://www.geeksforgeeks.org/array-rotation/
	 * 
	 * http://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/
	 * 
	 * http://www.geeksforgeeks.org/block-swap-algorithm-for-array-rotation/
	 * 
	 * (1) using temp array
	 * (2) don't using temp array. 三步翻转法
	 * 
	 * Write a function rotate(ar[], d, n) that rotates arr[] of size n by d elements.
	 * e.g
	 * 1 2 3 4 5 6 7
	 * after rotate
	 * 3 4 5 6 7 1 2
	 * 
	 * 
	 * 
	 */
	
	
	public static void test6() {
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		int d = 3;
		P4_Array.printArray(a);
		leftRotate3(a, d);
		P4_Array.printArray(a);
		Arrays.sort(a);
		P4_Array.printArray(a);
		leftRotate4(a, d);
		P4_Array.printArray(a);
	}
	/*
	 * method3
	 */
	public static void leftRotate3(int[] a, int d) {
		int n = a.length;
		int gcd = gcd(n, d);
		for (int i = 0; i < gcd; i++) {
			int temp = a[i];
			int j = i;
			while(true) {
				int k = j + d;
				// if k >= n, let k = k - n
				if (k >= n) {
					k = k - n;
				}
				// if k reach i, finish the shift. break
				if (k == i) {
					break;
				}
				// move the a[k] to a[j]
				a[j] = a[k];
				// update j
				j = k;
			}
			a[j] = temp;
		}
	}// Time: O(n)
	public static int gcd(int a, int b) { // valid for positive integers
		while(b > 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
	public static int gcd2(int a , int b) {
		if (b == 0) {
			return b;
		} else {
			return gcd2(b, a%b);
		}
	}
	
	/*
	 * method4
	 * reverse array
	 * 
	 * 1 2 3 4 5 6 7   d = 2
	 *  reverse a[0..d-1]  2 1 3 4 5 6 7
	 *  reverse a[d..n-1]  2 1 7 6 5 4 3
	 *  reverse a[0..n-1]  3 4 5 6 7 1 2
	 *          
	 */
	public static void leftRotate4(int[] a, int d) {
		int n = a.length;
		reverse(a, 0, d - 1);
		reverse(a, d, n - 1);
		reverse(a, 0, n - 1);
	}
	
	public static void reverse(int[] a, int start, int end) {
		while(start < end) {
			swap(a, start, end);
			start++;
			end --;
		}
	}
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	
	
	
	/*
	 * task7
	 * Maximum sum such that no two elements are adjacent
	 * http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
	 * Given an array of positive numbers, 
	 * find the maximum sum of a subsequence with the constraint 
	 * that no 2 numbers in the sequence should be adjacent in the array. 
	 * So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 
	 * should return 15 (sum of 3, 5 and 7).Answer the question in most efficient way
	 * !!!
	 * incl = max sum including the previous elements
	 * excl = max sum including the previous elements
	 * 
	 * the max sum including the current element is excl + cur
	 * the max sum excluding the current element is Math.max(incl, excl)
	 */
	public static void test7() {
		int[] a = {5 , -5, -10, 100, 10, 5};
		System.out.println(maxSum(a));
	}
	
	public static int maxSum(int[] a) {
		int incl = 0, excl = 0;
		int maxSum = 0;
		incl = a[0];
		maxSum = Math.max(maxSum, incl);
		for (int i = 1; i < a.length; i++) {
			int excl_new = Math.max(incl, excl);
			incl = excl + a[i];
			excl = excl_new;
		}
		return Math.max(incl, excl);
	}
	
	/*
	 * extension: 
	 * if there is still negative elements
	 */
	public static int maxSum2(int[] a) {
		return -1;
	}
	
	/*
	 * task8 
	 * Leaders in an array
	 * http://www.geeksforgeeks.org/leaders-in-an-array/ 
	 * Write a program to
	 * print all the LEADERS in the array. 
	 * An element is leader if it is greater
	 * than all the elements to its right side. And the rightmost element is
	 * always a leader. 
	 * For example int the array {16, 17, 4, 3, 5, 2}, leaders
	 * are 17, 5 and 2.
	 * 
	 * 1 naive solution 2 scan from the right
	 * 2 scan from the right
	 *   keep the max element from the right. 
	 *         if cur > max, print it out;
	 *         update max = cur;
	 *         if cur <= max, continue  
	 */
	
	public static void printLeaders(int[] a) {
		int n = a.length;
		int rightMax = a[n - 1];
		System.out.print(a[n-1] + " ");
		for (int i = n - 2; i >= 0; i--) {
			int cur = a[i];
			if (cur > rightMax) {
				System.out.print(cur + " ");
				rightMax = cur;
			}
		} 
	}
	
	public static void test8() {
		int[] a = {16, 17, 4,3,5,2};
		printLeaders(a);
	}
	
	/*
	 * task9
	 * Sort elements by frequency | Set 1
	 * http://www.geeksforgeeks.org/sort-elements-by-frequency/
	 */
	public static void test9() {
		
	}
	
	/*
	 * task10
	 * Count Inversions in an array
	 * http://www.geeksforgeeks.org/counting-inversions/
	 * inversion count for an array indicates: how far (or close) the array is from being sorted
	 * if an array is already sorted, the inversion is 0;
	 * if an array is in reverse order, the inversion is maximum
	 * e.g
	 * input = {2,4,1,3,5};
	 * have 3 inversions. (2,1) (4,1) (4,3)
	 * method 1 two loop. for every elements, find the smaller elem in its right
	 * 
	 * method 2: enhance merge sort
	 * in merge sort, we have 3 steps. 
	 * 1, sort left side; 2 sort right side; 3 merge the left and right;
	 * inv_count, we can get the inv_count from the above. 
	 * the 1 and 2 are straight forward. the 3 is a little tricky
	 * 
	 *  
	 */
	
	public static int mergeSort(int[] a) {
		int n = a.length;
		int[] temp = new int[n];
		int left = 0, right = n-1;
		return mergeSortHelper(a, temp, left, right);
	}
	
	public static int mergeSortHelper(int[] a, int[] temp, int left, int right) {
		int inv_count = 0;
		if (left < right) {
			int mid = left + (right - left)/2;	
			inv_count += mergeSortHelper(a, temp, left, mid);
			inv_count += mergeSortHelper(a, temp, mid + 1, right);
			inv_count += merge(a, temp, left, mid + 1, right);
		}
		return inv_count;
	}
	public static int merge(int[] a, int[] temp, int left ,int rightStart, int right) {
		int inv_count = 0;
		int i = left, j = rightStart;
		int index = left;
		while(i <= rightStart - 1 && j <= right) {
			//start merge
			if (a[i] <= a[j]) {
				temp[index ++] = a[i ++];
			} else {
				temp[index ++] = a[j++];
				//if a[i] > a[j], there is an inversion. 
				//since a[left, mid - 1] is already sorted. so, a[i..mid-1] all have inversion. 
				// the total number for this is mid - 1 - i + 1 = mid - i;
				inv_count += (rightStart - i);
			}
		}
		// merge the remaining in a[left]
		while(i <= rightStart - 1) {
			temp[index ++] = a[i ++];
		}
		while(j <= right) {
			temp[index ++] = a[j ++];
		}
		// copy temp back into a
		for (int k = left; k <= right; k++) {
			a[k] = temp[k];
		}
		return inv_count;
	} // time: O(n* log n)
	
	public static void test10() {
		int[] a = {2, 4, 1, 3, 5};
		System.out.println(mergeSort(a));
	}
	
	
	/*
	 * task11
	 * http://www.geeksforgeeks.org/two-elements-whose-sum-is-closest-to-zero/
	 * 2Sum Closest  Also refer to P12
	 */
	
	/*
	 * task12
	 * Find the smallest and second smallest element in an array
	 * http://www.geeksforgeeks.org/to-find-smallest-and-second-smallest-element-in-an-array/
	 * 
	 * algorithm:
	 * 1 first_smallest = second_smallest = Integer.Max_value;
	 * 2 loop all elements
	 *    if cur_val < first_smallest   update first_smallest and second_smallest
	 *    else if cur< second_smallest, then update the second
	 */
	
	public static void print1st_2ndSmallest(int[] a) {
		int first_smallest = Integer.MAX_VALUE;
		int second_smallest = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			int cur = a[i];
			if (cur < first_smallest) {
				second_smallest = first_smallest;
				first_smallest = cur;
			} else if (cur < second_smallest) {
				second_smallest = cur;
			} else {
				// do nothing
			}
		}
		System.out.println("first smallest = " + first_smallest);
		System.out.println("second smallest = " + second_smallest);
	}
	
	
	
	/*
	 * Summary
	 * (1)median of two sorted array
	 *    find the kth element in two sorted array
	 *    are the same problem. 
	 * (2) elements in an array has range from 1..n etc. 
	 *     find the missing number, we can use Xor or using the value of elements as index, 
	 *     then change the value in the array to - value;
	 *     
	 * (3)
	 */

}

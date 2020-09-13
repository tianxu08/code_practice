package arrays;

import java.util.Arrays;


public class P5_Arrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
//		test4();
//		test7();
//		test8();
//		test9();
//		test12();
		test14();
	}
	
	/*
	 * 1 Tug of War
	 * 2 Print Matrix Diagonally
	 * 3 Divide and Conquer | Set 3 (Maximum Subarray Sum)
	 * 4 Counting Sort
	 * 5 Merge Overlapping Intervals
	 * 6 Find the maximum repeating number in O(n) time and O(1) extra space
	 * 7 Find the maximum repeating number in O(n) time and O(1) extra space !!! The same with 6
	 * 8 Stock Buy Sell to Maximize Profit
	 * 9 Rearrange positive and negative numbers in O(n) time and O(1) extra space
	 * 10 Sort elements by frequency | Set 1
	 * 11 Sort elements by frequency | Set 2
	 * 12 Find a peak element
	 * 13 Print all possible combinations of r elements in a given array of size n
	 * 14 Given an array of of size n and a number k, find all elements that appear more than n/k times
	 * 15 Unbounded Binary Search Example 
	 *    (Find the point where a monotonically increasing function becomes positive first time)
	 * 16 Find the Increasing subsequence of length three with maximum product
	 * 
	 */
	
	/*
	 * task1 
	 * Tug of War
	 * http://www.geeksforgeeks.org/tug-of-war/
	 * 
	 * Given a set of n integers, divide the set in two subsets of n/2 sizes
	 * each such that the difference of the sum of two subsets is as minimum as
	 * possible. If n is even, then sizes of two subsets must be strictly n/2
	 * and if n is odd, then size of one subset must be (n-1)/2 and size of
	 * other subset must be (n+1)/2.
	 * 
	 * 
	 */
	
	public static class MinDiff {
		int val;    
		public MinDiff() {
			this.val = 0;
		}
	}
	public static void twoUntil(int[] a, boolean[] cur_elems, boolean[] soln_elems, 
			 MinDiff min_dif, int no_of_selected, int sum, int cur_sum, int cur_pos) {
		
		int n = a.length;
		if (cur_pos == n) {
			return ;
		}
		/*
		 * check that the numbers of elements left are not less than the number 
		 * of elements required to form the solution
		 */
		if ((n - cur_pos) < (n/2 - no_of_selected)) {
			return ;
		}
		
		// we don't use this element
		twoUntil(a, cur_elems, soln_elems, min_dif,no_of_selected, sum, cur_sum, cur_pos + 1);
		
		// we use this element
		// add the current element to the solution
		no_of_selected ++;
		cur_sum = cur_sum + a[cur_pos];
		
		cur_elems[cur_pos] = true;

		// check if a solution is formed
		if (no_of_selected == n/2) {
			// check if the solution formed is better than the best solution
			if (Math.abs(sum/2 - cur_sum) < min_dif.val) {
				min_dif.val = Math.abs(sum/2 - cur_sum);
				// copy the current solution to solution. 
				for (int i = 0; i < n; i++) {
					soln_elems[i] = cur_elems[i];
				}
			}
		} else {
			// consider the cases where current element is included in the solution
			twoUntil(a, cur_elems,  soln_elems, min_dif,no_of_selected, sum, cur_sum, cur_pos + 1);
		}
		// remove current element before returning to the caller
		cur_elems[cur_pos] = false;
	}
	
	public static void tugOfWar(int[] a) {
		int n = a.length;
		boolean[] cur_elems = new boolean[n];
		boolean[] soln_elems = new boolean[n];
		MinDiff min_dif = new MinDiff();
		min_dif.val = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += a[i];
			cur_elems[i] = false;
			soln_elems[i] = false;		
		}
		
		twoUntil(a, cur_elems, soln_elems, min_dif, 0, sum, 0, 0);
		
		// print the solution
		int firstSum = 0;
		System.out.println("the first array: ");
		for (int i = 0; i < n; i++) {
			if (soln_elems[i] == true) {
				System.out.print(a[i] + " ");
				firstSum += a[i];
			}
		}
		System.out.println("\nfirstSum = " + firstSum);
		System.out.println();
		System.out.println("the second array: ");
		for (int i = 0; i < n; i++) {
			if (soln_elems[i] == false) {
				System.out.print(a[i] + " ");
			}
		}
		System.out.println();
		System.out.println("sum = " + sum);
	}
	
	
	public static void test1() {
		int[] a = {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
		tugOfWar(a);
	}
	
	/*
	 * task2
	 * Print Matrix Diagonally
	 * http://www.geeksforgeeks.org/print-matrix-diagonally/
	 */
	
	public static void test2() {
		int[][] a = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16},
				{17,18,19,20}
		};
		printDiagonalOrder(a);
	}
	
	public static void printDiagonalOrder(int[][] a) {
		int rLen = a.length;
		int cLen = a[0].length;
		int totalIndex = rLen + cLen - 2;
		
		for (int curTotal = 0; curTotal <= totalIndex; curTotal++) {
			if (curTotal <= rLen - 1) {
				for (int i = curTotal; i >= 0 ; i --) {
					int j = curTotal - i;
					if (j >= 0 && j < cLen) {
						System.out.print(a[i][j] + " ");
					}
				}
				System.out.println();
			} else {
				for (int i = rLen - 1; i >= 0; i--) {
					int j = curTotal - i;
					if (j >=0 && j < cLen) {
						System.out.print(a[i][j] + " ");
					}
				}
				System.out.println();
			}
		}
	}
	
	/*
	 * task3
	 * Divide and Conquer | Set 3 (Maximum Subarray Sum)
	 * http://www.geeksforgeeks.org/divide-and-conquer-maximum-sum-subarray/
	 */
	
	/*
	 * use divide and conquer
	 */
	public static int maxCrossSum(int[] a, int left, int mid, int right) {
		int sum = 0;
		int leftSum = Integer.MIN_VALUE;
		for (int i = mid; i >= left; i--) {
			sum += a[i];
			if (sum > leftSum) {
				leftSum = sum;
			}
		}
		sum = 0;
		int rightSum = Integer.MIN_VALUE;
		for (int i = mid + 1 ; i <= right; i++) {
			sum += a[i];
			if (sum > rightSum) {
				rightSum = sum;
			}
		}
		return leftSum + rightSum;
	}
	
	public static int maxSubArraySum(int[] a, int left, int right) {
		if (left == right) {
			return a[left];
		}
		int mid = left + (right - left)/2;
		
		// max in the left part
		// max in the right part
		// max in across the left and right part
		return maxOf3(maxSubArraySum(a, left, mid),
				maxSubArraySum(a, mid + 1, right), 
				maxCrossSum(a, left, mid, right));
	}
	public static int maxSubarraySum(int[] a) {
		return maxSubArraySum(a, 0, a.length - 1);
	}
	public static int maxOf3(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}
	public static void test3() {
		int[] a = {2, 3, -8, 5, 7};
		System.out.println(maxSubarraySum(a));
	}
	
	/*
	 * task4
	 * Counting Sort
	 * http://www.geeksforgeeks.org/counting-sort/
	 */
	// n stands for range 0..to n-1
	public static int[] countSort(int[] input, int n) {
		if (input == null || input.length <= 1 ) {
			return input;
		}
		int[] count = new int[n];
		int[] output = new int[input.length];
		// get count num
		for (int i = 0; i < input.length; i++) {
			count[input[i]]++;
		}
		// modify the count so that count[i] is sum of count[0..i]
		for (int i = 0; i < count.length; i++) {
			if (i == 0) {
				count[i] = count[i];
			} else {
				count[i] += count[i-1];
			}
		}
		// from output.length - 1 to 0. this will maintain the sort stable
		for (int i = output.length - 1; i >= 0; i--) {
			// input[i]'s position in output[] is count[input[i]] - 1
			output[count[input[i]] - 1] = input[i];
			count[input[i]] --;
		}
		
		return output;
	}
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	public static void test4() {
//		int[] a = {1, 4, 1, 2, 7, 5, 2};
//		int[] output = countSort(a, 8);
//		printArray(output);
		
		int[] a2 = {14,18,11,13,19,14,12,13,13,13};
		int start = a2[0];
		int end = a2[0];
		for (int i = 0; i < a2.length; i++) {
			start = Math.min(start, a2[i]);
			end = Math.max(end, a2[i]);
		}
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		int[] output2 = countSortRange(a2, start, end);
		printArray(output2);
		Arrays.sort(a2);
		printArray(a2);
		
	}
	
	// range: [start..end]
	public static int[] countSortRange(int[] input, int start, int end) {
		if (input == null ) {
			return input;
		}
		int range = end - start + 1;
	
		int[] count = new int[range];
		int[] output = new int[input.length];
		// get the count
		for (int i = 0; i < input.length; i++) {
			count[input[i] - start] ++;
		}
		
		// get the count index
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i-1];
		}
		printArray(count);
		// get the output
		for (int i = input.length - 1; i>= 0; i--) {
			// input[i]'s index in output is: count[input[i] - start] - 1
			output[count[input[i] - start] - 1] = input[i];
			count[input[i] - start] --;
		}
		return output;
	}
	
	/*
	 * task5
	 * Merge Overlapping Intervals
	 * http://www.geeksforgeeks.org/merging-intervals/
	 */
	
	
	/*
	 * task6
	 * Find the maximum repeating number in O(n) time and O(1) extra space
	 * http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
	 */
	
	/*
	 * task7
	 * Find the maximum repeating number in O(n) time and O(1) extra space
	 * http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
	 */
	
	public static void test7() {
		int[] a = {2, 3, 3, 5, 3, 4, 1, 7};
		int k = 8;
		int max = maxRepeating(a, k);
		System.out.println("max = " + max);
	}
	public static int maxRepeating(int[] a, int k) {
		printArray(a);
		for (int i = 0; i < a.length; i++) {
			a[a[i]%k] += k;
		}
		printArray(a);
		
		// find index of the maximum repeating element
		int max = a[0];
		int result = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
				result = i;
			}
		}
		/*
		 * get the original back
		 * 
		 */
		for (int i = 0; i < a.length; i++) {
			a[i] = a[i]%k;
		}
		return result;
	}
	
	/*
	 * task8
	 * Stock Buy Sell to Maximize Profit
	 * http://www.geeksforgeeks.org/stock-buy-sell/
	 */
	// only buy and sell once. The maximum difference between two elements
	public static int maxProfit1Txn(int[] a) {
		int curMin = a[0];
		int maxProfit = 0;
		for (int i = 1; i < a.length; i++) {
			if (curMin > a[i]) {
				curMin = a[i];
			}
			int profit = a[i] - curMin;
			System.out.println("profit = " + profit);
			System.out.println("curMin = " + curMin);
			maxProfit = Math.max(maxProfit, profit);
		}
		return maxProfit;
	}
	
	public static void test8() {
		int[] a = {100, 180, 260, 310, 40, 535, 695};
		int maxProfit = maxProfit1Txn(a);
		System.out.println("maxProfit = " + maxProfit);
	}
	
	/*
	 * as many as txn 
	 */
	public static int maxProfitManyTxn(int[] a) {
		int maxProfit = 0;
		for (int i = 1; i < a.length; i++) {
			int diff = a[i] - a[i-1];
			if (diff > 0) {
				maxProfit += diff;
			}
		}
		return maxProfit;
	}
	
	/*
	 * only two txn, buy twice and sell twice
	 */
	public static int maxProfit2Txn(int[] a) {
		int n = a.length;
		int[] left = new int[n];  // maxProfit of left side for position i
		int[] right = new int[n]; // maxProfit of right side for position i
		
		left[0] = 0;
		int min = a[0];
		for (int i = 1; i < n; i++) {
			if (min > a[i]) {
				min = a[i];
			}
			left[i] = Math.max(left[i-1], a[i] - min);
		}
		
		right[n-1] = 0;
		int max = a[n-1];
		for (int i = n-2; i >=0 ; i--) {
			if (max < a[i]) {
				max = a[i];
			}
			right[i] = Math.max(right[i + 1], max - a[i]);
		}
		
		int maxProfit = 0;
		for (int i = 0; i < n; i++) {
			maxProfit = Math.max(maxProfit, left[i] + right[i]);
		}
		return maxProfit;
	}
	
	/*
	 * task9
	 * Rearrange positive and negative numbers in O(n) time and O(1) extra space
	 * http://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
	 * 
	 * More question: 
	 * <1> what the number of positive and negative? are they same or not same
	 * <2> if positive >> negative, where to put the remaining positive
	 * <3> if negative >> positive, where to put the remaining negative
	 *
	 * Situation1:
	 * put the remaining number of positive or negative in the remaining of array
	 * (1) separate the array. positive first and negative follows
	 * (2) swap every alternative negative with the next positive number
	 */
	public static int partition(int[] a, int pivot) {
		int start = 0, end = a.length - 1;
		while(start < end) {
			while(start < end && a[start] < 0 ) {
				start ++;
			}
			while(start < end && a[end] > 0) {
				end --;
			}
			if (start == end) {
				break;
			}
			swap(a, start, end);
		}
		// return the first positive element's index, if there exist
		return start;
	}
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void rearrangePositiveAndNegative(int[] a) {
		if (a == null || a.length <= 1) {
			return ;
		}
		int firstPositiveIndex = partition(a, 0);
		// after partition, the first part is negative and the second part is positive
		int neg = 0, pos = firstPositiveIndex;
		while(neg < pos && pos < a.length && a[neg] < 0) {
			swap(a, neg, pos);
			pos ++ ;
			neg += 2;
		}
	}
	
	public static void test9() {
		int[] a = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
//		int[] a = {1,2,3,4,5,6,-7};
//		int[] a = {1,-2,-3,-4,-5};
//		System.out.println(partition(a, 0));
		P4_Array.printArray(a);
		rearrangePositiveAndNegative(a);
		P4_Array.printArray(a);
	}
	
	
	/*
	 * task10
	 * Sort elements by frequency | Set 1
	 * http://www.geeksforgeeks.org/sort-elements-by-frequency/
	 */

	
	/*
	 * task11
	 * Sort elements by frequency | Set 2
	 * http://www.geeksforgeeks.org/sort-elements-by-frequency-set-2/
	 */

	
	/*
	 * task12
	 * Find a peak element
	 * http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
	 * 
	 * corner case
	 * (1) if input array is already sorted in strictly increasing order, the last is the peak
	 * (2) if input array is sorted in strictly decreasing order, the first is the peak
	 * (3) if all elements of input array are same, every element is a peak element 
	 * binary search
	 * if a[mid] > a[mid + 1] && a[mid] < a[mid - 1]  so, a[mid] is the peak
	 * else if a[mid - 1] < a[mid], the peak is in the right side
	 * else // a[mid] < a[mid - 1], the peak is in the left side
	 * 
	 */
	
	public static int findPeak(int[] a, int start, int end, int n) {
		int mid = start + (end - start)/2;
		if ((mid == 0 || a[mid-1] <= a[mid]) && (mid == n - 1 || a[mid + 1] <= a[mid])) {
			return mid;
		} else if (mid > 0 && a[mid - 1] > a[mid]) {
			return findPeak(a, start, mid - 1, n);
		} else {
			return findPeak(a, mid + 1, end, n);
		}
	}
	public static int findPeak2(int[] a, int start, int end, int n) {
		int mid = (start + end)/2;
		if (mid == 0 || mid == n-1) {
			return mid;
		} else if (a[mid - 1] <= a[mid] && a[mid] >= a[mid + 1]) {
			return mid;
		} else if (mid > 0 && a[mid - 1] > a[mid]) {
			return findPeak2(a, start, mid - 1, n);
		} else {
			return findPeak2(a, mid + 1, end, n);
		}
	}
	
	public static int findPeak(int[] a){
		int n = a.length;
		int start = 0, end = n - 1;
//		return findPeak(a, start, end, n);
		return findPeak2(a, start, end, n);
	}
	
	public static void test12() {
//		int[] a1 = {1,2,3,4,5,6,7,8};
//		int index1 = findPeak(a1);
//		System.out.println("index = " + index1 + " value = " + a1[index1]);
//		
//		int[] a2 = {4,5,6,7,8,1,2,3};
//		int index2 = findPeak(a2);
//		System.out.println("index = " + index2 + " value = " + a2[index2]);
		
//		
//		int[] a3 = {8,7,6,5,4,3,2,1};
//		
//		int index3 = findPeak(a3);
//		System.out.println("index = " + index3 + " value = " + a3[index3]);
//		
		int[] a4 = {1,1,1,1,1,1,1,1};
		int index4 = findPeak(a4);
		System.out.println("index = " + index4 + " value = " + a4[index4]);
		
		
	}
	
	
	
	/*
	 * task13
	 * Print all possible combinations of r elements in a given array of size n
	 * http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
	 */
	
	
	
	/*
	 * task14
	 * Given an array of of size n and a number k, find all elements that appear more than n/k times
	 * http://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/
	 * 
	 * e.g
	 * input[] = {3, 1, 2, 2, 1, 2, 3, 3}; and k = 4; n = 8; then the output should be 2,3
	 * 
	 * 1 two loops. pick one element, traverse the whole array to get its count. O(n*n)
	 * 2 Sorting. O(n log n)
	 * 3 O(n*k) solution and O(k - 1 ) space
	 *   3.1 create a temp array of size k - 1 to store elements and their counts. (the output 
	 *       elements are going to be among these k - 1 elements.)
	 *   3.2 Traverse through the input array and update temp[]. (add / remove an element or 
	 *       increase / decrease count) for every traversed elemen. 
	 *       The temp[] stores potential (k - 1) candidates at every step. This step use O(n*k)
	 *   3.3 Iterate the temp[]. if count > n/k, print out the element. 
	 *       Takes O(nk) 
	 *
	 */
	
	public static void test14() {
		int[] a1 = {4, 5, 6, 7, 8, 4, 4};
		int k1 = 3;
		moreThanNdK2(a1, k1);
		
		int[] a2 = {4, 2, 2, 7};
		int k2 = 2;
		moreThanNdK2(a2, k2);

	}
	
	public static class Item{
		public int val;
		public int count;
		public Item() {
			this.val = 0;
			this.count = 0;
		}
		public Item(int v, int c) {
			this.val = v;
			this.count = c;
		}
	}
	public static void moreThanNdK2(int[] a, int k) {
		int n = a.length;
		if (k < 2) {
			return ;
		}
		// step 1: initialize temp[]
		Item[] temp = new Item[k-1];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new Item();
		}
		
		// step2: precess all elements of input array
		for (int i = 0; i < n; i++) {
			int j = 0;
			// 2.1 if a[i] is already present in temp[]
			for (; j < k - 1; j++) {
				if (temp[j].val == a[i]) {
					temp[j].count ++;
					break;
				}
			}
			// 2.2 a[i] is not present in temp[], then place a[i] in the first available
			// position and set count as 1
			if (j == k - 1) {
				int l = 0;
				for (; l < k - 1; l ++) {
					if (temp[l].count == 0) {
						temp[l].val = a[i];
						temp[l].count = 1;
						break;
					}
				}
				// if all the position in the temp[] are filled, then decrease count of 
				// every element by 1
				if (l == k - 1) {
					for (int m = 0; m < temp.length; m++) {
						temp[m].count --;
					}
				}
			}	
		}
		// step 3: check actual counts of protential candidates in temp[]
		for (int i = 0; i < temp.length; i++) {
			int ac = 0;
			for (int j = 0; j < a.length; j++) {
				if (a[j] == temp[i].val) {
					ac ++;
				}
			}
			if (ac > n/k) {
				System.out.println("number:  " + temp[i].val + " count: " + ac);
			}
		}		
	}

	
	/*
	 * task15
	 * Unbounded Binary Search Example (Find the point where a monotonically increasing function becomes positive first time)
	 * http://www.geeksforgeeks.org/find-the-point-where-a-function-becomes-negative/
	 */
	
	
	
	
	
	
	/*
	 * task16
	 * Find the Increasing subsequence of length three with maximum product
	 * http://www.geeksforgeeks.org/increasing-subsequence-of-length-three-with-maximum-product/
	 * 
	 * 
	 * Given a sequence of non-negative integers, 
	 * find the subsequence of length 3 having maximum product 
	 * with the numbers of the subsequence being in ascending order.
	 * 
	 * input: {6, 7, 8, 1, 2, 3, 9, 10}
	 * Output:8,9,10
	 * 
	 * Input: 
	 */
	
	
	
	
	
	
	
	

}

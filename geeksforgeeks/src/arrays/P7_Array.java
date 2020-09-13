package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class P7_Array {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test3();
//		test4();
//		test5();
//		test6();
//		test7();
//		test9();
//		test10();
//		test14();
	}
	/*
	 * 1 Find the two numbers with odd occurrences in an unsorted array
	 * 2 The Celebrity Problem
	 * 3 Dynamic Programming | Set 15 (Longest Bitonic Subsequence)
	 * 4 Find a sorted subsequence of size 3 in linear time !!!
	 * 5 Largest subarray with equal number of 0s and 1s
	 * 6 Dynamic Programming | Set 18 (Partition problem)
	 * 7 Maximum Product Subarray
	 * 8 Find a pair with the given difference
	 * 9 Replace every element with the next greatest
	 * 10 Dynamic Programming | Set 20 (Maximum Length Chain of Pairs)
	 * 11 Find four elements that sum to a given value | Set 1 (n^3 solution)
	 * 12 Find four elements that sum to a given value | Set 2 ( O(n^2Logn) Solution)
	 * 13 Sort a nearly sorted (or K sorted) array
	 * 14 Maximum circular SubArray sum
	 */
	

	/*
	 * task1
	 * Find the two numbers with odd occurrences in an unsorted array
	 * http://www.geeksforgeeks.org/find-the-two-numbers-with-odd-occurences-in-an-unsorted-array/
	 * Given an unsorted array that contains even number of occurrences for all numbers except two numbers. 
	 * Find the two numbers which have odd occurrences in O(n) time complexity and O(1) extra space.
	 * (1) naive method: two loops
	 * (2) use sorting
	 * (3) use hashing. key, count
	 * (4) use XOR
	 */
	
	// XOR
	public static void printTwoElemWithOddCount(int[] a) {
		int xor = 0;
		// get the xor of all elements in a[]
		for(int i = 0; i< a.length; i++) {
			xor ^= a[i];
		}
		
		// now the xor is x^y, there is at least one digit are different, so, find the least significant 1 
		// in xor  using xor & ~(xor - 1)
		int set_bit_no = xor & ~(xor - 1);
		int x = 0, y = 0;
		for (int i = 0; i < a.length; i++) {
			if ((set_bit_no & a[i]) == set_bit_no) {
				x ^= a[i];
			}else {
				y ^= a[i];
			}
		}
		System.out.println("x = " + x + " y= " + y);
	}
	
	public static void test1() {
		int[]  a= {10,20};
		printTwoElemWithOddCount(a);
	}
	
	
	/*
	 * task2
	 * The Celebrity Problem
	 * http://www.geeksforgeeks.org/the-celebrity-problem/
	 * 
	 * If A knows B, then A can’t be celebrity. Discard A, and B may be celebrity.
	 * If A doesn’t know B, then B can’t be celebrity. Discard B, and A may be celebrity.
	 * Repeat above two steps till we left with only one person.
	 * Ensure the remained person is celebrity. (Why do we need this step?)
	 * We can use stack to verity celebrity.
	 * Push all the celebrities into a stack.
	 * Pop off top two persons from the stack, discard one person based on return status of HaveAcquaintance(A, B).
	 * Push the remained person onto stack.
	 * Repeat step 2 and 3 until only one person remains in the stack.
	 * Check the remained person in stack doesn’t have acquaintance with anyone else.
	 * 
	 * 
	 * 
	 * 1. Write code to find celebrity. Don’t use any data structures like graphs, stack, etc… 
	 * you have access to N and HaveAcquaintance(int, int) only.
	 * 2. Implement the algorithm using Queues.
	 *  What is your observation? Compare your solution with 
	 *  Finding Maximum and Minimum in an array and Tournament Tree. 
	 *  What are minimum number of comparisons do we need (optimal number of calls to HaveAcquaintance())?
	 * 
	 */
	
	
	/*
	 * task3
	 * Dynamic Programming | Set 15 (Longest Bitonic Subsequence)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
	 * Given an array arr[0 … n-1] containing n positive integers, a subsequence of arr[] is called Bitonic 
	 * if it is first increasing, then decreasing. 
	 * Write a function that takes an array as argument and 
	 * returns the length of the longest bitonic subsequence.
	 * 
	 * method:
	 * get the Longest Increasing Subsequence ending with a[i], lis[]
	 * 				   Decreasing 			 starting			lds[]
	 * 
	 * the final result is max(lis[i] + lds[j] -1)
	 * 
	 */
	public static int longestBitonicSubsequence(int[] a) {
		int n = a.length;
		int[] lis = new int[n]; // longest increasing subsequence ending with a[i]
		int[] lds = new int[n]; // longest decreasing subsequence starting with a[i]
		
		// calculate the lis from left to right
		lis[0] = 1;
		for (int i = 1; i < lds.length; i++) {
			lis[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && lis[j] + 1 > lis[i]) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		
		
		// calculate the lds from right to left
		lds[n-1] = 1;
		for (int i = n-2; i >= 0; i--) {
			lds[i] = 1;
			for (int j = n-1; j > i; j--) {
				if (a[i] > a[j] && lds[j] + 1 > lds[i]) {
					lds[i] = lds[j] + 1;
				}
			}
		}
		System.out.println(Arrays.toString(lis));
		System.out.println(Arrays.toString(lds));
		
		// calculate the maxLen
		int maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			maxLen = Math.max(maxLen, lis[i] + lds[i] - 1);
		}
		return maxLen;
	}
	public static void test3() {
		int[] a = {1, 11, 2, 10, 4, 5, 2, 1};
		System.out.println(longestBitonicSubsequence(a));
	}
	
	/*
	 * task4
	 * Find a sorted subsequence of size 3 in linear time
	 * http://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
	 * Given an array of n integers, find the 3 elements such that
	 * a[i] < a[j] < a[k] and i < j < k in 0(n) time. If there are multiple such triplets, 
	 * then print any one of them.
	 * 
	 * !!! more detail 
	 * Using auxiliary space
	 * Extension:
	 * http://stackoverflow.com/questions/17654673/find-a-sorted-subsequence-of-size-4-in-an-array-in-linear-time
	 */
	
	public static void find3Sorted(int[] a) {
		int n = a.length;
		int[] smaller = new int[n];
		int[] larger = new int[n];
		
		// get smaller
		int min = 0;
		smaller[0] = -1;
		for (int i = 1; i < n; i++) {
			if (a[i] > a[min]) {
				smaller[i] = min;
			} else {
				//a[i] <= a[min]
				min = i;
				smaller[i] = -1;
			}
		}
		
		//get larger
		int max = n-1;
		larger[n-1] = -1;
		for (int i = n-2; i >= 0; i--) {
			if (a[i] < a[max]) {
				larger[i] = max;
			} else {
				// a[i] >= a[max]
				max = i;
				larger[i] = -1;
			}
		}
		
		// get the number
		for (int i = 0; i < n; i++) {
			if (smaller[i] != -1 && larger[i] != -1) {
				System.out.println(a[smaller[i]] + " " + a[i] + " " + a[larger[i]]);
			}
		}
	}
	public static void test4() {
		int[] a1 = {-4, 2, 8, 3, 1, 5};
//		int[] a2 = {1, 2, 3, 4};
		int[] a3 = {4, 3, 2, 1};
//		find3Sorted(a1);
//		find3Sorted(a2);
//		find3Sorted(a3);
		print3sorted2(a1);
		System.out.println("-----------");
		int[] a2 = {-4, 2, 8, 3, 1, 5};
		int k = 4;
		printExt2(a2,k);
	}
	public static void print3sorted2(int[] a) {
		int n = a.length;
		int[] p1 = new int[n];
		int[] p2 = new int[n];
		// get p1
		int min = -1;
		for (int i = 0; i < n; i++) {
			if (min != -1 && a[i] > a[min]) {
				p1[i] = min;
			} else {
				min = i;
				p1[i] = -1;
			}
		}
		
		// get p2
		min = -1;
		for (int i = 0; i < n; i++) {
			if (min != -1 && a[i] > a[min]) {
				p2[i] = min;
			} else {
				p2[i] = -1;
				if (p1[i] != -1) {
					// update min, p1[i] != -1 means that there is an element smaller than array[i]
					// so, array[i] could be the candidate for the 2nd smallest element for future
					// element in the array. 
					min = i;
				}
			}
		}
		
		printArray(p1);
		printArray(p2);
		for (int i = 0; i < n; i++) {
			if (p2[i] != -1) {
				int firstIndex = p1[p2[i]];
				int secondIndex = p2[i];
				System.out.println(a[firstIndex] + " " + a[secondIndex] + " " + a[i]);		
			}
		}
		
	}
	
	
	/*
	 * extension:
	 * (1) Find a subsequence of size 3 such that arr[i] < arr[j] > arr[k].
	 * (2) Find a sorted subsequence of size 4 in linear time
	 */
	 
	 // find a sorted subsequence of size k in k*n time.
	 // if k == 4, it is 4*n time, which is linear. 
	 /*
	 * Index  0   1  2  3  4  5 
	 * Array: -4  2  8  3  1  5
	 * p1:    -1  0  0  0  0  0      1st smallest index
	 * p2:    -1 -1  1  1 -1  4      2nd smallest index
	 * p3:    -1 -1 -1  -1 -1 3      3rd smallest index
	 * if we want to find 4 sorted subsequence, 
	 * traverse the p3, p3[5] = 3
	 * the indices of result is: p1[p2[p3[5]]] , p2[p3[5]] ,  p3[5] , 5
         *                                 0            1           3      5                         
	 *           the content is:       -4           2           3      5 
	 */
	public static void printExt1(int[] a) {
		int n = a.length;
		int[] leftSmaller = new int[n];
		int[] rightSmaller = new int[n];
		
	}

	public static void printExt2(int[] a, int k) {
		// using 3
		int n = a.length;
		ArrayList<int[]> pList = new ArrayList<int[]>();
		for (int i = 0; i < k - 1; i++) {
			int[] p = new int[n];
			pList.add(p);
		}
		// System.out.println("pList.size() = " + pList.size());
		for (int i = 0; i < pList.size(); i++) {
			int min = -1;
			if (i == 0) {
				int[] temp = pList.get(i);
				for (int j = 0; j < n; j++) {
					if (min != -1 && a[j] > a[min]) {
						temp[j] = min;
					} else {
						temp[j] = -1;
						// update the min
						min = j;
					}
				}
			} else {
				int[] temp = pList.get(i);
				int[] prev = pList.get(i - 1);
				for (int j = 0; j < n; j++) {
					if (min != -1 && a[j] > a[min]) {
						temp[j] = min;
					} else {
						temp[j] = -1;
						if (prev[j] != -1) {
							min = j;
						}
					}
				}
			}
		}
		System.out.println("------------");
		for (int i = 0; i < pList.size(); i++) {
			printArray(pList.get(i));
		}
		System.out.println("============");
		int last = pList.size() - 1;
		int[] temp = pList.get(last);
		int[] revIndex = new int[k];
		int i = 0;
		for (; i < temp.length; i++) {
			if (temp[i] != -1) {
				break;
			}
		}
		for (int j = k - 1; j >= 0; j--) {
			if (j == k - 1) {
				revIndex[j] = i;
			} else {
				revIndex[j] = pList.get(j)[revIndex[j + 1]];
			}
		}
		// System.out.println(revIndex.length);
		printArray(revIndex);

		for (int j = 0; j < revIndex.length; j++) {
			int index = revIndex[j];
			System.out.print(a[index] + " ");
		}
		System.out.println();
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	/*
	 * task5
	 * Largest subarray with equal number of 0s and 1s
	 * http://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
	 * 
	 * Given an array containing only 0s and 1s, find the largest subarray 
	 * which contain equal no of 0s and 1s. Expected time complexity is O(n).
	 * 
	 * Input: arr[] = {1, 0, 1, 1, 1, 0, 0}
	 * Output: 1 to 6 (Starting and Ending indexes of output subarray)
	 * 
	 * Input: arr[] = {1, 1, 1, 1}
	 * Output: No such subarray
	 * 
	 * Input: arr[] = {0, 0, 1, 1, 0}
	 * Output: 0 to 3 Or 1 to 4
	 */
	
	/*
	 * Simple Solution:
	 * consider the 0 as -1, the problem reduces to find the max length of subArray 
	 * whose sum == 0
	 * two loops 
	 */
	public static int largestSubArrayWithEqual0_1(int[] a) {
		int maxLen = -1;
		int startIndex = 0;
		int sum = 0;
		for (int i = 0; i < a.length - 1; i++) {
			sum = (a[i] == 1)? 1: -1;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] == 1) {
					sum += 1;
				} else {
					sum += -1;
				}
				if (sum == 0 && maxLen < j - i + 1) {
					
					maxLen = j - i + 1;
					startIndex = i;
				}
			}
		}
		if (maxLen == -1) {
			System.out.println("No such subarray");
		} else {
			System.out.println("from: " + startIndex + "  end: " + (startIndex + maxLen - 1));
		}
		return maxLen;
	}// time: O(n^2)
	
	/*
	 * OPT
	 * Consider 0 as -1, this problem reduces to get the max length of subarray whose sum == 0
	 * get leftSum[], where leftSum[i] == sum of a[0..i]
	 * Two cases:
	 * (1)Subarray: index starts from 0
	 *    scan the leftSum, find the maximum i where leftSum[i] == 0
	 * (2)Subarray: index doesn't start from 0
	 *    this problem equivalent to finding two indexes i and j, where leftSum[i] == leftSum[j]
	 *    and j - i is max
	 *    To solve this, we can create a hash table with size = max - min + 1;
	 *    	max is the max element in leftSum
	 *    	min is the min element in leftSum
	 *    the idea is to hash the left most occurences of all different values in leftSum[];
	 *    	initialize all values as -1
	 * (3) for the corner case, if all 1s or all 0s, we initialize the maxSize == -1, 
	 *     if maxSize == -1, print no such subArray
	 */
	public static int largestSubArrayWithEqual0_1s_2(int[] a) {
		return -1;
	}
	
	public static void test5() {
		int[] a = {1, 0, 1, 1, 1, 0, 0};
		System.out.println(largestSubArrayWithEqual0_1(a));
	}
	
	/*
	 * task6
	 * Dynamic Programming | Set 18 (Partition problem)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
	 * 
	 * Partition problem is to determine whether a given set can be partitioned into two subsets 
	 * such that the sum of elements in both subsets is same.
	 * 
	 * 1: calculate the sum of the array. if the sum is odd, return false;
	 * 2: if the sum is even, this problem change to find a subset of array with sum equal to sum/2
	 * 
	 * time O(2^n) in worst case
	 */
	public static boolean isSubsetSum(int[] a, int n, int sum) {
		if (sum == 0) {
			return true;
		}
		if (n == 0 && sum != 0) {
			return false;
		}
		// if a[n-1] > sum, ignore it
		if (a[n-1] > sum) {
			return isSubsetSum(a, n - 1, sum);
		}
		/*
		 *  else
		 *  check if sum can be obtained by any of the following
		 *  (a) including the last element
		 *  (b) excluding the last element 
		 */
		return isSubsetSum(a, n - 1, sum) || isSubsetSum(a, n - 1, sum  - a[n-1]);
	}
	
	public static boolean findPartition(int[] a) {
		int n = a.length;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		if (sum %2 == 1) {
			return false;
		} else {
//			return isSubsetSum(a, n, sum/2);
			return isSubsetSumDP(a, sum/2);
		}
	}
	public static void test6() {
		int[] a = {1, 5, 11, 5};
		System.out.println(findPartition(a));
	}
	
	
	// DP
	/*
	 * 
	 * f[i][j] = true if a subset of {arr[0], arr[1], ..arr[j-1]} has sum 
             equal to i, otherwise false
	 * f[i][j] : whether there is a subset of a[0..j - 1] has the sum== i
	 * f[i][j]  = 
	 *   if i >= a[j - 1]
	 *   f[i][j] = f[i][j - 1] || f[i - a[j-1]][j -1]
	 */
	public static boolean isSubsetSumDP(int[] a, int sum) {
		int n = a.length;
		boolean[][] f = new boolean[sum + 1][n + 1];
		//initialize   f[0][j] == true  f[i][0] == false (i >= 1)
		for (int j = 0; j <= n; j++) {
			f[0][j] = true;
		}
		for (int i = 1; i <= sum; i++) {
			f[i][0] = false;
		}
		
		for (int i = 1; i <= sum; i++) {
			for (int j = 1; j <= n; j++) {
				f[i][j] = f[i][j - 1];
				if (i >= a[j - 1]) {
					f[i][j] = f[i][j] || f[i - a[j - 1]][j - 1];
				}
			}
		}
		return f[sum][n];
	}
	
	/*
	 * task7
	 * Maximum Product Subarray
	 * http://www.geeksforgeeks.org/maximum-product-subarray/
	 */
	
	public static int maxSubArrayProduct(int[] a) {
		int max_ending_here = 1;
		int min_ending_here = 1;
	
		int max_so_far = 1;
		/*
		 * max_ending_here is always 1 or positive value ending with a[i]
		 * min_ending_here is always  
		 */
		for (int i = 0; i < a.length; i++) {
			if (a[i] > 0) {
				max_ending_here = max_ending_here * a[i];
				min_ending_here = Math.min(min_ending_here * a[i], 1);
			} else if (a[i] == 0) {
				// this element is 0, the maximum
				max_ending_here = 1;
				min_ending_here = 1;
			} else {
				// a[i] < 0, this element is negative. 
				// the min_ending_here is updated with max_ending_here * a[i]
				// but we need to use min_ending_here to update the max_ending_here.
				// so, first store the min_ending_here. 
				int temp = max_ending_here;
				// update max_ending_here
				max_ending_here = Math.max(min_ending_here * a[i], 1);
				// update min_ending_here
				min_ending_here = temp * a[i];
			}
			if (max_ending_here > max_so_far) {
				max_so_far = max_ending_here;
			}
		}
		return max_so_far;
	}
	public static void test7() {
		int[] a = {1, -2, -3, 0, 7, -8, -2};
		int max = maxSubArrayProduct(a);
		System.out.println("max = " + max);
	}
	
	
	
	
	/*
	 * task8
	 * Find a pair with the given difference
	 * http://www.geeksforgeeks.org/find-a-pair-with-the-given-difference/
	 * 
	 * Given an unsorted array and a number n, 
	 * find if there exists a pair of elements in the array whose difference is n.
	 * 
	 * The same with 2Sum. We can both use (1)hashmap (2) sort the array. 
	 */
	
	public static void test8() {
		
	}
	
	
	/*
	 * task9
	 * Replace every element with the next greatest
	 * http://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/
	 * 
	 * Given an array of integers, replace every element with the next greatest element 
	 * (greatest element on the right side) in the array. 
	 * Since there is no element next to the last element, replace it with -1
	 * e.g
	 * input[] = {16, 17, 4, 3, 5, 2}
	 * output[]= {17, 5 , 5, 5, 2,-1}
	 */
	// start from the right most. 
	public static void replaceWithNextGreatest(int[] a) {
		int rightMax = -1;
		for (int i = a.length - 1; i >= 0; i--) {
			int cur = a[i];
			// update a[i]
			a[i] = rightMax;
			// update rightMax 
			rightMax = Math.max(rightMax, cur);
		}
	}
	public static void test9() {
		int[] a = {16, 17, 4, 3, 5, 2};
		P4_Array.printArray(a);
		replaceWithNextGreatest(a);
		P4_Array.printArray(a);
	}
	
	/*
	 * task10
	 * Dynamic Programming | Set 20 (Maximum Length Chain of Pairs)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain-of-pairs/
	 * 
	 * You are given n pairs of numbers. In every pair, 
	 * the first number is always smaller than the second number. 
	 * A pair (c, d) can follow another pair (a, b) if b < c.
	 * 
	 * variation of LIS (Longest Increasing Subsequence)
	 * 
	 */
	public static class Pair{
		public int a;
		public int b;
		public Pair() {
			this.a = 0;
			this.b = 0;
		}
		public Pair(int x, int y) {
			this.a = x;
			this.b = y;
		}
	}
	public static int maxLengthChainOfPairs(Pair[] pair) {
		Comparator<Pair> pairComp = new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				// TODO Auto-generated method stub
				return o1.a - o2.a;
			}
		};
		Arrays.sort(pair, pairComp);
		
		for (int i = 0; i < pair.length; i++) {
			System.out.println(pair[i].a + "  " + pair[i].b);
		}
		
		int n = pair.length;
		int[] f = new int[n];
		f[0] = 1;
		
		for (int i = 1; i < n; i++) {
			f[i] = 1;
			for (int j = 0; j < i; j++) {
				if (pair[i].a > pair[j].b && f[j] + 1 > f[i]) {
					f[i] = f[j]  + 1;
				}
			}
		}
		for (int i = 0; i < f.length; i++) {
			System.out.print(f[i] + " ");
		}
		System.out.println();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < f.length; i++) {
			max = Math.max(max, f[i]);
		}
		return max;
	}
	
	public static void test10() {
		Pair[] pair = {
				new Pair(5,24), new Pair(39, 60),
				new Pair(15, 28), new Pair(27, 40), new Pair(50, 90)
		}; 
		int lis = maxLengthChainOfPairs(pair);
		System.out.println("lis = " + lis);
	}
	
	
	/*
	 * task11
	 * Find four elements that sum to a given value | Set 1 (n^3 solution)
	 * http://www.geeksforgeeks.org/find-four-numbers-with-sum-equal-to-given-sum/
	 */
	
	
	/*
	 * task12
	 * Find four elements that sum to a given value | Set 2 ( O(n^2Logn) Solution)
	 * http://www.geeksforgeeks.org/find-four-elements-that-sum-to-a-given-value-set-2/
	 */
	
	/*
	 * task13
	 * Sort a nearly sorted (or K sorted) array
	 * http://www.geeksforgeeks.org/nearly-sorted-algorithm/
	 * 
	 * (1) we can use insertion sort
	 * 
	 * (2) use heap sort  !!! review heap sort
	 */
	
	
	/*
	 * task14
	 * Maximum circular SubArray sum
	 * http://www.geeksforgeeks.org/maximum-contiguous-circular-sum/
	 * 
	 * Given n numbers (both +ve and -ve), arranged in a circle, 
	 * find the maximum sum of consecutive number.
	 * e.g
	 * Input: a[] = {8, -8, 9, -9, 10, -11, 12}
	 * Output: 22 (12 + 8 - 8 + 9 - 9 + 10)
	 * 
	 * Input: a[] = {10, -3, -4, 7, 6, 5, -4, -1} 
	 * Output:  23 (7 + 6 + 5 - 4 -1 + 10)
	 * 
	 * Input: a[] = {-1, 40, -14, 7, 6, 5, -4, -1}
	 * Output: 52 (7 + 6 + 5 - 4 - 1 - 1 + 40)
	 * 
	 * Case1: The elements that contribute to the maximum sum are arranged such that no 
	 *        wrapping is there. e.g {-10, 2, -1, 5} {-2, 4, -1, 4, -1}
	 *        In this case, just run the Kadane's algirithm. 
	 *        
	 * Case2: The elements that contribute to the maximum sum are arranged such that the 
	 *        wrapping is there. e.g {10, -12, 11} {12, -5, 8, -8, 11}
	 *        In this case, we change wrapping to non-wrapping. 
	 *        Wrapping of contributing elements implies no wrapping of non contributing elements
	 *        so, find the sum of non contributing elements and substract this sum from 
	 *        the total sum. 
	 *        To find out the non contributing, invert sign of each element and then run 
	 *        Kadan's algorithm. 
	 */
	public static int maxSumNoCircular(int[] a) {
		int sum = 0, maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (sum < 0) {
				sum = 0;
			} 
			sum += a[i];
			if (sum > maxSum) {
				maxSum = sum;
			}
		}
		return maxSum;
	}
	
	public static int maxCircularSum(int[] a) {
		// case 1: get the maximum sum using standard kadane's algorithm
		int max_sum_nocircular = maxSumNoCircular(a);
		
		// case2: now find the maximum sum that include the corner element. 
		int max_wrap = 0;
		for (int i = 0; i < a.length; i++) {
			max_wrap += a[i];
			// invert the array. 
			a[i] = -a[i];
		}
		// max sum with corner elements will be 
		// array sum - {-max SubArray sum of inverted array}
		max_wrap = max_wrap + maxSumNoCircular(a);
		return Math.max(max_sum_nocircular, max_wrap);
	}
	
	public static void test14() {
		int[] a1 = {8, -8, 9, -9, 10, -11, 12};
		int[] a2 = {10, -3, -4, 7, 6, 5, -4, -1};
		int[] a3 = {-1, 40, -14, 7, 6, 5, -4, -1};
		
		System.out.println(maxCircularSum(a1));
		System.out.println(maxCircularSum(a2));
		System.out.println(maxCircularSum(a3));
	}
	
}

package small_yan;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Class1_BinarySearch_LinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2_1();
//		test2_2_2();
		test3();
	}
	
	
	/*
	 * Binary search and linked list
	 */
	
	/*
	 * Binary search
	 * search in a sorted sequence(array, arrayList, vector) for a target O(n log n)
	 * key point for binary search
	 * -- partition the sequence to two halves by "mid"
	 * -- determine the answer should be guaranteed to be in/not in one of the halves
	 * -- there has to be a comparison operation about "mid" element
	 *     ---> compare to target
	 *     ---> compare to left most
	 *     ---> compare to right most
	 *     ---> compare to neighbor
	 *     ---> etc
	 * 
	 * classic 
	 *   -- we can guarantee mid is or is not the answer
	 * 
	 * V1, V2
	 *   Closest/smallest larger than/ largest smaller than/ smallest largest or equals /largest smaller or equals/
	 *   /first occurrence/ last occurrence.
	 *  -- deal with the situation, we can not determine "mid"
	 *  -- guarantee within the while loop left != mid, mid != right, left != right, do not need to deal with too many cornercase
	 *  ---the corner case (left == right or left + 1 == right) moved to pre/post processing
	 * 
	 * V3 
	 *  --we don't even know the right/left boundary of the sequence
	 *  
	 *  e.g 
	 *  given an unsorted integer array, return the position of any local minimum. +infi {1​,​3, 2​,​4, 5} +infi
	 *  Assumptions:
	 *  1. no duplicates
	 *  2. a[i] < a[i+1] && a[i] < a[i­1]
	 *  {1​,​2, 3, 4, 5}, {4, 3, 2, 1​}​
	 *  1). does it exist? → global minimum element has to be local minimum 
	 *  2). Naive Solution → find the global minimum. O(n)
	 * 
	 *  array[mid] : a​rray[mid] < array[mid ­ 1] && array[mid] <array[mid + 1]
	 *  left = 0, right = array.length - 1;
	 *  while(left + 1 < right) {
	 *  	mid = (left + right)/2;
	 *      // compare with neighbor
	 *      if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1])
	 *           return mid;
	 *       else if (a[mid] > a[mid - 1]) {
	 *       	// we should find a local minimum in the left side
	 *       	right = mid;
	 *       } else {
	 *       	// we should find a local minimum in the right side
	 *       	left = mid;
	 *       }
	 *  }
	 *  
	 *  return a[left] < a[right]? left : right;
	 *
	 */
	public static void test1() {
		int[] array = {2,1,3};
		int localMin = task1_localMinPos(array);
		int localMinVal = array[localMin];
		System.out.println("localMin = " + localMin);
		System.out.println("localMinVal = " + localMinVal);
	}
	
	public static int task1_localMinPos(int[] array) {
		if (array ==null || array.length == 0) {
			return -1;
		}
		if (array.length == 1) {
			return 0;
		}
		int left = 0, right = array.length - 1;
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (array[mid] < array[mid - 1] && array[mid] < array[mid + 1]) {
				// compare with neighbors
				return mid;
			} else if (array[mid] > array[mid - 1]) {
				// search in left side
				right = mid;
			} else{
				// search in right side
				left = mid;
			}
		}
		return array[left] < array[right] ? left : right;
	}
	
	
	/*
	 * task2 
	 * int[] array, rotated sorted array, min position. No duplicate elements
	 */
	public static int task2_minPosition_Rotated_NoDup(int[] array) {
		int left = 0, right = array.length - 1;
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (array[mid] < array[right]) {
				// rotated part in the left side, 
				// the minimum should also in the left side
				right = mid;
			} else {
				// in the right side
				left = mid;
			}
		}
		return array[left] < array[right] ? left : right;
	}
	
	/*
	 * task2.1 follow up
	 * with duplicates
	 * 
	 * {3, 3, 3​,​3, 3, 3} 
	 * {3, 1, 3, 3, 3, 3} 
	 * {3, 3, 3, 3, 1, 3}
	 * array[mid] compare to array[right] right­­
	 */
	
	public static void test2_1() {
		int[] array = {3,3,3,3,3,1,3};
		int minPos = task2_minPosition_Rotated_WithDup(array);
		System.out.println("minPos = " + minPos);
		System.out.println("minPosVal = " + array[minPos]);
	}
	
	public static int task2_minPosition_Rotated_WithDup(int[] array) {
		int left = 0, right = array.length - 1;
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (array[mid] < array[right]) {
				// rotated part in the left side, the minimum should also in the left side
				right = mid;
			} else if (array[mid] > array[right]){
				// in the right side
				left = mid;
			} else { // array[mid] == array[right]
				right --;
			}
		}
		return array[left] < array[right] ? left : right;
	}
	
	/*
	 * task2.2.1
	 * Given sorted int array, “rotated then reversed” first increasing. 
	 * find the maximum value.
	 * 1, 2, 3, 4, 5, 6 → 5, 6, 1, 2, 3, 4 → 5, 6, 4, 3, 2 ,1
	 * 
	 * 5, 6, 4, 3, 2, 1
	 * 
	 * array[mid] > array[left] : left = mid; ==> right part  
	 * array[mid] < array[left] : right = mid; ==> left part
	 * 
	 */
	
	
	/*
	 * task2.2.2
	 * !!!this is slightly different with 2.2.1. 
	 * although the right part is reversed, 
	 * it still smaller than than the left part. 
	 * However, 2.2.2 doesn't have this restriction. 
	 * 
	 * Given sorted int array, first increasing then decreasing.
	 * find the maximum value.
	 * 
	 * {1, 3, 7, 6, 4, 2, 0}
	 * mid ­- 1,  mid, mid + 1 decreasing right part , right = mid
	 * mid ­- 1,  mid, mid + 1 increasing left part left = mid;
	 */
	public static void test2_2_2() {
		int[] array = {1,3,5,7,6,4,2,0};
		int maxIndex = task2_2_2_maxValue(array);
		System.out.println("maxIndex = " + maxIndex);
		System.out.println("maxVal = " + array[maxIndex]);
		
	}
	
	public static int task2_2_2_maxValue(int[] array) {
		int left = 0, right = array.length - 1;
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if (array[mid] > array[mid - 1] && array[mid] < array[mid + 1] ) {
				// go to right side
				left = mid;
			} else if (array[mid] < array[mid - 1] && array[mid] > array[mid + 1]) {
				// in right side, to to left side
				right = mid;
			} else {
				return mid;
			}
		}
		return array[left] > array[right] ? left : right;
	} 
	
	/*
	 * task3. 
	 * Missing Number, n sized array contains number from [1, n + 1] except one. 
	 * 
	 * {3,2,4} 1,2,3,4
	 * 1). sum → overflow O(n)
	 * 2). swap to its index. O(n)
	 * 3). hashtable. O(n)
	 * 4). bit xor. [1, n+1] → missing number, all numbers appears twice except one. O(n)
	 * {3,2,4}→ {4,2,3}
	 * 
	 */
	
	// using swap to its index
	// the input array is unsorted
	/* index:   0   1   2
	 * input:   3   2   4
	 * i:       0
	 * a[i]-1:  2
	 * modify:  4   2   3
	 * i:           1
	 * a[i]-1       1
	 *  	0   1   2   3   4
	 *  	1   5   4   2   6
	 *      1   6           5
	 *      1   6   2   4   5
	 *      1   2   6   4   5
	 *       
	 */
	
	public static void test3() {
		int[] array = {1,5,4,2,6};
		int rev = task3_missing(array);
		System.out.println("val = " + rev);
	}
	public static int task3_missing(int[] array) {
		if (array == null || array.length == 0) {
			return 1;
		}
		System.out.println(Arrays.toString(array));
		// swap with its index
		for(int i = 0;i < array.length;) {
			// array[i] - 1 is the index which it belongs to  
			// if index != i  the element is not in its right position
			// index is within bound
			if (array[i] - 1 != i && array[i] - 1 < array.length) {
				// swap the element to its position
				swap(array, i, array[i] - 1);
			} else {
				i ++;
			}
			System.out.println(Arrays.toString(array));
		}
		System.out.println("debug--------");
		System.out.println(Arrays.toString(array));
		// find the result
		for(int i = 0; i < array.length; i ++) {
			if (array[i] != i + 1) {
				return i + 1;
			}
		}
		return array.length + 1;
	}
	
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	// task3.1 follow up: the given array is sorted
	/*
	 * binary search
	 * a[mid] - mid == 2, the missing number is in the left side. right =mid
	 * else 			  the missing number is in the right side left = mid;
	 * 
	 * if (a[left] - left == 2)  return left + 1
	 * if (a[right] - right == 2) return right + 1
	 * return array.length + 1;
	 */
	public static int task3_1_missing(int[] array) {
		if (array == null || array.length == 0) {
			return 1;
		}
		int left = 0, right = array.length - 1;
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (array[mid] == mid  + 2) {
				// the missing number is in the left side
				right = mid;
			} else {
				// the missing number is in the right side
				left = mid;
			}
		}
		if (array[left] == left + 2) {
			return left + 1; 
		}
		if (array[right] == right + 2) {
			return right + 1;
		}
		return array.length + 1;
	
	}
	
	
	/*
	 * task4 
	 * Closes 
	 * / smallest larger than 
	 * / largest smaller than 
	 * / smallest larger or equals
	 * / largest smaller or equals 
	 * / first occurrence 
	 * / last occurrence ........
	 */
	
	
	/*
	 * Derived from task4
	 * task5 
	 * next XX number questions. (fibonacci, prime, ugly, beautiful numbers.. etc)
	 * 
	 * task5.1
	 * given a integer number, find the smallest fibonacci number larger than the given number.
	 * 
	 * 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
	 * 
	 * target = 6, return 8; 
	 * target = 12, return 13; 
	 * target = 25, return 34;
	 * 
	 * 
	 */
	
	/*
	 * task5.2
	 *  given a list of symbols, each symbol has its own weight:
	 *  A: 40 
	 *  B: 30 
	 *  C: 10 
	 *  D: 20
	 *  
	 *  A: 0-40 -> 40
	 *  B: 40-70 -> 30
	 *  C: 70-80 ->10
	 *  D: 80-100 ->20
	 *  
	 *  see class1_SymbolGenerator
	 */
	
	
	
	
	/*
	 * (1) Classic binary search
	 */
	
	/*
	 * (2) int[] array, rotated sorted array, rotation position. 
	 * (no duplicate elements).
	 * 
	 * while (left < right - 1)
	 * a[mid - 1] > a[mid], return a[mid];
	 * a[left] > a[mid] left = mid   // the rotation in the right part
	 *         else     right = mid  // the rotation in the left part
	 *  
	 *  finally, it will stop when left = right -1. 
	 * return a[left] > a[right]? right: left;
	 */
	
	/*
	 * LinkedList
	 * insert()
	 * delete();
	 * find();
	 * kth() from start/end
	 * middle node
	 * merge()
	 * reverse();
	 * 
	 * (a) slow and faster pointer
	 * (b) dummy head - head might be changed
	 * (c) iterative
	 * (d) recursive 
	 */
	
	
	
	
	
	

}

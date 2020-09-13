package small_yan;

import java.util.Arrays;

public class  Class5_Arrays2_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
		test2();
	}
	
	/*
	 * task1
	 * for an int array, swap the elements to make it as: the element on odd index >= the two neighbors on even index.
	 * {1,2,3,4,5,6} --> {1,3,2,6,4,5}
	 * 
	 * for i = 0.. n-1
	 * if i is odd, compare 
	 * 
	 * See task1.1
	 * 
	 */	
	/*
	 * task1.1 
	 * Sort an array in wave form
	 * Given an unsorted array of integers, sort the array into a wave like array. 
	 * An array ‘arr[0..n-1]’ is sorted in wave form if arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] >= …..
	 * http://www.geeksforgeeks.org/sort-array-wave-form-2/
	 * 
	 */
	
	/*
	 * Method 1
	 * Sort the array. swap the adjacent array. 
	 * e.g 
	 * {1,2,3,4,5,6,7}
	 * ==>
	 * {2,1,4,3,6,5,7}
	 * 
	 * Time: O(n log n)
	 */
	

	public static void sortArrayInWaveForm1(int[] A) {
		if (A == null || A.length <= 1) {
			return;
		}
		Arrays.sort(A);
		for (int i = 0; i < A.length-1; i+=2) {
			swap(A, i, i+1);
		}
	}
	public static void swap(int[] A, int x, int y) {
		int temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}
	
	/*
	 * Method2
	 * O(n) 
	 * for every even position, if current <= previous, swap(current, previous)
	 *                          if current <= next,  swap(current, next)
	 * The idea is based on the fact that if we make sure that all even position(at index 0, 2, 4, ..)
	 * elements are greater than its adjacent odd elements, we don't need to worry about the odd position element.                           
	 *                    
	 */
	public static void sortArrayInWaveForm2(int[] A) {
		if (A == null || A.length <= 1) {
			return;
		}
		
		for (int i = 0; i < A.length; i +=2) {
			if (i > 0 && A[i-1] > A[i]) {
				swap(A, i-1, i);
			}
			if (i < A.length-1 && A[i] < A[i+1]) {
				swap(A, i, i+1);
			}
		}
	}
	
	
	/*
	 * task2
	 * for an ​i​nt array, replace each element with the multiplication of all elements other than that element.
	 * e.g {1, 2, 3, 4} → {24, 12, 8, 6}
	 * 
	 * (1) naive: O(n*n)
	 * (2) get the total product of all elements, then divide current element..  <a>Overflow <b> a[i] = 0
	 * (3) left[i] is the left product a[0]*a[1]*a[i-1]
	 *     right[i] is the right product a[i+ 1]* a[i + 1] *..* a[n - 1]
	 *     for every a[i], the product is left[i] * right[i]
	 *     Time: O(n)
	 *     Space: O(2* n)  = O(n) avoid to use divide, avoid for overflow
	 */
	
	public static void test2() {
		int[] array = {1,2,3,4,5};
		task2_replace_element_with_multiplication(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static int[] task2_replace_element_with_multiplication(int[] array) {
		// sanity check
		int n = array.length;
		int[] leftProduct = new int[n];
		int[] rigthProdcut = new int[n];
		for(int i = 0; i < n; i ++) {
			if (i == 0) {
				leftProduct[i] = 1;
				rigthProdcut[n - 1 - i] = 1;
			} else {
				leftProduct[i] = leftProduct[i - 1] * array[i - 1];
				rigthProdcut[n - 1 - i] = rigthProdcut[n - i] * array[n - i];
			}
		}
		
		for(int i = 0; i < n; i ++) {
			if (i == 0) {
				array[i] = rigthProdcut[i];
			} else if (i == n - 1) {
				array[i] = leftProduct[i];
			} else {
				array[i] = leftProduct[i] * rigthProdcut[i];
			}
		}
		return array;
	}
	
	/*
	 * task3
	 * trapping water 1d array
	 * Example: [​0,1,0,2,1,0,1,3,2,1,2,1],​return 6​.​
	 * (1)for each element, we need to know max height in the left side, max height in the right side
	 *    then compare the current height with min(maxLeft,maxRight)
	 * (2)
	 * 
	 */
	
	public static void test3() {
		int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
		int total = maxTrapped(a);
		System.out.println("total = " + total);
	}
	public static int maxTrapped(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}
		int n = array.length;
		int[] leftMax = new int[n];
		int[] rightMax = new int[n];

		leftMax[0] = 0;
		rightMax[n - 1] = 0;

		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], array[i - 1]);
			rightMax[n - 1 - i] = Math.max(rightMax[n - i], array[n - i]);
		}
		
		int total = 0;
		for(int i = 0; i < n; i ++) {
			int diff = Math.min(leftMax[i], rightMax[i]) - array[i];
			if(diff > 0) {
				total += diff;
			}
		}
		return total;
	}
	
	// Time: O(n)
	// Space: O(1)
	public static int maxTrapped2(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int leftMax = array[0];
		int rightMax = array[n - 1];
		int i = 0, j = n - 1;
		int sum = 0;
		while (i <= j) {
			leftMax = Math.max(array[i], leftMax);
			rightMax = Math.max(array[j], rightMax);
			if (leftMax < rightMax) {
				// we leftMax is shorter
				sum += Math.min(leftMax, rightMax) - array[i];
				i ++;
			} else {
				// right is shorter
				sum += Math.min(leftMax, rightMax) - array[j];
				j --;
			}
		}
		return sum;
	}
	
	/*
	 * task3.1
	 * trapping rain water 2­d array
	 * 4 5 6 5 
	 * 3 ​​2 1 ​​7
	 * 8 4 ​2 ​​8
	 * 5 6 3 3
	 * while (heap is not empty) {
	 * min heap maintain the current minimum height. get the min from the heap, DFS/BFS.
	 * insert 4 to the heap.
	 * }
	 */
	
	
	/*
	 * task4
	 * Container with most water
	 * Given an array of non-negative integers, 
	 * each of them representing the height of a board perpendicular to the horizontal line, 
	 * the distance between any two adjacent boards is 1. Consider selecting two boards such that together with the horizontal line they form a container. Find the volume of the largest such container.
	 * Assumptions
	 * The given array is not null and has size of at least 2
	 * Examples
	 * { 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards of height 2, 
	 * the volume of the container is 2 * 4 = 8.
	 *  
	 */
	public static int task4_largest(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int n = array.length;
		int left = 0;
		int right = n - 1;

		while (left < right) {
			int curWater = Math.min(array[left], array[right]) * (right - left);

			if (curWater > max) {
				max = curWater;
			}
			if (array[left] < array[right]) {
				left++;
			} else {
				right--;
			}
		}
		return max;
	}
	
	
	/*
	 * task5
	 * array of integers. exists i, j, k? such that i < j < k && array[i] < array[j] < array[k]
	 *  1 2 3 4 5
	 * 
	 * path1:
	 * for each j, traverse array[0..j - 1], find if there is i that a[i] < a[j] and maintain the smallest 
	 * on the left side of a[j]
	 * path2:
	 * for each j, find if there exist k that a[k] > a[j] and maintain the largest element on the right side of a[j]
	 * 
	 * leftMin[]
	 * rightMax[]
	 * traverse the leftMin[i] and rightMax[i]. if leftMin[i] < array[i] < rightMax[i]
	 * 
	 * 
	 * geeksforgeeks array/P7
	 * Find a sorted subsequence of size 3 in linear time
	 * follow up
	 * find the k sorted sequence in array
	 * refer geeks4geeks  arrays.P7_array.task4
	 */
	public static void task5_find3Sorted(int[] a) {
		int n = a.length;
		int[] leftMinIndex = new int[n];
		int[] rightMaxIndex = new int[n];
		
		int minIndex = 0;
		leftMinIndex[0] = -1;
		for(int i = 1; i < n; i ++) {
			if (a[i] > a[minIndex]) {
				leftMinIndex[i] = minIndex;
			} else {
				// a[i] <= a[minIndex]
				minIndex = i;
				leftMinIndex[i] = -1;
			}
		}
		
		int maxIndex = n - 1;
		rightMaxIndex[n - 1] = -1;
		for(int i = n - 2; i >= 0; i --) {
			if (a[i] < a[maxIndex]) {
				rightMaxIndex[i] = maxIndex;
			} else {
				// a[i] >= a[maxIndex]
				maxIndex = i;
				rightMaxIndex[i] = -1;
			}
		}
		
		for(int i = 0; i < n; i ++) {
			if (leftMinIndex[i] != -1 && rightMaxIndex[i] != -1) {
				System.out.println(a[leftMinIndex[i]] + " " + a[i] + " " + a[rightMaxIndex[i]]) ;
			}
		}
	} 
	
	
	
	
	/*
	 * task5.1
	 * array of integers. how many (i, j, k) triples? such that i < j < k && array[i] < array[j] < array[k]
	 * 
	 * we need to know, for each a[j], how many elements on the left of a[j] which a[i] < a[j]
	 * and how many elements on the right side of a[j] which a[k] > a[j]
	 * find candidates, then, swip candidate's left and right, update counter ???
	 * is there a better way? 
	 */
	public static class Item{
		int index;
		int counter;
		public Item(int i, int c) {
			this.index = i;
			this.counter = c;
		}
	}
	public static int task5_1_number_triples(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		Item[] leftMinIndex = new Item[n];
		Item[] rightMaxIndex = new Item[n];
		
		leftMinIndex[0] = new Item(-1, 0);
		rightMaxIndex[n - 1] = new Item(n, 0);
		
		Item minIndexWrapper = new Item(0, 0);
		for(int i = 1; i < n; i ++) {
			if (array[i] > array[minIndexWrapper.index]) {
				
			}
		}
		return -1;
	}
	
	
	/*
	 * task5.2 triples, ==> four elements, i < j < k < m, a[i] < a[j] < a[k] < a[m]
	 * ==> k elements how to handle. see geeks for geeks.
	 */
	
	
	
	
	
	/*
	 * task5.3
	 * array of integers, find the index pair (i, j) with the largest j ­- i, such that, j > i and a[j] < a[i]
	 * Example, {​1,​​3,​0,​6,​4, 2} 
	 * i = 1, j = 5, j - i = 4
	 * 
	 */
	
	
	
	/*
	 * task5.4
	 * array of integers, find the index pair(i, j) with the largest j - i, such that , i < j and a[i] < a[j]
	 * Example, {1,3,0,6,4,2}
	 * i = 0, j = 5
	 * j - i = 5
	 * 
	 * compare with task5.3
	 */
	
	
	/*
	 * task5.5
	 * array of integers, find the longest subarray, the average of the subarray is < k
	 * (1) a'[i] = a[i] - k  --> sum of the subarray < 0
	 * (2) b'[i] = a'[0] + a'[1] + a'[2] + ... + a'[i]  
	 * (3) reduce to find 5.3. Find the index pair(i,j) with the largest j - i, such that j > i and b'[j] < b[i]
	 * Time: O(n)
	 * Space: O(n)
	 */
	
	
	/*
	 * task5.6
	 * String containing only ‘a’ or ‘b’, for example, “aaabbab”. 
	 * we want to replace some of the ‘a’ with ‘b’ and/or some of the ‘b’ with ‘a’, 
	 * so that the result String has all ‘a’s at the left side and all ‘b’s at right side.
	 * What is the minimum number of replacements?
	 * 
	 * !!!!
	 */
	
	
	
	
	
	
	
	

}


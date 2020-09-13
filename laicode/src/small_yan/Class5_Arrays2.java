package small_yan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


public class  Class5_Arrays2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test3_2d();
//		test5_4();
		test5_3();
	}
	
	/*
	 * task1
	 * for an int array, swap the elements to make it as: 
	 * the element on odd index >= the two neighbors on even index.
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
	 * Follow up 
	 * leetcode 324 
	 * Wiggle Sort II
	 * https://leetcode.com/problems/wiggle-sort-ii/
	 * 
	 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
	 * Example:
	 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
	 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
	 */
	public static void wiggleSort2(int[] nums) {
		// check
		if (nums == null || nums.length <= 1) {
			return ;
		}
		if (nums.length == 2) {
			if (nums[0] > nums[1]) {
				swap(nums, 0, 1);
			}
		}
		int len = nums.length;
		// get median and medianIndex
		getKthLargest(nums, 0, len - 1, (len - 1)/2);
		int median = nums[(len - 1)/2];
		int medianIndex = (len - 1)/2;
		
		int start = 1;
		int end = medianIndex + 1;
		while(start < end ) {
			
		}
		
		
		
	} 
	public static void getKthLargest(int[] nums, int left, int right, int k) {
		int pivotIndex = partition(nums, 0, nums.length - 1);
		if (pivotIndex == k) {
			return ;
		} else if (pivotIndex > k) {
			// k is in 0..pivotIndex - 1.
			getKthLargest(nums, left, pivotIndex - 1, k);
		} else {
			// pivotIndex < k
			// k is in pivotIndex + 1, right 
			getKthLargest(nums, pivotIndex + 1, right, k);
		}
		
	}
	public static int partition(int[] nums, int left, int right) {
		int pivot = nums[right];
		int start = left, end = right - 1;
		while(start <= end) {
			if (nums[start] < pivot) {
				start ++;
			} else if (nums[end] >= pivot) {
				end --;
			} else {
				swap(nums, start, end);
				start ++;
				end --;
			}
		}
		
		swap(nums, start, right);
		return start;
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
				sum += Math.min(leftMax, rightMax) - array[i];
				i ++;
			} else {
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
	 * 
	 * 
	 */
	public static void test3_2d() {
		int[][] matrix = { 
				{ 12, 13, 0, 12 }, 
				{ 13, 4, 13, 12 },
				{ 13, 8, 10, 12 }, 
				{ 12, 13, 12, 12 }, 
				{ 13, 13, 13, 13 } 
		};
		int max = maxTrapWater2D(matrix);
		System.out.println("max = " + max);
	}
	
	public static class Cell implements Comparable<Cell>{
		public int x, y, h;  
		// h means that tha maximum height that this cell can hold.
		// if h > matrix[x][y], we can trap some water on this cell
		// if h <= matrix[x][y], we can NOT trap any water on this cell. 
		public Cell(int xx, int yy, int hh) {
			this.x = xx;
			this.y = yy;
			this.h = hh;  
		}
		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			if (this.h == o.h) {
				return 0;
			}
			return this.h < o.h ? -1 : 1;
		}
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1,1,  0, 0};
	
	public static int maxTrapWater2D(int[][] matrix) {
		// edge check
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		// minHeap
		PriorityQueue<Cell> q = new PriorityQueue<Cell>();
		// use visited
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		
		int rowLen = matrix.length;
		int colLen = matrix[0].length;
		
		// put the edges into queue
		// put the left, right into q
		for(int i = 0; i < rowLen; i ++) {
			Cell c1 = new Cell(i, 0, matrix[i][0]);
			Cell c2 = new Cell(i, colLen - 1, matrix[i][colLen - 1]);
			
			q.offer(c1);
			q.offer(c2);
			
			visited[i][0] = true;
			visited[i][colLen - 1] = true;
		}
		
		// put the up, down bounds into q
		for(int j = 1; j < colLen - 1; j ++) {
			Cell c1 = new Cell(0, j, matrix[0][j]);
			Cell c2 = new Cell(rowLen - 1, j, matrix[rowLen - 1][j]);
			
			q.offer(c1);
			q.offer(c2);
			
			visited[0][j] = true;
			visited[rowLen - 1][j] = true;
		}
		
		int sum = 0;
		
		while(!q.isEmpty()) {
			Cell cur = q.poll();
			for(int i = 0; i < 4; i ++) {
				// neighbor's position
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx >= 0 && nx < rowLen && ny >= 0 && ny < colLen && !visited[nx][ny]) {
					Cell nei = new Cell(nx, ny, Math.max(cur.h, matrix[nx][ny]));
					visited[nx][ny] = true;
					q.offer(nei);
					
					sum = sum + Math.max(0, cur.h - matrix[nx][ny]);	
				}
			}
		}
		
		return sum;
		
	}
	
	
	
	
	
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
	 * 
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
	 * {4,3,5, 2, 1, 3, 2,3}
	 * 
	 * index: 0 1 2 3 4 5 6 7
	 * value: 4,3,5,2,1,3,2,3
	 * 
	 * sort:  
	 * index:  0 1 2 3 4 5 6 7
	 * value:  1 2 2 3 3 3 4 5
	 * oIndex: 4 3 6 1 5 7 0 2
	 * lkupTb: 
	 */
	public static void test5_3() {
		int[] array = {1,3,0,6,4,2};
		int rev1 = task5_3_distance_max_sort(array);
		int rev2 = task5_3_distance_max_linear(array);
		
	}
	
	/*
	 * Time: O(n * log n)
	 */
	public static int task5_3_distance_max_sort(int[] array) {
		// check
		if (array == null || array.length == 0) {
			return -1;
		}
		int len = array.length;
		ArrayList<Element> list = new ArrayList<Class5_Arrays2.Element>();
		for(int i = 0; i < len; i ++) {
			list.add(new Element(array[i], i));
		}
		Comparator<Element> myComp = new Comparator<Class5_Arrays2.Element>() {

			@Override
			public int compare(Element o1, Element o2) {
				// TODO Auto-generated method stub
				if (o1.value == o2.value) {
					return o1.index - o2.index;
				}
				return o1.value > o2.value ? -1 : 1;
			}
		};
		
		// sort the list
		Collections.sort(list, myComp);
		
		for(int i = 0; i < list.size(); i ++) {
			System.out.println(list.get(i).value + " : " + list.get(i).index);
		}
		
		
		// get the lookUpTable
		int[] table = new int[len];
		for(int i = len - 1; i >= 0; i--) {
			if (i == len - 1) {
				table[i] = list.get(i).index;
			} else {
				table[i] = Math.max(list.get(i).index, table[i + 1]);
			}
		}
		int global_max = Integer.MIN_VALUE;
		for(int i = 0; i < len; i ++) {
			int startIdx = list.get(i).index;
			int endIdx = table[i];
			
			if (array[startIdx] > array[endIdx]) {
				global_max = Math.max(global_max, endIdx - startIdx);
			}
		}
		System.out.println("global_max = " + global_max);
		return global_max;
		
	}
	
	/*
	 * O(n)
	 */
	public static int task5_3_distance_max_linear(int[] array) {
		// check 
		if (array == null || array.length == 0) {
			return -1;
		}
		int len = array.length;
		ArrayList<Integer> increase = new ArrayList<Integer>();
		increase.add(0);
		for(int i = 1; i < len; i ++) {
			int lastElemIdx = increase.get(increase.size() - 1);
			if (array[i] > array[lastElemIdx]) {
				increase.add(i);
			}
		}
		
		int startIdx = increase.size() - 1;
		int start = increase.get(startIdx);
		int end = array.length - 1;
		int global_max = Integer.MIN_VALUE;
		while(start >= 0 && end > start) {
			if (array[start] > array[end]) {
				global_max = Math.max(global_max, end - start);
				// we update start
				startIdx --;
				if (startIdx >= 0 && startIdx < increase.size()) {
					// startIdx with bound
					start = increase.get(startIdx);
				} else {
					// startIdx out of bound
					break;
				}
			} else {
				// update end
				end --;
			}
		}
		
		System.out.println("2 : global_max = " + global_max);
		return global_max;
	}
	
	
	
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
	 * http://articles.leetcode.com/2011/05/a-distance-maximizing-problem.html
	 * A Distance Maximizing Problem
	 * Given an array A of integers, find the maximum of j-i subjected to the constraint of A[i] < A[j].
	 */
	// brute force method
	// two loops
	public static int task5_4_distance_max_1(int[] array) {
		// check
		if (array == null || array.length == 0) {
			return -1;
		}
		int global_max = Integer.MIN_VALUE;
		for(int i = 0; i < array.length; i ++) {
			for(int j = i + 1; j < array.length; j ++) {
				if (array[j] > array[i] ) {
					global_max = Math.max(global_max, j - i);
				}
			}
		}
		System.out.println("1: global_max = " + global_max);
		return global_max;
	}
	
	// use sorting
	/*
	 * sort the array according to its value. 
	 * Once sorted, we can fin the maximum distance in O(n) time. 
	 * 
	 * Total: O(n * log n)
	 */
	public static void test5_4() {
		int[] array = {4,3,5,2,1,3,2,3};
		int rev2 = task5_4_distance_max_2(array);
		int rev1 = task5_4_distance_max_1(array);
		int rev3 = task5_4_distance_max_3(array);
	}
	
	public static int task5_4_distance_max_2(int[] array) {
		// check 
		if (array == null || array.length == 0) {
			return -1;
		}
		ArrayList<Element> list = new ArrayList<Element>();
		for(int i = 0; i < array.length; i ++) {
			list.add(new Element(array[i], i));
		}
		// sort the list according element's value
		Collections.sort(list);
		
		// lookUpTable[i] store the rightmost index, its element.value >= curVal
		int[] lookUpTable = new int[array.length];
		
		// get the lookUp table
		for(int i = array.length - 1; i >= 0; i --) {
			if (i == array.length - 1) {
				lookUpTable[i] = list.get(i).index;
			} else {
				lookUpTable[i] = Math.max(lookUpTable[i + 1], list.get(i).index);
			}
		}
		System.out.println(Arrays.toString(lookUpTable));
		
		int global_max = Integer.MIN_VALUE;
		
		for(int i = 0; i < array.length ; i ++) {
			int start = list.get(i).index;
			int end = lookUpTable[i];
			// here, we need to check array[end] > array[start]
			if (array[end] > array[start]) {
				global_max = Math.max(global_max, end - start);
			}
			// here, we can also do some optimization for the duplicates. 
			// finish later. 
		}
		System.out.println("global_max = " + global_max);
		return global_max;
	}
	
	public static class Element implements Comparable<Element>{
		int value;
		int index;
		public Element(int _v, int _i) {
			this.value = _v;
			this.index = _i;
		}
		@Override
		public int compareTo(Element o) {
			// TODO Auto-generated method stub
			if (o.value == this.value) {
				return this.index - o.index;
			}
			return this.value < o.value ? -1 : 1;
		}
	}
	
	
	/*
	 * index: 0 1 2 3 4 5 6 7
	 * value: 4 3 5 2 1 3 2 3
	 * 
	 * Assume that choosing index b as the starting point, the max distance is j - b, where A[j] > A[b]
	 * Now, if we have a and a < b and A[a] is not greater than A[b], then, A[j] > A[a]
	 * we can form a farther distance by choosing "a" as the starting index
	 * Therefore, we cannot choose b as the starting point as this forms a contradiction. 
	 * 
	 * Generally, we want to choose only starting points with no such lines that are shorter to its left side. 
	 * So, from the 0, we compose a no-increasing index array. as the above example, is [0,1,3,4]
	 * 
	 * Once we gather all valid starting points by scanning from left to right, we are able to obtain maximum distance
	 * by scanning backwards. 
	 * 
	 * if ending point is less than the shortest starting point, 
	 * then it will NOT be a valid solution for all other starting point.
	 * 
	 * Therefore, we scan from right to left until we meet the first ending point that satisfies the condition. 
	 * Then we proceed to the next shortest starting point, and continue from the previous ending point. 
	 * 
	 * Using this strategy, we would guarantee that we are able to find the maximum distance in O(n) 
	 * 
	 */
	public static int task5_4_distance_max_3(int[] array) {
		// check
		if (array == null || array.length == 0) {
			return -1;
		}
		ArrayList<Integer> decrease = new ArrayList<Integer>();
		decrease.add(0);
		for(int i = 1; i < array.length; i ++) {
			if (array[i] <= array[decrease.get(decrease.size() - 1)]) {
				decrease.add(i);
			}
		}
		
		
		int startIndex = decrease.size() - 1;
		int start = decrease.get(startIndex);
		int end = array.length - 1;
		int global_max = Integer.MIN_VALUE;
		while(end > start && start >= 0) {
			if (array[end] > array[start]) {
				global_max = Math.max(global_max, end - start);
				// update start
				startIndex --;
				if (startIndex >= 0 && startIndex < decrease.size()) {
					// make sure that startIndex is within bound
					start = decrease.get(startIndex);
				} else {
					// if out of bound, break
					break;
				}
			} else {
				// array[end] <= array[start]
				// update end
				end --;
			}
		}
		System.out.println("3  global_max " + global_max);
		return global_max;
	}
	
	
	
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


package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class P6_Arrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test5();
//		test2();
//		test8();
//		test11();
//		test9();
//		test12();
//		test13();
		test14();
	}
	
	/*
	 * 1 Find the row with maximum number of 1s
	 * 2 Median of two sorted arrays of different sizes
	 * 3 Print unique rows in a given boolean matrix
	 * 4 Shuffle a given array
	 * 5 Count the number of possible triangles
	 * 6 Iterative Quick Sort
	 * 7 Inplace M x N size matrix transpose
	 * 8 Find the number of islands
	 * 9 Construction of Longest Monotonically Increasing Subsequence (N log N)
	 * 10 Find the first circular tour that visits all petrol pumps
	 * 11 Arrange given numbers to form the biggest number
	 * 12 Dynamic Programming | Set 27 (Maximum sum rectangle in a 2D matrix)
	 * 13 Pancake sorting
	 * 14 A Pancake Sorting Problem
	 */
	
	/*
	 * task1
	 * Find the row with maximum number of 1s
	 * http://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
	 * 
	 * 1: simple solution, two loops
	 * 2: binary search, for every array, search the leftMost index of 1
	 * 3: binary search, a better solution. 
	 * 
	 * 4: linear search
	 */
	public static void test1() {
		int[][] a = {
				{0,1,1,1},
				{0,0,1,1},
				{1,1,1,1},
				{0,0,0,0}
		};
		System.out.println("method 1 = " + maxNumOf1s_1(a));
		System.out.println("method 2 = " + maxNumOf1s_2(a));
		System.out.println("method 3 = " + maxNumOf1s_3(a));
		
		System.out.println("----------------");
		int[][] a2 = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		
		System.out.println("method 1 = " + maxNumOf1s_1(a2));
		System.out.println("method 2 = " + maxNumOf1s_2(a2));
		System.out.println("method 3 = " + maxNumOf1s_3(a2));
		
		System.out.println("----------------");
		int[][] a3 = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,1}
		};
		
		System.out.println("method 1 = " + maxNumOf1s_1(a3));
		System.out.println("method 2 = " + maxNumOf1s_2(a3));
		System.out.println("method 3 = " + maxNumOf1s_3(a3));
		System.out.println("----------------");
		
		
	}
	
	public static int maxNumOf1s_1(int[][] a) {
		int maxNum = 0;
		int rLen = a.length;
		
		for (int i = 0; i < rLen; i++) {
			int curLeft = findFirstIndex(a[i], 0, a[i].length - 1);
			int curLen = 0;
			if (curLeft != -1) {
				curLen = a[0].length - curLeft;
				maxNum = Math.max(maxNum, curLen);
			}
		}
		return maxNum;
	} // Time: O(m * log n)   a[m][n] 
	
	// a little bit optimization
	// maintain a leftMostIndex, every time, we only need to search a[0, leftMost - 1]. 
	// if return index != -1, then we update the leftMostIndex.
	// otherwise, continue
	public static int maxNumOf1s_2(int[][] a) {
		int rLen = a.length;
		int cLen = a[0].length;
		int leftMostIndex = cLen; // initialize as cLen
		for (int i = 0; i < rLen; i++) {
			int curLeftIndex = findFirstIndex(a[i], 0, leftMostIndex - 1);
			if (curLeftIndex != -1) {
				leftMostIndex = curLeftIndex;
			}
		}
		return cLen - leftMostIndex;
	}
	
	// OPT  O(m + n)
	public static int maxNumOf1s_3(int[][] a) {
		int rLen = a.length;
		int cLen = a[0].length;
		int leftMostIndex = cLen;
		for (int i = 0; i < rLen; i++) {
			while(leftMostIndex > 0 && a[i][leftMostIndex - 1] == 1) {
				leftMostIndex --;
			}
		}
		return cLen - leftMostIndex;
	}
	
	public static int findFirstIndex(int[] a, int left, int right) {
		if (left > right) {
			return -1;
		}
		int start = left, end = right;
		while( start + 1 < end) {
			int mid = start + (end - start)/2;
			if (a[mid] == 1) {
				end = mid;
			} else if (a[mid] < 1) {
				start = mid;
			} else {
				end = mid;
			}
		}
		if (a[start] == 1) {
			return start;
		} else if (a[end] == 1) {
			return end;
		} else {
			return -1;
		}
	}
	
	/*
	 * task2
	 * Median of two sorted arrays of different sizes
	 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
	 * Median of two sorted arrays of same size
	 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
	 */
	public static void test2() {
		int[] A = {900, 1000};
		int[] B = {5, 8, 10, 20};
		System.out.println(medianOfTwoSortedArray(A, B));
		
	}
	public static double medianOfTwoSortedArray(int[] A, int[] B) {
		int len = A.length + B.length;
		if (len % 2 == 0) {
			return (KthElement(A, 0, B, 0, len/2) + KthElement(A, 0, B, 0, len/2 + 1))/2.0;
		} else {
			return KthElement(A, 0, B, 0, len/2 + 1);
		}
	} 
	
	public static int KthElement(int[] A, int A_start, int[] B, int B_start, int k) {
		//if A_start out of bound
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
			// A[A_start.. A_start + k/2 - 1] must be smaller than the kth element
			// so we can delete A[A_start.. A_start + k/2 - 1] and update k
			return KthElement(A, A_start + k/2, B, B_start, k - k/2);
		} else {
			// A_key >= B_key
			// B[B_start .. B_start + k/2 - 1] must smaller than the kth element
			// we can delete B[B_start .. B_start + k/2 - 1]
			return KthElement(A, A_start, B, B_start + k/2, k - k/2);
		}
	}
	
	/*
	 * task3
	 * Print unique rows in a given boolean matrix
	 * http://www.geeksforgeeks.org/print-unique-rows/
	 * 
	 * !!! using TRIE structure to optimize
	 */
	public static void test3() {
		
	}
	
	
	/*
	 * task4
	 * Shuffle a given array
	 * http://www.geeksforgeeks.org/shuffle-a-given-array/
	 */
	public static void test4() {
		
	}
	
	
	/*
	 * task5 
	 * Count the number of possible triangles
	 * http://www.geeksforgeeks.org/find-number-of-triangles-possible/
	 * Input: unsorted array
	 * Output: num of triangle can using 3 elements in the array
	 * 
	 * As another example, consider the array 
	 * {10, 21, 22, 100, 101, 200, 300}.
	 * There can be 6 possible triangles: 
	 * {10, 21, 22}, {21, 100, 101}, {22,100, 101}, 
	 * {10, 100, 101}, {100, 101, 200} and {101, 200, 300}
	 * 
	 * This problem is like 3Sum. 
	 * a1, a2, a3;  suppose a3 is the largest one. 
	 * a1 + a2 > a3 ==> these three elements can form a triangle
	 * 
	 * brute force: three loops; Time: O(n^3)
	 * two pointers: sort the array.  Time: O(n^2)
	 */
	public static void test5() {
		int[] a = {1,2,3,4,5};
		System.out.println(NumOfTriangle(a));
	}
	public static int NumOfTriangle(int[] a) {
		if (a == null || a.length < 3) {
			return -1;
		}
		Arrays.sort(a);
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			int k = i + 2;
			for (int j = i + 1; j < a.length; j++) {
				while(k < a.length && a[i] + a[j] > a[k]) {
					k ++;
				}
				count += k - 1 - j;
			}
		}
		return count;
	}
	/*
	 * Time: O(n^2)
	 * Analysis: for every i, k's loop only execute once. 
	 * so, the time is O(n*n)
	 * if a[i] + a[j] > a[k], so, for a[j + 1], we can directly get a[i] + a[j + 1] > a[k]
	 * since a[j + 1] >= a[j]. a[] is already sorted in nodescending order. 
	 */
	
	/*
	 * task6
	 * Iterative Quick Sort
	 * http://www.geeksforgeeks.org/iterative-quick-sort/
	 * 
	 */
	public static void test6() {
		
	}
	
	/*
	 * task7
	 * Inplace M x N size matrix transpose
	 * http://www.geeksforgeeks.org/inplace-m-x-n-size-matrix-transpose/
	 * 
	 * !!!
	 */
	
	public static void test7() {
		
	}
	
	/*
	 * task8
	 * Find the number of islands
	 * http://www.geeksforgeeks.org/find-number-of-islands/
	 * 
	 * This is an variation of the standard problem: 
	 * “Counting number of connected components in a undirected graph”.
	 *
	 * e.g
	 * {1, 1, 0, 0, 0},
	 * {0, 1, 0, 0, 1},
	 * {1, 0, 0, 1, 1},
	 * {0, 0, 0, 0, 0},
	 * {1, 0, 1, 0, 1}
	 * there are 5 islands in the above 2D matrix
	 * 
	 * we can use dfs to visit the 1. also, we keep track of visited 1s so that it won't 
	 * be visited again. 
	 */
	
	public static boolean isSafe(int[][] matrix, int row, int col, boolean[][] visited) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		return (row >= 0) && (row < rLen) &&
				(col >= 0) && (col < cLen) && 
				(matrix[row][col] == 1) && (visited[row][col] == false);
	}
	public static int[] rowNbr = {-1, -1, -1,  0, 0, 1, 1, 1};
	public static int[] colNbr = {-1, 0,   1, -1, 1,-1, 0, 1};
	
	public static void  DFS(int[][] matrix, int row, int col, boolean[][] visited) {
		visited[row][col] = true;
		for (int k = 0; k < 8; k++) {
			int row_new = row + rowNbr[k];
			int col_new = col + colNbr[k];
			if (isSafe(matrix, row_new, col_new, visited)) {
				DFS(matrix, row_new, col_new, visited);
			}
		}
	}
	
	// the main function
	public static int countIslands(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		int count = 0;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (matrix[i][j] == 1 && visited[i][j] == false) {
					DFS(matrix, i, j, visited);
					count ++;
				}
			}
		}
		return count;
	}
	
	public static void test8() {
		int[][] matrix = {
				 {1, 1, 0, 0, 0},
                 {0, 1, 0, 0, 1},
                 {1, 0, 0, 1, 1},
                 {0, 0, 0, 0, 0},
                 {1, 0, 1, 0, 1}
		};
		int count = countIslands(matrix);
		System.out.println("count = " + count);
	}
	
	/*
	 * task9
	 * Construction of Longest Monotonically Increasing Subsequence (N log N)
	 * http://www.geeksforgeeks.org/construction-of-longest-monotonically-increasing-subsequence-n-log-n/
	 *
	 * !!! review later
	 */
	
	/*
	 * task10
	 * Find the first circular tour that visits all petrol pumps
	 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
	 * 
	 * we can use a queue to store the current tour. 
	 * we first enqueue first petrol pump to the queue, we keep enqueuing petrol pumps 
	 * until we eighter complete the tour or current amount of petrol become negative. 
	 * if the cur_petrol is negative, then we dequeue petrol pumps until the current 
	 * amount becomes positive or queue become empty. 
	 * 
	 * we can use the array itself as queue. we maintain two index variables start and end
	 * that represent front and rear of the queue. 
	 */
	public static class PetroPump{
		public int petrol;
		public int distance;
		public PetroPump(int x, int y) {
			this.petrol = x;
			this.distance = y;
		}
	}
	public static int theTour(PetroPump[] arr) {
		int start = 0, end = 1;
		int n = arr.length;
		int cur_petrol = arr[start].petrol - arr[start].distance;
		
		// run a loop while all petropump are not visited
		// and we have reached first petrol pump again with 0 or more petrol
		while(end != start || cur_petrol < 0) {
			
			// if cur_petrol < 0 and start != end, start hasn't reach end
			while(cur_petrol < 0 && start != end) {
				// remove the starting petrol pump. change start
				cur_petrol -= (arr[start].petrol - arr[start].distance);
				start = (start + 1) % n;
				if (start == 0) {
					return -1;
				}
			}
			cur_petrol += arr[end].petrol - arr[end].distance;
			end = (end + 1) % n;
		}
		return start;
	}
	
	
	/*
	 * task11
	 * Arrange given numbers to form the biggest number
	 * http://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
	 * 
	 * 
	 * The idea is to use any comparison based sorting algorithm. 
	 * In the used sorting algorithm, instead of using the default comparison,
	 * write a comparison function myCompare() and use it to sort numbers. 
	 * Given two numbers X and Y, how should myCompare() decide which number to put first 
	 * – we compare two numbers XY (Y appended at the end of X) and YX (X appended at the end of Y).
	 * 
	 * If XY is larger, then X should come before Y in output, else Y should come before. 
	 * For example, let X and Y be 542 and 60. To compare X and Y, we compare 54260 and 60542. 
	 * Since 60542 is greater than 54260, we put Y first.
	 */
	public static void printLargest(ArrayList<String> arr) {
		Comparator<String> myComp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				String o1o2 = o1 + o2;
				String o2o1 = o2 + o1;
				// !!! here, in reverse order
				return o2o1.compareTo(o1o2);
			}
		};
				
		System.out.println();
		Collections.sort(arr, myComp);
		
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i) + " ");
		}
	}
	public static void test11() {
		String[] str = {
				"54","546", "548", "60"
		};
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			arr.add(str[i]);
		}
		printLargest(arr);	
	}
	
	/*
	 * task12
	 * Dynamic Programming | Set 27 (Maximum sum rectangle in a 2D matrix)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
	 */
	public static class Start{
		public int val;
		public Start() {
			this.val = 0;
		}
		public Start(int v) {
			this.val = v;
		}
	}
	
	public static class Finish{
		public int val; 
		public Finish() {
			this.val = 0;
		}
		public Finish(int v) {
			this.val = v;
		}
	}
	public static void test12() {
		int[][] matrix = {
				{ 1,  2, -1, -4, -20},
                {-8, -3,  4,  2,  1},
                { 3,  8, 10,  1,  3},
                {-4, -1,  1,  7, -6}
         };
		int max = findMaxSum(matrix);
		System.out.println("max = " + max);
	}
	public static int kadande(int[] a, Start start, Finish finish) {
		int sum = 0, maxSum = Integer.MIN_VALUE;
		
		finish.val = -1;
		int local_start = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum < 0) {
				sum = 0;
				local_start = i + 1;
			} else {
				if (sum > maxSum) {
					maxSum = sum;
					start.val = local_start;
					finish.val = i;
				}
			}
			
		}
		if (finish.val != -1) {
			return maxSum;
		}
		
		maxSum = a[0];
		start.val = 0;
		finish.val = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] > maxSum) {
				maxSum = a[i];
				start.val = i;
				finish.val = i;
			}
		}
		return maxSum;
	}
	
	public static int findMaxSum(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int maxSum = Integer.MIN_VALUE;
		Start start = new Start(-1);
		Finish finish = new Finish(-1);
		int sum = 0;
		int final_left = 0, final_right = 0, final_start = 0, final_finish = 0;
		for (int left = 0; left < cLen; left++) {
			// initialize the temp[] 
			int[] temp = new int[rLen];
			for (int right = left; right < cLen; right++) {
				// 
				for (int i = 0; i < rLen; i++) {
					temp[i] += matrix[i][right];
				}
				
				// find the maximum SubArray in temp[]. 
				// the kadande function also find the start and finish value of the 
				// maximum SubArray. 
				sum = kadande(temp, start, finish);
				
				// compare sum with maximum sum so far. If sum > maxSum, update sum 
				// and finia_left, final_right, final_start, final_finish. 
				if (sum > maxSum) {
					maxSum = sum;
					final_left = left;
					final_right = right;
					final_start = start.val;
					final_finish = finish.val;
				}
				
			}
		}
		System.out.println("final_start = " + final_start);
		System.out.println("final_left = " + final_left);
		System.out.println("final_finish = " + final_finish);
		System.out.println("final_right = " + final_right);
		
		return maxSum;
	}
	
	
	
	
	
	/*
	 * task13
	 * Pancake sorting
	 * http://www.geeksforgeeks.org/pancake-sorting/
	 * 
	 * Given an an unsorted array, sort the given array. 
	 * You are allowed to do only following operation on array.
	 * flip(arr, i): Reverse array from 0 to i
	 * 
	 * algorithm:
	 * a[] , size = n
	 * 1 Start from the current size equal to n. reduce the current size by 1 while its greater 
	 *   than 1. Let current size is cur_size. 
	 *   (1) find the index of maximum element in arr[0..cur_size - 1]. let the index be max_index
	 *   (2) flip(a, max_index).   ==> put the max element to the front of the array
	 *   (3) flip(a, cur_size - 1) ==> put the max element to the end of array.  
	 */
	
	public static int findMax(int[] a, int size) {
		int maxIndex = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			if (a[i] > max) {
				max = a[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public static void pancakeSort(int[] a) {
		int n = a.length;
		for(int cur_size = n; cur_size > 0; cur_size --) {
			int maxIndex = findMax(a, cur_size);
			if (maxIndex != cur_size - 1) {
				flip(a, maxIndex);
				flip(a, cur_size - 1);
			}
		}
	}
	public static void test13() {
		int[] a = {23, 10, 20, 11, 12, 6, 7};
		P4_Array.printArray(a);
		pancakeSort(a);
		P4_Array.printArray(a);
	}
	
	public static void flip(int[] a, int index) {
		int start = 0, end = index;
		while( start < end) {
			int temp = a[start];
			a[start] = a[end];
			a[end] = temp;
			start ++;
			end --;
		}
	}
	
	
	/*
	 * task14
	 * A Pancake Sorting Problem
	 * http://www.geeksforgeeks.org/a-pancake-sorting-question/
	 */
	
	// find the index of the first larger element in a[0..size- 1]
	public static int ceilSearch(int[] a, int lastIndex, int target) {
		int start = 0, end = lastIndex;
		if (target <= a[start]) {
			return 0;
		}
		if (a[end] <= target) {
			return -1;
		}
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
			return start + 1;
		} else if (a[end] == target) {
			return end + 1;
		} else {
			return end;
		}
	}
	
	public static void test14() {
		int[] a = {1, 2, 8, 10,10, 10, 12, 12};
		int target = 10;
		int size = a.length - 1;
		int index = ceilSearch(a, size, target);
		System.out.println("index = " + index);
//		System.out.println("a[index] = " + a[index]);
		
		int[] a2 = {18, 40, 35, 12, 30, 35, 20, 6, 90, 80};
		P4_Array.printArray(a2);
		insertSort(a2);
		P4_Array.printArray(a2);
		
	}
	
	public static void insertSort(int[] a) {
		int size = a.length;
		for (int i = 1; i < size; i++) {
			int j = ceilSearch(a, i - 1, a[i]);
			if (j != -1) {
				/*
				 *  1 5 7 9 10 8  i points 8  j should points 9
				 *  
				 */
				flip(a, j - 1); // 7 5 1 9 1 8
				flip(a, i - 1); // 10 9 1 5 7 8
				flip(a, i);    // 8 7 5 1 9 10
				flip(a, j);    // 1 5 7 8 9 10
			}
		}
	}

}

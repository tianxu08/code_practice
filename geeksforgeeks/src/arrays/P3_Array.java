package arrays;

import java.util.Arrays;

public class P3_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();
		// test5();
		// test6();
		// test8();
		// test9();
//		test11();
//		test12();
		test13();
	}
	
	
	
	/*
	 * 1: Find the number of zeroes Given an array of 1s and 0s which has all 1s first followed by all 0s. 
	 *        Find the number of 0s. Count the number of zeroes in the given array.
	 * 2: Kth smallest element in a row-wise and column-wise sorted 2D array
	 * 3: Bucket Sort
	 * 4: Divide and Conquer | Set 6 (Search in a Row-wise and Column-wise Sorted 2D Array)
	 * 5: Remove minimum elements from either side such that 2*min becomes more than max
	 * 6: Smallest subarray with sum greater than a given value
	 * 7: Create a matrix with alternating rectangles of O and X
	 * 8: Find k closest elements to a given value
	 * 9:  Count number of binary strings without consecutive 1’s
	 * 10: Find next greater number with same set of digits
	 * 11: Maximum Sum Path in Two Arrays
	 * 12: Search in an almost sorted array
	 * 13: Sort an array according to the order defined by another array 
	 */

	/*
	 * task1 
	 * http://www.geeksforgeeks.org/find-number-zeroes/ 
	 * Find the number of zeroes Given an array of 1s and 0s which has all 1s first followed by all
	 * 0s. Find the number of 0s. Count the number of zeroes in the given array.
	 */
	// binary search,find the first occurence of 0 in the array
	public static int numOfZeros(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int start = 0, end = a.length - 1;
		int firstIndex = -1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (a[mid] == 0) {
				end = mid;
			} else if (a[mid] > 0) {
				// left side is 1
				start = mid;
			}
		}
		if (a[start] == 0) {
			firstIndex = start;
		} else if (a[end] == 0) {
			firstIndex = end;
		} else {
			firstIndex = -1;
		}
		if (firstIndex == -1) {
			return 0;
		} else {
			return a.length - firstIndex;
		}
	}

	public static void test1() {
		int[] a = { 1, 1, 1, 1, 1 };
		int[] a1 = { 1, 1, 1, 0, 0 };
		int[] a2 = { 0, 0, 0, 0, 0 };
		System.out.println(numOfZeros(a));
		System.out.println(numOfZeros(a1));
		System.out.println(numOfZeros(a2));
	}

	/*
	 * task2 
	 * Kth smallest element in a row-wise and column-wise sorted 2D array
	 * | Set 1 using heap
	 * http://www.geeksforgeeks.org/kth-smallest-element-in-a-
	 * row-wise-and-column-wise-sorted-2d-array-set-1/
	 */

	/*
	 * task3 Bucket Sort
	 */

	/*
	 * task4 
	 * Divide and Conquer | Set 6 (Search in a Row-wise and Column-wise Sorted 2D Array)
	 * http://www.geeksforgeeks.org/divide-conquer-set-6-search-row-wise-column-wise-sorted-2d-array/
	 */

	/*
	 * task5 
	 * Remove minimum elements from either side such that 2*min becomes more than max
	 * http://www.geeksforgeeks.org/remove-minimum-elements-either-side-2min-max/
	 * 
	 * Given an unsorted array, trim the array such that twice of minimum is
	 * greater than maximum in the trimmed array. Elements should be removed
	 * either end of the array.
	 * 
	 * e.g arr[] = {4, 5, 100, 9, 10, 11, 12, 15, 200} Output: 4 We need to
	 * remove 4 elements (4, 5, 100, 200) so that 2*min becomes more than max.
	 */
	public static int min(int[] a, int left, int right) {
		int min = a[left];
		for (int i = left; i <= right; i++) {
			min = Math.min(min, a[i]);
		}
		return min;
	}

	public static int max(int[] a, int left, int right) {
		int max = a[left];
		for (int i = left; i <= right; i++) {
			max = Math.max(max, a[i]);
		}
		return max;
	}

	public static int minRemove(int[] a, int left, int right) {
		int min = min(a, left, right);
		int max = max(a, left, right);

		if (min * 2 > max) {
			return 0;
		}
		// otherwise
		return Math.min(minRemove(a, left + 1, right),
				minRemove(a, left, right - 1)) + 1;
	}

	// Time: T(n) = 2 * T(n- 1) + O(n)
	// O(2^n * n)

	public static void test5() {
		int[] a = { 4, 5, 100, 9, 10, 11, 12, 15, 200 };
		System.out.println(minRemove(a, 0, a.length - 1));
		System.out.println(minRemoveDP(a));
		System.out.println(minRemoveOPT(a));

	}

	/*
	 * OPT: DP
	 */
	public static int minRemoveDP(int[] a) {
		int n = a.length;
		int[][] f = new int[n][n];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int gap = 0; gap < n; gap++) {
			for (int i = 0, j = gap; i < n && j < n; i++, j++) {
				min = min(a, i, j);
				max = max(a, i, j);
				if (2 * min > max) {
					f[i][j] = 0;
				} else {
					f[i][j] = Math.min(f[i][j - 1], f[i + 1][j]) + 1;
				}
			}
		}
		return f[0][n - 1];
	}

	// Time: O(n^3)

	/*
	 * O(n^2) solution two nested loops. the outer loop choose a starting point
	 * and the inner loop chooses ending point for the current starting point.
	 * keep track of the longest subarray with the given property.
	 */
	public static int minRemoveOPT(int[] a) {
		int longest_start = -1, longest_end = 0;
		int n = a.length;
		for (int start = 0; start < n; start++) {
			int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			for (int end = start; end < n; end++) {
				int val = a[end];
				if (val < min) {
					min = val;
				}
				if (val > max) {
					max = val;
				}

				// if the property is violated, then no point to continue for
				// a bigger array
				if (2 * min <= max) {
					break;
				}

				// update the longest_start and longest_end
				if (end - start > longest_end - longest_start
						|| longest_start == -1) {
					longest_start = start;
					longest_end = end;
				}
			}
		}
		System.out.println("start = " + longest_start);
		System.out.println("end = " + longest_end);
		// if not even a single element follow the property, then return n
		if (longest_start == -1) {
			System.out.println("line 179");
			return n;
		}
		// return the deleting no of elements
		return n - (longest_end - longest_start + 1);
	}

	/*
	 * task6 Smallest subarray with sum greater than a given value
	 * http://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
	 * 
	 * share the same idea with
	 * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
	 * 
	 * first find a reasonable solution then, trim the solution, to make sure
	 * that the solution is minimum.
	 */
	public static void test6() {
		int[] a = { 1, 4, 45, 6, 10, 19 };
		int sum = 51;
		System.out.println(minWithSumGreaterThan(a, sum));
	}

	public static int minWithSumGreaterThan(int[] a, int sum) {
		int n = a.length;
		int cur_sum = 0;
		int min_len = Integer.MAX_VALUE;
		int min_start = -1;
		int start = 0, end = 0;
		while (end < n) {
			// keep adding a[end] to cur_sum if cur_sum <= sum
			while (cur_sum <= sum && end < n) {
				cur_sum += a[end];
				end++;
			}

			// if cur_sum > sum, keep remove the a[start]
			while (cur_sum > sum && start < n) {
				if (end - start < min_len) {
					min_len = end - start;
					min_start = start;
				}
				cur_sum -= a[start];
				start++;
			}
		}
		System.out.println("min_start = " + min_start);
		for (int i = 0; i < min_len; i++) {
			System.out.print(a[i + min_start] + " ");
		}
		System.out.println();
		return min_len;
	}

	/*
	 * task7 Create a matrix with alternating rectangles of O and X
	 * http://www.geeksforgeeks.org/create-a-matrix-with-alternating-rectangles-of-0-and-x/
	 * 
	 * !!!
	 */

	/*
	 * task8 
	 * Find k closest elements to a given value
	 * http://www.geeksforgeeks.org/find-k-closest-elements-given-value/ 
	 * Given a sorted array arr[] and a value X, find the k closest elements to X in arr[].
	 * 
	 * simple solution: 1 start from the first element and search for the
	 * crossover point, the point before which element are smaller or equal to X
	 * and after which are greater. O(n) 2 Once we find the crossover point, we
	 * can compare elements on both sides of crossover point to print k closest
	 * elements. This step takes O(k)
	 * 
	 * Total: O(n) Binary search 1 we can use binary search for the crossover
	 * elments. 2 the same with above. Time: O(log n + k)
	 * 
	 * assume that all elements are distinct
	 */
	public static int findCrossOverIndex(int[] a, int X) {
		int start = 0, end = a.length - 1;
		if (a[end] <= X) {
			// X is greater than all
			return end;
		}
		if (a[start] > X) {
			// X is smaller than all
			return start;
		}
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (a[mid] <= X && a[mid + 1] > X) {
				return mid;
			} else if (a[mid] < X) {
				start = mid;
			} else {
				end = mid;
			}
		}
		if (a[start] <= X) {
			return start;
		} else {
			return end;
		}
	}

	public static void printKClosest(int[] a, int X, int k) {
		if (a == null || a.length < k) {
			return;
		}
		int n = a.length;
		int crossOverIndex = findCrossOverIndex(a, X);
		int i = crossOverIndex, j = crossOverIndex + 1;
		// if the crossOverIndex's value == X, left the i --;
		if (a[crossOverIndex] == X) {
			i--;
		}
		int count = 0;
		while (i >= 0 && j < n && count < k) {
			if (Math.abs(a[i] - X) < Math.abs(a[j] - X)) {
				System.out.print(a[i] + " ");
				i--;
				count++;
			} else {
				System.out.print(a[j] + " ");
				j++;
				count++;
			}
		}
		while (i >= 0 && count < k) {
			System.out.print(a[i] + " ");
			i--;
		}
		while (j < n && count < k) {
			System.out.print(a[i] + " ");
			j++;
		}
	}

	public static void test8() {
		int[] a = { 12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56 };
		int X = 35;
		int k = 4;
		printKClosest(a, X, k);
		// int crossoverIndex = findCrossOverIndex(a, X);
		// System.out.println(crossoverIndex);
		// System.out.println(a[crossoverIndex]);
		// System.out.println("----------------");
		// int[] a1 = {1,2,3,4,5,6};
		// int X1 = 0;
		// System.out.println(findCrossOverIndex(a1, X1));
	}

	/*
	 * Follow up if there is duplicates, then we find the first larger element
	 * of X in the array.
	 */

	/*
	 * task9 
	 * Count number of binary strings without consecutive 1’s
	 * http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
	 */
	public static void test9() {
		int n = 2;
		System.out.println(numOfBinaryStringWithoutConsec1s(n));
		int n1 = 3;
		System.out.println(numOfBinaryStringWithoutConsec1s(n1));
	}

	// n is the length of string
	// a[i] length of i, ending with 0, no consecutive 1s
	// b[i] length of i, ending with 1, no consecutive 1s
	// a[i] = a[i-1] + b[i-1] we can append 0 to a[i-1] and b[i-1]
	// b[i] = a[i-1] we can only append 1 to a[i-1]
	public static int numOfBinaryStringWithoutConsec1s(int n) {
		int[] a = new int[n + 1]; // ending with 0
		int[] b = new int[n + 1]; // ending with 1
		a[0] = 0;
		a[1] = 1;
		b[0] = 0;
		b[1] = 1;
		for (int i = 2; i <= n; i++) {
			b[i] = a[i - 1];
			a[i] = a[i - 1] + b[i - 1];
		}
		return a[n] + b[n];
	}

	/*
	 * task10 
	 * Find next greater number with same set of digits
	 * 
	 * http://www.geeksforgeeks.org/find-next-greater-number-set-digits/
	 * 
	 * next permutation.
	 */

	/*
	 * task11 
	 * Maximum Sum Path in Two Arrays
	 * http://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/
	 * 
	 * 1 initialize result = 0; 
	 * sum1 for a1[], 
	 * sum2 for a2[] 
	 * these sums are between two common points. so, every time, after we met two common
	 * points, we set sum1 = 0 , sum2 = 0
	 *  
	 * 2 run loops for both arrays. i for a1[], j for a2[] 
	 *   2.1 if a1[i] < a2[j] sum1 += a1[i] i ++; 
	 *   2.2 if a1[i] > a2[j] sum2 += a2[j] j ++; 
	 *   2.3 if a1[i] == a2[j] result = max(sum1, sum2); set sum1 and sum2 to 0. then add the common elements to result; result +=
	 * common elements 3 a1[] or a2[] might still have remaining elements.
	 * 
	 * Get the idea of merge sort.
	 */

	public static int maxSumPath(int[] a1, int[] a2) {
		int sum1 = 0, sum2 = 0;
		int result = 0;
		int i = 0, j = 0;
		while (i < a1.length && j < a2.length) {
			if (a1[i] < a2[j]) {
				// update sum1
				sum1 += a1[i];
				i++;
			} else if (a1[i] > a2[j]) {
				sum2 += a2[j];
				j++;
			} else {
				// a[i] == a[j]
				result += Math.max(sum1, sum2);
				// update sum1 and sum2 after this intersection points
				sum1 = 0;
				sum2 = 0;
				while (i < a1.length && j < a2.length && a1[i] == a2[j]) {
					result += a1[i];
					i++;
					j++;
				}
			}
		}
		while (i < a1.length) {
			sum1 += a1[i];
			i++;
		}
		while (j < a2.length) {
			sum2 += a2[j];
			j++;
		}
		result += Math.max(sum1, sum2);
		return result;
	}

	public static void test11() {
		int[] a1 = { 2, 3, 7, 10, 12, 15, 30, 34 };
		int[] a2 = { 1, 5, 7, 8, 10, 15, 16, 19 };
		System.out.println(maxSumPath(a1, a2));
	}

	/*
	 * task12 
	 * Search in an almost sorted array
	 * http://www.geeksforgeeks.org/search-almost-sorted-array/
	 * Key point: the element a[i] might be swapped with a[i-1] or a[i + 1]
	 * so, in the binary search, compare a[i], a[i-1], a[i + 1] with the target. 
	 * 
	 * for the update part, start = mid + 2; end = mid - 2;
	 * 
	 * Given an array which is sorted, but after sorting some elements are moved
	 * to either of the adjacent positions, 
	 * i.e., arr[i] may be present at arr[i+1] or arr[i-1]. 
	 * Write an efficient function to search an element in this array. 
	 * Basically the element arr[i] can only be swapped with either arr[i+1] or arr[i-1].
	 * 
	 * 
	 * he idea is to compare the key with middle 3 elements, 
	 * if present then return the index. 
	 * If not present, 
	 *   then compare the key with middle element 
	 *   to decide whether to go in left half or right half. 
	 *   Comparing with middle element is enough
	 *   as all the elements after mid+2 (can only swap with its neighbour)
	 *   must be greater than element mid 
	 *   and all elements before mid-2 must be smaller than mid element.
	 */
	
	public static int searchInAlmostSorted(int[] a, int target) {
		int start = 0, end = a.length - 1;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if (a[mid] == target) {
				return mid;
			} else if (a[mid - 1] == target) {
				return mid - 1;
			} else if (a[mid + 1] == target) {
				return mid + 1;
			} else if (a[mid] > target) {
				// it can only be in the left subArray
				end = mid - 2;
			} else {
				start = mid + 2;
			}
		}
		return -1;
	}
	public static void test12() {
		int[] a = {10, 3, 40, 20, 50, 80, 70};
		int target = 40;
		System.out.println(searchInAlmostSorted(a, target));
	}
 	

	/*
	 * task13 
	 * Sort an array according to the order defined by another array
	 * http://www.geeksforgeeks.org/sort-array-according-order-defined-another-array/
	 * 
	 * method1
	 * 1 use a temp[] to copy a1[], and then sort temp. 
	 * 2 create a boolean[] visited to mark whether elements in temp has been visited
	 * 3 for every elements in a2[], do binary search in temp[], find the first occurence 
	 * 4 copy the elements from temp[] to a1[], and mark in the corresponding index in visited as true
	 * 5 traverse the temp[], for the no-visited elements, copy them to a1[] after the already sorted one. 
	 *
	 * method2
	 * (1)using hashMap <Key, Value>  key is the elements in the array. value is its count
	 * (2)traverse the a2[]. put corresponding element into a1[], and delete the element from hashMap
	 * (3)sort the remaining map. and append to a1[]
	 * 
	 * method3
	 * using balanced binary search tree. 
	 * TreeNode includes: boolean visited; int count;
	 * (1) construct the balanced bst for a1[]. the visited initailized false. 
	 * (2) for every elements in a2, search them in the bst. and put the corresponding elements in a1[]
	 *     and mark the elements as visited
	 * (3) do an inorder traversal of bst and put the unvisited to a1[]
	 * 
	 *  method4
	 *  by writing a customized compare method
	 *  1 if num1 and num2 are both in a2[], then the lower index in a1[] will be treated smaller than other
	 *  2 if only 1 in num1 and num2 are in a2[], then the number in a2[] will be treated smaller
	 *  3 if both are not in a2[], the natural ordering will be taken. 
	 */
	public static void test13() {
		int[] a1 = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
		int[] a2 = {2, 1, 8, 3};
		P4_Array.printArray(a1);
		P4_Array.printArray(a2);
		sortAccording(a1, a2);
		P4_Array.printArray(a1);
	}
	public static void sortAccording(int[] a1, int[] a2) {
		int n1 = a1.length;
		int n2 = a2.length;
		int[] temp = new int[n1];;
		boolean[] visited = new boolean[n1];
		
		// copy a1 to temp
		for (int i = 0; i < n1; i++) {
			temp[i] = a1[i];
		}
		Arrays.sort(temp);
		int ind = 0;
		for (int i = 0; i < n2; i++) {
			int fistIndex = firstIndex(temp, a2[i]);
			if (fistIndex == -1) {
				continue;
			}
			for (int j = fistIndex; j < n1 && temp[j] == a2[i]; j++) {
				a1[ind++] = temp[j];
				visited[j] = true;
			}
		}
		
		// copy all the elements of temp which are not present in a2[]
		for (int i = 0; i < n1; i++) {
			if (visited[i] == false) {
				a1[ind++] = temp[i];
			}
		}
	}
	
	public static int firstIndex(int[] a, int x) {
		int start = 0, end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (x == a[mid]) {
				end = mid;
			} else if (x < a[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (a[start] == x) {
			return start;
		} else if (a[end] == x) {
			return end;
		} else {
			return -1;
		}
	}
	
	

	/*
	 * task14 
	 * Rearrange array in alternating positive & negative items with O(1) extra space
	 * http://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-items-o1-extra-space/
	 * 
	 * 
	 * 
	 * http://www.geeksforgeeks.org/amazon-interview-set-118-campus-internship/
	 * http://www.geeksforgeeks.org/amazon-interview-set-114-campus-internship/
	 * 
	 * 1 partition, let the positive in front of the negative 
	 * 2 the positive, every time, we move 2 step
	 *   the negative, every time, we move 1 step. 
	 */
	
	
	

}

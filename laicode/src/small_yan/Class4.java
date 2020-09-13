package small_yan;

import java.util.Arrays;
import java.util.HashMap;

public class Class4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * task1 Majority Number
	 */
	/*
	 * task1.1 There is one number in a sized N array that exists > N / ​2​times, how do you find the number?
	 * <1> sort the array, find the majority number O(n log n)
	 * <2> HashMap<Integer, Integer>  (elements, counter), Time: O(n) Space: O(n)
	 * <3> maintain current and counter 
	 */
	
	/*
	 * task1.2 Majority Number -Enhanced
	 * There are possibly some numbers in a sized N array that exists > N / ​3​times, how do you find the numbers?
	 * Example: int[] array = {1, 2, 3, 1, 1, 2, 2, 3, 4, 1, 2}
	 * return [1,2].  max # of result is 2. 
	 * 
	 * two candidates, with their counter
	 * candidate1, count1
	 * candidate2, count2
	 * 
	 * 
	 */
	
	/*
	 * task1.3 
	 * Majority Number ­ Enhanced II
	 * There are possibly some numbers in a sized N array that exists > N / ​K​times, how do you find the numbers?
	 * 
	 * max # of result is k - 1
	 * 
	 * (k - 1) space
	 * HashMap<Integer, Integer> map
	 * if current number is already in map; count ++;
	 * if current number is not in map
	 *     if (has at least 1 space(map.size < k - 1)) ==> map.put(number, 1);
	 *     else (there is no space) {
	 *     	for(each number in map) {
	 *     		count --;
	 *          if (count == 0)
	 *             remove number from map
	 *     	}
	 *     }
	 */
	
	/*
	 * task2
	 * Partition Array
	 * 
	 * 
	 */
	public static void task2_partition(int[] a, int left, int right) {
		int start = left, end = right;
		int pivot = a[end];
		while (start < end) {
			while (start < end && a[start] < pivot) {
				start++;
			}
			while (start < end && a[end] >= pivot) {
				end--;
			}
			swap(a, start, end);
		}
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		swap(a, start, right);
	}

	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	
	/*
	 * task2.2
	 * quick sort, partition
	 */
	
	
	/*
	 * task2.3
	 * color sort
	 * 0 red
	 * 1 yellow
	 * 2 blue
	 * 
	 * 00000011111XxxxX222222
	 *       i    j   k
	 * [0..i-1]      0 Red
	 * [i..j-1]      1
	 * [j.. k]       unknown
	 * [k + 1, n-1]  2
	 * 
	 * initialize state: 
	 * i = 0, j = 0
	 * k = n - 1
	 * 
	 * 
	 */
	
	public static void sortColor(int[] a) {
		int i = 0, j = 0, k = a.length - 1;
		while(j <= k) {
			if (a[j] == 0 ) {
				swap(a, i, j);
				i ++;
				j ++;
			} else if (a[j] == 2) {
				swap(a, j, k);
				k --;
			} else {
				// a[j] == 1
				j ++;
			}
		}
	}
	
	/*
	 * task2.4 
	 * sort letter by case
	 * 
	 * the same with partition
	 */
	
	/*
	 * task3
	 * Interleaving Positive and Negative Numbers
	 * 
	 *
	 */
	
	
	
	
	
	
	/*
	 * task3.2
	 * Array Re­Order
	 * { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
	 * { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }
	 */
	
	public static void task3_reorder(int[] array) {
		if (array == null || array.length == 0) {
			return ;
		}	
	}
	public static void task3_reorderHelper(int[] array, int left, int right) {
		if (left + 1 >= right) {
			return ;
		}
		int length = right - left + 1;
		int mid = left + length/2;
		int leftMid = left + length/4;
		int rightMid = left + length*3/4;
		
		// shift the middle part
		// reverse array[leftMid, mid - 1]
		task3_reverse(array, leftMid, mid - 1);
		// reverse array[mid, rightMid - 1]
		task3_reverse(array, mid, rightMid - 1);
		// reverse array[leftMid, rightMid - 1]
		task3_reverse(array, leftMid, rightMid - 1);
		
		task3_reorderHelper(array, left, 2*(leftMid - left) - 1);
		task3_reorderHelper(array, 2*(leftMid - left), right);
	}
	
	public static void task3_reverse(int[] array, int left, int right) {
		while(left < right) {
			swap(array, left ++, right --);
		}
	}
	
	
	/*
	 * task3.3
	 * Array Re­Order Reverse
	 */
	
	
	
	/*
	 * task3.4
	 * Rotation/Shift
	 * {1, 2, 3,​4, 5}​→ right shift 2 → {4, 5, 1, 2, 3} 
	 * 1). {​1, 2, 3,​5, 4}
	 * 2). {​3, 2, 1, 5, 4}​
	 * 3). {4, 5, 1, 2, 3}
	 * 
	 * 
	 * left shift 3 -> reduce to right shift length - 2 
	 */
	
	/*
	 * task4
	 * Quick Sort VS Merge Sort
	 * 1 quick sort in place, merge sort extra place
	 * 2 stable
	 * 
	 * selection sort YES
	 * Insertion sort YES
	 * QuickSort  NO
	 * MergeSort --> YES
	 * 
	 * Arrays.sort(int[] array)-- primitive type array: quicksort
	 * Arrays.sort(Object[] array) --> Object type array: merge sort
	 * Collectoins.sort(List<E> list) --> MergeSort
	 *   
	 */
	
	/*
	 * task5
	 * 2­sum, 3­sum, 4­sum, 3­sum closest
	 * 
	 * (1) Sort the array first, and use left and right pointer to getting close target. O(n log n) + O(n) = O(n log n)
	 * (2) Use hashmap/hashSet, O(n), extra space O(n)
	 * 
	 */
	/*
	 * task5.1 
	 * 2­sum, how many pairs sum to target, with/without duplicates?
	 */
	/*
	 * task5.2
	 * Given an int array, find if(all) there are 4 elements in the array such that A + B + C = D.
	 */
	/*
	 * task5.3
	 * Given an int array, find if(all) there are 4 elements in the array such that A + B = C + D.
	 */
	
	/*
	 * task5.4
	 * Given an int array, find the number of pairs(i, j) that Math.abs(a[i] ­- a[j]) = target. 
	 * e.g {1,3,1,3}  target = 2 # of pairs is 4: (0,1) (0,3) (1,2) (2,3)
	 * 
	 * 
	 * 
	 * 
	 */
	
	/*
	 * task6
	 * Find the subarray, the sum = 0.
	 * 1). does it exist? 2). how many subarrays?
	 * b[i] = a[0] + a[1] + ... + a[i]
	 * subarray(i, j) = b[j] - b[i - 1]
	 * 1)
	 * (1) create b[i] = a[i-1] + a[i]
	 * (2) b[i] == b[j] or a[i] == 0 ||b[i] == 0, EXIST   use HashMap
	 * 2)
	 * how many
	 * use a counter. 
	 * <a> b[i] == b[j]
	 * <b> a[i] == 0
	 * <c> b[i] == 0
	 * 
	 * we nee do to take into some consideration to the above cases
	 */
	
	/*
	 * task6.1
	 * Find the subarray, the sum is closest to 0.
	 * b[i] = a[0] + a[1] + a[2] + ... + a[i]
	 * 
	 * sort <b[i], index>, then find the closest.
	 * Time: O(n log n) 
	 * 
	 */
	
	
	/*
	 * task6.2
	 * Find the subarray, the sum = k.
	 * like task6. 
	 * get b[] 
	 * b[j] - b[i] == k, a[i] == k, b[i] == k 
	 * Space: O(n)
	 * Time: O(n)
	 * 
	 * task6.3
	 * Find the subarray, the sum is closest to k.
	 * sort<b[i], index>
	 * find the closest
	 */
	
	// in this task, we just return one subarray if exist
	public static int[] test6_2_subarray_sumK(int[] array, int k) {
		if (array == null || array.length == 0) {
			return new int[0];
		}
		int[] b = new int[array.length];
		int start = -1, end = -1;
		for(int i = 0; i < array.length; i ++) {
			if (array[i] == 0) {
				start = i;
				end = i;
				return Arrays.copyOfRange(array, start, end + 1);
			}
			if (i == 0) {
				b[i] = array[i];
			} else {
				b[i] = b[i - 1]+ array[i];
			}
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < b.length; i ++) {
			if (b[i] == 0) {
				return Arrays.copyOfRange(array, 0, i + 1);
			} else {
				if (map.containsKey(b[i])) {
					start = map.get(b[i]);
					end = i;
					return Arrays.copyOfRange(array, start, end + 1);
				} else {
					map.put(b[i], i);
				}
			}
			
		}
		return new int[0];
	}
	
	
	
	
	

}

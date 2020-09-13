package small_yan;

import java.util.*;
import java.util.Map.Entry;

import debug.Debug;

public class Class4_Arrays1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
		test3();
//		test2_2();
//		test6_1();
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
	 public static void majority1(int[] input) {
		int candidate = Integer.MIN_VALUE;
		int count = 0;
		for(int i = 0; i < input.length; i ++) {
			int cur = input[i];
			if (candidate == cur) {
				count ++;
			} else {
				if (count == 0) {
					// update candidate
					candidate = cur;
					count ++;
				} else {
					count --;
				}
			}
		}
//		System.out.println(candidate);
		count = 0;
		for(Integer i: input) {
			if (i == candidate) {
				count ++;
			}
		}
		
		if (count > input.length/2) {
			System.out.println(candidate);
		}
	}
	 
	
	/*
	 * task1.2 Majority Number -Enhanced
	 * There are possibly some numbers in a sized N array that exists > N / ​3​ times, how do you find the numbers?
	 * Example: int[] array = {1, 2, 3, 1, 1, 2, 2, 3, 4, 1, 2}
	 * return [1,2].  max # of result is 2. 
	 * 
	 * two candidates, with their counter
	 * candidate1, count1
	 * candidate2, count2
	 */
	 
	 public static void majority2(int[] input) {
		int cand1 = Integer.MIN_VALUE;
		int cand2 = Integer.MIN_VALUE;
		int count1 = 0;
		int count2 = 0;
		
		for(int i = 0; i < input.length; i ++) {
			int cur = input[i];
			if (cand1 == cur) {
				count1 ++;
			} else if (cand2 == cur) {
				count2 ++;
			} else {
				if (count1 == 0) {
					cand1 = cur;
					count1 ++;
				} else if (count2 == 0) {
					cand2 = cur;
					count2 ++;
				} else {
					// count1 and count2 both != 0
					count1--;
					count2 --;
				}
			}
		}
		
		
		// after that, candidate 1 and candidate2 might be the result
		count1 = 0;
		count2 = 0;
		for(Integer i: input) {
			if (cand1 == i) {
				count1++;
			}
			if (cand2 == i) {
				count2 ++;
			}
		}
		if (count1 > input.length/3) {
			System.out.println(cand1);
		}
		if (count2 > input.length/3) {
			System.out.println(cand2);
		}
	}
	
	
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
	
	
	public static void majority3(int[] input, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0; i < input.length; i ++) {
			int key = input[i];
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				// map doesn't contain key
				if (map.size() < k - 1) {
					// insert the key into map
					map.put(key, 1);
				} else {
					// decrease every element's value by 1
					ArrayList<Integer> elemList = new ArrayList<Integer>();
					for(Entry<Integer, Integer> entry: map.entrySet()) {
						int curKey = entry.getKey();
						int curVal = entry.getValue();
						map.put(curKey, curVal  - 1);
						elemList.add(curKey);
					}
					
					for(Integer elem : elemList) {
						if (map.get(elem) == 0) {
							map.remove(elem);
						}
					}
				}
			}
		}
		
		// after the above loop, all the candidates are in the hashMap
		// to make sure that all candidate has strictly larger than n/k
		
		// set all values in HashMap to 0
		for(Entry<Integer, Integer> entry: map.entrySet()) {
			map.put(entry.getKey(), 0);
		}
		// traverse the input array. count each candidate's appear time
		for(Integer elem: input) {
			if (map.containsKey(elem)) {
				int val = map.get(elem);
				map.put(elem, val + 1);
			}
		}
		
		// traverse the map again, print out the result
		for(Entry<Integer, Integer> entry: map.entrySet()) {
			if (entry.getValue() > input.length/k) {
				System.out.println(entry.getKey());
			}
		}
	}
	
	
	/*
	 * task2
	 * Partition Array
	 */
	public static void test2() {
		int[] a = {4,2,5,9,0,8,1,6};
		System.out.println(Arrays.toString(a));
		int left = 0, right = a.length - 1;
		task2_partition(a, left, right);
		System.out.println(Arrays.toString(a));
		
	}
	
	
	public static int task2_partition(int[] a, int left, int right) {
		int start = left, end = right;
		int pivot = a[end];
		
		while (start <= end) {
			if (a[start] < pivot) {
				start ++;
			} else if (a[end] >= pivot) {
				end --;
			} else {
				swap(a, start, end);
				start ++;
				end --;
			}
		}
		
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		swap(a, start, right);
		// start is the pivot position
		return start;
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
	public static void test2_2() {
		int[] array = {1,3,5,8,2,1,0};
		System.out.println(Arrays.toString(array));
		quickSort(array);
		System.out.println(Arrays.toString(array));
	}
	public static void quickSort(int[] array) {
		// sanity check
		if (array == null || array.length <= 1) {
			return ;
		}
		quickSortHelper(array, 0, array.length - 1);
	}
	
	public static void quickSortHelper(int[] a, int left, int right) {
		if (left >= right) {
			return ;
		}
		int pivotIndex = partition(a, left, right);
		quickSortHelper(a, left, pivotIndex - 1);
		quickSortHelper(a, pivotIndex + 1, right);
		
	}
	
	public static int getPivotIndex(int left, int right) {
		int pivotIndex = left + (int)(Math.random() *(right -left + 1));
		return pivotIndex;
	}
	
	public static int partition(int[] array, int left, int right) {
		int pivotIndex = getPivotIndex(left, right);
		int pivot = array[pivotIndex];
		swap(array, pivotIndex, right);
		
		int start = left, end = right;
		while(start <= end) {
			if (array[start] < pivot) {
				start ++;
			} else if (array[end] >= pivot) {
				end --;
			} else {
				swap(array, start, end);
				start ++;
				end --;
			}
		}
		swap(array, start, right);
		return start;	
	}
	
	
	
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
	 * guarantee that # of positive >= # of negative numbers
	 * 
	 * {1,-3, 2,-2,4,-1,3} -> {1,-2, 2, -3, 3, -1,4}
	 * 
	 * {-3, -2, -1, 1,2,3,4} 
	 * while(neg < pos && pos < n && a[neg] < 0) {
	 * 	swap(&neg, &pos)
	 * 	neg += 2;
	 *  pos ++;
	 * }
	 * 
	 * refer to geeksforgeeks P5_Array
	 * 
	 */
	
	
	public static int task3_partition(int[] a, int pivot) {
		int left = 0, right = a.length - 1;
		while(left <= right) {
			if (a[left] < pivot) {
				left ++;
			} else if (a[right] >= pivot) {
				right --;
			} else {
				swap(a, left, right);
				left ++;
				right --;
			}
		}
		System.out.println(Arrays.toString(a));
		System.out.println("left = " + left + ": " + a[left]);
		System.out.println("right = " + right + ": " + a[right]);

		// left is the first element > pivot
		return left;
	}
	
	// neg 走两步， pos 走一步
	public static void task3_interleaving_positive_negative(int[] a) {
		if (a == null || a.length <=1) {
			return ;
		}
		
		int neg = 0;
		int pos = task3_partition(a, 0);
	
		while(neg < pos && pos < a.length && a[neg] < 0) {
			// make sure that a[neg] < 0
			swap(a, neg, pos);
			neg += 2;
			pos ++;
		}
	}
	
	public static void test3() {
		int[] a = {1,-3,2,-2,4, -1, 3};
		task3_interleaving_positive_negative(a);;
		Debug.printArray(a);
	}
	
	
	
	
	
	
	
	/*
	 * task3.2
	 * Array Re­Order
	 * { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
	 * { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }
	 */
	
	public static void task3_2_reorder(int[] array) {
		if (array == null || array.length == 0) {
			return ;
		}	
	}
	public static void task3_2_reorderHelper(int[] array, int left, int right) {
		if (left + 1 >= right) {
			return ;
		}
		int length = right - left + 1;
		int mid = left + length/2;
		int leftMid = left + length/4;
		int rightMid = left + length*3/4;
		
		// shift the middle part
		// reverse array[leftMid, mid - 1]
		task3_2_reverse(array, leftMid, mid - 1);
		// reverse array[mid, rightMid - 1]
		task3_2_reverse(array, mid, rightMid - 1);
		// reverse array[leftMid, rightMid - 1]
		task3_2_reverse(array, leftMid, rightMid - 1);
		
		task3_2_reorderHelper(array, left, 2*(leftMid - left) - 1);
		task3_2_reorderHelper(array, 2*(leftMid - left), right);
	}
	
	public static void task3_2_reverse(int[] array, int left, int right) {
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
	 * 2­sum, 3­sum, 4­sum, k sum 
	 * 
	 * refer to lai_online.Class24.java
	 * 
	 * (1) Sort the array first, and use left and right pointer to getting close target. O(n log n) + O(n) = O(n log n)
	 * (2) Use hashmap/hashSet, O(n), extra space O(n)
	 * 
	 */
	
	
	
	
	/*
	 * task5.0 
	 * 3­sum closest
	 */
	
	/*
	 * task5.1 
	 * 2­sum, how many pairs sum to target, with/without duplicates?
	 * 
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
	 * =========================================================================================
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
	 * we need to take into some consideration to the above cases
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
	public static class Node implements Comparable<Node>{
		int val;
		int index;
		public Node(int v, int i) {
			this.val = v;
			this.index = i;
		}
		@Override
		public int compareTo(Node other) {
			// TODO Auto-generated method stub
			if (this.val == other.val) {
				return 0;
			}
			return this.val < other.val ? -1 : 1;
		}
	}
	
	public static void test6_1() {
		int[] array = {4,9,7,2,3};
		task6_1_subarray_closest0(array);
	}
	public static void task6_1_subarray_closest0(int[] array) {
		// sanity check
		if (array == null || array.length == 0) {
			return ;
		}
		int n = array.length;
		int[] preSum = new int[n];
		for(int i = 0; i < n; i ++) {
			if (i == 0) {
				preSum[i] = array[i];
			} else {
				preSum[i] = array[i] + preSum[i - 1];
			}
		}
		System.out.println(Arrays.toString(preSum));
		
		// now, we have the preSum array
		ArrayList<Node> list = new ArrayList<Class4_Arrays1.Node>();
		for(int i = 0; i < n; i ++) {
			Node node = new Node(preSum[i], i);
			list.add(node);
		}
		Collections.sort(list);
		// for debug
		for(int i = 0; i < list.size(); i ++) {
			System.out.print(list.get(i).val + " ");	
		}
		System.out.println();
		for(int i = 0; i < list.size(); i ++) {
			System.out.print(list.get(i).index + " ");
		}
		System.out.println();
		int minDiff = Integer.MAX_VALUE;
		int start = -1;
		int end = -1;
		for(int i = 0; i < list.size() - 1; i ++) {
			if (minDiff > list.get(i + 1).val - list.get(i).val) {
				minDiff = list.get(i + 1).val - list.get(i).val;
				start = Math.min(list.get(i + 1).index, list.get(i).index) + 1;
				end = Math.max(list.get(i + 1).index, list.get(i).index);
			}
		}
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		System.out.println("minDiff = " + minDiff);
		
	}
	
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
	// this is also a function that find a subarray that sum == 0
	public static int[] test6_2_subarray_sum0(int[] array, int k) {
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

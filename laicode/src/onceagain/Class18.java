package onceagain;

import java.util.*;

public class Class18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test4();
//		test5();
		test6();
	}

	/*
	 * task1 Array dedeuplicate I (sorted array, duplicate element only retain
	 * one)
	 */

	/*
	 * task2 Array Deduplicate II (sorted array, duplicate element only retain
	 * two)
	 */

	/*
	 * task3 Array Deduplicate III (sorted array, duplicate element not retain
	 * any)
	 */

	/*
	 * task4 Array Deduplicate IV (unsorted array, repeatedly deduplication)
	 * input: {1,2,2,3,3,1} output {}
	 * 
	 * input: {1,2,2,2,3,3,2} output: {1,2}
	 * 
	 * [0, end)
	 */
	public static int[] task4_ArrayDeduplicateIV(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int end = -1;
		for (int i = 0; i < array.length; i++) {
			if (end == -1 || array[end] != array[i]) {
				array[++end] = array[i];
			} else {
				// array[i] == array[i - 1]
				// skip the following element which is same with array[i]
				while (i + 1 < array.length && array[i] == array[i + 1]) {
					i++;
				}
				// end --
				end--;
			}
		}
		System.out.println("end = " + end);

		return Arrays.copyOfRange(array, 0, end + 1);
	}

	public static void test4() {
		int[] array = { 1, 2, 2, 2, 3, 3, 1 };
		int[] result = task4_ArrayDeduplicateIV(array);
		System.out.println(Arrays.toString(result));
	}

	/*
	 * task5 Largest And Smallest Use the least number of comparisons to get the
	 * largest and smallest number in the given integer array. Return the
	 * largest number and the smallest number. Assumptions The given array is
	 * not null and has length of at least 1 Examples {2, 1, 5, 4, 3}, the
	 * largest number is 5 and smallest number is 1. return [5, 1].
	 */
	/*
	 * compartion time: O(2*n)
	 */
	public static int[] task5_largest_smallest(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int max = array[0];
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			int curVal = array[i];
			if (curVal > max) {
				max = curVal;
			} else if (curVal < min) {
				min = curVal;
			}
		}
		return new int[] { max, min };
	}

	public static int[] task5_largest_smallest_opt(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		if (array.length == 1) {
			return new int[] { array[0], array[0] };
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		if (array[0] > array[1]) {
			max = array[0];
			min = array[1];
		} else {
			min = array[0];
			max = array[1];
		}

		for (int i = 2; i <= array.length - 2; i += 2) { // n/2
			if (array[i] > array[i + 1]) { // 1
				if (max < array[i]) { // 1
					max = array[i];
				}
				if (min > array[i + 1]) { // 1
					min = array[i + 1];
				}
			} else {
				// array[i] <= array[i + 1]
				if (max > array[i + 1]) {
					max = array[i + 1];
				}
				if (min < array[i]) {
					min = array[i];
				}
			}
		}
		// compare the last element if there are odd number of elements
		if (array.length % 2 == 1) {
			if (array[array.length - 1] > max) {
				max = array[array.length - 1];
			}
			if (array[array.length - 1] < min) {
				min = array[array.length - 1];
			}
		}
		return new int[] { max, min };
	} // total: n*3/2 = 1.5 n

	public static int[] task5_largest_smallest_opt2(int[] array) {
		List<Integer> larger = new ArrayList<Integer>();
		List<Integer> smaller = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i += 2) {
			if (i + 1 == array.length) {
				larger.add(array[i]);
				smaller.add(array[i]);
			} else if (array[i] <= array[i + 1]) {
				smaller.add(array[i]);
				larger.add(array[i + 1]);
			} else {
				smaller.add(array[i + 1]);
				larger.add(array[i]);
			}
		}
		int max = largest(larger);
		int min = smallest(smaller);
		return new int[] { max, min };
	} // total: n*3/2

	private static int largest(List<Integer> larger) {
		int largest = larger.get(0);
		for (int num : larger) {
			if (num > largest) {
				largest = num;
			}
		}
		return largest;
	}

	private static int smallest(List<Integer> smaller) {
		int smallest = smaller.get(0);
		for (int num : smaller) {
			if (num < smallest) {
				smallest = num;
			}
		}
		return smallest;
	}
	
	public static void test5() {
		int[] array = {4,1,5,2,9, 3,7};
		int[] result = task5_largest_smallest(array);
		int[] result2 = task5_largest_smallest_opt(array);
		int[] result3 = task5_largest_smallest_opt2(array);
		
		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.toString(result2));
		System.out.println(Arrays.toString(result3));
		
	}
	/*
	 * task6 Largest And Second Largest
	 * 
	 * Map<Integer, ArrayList<Integer>> map: index, List of Intgers which smaller than array[index]
	 * 
	 */
	public static class Pair{
		int first;
		int second;
		public Pair(int f, int s) {
			this.first = f;
			this.second = s;
		}
	}
	
	public static Pair task6_LargestAndSecondLargest(int[] array) {
		List<Pair> list = new ArrayList<Pair>();
		// add <index, value> into list
		for(int i = 0; i < array.length; i ++) {
			list.add(new Pair(i, array[i]));
		}
		
		System.out.println(list.size());
		
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		while(list.size() > 1) {  
			// if list.size() == 0, we get the champion, the largest 
			// the second largest must be in the list of the largest
			List<Pair> nextRound = new ArrayList<Class18.Pair>();
			for(int i = 0; i < list.size(); i += 2) {
				if (i + 1 < list.size()) {
					Pair p1 = list.get(i);
					Pair p2 = list.get(i + 1);
					if (p1.second > p2.second) {
						nextRound.add(p1);
						if (!map.containsKey(p1.first)) {
							map.put(p1.first, new ArrayList<Integer>());
						}
						map.get(p1.first).add(p2.second);
					} else {
						// p1.second <= p2.second
						nextRound.add(p2);
						if (!map.containsKey(p2.first)) {
							map.put(p2.first, new ArrayList<Integer>());
						}
						map.get(p2.first).add(p1.second);
					}
				} else {
					nextRound.add(list.get(i));
				}
			}
			list = nextRound;
		}
		
		return new Pair(list.get(0).second, task6_max(map.get(list.get(0).first)));
	}
	
	public static int task6_max(ArrayList<Integer> list) {
		int max = list.get(0);
		for(Integer i: list) {
			max = Math.max(max, i);
		}
		return max;
	}
	
	public static void test6() {
		int[] array ={2,1,5,4,3};
		Pair result = task6_LargestAndSecondLargest(array);
		System.out.println(result.first + "  :  " + result.second);
	}
	

	/*
	 * task7 Spiral Order Print
	 */
	

	/*
	 * task8 Rotate Matrix By 90 Degree Clockwise
	 */

	/*
	 * task9 Zig-Zag Order Print Binary Tree
	 */

}

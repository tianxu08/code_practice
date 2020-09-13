package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ch18_advanced1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test4();
		test6();
	}
	
	/*
	 * t1 array deduplication I(sorted array, duplicate element only retain one)
	 * t2 array deduplication II(sorted array, duplicate element only retain two)
	 * t3 array deduplication III(sorted array, duplicate element only not retain any) 
	 * t4 array deduplication IV(unsorted array, repeately deduplication)
	 */
	/*
	 * t5 largest and smallest
	 * t6 largest and value largest
	 */
	/**
	 * 
	 * @param arr
	 * @return: the new length of deduped array
	 * Assume: array sorted
	 */
	public static int t1_dedup(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int end = 0;
		// start from 1
		for(int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[end]) {
				end ++;
				arr[end] = arr[i];
			}
		}
		return end + 1;
	}
	
	/**
	 * 
	 * @param arr
	 * @return: the new length of deduplicated array
	 * assume: the array is sorted
	 */
	public static int t2_dedup2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length <= 2) {
			return arr.length;
		}
		int end = 1;
		for(int i = 2; i < arr.length; i ++) {
			// compare arr[i] with arr[end - 1]
			if (arr[i] != arr[end - 1]) {
				end ++;
				arr[end] = arr[i];
			}
		}
		return end + 1;
	}

	public static void test3() {
		int[] a = {1,2,2,3,3};
		int new_len = t3_dedup3(a);
		for(int i = 0; i < new_len; i ++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * sorted array, duplicate element only not retain any
	 * @param arr
	 * @return: 
	 */
	public static int t3_dedup3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length <= 1) {
			return arr.length;
		}
		int cand_idx = 0;
		boolean there_is_dup = false;
		for(int i = 1; i < arr.length; i ++) {
			if (there_is_dup) {
				// still duplicate
				if (arr[i] == arr[cand_idx]) {
					continue;
				} else {
					// update the candidate and reset the flag
					arr[cand_idx] = arr[i];
					there_is_dup = false;
				}
			} else {
				// NO duplicate for current candidate
				if (arr[i] == arr[cand_idx]) {
					// set the flag to true
					there_is_dup = true;
				} else {
					cand_idx ++;
					there_is_dup = false;
					arr[cand_idx] = arr[i];	
				}
			}
		}
		if (there_is_dup) {
			// the element in cand_idx has duplicate
			return cand_idx;
		} else {
			// the element in cand_idx doesn't have duplicate
			return cand_idx + 1;
		}
	}
	
	public static void test4() {
		int[] arr = {5,1,3,2,3,3,2,2,1,9,6};
		int new_len = t4_dedup4(arr);
		
		System.out.println(Arrays.toString(Arrays.copyOf(arr, new_len)));
	}
	
	/**
	 * 
	 * input {5,1,3,2,3,3,2,2,1,9,6}
	 * output: [5, 1, 3, 1, 9, 6]
	 * 
	 * @param arr
	 * @return: new length after deduplicate
	 */
	public static int t4_dedup4(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length <= 1) {
			return arr.length;
		}
		int end = -1;
		for(int i = 0; i < arr.length; i ++) {
			if (end == -1) {
				end ++;
				arr[end] = arr[i];
			} else if (arr[i] != arr[end]) {
				end ++;
				arr[end] = arr[i];
			} else {
				// end != -1 && arr[i] == arr[end]
				while(i + 1 < arr.length && arr[end] == arr[i + 1]) {
					i ++;
				}
				// put end one step  backward
				end --;
				
			}
		}
		
		return end + 1;
	}
	
	
	public static void test6() {
		int[] a = {5,1,2,3,6,5,4,3,9,10,4,20};
		int[] rev = t6_largest_and_value_largest(a);
		System.out.println(Arrays.toString(rev));
	}
	
	
	public static int[] t6_largest_and_value_largest(int[] a) {
		List<Pair> list = new ArrayList<Pair>();
		for(int i = 0; i < a.length; i++) {
			Pair p = new Pair(i, a[i]);
			list.add(p);
		}
		// Element index,  List of element which is smaller than the 'element' after comparasion
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		
		while(list.size() > 1) {
			List<Pair> next_round = new ArrayList<Pair>();
			for(int i = 0; i < list.size(); i += 2) {
				if (i + 1 < list.size()) {
					Pair p1 = list.get(i);
					Pair p2 = list.get(i + 1);
					
					if (p1.value <= p2.value) {
						// p2 goes to next round
						next_round.add(p2);
						if (!map.containsKey(p2.index)) {
							map.put(p2.index, new ArrayList<Integer>());
						}
						map.get(p2.index).add(p1.value);
					} else {
						// p1 goes to next round
						next_round.add(p1);
						if (!map.containsKey(p1.index)) {
							map.put(p1.index, new ArrayList<Integer>());
						}
						map.get(p1.index).add(p2.value);
					}
				} else {
					// i + 1 == list.size
					next_round.add(list.get(i));
				}
			}
			// update list to next_round
			list = next_round;
		}
		
		int index_largest = list.get(0).value;
		
		// scan the champion's defeated list
		int value_largest = Integer.MIN_VALUE;
		
		for(Integer i : map.get(list.get(0).index)) {
			value_largest = Math.max(value_largest, i);
		}
		
		return new int[]{index_largest, value_largest};		
	}
	public static class Pair{
		int index;
		int value;
		public Pair(int _i, int _v) {
			this.index = _i;
			this.value = _v;
		}
	}
	
	
	
	
	

}

package tag_array;

import java.util.*;
import java.util.Map.Entry;

public class Sums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test4();
//		test5();
	}
	

	
	/*
	 * 2 Sum All pairs II
	 * Find all pairs of elements in a given array that sum to the given target number. 
	 * Return all the pairs of indices.
	 * 
	 * Assumptions
	 * The given array is not null and has length of at least 2.
	 * Examples
	 * A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
	 * A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
	 * 
	 * 
	 * solution: 
	 * use hasMap<Integer, List<Integer>>  value, array(indices)
	 * 
	 */
	public static void test4() {
		int[] array = {1,2,2,4};
		int target = 6;
		List<List<Integer>> result = twoSumAllPairs1(array, target);
		System.out.println(result);
	}
	
	public static List<List<Integer>> twoSumAllPairs1(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		// key: value ==> element, indices of element
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		// fill in the hashMap
		for(int i = 0; i < array.length; i++) {
			if (!map.containsKey(array[i])) {
				map.put(array[i], new ArrayList<Integer>());
			}
			// add array[i]'s index
			map.get(array[i]).add(i);
		}
		
		for (int i = 0; i < array.length; i++) {
			int cur = array[i];
			int elem_to_find = target - cur;
			if (cur == elem_to_find) {
				if (map.get(cur).size() >= 2) {
					ArrayList<Integer> list_indices = map.get(cur);
					for(int j = 0; j < list_indices.size() - 1; j++) {
						result.add(Arrays.asList(j, j + 1));
					}
					map.remove(cur);
				}
			} else {
				if (map.containsKey(elem_to_find)) {
					ArrayList<Integer> cur_index_list = map.get(cur);
					ArrayList<Integer> elem_to_find_index_list = map.get(elem_to_find);
					for (Integer k : cur_index_list) {
						for(Integer m : elem_to_find_index_list) {
							result.add(Arrays.asList(k, m));
						}
					}
					// delete the cur and element_to_find from HashMap
					map.remove(cur);
					map.remove(elem_to_find);
				}
				
			}
		}
		
		return result;
		
	}
	
	/**
	 * Find all pairs of elements in a given array that sum to the pair the given target number.
	 * Return all the distinct pairs of values.
	 * 
	 */
	public static List<List<Integer>> twoSumAllPairs2(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int num : array) {
			// two cases
			// if x * 2 == target, we need to make sure that the count == 1 <== already 
			// if x + y == target, and this is the first time for x or y are present
			// so we can make sure that there is no duplicate
			Integer count = map.get(num);
			if (num * 2 == target && count != null && count == 1) {
				result.add(Arrays.asList(num, num));
			} else if (map.containsKey(target - num) && count == null) {
				result.add(Arrays.asList(target - num, num));
			}
			
			// add the num into map
			if (count == null) {
				map.put(num, 1);
			} else {
				map.put(num, count + 1);
			}
		}
		return result;
	}
	

	/**
	 * 4 Sum
	 * Determine if there exists a set of four elements in a given array that sum to the given target number.
	 * Assumptions
	 * The given array is not null and has length of at least 4
	 * Examples
	 * A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)
	 * A = {1, 2, 2, 3, 4}, target = 12, return false
	 * 
	 * 
	 */
	public static boolean fourSum(int[] array, int target) {
		if (array == null || array.length < 4) {
			return false;
		}
		// key: value ==> sum, <leftIndex, rightIndex>
		// if there is duplicate sum, only store the <leftIndex, rightIndex> which rightIndex is smallest
		// this helps us to avoid overlap of two pairs <leftIndex1, rightIndex1> <leftIndex2, rightIndex2>
		HashMap<Integer, Pair> map = new HashMap<Integer, Pair>();
		
		// traverse the array
		for (int j = 1; j < array.length; j++) {
			for (int i = 0; i < j; i++) {
				int leftIndex = i, rightIndex = j;
				int curSum = array[leftIndex] + array[rightIndex];
				// if target - curSum is already in the map
				// and 
				// the right bound of target - curSum is smaller than leftIndex
				// ===> there is no overlap
				if (map.containsKey(target - curSum) && map.get(target - curSum).right < leftIndex) {
					return true;
				}
				
				if (!map.containsKey(curSum)) {
					map.put(curSum, new Pair(leftIndex, rightIndex));
				}
			}
		}
		return false;
	}

	public static class Pair{
		int left;
		int right;
		public Pair(int _l, int _r) {
			this.left = _l;
			this.right = _r;
		}
		@Override
		public String toString() {
			return "[ " + left + "  " + right + " ]";
		}
	}
}

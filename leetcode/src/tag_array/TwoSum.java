package tag_array;

import java.util.HashMap;
import java.util.Map.Entry;

public class TwoSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Two Sum III - Data structure design Design and implement a TwoSum class.
	 * It should support the following operations: add and find. 
	 * add - Add the number to an internal data structure. 
	 * find - Find if there exists any pair of numbers which sum is equal to the value.
	 * 
	 * add(1); add(3); add(5); 
	 * find(4) -> true 
	 * find(7) -> false
	 */
	

	// use hashMap to store the element and their counter.
	private HashMap<Integer, Integer> map;

	/**
	 * Initialize your data structure here.
	 */
	public TwoSum() {
		map = new HashMap<>();
	}

	/**
	 * Add the number to an internal data structure..
	 * Time: O(1)
	 */
	public void add(int number) {
		if (map.containsKey(number)) {
			map.put(number, map.get(number) + 1);
		} else {
			map.put(number, 1);
		}
	}

	/**
	 * Find if there exists any pair of numbers which sum is equal to the value.
	 * Time: O(n)
	 */
	public boolean find(int value) {
		// traverse the map
		for(Entry<Integer, Integer> entry: map.entrySet()) {
			int target = value - entry.getKey();
			if (target == entry.getKey()) {
				if (map.get(target) >= 2) {
					return true;
				}
			} else {
				if (map.containsKey(target)) {
					return true;
				}
			}
		}
		return false;
	}

}

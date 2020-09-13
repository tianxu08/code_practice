package sortbynum;

import java.util.HashMap;

public class Task151_200_TwoSum3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// HashMap<Key, counter>   counter is the appearance of key, for the same elements. 
	private HashMap<Integer, Integer> map;
	public Task151_200_TwoSum3() {
		map = new HashMap<Integer, Integer>();
	}

	// Add the number to an internal data structure.
	public void add(int number) {
		int counter = 0;
		if (map.containsKey(number)) {
			counter = map.get(number);
		}
		map.put(number, counter + 1);
	}

	// Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		for(Integer i: map.keySet()) {
			int target = value - i;
			if (map.containsKey(target)) {
				if (i == target && map.get(target) < 2) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}
}

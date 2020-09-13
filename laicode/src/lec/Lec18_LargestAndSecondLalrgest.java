package lec;

import java.util.*;

public class Lec18_LargestAndSecondLalrgest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		int[] array = {2, 1, 5, 4, 3};
		int[] result = largestAndSecond(array);
		for(int i: result) {
			System.out.println(i);
		}
		System.out.println("----");
		System.out.println(result.length);
	}

	public static int[] largestAndSecond(int[] array) {
		// write your solution here
		// return a Pair object, the field first is the largest
		if (array == null || array.length == 0) {
			return null;
		}
		List<Pair> list = new ArrayList<Pair>();
		for (int i = 0; i < array.length; i++) {
			// pair<index, value>
			list.add(new Pair(i, array[i]));
		}
		// <Index, Smaller list of This Integer whose index is Index>
		
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		while (list.size() > 1) {
			List<Pair> nextRound = new ArrayList<Pair>();
			for (int i = 0; i < list.size(); i += 2) {
				if (i + 1 < list.size()) {
					Pair p1 = list.get(i);
					Pair p2 = list.get(i + 1);
					// p1.val <= p2.val, p2 win
					if (p1.second <= p2.second) {
						// p2 win, add p2 to the nextRound
						nextRound.add(p2);
						// if this p2.first is not in 
						if (!map.containsKey(p2.first)) {
							map.put(p2.first, new ArrayList<Integer>());
						}
						// add p2's smaller to p2's smaller list
						map.get(p2.first).add(p1.second);
					} else {
						// p1 win
						nextRound.add(p1);
						if (!map.containsKey(p1.first)) {
							map.put(p1.first, new ArrayList<Integer>());
						}
						// add p1's smaller to p1's smaller list
						map.get(p1.first).add(p2.second);
					}
				} else {
					// add the last pair into nextRound if list.size() is odd
					nextRound.add(list.get(i));
				}
			}
			list = nextRound;
		}
		int secondMax = max(map.get(list.get(0).first));
		System.out.println("secondMax = " + secondMax);
		return new int[] { list.get(0).second, secondMax };

	}

	public static int max(List<Integer> list) {
		int max = list.get(0);
		for (Integer i : list) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public static class Pair {
		public int first;
		public int second;

		public Pair(int x, int y) {
			this.first = x;
			this.second = y;
		}
	}

}

package sortbynum;

import java.util.ArrayList;
import java.util.HashMap;

public class Task244_WordDistanceII {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		Task244_WordDistanceII wd = new Task244_WordDistanceII(words);
		String word1 = "practice";
		String word2 = "coding";
		int rev = wd.shortest(word1, word2);
		System.out.println("rev = " + rev);
	}
	
	/**
	 * Time: O(m + n)
	 * Space: O(n)
	 */
	
	
	// key: value ==> string, list of indices of that string
	HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
	
	public Task244_WordDistanceII(String[] words) {
		for(int i = 0; i < words.length; i ++) {
			String curStr = words[i];
			if (!map.containsKey(curStr)) {
				map.put(curStr, new ArrayList<Integer>());
			}
			map.get(curStr).add(i);
		}
	}

	
	public int shortest(String word1, String word2) {
		ArrayList<Integer> array1 = map.get(word1);
		ArrayList<Integer> array2 = map.get(word2);
		
		return getShortestDist(array1, array2);
	}
	
	
	/*
	 * 
	 * Time: O(m + n)  m is the length of array1, n is the length of array2
	 * 
	 * reduce to find the minimum distance between to sorted array
	 * 
	 * ==> move the smaller one. 
	 */
	private int getShortestDist(ArrayList<Integer> array1, ArrayList<Integer> array2) {
		int i = 0, j = 0;
		int minDist = Integer.MAX_VALUE;
		while(i < array1.size() && j < array2.size()) {
			int curDiff =Math.abs( array1.get(i) - array2.get(j));
			if (curDiff < minDist) {
				minDist = curDiff;
			}
			
			if (array1.get(i) < array2.get(j)) {
				i ++;
			} else {
				j ++;
			}
		}
		
		return minDist;
	}

}

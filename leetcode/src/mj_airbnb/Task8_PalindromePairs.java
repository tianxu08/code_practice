package mj_airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Task8_PalindromePairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/*
	 * there are several cases to be considered that is Palindrome(s1 + s2)
	 * 1: if s1 is a blank string, then any string that is palindrom s2 --> s1 + s2, s2 + s1 are palindrome
	 * 2: if s2 is the reverse string of s1, then s1 + s2 and s2 + s1 are palindrome
	 * 3: if s1[0..i] is palindrome && there the s1[i + 1.. n -1] is the reverse string of s2 --> s2 + s1 is palindrome
	 * 4: similar to 3, 
	 *    if s1[i + 1, n] is palindrome and s1[0..i] is the reverse string os s2 --> s1 + s2 is palindrome
	 * 
	 * To make things faster, use hashMap to store string and its index
	 */
	public static List<List<Integer>> task336_palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (words == null || words.length == 0) {
			return result;
		}
		// map: key-value: String -index
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		// map<String, index in the array>
		for(int i = 0; i < words.length; i ++) {
			map.put(words[i], i);
		}
		
		// deal with case1:
		if (map.containsKey("")) {
			int blankIdx = map.get("");
			// traverse the String[] words, find the palindrome
			for (int i = 0; i < words.length; i++) {
				if (task336_isPalindrom(words[i])) {
					if (i == blankIdx) {
						continue;
					}
					result.add(Arrays.asList(blankIdx, i));
					result.add(Arrays.asList(i, blankIdx));
				}
			}
		}
		
		// case2: find all string and reverse string pairs
		for(int i = 0; i < words.length; i++) {
			String curStr = words[i];
			String curStrR = new StringBuilder(curStr).reverse().toString();
			if (map.containsKey(curStrR)) {
				int indexR = map.get(curStrR);
				// make sure that indexR != i
				if (indexR == i) {
					continue;
				}
				result.add(Arrays.asList(i, indexR));
			}
		}
		
		
		// case3  s1[0..i] is palindrome && s1[i + 1, n - 1] == reverse(s2) ---> (s2, s1)
		// case4  s1[i + 1..n - 1] is palindrome && s1[0..i] == reverse(s2) ---> (s1, s2)
		for (int i = 0; i < words.length; i ++) {
			String cur = words[i];
			for (int j = 1; j < cur.length(); j ++) {
				String curLeft = cur.substring(0, j);   // the cut index is j  !!!
				String curRight = cur.substring(j);
				
				if (task336_isPalindrom(curLeft)) {
					String curRightR = new StringBuilder(curRight).reverse().toString();
					if (map.containsKey(curRightR)) {
						int curRightRIdx = map.get(curRightR);
						if (curRightRIdx == i) {
							continue;
						}
						result.add(Arrays.asList(curRightRIdx, i));
					}
				}
				
				if (task336_isPalindrom(curRight)) {
					String curLeftR = new StringBuilder(curLeft).reverse().toString();
					if (map.containsKey(curLeftR)) {
						int curLeftRIdx = map.get(curLeftR);
						if (curLeftRIdx == i) {
							continue;
						}
						result.add(Arrays.asList(i, curLeftRIdx));
					}
				}
			}
		}	
		return result;
	}
		
	public static boolean task336_isPalindrom(String input) {
		int left = 0, right = input.length() -1;
		while(left <= right) {
			if (input.charAt(left) != input.charAt(right)) {
				return false;
			}
			left ++;
			right --;
		}
		return true;
	}

}

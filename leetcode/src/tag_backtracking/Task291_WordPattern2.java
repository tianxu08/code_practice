package tag_backtracking;
import java.util.*;

public class Task291_WordPattern2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task291_WordPattern2 sln = new Task291_WordPattern2();
		String pattern = "abbb";
		String str = "xyzabcxzyabc";
		boolean rev = sln.wordPatternMatch(pattern, str);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * Given a pattern and a string str, find if str follows the same pattern.
	 * Here follow means a full match, such that there is a bijection between a letter 
	 * in pattern and a non-empty substring in str.
	 * Examples:
	 * pattern = "abab", str = "redblueredblue" should return true.
	 * pattern = "aaaa", str = "asdasdasdasd" should return true.
	 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
	 * Notes:
	 * You may assume both pattern and str contains only lowercase letters.
	 */
	
	public boolean wordPatternMatch(String pattern, String str) {
		Map<Character, String> map = new HashMap<>();
		return helper(pattern, 0, str, 0, map);
	}
	
	public boolean helper(String pattern, int pIndex, String str, int sIndex, Map<Character, String> map) {
		// successful condition
		// if pIndex and sIndex all reach the end, return true
		if (pIndex == pattern.length() && sIndex == str.length()) {
			return true;
		}
		// if only one of pIndex and sIndex reach the end, return false
		if (pIndex == pattern.length() || sIndex == str.length()) {
			return false;
		}
		
		char patCurCh = pattern.charAt(pIndex);
		// try to find a substring in str to match pCh
		for(int i = sIndex; i < str.length(); i ++) {
			String strCurSub = str.substring(sIndex, i + 1);
			if (map.containsKey(patCurCh) && map.get(patCurCh).equals(strCurSub)) {
				// the map have <patCurCh, strCurSub>
				// continue to match
				if (helper(pattern, pIndex + 1, str, i + 1, map)) {
					return true;
				}
			}
			// if map doesn't containsKey(patCurCh) and map doesn't containsValue(strCurSub)
			if (!map.containsKey(patCurCh) && !map.containsValue(strCurSub) ) {
				map.put(patCurCh, strCurSub);
				if (helper(pattern, pIndex + 1, str, i + 1, map)) {
					return true;
				}
				map.remove(patCurCh);
			}
		}
		return false;
	}

}

package tag_backtracking;

import java.util.*;

public class Task17_LetterCombinationsOfPhoneNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test17();

	}
	
	/*
	 * Given a digit string, return all possible letter combinations that the number could represent.
	 * A mapping of digit to letters (just like on the telephone buttons) is given below.
	 * 
	 * Input:Digit string "23"
	 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	 */
		
	/*
	 * task17 Letter Combinations of a Phone Number
	 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
	 */
	public static void test17() {
		String digits = "222";
		List<String> result = task17_letterCombinations(digits);
		System.out.println(result);
	}

	public static List<String> task17_letterCombinations(String digits) {
		List<String> result = new ArrayList<String>();
		if (digits == null || digits.length() == 0) {
			return result;
		}

		StringBuilder stb = new StringBuilder();
		// build the map
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");

		task17_helper(digits, stb, 0, map, result);
		return result;

	}

	public static void task17_helper(String digits,
									 StringBuilder stb,
									 int index,
									 HashMap<Integer, String> map,
									 List<String> result) {
		if (index == digits.length()) {
			// get a reasonable result
			String oneresult = stb.toString();
			result.add(oneresult);
			return;
		}
		Integer curVal = (int) digits.charAt(index) - '0';
		String curStr = map.get(curVal);
		for (int i = 0; i < curStr.length(); i++) {
			stb.append(curStr.charAt(i));
			task17_helper(digits, stb, index + 1, map, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	/*
	 * follow up
	 * iterative
	 */
	public static void test17_2() {
		String str = "23";
		List<String> rev1 = letterCombinations(str);
		System.out.println(rev1);
		System.out.println("----------------");
		List<String> rev2 = task17_letterCombinations(str);
		System.out.println(rev2);
	}
	public static List<String> letterCombinations(String digits) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");
        LinkedList<String> list = new LinkedList<String>();
        list.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            int size = list.size();
            for (int k = 0; k < size; k++) {
                String tmp = list.pop();
                for (int j = 0; j < map.get(num).length(); j++)
                    list.add(tmp + map.get(num).charAt(j));
            }
        }
        List<String> rec = new LinkedList<String>();
        rec.addAll(list);
        return rec;
    }

	// follow up
	// additional requirement: no duplicate set of characters for the same
	// number.
	// HashMap<Integer, HashSet<Character>> visited 用这个来去重复
	public static void test17_1() {
		String digits = "222";
		List<String> result = task17_letterCombinations2(digits);
		System.out.println(result);
	}

	public static List<String> task17_letterCombinations2(String digits) {
		List<String> result = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return result;
		}

		StringBuilder stb = new StringBuilder();
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");

		HashMap<Integer, HashSet<Character>> visited = new HashMap<Integer, HashSet<Character>>();

		task17_helper2(digits, stb, 0, map, result, visited);
		return result;

	}

	public static void task17_helper2(String digits, StringBuilder stb,
			int index, HashMap<Integer, String> map, List<String> result,
			HashMap<Integer, HashSet<Character>> visited) {
		if (index == digits.length()) {
			// get a reasonable result
			String oneresult = stb.toString();
			result.add(oneresult);
			return;
		}
		Integer curVal = (int) digits.charAt(index) - '0';
		String curStr = map.get(curVal);

		for (int i = 0; i < curStr.length(); i++) {
			char curCh = curStr.charAt(i);
			if (visited.containsKey(curVal) && visited.get(curVal).contains(curCh)) {
				continue;
			}
			stb.append(curCh);
			if (!visited.containsKey(curVal)) {
				visited.put(curVal, new HashSet<>());
			}
			visited.get(curVal).add(curCh);
			task17_helper2(digits, stb, index + 1, map, result, visited);

			stb.deleteCharAt(stb.length() - 1);
			visited.get(curVal).remove(curCh);
		}
	}
}

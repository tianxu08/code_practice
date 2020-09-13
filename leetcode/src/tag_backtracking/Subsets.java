package tag_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
	
	public static List<String> subSets(String set) {
		List<String> result = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		helper(set, 0, sb, result);
		return result;
	}
	
	public static void helper(String set, int index, StringBuilder sb, List<String> result) {
		if (index == set.length()) {
			// get the string
			result.add(sb.toString());
			return ;
		}
		helper(set, index + 1, sb, result);
		
		sb.append(set.charAt(index));
		helper(set, index + 1, sb, result);
		sb.deleteCharAt(sb.length() - 1);
	}
	
	
	/*
	 * another DFS
	 */
	public static void test2() {
		String set = "abc";
		List<String> result = subSet_another(set);
		System.out.println("result size = " + result.size());
	}
	public static List<String> subSet_another(String set) {
		char[] setArr = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		int index = 0;
		List<String> result = new ArrayList<String>();
		helper2(setArr, sb, index, result);
		return result;
	}
	public static void helper2(char[] set, StringBuilder sb, int index, List<String> result) {
		result.add(sb.toString());
		System.out.println(sb.toString());
		// 
		for(int i = index; i < set.length; i ++) {
			sb.append(set[i]);
			helper2(set, sb, i + 1, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	
	
	// subset with duplicates
	public static List<String> subSet_another2(String set) {
		char[] setArr = set.toCharArray();
		Arrays.sort(setArr);
		StringBuilder sb = new StringBuilder();
		int index = 0;
		List<String> result = new ArrayList<String>();
		helper22(setArr, sb, index, result);
		return result;
	}
	public static void helper22(char[] set, StringBuilder sb, int index, List<String> result) {
		result.add(sb.toString());
		System.out.println(sb.toString());
		// 
		for(int i = index; i < set.length; i ++) {
			if (i != index && set[i] == set[i - 1]) {
				continue;
			}
			sb.append(set[i]);
			helper22(set, sb, i + 1, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

}

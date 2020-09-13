package dfs;
import java.util.*;

public class AllSubset1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
		test2();
	}
	
	public static void test() {
		
		List<String> result = subSets("abc");
		System.out.println(result);
	}
	
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

}

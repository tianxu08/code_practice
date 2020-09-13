package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubSet2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	public static void test1() {
		String set = "aab";
		List<String> result = subSet_another(set);
		System.out.println(result);
	}
	
	public static List<String> subSet_another(String set) {
		char[] setArr = set.toCharArray();
		Arrays.sort(setArr);
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
			if (i != index && set[i] == set[i - 1]) {
				continue;
			}
			sb.append(set[i]);
			helper2(set, sb, i + 1, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

}

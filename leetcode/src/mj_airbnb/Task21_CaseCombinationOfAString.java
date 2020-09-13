package mj_airbnb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task21_CaseCombinationOfAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Bit Manipulation
	private static List<String> caseCombination(String input) {
		List<String> result = new ArrayList<>();
		char[] arr = input.toCharArray();
		int n = input.length();
		for (int i = 0; i < (1 << n); i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < n; j++) {
				int bit = (i >> j) & 1;
				sb.append(bit == 1 ? Character.toUpperCase(arr[j]) : Character.toLowerCase(arr[j]));
			}
			result.add(sb.toString());
		}
		return result;
	}

	// DFS
	private static List<String> caseCombination2(String input) {
		List<String> result = new ArrayList<>();
		dfs(input, 0, new StringBuilder(), result);
		return result;
	}

	private static void dfs(String input, int i, StringBuilder sb, List<String> result) {
		if (i == input.length()) {
			result.add(new String(sb.toString()));
			return;
		}
		// 不需要for循环和startIndex
		char c = input.charAt(i);
		sb.append(Character.toUpperCase(c));
		dfs(input, i + 1, sb, result);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(Character.toLowerCase(c));
		dfs(input, i + 1, sb, result);
		sb.deleteCharAt(sb.length() - 1);
	}

}

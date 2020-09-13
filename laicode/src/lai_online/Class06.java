package lai_online;

import java.util.*;

public class Class06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test7();
//		test7_1();
//		test8();
		test9();
//		test10();
	}
	
	
	/*
	 * task7
	 * All Subsets I
	 * Given a set of characters represented by a String, return a list containing all subsets of the characters.
	 * Assumptions
	 * There are no duplicate characters in the original set.
	 * ​Examples
	 * Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
	 * Set = "", all the subsets are [""]
	 * Set = null, all the subsets are []
	 * 
	 */
	public static void  test7() {
		String set = "abc";
		List<String> result = task7_subsets(set);
		System.out.println(result);
	}
	
	public static List<String> task7_subsets(String set) {
		ArrayList<String> result = new ArrayList<String>();
		if (set ==null || set.length() == 0) {
			return result;
		}
		StringBuilder sb = new StringBuilder();
		task7_helper(set, 0, sb, result);
		return result;
	}
	
	// this helper function, we use a StringBuilder for the intermediate result
	public static void task7_helper(String set, int index, StringBuilder sb, List<String> result) {
		if (index == set.length()) {
			result.add(sb.toString());
			return ;
		}
		task7_helper(set, index + 1, sb, result);
		sb.append(set.charAt(index));
		task7_helper(set, index + 1, sb, result);
		sb.deleteCharAt(sb.length() - 1);
	}
	
	// another method
	public static void test7_1() {
		String set = "abc";
		List<String> result = task7_1_subsets(set);
		System.out.println(result);
	}
	
	public static List<String> task7_1_subsets(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		StringBuilder stb = new StringBuilder();
		task7_1_helper(input, stb, result, 0);
//		helperII(input, stb, 0, result);
		System.out.println(result);
		return result;
	}
	public static int count = 0;
	public static void task7_1_helper(char[] set, StringBuilder stb, List<String> result, int index) {
		System.out.println(stb.toString() + " count = " + count ++);
		// choose what is the index in the original set to pick
		// maintain the ascending order of the picked indices
		result.add(stb.toString());
		
		for(int i = index; i < set.length; i ++) {
			stb.append(set[i]);
			task7_1_helper(set, stb, result, i + 1);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	public static void helperII(char[] set, StringBuilder sb, int index, List<String> result) {
		result.add(sb.toString());
		// choose what is the index in the original set to pick.
		// maintain the ascending order of the picked indices.
		for (int i = index; i < set.length; i++) {
			sb.append(set[i]);
			helperII(set, sb, i + 1, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	
	/*
	 * task8
	 * Subset2
	 */
	
	public static void test8() {
		String set = "aab";
		List<String> result = task8_subsetII(set);
		System.out.println(result);
	}
	
	public static List<String> task8_subsetII(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		Arrays.sort(input);
		StringBuilder stb = new StringBuilder();
		task8_helper(input, 0, stb, result);
		return result;	
	}
	
	public static void task8_helper(char[] set, int index, StringBuilder stb, List<String> result) {
		result.add(stb.toString());
		for(int i = index; i < set.length; ++i) {
			if (i == index || set[i] != set[i - 1]) {
				stb.append(set[i]);
				task8_helper(set, i + 1, stb, result);
				stb.deleteCharAt(stb.length() - 1);
			}
		}
	}
	
	
	
	/*
	 * task9
	 * All permutations I
	 * 
	 */
	public static void test9() {
		String str = "abc";
		List<String> result = task9_permutations(str);
		System.out.println(result);
	}
	
	public static List<String> task9_permutations(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		task9_helper(input, 0, result);
		System.out.println(result);
		return result;
	}
	
	public static void task9_helper(char[] set, int index, List<String> result) {
		System.out.println(new String(set));
		if (index == set.length) {
			String str = new String(set);
			result.add(str);
			return ;
		}
		for(int i = index; i < set.length; i ++) {
			swap(set, index, i);
			task9_helper(set, index + 1, result);
			swap(set, index, i);
		}
	}
	
	public static void swap(char[] set, int x, int y) {
		char temp = set[x];
		set[x] = set[y];
		set[y] = temp;
	}
	
	
	/*
	 * task10
	 * permutation2 with duplicate
	 */
	public static void test10() {
		String str = "abb";
		List<String> result = task10_permutationsII(str);
		System.out.println(result);
	}
	
	public static List<String> task10_permutationsII(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		task10_helperII(input, 0, result);
		System.out.println(result);
		return result;
	}
	
	public static void task10_helperII(char[] set, int index, List<String> result) {
		if (index == set.length) {
			String str = new String(set);
			result.add(str);
			return ;
		}
		HashSet<Character> used = new HashSet<Character>();
		// 每一层放一个 hashset, 用来指示： 在某这个位置，放没放过某一个char
		
		for(int i = index; i < set.length; i ++) {
			if(!used.contains(set[i])) {
				// 在index 上没有放过set[i]
				used.add(set[i]);
				swap(set, index, i);
				task10_helperII(set, index + 1, result);
				swap(set, index, i);
			}
			
		}
	}
	
	
	
	/*
	 * task11
	 * All Valid Permutations Of Parentheses I Fair Recursion
	 * Given N pairs of parentheses “()”, return a list with all the valid permutations.
	 * Assumptions
	 * N >= 0
	 * Examples
	 * N = 1, all valid permutations are ["()"]
	 * N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
	 * N = 0, all valid permutations are [""]
	 */
	public static List<String> task11_validParentheses(int n) {
		// write your solution here
		List<String> result = new ArrayList<String>();

		char[] cur = new char[n * 2];
		task11_helper(cur, n, n, 0, result);

		return result;
	}

	/*
	 * 
	 * left = "(" num remaining 
	 * right = ")" num remaining
	 */
	public static void task11_helper(char[] cur, int left, int right, int index,
			List<String> result) {
		if (left == 0 && right == 0) {
			// find a solution
			result.add(new String(cur));
			return;
		}

		if (left > 0) {
			cur[index] = '(';
			task11_helper(cur, left - 1, right, index + 1, result);
		}

		if (right > left) {
			cur[index] = ')';
			task11_helper(cur, left, right - 1, index + 1, result);
		}
	}
	
	
	
	/*
	 * task12
	 * All Valid Permutations Of Parentheses II
	 * Hard Recursion 
	 * Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.
	 * Assumptions
	 * l, m, n >= 0
	 * Examples
	 * l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]
	 */
	
	public static List<String> validParentheses(int l, int m, int n) {
		// write your solution here
		final char[] PS = { '(', ')', '[', ']', '{', '}' };
		int[] remaining = { l, l, m, m, n, n };

		// when we add right parentheses, we need to use the stack to make sure
		// they are in the same type
		Deque<Character> stack = new LinkedList<Character>();

		StringBuilder cur = new StringBuilder();

		List<String> result = new ArrayList<String>();

		int targetLen = 2 * l + 2 * m + 2 * n;

		task12_helper(remaining, PS, stack, cur, targetLen, result);
		return result;
	}

	public static void task12_helper(int[] remaining, char[] PS,
			Deque<Character> stack, StringBuilder cur, int targetLen,
			List<String> result) {
		if (cur.length() == targetLen) {
			// get a solution
			result.add(cur.toString());
			return;
		}

		for (int i = 0; i < remaining.length; i++) {
			if (i % 2 == 0) {
				// add left parentheses
				if (remaining[i] > 0) {
					cur.append(PS[i]);
					remaining[i]--;
					stack.offerFirst(PS[i]); // push the PS[i] into stack
					task12_helper(remaining, PS, stack, cur, targetLen, result);

					// backtrack
					remaining[i]++;
					cur.deleteCharAt(cur.length() - 1);
					stack.pollFirst();
				}
			} else {
				// add right parentheses
				if (!stack.isEmpty() && stack.peekFirst().equals(PS[i - 1])) {
					// stack is NOT empty and right parentheses matches the left parentheses
					cur.append(PS[i]);
					remaining[i]--;
					stack.pollFirst();
					task12_helper(remaining, PS, stack, cur, targetLen, result);

					stack.offerFirst(PS[i - 1]);
					remaining[i]++;
					cur.deleteCharAt(cur.length() - 1);
				}
			}
		}
	}
	
	
	/*
	 * task13
	 * Combinations Of Coins
	 * Fair Recursion
	 * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.
	 * Arguments
	 * coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
	 * target - a positive integer representing the target number of cents, eg. 99
	 * Assumptions
	 * coins is not null and is not empty, all the numbers in coins are positive
	 * target >= 0
	 * You have infinite number of coins for each of the denominations, you can pick any number of the coins.
	 * Return
	 * a list of ways of combinations of coins to sum up to be target.
	 * each way of combinatoins is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
	 * Examples
	 * coins = {2, 1}, target = 4, the return should be
	 * [
	 * [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)
	 * [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)
	 * [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)
	 * ]
	 */
	
	public static List<List<Integer>> task13_combinations(int target, int[] coins) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		task13_helper(target, coins, 0, cur, result);
		return result;
	}

	public static void task13_helper(int target, int[] coins, int index, List<Integer> cur,
			List<List<Integer>> result) {
		if (index == coins.length - 1) {
			if (target % coins[coins.length - 1] == 0) {
				cur.add(target / coins[coins.length - 1]);
				result.add(new ArrayList<Integer>(cur));
				cur.remove(cur.size() - 1);
			}
			return;
		}
		int max = target / coins[index];
		for (int i = 0; i <= max; i++) {
			cur.add(i);
			task13_helper(target - i * coins[index], coins, index + 1, cur, result);
			cur.remove(cur.size() - 1);
		}
	}
	
	
	/*
	 * task14
	 * N Queens
	 * Fair Recursion
	 * Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.
	 * Assumptions
	 * N > 0
	 * Return
	 * A list of ways of putting the N Queens
	 * Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
	 * Example
	 * N = 4, there are two ways of putting 4 queens:
	 * [1, 3, 0, 2] --> the Queen on the first row is at y index 1, 
	 * the Queen on the second row is at y index 3, the Queen on the third row is at y index 0 
	 * and the Queen on the fourth row is at y index 2.
	 * 
	 * [2, 0, 3, 1] --> the Queen on the first row is at y index 2, 
	 * the Queen on the second row is at y index 0, the Queen on the third row is at y index 3 
	 * and the Queen on the fourth row is at y index 1.
	 */
	
	public List<List<Integer>> nqueens(int n) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		helper(0, n, cur, result);
		return result;
	}

	public void helper(int index, int n, List<Integer> cur,
			List<List<Integer>> result) {
		if (index == n) {
			result.add(new ArrayList<Integer>(cur));
			return;
		}
		for (int i = 0; i < n; i++) {
			cur.add(i);
			if (isValid(cur)) {
				helper(index + 1, n, cur, result);
			}
			cur.remove(cur.size() - 1);
		}
	}

	// check whether add the last element, the cur List is still valid
	public boolean isValid(List<Integer> cur) {
		int size = cur.size();
		for (int i = 0; i < size - 1; i++) {
			int rowIndex = i;
			int colIndex = cur.get(i);
			int targetRowIndex = size - 1;
			int targetColIndex = cur.get(targetRowIndex);
			// not in the same row, same column, same diag
			// since i != size - 1, so cannot be the same row
			// just check same column, same diag and counter-diag
			if (colIndex == targetColIndex
					|| rowIndex + colIndex == targetColIndex + targetRowIndex
					|| rowIndex - colIndex == targetRowIndex - targetColIndex) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	

}


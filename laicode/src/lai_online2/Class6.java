package lai_online2;
import java.util.*;

public class Class6 {
	/*
	 * task1
	All Subsets I
	Fair
	Recursion
	Given a set of characters represented by a String, return a list containing all subsets of the characters.

	Assumptions

	There are no duplicate characters in the original set.
	​Examples

	Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
	Set = "", all the subsets are [""]
	Set = null, all the subsets are []
	*/
	public List<String> subSets(String set) {
		// write your solution here

		ArrayList<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		ArrayList<Character> path = new ArrayList<Character>();
		task7_helper(set, 0, path, result);
		return result;
	}

	public void task7_helper(String set, int index, ArrayList<Character> path,
			ArrayList<String> result) {
		if (index == set.length()) {
			// get the string
			String str = task7_getString(path);
			result.add(str);
			return;
		}
		// don't add set[index] into path
		task7_helper(set, index + 1, path, result);

		// add set[index] into path
		path.add(set.charAt(index));
		task7_helper(set, index + 1, path, result);
		path.remove(path.size() - 1);
	}

	public String task7_getString(ArrayList<Character> path) {
		StringBuilder stb = new StringBuilder();
		for (Character ch : path) {
			stb.append(ch);
		}
		return stb.toString();
	}
	
	/*
	task2
	All Permutations I
	Fair
	Recursion
	Given a string with no duplicate characters, return a list with all permutations of the characters.

	Examples

	Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
	Set = "", all permutations are [""]
	Set = null, all permutations are []
	*/
	public List<String> permutations(String set) {
		// write your solution here
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		task9_helper(input, 0, result);
		System.out.println(result);
		return result;
	}

	public void task9_helper(char[] set, int index, List<String> result) {
		if (index == set.length) {
			String str = new String(set);
			result.add(str);
			return;
		}
		for (int i = index; i < set.length; i++) {
			swap(set, index, i);
			task9_helper(set, index + 1, result);
			swap(set, index, i);
		}
	}

	public void swap(char[] set, int x, int y) {
		char temp = set[x];
		set[x] = set[y];
		set[y] = temp;
	}
	
	/*
	task3
	All Valid Permutations Of Parentheses I
	Fair
	Recursion
	Given N pairs of parentheses “()”, return a list with all the valid permutations.

	Assumptions

	N >= 0
	Examples

	N = 1, all valid permutations are ["()"]
	N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
	N = 0, all valid permutations are [""]
	*/
	public List<String> validParentheses(int n) {
		// write your solution here
		List<String> result = new ArrayList<String>();

		char[] cur = new char[n * 2];
		helper(cur, n, n, 0, result);

		return result;
	}

	public void helper(char[] cur, int left, int right, int index,
			List<String> result) {
		if (left == 0 && right == 0) {
			// find a solution
			result.add(new String(cur));
			return;
		}

		if (left > 0) {
			cur[index] = '(';
			helper(cur, left - 1, right, index + 1, result);
		}

		if (right > left) {
			cur[index] = ')';
			helper(cur, left, right - 1, index + 1, result);
		}
	}
	
	/*
	task4
	Combinations Of Coins
	Fair
	Recursion
	Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.

	Arguments

	coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
	target - a positive integer representing the target number of cents, eg. 99
	Assumptions

	coins is not null and is not empty, all the numbers in coins are positive
	target >= 0
	You have infinite number of coins for each of the denominations, you can pick any number of the coins.
	Return

	a list of ways of combinations of coins to sum up to be target.
	each way of combinatoins is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
	Examples

	coins = {2, 1}, target = 4, the return should be

	[

	  [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)

	  [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)

	  [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)

	]
	*/

	public List<List<Integer>> combinations(int target, int[] coins) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		helper(target, coins, 0, cur, result);
		return result;
	}

	public void helper(int target, int[] coins, int index, List<Integer> cur,
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
			helper(target - i * coins[index], coins, index + 1, cur, result);
			cur.remove(cur.size() - 1);
		}
	}
}

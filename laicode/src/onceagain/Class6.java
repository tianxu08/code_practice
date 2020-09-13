package onceagain;

import java.util.*;

public class Class6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test1_1();
//		test2();
//		test2_1();
//		test3();
//		test4();
		test4_1();
	}
	
	/*
	 * task1 all subsets
	 */
	public static List<String> task1_subSets(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null || set.length() == 0) {
			return result;
		}
		char[] setArr = set.toCharArray();
		StringBuilder stb = new StringBuilder();
		task1_helper(setArr, result, stb, 0);
		return result;
	}
	
	public static void task1_helper(char[] set, List<String> result, StringBuilder stb, int index) {
		System.out.println(stb.toString());
		result.add(stb.toString());
		
		/*
		 * choose what is the index in the original set to pick
		 * maintain the ascending order of the picked indices
		 */
		for(int i = index; i < set.length; i ++) {
			stb.append(set[i]);
			task1_helper(set, result, stb, i + 1);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	public static void test1() {
		String set = "abc";
		List<String> result = task1_subSets(set);
		System.out.println(result);
	}
	
	
	/*
	 * task1.1 all subsets with duplicates
	 */
	public static List<String> task1_1_allSubsets(char[] set) {
		Arrays.sort(set);
		StringBuilder stb = new StringBuilder();
		List<String> result = new ArrayList<String>();
		task1_1_helper(set, stb, 0, result);
		return result;
	}
	
	public static void task1_1_helper(char[] set, StringBuilder stb, int index, List<String> result) {
		result.add(stb.toString());
		
		for(int i = index; i < set.length; i ++) {
			if (i != index && set[i] == set[i - 1]) {
				continue;
			}
			stb.append(set[i]);
			task1_1_helper(set, stb, i + 1, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	public static void test1_1() {
		char[] set = "aabbc".toCharArray();
		List<String> result = task1_1_allSubsets(set);
		System.out.println(result);
	}
	
	/*
	 * task2 all valid permutation of parentheses I
	 */
	public static List<String> task2_validParen(int k) {
		List<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		char[] temp = new char[2*k];
		int index = 0;
		task2_helper(k, k, result, temp, index);
		return result;
	}
	public static void task2_helper(int leftR, int rightR, List<String> result, char[] temp, int index) {
		if (leftR == 0 && rightR == 0) {
			result.add(new String(temp));
			return ;
		}
		if (leftR > 0) {
			temp[index] = '(';
			task2_helper(leftR - 1, rightR, result, temp, index + 1);
		}
		if (leftR < rightR) {
			temp[index] = ')';
			task2_helper(leftR, rightR - 1, result, temp, index + 1);
		}
	} 
	/*
	 * NOTE: 字符串长度确定， 直接开一个2*k 长度的char Array. 
	 */
	
	public static void test2() {
		int k = 2;
		List<String> result = task2_validParen(k);
		System.out.println(result);
	}
	
	/*
	 * task2.1 all valid permutation of ()[]{}
	 * Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.
	 * Assumptions
	 * l, m, n >= 0
	 * Examples
	 * l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]
	 */
	public static List<String> task2_1_allValidPermutation(int l, int m, int n) {
		char[] dict = {'(',')', '[',']' , '{', '}'};
		int[] remaining = {l, l,  m, m, n, n};
		
		LinkedList<Character> stack = new LinkedList<Character>();
		int len = 2 * l + 2 * m + 2 * n;
		StringBuilder stb = new StringBuilder();
		List<String> result = new ArrayList<String>();
		task2_1_helper(dict, remaining, stb, len, stack, result);
		return result;
		
	}
	
	public static void task2_1_helper(char[] dict, int[] remaining, StringBuilder stb, 
			int targetLen, LinkedList<Character> stack, List<String> result) {
		if (stb.length() == targetLen) {
			// get a reasonable solution
			result.add(stb.toString());
			return ;
		}
		
		for(int i = 0; i < remaining.length; i ++) {
			// add left parentheses
			if (i % 2 ==  0) {
				if (remaining[i] > 0) {  // !!! don't forget here
					stb.append(dict[i]);
					remaining[i] --; 
					stack.offerFirst(dict[i]);
					task2_1_helper(dict, remaining, stb, targetLen, stack, result);
					// backtracking
					remaining[i] ++;
					stack.pollFirst();
					stb.deleteCharAt(stb.length() - 1);
				}
			} else {
				// add right parentheses
				if (!stack.isEmpty() && stack.peekFirst().equals(dict[i - 1])) {
					stb.append(dict[i]);
					remaining[i] --;
					stack.pollFirst();
					task2_1_helper(dict, remaining, stb, targetLen, stack, result);
					// backtracking
					remaining[i] ++;
					stb.deleteCharAt(stb.length() - 1);
					stack.offerFirst(dict[i - 1]);
				}
			}
		}
	}
	
	public static void test2_1() {
		int l = 1;
		int m = 1;
		int n = 1;
		List<String> result = task2_1_allValidPermutation(l, m, n);
		System.out.println(result);
		
	}
	
	
	
	/*
	 * task3 combinations of coins
	 * coins are soroted by descending order. and number of coins is infinitei
	 * e.g
	 * input: coins: {2, 1}  target 4
	 * output: [0, 4]
	 *         [1, 2]
	 *         [2, 0] 
	 */
	public static List<List<Integer>> task3_conbinationsOfCoins(int[] coins, int target) {
		List<Integer> cur = new ArrayList<Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		task3_helper(coins, target, 0, cur, result);
		return result;
	}
	
	public static void task3_helper(int[] coins, int target, int index, List<Integer> cur, List<List<Integer>> result) {
		if (target < 0) {
			return ;
		}
		if (index == coins.length) {
			if (target == 0) {
				result.add(new ArrayList<Integer>(cur));
				return ;
			}
		} else { 
			int maxNumCoins = target/coins[index];
			for(int i = 0; i <= maxNumCoins; i ++) {
				cur.add(i);
				task3_helper(coins, target - i * coins[index], index + 1, cur, result);
				cur.remove(cur.size() - 1);
			}
		}	
	}
	
	public static void test3() {
		int[] coins = {2,1};
		int target = 4;
		List<List<Integer>> result = task3_conbinationsOfCoins(coins, target);
		System.out.println(result);
	}
	
	/*
	 * task4 all permutation 1
	 */
	public static void test4() {
		String set = "abc";
		List<String> result = task4_allpermutation(set);
		System.out.println(result);
		
	}
	
	public static List<String> task4_allpermutation(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] setArr = set.toCharArray();
		task4_helper(setArr, result, 0);
		return result;
	}
	
	public static void task4_helper(char[] set, List<String> result, int index) {
		if (index == set.length) {
			result.add(new String(set));
			return ;
		}
		
		for(int i = index; i < set.length; i ++) {
			swap(set, index, i);
			task4_helper(set, result, index + 1);
			// backtracking
			swap(set, index, i);
		}
	}
	
	public static void swap(char[] set, int x, int y) {
		char temp = set[x];
		set[x] = set[y];
		set[y] = temp;
	}
	
	/*
	 * task4.1 all permutation 2
	 */
	public static void test4_1() {
		String set = "aab";
		List<String> result = task4_1_allpermutation(set);
		System.out.println(result);
	}
	
	public static List<String> task4_1_allpermutation(String set) {
		char[] setArr = set.toCharArray();
		List<String> result = new ArrayList<String>();
		task4_1_helper(setArr, 0, result);
		return result;
	}
	
	public static void task4_1_helper(char[] setArr, int position, List<String> result) {
		if (position == setArr.length) {
			result.add(new String(setArr));
			return ;
		}
		Set<Character> mySet = new HashSet<Character>();
		// mySet records the Characters which already be placed in this position.
		for(int i = position; i < setArr.length; i ++) {
			if (mySet.contains(setArr[i])) {
				continue;
			}
			mySet.add(setArr[i]);
			swap(setArr, position, i);
			task4_1_helper(setArr, position + 1, result);
			swap(setArr, position, i);
		}
	}

}

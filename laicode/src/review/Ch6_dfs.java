package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Ch6_dfs {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
//		test6();
		test7();
	}
	
	/*
	 * t1 all subsets
	 */
	public static void test1() {
		int[] arr = {1,2,3,4};
		List<List<Integer>> subsets = t1_subset(arr);
		System.out.println(subsets);
		System.out.println("--------");
		List<List<Integer>> subset2 = t1_subset2(arr);
		System.out.println(subset2);
	}
	
	public static List<List<Integer>> t1_subset(int[] arr) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		t1_subset_helper(arr, result, 0, list);
		return result;
	}
	
	public static void t1_subset_helper(int[] arr, List<List<Integer>> result, 
			int index, List<Integer> list) {
		if (index == arr.length) {
			result.add(new ArrayList<Integer>(list));
			return ;
		}
		
		// add arr[index] into list
		list.add(arr[index]);
		t1_subset_helper(arr, result, index + 1, list);
		list.remove(list.size() - 1);
		
		// don't add
		t1_subset_helper(arr, result, index + 1, list);
	}
	
	
	public static List<List<Integer>> t1_subset2(int[] arr) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		t1_subset2_helper(arr, 0, list, result);
		return result;
	}
	public static void t1_subset2_helper(int[] arr, int index, List<Integer> list, 
			List<List<Integer>> result) {
		result.add(new ArrayList<Integer>(list));
		
		// choose the index from orignal set to pick up and maintian the ascending order of pthe picked
		// up indices. 
		for(int i = index; i < arr.length; i++) {
			list.add(arr[i]);
			t1_subset2_helper(arr, i + 1, list, result);
			list.remove(list.size() - 1);
		}
	}
	
	/*
	 * t2 subset with duplicates
	 */
	public static void test2() {
		int[] arr = {1,2,2};
		List<List<Integer>> rev = t2_subset_dup(arr);
		System.out.println(rev);
	}
	
	public static List<List<Integer>> t2_subset_dup(int[] arr) {
		Arrays.sort(arr);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		t2_subset_dup_helper(arr, 0, list, result);
		return result;
	}
	
	public static void t2_subset_dup_helper(int[] arr, int index, List<Integer> list, List<List<Integer>> result) {
		result.add(new ArrayList<Integer>(list));
		
		for(int i = index; i < arr.length; i++) {
			if (i == index || arr[i] != arr[i - 1]) {
				list.add(arr[i]);
				t2_subset_dup_helper(arr, i + 1, list, result);
				list.remove(list.size() - 1);
			}
		}
	}
	
	/*
	 * t2 all valid permutations of parenthesis
	 */
	public static void test3() {
		int n = 2;
		List<String> result = t3_allValidPermutationsOfParenthesis(n);
		System.out.println(result);
	}
	
	public static List<String> t3_allValidPermutationsOfParenthesis(int n) {
		StringBuilder stb = new StringBuilder();
		List<String> result = new ArrayList<String>();
		t3_helper(n, n, stb, result);
		return result;
	}
	
	public static void t3_helper(int leftRemain, int rightRemain, StringBuilder stb, List<String> result) {
		if (leftRemain == 0 && rightRemain == 0) {
			result.add(stb.toString());
			return ;
		}
		if (leftRemain > 0) {
			stb.append("(");
			t3_helper(leftRemain - 1, rightRemain, stb, result);
			stb.deleteCharAt(stb.length() - 1);
		}
		
		if (rightRemain > leftRemain) {
			stb.append(")");
			t3_helper(leftRemain, rightRemain - 1, stb, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	
	/*
	 * t4  Print all combinations of coins that can sum up to a total value k.
	 * coin value(币值) ＝ 25 10 5 1 cent
	 * assume each coin has infinite amount. 
	 */
	public static void test4() {
		int[] coins = {25,10, 5,1};
		int target = 99;
		List<List<Integer>> result = t4_allCombinationsOfCoins(coins, target);
		System.out.println(result);
	}
	
	public static List<List<Integer>> t4_allCombinationsOfCoins(int[] coins, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		int index = 0;
		t4_helper(coins, target, index, cur, result);
		return result;
	}
	
	public static void t4_helper(int[] coins, int target, int index, 
			List<Integer> cur, List<List<Integer>> result) {
		if (index == coins.length - 1) {
			if (target % coins[coins.length - 1] == 0) {
				cur.add(target/coins[coins.length - 1]);
				result.add(new ArrayList<Integer>(cur));
				cur.remove(cur.size() - 1);
			}
			return ;
		}
		
		int maxOfCurCoin = target/coins[index];
		for(int i = 0; i <= maxOfCurCoin; i++) {
			cur.add(i);
			t4_helper(coins, target - i * coins[index], index + 1, cur, result);
			cur.remove(cur.size() - 1);
		}
	}
	
	/*
	 * 5.1 all permutations
	 * 
	 * 5.2 all permutations, maintain the order of all permutations
	 */
	public static void test5() {
		String set = "abc";
		List<String> result = t5_1_permutations(set);
		System.out.println(result);
		System.out.println("---------------");
		List<String> result2 = t5_2_permutaions_with_order(set);
		System.out.println(result2);
	}
	
	public static List<String> t5_1_permutations(String set) {
		List<String> result = new ArrayList<String>();
		char[] arr = set.toCharArray();
		t5_1_helper(arr, 0, result);
		return result;
	}
	public static void t5_1_helper(char[] arr, int index, List<String> reslut) {
		if (index == arr.length) {
			reslut.add(new String(arr));
			return ;
		}
		
		for(int i = index; i < arr.length; i ++) {
			swap(arr, index, i);
			t5_1_helper(arr, index + 1, reslut);
			swap(arr, index, i);
		}
	}
	
	public static void swap(char[] arr, int x, int y) {
		char temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	public static List<String> t5_2_permutaions_with_order(String set) {
		char[] arr = set.toCharArray();
		Arrays.sort(arr);
		List<String> result = new ArrayList<String>();
		boolean[] used = new boolean[set.length()];
		StringBuilder stb = new StringBuilder();
		t5_2_helper_with_order(arr, stb, result, used);
		return result;
	}
	
	public static void t5_2_helper_with_order(char[] arr, StringBuilder stb, List<String> result, boolean[] used) {
		if (stb.length() == arr.length) {
			result.add(stb.toString());
			return ;
		}
		
		for(int i = 0; i < arr.length; i ++) {
			if (!used[i]) {
				stb.append(arr[i]);
				used[i] = true;
				t5_2_helper_with_order(arr, stb, result, used);
				// backtracking
				used[i] = false;
				stb.deleteCharAt(stb.length() - 1);
			}
		}
	}

	
	/*
	 * 6.1 permutations with dup
	 * 6.2 permutations with dup, maintain the order of all permutations
	 */
	public static void test6() {
		String set = "aab";
		List<String> rev = t6_1_permutations_dup(set);
		System.out.println(rev);
		System.out.println("--------");
		List<String> rev2 = t6_2_permutations_dup_with_order(set);
		System.out.println(rev2);
	}
	
	public static List<String> t6_1_permutations_dup(String set) {
		List<String> result = new ArrayList<String>();
		int index = 0;
		char[] arr = set.toCharArray();
		t6_1_helper_dup(arr, index, result);
		return result;
	}
	public static void t6_1_helper_dup(char[] arr, int index, List<String> result) {
		if (index == arr.length) {
			result.add(new String(arr));
			return;
		}
		HashSet<Character> set = new HashSet<Character>();
		for(int i = index; i < arr.length; i ++) {
			if (i != index && set.contains(arr[i])) {
				continue;
			}
			swap(arr, index, i);
			set.add(arr[i]);
			t6_1_helper_dup(arr, index + 1, result);
			swap(arr, index, i);
		}
	}
	
	/*
	 * permutations with duplicate with order. 
	 */
	public static List<String> t6_2_permutations_dup_with_order(String set) {
		List<String> result = new ArrayList<String>();
		char[] arr = set.toCharArray();
		Arrays.sort(arr);
		StringBuilder stb = new StringBuilder();
		boolean[] used = new boolean[set.length()];
		t6_2_helper_dup_order(arr, stb, result, used);
		return result;
	}
	
	public static void t6_2_helper_dup_order(char[] arr, StringBuilder stb, List<String> result, boolean[] used) {
		if (stb.length() == arr.length) {
			result.add(stb.toString());
			return ;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if (!used[i]) {
				if (i != 0 && arr[i] == arr[i - 1] && used[i - 1]) {
					// a[i] == a[i - 1] and i-t's element is already used
					// we cannot put the duplicate element in this position. 
					continue;
				}	
				stb.append(arr[i]);
				used[i] = true;
				t6_2_helper_dup_order(arr, stb, result, used);
				// backtracking
				used[i] = false;
				stb.deleteCharAt(stb.length() - 1);
			}
		}
	}
	
	
	/*
	 * 7 a valid permutation of parentheses II
	 * Assumption
	 * l, m, n 
	 * e.g
	 * l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]
	 */
	public static void test7() {
		int l = 1, m = 1, n = 0;
		List<String> result = t7_all_valid_permutations2(l, m, n);
		System.out.println(result);
	}

	public static List<String> t7_all_valid_permutations2(int l, int m, int n) {
		Deque<Integer> stack = new LinkedList<Integer>();
		int totalLen = 2 * l + 2 * m + 2 * n;
		StringBuilder stb = new StringBuilder();
		char[] PS = {'(', ')', '[', ']', '{', '}'};
		int[] remain = {l, l, m, m, n, n};
		List<String> result = new ArrayList<String>();
		t7_helper(remain, PS, stack, stb, totalLen, result);
		return result;
	}
	
	public static void t7_helper(int[] remain, char[] PS, Deque<Integer> stack, StringBuilder stb, 
			int totalLen, List<String> result) {
		if (stb.length() == totalLen) {
			result.add(stb.toString());
			return ;
		}
		
		for(int i = 0; i < remain.length; i ++ ) {
			if (i % 2 == 0) {
				// add left parenthese
				if (remain[i] > 0) {
					stb.append(PS[i]);
					remain[i] --;
					stack.offerLast(i);
					t7_helper(remain, PS, stack, stb, totalLen, result);
					
					// backtracking
					stb.deleteCharAt(stb.length() - 1);
					remain[i] ++;
					stack.pollLast();
				}
				
			} else {
				// add right parentheses
				if (!stack.isEmpty() && stack.peekLast() == (i - 1)) {
					stb.append(PS[i]);
					remain[i] --;
					stack.pollLast();
					t7_helper(remain, PS, stack, stb, totalLen, result);
					// backtracking
					stb.deleteCharAt(stb.length() - 1);
					remain[i] ++;
					stack.offerLast(i -1);
				}
			}
		}
	}
	
	
	
	/*
	 * 8
	 * Generate Random Maze
	 * Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. 
	 * For each pair of cells on the corridor, there must exist one and only one path between them. 
	 * (Randomly means that the solution is generated randomly, 
	 * and whenever the program is executed, the solution can be different.). 
	 * The wall is denoted by 1 in the matrix and corridor is denoted by 0.
	 * 
	 * Assumptions
	 * N = 2K + 1 and K >= 0
	 * the top left corner must be corridor
	 * there should be as many corridor cells as possible
	 * for each pair of cells on the corridor, there must exist one and only one path between them
	 * 
	 * Examples: 
	 * N = 5
	 * 0  0  0  1  0
	 * 1  1  0  1  0
	 * 0  1  0  0  0
	 * 0  1  1  1  0
	 * 0  0  0  0  0
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	

}

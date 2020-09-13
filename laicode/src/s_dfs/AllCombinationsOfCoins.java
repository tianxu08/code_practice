package dfs;

import java.util.ArrayList;
import java.util.List;

public class AllCombinationsOfCoins {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/*
	 * e.g
	 * total value k = 99
	 * coin value = {25,10,5,1}
	 */
	public static void test1() {
		int[] coins = {25,10, 5,1};
		int target = 99;
		for(List<Integer> list: combinations(target, coins)) {
			System.out.println(list);
		}
	}
	
	public static List<List<Integer>> combinations(int target, int[] coins) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		helper(target, coins, 0, cur, result);
		return result;
	}
	
	public static void helper(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
		if (index == coins.length - 1) {
			if (target % coins[coins.length - 1] == 0) {
				cur.add(target/coins[coins.length - 1]);
				result.add(new ArrayList<Integer>(cur));
				cur.remove(cur.size() - 1);
			}
			return ;
		}
		int max = target / coins[index];
		for(int i = 0; i <= max; i ++) {
			cur.add(i);
			helper(target - i * coins[index], coins, index + 1, cur, result);
			cur.remove(cur.size() - 1);
		}
	}

}

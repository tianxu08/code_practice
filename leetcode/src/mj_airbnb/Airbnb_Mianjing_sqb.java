package mj_airbnb;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class Airbnb_Mianjing_sqb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/**
	 * task1: Collatz Conjecture
	 * 题目是给你公式，比如偶数的话除 2，奇数的话就变成 3*n+1，对于任何一个正数，数学猜想是最 终他会变成 1。
	 * 每变一步步数加 1，给一个上限，让找出范围内最长步数。
	 * 比如 7，变换到 1 是如下顺序:7->22->11->34->17->52->26->13->40->20->10->5->16->8->4->2->1, 总 共需要 17 步。
	 */
	
	public static void test1() {
		int n = 100;
		int rev = task1_findLongestSteps(n);
		System.out.println("==>>> rev: " + rev);
		
		
		
	}
	
//	public static Map<Integer, Integer> task1_map = new HashMap<>();
	public static int task1_findSteps(int n, Map<Integer, Integer> map) {
		if (n == 1) {
			return 0;
		}
		if (map.containsKey(n)) {
			return map.get(n);
		}
		
		int curSteps = 0;
		if (n % 2 == 0) {
			curSteps = 1 + task1_findSteps(n / 2, map);
		} else {
			curSteps = 1 + task1_findSteps(3 * n + 1, map);
		}
		map.put(n, curSteps);
		return curSteps;
	}
	
	public static int task1_findLongestSteps(int n) {
		if (n == 1) {
			return 1;
		}
		int result = 1;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 2; i <= n; i++) {
			int curSteps = task1_findSteps(i, map);
			result = Math.max(curSteps, result);
		}
		
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println(entry.getKey() + "==>>>" + entry.getValue());
		}
		
		return result;
	}

}

package mj_pinterest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import ds.Debug;

public class Mianjing_Onsite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test3();
	}

	/**
	 * task1: 
	 * Given 2 input arrays of integers, unsorted. 从两个array各拿出一个数相加，成为一个pair，找出largest k pairs
	 * 2, 3,-1,0, 5
	 * 1, 4, 2
	 * k = 5 .
	 * --> (5, 4), (5, 2), (3, 4), (5, 1), (2, 4)
	 * 
	 * <1> sort: 
	 * 5, 3, 2, 0, -1
	 * 4, 2, 1
	 *
	 * use minHeap. element is [(5, 4), 0, 0]
	 * for the next element is either [(5, 2), 0, 1] or [(3, 4), 1, 0]
	 * 
	 * 
	 * https://www.geeksforgeeks.org/find-k-pairs-smallest-sums-two-arrays/
	 * 
	 */
	public static void test1() {
		Integer[] a1 = {2, 3, -1, 0, 5};
		Integer[] a2 = {1, 4, 2};
		int k = 5;
		List<Integer[]> result = task1_largestKPairs(a1, a2, k);
		for (Integer[] p: result) {
			System.out.println(p[0] + ", "+  p[1]);
			
		}
	}
	
	/**
	 * 
	 * @param a1
	 * @param a2
	 * @param k
	 * @return
	 * 
	 * Time: O(n * log n)
	 * Space: O(n)
	 */
	public static List<Integer[]> task1_largestKPairs(Integer[] a1, Integer[] a2, int k) {
		if (a1 == null || a1.length == 0 || a2 == null || a2.length == 0) {
			return null;
		}
		List<Integer[]> result = new ArrayList<>();
		Arrays.sort(a1, Collections.reverseOrder());
		Arrays.sort(a2, Collections.reverseOrder());
		
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
		
		PriorityQueue<Elem> maxHeap = new PriorityQueue<>(k, 
				(a, b) -> {return  ( b.pair[0] + b.pair[1]) - (a.pair[0] + a.pair[1]);});
		
		maxHeap.offer(new Elem(new Integer[] {a1[0] , a2[0]}, 0, 0));
		
		while(k > 0 && !maxHeap.isEmpty()) {
			Elem curElem = maxHeap.poll();
			int curX = curElem.x;
			int curY = curElem.y;
			int nextX = curElem.x + 1;
			int nextY = curElem.y + 1;
			
			if (nextX + 1 < a1.length) {
				maxHeap.offer(new Elem(new Integer[] {a1[nextX] , a2[curY]}, nextX, curY));
			}
			
			if (nextY + 1 < a2.length) {
				maxHeap.offer(new Elem(new Integer[] {a1[curX] , a2[nextY]}, curX, nextY));
			}
			
			result.add(curElem.pair);
			k --;
		}
		
		return result;
		
		
	}
	
	private static class Elem{
		Integer[] pair; 
		int x;
		int y;
		public Elem(Integer[] _p, int _x, int _y) {
			this.pair = _p;
			this.x = _x;
			this.y = _y;
		}
	}
	
	/**
	 * LC34
	 * Find first and last position of element in sorted array
	 * 
	 * binary search
	 */
	
	/**
	 * LC354: Russian Doll Envelopes
	 * 
	 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
	 * One envelope can fit into another if and only if both the width 
	 * and height of one envelope is greater than the width and height of the other envelope.
	 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
	 * 
	 * Input: [[5,4],[6,4],[6,7],[2,3]]
	 * Output: 3 
	 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
	 * 
	 * variation of longest increasing sequence
	 */
	
	public static void test3() {
		int[][] envs = {
				{46, 89},{50, 53},{52, 68},{72, 45}, {77, 81}
		};
		int maxLen = task3_maxEnvelopes(envs);
		System.out.println("====>>> maxLen : " + maxLen);
	}
	
	public static int task3_maxEnvelopes(int[][] envs) {
		if (envs == null || envs.length == 0 || envs[0] == null || envs[0].length == 0) {
			return 0;
		}
		// sort the envelops according to width in ascending order
		Arrays.sort(envs, (a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			}
			return a[0] - b[0]; });
		
		Debug.printMatrix(envs);
		int maxLen = 0;
		int[] M = new int[envs.length];
		M[0] = 1;
		for (int i = 1; i < envs.length; i++) {
			M[i] = 1;
			int[] curEnv = envs[i];
			for (int j = 0; j < i; j++) {
				int[] prevEnv = envs[j];
				if (prevEnv[0] < curEnv[0] && prevEnv[1] < curEnv[1] && M[j] + 1 > M[i]) {
					M[i] = M[j] + 1;
					maxLen = Math.max(maxLen, M[i]);
				}
			}
		}
		System.out.println(Arrays.toString(M));
		return maxLen;
	}
	
	/**
	 * opt task3 Time: O(n log n)
	 * 
	 */
	
	
	
	
	
	
	

}

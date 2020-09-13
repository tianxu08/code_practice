package mj_linkedin;

public class Mianjing_0715 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Nested List Weight Sum
	 * 
	 * retainBestCache，设计一个cache，他给你一个getRank()的方法，当cache满了的时候，
	 * 要把rank最低的移除，开始以为是LRU Cache，结果发现不是，稍微有点慌，卡住了，
	 * 说用arraylist来存，说完发现好蠢，每次移除都得O(n)来找rank最低的，然后他提示我还可以更高效，
	 * 我一下子就想到了PriorityQueue，
	 * 面完了发现确实该用PriorityQueue，可惜没完全写完，把comparator什么的写完了，但是写得很乱
	 * 
	 * LC235: LCA in BST
	 * 
	 * follow up LC236: LCA in BT
	 * 
	 * LC243 shortest word distance
	 * 
	 *  power(a, b) 这个是leetcode原题， 一遍bug free写完了也没问followup
	 *  
	 *  版上常见的那个interval题目， 要实现一个接口，实现addInterval和getTotalCoverage两个函数。 
	 *  基本算是leetcode的两个interval题目的变种， 第一遍bug free写了一个add O(n)和get O(1)的方法， 
	 *  然后交流了一下之后做了些小优化。 followup是问有没有让add方法更efficient的写法, 
	 *  然后我说了下add O(1), get O(nlogn)的方法， 然后又讨论了一些优化的问题。
	 *  
	 *  
	 */
	

}

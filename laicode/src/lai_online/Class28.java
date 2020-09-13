package lai_online;

import java.util.Map;

import ds.TreeNode;

public class Class28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * task1: Longest Ascending Subsequence
	 * task1.1: Longest Ascending Subarray
	 * task2 De-serialization of binary tree problem: Restruct a tree by using xxx-order and IN_ORDER traversal sequences
	 * task2.1 Construct a BST with pre_order and in_order sequences of all nodes
	 * task2.2 Construct a BST with post_order and in_order sequences of all nodes
	 * task2.3 Construct a BST with level_order and in_order sequences of all nodes
	 * 
	 * task3 Serialization of a binary tree
	 * 
	 * task4: Max Number Of Points On The Same Line
	 * task5: Largest Set of Points With Positive Slope
	 * 
	 * task6: Random Generate Maze
	 * task7: Max Water TrappedII 
	 */
	
	
	public static TreeNode task2_1_ConstructBST_pre_in(int[] pre, int[] in) {
		return null;
	}
	public static TreeNode task2_1_helper(int[] pre, int pre_l, int pre_r, 
			int[] in, int in_l, int in_r, Map<Integer, Integer> map) {
		if (in_l > in_r) {
			// there is NO element in in
			return null;
		}
		TreeNode root = new TreeNode(pre[pre_l]);
		int in_index = map.get(pre[pre_l]);
		root.left = task2_1_helper(pre, pre_l, pre_r, in, in_l, in_r, map);
		return null;
	}
	
	/*
	 * Level Order and In Order
	 */
	public static TreeNode task2_3_Construct_Level_In(int[] in, int[] level) {
		return null;
	}
	
	/*
	 * task3
	 * Serialization of a binary tree
	 * given a binary tree, convert it to a Double Linked List according to its in order sequence
	 * 
	 *             	10
	 *            /  	\
	 *           12   	15
	 *          / \     /
	 *         25  30  36
	 * 
	 * 25 <-> 12 <-> 30 <->10 <-> 26 <-> 15
	 * head
	 * 
	 */
	public static TreeNode prev = null;
	public static TreeNode head = null;
	
	public static void task3_serialization(TreeNode root) {
		if (root == null) {
			return ;
		}
		task3_serialization(root.left);
		if (prev == null) {
			head = root;
			// update prev
			prev = root;
		} else {
			// head != null
			prev.right = root;
			root.left = prev;
			// update prev
			prev = root;
		}
		task3_serialization(root.right);
	}
	
	/*
	 * task4.1 Given an array of cooridinates of points, how to find the largest number of points
	 * that can be crossed by a same line in 2D space ? 
	 */
	
	/*
	 * tas4.2 Given an array of cooridinates of points, how to find the largest number of points 
	 * that can form a set such that any pair of points in the set can form a line with positive slope.
	 * 
	 * set = {};
	 * p1 = <x1, y1>
	 * p2 = <x2, y2>  if x1 < x2, then we must have y1 < y2
	 * p3 = <x3, y3>  if x1 < x2 < x3, then we must have y1 < y2 < y3
	 * 
	 * solution: 
	 * 1) step1: sort all the points according to their x-coordinate
	 * 2) step2: essentially, it's to ask what is the Longest Increasing Subsequence in the y-coordinates
	 * 			 of all the points
	 */
	
	
	/*
	 * task5
	 * How to design a search suggestion system
	 * e.g football -> ticket
	 * 				   season
	 * 				   player
	 * 
	 * System Design: what should we ask instead of giving answers at the very beginning. 
	 * Make assumption / ask questions: 
	 * 1) Billion of users
	 * 2) different countries / with different languages (location)
	 * 3) relevance / how to order / sort the result ? 
	 * 4) Time intervals : how often should we update the systems
	 * 5) ...
	 * 
	 * How to design this module : relevance / how to order / sort the result ? 
	 * Big Data: 分析数据的 frequency
	 * How to implement? 
	 * What's the important factor/metrics to order all the words following the key word ? 
	 * Proposal 1: <football ticket> 1.8 billion
	 * 				<football season> 1.2 billion
	 * 				.....
	 * 				<first word = football, second word = x1>, value = counter1 (1.8 billion)
	 * 				<first word = football, second word = x2>, value = counter2(1.2 billion)
	 * 瓶颈： 
	 * 一台机器处理不过来， 比如说用prefix 分配机器..
	 * 
	 *  先做sampling, 大概分析一下每个 log 里边，a 开始的数据有多少， b 开头的数据大概有多少， etc, 
	 *  get distribution, 按照数据的portion 来合理分析数据
	 *  处理完了每个key word's frequency. 
	 *  （按照sample 出来的数据来平均的分配..）
	 *  
	 *  （a）处理log 的时候， 应该先用 location 来区分。 
	 *   (b) time intervals : how often should we update the system ? 
	 *   在map reduce 的时候， 分批处理log 的数据，把sub－solution 存储在counter, 然后淘汰windows 以外的counter 
	 *  
	 */
	
	/*
	 * task6
	 * Given a NxN matrix, how to randomly generate a maze whose corridor and wall's width are both 1 cell.
	 * In the meantime, for each pair of cells on the corridor, there must exist a pth between them. 
	 * (Randomly means that the solution is generated randomly, 
	 * and whenever the program is executed, the solution can be different.)
	 */
	
	
	

}

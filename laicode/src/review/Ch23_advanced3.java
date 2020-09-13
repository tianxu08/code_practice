package review;



import java.util.ArrayList;

import ds.TreeNode;

public class Ch23_advanced3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test3();
//		test4();
		test5();
	}
	
	/*
	 * t1 Determine if tree is balanced
	 * t2 Max Path Sum Binary Tree (path from leaf to leaf)
	 * t3 Max Path Sum Binary TreeII(path from any node to any node)
	 * t4 Max Path Sum Binary Tree III
	 *    (the two nodes can be the same node and they can only be on the path from root to one of the leaf nodes)
	 * t5 Binary Tree Path Sum To Target
	 *    (the two nodes can be the same node and they can only be on the path from root to one of the leaf nodes)
	 * t6 
	 */
	
	public static void test2() {
		TreeNode n1 = new TreeNode(-15);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(11);
		TreeNode n4 = new TreeNode(6);
		TreeNode n5 = new TreeNode(14);
		
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n3.right = n5;
		
		int rev = t2_max_path_sum_BT_1(n1);
		System.out.println("rev = " + rev);
	}
	
	/**
	 * from leaf to leaf
	 * Find the maximum possible sum from one leaf node to another leaf node. 
	 * If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).
	 * @param root
	 */
	public static int t2_max_path_sum_BT_1(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		max_sum = Integer.MIN_VALUE;
		t2_helper(root);
		return max_sum;
	}
	
	public static int max_sum;
	public static int t2_helper(TreeNode node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		// leaf node
		if (node.left == null && node.right == null) {
			return node.val;
		}
		
		int left_sum = t2_helper(node.left);
		int right_sum = t2_helper(node.right);
		
		if (left_sum != Integer.MIN_VALUE && right_sum != Integer.MIN_VALUE) {
			int cur_sum = left_sum + right_sum + node.val;
			if (max_sum < cur_sum) {
				max_sum = cur_sum;
			}
		}
		// return one side
		return Math.max(left_sum, right_sum) + node.val;
		
	}
	
	 
	public static void test3() {
		TreeNode n1 = new TreeNode(-1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(11);
		TreeNode n4 = new TreeNode(6);
		TreeNode n5 = new TreeNode(-14);
		
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n3.right = n5;
		
		int rev = t3_max_path_sum_BT_2(n1);
		System.out.println("rev = " + rev);
		
	}
	
	/**
	 * Find the maximum possible sum from any node to any node (the start node and the end node can be the same). 
	 * @param root
	 * @return
	 * Examples
	 *        -1
	 *      /    \
	 *     2      11
	 *          /    \
	 *         6    -14
	 * 
	 * one example of paths could be -14 -> 11 -> -1 -> 2
	 * another example could be the node 11 itself
	 * The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
	 */
	public static int t3_max_path_sum_BT_2(TreeNode root) {
		if (root == null) {
			return 0;
		}
		max_sum = Integer.MIN_VALUE;
		
		t3_helper(root);
		return max_sum;
	}
	
	public static int t3_helper(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left_sum = t3_helper(node.left);
		int right_sum = t3_helper(node.right);
		
		if (left_sum < 0) {
			left_sum = 0;
		}
		if (right_sum < 0) {
			right_sum = 0;
		}
		int cur_sum = left_sum + right_sum + node.val;
		
		if (max_sum < cur_sum) {
			max_sum = cur_sum;
		}
		
		return Math.max(left_sum, right_sum) + node.val;
	} 
	
	
	/*
	 * Find the maximum possible subpath sum
	 * (both the starting and ending node of the subpath 
	 * should be on the same path from root to one of the leaf nodes, 
	 * and the subpath is allowed to contain only one node).
	 */
	public static int t4_max_path_sum_4(TreeNode root) {
		if (root == null) {
			return 0;
		}
		max_sum = Integer.MIN_VALUE;
		t4_helper(root);
		return max_sum;
	}
	
	public static int t4_helper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left_sum = t4_helper(root.left);
		int right_sum = t4_helper(root.right);
		
		// only calculate the single side sum.
		int sin_sum = Math.max(root.val, Math.max(left_sum, right_sum) + root.val);
		max_sum = Math.max(max_sum, sin_sum);
		// return the single side sum
		return sin_sum;
	}
	
	public static void test4() {
		TreeNode n1 = new TreeNode(-15);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(11);
		TreeNode n4 = new TreeNode(6);
		TreeNode n5 = new TreeNode(14);
		
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n3.right = n5;
		
		int rev = t4_max_path_sum_4(n1);
		System.out.println("rev = " + rev);
	}
	
	
	/**
	 * Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), 
	 * the sum of the numbers on the path is the given target number.
	 * @param root
	 * @param target
	 * @return
	 */
	public static boolean t5_exist_path_to_target(TreeNode root, int target) {
		ArrayList<TreeNode> path = new ArrayList<TreeNode>();
		return t5_helper(root, target, path);
	}
	
	public static boolean t5_helper(TreeNode node, int target, ArrayList<TreeNode> path) {
		path.add(node);
		int temp = 0;
		for(int i = path.size() - 1; i >= 0; i --) {
			temp += path.get(i).val;
			if (temp == target) {
				path.remove(path.size() - 1);
				return true;
			}
		}
		
		// traverse left subtree
		if (node.left != null && t5_helper(node.left, target, path)) {
			path.remove(path.size() - 1);
			return true;
		}
		
		// traverse right subtree
		if (node.right != null && t5_helper(node.right, target, path)) {
			path.remove(path.size() - 1);
			return true;
		}
		
		path.remove(path.size() - 1);
		return false;
	}
	
	public static void test5() {
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(11);
		TreeNode n4 = new TreeNode(6);
		TreeNode n5 = new TreeNode(14);
		TreeNode n6 = new TreeNode(3);
		
		n1.left = n2;
		n1.right = n3;
		n3.left = n4; 
		n3.right = n5;
		n4.left = n6;
		int target = 17;
		boolean rev = t5_exist_path_to_target(n1, target);
		System.out.println("rev = " + rev);
		
	}
	

}

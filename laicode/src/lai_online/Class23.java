package lai_online;

import java.util.List;

import ds.TreeNode;


/*
 * task1: If Binary Tree is Balanced
 * task2: Max Path Sum Binary Tree(path from leaf to root)
 * task3: Max Path Sum Binary TreeII(path from any node to any node)
 * task4: Max Path Sum Binary TreeIII(the two nodes can be the same node and they can
 *        only be on the path from root to one of the leaf nodes )
 * task5: Binary Tree Path Sum To Target(the two nodes can be the same node and they can only on the path 
 *        from root to one of the leaf nodes)
 * 
 * task6: Reconstruct Binary Search With PostOrder
 * task7: Reconstruct Binary Search With PreOrder
 * task8: Reconstruct Binary Tree With LevelOrder and InOrder
 * task9: Reconstruct Binary Tree With PreOrder and InOrder
 * task10: Reconstruct Binary Tree With PostOrder and InOrder
 * 
 */



public class Class23 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1_1();
	}
	
	/*
	 * Q1: use recursion to return values in a bottom up way in binary tree
	 * 
	 * Q1.1 determine whether a binary tree is a balance binary tree 
	 * balanced: 
	 * the tree has a minimum possible overall height
	 * no leaf is too futher away, i.e, 0 or 1, from root than any other leaf
	 * left and right sub-tree have similar height, i.e, difference is 0 or 1 (balanced height)
	 * 
	 * method1: O(n * log n)
	 */
	public static boolean task1_1_balancedTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		int diff = task1_1_getHeight(root.left) - task1_1_getHeight(root.right);
		if (Math.abs(diff) > 1) {
			return false;
		}
		return task1_1_balancedTree(root.left) && task1_1_balancedTree(root.right);
	}
	
	public static int task1_1_getHeight(TreeNode node) {
		if ( node == null) {
			return 0;
		}
		return Math.max(task1_1_getHeight(node.left), task1_1_getHeight(node.right)) + 1;
	}
	
	public static void test1_1() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		n1.left = n2;
		n1.right = n3;
		
		boolean rev = task1_1_balancedTreeOPT(n1);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * method2:
	 * Time: O(n)
	 */
	public static boolean task1_1_balancedTreeOPT(TreeNode root) {
		return task1_1_getHeightOPT(root) != -1;
	}
	
	public static int task1_1_getHeightOPT(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftH = task1_1_getHeightOPT(node.left);
		int rightH = task1_1_getHeightOPT(node.right);
		if (leftH == -1 || rightH == -1 || Math.abs(leftH - rightH) > 1) {
			return -1;
		}
		return Math.max(leftH, rightH) + 1;
	}
	
	/*
	 * task1.3
	 * Given a binary tree in which each node element contains a number
	 * Find the maximum possible sum from one leaf node to another
	 * The maximum sum path may or may not go through root. 
	 * For example, in the following binary tree, the maximum sum is 27(3 + 6 + 9 + 0 – 1 + 10). 
	 * Expected time complexity is O(n).
	 * 
	 */
	public static int maxSum = 0;
	public static int task1_3_maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftPathSum = task1_3_maxPathSum(root.left);
		int rightPathSum = task1_3_maxPathSum(root.right);
		
		// find the current result
		int curSum = leftPathSum + rightPathSum + root.val;
		// update
		if (curSum > maxSum && root.left != null && root.right != null) {
			maxSum = curSum;
		}
		if (root.left == null) {
			return rightPathSum + root.val;
		} else if (root.right == null) {
			return leftPathSum + root.val;
		} else {
			return Math.max(leftPathSum, rightPathSum) + root.val;
		}
	}
	
	
	/*
	 * task1.4
	 *  (人字形path 问题）
	 *  Laicode.com Class 20 (Maximum Path Sum Binary Tree II
	 *  
	 *  Get Maxim sum of the path cost from any node to any node (not necessarily the leaf to leaf) 
	 */
	public static int result1_4 = 0;
	public static int task1_4_maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// step1
		int leftSum = task1_4_maxPathSum(root.left);
		int rightSum = task1_4_maxPathSum(root.right);
		
		// step2
		if (leftSum < 0) {
			leftSum = 0;
		}
		if (rightSum < 0) {
			rightSum = 0;
		}
		result1_4 = Math.max(result1_4, root.val + leftSum + rightSum);

		// step3
		return leftSum > rightSum ? leftSum + root.val : rightSum + root.val;
	}
	
	/*
	 * task2.1
	 * Find the maximum path cost for all paths from leaf to root in a Binary Tree
	 * Given a Binary Tree, find the maximum sum path from a leaf to root. 
	 * For example, in the following tree, 
	 * there are three leaf to root paths 8->-2->10, -4->-2->10 and 7->10. 
	 * The sums of these three paths are 16, 4 and 17 respectively. 
	 * The maximum of them is 17 and the path for maximum is 7->10.
	 * 				 10
	 * 				/  \
	 * 				-2  7
	 * 			   / \
	 * 			  8   -4
	 */
	public static int task2_1_maxPathSumLeaf2Root(TreeNode root) {
		return -1;
	}
	public static boolean matched = false;
	public static void task2_1_helper(TreeNode root, List<Integer> prefix, int target) {
		if (root == null || matched) {
			return ;
		}
		int sum = 0;
		prefix.add(root.val);
		for(int i = prefix.size() - 1; i >= 0; i --) {
			sum += prefix.get(i);
			if (sum == target) {
				matched = true;
				return ;
			}
		}
		task2_1_helper(root.left, prefix, target);
		task2_1_helper(root.right, prefix, target);
		prefix.remove(prefix.size() - 1);
	}
	
	
	
	/*
	 * task2.2
	 * Binary Tree Path Sum To Target
	 * Given a binary tree in which each node contains an integer number. 
	 * Determine if there exists a path from any node to any node 
	 * (the two nodes can be the same node and they can only be on the path from root to one of the leaf nodes), 
	 * the sum of the numbers on the path is the given target number.
	 * 
	 * E.g
	 * 				5
	 * 			   / \
	 * 			  2   11
	 *  			  / \
	 *  			 6  14
	 *  If target == 17, there exist a path 11 + 6, the sum of the path is target
	 *  If target == 10, they doesn't exist any sum of which is a target
	 */
	
	/*
	 * task2.3
	 * (laicode.com Class 20)  Maximum Path Sum Binary Tree III
	 * Given a binary tree in which each node contains an integer number. 
	 * Find the maximum possible sum from any node to any node 
	 * (the two nodes can be the same node and 
	 * they can only be on the path from root to one of the leaf nodes).
	 */
	
	
	
	
	/*
	 * task3
	 * Common elements problems
	 * 
	 * task3.1 find common elements in two arrays
	 * assumption: 
	 * 1 sorted VS unsorted
	 * 2 size of the two arrays  2 VS 2 billion
	 * 
	 * M1 use HashMap
	 * 
	 * M2 do not use a hash table
	 * 
	 * M3: concerns about the size of two arrays
	 * 
	 */
	
	/*
	 * task3.2
	 * Find common elements in 3 SORTED arrays 
	 * M1 use hash table
	 * 		(1) find the common elements between 1 and 2
	 * 		(2) find the common elements between array12 and 3
	 * M2 do NOT use hash table
	 * 		(1) merge array 1 and 2 an dget intermediate result in array4
	 * 		(2) merge 
	 * 
	 */
	
	
	

}

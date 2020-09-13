package lai_online2;
import ds_lai_online2.*;
import java.util.*;
public class Class22 {
	/*
	 * task1
	Binary Tree Path Sum To Target
	Fair
	Data Structure
	Given a binary tree in which each node contains an integer number. Determine if there exists a path from any node to any node (the two nodes can be the same node and they can only be on the path from root to one of the leaf nodes), the sum of the numbers on the path is the given target number.

	Examples

	    5

	  /    \

	2      11

	     /    \

	    6     14

	If target = 17, There exists a path 11 + 6, the sum of the path is target,

	If target = 10, There does not exist any paths sum of which is target.

	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	*/
	public class Result {
		public int val;
		public Result (int v) {
			this.val = v;
		}
	}
  public boolean exist(TreeNode root, int target) {
    // Write your solution here.
    if (root == null) {
			return false;
		}
		Result result = new Result(0);
		ArrayList<Integer> path = new ArrayList<Integer>();
		Q2_2Helper(root, target, path, result);
		return result.val == 1;
  }
  
  public void Q2_2Helper(TreeNode node, int target, ArrayList<Integer> path, Result result) {
		if (node == null) {
			return ;
		}
		int sum = 0;
		path.add(node.key);
		for(int i = path.size() - 1; i >= 0; i --) {
			sum += path.get(i);
			if (target == sum) {
				result.val = 1;
				return ;
			}
		}
		Q2_2Helper(node.left, target, path, result);
		Q2_2Helper(node.right, target, path, result);
		path.remove(path.size() - 1);
	}
  
  
  /*
   * task2
   
  Reconstruct Binary Search Tree With Postorder Traversal
  Fair
  Data Structure
  Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.

  Assumptions

  The given sequence is not null
  There are no duplicate keys in the binary search tree
  Examples

  postorder traversal = {1, 4, 3, 11, 8, 5}

  the corresponding binary search tree is

          5

        /    \

      3        8

    /   \        \

  1      4        11

  How is the binary tree represented?

  We use the pre order traversal sequence with a special symbol "#" denoting the null node.

  For Example:

  The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

      1

    /   \

   2     3

        /

      4
  */
  public TreeNode task2_reconstruct(int[] post) {
	    // Write your solution here.
	    return null;
	  }
  
  
  
  /*
   * task3
  Maximum Path Sum Binary Tree III
  Fair
  Recursion
  Given a binary tree in which each node contains an integer number. Find the maximum possible subpath sum(both the starting and ending node should be on the same path from root to one of the leaf nodes, and the path is allowed to contain only one node).

  Assumptions

  The root of given binary tree is not null
  Examples

     -5

    /    \

  2      11

       /    \

      6     14

  The maximum path sum is 11 + 14 = 25

  How is the binary tree represented?

  We use the level order traversal sequence with a special symbol "#" denoting the null node.

  For Example:

  The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

      1

    /   \

   2     3

        /

      4
      */
  
  public int maxPathSum(TreeNode root) {
	    // Write your solution here.
	    if (root == null) {
				return 0;
			}
			ArrayList<Integer> path = new ArrayList<Integer>();
			Result result = new Result(Integer.MIN_VALUE);
			task4_helper(root, path, result);
			return result.val;
	  }
	  
	  
	 
		
		public void task4_helper(TreeNode node, ArrayList<Integer> path, Result result) {
			if (node == null) {
				return ;
			}
			path.add(node.key);
			if (node.left == null && node.right == null) {
				int maxSum = getLargestSum(path);
				if (maxSum > result.val) {
					result.val = maxSum;
				}
			}
			task4_helper(node.left, path, result);
			task4_helper(node.right, path, result);
			
			path.remove(path.size() - 1);
			
		}
		
		public int getLargestSum(ArrayList<Integer> list) {
			if (list == null || list.size() == 0) {
				return 0;
			}
			int sum = 0;
			int maxSum = Integer.MIN_VALUE;
			for(int i = 0; i < list.size(); i ++) {
				if (sum < 0) {
					sum = 0;
				}
				sum += list.get(i);
				maxSum = Math.max(maxSum, sum);
			}
			return maxSum;
		}
		
	/*
	 * task4
		Reconstruct Binary Tree With Preorder And Inorder
		Fair
		Recursion
		Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

		Assumptions

		The given sequences are not null and they have the same length
		There are no duplicate keys in the binary tree
		Examples

		preorder traversal = {5, 3, 1, 4, 8, 11}

		inorder traversal = {1, 3, 4, 5, 8, 11}

		the corresponding binary tree is

		        5

		      /    \

		    3        8

		  /   \        \

		1      4        11

		How is the binary tree represented?

		We use the pre order traversal sequence with a special symbol "#" denoting the null node.

		For Example:

		The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

		    1

		  /   \

		 2     3

		      /

		    4
		    */
		
		  public TreeNode task4_reconstruct(int[] in, int[] pre) {
			    // Write your solution here.
			    return null;
			  }
		  
	/*
	 * task5
		  Maximum Path Sum Binary Tree II
		  Hard
		  Recursion
		  Given a binary tree in which each node contains an integer number. Find the maximum possible sum from any node to any node (the start node and the end node can be the same). 

		  Assumptions

		  ​The root of the given binary tree is not null
		  Examples

		     -1

		    /    \

		  2      11

		       /    \

		      6    -14

		  one example of paths could be -14 -> 11 -> -1 -> 2

		  another example could be the node 11 itself

		  The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18

		  How is the binary tree represented?

		  We use the level order traversal sequence with a special symbol "#" denoting the null node.

		  For Example:

		  The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

		      1

		    /   \

		   2     3

		        /

		      4
		      */
	public int task5_maxPathSum(TreeNode root) {
		// Write your solution here.

		if (root == null) {
			return 0;
		}
		Result result = new Result(Integer.MIN_VALUE);
		maxPath(root, result);
		return result.val;
	}

	public static int maxPath(TreeNode root, Result result) {
		if (root == null) {
			return 0;
		}

		int left = maxPath(root.left, result);
		int right = maxPath(root.right, result);

		// cur
		if (left < 0) {
			left = 0;
		}
		if (right < 0) {
			right = 0;
		}
		if (result.val < root.key + left + right) {
			result.val = root.key + left + right;
		}
		return Math.max(left, right) + root.key;
	}
	
	/*
	 * task6
	 
	Reconstruct Binary Tree With Levelorder And Inorder
	Hard
	Recursion
	Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

	Assumptions

	The given sequences are not null and they have the same length
	There are no duplicate keys in the binary tree
	Examples

	levelorder traversal = {5, 3, 8, 1, 4, 11}

	inorder traversal = {1, 3, 4, 5, 8, 11}

	the corresponding binary tree is

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	How is the binary tree represented?

	We use the pre order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	  
	*/
	public TreeNode task6_reconstruct(int[] in, int[] level) {
		// Write your solution here.
		return null;
	}
	
	
}

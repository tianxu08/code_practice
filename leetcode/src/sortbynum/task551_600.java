package sortbynum;

import java.util.*;

import javax.security.auth.kerberos.KerberosKey;

import ds.*;


public class task551_600 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test526();
//		test572();
		test576();
	}

	/**
	 * task526: Beautiful Arrangement
	 */

	public static void test526() {
		int N = 3;
		int rev = task526_countArrangement(N);
		System.out.println("rev = " + rev);
	}

	public static int task526_countArrangement(int N) {
		task526_count = 0;
		task526_helper(new HashSet<>(), N);
		return task526_count;
	}

	public static int task526_count = 0;

	public static void task526_helper(Set<Integer> set, int N) {
		if (set.size() == N) {
			task526_count++;
			return;
		}

		for (int i = 1; i <= N; i++) {
			int nextIdx = set.size() + 1;
			if (!set.contains(i)) {
				if (i % nextIdx == 0 || nextIdx % i == 0) {
					set.add(i);
					task526_helper(set, N);
					set.remove(i);
				}
			}

		}
	}

	// opt
	public int task526_countArrangement_opt(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		task526_helper(arr, 0);
		return counter;
	}

	public int counter = 0;

	public void task526_helper(int[] arr, int index) {
		if (index == arr.length) {
			// System.out.println(Arrays.toString(arr));
			counter++;
			return;
		}
		for (int i = index; i < arr.length; i++) {

			if (arr[i] % (index + 1) == 0 || (index + 1) % arr[i] == 0) {
				// put arr[i] into index's position.
				swap(arr, index, i);
				task526_helper(arr, index + 1);
				swap(arr, index, i);
			}
		}
	}

	public void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	/**
	 * task559
	 */
	
	static class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val,List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	};
	

	public static int task559_maxDepth(Node root) {
		return task559_helper(root);
    }
	
	public static int task559_helper(Node node) {
		if (node == null) {
			return 0;
		}
		if (node.children == null || node.children.size() == 0) {
			return 1;
		}
		int maxDepth = 0;
		for (Node n: node.children) {
			int curDepth = task559_helper(n);
			maxDepth = Math.max(maxDepth, curDepth);
		}
		return maxDepth + 1;
	}
	
	
	
	
	
	/*
	 * 563: Binary Tree Tilt
	 * Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
Note:

The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.

	 */

	public static int task563_totalTilt = 0;

	public static int findTilt(TreeNode root) {
		task563_helper(root);
		return task563_totalTilt;
	}
	
	public static int task563_helper(TreeNode node) {
		if (node == null) return 0;
		int leftSum = task563_helper(node.left);
		int rightSum = task563_helper(node.right);
		
		// get tilt
		int tilt = Math.abs(leftSum - rightSum);
		task563_totalTilt += tilt;
		
		return leftSum + rightSum + node.val;
	}
	
	/**
	 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.

	 */
	

	public boolean isSameTree(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return true;
		}
		if (t1 == null || t2 == null) {
			return false;
		}
		if (t1.val != t2.val) {
			return false;
		}
		return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
	}

	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (t == null) {
			return true;
		}
		return s != null && (isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
	}

	
	public static void test572() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		
		TreeNode n11 = new TreeNode(2);
		TreeNode n12 = new TreeNode(4);
		TreeNode n13 = new TreeNode(5);
		n11.left = n12;
		n11.right = n13;
		
		boolean rev = task572_isSubtree(null, n11);
		System.out.println("rev = " + rev);
	}
	public static boolean task572_isSubtree(TreeNode s, TreeNode t) {
		String sStr = task572_serizlize(s);
		String tStr = task572_serizlize(t);
		System.out.println("sStr: " + sStr);
		System.out.println("tStr: " + tStr);
		if (sStr.contains(tStr)) {
			return true;
		}
		
		return false;
	}
	
	public static String task572_serizlize(TreeNode n) {
		StringBuilder stringBuilder = new StringBuilder();
		task572_serialize(n, stringBuilder);
		return stringBuilder.toString();
	}
	public static void task572_serialize(TreeNode n, StringBuilder stb) {
		if (n == null) {
			stb.append("#");
			return ;
		}
		stb.append("(" + n.val + ")");
		task572_serialize(n.left, stb);
		task572_serialize(n.right, stb);
	}
	
	/**
	 * task576 out of boundry paths
	 * 
	 */
	
	public static void test576() {

		
		int m = 8;
		int n = 50;
		int N = 23;
		int i = 5;
		int j = 26;
	
		int rev = task672_findPathOPT(m, n, N, i, j);
		System.out.println("rev = " + rev);
	}
	
	public static int task672_findPath(int m, int n, int N, int i, int j) {
		if (i < 0 || i >= m || j < 0 || j > n) {
			return 1;
		}
		
		return task672_findPath(m, n, N - 1, i - 1, j) + task672_findPath(m, n, N - 1, i, j - 1) + task672_findPath(m, n, N - 1, i, j - 1) + task672_findPath(m, n, N - 1, i, j + 1); 
	}
	
	
	public static int M=1000000007;
	public static int task672_findPathOPT(int m, int n, int N, int i, int j) {
		int[][][] memo = new int[m][n][N + 1];
		for (int[][] l : memo) {
			for (int[] sl: l) {
				Arrays.fill(sl, -1);
			}
		}
		return task672_findPathOPTHelper(m, n, N, i, j, memo);
	}
	
	public static int task672_findPathOPTHelper(int m, int n, int N, int i, int j, int[][][] memo) {
		if (i < 0 || i >= m || j < 0 || j >= n) {
			return 1;
		}
		
		if (N == 0) {
			return 0;
		}
		
		if (memo[i][j][N] >= 0) {
			return memo[i][j][N];
		}
		
		int t1 = task672_findPathOPTHelper(m, n, N - 1, i - 1, j, memo);
		int t2 = task672_findPathOPTHelper(m, n, N - 1, i, j - 1, memo);
		int t3 = task672_findPathOPTHelper(m, n, N - 1, i + 1, j, memo) ;
		int t4 = task672_findPathOPTHelper(m, n, N - 1, i, j + 1, memo);
		
		int total = ((t1 + t2) % M + (t3 + t4) % M) % M;
		memo[i][j][N] = total;
		return memo[i][j][N];
	}
	
	


}

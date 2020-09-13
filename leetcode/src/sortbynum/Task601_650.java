package sortbynum;

import ds.TreeNode;

public class Task601_650 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test606();
	}
	
	/**
	 * *
	 * @param c
	 * @return
	 * 
	 * 606
	 */
	
	public static void test606() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
//		n2.right = n5;
		
		String rev = task606_tree2Str(n1);
		System.out.println("rev: " + rev);
	}
	public static String task606_tree2Str(TreeNode t) {
		StringBuilder stb = new StringBuilder();
		task606_helper(t, stb);
		return stb.toString();
	}
	
	public static void task606_helper(TreeNode node, StringBuilder sb) {
		if (node == null) {
			return;
		}

		sb.append(node.val);
		if (node.left != null) {
			sb.append("(");
			task606_helper(node.left, sb);
			sb.append(")");
		} else if (node.right != null) {
			sb.append("()");
		}
		if (node.right != null) {
			sb.append("(");
			task606_helper(node.right, sb);
			sb.append(")");
		}
	}
	
	
	/**
	 * 617 merge two binary tree
	 * @param c
	 * @return
	 */
	
	public TreeNode task617_mergeTrees1(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return null;
		}
		TreeNode root;
		if (t1 == null) {
			root = new TreeNode(t2.val);
			root.left = task617_mergeTrees1(null, t2.left);
			root.right = task617_mergeTrees1(null, t2.right);
		} else if (t2 == null) {
			root = new TreeNode(t1.val);
			root.left = task617_mergeTrees1(t1.left, null);
			root.right = task617_mergeTrees1(t1.right, null);
		} else {
			root = new TreeNode(t1.val + t2.val);
			root.left = task617_mergeTrees1(t1.left, t2.left);
			root.right = task617_mergeTrees1(t1.right, t2.right);
		}
		return root;
	}

	public static TreeNode task617_mergeTrees2(TreeNode t1, TreeNode t2) {
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}
		// by here t2 always exists
		t1.val += t2.val;

		t1.left = task617_mergeTrees2(t1.left, t2.left);
		t1.right = task617_mergeTrees2(t1.right, t2.right);

		return t1;
	}

	/*
	 * task633 sum of Square numbers
	 */
	public static boolean task633_judgeSquareSum(int c) {
		if (c < 0) {
			return false;
		}
		int left = 0, right = (int) Math.sqrt(c);
		while (left <= right) {
			int cur = left * left + right * right;
			if (cur < c) {
				left++;
			} else if (cur > c) {
				right--;
			} else {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 638 shopping offers
	 * @param s
	 * @return
	 * 
	 * 
	 */

	/*
	 * 639. Decode Ways II
	 */
	public static int task639_numDecodings(String s) {
		int len = s.length();
		int mod = 1000000007;
		long[] dp = new long[len + 1];
		dp[0] = 1;
		if (s.charAt(0) == '0')
			return 0;
		if (s.charAt(0) == '*') {
			dp[1] = 9;
		} else {
			dp[1] = 1;
		}
		for (int i = 2; i <= len; i++) { // i-1 the index of current character
											// in s.
			if (s.charAt(i - 1) == '0') {
				if (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2') {
					dp[i] = dp[i - 2];
				} else if (s.charAt(i - 2) == '*') {
					dp[i] = 2 * dp[i - 2];
				} else {
					return 0;
				}
			} else if (s.charAt(i - 1) >= '1' && s.charAt(i - 1) <= '9') {
				dp[i] = dp[i - 1];
				if (s.charAt(i - 2) == '1'
						|| (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
					dp[i] = (dp[i] + dp[i - 2]) % mod;
				} else if (s.charAt(i - 2) == '*') {
					if (s.charAt(i - 1) <= '6') {
						dp[i] = (dp[i] + dp[i - 2] * 2) % mod;
					} else {
						dp[i] = (dp[i] + dp[i - 2]) % mod;
					}
				}
			} else { // s.charAt(i-1) == '*'
				dp[i] = (9 * dp[i - 1]) % mod;
				if (s.charAt(i - 2) == '1') { // 11 - 19
					dp[i] = (dp[i] + 9 * dp[i - 2]) % mod;
				} else if (s.charAt(i - 2) == '2') { // 21 - 26
					dp[i] = (dp[i] + 6 * dp[i - 2]) % mod;
				} else if (s.charAt(i - 2) == '*') {
					dp[i] = (dp[i] + 15 * dp[i - 2]) % mod;
				}
			}
		}
		return (int) dp[len];
	}

}

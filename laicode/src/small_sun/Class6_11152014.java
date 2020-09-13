package small_sun;


import ds.TreeNode;

public class Class6_11152014 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
		test4();
	}
	
	
	/*
	 *  Q1.  Given a Binary Tree (BT), convert it to a Doubly Linked List(DLL) In­ Place
	 *  TreeNode{
	 *    TreeNode left;
	 *    TreeNode right;
	 *  }
	 *  In order traversal  
	 *  
	 */
	public static void test1() {
		TreeNode n1 = new TreeNode(10);
		TreeNode n2 = new TreeNode(12);
		TreeNode n3 = new TreeNode(15);
		TreeNode n4 = new TreeNode(25);
		TreeNode n5 = new TreeNode(30);
		TreeNode n6 = new TreeNode(16);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		
		task1_BT2DDL(n1);
		TreeNode cur = head;
		while (cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.right;
		}	
	}
	
	
	public static TreeNode prev = null;
	public static TreeNode head = null;
	public static void task1_BT2DDL(TreeNode node) {
		if (node == null) {
			return ;
		}
		task1_BT2DDL(node.left);
		if (prev == null) {
			// this is the leftMost node
			head = node;
		} else {
			// prev != null
			// link the prev and node
			node.left = prev;
			prev.right = node;
		}
		// update prev
		prev = node;
		task1_BT2DDL(node.right);
	}
	
	
	/*
	 * Q2: atoi 
	 * convert string to integers
	 * 
	 */
	public static void test2() {
		String str = "-100";
		int rev = task2_string2Int(str);
		System.out.println("rev = " + rev);
	}
	
	
	public static int task2_string2Int(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		int sign = 1;
		String input = str.trim();
		int start = 0;
		if (input.charAt(start) == '-') {
			sign = -1;
			start ++;
		} 
		long sum = 0;
		for(; start < input.length(); start ++) {
			char curCh = input.charAt(start);
			if (!Character.isDigit(curCh)) {
				return (int)sum * sign;
			}
			sum = sum * 10 + (curCh - '0');
			// !!! there might be overflow
			if (sign > 0 && sum > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			} 
			if (sign < 0 && sign * sum < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}
		return (int) sum * sign;
	}
	/*
	 * String.trim()
	 * It returns a copy of this string with leading and trailing white space removed, 
	 * or this string if it has no leading or trailing white space. 
	 */
	
	
	
	/*
	 * Q3 Longest palindromic substring
	 * palindrome: a word, phrase, number which reads the same forward or backword
	 * 
	 * dp[i][j] the whether substring s[i..j] is a palindrome
	 * 
	 * init:
	 * dp[i][i] = T;
	 * dp[i][i + 1] = T when s[i] == s[i + 1]
	 * 
	 * dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j]
	 * 
	 * maxLen, start
	 * return dp[0][n - 1]
	 */
	
	public static String task3_longestPalidromicSubstring(String input){
		if (input == null || input.length() == 0) {
			return null;
		}
		int inputLen = input.length();
		boolean[][] dp = new boolean[inputLen][inputLen];
		int maxLen = 1;
		int maxStart = 0;
		for(int i = 0; i < inputLen; i ++) {
			dp[i][i] = true;
			if (i + 1 < inputLen) {
				if (input.charAt(i) == input.charAt(i + 1)) {
					dp[i][i + 1] = true;
					maxLen = Math.max(maxLen, 2);
					maxStart = i;
				}
			}
		}
		
		for(int len = 3; len <= inputLen; len ++) {
			for(int i = 0; i < inputLen + 1 - len; i++) {
				int j = i + len - 1;     // j < inputLen i + len - 1 < inputLen  i < inputLen + 1 - len
				if (input.charAt(i) == input.charAt(j) && dp[i + 1][j - 1]) {
					dp[i][j] = true;
					maxLen = Math.max(maxLen, len);
					maxStart = i;
				}
			}
		}
		
		return input.substring(maxStart, maxStart + maxLen);
	}
	
	public static void test3() {
		String str = "abbbac";
		String rev = task3_longestPalidromicSubstring(str);
		System.out.println(rev);
	}
	
	
	/*
	 * Q4 Given Inorder and Preorder traversals of a binary tree, 
	 * print Postorder traversal without reconstructing a binary tree.
	 */
	public static void test4() {
		int[] inOrder = {4, 2, 5, 1, 3, 6};
		int[] preOrder = {1, 2, 4, 5, 3, 6};
		task4_printPostOrder(inOrder, preOrder);
	}
	
	public static void task4_printPostOrder(int[] inOrder, int[] preOrder) {
		int start = 0, end = inOrder.length - 1;
		
	}

	
	public static void task4_printPostOrderH(int[] inOrder, int[] preOrder, int start, int end){
		// find the first element in preOrder, is always the root. 
		// search it inOrder[] to find the left and right subtree
		int rootIdx = search(inOrder,preOrder[0]);
		
		// if left subtree is NOT empty, print the left subtree
		if (rootIdx != 0) {
			if (start < rootIdx) {
				task4_printPostOrderH(inOrder,preOrder, start, rootIdx - 1);
			}
		}
		
		if (rootIdx != end) {
			if (rootIdx < end) {
				task4_printPostOrderH(inOrder, preOrder, rootIdx + 1, end);	
			}
		}
		
		System.out.print(preOrder[start] + " ");
	}
	
	public static int search(int[] array, int target) {
		for(int i = 0; i <= array.length; i ++) {
			if (array[i] == target) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	/*
	 * Q5 Given a telephone keypad, and a n digit number, print all words which are possible by pressing
	 * these numbers.
	 */
	
	

}

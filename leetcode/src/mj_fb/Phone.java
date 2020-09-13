package mj_fb;

import ds.ListNode;
import ds.TreeNode;

public class Phone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * task1 Reverse Nodes in k-Group
	 * https://leetcode.com/problems/reverse-nodes-in-k-group/
	 */
	public static ListNode task1_reverseKGroup(ListNode head, int k) {
		return null;
	}

	/*
	 * task2 Move all 0s to the right of an array 
	 * (1) All the zeros are at the
	 * end of the list 
	 * (2) The order of nozero elements is preserved
	 */

	/*
	 * task3 Phone number to string 
	 * Input: string of digits of arbitary length
	 * Output: print all possible
	 */

	/*
	 * task4 2Sum 3Sum 4Sum
	 * 
	 * 3Sum follow up 如果找任意k 个数和 为 0 ？
	 */

	/*
	 * task5 
	 * string 有多少palindrome substring (1) DP Time: O(n*n) Space: O(n*n)
	 * (2) straight forward method Time: O(n*n)
	 * http://www.1point3acres.com/bbs/forum
	 * .php?mod=viewthread&tid=107664&extra=
	 * page%3D1%26filter%3Dsortid%26sortid%3D
	 * 311%26searchoption%5B3088%5D%5Bvalue
	 * %5D%3D1%26searchoption%5B3088%5D%5Btype
	 * %5D%3Dradio%26searchoption%5B3089%5D
	 * %5Bvalue%5D%5B2%5D%3D2%26searchoption%
	 * 5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption
	 * %5B3090%5D%5Bvalue%5D%3D1%26searchoption
	 * %5B3090%5D%5Btype%5D%3Dradio%26searchoption
	 * %5B3046%5D%5Bvalue%5D%3D2%26searchoption
	 * %5B3046%5D%5Btype%5D%3Dradio%26searchoption
	 * %5B3091%5D%5Bvalue%5D%3D2%26searchoption
	 * %5B3091%5D%5Btype%5D%3Dradio%26sortid%3D311
	 */

	/*
	 * task6 https://leetcode.com/problems/number-of-islands/
	 * https://leetcode.com/problems/number-of-islands-ii/
	 */

	/*
	 * task7 在一些区间中，寻找一个点，使这个点能够落入尽量多的区间，提示我用暴力解法 比如 区间： 2,3 | 3,5 | 4,5 | 1,5 |
	 * 1,2 那么4和5都是答案.
	 * 
	 * sort the intervals, then scan
	 * 
	 * it seems to be the scan line problem.
	 * 
	 * 线段树
	 */
	
	

	/*
	 * task8 Plus one
	 */

	/*
	 * task9 merge intervals
	 */

	/*
	 * task10
	 */

	/*
	 * task11
	 */

	/*
	 * task22 1 Flatten list of List 2 Find first bad version 3 Binary Tree to
	 * DDL
	 */
	public static TreeNode task22_3_BT2DLL(TreeNode root) {
		return head;
	}

	public static TreeNode prev = null;
	public static TreeNode head = null;

	public static void task22_3_helper(TreeNode node) {
		if (node == null) {
			return;
		}
		task22_3_helper(node.left);
		if (prev == null) {
			head = node;
		} else {
			node.left = prev;
			prev.right = node;
		}
		// update prev
		prev = node;
		task22_3_helper(node.right);
	}

	/*
	 * task23 1 find alibaba 2 Read N char using read 4 3 Regular expression
	 * matching
	 */

	/*
	 * task24 
	 * 1 merge two sorted list 
	 * 2 reverse words in a string e.g how are you -> you are how 
	 * 3 integer array and remove duplicated elements 
	 * 4 give a tree, return an array containing average values for every level. Tree
	 * is not a binary tree, which means any node may have number of child
	 * nodes. Do BFS.
	 */

	/*
	 * task25
	 */

	public static int partition(String s) {
		int count = 0;
		// single char is palindrome
		for (int i = 0; i < s.length(); i++)
			count++;
		for (int i = 1; i < s.length(); i++) {
			// even position
			for (int l = i - 1, r = i; l >= 0 && r < s.length()
					&& s.charAt(l) == s.charAt(r); l--, r++) {
				count++;
			}
			// odd
			for (int l = i - 1, r = i + 1; l >= 0 && r < s.length()
					&& s.charAt(l) == s.charAt(r); l--, r++) {
				count++;
			}
		}
		return count;
	}

	public int palindrome(String s) {
		if (s.length() == 0)
			return 0;
		if (s.length() == 1)
			return 1;

		int num = 0;
		// initialize the table.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
		int[][] table = new int[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			table[i][i] = 1;
			num += 1;
		}
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				table[i][i + 1] = 1;
				num += 1;
			} else {
				table[i][i + 1] = 0;
			}

		}

		// looping

		for (int len = 3; len <= s.length(); len++) {
			for (int i = 0; i <= s.length() - len; i++) {
				if (s.charAt(i) == s.charAt(i + len - 1)
						&& table[i + 1][i + len - 2] == 1) {
					table[i][i + len - 1] = 1;
					num += 1;
				} else {
					table[i][i + len - 1] = 0;
				}
			}
		}

		return num;
	}
	
	
	

}

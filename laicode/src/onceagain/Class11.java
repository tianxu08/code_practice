package onceagain;

import debug.Debug;
import ds.ListNode;
import ds.TreeNode;
import java.util.*;

public class Class11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();
		// test2();
		test3();
	}

	/*
	 * task1 Spiral Order Traverse II
	 */
	public static void task1_spiralOrderTraverse(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int left = 0, right = cLen - 1;
		int up = 0, down = rLen - 1;
		while (left < right && up < down) {
			// left -> right
			for (int j = left; j <= right; j++) {
				System.out.print(matrix[up][j] + " ");
			}
			up++;
			// up -> down
			for (int i = up; i <= down; i++) {
				System.out.print(matrix[i][right] + " ");
			}
			right--;
			// right -> left
			for (int j = right; j >= left; j--) {
				System.out.print(matrix[down][j] + " ");
			}
			down--;
			// down -> up
			for (int i = down; i >= up; i--) {
				System.out.print(matrix[i][left] + " ");
			}
			left++;
		}

		if (left == right) {
			for (int i = up; i <= down; i++) {
				System.out.print(matrix[i][left] + " ");
			}
		} else {
			for (int j = left; j <= right; j++) {
				System.out.print(matrix[up][j] + " ");
			}
		}
		System.out.println();
	}

	public static void test1() {
		int[][] matrix = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 },
				{ 11, 12, 13, 14, 15 } };
		task1_spiralOrderTraverse(matrix);
	}

	/*
	 * task2 Reverse Linked List in Pairs 1 -> 2 -> 3 ->4 ->5 ->6 2 -> 1 -> 4
	 * ->3 ->6 ->5
	 * 
	 * 1 1
	 * 
	 * 1 -> 2 2 -> 1
	 */
	public static ListNode task2_reverseInPairs(ListNode head) {
		// base case
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = task2_reverseInPairs(head.next.next);
		newHead.next = head;
		return newHead;
	}

	public static void test2() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;

		// ListNode list1 = task2_reverseInPairs(n1);
		ListNode list2 = task2_reverseInPairs_Iter(n1);
		// Debug.printLinkedList(list1);
		Debug.printLinkedList(list2);
	}

	/*
	 * dummy -> n1 - > n2 - > n3 -> n4 -> n5 -> n6 cur next nnext
	 */
	public static ListNode task2_reverseInPairs_Iter(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = dummy;

		while (cur != null && cur.next != null) {
			ListNode nnext = cur.next.next;

			cur.next.next = nnext.next;
			nnext.next = cur.next;
			cur.next = nnext;
			cur = nnext.next;
		}
		return dummy.next;

	}

	/*
	 * task3 Abbreviation matching
	 */
	public static boolean task3_abbrev_match(String text, String pattern) {
		return task3_helper(text, 0, pattern, 0);
	}

	public static boolean task3_helper(String text, int tIdx, String pattern,
			int pIdx) {
		if (tIdx == text.length() && pIdx == pattern.length()) {
			return true;
		}
		if (tIdx >= text.length() || pIdx >= pattern.length()) {
			return false;
		}
		if (pattern.charAt(pIdx) < '0' || pattern.charAt(pIdx) > '9') {
			if (pattern.charAt(pIdx) == text.charAt(tIdx)) {
				return task3_helper(text, tIdx + 1, pattern, pIdx + 1);
			}
			return false;
		} else {
			// this is a number
			int counter = 0;
			for (; pIdx < pattern.length() && pattern.charAt(pIdx) >= '0'
					&& pattern.charAt(pIdx) <= '9'; pIdx++) {
				counter = counter * 10 + (pattern.charAt(pIdx) - '0');
			}
			System.out.println("counter = " + counter);
			return task3_helper(text, tIdx + counter, pattern, pIdx);
		}
	
	}

	public static void test3() {
		String text = "happy";
		String pattern = "h3y";
		boolean rev = task3_abbrev_match(text, pattern);
		System.out.println("rev = " + rev);
	}

	/*
	 * task4
 	 * Store Number of nodes in subtree in the node
	 */
	public static class TreeNode4 {
		int value;
		TreeNode4 left;
		TreeNode4 right;
		int numOfLeft;
		public TreeNode4(int v) {
			this.value = v;
			this.left = null;
			this.right = null;
			this.numOfLeft = 0;
		}
	}
	public static void task4_numOfNodesInLeft(TreeNode4 root) {
		task4_helper(root);
	}
	
	public static int task4_helper(TreeNode4 node) {
		if (node == null) {
			return 0;
		}
		int leftNum = task4_helper(node.left);
		int rightNum = task4_helper(node.right);
		
		node.numOfLeft = leftNum;
		return leftNum + rightNum + 1;
	}
	
	/*
	 * task5
	 * 
	 */
	public static TreeNode task5_maxDiffNode(TreeNode root) {
		if (root == null) {
			return null;
		}
		int[] diff = new int[1];
		TreeNode[] result = new TreeNode[1];
		task5_helper(root, diff, result);
		return result[0];
	}
	public static int task5_helper(TreeNode node, int[] diff, TreeNode[] result) {
		if (node == null) {
			return 0;
		}
		int leftNum = task5_helper(node.left, diff, result);
		int rightNum = task5_helper(node.right, diff, result);
		
		if (diff[0] > Math.abs(leftNum - rightNum)) {
			diff[0] = Math.abs(leftNum - rightNum);
			result[0] = node;
		}
		return leftNum + rightNum + 1;
		
	}
	
	/*
	 * task 6
	 * Lowest Common Concestor
	 */
	public static TreeNode task6_lowestCommonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
		return task6_helper(root, n1, n2);
	}
	public static TreeNode task6_helper(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return null;
		}
		if (root == n1 || root == n2) {
			return root;
		}
		TreeNode left = task6_helper(root.left, n1, n2);
		TreeNode right = task6_helper(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		} 
		if (left != null) {
			return left;
		} else{
			return right;
		}
	}
	
	/*
	 * task6.1
	 * Lowest Common Concestor with parent pointer
	 */
	public static class TreeNodeP{
		int value;
		TreeNodeP left;
		TreeNodeP right;
		TreeNodeP parent;
		public TreeNodeP(int v) {
			this.value = v;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}
	
	public static TreeNodeP task6_1_lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		int oneDepth = task6_1_getDepth(one);
		int twoDepth = task6_1_getDepth(two);
		if(oneDepth < twoDepth) {
			return task6_1_lowestCommonAncestor(two, one);
		}
		int diff = oneDepth - twoDepth;
		while(diff > 0) {
			one = one.parent;
			diff --;
		}
		
		while(one != two) {
			one = one.parent;
			two = two.parent;
		}
		return one;
	}
	public static int task6_1_getDepth(TreeNodeP node) {
		int depth = 0;
		while(node != null) {
			depth ++;
			node = node.parent;
		}
		return depth;
	}
	
	/*
	 * task6.2 
	 * Lowest Common Ancestor of K nodes
	 */
	public static TreeNode task6_2_LowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
		if (root == null) {
			return null;
		}
		for(TreeNode node: nodes) {
			if (root == node) {
				return root;
			}
		}
		
		TreeNode left = task6_2_LowestCommonAncestor(root.left, nodes);
		TreeNode right = task6_2_LowestCommonAncestor(root.right, nodes);
		
		if (left != null && right != null) {
			return root;
		}
		if (left != null) {
			return left;
		} else {
			return right;
		}
	} 
	
	
}

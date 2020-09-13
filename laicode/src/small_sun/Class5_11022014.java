package small_sun;

import java.util.Arrays;
import java.util.LinkedList;

import small_yan.IntervalTree.ITNode;
import ds.ListNode;
import ds.TreeNode;

public class Class5_11022014 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test6();
		test7();
	}
	
	/*
	 * Q1. Given a list of records, each record has (start, end, weight). 
	 * find a subset of the records, such that there is no overlap, and the sum of weight is maximized.
	 * 
	 * state[x] : the maximum weights ending at records whose starting at x
	 * state[x] = weight[x] + max(state[i]) if no overlapping b etween x and i  (0 <= i < x)
	 * 
	 */
	public static void test1() {
		Record r1 = new Record(0, 2, 3);
		Record r2 = new Record(1, 2, 4);
		Record r3 = new Record(3, 5, 9);
		Record[] records = {r1, r2, r3};
		int rev = task1_max_sum_weight(records);
		System.out.println(rev);
	}
	
	
	public static class Record{
		int x;
		int y;
		int w;
		public Record(int _x, int _y, int _w) {
			this.x = _x;
			this.y = _y;
			this.w = _w;
		}
	}
	
	/*
	 * Sort by the start of the record
	 * state[i]: the max sum weight ending records[i] and there is no overlap
	 * 
	 * state[i] = state[j] + records[i]  && record[i] and record[j] there is no overlap
	 * (j >= 0 && j < i)
	 */
	public static int task1_max_sum_weight(Record[] records) {
		int n = records.length;
		int[] state = new int[n];
		state[0] = records[0].w;
		for(int i = 1; i < n; i ++) {
			int prevMax = 0;
			for(int j = 0; j < i ; j ++) {
				if (records[i].x < records[j].y) {
					// there is overlap
					continue;
				} else {
					// update the maximum previous weight
					prevMax = Math.max(prevMax, state[j]);
				}
			}
			// update the state
			state[i] = prevMax + records[i].w;
		}
		System.out.println(Arrays.toString(state));
		
		int maxSumWeight = Integer.MIN_VALUE;
		for(int i = 0; i < n; i ++) {
			maxSumWeight = Math.max(state[i], maxSumWeight);
		}
		
		return maxSumWeight;
	}
	
	
	
	/*
	 * Q2   Given two binary trees (with unique letters), check if the first tree is subtree of the second one. 
	 * A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.
	 * 
	 * API
	 * isSameTree(TreeNode root1, TreeNode root2);
	 * 
	 * Traverse the second tree, find the node which equals first root;
	 * call isSameTree(node, root1)
	 */
	
	
	
	/*
	 * Q3  Sort an array according to the order defined by another array. 
	 * Given two arrays A1[] and A2[], sort A1 in such a way that the relative order 
	 * among the elements will be same as those are in A2. For the elements not present in A2, 
	 * append them at last in sorted order. 
	 */
	
	
	
	/*
	 * Q4:  Construct a tree from Inorder and Level order traversals
	 * Given inorder and level­order traversals of a Binary Tree  
	 * (you can assume all unique numbers in the tree) , construct the Binary Tree. 
	 * Following is an example to illustrate the problem.
	 */
	
	
	
	/*
	 * Q5 
	 * Given a binary tree, print all paths that can start from any node and end at any node 
	 * that sum to a target number.   
	 * e.g.  target  = 22 then return (1) {22}, and (2) {12, 10}
	 * the value of the node can be any arbitrary integer. {negative, zero, positive}
	 */
	
	
	
	
	/*
	 * Q6. Given a linked list, check whether it is a palindrome.   Space O(1)
	 */
	public static boolean task6_listpalindrom(ListNode head) {
		ListNode mid = task6_getMid(head);
		ListNode s_head = mid.next;
		mid.next = null;
		ListNode second_head = task6_reverse(s_head);
		
		ListNode ptr1 = head;
		ListNode ptr2 = second_head;
		
		// the first part and the second part are 
		while(ptr1 != null && ptr2 != null){
			if (ptr1.value != ptr2.value) {
				return false;
			}
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}
		return true;
	}
	
	
	/*
	 *  1 -> 2 -> 3 -> 4 ->5 ->6 -> 7
	 */
	public static void test6() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);
		 
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		
		ListNode mid = task6_getMid(n1);
		System.out.println("mid = " + mid.value);
	}
	public static ListNode task6_getMid(ListNode head) {
		ListNode fast = head.next;
		ListNode slow = head;
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public static ListNode task6_reverse(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}
		ListNode next = node.next;
		ListNode newHead = task6_reverse(next);
		next.next = node;
		node.next = null;
		return newHead;
	}
	
	
	/*
	 * Q7 Given an array of strings, find if the strings can be chained to form a circle
	 * !!! implement
	 */
	public static void test7() {
		String[] strarr = {
				"aaa", "bbb", "aab", "bba"
		};
		task7_findCircle(strarr);
	}
	
	public static void task7_findCircle(String[] strarr){
		if (strarr == null || strarr.length == 0) {
			return ;
		}
		if (strarr.length == 1) {
			String cur = strarr[0];
			if (cur.charAt(0) == cur.charAt(cur.length() - 1)) {
				System.out.println(cur);
			}
		} else {
			task7_circleHelper(strarr, 1);
		}
		
		
	}
	
	public static void task7_circleHelper(String[] strarr, int index) {
		if (index == strarr.length) {
			// if the last string's last char equals the first str's first char
			String first = strarr[0];
			String last = strarr[strarr.length - 1];
			if (first.charAt(0) == last.charAt(last.length() - 1)) {
				// print out the string array
				for(int i = 0; i < strarr.length; i ++) {
					System.out.print(strarr[i] + " ");
				}
				System.out.println();
			}
			return ;
		}
		
		for(int i = index; i < strarr.length; i++) {
			// get the previous string in strarr
			String prev = strarr[index - 1];
			// get the current string in strarr
			String cur = strarr[i];
			// the last char in previous string
			char prev_last = prev.charAt(prev.length() - 1);
			// the first char in current string
			char cur_first = cur.charAt(0);
			if (prev_last == cur_first) {
				swap(strarr, i, index);
				task7_circleHelper(strarr, index + 1);
				swap(strarr, i, index);
			}
		}
	} 
	
	public static void swap(String[] strarr, int x, int y){
		String temp = strarr[x];
		strarr[x] = strarr[y];
		strarr[y] = temp;
	}
	
	
	
	
	
	/*
	 * Q8.  Find distance between two given keys of a Binary Tree
	 * (1) Get LCA of the two given nodes
	 * (2) using BFS/Level order traversal to get the distance from LCA to the two given nodes
	 * 
	 */
	public static int task8_distanceBetweenTwoNodes(TreeNode root, TreeNode n1, TreeNode n2) {
		// assume that n1 and n2 are both in the tree whose root is "root"
		TreeNode LCA = LCA(root, n1, n2);
		if (LCA == n1) {
			return BFS(LCA, n2);
		} else if (LCA == n2) {
			return BFS(LCA, n1);
		} else {
			return BFS(LCA, n1) + BFS(LCA, n2);
		}
	}
	
	public static TreeNode LCA(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return null;
		}
		if (root == n1 || root == n2) {
			return root;
		}
		TreeNode left = LCA(root.left, n1, n2);
		TreeNode right = LCA(root.right, n1, n2);
		
		if (left != null && right != null && left != right) {
			return root;
		}
		return left == null ? right : left;
	}
	
	// get the distance of root and n, assume n is in the tree rooted by root 
	public static int BFS(TreeNode root, TreeNode n) {
		int distance = 0;
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i ++) {
				TreeNode cur = q.poll();
				if (cur == n) {
					return distance;
				}
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
			distance ++;
		}
		return distance;
	}
	
	
	
}

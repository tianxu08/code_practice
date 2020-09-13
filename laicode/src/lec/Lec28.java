package lec;

import ds.Tree;
import ds.TreeNode;

public class Lec28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Q3_test();
	}
	
	/*
	 * Q1 Longest Increasing Sub­Array vs Sub­Sequence problem
	 * Q1.1 ​Longest Ascending Subarray
	 * Q1.2 ​Longest Ascending Subsequence
	 */
	
	
	
	/*
	 * Q2 De­serialization of binary tree problems: 
	 * Reconstruct a BST by using xxx­order and i​n­order t​raversal sequences
	 * 
	 * Q2.1​ How to reconstruct a BST with ​pre­order​ and i​n­order​sequences of all nodes.
	 * Q2.2 How to reconstruct a BST with ​post­order​ and i​n­order​sequences of all nodes.
	 * Q2.3 ​How to reconstruct a BST with ​level order​ and i​n­order​sequences of all nodes.
	 * 
	 */
	 
	
	/*
	 * Q3.Serialization of a binary tree
	 * Given a binary tree, convert it to a doubly linked List according to its i​norder sequence.
	 */
	public static void Q3_test() {
		int[] a = {1,2,3,4,5,6,7,8};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		TreeNode head = convertFromBinaryTree2DDL(root);
		System.out.println("--- after convert ---");
		printDDL(head);
	}
	public static void printDDL(TreeNode head) {
		if (head == null) {
			return ;
		}
		TreeNode cur = head;
		TreeNode tail = null;
		while(cur != null) {
			System.out.print(cur.val + " ");
			if(cur.right == null) {
				tail = cur;
//				System.out.println(tail.val);
			}
			cur = cur.right;
		}
		System.out.println();
		while(tail != null) {
			System.out.print(tail.val + " ");
			tail = tail.left;
		}
	}
	
	public static class WrapNode {
		TreeNode prev;
		TreeNode head;
		public WrapNode() {
			this.prev = null;
			this.head = null;
		}
		public WrapNode(TreeNode prev, TreeNode head) {
			this.prev = prev;
			this.head = head;
		}
	}
	public static TreeNode convertFromBinaryTree2DDL(TreeNode root) {
		if (root == null) {
			return root;
		}
		WrapNode wrap = new WrapNode();
		convertFromBinaryTree2DDL(root, wrap);
		return wrap.head;
	}
	
	public static void convertFromBinaryTree2DDL(TreeNode node, WrapNode wrap) {
		if (node == null) {
			return ;
		}
		convertFromBinaryTree2DDL(node.left, wrap);
		// 
		if (wrap.prev == null) {
			// we reach the left most node. set the head
			wrap.head = node;
		} else {
			node.left = wrap.prev;
			wrap.prev.right = node;
		}
		wrap.prev = node;
		convertFromBinaryTree2DDL(node.right, wrap);
	}
	
	/*
	 * Q4 Most number of points in 2D space problems
	 * Q4.1 ​Given an array of coordinates of points, 
	 *      how to​ find largest number of points​that can be crossed by a same line in 2D space?
	 * 
	 * Q4.2 ​Given an array of coordinates of points, how to find the largest number of points that
	 *      can form a set such that for any pair of points in the set can form a line with positive slope.
	 */
	
	
	
	/*
	 * Q5 How to design a search suggestion system.
	 * 
	 */

}

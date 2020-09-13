package review;

import java.util.Deque;
import java.util.LinkedList;

import debug.Debug;
import ds.ListNode;
import ds.TreeNode;

public class Ch4_binary_tree_binary_search_tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/*
	 * 1 preOrder binary tree
	 */
	
	/*
	 * M1
	 * 1 we can always print out the root first, the root can be removed from the stack
	 * 2 We traverse left sub first, so the righti sub tree should be retained in the stack before the 
	 *   left sub is done
	 */
	public static void t1_preOrder_iter1(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		st.offerFirst(root);
		while(!st.isEmpty()) {
			// print out the stack's top element
			TreeNode cur = st.pollFirst();
			System.out.print(cur.val + " ");
			if (cur.right != null) {
				st.offerFirst(cur.right);
			}
			if (cur.left != null) {
				st.offerFirst(cur.left);
			}
		}
	}
	
	public static void test1() {
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(1);
		TreeNode n4 = new TreeNode(3);
		TreeNode n5 = new TreeNode(8);
		
		n1.left = n2;
		n1.right = n5;
		n2.left = n3;
		n2.right = n4;
		t1_preOrder_iter1(n1);
		System.out.println("---");
		t1_preOrder_iter2(n1);
		System.out.println("~~~");
		t2_inOrder_iter(n1);
		System.out.println("\n----post order---");
		t3_postOrder_iter(n1);
		
		System.out.println("\n---post order 2---");
		Debug.postOrder(n1);
		
	}
	
	public static void t1_preOrder_iter2(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !st.isEmpty()) {
			if (cur != null) {
				// print out cur.val
				System.out.println(cur.val);
				st.offerFirst(cur);
				// update cur 
				cur = cur.left;
			} else {
				// cur == null
				cur = st.pollFirst();
				
				cur = cur.right;
			}
		}
	}
	
	/*
	 * 2 inOrder binary tree
	 * the problem is, we cannot throw away the root in the stack before we traversed all the node in left subtree. 
	 * 
	 * How can we know we have already traversed all the nodes in the left sub? 
	 * 
	 * Use a helper node to store the current 'visiting' node to help to identify when we go back to the 
	 * previous level. 
	 * 
	 * 1 when helper node is not null, push helper and let helper = helper.left
	 * 2 when helper is null, means the left subtree is finished, we can print the top, and let 
	 *   helper = top.right(traverse the left subtree first, then root, then the right subtree)
	 * 3 do 1 and 2 until helper is null and there is no node left in the stack. 
	 */
	public static void t2_inOrder_iter(TreeNode root) {
		if (root == null) {
			return ;
		}
		Deque<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !st.isEmpty()) {
			if (cur != null) {
				st.offerFirst(cur);
				cur = cur.left;
			} else {
				cur = st.pollFirst();
				// print out the cur.val
				System.out.print(cur.val + " ");
				cur = cur.right;
			}
		}
	}
	
	/*
	 * 3 postOrder binary tree
	 * maintain a previous Node, to record he previous visiting node on the traversing path. 
	 * -- current Node = stack.top;
	 * -- when previous node == current node.left, means current node's left subtree is done
	 * -- when previous node == current node.right, means current node's right subtree is also done.  
	 */
	public static void t3_postOrder_iter(TreeNode root) {
		Deque<TreeNode> st = new LinkedList<TreeNode>();
		
		TreeNode prev = null;
		st.offerFirst(root);
		while(!st.isEmpty()) {
			TreeNode cur = st.peekFirst();
			if (prev == null || prev.left == cur || prev.right == cur) {
				// prev is null or cur is prev's child
				// go down. left subtree has higher proreity.  
				if (cur.left != null) {
					st.offerFirst(cur.left);
				} else if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// this node is leaf node
					// print out
					System.out.print(cur.val + " ");
					// once print out, pop the cur from stack
					st.pollFirst();
				}
			} else if (cur.left == prev) {
				// prev is cur.left child
				// the left subtree we have finished
				if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// cur.right == null
					// print the cur out
					System.out.print(cur.val + " ");
					st.pollFirst();
				}
			} else if(cur.right == prev){
				System.out.print(cur.val + " ");
				st.pollFirst();
			}
			
			// update prev
			prev = cur;
		}
	}
	
	
	/*
	 * 4 check if binary tree is balanced
	 */
	public static boolean t4_isbalanced(TreeNode root) {
		return false;
	}
	
	/*
	 * 5 symmetric binary tree
	 */
	
	
	/*
	 * 6 tweaked identical binary tree
	 */
	
	/*
	 * 7 is Binary Search or NOT
	 */
	public static boolean t7_isBinaryTree(TreeNode root) {
		return t7_helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static boolean t7_helper(TreeNode node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.val > max || node.val < min) {
			return false;
		}
		return t7_helper(node.left, min, node.val) && t7_helper(node.right, node.val,  max);
	}
	
	/*
	 * 8 Get keys in binary search in Given Range
	 */
	
	
	
	
	
	/*
	 * Binary Search Tree. Create/Insert/Delete
	 */
	

}

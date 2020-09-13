package tree;

import java.util.*;

public class P6_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test6_7();
	}
	
	/*
	 * task1
	 * Construct BST from given preorder traversal | Set 1
	 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
	 */
	
	/*
	 * task2
	 * Construct BST from given preorder traversal | Set 2
	 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversal-set-2/
	 */
	
	/*
	 * task3
	 * Floor and Ceil from a BST
	 * http://www.geeksforgeeks.org/floor-and-ceil-from-a-bst/
	 */
	
	/*
	 * task4
	 * Iterative Preorder Traversal
	 * http://www.geeksforgeeks.org/iterative-preorder-traversal/
	 */
	
	/*
	 * task5
	 * Convert a BST to a Binary Tree such that sum of all greater keys is added to every key
	 * http://www.geeksforgeeks.org/convert-bst-to-a-binary-tree/
	 */
	
	/*
	 * task6
	 * Morris traversal for Preorder
	 * http://www.geeksforgeeks.org/morris-traversal-for-preorder/
	 */
	public static void MorrisPreOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeNode cur = root;
		while(cur != null) {
			if (cur.left == null) {
				System.out.print(cur.val + " ");
				cur = cur.right;
			} else {
				TreeNode predecessor = cur.left;
				while(predecessor.right != null && predecessor.right != cur) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = cur;
					System.out.print(cur.val + " ");
					cur = cur.left;
				} else {
					// predecessor.right == cur
					predecessor.right = null;
//					System.out.print(cur.val + " ");
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}
	
	
	
	/*
	 * task7
	 * Morris traversal for inOrder
	 *
	 * 1 if cur.left == null, print out the cur.val, cur = cur.right;
	 * 2 if cur.left != null
	 *    find the predecessor node of cur. 
	 *    	if (predecessor.right == null)  link predecessor.right = cur; cur go to left. cur = cur.left
	 *      if (predecessor.right == cur)   unlink, set predecessor.right = null. print out cur.val
	 *                                      cur = cur.right;
	 */
	
	public static void MorrisInorder(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeNode cur = root;
		while(cur != null) {
			if (cur.left == null) {
				System.out.print(cur.val + " ");
				cur = cur.right;
			} else {
				TreeNode predecessor = cur.left;
				while(predecessor.right != null && predecessor.right != cur) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = cur;
					cur = cur.left;
				} else {
					// predecessor.right == cur
					predecessor.right = null;
					System.out.print(cur.val + " ");
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}
	public static void test6_7() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);;
		Tree.inOrder(root);
		Tree.postOrder(root);
		System.out.println("print by morris inOrder");
		MorrisInorder(root);
		System.out.println("print by morris preOrder");
		MorrisPreOrder(root);
		System.out.println("print post order 2 stacks ");
		postOrder2Stacks(root);
		
		System.out.println("print post order 1 stack: ");
		postOrder1Stack(root);
	}
	
	
	/*
	 * task8
	 * Linked complete binary tree & its creation
	 * http://www.geeksforgeeks.org/linked-complete-binary-tree-its-creation/
	 */
	
	/*
	 * task9
	 * Ternary Search Tree
	 * http://www.geeksforgeeks.org/ternary-search-tree/
	 */
	
	/*
	 * task10
	 * Segment Tree | Set 1 (Sum of given range)
	 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
	 */
	
	
	
	/*
	 * task11
	 * Segment Tree | Set 2 (Range Minimum Query)
	 * http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
	 */
	
	/*
	 * task12
	 * Dynamic Programming | Set 26 (Largest Independent Set Problem)
	 * http://www.geeksforgeeks.org/largest-independent-set-problem/
	 */
	
	/*
	 * task13
	 * Iterative Postorder Traversal | Set 1 (Using Two Stacks)
	 * http://www.geeksforgeeks.org/iterative-postorder-traversal/
	 */
	
	public static void postOrder2Stacks(TreeNode root) {
		if (root == null) {
			return ;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		Stack<Integer> out = new Stack<Integer>();
		st.push(root);
		
		while(!st.isEmpty()) {
			TreeNode cur = st.pop();
			out.push(cur.val);
			if (cur.left != null) {
				st.push(cur.left);
			}
			if (cur.right != null) {
				st.push(cur.right);
			}
		}
		while(!out.isEmpty()) {
			System.out.print(out.pop() + " ");
		}
		System.out.println();
	}
	
	
	/*
	 * task14
	 * Iterative Postorder Traversal | Set 2 (Using One Stack)
	 * http://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
	 */
	public static void postOrder1Stack(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeNode cur = root;
		Stack<TreeNode> st = new Stack<TreeNode>();
		while (cur != null || !st.isEmpty()) {
			while(cur != null) {
				if (cur.right != null) {
					st.push(cur.right);
				}
				st.push(cur);
				cur = cur.left;
			}
			cur = st.pop();
			if (cur.right != null && !st.isEmpty() && st.peek() == cur.right) {
				st.pop();
				st.push(cur);
				cur = cur.right;
			} else {
				System.out.print(cur.val + " ");
				cur = null;
			}
		}
	}
	
	
	/*
	 * task15
	 * Find if there is a triplet in a Balanced BST that adds to zero
	 * http://www.geeksforgeeks.org/find-if-there-is-a-triplet-in-bst-that-adds-to-0/
	 */

}

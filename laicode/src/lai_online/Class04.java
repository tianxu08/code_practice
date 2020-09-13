package lai_online;

import java.util.ArrayList;
import java.util.LinkedList;

import ds.TreeNode;

import java.util.*;

public class Class04 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
		
	}
	
	public static void test() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		
//		task1_inOrder(n1);
		
		System.out.println(task1_postOrder(n1));
		postOrder_1stack(n1);
		System.out.println();
		task2_morris_inorder_traversal(n1);
		
	}
	
	/*
	 * task1
	 * 
	 * iterative
	 * preOrder
	 * 
	 *      1
	 *     / \
	 *    2   3
	 *   / \
	 *   4  5
	 *  			print
	 * stack  1
	 *        null    1
	 * stack  3 2    
	 *        3       2
	 * stack  3 5 4   
	 *                4
	 *                5
	 *                3
	 * order: 1 2 4 5 3
	 * 
	 * inOrder:
	 *      1
	 *     / \
	 *    2   3
	 *   / \
	 *  4  5
	 *    
	 * stack     cur    print 
	 *            1
	 * 1          2
	 * 1 2        4
	 * 1 2 4      null    
	 * 1 2		  4  	 4
	 * 1 2        null
	 * 1          2      2
	 *            5
	 * 1 5        null
	 * 1          5     5
	 *            null
	 * empty      1     1
	 *            3
	 * 3          null
	 *            3     3
	 *            null     
	 * order: 4 2 5 1 3
	 * 
	 * postOrder:
	 * 
	 */

	public static void task1_preOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		st.offerFirst(root);
		while(!st.isEmpty()) {
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
	
	
	public static void task1_preOrder2(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !st.isEmpty()) {
			if (cur != null) {
				System.out.print(cur.val + " ");
				// push cur into stack
				st.offerFirst(cur);
				cur = cur.left;
			} else {
				// cur == null, get cur = st.pop()
				// go to cur.right
				cur = st.pollFirst();
				cur = cur.right;
			}
		}
	}
	
	public static void task1_inOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode cur = root;
		
		while(cur != null || !st.isEmpty()) {
			// debug:
			// traverse the stack
//			System.out.println("---------------");
//			for(TreeNode node: st) {
//				System.out.print(node.val + " ");
//			}
//			System.out.println();
//			if (cur == null) {
//				System.out.println("cur == null");
//			} else {
//				System.out.println("cur.val = " + cur.val);
//			}
			System.out.println("---------------");
			if (cur != null) {
				st.offerLast(cur);
				cur = cur.left;
			} else {
				cur = st.pollLast();
				System.out.println("==========");
				System.out.print(cur.val + " ");
				System.out.println("\n==========");
				cur = cur.right;
			}
		}
	}
	
	
	
	/*
	 *      1
	 *     / \
	 *    2   3
	 *   / \
	 *  4  5
	 *  
	 *  prev is the pointer in traverse, the previous node we access
	 *  (1) prev == null || prev is cur's parent, going down, left child has higher priority
	 *  (2) prev == cur.left, prev is left subtree, we have traversed left subtree. 
	 *                        visit cur.right if cur.right != null
	 *  (3) else
	 *      prev == cur.right, prev is right subtree. we finish traverse right child, go up
	 *      st.pop()
	 *  
	 *  stack            prev        cur     print
	 *    1              null         1
	 *    1 2            1
	 *    1 2            1            2
	 *    1 2 4          2
	 *    1 2 4          2            4
	 *                                        4
	 *    1 2            4
	 *    1 2            4            2                       prev = cur.left, go right if cur.right != null
	 *    1 2 5          2
	 *    1 2 5          2            5                       prev = cur.parent, go left(higher priority) or go right
	 *    1 2            5                    5
	 *    1 2            5            2                       prev = cur.right, go up
	 *    1              2                    2
	 *    1              2            1                       prev = cur.left, go right if cur.righ != null
	 *    1 3            1           
	 *    1 3            1            3                       prev = cur.parent, go down (left has higher priority)
	 *    1              3                    3 
	 *    1              3            1                       prev = cur.right, go up
	 *                   1                    1
	 *    
	 *    order: 4 5 2 3 1
	 *    
	 */
	
	// prev is the pointer in traverse, the previous node we access
	public static List<Integer> task1_postOrder(TreeNode root) {
		List<Integer> postOrder = new ArrayList<Integer>();
		if (root == null) {
			return postOrder;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode prev = null;
		TreeNode cur = root;
		
		st.offerFirst(cur);
		while(!st.isEmpty()) {
			cur = st.peekFirst();
			// prev == null or prev is cur's parent --> going down
			// left child has higher priority
			if (prev == null || prev.left == cur || prev.right == cur) {
				if (cur.left != null) {
					st.offerFirst(cur.left);
				} else if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// cur is leaf node, print out
					postOrder.add(cur.val);
					st.pollFirst();
				}
			} else if (prev == cur.left) {
				// prev is cur.left, left subtree
				// if cur.right exists
				if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// print out this node
					postOrder.add(cur.val);
					st.pollFirst();
				}
			} else {
				// prev == cur.right  go up
				postOrder.add(cur.val);
				st.pollFirst();
			}
			prev = cur;
		}
		return postOrder;
	}
	
	
	
	
	public static void postOrder_1stack(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeNode prev = null;
		TreeNode cur = root;
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		st.offerFirst(root);
		
		while(!st.isEmpty()) {
			cur = st.peekFirst();
			// prev == null || prev = cur.parent, go down, left has higher priority
			if (prev == null || cur == prev.left || cur == prev.right) {
				if (cur.left != null) {
					st.offerFirst(cur.left);
				} else if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// this is a leaf node, print out
					System.out.print(cur.val + " ");
					st.pollFirst();
				}
			} else if (prev == cur.left) {
				if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// print out
					System.out.print(cur.val + " ");
					st.pollFirst();
				}
			} else {
				// prev == cur.right, go up
				System.out.print(cur.val + " ");
				st.pollFirst();
			}
			prev = cur;
		}
	}
	
	/*
	 * task2
	 * Morris In Order Traversal
	 * 
	 *      1
	 *     / \
	 *    2   3
	 *   / \
	 *  4  5
	 * 
	 */
	
	/*
	 * Morris InOrder Traversal 
	 * (1) if current.left == null, PRINT current.val, current = current.right 
	 * (2) if current.left != null, get rightmost node of left subtree of current. 
	 * 		(2.1) if the rightmost node.right == null, node.right = current current = current.left; 
	 *      (2.2) if the rightmost node.right == current, node.right = null (recover the tree) 
	 *            PRINT current.value current = current.right; 
	 * repeat (1) (2) until current == null
	 */
	
	
	public static void task2_morris_inorder_traversal(TreeNode root) {
		if (root == null) {
			return ;
		}		
		TreeNode cur = root;
		while(cur != null) {
			if (cur.left == null) {
				// print out cur
				System.out.print(cur.val + " ");
				cur = cur.right;
			} else {
				// cur.left != null  get the rightmost node of cur.left
				TreeNode rightMost = cur.left;
				while(rightMost.right != null && rightMost.right != cur) {
					rightMost = rightMost.right;
				}			
				// 2.1
				if (rightMost.right == null) {
					// link 
					rightMost.right = cur;
					cur = cur.left;
				} else {
				// 2.2
					// rightMost.right == cur
					// unlink
					rightMost.right = null;
					System.out.print(cur.val + " ");
					cur = cur.right;
				}
			}
		}		
	}
	
	
	/*
	 * task3
	 * Check if Binary Tree is balanced
	 */
	public static boolean task3_isBalanced(TreeNode root) {
		return task3_getHeight(root) != -1;
	}
	
	public static int task3_getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftH = task3_getHeight(node.left);
		int rightH = task3_getHeight(node.right);
		if (leftH == -1 || rightH == -1 || Math.abs(leftH - rightH) > 1) {
			return -1;
		}
		return Math.max(leftH, rightH) + 1;
	}
	
	
	/*
	 * task4
	 * Check if Binary Tree is Symmetric
	 */
	
	/*
	 * task5
	 * Tweaked Identical Binary Tree
	 */
	
	/*
	 * task6
	 * Is Binary Search Tree OR NOT
	 */
	
	/*
	 * task7
	 * Get Keys in Binary Search Tree in a Given Range
	 * 
	 */
	public static List<Integer> task7_getRange(TreeNode root, int min, int max) {
		List<Integer> list = new ArrayList<Integer>();
		task7_getRange(root, min, max, list);
		return list;
	}

	private static void task7_getRange(TreeNode root, int min, int max, List<Integer> list) {
		if (root == null) {
			return;
		}
		// 1. determine if left subtree should be traversed, 
		// only when root.key > min, 
		// we should traverse the left subtree.
		if (root.val > min) {
			task7_getRange(root.left, min, max, list);
		}
		// 2. determine if root should be traversed.
		if (root.val >= min && root.val <= max) {
			list.add(root.val);
		}
		// 3. determine if right subtree should be traversed, only when
		// root.key < max, we should traverse the right subtree.
		if (root.val < max) {
			task7_getRange(root.right, min, max, list);
		}
	}
	

	
	
	
	
	
	
}

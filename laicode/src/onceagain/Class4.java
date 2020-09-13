package onceagain;

import java.util.*;

import ds.TreeNode;

public class Class4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	/*
	 * task1
	 * binary tree pre-Order Interative Traversal
	 * 
	 * 1 stack
	 * 
	 */
	public static void task1_preOrder1(TreeNode root) {
		// 
		if (root == null) return ;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while(!stack.isEmpty()) {
			TreeNode cur = stack.peekFirst();
			stack.pollFirst();
			System.out.print(cur.val + " ");
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
	}
	
	
	/*
	 *	preOrder 2 
	 */
	public static void task1_preOrder2(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !stack.isEmpty()) {
			if (cur != null) {
				System.out.print(cur.val + " ");
				stack.offerFirst(cur);
				cur = cur.left;
			} else {
				cur = stack.pollFirst();
				cur = cur.right;
			}
		}
	}
	
	
	public static void test1() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		
		System.out.println("-----preOrder1------");
		task1_preOrder1(n1);
		System.out.println();
		
		System.out.println("------preOrder2------");
		task1_preOrder2(n1);
		System.out.println();
		System.out.println("------inOrder--------");
		task2_inOrder(n1);
		System.out.println();
		
		System.out.println("-----postOrder-------");
		task3_postOrder(n1);
		System.out.println();
		
		System.out.println("-----inOrderMorris---------");
		task4_morrisInOrder(n1);
		
	}
	
	public static void task2_inOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !stack.isEmpty()) {
			if (cur != null) {
				// put cur into stack
				stack.offerFirst(cur);
				cur = cur.left;
			} else {
				// cur == null
				cur = stack.pollFirst();
				// print out cur.val
				System.out.print(cur.val + " ");
				cur = cur.right;
			}
		}
	}
	
	
	/*
	 * task3
	 * postOrder
	 */
	public static void task3_postOrder(TreeNode root) {
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode prev = null;
		TreeNode cur = null;
		stack.offerFirst(root);
		while(!stack.isEmpty()) {
			cur = stack.peekFirst();
			// if prev is null or prev is cur's parent, go down
			if (prev == null || prev.left == cur || prev.right == cur) {
				if (cur.left != null) {
					stack.offerFirst(cur.left);
				} else if (cur.right != null) {
					stack.offerFirst(cur.right);
				} else {
					// this is a leaf node. print it out
					stack.pollFirst();
					System.out.print(cur.val + " ");
				}
			} else if (cur.left == prev) { 
				// prev == cur.left, we already finish the left subtree, go right 
				if (cur.right != null) {
					stack.offerFirst(cur.right);
				} else { 
					// cur.right == null
					stack.pollFirst();
					System.out.print(cur.val + " ");
				}
			} else {
				// cur.right == prev
				// we have finished the left and right subtree. go up 
				System.out.print(cur.val + " ");
				stack.pollFirst();
			}
			prev = cur;
		}
	}

	/*
	 * Morris inOrder traversal
	 */
	public static void task4_morrisInOrder(TreeNode root) {
		TreeNode cur = root;
		while(cur != null) {
			// cur is a leaf node, print out
			if (cur.left == null) {
				System.out.print(cur.val + " ");
				cur = cur.right;
			} else {
				// cur.left != null
				TreeNode preDecessor = cur.left;
				while(preDecessor.right != null && preDecessor.right != cur) {
					preDecessor = preDecessor.right;
				}
				
				if (preDecessor.right == null) {
					preDecessor.right = cur;
					cur = cur.left;
				} else {
					preDecessor.right = null;
					System.out.print(cur.val + " ");
					cur = cur.right;
				}
			}
		}
	}
	
	/*
	 * task5
	 * check if Binary Tree is Balanced
	 * 
	 */
	public static boolean task5_BTBalanced(TreeNode root) {
		return task5_getHeight(root) != -1;
	} 
	
	public static int task5_getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = task5_getHeight(node.left);
		if (leftHeight == -1) {
			return -1;
		}
		int rightHeight = task5_getHeight(node.right);
		if (rightHeight == -1) {
			return -1;
		}
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/*
	 * task6
	 * Symmetric Binary Tree
	 */
	public static boolean task6_symmetricBinaryTree(TreeNode root) {
		if (root == null) {
			return true;
		} 
		return task6_isSymmetric(root.left, root.right);
	}
	
	public static boolean task6_isSymmetric(TreeNode one, TreeNode two) {
		if (one == null && two == null) {
			return true;
		} else if (one == null || two == null) {
			return false;
		} else if (one.val != two.val) {
			 return false;
		} else {
			return task6_isSymmetric(one.left, two.left) &&
					task6_isSymmetric(one.right, two.right);
		}
	}
	
	
	/*
	 * task7
	 * Tweaked Identical Binary Tree
	 * 
	 */
	public static boolean task7_isTweakedIdentical(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		} else if (node1 == null || node2 == null) {
			return false;
		} else if (node1.val != node2.val) {
			return false;
		} else {
			return (task7_isTweakedIdentical(node1.left, node2.left) && task7_isTweakedIdentical(node1.right, node2.right))
					||(task7_isTweakedIdentical(node1.left, node2.right) && task7_isTweakedIdentical(node1.right, node2.left)); 
		}
	}
	
	/*
	 * task8
	 * Is Binary Search Tree or NOT
	 */
	public static boolean task8_isBST(TreeNode root) {
		return task8_helper(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}
	
	/*
	 * 
	 */
	public static boolean task8_helper(TreeNode node, int max, int min) {
		if (node == null) {
			return true;
		}
		if (node.val > max || node.val < min) {
			return false;
		}
		return task8_helper(node.left, node.val, min) && task8_helper(node.right, max, node.val);
	}
	
	/*
	 * task9
	 * get keys in binary search tree in given range
	 */
	public static List<Integer> task9_getRange(TreeNode root, int min, int max) {
		List<Integer> result = new ArrayList<Integer>();
		task9_helper(root, min, max, result);
		return result;
	}
	
	public static void task9_helper(TreeNode root, int min, int max, List<Integer> result) {
		if (root == null) {
			return ;
		}
		if (root.val < min) {
			// no need to traversal root.left
			task9_helper(root.right, min, max, result);
		} else if (root.val > max) {
			// no need to traversal root.right
			task9_helper(root.left, min, max, result);
		} else {
			// root.val >= min && root.val <= max
			result.add(root.val);
			task9_helper(root.left, min, max, result);
			task9_helper(root.right, min, max, result);
		}
	}
	public static void task9_helper2(TreeNode root, int min, int max, List<Integer> result) {
		if (root == null) {
			return ;
		}
		
		if (root.val > min) {
			// traverse the left subtree
			task9_helper2(root.left, min, max, result);
		}
		if (root.val >= min && root.val <= max) {
			result.add(root.val);
		}
		if (root.val < max) {
			// traverse the right subtree
			task9_helper2(root.right, min, max, result);
		}
	}
	
	
	
}

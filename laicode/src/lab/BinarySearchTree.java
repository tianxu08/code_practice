package lab;

import ds.TreeNode;

public class BinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/*
	 * root.key == target
	 * root.key < target, go right
	 * root.key > target, go left
	 */
	
	public static TreeNode search(TreeNode root, int target) {
		if (root == null) {
			return root;
		}
		if (root.val == target) {
			return root;
		} else if (target < root.val) {
			// go left
			return search(root.left, target);
		} else {
			// go right
			return search(root.right, target);
		}
	}
	
	/*
	 * Tail Recursion:
	 * The recursive call is always the last execution statement.
	 * We can easily transfer the tail recursion to iterative solution.
	 */
	public static TreeNode searchIter(TreeNode root, int target) {
		while(root != null && root.val != target) {
			if (target < root.val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return root;
	}
	
	/*
	 * insert
	 * A very common way when the tree structure could be changed, 
	 * in this case, we return the new root if the structure could be changed.
	 */
	public static TreeNode insert(TreeNode root, int target) {
		if (root == null) {
			return new TreeNode(target);
		}
		if (target < root.val) {
			// insert into the left sub tree
			root.left = insert(root.left, target);
		} else if (target > root.val) {
			root.right = insert(root.right, target);
		}
		return root;
	}
	
	
	public static TreeNode insertIter(TreeNode root, int target) {
		if (root == null) {
			return new TreeNode(target);
		}
		TreeNode temp = root;
		while(temp.val != target) {
			if (temp.val > target) {
				if (temp.left == null) {
					temp.left = new TreeNode(target);
					break;
				} else {
					temp = temp.left;
				}
			} else {
				if (temp.right == null) {
					temp.right = new TreeNode(target);
					break;
				} else {
					temp = temp.right;
				}
			}
		}
		return root;
	}
	
	// delete
	public static TreeNode delete(TreeNode root, int target) {
		if (root ==null) {
			return root;
		}
		if (root.val > target) {
			// delete in the left subtree
			root.left = delete(root.left, target);
			return root;
		}  else if (root.val < target) {
			// delete in the right subtree
			root.right = delete(root.right, target);
			return root;
		} else {
			// case 1 2 3
			if (root.left == null) {
				// this node doesn't have left subtree. its inorder successor is its right child
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else if (root.right.left == null) {
				// 4.1
				// root.right doesn't have left child, meaning itself(root.right) is the smallest node in
				// this case. we just move root.right up
				root.right.left = root.left;
				return root.right;
			} else {
				// 4.2
				// root.right.left != null
				// find the smallest in the right subtree, remove from the right subtree
				TreeNode smallest = deleteSmallest(root.right);
				// link the smallest node to root.left and root.right
				smallest.left = root.left;
				smallest.right = root.right;
				
				return smallest;
			}
		}
	}
	
	private static TreeNode deleteSmallest(TreeNode cur) {
		// cur != null && cur.left != null
		while(cur.left.left != null) {
			cur = cur.left;
		}
		TreeNode smallest = cur.left;
		cur.left = smallest.right;
		return smallest;
	}

}

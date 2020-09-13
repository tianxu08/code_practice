package tag_iterator;

import java.util.*;

import ds.TreeNode;

public class IteratorBTPostOrder {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		
		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		
		postOrder(n1);
		System.out.println("------------------");
		IteratorBTPostOrder sln = new IteratorBTPostOrder(n1);
		while(sln.hasNext()) {
			System.out.print(sln.next().val + " ");
		}
		System.out.println();
	}
	
	/*
	 *      1
	 *     /\
	 *    2  3
	 *   /\
	 *  4  5
	 *  
	 *  preorder: 1 2 4 5 3
	 *  inorder: 4 2 5 1 3
	 *  postOrder: 4 5 2 3 1
	 */
	private Stack<TreeNode> stack;
	private TreeNode prev;
	
	public IteratorBTPostOrder(TreeNode root) {
		stack = new Stack<TreeNode>();
		stack.push(root);
		prev = null;
	}
	
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public TreeNode next() {
		if (!hasNext()) {
			return null;
		} else {
			TreeNode reNode = null;
			while(true) {
				TreeNode cur = stack.peek();
				// prev == null or prev is cur's parent, go down, left has priority
				if (prev == null || prev.left == cur || prev.right == cur) {
					if (cur.left != null) {
						stack.push(cur.left);
					} else if (cur.right != null) {
						stack.push(cur.right);
					} else {
						// cur.left == null && cur.right == null, leaf node, print out
						reNode = cur;
						prev = cur;
						stack.pop();
						break;
						
					}
				} else if (prev == cur.left) { // prev == cur.left, prev is current's left child
					// left subtree finished. visit the right subtree
					if (cur.right != null) {
						stack.push(cur.right);
					} else {
						// cur.right == null. print out
						reNode = cur;
						stack.pop();
						prev = cur;
						break;
					}
				} else { // prev == cur.right, prev is current's right child. print the node 
					reNode = cur;
					stack.pop();
					prev = cur;
					break;
				}	
			}
			return reNode;
		}
	}
 	
	
	
	/*
	 * Post Order Traversal
	 * 
	 * if previous is null → going down (left subtree has priority)
	 * if previous is current’s parent, → going down(left subtree has priority)
	 * if previous == current.left, → left subtree finished, going current.right
	 * if previous == current.right → right subtree finished, pop current, going up
	 */
	
	public static void postOrder(TreeNode root) {
		// check 
		if (root == null) {
			return ;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode prev = null;
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNode cur = stack.peek();
			// prev == null or prev is cur's parent, go down, left has priority
			if (prev == null || prev.left == cur || prev.right == cur) {
				if (cur.left != null) {
					stack.push(cur.left);
				} else if (cur.right != null) {
					stack.push(cur.right);
				} else {
					// cur.left == null && cur.right == null, leaf node, print out
					System.out.print(cur.val + " ");
					stack.pop();
				}
			} else if (prev == cur.left) { // prev == cur.left, prev is current's left child
				// left subtree finished. visit the right subtree
				if (cur.right != null) {
					stack.push(cur.right);
				} else {
					// cur.right == null. print out
					System.out.print(cur.val + " ");
					stack.pop();
				}
			} else { // prev == cur.right, prev is current's right child. print the node 
				System.out.print(cur.val + " ");
				stack.pop();
			}
			// update the prev
			prev = cur;
		}
		System.out.println();
	}
	
	
}

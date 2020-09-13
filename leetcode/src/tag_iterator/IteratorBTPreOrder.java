package tag_iterator;

import java.util.*;

import ds.TreeNode;

public class IteratorBTPreOrder {
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
		
		IteratorBTPreOrder sln = new IteratorBTPreOrder(n1);
		while(sln.hasNext()) {
			System.out.println(sln.next().val);
		}
		
	}
	
	private Stack<TreeNode> stack ;
	private TreeNode current;
	public IteratorBTPreOrder(TreeNode root) {
		current = root;
		stack = new Stack<>();
	}
	
	public boolean hasNext() {
		return current != null || !stack.isEmpty();
	}
	
	public TreeNode next() {
		if (!hasNext()) {
			return null;
		}
		TreeNode retNode = null;
		while(true) {
			if (current != null) {
				retNode = current;
				stack.push(current);
				current = current.left;
				break;
			} else {
				if (!stack.isEmpty()) {
					// we need to make sure that the stack is NOT empty
					current = stack.pop();
					current = current.right;
				}	
			}
		}
		return retNode;
	}
	
	/*
	 *    1
	 *   / \
	 *  2   3
	 * / \
	 * 4 5
	 */
	
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
		
		preOrder(n1);
		
	}
	
	
	/*
	 *     1
	 *     /\
	 *    2  3
	 *   /\
	 *  4  5
	 *  
	 *  inorder: 4 2 5 1 3
	 */
	
	
	public static void preOrder(TreeNode root) {
		TreeNode current = root;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		while(current != null || !stack.isEmpty()) {
			if (current != null) {
				System.out.println(current.val);
				stack.offerFirst(current);
				current = current.left;
			} else {
				current = stack.pollFirst();
				current = current.right;
			}
		}
	}
	
	
}

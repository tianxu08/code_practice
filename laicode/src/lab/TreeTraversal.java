package lab;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import ds.ListNode;
import ds.TreeNode;

public class TreeTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	/*
	 * Pre Order Traversal
	 * 
	 * 1 We always print root first, then root can be eliminated from stack.
	 * 2 We traverse left sub first, so the right sub should be retained in the stack before the left sub is done.
	 */
	public static void preOrderIter(TreeNode root) {
		if (root == null) {
			return;
		}
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while(!stack.isEmpty()) {
			// visit the node
			TreeNode cur = stack.pollFirst();
			System.out.print(cur.val + " ");
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
	}
	
	public static void preOrderIter2(TreeNode root) {
		if (root == null) {
			return ;
		}
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = null;
		while(cur != null || !stack.isEmpty()) {
			if (cur != null) {
				System.out.print(cur.val + " ");
				stack.offerFirst(cur);
				cur = cur.left;
			} else {
				// cur == null
				cur = stack.pollFirst();
				cur = cur.right;
			}
		}
	}
	
	public static void test_pre() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		
		
		
	}
	
	
	
	// InOrder
	/*
	 * The problem is, we can not throw away the root in the stack before we traversed 
	 * all the nodes in left subtree. 
	 * How can we know we have already traversed all the nodes in left sub?
	 * Use a helper node to store the current “visiting” node to help identify 
	 * when we can go back to the previous level.
	 * 1 when helper node is not null, push helper and let helper = helper.left
	 * 2 when helper is null, means the left subtree is finished, we can print the top, 
	 * and let helper = top.right. (traverse the left subtree first, then root, then right subtree)
	 * 3 do 1 and 2 until helper is null and there is no nodes left in the stack.
	 */
	public static void inOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !stack.isEmpty()) {
			if (cur != null) {
				// push cur into stack
				stack.offerFirst(cur);
				// go left
				cur = cur.left;
			} else {
				// cur == null
				cur = stack.pollFirst();
				// visit the node
				System.out.print(cur.val + " ");
				// go to cur.right
				cur = cur.right;
			}
		}
	}
	
	
	// post Order Traversal
	/*
	 * left subtree, right subtree, root --> reverse order of root, right subtree, left subtree
	 * 
	 */
	public static void postOrder1(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		LinkedList<TreeNode> stack2 = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while(!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			stack2.offerFirst(cur);
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
		}
		
		while(!stack2.isEmpty()) {
			System.out.print(stack2.pollFirst().val + " ");
		}
	}
	
	
	public static void postOrder2(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeNode prev = null;
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		st.offerFirst(root);
		while(!st.isEmpty()) {
			TreeNode cur = st.peekFirst();
			if (prev ==  null || cur == prev.left || cur== prev.right) {
				// prev == null or prev = cur.parent
				// go down, left subtree first
				if (cur.left != null) {
					st.offerFirst(cur.left);
				} else if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// cur is leaf node
					// print out cur
					System.out.print(cur.val + " ");
					// pop the cur node
					st.pollFirst();
				}
			} else if (prev == cur.left && cur.right != null) {
				// the left subtree has been finished, go right subtree
				st.offerFirst(cur.right);
			} else {
				// prev == cur.right, the right subtree has been finished. 
				// go up
				// visit the current node and pop it from stack
				System.out.print(cur.val + " ");
				st.pollFirst();
			}
			// update prev
			prev = cur;
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
		if (root == null) {
			return ;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		TreeNode prev = null;
		while(!st.isEmpty()) {
			TreeNode cur = st.peek();
			// if previous is null, going down
			
			// if previous is null → going down (left subtree has priority)
			if (prev == null || cur == prev.left || cur == prev.right) {
				if (cur.left != null) {
					st.push(cur.left);
				} else if (cur.right != null){
					st.push(cur.right);
				} else {
					// leaf node
					System.out.println(cur.val + " ");
					st.pop();
				}
			} else if (prev == cur.left) {  // from left, bottom to top
				if (cur.right != null) {
					st.push(cur.right);
				} else {
					System.out.println(cur.val + " ");
					st.pop();
				}
			} else {
				// from right bottom to top
				System.out.println(cur.val + " ");
				st.pop();
			}
			// update prev
			prev = cur;
		}
	}

}

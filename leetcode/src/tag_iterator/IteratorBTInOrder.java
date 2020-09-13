package tag_iterator;

import java.util.Stack;

import ds.TreeNode;

public class IteratorBTInOrder {
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
		
		IteratorBTInOrder sln = new IteratorBTInOrder(n1);
		while(sln.hasNext()) {
			System.out.println(">>> " + sln.next().val);
		}
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
	 *  
	 */
	
	private Stack<TreeNode> stack;
	private TreeNode current;

	public IteratorBTInOrder(TreeNode root) {
		this.stack = new Stack<>();
		current = root;
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		// current != null or stack.isEmpty() == false
		return current != null || !stack.isEmpty();
	}

	public TreeNode next() {
		// TODO Auto-generated method stub
		if (!hasNext()) {
			return null;
		} else {
			TreeNode retNode = null;
			while(true) {
				if (current != null) {

					stack.push(current);
					current = current.left;
				} else {
					if (!stack.isEmpty()) {
						stack.pop();
						retNode = current;
						current = current.right;
						break;
					}
				}
			}
			return retNode;
		}
		
	}
	
	public void remove() {}
	
	public static void inOrder(TreeNode node) {
		if (node == null) {
			return ;
		}
		TreeNode cur = node;
		Stack<TreeNode> stack = new Stack<>();
		while(cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				// cur == null
				cur = stack.pop();
				System.out.print(cur.val + " ");
				cur = cur.right;
			}
		} 
	}

}

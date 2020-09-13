package tree;

import java.util.Stack;

public class Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = createTree(a);
		preOrder(root);
		preOrder2(root);
		preOrderRec(root);
		System.out.println();
		
		inOrder(root);
		inOrderRec(root);
		System.out.println();
		inOrderMorris(root);
		
		
		postOrder(root);
		postOrderRec(root);
		
		
	}
	
	public static TreeNode createTree(int[] a) {
		return helper(a, 0, a.length - 1);
	}
	public static TreeNode helper(int[] a, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start)/2;
		TreeNode node = new TreeNode(a[mid]);
		node.left = helper(a, start, mid - 1);
		node.right = helper(a, mid + 1, end);
		
		return node;
	}
	
	
	public static void preOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		while(!st.isEmpty()) {
			TreeNode cur = st.pop();
			System.out.print(cur.val + " ");
			if (cur.right != null) {
				st.push(cur.right);
			}	
			if (cur.left != null) {
				st.push(cur.left);
			}
		}
		System.out.println();
	}
	
	public static void preOrder2(TreeNode root) {
		if (root == null) {
			return ;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode cur = root;
		
		while(cur != null || !st.isEmpty()) {
			if (cur != null) {
				System.out.print(cur.val + " ");
				st.push(cur);
				cur = cur.left;
			} else {
				cur = st.pop();
				cur = cur.right;
			}
		}
		System.out.println();
	}
	
	public static void preOrderRec(TreeNode root) {
		if (root == null) {
			return ;
		}
		System.out.print(root.val + " ");
		preOrderRec(root.left);
		preOrderRec(root.right);
	}
	
	public static void inOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeNode cur = root;
		Stack<TreeNode> st = new Stack<TreeNode>();
		while (cur != null || !st.isEmpty()) {
			if (cur != null) {
				st.push(cur);
				cur = cur.left;
			} else {
				cur = st.pop();
				System.out.print(cur.val + " ");
				cur = cur.right;
			}
		}
		System.out.println();
	}
	
	public static void inOrderRec(TreeNode root) {
		if (root == null) {
			return ;
		}
		inOrderRec(root.left);
		System.out.print(root.val + " ");
		inOrderRec(root.right);
	}
	
	public static void inOrderMorris(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeNode cur = root;
		while(cur != null) {
			if (cur.left == null) {
				System.out.print(cur.val + " ");
				cur = cur.right;
			} else {
				// cur.left != null
				// find the inorder predecessor of cur in cur.left subtree
				TreeNode pre = cur.left;
				
				while(pre.right != null && pre.right != cur) {
					pre = pre.right;
				}
				if (pre.right == null) {
					// link pre.right to cur
					pre.right = cur;
					cur = cur.left;
				} else{
					// pre.right = cur
					// unlink pre.right 
					pre.right = null;
					System.out.print(cur.val + " ");
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}
	
	public static void postOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		Stack<Integer> output = new Stack<Integer>();
		while (!st.isEmpty()) {
			TreeNode cur = st.pop();
			output.push(cur.val);
			if (cur.left != null) {
				st.push(cur.left);
			}
			if (cur.right != null) {
				st.push(cur.right);
			}
		}
		
		while(!output.isEmpty()){
			System.out.print(output.pop() + " ");
		}
		System.out.println();
	}
	
	public static void postOrderRec(TreeNode root) {
		if (root == null) {
			return ;
		}
		postOrderRec(root.left);
		postOrderRec(root.right);
		System.out.print(root.val + " ");
	}
	
	

}

package tag_tree;

import ds.TreeNode;

public class BT_DDL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/**
	 * Time:
	 *
	 * use a prev pinter to point the prev visited node
	 */
	public static TreeNode prev = null;
	public static TreeNode head = null;
	public static void BT2DDL(TreeNode node) {
		if (node == null) {
			return ;
		}
		
		// convert the left subtree
		BT2DDL(node.left);
		
		// convert this node
		if (prev == null) {
			head = node; // this is the first node
		} else {
			// prev is NOT null
			node.left = prev;
			prev.right = node;
		}

		// update the prev
		prev = node;

		// convert the right subtree
		BT2DDL(node.right);
	}
	
	public static void test() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		
		BT2DDL(n1);
		
		TreeNode cur = head;
		if (cur == null) {
			System.out.println("cur is null");
		}
		while(cur != null) {
			System.out.print(cur.val +" ");
			cur = cur.right;
		}
		System.out.println();			
	}

}

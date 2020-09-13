package debug;

import ds.ListNode;
import ds.TreeNode;

public class Debug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static void printMatrix(int[][] m) {
		System.out.println("----------------------");
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}
	public static void printMatrix(boolean[][] m) {
		System.out.println("----------------------");
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}
	
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void printLinkedList(ListNode head) {
		if (head == null) {
			return ;
		}
		ListNode cur = head;
		while(cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void preOrderBT(TreeNode root) {
		if (root == null) {
			return ;
		}
		System.out.print(root.val + " ");
		preOrderBT(root.left);
		preOrderBT(root.right);
	}
	
	public static void inOrderBT(TreeNode root) {
		if (root == null) {
			return ;
		}
		inOrderBT(root.left);
		System.out.print(root.val + " ");
		inOrderBT(root.right);
	}
	
	
	public static void postOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.val + " ");
	}
	
	public static ListNode createLinkedList(int n) {
		ListNode head = new ListNode(1);
		ListNode tail = head;
		int i = 2;
		while( i <= n) {
			ListNode newNode = new ListNode(i);
			tail.next = newNode;
			tail = tail.next;
			i ++;
		}
		return head;
	}
}

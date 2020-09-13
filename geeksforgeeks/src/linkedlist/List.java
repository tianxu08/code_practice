package linkedlist;

import java.util.ArrayList;

public class List {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static ListNode createList(int size) {
		ArrayList<ListNode> a = new ArrayList<ListNode>();
		for (int i = 1; i <= size; i++) {
			ListNode node = new ListNode(i);
			a.add(node);
		}
		ListNode head = null;
		for (int i = 0; i < a.size() - 1; i++) {
			a.get(i).next = a.get(i + 1);
		}
		head = a.get(0);
		return head;
	}

	public static void test() {
		ListNode head = createList(10);
		printList(head);
	}

	public static void printList(ListNode node) {
		System.out.println("\n-----start-------");
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println("\n-----end-------");
	}

	public static ListNode createListWithDup(int[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		ArrayList<ListNode> arr = new ArrayList<ListNode>();
		for (int i = 0; i < a.length; i++) {
			ListNode node = new ListNode(a[i]);
			arr.add(node);
		}
		ListNode head = null;
		for (int i = 0; i < arr.size() - 1; i++) {
			arr.get(i).next = arr.get(i + 1);
		}
		head = arr.get(0);
		return head;
	}

}

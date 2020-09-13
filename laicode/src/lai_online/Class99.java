package lai_online;
import ds.*;
public class Class99 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Reorder Linked List
	 */
	
	
	public ListNode reorder(ListNode head) {
		// write your solution here

		ListNode mid = findMid(head);

		ListNode second = mid.next;
		mid.next = null;

		ListNode newSecond = reverse(second);

		ListNode firstRunner = head;

		ListNode secondRunner = newSecond;

		while (secondRunner != null) {
			ListNode nextS = secondRunner.next;

			// insert the secondRunner after firstRunner
			secondRunner.next = firstRunner.next;
			firstRunner.next = secondRunner;

			firstRunner = firstRunner.next.next;
			secondRunner = nextS;
		}

		return head;
	}

	public ListNode findMid(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode fast = head.next;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow;
	}

	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode next = head.next;
		ListNode newHead = reverse(next);

		next.next = head;
		head.next = null;

		return newHead;

	}

}

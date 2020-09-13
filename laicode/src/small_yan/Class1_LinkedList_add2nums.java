 package small_yan;
import ds.*;

public class Class1_LinkedList_add2nums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test2();
		// test3();
		test4();
	}

	/*
	 * You are given two linked lists representing two non-negative numbers. The
	 * digits are stored in reverse order and each of their nodes contain a
	 * single digit. Add the two numbers and return it as a linked list.
	 * 
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
	 * 
	 * 2->4->3 stands for 342 
	 * 5->6->4 stands for 465 
	 * the sum is 807 LinkedList
	 * least significant --> most significant.
	 */
	/*
	 * Follow up LinkedList most significant --> least significant e.g 342
	 * 3->4->2 465 4->6->5 807 8->0->7
	 * 
	 * Follow up: If we don't allow to modify lists. 
	 * What should we do? 
	 */

	public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 == null || l2 == null) {
			ListNode result = l1 != null ? l1 : l2;
			return result;
		}

		int carry = 0, sum = 0;
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		ListNode l1Ptr = l1, l2Ptr = l2;
		while (l1Ptr != null && l2Ptr != null) {
			sum = l1Ptr.value + l2Ptr.value + carry;
			carry = sum / 10;
			sum = sum % 10;
			ListNode newNode = new ListNode(sum);
			tail.next = newNode;
			tail = newNode;
			l1Ptr = l1Ptr.next;
			l2Ptr = l2Ptr.next;
		}
		// if l1Ptr == null, the first list is null
		// only focus the second list
		while (l2Ptr != null) {
			sum = l2Ptr.value + carry;
			carry = sum / 10;
			sum = sum % 10;
			ListNode newNode = new ListNode(sum);
			tail.next = newNode;
			tail = newNode;
			l2Ptr = l2Ptr.next;
		}

		while (l1Ptr != null) {
			sum = l1Ptr.value + carry;
			carry = sum / 10;
			sum = sum % 10;
			ListNode newNode = new ListNode(sum);
			tail.next = newNode;
			tail = newNode;
			l1Ptr = l1Ptr.next;
		}

		// !!! Don't forget to do this
		if (carry != 0) {
			ListNode newNode = new ListNode(carry);
			tail.next = newNode;
		}
		return dummy.next;
	}

	/*
	 * Follow up LinkedList most significant --> least significant 
	 * e.g 
	 * 342 3->4->2 
	 * 465 4->6->5 
	 * 807 8->0->7
	 * 
	 */
	public static void test2() {
		ListNode node1_1 = new ListNode(9);
		ListNode node1_2 = new ListNode(9);
		ListNode node1_3 = new ListNode(9);
		ListNode node1_4 = new ListNode(9);

		node1_1.next = node1_2;
		node1_2.next = node1_3;
		node1_3.next = node1_4;

		ListNode list1 = node1_1;

		ListNode node2_1 = new ListNode(0);
		ListNode node2_2 = new ListNode(0);
		ListNode node2_3 = new ListNode(0);
		ListNode node2_4 = new ListNode(1);
		node2_1.next = node2_2;
		node2_2.next = node2_3;
		node2_3.next = node2_4;

		ListNode list2 = node2_1;

		printList(list1);
		printList(list2);

	}
	
	public static class Carry {
		public int value;
		public Carry() {
			this.value = 0;
		}
		public Carry(int c) {
			this.value = c;
		}
	}

	public static ListNode addTwoNumbers2(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		// get the length of list1, list2
		int list1Len = getLength(list1);
		int list2Len = getLength(list2);

		if (list1Len < list2Len) {
			list1 = addDummyNode(list1, list2Len - list1Len);
		} else if (list1Len > list2Len) {
			list2 = addDummyNode(list2, list1Len - list2Len);
		} else {
			; // the length are same, do nothing
		}

		Carry carry = new Carry();
		ListNode dummy = new ListNode(0);
		dummy.next = addSameSize(list1, list2, carry);

		if (carry.value != 0) {
			dummy.value = carry.value;
			return dummy;
		} else {
			return dummy.next;
		}
	}

	// This guarantee that the length of list1 and list2 are same
	public static ListNode addSameSize(ListNode list1, ListNode list2, Carry carry) {
		if (list1 == null) {
			return null;
		}
		int sum = 0;
		ListNode result = new ListNode(0);
		result.next = addSameSize(list1.next, list2.next, carry);

		sum = list1.value + list2.value + carry.value;
		carry.value = sum / 10;
		sum = sum % 10;
		result.value = sum;

		return result;
	}

	// add num nodes in front of list
	public static ListNode addDummyNode(ListNode list, int num) {
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		for (int i = 0; i < num; i++) {
			ListNode newNode = new ListNode(0);
			tail.next = newNode;
			tail = newNode;
		}
		tail.next = list;
		return dummy.next;
	}

	public static void test3() {
		ListNode node1_1 = new ListNode(9);
		ListNode node1_2 = new ListNode(9);
		ListNode node1_3 = new ListNode(9);
		ListNode node1_4 = new ListNode(9);

		node1_1.next = node1_2;
		node1_2.next = node1_3;
		node1_3.next = node1_4;

		ListNode list1 = node1_1;

		ListNode node2_1 = new ListNode(10);
		// ListNode node2_2 = new ListNode(0);
		// ListNode node2_3 = new ListNode(0);
		// ListNode node2_4 = new ListNode(2);
		// node2_1.next = node2_2;
		// node2_2.next = node2_3;
		// node2_3.next = node2_4;

		ListNode list2 = node2_1;

		printList(list1);
		printList(list2);

		ListNode result = addTwoNumbers2(list1, list2);
		printList(result);

	}
	

	// Follow up: what if we don't allow to modify the original lists
	public static ListNode addTwoNumbers3(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}

		int list1_len = getLength(list1);
		int list2_len = getLength(list2);

		ListNode dummy = new ListNode(0);
		Carry carry = new Carry();

		// let's make the list1 be the longer list
		if (list1_len < list2_len) {
			return addTwoNumbers3(list2, list1);
		}

		if (list1_len == list2_len) {
			dummy.next = addSameSize(list1, list2, carry);
			if (carry.value != 0) {
				dummy.value = carry.value;
				return dummy;
			} else {
				return dummy.next;
			}
		} else {
			// list1_len > list2_len
			// list1_len - list2_len = diff
			int diff = list1_len - list2_len;
			ListNode cur = list1;
			
			for (int i = 0; i < diff; i++) {
				cur = cur.next;
			}
			
			// now the cur points the linked list in list1 has the same length with list2
			ListNode curResult = addSameSize(cur, list2, carry);
			
			// add the carry to the remaining part in front of cur in list1
			ListNode result = addCarryToRemaining(list1, cur, carry);
			
			ListNode tail = result;
			// find the tail of result linked list
			while (tail != null && tail.next != null) {
				tail = tail.next;
			} 
			// link curResult at the end of result, pointed by tail
			tail.next = curResult;
		
			// link the result to dummy. 
			dummy.next = result;
			//check value in carry
			if (carry.value != 0) {
				// if the carry is still not 0
				dummy.value = carry.value;
				return dummy;
			} else {
				return dummy.next;
			}
		}
		
	}

	public static ListNode addCarryToRemaining(ListNode head, ListNode cur, Carry carry) {
		if (head == cur) {
			return null;
		}
		int sum = 0;
		ListNode result = new ListNode(0);

		result.next = addCarryToRemaining(head.next, cur, carry);
		sum = head.value + carry.value;
		carry.value = sum / 10;
		result.value = sum % 10;
		return result;

	}

	public static void test4() {
		ListNode node1_1 = new ListNode(1);
		ListNode node1_2 = new ListNode(9);
		ListNode node1_3 = new ListNode(3);
		ListNode node1_4 = new ListNode(9);

		node1_1.next = node1_2;
		node1_2.next = node1_3;
		node1_3.next = node1_4;

		ListNode list1 = node1_1;

		ListNode node2_1 = new ListNode(3);
		// ListNode node2_2 = new ListNode(0);
		// ListNode node2_3 = new ListNode(0);
		// ListNode node2_4 = new ListNode(2);
		// node2_1.next = node2_2;
		// node2_2.next = node2_3;
		// node2_3.next = node2_4;

		ListNode list2 = node2_1;

		printList(list1);
		printList(list2);

		ListNode result = addTwoNumbers3(list2, list1);
		printList(result);

	}
	
	
	public static int getLength(ListNode head) {
		ListNode cur = head;
		int len = 0;
		while (cur != null) {
			len++;
			cur = cur.next;
		}
		return len;
	}

	public static void printList(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.print(cur.value + " ");
			// update the pointer
			cur = cur.next;
		}
		System.out.println();
	}
}

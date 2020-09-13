package linkedlist;

public class P5_LinkedList {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test7();
	}

	
	/*
	 * http://www.geeksforgeeks.org/write-a-c-function-to-detect-loop-in-a-linked-list/
	 * Write a C function to detect loop in a linked list
	 * 
	 * 1 Using Hash, putting the node address in a hash table
	 * 2 Modify the node's structure, adding a visited value
	 * 3 Floyd's cycle finding algorithm. Two pointers. (Implement this)
	 * 
	 */
	
	
	
	/* 
	 * http://www.geeksforgeeks.org/write-a-function-to-reverse-the-nodes-of-a-linked-list/
	 * Write a function to reverse a linked list
	 * 
	 * 1 Recursion
	 * 2 Iteration 
	 
	 */
	
	public static ListNode reverseIter(ListNode head) {
		ListNode cur = head;
		ListNode prev = null;
		ListNode next = null;
		
		while (cur != null) {
			// record cur.next, for cur's update in future
			next = cur.next;
			// pointer cur.next to prev
			cur.next = prev;
			// update prev
			prev = cur;
			// update cur
			cur = next;
		}
		
		// after the above while finish, the prev will point to the new head
		return prev;
		
	}
	
	public static ListNode reverseRec(ListNode head) {
		return null;
	}
	
	/*
	 * Task3
	 * http://www.geeksforgeeks.org/in-a-linked-list-given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
	 * 
	 * Given only a pointer to a node to be deleted in a singly linked list, how do you delete it?
	 * 
	 * (1) simple solution, traverse the linked until u find the node u wanna delete. But this solution require the head pointer. 
	 * (2) fast solution: copy the data from the next node to the node to be deleted and deleted the next node. 
	 * 
	 */
	
	/*
	 * Task4
	 * http://www.geeksforgeeks.org/write-a-function-that-counts-the-number-of-times-a-given-int-occurs-in-a-linked-list/
	 * Write a function that counts the number of times a given int occurs in a Linked List
	 * 
	 * traverse the linked list 
	 */
	
	/*
	 * Task5 
	 * http://www.geeksforgeeks.org/write-a-function-to-delete-a-linked-list/
	 * Write a function to delete a Linked List
	 */
	
	/*
	 * Task6
	 * http://www.geeksforgeeks.org/nth-node-from-the-end-of-a-linked-list/
	 * Nth node from the end of a Linked List
	 * Given a Linked List and a number n, write a function that returns the value at the nth node from end of the Linked List.
	 * 
	 *  (1) use the length of linked list. 
	 *     a. calculate the length of linked list. 
	 *     b. print the len - n + 1 th node from the beginning of the linked list
	 *  (2) use two pointers.
	 */
	
	/*
	 * Task7 
	 * http://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
	 * Write a C function to print the middle of a given linked list
	 * 
	 * (1) Length
	 * (2) two pointers. 
	 */
	
	public static ListNode getMid(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode fast = head.next;  // here, note fast = head.next. 
		// if the list is 1->2->3->4->5  the mid is 3
		// if the list is 1->2->3->4->5->6  the mid is 3. the first one
		ListNode slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public static void test7() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		
		System.out.println(getMid(node1).val);
		
	}
	
	
	
}

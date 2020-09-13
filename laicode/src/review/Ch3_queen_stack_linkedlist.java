package review;

import debug.Debug;
import ds.ListNode;

public class Ch3_queen_stack_linkedlist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test3();
	}
	
	/*
	 * 1 Queue by two stacks
	 * use stack1 as input
	 * when pop, check whether stack2 is empty. if no, direct pop from stack2
	 * if stack2 is empty, pop all element from stack1 and push into stack2. 
	 * then, pop from stack 2
	 */
	
	
	
	/*
	 * 2 stack with min
	 * create a min stack to store the all the minimum number so far. 
	 * two scenarios:
	 * (1) there is no duplicate in the input.  
	 * (2) there is duplicate in the input: when push element into the stack, we also need to push the 
	 * current minimum element into the min stack.  
	 * 
	 */
	
	
	/*
	 * 3 reverse linked list
	 */
	public static void test3() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		ListNode head = n1;
		Debug.printLinkedList(head);
		
		ListNode r_head = t3_reverse_rec(head);
		Debug.printLinkedList(r_head);
		
		ListNode r_head2 = t3_reverse_iter(r_head);
		
		Debug.printLinkedList(r_head2);
		
	}
	public static ListNode t3_reverse_rec(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode n_head = t3_reverse_rec(next);
		next.next = head;
		
		head.next = null; // break the head and next
		
		return n_head;
	}
	
	public static ListNode t3_reverse_iter(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode cur = head;
		while(cur != null) {
			// get the next node
			ListNode next = cur.next;
			// reverse the cur.next pointer
			cur.next = prev;
			// update prev
			prev = cur;
			// update cur
			cur = next;
		}
		// now the prev is the new head
		return prev;
	}
	
	
	/*
	 * 4 middle node of linked list
	 */
	
	/*
	 * 5 check if a list has cycle
	 * if yes, find the start point of the cycle
	 */
	
	/*
	 * 6 insert in a sorted linked list
	 */
	
	/*
	 * 7 merge two sorted linked list
	 */
	
	/*
	 * 8 reorder linked list
	 */
	
	
	
	/*
	 * 9 partition linked list
	 */

}

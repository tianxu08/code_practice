package lai_online;

import debug.Debug;
import ds.ListNode;


public class Class03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test3();
	}
	/*
	 * list
	 * 1 number of nodes
	 * 2 reverse linked list
	 * 3 middle of list node
	 * 4 
	 */
	
	/*
	 * 什么问题要往Stack 上考虑？
	 * Anwser: 从左到右linear scan 一个array/string时, 如果要不断回头看左边最新的元素时，往往要用到stack
	 * 1. Histogram 中找最大的长方形
	 * 2. reverse polish notation 逆波兰表达式的计算   a * (b+c)  →  abc+*
	 * 1*(2+3)  →  1 2 3 + *5           stack|| 5
	 * 3. String的repeatedly deduplication.  cabba → caa → c 
	 * 
	 */

	/*
	 * task1 Number Of Nodes 
	 * Easy Data Structure 
	 * 
	 * Return the number of nodes in
	 * the linked list.
	 * 
	 * Examples
	 * 
	 * L = null, return 0 L = 1 -> null, return 1 L = 1 -> 2 -> null, return 2
	 */
	
	
	

	/*
	 * task2 Reverse Linked List 
	 * Easy Data Structure 
	 * Reverse a singly-linked list.
	 * 
	 * Examples
	 * 
	 * L = null, return null L = 1 -> null, return 1 -> null L = 1 -> 2 -> 3 ->
	 * null, return 3 -> 2 -> 1 -> null
	 */
	public static ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode newHead = reverse(next);
		head.next = null;
		next.next = head;
		return newHead;
	}
	/*
	 *      1 -> 2 -> 3 -> 4 -> 5
	 *  p   c
	 */
	public static ListNode reverseIter(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode cur = head;
		while(cur != null) {
			// store the cur.next
			ListNode next = cur.next;
			cur.next = prev;
			// update prev
			prev = cur;
			// update cur
			cur = next;
		}
		return prev;
	}
	
	public static void test1() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		Debug.printLinkedList(n1);
		ListNode r = reverseIter(n1);
		System.out.println("-------------");
		Debug.printLinkedList(r);
	}
	

	/*
	 * task3 Middle Node Of Linked List 
	 * Easy Data Structure 
	 * Find the middle node
	 * of a given linked list.
	 * 
	 * Examples
	 * 
	 * L = null, return null L = 1 -> null, return 1 L = 1 -> 2 -> null, return
	 * 1 L = 1 -> 2 -> 3 -> null, return 2 L = 1 -> 2 -> 3 -> 4 -> null, return
	 * 2
	 */
	public static void test3() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		ListNode mid = task3_mid(n1);
		System.out.println("mid = " + mid.value);
		
	}
	
	public static ListNode task3_mid(ListNode head) {
		ListNode f = head.next; // !!! remember, f = head.next
		ListNode s = head;
		while(f != null && f.next != null) {
			f = f.next.next;
			s = s.next;
		}
		return s;
	}
	

	/*
	 * task4 Check If Linked List Has A Cycle 
	 * Easy Data Structure 
	 * 
	 * Check if a
	 * given linked list has a cycle. Return true if it does, otherwise return
	 * false.
	 */

	/*
	 * task5 Insert In Sorted Linked List Easy Data Structure Insert a value in
	 * a sorted linked list.
	 * 
	 * Examples
	 * 
	 * L = null, insert 1, return 1 -> null L = 1 -> 3 -> 5 -> null, insert 2,
	 * return 1 -> 2 -> 3 -> 5 -> null L = 1 -> 3 -> 5 -> null, insert 3, return
	 * 1 -> 3 -> 3 -> 5 -> null L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3
	 * -> null
	 */

	
	/*
	 * task6 Merge Two Sorted Linked Lists Easy Data Structure Merge two sorted
	 * lists into one large sorted list.
	 * 
	 * Examples
	 * 
	 * L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2
	 * -> 4 -> 5 -> 6 -> null L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to
	 * 1 -> 2 -> null L1 = null, L2 = null, merge L1 and L2 to null
	 */

	/*
	 * task7 Stack With min() Fair Data Structure Java: Enhance the stack
	 * implementation to support min() operation. min() should return the
	 * current minimum value in the stack. If the stack is empty, min() should
	 * return null.
	 * 
	 * C++: Enhance the stack implementation to support min() operation. min()
	 * should return the current minimum value in the stack. If the stack is
	 * empty, min() should return -1.
	 * 
	 * pop() - remove and return the top element, if the stack is empty, return
	 * -1 push(int element) - push the element to top top() - return the top
	 * element without remove it, if the stack is empty, return -1 min() -
	 * return the current min value in the stack.
	 */

	/*
	 * task8 Queue By Two Stacks Fair Data Structure Java: Implement a queue by
	 * using two stacks. The queue should provide size(), isEmpty(), offer(),
	 * poll() and peek() operations. When the queue is empty, poll() and peek()
	 * should return null.
	 * 
	 * C++: Implement a queue by using two stacks. The queue should provide
	 * size(), isEmpty(), push(), front() and pop() operations. When the queue
	 * is empty, front() should return -1.
	 * 
	 * Assumptions
	 * 
	 * The elements in the queue are all Integers. size() should return the
	 * number of elements buffered in the queue. isEmpty() should return true if
	 * there is no element buffered in the queue, false otherwise.
	 */

	/*
	 * task9 Partition Linked List Fair Data Structure Given a linked list and a
	 * target value T, partition it such that all nodes less than T are listed
	 * before the nodes larger than or equal to target value T. The original
	 * relative order of the nodes in each of the two partitions should be
	 * preserved.
	 * 
	 * Examples
	 * 
	 * L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4
	 * -> 3 -> 5 -> null
	 */

}

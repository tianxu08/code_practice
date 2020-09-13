package lai_online2;
import java.util.*;
import ds_lai_online2.*;
public class Class3 {
	/*
	 * task1
	Number Of Nodes
	Easy
	Data Structure
	Return the number of nodes in the linked list.

	Examples

	L = null, return 0
	L = 1 -> null, return 1
	L = 1 -> 2 -> null, return 2
	 
	*/
	public int numberOfNodes(ListNode head) {
		// write your solution here
		if (head == null)
			return 0;
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}
	/*	
	task2
	Reverse Linked List
	Easy
	Data Structure
	Reverse a singly-linked list.

	Examples

	L = null, return null
	L = 1 -> null, return 1 -> null
	L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
	*/
	public ListNode reverse(ListNode head) {
		// write your solution here
		if (head == null || head.next == null)
			return head;

		ListNode next = head.next;
		ListNode newNode = reverse(next);

		next.next = head;
		head.next = null;
		return newNode;

	}
	
	/*
	task3
	Middle Node Of Linked List
	Easy
	Data Structure
	Find the middle node of a given linked list.

	Examples

	L = null, return null
	L = 1 -> null, return 1
	L = 1 -> 2 -> null, return 1
	L = 1 -> 2 -> 3 -> null, return 2
	L = 1 -> 2 -> 3 -> 4 -> null, return 2
	*/
	public ListNode middleNode(ListNode head) {
		// write your solution here
		if (head == null || head.next == null)
			return head;
		ListNode fast = head.next;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	/*
	 * task4
	 * Check If Linked List Has A Cycle
	 * Easy
	 * Data Structure
	 * Check if a given linked list has a cycle. Return true if it does, otherwise return false.
	 */
	public boolean hasCycle(ListNode head) {
		// write your solution here
		if (head == null || head.next == null)
			return false;
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}

	/*
	task5
	Insert In Sorted Linked List
	Easy
	Data Structure
	Insert a value in a sorted linked list.

	Examples

	L = null, insert 1, return 1 -> null
	L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
	L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
	L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
	*/
	public ListNode insert(ListNode head, int value) {
		// write your solution here
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode cur = dummy;
		while (cur != null) {
			if (cur.next == null) {
				// insert at the last of list
				cur.next = new ListNode(value);
				break;
			} else if (cur.next.value > value) {
				// insert after cur.
				ListNode next = cur.next;
				cur.next = new ListNode(value);
				cur.next.next = next;
				break;
			}
			// update cur
			cur = cur.next;
		}
		return dummy.next;
	}
	
	/*
	 * task6
	Merge Two Sorted Linked Lists
	Easy
	Data Structure
	Merge two sorted lists into one large sorted list.

	Examples

	L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
	L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
	L1 = null, L2 = null, merge L1 and L2 to null
	*/
	public ListNode merge(ListNode one, ListNode two) {
		// write your solution here
		if (one == null) {
			return two;
		}
		if (two == null) {
			return one;
		}
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (one != null && two != null) {
			if (one.value < two.value) {
				cur.next = one;
				cur = cur.next;
				one = one.next;
			} else {
				cur.next = two;
				cur = cur.next;
				two = two.next;
			}
		}
		if (one != null) {
			cur.next = one;
		}
		if (two != null) {
			cur.next = two;
		}

		return dummy.next;
	}
	
	
	/*
	 * task7
	Queue By Two Stacks
	Fair
	Data Structure
	Java: Implement a queue by using two stacks. The queue should provide size(), isEmpty(), offer(), poll() and peek() operations. When the queue is empty, poll() and peek() should return null.

	C++: Implement a queue by using two stacks. The queue should provide size(), isEmpty(), push(), front() and pop() operations. When the queue is empty, front() should return -1.

	Assumptions

	The elements in the queue are all Integers.
	size() should return the number of elements buffered in the queue.
	isEmpty() should return true if there is no element buffered in the queue, false otherwise.
	*/
	
	/*
	 * task8
	 * 
	Stack With min()
	Fair
	Data Structure
	Java: Enhance the stack implementation to support min() operation. min() should return the current minimum value in the stack. If the stack is empty, min() should return null.

	C++: Enhance the stack implementation to support min() operation. min() should return the current minimum value in the stack. If the stack is empty, min() should return -1.

	pop() - remove and return the top element, if the stack is empty, return -1
	push(int element) - push the element to top
	top() - return the top element without remove it, if the stack is empty, return -1
	min() - return the current min value in the stack.
	*/
	
	/*
	 * task9
	Partition Linked List
	Fair
	Data Structure
	Given a linked list and a target value T, partition it such that all nodes less than T are listed before the nodes larger than or equal to target value T. The original relative order of the nodes in each of the two partitions should be preserved.

	Examples

	L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
	*/
	public ListNode task9_partition(ListNode head, int target) {
		// write your solution here

		ListNode dummyS = new ListNode(-1);
		ListNode tailS = dummyS;

		ListNode dummyL = new ListNode(-1);
		ListNode tailL = dummyL;

		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			if (cur.value < target) {
				tailS.next = cur;
				tailS = cur;
			} else {
				tailL.next = cur;
				tailL = cur;
			}
			cur.next = null;
			cur = next;
		}

		tailS.next = dummyL.next;

		return dummyS.next;
	}
}

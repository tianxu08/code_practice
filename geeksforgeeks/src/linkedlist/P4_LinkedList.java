package linkedlist;

import java.util.HashMap;


public class P4_LinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test6();
//		test11();
		test12();
	}
	
	/*
	 * Task1 
	 * http://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list/
	 * Pairwise swap elements of a given linked list
	 * 1-2-3-4-5 after swap 2-1-4-3-5
	 * 1-2-3-4-5-6 after swap 2-1-4-3-6-5
	 * 
	 * 1: we can swap the val of node with node.next
	 * 2: we can also reverse the nodes by pair. then connect them. 
	 */
	
	public static ListNode pairWiseSwap(ListNode head) {
		if (head == null || head.next == null) {
			return head ;
		}
		
		ListNode odd_dummy = new ListNode(-1);
		ListNode odd_tail = odd_dummy;
		ListNode even_dummy = new ListNode(-1);
		ListNode even_tail = even_dummy;
		ListNode cur = head;
		while(cur != null) {
			ListNode next = cur.next;
			odd_tail.next = cur;
			cur.next = null;
			cur = next;
			odd_tail = odd_tail.next;
			
			if (cur != null) {
				next = cur.next;
				even_tail.next = cur;
				cur.next = null;
				cur = next;
				even_tail = even_tail.next;
			}
		}
		
		ListNode odd_head = odd_dummy.next;
		ListNode even_head = even_dummy.next;
//		List.printList(odd_head);
//		List.printList(even_head);
		
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		// merge the odd list and even list
		while(odd_head != null && even_head != null) {
			ListNode even_next = even_head.next;
			tail.next = even_head;
			even_head.next = null;
			even_head = even_next;
			tail = tail.next;
			
			ListNode odd_next = odd_head.next;
			tail.next = odd_head;
			odd_head.next = null;
			odd_head = odd_next;
			tail = tail.next;
		}
		
		while(odd_head != null) {
			ListNode odd_next = odd_head.next;
			tail.next = odd_head;
			odd_head = odd_next;
			tail = tail.next;
		}
		
		while(even_head != null) {
			ListNode even_next = even_head.next;
			tail.next = even_head;
			even_head = even_next;
			tail = tail.next;
		}
//		List.printList(dummy);
				
		return dummy.next;
	}
	
	
	public static void test1() {
		ListNode head = List.createList(5);
		List.printList(head);
		ListNode new_head = pairWiseSwap(head);
		List.printList(new_head);
	}
	
	/*
	 * task2 
	 * http://www.geeksforgeeks.org/move-last-element-to-front-of-a-given-linked-list/
	 * Move last element to front of a given Linked List
	 * Write a C function that moves last element to front in a given Singly Linked List. 
	 * For example, if the given Linked List is 1->2->3->4->5, 
	 * then the function should change the list to 5->1->2->3->4.
	 */
	
	public static ListNode moveToFront(ListNode head) {
		if (head == null ||head.next == null) {
			return head;
		}
		
		ListNode fast = head.next;
		ListNode slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		
		// the slow would points to the second last element;
		// the fast would point the last element;
		slow.next = null;
		fast.next = head;
		ListNode new_head = fast;
		return new_head;
	}
	
	public static void test2() {
		ListNode head = List.createList(10);
		List.printList(head);
		
		ListNode new_head = moveToFront(head);
		List.printList(new_head);
	}
	
	/*
	 * task3 
	 * http://www.geeksforgeeks.org/split-a-circular-linked-list-into-two-halves/
	 * Split a Circular Linked List into two halves
	 * Algorithm:
	 * 1) Store the mid and last pointers of the circular linked list using tortoise and hare algorithm.
	 * 2) Make the second half circular.
	 * 3) Make the first half circular.
	 * 4) Set head (or start) pointers of the two linked lists.
	 */
	
	/*
	 * task4
	 * http://www.geeksforgeeks.org/reverse-a-doubly-linked-list/
	 * Reverse a Doubly Linked List
	 */
	
	/*
	 * task5 
	 * http://www.geeksforgeeks.org/remove-duplicates-from-an-unsorted-linked-list/
	 * Remove duplicates from an unsorted linked list
	 * (1) Two loops
	 * (2) Sorted the list, using remove dupliates from an sorted linked list
	 * (3) Use hash map
	 */
	
	/*
	 * task6 
	 * http://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/
	 * Remove duplicates from an sorted linked list
	 */
	
	public static ListNode removeDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode next_next = null;
		while( cur != null && cur.next != null ) {
			
			if (cur.val == cur.next.val) {
				 next_next = cur.next.next;
				 cur.next = next_next;
			} else {
				cur = cur.next;
			}
		}
		return head;
	}
	
	public static void test6() {
		int[] a = {1,1};
		ListNode head = List.createListWithDup(a);
		List.printList(head);
		ListNode new_head = removeDuplicates(head);
		List.printList(new_head);
	}
	
	/*
	 * task7 http://www.geeksforgeeks.org/write-a-recursive-function-to-print-reverse-of-a-linked-list/
	 * Write a recursive function to print reverse of a Linked List
	 * 
	 * Algorithm:
	 * printReverse(head)
	 *  1 call print reverse for head.next
	 *  2 print head.data
	 */
	
	
	/*
	 * task8 
	 * http://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/
	 * Write a function to get the intersection point of two Linked Lists.
	 * 
	 * There are two singly linked lists in a system. 
	 * By some programming error the end node of one of the linked list got linked into the second list, 
	 * forming a inverted Y shaped list. Write a program to get the point where two linked list merge.
	 */
	
	/*
	 * (1)Traverse one linked list, take it as list1, connect the last node with list1's head, 
	 *    which make a cycle
	 *    In this way, we can convert the question to 
	 * (2)Find the start of cycle in the a linked list with cycle.
	 * (3)Remove the link between list1's last element and its head
	 */
	
	
	/*
	 * task9 
	 * http://www.geeksforgeeks.org/given-a-linked-list-which-is-sorted-how-will-you-insert-in-sorted-way/
	 * Given a linked list which is sorted, how will you insert in sorted way
	 * 
	 * Use a dummy node, dummy.next = head
	 */
	
	
	
	/*
	 * task10
	 * http://www.geeksforgeeks.org/memory-efficient-doubly-linked-list/
	 * Memory efficient doubly linked list
	 * 
	 * This is very tricky 
	 */
	
	/*
	 * task11
	 * http://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
	 * Clone a linked list with next and random pointer | Set 1
	 */
	public static RandomListNode cloneRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}
		RandomListNode cur = head;
		while (cur != null) {
			RandomListNode new_node = new RandomListNode(cur.val);
			// copy the cur.random to new_node.random, later, we will modify it
			new_node.random = cur.random;
			// record cur.next, for update
			RandomListNode next = cur.next;
			cur.next = new_node;
			new_node.next = next;
			cur = next;
		} // this finish the create part.

		// copy the random
		cur = head;
		while (cur != null) {
			RandomListNode copy_cur = cur.next;
			if (cur.random != null) {
				copy_cur.random = cur.random.next;
			}
			cur = cur.next.next;	
		}
		// the following is to split the two list
		cur = head;
		RandomListNode copy_head = cur.next;
		while (cur != null) {
			RandomListNode temp = cur.next;
			cur.next = temp.next;
			cur = cur.next;

			if (temp.next != null) {
				temp.next = temp.next.next;
			}
		}
		return copy_head;
	}
	
	public static RandomListNode cloneRandomListHashMap(RandomListNode head) {
		if (head == null) {
			return null;
		}
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode cur = head;
		RandomListNode dummy = new RandomListNode(-1);
		RandomListNode tail = dummy;
		// create the list
		while (cur != null) {
			RandomListNode new_node = new RandomListNode(cur.val);
			map.put(cur, new_node);
			tail.next = new_node;
			tail = tail.next;
			cur = cur.next;
		}
		
		// copy the random pointer
		cur = head;
		while( cur != null) {
			RandomListNode copy_cur = map.get(cur);
			RandomListNode cur_random = cur.random;
			if (cur_random != null) {
				copy_cur.random = map.get(cur_random);
			}
			cur = cur.next;
		}
		return dummy.next;		
	}
	
	public static void test11() {
		RandomListNode node = new RandomListNode(-1);
		RandomListNode new_node = cloneRandomList(node);
		RandomListNode cur = new_node;
		while(cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}
	}
	
	/*
	 * task12
	 * http://www.geeksforgeeks.org/the-great-tree-list-recursion-problem/
	 * The Great Tree-List Recursion Problem.
	 * 
	 * 
	 */
	
	
	
	/*
	 * task13
	 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
	 * Function to check if a singly linked list is palindrome
	 * 
	 * Algorithm 
	 * 1 using stack. 
	 * 2 reverse the second part. then compare them. 
	 */
	
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode mid = getMid(head);
		// 1-2-3-4-5-6  the mid will point to 3
		// 1-2-3-4-5    the mid will point to 3
		// the second part will be <= the first part
		ListNode second = mid.next;
		mid.next = null;
		
		// reverse the second part
		ListNode prev = null;
		ListNode cur = second;
		while( cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		// the prev will be the new head;
		second = prev;
		ListNode cur_first = head;
		ListNode cur_second = second;
		
		while(cur_second != null) {
			if (cur_first.val != cur_second.val) {
				return false;
			}
			cur_first = cur_first.next;
			cur_second = cur_second.next;
		}
		return true;
	}
	
	
	public static ListNode getMid(ListNode head) {
		if (head ==null || head.next == null) {
			return head;
		}
		ListNode fast = head.next;
		ListNode slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public static void test12() {
		int[] a = {1,2,3,4,5,3,2,1};
		ListNode head = List.createListWithDup(a);
		boolean isPal = isPalindrome(head);
		System.out.println("isPal = " + isPal);
		
	}

}

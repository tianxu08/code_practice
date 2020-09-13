package linkedlist;

public class P3_LinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
//		test6();
//		test10();
		test11();
	}
	
	/*
	 * task1 
	 * http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
	 * Detect and Remove Loop in a Linked List
	 */
	
	public static void detectAndRemoveLoop(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		// first, detect the cycle
		ListNode fast = head, slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			// we find the loop
			if (fast == slow) {
				break;
			}
		}
		
		if (fast == null || fast.next == null) {
			// there is no loop
			return ;
		}
		
		slow = head;
		while(fast.next != slow.next) {
			fast = fast.next;
			slow = slow.next;
		}
		fast.next = null;
	}
	
	public static void test1() {
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
		n6.next = n3;
		
//		List.printList(n1);
		detectAndRemoveLoop(n1);
		List.printList(n1);
	}
	
	/*
	 * task2
	 * http://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked
	 * -list/ Segregate even and odd nodes in a Linked List
	 * 
	 * Given a Linked List of integers, write a function to modify the linked
	 * list such that all even numbers appear before all the odd numbers in the
	 * modified linked list. Also, keep the order of even and odd numbers same.
	 * 
	 * Examples: Input: 17->15->8->12->10->5->4->1->7->6->NULL Output:
	 * 8->12->10->4->6->17->15->5->1->7->NULL
	 * 
	 * Algorithm: 
	 * …1) Get pointer to the last node. 
	 * …2) Move all the odd nodes to the end. ……..
	 *     a) Consider all odd nodes before the first even node and move them to end. 
	 *     b) Change the head pointer to point to the first even node.
	 *     c) Consider all odd nodes after the first even node and move them to the end.
	 *     
	 * Second Algorithm
	 * in the bellow method
	 */
	public static ListNode segerateEvenAndOdd(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode dummy_odd = new ListNode(-1);
		ListNode tail_odd = dummy_odd;
		
		ListNode dummy_even = new ListNode(0);
		ListNode tail_even = dummy_even;
		ListNode cur = head;
		while(cur != null) {
			ListNode next = cur.next;
			if (cur.val % 2 == 0) {
				tail_even.next = cur;
				tail_even = cur;
			} else {
				tail_odd.next = cur;
				tail_odd = cur;
			}
			cur.next = null;
			cur = next;
		}
		tail_even.next = dummy_odd.next;
		
		return dummy_even.next;
	}
	
	public static void test2() {
		ListNode head = List.createList(10);
		List.printList(head);
		ListNode new_head = segerateEvenAndOdd(head);
		List.printList(new_head);
	}
	
	/*
	 * task3 
	 * http://www.geeksforgeeks.org/delete-nodes-which-have-a-greater-value-on-right-side/
	 * Delete nodes which have a greater value on right side
	 *
	 * Algorithm: 
	 * (1) two loops. for every node, we traverse its following node 
	 *     NOTE: we need to use cur.next, it's easier to delete the node
	 *     Time: O(n^2)
	 * (2) 
	 *   a) reverse the list.
	 *   b) keep the max value so far. when the cur.next.val < max, delete it. 
	 *   	!!! remember to update the max
	 *   c) reverse the deleted list
	 *   Time: O(n)
	 */
	
	public static ListNode deleteWithGreaterOnRight(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		// reverse the list
		ListNode rev_head = reverse(head);
		ListNode cur = rev_head;
		int max_so_far = cur.val;
		while(cur != null && cur.next != null) {
			// update max_so_far
			if (max_so_far < cur.val) {
				max_so_far = cur.val;
			}
			if (cur.next.val < max_so_far) {
				// delete cur.next
				ListNode next = cur.next.next;
				cur.next = next;
			} else {
				cur = cur.next;
			}
		}
	
		return reverse(rev_head);
	}
	
	public static ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode cur = head;
		while(cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
	
	public static void test3() {
		int[] a = {12,11,14};
		ListNode head = List.createListWithDup(a);
		List.printList(head);
		ListNode new_head = deleteWithGreaterOnRight(head);
		List.printList(new_head);
	}
	
	
	/*
	 * task4 
	 * http://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/
	 * Reverse alternate K nodes in a Singly Linked List
	 * 
	 * Given a linked list, write a function to reverse every alternate k nodes (where k is an input to the function) in an efficient way. Give the complexity of your algorithm.
	 * Example:
	 * Inputs:   1->2->3->4->5->6->7->8->9->NULL and k = 3
	 * Output:   3->2->1->4->5->6->9->8->7->NULL. 
	 */
	
	/*
	 * Algorithm: 
	 * (1) reverse the firsrt k node 
	 * (2) link the reverse k node linked list to the rest of the list
	 * (3) skip the next k node. 
	 * (4) call the kAltReverse() recursively for the rest of the n-2k nodesl
	 * (5) return the new head of the list
	 */
	public static ListNode kAltReverse(ListNode head, int k) {
		ListNode cur = head;
		ListNode prev = null;
		ListNode next = null;
		int count = 0;
		// (1) reverse the first k nodes of the linked list
		while(cur != null && count < k) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			count ++;
		}  // the prev will be the new head
		
		// (2) Now the head points the kth node. So change the head.next to cur;
		if (head != null) {
			head.next = cur;
		}
		// (3) we do not want to reverse the next k node
		count = 0;
		while(cur != null && count < k - 1) {
			cur = cur.next;
			count ++;
		}
		
		// (4) recursively call the rest of the list. 
		if (cur != null) {
			cur.next = kAltReverse(cur.next, k);
		}
		return prev;  // the prev points the new head
	}
	
	public static void test4() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		ListNode head = List.createListWithDup(a);
		List.printList(head);
		int k = 6;
		ListNode rev = kAltReverse(head, k);
		List.printList(rev);
	}
	
	/*
	 * task5 
	 * Sorted insert for circular linked list
	 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
	 */
	
	public static void insertInCircularList(ListNode head, ListNode new_node) {
		// if the list is empty, insert the node and set k.next = k
		if (head == null) {
			head = new_node;
			head.next = head;
		}
		
		// if the new_node.val < head.val, traverse the list,
		// find the last node of the cycle list
		if (new_node.val < head.val) {
			ListNode cur = head;
			while(cur.next != head) {
				cur = cur.next;
			}
			// now cur points the last node of list
			cur.next = new_node;
			// link the new node to head
			new_node.next = head;
			// update the head
			head = new_node; 
		} else {
			// the new_node shoud be insert inside of the list
			ListNode cur = head;
			while (cur.next != head && cur.next.val < new_node.val) {
				cur = cur.next;
			}
			ListNode next = cur.next;
			cur.next = new_node;
			new_node.next = next;
		}
	}
	
	/*
	 * task5
	 * http://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
	 * Reverse a Linked List in groups of given size
	 * 
	 * Given a linked list, write a function to reverse every k nodes (where k
	 * is an input to the function).
	 * 
	 * Example: 
	 * Inputs: 1->2->3->4->5->6->7->8->NULL and k = 3 
	 * Output: 3->2->1->6->5->4->8->7->NULL.
	 * 
	 * Inputs: 1->2->3->4->5->6->7->80->NULL and k = 5 
	 * Output: 5->4->3->2->1->8->7->6->NULL.
	 */
	
	public static ListNode reverseGroupK(ListNode head, int k) {
		if (head == null || head.next == null) {
			return head;
		}
		if (k <= 0) {
			return head;
		}
		
		// reverse the list size of k
		ListNode prev = null, cur = head;
		int count = 0;
		while (cur != null && count < k) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			count ++;
		}
		// now the prev points the new head;
		// the head points the last node of the first k list nodes list
		if (cur != null) {
			head.next = reverseGroupK(cur, k);
		}
		
		return prev;
	}
	
	public static void test5() {
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		ListNode head = List.createListWithDup(a);
		List.printList(head);
		int k = 1;
		ListNode rev = reverseGroupK(head, k);
		List.printList(rev);
	}
	
	
	/*
	 * task6
	 * http://www.geeksforgeeks.org/merge-sort-for-linked-list/ 
	 * Merge sort linked list
	 */
	
	public static ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode mid = getMid(head);
		ListNode second = mergeSort(mid.next);
		mid.next = null;
		ListNode first = mergeSort(head);
		return merge(first, second);
	}
	
	public static ListNode getMid(ListNode head) {
		if (head == null || head.next == null) {
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
	
	public static ListNode merge(ListNode list1, ListNode list2) {
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		ListNode cur1 = list1, cur2 = list2;
		
		while(cur1 != null && cur2 != null) {
			if (cur1.val < cur2.val) {
				tail.next = cur1;
				tail = tail.next;
				cur1 = cur1.next;
			} else {
				tail.next = cur2;
				tail = tail.next;
				cur2 = cur2.next;
			}
		}
		
		while(cur1 != null) {
			tail.next = cur1;
			tail = tail.next;
			cur1 = cur1.next;
		}
		
		while(cur2 != null) {
			tail.next = cur2;
			tail = tail.next;
			cur2 = cur2.next;
		}
		return dummy.next;
	}
	
	public static void test6() {
		int[] a1 = {9,2,5,8,4,1,10,51,31,20};
		ListNode head = List.createListWithDup(a1);
		List.printList(head);
		ListNode new_head = mergeSort(head);
		List.printList(new_head);
	}
	
	
	/*
	 * task7 http://www.geeksforgeeks.org/identical-linked-lists/ Identical
	 * Linked Lists
	 * 
	 * Two Linked Lists are identical when they have same data and arrangement
	 * of data is also same. 
	 * For example Linked lists a (1->2->3) and b(1->2->3) are identical. . 
	 * Write a function to check if the given two linked lists are identical.
	 */
	
	public static boolean identialRec(ListNode list1, ListNode list2) {
		if (list1 == null && list2 == null) {
			return true;
		}
		if (list1 == null || list2 == null) {
			return false;
		}
		return list1.val == list2.val && identialRec(list1.next, list2.next);
	}
	
	/*
	 * task8
	 * Merge two sorted linked lists
	 * http://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
	 * 
	 * 
	 */
	
	/*
	 * task9
	 * http://www.geeksforgeeks.org/alternating-split-of-a-given-singly-linked
	 * -list/
	 * 
	 * Write a function AlternatingSplit() that takes one list and divides up
	 * its nodes to make two smaller lists ‘a’ and ‘b’. The sublists should be
	 * made from alternating elements in the original list. 
	 * So if the original list is 0->1->0->1->0->1 
	 * then one sublist should be 0->0->0 and the other should be 1->1->1.
	 */
	
	/*
	 * task10
	 * Delete alternate nodes of a Linked List
	 * http://www.geeksforgeeks.org/delete-alternate-nodes-of-a-linked-list/
	 * Given a Singly Linked List, starting from the second node delete all alternate nodes of it.
	 * For example, if the given linked list is 1->2->3->4->5 
	 * then your function should convert it to 1->3->5, 
	 * and if the given linked list is 1->2->3->4 then convert it to 1->3.
	 */
	
	public static ListNode deleteAlternate(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = head;
		ListNode cur = head.next;
		while (prev != null && cur != null) {
			prev.next = cur.next;
			prev = prev.next;
			if (prev != null) {
				cur = prev.next;
			}
		}
		return head;
	} 
	
	public static void test10() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		ListNode head = List.createListWithDup(a);
		List.printList(head);
		
		ListNode new_head = deleteAlternate(head);
		List.printList(new_head);
	}
	
	/*
	 * task11
	 * http://www.geeksforgeeks.org/intersection-of-two-sorted-linked-lists/
	 * Intersection of two Sorted Linked Lists 
	 * Given two lists sorted in
	 * increasing order, create and return a new list representing the
	 * intersection of the two lists. The new list should be made with its own
	 * memory — the original lists should not be changed.
	 * 
	 * For example, let the first linked list be 1->2->3->4->6 and second linked
	 * list be 2->4->6->8, then your function should create and return a third
	 * list as 2->4->6.
	 */
	
	public static ListNode intersectionOf2SortedList(ListNode list1,ListNode list2) {
		if (list1 == null || list2 == null) {
			return null;
		}
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		while(list1 != null && list2 != null) {
			if (list1.val == list2.val) {
				tail.next = new ListNode(list1.val);
				tail = tail.next;
				list1 = list1.next;
				list2 = list2.next;
			} else if (list1.val < list2.val) {
				list1 = list1.next;
			} else {
				list2 = list2.next;
			}
		}
		return dummy.next;
	}
	
	public static void test11() {
		int[] a1 = {1,2,3,4,6};
		int[] a2 = {2,4,6};
		ListNode list1 = List.createListWithDup(a1);
		ListNode list2 = List.createListWithDup(a2);
		
		ListNode intec = intersectionOf2SortedList(list1, list2);
		List.printList(intec);
	}
	
	
	/*
	 * task12
	 * Delete a node in a Doubly Linked List
	 * http://www.geeksforgeeks.org/delete-a-node-in-a-doubly-linked-list/
	 */
	
	
	
	

}

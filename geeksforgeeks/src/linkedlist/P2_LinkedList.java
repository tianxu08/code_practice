package linkedlist;

public class P2_LinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test3();
//		test6();
		test7();
	}
	
	/*
	 * task1
	 * http://www.geeksforgeeks.org/sort-a-linked-list-of-0s-1s-or-2s/
	 * Following steps can be used to sort the given linked list. 1) Traverse
	 * the list and count the number of 0s, 1s and 2s. Let the counts be n1, n2
	 * and n3 respectively. 2) Traverse the list again, fill the first n1 nodes
	 * with 0, then n2 nodes with 1 and finally n3 nodes with 2.
	 */
	
	/*
	 * task2
	 * http://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
	 * Add two numbers represented by linked lists | Set 1
	 * 
	 */
	/*
	 * List: 5->6->3   represent 365
	 * List2: 8->4->2  represent 248
	 * output 3 ->1->6  represent 316 
	 * 
	 * Input:
	 * First List: 7->5->9->4->6  // represents number 64957
	 * Second List: 8->4 //  represents number 48
	 * Output
	 * Resultant list: 5->0->0->5->6  // represents number 65005
	 */
	public static ListNode addTwoList_task2(ListNode head1, ListNode head2) {
		if (head1 == null ) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		ListNode cur1 = head1;
		ListNode cur2 = head2;
		int carry = 0;
		while(cur1 != null && cur2 != null) {
			int sum = cur1.val + cur2.val + carry;
			carry = sum/10;
			sum %= 10;
			ListNode node = new ListNode(sum);
			tail.next = node;
			tail = node;
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		
		while(cur1 != null) {
			int sum = cur1.val + carry;
			carry = sum/10;
			sum %= 10;
			ListNode node = new ListNode(sum);
			tail.next = node;
			tail = node;
			cur1 = cur1.next;
		}
		
		while(cur2 != null) {
			int sum = cur2.val + carry;
			carry = sum/10;
			sum %= 10;
			ListNode node = new ListNode(sum);
			tail.next = node;
			tail = node;
			cur2 = cur2.next;
		}
		
		return dummy.next;
	}
	public static void test2() {
		int[] a1 = {7,5,9,4,6};
		int[] a2 = {8,4};
		ListNode head1 = List.createListWithDup(a1);
		ListNode head2 = List.createListWithDup(a2);
		
		ListNode sum = addTwoList_task2(head1, head2);
		List.printList(sum);
	}
	
	/*
	 * task3 Add two numbers represented by linked lists | Set 2
	 * http://www.geeksforgeeks.org/sum-of-two-linked-lists/
	 * 
	 * 
	 * Given two numbers represented by two linked lists, write a function that
	 * returns sum list. The sum list is linked list representation of addition
	 * of two input numbers. It is not allowed to modify the lists. Also, not
	 * allowed to use explicit extra space (Hint: Use Recursion).
	 * 
	 * Example
	 * 
	 * Input: 
	 * First List: 5->6->3 // represents number 563 
	 * Second List: 8->4->2 // represents number 842 
	 * Output Resultant list: 1->4->0->5 // represents number 1405
	 */

	/**
	 * Algorithm
	 * (1) calculate the size of given two linked list
	 * (2) if size are the same, then calculate the sum using recursion. 
	 *     Hold all node in recursion call stack till the right most node, 
	 *     carry sum of the rightmost nodes and forward the carry to left side
	 * (3) if size are not same, let the difference be diff
	 *     <1> Move the diff step in the longer list ahead. use step (2) to calculate sum of 
	 *         smaller list and right sublist (of same size) of larger list. Also , store the 
	 *         carry of this sum
	 *     <2> calculate sum of carry( calculated in <1>) with the remaining left sub-left of 
	 *         longer list. Nodes this sum are added 
	 *         at the beginning of sum list obtained previous step
	 */
	public static class Carry{
		int val;
		public Carry() {
			this.val = 0;
		}
		public Carry(int c) {
			this.val = c;
		}
	}
	
	
	public static ListNode addTwoList_task3(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		
		int list1_len = getLength(list1);
		int list2_len = getLength(list2);
		if (list1_len < list2_len) {
			return addTwoList_task3(list2, list1);
			// we let the list1 to be the longer list
		}
		
		Carry carry = new Carry();
		if (list1_len == list2_len) {
			// the lengths are same. call the addListSameSize
			ListNode result = addListSameSize(list1, list2, carry);
			
			if (carry.val != 0) {
				ListNode new_node = new ListNode(carry.val);
				new_node.next = result;
				return new_node;
			} else {
				return result;
			}
		} else {
			int diff = list1_len - list2_len;
			ListNode cur = list1;
			
			for (int i = 0; i < diff; i++) {
				cur = cur.next;
			}
			
			// now the cur points the sublist( in list1) whose length is same with list2
			ListNode curResult = addListSameSize(cur, list2, carry);
			
			// add the carry to the remaining list (the first part) of list1
			ListNode result = addCarryToRemaining(list1, cur, carry);
			
			// connect the result(the left part sum) with curResult (the right part sum)
			ListNode tail = result;
			while(tail != null && tail.next != null) {
				tail = tail.next;
			}
			
			tail.next = curResult;	
			// now the carry.val might not be 0, we still need to create a new node
			if (carry.val != 0) {
				ListNode new_node = new ListNode(carry.val);
				new_node.next = result;
				return new_node;
			} else {
				return result;
			}
		}
	}
	
	
	public static ListNode addListSameSize(ListNode list1, ListNode list2, Carry carry) {
		if (list1 == null) {
			return null;
		}
		int sum = 0;
		ListNode result = new ListNode(0);
		result.next = addListSameSize(list1.next, list2.next, carry);
		
		//after the above code, we'll get the carry from its right nodes add
		sum = list1.val + list2.val + carry.val;
		carry.val = sum/10;
		result.val = sum%10;
		
		return result;
	}
	
	public static ListNode addCarryToRemaining(ListNode list, ListNode cur, Carry carry) {
		if (list == cur) {
			// when met cur, the remaining cannot go forward
			return null;
		}
		int sum = 0;
		ListNode result = new ListNode(0); 
		result.next = addCarryToRemaining(list.next, cur, carry);
		
		sum = list.val + carry.val;
		carry.val = sum / 10;
		result.val = sum % 10;
		
		return result;
	}
	
	public static int getLength(ListNode head) {
		ListNode cur = head;
		int count = 0;
		while(cur != null) {
			count ++;
			cur = cur.next;
		}
		return count;
	}
	
	public static void test3() {
		int[] a1 = {9, 9, 9};
		int[] a2 = {1,8};
		ListNode list1 = List.createListWithDup(a1);
		ListNode list2 = List.createListWithDup(a2);
		
		ListNode sum = addTwoList_task3(list1, list2);
		List.printList(sum);
	}
	
	/*
	 * task4 
	 * Flattening a Linked List
	 * http://www.geeksforgeeks.org/flattening-a-linked-list/
	 */
	
	public static class Node {
		public int data;
		public Node right;
		public Node down;
		public Node(int v) {
			this.data = v;
		}
	} 
	
	public static Node flatten(Node root) {
		if (root == null || root.right == null) {
			return root;
		}
		return merge(root, flatten(root.right));
	}
	
	public static Node merge(Node a, Node b) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		
		Node result = null;
		if (a.data < b.data) {
			result = a;
			result.down = merge(a.down, b);
		} else {
			result = b;
			result.down = merge(a, b.down);
		}
		return result;
	}
	
	public static void test4() {
		
	}
	
	/*
	 * task5 
	 * http://www.geeksforgeeks.org/implement-lru-cache/
	 * Implement LRU Cache
	 */
	
	/*
	 * task6 http://www.geeksforgeeks.org/rotate-a-linked-list/ Rotate a Linked
	 * List Given a singly linked list, rotate the linked list counter-clockwise
	 * by k nodes. Where k is a given positive integer. 
	 * For example, if the given linked list is 10->20->30->40->50->60 and k is 4, 
	 * the list should
	 * be modified to 50->60->10->20->30->40. Assume that k is smaller than the
	 * count of nodes in linked list.
	 */
	
	public static ListNode rotateList(ListNode head, int k) {
		if (head == null || head.next == null) {
			return head;
		}
		int len = getLength(head);
		if (k > len) {
			k = k%len;
		}
		ListNode cur = head;
		for (int i = 0; i < k - 1; i++) {
			cur = cur.next;
		}
		ListNode new_head = cur.next;
		cur.next = null;
		cur = new_head;
		while(cur != null && cur.next != null) {
			cur = cur.next;
		}
		cur.next = head;
		return new_head;
	}
	public static void test6() {
		int[] a = {1,2,3,4,5,6,7,8};
		int k = 2;
		ListNode head = List.createListWithDup(a);
		ListNode new_head = rotateList(head, k);
		List.printList(new_head);
	}
	
	/*
	 * task7 Find a triplet from three linked lists with sum equal to a given
	 * number
	 * http://www.geeksforgeeks.org/find-a-triplet-from-three-linked-lists
	 * -with-sum-equal-to-a-given-number/
	 * 
	 * Given three linked lists, say a, b and c, find one node from each list
	 * such that the sum of the values of the nodes is equal to a given number.
	 * For example, if the three linked lists are 12->6->29, 23->5->8 and
	 * 90->20->59, and the given number is 101, 
	 * the output should be triple “6 5 90″.
	 */
	
	/*
	 * Algorithm 
	 * Simple idea:
	 * use three loop; Time O(n^3)
	 * 
	 * Another one:
	 * (1) sort the list b in ascending order and list c in descending order.
	 * (2) traverse list a, if sum < target, we need a larger one. listb = listb.next
	 *     if sum > target, we need a smaller one. listc = list.next
	 *      
	 */
	public static void printTriple(ListNode list1, ListNode list2, ListNode list3, int target) {
		ListNode list2_ascending = mergeSortAscending(list2);
		ListNode list3_descending = mergeSortDescending(list3);
		
		ListNode cur1 = list1;
		while(cur1 != null) {
			ListNode cur2 = list2_ascending;
			ListNode cur3 = list3_descending;
			
			while(cur2 != null && cur3 != null) {
				int sum = cur1.val + cur2.val + cur3.val;
				if (sum == target) {
					System.out.println(cur1.val + " " + cur2.val + " " + cur3.val);
					return ;
				} else if (sum < target) {
					// we need a larger one
					cur2 = cur2.next;
				} else {
					cur3 = cur3.next;
				}
			}
			
			cur1 = cur1.next;
		}
		
		System.out.println("donsn't exist the triple");
	}
	
	public static void test7() {
		int[] a = {12,4,7,19,5,14,3,1};
		ListNode list1 = List.createListWithDup(a);
		ListNode list2 = List.createListWithDup(a);
		
		ListNode sort1 = mergeSortAscending(list1);
		List.printList(sort1);
		
		ListNode sort2 = mergeSortDescending(list2);
		List.printList(sort2);
	}
	public static ListNode mergeSortAscending(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode mid = getMid(head);
		ListNode second = mergeSortAscending(mid.next);
		mid.next = null;
		
		ListNode first = mergeSortAscending(head);
		
		return mergeAscending(first, second);
			
	}
	
	public static ListNode mergeAscending(ListNode list1, ListNode list2) {
		ListNode cur1 = list1;
		ListNode cur2 = list2;
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
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
	
	public static ListNode mergeSortDescending(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = getMid(head);
		ListNode second = mergeSortDescending(mid.next);
		mid.next = null;
		ListNode first = mergeSortDescending(head);
		
		return mergeDescending(first, second);
	}
	public static ListNode mergeDescending(ListNode list1, ListNode list2) {
		ListNode cur1 = list1;
		ListNode cur2 = list2;
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		while(cur1 != null && cur2 != null) {
			if (cur1.val > cur2.val) {
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
	
	public static ListNode getMid(ListNode head) {
		ListNode fast = head.next;
		ListNode slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	/*
	 * task8 
	 * 
	 */
	
	/*
	 * task9 
	 * Union and Intersection of two Linked Lists
	 * http://www.geeksforgeeks.org/union-and-intersection-of-two-linked-lists/
	 */
	
	/*
	 * task10
	 * http://www.geeksforgeeks.org/in-place-conversion-of-sorted-dll-to-balanced-bst/
	 * In-place conversion of Sorted DLL to Balanced BST
	 */
	
	
	
	/*
	 * task11
	 * Sorted Linked List to Balanced BST
	 * http://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
	 */
	
	/*
	 * task12
	 * http://www.geeksforgeeks.org/delete-a-given-node-in-linked-list-under-given-constraints/
	 * Delete a given node in Linked List under given constraints
	 */
	
	/*
	 * task13
	 * http://www.geeksforgeeks.org/how-to-write-functions-that-modify-the-head-pointer-of-a-linked-list/
	 * How to write C functions that modify head pointer of a Linked List?
	 */
	/*
	 * task14
	 * XOR Linked List – A Memory Efficient Doubly Linked List | Set 1
	 * http://www.geeksforgeeks.org/xor-linked-list-a-memory-efficient-doubly-linked-list-set-1/
	 */

}

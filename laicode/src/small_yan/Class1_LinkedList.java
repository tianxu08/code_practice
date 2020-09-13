package small_yan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import debug.Debug;
import ds.DListNode;
import ds.ListNode;

public class Class1_LinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
//		 test1_3();
		// test4_2();
		// test5();
//		test7_1();
//		test7_2();
		test8_1();
	}

	/*
	 * Linked List 
	 * ● insert() 
	 * ● delete() 
	 * ● find() 
	 * ● kth() from start/end ●
	 * middle node ● merge() ● reverse() ● partition a). slow and fast pointer
	 * b). dummy head ­­ head有可能会变化 c). iterative d). recursive head ­> (next
	 * list) Other Topics: ­ Double Linked List ­ Circular Single/Double Linked
	 * List ­ Double Linked List + Other Data Structure(HashMap) ­ O(1) delete
	 * if knows the position of the node ­ O(1) search if we have a HashMap
	 * 
	 * ­ Advanced Linked Lists: Skip List, Reverted index list* ­ Linked List as
	 * index implemented along with actual content in byte array(free list, file
	 * blocks)*
	 */

	/*
	 * task1 reverse linked list recursion iterative stack
	 * 
	 * see lec.Lec23
	 */
	public static ListNode task1_reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode next = cur.next;
		ListNode newHead = task1_reverse(next);
		cur.next = null;
		next.next = cur;

		return newHead;
	}
	
	public static ListNode task1_reverse_iter(ListNode head) {
		// sanity check
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null, cur = head;
		while(cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		
		return prev;
	}

	/*
	 * task1.1 print linked list in reverse order recursion stack iterative : --
	 * reverse first -- print -- reverse back
	 */
	public static void test() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;

		Debug.printLinkedList(n1);

		// task1_1_printReverse(n1);
		ListNode newHead = task1_reverse_iter(n1);
		Debug.printLinkedList(newHead);

	}

	public static void task1_1_printReverse(ListNode head) {
		if (head == null) {
			return;
		}
		task1_1_printReverse(head.next);
		System.out.print(head.value + " ");
	}

	/*
	 * task1.2 reverse Double Linked List
	 * cur  next
	 * 1 -> 2 -> 3 ->4 ->5
	 *   <-   <-   <-  <-
	 * 
	 * cur = head;
	 * next = cur.next;
	 * next.prev = null;
	 * 
	 * newHead = reverseDDL(head.next);
	 * 
	 * next.next = cur;
	 * cur.prev = next;
	 * 
	 * return newHead;
	 * 
	 */

	public static DListNode task1_2_reverseDLL(DListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		DListNode cur = head;
		DListNode next = cur.next;
		next.prev = null;

		DListNode newHead = task1_2_reverseDLL(next);

		next.next = cur;
		cur.prev = next;

		return newHead;
	}

	public static DListNode task1_2_reverseDDLIter(DListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		DListNode cur = head;
		while (cur.next != null) {
			DListNode next = cur.next;

			// set old pointer null
			next.prev = null;
			cur.next = null;

			// set the new pointer
			next.next = cur;
			cur.prev = next;

			// update cur
			cur = next;
		}
		return cur;
	}

	/*
	 * task1.3 reverse circular linked list 1->2->3->1
	 * 
	 * 1<-2<-3<-1
	 */
	public static ListNode task1_3_reverseCircular(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = head;
		ListNode cur = head.next;
		ListNode stop = cur;

		while (true) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			if (cur == stop) {
				break;
			}
		}
		return prev; // can also return the head.
	}

	public static void test1_3() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n1;

		ListNode cur = n1;
		do {
			System.out.print(cur.value + " ");
			cur = cur.next;
		} while (cur != n1);

		System.out.println();
		ListNode reverse = task1_3_reverseCircular(n1);
		System.out.println("rHead = " + reverse.value);
		cur = reverse;

		do {
			System.out.print(cur.value + " ");
			cur = cur.next;
		} while (cur != reverse);
	}

	/*
	 * list的长度可以帮助解题. 
	 * 2. List A and List B, intersection, 
	 * A : 1​ ­ -> 2 ­ -> ​5
	 * ­ -> 6 ­ -> 7 ­ -> 8 | B 5
	 * 
	 * get A's length, B's length diff = alength - blength suppose aLength >
	 * bLenght let a first goes diff steps. then, a and b go together.
	 */

	/*
	 * task3 
	 * task3.1 Add two Lists I
	 * 
	 * 1 -­> 2 ­-> 3 -­> null 7 ­-> 8 -­> null 321 + 87 = 408 8 ­-> 0 ­-> 4 -­>
	 * null
	 * 
	 * use a carry
	 * 
	 * 
	 * task3.2
	 * 
	 * 3.2 Add two Lists II 1 -­> 2 -­> 3 -­> null 0 -­> 7 -­> 8 -­> null 123 +
	 * 78 = 201 2 -­> 0 -­> 1 -­> null
	 * 
	 * 
	 * Method 1: reverse both list, add two lists I, reverse back. Method 3:
	 * Stack. Method 2: 1). Recursion. 2). a’s length ­ b’s length = 1 ListNode
	 * add(ListNode a, ListNode b, int difference) { if (a == null) return null;
	 * if (difference == 0) { ListNode next = add(a.next, b.next, 0); ListNode
	 * current = new ListNode(a​.val + b.val + next.val / 10)​; next.val %= 10;
	 * current.next = next; return current; } else { //... ListNode next =
	 * add(a.next, b, difference ­ 1); ListNode current = new ListNode(a​.val +
	 * 0 + next.val / 10)​; //.. }
	 * 
	 * 
	 * More details, see Class1_LinkedList_add2nums
	 */

	/*
	 * task4 删除多个List里面的符合要求的node 
	 * 4.1 remove all nodes contains vowels.
	 * 用cur.next 判断
	 */

	/*
	 * task4.2 remove all nodes by indices 1->2->3->4->5 ->null indices needed
	 * to be deleted {1,3}, so we will delete 2, and 4 1-3->5->null
	 * 
	 * using dummy node
	 */
	public static void test4_2() {
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
		int[] indices = { 2, 3 };
		ListNode newHead = task4_2_remove_all_nodes_by_index(n1, indices);
		Debug.printLinkedList(newHead);
	}

	public static ListNode task4_2_remove_all_nodes_by_index(ListNode head,
			int[] indices) {
		if (head == null) {
			return null;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		int count = 0;
		int countIndex = 0;
		ListNode cur = dummy;

		while (cur.next != null && countIndex < indices.length) {
			if (count == indices[countIndex]) {
				// delete cur.next
				cur.next = cur.next.next;
				// increase countIndex
				countIndex++;
			} else {
				// update cur
				cur = cur.next;
			}
			// update count
			count++;
		}
		return dummy.next;
	}

	/*
	 * task5
	 * 
	 * Given linked list as 1->2->3->4->5->6 output is as 1->3->5->2->4->6 even
	 * position: 1->3->5 odd position: 2->4->6
	 */
	public static void test5() {
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

		Debug.printLinkedList(n1);

		ListNode newHead = task5_divide_odd_even_node(n1);
		Debug.printLinkedList(newHead);

	}

	public static ListNode task5_divide_odd_even_node(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		ListNode dummy_even = new ListNode(-1);
		ListNode dummy_odd = new ListNode(-1);
		ListNode tail_even = dummy_even;
		ListNode tail_odd = dummy_odd;
		ListNode cur = head;
		boolean isEven = true;
		while (cur != null) {
			ListNode next = cur.next;
			// unlink cur.next
			cur.next = null;
			if (isEven) {
				tail_even.next = cur;
				tail_even = tail_even.next;
			} else {
				tail_odd.next = cur;
				tail_odd = tail_odd.next;
			}
			isEven = !isEven;
			cur = next;
		}

		Debug.printLinkedList(dummy_even);
		Debug.printLinkedList(dummy_odd);
		tail_even.next = dummy_odd.next;
		return dummy_even.next;
	}

	/*
	 * task6 determine if a list is palindrome 
	 * 1 mid node 
	 * 2 reverse second half
	 * 3 ... 
	 * 4 reverse back second half and connect with the frist half.
	 */
	

	/*
	 * task7 单个链表， k 个分组，分别反转 0 -> 1 -> 2 ->3 ->4 ->5 ->6 ->7 
	 * k = 3 2 -> 1 -> 0 ->5 ->4 ->3 ->7 ->6
	 * 
	 * task7.1  单个链表， 2个分组， 反转
	 */
	
	public static void test7_1() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);
		ListNode n8 = new ListNode(8);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		
		Debug.printLinkedList(n1);
		ListNode h1 = task7_1_reverseKNode_group2(n1);
		Debug.printLinkedList(h1);
	}
	
	public static ListNode task7_1_reverseKNode_group2(ListNode head) {
		// sanity check
		if (head == null || head.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode next = cur.next;
		ListNode nnext = next.next;
		
		cur.next = task7_1_reverseKNode_group2(nnext);
		next.next = cur;
		
		return next;
	}
	public static ListNode task7_1_reverseKNode_group3(ListNode head) {
		// sanity check
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		
		ListNode cur = head;
		ListNode next = cur.next;
		ListNode nnext = next.next;
		ListNode nnnext = nnext.next;
		cur.next = task7_1_reverseKNode_group3(nnnext);
		nnext.next = next;
		next.next = cur;
		
		return nnext;
	}
	
	public static ListNode task7_1_reverseKNode_groupk(ListNode head, int k) {
		return null;
	}
 	
	
	
	/*
	 * task7.2 单个链表，k 个分组，翻转奇数组 0 -> 1 -> 2 ->3 ->4 ->5 ->6 ->7 k = 3
	 * 
	 * 2 ->1 ->0 ->3 ->4 ->5 ->7 ->6
	 */
	public static void test7_2() {
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

		Debug.printLinkedList(n1);
		int k = 3;
		ListNode newHead = reverseFirstK(n1, k);
		Debug.printLinkedList(newHead);
	}

	public static ListNode reverseFirstK(ListNode head, int k) {
		int count = 1;
		ListNode cur = head;
		ListNode tail = head;
		while (count < k && cur != null) {
			count++;
			cur = cur.next;
		}
		// cur should point the kth 1based node
		ListNode next = null;
		if (cur != null) {
			next = cur.next;
			
			cur.next = null;
		}
		// otherwise, do nothing.

		// unlink cur
		// reverse head --> cur
		ListNode newHead = task1_reverse(head);

		tail.next = next;

		return newHead;
	}
	
	
	/*
	 * task8 merge k sorted list
	 * 
	 * task8.1 merge k sorted list, and de-duplicate , the node with same value should only appear once 
	 * use minHeap  see Lec.lec21
	 * 
	 * 1->2->3
	 * 1->4->5
	 * 3->5->6
	 * 
	 * 1->2->3->4->5->6
	 * 
	 * task8.2
	 * what if the duplicates should not be in the final list
	 * 2->4->6
	 */
	
	public static void test8_1() {
		ListNode n1_1 = new ListNode(1);
		ListNode n1_2 = new ListNode(2);
		ListNode n1_3 = new ListNode(3);
		n1_1.next = n1_2;
		n1_2.next = n1_3;
		
		
		ListNode n2_1 = new ListNode(1);
		ListNode n2_2 = new ListNode(4);
		ListNode n2_3 = new ListNode(5);
		n2_1.next = n2_2;
		n2_2.next = n2_3;
		
		ListNode n3_1 = new ListNode(3);
		ListNode n3_2 = new ListNode(5);
		ListNode n3_3 = new ListNode(6);
		n3_1.next = n3_2;
		n3_2.next = n3_3;
		
		
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		lists.add(n1_1);
		lists.add(n2_1);
		lists.add(n3_1);
		
		
//		ListNode newHead = task8_1_mergeKSortedList(lists);
//		Debug.printLinkedList(newHead);
		
		ListNode newHead2 = task8_2_mergeKSortedList(lists);
		Debug.printLinkedList(newHead2);
		
		
	}
	
	public static ListNode task8_1_mergeKSortedList(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		// define th ecomparator
		Comparator<ListNode> myComp = new Comparator<ListNode>() {
			
			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				if (o1.value == o2.value) {
					return 0;
				}
				return o1.value < o2.value ? -1 : 1;
			}
		};
		
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.size(), myComp);
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode tail = dummy;
		// add all lists' head into minHeap
		for(ListNode head: lists) {
			minHeap.add(head);
		}
		// 
		while(!minHeap.isEmpty()) {
			ListNode cur = minHeap.poll();
			// cur.val != tail.val, append it to the linked list
			if (tail.value != cur.value) {
				tail.next = cur;
				tail = tail.next;	
			}
			// add cur.next to the minHeap
			if (cur.next != null) {
				minHeap.offer(cur.next);
			}
		}	
		return dummy.next;
	}
	
	
	public static ListNode task8_2_mergeKSortedList(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		Comparator<ListNode> myComp = new Comparator<ListNode>() {
			
			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				if (o1.value == o2.value) {
					return 0;
				}
				return o1.value < o2.value ? -1 : 1;
			}
		};
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.size(), myComp);
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode tail = dummy;
		// add all lists' head into minHeap
		for(ListNode head: lists) {
			minHeap.add(head);
		}
		
		// use a flag to indicate,so far, whether there is a same element with cur
		boolean duplicate = false;
		
		while(!minHeap.isEmpty()) {
			ListNode cur = minHeap.poll();
			if (duplicate) {
				// this is duplicate
				if (!minHeap.isEmpty() && minHeap.peek().value == cur.value) {
					// do nothing. duplicate still true
					// duplicate = true;  // still true
				} else {
					// minHeap is null or minHeap.peek().value != cur.value
					duplicate = false;
				}				
			} else {
				// duplicate is false
				if (!minHeap.isEmpty() && minHeap.peek().value == cur.value) {
					// don't add this node to new linked list
					// set duplicate is true
					duplicate = true;
				} else {
					// minHeap is null or minHeap.peek().value != cur.value
					// append this node to new linked list
					tail.next = cur;
					tail = tail.next;
				}
			}
			if (cur.next != null) {
				minHeap.add(cur.next);
			}	
		}
		
		return dummy.next;
		
	}

}

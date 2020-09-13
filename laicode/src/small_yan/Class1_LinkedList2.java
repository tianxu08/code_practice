package small_yan;

import java.util.HashSet;

import debug.Debug;
import ds.DListNode;
import ds.ListNode;

public class Class1_LinkedList2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test5();
		test8_2();
	}
	
	/*
	 * Slow/Fast Pointers
	 * -- fast pointer moves with a faster speed then slow
	 * -- fast pointer moves a few steps prior to slow pointer, then move with the same speed
	 * -- fast/slow pointers move with different conditions..
	 * 
	 * !!! This usually apply to array questions as well. 
	 * 
	 * 1 mid node
	 * 2 check if linked list has cycle/ return the node where cycle starts
	 * 3 determine the mid node of linked list that possibly has cycle
	 * 4 get/ delete nth node from tail
	 * 5 determine the largest sub list not containing duplicate values
	 *  
	 */
	public static void test1() {
		ListNode head = Debug.createLinkedList(3);
		ListNode mid = task1_getMidNode(head);
		Debug.printLinkedList(head);
		Debug.printLinkedList(mid);
	}
	
	
	/*
	 * 1->2->3     return 2
	 * 1->2->3->4  return 2
	 */
	public static ListNode task1_getMidNode(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
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
	
	
	public  static boolean  task2_1_check_cycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null && slow != null && slow.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}
	
	public  static ListNode  task2_2_check_cycle_node(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null && slow != null && slow.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			if (fast == slow) {
				break;
			}
		}
		
		if (fast == null || fast.next == null) {
			// there is no cycle
			return null;
		}
		slow = head;
		while(fast !=  slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
	
	/*
	 * task3
	 * determine the mid node of linked list that possibly has cycle
	 */
	
	/*
	 * task4
	 * get/ delete nth node from tail
	 */
	
	/*
	 * task5
	 * determine the longest sub list not containing duplicate values
	 * 1 -> 2 ->3 ->1 ->4 -> null, longest is 2 ->3 ->1 ->4
	 * 
	 * array: {1,2,3,1,4} longest subarray not containing duplicate values
	 * longest substring without duplicate characters
	 * 
	 * 1 use hashSet to store the values
	 * 2 slow faster pointer, no duplicated
	 *   a) when to mover fast: if fast.val not in the HashSet
	 *   b) when to move slow: if fast.val in the HashSet
	 * 3 count: number of nodes between slow and fast. count == set.size()
	 * 4 return max(count) 
	 */
	
	public static void test5() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(1);
		ListNode n5 = new ListNode(4);
		
		n1.next =n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		int max = task5_longest_sublist_no_duplicate(n1);
		System.out.println("max = " + max);
	}
	
	public static int task5_longest_sublist_no_duplicate(ListNode head) {
		if (head == null) {
			return 0;
		}
		ListNode fast = head;
		ListNode slow = head;
		HashSet<Integer> myset = new HashSet<Integer>();
		int result = 0;
		while(fast != null) {
			if (!myset.contains(fast.value)) {
				myset.add(fast.value);
				fast = fast.next;
			} else {
				// myset contains fast.value
				//move slow
				myset.remove(slow.value);
				slow = slow.next;
			}
			result = Math.max(result, myset.size());
		}
		return result;
	}
	
	/*
	 * 6 determine the longest sub list in a circular list not containing duplicate values
	 * 
	 * !!!
	 */
	
	/*
	 * task7
	 * 拍扁
	 * More generally, the list can be treated as Graph Example:
	 * ­ Clone Linked List with random pointer
	 * ­ Clone Graph
	 */
	
	/*
	 * task8
	 * 1. 拍扁一个double linked list with child successor.
	 * each child is the head of a separate list.
	 */
	
	public static class ListNodeCD {
		ListNodeCD child;
		ListNodeCD next;
		ListNodeCD prev;
		int val;
	} 
	
	
	/*
	 * http://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
	 * 
	 * 10 -> 5 -> 12 -> 7 ->11
	 *  |               |
	 *  4 -> 20 -> 13   17 -> 6
	 *        |     |    |  
	 *        2     16   9 -> 8
	 *              ｜   ｜
	 *              3    19 －>15
	 *              
	 *  result:
	 *  10 -> 5 ->12 ->7 ->11 ->4 -> 20 ->13 ->17 ->6 ->2 ->16 ->9 ->8 ->3 ->19 ->15
	 *  we need to clearly say that we need to flatten level by level. 
	 *  
	 *  get the tail of the first level, in the above example, the ptr points to 11
	 *  start from the first level, process all nodes one by one, if a node has a child, 
	 *  then we append the child to tail and update the tail, otherwise, we don't do anything. 
	 *  
	 *   After the first level is processed, all the next level nodes will be appended after first level. 
	 *   Same process of the append nodes
	 */
	
	public static void test8_2() {
		ListNodeCS n1 = new ListNodeCS(1);
		ListNodeCS n2 = new ListNodeCS(2);
		ListNodeCS n3 = new ListNodeCS(3);
		ListNodeCS n4 = new ListNodeCS(4);
		ListNodeCS n5 = new ListNodeCS(5);
		ListNodeCS n6 = new ListNodeCS(6);
		
		n1.next = n2;
		n2.next = n3;
		n1.child = n4;
		n4.next = n5;
		n5.child = n6;
		
		printListNodeCS(n1);
		ListNodeCS rev = task8_flatten(n1);
		printListNodeCS(rev);
		
	}
	
	public static class ListNodeCS{
		ListNodeCS child;
		ListNodeCS next;
		int val;
		public ListNodeCS(int v) {
			this.val = v;
			this.child = null;
			this.next = null;
		}
	}
	
	public static ListNodeCS task8_flatten(ListNodeCS head) {
		// sanity check
		if (head == null) {
			return head;
		}
		
		// get the tail of the first level
		ListNodeCS tail = head;
		while(tail.next != null) {
			tail = tail.next;
		}
		
		ListNodeCS cur = head;
		
		while (cur != null) {
			// if current node has child, append its child to the tail and update tail
			if (cur.child != null) {
				ListNodeCS child = cur.child;
				cur.child = null;
				// append to the tail
				tail.next = child;
				// update the tail
				while(tail.next != null) {
					tail = tail.next;
				}
			}
			// update the current node
			cur = cur.next;
		}
		return head;
	}
	
	public static void printListNodeCS(ListNodeCS head) {
		ListNodeCS cur = head;
		while(cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	
	
	
	
	
	/*
	 * task9
	 * HashMap + Double Linked List
	 * key points;
	 * --- HashMap provide average O(1) lookup
	 * --- Double linked list provide O(1) remove, appendTail, appendHead
	 * 
	 * task9.1
	 * LRU(last recently used cache)
	 * 
	 * HashMap + Double Linked List
	 */
	
	
	
	
	/*
	 * task9.2
	 * First Non-Repeating Character In Stream
	 * A stream of characters, read one by one, need to know at any time, the earlist read no-repeating character
	 * in the stream.
	 */
	
	
	
	
	/* 
	 * task9.3
	 * Implement a Stack with findMid(), at any time return the mid element in O(1).
	 * pop(), push(), findMid() O(1) time complicity
	 * 
	 */
	
	
	
	
	
	
	

}

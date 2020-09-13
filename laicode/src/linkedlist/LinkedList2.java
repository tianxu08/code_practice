package linkedlist;

import ds.*;


public class LinkedList2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1_insertSort();
	}
	
	public static ListNode createList(int[] array) {
		ListNode head = null;
		ListNode tail = null;
		for(int i = 0; i < array.length; i ++) {
			ListNode node = new ListNode(array[i]);
			if (head == null) {
				head = node;
				tail = node;
			} else {
				tail.next =node;
				tail = tail.next;
			}
		}
		return head;
	} 
	
	public static void printList(ListNode head) {
		while(head !=  null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}
	
	public static void test1_insertSort() {
		int[] array = {5, 3, 8, 1, 4};
		ListNode head = createList(array);
		
		printList(head);
		
		ListNode newHead = task1_insertionSortList(head);
		
		printList(newHead);
	}
	
	public static ListNode task1_insertionSortList(ListNode head) {
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		// don't link dummy to head
		
		ListNode cur = head;
		
		while(cur != null) {
			ListNode ptr = dummy;
			while( ptr.next != null && ptr.next.value < cur.value) {
				System.out.println("hello");
				ptr = ptr.next;
			}
			
			ListNode next = cur.next;
			// insert cur after ptr.
			cur.next = ptr.next;
			ptr.next = cur;
			
			// update cur
			cur = next;
		}
		return dummy.next;
	}
	
	// every time, find the minimum, then insert it after dummy
	public static ListNode task2_selectionSortList(ListNode head) {
		
		int globalMin = Integer.MAX_VALUE;
		ListNode globalMinPrevPtr = null;
		ListNode cur = head;
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode tail = dummy;
		while(cur != null) {
			ListNode ptr = cur;
			while(ptr != null && ptr.next != null) {
				if (cur.next != null && ptr.next.value < globalMin) {
					globalMin = ptr.next.value;
					globalMinPrevPtr = ptr;
				}
				ptr = ptr.next;
			}
			
			// delete the globalMin node from previous node. 
			ListNode globalMinNode = globalMinPrevPtr.next;
			globalMinPrevPtr = globalMinPrevPtr.next.next;
			
			// insert globalMinNode after tail
			globalMinNode.next = tail.next;
			tail.next = globalMinNode;
			
			// update tail
			tail = tail.next;
			
			// update cur
			cur = cur.next;
		}
		return dummy.next;
	}
	
	
	

	

}


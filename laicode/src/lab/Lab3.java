package lab;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;


import ds.ListNode;

public class Lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
		test4();
	}

	/*
	 * Class 3 - Queue & Stack & Deque
	 * 
	 */
	
	/*
	 * null & empty collections, array
	 * 
	 * (1)null - there is no array object associated with the reference
	 * (2)empty array/list - there is an array/list object, 
	 * 	  but the array/list object does not contain any element yet(length/size == 0).
	 */
	public static void test1() {
		int[] array = null;
		int[] array1 = new int[0];
		
		/*
		ArrayList<Integer> list = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		// They are different
		if (!list.isEmpty()) {
			System.out.println(list.size());
		}
		
		System.out.println("-----------");
		System.out.println(list.get(0));
 		*/
	
	}
	
	/*
	 * check corner cases & boundary conditions
	 */
	
	/*
	 * Almost all of the methods you wrote follow the structure:
	 * initial sanity check for null/empty/length/size
	 * some logic need to be done in a for/while loop
	 * for each step in the loop, what condition we need to maintain, for example,array index range,
	 * if node.next is called, node can not be null
	 * etc...
	 * when the loop will stop
	 * after exiting the loop, if there is any postprocess needed.
	 * 
	 */
	public static int firstOccurance(int[] a, int target) {
		// 1 sanity check
		if (a == null || a.length == 0) {
			return -1;
		}
		if (target < a[0] || target > a[a.length - 1]) {
			return -1;
		}
		int left = 0;
		int right = a.length - 1;
		// 2 enter the main logic (while/for loop)
		// for each step, what we guarantee is left < right - 1
		// when existing the loop, left = right - 1 || left == right (if there is only 1 element)
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if (a[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}	
		// 3. Jump out of the while loop, post process
		// we need to cover the cases of left == right || left == right - 1
		if (a[left] == target) {
			return left;
		}
		if (a[right] == target) {
			return right;
		}
		return -1;	
	}
	/*
	 * another thing to keep in mind is that any index can not be out of range(< 0 || >= array.length)
	 */
	/*
	 * array[] = {1,1,2,3,3,3};  =>  {1,2,3,x,x,x} return 3
	 *  [0..i)  no duplicate
	 *  
	 */
	public static void test2() {
		int[] a = { 1, 1, 2, 3, 3, 3 };
		int rev = deduplicate(a);
		System.out.println("rev = " + rev);
	}

	public static int deduplicate(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int start = 0;
		for (int i = 1; i < a.length; ++i) {
			if (a[i] != a[i -1]) {
				a[++start] = a[i];
			} 
		}
		for(int i = 0; i < a.length; i ++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		// 3 postprocess
		return start + 1;
	}
	
	
	/*
	 * linked list
	 * 
	 * 1->2->3->4->null
	 * 1->2->3-null
	 */
	public static void test3() {
		
	}
	public static ListNode middle(ListNode head) {
		// 1 
		if (head == null || head.next == null) {
			return head;
		}
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast != null && fast.next != null&& fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	
	/*
	 * dummy node
	 * 
	 * -- the head could be changed when solving the problem
	 * -- not sure yet which node will be head when constructing the list
	 */
	
	
	
	
	
	/*
	 * Queue & Stack & Deque
	 */
	public static void test4() {
		
	}
}


// using linked list to implement linkedlist
class Stack{
	private ListNode head;
	public Stack() {
		head = null;
	}
	
	public boolean push(int ele) {
		ListNode node = new ListNode(ele);
		node.next = head;
		head = node;
		return true;
	}
	
	public Integer pop() {
		if (head == null) {
			// if stack is null, return null;
			return null;
		}
		ListNode node = head;
		head = head.next;
		return node.value;
	}
	
	public Integer top() {
		if (head == null) {
			return null;
		}
		return head.value;
	}
};

class Queue{
	private ListNode head;
	private ListNode tail;
	
	
};

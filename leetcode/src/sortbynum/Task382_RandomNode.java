package sortbynum;

import java.util.Random;

import ds.ListNode;

public class Task382_RandomNode {

	/**
	 * http://www.cnblogs.com/grandyang/p/5759926.html
	 * 
	 * sine the length of the linked list is unknown, we can use the reservior sampling. 
	 * 
	 * keep a reservior whose size is 1. 
	 * scan starts from cur.next
	 * every time, generate a random from [0..i], if random == i, update the result in reservior. 
	 * after traversal the linked list. (cur.next is null)
	 * return the result. 
	 * 
	 */
	ListNode head;
	Random random;

	public Task382_RandomNode(ListNode head) {
		this.head = head;
	}

	public int getRandom() {

		ListNode cur = head;
		int res = cur.val;
		
		for (int i = 1; cur.next != null; i++) {
			cur = cur.next;
			// if the random generated between [0..i] is i
			// update the res to cur.val
			if (randInt(0, i) == i){
				res = cur.val;
			}
			
				
		}
		return res;
	}

	// generate the [min, max]
	private int randInt(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

}

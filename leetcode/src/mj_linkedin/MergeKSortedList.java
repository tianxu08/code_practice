package mj_linkedin;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import ds.ListNode;

public class MergeKSortedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * basic merge 2 sorted list
	 */

	/*
	 * merge k sorted list method 1
	 */

	public static ListNode mergeKSortedList_minHeap(List<ListNode> lists) {
		// check
		if (lists == null || lists.size() == 0) {
			return null;
		}
		int k = lists.size();
		// comparator for minHeap
		Comparator<ListNode> minComp = new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				if (o1.val == o2.val) {
					return 0;
				}
				return o1.val < o2.val ? -1 : 1;
			}
		};

		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;

		// create minHeap
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(k,
				minComp);

		// add the head of list in lists to minHeap
		for (ListNode node : lists) {
			minHeap.offer(node);
		}

		// add node into heap and pop head from heap and link the heap.pop()
		// with tail
		while (!minHeap.isEmpty()) {
			ListNode cur = minHeap.poll();
			tail.next = cur;
			tail = tail.next;
			if (cur.next != null) {
				minHeap.offer(cur.next);
			}
		}

		return dummy.next;
	}

	/*
	 * method 2: binary reduction
	 */
	
	public static ListNode mergeKList_binary_reduction(
			List<ListNode> listOfLists) {
		if (listOfLists == null || listOfLists.size() == 0) {
			return null;
		}
		int left = 0, right = listOfLists.size() - 1;
		return helper(listOfLists, left, right);
	}

	public static ListNode helper(List<ListNode> lists, int left,
			int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			// merge the left side
			ListNode leftSide = helper(lists, left, mid);
			ListNode rightSide = helper(lists, mid + 1, right);
			return merge2Lists(leftSide, rightSide);
		}
		return lists.get(left);
	}

	// return newHead after merged two sorted list.
	public static ListNode merge2Lists(ListNode head1, ListNode head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		ListNode cur1 = head1;
		ListNode cur2 = head2;
		ListNode newHead = null;
		ListNode cur = newHead;
		while (cur1 != null && cur2 != null) {
			if (cur1.val < cur2.val) {
				if (newHead == null) {
					newHead = cur1;
					// update cur
					cur = newHead;
				} else {
					cur.next = cur1;
					// update cur
					cur = cur.next;
				}
				cur1 = cur1.next;
			} else {
				if (newHead == null) {
					newHead = cur2;
					cur = newHead;
				} else {
					cur.next = cur1;
					cur = cur.next;
				}
				cur2 = cur2.next;
			}
		}
		if (cur1 != null) {
			if (newHead == null) {
				newHead = cur1;
			} else {
				cur.next = cur1;
			}
		}
		if (cur2 != null) {
			if (newHead == null) {
				newHead = cur2;
			} else {
				cur.next = cur2;
			}
		}
		return newHead;
	}

	

}

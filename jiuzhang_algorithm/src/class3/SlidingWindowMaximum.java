package class3;

import java.util.*;

public class SlidingWindowMaximum {

	
	/*
	 * Sliding Window Maximum
	 * 
	 * deque<Integer>
	 * 
	 * max -> min of current window
	 * maintain a decreasing order in  deque. 
	 * the nums[deque.peekFirst()] is the max for current sliding window
	 * 
	 * if (deque.peekFirst() <= i - k)  deque.pollFirst()   // out of the window
	 * if nums[i] >= nums[deque.peekLast()]  we have a larger number for the next window, so, deque.pollLast()
	 * 
	 * also refer to refer leetcode 239
	 * 
	 */

	public static void test239() {
		int[] nums = { 1,3,-1,-3,5,3,6,7 };
		int k = 3;
		int[] result = task239_maxSlidingWindow(nums, k);

	}
	
	public static int[] task239_maxSlidingWindow(int[] nums, int k) {
		if (nums == null) {
			return null;
		}
		int n = nums.length;
		int[] result = new int[n - k + 1];
		int index = 0;
		Deque<Integer> deque = new LinkedList<Integer>();
		
		// the first k elements
		for (int i = 0; i < k; i++) {
			// if new element: nums[i] >= nums[deque.peekLast()]
			// pop the deque Last element
			while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
				deque.pollLast();
			}
			deque.offerLast(i);
		}
		// k .. n - 1
		for (int i = k; i < n; i++) {
			// get the current window max
			result[index] = nums[deque.peekFirst()];
			index++;

			// for remover element if the deque.peekFirst is smaller than or
			// equal to i - k, out of window
			while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.pollFirst();
			}
			
			// remove useless element's index
			while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
				deque.pollLast();
			}

			deque.offerLast(i);
		}
		// !!! don't forget the last element
		result[index++] = nums[deque.peekFirst()];

		return result;
	}

}

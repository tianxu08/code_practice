package class3;

import java.util.ArrayList;

public class SlidingWindowMedian {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * Given an array of n integer, and a moving window(size k), 
	 * move the window at each iteration from the start of the array, 
	 * find the median of the element inside the window at each moving. 
	 * (If there are even numbers in the array, 
	 * return the N/2-th number after sorting the element in the window. )
	 * 
	 * For array [1,2,7,8,5], moving window size k = 3. return [2,7,7]
	 * At first the window is at the start of the array like this
	 * [ | 1,2,7 | ,8,5] , return the median 2;
	 * then the window move one step forward.
	 * [1, | 2,7,8 | ,5], return the median 7;
	 * then the window move one step forward again.
	 * [1,2, | 7,8,5 | ], return the median 7;
	 * 
	 * follow up question from median of data stream. 
	 * we need to delete the any element from the heap. so use hashHeap
	 */
	public static void test() {
		int[] nums = {1,2,9,4,5,6,7};
		int k = 3;
		ArrayList<Integer> result = medianSlidingWindow(nums, k);
		System.out.println(result);
	}

	public static ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0 || k == 0) {
			return result;
		}
		

		HashHeap maxHeap = new HashHeap("max");
		HashHeap minHeap = new HashHeap("min");

		// maxHeap.size == minHeap.size or maxHeap.size = minHeap.size + 1
		int median = nums[0];
		for (int i = 0; i < nums.length; i++) {
			// add current into heap
			if (i != 0) {
				if (nums[i] > median) {
					minHeap.offer(nums[i]);
				} else {
					maxHeap.offer(nums[i]);
				}
			}

			if (i >= k) {
				System.out.println(" ------  i = " + i);
				if (nums[i - k] == median) {
					// throw away the median, update it from
					if (maxHeap.size() > 0) {
						median = maxHeap.poll();
					} else if (minHeap.size() > 0) {
						median = minHeap.poll();
					}
				} else if (median < nums[i - k]) {
					// nums[i - k ] != median && median < nums[i - k]
					// nums[i - k] is in the minHeap. 
					minHeap.pollElement(nums[i - k]);	
				} else {
					// nums[i - k] is in the MaxHeap
					maxHeap.pollElement(nums[i - k]);
				}
			}

			
			// maintain maxHeap.size == minHeap.size or maxHeap.size = minHeap.size + 1
			// shift element from maxHeap to minHeap until maxSize == minSize
			while (maxHeap.size() > minHeap.size()) {
				minHeap.offer(median);
				median = maxHeap.poll();
			}

			// minSize > 1 + maxSize
			// shift element from minHeap to maxHeap until maxSize + 1 = minSize 
			while (maxHeap.size() + 1 < minHeap.size()) {
				maxHeap.offer(median);
				median = minHeap.poll();
			}
			
			if (i + 1 >= k) {
				result.add(median);
			}
			
			// this is for debug
		
			if (i + 1>= k) {
				System.out.println("***************");
				System.out.println("nums[i] " + nums[i]);
				if (i >= k) 
					System.out.println("nums[i - k] " + nums[i - k]);
				
				System.out.println("----------------");
				System.out.println("info for maxHeap");
				maxHeap.printHeap();
				maxHeap.printHash();
				System.out.println("maxHash.size = " + maxHeap.map.size());
				System.out.println("maxHeap.size= " + maxHeap.size());
				
				System.out.println("================");
				System.out.println("median = " + median);
				System.out.println("================");
				
				System.out.println("info for minHeap");
				minHeap.printHeap();
				minHeap.printHash();
				System.out.println("minHeap.size = " + minHeap.size());
				System.out.println("----------------");
			}
			
			
			
			

		}
		return result;

	}
	

}

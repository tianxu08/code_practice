package lai_online;

import java.util.*;

import debug.Debug;
import ds.TreeNode;

public class Class05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public List<List<Integer>> layerByLayer(TreeNode root) {
		// Write your solution here.

		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		helper(result, root, 0);

		return result;
	}

	public void helper(ArrayList<List<Integer>> result, TreeNode node, int level) {
		// do a preorder traversal
		if (node == null) {
			return;
		}
		List<Integer> line = null;
		if (result.size() <= level) {
			line = new ArrayList<Integer>();
			result.add(line);
		} else {
			line = result.get(level);
		}
		line.add(node.val);

		helper(result, node.left, level + 1);
		helper(result, node.right, level + 1);

	}

	public static void test() {
		int[] array = { 3, 1, 5, 2, 4 };
		int[] result = kSmallest(array, 5);

		Debug.printArray(result);

	}

	// method1: using priority queue. maxHeap size is k
	// time: n log k
	public static int[] kSmallest(int[] array, int k) {
		if (array == null || array.length == 0) {
			return array;
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						if (o1 > o2) {
							return -1;
						} else if (o1 < o2) {
							return 1;
						} else {
							return 0;
						}
					}

				});

		for (int i = 0; i < k; i++) {
			maxHeap.offer(array[i]);
		}

		System.out.println(maxHeap.peek());

		for (int i = k; i < array.length; i++) {
			if (array[i] >= maxHeap.peek()) {
				continue;
			} else {
				maxHeap.poll();
				maxHeap.add(array[i]);
			}
		}

		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = maxHeap.poll();
		}
		return result;
	}

	public static int[] kSmallest2(int[] array, int k) {
		if (array == null) {
			return null;
		}
		if (array.length == 0 || k == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						if (o1.equals(o2)) {
							return 0;
						}
						return o1 > o2 ? -1 : 1;
					}
				});

		for (int i = 0; i < array.length; i++) {
			if (i < k) {
				// offer the first k elements into max heap.
				maxHeap.offer(array[i]);
			} else if (array[i] < maxHeap.peek()) {
				// for the other elements, only offer it into the max heap if it
				// is
				// smaller than the max value in the max heap.
				maxHeap.poll();
				maxHeap.offer(array[i]);
			}
		}
		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = maxHeap.poll();
		}
		return result;
	}
	
	
	// method2
	// use the idea quick sort
	public static int[] kSmallest_method2 (int[] array, int k) {
		return null;
	}
	
	public static int quickSelect(int[] array, int start, int end, int target) {
		return -1;
	}
}

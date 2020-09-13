package onceagain;

import java.util.*;

import ds.GraphNode;
import ds.TreeNode;

public class Class5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2_1();
	}
	
	/*
	 * task1
	 * K Smallest in Unsorted Array
	 * 
	 * 1 max Heap 
	 *    size k. time: O(n - k) * log k + O(k * log k) = O(n * log k)   
	 * 2 min Heap  O(n * log n + k * log n)
	 * 3 quick partition O(n)
	 */
	public static int[] task1_kSmallest_maxHeap(int[] array, int k) {
		// check
		if (array == null || array.length == 0) {
			return null;
		}
		if (array.length <= k) {
			return array;
		}
		int[] result = new int[k];
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if (o1 == o2) {
					return 0;
				}
				return o1 > 02 ? -1 : 1;
			}	
		});
		
		for(int i = 0; i < array.length; i ++) {
			if (i < k) {
				maxHeap.offer(array[i]);
			} else {
				if (array[i] < maxHeap.peek()) {
					maxHeap.poll();
					maxHeap.offer(array[i]);
				}
			}
			
		}
		
		
		for(int i = k - 1; i >= 0; i --) {
			result[i] = maxHeap.poll();
		}
		return result;
	}
	
	public static void test1() {
		int[] array = {1,9,3,4,5};
//		int[] result = task1_kSmallest_maxHeap(array, 2);
		int[] result = task1_kSmallest_partition(array, 2);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] task1_kSmallest_minHeap(int[] array, int k) {
		if (array == null || array.length == 0) {
			return null;
		}
		if (array.length <= k) {
			return array;
		}
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		for(int i = 0; i < array.length; i++) {
			minHeap.offer(array[i]);
		}
		int[] result = new int[k];
		for(int i = 0; i < k; i++) {
			result[i] = minHeap.poll();
		}
		return result;
	}
	
	
	public static int[] task1_kSmallest_partition(int[] array, int k) {
		if (array == null || array.length == 0) {
			return null;
		}
		if (array.length <= k) {
			return array;
		}
		
		quickSelect(array, 0, array.length - 1, k - 1);
		int[] result = Arrays.copyOf(array, k);
		return result;
		
	}
	public static void quickSelect(int[] array, int left, int right, int targetIdx) {
		int pivotIdx = task1_partition(array, left, right);
		if (pivotIdx == targetIdx) {
			return ;
		} else if (pivotIdx < targetIdx) {
			quickSelect(array, pivotIdx + 1, right, targetIdx);
		} else {
			// pivotIdx > targetIdx
			quickSelect(array, left, pivotIdx - 1, targetIdx);
		}
	}
	
	// partition according the pivot. and return the index of pivot
	public static int task1_partition(int[] array, int left, int right) {
		int pivot = array[right];
		int start = left;
		int end = right - 1;
		while(start <= end) {
			if (array[start] < pivot) {
				start ++;
			} else if (array[end] >= pivot) {
				end --;
			} else {
				swap(array, start, end);
				start ++;
				end --;
			}
		}
		swap(array, start, right);
		return start;
	}
	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	
	/*
	 * task2
	 * get keys in binary tree layer by layer
	 */
	public static void task2_levelOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i ++) {
				TreeNode cur = queue.poll();
				System.out.print(cur.val + " ");
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			System.out.println();
		}
	}
	
	/*
	 * task2.1 
	 * get keys in binary tree in zigzag order
	 * use a deque and a flag to control from left to right and right to left
	 */
	public static void task2_1_ZigzagOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		Deque<TreeNode> deque = new LinkedList<TreeNode>();
		deque.offerLast(root);
		boolean flag = true;
		while(!deque.isEmpty()) {
			int size = deque.size();
			if (flag) { // flag = true. from left to right
				for(int i = 0; i < size; i ++) {
					TreeNode cur = deque.pollFirst();
					System.out.print(cur.val + " ");
					if (cur.left != null) {
						deque.offerLast(cur.left);
					} 
					if (cur.right != null) {
						deque.offerLast(cur.right);
					}
				}
			} else {
				for(int i = 0; i < size; i ++) {
					TreeNode cur = deque.pollLast();
					System.out.print(cur.val + " ");
					if (cur.right != null) {
						deque.offerFirst(cur.right);
					}
					if (cur.left != null) {
						deque.offerFirst(cur.left);
					}
				}
			}
			System.out.print(flag + "   ---------  ");
			System.out.println();
			flag = !flag;
			
		}
	} 
	
	
	public static void test2_1() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		
		task2_1_ZigzagOrder(n1);
		 
	}
	

	/*
	 * task3 Bipartite
	 */
	public static boolean task3_isBipartite(List<GraphNode> graph) {
		if (graph.isEmpty()) {
			return false;
		}
		Map<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
		for(GraphNode node: graph) {
			if (!task3_bfsAndMark(node, map)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean task3_bfsAndMark(GraphNode node, Map<GraphNode, Integer> map) {
		if (map.containsKey(node)) {
			return true;
		}
		LinkedList<GraphNode> q = new LinkedList<GraphNode>();
		q.offer(node);
		map.put(node, 0);
		// do BFS
		while(!q.isEmpty()) {
			GraphNode cur = q.poll();
			int curSign = map.get(cur);
			int neiSign = curSign == 0 ? 1 : 0;
			List<GraphNode> neighbors = cur.neighbors;
			for(GraphNode nei: neighbors) {
				if (!map.containsKey(nei)) {
					map.put(nei, neiSign);
					q.offer(nei);
				} else {
					// contains
					if (neiSign != map.get(nei)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/*
	 * task4 check if binary tree is complete or NOT
	 */
	
	/*
	 * task5 Undirected Graph has cycle
	 */
	
	/*
	 * taks6
	 * Kth Smallest Number In Sorted Matrix
	 */
	
	
}

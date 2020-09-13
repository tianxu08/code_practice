package review;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import ds.GraphNode;
import ds.ListNode;
import ds.TreeNode;

public class Ch21_advanced2 {
	
	/*
	 * t1 deep copy of list with random pointer
	 * t2 deep copy of a tree
	 * t3 deep copy of Graph(with possible cycle)
	 * 
	 * 
	 * t4 merge K sorted Arrays
	 * t5 merge K sorted Lists
	 * 
	 * t6 Binary Search Tree Closest to Target
	 * t7 Binary Search Tree Largest Number smaller than Target
	 * 
	 */
	
	public static class RandomListNode{
		public  int value;
		public RandomListNode next;
		public RandomListNode random;
		public RandomListNode(int _v) {
			this.value = _v;
		}
	}
	
	public static RandomListNode t1_deep_copy_list_with_ramdom(RandomListNode head) {
		if (head == null) {
			return null;
		}
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode tail = dummy;
		RandomListNode ptr = head;
		
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		while(ptr != null) {
			if (!map.containsKey(ptr)) {
				map.put(ptr, new RandomListNode(ptr.value));
			}
			// link the next pointer
			tail.next = map.get(ptr);
			
			// link the random pointer
			if (ptr.random != null) {
				if (!map.containsKey(ptr.random)) {
					map.put(ptr.random, new RandomListNode(ptr.random.value));
				}
				tail.next.random = map.get(ptr.random);
			}
			
			// update head to head.next
			ptr = ptr.next;
			// update cur
			tail = tail.next;
		}
		return dummy.next;
	}
	
	public static List<GraphNode> t3_deep_copy_graph(List<GraphNode> graph) {
		if (graph == null) {
			return null;
		}
		HashMap<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
		for(GraphNode node: graph) {
			if (!map.containsKey(node)) {
				map.put(node, new GraphNode(node.key));
				t3_dfs(node, map);
			}
		}
		return new ArrayList<GraphNode>(map.values());
	}
	
	public static void t3_dfs(GraphNode seed, HashMap<GraphNode, GraphNode> map) {
		GraphNode copy = map.get(seed);
		for(GraphNode nei : seed.neighbors) {
			if (!map.containsKey(nei)) {
				map.put(nei, new GraphNode(nei.key));
				t3_dfs(nei, map);
			}
			copy.neighbors.add(map.get(nei));
		}
	}
	
	/**
	 * merge K sorted arrays
	 * @param arrayOfArrays
	 * @return a sorted array
	 * assume: arrayOfArrays is NOT null. 
	 * This method use minHeap. 
	 * Time: O(n * log n) 
	 */
	public static int[] t4_merge_k_sorted_arrays(int[][] arrayOfArrays) {
		int r_num = arrayOfArrays.length;
		
		PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(r_num, new Comparator<Entry>() {

			@Override
			public int compare(Entry o1, Entry o2) {
				// TODO Auto-generated method stub
				if (o1.val == o2.val) {
					return 0;
				}
				return o1.val < o2.val ? -1 : 1;
			}
		});
		int new_len = 0;
		// put the first column of every row into minHeap
		for(int i = 0; i < r_num; i ++) {
			int[] array = arrayOfArrays[i];
			new_len += array.length;
			if (array != null && array.length != 0) {
				Entry entry = new Entry(i, 0, arrayOfArrays[i][0]);
				minHeap.offer(entry);
			}
		}
		int new_idx = 0;
		int[] result = new int[new_len];
		while(!minHeap.isEmpty()) {
			Entry cur = minHeap.poll();
			result[new_idx++] = cur.val;
			
			// get cur's next element in the same array
			if (cur.y + 1 < arrayOfArrays[cur.x].length) {
				Entry next_entry = new Entry(cur.x, cur.y + 1, arrayOfArrays[cur.x][cur.y + 1]);
				minHeap.offer(next_entry);
			}
		}
		return result;
	}
	
	public static class Entry{
		int x;
		int y;
		int val;
		public Entry(int _x, int _y, int _val) {
			this.x = _x;
			this.y = _y;
			this.val = _val;
		}
	}
	
	
	/**
	 * 
	 * @param listOfLists
	 * @return sorted list
	 * 
	 */
	public static ListNode t5_merge_k_sorted_list(List<ListNode> listOfLists) {
		if (listOfLists == null || listOfLists.size() == 0) {
			return null;
		}
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		int list_size = listOfLists.size();
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(list_size, new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				if (o1.value == o2.value) {
					return 0;
				}
				return o1.value < o2.value ? -1 : 1;
			}
		});
		
		// add the head of every list into minHeap
		for(ListNode node : listOfLists) {
			if (node != null) {
				minHeap.offer(node);
			}
		}
		
		while(!minHeap.isEmpty()) {
			ListNode cur = minHeap.poll();
			tail.next = cur;
			// update tail
			tail = tail.next;
			if (cur.next != null) {
				minHeap.offer(cur.next);
			}
		}
		
		return dummy.next;
	}
	
	
	public static TreeNode t6_bst_closest_to_target(TreeNode root, int target){
		if (root == null) {
			return null;
		}
		TreeNode result = root;
		while(root != null) {
			if (root.val == target) {
				return root;
			} else {
				if (Math.abs(root.val - target) < Math.abs(result.val - target)) {
					result = root;
				}
				
				if (root.val < target) {
					root = root.right;
				} else {
					root = root.left;
				}
			}
		}
		return result;
	}
	
	public static TreeNode t7_largest_smaller(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		TreeNode result = null;
		while(root != null) {
			if (root.val >= target) {
				// root and its right subtree's value are greater than target, so ignore the right subtree
				root = root.left;
			} else {
				// root.val < target
				// serach the left subtree and find the largest
				result = root;
				// try to find a larger candidate, so go right
				root = root.right;
			}
		}
		
		return result;
	}
	
	
	
	
	
	
	

}

package lai_online2;
import ds_lai_online2.*;
import java.util.*;
public class Class21 {
	/*
	 * task1
	Merge Stones
	Hard
	DP
	We have a list of piles of stones, each pile of stones has a certain weight, represented by an array of integers. In each move, we can merge two adjacent piles into one larger pile, the cost is the sum of the weights of the two piles. We merge the piles of stones until we have only one pile left. Determine the minimum total cost.

	Assumptions

	stones is not null and is length of at least 1
	Examples

	{4, 3, 3, 4}, the minimum cost is 28

	merge first 4 and first 3, cost 7

	merge second 3 and second 4, cost 7

	merge two 7s, cost 14

	total cost = 7 + 7 + 14 = 28
	*/
	
	public static int task1_minCost(int[] stones) {
		// write your solution here
		if (stones == null || stones.length == 1) {
			return 0;
		}
		int n = stones.length;
		int[][] M = new int[n][n];
		int[] preSum = new int[n];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				preSum[i] = stones[i];
			} else {
				preSum[i] = preSum[i - 1] + stones[i];
			}
		}

		// initialize
		for (int i = 0; i < n; i++) {
			M[i][i] = 0;
		}

		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1; // i + len - 1 <= n - 1 i <= n - len
				int curMin = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					curMin = Math.min(curMin,
							M[i][k] + M[k + 1][j] + getRange(preSum, i, j));
				}
				M[i][j] = curMin;
			}
		}

		return M[0][n - 1];

	}

	public static int getRange(int[] preSum, int i, int j) {
		if (i == 0) {
			return preSum[j];
		} else {
			return preSum[j] - preSum[i - 1];
		}
	}
	
	/*
	 * task2
	Cutting Wood I
	Hard
	DP
	There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.

	Examples

	L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
	*/
	public int task2_minCost(int[] cuts, int length) {
		// write your solution here
		if (length <= 1) {
			return 0;
		}

		int[] cut = new int[cuts.length + 2];
		for (int i = 0, j = 0; i < cut.length; i++) {
			if (i == 0) {
				cut[i] = 0;
			} else if (i == cut.length - 1) {
				cut[i] = length;
			} else {
				cut[i] = cuts[j];
				j++;
			}
		}
		// for debug

		int n = cut.length;
		int[][] M = new int[n][n];
		// initialize
		for (int i = 0; i < n - 1; i++) {
			M[i][i + 1] = 0;
		}

		for (int i = n - 3; i >= 0; i--) {
			for (int j = i + 2; j < n; j++) {
				int curMin = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; k++) {
					curMin = Math.min(curMin, M[i][k] + M[k][j]);
				}
				M[i][j] = cut[j] - cut[i] + curMin;
			}
		}

		return M[0][n - 1];
	}
	
	/*
	 * task3
	Delete In Binary Search Tree
	Fair
	Data Structure
	Delete the target key K in the given binary search tree if the binary search tree contains K. Return the root of the binary search tree.

	Find your own way to delete the node from the binary search tree, after deletion the binary search tree's property should be maintained.

	Assumptions

	There are no duplicate keys in the binary search tree
	*/
	public TreeNode task3_delete(TreeNode root, int key) {
		// Write your solution here.
		return root;
	}
	
	/*
	 * task4
	Deep Copy Linked List With Random Pointer
	Fair
	Data Structure
	Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. Make a deep copy of the original list.
	*/
	public RandomListNode task4_copy(RandomListNode head) {
		// write your solution here
		if (head == null) {
			return null;
		}
		RandomListNode cur = head;
		// create the copy node and assert it after cur
		while (cur != null) {
			RandomListNode curCopy = new RandomListNode(cur.value);
			curCopy.random = cur.random;

			curCopy.next = cur.next;
			cur.next = curCopy;

			cur = cur.next.next;
		}

		// update random pointer
		cur = head;
		RandomListNode newHead = head.next;
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}

		// split the list
		cur = head;
		while (cur != null) {
			RandomListNode temp = cur.next;
			cur.next = temp.next;
			cur = cur.next;

			if (temp.next != null) {
				temp.next = temp.next.next;
			}
		}

		return newHead;
	}
	
	/*
	 * task5
	Merge K Sorted Array
	Fair
	Data Structure
	Merge K sorted array into one big sorted array in ascending order.

	Assumptions

	The input arrayOfArrays is not null, none of the arrays is null either.
	*/
	public class HeapElement {
		public int val;
		public int index;
		public int pos;

		public HeapElement(int v, int i, int pos) {
			this.val = v;
			this.index = i;
			this.pos = pos;
		}
	}

	public int[] task5_merge(int[][] arrayOfArrays) {
		// write your solution here
		if (arrayOfArrays == null || arrayOfArrays.length == 0) {
			return null;
		}
		int rowLen = arrayOfArrays.length;
		ArrayList<Integer> rev = new ArrayList<Integer>();
		// create a minHeap
		Comparator<HeapElement> myComp = new Comparator<HeapElement>() {

			@Override
			public int compare(HeapElement o1, HeapElement o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		// create a minHeap
		PriorityQueue<HeapElement> q = new PriorityQueue<HeapElement>(rowLen,
				myComp);

		// add the the first element of all rows into the priority queue
		for (int i = 0; i < rowLen; i++) {
			if (arrayOfArrays[i] != null && arrayOfArrays[i].length > 0) {
				HeapElement element = new HeapElement(arrayOfArrays[i][0], i, 0);
				q.offer(element);
			}
		}

		while (!q.isEmpty()) {
			HeapElement cur = q.poll();
			rev.add(cur.val);

			if (arrayOfArrays[cur.index] != null
					&& cur.pos < arrayOfArrays[cur.index].length - 1) {
				HeapElement elem = new HeapElement(
						arrayOfArrays[cur.index][cur.pos + 1], cur.index,
						cur.pos + 1);
				q.add(elem);
			}
		}

		int[] result = new int[rev.size()];
		for (int i = 0; i < rev.size(); i++) {
			result[i] = rev.get(i);
		}
		return result;
	}
	
	/*
	 * task6
	Merge K Sorted Lists
	Fair
	Data Structure
	Merge K sorted lists into one big sorted list in ascending order.

	Assumptions

	None of the lists is null.
	*/
	public ListNode task6_merge(List<ListNode> lists) {
		// write your solution here
		if (lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();

		// minHeap
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(size,
				new Comparator<ListNode>() {

					@Override
					public int compare(ListNode o1, ListNode o2) {
						// TODO Auto-generated method stub
						return o1.value - o2.value;
					}

				});

		// add all head into the priority queue
		for (ListNode node : lists) {
			if (node != null) {
				q.add(node);
			}
		}

		ListNode newHead = null;
		ListNode tail = newHead;

		while (!q.isEmpty()) {
			ListNode cur = q.poll();
			if (newHead == null) {
				newHead = cur;
				tail = newHead;
			} else {
				tail.next = cur;
				tail = tail.next;
			}

			if (cur.next != null) {
				q.offer(cur.next);
			}
		}

		return newHead;

	}
	
	/*
	 * task7
	Closest Number In Binary Search Tree
	Fair
	Data Structure
	In a binary search tree, find the node containing the closest number to the given target number.

	Assumptions:

	The given root is not null.
	There are no duplicate keys in the binary search tree.
	Examples:

	    5

	  /    \

	2      11

	     /    \

	    6     14

	closest number to 4 is 5

	closest number to 10 is 11

	closest number to 6 is 6

	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	  */
	
	public int task7_closest(TreeNode root, int target) {
		// Write your solution here.
		task6_helper(root, target);

		return closest;

	}

	public int closest = Integer.MAX_VALUE;

	public void task6_helper(TreeNode node, int target) {
		if (node == null) {
			return;
		}
		System.out.println("closest = " + closest);
		if (node.key == target) {
			closest = node.key;
			return;
		}
		if (Math.abs(node.key - target) < Math.abs(closest - target)) {
			closest = node.key;
		}
		if (node.key < target) {
			task6_helper(node.right, target);
		} else {
			task6_helper(node.left, target);
		}
	}
	
	/*
	 * task8
	Largest Number Smaller In Binary Search Tree
	Fair
	Data Structure
	In a binary search tree, find the node containing the largest number smaller than the given target number.

	If there is no such number, return INT_MIN.

	Assumptions:

	The given root is not null.
	There are no duplicate keys in the binary search tree.
	Examples

	    5

	  /    \

	2      11

	     /    \

	    6     14

	largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)

	largest number smaller than 10 is 6

	largest number smaller than 6 is 5

	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	 */
	public int task8_largestSmaller(TreeNode root, int target) {
		// Write your solution here.
		task8_helper(root, target);
		return largestSmaller;
	}

	public int largestSmaller = Integer.MIN_VALUE;

	public void task8_helper(TreeNode node, int target) {
		if (node == null) {
			return;
		}
		if (node.key < target
				&& Math.abs(node.key - target) < Math.abs(largestSmaller
						- target)) {
			largestSmaller = node.key;
		}
		if (node.key < target) {
			task8_helper(node.right, target);
		} else {
			task8_helper(node.left, target);
		}
	}
	
	
	/*
	 * task9
	Deep Copy Undirected Graph
	Fair
	Graph
	Make a deep copy of an undirected graph, there could be cycles in the original graph.

	Assumptions

	The given graph is not null
	*/
	
	public List<GraphNode> task9_copy(List<GraphNode> graph) {
		// write your solution here
		if (graph == null) {
			return null;
		}
		// map: map from original node to copyNode. also used to avoid
		// duplicate.
		HashMap<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
		List<GraphNode> copyGraph = new ArrayList<GraphNode>();

		// copy the node
		for (GraphNode node : graph) {
			GraphNode copyNode = new GraphNode(node.key);
			copyGraph.add(copyNode);
			map.put(node, copyNode);
		}

		// clone the graph.
		for (GraphNode node : graph) {
			GraphNode copyNode = map.get(node);
			// traverse the neighbor list of node, then add corresponding node
			// into copyNode's neighbor list.
			for (GraphNode neiNode : node.neighbors) {
				copyNode.neighbors.add(map.get(neiNode));
			}
		}
		return copyGraph;

	}
}

package lec;

import java.util.*;

import debug.Debug;
import ds.ListNode;
import ds.TreeNode;

public class Lec21 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Q1_3_1_test();
//		Q2_1_2test();
//		Q4_test();	
	}
	
	/*
	 * Q1
	 * skiplist/graph copy problem
	 * (1) traversal graph/tree/list.
	 * (2)
	 * 
	 * 
	 * twice traverse
	 * <1> copy the list
	 * <2> link the other 
	 */
	public static class SkipListNode {
		public int value;
		public SkipListNode next;
		public SkipListNode other;
		public SkipListNode(int v) {
			this.value = v;
			this.next = null;
			this.other = null;
		}
	}
	
	public static SkipListNode Q1_1deepCopySkipList(SkipListNode head) {
		if (head == null) {
			return null;
		}
		// use hashMap to look up: 1-1 mapping from old list to new list
		Map<SkipListNode, SkipListNode> map = new HashMap<Lec21.SkipListNode, Lec21.SkipListNode>();
		SkipListNode newHead = new SkipListNode(head.value);
		// put head and newHead into map
		map.put(head, newHead);
 		
		SkipListNode cur = newHead;
		// traverse old list
		while(head != null) {
			// link head's next
			if (head.next != null) {
				if (!map.containsValue(head.next)) {
					// hasn't been copied over due to other pointer
					SkipListNode node =	new SkipListNode(head.next.value);
					map.put(head.next, node);
				}
				cur.next = map.get(head.next);
			}
			// link head.other
			if (head.other != null) {
				if (!map.containsValue(head.other)) {
					SkipListNode node = new SkipListNode(head.other.value);
					map.put(head.other, node);
				}
				cur.other = map.get(head.other);
			}
			// update head and cur
			head = head.next;
			cur = cur.next;
		}
		return newHead;
	}
	
	/*
	 * Q1.2 (LinkedList with random pointer)
	 */
	public static class RandomListNode {
		public int value;
		public RandomListNode next;
		public RandomListNode random;
		public RandomListNode(int v) {
			this.value = v;
			this.next = null;
			this.random = null;
		}
	}
	
	/*
	 * Method 1
	 * using hash map <original node, copy node> 
	 */
	public static RandomListNode Q1_2_1deepCopyListWithRandomPointer(RandomListNode head) {
		if (head == null) {
			return null;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		
		RandomListNode newHead = new RandomListNode(head.value);
		map.put(head, newHead);
		RandomListNode cur = head.next;
		RandomListNode newCur = newHead;
		// create the copy list and link the next pointer
		while(cur != null) {
			RandomListNode newNode = new RandomListNode(cur.value);
			newCur.next = newNode;
			map.put(cur, newNode);
			// update cur and newCur. 
			cur = cur.next;
			newCur = newCur.next;
		}
		
		// link the random pointer
		cur = head;
		while( cur != null) {
			RandomListNode curCopy = map.get(cur);
			curCopy.random = map.get(cur.random);
			cur = cur.next;
		}
		return newHead;
	}
	
	// method 2
	// don't use hashMap
	// in this way, we need to change the structure of linked list node
	// 1 -> 2 -> 3 -> 4 ->5 -> 6 -> 7
	// 
	
	// 
	public static void test1_2() {
		
	}
	public static RandomListNode Q1_2_2DeepCopyListWithRandomPointer(RandomListNode head) {
		// edge case: 
		if (head == null) {
			return null;
		}
		
		RandomListNode cur = head;
		/*
		 * 1 -> 2 -> 3 -> 4 -> 5
		 * 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> 4 -> 4' -> 5 -> 5'
		 */
		while(cur != null) {
			RandomListNode newNode = new RandomListNode(cur.value);
			// !!! Here, we need to copy the random from cur, then, we will modify it.
			newNode.random = cur.random;
			
			// insert newNode after cur. 
			RandomListNode curNext = cur.next;
			cur.next = newNode;
			newNode.next = curNext;
			// update cur. 
			cur = cur.next.next;
		}
		// update the cur.random
		cur = head;
		RandomListNode newHead = cur.next;
		// link the random pointer
		while( cur != null) {
			// 
			if (cur.next.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
		// split the list. 
		cur = head;
		while (cur != null) {
			RandomListNode temp = cur.next;
			cur.next = temp.next;
			
			// update cur
			cur = cur.next;
			
			if (temp.next != null) {
				temp.next = temp.next.next;
			}
		}
		
		return newHead;
	}
	
	
	/*
	 * Q1.3 (Tree)
	 * Clone a Binary Tree with Random Pointers
	 */
	public static class RandomTreeNode {
		public int val;
		public RandomTreeNode left;
		public RandomTreeNode right;
		public RandomTreeNode random;
		public RandomTreeNode(int v) {
			this.val = v;
			this.left = null;
			this.right = null;
			this.random = null;
		}
	}
	
	// method 1
	// use hash map.
	public static RandomTreeNode Q1_3_1DeepCopyBTWithRandomPointer(RandomTreeNode root) {
		if (root == null) {
			return null;
		}
		
		HashMap<RandomTreeNode, RandomTreeNode> map =new HashMap<RandomTreeNode, RandomTreeNode>();
		Q1_3_1GetMap(root, map);
		
		Q1_3_1LinkPointers(root, map);
		
		RandomTreeNode new_head = map.get(root);
		return new_head;
	}
	// do a preOrder method 
	public static void Q1_3_1GetMap(RandomTreeNode node, HashMap<RandomTreeNode, RandomTreeNode> map) {
		if (node == null) {
			return ;
		}
		RandomTreeNode new_node = new RandomTreeNode(node.val);
		map.put(node, new_node);
		Q1_3_1GetMap(node.left, map);
		Q1_3_1GetMap(node.right, map);
	}
	
	public static void Q1_3_1LinkPointers(RandomTreeNode node, HashMap<RandomTreeNode, RandomTreeNode> map) {
		if (node == null) {
			return ;
		}
		RandomTreeNode cur_copy = map.get(node);
		cur_copy.left = map.get(node.left);
		cur_copy.right = map.get(node.right);
		cur_copy.random = map.get(node.random);
		
		Q1_3_1LinkPointers(node.left, map);
		Q1_3_1LinkPointers(node.right, map);
	}
	public static void Q1_3_1_test() {
		RandomTreeNode n1 = new RandomTreeNode(1);
		RandomTreeNode n2 = new RandomTreeNode(2);
		RandomTreeNode n3 = new RandomTreeNode(3);
		RandomTreeNode n4 = new RandomTreeNode(4);
		RandomTreeNode n5 = new RandomTreeNode(5);
		RandomTreeNode n6 = new RandomTreeNode(6);
		RandomTreeNode n7 = new RandomTreeNode(7);
		
		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		n2.random = n3;
		
		n3.left = n6;
		n3.right = n7;
		
		n4.random = n1;
		n5.random = n7;
		
		Q1_3_1PrintInOrder(n1);
		
		System.out.println();
		
		RandomTreeNode newHead = Q1_3_1DeepCopyBTWithRandomPointer(n1);
		Q1_3_1PrintInOrder(newHead);
		
		System.out.println();
		
		RandomTreeNode newHead2 = Q1_3_2DeepCopyBTWithRandomPointer(n1);
		Q1_3_1PrintInOrder(newHead2);
	}
	public static void Q1_3_1PrintInOrder(RandomTreeNode node) {
		if (node == null) {
			return ;
		}
		Q1_3_1PrintInOrder(node.left);
		System.out.print("[");
		System.out.print(node.val + " ");
		if (node.random == null) {
			System.out.print("  Null ");
		} else {
			System.out.print(" " + node.random.val + " ");
		}
		System.out.print("] ");
		Q1_3_1PrintInOrder(node.right);
	}
	
	// method2
	// Temporarily modify the given binary tree
	/*
	 * http://www.geeksforgeeks.org/clone-binary-tree-random-pointers/
	 * 1 create new nodes in a cloned tree and insert each new node in original tree between the left edge
	 *   of corresponding node in the original tree. 
	 *   e.g
	 *   if the current node is A and its left child is B (A --> B). Then the new cloned node with key A
	 *   will be created say cA and it will be put A-->cA-->B  (B can be null or a non-null left child).
	 *   Right child pointer will be set correctly. 
	 *   
	 *   e.g If for current node A, right child is C in original tree. A-->C
	 *   Then corresponding cloned nodes cA and cC will like cA --> cB
	 *   
	 * 2 Set random pointer in cloned tree as per original tree. 
	 *   If A's random pointer points to node B, then in cloned tree, cA will points to cB. 
	 *   
	 * 3 Restore left pointer correctly in both original and cloned tree. 
	 */
	public static RandomTreeNode Q1_3_2DeepCopyBTWithRandomPointer(RandomTreeNode root) {
		if (root == null) {
			return null;
		}
		RandomTreeNode cloneNode = copyLeftRightNode(root);
		copyRandomNode(root, cloneNode);
		restoreTreeLeftNode(root, cloneNode);
		return cloneNode;
	}
	
	// this function create new nodes of cloned tree 
	// and puts the new cloned node between current node and it's left child
	// Return: for every layer, return the now cloned node for current node.
	//         i.e. cur.left  (new copy)
	public static RandomTreeNode copyLeftRightNode(RandomTreeNode node) {
		if (node == null) {
			return null;
		}
		RandomTreeNode oldLeft = node.left; // store the old left. 
		
		// create the new cloned node and link it to node.left
		node.left = new RandomTreeNode(node.val);
		// link the old left. 
		node.left.left = oldLeft;
		// go left. and link the new cloned node of oldLeft 
		if (oldLeft != null) {
			oldLeft.left = copyLeftRightNode(oldLeft);
		}
		node.left.right = copyLeftRightNode(node.right);
		
		// return the node.left
		return node.left;
	}
	
	public static void copyRandomNode(RandomTreeNode node, RandomTreeNode cloneNode) {
		if (node == null) {
			return ;
		}
		if (node.random != null) {
			cloneNode.random = node.random.left;
		} else {
			cloneNode.random = null;
		}
		if (node.left != null && cloneNode.left != null) {
			// we need to make sure that node.left and cloneNode.left are NOT null
			copyRandomNode(node.left.left, cloneNode.left.left);
		}
		copyRandomNode(node.right, cloneNode.right);
	}
	
	public static void restoreTreeLeftNode(RandomTreeNode node, RandomTreeNode cloneNode) {
		if (node == null) {
			return ;
		}
		if (cloneNode.left != null) {
			RandomTreeNode cloneLeft = cloneNode.left.left;
			node.left = node.left.left;
			cloneNode.left = cloneLeft;
		} else {
			node.left = null;
		}
		restoreTreeLeftNode(node.left, cloneNode.left);
		restoreTreeLeftNode(node.right, cloneNode.right);
	}
	
	
	
	
	/*
	 * Q1.4: (Graph)​How to copy a graph with possible cycles? G­> G’
	 * Method: build a hash_map to avoid duplication when copying a node.
	 * Make a deep copy of an undirected graph, there could be cycles in the original graph.
	 * Assumptions
	 * The given graph is not null
	 * 
	 * this has to use hash map
	 */
	public static class GraphNode {
		public int key;
		public List<GraphNode> neighbors;
		public GraphNode(int key) {
			this.key = key;
			this.neighbors = new ArrayList<GraphNode>();
		}
	}
	
	// this function return a copy graph. 
	// Input: List<GraphNode> (since we cannot guarantee the graph is connected.)
	// Method:
	// (1) use a hashMap to map original node to copyNode.
	// (2) copy the node
	
	public static List<GraphNode> Q1_4DeepCopyGraph(List<GraphNode> graph) {
		if (graph == null) {
			return null;
		}
		// map: map from original node to copyNode. also used to avoid duplicate. 
		HashMap<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
		List<GraphNode> copyGraph = new ArrayList<GraphNode>();
	
		// copy the node
		for(GraphNode node: graph) {
			GraphNode copyNode = new GraphNode(node.key);
			copyGraph.add(copyNode);
			map.put(node, copyNode);
		}
		
		// clone the graph. 
		for(GraphNode node: graph) {
			GraphNode copyNode = map.get(node);
			// traverse the neighbor list of node, then add corresponding node into copyNode's neighbor list. 
			for(GraphNode neiNode : node.neighbors) {
				copyNode.neighbors.add(map.get(neiNode));
			}
		}
		return copyGraph;
	}
		
	
	
	
	
	
	
	
	/*
	 * Q2 k-way merge problem
	 * Q2.1 How to merge k sorted short array​into one long sorted array?
	 * 
	 * M1: binary- reduction
	 *     k arrays, every array has n elements. 
	 *     Time: O(nk * log(k))
	 * 
	 * M2: minHeap
	 * 	   Time: O(nk * log(k))  
	 * HeapElement {
	 * 	 double value;
	 *   int index;  // for kth array
	 *   int pos;    // position at original array. 
	 * }  
	 */
	
	public static int[] Q2_1_1MergeKSortedArray(int[][] arrayOfArrays) {
		int size = arrayOfArrays.length;
		return null;
	} 
	
	public static int[] Q2_1_1Helper(int[][] arrayOfArrays, int left, int right) {
		return null;
	}
	
	public static void Q2_1_2test() {
		int[][] matrix = {
				{},
				{1,5,7},
				{4},
				{2,3,5,11},
				{2,4,4,6,8}
		};
		int[] merge = Q2_1_2MergeKSortedArray(matrix);
		for(int i = 0; i < merge.length ; i ++) {
			System.out.print(merge[i] + " ");
		}
		System.out.println();
	}
	
	public static class HeapElement {
		public int val;
		public int index;
		public int pos;
		public  HeapElement(int v, int i, int pos) {
			this.val = v;
			this.index = i;
			this.pos = pos;
		}
	}
	
	public static int[] Q2_1_2MergeKSortedArray(int[][] arrayOfArrays) {
		if (arrayOfArrays == null || arrayOfArrays.length == 0 ) {
			return null;
		}
		int rowLen = arrayOfArrays.length;
		ArrayList<Integer> rev = new ArrayList<Integer>();
		Comparator<HeapElement> myComp = new Comparator<HeapElement>() {
			
			@Override
			public int compare(HeapElement o1, HeapElement o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		// create a minHeap
		PriorityQueue<HeapElement> q = new PriorityQueue<HeapElement>(rowLen, myComp);
		
		// add the the first element of all rows into the priority queue
		for(int i = 0; i < rowLen; i ++) {
			// !!! Note, we need to check whether the array.length > 0
			if (arrayOfArrays[i] != null && arrayOfArrays[i].length > 0) {
				HeapElement element = new HeapElement(arrayOfArrays[i][0], i, 0);
				q.offer(element);
			}
		}
		
		while(!q.isEmpty()) {
			HeapElement cur = q.poll();
			rev.add(cur.val);
			
			if (arrayOfArrays[cur.index] != null && cur.pos < arrayOfArrays[cur.index].length - 1) {
				HeapElement elem = new HeapElement(arrayOfArrays[cur.index][cur.pos + 1], cur.index, cur.pos + 1);
				q.add(elem);
			}
		}
		
		int[] result = new int[rev.size()];
		for(int i = 0; i < rev.size(); i ++) {
			result[i] = rev.get(i);
		}
		return result;
		
	}
	
	
	/*
	 * Q2.2 How to merge k sorted LinkedList into one big Linked List. 
	 * more simple. 
	 */
	// method 1: binary reduction
	public static ListNode Q2_2_1MergeKList(List<ListNode> listOfLists) {
		if(listOfLists == null || listOfLists.size() == 0) {
			return null;
		}
		int left = 0, right = listOfLists.size() - 1;
		return helper(listOfLists, left, right);
	}
	
	public static ListNode helper(List<ListNode> lists, int left, int right) {
		if (left < right) {
			int mid = left + (right - left)/2;
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
			if (cur1.value < cur2.value) {
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
	
	
	// method2: use minHeap
	public static ListNode Q2_2_2MergeKLists(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		
		// minHeap
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(size, new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				return o1.value - o2.value;
			}

		});
		
		// add all head into the priority queue
		for(ListNode node: lists) {
			if (node != null) {
				q.add(node);
			}
		}
		
		ListNode newHead = null;
		ListNode tail = newHead;
		
		while(!q.isEmpty()) {
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
	 * Q3 Binary search tree (BST: find, insert, remove a node)
	 * Q3.1: (Find a node whose value is closest to the target value)
	 * 		case(1) if cur.val < target, compare the diff of cur.val with target
	 * 				update if it is closer. Go to the right child node
	 * 		case(2) if cur.val > target, compare the diff of cur.val with target
	 *              update if it is closer. Go to left child node. 
	 * 
	 * Q3.2 ​Given a BST​, how to find​the largest element in the tree that is smaller than 
	 * a target number x. 
	 * 
	 * 
	 */
	public static class Result {
		public int val;
		public Result(int v) {
			this.val = v;
		}
	}
	public static int Q3_1BSTClosestElement(TreeNode root, int target) {
		return -1;
	}
	public static void Q3_1Helper(TreeNode node, int target, Result result) {
		if (node == null) {
			return ;
		}
		int diff = node.val - target;
		if (diff > 0) { // current node is larger than target
			
		}
	}
	
	public static int Q3_2BSTLargestInAllSmaller(TreeNode root, int target) {
		return -1;
	}
	
	
	/*
	 * Q3.3​
	 * How to remove​a target node from BST
	 *  
	 */
	
	
	/*
	 * Q3.4​How to insert​ target node to a BST
	 */
	
	/*
	 * Q4  DP 1D different weight for each smallest element
	 * 
	 * 
	 */
	
	
	/*
	 * There is a wooden stick with length L >= 1, 
	 * we need to cut it into pieces, where the cutting positions are defined in an int array A. 
	 * The positions are guaranteed to be in ascending order in the range of [1, L - 1]. 
	 * The cost of each cut is the length of the stick segment being cut. 
	 * Determine the minimum total cost to cut the stick into the defined pieces.
	 */
	
	/*
	 * M[i][j] stands for the min cost of cutting the wood between index i and index j (taking all
	 * possible cut methods/order)
	 * base case:
	 * 	M[i][i + 1] = 0
	 * induction rule:
	 * 	M[i][j] = A[j] - A[i] + min {M[i][k] + M[k][j]}  i < k < j
	 * 
	 * fill the table from left to right. 
	 * fill in the table from bottom to top
	 */
	public static int Q4_minCost(int[] cuts, int length) {
		if (length <= 1) {
			return 0;
		}
		
		int[] cut = new int[cuts.length + 2];
		for(int i = 0, j = 0; i < cut.length; i ++) {
			if (i == 0) {
				cut[i] = 0;
			} else if (i == cut.length - 1) {
				cut[i] = length;
			} else {
				cut[i]= cuts[j];
				j ++;
			}
		}
		// for debug
		Debug.printArray(cuts);
		Debug.printArray(cut);
		
		int n = cut.length;
		int[][] M = new int[n][n];
 		// initialize
		for(int i = 0; i < n - 1; i ++) {
			M[i][i + 1] = 0;
		}
		
		for(int i = n -3; i >= 0; i -- ) {
			for(int j = i + 2;  j < n; j ++) {
				int curMin = Integer.MAX_VALUE;
				for(int k = i + 1; k < j; k ++) {
					curMin = Math.min(curMin, M[i][k] + M[k][j]);
				}
				M[i][j] = cut[j] - cut[i] + curMin;	
			}
		}
		
		
		return M[0][n - 1];
	} 
	public static void Q4_test() {
		int[] cuts = {2,4,7};
		int minCut = Q4_minCost(cuts, 10);
	}
	
	
	
	
	
	
	
	
	
	

}

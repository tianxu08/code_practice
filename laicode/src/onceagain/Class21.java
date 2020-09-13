package onceagain;

import java.util.*;

import debug.Debug;
import ds.GraphNode;
import ds.ListNode;

public class Class21 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
		test4();
	}

	/*
	 * task1 
	 * Deep Copy List with Random Pointer
	 */
	public static class RandomListNode{
		int value;
		RandomListNode next;
		RandomListNode random; 
		
		public RandomListNode(int v) {
			this.value = v;
			this.next = null;
			this.random = null;
		}
	}
	
	public static RandomListNode task1_deepCopyList_Map(RandomListNode head) {
		RandomListNode cur = head;
		Map<RandomListNode, RandomListNode> myMap = new HashMap<RandomListNode, RandomListNode>();
		// create the node
		while(cur != null) {
			myMap.put(cur, new RandomListNode(cur.value));
			cur = cur.next;
		}
		
		// link the next pointer
		cur =head;
		while(cur != null) {
			myMap.get(cur).next = myMap.get(cur.next);
			cur = cur.next;
		}
		
		// link the random pointer
		cur = head;
		while(cur != null) {
			if (cur.random != null) {
				myMap.get(cur).random = myMap.get(cur.random);
			} else {
				// cur.random == null
				myMap.get(cur).random = null;
			}
		}
		
		return myMap.get(head);
	}
	
	public static RandomListNode task1_deepCopyList_Map2(RandomListNode head) {
		if (head == null) {
			return null;
		}
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode cur = dummy;
		while(head != null) {
			// for next pointer
			if (!map.containsKey(head)) {
				map.put(head, new RandomListNode(head.value));
			}
			cur.next = map.get(head);
			// for random pointer
			if (head.random != null) {
				if (!map.containsKey(head.random)) {
					map.put(head.random, new RandomListNode(head.random.value));
				}
				map.get(head).random = map.get(head.random);
			}
			head = head.next;
			cur = cur.next;
		}
		return dummy.next;
	}
	
	/*
	 * task2
	 * Deep Copy Of Graph(with possible cycles)
	 */
	public static List<GraphNode> task2_deepCopy(List<GraphNode> graph) {
		Map<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
		for(GraphNode node: graph) {
			if (!map.containsKey(node)) {
				map.put(node, new GraphNode(node.key));
				DFS(node, map);
			}
		}
		return new ArrayList<GraphNode>(map.values());
	}
	public static void DFS(GraphNode seed, Map<GraphNode, GraphNode> map) {
		GraphNode copy = map.get(seed);
		for(GraphNode nei: map.get(seed).neighbors) {
			if (!map.containsKey(nei)) {
				map.put(nei, new GraphNode(nei.key));
				DFS(nei, map);
			}
			copy.neighbors.add(map.get(nei));
		}
	}
	
	/*
	 * task3
	 * Merge K sorted Array
	 * imput: int[][] array
	 * output: int[] output
	 */
	public static int[] task3_mergedKSortedArray(int[][] arrayOfArrays) {
		if (arrayOfArrays == null || arrayOfArrays[0] == null || arrayOfArrays.length == 0 || arrayOfArrays[0].length == 0) {
			return null;
		}
		int rLen = arrayOfArrays.length;
		int len = 0;
		PriorityQueue<Element> minHeap = new PriorityQueue<Class21.Element>(100, myComp);
		for(int i = 0; i < rLen; i ++) {
			len += arrayOfArrays[i].length;
			if (arrayOfArrays[i] != null && arrayOfArrays[i].length != 0) {
				minHeap.add(new Element(i, 0, arrayOfArrays[i][0]));
			}
		}
		int[] result = new int[len];
		int index = 0;
		while(!minHeap.isEmpty()) {
			Element cur = minHeap.poll();
			result[index ++] = cur.value;
			
			if (cur.y + 1 < arrayOfArrays[cur.x].length) {
				minHeap.add(new Element(cur.x, cur.y + 1, arrayOfArrays[cur.x][cur.y + 1]));
			}
		}
		
		
		return result;	
	}
	
	public static Comparator<Element> myComp = new Comparator<Class21.Element>() {
		@Override
		public int compare(Element o1, Element o2) {
			// TODO Auto-generated method stub
			if (o1.value == o2.value) {
				return 0;
			} 
			return o1.value < o2.value ? -1 : 1;
		}
	};
	
	public static class Element{
		int x;
		int y;
		int value;
		public Element(int _x, int _y, int _v) {
			this.x = _x;
			this.y = _y;
			this.value = _v;
		}
	}
	
	public static void test3() {
		int[][] matrix = {
				{1,4,7},
				{2,5},
				{3,6,9}
		};
		int[] result = task3_mergedKSortedArray(matrix);
		System.out.println(Arrays.toString(result));
	}
	
	
	
	/*
	 * task4 
	 * Merge K sorted List
	 * 
	 */
	public static ListNode task4_mergeKSortedList(List<ListNode> listOfLists) {
		if (listOfLists == null  || listOfLists.size() == 0) {
			return null;
		}
		int len = listOfLists.size();
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(len, new MyComparator());
		
		for (ListNode node : listOfLists) {
			minHeap.add(node);
		}
		System.out.println(minHeap.size());
		while(!minHeap.isEmpty()) {
			System.out.println("minHeap.size = " + minHeap.size());
			System.out.println("hello2");
			ListNode cur = minHeap.poll();
			tail.next = cur;
			if (cur.next != null) {
				minHeap.offer(cur.next);
			}
			tail = tail.next;
		}
		return dummy.next;
	} 
	
	
	
	public static class MyComparator implements Comparator<ListNode> {
		@Override
		public int compare(ListNode o1, ListNode o2) {
			if (o1.value == o2.value) {
				return 0;
			}
			return o1.value < o2.value ? -1 : 1;
		}
	}

	
	public static void test4() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(5);
		n1.next = n2;
		
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(3);
		n3.next = n4;
		
		List<ListNode> listOfLists = new ArrayList<ListNode>();
		listOfLists.add(n1);
		listOfLists.add(n3);
		ListNode newHead = task4_mergeKSortedList(listOfLists);
		Debug.printLinkedList(newHead);
		
	}
	
	
	/*
	 * task5
	 * Binary Search Tree Closest To target
	 */
	
	
	/*
	 * task6
	 * Binary Search Tree Largest Number Smaller Than Target
	 */
	
	/*
	 * task7
	 * Binary Search Tree Delete
	 */
	
	/*
	 * task8
	 * Wood Cut
	 */
}

package class3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class HashHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashHeap minHeap = new HashHeap("min");
		
		minHeap.offer(1);
		minHeap.offer(5);
		minHeap.offer(10);
		minHeap.offer(8);
		minHeap.offer(1);
		
		minHeap.printHeap();
		minHeap.printHash();
		
		System.out.println("size = " + minHeap.size());
		
		minHeap.poll();
		System.out.println("------------------------");
		minHeap.printHeap();
		minHeap.printHash();
		
		
		int head = minHeap.poll();
		System.out.println("head = " + head);
		System.out.println("------------------------");
		minHeap.printHeap();
		minHeap.printHash();
			
		System.out.println("size = " + minHeap.size());
			
	}

	public ArrayList<Integer> heap;
	private String mode;
	private int size_t;   //  the size of the heap. will >= map.size if there is duplicate
	public HashMap<Integer, Node> map;
	
	public class Node{
		
		public Integer index;  // index of curElement in arrayList
		
		public Integer counter; // Counter of curElement in arrayList
		
		public Node(Node now) {
			// TODO Auto-generated constructor stub
			this.index = now.index;
			this.counter = now.counter;
		}
		
		public Node(Integer first, Integer second) {
			this.index = first;
			this.counter = second;
		}
	}
	
	public HashHeap(String mod) {
		this.heap = new ArrayList<Integer>();
		this.mode = mod;
		map = new HashMap<Integer, Node>();
		size_t = 0;
	}
	
	/*
	 * peek()
	 * return the root of heap
	 */
	public int peek() {
		if (heap == null || heap.size() == 0) {
			throw new IllegalArgumentException("the heap is empty");
		}
		return heap.get(0);
	}
	
	public int size() {
		return size_t;
	}
	
	public boolean isEmpty() {
		return size_t == 0;
	}
	
	public int parent(int id) {
		if (id == 0) {
			return -1;
		}
		return (id - 1)/2;
	}
	
	public int leftChild(int id) {
		return id * 2 + 1;
	}
	
	public int rightChild(int id) {
		return id * 2 + 2;
	}
		
	public void offer(int element) {
		// size_t ++
		size_t ++;
		// already in map
		if (map.containsKey(element)) {
			Node node = map.get(element);
			map.put(element, new Node(node.index, node.counter + 1));
		} else { // not in map
			// add element into heap
			heap.add(element);
			map.put(element, new Node(heap.size() - 1, 1));
		}
		shiftUp(heap.size() - 1);
	}
	
	
	/*
	 * if a <= b
	 * if mode = 'min', a has higher priority, return true
	 * if mode != 'min' [mode = 'max'], b has higher priority, return false;
	 * 
	 * if a > b
	 * if mode = 'min', b has higher priority, return false;
	 * if mode 1= 'min'[mode == 'max'], a has higher priority, return true;
	 */
	public boolean firstHasHigherPriority(int a, int b) {
		if (mode.equals("min")) {
			if (a < b) {
				return true;
			} else {
				return false;
			}
		} else {
			// max heap
			if (a > b) {
				return true;
			} else {
				return false;
			}
		}	
	}
	
	public void swap(int indexA, int indexB) {
		// the original value from heap. 
		int valA = heap.get(indexA);
		int valB = heap.get(indexB);
		
		
		// update in heap
		heap.set(indexA, valB);
		heap.set(indexB, valA);
		
		
		int cntA = map.get(valA).counter;
		int cntB = map.get(valB).counter;
		
		map.put(valA, new Node(indexB, cntA));
		map.put(valB, new Node(indexA, cntB));
	}
	
	
	public Integer poll() {
		if (isEmpty()) {
			throw new IllegalAccessError("the heap is empty");
		}
		size_t --;
		
		Integer now = heap.get(0);
		Node hashnow = map.get(now);
		if (hashnow.counter == 1) { // no duplicate, delete this val from heap and hash
			swap(0, heap.size() - 1);
			// delete from heap
			heap.remove(heap.size() - 1);
			
			// delete from hash
			map.remove(now);
		
			// shiftDown the new head
			if (heap.size() > 0) {
				shiftDown(0);
			}
			
		} else {
			// now has duplicates, only decreate the counter.
			map.put(now, new Node(0, hashnow.counter - 1));
		}
		return now;
	}
	
	public boolean pollElement(int element) {
		if (!map.containsKey(element)) {
			return false;
		}
		size_t --;
		Node node = map.get(element);
		int index = node.index;
		int count = node.counter;
		if (index < 0 || index >= heap.size()) {
			return false;
		}
		if (count > 1) {
			map.put(element, new Node(index, count - 1));
		} else {
			swap(index, heap.size() - 1);
			
			heap.remove(heap.size() - 1);
			map.remove(element);
			if (heap.size() > index) {
				shiftUp(index);
				shiftDown(index);
			}
			
		}
		return true;
	}
	
		
	public void shiftDown(int index) {
		while(index < heap.size()) {
			int leftChildIndex = leftChild(index);
			int rightChildIndex = rightChild(index);
			
			int min = index;
			
			if (leftChildIndex < heap.size() && firstHasHigherPriority(heap.get(leftChildIndex), heap.get(min))) {
				min = leftChildIndex;
			}
			
			if (rightChildIndex < heap.size() && firstHasHigherPriority(heap.get(rightChildIndex), heap.get(min))) {
				min = rightChildIndex;
			}
			
			if (min == index) {
				break;
			}
			
			swap(index, min);
			index = min;
		}
	}
	
	
	
	public void shiftUp(int index) {
		if (index <= 0) {
			return ;
		}
		while(index > 0) {  // here, index cannot be 0. 
			int parent = (index - 1)/2;
			if (!firstHasHigherPriority(heap.get(index), heap.get(parent))) {
				break;
			}
			swap(index, parent);
			index = parent;
		}
	}
	
	
	public void printHeap() {
		for(int i = 0; i< heap.size(); i ++) {
			System.out.print(heap.get(i) + " ");
		}
		System.out.println();
	}
	
	public void printHash() {
		for(Entry<Integer, Node> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + "[ " + entry.getValue().index +" : " +entry.getValue().counter + "]");
		}
	}
	
	
	
	
	/*
	 * follow up
	 * 
	 * how to create heap which has duplicates
	 * 
	 * 节点对应值是多少， count
	 * Node{
	 * 	int value;
	 * 	int count;
	 * }
	 * 
	 * offer(10)
	 * offer(10)
	 * 
	 * 
	 * delete(10)  count --
	 * delete(10)  
	 */
	
	
	
	/*
	 * HashHeap  almost the same with LRU cache
	 * 
	 * LRU cache: HashMap + Linked List
	 */
	

}

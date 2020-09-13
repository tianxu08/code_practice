package class3;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMinHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private ArrayList<Integer> heap;
	private int size;
	private HashMap<Integer, Node> map;
	
	public class Node {
		int index;
		int counter;
		
		public Node(Node node){
			this.index = node.index;
			this.counter = node.counter;
		}
		
		public Node(int idx, int cnt) {
			this.index = idx;
			this.counter = cnt;
		}
	}
	
	public HashMinHeap() {
		heap = new ArrayList<Integer>();
		size = 0;
		map = new HashMap<Integer, Node>();
	}
	// swap indexA's value with indexB's value in heap
	public void swap(int indexA, int indexB) {
		// get value from heap
		int valA = heap.get(indexA);
		int valB = heap.get(indexB);
		
		
		// update in heap
		heap.set(indexA, valB);
		heap.set(indexB, valA);
		
		// update the hashMap
		int cntA = map.get(valA).counter;
		int cntB = map.get(valB).counter;
		
		// only update the index
		map.put(valA, new Node(indexB, cntA));
		map.put(valB, new Node(indexA, cntB));
		
	}
	
	public boolean isEmpty() {
		return heap.size() == 0;
	}
	
	public Integer poll() {
		if (isEmpty()) {
			throw new IllegalArgumentException("the heap is empty");
		}
		size --;
		Integer now = heap.get(0);
		Node nodeNow = map.get(now);
		
		if (nodeNow.counter == 1) {
			swap(0, heap.size() - 1);
			heap.remove(heap.size() - 1);
			map.remove(now);
		} else {
			map.put(now, new Node(0, map.get(nodeNow).counter - 1));
		}
		
		return now;
	}
	
	public void offer(int element) {
		size ++;
		// already in map
		if (map.containsKey(element)) {
			Node node = map.get(element);
			map.put(element, new Node(node.index, node.counter + 1));
		} else { // not in map
			heap.add(element);
			map.put(element, new Node(heap.size() - 1, 1));
			shiftUp(heap.size() - 1);
		}
	}
	
	public int peek() {
		if (isEmpty()) {
			throw new IllegalArgumentException("heap is empty");
		}
		return heap.get(0);
	}
	
	public int size() {
		return size;
	}
	
	
	public void shiftDown(int index) {
		while(index < size) {
			int leftChild = index * 2 + 1;
			int rightChild = index * 2 + 2;
			
			int min = index;
			if (leftChild < size && heap.get(leftChild) < heap.get(min)) {
				min = leftChild;
			}
			if (rightChild < size && heap.get(rightChild) < heap.get(min)) {
				min = rightChild;
			}
			
			if (min == index) {
				break;
			} else {
				// swap min with index
				swap(min, index);
				// update index
				index = min;
			}
		}
	}
	
	public void shiftUp(int index) {
		while(index >= 0) {
			int parent = (index - 1)/2;
			
			if (parent >= 0 && heap.get(index) < heap.get(parent)) {
				swap(index, parent);
				// update index
				index = parent;
			} else {
				break;
			}
		}
	}
	
}

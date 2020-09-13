package class3;

import java.util.Arrays;

public class MinHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Implementation of Capacity Limited Min Heap
	 * 
	 * minHeap
	 * basic method
	 * percolateUp / percolateDown
	 * 
	 * public methods:
	 * size()
	 * isEmpty()
	 * isFull()
	 * peek()
	 * poll()
	 * peekAt(int index) -- get the value at a given index
	 * pollAt(int index) -- get the value at a given index and remove it from the heap
	 * update(int index, int value) -- update the index to a given new value
	 */

	public static int DEFAULT_CAPABILITY = 1024;
	private int[] array;
	private int size;
	
	public MinHeap() {
		this(DEFAULT_CAPABILITY);
	}
	
	public MinHeap(int capability) {
		if (capability <= 0) {
			throw new IllegalArgumentException("the capability should not smaller than 0");
		}
		array = new int[capability];
		size = 0;
	}
	
	public MinHeap(int[] A, int capacity) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Can not initialize with null or empty array");
		}
		
		if (capacity < A.length) {
			throw new IllegalArgumentException("Initial capacity has to be larger than or equals to the array's length");
		}
		size = A.length;
		this.array = Arrays.copyOf(A, capacity);
		heapify();
	}
	
	public int size() {
		return size;
	} 
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == array.length;
	}
	
	public int peek() {
		if (isEmpty()) {
			return -1;
		}
		return -1;
	}
	
	
	/*
	 * heapify()
	 */
	public void heapify() {
		for(int i = size /2 - 1; i >=0; i --) {
			shiftDown(i);
		}
	}
	
	public int poll() {
		swap(0, size - 1);
		size --;
		shiftDown(0);
		return array[size];
	}
	
	public boolean offer(int element) {
		if (isFull()) {
			return false;
		}
		size ++;
		array[size - 1] = element;
		shiftUp(size - 1);
		
		return true;
	}
	
	/*
	 * Check if the index is in the range first
	 * update to the new value at the desired index
	 * if can do shiftUp(index), do shiftUp, else do shiftDown(index)
	 * return the previous element
	 */
	public int update(int index, int newElement) {
		// check index is valid [0, size - 1]
		int result = array[index];
		array[index] = newElement;
		shiftUp(index);
		shiftDown(index);
		return result;
	}
	
	/*
	 * pollAt(int index)
	 * get the value at a given index and remove it from the heap
	 * swap(index, size - 1)
	 * shiftDown(index);
	 * size --
	 * return array[size]
	 * 
	 */
	public int pollAt(int index) {
		// check index is in [0, size - 1]
		swap(index, size - 1);
		size --;
		shiftDown(index);
		
		return array[size];
		
	}
	
	/*
	 * peekAt(int index)
	 */
	public int peekAt(int index) {
		// check index in [0... size - 1]
		return array[index];
	}
	
	/*
	 * shiftDown(int index)
	 * from current index, current index's left child
	 * current index's right child, pick the one with smallest value
	 * if the smallest value's index is not current index,
	 * we need to swap and shiftDown on the smallest value's index
	 */
	public void shiftDown(int index){
		while(index < size) {
			// current index, left child, right child
			int left = index * 2 + 1;
			int right = index * 2 + 2;
			
			// find the index having the smallest value
			int min = index;
			// compare with left
			if (left < size && array[left] < array[min]) {
				min = left;
			}
			
			// compare with right
			if (right < size && array[right] < array[min]) {
				min = right;
			}
			
			if (index == min) {
				break;
			} else {
				// swap array[index] with array[min]
				swap(index, min);
				// update index
				index = min;
			}
		}
	}
	
	/*
	 * shiftUp
	 * if parent index's value is larget than current index's value
	 * we need to do swap and shiftUp on parent's index
	 */
	
	public void shiftUp(int index) {
		while(index > 0) {
			// compare itself with its parent
			int parent = (index - 1)/2;
			if (array[index] >= array[parent] ) {
				break;
			}
			swap(index, parent);
			// update index
			index = parent;
		}
	}
	
	public void swap(int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	
	
	
}

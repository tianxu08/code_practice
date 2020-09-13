package lab;

public class MinHeap {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	// golbal constant variable
	public static final int DEFAULT_CAPACITY = 1024;
	private int[] array;// array.length is the maximum capacity of the minHeap
	private int size; // how many cells actually been filled now

	public MinHeap(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException(
					"Cpapcity can not be less than or equan zero");
		}
		array = new int[capacity];
		size = 0;
	}

	public MinHeap() {
		this(DEFAULT_CAPACITY); // call its own constructor
	}
	
	public MinHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Can not take null/empty array");
		}
		this.array = array;
		this.size = array.length;
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
			throw new ArrayIndexOutOfBoundsException("");
		}
		return array[0];
	}

	// check if empty first.
	// size --
	// return the element at index 0 , move the last element to index 0 and do percolatedown(0)
	public int poll() {
		return pollAt(0);
	}

	public boolean offer(int ele) {
		return true;
	}
	
	private void heapify() {
		for(int i = size/2 - 1; i >= 0; i --) {
			percolateDown(i);
		}
	}
	
	public void checkFull() {
		if (isFull()) {
			throw new IllegalStateException("The minHeap is full, can not offer");
		}
	}
	
	public void checkRange(int index){
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException("index " + index + " out of bound ");
		}
	}
	
	public int peekAt(int index) {
		checkRange(index);
		return array[index];
	}
	
	public int pollAt(int index) {
		checkRange(index);
		// swap with the last element
		swap(index, size - 1);
		size --;
//		if (array[index] < array[size]) {
//			percolateUp(index);
//		} else {
//			percolateDown(index);
//		}
//		
//		return array[size];
		return update(index, array[size]);
	}

	public int update(int index, int ele) {
		checkRange(index);
		int answer = array[index];
		percolateUp(index);
		percolateDown(index);
		return answer;
	}
	/*
	 * 
	 */
	// if parent 
	private void percolateUp(int index) {
		while(index > 0) {
			int parent = (index - 1)/2;
			if (array[index] >= array[parent]) {
				break;
			}
			// swap if necessary
			swap(index, parent);
			index = parent;
		}
	}

	private void percolateDown(int index) {
		while(index < size) {
			// current index, left child, right child
			int left = 2*index + 1;
			int right = 2*index + 2;
			
			int min = index;
			// compare it with left
			if (left < size && array[left] < array[min]) {
				min = left;
			}
			if (right < size && array[right] < array[min]) {
				min = right;
			}
			// swap if necessary 
			if (min == index) {
				break;
			}
			swap(index, min);
			index = min;
		}
	}
	
	
	private  void swap(int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

}

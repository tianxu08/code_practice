package lai_online2;

public class Class1 {
	/*
	 * task1
	 *
	Selection Sort
	Easy
	Data Structure
	Given an array of integers, sort the elements in the array in ascending order. The selection sort algorithm should be used to solve this problem.

	Examples

	{1} is sorted to {1}
	{1, 2, 3} is sorted to {1, 2, 3}
	{3, 2, 1} is sorted to {1, 2, 3}
	{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
	Corner Cases

	What if the given array is null? In this case, we do not need to do anything.
	What if the given array is of length zero? In this case, we do not need to do anything.
	*/
	public int[] solve(int[] array) {
		// Write your solution here.
		if (array == null) {
			return array;
		}

		for (int i = 0; i < array.length; i++) {
			int min = array[i];
			int minIndex = i;
			int j = i;
			for (; j < array.length; j++) {
				if (min > array[j]) {
					min = array[j];
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
		}
		return array;
	}

	public void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	/*
	 * task2
	Move 0s To The End I
	Easy
	Data Structure
	Given an array of integers, move all the 0s to the right end of the array.

	The relative order of the elements in the original array does not need to be maintained.

	Assumptions:

	The given array is not null.
	Examples:

	{1} --> {1}
	{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}
	*/
	
	public int[] moveZero(int[] array) {
	    // Write your solution here.
	    int index = 0;
	    for(int i = 0; i< array.length; i ++) {
	      if(array[i] != 0) {
	        array[index++] = array[i];
	      }
	    }
	    while(index < array.length ) {
	      array[index ++] = 0;
	    }
	    return array;
	  }    
	
	/*
	 * task3
	Rainbow Sort
	Fair
	Data Structure
	Given an array of balls, where the color of the balls can only be Red, Green or Blue, sort the balls such that all the Red balls are grouped on the left side, all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side. (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).

	Examples

	{0} is sorted to {0}
	{1, 0} is sorted to {0, 1}
	{1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
	Assumptions

	The input array is not null.
	Corner Cases

	What if the input array is of length zero? In this case, we should not do anything as well.
	*/
	public int[] rainbowSort(int[] array) {
		// Write your solution here
		int index0 = partition(array, 0, array.length - 1, -1);
		int index1 = partition(array, index0, array.length - 1, 0);

		return array;
	}

	public int partition(int[] a, int start, int end, int pivot) {
		while (start < end) {
			while (start < end && a[start] <= pivot) {
				start++;
			}
			while (start < end && a[end] > pivot) {
				end--;
			}
			if (start == end) {
				break;
			}
			swap(a, start, end);
		}
		// the start would be the index of first element > pivot
		return start;
	}
	
	/*
	 * task4
	
	Merge Sort
	Fair
	Recursion
	Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.

	Examples

	{1} is sorted to {1}
	{1, 2, 3} is sorted to {1, 2, 3}
	{3, 2, 1} is sorted to {1, 2, 3}
	{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
	Corner Cases

	What if the given array is null? In this case, we do not need to do anything.
	What if the given array is of length zero? In this case, we do not need to do anything.

	*
	*/
	public int[] mergeSort(int[] array) {
		// Write your solution here.
		int left = 0, right = array.length - 1;
		int[] temp = new int[array.length];
		helper(array, left, right, temp);
		return array;
	}

	public void helper(int[] array, int left, int right, int[] temp) {
		if (left >= right) {
			return;
		}

		int mid = left + (right - left) / 2;
		helper(array, left, mid, temp);
		helper(array, mid + 1, right, temp);

		merge(array, left, mid + 1, right, temp);
	}

	public void merge(int[] array, int leftStart, int rightStart, int rightEnd,
			int[] temp) {

		int leftEnd = rightStart - 1;
		int elementNum = rightEnd - leftStart + 1;

		int index = leftStart;
		while (leftStart <= leftEnd && rightStart <= rightEnd) {
			if (array[leftStart] <= array[rightStart]) {
				temp[index++] = array[leftStart++];
			} else {
				temp[index++] = array[rightStart++];
			}
		}

		while (leftStart <= leftEnd) {
			temp[index++] = array[leftStart++];
		}

		while (rightStart <= rightEnd) {
			temp[index++] = array[rightStart++];
		}

		// copy the temp back to array
		for (int i = 0; i < elementNum; i++) {
			array[rightEnd - i] = temp[rightEnd - i];
		}
	}
	
	/*
	task5
	Quick Sort
	Fair
	Recursion
	Given an array of integers, sort the elements in the array in ascending order. The quick sort algorithm should be used to solve this problem.

	Examples

	{1} is sorted to {1}
	{1, 2, 3} is sorted to {1, 2, 3}
	{3, 2, 1} is sorted to {3, 2, 1}
	{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
	Corner Cases

	What if the given array is null? In this case, we do not need to do anything.
	What if the given array is of length zero? In this case, we do not need to do anything.
	*/
	
	public int[] quickSort(int[] array) {
		// Write your solution here
		int left = 0, right = array.length - 1;
		helper(array, left, right);
		return array;
	}

	public void helper(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivotPos = partition(array, left, right);
		helper(array, left, pivotPos - 1);
		helper(array, pivotPos + 1, right);
	}

	public int partition(int[] array, int start, int end) {

		int pivot = array[end];
		int left = start, right = end;
		while (true) {
			while (left >= 0 && left < array.length && array[left] < pivot) {
				left++;
			}
			while (right < array.length && right >= 0 && array[right] >= pivot) {
				right--;
			}
			if (left >= right) {
				break;
			}
			swap(array, left, right);
			// swap array[left] and array[right]
		}

		// swap
		swap(array, left, end);
		return left;
	}

}

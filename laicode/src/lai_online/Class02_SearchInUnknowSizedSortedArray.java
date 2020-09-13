package lai_online;

public class Class02_SearchInUnknowSizedSortedArray {

	
	/*
	 * Search In Unknown Sized Sorted Array
	 * Fair Data Structure
	 * Given a integer dictionary A of unknown size, 
	 * where the numbers in the dictionary are sorted in ascending order, determine if a given target integer T is in the dictionary. Return the index of T in A, return -1 if T is not in A.
	 * Assumptions
	 * dictionary A is not null
	 * dictionary.get(i) will return null if index i is out of bounds
	 * Examples
	 * A = {1, 2, 5, 9, ......}, T = 5, return 2
	 * A = {1, 2, 5, 9, 12, ......}, T = 7, return -1
	 * 
	 * the key idea is that when the pointer out of bound,return null rather than throw out an exception. 
	 *  
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		int[] arr = {1,2,3,4,5,6,8,9};
		Dictionary dict = new Dictionary(arr);
		int target = 10;
		int resultIndex = unknowSizeBinarySearch(dict, target);
		System.out.println("resultIndex = " + resultIndex);
	}
	
	
	public static class Dictionary{
		public int[] array;
		public Dictionary(int[] array) {
			this.array = array;
		}
		
		public Integer get(int index) { // !!! note the return type
		    // key: if index >= array.length, then return null;
			// NOT throw an exception. 
			if (array == null || index >= array.length) {
				return null;
			}
			return array[index];
		}
	}
	
	public static int unknowSizeBinarySearch(Dictionary dict, int target) {
		if (dict == null) {
			return -1;
		}
		int left = 0;
		int right = 1;
		
		// if we still didn't find the right bound
		while(dict.get(right) != null && dict.get(right) < target) {
			left = right;
			right *= 2;
		}
		
		// after the above loop, array[right] >= target or right out of bound
		return binarySearch(dict, left, right, target); 
	}
	
	public static int binarySearch(Dictionary dict, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
	
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			// first, handle the case that:  dict.get(mid) == null 
			if (dict.get(mid) == null || dict.get(mid) > target ) {
				// special attention to right
				// mid is out of bound or array[mid] > target
				// move to left side
				right = mid;
			} else if (dict.get(mid) < target) {
				// move to right side
				left = mid;
			} else {
				return mid;
			}
		}
		
		if (dict.get(left) != null && dict.get(left) == target) {
			return left;
		} else if (dict.get(right) != null && dict.get(right) == target) {
			return right;
		} else {
			return -1;
		}
	}
	
	
}

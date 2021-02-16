package s_array;

public class SearchInRotatedArrayII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		int[] a = { 15, 1, 3, 6, 10 };
		int target = 1;
		int index = search(a, target);

		System.out.println("index = " + index);
	}

	public static int search(int[] array, int target) {
		// Write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}
		int n = array.length;
		int start = 0, end = n - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] == target) {
				return mid;
			}
			if (array[mid] > array[start]) {
				// the left side is sorted
				if (target >= array[start] && target <= array[mid]) {
					// in the left side
					end = mid;
				} else {
					start = mid;
				}
			} else if (array[mid] < array[start]) {
				// right side is sorted
				if (target >= array[mid] && target <= array[end]) {
					start = mid;
				} else {
					end = start;
				}
			} else {
				start++;
			}
		}
		if (array[start] == target) {
			return start;
		} else if (array[end] == target) {
			return end;
		} else {
			return -1;
		}
	}

}

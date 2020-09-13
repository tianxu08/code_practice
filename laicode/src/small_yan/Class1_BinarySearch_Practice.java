package small_yan;

public class Class1_BinarySearch_Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	
	public static void test() {
		int[] array = { 1, 3,3,3,3,3,3, 5,5,5,5,5,5,5,7,7,7,7, 7, 9, 11 };
//		int target = 5;
//		int index = task3_largest_smaller_index(array, target);
//		System.out.println("index = " + index);
//		if (index != -1) {
//		System.out.println("array[index] = " + array[index]);
//		}
		// int target2 = 0;
		// int index2 = task2_smallest_larger_index(array, target2);
		// System.out.println("index2 = " + index2);
		// if (index2 != array.length) {
		// System.out.println("array[index2] = " + array[index2]);
		// }
//		int target4 = 7;
//		int index4 = task4_smallest_larger_equal_index(array, target4);
//		System.out.println("index4 = " + index4);
//		if (index4 != array.length) {
//			System.out.println("array[index2] = " + array[index4]);
//		}
		int target5 = 3;
		int index5 = task5_largest_smaller_equal(array, target5);
		System.out.println("index5 = " + index5);
		
		
	}

	/*
	 * task1 closest
	 */

	/*
	 * task2 smallest larger than target
	 */
	public static int task2_smallest_larger_index(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int left = 0, right = array.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				// let's go to the right side
				left = mid;
			} else if (target < array[mid]) {
				// search in the left side
				right = mid;
			} else {
				// search in the right side
				left = mid;
			}
		}

		// after the above loop, array[left] should smaller than target,
		// array[right] should larger than target
		// we check array[left] first, just for corner case.
		if (array[left] > target) {
			return left;
		}
		if (array[right] > target) {
			return right;
		}
		return array.length;
	}

	/*
	 * task3 largest smaller than target
	 */
	public static int task3_largest_smaller_index(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		// pre-processing, actually, we don't need this.
		// smaller than the first element
		if (target < array[0]) {
			return -1;
		}
		// larger than the last element
		if (target > array[array.length - 1]) {
			return array.length - 1;
		}

		int left = 0, right = array.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				// let's go to the left side
				right = mid;
			} else if (target < array[mid]) {
				// search in the left side
				right = mid;
			} else {
				// search in the right side
				left = mid;
			}
		}

		// array[left] should smaller than the target, array[right] should
		// larger than the target
		// but we first compare target with array[right] just in case
		if (array[right] < target) {
			return right;
		}
		if (array[left] < target) {
			return left;
		}
		return -1;
	}

	/*
	 * task4 smallest larger or equal
	 */
	public static int task4_smallest_larger_equal_index(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int left = 0, right = array.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				// let's go to the left side
				right = mid;
			} else if (target < array[mid]) {
				// search in the left side
				right = mid;
			} else {
				// search in the right side
				left = mid;
			}
		}

		// after the above loop, array[left] should smaller than target,
		// array[right] should larger than target
		// we check array[left] first, just for corner case.
		if (array[left] >= target) {
			return left;
		}
		if (array[right] >= target) {
			return right;
		}
		return array.length;
	}

	/*
	 * task5 largest smaller or equals
	 */
	public static int task5_largest_smaller_equal(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0, right = array.length - 1;
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (array[mid] == target) {
				right = mid;
			} else if (target < array[mid]) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		if (array[right] <= target) {
			return right;
		}
		if (array[left] <= target) {
			return left;
		}
		return -1;
	}
	

	/*
	 * task6 first occurrence if not found, return -1
	 */
	public static int task6_first_occurence_index(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;
		int start = 0, end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (target == nums[mid]) {
				// there might still target in the left side,since we want the
				// first occurrence
				end = mid;
			} else if (target < nums[mid]) {
				// in the left side
				end = mid;
			} else {
				// in the right side
				start = mid;
			}
		}
		// first check the left
		if (nums[start] == target)
			return start;
		// then check the right, since we want the first occurence.
		if (nums[end] == target)
			return end;
		return -1;
	}

	/*
	 * task7 last occurrence
	 */

	public static int task6_last_occurence_index(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;
		int start = 0, end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (target == nums[mid]) {
				// there might still target in the right side,since we want the
				// last occurrence
				start = mid;
			} else if (target < nums[mid]) {
				// in the left side
				end = mid;
			} else {
				// in the right side
				start = mid;
			}
		}
		// first check the right, since we want the last occurrence.
		if (nums[end] == target)
			return end;

		// then check the left
		if (nums[start] == target)
			return start;
		return -1;
	}

}

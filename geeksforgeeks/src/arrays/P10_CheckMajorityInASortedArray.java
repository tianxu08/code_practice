package arrays;

public class P10_CheckMajorityInASortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
		test2();
	}
	
	/*
	 * http://www.geeksforgeeks.org/check-for-majority-element-in-a-sorted-array/
	 */
	
	public static int lastSmaller(int[] a, int target) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int start = 0, end = a.length - 1;
		int lastSmallerIndex = -1;
		while(start + 1< end) {
			int mid = start + (end - start)/2;
			if (a[mid] == target) {
				end = mid;
			} else if (target < a[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		
		// after execute the above code, if target > a[0] && target < a[n-1]
		// the start will the last smaller element. 
		// but this doesn't work for target <= a[0] and target >= a[n-1]
		// in this case, we need to take in special consideration. 
		
		if (target <= a[start]) {
			lastSmallerIndex = start - 1;
		} else if (target >= a[end]) {
			lastSmallerIndex = end;
		} else{
			lastSmallerIndex = start;
		}
		
		return lastSmallerIndex;
	}
	
	public static void test() {
		int[] a = {1,4,5,6,8,10,20,30};
		int x = 1;
//		int rev = lastSmaller(a, x);
//		System.out.println("rev = " + rev);
//		System.out.println("---------------");
		int firstLarger = firstLarger(a, x);
		System.out.println("firstLarger = " + firstLarger);
				
	}
	
	public static int firstLarger(int[] a, int target) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int firstLargerIndex = -1;
		int start = 0, end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (target == a[mid]) {
				start = mid;
			} else if (target < a[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		
		// after above execution, if target >= a[0] and target < a[n-1]
		// then firstLargerIndex = end;
		// if target < a[0] firstLargerindex = 0 
		// if target >= a[n-1]  firstLargerIndex = n
		//    
		if (target < a[start]) {
			firstLargerIndex = start;
		} else if (target >= a[end]) {
			firstLargerIndex = end + 1;
		} else {
			firstLargerIndex = end;
		}
		return firstLargerIndex;
	}
	
	public static int findTheFirstIndex(int[] a, int target) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int firstIndex = -1;
		int start = 0, end = a.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start)/2;
			if (target == a[mid]) {
				end = mid;
			} else if (target < a[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (target == a[start]) {
			firstIndex = start;
		} else if (target == a[end]) {
			firstIndex = end;
		} else {
			firstIndex = -1;
		}
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		return firstIndex;
	}
	
	public static int findTheLastIndex(int[] a, int target) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int start = 0, end = a.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (target == a[mid]) {
				start = mid;
			} else if (target < a[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		if (target == a[start]) {
			return start;
		} else if (target == a[end]) {
			return end;
		} else {
			return -1;
		}
	}
	
	public static void test2() {
		int[] a = {1,2,4,4,4,4,4,4,5,7,9,10};
		int firstIndex = findTheFirstIndex(a, 1);
		System.out.println("firstIndex = " + firstIndex);
		System.out.println("----------------");
		int lastIndex = findTheLastIndex(a, 10);
		System.out.println("lastIndex = " + lastIndex);
	}
	
	
	/*
	 * Check for Majority Element in a sorted array
	 */
	public static boolean isMajority(int[] a, int target) {
		int firstIndex = findTheFirstIndex(a, target);
		int lastIndex = findTheLastIndex(a, target);
		if (firstIndex == -1) {
			return false;
		}
		int size = lastIndex - firstIndex + 1;
		if (size > a.length/2) {
			return true;
		}
		return false;
	}

}

package arrays;

import java.util.ArrayList;

public class P10_FloorAndCeilingInSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/search-floor-and-ceil-in-a-sorted-array/
	 */
	/*
	 * Given a sorted array and a value x, the ceiling of x is the smallest element in array greater than or equal to x, and the floor is the greatest element smaller than or equal to x. Assume than the array is sorted in non-decreasing order. Write efficient functions to find floor and ceiling of x.
		For example, let the input array be {1, 2, 8, 10, 10, 12, 19}
		For x = 0:    floor doesn't exist in array,  ceil  = 1
		For x = 1:    floor  = 1,  ceil  = 1
		For x = 5:    floor  = 2,  ceil  = 8
		For x = 20:   floor  = 19,  ceil doesn't exist in array
	 */
	
	/*
	 * if x in array, floor and ceil are both x
	 * if not         floor is the first element < x
	 * 				  ceil is the first element > x
	 * Linear Search is straight forward. Time: O(n)
	 * Binary Search Time: O(log n) 
	 */
	
	public static ArrayList<Integer> floorAndCeil(int[] a, int x) {
		ArrayList<Integer> floorAndCeil = new ArrayList<Integer>();
		int floor = Integer.MIN_VALUE;
		int ceil = Integer.MAX_VALUE;
		int n = a.length;
		if (x < a[0]) {
			floor = Integer.MIN_VALUE;
			ceil = a[0];
			floorAndCeil.add(floor);
			floorAndCeil.add(ceil);
			return floorAndCeil;
		}
		if (x > a[n-1]) {
			floor = a[n-1];
			ceil = Integer.MAX_VALUE;
			floorAndCeil.add(floor);
			floorAndCeil.add(ceil);
			return floorAndCeil;
		}
		// find the floor
		int start = 0, end = a.length - 1;
		while (start + 1< end) {
			int mid = start + (end - start) / 2;
			if (a[mid] == x) {
				floor = x;
				ceil = x;
				floorAndCeil.add(floor);
				floorAndCeil.add(ceil);
				return floorAndCeil;
			} else if (x < a[mid]) {
				// in the left side
				end = mid;
			} else {
				start = mid;
			}
		}

//		System.out.println("start = " + a[start]);
//		System.out.println("end = " + a[end]);
		// after the above loop end, the start + 1 = end, start and end are adjacent
		// and the a[start] < x < a[end]
		floor = a[start];
		ceil = a[end];
		
		floorAndCeil.add(floor);
		floorAndCeil.add(ceil);
		return floorAndCeil;
	}
	
	public static void test() {
		int[] a = {1, 2,7,8, 10, 10, 12, 19};
		
		System.out.println(floorAndCeil(a, 0));
		System.out.println(floorAndCeil(a, 1));
		System.out.println(floorAndCeil(a, 5));
		System.out.println(floorAndCeil(a, 10));
		System.out.println(floorAndCeil(a, 18));
		System.out.println(floorAndCeil(a, 30));
		
	}
	
}

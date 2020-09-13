package arrays;

import java.util.ArrayList;

public class P10_FindDuplicatesInLInearTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
	 */
	
	/*
	 * Given an array of n elements which contains elements from 0 to n-1, with
	 * any of these numbers appearing any number of times. Find these repeating
	 * numbers in O(n) and using only constant memory space.
	 * 
	 * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer
	 * should be 1, 3 and 6.
	 */
	/*
	 * Extension of 
	 * http://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-array/
	 */
	/*
	 * Method 1 and Method 2 of the above link are not applicable as the
	 * question says O(n) time complexity and O(1) constant space. 
	 * Also, Method 3 and Method 4 cannot be applied here because there can be more than 2
	 * repeating elements in this problem. 
	 * Method 5 can be extended to work for this problem. 
	 * Below is the solution that is similar to the Method 5.
	 */
	
	public static void printRepeating(int[] a) {
		ArrayList<Integer> repeat = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			if (a[Math.abs(a[i])] > 0) {
				a[Math.abs(a[i])] = - a[Math.abs(a[i])];
			} else {
				repeat.add(Math.abs(a[i]));
			}
		}
		System.out.println(repeat);
	}
	
	public static void test() {
		int[] a = {1, 2, 3, 1, 3, 6, 6};
		printRepeating(a);
	}
	
	

}

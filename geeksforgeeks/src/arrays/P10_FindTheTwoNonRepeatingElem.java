package arrays;

import java.util.ArrayList;

public class P10_FindTheTwoNonRepeatingElem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/find-two-non-repeating-elements-in-an-array-of-repeating-elements/
	 *
	 * Given an array in which all numbers except two are repeated once. (i.e.
	 * we have 2n+2 numbers and n numbers are occurring twice and remaining two
	 * have occurred once). Find those two numbers in the most efficient way.
	 */
	
	public static ArrayList<Integer> findTwoNonRepeatingElem(int[] a) {
		// suppose that the two elements are X and Y
		ArrayList<Integer> result = new ArrayList<Integer>();
		int xor = a[0];
		for (int i = 1; i < a.length; i++) {
			xor ^= a[i];
		}
		// now the xor is X^Y
		int set_bit_no = xor & ~(xor - 1);
		int X = 0, Y = 0;
		for (int i = 0; i < a.length; i++) {
			if ((a[i] & set_bit_no) == set_bit_no) {
				X ^= a[i];
			} else {
				Y ^= a[i];
			}
		}
		result.add(X);
		result.add(Y);
		
		return result;
		
	}  // Time: O(n)  Space: O(1)
	
	public static void test() {
		int[] a = {2, 3, 7, 9, 11, 2, 3, 11};
		ArrayList<Integer> rev = findTwoNonRepeatingElem(a);
		System.out.println(rev);
	}

}

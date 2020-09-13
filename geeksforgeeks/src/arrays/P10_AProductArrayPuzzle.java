package arrays;

public class P10_AProductArrayPuzzle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/a-product-array-puzzle/
	 */
	
	/*
	 * A Product Array Puzzle Given an array arr[] of n integers, construct a
	 * Product Array prod[] (of same size) such that prod[i] is equal to the
	 * product of all the elements of arr[] except arr[i]. Solve it without
	 * division operator and in O(n).
	 */
	
	/*
	 * left[i] contains the product of a[0..i-1] 
	 * right[i] contain the product of a[i+1, n-1]
	 * product[i] = left[i] * right[i] 
	 */
	
	public static int[] product(int[] a) {
		int n = a.length;
		int[] left = new int[n];
		int[] right = new int[n];
		int[] prod = new int[n];
		left[0] = 1;
		for (int i = 1; i < right.length; i++) {
			left[i] = left[i-1] * a[i - 1];
		}
		
		right[n-1] = 1;
		for (int i = n-2; i >= 0; i--) {
			right[i] = right[i + 1] * a[i + 1];
		}
		
		for (int i = 0; i < n; i++) {
			prod[i] = left[i] * right[i];
		}
		printArray(prod);
		return prod;
	} // Time: O(n)  Space: O(n) Auxiliary Space: O(n)
	
	/*
	 * Optimize: we can optimize auxiliary space to O(1)
	 */
	
	public static int[] productOPT(int[] a) {
		int n = a.length;
		int[] prod = new int[n];
		int temp = 1;
		for (int i = 0; i < prod.length; i++) {
			prod[i] = temp;
			temp *= a[i];
		}
		
		temp = 1;
		for (int j = n - 1; j >= 0; j--) {
			prod[j] *= temp;
			temp *= a[j];
		}
		
		printArray(prod);
		return prod;
	}
	
	
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void test() {
		int[] a = {10, 3, 5, 6, 2};
		printArray(a);
		product(a);
		productOPT(a);
	}
	

}

package arrays;

public class P10_Segregate0sand1sInAnArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
		test2();
	}
	
	/*
	 * http://www.geeksforgeeks.org/segregate-0s-and-1s-in-an-array-by-traversing-array-once/
	 * 
	 * You are given an array of 0s and 1s in random order. Segregate 0s on left side and 1s on right side of the array. Traverse array only once.
	 * Input array   =  [0, 1, 0, 1, 0, 0, 1, 1, 1, 0] 
	 * Output array =  [0, 0, 0, 0, 0, 1, 1, 1, 1, 1] 
	 * 
	 * use quick sort partition idea
	 * 
	 */
	
	public static void segregate(int[] a) {
		int left = 0, right = a.length - 1;
		while(left < right) {
			while(left < right && a[left] == 0) {
				left ++;
			}
			while (left < right && a[right] == 1) {
				right --;
			}
			// swap a[left] and a[right]
			int temp = a[left];
			a[left] = a[right];
			a[right] = temp;
			
			left ++;
			right --;
		}
	}
	
	public static void test() {
		int[] a = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0};
		segregate(a);;
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	// follow up
	/*
	 * http://www.geeksforgeeks.org/segregate-even-and-odd-numbers/
	 */
	
	/*
	 * Segregate Even and Odd numbers
	 * Given an array A[], write a function that segregates even and odd numbers. 
	 * The functions should put all even numbers first, and then odd numbers.
	 */
	
	public static void segregateOddAndEven(int[] a) {
		int left = 0, right = a.length - 1;
		while (left < right) {
			while(left < right && a[left] % 2 == 0) {
				left ++;
			}
			while (left < right && a[right] % 2 == 1) {
				right --;
			}
			swap(a, left, right);
			left ++;
			right --;
		}
	}
	
	public static void test2() {
		int[] a = {2, 34, 45, 9, 8, 90, 3};
		segregateOddAndEven(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void swap(int[] a, int x, int y) {
		a[x] = a[x] + a[y];
		a[y] = a[x] - a[y];
		a[x] = a[x] - a[y];
	}
	
	

}

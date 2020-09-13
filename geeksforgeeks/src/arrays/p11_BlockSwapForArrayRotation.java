package arrays;

public class p11_BlockSwapForArrayRotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/block-swap-algorithm-for-array-rotation/
	 */	
	// rotate array: 1 2 3 4 5 6 7 k = 2
	// output: 3 4 5 6 7 1 2

	public static void rotate(int[] a, int k) {
		int n = a.length;
		// swap the first k elements with the last k elements
		int i = 0, j = n - 1;
		if (k > n) {
			k = k % n;
		}
		for (; i < k && i <= j; i++, j--) {
			swap(a, i, j);
		}

		// after the above 
		// reverse the unswap part
		// in this example, it is 3,4,5
		reverse(a, i, j);
	   // reverse the whole first part	
		reverse(a, 0, j);
		// reverse the last part
		reverse(a, j + 1,  n- 1);
		for (int m = 0; m < a.length; m++) {
			System.out.print(a[m] + " ");
		}
		System.out.println();
	}

	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	public static void reverse(int[] a, int x, int y) {
		// reverse the a[x..y]
		while (x < y) {
			swap(a, x, y);
			x++;
			y--;
		}
	}

	public static void test() {
		int[] a = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 2;
		rotate(a, k);
		int[] a2 = {1,2,3,4,5,6,7};
		rotate2(a2, k);
	}
	
	/*  1,2,3,4,5,6,7  k = 2
	 * (1) seperate the input array as two part first as A, second as B
	 *     A = 1,2   B = 3,4,5,6,7
	 * (2) reverse A => ARev ={2,1}
	 * (3) reverse B => BRev ={7 6 5 4 3}   ARev and BRev = 2 1 7 6 5 4 3
	 * (4) reverse the whole array.         3 4 5 6 7 1 2
	 * 
	 * 三步翻转法，类似的 reverse by word
	 */
	public static void rotate2(int[] a, int k) {
		int n = a.length;
		if (k > n) {
			k = k % n;
		}
		reverse(a, 0, k - 1);
		reverse(a, k,  n-1);
		reverse(a, 0, n-1);
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	

}

package arrays;

public class p11_leadersInArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/leaders-in-an-array/
	 * 
	 * Write a program to print all the LEADERS in the array. An element is
	 * leader if it is greater than all the elements to its right side. And the
	 * rightmost element is always a leader. 
	 * For example int the array {16, 17,4, 3, 5, 2}, leaders are 17, 5 and 2.
	 */
	
	
	public static void leaders(int[] a) {
		int n = a.length;
		int rightMax = a[n - 1];
		System.out.print(rightMax + " ");
		for (int i = n - 2; i >= 0; i--) {
			if (a[i] > rightMax) {
				System.out.print(a[i] + " ");
				rightMax = a[i];
			}
		}
	}
	public static void test() {
		int[] a = {16, 17,4, 3, 5, 2};
		leaders(a);
	}

}

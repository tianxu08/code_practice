package arrays;

import java.util.Arrays;

public class p11_2SumCloseToZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * http://www.geeksforgeeks.org/two-elements-whose-sum-is-closest-to-zero/
	 */
	
	/*
	 *  sorting
	 *  (1) sort
	 *  (2) sum = a[left] + a[right];
	 *  (3) if sum == 0, print out a[left] and a[right]
	 *      else if sum < 0  left ++;
	 *      else // sum > 0  right --;
	 */
	
	public static void twoSumClose0(int[] a) {
		Arrays.sort(a);
		int left = 0, right = a.length - 1;
		int min = Integer.MAX_VALUE;
		int resultLeft = 0;
		int resultRight = 0;
		while(left < right) {
			int sum = a[left] + a[right];
			if (Math.abs(min) > Math.abs(sum)) {
				min = sum;
				resultLeft = left;
				resultRight = right;
			} else if (sum < 0) {
				// we need a larger one
				left ++;
			} else {
				right --;
			}
		}
		System.out.println("minSum = " + min);
		System.out.println("elements are: " + a[resultLeft] + " " + a[resultRight]);
	}
	
	

}

package arrays;

public class p11_1stAnd2ndSmallestElem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * 
	 */
	
	/*
	 * Initialize:
	 * (1) first = second = Integer.Max;
	 * (2) traverse the array. 
	 * 	   if a[i] < first, update first and second.  second = first; first = a[i] 
	 *     if a[i] > first and a[i] < second   second = a[i];
	 *     
	 */
	
	public static void print2Smallest(int[] a) {
		// edge case
		if (a == null || a.length < 2) {
			return ;
		}
		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			int cur = a[i];
			if (cur < first) {
				// update the first and second
				second = first;
				first = cur;
			} 
			if (cur > first && cur < second) {
				// update the second 
				second = cur;
			}
			
		}
		System.out.println("first = " + first);
		System.out.println("second = " + second);
	}
	
	public static void test() {
		int[] a = {12, 13, 1, 10, 34, 1};
		print2Smallest(a);
	}

}

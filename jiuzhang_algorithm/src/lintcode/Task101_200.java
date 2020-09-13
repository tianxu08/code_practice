package lintcode;

public class Task101_200 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test179();
	}

	/*
	 * task179
	 */
	public static void test179() {
		int n = -11, m = -789,i = 0, j = 31;
		int rev = task179_updateBits(n, m, i, j);
		System.out.println(rev);
	}
	public static int task179_updateBits(int n, int m, int i, int j) {
		// write your code here
		int mask_clear;
		if (j < 31) {
			mask_clear = ~(((1 << (j - i + 1)) - 1) << i); // i = 2, j = 6
		} else {
			// if j >= 31, the left side is all 0s
			mask_clear = (1 << i) - 1;
		}
		
		printBis(mask_clear, 32);
		
		// 0000 0000 0010 0000
		// 0000 0000 0001 1111
		// 0000 0000 0111 1100
		n = n & mask_clear;
		int mask2 = m << i;
		printBis(mask2, 32);
		n = n | mask2;
		return n;
	}
	public static void printBis(int num, int shift) {
		StringBuilder builder = new StringBuilder();
		while(shift != 0) {
			builder.append((num & 1) == 1 ? 1 : 0);
			shift --;
			num >>>= 1;
		}
		System.out.println(builder.reverse());
		
	}
	
	/*
	 * task180
	 * Binary Representation
	 * http://www.lintcode.com/en/problem/binary-representation/
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}

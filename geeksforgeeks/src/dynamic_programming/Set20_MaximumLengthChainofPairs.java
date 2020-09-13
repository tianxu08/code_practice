package dynamic_programming;

public class Set20_MaximumLengthChainofPairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain-of-pairs/
	 * 
	 * Modification of LIS
	 */
	
	/*
	 * Make a little modification of LIS
	 * We cannot sort
	 * Time: O(n^2)
	 * Space: O(n)
	 */
	public static class Pair{
		int a;
		int b;
		public Pair(int x, int y) {
			this.a = x;
			this.b = y;
		}
	}
	public static int maxLenChainOfPairs(Pair[] input){
		if (input == null || input.length == 0) {
			return 0;
		}
		int len = input.length;
		int[] maxChainLen = new int[len];
		for (int i = 0; i < len; i++) {
			maxChainLen[i] = 1;
		}
		for (int i = 1; i < len; i++) {
			for (int j = 0; j < i; j++) {
				if (input[i].a > input[j].b) {
					maxChainLen[i] = Math.max(maxChainLen[i], maxChainLen[j] + 1);
				}
			}
		}
		int maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < maxChainLen.length; i++) {
			maxLen = Math.max(maxLen, maxChainLen[i]);
		}
		return maxLen;
	}
	
	public static void test() {
		Pair[] input = {
				new Pair(5, 24),
				new Pair(39, 60),
				new Pair(15, 28),
				new Pair(27, 40), 
				new Pair(50, 90)
		};
		int maxLen = maxLenChainOfPairs(input);
		System.out.println("maxLen = " + maxLen);
	}

}

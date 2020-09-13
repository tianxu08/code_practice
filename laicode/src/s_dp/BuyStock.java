package dp;

public class BuyStock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Buy Stock I Easy DP 
	 * Given an array of positive integers representing a
	 * stock’s price on each day. On each day you can only make one operation:
	 * either buy or sell one unit of stock and you can make at most 1
	 * transaction. Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and is length of at least 2. Examples
	 * 
	 * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4
	 */
	
	// the function return the maxProfit of one transaction
	public int maxProfit(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int curMin = array[0];
		int maxFit = 0;
		for (int i = 1; i < array.length; i++) {
			if (curMin > array[i]) {
				curMin = array[i];
			}
			maxFit = Math.max(array[i] - curMin, maxFit);
		}

		return maxFit;
	}
	
	/*
	 * Buy Stock II Easy DP 
	 * Given an array of positive integers representing a
	 * stock’s price on each day. On each day you can only make one operation:
	 * either buy or sell one unit of stock, you can make as many transactions
	 * you want, but at any time you can only hold at most one unit of stock.
	 * Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The give array is not null and is length of at least 2 Examples
	 * 
	 * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is (3 - 2) + (5 - 1) = 5
	 */
	public int maxProfit2(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int maxProfit = 0;
		for (int i = 1; i < array.length; i++) {
			int diff = array[i] - array[i - 1];
			if (diff > 0) {
				maxProfit += diff;
			}
		}
		return maxProfit;
	}
	
	
	/*
	 * Buy Stock III Fair DP 
	 * Given an array of positive integers representing a
	 * stock’s price on each day. On each day you can only make one operation:
	 * either buy or sell one unit of stock, at any time you can only hold at
	 * most one unit of stock, and you can make at most 2 transactions in total.
	 * Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The give array is not null and is length of at least 2 Examples
	 * 
	 * {2, 3, 2, 1, 4, 5, 2, 11}, the maximum profit you can make is (5 - 1) +
	 * (11 - 2) = 13
	 */
	
	public int maxProfit3(int[] array) {
		// write your solution here
		return 0;
	}
	
	
	/*
	 * Buy Stock IV Hard DP 
	 * Given an array of integers representing a stock’s
	 * price on each day. On each day you can only make one operation: either
	 * buy or sell one unit of stock, and at any time you can only hold at most
	 * one unit of stock, and you can make at most K transactions in total.
	 * Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The give array is not null and is length of at least 2 Examples
	 * 
	 * {2, 3, 2, 1, 4, 5, 2, 11}, K = 3, the maximum profit you can make is (3 - 2) + (5 - 1) + (11 - 2)= 14
	 */
	public int maxProfit4(int[] array, int k) {
		// write your solution here
		return 0;
	}

}

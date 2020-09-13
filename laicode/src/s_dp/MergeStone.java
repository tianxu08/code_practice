package dp;

public class MergeStone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * Merge Stones Hard DP
	 * We have a list of piles of stones, each pile of stones has a certain weight, 
	 * represented by an array of integers. In each move, we can merge two adjacent piles into one larger pile, the cost is the sum of the weights of the two piles. We merge the piles of stones until we have only one pile left. Determine the minimum total cost.
	 * Assumptions
	 * stones is not null and is length of at least 1
	 * Examples
	 * {4, 3, 3, 4}, the minimum cost is 28
	 * merge first 4 and first 3, cost 7
	 * merge second 3 and second 4, cost 7
	 * merge two 7s, cost 14
	 * total cost = 7 + 7 + 14 = 28
	 */
	
	/*
	 * This should be the variation of matrix chain multiplication
	 * M[i][j] stands for the min cost to merge from stone[i] to stone[j](both included)
	 * Base case: M[i][i] = 0;
	 * Induction rule: M[i][j] = min(M[i][k] + M[k+ 1][j] + sum of stone[i..j])  (k >= i && k < j)
	 */
	public static int minCost(int[] stones) {
		if (stones == null || stones.length == 1) {
			return 0;
		}
		int n = stones.length;
		int[][] M = new int[n][n];
		int[] preSum = new int[n];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				preSum[i] = stones[i];
			} else {
				preSum[i] = preSum[i- 1] + stones[i];
			}
		}
		
		
		// initialize
		for(int i = 0; i<n; i++) {
			M[i][i] = 0;
		}
		
		printMatrix(M);
		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;  // i + len - 1 <= n - 1  i <= n - len
				int curMin = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					curMin = Math.min(curMin, M[i][k] + M[k + 1][j] + getRange(preSum, i, j));
				}
				M[i][j] = curMin;
			}
		}
		printMatrix(M);
		return M[0][n-1];
	}
	
	public static int getRange(int[] preSum, int i, int j) {
		if (i == 0) {
			return preSum[j];
		} else {
			return preSum[j] - preSum[i - 1];
		}
	}
	public static void test() {
		int[] stones = {4,3,3,4};
		int minCost = minCost(stones);
		System.out.println("minCost = " + minCost);
	}
	public static void printMatrix(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

}

package dynamic_programming;

public class Set30_OptimalBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
	 */
	
	// recursion
	public static int optBST(int[] freq) {
		return optCostRec(freq, 0, freq.length - 1);
	}
	
	public static int optCostRec(int[] freq, int i, int j) {
		if (i > j) {
			return 0;
		}
		if (i == j) {
			return freq[i];
		}
		int sum = getSum(freq, i, j);
		int min = Integer.MAX_VALUE;
		for (int r = i; r <= j; r++) {
			int cost = optCostRec(freq, i, r - 1) + optCostRec(freq, r+1, j);
			System.out.println("cost = " + cost);
			if (min > cost) {
				min = cost;
			}
		}
		System.out.println("min + sum = " + (min + sum));
		return min + sum;
	}
	
	
	
	public static int getSum(int[] freq, int i, int j) {
		int sum = 0;
		for (int k = i; k <= j; k++) {
			sum += freq[k];
		}
		return sum;
	}
	
	public static int optCostDP(int[] freq) {
		int n = freq.length;
		int[][] cost = new int[n][n];
		// cost[i][j] stands optCost(i,j) i <= j;
		
		//initiazlie 
		for (int i = 0; i < n; i++) {
			cost[i][i] = freq[i];
		}
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++) {
				System.out.print(cost[i][j] + " ");
			}
			System.out.println();
		}
		
		// cost[0][n-1] will store the final result
		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len ; i++) {
				int j = i + len - 1;   //i + len - 1 <= n - 1  i <= n - len
				int min = Integer.MAX_VALUE;
				for (int r = i; r <= j; r++) {
					int c = 0 ;
					if (r > i) {
						c += cost[i][r-1];
					}
					if (r < j) {
						c += cost[r+1][j];
					}
					c += getSum(freq, i, j);
					if (min > c) {
						min = c;
					}
				}
				cost[i][j] = min;
			}
		}
		
		System.out.println("-------------------");
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++) {
				System.out.print(cost[i][j] + " ");
			}
			System.out.println();
		}
		return cost[0][n-1];	
	}
	
	public static void test() {
		int[] f1 = {34, 8, 50};
		int minCost1 = optBST(f1);
		int minCost2 = optCostDP(f1);
		
		System.out.println("minCost1 = " + minCost1);
		System.out.println("minCost2 = " + minCost2);
	}

}

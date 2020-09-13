package dynamic_programming;

public class Set18_Partitionproblem {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
	 * 
	 * Partition problem is to determine whether a given set can be partitioned
	 * into two subsets such that the sum of elements in both subsets is same.
	 * 0-1 knapsack
	 */

	/*
	 * Following are the two main steps to solve this problem: 
	 * 1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum,
	 * so return false. 
	 * 2) If sum of array elements is even, calculate sum/2 and
	 * find a subset of array with sum equal to sum/2.
	 */
	// whether a subset of arr[0..n-1] can reach the sum 
	// isSubsetSum(a, n, sum) = including the a[n-1]  isSubset(a, n-1, sum - a[n-1])  
    //							|| excluding the a[n-1] isSubset(a, n-1, sum)
	public static boolean isSubsetSum(int[] arr, int n, int sum) {
		//base case
		if(sum == 0) {
			return true;
		}
		if (sum > 0 && n == 0) {
			return false;
		}
		if (sum < 0) {
			return false;
		}
		//if the last elem of arr[0..n-1] > sum, ignore it
		if (arr[n-1] > sum) {
			return isSubsetSum(arr, n-1, sum);
		}
		return isSubsetSum(arr, n- 1, sum) || isSubsetSum(arr, n - 1, sum - arr[n-1]);
	}
	
	public static boolean partition(int[] a) {
		if (a == null || a.length == 0) {
			return true;
		}
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		if (sum %2 == 1) {
			return false;
		}
		int n = a.length;
		return isSubsetSum(a, n, sum/2);
	}
	
	public static void test() {
		int[] a = {1,2,3,4,5,6,7};
		boolean rev = partition(a);
		boolean rev2 = partitionDP(a);
		System.out.println("rev = " + rev);
		System.out.println("rev2 = " + rev2);
	}
	
	/*
	 * f[i][j] subset of a[0..i-1] can compose sum of j
	 * f[i][j] = including a[i-1]  f[i-1][j-a[i-1]]    condition j > a[i-1]
	 *         ||excluding a[i-1]  f[i-1][j]
	 * initialize
	 * f[n + 1][sum + 1]
	 * f[0][j] = false
	 * f[i][0] = true
	 * 
	 */
	/*
	 * f[i][j] subset of a[0..j-1] can compose sum of i
	 * f[i][j] = f[i - a[j-1]][j-1]  including a[j-1]
	 *         = f[i][j-1]           excluding a[j-1]
	 *         
	 * f[sum + 1][ n + 1]
	 * 
	 * f[i][0] = false;
	 * f[0][j] = true;
	 */

	
	public static boolean partitionDP(int[] a) {
		int n = a.length;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		if (sum % 2 == 1) {
			return false;
		}
		boolean[][] f = new boolean[sum/2 + 1][n + 1];
		//initialize
		for (int i = 1; i < f.length; i++) {
			f[i][0] = false;
		}
		for (int j = 0; j < f[0].length; j++) {
			f[0][j] = true;
		}
		
		
		for (int i = 1; i <= sum/2; i++) {
			for (int j = 1; j <= n; j++) {
				f[i][j] = f[i][j-1];
				if (i >= a[j-1]) {  // !!! NOTE here, it must be i >= a[j-1] 
					f[i][j] = f[i][j] || f[i - a[j-1]][j-1];
				}
			}
		}
		
		
		return f[sum/2][n];
	}
	

}

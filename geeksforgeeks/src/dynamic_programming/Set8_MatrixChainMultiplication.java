package dynamic_programming;

public class Set8_MatrixChainMultiplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
	 */
	
	// Input: int[] p  matrix Ai can be represent as p[i-1] * p[i]
	// Output: the most efficient way to multiply these matrixes together
	
	// e.g Input: p[] = {40, 20, 30, 10, 30} stands the matrixes 40*20  20*30  30*10  10*30
	//     Output: 
	
	// matrix Ai is p[i-1] * p[i]
	// matrixChainOrder(p, 1, n-1)
	public static int matrixChainOrder(int[] p, int i, int j) {
		// base case
		if (i == j) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		int count = 0;
		for(int k = i; k < j; k++) {
			count = matrixChainOrder(p, i, k) + matrixChainOrder(p, k+1, j) + p[i-1] * p[k] * p[j];
			
			if (min > count) {
				min = count;
			}
		}
		return min;
	}
	
	public static void test() {
		int[] p = {40, 20, 30, 10, 30};
		int n = p.length - 1;
		int min = matrixChainOrder(p, 1, n);
		int min2 = matrixChainOrderDP(p);
		
		System.out.println("min = " + min);
		System.out.println("min2 = " + min2);
	}
	
	public static int matrixChainOrderDP(int[] p) {
		int n = p.length;
		
		int[][] f = new int[n][n];
		// f[i][j] stands for the minimum of product from matrix(i) to matrix(j)
		// f[i][j] = Min(f[i][k] + f[k+1][j] + p[i-1]* p[k] * p[j])  (i <= k < j)
		for (int i = 1; i < n; i++) {
			f[i][i] = 0;
		}
		// max matrix len = n - 1
		// i = 1, len, j = i + len - 1 since  j <= n - 1, i + len - 1 <= n - 1 ==> i <= n - len
		for (int len = 2; len <= n - 1; len++) {
			for (int i = 1; i <= n - len; i++) {
				int j = i + len - 1;
				
				f[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int count = f[i][k] + f[k + 1][j] + p[i-1]*p[k]*p[j];
					if (f[i][j] > count) {
						f[i][j] = count;
					}
				}
			}
		}
		return f[1][n-1];
	}
	
}

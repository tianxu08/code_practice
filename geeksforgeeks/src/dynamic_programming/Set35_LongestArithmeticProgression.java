package dynamic_programming;

public class Set35_LongestArithmeticProgression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	/*
	 * http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
	 */
	
	// naive method
	public static int LLAP(int[] a) {
		int longest = Integer.MIN_VALUE;
		
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				int count = 2;
				int first = a[i];
				int second = a[j];
				int dif = second - first;
				int tail = second;
				for (int k = j+1; k < a.length; k++) {
					if (a[k] - tail == dif) {
						count ++;
						tail = a[k];
					}
				}
				if (longest < count) {
					longest = count;
				}
			}
		}
		return longest;
	}
	public static void test1() {
		int[] a = {1, 7, 10, 15, 27, 29};
		int longest = LLAP(a);
		int llap = LLAP_dp(a);
		System.out.println("longest = " + longest);
		System.out.println("llap = " + llap);
		
		int[] a2 = {5, 10, 15, 20, 25, 30};
		int longest2 = LLAP(a2);
		int llap2 = LLAP_dp(a2);
		System.out.println("longest2 = " + longest2);
		System.out.println("llap = " + llap2);
		
		
		int[] a3 = {1, 7, 10, 13, 14, 19};
		int[] a4 = {1, 7, 10, 15, 27, 29};
		int[] a5 = {2, 4, 6, 8, 10};
		int llap3_1 = LLAP(a3);
		int llap3_2 = LLAP_dp(a3);
		System.out.println("llap3_1 = " + llap3_1  );
		System.out.println("llap3_2 = " + llap3_2);
		
		int llap4_1 = LLAP(a4);
		int llap4_2 = LLAP_dp(a4);
		System.out.println("llap4_1 = " + llap4_1  );
		System.out.println("llap4_2 = " + llap4_2);
		
		int llap5_1 = LLAP(a5);
		int llap5_2 = LLAP_dp(a5);
		System.out.println("llap5_1 = " + llap5_1);
		System.out.println("llap5_2 = " + llap5_2);
		
		
		
	}
	
	// a simple solution
	// give a set, find if there exist three elements in Arithmetic Progression or not
	public static boolean exist3ElemAP(int[] a) {
		for (int j = 1; j < a.length - 1; j++) {
			int i = j - 1;
			int k = j + 1;
			while(i >= 0 && k < a.length) {
				if (a[i] + a[k]  == 2 * a[j]) {
					return true;
				} else if (a[i] + a[k] > 2 * a[j]) {
					// we need a smaller one. 
					i--;
				} else {
					// we need larger sum
					k ++;
				}
			}
		}
		return false;
	}
	
	// DP
	/*
	 * f[i][j]  the length set[i] and set[j] as the first two elements of AP and j > i
	 * f[i][n-1] = 2;    to the last element, the longest length is 2
	 * 
	 */
	
	public static int LLAP_dp(int[] a) {
		int n = a.length;
		if (n <= 2) {
			return n;
		}
		
		int[][] f = new int[n][n];
		
		//initialize
		for (int i = 0; i < f.length; i++) {
			for (int j = i + 1; j < f.length; j++) {
				f[i][j] = 2;
			}
		}
		
		int llap = Integer.MIN_VALUE;
		for (int j = n-2; j > 0; j--) {
			int i = j - 1;
			int k = j + 1;
			while(i >= 0 && k <= n - 1) {
				if (a[i] + a[k] < 2 * a[j]) {
					// we need larger one
					k ++;
				} else if (a[i] + a[k] > 2 * a[j]) {
					i --;
				} else {
					// found a[i] + a[k] == 2 * a[j]
					f[i][j] = f[j][k] + 1;
					
					llap = Math.max(llap, f[i][j]);
					i --;
					k ++;
				}
			}
		}
		
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[0].length; j++) {
				System.out.print(f[i][j] + " ");
			}
			System.out.println();
		}
		return llap;
	}
	
	
	
	

}

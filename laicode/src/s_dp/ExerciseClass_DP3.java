package dp;

public class ExerciseClass_DP3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	
	
	
	public static void test1() {
		int[] A = {0,1,1,1,0,1,0};
		int maxLen = longestConsectuive1s(A);
		System.out.println("maxLen = " + maxLen);
	}
	public static int longestConsectuive1s(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int maxLen = 0;
		int n = A.length;
		int[] M = new int[n];
		M[0] = A[0] == 0 ? 0 : 1;
		for (int i = 1; i < n; i++) {
			if (A[i] == 0) {
				M[i] = 0;
			} else {
				if (A[i - 1] == 1) {
					M[i] = M[i - 1] + 1;
				} else {
					M[i] = 1;
				}
			}
			maxLen = Math.max(maxLen, M[i]);
		}
		return maxLen;
	}
	
	public	static int longestConsectuive1s2(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int maxLen = 0;
		int curLen = 0;
		for(int i = 0; i < A.length; i++) {
			if (A[i] == 1) {
				curLen ++;
			} else {
				curLen = 0;
			}
			maxLen = Math.max(maxLen, curLen);
		}
		return maxLen;
	}

}

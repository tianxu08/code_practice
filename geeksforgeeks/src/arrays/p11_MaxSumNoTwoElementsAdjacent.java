package arrays;

public class p11_MaxSumNoTwoElementsAdjacent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static int maxSumNoTwoEleAdj(int[] a) {
		int max_including = 0;
		int max_excluding = 0;
		for (int i = 0; i < a.length; i++) {
			int new_max_excluding = Math.max(max_including, max_excluding);
			
			max_including = max_excluding + a[i];
			max_excluding = new_max_excluding;
		}
		
		return Math.max(max_including, max_excluding);
	}
	
	public static void test() {
		int[] a = {5,  5, 10, 40, 50, 35};
		int rev = maxSumNoTwoEleAdj(a);
		System.out.println("rev = " + rev);
	}

}

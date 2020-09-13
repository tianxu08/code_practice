package arrays;

public class p12_LargestSumContiguousSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	
	//sliding window
	public static int largestSumContiguousSubarray(int[] a) {
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			// if the sum < 0 , we set it as 0. 
			if (sum < 0) {
				sum = 0;
			}
			sum += a[i];
			if (maxSum < sum) {
				maxSum = sum;
			}
		}
		return maxSum;
	}
	
	public static void test1() {
		int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		int maxSum = largestSumContiguousSubarray(a);
		int maxSum2 = largetSumContigousSubarray2(a);
		System.out.println("maxSum = " + maxSum);
		System.out.println("maxSum2 = " + maxSum2);
	}
	
	// prefix sum
	public static int largetSumContigousSubarray2(int[] a) {
		int maxSum = Integer.MIN_VALUE;
		int minSum = 0; 
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			maxSum = Math.max(maxSum, sum - minSum);
			minSum = Math.min(minSum, sum);
		}
		return maxSum;
	}

}

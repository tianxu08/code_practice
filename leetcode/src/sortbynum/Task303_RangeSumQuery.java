package sortbynum;

public class Task303_RangeSumQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int[] preSum;

	public Task303_RangeSumQuery(int[] nums) {
		int n = nums.length;
		preSum = new int[n];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				preSum[i] = nums[i];
			} else {
				preSum[i] = preSum[i - 1] + nums[i];
			}
		}
	}

	public int sumRange(int i, int j) {
		if (i == 0) {
			return preSum[j];
		} else {
			return preSum[j] - preSum[i - 1];
		}
	}

}

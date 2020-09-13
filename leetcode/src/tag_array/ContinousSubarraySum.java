package tag_array;
import java.util.*;

public class ContinousSubarraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test3();
	}
	
	
	/*
	 * t1
	 * find subarray with given sum
	 * input: unsorted array of no-negative integers. 
	 * find a coutinious subarray which adds to a given number
	 * 
	 * we can use sliding window for non-negative integers
	 */
	public static void t1_subArraySum(int[] nums, int sum){
		int cur_sum = nums[0];
		int start = 0;
		int i = 0;
		for(i = 1; i <= nums.length; i ++) {
			
			// while cur_sum > sum, shrink the window
			while(cur_sum > sum && start < i - 1) {
				cur_sum = cur_sum - nums[start];
				start ++;
			}
			
			if (cur_sum == sum) {
				int end = i - 1;
				System.out.println("sum found between: " + start + " : " + end);
				return ;
			}
			
			// add current element to cur_sum
			if (i < nums.length) {
				cur_sum = cur_sum + nums[i];
			}
		}
		System.out.println("no subarray found");
	}

	/*
	 * t2
	 * input: usorted array of integers (can be negative)
	 */
	public static void t2_subArraySum(int[] nums, int sum) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// key-value: sum of nums[0..ending_idx]: ending index
		int cur_sum = 0;
		for(int i = 0; i < nums.length; i ++) {
			cur_sum = cur_sum + nums[i];
			
			if (cur_sum == sum) {
				System.out.println("subarray found between " + "0" + " and " + i);
				return ;
			}
			
			// B_cur_sum - A_cur_sum == sum
			if (map.containsKey(cur_sum - sum)) {
				System.out.println("subarray found between " + (map.get(cur_sum) + 1) +  " " + i);
				return ;
			}
			// put current sum and its index into map
			map.put(cur_sum, i);
		}
	}
	
	
	/* t3
	 * LC560. Subarray Sum Equals K
	 * Given an array of integers and an integer k, 
	 * you need to find the total number of continuous subarrays whose sum equals to k.
	 * 
	 * https://leetcode.com/articles/subarray-sum-equals-k/
	 * 
	 */
	public static void test3() {
		int[] nums = {1,1,1};
		int k = 2;
		int rev = t3_subarraySum(nums, k);
		System.out.println("rev = " + rev);
	}
	/*
	 * Time: O(n)
	 * Space: O(n)
	 */
	public static int t3_subarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		// key-value: presum -- counter of the presum
		int cur_sum = 0;
		int count = 0;
		map.put(0, 1);
		for(int i = 0; i < nums.length; i++) {
			cur_sum = cur_sum + nums[i];

			if (map.containsKey(cur_sum - k)) {
				count += map.get(cur_sum - k);
			}
			if (map.containsKey(cur_sum)) {
				map.put(cur_sum, map.get(cur_sum) + 1);
			} else {
				map.put(cur_sum, 1);
			}
		}
		return count;
	}
	
	/*
	 * t4 LC523 Given a list of non-negative numbers and a target integer k,
	 * 
	 * check if the array has a continuous subarray of size at least 2 that sums
	 * up to the multiple of k, that is, sums up to n*k where n is also an
	 * integer.
	 * 
	 * Input: [23, 2, 4, 6, 7], k=6 Output: True Explanation: Because [2, 4] is
	 * a continuous subarray of size 2 and sums up to 6.
	 * 1、处理k为0的情况；
	 * 2、用HashMap保存sum对k取余数，如果前序有余数也为sum % k的位置，那么就存在连续子数组和为k的倍数
	 */
	public boolean checkSubarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			Integer prev = k == 0 ? map.get(sum) : map.get(sum % k);
			if (prev != null) {
				if (i - prev > 1) { // subarray size at least 2
					return true;
				}
			} else {
				if (k == 0) {
					map.put(sum, i);
				} else {
					map.put(sum % k, i);
				}
			}
		}
		return false;
	}
	
	
}

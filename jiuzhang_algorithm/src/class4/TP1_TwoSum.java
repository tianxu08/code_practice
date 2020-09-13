package class4;
import java.util.*;

public class TP1_TwoSum {

	/*
	 * 2 Sum 类
	 * 3 Sum Closest
	 * 4 Sum
	 * 3 Sum
	 * 2 Sum II
	 * Triangle Count
	 * Trapping Rain Water
	 * Container With Most Water
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
		test3();
	}
	
	
	/*
	 * a + b > c
	 * for each c, find two sum > c
	 * 
	 * sort first
	 */
	public static void test2() {
		int[] array = {3,4,6,7};
		int count = triangleCount(array);
		System.out.println("count = " + count);
	}
	
	public static int triangleCount(int[] array) {
		if (array == null || array.length <= 3) {
			return 0;
		}
		Arrays.sort(array);
		int counter = 0;
		for(int i = 2; i < array.length; i ++) {
			int left = 0, right = i - 1;
			while(left < right) {
				if (array[left] + array[right] > array[i]) {
					counter += right - left;
					right --;
				} else {
					left ++;
				}
			}
		}
		return counter;
	}
	
	public static int trappingRainWater(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int leftMax = array[0];
		int rightMax = array[n -1];
		int i = 0, j = n - 1;
		int sum = 0;
		while(i <= j) {
			leftMax = Math.max(leftMax, array[i]);
			rightMax = Math.max(rightMax, array[i]);
			
			if (leftMax < rightMax) {
				sum += Math.max(0, leftMax - array[i]);
				i ++;
			} else {
				sum += Math.max(0, rightMax - array[i]);
				j --;
			}
		}
		return sum;	
	}
	
	
	
	
	
	/*
	 * Partition 类
	 * Partition-array
	 * Sort Colors
	 * Partition Array By Odd and Even
	 * Sort Letter by Case
	 * Valid Palindrome
	 */
	
	public static void test3() {
		int[] nums = {1,2,3,4,5,6,7};
		for(int i = 0; i < nums.length; i ++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
		partitionArrayOddEven(nums);
		for(int i = 0; i < nums.length; i ++) {
			System.out.print(nums[i] + " ");
		}
		
	}
	
	public static void partitionArrayOddEven(int[] nums) {
        // write your code here;
		int left = 0, right = nums.length - 1;
		
		while(left <= right) {
			if (nums[left]% 2 == 0) {
				left ++;
			} else if (nums[right] % 2 == 1) {
				right --;
			} else {
				swap(nums, left, right);
				left ++;
				right --;
			}
		}
    }
	
	public static void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
	
	
	
	
	
	
	
	
	
	
}

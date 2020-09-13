package tag_math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import ds.Test;



public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1_1();
//		test1_2();
//		test1_3();
		test1_4();
	}
	
	/*
	 * permutation1.1
	 * 
	 * No Duplicate
	 * result in order
	 */
	public static void test1_1() {
		int[] nums = {1,2,3};
		for(List<Integer> list : permutation1_1(nums)) {
			System.out.println(list);
		}
	}
	
	
	public static List<List<Integer>> permutation1_1(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		boolean[] used = new boolean[nums.length];
		List<Integer> curResult = new ArrayList<Integer>();
		helper1_1(nums, used, curResult, result);
		return result;
		
	}
	public static void helper1_1(int[] nums,  boolean[] used, 
			List<Integer> curResult, List<List<Integer>> result) {
		if (curResult.size() == nums.length) {
			// get a result
			System.out.println("curResult");
			System.out.println(curResult);
			result.add(new ArrayList<Integer>(curResult));
			return ;
		}
		for(int i = 0; i < nums.length; i ++) {
			if (used[i] == true) {
				continue;
			}
			curResult.add(nums[i]);
			used[i] = true;
			helper1_1(nums, used, curResult, result);
			//
			used[i] = false;
			curResult.remove(curResult.size() - 1);
		}
	}
	
	
	
	/*
	 * Permutation1.2
	 * with Duplicate
	 * result in order
	 */
	public static void test1_2() {
		int[] nums = {1,2,2};
		for(List<Integer> list: permutation1_2(nums)) {
			System.out.println(list);
		}
		
	}
	public static List<List<Integer>> permutation1_2(int[] nums) {
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		boolean[] used = new boolean[nums.length];
		Arrays.sort(nums);
		List<Integer> curResult = new ArrayList<Integer>();
		helper1_2(nums, used, curResult, result);
		return result;
	}
	public static void helper1_2(int[] nums,  boolean[] used, 
			List<Integer> curResult, List<List<Integer>> result) {
		if (curResult.size() == nums.length) {
			// get a result
			
			result.add(new ArrayList<Integer>(curResult));
			return ;
		}
		for(int i = 0; i < nums.length; i ++) {
			// if used[i] is true
			// or
			// if the current element has duplicate and NOT the first time visited. 
			if (used[i] == true || (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true )) {
				continue;
			}
			curResult.add(nums[i]);
			used[i] = true;
			helper1_2(nums, used, curResult, result);
			//
			used[i] = false;
			curResult.remove(curResult.size() - 1);
		}
	}
	
	/*
	 * Permutation1.3
	 * No Duplicate
	 * result NOT required in Order
	 *
	 */
	public static void test1_3() {
		int[] nums = {1,2,3};
		List<List<Integer>> result = permutation1_3(nums);
		System.out.println(result);
	}
	public static List<List<Integer>> permutation1_3(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		helper1_3(nums, 0, result);
		return result;
	}
	public static void helper1_3(int[] nums, int index, List<List<Integer>> result) {
		if (index == nums.length) {
			List<Integer> curResult = new ArrayList<Integer>();
			for(int i = 0; i < nums.length; i++) {
				curResult.add(nums[i]);
			}
			result.add(curResult);
			return ;
		}
		
		for(int i = index; i < nums.length; i ++) {
			swap(nums, i, index);
			helper1_3(nums, index + 1, result);
			swap(nums, i, index);
		}
		
	}
	
	public static void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
	
	/*
	 * Permutation1.4
	 * with Duplicate
	 * result NOT required in Order
	 */
	public static void test1_4() {
		int[] nums = {1,2,2};
		List<List<Integer>> result = permutation1_4(nums);
		System.out.println(result);
	}
	
	
	public static List<List<Integer>> permutation1_4(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		helper1_4(nums, 0, result);
		
		return result;
	}
	
	public static void helper1_4(int[] nums, int index, List<List<Integer>> result) {
		if (index == nums.length) {
			List<Integer> curResult = new ArrayList<Integer>();
			for(int i = 0; i < nums.length; i++) {
				curResult.add(nums[i]);
			}
			result.add(curResult);
			return ;
		}
		HashSet<Integer> used = new HashSet<Integer>();
		for(int i = index; i < nums.length; i ++) {
			if (used.contains(nums[i])) {
				continue;
			}
			used.add(nums[i]);
			swap(nums, i, index);
			helper1_4(nums, index + 1, result);
			swap(nums, i, index);
		}
	}
	
	
	/*
	 * 60.Permutation sequence
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.
	 * By listing and labeling all of the permutations in order,
	 * We get the following sequence (ie, for n = 3):
	 * "123"
	 * "132"
	 * "213"
	 * "231"
	 * "312"
	 * "321"
	 * Given n and k, return the kth permutation sequence.
	 * Note: Given n will be between 1 and 9 inclusive.
	 */
	
	
	public static String task60_getPermutation(int n, int k) {
        if (n <= 0) {
			return "";
		}
        int factor = 1;
        ArrayList<Integer> candidate_list = new ArrayList<Integer>();
        StringBuilder stb = new StringBuilder();
        for(int i = 1; i <= n; i ++ ) {
        	factor = factor * i;
        	candidate_list.add(i);
        }
        
        k --;
        for(int i = 0; i < n; i ++) {
        	factor = factor / (n - i);
        	int curIndex = k / factor;
        	stb.append(candidate_list.get(curIndex));
        	// remove the element in curIndex
        	candidate_list.remove(curIndex);
        	// update k
        	k = k % factor;
        }
        return stb.toString();
    }

}

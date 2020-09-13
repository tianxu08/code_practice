package small_sun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Class2_10112014 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
//		test1_1();
	}
	
	/*
	 * Q1  Given a sequence of integers [1, 1, 2, 2, 3, 3, ...., n - 1, n - 1, n, n] 
	 * please shuffle the input integer array, 
	 * such that the output integer array satisfy this condition: 
	 * between each two i's, they are exactly i integers 
	 * 
	 * (for example: between the two 1s, there is one number, between the two 2's there are two numbers) 
	 * example n = 3, input 1, 1, 2, 2, 3, 3 output 2, 3, 1, 2, 1, 3
	 * 
	 * !!! 
	 * add first element of the array to result 
	 * then call the next layer we can add second element ... to the end of the array .
	 */
	public static void test1() {
		int[] array = {1,1,2,2,3,3,4,4,5,5,6,6,7,7};
		ArrayList<ArrayList<Integer>> result = task1_shuffle(array);
		System.out.println(result);
		task1_shuffler2(array);
	}
	public static ArrayList<ArrayList<Integer>> task1_shuffle(int[] array) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		boolean[] visit = new boolean[array.length];
		HashSet<Integer> set = new HashSet<Integer>();
		ArrayList<ArrayList<Integer>> finalResult = new ArrayList<ArrayList<Integer>>();
		dfs(array, result, finalResult, visit, set);
		
		return finalResult;
	}
	
	public static boolean found = false;
	public static void dfs(int[] array, ArrayList<Integer> result,
			ArrayList<ArrayList<Integer>> finalResult,  boolean[] visit, HashSet<Integer> set) {
		if (found) {
			return;
		} else {
			
			if (result.size() == array.length) {
				finalResult.add(new ArrayList<Integer>(result));
				found = true;
				return ;
			}
			
			for(int i = 0; i < array.length; i ++) {
				if (!visit[i]) {
					if (set.isEmpty() || !set.contains(array[i])) {
						// the first element
						visit[i] = true;
						result.add(array[i]);
						set.add(array[i]);
						dfs(array, result,finalResult, visit, set);
						int toDel = result.get(result.size() - 1);
						result.remove(result.size() - 1);
						visit[i] = false;
						set.remove(toDel);
					} else {
						// the second element
						if (array[i] <= result.size() - 1 && 
								array[i] == result.get(result.size() - 1 - array[i])) {
							// 
							visit[i] = true;
							result.add(array[i]);
							
							dfs(array, result, finalResult,visit, set);
							
							result.remove(result.size() - 1);
							visit[i] = false;
						}
					}
					
				}
			}
		}
	}
	
	public static void test1_1() {
		int[] array = {1,1,2,2,3,3,4,4,5,5,6,6,7,7};
		task1_shuffler2(array);
	}
	// a simpler way, but this method is wrong.
	// to do more investigation 
	public static void task1_shuffler2(int[] array) {
		if (array == null || array.length == 0) {
			return ;
		}
		shuffleHelper(array, 0);
	} 
	
	public static void shuffleHelper(int[] array, int index) {
		if (index == array.length) {
			System.out.println(Arrays.toString(array));
			return ;
		}
		for(int i = index; i < array.length; i ++) {
			if (task1_isValid(array, i, index)) {
				swap(array, index, i);
				shuffleHelper(array, index + 1);
				swap(array, index, i);
			}
		}
	}
	
	public static boolean task1_isValid(int[] array, int i ,int index) {
		boolean result = false;
		swap(array, index, i);
		if (i - 1 - array[i] <0 || array[i - 1 - array[i]] == array[i]) {
			result = true;
		}
		swap(array, index, i);
		return result;
	}
	
	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	
	
	/*
	 * Q2 Find common elements in 3 sorted arrays.
	 */
	public static void test2() {
		return ;
	}
	public static ArrayList<Integer> task2_common_elems_3_arrays(int[] array1, int[] array2, int[] array3) {
		// sanity check
		if (array1 == null || array2 == null || array3 == null || 
				array1.length == 0 || array2.length == 0 || array3.length == 0) {
			return new ArrayList<Integer>();
		}
		int i = 0, j = 0, k = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while(i < array1.length && j < array2.length && k < array3.length) {
			if (array1[i] == array2[j]  && array2[j]== array3[k]) {
				result.add(array1[i]);
				i ++;
				j ++;
				k ++;
			} else if (array1[i] <= array2[j] && array1[i] <= array3[k]) {
				// array1[i] is the smallest
				i ++;
			} else if (array2[j] <= array1[i] && array2[j] <= array3[k]) {
				// array2[j] is the smallest
				j ++;
			} else {
				k ++;
			}
		}
		return result;
	}
	
	/*
	 * Q3   Given a sorted array (sorted in nondecreasing order) of positive numbers, 
	 * find the smallest positive integer value that 
	 * cannot be represented as sum of elements of any sub­sequence of the given array.
	 * 
	 * Input: arr[] = {1, 3, 6, 10, 11, 15}; Output: 2
	 * Input: arr[] = {1, 1, 1, 1}; Output: 5
	 * Input: arr[] = {1, 1, 3, 4}; Output: 10
	 * Input: arr[] = {1, 2, 5, 10, 20, 40}; Output: 4
	 * Input: arr[] = {1, 2, 3, 4, 5, 6}; Output: 22
	 *
	 * method1: 
	 * (1)find all subset of array, get their sum, and put it into hash map
	 * (2)while(i++) {
	 * 	 if not in the hashMap, then return i;
	 * }
	 * O(n^n)
	 * 
	 * method2:
	 * http://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
	 * More researching
	 */
 
	
	
	
	
	
	
	
	
	
	

}

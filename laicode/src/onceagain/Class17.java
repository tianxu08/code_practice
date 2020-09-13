package onceagain;

import java.util.*;

public class Class17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test5();
	}
	
	/*
	 * task1 Shuffle
	 */
	public static void task1_shuffle(int[] array) {
		if (array == null || array.length == 0) {
			return ;
		}
		for(int i = 0; i < array.length; i ++) {
			int random = (int)(Math.random() * (array.length - i) )+ i;
			swap(array, random, i);
		}
	}
	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	/*
	 * task2
	 * 
	 * Consider an unlimited flow of data elements. 
	 * How do you sample one element from this flow, 
	 * such that at any point during the processing of the flow, 
	 * you can return a random element from the n elements read so far.
	 * You will implement two methods for a sampling class:
	 * 
	 * read(int value) - read one number from the flow
	 * sample() - return at any time the sample,
	 *  
	 * if n values have been read, the probability of returning any one of the n values is 1/n, 
	 * return null(Java)/INT_MIN(C++) if there is no value read so far
	 * You may need to add more fields for the class.
	 */
	
	/*
	 * task3
	 * Random 7 using Random 5
	 */
	public static int task3_Random7() {
		while(true) {
			int rand = 5 * task3_Random5() + task3_Random5();
			if (rand < 7) {
				return rand;
			}
		}
	}
	
	public static int task3_Random5() {
		return -1;
	}
	
	/*
	 * task4
	 * Median Tracker of data flow
	 */
	
	/*
	 * task5
	 * 95 percentile
	 * 
	 * Given a list of integers representing the lengths of urls, 
	 * find the 95 percentile of all lengths (95% of the urls have lengths <= returned length).
	 * Assumptions
	 * The maximum length of valid url is 4096
	 * The list is not null and is not empty and does not contain null
	 * Examples
	 * [1, 2, 3, ..., 95, 96, 97, 98, 99, 100], 95 percentile of all lengths is 95.
	 */
	public static int task5_95Percentile(List<Integer> lengths) {
		int[] count = new int[4097];
		for(Integer len : lengths) {
			count[len] ++;
		}
		int sum = 0;
		int len = 4097;
		while(sum <= 0.05 * lengths.size()) {
			sum += count[--len];
		}
		return len;
	}
	
	public static void test5() {
		List<Integer> lengths = new ArrayList<Integer>();
		for(int i = 1; i <= 100; i ++) {
			lengths.add(i);
		}
		int rev = task5_95Percentile(lengths);
		System.out.println("rev = " + rev);
	}

}

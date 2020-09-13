package lai_online;

import java.util.*;

public class Class16_Math {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test6();
	}
	
	/*
	 * task1: Median Tracer of Flow
	 * task2: 95th percentile
	 * 
	 * task3: Shuffle
	 * task4: Reservoir Sampling
	 * task5: Random7 Using Random5
	 * task6: Random1000 Using Random5
	 * 
	 */

	/*
	 * task1 
	 * Median Tracker Fair Data Structure Given an unlimited flow of
	 * numbers, keep track of the median of all elements seen so far.
	 * 
	 * You will have to implement the following two methods for the class
	 * 
	 * read(int value) - read one value from the flow median() - return the
	 * median at any time, return null if there is no value read so far Examples
	 * 
	 * read(1), median is 1 read(2), median is 1.5 read(3), median is 2 read(4),
	 * median is 2.5 ......
	 */

	/*
	 * task2 
	 * 95 Percentile 
	 * 
	 * Given a list of integers representing the lengths of urls,
	 *  
	 * find the 95 percentile of all lengths (95% of the urls have lengths <= returned length).
	 * 
	 * Assumptions
	 * 
	 * The maximum length of valid url is 4096
	 * 
	 * The list is not null and is not empty and does not contain null
	 * 
	 * Examples
	 * 
	 * [1, 2, 3, ..., 95, 96, 97, 98, 99, 100],
	 * 95 percentile of all lengths is
	 * 
	 * 95.
	 * 
	 * 
	 * <key = length, value = counter>
	 * <100, 1>
	 * <99, 1>
	 * <98, 1>
	 * etc
	 * 
	 * sum = 0; 
	 * while(sum <= 0.05 * (total urls number))
	 *    sum += count[--length];
	 * 
	 * return length
	 * 
	 */
	public int percentile95(List<Integer> lengths) {
		// write your solution here
		int[] count = new int[4097];
		for (Integer i : lengths) {
			count[i]++;
		}
		int sum = 0;
		int length = 4097;
		while (sum <= 0.05 * (lengths.size())) {
			sum += count[--length];
		}
		return length;
	}

	/*
	 * task3 
	 * Perfect Shuffle Fair Probability Given an array of integers
	 * (without any duplicates), shuffle the array such that all permutations
	 * are equally likely to be generated.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null
	 */

	public static void shuffle(int[] array) {
		// write your solution here
		int n = array.length;
		for (int i = 0; i < n; i++) {
			int k = rand(0, i);
			swap(array, i, k);
		}
	}

	/*
	 * generate a random [low, high]
	 */
	public static int rand(int low, int high) {
		return low + (int) (Math.random() * (high - low + 1));
	}

	public static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	/*
	 * task4
	 * 
	 * Reservoir Sampling Fair Probability 
	 * Consider an unlimited flow of data
	 * elements. How do you sample one element from this flow, 
	 * such that at any point during the processing of the flow, 
	 * you can return a random element from the n elements read 
	 * so far.
	 * 
	 * You will implement two methods for a sampling class:
	 * 
	 * read(int value) - read one number from the flow 
	 * 
	 * sample() - return at any time the sample, if n values have been read, the probability of returning
	 * any one of the n values is 1/n, return null if there is no value read so far 
	 * You may need to add more fields for the class.
	 */
	

	/*
	 * task5 Random7 Using Random5 Fair Probability Given a random generator
	 * random5(), the return value of random5() is 0 - 4 with equal probability.
	 * Use random5() to implement random7().
	 */

	public static int random7() {
		// write your solution here
		// you can use RandomFive.random5() for generating
		// 0 - 4 with equal probability.
		 int temp = RandomFive.random5() * 5 + RandomFive.random5();
		 if (temp < 21) {
		 return temp % 7;
		 }
		 return random7();
	}

	public static class RandomFive {
		public static int random5() {
			return (int)(Math.random() * 5);
		}
	}

	/*
	 * task6 Random1000 Using Random5 Fair Probability Given a random generator
	 * random5(), the return value of random5() is 0 - 4 with equal probability.
	 * Use random5() to implement random1000()
	 */
	
	public static void test6() {
//		for(int i = 0; i < 10; i ++) {
//			int random1000 = random1000();
//			System.out.println("random1000 = "  + random1000);
//		}
		
		random1000_2();
		
	}

	public static int random1000() {
		// write your solution here
		// you can use RandomFive.random5() for generating
		// 0 - 4 with equal probability.
		int n = 11;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = random2();
		}
		// now we get the number
		int digit = 1;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] == 1) {
				sum += digit;
			}
			digit *= 2;
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(" sum = " + sum);
		if (sum < 1000) {
			return sum;
		}
		return random1000();
	}

	public static int random2() {
		// return RandomFive.random5()%2;
		return RandomFive.random5() % 2;
	}
	
	
	public  static int random1000_2() {
		
		int n = 5;
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i ++ ) {
			arr[i] = RandomFive.random5();
		}
	
		
		int digit = 1;
		int sum = 0;
		for(int i = 0; i < n; i ++) {
			sum += arr[i] * digit;
			digit *=5;
		}
		
		if (sum < 1000) {
			return sum;
		}
		
		return random1000_2();
	}
	
	

}

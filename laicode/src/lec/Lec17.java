package lec;
import java.util.*;
public class Lec17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * Class 17 Probability, Sampling, Randomization, etc.
	 * 
	 */
	
	/*
	 * Question 1: shuffling algorithm (OOD): 
	 * spades (♠), 		
	 * hearts (♥), 
	 * diamonds (♦) 
	 * clubs (♣)
	 * 
	 * step1: randomly generate an integer from [0..51], assume the value is 16, swap[16, 51]
	 * step2: P(Y = arbitrary card ) = 1/52, randomly generate an integer from [0..50], 
	 *        assume the value is 12, swap[12, 50]
	 *        the probability of any card that was not selected in step1 is: 1 - 1/52 = 51/52
	 *        1/51 * 51/52 = 1/52
	 * 
	 */
	
	public static void shuffle(int[] decks) {
		for (int i = 0; i < decks.length; i++) {
			int k = rand(0, i);
			swap(decks, i, k);
		}
	}
	
	public static int rand(int low, int high) {
		return low + (int)(Math.random() * (high - low + 1));
	}
	
	public static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	
	/*
	 * Question2:
	 * How to do sampling for an unlimited data flow and 
	 * when reading the n-th element we are required to return one random number among all numbers read so far, 
	 * such that the probability of returning any element read so far is 1/n.  
	 */
	
	
	
	
	
	
	/*
	 * Q3a
	 * How to design a random number generator Random(7), with Random(5).
	 * 
	 * 线性不可分，加维数
	 * 
	 * step1: generate two random numbers by using Random(5), x and y
	 * step2: calculate 5 * x + y = Random(25) [0..24]
	 *        if (Random(25) < 21) {
	 *        	return Random(25) % 7;
	 *        }
	 *        else{
	 *        	go to step1
	 *        }
	 */
	public static int random5() {
		return -1;
	}
	
	public static int random7() {
		int temp = 5 * random5() + random5();
		if (temp < 21) {
			return temp % 7;
		}
		return random7();
	}
	
	/*
	 * Q3b How to design a random number generator Random(1,000,000,000), with Random(5).
	 * 
	 * Share the same idea with Q3c
	 */
	
	/*
	 * Q3c Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. 
	 * Use random5() to implement random1000()
	 */
	public int random1000() {
		// write your solution here
		// you can use RandomFive.random5() for generating
		// 0 - 4 with equal probability.
		int[] arr = new int[11];
		for (int i = 0; i < 11; i++) {
			arr[i] = random2();
		}
		// now we get the number
		int digit = 1;
		int sum = 0;
		for (int i = 0; i < 11; i++) {
			if (arr[i] == 1) {
				sum += digit;
			}
			digit *= 2;
		}
		if (sum < 1000) {
			return sum;
		}
		return random1000();
	}

	public int random2() {
		return random5() % 2;
	}
	
	/*
	 * Question 4 : Given an unlimited data flow, how to keep track of the median of the numbers read so far?
	 * Refer to: Lec17_Median.java
	 */
	
	
	/*
	 * Question 5: Given a certain number (100000) of urls, how to find 95-th percentile of all url’s length
	 * 
	 * Assume: the max length of all url- 4000  !!!!!!!  this is really important. 
	 * returned length = x;
	 * 100,000 * 95% = 95,000
	 * 
	 * <key = length, value = counter>
	 * <1, 5>
	 * <2, 7>
	 * <3, 200>
	 * <4, 500>
	 * ...
	 */
	public static int Percentile(List<Integer> lengths) {
		int[] count = new int[4097];
		for(Integer i: lengths) {
			count[i]++;
		}
		int sum = 0;
		int length = 4097;
		while(sum <= 0.05 * (lengths.size())) {
			sum += count[--length];
		}
		return length;
	}
	
	
	/*
	 * Question 6: Given only two dices, we can only put one digit [0 … 9] on a face. 
	 * Then how to represent an arbitrary date in a month [1… 31] by using these two dices.
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package mathematical_algorithms;

import debug.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Part1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test6();
		test8();
//		test9();
//		test11_1();
//		tes t13();
//		test14();
//		test15();
	}
	
	/*
	 * http://www.geeksforgeeks.org/fundamentals-of-algorithms/
	 */
	/*
	 * task1
	 * Write an Efficient Method to Check if a Number is Multiple of 3
	 * http://www.geeksforgeeks.org/write-an-efficient-method-to-check-if-a-number-is-multiple-of-3/
	 */
	
	/*
	 * task2
	 * Efficient way to multiply with 7
	 * http://www.geeksforgeeks.org/efficient-way-to-multiply-with-7/
	 */
	
	/*
	 * task3
	 * 
	 */
	
	/*
	 * task6
	 * Babylonian method for square root
	 * 
	 * !!!
	 * y = 1/2*(x + n/x) = x/2 + n/2x
	 * 
	 * 
	 * y = x/2 + y/2
	 * y = n/x
	 * 
	 * init: 
	 * x = n, y = 1
	 */
	public static void test6() {
		float n = (float)3.5;
		System.out.println(task6_squareroot(n));
	}
	public static float task6_squareroot(float n) {
		float x = n;
		float y = 1;
		float e = (float) 0.00000001;
		while (x - y > e) {
			x = (x + y)/2;
			y = n/x;
		}
		return x;
	}
	
	/*
	 * task7
	 * Multiply two integers without using multiplication, division and bitwise operators, and no loops
	 * 
	 * to multiply x and y, recursively add x, y times
	 */
	public static int task7_multiplyXY(int x, int y) {
		if (y == 0) {
			return 0;
		}
		if (y > 0) {
			return x + task7_multiplyXY(x, y - 1);
		} else {
			return -task7_multiplyXY(x, -y);
		}
	}
	
	/*
	 * task8
	 * Print all combinations of points that can compose a given number
	 * You can win three kinds of basketball points, 1 point, 2 points, and 3 points. 
	 * Given a total score n, print out all the combination to compose n.
	 * 
	 * For n = 1, the program should print following:
	 * 1
	 * For n = 2, the program should print following:
	 * 1 1
	 * 2
	 * For n = 3, the program should print following:
	 * 1 1 1
	 * 1 2
	 * 2 1
	 * 3
	 * 
	 * a typical DFS
	 */
	public static void test8() {
		int n = 3;
		task8_printAllCombination(n);
	}
	
	public static void task8_printAllCombination(int n) {
		if (n < 1) {
			return ;
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		task8_helper(n, path);
		
	}
	public static int MAXPOINT = 3;
	public static void task8_helper(int n, ArrayList<Integer> path) {
		if (n < 0) {
			// !!! Note, n might be < 0
			return ;
		}
		if (n == 0) {
			System.out.println(path);
		} 
		for(int i = 1; i <= MAXPOINT; i ++) {
			path.add(i);
			task8_helper(n - i, path);
			path.remove(path.size() - 1);
		}
	}
	
	/*
	 * task9
	 * Write you own Power without using multiplication(*) and division(/) operators
	 * 
	 * method1:
	 * using nested loops
	 * 5^6
	 * (1) first 5 times, add 5, get 25 (5^2)
	 * (2) then 5 times,  add 25, get 125 (5^3)
	 * (3) then 5 times, add 125, get 625 (5^4)
	 * (4) then 5 times, add 625, get 3125(5^5)
	 * (5) then 5 times, add 3125, get 15625 (5^6)
	 */
	public static void test9() {
		int a = 5, b = 6;
		System.out.println(task9_power(a, b));
	}
	
	public static int task9_power(int a, int b) {
		if (b == 0) {
			return 1;
		}
		int answer = a;
		int increment = a;
		for(int i = 1; i < b; i ++) {
			for(int j = 1; j < a; j ++) {
				answer += increment;
			}
			increment = answer;
		}
		return answer;
	}
	
	/*
	 * task10
	 * http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
	 * Program for Fibonacci numbers
	 * (1) recursion
	 * (2) dp
	 * (3) matrix multiplication
	 */
	
	/*
	 * task11
	 * Average of a stream of numbers
	 * http://www.geeksforgeeks.org/average-of-a-stream-of-numbers/
	 * Given a stream of numbers, print average (or mean) of the stream at every point. 
	 * For example, let us consider the stream as 10, 20, 30, 40, 50, 60, …
	 * 
	 * let n is the count, prev_avg be the previous average and x be the new number added. 
	 * The average after adding x is: (prev_avg * n + x)/(n + 1);
	 */
	
	/*
	 * follow up
	 * task11.1
	 * Median of a stream of numbers. 
	 * 
	 * use two heap. maxHeap and minHeap.
	 * (1)maxHeap.size == minHeap.size or maxHeap.size = minHeap.size + 1
	 * 
	 * the first case, return (maxHeap.peek() + minHeap.peek()) / 2
	 * the second case, return the maxHeap.peek();
	 * 
	 * meanwhile, we need to keep the property of (1)
	 * 
	 */
	public static void  test11_1() {
		int[] a = {6,5,4,3,2,1};
		ArrayList<Integer> input = new ArrayList<Integer>();
		for(int i = 0; i < a.length; i ++) {
			input.add(a[i]);
		}
		System.out.println(input);
		ArrayList<Double> output = task11_1_medianOfStream(input);
		
		System.out.println(output);
	}
	public static ArrayList<Double> task11_1_medianOfStream(ArrayList<Integer> input) {
		ArrayList<Double> output = new ArrayList<Double>();
		if (input == null || input.size() == 0) {
			return output;
		}
	
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		}) ;
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		
		for(int i = 0; i < input.size(); i ++) {
			int cur = input.get(i);
			// add in the maxHeap
			if (maxHeap.isEmpty() || cur < maxHeap.peek() ) {
				maxHeap.add(cur);
			} else {
				// add into minHeap
				minHeap.add(cur);
			}
			
			// adjust, make sure that maxHeap.size == minHeap.size or maxHeap.size == minHeap.size + 1
			if (maxHeap.size() > minHeap.size() + 1) {
				// remove the maxHeap.peek() and add to minHeap
				int temp = maxHeap.poll();
				minHeap.offer(temp);
			} else if (maxHeap.size() < minHeap.size()) {
				// remove the minHeap.peek() and add to maxheap
				int temp = minHeap.poll();
				maxHeap.offer(temp);
			}
			
			// output the result
			if (maxHeap.size() == minHeap.size()) {
				double median = (double) (maxHeap.peek() + minHeap.peek())/2;
				output.add(median);
			} else {
				double median = (double)maxHeap.peek();
				output.add(median);
			}
		}
		System.out.println(output);
		return output;
		
	}
	
	/*
	 * task12
	 * Count numbers that don’t contain 3
	 * Given a number n, write a function that returns count of numbers from 1 to n 
	 * that don’t contain digit 3 in their decimal representation.
	 * http://www.geeksforgeeks.org/count-numbers-that-dont-contain-3/
	 * 
	 * simple 
	 * (1) checkNo3(int num) return true if num doesn't contains 3 in its decimal representation.log_10(n) 
	 * (2) from 1 .. n, run checkNo3()   Time: O(n)
	 * Total: O(n* log n)
	 * 
	 * 
	 */
	
	/*
	 * task13
	 * Magic Square
	 * http://www.geeksforgeeks.org/magic-square/
	 * A magic square of order n is an arrangement of n^2 numbers, usually distinct integers, 
	 * in a square, such that the n numbers in all rows, 
	 * all columns, and both diagonals sum to the same constant. 
	 * A magic square contains the integers from 1 to n^2.
	 * 
	 * 
	 * 1. The position of next number is calculated by decrementing row number of previous number by 1
	 *    and incrementing the column number of previous number by 1. 
	 *    Any time, if the calculated row position become -1, it will wrap around to n-1;
	 *              if the calculated column position become n, it will wrap around to 0;
	 * 2. If the magic square already contains a number at the calculated position, the calculated column  position 
	 *    will be decremented by 2, and calculated row position will be increment by 1.
	 * 3. If the calculated row position is -1 & calculated column position is n, the new position would be (0, n-2) 
	 */
	public static void test13() {
		int n = 3;
		int[][] matrix = task13_magicSquare(n);
	}
	
	public static int[][] task13_magicSquare(int n) {
		if (n <= 0) {
			return null;
		}
		int[][] matrix =new int[n][n];
		int i = n/2;
		int j = n - 1;
		
		for(int num = 1; num <= n * n;) {
			// condition 3:
			if (i == -1 && j == n) {
				j = n - 2;
				i = 0;
			} else {
				// 1st condition
				if (j == n) {
					j = 0;
				}
				if (i < 0) {
					i = n - 1;
				}
			}
			// condition 2:
			if (matrix[i][j] > 0) {
				j -= 2;
				i ++;
				continue;
			} else {
				matrix[i][j] = num;
				num ++;
			}
			i --;
			j ++;
		}
		Debug.printMatrix(matrix);
		System.out.println("-------");
		System.out.println(Arrays.deepToString(matrix));
		return matrix;
	}
	
	
	/*
	 * task14
	 * Sieve of Eratosthenes
	 * Given a number n, print all primes smaller than or equal to n. It is also given that n is a small number.
	 * For example, if n is 10, the output should be “2, 3, 5, 7″. 
	 * If n is 20, the output should be “2, 3, 5, 7, 11, 13, 17, 19″.
	 * http://www.geeksforgeeks.org/sieve-of-eratosthenes/
	 */
	public static void test14() {
		int n = 97;
		ArrayList<Integer> output = task14_primnumber(n);
		System.out.println(output);
	}
	
	public static ArrayList<Integer> task14_primnumber(int n) {
		boolean[] input = new boolean[n + 1];
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i = 0; i <= n; i ++) {
			input[i] = true;
		}
		
		for(int i = 2; i < (int)Math.sqrt(n); i ++) {
			if (input[i]) {
				for(int j = i*i; j <=n; j +=i) {
					input[j] = false;
				}
			}
		}
		for(int i = 2; i <=n; i ++) {
			if (input[i]) {
				output.add(i);
			}
		}
		return output;
	}
	
	/*
	 * task15
	 * Find day of the week for a given date
	 * http://www.geeksforgeeks.org/find-day-of-the-week-for-a-given-date/
	 * Write a function that calculates the day of the week for any particular date in the past or future. 
	 * A typical application is to calculate the day of the week 
	 * on which someone was born or some other special event occurred.
	 */
	
	public static int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
	public static int task15_dayofweek(int d, int m, int y)
	{
		if (m < 3) {
			 y = y -m ;
		}
	    return ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
	}
	public  static void test15() {
		int day = task15_dayofweek(31, 8, 2010);
		System.out.println(day);
	}
	
	/*
	 * task16
	 * DFA based division
	 * http://www.geeksforgeeks.org/dfa-based-division/
	 */
	
	
	/*
	 * task17
	 * Generate integer from 1 to 7 with equal probability
	 * Generate rand7() with rand5()
	 * 
	 * temp = rand5() * 5 + rand5();
	 * if (temp < 21) {
	 * 	 return temp %7;
	 * }
	 * return rand7();
	 * 
	 *
	 */
	public static int rand5() {
		return -1;
	}
	public static int task17_rand7() {
		int temp = 5 * rand5() + rand5();
		if (temp < 21) {
			return temp % 7;
		}
		return task17_rand7();
	}
	
	
	/*
	 * task17.1
	 * How to design a random number generator Random(1,000,000,000), with Random(5). 
	 */
	public static int task17_1_rand() {
		return -1;
	}
	
	
	
	
	/*
	 * task18
	 * Given a number, find the next smallest palindrome
	 * http://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
	 */
	
	
	/*
	 * task19
	 * http://www.geeksforgeeks.org/generate-integer-from-1-to-7-with-equal-probability/
	 */
	
	/*
	 * task20
	 * Make a fair coin from a biased coin
	 * http://www.geeksforgeeks.org/print-0-and-1-with-50-probability/
	 */
	
	/*
	 * task21
	 * Check divisibility by 7
	 * http://www.geeksforgeeks.org/divisibility-by-7/
	 */
	
	
	/*
	 * task22
	 * Find the largest multiple of 3
	 * http://www.geeksforgeeks.org/find-the-largest-number-multiple-of-3/
	 */
	
	
	
	
	
	

}

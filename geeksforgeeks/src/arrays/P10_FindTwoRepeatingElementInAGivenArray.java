package arrays;

import java.util.ArrayList;

public class P10_FindTwoRepeatingElementInAGivenArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		int set_no = 2;
		System.out.println(Integer.toBinaryString(set_no));
		int add = 4 & set_no;
		System.out.println(Integer.toBinaryString(add));
		System.out.println("add = " + add);
		*/
		test1();
//		test2();
//		test3();
//		test4();
	}
	/*
	 * You are given an array of n+2 elements. All elements of the array are in
	 * range 1 to n. And all elements occur once except two numbers which occur
	 * twice. Find the two repeating numbers.
	 * 
	 * For example, array = {4, 2, 4, 5, 2, 3, 1} and n = 5
	 * 
	 * The above array has n + 2 = 7 elements with all elements occurring once
	 * except 2 and 4 which occur twice. So the output should be 4 2.
	 */
	
	// Brute Force
	public static void printRepeating(int[] a) {
	
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] == a[j]) {
					System.out.println(a[i]);
				}
			}
		}
	}
	
	public static void test1() {
		int[] a = {4,2,4,5,2,3,1};
		printRepeating(a);
		System.out.println("--------");
		printRepeating2(a);
		
		System.out.println("--------");
		printTwoRepeat3(a);
		System.out.println("--------");
		printTwoRepeat3_2(a);
	}
	
	// use count array
	public static void printRepeating2(int[] a) {
		int size = a.length;
		int[] count = new int[size - 1];
		for (int i = 0; i < size; i++) {
			int cur = a[i];
			count[cur] ++;
			if (count[cur] == 2) {
				System.out.println(cur);
			}
		}
	}// Time: O(n)  Space: O(n)
	
	/*
	 * Using XOR
	 * Suppose the two repeating nums are X and Y
	 * (1) XOR all elements in arr and 1-n, the result would be X xor Y
	 * (2) find the first bit == 1 in result. There must exist, since X and Y are different
	 * (3) XOR all elements whose kth bit == 1 in arr and 1-n  the result would be one of X and Y, let's call it X
	 * (4) XOR all elements whose kth bit == 0 in arr and 1-n  the result would be Y
	 */
	
	public static boolean checkKthOne(int a, int k) {
		return ((a>>k) & 1) == 1;
	}
	
	public static void test2() {
		int a = 10;
		System.out.println(Integer.toBinaryString(a));
		int k = 1;
		// 10 == 1010  from the least significant to most significant, the 1 bit is 1 
		boolean kth = checkKthOne(a, k);
		System.out.println("kth =" + kth);
	}
	
	public static void test3() {
		int a = 8;
		int k = 0;
		for (; k < Integer.toBinaryString(a).length(); k++) {
			if (checkKthOne(a, k)) {
				break;
			}
		}
//		while (a > 0) {
//			if (a %2 == 1) {
//				break;
//			} else {
//				k ++;
//			}
//			a /=2;
//		}
		System.out.println("k = " + k);
	}
	
	public static int[] printTwoRepeat3(int[] a) {
		int n = a.length - 2;
		// suppose X and Y are the two repeating elements
		int result = a[0];
		for (int i = 1; i < a.length; i++) {
			result ^= a[i];
		}
		for (int i = 1; i <= n; i++) {
			result ^= i;
		}
		
		// now result is X xor Y
		int k = 0;
//		int copyResult = result;
//		while(copyResult > 0) {
//			if (copyResult %2 == 1) {
//				break;
//			} else {
//				k ++;
//			}
//			copyResult /=2;
//		}
		for (; k < Integer.toBinaryString(result).length(); k ++) {
			if (checkKthOne(result, k)) {
				break;
			}
		}
		
		int X = 0;
		int Y = 0;
		for (int i = 0; i < a.length; i++) {
			if (checkKthOne(a[i], k)) {
				X ^= a[i];
			}else {
				Y ^= a[i];
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if (checkKthOne(i, k)) {
				X ^= i;
			} else {
				Y ^= i;
			}
		}
		
		int[] rev = new int[2];
		rev[0] = X;
		rev[1] = Y;
		System.out.println(X);
		System.out.println(Y);
		return rev;
	}
	
	public static ArrayList<Integer> printTwoRepeat3_2(int[] a) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int n = a.length - 2;
		int xor = a[0];
		for (int i = 1; i < a.length; i++) {
			xor ^= a[i];
		}
		for (int i = 1; i <= n; i++) {
			xor ^= i;
		}
		
		int set_bit_no = xor & ~(xor - 1);
		int X = 0, Y = 0;
		for (int i = 0; i < a.length; i++) {
			if ((a[i] & set_bit_no) == set_bit_no) {
				X = X ^a[i];
			} else {
				Y =Y ^a[i];
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if ((i & set_bit_no) == set_bit_no) {
				X = X ^ i;
			} else {
				Y =Y ^ i;
			}
		}
		result.add(X);
		result.add(Y);
		
		
		System.out.println(result);
		
		return result;
	}
	
	
	/*
	 * This is A tricky method
	 * Traverse the array. Do following for every index i of A[]. 
	 * { check for sign of A[abs(A[i])] ; 
	 *    if positive then make it negative by A[abs(A[i])]=-A[abs(A[i])]; 
	 *    else // i.e., A[abs(A[i])] is negative this
	 * 		element (ith element of list) is a repetition 
	 * }
	 * 
	 * since the element is between 1--n so, there we can use index to stand for the element
	 * every time, we mark A[A[i]] to -A[A[i]], indicates that index= A[i] already exist. 
	 * if in the future, we access this index again(we will meet a negative num)
	 *    which means that this eleement already exist. So print out the duplicates
	 */
	public static void printTwoRepeating4(int[] arr) {
		int size = arr.length;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			if (arr[Math.abs(arr[i])] > 0){
				arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
				System.out.println("i == " + i);
				printArray(arr);
			}
			else{
//				System.out.println(Math.abs(arr[i]));
				result.add(Math.abs(arr[i]));
				System.out.println("i == " + i);
				printArray(arr);
			}
		}
		System.out.println(result);
	}
	
	public static void test4() {
		int[] a = {1,2,3,4,5,6,1,2};
		printTwoRepeating4(a);
		
	}
	
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

}

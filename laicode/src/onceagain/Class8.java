package onceagain;

import java.util.Arrays;

public class Class8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}
	
	/*
	 * task1 reverse string
	 */
	public static String task1_reverse_iter(String str) {
		char[] array = str.toCharArray();
		int i = 0, j = array.length - 1;
		while(i < j) {
			swap(array, i, j);
			i ++; 
			j --;
		}
		return new String(array);
	}
	
	public static void swap(char[] array, int x, int y) {
		char temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	public static String task1_reverse_rec(String str) {
		char[] array = str.toCharArray();
		int left = 0, right = array.length - 1;
		task1_helper(array, left, right);
		return new String(array);
	}
	
	public static void task1_helper(char[] array, int left, int right) {
		if (left >= right) {
			return ;
		}
		swap(array, left, right);
		task1_helper(array, left + 1, right - 1);
	}
	
	/*
	 * task2
	 * reverse words in sentences
	 */
	public static void test2() {
		String str = "hello world hi wild cat";
		String result = task2_reverseWords(str);
		System.out.println(result);
	}
	
	public static String task2_reverseWords(String str) {
		char[] array = str.toCharArray();
		reverse(array, 0, array.length -1);
		int start = 0;
		for(int i = 0; i < array.length; i ++) {
			if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
				start = i;
			}
			
			if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
				 reverse(array, start, i);
			}
		}
		return new String(array);
	}
	
	public static void reverse(char[] array, int left, int right) {
		while(left < right) {
			swap(array, left, right);
			left ++;
			right --;
		}
	}
	
	/*
	 * task3 
	 * Right shift
	 */
	
	
	/*
	 * task4
	 * 
	 */

}

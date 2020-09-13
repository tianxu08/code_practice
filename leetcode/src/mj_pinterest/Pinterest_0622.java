package mj_pinterest;

import java.util.Deque;
import java.util.LinkedList;

public class Pinterest_0622 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}
	
	
	/*
	 * CanWin. 给一个数组比如 [2,3,0,3,5]和一个index比如4，从这个index每次可以向左或者向右跳ary[index]的距离， 
	 * 如果能跳到值为0的index则返回true。
	 */
	public static void test1() {
		int[] input = {2,3,0,3,4};
		int index = 4;
		boolean rev = t1_canWin(input, index);
		System.out.println("rev = " + rev);
	}
	public static boolean t1_canWin(int[] array, int index) {
		Deque<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[array.length];
		q.offer(index);
		visited[index] = true;
		
		while(!q.isEmpty()) {
			int curIdx = q.poll();
			if (array[curIdx] == 0) {
				return true;
			}
			
			int nextLeftIdx = curIdx - array[curIdx];
			if (nextLeftIdx >= 0 && nextLeftIdx < array.length && !visited[nextLeftIdx]) {
				q.offer(nextLeftIdx);
			}
			
			int nextRightIdx = curIdx + array[curIdx];
			if (nextRightIdx >= 0 && nextRightIdx < array.length && !visited[nextRightIdx]) {
				q.offer(nextRightIdx);
			}
		}
		return false;
	}
	
	
	/**
	 * look and say, 给定一个string，里面全是数字，输出一个string表达input string应该怎么读。
	 * 举例: input: "1", output: "11" 因为在英语里描述“1”是“one one”， 一个1. 
	 * input: "12", output: "1112" 因为在英语里描述“12”是“one one one two”， 一个1一个2 
	 * input: "111211", output: "311221" 因为在英语里描述“ 111211”是“three one one two two one”， 
	 * 三个1一个2两个1. 这个直接把string过一遍记好每个char出现的次数就行了。 
	 * 链接: http://172.252.15.228/instant_/discuz_thread/206303
	 * 来源: 一亩三分地
	 */
	public static void test2() {
		String num = "1222333333";
		String rev = lookAndSay(num);
		System.out.println(rev);
	}
	
	
	// assume the number of duplicate element in the string is less than 10
	public static String lookAndSay(String num) {
		int slow = 0, fast = 1;
		int counter = 1;
		StringBuilder stb = new StringBuilder();
		String[] map = {
				"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
		};
		
		while(fast < num.length()) {
			if (num.charAt(fast) != num.charAt(slow)) {
				stb.append(map[counter] + " ");
				stb.append(map[num.charAt(slow) - '0'] + " ");
				// reset the counter
				counter = 1;
				slow = fast;
				fast++;
			} else {
				// num[fast] ==  num[slow]
				counter++;
				fast++;
			}
		}
		
		// don't forget the last one
		stb.append(map[counter] + " ");
		stb.append(map[num.charAt(slow) - '0']);
		
		return stb.toString();
	}
	
	public static String getEnglishNumber(int num) {
		return null;
	}
	

}

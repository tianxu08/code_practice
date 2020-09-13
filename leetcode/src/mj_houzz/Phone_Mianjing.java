package mj_houzz;

import java.util.*;
import java.util.Map.Entry;

public class Phone_Mianjing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}

	/*
	 * 1 Efficient program to print all prime factors of a given number
	 * 1) While n is divisible by 2, print 2 and divide n by 2.
	 * 2) After step 1, n must be odd. Now start a loop from i = 3 to square root of n. 
	 * While i divides n, print i and divide n by i, increment i by 2 and continue.
	 * 3) If n is a prime number and is greater than 2, then n will not become 1 by above two steps. 
	 * So print n if it is greater than 2.
	 */
	public static void primeFactors(int n) {
		// Print the number of 2s that divide n
		while (n % 2 == 0) {
			System.out.print(2 + " ");
			n /= 2;
		}

		// n must be odd at this point. So we can
		// skip one element (Note i = i +2)
		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			// While i divides n, print i and divide n
			while (n % i == 0) {
				System.out.print(i + " ");
				n /= i;
			}
		}

		// This condition is to handle the case whien
		// n is a prime number greater than 2
		if (n > 2)
			System.out.print(n);
	}
	
	/**
	 * https://instant.1point3acres.com/thread/271095
	 * 
	 * 第一題問了一道經典題目 what happen when you go to www.google.com 
	 * 1. check browser chche, then OS host file to find if IP was visited, 
	 * if not, go to DNS to get the IP; 
	 * 2. request get to the correct IP, could be a web/app server, 
	 * or a Load Balancer and forward you to the web server (LB又可以瞎扯一堆) 
	 * 3. request go through LB, app server to backend, DB … 
	 * 4. server reply back with 200 OK, and some HTTP contents 
	 * 5. based on the HTTP contents your browser would build up a DOM tree, 
	 * 	  and get the static contents from CDN, and send other HTTP requests to the server. 
	 * 6. there could be some AJAX requests as well.
	 * 
	 *  
	 * ArrayList v.s LinkedList 
	 * Set vs List
	 * 
	 * LC  Text Justification
	 */
	public List<String> task68_fullJustify(String[] words, int maxWidth) {
		List<String> result = new ArrayList<String>();
		if (words == null || words.length == 0) {
			return result;
		}

		int count = 0; 
		// is the current string length
		int last = 0; 
		// last is the last String we have choose.
		for (int i = 0; i < words.length; i++) {
			// traverse the string array
			if (count + words[i].length() + (i - last) > maxWidth) {
				int spaceNum = 0;
				int extraNum = 0;
				// in this case, we didn't choose the words[i]
				if (i - last - 1 > 0) {
					// interval's
					spaceNum = (maxWidth - count) / (i - last - 1);
					extraNum = (maxWidth - count) % (i - last - 1);
				}

				StringBuilder st = new StringBuilder();
				for (int j = last; j < i; j++) {
					st.append(words[j]);
					if (j < i - 1) {
						for (int k = 0; k < spaceNum; k++) {
							st.append(" ");
						}
						if (extraNum > 0) {
							st.append(" ");
						}
						extraNum--;
					}
				}

				for (int j = st.length(); j < maxWidth; j++) {
					st.append(" ");
				}
				result.add(st.toString());
				count = 0;
				last = i; // the next word
			}
			count += words[i].length();
		}

		// the last line
		StringBuilder str = new StringBuilder();
		for (int i = last; i < words.length; i++) {
			str.append(words[i]);
			if (str.length() < maxWidth) {
				str.append(" ");
			}
		}
		for (int i = str.length(); i < maxWidth; i++) {
			str.append(" ");
		}
		result.add(str.toString());
		return result;
	}
	
	public static void test1() {
		String s = "rft567.908kih000000hh890jug678gtff567";
		extract_number_in_string(s);
	}
	public static void extract_number_in_string(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				int num = s.charAt(i) - '0';
				while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
					num = num * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				System.out.println(num);
			}
		}
	}
	
	
	/*
	 * edit distance
	 * 
	 */
	
	
	/*
	 * 
	 */
	public static int helper(HashMap<Integer, Integer> map, int target) {
		if (map.containsKey(target)) {
			return map.get(target);
		}
		int count = 0;
		int num = target;
		while(num != 1) {
			if (map.containsKey(num)) {
				return count + map.get(num);
			}
			if (num % 2 == 1) {
				num = 3 * num + 1;
			} else {
				num = num / 2;
			}
			count++;
		}
		return count;
	}
	
	public static int helper2(HashMap<Integer, Integer> map, int target) {
		if (map.containsKey(target)) {
			return map.get(target);
		}
		if (target == 1) {
			return 0;
		}
		int count = 0;
		if (target % 2 == 1) {
			count = helper2(map, 3 * target + 1) + 1;
		} else {
			count = helper2(map, target/2) + 1;
		}
		map.put(target, count);
		return count;
	}
	
	public static int longestSeq(int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int rev =helper(map, target);
		
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return rev;
	}
	
	public static int longestSeq2() {
		int rev = Integer.MIN_VALUE;
		HashMap<Integer, Integer> map =new HashMap<Integer, Integer>();
		for (int i = 1; i <= 100; i++) {
			int count = helper(map, i);
			map.put(i, count);
			rev = Math.max(rev, count);
		}
		
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		return rev;
	}
	
	public static int longestSeq3() {
		int rev = Integer.MIN_VALUE;
		HashMap<Integer, Integer> map =new HashMap<Integer, Integer>();
		for (int i = 1; i <= 10; i++) {
			rev = Math.max(rev, helper2(map, i));
		}
		
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		return rev;
	}
	
	public static void test2() {
		int target = 5;
		int rev = longestSeq(target);
//		System.out.println("rev = " + rev);
		
		System.out.println("---------");
		rev = longestSeq2();
		System.out.println("rev = " + rev);
		System.out.println("---------");
		System.out.println("---------");
		rev = longestSeq3();
		System.out.println("rev3 = " + rev);
		
	}
	
	

}

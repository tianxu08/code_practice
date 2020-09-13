package mj_houzz;

import java.util.HashMap;
import java.util.*;

public class Misc_Knowledge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
		test10();
	}
	
	
	/**
	 * 
	 * Web:
	 * 1. What happens when entering www.google.com 
	 * (send get request, DNS, load balancer, server, find static file or go into MVC and 
	 * database interactions, send back result)
	 * 2. Scalability. Now we have one server, one database, 
	 * what if response time is slow? How to optimize? 
	 * 链接: https://instant.1point3acres.com/thread/101771
	 * 
	 * Database: Was going to ask database questions, 
	 * then I said I’m not good at it, so he just asked am I willing to learn.
	 * 
	 */
		
	/**
	 * 很多java的基本知识, 比如interface和 abstract class的区别
	 * Coding 部分让实现一个singleton设计模式Next permutation
	 * 
	 * 第二轮Java 里面 如何自己实现equalsmethodCoding 部分让实现一个singleton设计模式Word break
	 * 链接: https://instant.1point3acres.com/thread/272938
	 * 
	 * 
	 */
	
	/**
	 * 1 fimd median of two sorted array. 
	 * 2 merge k sorted arrays
	 * 3 Degisn Twitter
	 * 
	 */
	
	/**
	 * max rectangle 输出其坐标
	 */
	
	/**
	 * Text Justification
	 */
	
	/**
	 * 给一串string，里面有数字, return 最大的top k 个数字，比如k = 3： 
	 * input = “dfsfs980sdf123poier110poipoikkj-10” output = [980, 123, 110] 
	 * 链接: https://instant.1point3acres.com/thread/190488
	 */
	
	/**
	 * input 一个日期，即年月日，要求返回加上n天以后得到的日期。
	 * 链接: https://instant.1point3acres.com/thread/198546
	 * 
	 * fib 数列
	 * edit distance
	 * 
	 */
	
	/**
	 * sql how to implement join
	 * http request: get and post
	 * data stream 最后k个输入的平均值
	 * 
	 * matrix 左上 到 右下 的路径， 开始只能往右往下走。。。
	 * follow up: 四个方向都可以
	 * https://instant.1point3acres.com/thread/264939
	 * 
	 * 
	 */
	
	/**
	 * 一堆不同面值的硬币，一个target，找到最少硬币数，求和等于target。（用dp做，2个for loop就行了） 
	 * 链接: https://instant.1point3acres.com/thread/196921
	 * 
	 * 
	 */
	
	/**
	 * 1. Remove any number containing 9 like 9, 19, 29, ... 91, 92, ... 99, 109... 
	 * Write a function that returns the nth number. 
	 * E.g. newNumber(1) = 1 newNumber(8) = 8, newNumber(9) = 10, 最后给了hint把数变成9-based 
	 * 2. kSum.... expect better runtime than backtracking... 
	 *    Consider 3sum's backtracking complexity is worse than n^2, 
	 *    which use's two sum's two pointer approach. 
	 * 3. Design Excel. Implement int get(string cell) void put(string cell, string expr). 
	 *    expr can be "A1= B1+1". 这题的关键在于，要解决各个cell的dependence问题. 
	 *    比如说call put(B1, "3")之后，同时也要update A1的值。
	 *    会牵扯到topological sort的问题。总之这题是design题，就看你有没有意识到这种dependence。
	 *     
	 * 4. Design Intagram 
	 * 5. Android lock pattern from leetcode. 
	 * 链接: https://instant.1point3acres.com/thread/196589
	 */
	
	/**
	 * 判断两个矩形是否相交
	 */
	
	/**
	 * convert string
	 * 比如"123.45", 返回123.45, "123.4ts5", 返回error，"-123.45",返回 -123.45. 感觉真的很难cover所有的cases啊，
	 * 链接: https://instant.1point3acres.com/thread/185063
	 * 
	 */
	
	/**
	 * How to get the repeating decimal pattern of a division? (e.g 1/3, 1/6) 
	 */
	public static void test2() {
		int num1 = 1;
		int num2 = 6;
		String rev = get_repeating_decimal_pattern(num1, num2);
		System.out.println(rev);
	}
	
	public static String get_repeating_decimal_pattern(int num1, int num2) {
		StringBuilder decimal = new StringBuilder();
		long n1 = Math.abs((long) num1);
		long n2 = Math.abs((long) num2);
		
		n1 = n1 % n2;
		System.out.println("n1 = " + n1);
		System.out.println("n2 = " + n2);
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		for (int i = 0; n1 != 0; i++) {
			if (map.get(n1) != null) {
				// this remainder already exist
				break;
			}
		
			map.put(n1, i);
			n1 = n1 * 10;
			long digit = n1 / n2;
			decimal.append(digit);
		
			// update n1
			n1 = n1 % n2;
		}
		if (n1 == 0) {
			return null;
		} else {
			int repeat_start_index = map.get(n1);
			String rev = decimal.substring(repeat_start_index);
			return rev;
		}
	
	}
	
	
	/**
	 * How to serialize a binary tree and deserialize from the data you get?
	 */
	
	/**
	 * Key-value store
	 */
	
	
	
	/**
	 * how would you detect a cycle in a DAG
	 * 
	 * Q1: print out prime factors. e.g., 20=2x2x5, 90=2x3x3x5
	 * How to get a list of prime numbers
	 * Q2: LRU. How to implement linkedHashtable
	 * Q3: draw architectural picture for a typical web stack.
	 * Q4: implement thread pool
	 */
	public static void test10() {
		 HashSet<String> hs = new HashSet<String>();  
	        hs.add("B");  
	        hs.add("A");  
	        hs.add("D");  
	        hs.add("E");  
	        hs.add("C");  
	        hs.add("F");  
	        System.out.println("HashSet 顺序:\n"+hs);  
	          
	        LinkedHashSet<String> lhs = new LinkedHashSet<String>();  
	        lhs.add("B");  
	        lhs.add("A");  
	        lhs.add("D");  
	        lhs.add("E");  
	        lhs.add("C");  
	        lhs.add("F");  
	        System.out.println("LinkedHashSet 顺序:\n"+lhs);
	        lhs.remove("D");
	        System.out.println("LinkedHashSet 顺序:\n"+lhs);
	        lhs.add("D");
	        System.out.println("LinkedHashSet 顺序:\n"+lhs);
	        System.out.println("------------------------");
	          
	        TreeSet<String> ts = new TreeSet<String>();  
	        ts.add("B");  
	        ts.add("A");  
	        ts.add("D");  
	        ts.add("E");  
	        ts.add("C");  
	        ts.add("F");  
	        System.out.println("TreeSet 顺序:\n"+ts);  
	}
	
	
	
	
}

package lec;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Lec26 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Q1 Best First Search
	 * max, min, shortest path
	 */
	/*
	 * 1 Initial state: <w1, w2>
	 * 2 Expansion/Generation rule    
	 * 3 Termination condition
	 */
	
	/*
	 *  Q1.1
	 *  ​一个字典有给一系列strings,要求找两个string,使得它们没有共同字符,并且长度乘积最大. 
	 *  Assumption: all letters in the word is from 'a-z' in ASCII
	 * 
	 * 1 Initial state: <w1, w2>
	 * 2 Expansion/Generation rule
	 *   a. expand <wi, wj>
	 *   b. generate <wi + 1, wj> or <wi, wj + 1> 
	 *      i. the definition of a valid state
	 *         1 i != j
	 *         2 wi and wj does not share same char
	 * 3 Termination condition
	 *   a. when the first state that is valid is popped out of the heap, then we terminate 
	 *      and return that state <wi, wj>
	 *        
	 * Priority Queue: space
	 *
	 * sorted by length
	 * w1 abcde size = 5
	 * w2 adzz  size = 4
	 * w3 abd   size = 3
	 * w4 fgz   size = 4
	 * solution abcde * fgz = 5 * 3 == 15
	 * 
	 * !!! Must sort the dictionary according to the lengths of each word
	 */
	/*
	public static class Pair {
		int x;
		int y;
	}
	public static class Element {
		public int product;
		public pair<i, j> indices; // indices of both elements in the original dictionary
	}
	*/
	
	// check whether two words share same char
	// (1) hash table. 
	// (2) bit vector. save to 1/8 space.
	
	public static boolean checkTwoWords(String w1, String w2) {
		int[] map = new int[256];
		int[] map2 = new int[4];
		for(int i = 0; i < w1.length(); i ++) {
			char ch = w1.charAt(i);
			int pos = 0;
		}
		return true;
	}
	
	/*
	 * Q1.2
	 * How to find the k­th smallest number in the f(x,y,z) = 3^x * 5^y * 7^z (int x > 0, y>0, z>0).
	 * 
	 * 1 Initial state <x = 1, y = 1, z = 1>
	 * 2 Expansion/Generation rule: 
	 *   	expand: <x = i, y = j, z = k>
	 *      generate <i + 1, j , k>
	 *      generate <i, j + 1, k>
	 *      generate <i, j, k + 1>
	 */
	/*
	 * De-duplication:
	 * expand <2,3,3>, <3,2,3>, <3,3,2>
	 * generate state -> <3,3,3>
	 * 
	 */
	public static int Q1_2KthSmallest(int k) {
		if (k < 0) {
			return -1;
		}
		
		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		minHeap.add(3*5*7);
		visited.add(3*5*7);
		
		while(k > 1) {
			int current = minHeap.poll();
			if (!visited.contains(current*3)) {
				visited.add(current * 3);
				minHeap.add(current * 3);
			}
			if (!visited.contains(current * 5)) {
				visited.add(current*5);
				minHeap.add(current* 5);
			}
			if (!visited.contains(current * 7)) {
				visited.add(current * 7);
				minHeap.add(current * 7);
			}
			k --;
		}
		
		return minHeap.peek();
	}
	
	/*
	 * Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, 
	 * x >= 0 and y >= 0, x and y are all integers.
	 */
	public int kth(int k) {
		// write your solution here
		if (k < 1) {
			return -1;
		}

		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		minHeap.add(1);
		visited.add(1);

		while (k > 1) {
			int current = minHeap.poll();
			if (!visited.contains(current * 2)) {
				visited.add(current * 2);
				minHeap.add(current * 2);
			}
			if (!visited.contains(current * 3)) {
				visited.add(current * 3);
				minHeap.add(current * 3);
			}
			k--;
		}
		return minHeap.peek();
	}
	
	
	
	/*
	 * Q1.3 G​iven three arrays with numbers in ascending order. 
	 * Pull one number from each array to form a coordinate <x,y,z> in a 3D​s​pace. 
	 * (1) How to find the coordinates of the points that is k­th closest to <0,0,0>?
	 *
	 * Initialize state <0,0,0>
	 * Expansion/generation rule:
	 * 			expand <i, j, k>
	 *  		generate <i + 1, j , k>
	 *    		generate <i, j + 1, k>
	 *    		generate <i, j, k + 1>
	 * Terminate condition: the kth element is popped out of the p_queue
	 */
	public static ArrayList<Integer> Q1_3Closest(final int[] a, final int[] b,final int[] c, int k) {
		PriorityQueue<ArrayList<Integer>> minHeap = 
				new PriorityQueue<ArrayList<Integer>>(2*k, new Comparator<ArrayList<Integer>>(){
					
					@Override
					public int compare(ArrayList<Integer> o1,
							ArrayList<Integer> o2) {
						// TODO Auto-generated method stub
						long d1 = distance(o1, a, b, c);
						long d2 = distance(o2, a, b, c);
						if (d1 == d2) {
							return 0;
						} else if (d1 < d2) {
							return -1;
						} else {
							return 1;
						}
					}
				} );
		HashSet<ArrayList<Integer>> visited = new HashSet<ArrayList<Integer>>();
		ArrayList<Integer> cur = (ArrayList<Integer>) Arrays.asList(0,0,0);
		visited.add(cur);
		minHeap.offer(cur);
		while( k > 0) {
			cur = minHeap.poll();
			ArrayList<Integer> n = (ArrayList<Integer>)Arrays.asList(cur.get(0) + 1, cur.get(1), cur.get(2));
			if (n.get(0) < a.length && visited.add(n)) {
				minHeap.offer(n);
			}
			
			n = (ArrayList<Integer>) Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
			if (n.get(1) < b.length && visited.add(n) ) {
				minHeap.offer(n);
			}
			
			n = (ArrayList<Integer>) Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
			if (n.get(2) < c.length && visited.add(n)) {
				minHeap.offer(n);
			}
			k --;
		}
		cur.set(0, a[cur.get(0)]);
		cur.set(1, a[cur.get(1)]);
		cur.set(2, a[cur.get(2)]);
			
		return cur;
	}

	
	public static long distance(ArrayList<Integer> point, int[] a, int[] b, int[] c) {
		// point position: point.get(0), point.get(1), point.get(2)
		 long ds = 0;
		 ds += a[point.get(0)] * a[point.get(0)];
		 ds += b[point.get(1)] * b[point.get(1)];
		 ds += c[point.get(2)] * c[point.get(2)];
		 return ds;
	}
	
	/*
	 * Q1.4 G​iven a gym with k equipments, and some obstacles. 
	 * Let’s say we bought a chair and wanted to put this chair 
	 * into the gym such that the sum of the shortest path cost 
	 * from the chair to the k equipments is minimal.
	 * 
	 * Primitive idea: run Dijkstra's algorithm from each empty element. 
	 * Time: n^2 * n^2 log(n) = n^4 * log(n)
	 * 
	 * Better Idea:
	 * Time: k * n ^2  log (n)
	 * 
	 * in LaiCode, this is Place To Put The Chair II
	 */
	
	
	// write 
	public static ArrayList<Integer> Q1_4GymPutChair(char[][] gym) {
		return null;
	}
	
	
	/*
	 * 
	 * Place To Put The Chair I
	 */
	
	
	/*
	 * Max Water Trapped II 3D
	 * 
	 */
	
	
	
	/*
	 * Q2 Design
	 * Given a single computer with a single CPU and a single core, 
	 * which has 2GB of memory and 1GB available for use, it also has two 100GB hard drives.
	 * How to s​ort​80GB integers of 64 bits.?
	 */
	
	/*
	 * Q3 String conversion
	 * Q3.1 “A1B2C3D4” ==> “ABCD1234”
	 * 
	 * Q3.2 “A​BCD1234” ==> “A1B2C3D4”
	 */
	
	
	/*
	 * Q4 Histogram questions (​直方图问题)
	 * Q4 .1​直方图中找最大矩形
	 * 
	 * Q4 .2​直方图下雨接水问题
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

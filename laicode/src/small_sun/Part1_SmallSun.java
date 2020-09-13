package small_sun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class Part1_SmallSun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		task2_test();
//		task3_test();
		task4_test();
		
	}
	
	/*
	 * task1
	 * print all permutation of a string with duplicates
	 */
	
	/*
	 * task2
	 * Given ​an int array A[], define: distance=A[i]+A[j]+ (j ­ i),​ ​j>=i​.​ 
	 * Find max distance in A[]?
	 * e.g
	 * input: {1,2,3,4,5}
	 * output: 
	 * 
	 * distance = A[i] + A[j] + (j - i) = (A[i] - i) + (A[j] + j)
	 * 
	 * using i_max_sofar to store maximum value of A[i] - i so far.
	 * 
	 * j is used as current index to scan from leftside to rightside. 
	 * 
	 * when we scan index j, 
	 * i_max_sofar = max(i_max_sofar, a[j] - j)
	 * 
	 * meanwhile, we need to update 
	 * global_result =( A[i] - i ) + (A[j] + j) = i_max_sofar + (A[j] + j)
	 * 
	 */
	public static int task2_maxDistancd(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int i_max_sofar = Integer.MIN_VALUE;
		int global_result = Integer.MIN_VALUE;
		
		for(int j = 0; j < a.length; j ++) {
			i_max_sofar = Math.max(i_max_sofar, a[j] - j);
			global_result = i_max_sofar + a[j] + j;
		}
		return global_result;
	}
	public static void task2_test() {
		int[] a = {1,2,3,4,5};
		int rev = task2_maxDistancd(a);
		System.out.println("rev = " + rev);
		
	}
	/*
	 * task3 
	 * Skyline Algorithm
	 * Given a vector of rectangles i (1 <= i <=n) 
	 * which are put along x­coordinate [x1, x2, height]. 
	 * The rectangles may overlap with each other. 
	 * How can we calculate the total area that these rectangles cover.
	 *
	 * (1) separator the xcooridinate with  [x1, height, START] and [x2, height, END]
	 * (2) sort the separator by x, if the x1 == x2, then sort by seperator.height in descending order
	 * (3) maintain a maxHeap to get the maxHeight so far. 
	 * (4) traversal the seperator arraylist.
	 *     curSep 
	 *     if loc == START
	 *        if curSep.height <= heap.peek() 
	 *          	this spot is under the skyline. do nothing. 
	 *        if curSep.height > heap.peek()
	 *              we need to add two new spot to the result. 
	 *              <1>One is the intersection with heap.peek();  (curSep.x, hepp.peek())
	 *              <2>The other one is itself                    (curSep.x, curSep.height)
	 *              <3>add curSep.height to the maxHeap
	 *     if loc == END
	 *     	  remove curSep.height from maxHeap
	 *     	  if curSep.height <= heap.peek()
	 *             	this spot is under the skyline. do nothing. 
	 *        if curSep.height > heap.peek()
	 *              we need to add two new spots to the result. 
	 *              <1> the curSep its itself. (curSep.x, curSep.height)
	 *              <2> the intersection with heap.peek(). (curSep.x, heap.peek()
	 */
	
	public static class Building {
		int x1; 
		int x2;
		int height;
		public Building (int _x1, int _x2, int _height) {
			this.x1 = _x1;
			this.x2 = _x2;
			this.height = _height;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String str = "x1 = " + this.x1 + " x2 = " + x2 + " height = " + height + '\n'; 
			return str;
		}
	}
	
	public static class OutlineSpot {
		public int x;
		public int y;
		public OutlineSpot(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String str = "x = " + x + "  y = " + y + "\n";
			return str;
		}
	}
	public static class BLDSeparator {
		public int x;
		public int height;
		public int loc;     // START means the start of building. END means the end of the building
		public BLDSeparator(int _x, int _height, int _loc) {
			this.x = _x;
			this.height = _height;
			this.loc = _loc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String str = "";
			if (loc == START) {
				str = x + "  " + height + " " + "  START\n";
			}
			if (loc == END) {
				str = x + "  " + height + " " + "  END\n";
			}
			return str;
		}
	}
	public static final int START = -1;
	public static final int END = -2;
	
	public static ArrayList<OutlineSpot> task3_skyline(ArrayList<Building> buildings) {
		ArrayList<BLDSeparator> bldSeps = transfer(buildings);
		ArrayList<OutlineSpot> result = new ArrayList<OutlineSpot>();
		System.out.println(bldSeps);
		
		// implement the maxHeap
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		};
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, maxHeapComparator);
		
		for(int i = 0; i < bldSeps.size(); i++) {
			BLDSeparator curSep = bldSeps.get(i);
			if (curSep.loc == START) {
				int curMaxHeight = maxHeap.isEmpty() ? 0 : maxHeap.peek();
				if (curSep.height > curMaxHeight) {
					OutlineSpot spot1 = new OutlineSpot(curSep.x, curMaxHeight);
					OutlineSpot spot2 = new OutlineSpot(curSep.x, curSep.height);
					result.add(spot1);
					result.add(spot2);
					maxHeap.add(curSep.height);
				}
			}
			if (curSep.loc == END) {
				maxHeap.remove(curSep.height);
				int curMaxHeight = maxHeap.isEmpty() ? 0 : maxHeap.peek();
				if (curSep.height > curMaxHeight) {
					OutlineSpot spot1 = new OutlineSpot(curSep.x, curSep.height);
					OutlineSpot spot2 = new OutlineSpot(curSep.x, curMaxHeight);
					result.add(spot1);
					result.add(spot2);
				}
			}
		}
		
		System.out.println(result);
		
		return result;
	}
	
	public static void task3_test() {
		Building bld1 = new Building(1, 4, 3);
		Building bld2 = new Building(2, 5, 4);
		Building bld3 = new Building(6, 7, 1);
		Building bld4 = new Building(8, 11,5);
		Building bld5 = new Building(8,15,4);
		Building bld6 = new Building(9, 12, 3);
		Building bld7 = new Building(13, 16, 5);
		
		ArrayList<Building> buildings = new ArrayList<Building>();
		buildings.add(bld3);
		buildings.add(bld1);
		buildings.add(bld2);
		buildings.add(bld4);
		buildings.add(bld5);
		buildings.add(bld6);
		buildings.add(bld7);
		
		ArrayList<OutlineSpot> result = task3_skyline(buildings);
		System.out.println("-------print out the result --------");
		System.out.println(result);
	}
	
	public static ArrayList<BLDSeparator> transfer(ArrayList<Building> buildings) {
		ArrayList<BLDSeparator> bldseparators = new ArrayList<BLDSeparator>();
		for(Building bld: buildings) {
			int x1 = bld.x1;
			int x2 = bld.x2;
			int height = bld.height;
			BLDSeparator sep1 = new BLDSeparator(x1, height, START);
			BLDSeparator sep2 = new BLDSeparator(x2, height, END);
			bldseparators.add(sep1);
			bldseparators.add(sep2);
		}
		
		// implement the comparator
		Comparator<BLDSeparator> bldComparator = new Comparator<BLDSeparator>() {

			@Override
			public int compare(BLDSeparator o1, BLDSeparator o2) {
				// TODO Auto-generated method stub
				if (o1.x - o2.x > 0) {
					return 1;
				} else if (o1.x - o2.x < 0) {
					return -1;
				}else {
					return o2.height - o1.height;
				}
			}
			
		};
		
		Collections.sort(bldseparators, bldComparator);
		return bldseparators;
	}
	
	
	/*
	 * task4 
	 * ​Given an array ["this", "is", "a", "is", "fox", "happy"], 
	 * we need to return the smallest indices difference between the two given words. 
	 * For example, dist("fox", "happy") = 1, and dist("is", "fox") = 1 but not 3, 
	 * since there are duplicated “is” in the dictionary. 
	 * Please implement int dist(string word1, string word2).
	 * 
	 * this is a is fox happy   (fox, is)    
	 *      |    |    |  
	 *      i    i
	 * scan the array. use previous point the last word in  (word1 or word2). 
	 * if current pointer's element is different with previous, get distance, update global_distance
	 * if current pointer's element is in (word1 or word2) but same with the previous pointer's content, 
	 * 		just update the previous pointer. 
	 *   
	 */
	
	public static void task4_test() {
		String[] sArray = {"this", "is", "a", "is", "fox", "h","happy"};
		String word1 = "fox";
		String word2 = "this";
		int minDist = task4_minDist(sArray, word1, word2);
		System.out.println("minDist = " + minDist);
	}
	// this function return the mimimus distance in an given array between two words, word1 and word2
	public static int task4_minDist(String[] sArr, String word1, String word2) {
		if (sArr == null || sArr.length == 0) {
			return Integer.MAX_VALUE;
		}
		HashSet<String> set = new HashSet<String>();
		set.add(word1);
		set.add(word2);
		int prev = -1; 
		int minDisance = Integer.MAX_VALUE;
		for(int cur = 0; cur < sArr.length; cur ++) {
			String curStr = sArr[cur];
			if (set.contains(curStr)) {
				if (prev == -1) {
					prev = cur;
					continue;
				} else {
					// prev != -1
					if (sArr[cur].equals(sArr[prev])) {
						prev = cur;
					} else {
						minDisance = Math.min(minDisance, cur - prev);
						prev = cur;
					}
				}
			}
		}
		return minDisance;
		
	}
	
	/*
	 * task5
	 * Print all valid parenthesis. for example is n = 2 ()()  (())
	 * 
	 * follow up 
	 * If we have different kinds of parentheses. e.g 3{} 2[] 2()  print all valid parenthesis. 
	 */
	public static void task5_printAllValidParenthesis(int n) {
		
	}
	public static void task5_helper(int left, int right, ArrayList<Character> line, int index) {
		
	}
	
	
	
	/*
	 * task6
	 * 数组排序, 排成 a1<a2 > a3<a4 > a5 < a6 。。。这种形式。 674523
	 * sorting in descending order, and then swap each pair
	 * 765432
	 * 674523
	 * 
	 * sorting in ascending order, and then swap each pair 
	 * 234567  
	 * 325476 a1>a2<a3>a4<a5>a6<a7
	 */
	
	/*
	 * task7
	 * Given an array of integers, how to divide the whole array into two parts, with their sums equal to 
	 * each other. 
	 * 
	 * subset-sum. 
	 * first, get the sum of the array, if sum is odd, we cannot divide. if sum is even, we might be OK to find 
	 * a way to divide the array equally. 
	 * then, get all subset of the array, if sum of subset == sum/2. then  
	 */
	/*
	 * task8
	 * 2D matrix, sorted on each row, 
	 * first element of next row is larger(or equal) to the last element of previous row, 
	 * now giving a target number, returning the position that the target locates within the matrix
	 * 
	 * Method1: start from top right.
	 * Method2: binary search
	 */
	
	/*
	 * task9
	 * Reverse a linked list using recursion
	 */
	
	/*
	 * task10: Given a binary tree where all the right nodes are leaf nodes, 
	 * flip it upside down and turn it into a tree with left leaf nodes.
	 */
	
	/*
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

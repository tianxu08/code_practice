package review;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Ch26_advanced5 {
	
	
	/*
	 * 1 largest product of length
	 * 2 kth smallest number in f(x,y,z) = 3^x  * 5^y * 7^z  (int x > 0, y>0, z>0)
	 * 3 Kth Closest Point To <0,0,0>
	 * 4 Place To Put Chair I
	 * 5 Largest Rectangle In Histogram
	 * 6 Max Water Trapped
	 */

	
	/*
	 * 1
	 * Largest Product Of Length
	 * Given a dictionary containing many words, 
	 * find the largest product of two words’ lengths, 
	 * such that the two words do not share any common characters.
	 * 
	 * Assumption: all letters in the word is from 'a-z' in ASCII
	 */
	public static int t1_largest_length_product(List<String> dict) {
		HashMap<String, Integer> bit_masks = t1_get_bit_masks(dict);
		// sort the dict according to string's length, in descending order
		Collections.sort(dict, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (o1.length() == o2.length()) {
					return 0;
				}
				return o1.length() > o2.length() ? -1 : 1;
			}
		});
		
		int largest = 0;
		for(int i = 1; i < dict.size(); i ++) {
			for(int j = 0; j < i; j ++) {
				int product = dict.get(i).length() * dict.get(j).length();
				if (product < largest) {
					break;
				}
				int i_mask = bit_masks.get(dict.get(i));
				int j_mask = bit_masks.get(dict.get(j));
				if ((i_mask & j_mask) == 0) {
					largest = product;
				}
			}
		}
		return largest;
	}
	
	private static HashMap<String, Integer> t1_get_bit_masks(List<String> dict) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String str : dict) {
			int bit_mask = 0;
			for(int i = 0; i < str.length(); i++) {
				bit_mask |= 1 << (int)(str.charAt(i) - 'a');
			}
			map.put(str, bit_mask);
		}
		return map;
	}	
	
	
	public static int t2_kth_smallest_product(int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		minHeap.offer(3*5*7);
		visited.add(3*5*7);
		while(k > 1) {
			int cur = minHeap.poll();
			if (!visited.add(3 * cur)) {
				minHeap.offer(3 * cur);
			}
			if (!visited.add(5 * cur)) {
				minHeap.offer(5 * cur);
			}
			if (!visited.add(7 * cur)) {
				minHeap.offer(7 * cur);
			}
			k --;
		}
		return minHeap.peek();
	}
	
	// method 2
	// use 3 deques
	public static int t2_kth_smallest_product2(int k) {
		int seed = 3 * 5 * 7;
		Deque<Integer> three = new LinkedList<Integer>();
		Deque<Integer> five = new LinkedList<Integer>();
		Deque<Integer> seven = new LinkedList<Integer>();
		three.add(seed * 3);
		five.add(seed * 5);
		seven.add(seed * 7);
		int result = seed;
		while(k > 1) {
			if (three.peekFirst() < five.peekFirst() && three.peekFirst() < seven.peekFirst()) {
				result = three.pollFirst();
				three.offerLast(result * 3);
				five.offerLast(result * 5);
				seven.offerLast(result * 7);
			} else if (five.peekFirst() < three.peekFirst() && five.peekFirst() < seven.peekFirst()) {
				result = five.pollFirst();
				five.offerLast(result * 5);
				seven.offerLast(result * 7);
			} else {
				result = seven.pollFirst();
				seven.offerLast(result * 7);
			}
			k --;
		}
		return result;
	}
	
	
	public static List<Integer> t3_kth_closest(int[] a, int[] b, int[] c) {
		final int[] af = a;
		final int[] bf = b;
		final int[] cf = c;
		
		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<List<Integer>>();
		return null;
	}
	
	private static long t3_get_distance(List<Integer> point, int[] a, int[] b, int[] c) {
		int aIdx = point.get(0);
		int bIdx = point.get(1);
		int cIdx = point.get(2);
		
		long dist = a[aIdx] * a[aIdx] + b[bIdx] * b[bIdx] + c[cIdx] * c[cIdx];
		
		return dist;
	}
	
	private static class Point{
		int x;
		int y; 
		int z;
		public Point(int _x, int _y, int _z){
			this.x = _x;
			this.y = _y;
			this.z = _z;
		}
	}
	
	
	
	
	

}

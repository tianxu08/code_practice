package mj_google;

import java.util.*;

public class Mianjing1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	/**
	 * 1. insert a number in ordered array. 应该是 LC35 变形，有duplicates。binary search解决。 
	 * 
	 * 2. find the center of mass in a 2D array. 就是找出数字左边sum和右边sum相差最小的那个 position。
	 * 
	 * 
	 * 
	 * 5. Design a data structure to show stock information of a company in a day: current price, high price and low price.

        functions that need implementations:
        1. update(timestamp t2, price): timestamp can be an old one or a new one. 
        2. delete(timestamp t1): delete the data at timestamp t1
        3. getCurrentPrice(), getHighPrice(), getLowPrice().
        Example: 
                                                                current        high         low
                        update(1, 5)                5                           5                        5.
                        update(2, 8)                8                           8                        5
                        update(1, 10)               8                           10                       8
                        delete(2)                  10                           10                       10

	 */
	
	
	public static void test() {
		int[] a = {1, 3, 5, 9, 10, 20};
		List<Integer> list = new ArrayList<>();
		for (Integer i : a) {
			list.add(i);
		}
		
		int target = 4;
		int idx = Collections.binarySearch(list, target);
		System.out.println("==>>>>:  " + idx);
		
	}
	
	

}

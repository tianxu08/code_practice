package mj_airbnb;

import java.util.Arrays;


public class Task18_RoundPrices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	public static class Elem {
		int floor;
		double diffToFloor;
		int index;
		public Elem (int floor, double diffToFloor, int index) {
			this.floor = floor;
			this.diffToFloor = diffToFloor;
			this.index = index;
		}
	}
	
	/**
	 * 
	 * arr =>  1.2, 2.5, 3.6, 4.0
	 * 
	 * floor=> 1    2    4    5
	 * diff => 0.2  0.5  0.6      sum = 1.3
	 * 
	 * 
	 * adjust 1 element
	 * adjust the element who has the largest diff
	 * 
	 */
	
	public static void test() {
		double[] input = {1.2, 2.5, 3.6, 4.0};
		int[] res = roundUp(input);
		System.out.println(Arrays.toString(res));
	}
	public static int[] roundUp(double[] input) {
		int len = input.length;
		Elem[] diffElems = new Elem[len];
		double diffSum = 0;
		
		// get the floor
		for (int i = 0; i < input.length; i++) {
			int floor = (int)input[i];
			double diff = input[i] - floor;
			diffSum += diff;
			
			Elem diffElem = new Elem(floor, diff, i);
			diffElems[i] = diffElem;
		}
		
		// sort the diffElem according to diffToFloor in descending order
		Arrays.sort(diffElems, (a, b)->{
			if (b.diffToFloor == a.diffToFloor) {
				return 0;
			} 
			return a.diffToFloor > b.diffToFloor ? -1 : 1;
		});
		
		int numsToAdjust = (int) (diffSum);
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			int index = diffElems[i].index;
			if (i < numsToAdjust) {
				result[index] = diffElems[i].floor + 1;
			} else {
				result[index] = diffElems[i].floor;
			}
		}
		
		return result;
	
	}
	
	
	

}

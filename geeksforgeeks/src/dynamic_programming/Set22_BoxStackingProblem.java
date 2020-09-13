package dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;

public class Set22_BoxStackingProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem
	 * 
	 * You are given a set of n types of rectangular 3-D boxes, where the i^th
	 * box has height h(i), width w(i) and depth d(i) (all real numbers). You
	 * want to create a stack of boxes which is as tall as possible, but you can
	 * only stack a box on top of another box if the dimensions of the 2-D base
	 * of the lower box are each strictly larger than those of the 2-D base of
	 * the higher box. Of course, you can rotate a box so that any side
	 * functions as its base. 
	 * It is also allowable to use multiple instances of the same type of box.
	 */
	
	public static class Box {
		int height;
		int width;
		int depth;
		
		public Box (int h, int w, int d) {
			this.height = h;
			this.width = w;
			this.depth = d;
		}
	}
	
	//we assume that width > depth
	public static int maxDepth(Box[] input) {
		int n = input.length;
		Box[] rot = new Box[3*n];
		int index = 0;
		//pub the input into the rot
		for (int i = 0; i < n; i++) {
			Box rot1 = new Box(input[i].height,
							   Math.max(input[i].width, input[i].depth),
							   Math.min(input[i].width, input[i].depth));
			rot[index] = rot1;
			
			index ++;
			
			Box rot2 = new Box(input[i].width,
							   Math.max(input[i].height, input[i].depth), 
					           Math.min(input[i].height, input[i].depth));
			rot[index] = rot2;
			index ++;
			Box rot3 = new Box(input[i].depth, 
							   Math.max(input[i].height, input[i].width),
							   Math.min(input[i].height, input[i].width));
			rot[index] = rot3;
			index ++;
		}
		Comparator<Box> comp = new Comparator<Box>() {

			@Override
			public int compare(Box o1, Box o2) {
				// TODO Auto-generated method stub
				return o2.depth * o2.width - o1.depth * o1.width;
			}
		};
		Arrays.sort(rot, comp);
		for (int i = 0; i < rot.length; i++) {
			System.out.println("   height = " + rot[i].height + 
								"  width = " + rot[i].width + 
								"  depth = " + rot[i].depth);
		}
		
		//now we have a 3*n Box[];
		//Use the Longest Increasing Sequence Idea.
		// h[i] stands the height of 
		int[] h = new int[3*n];
		//height is unsorted
		for (int i = 0; i < h.length; i++) {
			h[i] = rot[i].height;
		}
		
		// j is smaller than i, so rot[j].width > rot[i].width &&  rot[j].depth > rot[i].depth
		for (int i = 1; i < rot.length; i++) {
			for (int j = 0; j < i; j++) {
				if (rot[j].width > rot[i].width &&
					rot[j].depth > rot[i].depth ) {
					h[i] = Math.max(h[j] + rot[i].height, h[i]);
				}
			}
		}
		
		for (int i = 0; i < h.length; i++) {
			System.out.print(h[i]+ " ");
		}
		System.out.println();
		
		int maxHeight = Integer.MIN_VALUE;
		for (int i = 0; i < h.length; i++) {
			maxHeight = Math.max(maxHeight, h[i]);
		}
		return maxHeight;
		
	}
	/*
	 * Time: O(n^2)
	 * Space: O(n)
	 */
	
	public static void test() {
		Box[] arr = { new Box(4, 6, 7), new Box(1, 2, 3),new Box(4, 5, 6),new Box(10, 12, 32) };
		int maxHeight = maxDepth(arr);
		System.out.println("maxHeight = " + maxHeight);
	}
	

}

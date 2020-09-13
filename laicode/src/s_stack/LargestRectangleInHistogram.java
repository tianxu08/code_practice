package stack;

import java.util.LinkedList;
import java.util.Stack;

public class LargestRectangleInHistogram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * Given a non-negative integer array 
	 * representing the heights of a list of adjacent bars. 
	 * Suppose each bar has a width of 1. 
	 * Find the largest rectangular area that can be formed in the histogram.
	 * Assumptions
	 * The given array is not null or empty
	 * Examples
	 * { 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting 
	 * from index 2 and ending at index 4)
	 * 
	 * we maintain a increasing stack. 
	 * for every current element, if it is smaller than st.peek(). 
	 * keep pop until the st.peek() is smaller than cur element or the stack is empty. 
	 * meanwhile, calculate the area of the rectangle. 
	 */
	
	public static int largestRectInHistogram(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		LinkedList<Integer> st = new LinkedList<Integer>();
		int maxArea = 0;
		for(int i = 0; i <= n; i ++) {
			int cur = i == n ? -1 : array[i];
			while( !st.isEmpty() && cur < array[st.peek()]) {
				int curHeight = array[st.pop()];
				int curWidth = st.isEmpty() ? i : (i - st.peek() - 1); 
				int curArea = curHeight * curWidth;
				maxArea = Math.max(maxArea, curArea);
			}
			st.push(i);
		}
		System.out.println("maxArea = " + maxArea);
		return maxArea;
	}
	
	
	public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        System.out.println("max = " + max);
        
        return max;
    }
	public static void test() {
		int[] a = {2, 1, 3, 3, 4};
		int maxArea = largestRectInHistogram(a);
		int maxArea2 = largestRectangleArea(a);
		
		
	}

}

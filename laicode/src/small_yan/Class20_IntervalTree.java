package small_yan;

public class Class20_IntervalTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * See IntervalTree.java in the same package.
	 */

	/*
	 * task0.1 
	 * determine if a target point if covered by a list of intervals(no intersect), sort by the start
	 * 
	 * Binary Search
	 * Find the largest smaller or equals to target point
	 * 
	 */
	
	/*
	 * task0.2
	 * 
	 * whether a given interval is overlapped with any of the intervals? 
	 * [0,2][4,6][14,16][20,24]
	 * [7,8] is NOT overlapped with any of the intervals
	 * [5,8] is overlapped with any of the intervals
	 * 
	 * Binary Search: the largest smaller or equals to start of the given interval
	 */
	
	
	/*
	 * task0.3
	 * the given set of intervals could be already overlapped
	 * [0,2][4,12][8,10][15,19]
	 * is 11 covered by the list of intervals?
	 * is [5,8] overlapped with any of the intervals? 
	 * naive method1:
	 * Binary Search find the largest smaller or equals, traverse all the intervals at left side
	 * O(n)
	 * 
	 * 
	 */
	
	
}

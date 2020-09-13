package lai_online2;

public class Class27 {

	/*
	 * task1
	 
	Longest Ascending Subsequence
	Fair
	DP
	Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

	Assumptions

	A is not null
	Examples
	Input: A = {5, 2, 6, 3, 4, 7, 5}
	Output: 4
	Because [2, 3, 4, 5] is the longest ascending subsequence.
	*/
	// DP:  M[i] stands that the length of longest ascending subsequence ending with array[i]. 
	  // base case: M[0] = 1;
	  // induction rule: M[i] = array[i] > array[j] && M[j] + 1 > M[i] ? M[i] = M[j] + 1  (j >= 0 && j < i)
	public int longest(int[] array) {
		// Write your solution here.
		if (array == null || array.length == 0) {
			return 0;
		}
		if (array.length == 1) {
			return 1;
		}
		int n = array.length;
		int[] M = new int[n];
		M[0] = 1;
		for (int i = 1; i < n; i++) {
			M[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[i] > array[j] && M[j] + 1 > M[i]) {
					M[i] = M[j] + 1;
				}
			}
		}

		// scan M[], get the max
		int max = 1;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, M[i]);
		}
		return max;

	}
	
	/*
	 * task2
	Largest Set Of Points With Positive Slope
	Fair
	DP
	Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can form a set such that any pair of points in the set can form a line with positive slope. Return the size of such a maximal set.

	Assumptions

	The given array is not null and is not empty
	Examples

	<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.
	*/
	/*
	* class Point {
	*   public int x;
	*   public int y;
	*   public Point(int x, int y) {
	*     this.x = x;
	*     this.y = y;
	*   }
	* }
	*/
	public int task2_largest(Point[] points) {
		// write your solution here
		return 0;
	}
	
	
	/*
	 * task3
	 *
	Most Points On A Line
	Fair
	Data Structure
	Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can be crossed by a single line in 2D space.

	Assumptions

	The given array is not null and it has at least 2 points
	Examples

	<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)
	*/
	
	/*
	* class Point {
	*   public int x;
	*   public int y;
	*   public Point(int x, int y) {
	*     this.x = x;
	*     this.y = y;
	*   }
	* }
	*/
	public static class Point{
		public int x;
		public int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public int task3_most(Point[] points) {
	    // write your solution here
	    return 0;
	  }
	
	
	/*
	 * task4
	 * 
	Generate Random Maze
	Hard
	Recursion
	Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor is denoted by 0.

	Assumptions

	N = 2K + 1 and K >= 0
	the top left corner must be corridor
	there should be as many corridor cells as possible
	for each pair of cells on the corridor, there must exist one and only one path between them
	Examples

	N = 5, one possible maze generated is

	        0  0  0  1  0

	        1  1  0  1  0

	        0  1  0  0  0

	        0  1  1  1  0

	        0  0  0  0  0
	 */
	public int[][] task4_maze(int n) {
		// write your solution here
		return new int[n][n];
	}
	
}

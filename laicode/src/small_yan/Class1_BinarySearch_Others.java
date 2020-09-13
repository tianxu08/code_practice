package small_yan;

import java.util.ArrayList;

import small_yan.IntervalTree.ITNode;
import debug.Debug;

public class Class1_BinarySearch_Others {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test8();
	}
	
	/*
	 * One Dimension → Two Dimension
	 * 1. given an unsorted integer array, return the position of any local minimum. → two dimension
	 * 6. given an unsorted integer matrix, return the position of any local minimum. does it exists?
	 * 
	 * M * N matrix, N个竖条.
	 * array[mid] → size = M的array, check the whole sized M array. find the minimum element of the array.
	 * | |​   |  ​   ​| | | 
	 *      mid
	 *      O(M *logN)
	 *      Thoughts:
	 *      1. solve the one dimensional problem first, then extends to two dimensions.
	 *      2. partition the 2d spaces directly.
	 */
	
	
	/*
	 * Young’s Matrix
	 * see the handout
	 * 
	 * 
	 * 1). if you start looking at the matrix from the top left corner, it is a h​eap.​
	 * 2). if you start looking at the matrix from the top right corner, it is a b​inary search tree.​ 
	 * 3). it is natural to think of a d​ivide & conquer​solution for problems on Young’s Matrix.
	 * What are the typical interview questions?
	 * 1. smallest/largest k elements
	 * 2. kth smallest/largest
	 * 3. search for a value in the Young Matrix
	 * 4. two (sorted) array, what is the kth smallest sum if picking one element from each of the array.
	 * 5. Two sorted array A[] and B[], exist two elements such that a from A[] and b from B[], a + b = target?
	 * 
	 * 
	 * 1’. Two sorted array, pick one element from each of them, what is the kth smallest sum.
	 * A = {1, 3, 6}
	 * B = {2, 7, 8} 
	 * M[i][j] = A[i] + B[j]
	 * 
	 * 6. search for a value in the Young Matrix
	 * 1 O(m + n) search from top right or from left buttom . 
	 * 2 Divide the matrix directly. 
	 *   Time: O(log m + log n)
	 * see the hand out. 
	 * 
	 * 
	 * 7. number of values <= 0 in Young’s Matrix.
	 * ­-5 , ­ -3 , 0​,  1  3 ­ 
	 * -2 ,  2 , 3,  ​4  1
	 * -1 ,  ​5 , 6 , 7  1
	 *  0 ,  ​6 , 7 , 9  1
	 *  
	 *  
	 *  Method 1: smallest k elements → O(N * N log(N * N))
	 *  Method 2: binary search for each row, O(N * logN)
	 *  Method 3: search for <= 0, when move down, 
	 *  we can determine how many values <= 0 at the left side of the current row. O(N + N)
	 *  
	 * 7.1 two sorted array, pick one element from each of them, how many pairs has sum <= k.
	 * 
	 * 
	 * 
	 * 8. i​mage matrix with black(1)/white(0) cells, such that there is a black object , 
	 * given a random black point, 
	 * how do you determine the smallest rectangle can cover all the area of black cells.
	 * 
	 * !!! see handout. 
	 */
	
	/*
	 * task8 
	 * see leetcode 302
	 */
	public static void  test8() {
		char[][] matrix = {
				"oxoooo".toCharArray(),
				"oxxxxo".toCharArray(),
				"ooxxxo".toCharArray(), 
				"ooxxoo".toCharArray(), 
				"oooxoo".toCharArray()
		};
		ArrayList<Point> result = task8_getSmallestRect2(matrix);
		
	}
	
	
	public static class Point{
		int x ;
		int y;
		public Point(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
	public static ArrayList<Point> task8_getSmallestRect(char[][] matrix) {
		// sanity check
		if (matrix == null || matrix.length == 0 ||matrix[0] == null || matrix[0].length == 0) {
			return null;
		}
		int[][] left2Right = task8_getLeft2Right(matrix);
		int[][] up2Down = task8_getUp2Down(matrix);
		Debug.printMatrix(left2Right);
		Debug.printMatrix(up2Down);
		
		int rLen = matrix.length;
		int cLen = matrix[0].length;

		int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE;
		int x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
		// left to right
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				// update x1, y1
				if (left2Right[i][j] != 0) {
					x1 = Math.min(x1, i);
					y1 = Math.min(y1, j);
					x2 = Math.max(x2, i);
					y2 = Math.max(y2, j);
				}
				if (up2Down[i][j] != 0) {
					x1 = Math.min(x1, i);
					y1 = Math.min(y1, j);
					x2 = Math.max(x2, i);
					y2 = Math.max(y2, j);
				}
			}
		}
		
		System.out.println("x1 = " + x1 + " : " + "y1 = " + y1);
		System.out.println("x2 = " + x2 + " : " + "y2 = " + y2);
		
		
		// 
		return null;
	}
	
	
	// there is no need to construct the left2Right and up2Down matrix. 
	public static ArrayList<Point> task8_getSmallestRect2(char[][] matrix) {
		// sanity check
		if (matrix == null || matrix.length == 0 ||matrix[0] == null || matrix[0].length == 0) {
			return null;
		}
	
		int rLen = matrix.length;
		int cLen = matrix[0].length;

		int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE;
		int x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
		// left to right
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				// update x1, y1
				if (matrix[i][j] != 'o') {
					x1 = Math.min(x1, i);
					y1 = Math.min(y1, j);
					x2 = Math.max(x2, i);
					y2 = Math.max(y2, j);
				}
				if (matrix[i][j] != 'o') {
					x1 = Math.min(x1, i);
					y1 = Math.min(y1, j);
					x2 = Math.max(x2, i);
					y2 = Math.max(y2, j);
				}
			}
		}
		
		System.out.println("x1 = " + x1 + " : " + "y1 = " + y1);
		System.out.println("x2 = " + x2 + " : " + "y2 = " + y2);
		
		
		// 
		return null;
	}
	
	
	public static int[][] task8_getLeft2Right(char[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] left2Right = new int[rLen][cLen];
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (j == 0) {
					if (matrix[i][j] == 'o') {
						left2Right[i][j] = 0;
					} else {
						left2Right[i][j] = 1;
					}
				} else {
					if (matrix[i][j] == 'o') {
						left2Right[i][j] = 0;
					} else {
						left2Right[i][j] = 1 + left2Right[i][j - 1];
					}
				}
			}
		}
		return left2Right;
	}
	
	public static int[][]  task8_getUp2Down(char[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] up2Down = new int[rLen][cLen];
		
		for(int j = 0; j < cLen; j ++) {
			for(int i = 0; i < rLen; i ++) {
				if (i == 0) {
					if (matrix[i][j] == 'o') {
						up2Down[i][j] = 0;
					} else {
						up2Down[i][j] = 1;
					}
				} else {
					if (matrix[i][j] == 'o') {
						up2Down[i][j] = 0;
					} else {
						up2Down[i][j] = up2Down[i - 1][j] + 1;
					}
				}
			}
		}
		return up2Down;
	}
	
	
	

}

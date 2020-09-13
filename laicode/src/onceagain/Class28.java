package onceagain;

public class Class28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/*
	 * task 1
	 * Longest Ascending Subsequence
	 * M[i] stands the length of longest ascending subsequence ending array[i]
	 * 
	 * M[i] = M[j] + 1 (j >= 0 && j < i && array[i] > array[j])
	 * O(n*n)       
	 */
	
	public static int task1_longestAscendingSequence(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int len = array.length;
		int[] M = new int[len];
		int longestLen = 0;
		for(int i = 0; i < array.length; i++) {
			if (i == 0) {
				M[i] = 1;
			} else {
				int prevLenM = 0;
				for(int j = i - 1; j >= 0; j --) {
					if (array[i] > array[j]) {
						prevLenM = Math.max(prevLenM, M[j]);
					}
				}
				M[i] = prevLenM + 1;
			}
			longestLen = Math.max(longestLen, M[i]);
		}
		return longestLen; 
	}
	public static void test1() {
		int[] array = {4,1,9,2,6,3};
		int len = task1_longestAscendingSequence(array);
		System.out.println("len = " + len);
	}
	
	
	
	/*
	 * task2
	 * Reconstruct Binary Tree With Preorder And Inorder
	 */
	
	/*
	 * task3
	 * Reconstruct Binary Search Tree With Postorder
	 */
	
	/*
	 * task4
	 * Reconstruct Binary Tree With Levelorder And Inorder
	 */
	
	/*
	 * task6
	 * Max Number Of Points On The Same Line
	 */
	
	/*
	 * task7
	 * Largest Set Of Points With Positive Slopes
	 */
	
	/*
	 * task8
	 * Randomly Generate Maze
	 */

}

package lai_online2;
import java.util.*;
public class Class13 {
	/*
	 * task1
	Array Hopper II
	Fair
	DP
	Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.

	Assumptions

	The given array is not null and has length of at least 1.
	Examples

	{3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)

	{2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
	*/
	// M[i] stands the minimum jump steps to reach array[n-1]
	// base case: M[n-1] = 0;
	// induction rule: M[i] = 1 + min{M[j]} is all elements that can be reached
	// by 1 jump from i
	public int task1_minJump(int[] array) {
		// write your solution here
		if (array == null) {
			return 0;
		}
		if (array.length <= 0) {
			return 0;
		}
		int n = array.length;
		int[] M = new int[n];
		M[n - 1] = 0;
		for (int i = n - 2; i >= 0; i--) {
			if (array[i] == 0) {
				M[i] = Integer.MAX_VALUE;
			} else if (i + array[i] >= n - 1) {
				M[i] = 1;
			} else {
				int curMin = Integer.MAX_VALUE;
				for (int j = i + 1; j < n && j <= i + array[i]; j++) {
					curMin = Math.min(curMin, M[j]);
				}
				if (curMin != Integer.MAX_VALUE) {
					M[i] = 1 + curMin;
				} else {
					M[i] = Integer.MAX_VALUE;
				}
			}
		}
		if (M[0] == Integer.MAX_VALUE) {
			return -1;
		}
		return M[0];
	}
	
	/*
	 * task2
	Array Hopper III
	Fair
	DP
	Given an array of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). Determine the minimum number of jumps you need to jump out of the array.

	By jump out, it means you can not stay at the end of the array. Return -1 if you can not do so.

	Assumptions

	The given array is not null and has length of at least 1.
	Examples

	{1, 3, 2, 0, 2}, the minimum number of jumps needed is 3 (jump to index 1 then to the end of array, then jump out)

	{3, 2, 1, 1, 0}, you are not able to jump out of array, return -1 in this case.
	*/
	// M[i] stands the minimum steps from array[i] to jump out of array
	// we can reduce this problem to jump to array[n], n = array.length;
	// base case:
	// first, we might need to scan the array from right to left.
	public int task2_minJump(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}
		int n = array.length;
		int[] M = new int[n + 1];
		M[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (array[i] == 0) {
				M[i] = Integer.MAX_VALUE;
			} else if (i + array[i] >= n) {
				M[i] = 1;
			} else {
				int curMin = Integer.MAX_VALUE;
				for (int j = i + 1; j < n && j <= i + array[i]; j++) {
					curMin = Math.min(curMin, M[j]);
				}
				if (curMin == Integer.MAX_VALUE) {
					M[i] = Integer.MAX_VALUE;
				} else {
					M[i] = 1 + curMin;
				}
			}
		}
		if (M[0] == Integer.MAX_VALUE) {
			return -1;
		}

		return M[0];
	}
	
	/*
	task3
	Dictionary Word I
	Fair
	DP
	Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.

	Assumptions

	The given word is not null and is not empty
	The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
	Examples

	Dictionary: {“bob”, “cat”, “rob”}

	Word: “robob” return false

	Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
	*/
	 /*
    M[i] stands for the first i characters ending with input.charAt(i - 1) is in dict
    base case: M[0] = true
    induction rule: M[i] = if there exist one j, j>= 0 && j < i, M[j] && input.sub(i - j, i) in the dict
                           is True
  */
  public boolean task3_canBreak(String input, String[] dict) {
    // write your solution here
    if(input == null || input.length() == 0) {
      return true;
    }  
    Set<String> set = new HashSet<String>();
    for(String s: dict) {
      set.add(s);
    }
    
    int n = input.length();
    boolean[] M = new boolean[n + 1];  // !!! 
    M[0] = true;
    for(int i = 1; i<=n; i++) {
      for(int j = 0; j < i; j++) {
        String second = input.substring(j, i);
        if(M[j] == true && set.contains(second)) {
          M[i] = true;
          break;
        }
      }
    }
    return M[n];
  }
  
  /*
  task4
  Edit Distance
  Fair
  DP
  Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

  Assumptions

  Both strings are not null
  Examples

  string one: “sigh”, string two : “asith”

  the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
  */
  /*
  M[i][j] stands that the minimum distance between the first i characters in one, which is one[0, i - 1]
          and the first j characters in two, which is two[0, j - 1]
  base case: M[0][0] = 0
             M[i][0] = i;
             M[0][j] = j;
  induction rule: M[i][j] =   
                            one.charAt(i - 1) == two.charAt(j - 1)  M[i-1][j - 1]
                            else    min(M[i-1][j], M[i][j - 1], M[i-1][j-1]) + 1
  !!! string.length()
  */
  public int task4_editDistance(String one, String two) {
    // write your solution here
    if(one == null && two == null) {
       return 0;
    }
    if(one == null) {
      return two.length();
    }
    if(two == null) {
      return one.length();
    }
    int m = one.length();
    int n = two.length();
    int[][] M = new int[m+1][n+1];
    
    //initaizlie
    M[0][0] = 0;
    for(int i = 1; i <= m; i++) {
      M[i][0] = i;
    }
    
    for(int j = 1; j <= n; j++) {
      M[0][j] = j;
    }
    
    for(int i = 1; i <= m; i++) {
      for(int j = 1; j <= n; j++) {
        if(one.charAt(i - 1) == two.charAt(j - 1)) {
          M[i][j] = M[i-1][j - 1];
        } else {
          M[i][j] = Math.min(Math.min(M[i-1][j], M[i][j-1]), M[i-1][j-1]) + 1;
        }
      }
    }
    
    return M[m][n];
  }
  /*
  task5
  Largest Square Of 1s
  Hard
  DP
  Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

  Assumptions

  The given matrix is not null and guaranteed to be of size N * N, N >= 0
  Examples

  { {0, 0, 0, 0},

    {1, 1, 1, 1},

    {0, 1, 1, 1},

    {1, 0, 1, 1}}

  the largest square of 1s has length of 2
  */
	public int task5_largest(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		int maxLen = 0;
		int maxX = -1;
		int maxY = -1;

		// initialize
		// copy the first row and first column form matrix to M
		for (int i = 0; i < rLen; i++) {
			M[i][0] = matrix[i][0];
			if (maxLen < M[i][0]) {
				maxLen = M[i][0];
				maxX = i;
				maxY = 0;
			}
		}
		for (int j = 0; j < cLen; j++) {
			M[0][j] = matrix[0][j];
			if (maxLen < M[0][j]) {
				maxLen = M[0][j];
				maxX = 0;
				maxY = j;
			}
		}

		for (int i = 1; i < rLen; i++) {
			for (int j = 1; j < cLen; j++) {
				if (matrix[i][j] == 1) {
					M[i][j] = Math.min(Math.min(M[i - 1][j], M[i - 1][j - 1]),
							M[i][j - 1]) + 1;
					if (maxLen < M[i][j]) {
						maxLen = M[i][j];
						maxX = i;
						maxY = j;
					}
				} else {
					M[i][j] = 0;
				}

			}
		}

		return maxLen;

	}

}

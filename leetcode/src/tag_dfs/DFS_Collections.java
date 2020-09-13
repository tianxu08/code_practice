package tag_dfs;

import java.util.*;

public class DFS_Collections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test531();
		test542();
	}
	
	
	
	
	
	
	/*
	 * 472. Concatenated Words
	 */
	
	
	/*
	 * 473  Matchsticks to Square
	 */
	 public static boolean makesquare(int[] nums) {
		 return false;
	 }
	 
	 /*
	  * 491. Increasing Subsequences
	  */
	public static List<List<Integer>> l491_findSubsequences(int[] nums) {
		return null;
	}
	
	

	/*
	 * 531 lonely pixel I
	 */
	public static void test531() {
		char[][] picture = {
				"WWB".toCharArray(),
				"WBW".toCharArray(),
				"BWW".toCharArray()
		};
		
		int rev = l531_findLonelyPixel(picture);
		System.out.println("rev = " + rev);
	}
	
	public static int l531_findLonelyPixel(char[][] picture) {
		if (picture == null || picture.length == 0 || picture[0] == null || picture[0].length == 0) {
			return 0;
		}
		int rLen = picture.length;
		int cLen = picture[0].length;
		int[] rCount = new int[rLen];
		int[] cCount = new int[cLen];
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (picture[i][j] == 'B') {
					rCount[i]++;
					cCount[j]++;
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j++) {
				if (picture[i][j] == 'B' && rCount[i] == 1 && cCount[j] == 1 ) {
					count ++;
				}
			}
		}
		return count;
    }
	
	/*
	 * use the first row 
	 */
	public static int l491_findSubsequences_opt(char[][] picture) {
		int rLen = picture.length;
		int cLen = picture[0].length;

		int firstRowCount = 0;

		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (picture[i][j] == 'B') {
					if (picture[0][j] < 'Y' && picture[0][j] != 'V')
						picture[0][j]++;
					if (i == 0)
						firstRowCount++;
					else if (picture[i][0] < 'Y' && picture[i][0] != 'V')
						picture[i][0]++;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (picture[i][j] < 'W'
						&& (picture[0][j] == 'C' || picture[0][j] == 'X')) {
					if (i == 0)
						count += firstRowCount == 1 ? 1 : 0;
					else if (picture[i][0] == 'C' || picture[i][0] == 'X')
						count++;
				}
			}
		}
		return count;
	}
	
	
	/*
	 * 542
	 */
	
	public static void test542() {
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		int[][] arr2d = {
				{0, 0, 0},
				{0, 1, 0},
				{1, 1, 1}
		};
		for(int[] row : arr2d) {
			List<Integer> list = new ArrayList<Integer>();
			for(Integer i : row) {
				list.add(i);
			}
			matrix.add(list);
		}
		System.out.println(matrix);
		
		for(List<Integer> list : matrix) {
			System.out.println(list);
		}
		System.out.println();
		
		matrix = l542_updateMatrix(matrix);
		System.out.println("------------");
		for(List<Integer> list : matrix) {
			System.out.println(list);
		}
		System.out.println();
		
		
	}
	
	public static List<List<Integer>> l542_updateMatrix(List<List<Integer>> matrix) {
		int rLen = matrix.size();
		int cLen = matrix.get(0).size();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		// put all 0 elements into queue
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0;  j < cLen; j ++) {
				if (matrix.get(i).get(j) == 0) {
					queue.offer(i * cLen + j);
				} else {
					// set to MAX
					matrix.get(i).set(j, Integer.MAX_VALUE);
				}
			}
		}
		
		// do BFS
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		while (!queue.isEmpty()) {
			int curPos = queue.poll();
			int curX = curPos / cLen;
			int curY = curPos % cLen;
			
			for(int k = 0; k < 4; k ++) {
				int nextX = curX + dx[k];
				int nextY = curY + dy[k];
				
				if (nextX < 0 || nextX >= rLen || nextY < 0 || nextY >= cLen) {
					continue;
				}
				
				if (matrix.get(nextX).get(nextY) < matrix.get(curX).get(curY) + 1) {
					continue;
				}
				
				queue.offer(nextX * cLen + nextY);
				
				matrix.get(nextX).set(nextY, matrix.get(curX).get(curY) + 1);
				
			}
		}
		return matrix;
		
	}
	
	
	
	
	
	

}

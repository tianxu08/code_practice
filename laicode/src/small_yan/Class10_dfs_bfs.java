package small_yan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Class10_dfs_bfs {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
//		test3();
//		test17();
//		test17_1();
//		test17_2();
	}
	
	/*
	 * task1
	 * 输出整数分解的全部解,解要从大到小的输出.
	 * example: 
	 * input: 12
	 * output: 
	 * [12]
	 * [6,2]
	 * [4,3]
	 * [3,2,2]
	 * 
	 */
	public static void test1() {
		int num = 12;
		List<List<Integer>> result = task1_divide(num);
		System.out.println("result is ");
		System.out.println(result);
	}
	
	public static List<List<Integer>> task1_divide(int num) {
		List<Integer> path = new ArrayList<Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>(); 		
//		task1_helper(num, path, result);
		task1_helper_better(num, path, result);
		return result;
	}
	
	/*
	 * 只要保证你生成的每个序列都是严格非递增的就可以了，下一层最大可以选的数是上一层选的数
	 * 比如6，
	 * 在第一层，可以选的最大的数是6， 那么有三种可能：6，3， 2
	 * 那么如果选3，下一层可选的最大的数是3
	 * 如果选2，下一层可选的最大数是2
	 * 这样就不会有重复。
	 */
	public static void task1_helper_better(
			int num, 
			List<Integer> path, 
			List<List<Integer>> result) {
		if (num == 1) {
			result.add(new ArrayList<Integer>(path));
			return ;
		}
		for(int i = num; i > 1; i --) {
			if (!path.isEmpty() && i > path.get(path.size() - 1) ) {
				// if i > path[lastIndex], continue
				// 每个序列都是严格非递增的就可以了，下一层最大可以选的数是上一层选的数
				// 下一层的数不能比上一层的数大
				continue;
			}
			if (num %i == 0) {
				path.add(i);
				task1_helper_better(num/i, path, result);
				path.remove(path.size() - 1);
			}
		}
	}
	
	/*
	 *  task2
	 *  
	 *  给出一个数n,找出所有Unique的组合
	 *  e.g
	 *  2: [1,1]
	 *  3: [1,2] [1,1,1]
	 *  4:[1,3][1,1,2][1,1,1,1][2,2] 
	 */
	
	public static void test2() {
		int n = 4;
		List<List<Integer>> result = task2_uniqueComb(n);
		System.out.println(result);
	}
	
	public static List<List<Integer>> task2_uniqueComb(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		task2_helper(n, result, path);
		return result;
	}
	
	// this is a helper function to get the result
	// ArrayList<Integer> path is used to store temporary result
	public static void task2_helper(int n, List<List<Integer>> result, List<Integer> path) {
		if (n < 0) {
			return ;
		}
		if (n == 0) {
			result.add(new ArrayList<Integer>(path));
			// get a result, return 
			return ;
		}
		
		for(int i = 1; i <=n; i ++) {
			if (!path.isEmpty() && i < path.get(path.size() - 1)) {
				continue;
			}
			path.add(i);
			task2_helper(n - i, result, path);
			path.remove(path.size() - 1);
		}
	}
	
	
	/*
	 * task3
	 * Ocean
	 * see Ocean in the same file
	 */
	
	
	/*
	 * task4
	 * 
	 * Boggle Game
	 * 
	 * Given a matrix of characters, you can move from one cell to neighbor cell(up, down, left, right), 
	 * each cell can be used only once. can you find the path of a given word?
	 * 
	 * A B​ C D 
	 * D ​E E​ G 
	 * F I ​H I 
	 * J K L M
	 * 
	 * strings = ABEEHI
	 * 
	 * mark visited only on the current DFS path. 
	 * DFS + backtracking (when backtracking, the marked visited node to be marked unvisited)
	 */
	public static void test3() {
		char[][] matrix = {			
				{'A','B','C','D'},
				{'E','E','E','G'},
				{'F','I','H','I'},
				{'J','K','L','M'}
		};
		String str = "ABEEHIM";
		
		boolean result = task3_exist_string(matrix, str);
		System.out.println("result = " + result);
	}
	
	/*
	 * this function returns whether we can find the str in char matrix
	 */
	public static boolean task3_exist_string(char[][] matrix, String str) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return false;
		}
		if (str == null || str.length() == 0) {
			return false;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		boolean[][] visited = new boolean[rLen][cLen];
		
		// start from every position(i, j)
		for(int i = 0; i < matrix.length; i ++) {
			for(int j = 0; j < matrix[0].length; j ++) {
				if (task3_helper(matrix, str, visited, 0, i, j)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * this is a helper function to search from position(rowIdx, colIdx) to check whether 
	 * we can find str 
	 */
	public static boolean task3_helper(char[][] matrix, String str, boolean[][] visited, 
			int index, int rowIndex, int colIndex) {
		// base case, we reach out of str
		if (index == str.length()) {
			return true;
		}
		// index out of bound or already visited or matrix[rowIdx][colIdx] != str.charAt(idx)
		// return false
		if (rowIndex < 0 || rowIndex >= matrix.length ||
				colIndex < 0 || colIndex >= matrix[0].length ||
				visited[rowIndex][colIndex] || 
				matrix[rowIndex][colIndex] != str.charAt(index)) {
			return false;
		}
		
		visited[rowIndex][colIndex] = true;
		
		// next index, in 4 directions
		boolean result = false;
		for(int i = 0; i < 4; i ++) {
			int next_x = rowIndex + dx[i];
			int next_y = colIndex + dy[i];
			result = result || task3_helper(matrix, str, visited, index + 1, next_x, next_y);
		}
		visited[rowIndex][colIndex] = false;
		
		return result;		         
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0,  0, 1, 1};
	
	
	/*
	 * task5
	 * Shortest distance to police 
	 * see ShortestDistance2Police in same file
	 */
	
	
	/*
	 * task6
	 * Manhattan Distance
	 *
	 * find median
	 */
	
	
	
	
	/*
	 * task7
	 * Letter combination phone number II
	 * 
	 * DFS
	 * 
	 * additional requirement: no duplicates set of characters for the same number
	 * 
	 * leetcode task17  in leetcode5
	 */
	public static void test17() {
		String digits = "222";
		List<String> result = task17_letterCombinations(digits);
		System.out.println(result);
	}

	public static List<String> task17_letterCombinations(String digits) {
		List<String> result = new ArrayList<String>();
		if (digits == null || digits.length() == 0) {
			return result;
		}

		StringBuilder stb = new StringBuilder();
		// build the map
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");

		task17_helper(digits, stb, 0, map, result);
		return result;

	}

	public static void task17_helper(String digits, StringBuilder stb,
			int index, HashMap<Integer, String> map, List<String> result) {
		if (index == digits.length()) {
			// get a reasonable result
			String oneresult = stb.toString();
			result.add(oneresult);
			return;
		}
		
		Integer curVal = (int) digits.charAt(index) - '0';
		String curStr = map.get(curVal);
		for (int i = 0; i < curStr.length(); i++) {
			stb.append(curStr.charAt(i));
			task17_helper(digits, stb, index + 1, map, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}

	/*
	 * follow up iterative
	 */
	public static void test17_2() {
		String str = "222";
		List<String> rev1 = letterCombinations(str);
		System.out.println(rev1);
		System.out.println("----------------");
		List<String> rev2 = task17_letterCombinations(str);
		System.out.println(rev2);
	}

	public static List<String> letterCombinations(String digits) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");
		LinkedList<String> list = new LinkedList<String>();
		list.add("");
		for (int i = 0; i < digits.length(); i++) {
			int num = digits.charAt(i) - '0';
			int size = list.size();
			for (int k = 0; k < size; k++) {
				String tmp = list.pop();
				for (int j = 0; j < map.get(num).length(); j++)
					list.add(tmp + map.get(num).charAt(j));
			}
		}
		List<String> rec = new LinkedList<String>();
		rec.addAll(list);
		return rec;
	}

	// follow up
	// additional requirement: no duplicate set of characters for the same
	// number.
	// HashMap<Integer, HashSet<Character>> visited 用这个来去重复
	public static void test17_1() {
		String digits = "222";
		List<String> result = task17_letterCombinations2(digits);
		System.out.println(result);
	}

	public static List<String> task17_letterCombinations2(String digits) {
		List<String> result = new ArrayList<String>();
		if (digits == null || digits.length() == 0) {
			return result;
		}

		StringBuilder stb = new StringBuilder();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");

		HashMap<Integer, HashSet<Character>> visited = new HashMap<Integer, HashSet<Character>>();

		task17_helper2(digits, stb, 0, map, result, visited);
		return result;
	}

	public static void task17_helper2(String digits, StringBuilder stb,
			int index, HashMap<Integer, String> map, List<String> result,
			HashMap<Integer, HashSet<Character>> visited) {
		if (index == digits.length()) {
			// get a reasonable result
			String oneresult = stb.toString();
			result.add(oneresult);
			return;
		}
		Integer curVal = (int) digits.charAt(index) - '0';
		String curStr = map.get(curVal);

		for (int i = 0; i < curStr.length(); i++) {
			char curCh = curStr.charAt(i);

			if (visited.containsKey(curVal)
					&& visited.get(curVal).contains(curCh)) {
				continue;
			}
			stb.append(curCh);
			if (!visited.containsKey(curVal)) {
				visited.put(curVal, new HashSet<Character>());
			}
			visited.get(curVal).add(curCh);
			task17_helper2(digits, stb, index + 1, map, result, visited);

			stb.deleteCharAt(stb.length() - 1);
			visited.get(curVal).remove(curCh);
		}
	}
	
	
	
	/*
	 * task8
	 * 一个球从起点开始滚， 看能不能滚到终点
	 * see BFS_ball_reach_another
	 */
	
	

	
	
	
	

}

package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
		test5();
	}	

	/*
	 * task1 Write a C program to print all permutations of a given string
	 * http:/
	 * /www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of
	 * -a-given-string/
	 */
	public static void test1() {
		String input = "abc";
		task1_all_permutations(input);
	}
	
	public static void task1_all_permutations(String input) {
		char[] arr = input.toCharArray();
		task1_helper(arr, 0);
	}
	
	public static void task1_helper(char[] input, int index) {
		if (index == input.length) {
			System.out.println(new String(input));
			return ;
		}
		for(int i = index; i < input.length; i ++) {
			task1_swap(input, index, i);
			task1_helper(input, index + 1);
			task1_swap(input, index, i);
		}
	}
	
	public static void task1_swap(char[] input, int x, int y) {
		char temp = input[x];
		input[x] = input[y];
		input[y] = temp;
	}

	/*
	 * task2 Backtracking | Set 1 (The Knight’s tour problem)
	 * http://www.geeksforgeeks.org/backtracking-set-1-the-knights-tour-problem/
	 * 
	 * https://en.wikipedia.org/wiki/Knight%27s_tour#Computer_algorithms
	 */

	public static void test2() {
		int N = 5;
		task2_solveKT(N);
	}
	
	public static boolean task2_solveKT(int N) {
		int[][] soln = new int[N][N];
		for(int i = 0; i < soln.length; i ++ ){
			for(int j = 0; j < soln[0].length; j ++ ){
				soln[i][j] = -1;
			}
		}
		int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        
        soln[0][0] = 0;
        if (!task2_solveKTUntil(0, 0, 1, soln, dx, dy, N)) {
			System.out.println("Solution doesn't exist");
		} else {
			task2_printSoluntion(soln);
		}
        return true;
	}
	
	public static boolean task2_solveKTUntil(int x, int y , int move_i, int[][] soln, int[] dx, int[] dy, int N) {
		if (move_i == N * N) {
			return true;
		}
		for(int i = 0; i < 8; i ++ ) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			if (task2_isSafe(next_x, next_y, soln)) {
				soln[next_x][next_y] = move_i;
				if (task2_solveKTUntil(next_x, next_y, move_i + 1, soln, dx, dy, N)) {
					return true;
				} else {
					soln[next_x][next_y] = -1;
				}
			}
		}
		return false;
	}
	
	private static boolean task2_isSafe(int x, int y, int[][] soln) {
		return x >= 0 && x < soln.length && y >= 0 && y < soln[0].length && soln[x][y] == -1;
	}
	private static void task2_printSoluntion(int[][] soln) {
		for(int i = 0; i < soln.length; i ++) {
			for(int j = 0; j < soln[0].length; j ++) {
				System.out.print(soln[i][j] + " ");
			}
			System.out.println();
		}
	}
	

	/*
	 * task3 Backtracking | Set 2 (Rat in a Maze)
	 * http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
	 * 
	 * 0, obstacle
	 * 1, go through
	 *  
	 */
	public static void task3_rat_in_a_maze(int[][] matrix) {
		
	}
	
	
	/*
	 * task4 Backtracking | Set 3 (N Queen Problem)
	 * http://www.geeksforgeeks.org/backtracking-set-3-n-queen-problem/
	 */
	

	/*
	 * task5 Backtracking | Set 4 (Subset Sum)
	 * http://www.geeksforgeeks.org/backttracking-set-4-subset-sum/
	 * 
	 * to find subset of elements that are selected from a given set whose sum adds up 
	 * to a given number
	 * 
	 * This can reduce to subset
	 */
	public static void test5() {
		int[] array = {1,2,3,4,5};
		int sum = 9;
		List<List<Integer>> result = task5_subsetSum(array, sum);
		System.out.println(result);
	}
	
	public static List<List<Integer>> task5_subsetSum(int[] array, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		task5_helper(array, 0, result, list, sum, 0);
		return result;
	}
	
	public static void task5_helper(int[] array,int index ,List<List<Integer>> result, 
			List<Integer> list, int sum, int curSum) {
		if (index == array.length) {
			if (curSum == sum) {
				List<Integer> copy = new ArrayList<Integer>(list);
				result.add(copy);
			}
			return ;
		}
		// we don't use current element
		task5_helper(array, index + 1, result, list, sum, curSum);
		
		list.add(array[index]);
		curSum += array[index];
		
		task5_helper(array, index + 1, result, list, sum, curSum);	
		curSum -= list.get(list.size() - 1);
		list.remove(list.size() - 1);
	}
	
	
	
	

	/*
	 * task6 Backtracking | Set 5 (m Coloring Problem)
	 * http://www.geeksforgeeks.org/backttracking-set-5-m-coloring-problem/
	 * 
	 * given an undirected graph and a number m, determine if the graph can be colored with at most
	 * m colors such that no two adjacent vertices of the graph are colored with same color
	 * 
	 * input: 
	 * 1)a 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is 
	 * adjacent matrix representation for the graph. A value graph[i][j] is 1 if there is an directed edge
	 * from i to j, otherwise, graph[i][j] is 0
	 * 2) An Integer m which is matrixum number of colors that can be used
	 * 
	 * output: 
	 * A array color[V] that should have numbers from 1 to m. Color[i] should represent the color
	 * assigned to the ith vertex. 
	 * The code should also return false if the graph cannot be colored with m colors
	 * 
	 */
	
	
	

	/*
	 * task7 Backtracking | Set 6 (Hamiltonian Cycle)
	 * http://www.geeksforgeeks.org/backtracking-set-7-hamiltonian-cycle/
	 */

	/*
	 * task8 Backtracking | Set 7 (Sudoku)
	 * http://www.geeksforgeeks.org/backtracking-set-7-suduku/
	 */

	/*
	 * task9 Tug of War http://www.geeksforgeeks.org/tug-of-war/
	 */

	/*
	 * task10 Backtracking | Set 8 (Solving Cryptarithmetic Puzzles)
	 * http://www.geeksforgeeks
	 * .org/backtracking-set-8-solving-cryptarithmetic-puzzles/
	 */
}

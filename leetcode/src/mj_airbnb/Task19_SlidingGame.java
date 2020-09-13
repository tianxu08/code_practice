package mj_airbnb;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Task19_SlidingGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/**
	 * sliding game
	 * 
	 * You're given a 3x3 board of a tile puzzle, with 8 tiles numbered 1 to 8, and an empty spot. 
	 * You can move any tile adjacent to the empty spot, to the empty spot, 
	 * creating an empty spot where the tile originally was. 
	 * The goal is to find a series of moves that will solve the board, 
	 * i.e. get [ [1, 2, 3], [4, 5, 6], [7, 8, - ]...
	 * 
	 * 
	 * 实现一个sliding game，就是以前小时候玩的那种九宫格，九宫格，一共8个方块， 从1-8，一个方 块空出来，
	 * 然后打乱之后通过SLIDE还原，这个题要推广到N宫格， 先实现这个游戏，然后对于一 个任意的BOARD
	 */
	
	public static void test() {
		int[][] board1 = {
				{4,1,2},
				{5,0,3},
				{7,8,6}
		};
		
		int[][] board2 = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};
		
		int rev = slidingGame(board1, board2);
		System.out.println("rev = " + rev);
		
	}
	
	
	public static int[][] dir = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static int slidingGame(int[][] board1, int[][] board2) {
		if (board1 == null || board1.length == 0 || board1[0].length == 0) {
			return -1;
		}
		int rLen = board1.length;
		int cLen = board1[0].length;
		String start = "";
		String end = "";
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				start += board1[i][j];
				end += board2[i][j];
			}
		}
		
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer(start);
		visited.add(start);
		
		int steps = -1;
		while(!queue.isEmpty()) {
			steps ++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String curStr = queue.poll();
				if (curStr.equals(end)) {
					return steps;
				}
				int index = curStr.indexOf('0');
				int x = index / cLen;
				int y = index % cLen;
				
				for (int k = 0; k < 4; k++) {
					int nx = x + dir[k][0];
					int ny = y + dir[k][1];
					
					if (inBound(nx, ny, rLen, cLen)) {
						String next = swap(curStr, index, nx * cLen + ny);
						if (!visited.contains(next)) {
							visited.add(next);
							queue.offer(next);
						}
					}
				}				
			}
		}
		return -1;
	}
	
	public static String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return new String(arr);
	}
	
	public static void swapArr(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}
	
	public static boolean inBound(int x, int y, int rLen, int cLen) {
		return x >= 0 && x < rLen && y >= 0 && y < cLen;
	}

}

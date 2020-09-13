package sortbynum;

import java.util.LinkedList;

import ds.Debug;

public class Task317_ShortestDistanceFromAllBuildings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
//		test2();
	}
	
	/*
	 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
	 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
	 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
	 * Each 0 marks an empty land which you can pass by freely.
	 * Each 1 marks a building which you cannot pass through.
	 * Each 2 marks an obstacle which you cannot pass through.
	 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
	 * 
	 * 1 - 0 - 2 - 0 - 1
	 * |   |   |   |   |
	 * 0 - 0 - 0 - 0 - 0
	 * |   |   |   |   |
	 * 0 - 0 - 1 - 0 - 0
	 * The point (1,2) is an ideal empty land to build a house, 
	 * as the total travel distance of 3+3+1=7 is minimal. So return 7.
	 * 
	 * 
	 * 
	 */
	
	/*
	 * Algorithm:
	 * int[][] cost, cost[i][j] stands for the shortest distance from this empty land to all buildings
	 * 
	 * from all buildings, do BFS and update the cost. 
	 * Traverse the cost matrix, get the minimum 
	 */
	
	public static void test() {
		int[][] grid = {
				 {1,1,1,1,1,0},
				 {0,0,0,0,0,1},
				 {0,1,1,0,0,1},
				 {1,0,0,1,0,1},
				 {1,0,1,0,0,1},
				 {1,0,0,0,0,1},
				 {0,1,1,1,1,0}
		};
		int rev = shortestDistance(grid);
		System.out.println("rev = " + rev);
	}
	
	public static int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return -1;
		}
        
        int rLen = grid.length;
        int cLen = grid[0].length;
        int buildNum = 0;
        int[][] cost = new int[rLen][cLen];
        int[][] nums = new int[rLen][cLen];
        for(int i = 0; i < rLen; i++) {
        	for(int j = 0; j < cLen; j++) {
        		if (grid[i][j] == 1) {
        			buildNum ++;
        			BFS(grid, i, j, cost, nums);
				}
        	}
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < rLen; i ++) {
        	for(int j = 0; j < cLen; j ++) {
        		if (grid[i][j] == 0 && cost[i][j] != 0 && nums[i][j] == buildNum ) {
					min = Math.min(min, cost[i][j]);
				}
        	}
        }
       
        return min;
    }
	public static void test2() {
		int[][] grid = {
				 {1,1,1,1,1,0},
				 {0,0,0,0,0,1},
				 {0,1,1,0,0,1},
				 {1,0,0,1,0,1},
				 {1,0,1,0,0,1},
				 {1,0,0,0,0,1},
				 {0,1,1,1,1,0}
		};
		
		int[][] cost = new int[grid.length][grid[0].length];
		int[][] nums = new int[grid.length][grid[0].length];
		boolean rev = BFS(grid, 0, 0, cost, nums);
		System.out.println("rev = " + rev);
		
		Debug.printMatrix(cost);
	}
	
	public static boolean BFS(int[][] grid, int i, int j, int[][] cost, int[][] nums) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		int rLen = grid.length;
		int cLen = grid[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		
		LinkedList<Item> queue = new LinkedList<Item>();
		queue.offer(new Item(i, j));
		
		int distance = 0;
		
		while(!queue.isEmpty()) {
			distance ++;
			int size = queue.size();
			for(int idx = 0; idx < size; idx ++) {
				Item cur = queue.poll();		
				// get neighbors
				for(int k = 0; k < 4; k ++ ) {
					int nextX = cur.x + dx[k];
					int nextY = cur.y + dy[k];
					if (nextX >= 0 && nextX < rLen && 
						nextY >= 0 && nextY < cLen && 
						!visited[nextX][nextY]     && 
						grid[nextX][nextY] == 0 ) {
						
						visited[nextX][nextY] = true;
						cost[nextX][nextY]  += distance;
						nums[nextX][nextY] ++; // 纪录被更新了多少次
						queue.offer(new Item(nextX, nextY));
					}
				}
			}
		}

		return true;
	}
	
	public static class Item{
		int x;
		int y;
		public Item(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	} 
	
	

}

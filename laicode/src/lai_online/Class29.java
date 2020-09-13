package lai_online;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Class29 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	/*
	 * task1
	 * Max Trapped Water
	 * Level(x, y) is the water level for position(x, y)
	 * case1: If green cell's height is lower than 5(the height), then its water level == 5
	 * 
	 * case2: else, green cell's water level = max(green's cell physical height, 5)
	 * 
	 * 
	 * for every point Pi<xi, yi, zi> on the boarder, set the water level to <zi>
	 * for every point Pj<xj, yj, zj> NOT on the boarder, set the water level to infinity
	 * Insert all Pi into MIN_HEAP(set of active points)
	 * 
	 * while MIN_HEAP is NOT empty: 
	 * 	 	P = MIN_HEAP.pop()  // select the active point P with minimum level
	 * 		sum += Level(P) - Height(P)
	 * 		
	 * 		for every point Q adjacent to P:
	 * 			Level(Q) = max(Height(Q), min(Level(Q), Level(P)))  
	 * 			if Level(Q) was changed, 
	 * 				Add Q to the MIN_HEAP if it is NOT there yet
	 */
	public static void test() {
		int[][] matrix = {
				{2,3,4,2},
				{3,1,2,3},
				{4,3,5,4}
		};
		
		int sum = maxTrappedWater2D(matrix);
		System.out.println("max water is : " + sum);
	}
	
	
	public static int maxTrappedWater2D(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		// add the 4 border into the min_heap
		PriorityQueue<Element> minHeap = new PriorityQueue<Element>(rLen * cLen, new Comparator<Element>() {

			@Override
			public int compare(Element o1, Element o2) {
				// TODO Auto-generated method stub
				if (o1.level == o2.level) {
					return 0;
				}
				return o1.level < o2.level ? -1 : 1;
			}
			
		});
		boolean[][] visited = new boolean[rLen][cLen];
		int[][] level = new int[rLen][cLen]; // the final water level above each position
		
		// add the 4 boarder into the priority queue
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (i == 0 || i == rLen - 1 || j == 0 || j == cLen - 1) {
					if (!visited[i][j]) {
						Element elem = new Element(i, j, matrix[i][j], matrix[i][j]);
						level[i][j] = matrix[i][j];
						visited[i][j] = true;
						minHeap.add(elem);
					} 
				} else {
					level[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		
		int sum = 0;
		while(!minHeap.isEmpty()) {
			Element cur = minHeap.poll();
			sum += level[cur.x][cur.y] - cur.height;
			
			// generate its neighbor
			for(int k = 0; k < 4; k ++) {
				int nextX = cur.x + dx[k];
				int nextY = cur.y + dy[k];
				if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen
					&& !visited[nextX][nextY]) {
					int nextLevel = Math.max(matrix[nextX][nextY], Math.min(level[nextX][nextY], cur.level));
					// the minimum between the current level of [nextX][nextY] and its parent's level
					
					if (level[nextX][nextY] != nextLevel) {
						// the level has been changed
						Element elem = new Element(nextX, nextY, matrix[nextX][nextY], nextLevel);
						// add into minHeap
						minHeap.offer(elem);
						// update level[nextX][nextY] 
						level[nextX][nextY] = nextLevel;
						// update visited[nextX][nextY]
						visited[nextX][nextY] = true;
					}
				}
			}
		}
		
		return sum;
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1 ,1};
	
	public static class Element{
		int x;
		int y;
		int height;
		int level;
		public Element(int _x, int _y, int _h, int _l) {
			this.x = _x;
			this.y = _y;
			this.height = _h;
			this.level = _l;
		}
	}
	
	

}

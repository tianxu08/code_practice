package tag_union_find;
import java.util.*;

public class Union_Find_305_Num_IslandsII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		int rLen = 3, cLen = 3;
		int[][] positions = { 
				{ 0, 0 }, 
				{ 0, 1 }, 
				{ 1, 2 }, 
				{ 2, 1 } 
		};
		List<Integer> result = numIslands2(rLen, cLen, positions);
		System.out.println(result);
	}

	public static List<Integer> numIslands2(int rLen, int cLen, int[][] positions) {
		List<Integer> result = new ArrayList<Integer>();
		if (positions == null || positions.length == 0 || positions[0] == null || positions[0].length == 0) {
			return result;
		}
		
		int count = 0;
		int[] islands = new int[rLen * cLen];
		Subset[] subsets = createSet(rLen * cLen);
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for(int i = 0; i < positions.length; i ++) {
			int x = positions[i][0];
			int y = positions[i][1];
			int pos = x * cLen + y;
			count ++;
			if (islands[pos] != 1) {
				islands[pos] = 1;
				for(int k = 0; k < 4; k ++) {
					int nextX = x + dx[k];
					int nextY = y + dy[k];
					int newPos = nextX * cLen + nextY;
					if (nextX >= 0 && nextX < rLen && 
						nextY >= 0 && nextY < cLen && islands[newPos] == 1) {
						// when new position is land
						int findA = find(subsets, pos);
						int findB = find(subsets, newPos);
						if (findA != findB) {
							count --;
							union(subsets, pos, newPos);
						}
					}
				}
			}
			result.add(count);
		} 
		return result;
	}

	public static class Subset {
		int parent;
		int rank;

		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}

	public static Subset[] createSet(int n) {
		Subset[] subsets = new Subset[n];
		for (int i = 0; i < n; i++) {
			subsets[i] = new Subset(i, 0);
		}
		return subsets;
	}

	public static int find(Subset[] subsets, int x) {
		if (subsets[x].parent != x) {
			subsets[x].parent = find(subsets, subsets[x].parent);
		}
		return subsets[x].parent;
	}

	public static void union(Subset[] subsets, int x, int y) {
		int xRoot = find(subsets, x);
		int yRoot = find(subsets, y);

		if (subsets[xRoot].rank > subsets[yRoot].rank) {
			subsets[yRoot].parent = xRoot;
		} else if (subsets[xRoot].rank < subsets[yRoot].rank) {
			subsets[xRoot].parent = yRoot;
		} else {
			subsets[xRoot].rank++;
			subsets[yRoot].parent = xRoot;
		}
	}

}

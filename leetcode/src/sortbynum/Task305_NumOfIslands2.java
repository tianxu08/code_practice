package sortbynum;
import java.util.*;

public class Task305_NumOfIslands2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class Subset {
		int parent;
		int rank;

		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}

	public Subset[] createUnionFind(int n) {
		Subset[] subsets = new Subset[n + 1];
		for (int i = 0; i < n; i++) {
			subsets[i] = new Subset(i, 0);
		}
		return subsets;
	}

	// find i's parent
	public int find(Subset[] subsets, int i) {
		if (subsets[i].parent != i) {
			subsets[i].parent = find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}

	// union x and y 
	public void Union(Subset[] subsets, int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		if (subsets[xroot].rank < subsets[yroot].rank) {
			subsets[xroot].parent = yroot;
		} else if (subsets[xroot].rank > subsets[yroot].rank) {
			subsets[yroot].parent = xroot;
		} else {
			// promote xroot
			subsets[xroot].rank++;
			subsets[yroot].parent = xroot;
		}
	}

	public List<Integer> numIslands2(int rLen, int cLen, int[][] positions) {
		List<Integer> result = new ArrayList<Integer>();
		if (positions == null || positions.length == 0 || positions[0] == null
				|| positions[0].length == 0) {
			return result;
		}
		int count = 0;
		int[] island = new int[rLen * cLen];
		Subset[] subsets = createUnionFind(rLen * cLen);
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		for (int i = 0; i < positions.length; i++) {
			int x = positions[i][0];
			int y = positions[i][1];
			int pos = x * cLen + y;
			count++;
			if (island[pos] != 1) {
				island[pos] = 1;
				for (int k = 0; k < 4; k++) {
					int nextX = x + dx[k];
					int nextY = y + dy[k];
					int newPos = nextX * cLen + nextY;
					if (nextX >= 0 && nextX < rLen && nextY >= 0
							&& nextY < cLen && island[newPos] == 1) {    // when new
																		// position
																		// is
																		// land
						int findA = find(subsets, pos);
						int findB = find(subsets, newPos);
						if (findA != findB) {
							count--;

							Union(subsets, findA, findB);
						}
					}
				}
			}
			result.add(count);
		}

		return result;
	}

}

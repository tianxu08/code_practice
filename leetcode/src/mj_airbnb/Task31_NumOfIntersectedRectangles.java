package mj_airbnb;

import java.util.*;

public class Task31_NumOfIntersectedRectangles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * rectangle: leftTop rightBottom
	 * 
	 * 
	 */

	public static class Point {
		int x;
		int y;
	}

	// Returns true if two rectangles (l1, r1) and (l2, r2) overlap
	// https://www.geeksforgeeks.org/find-two-rectangles-overlap/
	public static boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
		// If one rectangle is on left side of other
		if (l1.x > r2.x || l2.x > r1.x)
			return false;

		// If one rectangle is above other
		if (l1.y < r2.y || l2.y < r1.y)
			return false;

		return true;
	}

	public static boolean intersect(int[][] rec1, int[][] rec2) {
		// rec[0]: top left
		// rec[3] bottom right
		return false;
	}

	private static int find(int val, int[] parents) {
		while (parents[val] != val) {
			val = parents[val];
		}

		return val;
	}

	public static int countIntersection(int[][][] rectangles) {
		int[] parents = new int[rectangles.length];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < rectangles.length; i++) {
			for (int j = i + 1; j < rectangles.length; j++) {
				if (intersect(rectangles[i], rectangles[j])) {
					// union
					int root1 = find(i, parents);
					int root2 = find(j, parents);
					if (root1 != root2) {
						parents[root1] = root2;
					}
				}
			}
		}
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < parents.length; i++) {
			set.add(find(i, parents));
		}
		return set.size();
	}
}

package mj_amazon;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class OA1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testRectOverlap();
//		testKclosest();
		testWindowSum();
	}
	/*
	 * http://wdxtub.com/interview/14520850399861.html
	 */
	
	/*
	 * Rectangle Overlap
	 */
	public static class Point{
		public int x;
		public int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
		// one rectangle is on the left side of another, no overlap
		if (l1.x > r2.x || l2.x > r1.x) {
			return false;
		}
		// one rectangl is on the up side of the other, no overlap
		if (r1.y > l2.y || r2.y > l1.y) {
			return false;
		}
		return true;
	}
	
	public static boolean doOverlap2(Point l1, Point r1, Point l2, Point r2) {
		if (l1.x > r2.x || l2.x > r1.x)
			return false;

		// If one rectangle is above other
		if (l1.y < r2.y || l2.y < r1.y)
			return false;

		return true;
	}
	
	public static void testRectOverlap() {
		Point l1 = new Point(1, 5);
		Point r1 = new Point(4, 3);
		Point l2 = new Point(2, 2);
		Point r2 = new Point(7, 1);
		
		boolean rev = doOverlap(l1, r1, l2, r2);
		boolean rev2 = doOverlap2(l1, r1, l2, r2);
		System.out.println("rev = " + rev);
		System.out.println("rev2 = " + rev2);
	}
	
	 /* K Closest Points
	 */
	public static List<Point> kClosestPoints(List<Point> points, final Point origin, int k) {
		if (points == null || points.size() == 0 || k < 0) {
			return new ArrayList<Point>();
		}
		Comparator<Point> myComp = new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				int dis1 = getDistance(o1, origin);
				int dis2 = getDistance(o2, origin);
				if (dis1 == dis2) {
					return 0;
				}
				return dis1 > dis2 ? -1 : 1;
			}
		};
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k, myComp);
		for(int i = 0; i < points.size(); i++) {
			Point cur = points.get(i);
			if (i < k) {
				maxHeap.offer(cur);
			} else {
				if (getDistance(cur, origin) < getDistance(maxHeap.peek(), origin)) {
					maxHeap.poll();
					maxHeap.offer(cur);
				} 
			}
		}
		List<Point> result = new ArrayList<Point>();
		while(!maxHeap.isEmpty()) {
			result.add(maxHeap.peek());
			maxHeap.poll();
		}
		return result;
	}
	
	public static int getDistance(Point p, Point origin) {
		return (p.x - origin.x) * (p.x - origin.x) + (p.y - origin.y) * (p.y - origin.y); 
	}
	public static void testKclosest() {
		List<Point> points  = new ArrayList<Point>();
		points.add(new Point(1, 2));
		points.add(new Point(3, 2));
		points.add(new Point(4, 2));
		points.add(new Point(5, 2));
		points.add(new Point(1, 2));
		points.add(new Point(1, 1));
		points.add(new Point(4, 5));
		points.add(new Point(3, 9));
		
		for(Point p : points) {
			System.out.println(p.x + " : " + p.y);
		}
		System.out.println("------------");
		
		Point origin = new Point(0, 0);
		int k = 3;
		List<Point> result = kClosestPoints(points, origin, k);
		for(Point p : result) {
			System.out.println(p.x + " : " + p.y);
		}
		
	}
	
	/* Window Sum
	 * get the sum of subarray size of k in the array
	 */
	public static List<Integer> windowSum(int[] array, int k) {
		if (array == null || array.length == 0 || k <= 0 || array.length < k) {
			return new ArrayList<Integer>();
		}
		List<Integer> result = new ArrayList<Integer>();
		int n = array.length;
		int[] preSum = new int[n];
		for(int i = 0; i <n; i ++) {
			if (i == 0) {
				preSum[0] = array[0];
			} else {
				preSum[i] = array[i] + preSum[i - 1];
			}
		}
		
		for(int i = k - 1; i < n; i++) {
			if (i == k - 1) {
				result.add(preSum[i]);
			} else {
				result.add(preSum[i] - preSum[i - k]);
			}
		}
		return result;
	}
	
	public static void testWindowSum() {
		int[] array = {1,5,8,2,1,2,3,4,5};
		int k = 3;
		List<Integer> result = windowSum(array, k);
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println("-----");
		List<Integer> reslut2 = windowSum2(array, k);
		for(int i = 0; i < reslut2.size(); i ++) {
			System.out.println(reslut2.get(i));
		}
	}
	
	public static List<Integer> windowSum2(int[] array, int k) {
		if (array == null || array.length == 0 || k <= 0 || array.length < k) {
			return new ArrayList<Integer>();
		}
		List<Integer> result = new ArrayList<Integer>();
		int sumOfK = 0;
		for(int i = 0; i < array.length; i++) {
			if (i < k - 1) {
				sumOfK += array[i];
			} else {
				// i >= k - 1
				sumOfK += array[i];
				if (i == k - 1) {
					result.add(sumOfK);
				} else {
					sumOfK -= array[i - k];
					result.add(sumOfK);
				}
			}
		}
		return result;
	}
	
	
	/* Longest Palindrome
	 * 
	 * Copy List with Random Pointer
	 * https://segmentfault.com/a/1190000007065373
	 * 
	 * Order Dependency
	 * https://segmentfault.com/a/1190000007064872
	 * 
	 * Minimum Spanning Tree
	 * 
	 * Five Scores
	 * https://segmentfault.com/a/1190000007065492
	 * 
	 * 
	 * Maximum Subtree(Company Tree)
	 * 
	 * 看这里https://segmentfault.com/blog/ganleetcode
	 * 
	 * Company Tree 最大平均子节点
	 * https://segmentfault.com/a/1190000007065158
	 * 
	 * 城市连接问题： MST
	 * https://segmentfault.com/a/1190000007064752
	 * 
	 * 
	 */
	
	/*
	 * number of valid parentheses
	 * BST minimum sum path. 
	 * Reverse Second Half of Linked List. 
	 * 
	 */
	
	/*
	 * 我抽到的题目是Window Sum， K Nearest Points 和 Order Dependency。
	 * 前两个很容易，如果流传的测试案例都能过就应该没问题。 
	 * 但Order Dependency就比较蛋疼了，亚马逊的测试案例有点问题，
	 * 某个OrderDepency 中的Order("A") 和另一个OrderDependency中的Order("A")是两个不同的对象，
	 * 两者并不相等！！！所以一定要注意。我就是在OA过程中一直没发现这个问题，结果一个test case都没过只能提交了。
	 * 另外用Map，PriorityQueue等类时，记得引入java.util.*。
	 */
	
	

}

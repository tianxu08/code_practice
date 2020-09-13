package mj_linkedin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PointsOnAPlaneImp implements PointsOnAPlane {

	Collection<Point> list;
	
	public PointsOnAPlaneImp() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<Point>();
	}
	@Override
	public void addPoint(Point point) {
		// TODO Auto-generated method stub
		list.add(point);
	}

	@Override
	public Collection<Point> findNearest(Point center, int m) {
		// TODO Auto-generated method stub
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(m);
		for(Point point : list) {
			if (maxHeap.size() < m) {
				maxHeap.offer(point);
			} else {
				if (maxHeap.peek().compareTo(point) < 0) {
					// the head of maxHeap has larger distance from the center, which has higher priority
					// we pop the head and add the point into maxHeap
					maxHeap.poll();
					maxHeap.offer(point);
				}
			}
		} 
		Comparator<Point> myComp = new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		return maxHeap;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PointsOnAPlaneImp sln = new PointsOnAPlaneImp();
		sln.addPoint(new Point(0, 1));
		sln.addPoint(new Point(0, 2));
		sln.addPoint(new Point(0, 3));
		sln.addPoint(new Point(0, 4));
		sln.addPoint(new Point(0, 5));
		int m = 3;
		Collection<Point> result = sln.findNearest(new Point(4, 1), m);
		System.out.println(result);
		
		
	}

}

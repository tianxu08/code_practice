package mj_linkedin;
import java.util.Collection;

public interface PointsOnAPlane {
	/*
	 * Stores a given point in a internal data structure
	 */
	void addPoint(Point point);
	
	/*
	 * For given 'center' point returns a subset of 'm' stored points 
	 * that are closer to that are closer to the center than others
	 * 
	 * e.g
	 * Stored: (0,1) (0,2) (0,3) (0,4) (0,5)
	 * findNearest(new Point(0,0), 3) 
	 * return (0,1) (0,2) (0,3)
	 */
	Collection<Point> findNearest(Point center, int m);
}



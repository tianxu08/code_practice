package mj_linkedin;

public class Point implements Comparable<Point> {
	int x;
	int y; 
	double dist;
	
	public Point(int _x, int _y, Point originPoint) {
		this.x = _x;
		this.y = _y;
		this.dist = Math.hypot(x - originPoint.x, y - originPoint.y);
		// Math.hypot() returns sqrt(x^2 + y^2)
	}
	// assume the origin point is (0,0)
	public Point(int _x, int  _y) {
		this.x = _x;
		this.y = _y;
		this.dist = Math.hypot(x, y);
	}
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return Double.valueOf(o.dist).compareTo(this.dist);
	}
	
	@Override
	public String toString() {
		return "x: " + x + "  "+ "y: " + y;
	}
	
	
	
	
}

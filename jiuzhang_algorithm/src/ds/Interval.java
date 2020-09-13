package ds;

public class Interval {
	public int start;
	public int end;
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ " +start + " , " + end + " ]";
	}
}

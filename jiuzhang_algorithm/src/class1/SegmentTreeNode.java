package class1;

public class SegmentTreeNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public SegmentTreeNode left, right;
	public int max;
	public int start;
	public int end;
	
	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString(){
		return "[ " + start  + " , " + end + " ]" + " max= " + max;
		
	}
}

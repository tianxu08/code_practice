package sortbynum;
import java.util.*;

public class Vector2D {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> vec2d = new ArrayList<List<Integer>>();
		List<Integer> line1 = new ArrayList<Integer>();
		List<Integer> line2 = new ArrayList<Integer>();
		line2.add(3);
		vec2d.add(line1);
		vec2d.add(line2);
		Vector2D vct = new Vector2D(vec2d);
		
		while(vct.hasNext()) {
			System.out.println("hello");
			System.out.println(vct.next());
		}
	}
	
	
	List<List<Integer>> list;
	int outIndex;
	int inIndex;
	public Vector2D(List<List<Integer>> vec2d) {
		this.list = vec2d;
		outIndex = 0;
		inIndex = 0;
	}

	public int next() {
		if (hasNext()) {
			int result = list.get(outIndex).get(inIndex);
			inIndex ++;
			
			while(outIndex < list.size() && inIndex == list.get(outIndex).size()) {
				outIndex ++;
				inIndex = 0;
			}
			
			return result;
		}
		return -1;
	}

	public boolean hasNext() {
		while(outIndex < list.size() && inIndex == list.get(outIndex).size()) {
			outIndex ++;
			inIndex = 0;
		}
		
		if (outIndex < list.size() && (inIndex < list.get(outIndex).size())) {
			return true;
		}
		return false;
	}
}

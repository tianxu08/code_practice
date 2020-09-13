package tag_iterator;


import java.util.*;
/**
 * 
 * @author xutian
 *
 * 
 */
public class IteratorZigZag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private List<Integer> v1;
	private List<Integer> v2;

	int index_v1;
	int index_v2;
	boolean flag;

	public IteratorZigZag(List<Integer> v1, List<Integer> v2) {
		this.v1 = v1;
		this.v2 = v2;
		index_v1 = 0;
		index_v2 = 0;
		this.flag = false;
	}

	public int next() {
		if (hasNext()) {
			if (index_v1 < v1.size() && index_v2 < v2.size()) {
				if (!flag) {
					// flag == false, choose from v1
					int rev = v1.get(index_v1);
					index_v1++;
					flag = true;
					return rev;
				} else {
					// flag == true, choose from v2
					int rev = v2.get(index_v2);
					index_v2++;
					flag = false;
					return rev;
				}
			} else {
				if (index_v1 < v1.size()) {
					int rev = v1.get(index_v1);
					index_v1++;
					return rev;
				} else {
					// choose from v2
					int rev = v2.get(index_v2);
					index_v2++;
					return rev;
				}
			}
		} else {
			return -1;
		}
	}

	public boolean hasNext() {
		if (index_v1 < v1.size() || index_v2 < v2.size()) {
			return true;
		}
		return false;
	}
}

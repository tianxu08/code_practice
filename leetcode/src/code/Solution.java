package code;

import java.util.*;
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sln = new Solution();
		System.out.println(sln.getTotalCoverag()); 
		// here should be 0, since there is no interval in the list.
		
		// add [1, 5]  
		sln.addInterval(1, 5);
		// add [8, 9] 
		sln.addInterval(8, 9);
		System.out.println(sln.getTotalCoverag());
		// now the length should be 5, since length of Interval(1,5) is 4, length of Interval(8,9) is 1
		
		// add [2,6]
		sln.addInterval(2, 6);
		System.out.println(sln.getTotalCoverag());
		// [1,5] and [2,6] will have overlap. after 'merged(not actual merge)', it's [1, 6] length is 5
	    // plus length of [8,9] the total length is 6
	}
	
	private List<Interval> list;
	public Solution() {
		list = new ArrayList<Interval>();
	}
	
	/*
	 * Time: O(1)
	 * just add the interval to the list
	 */
	public void addInterval(int from, int to) {
		// TODO Auto-generated method stub
		list.add(new Interval(from, to));
	}
    
	/*
	 * Time: O(n * log n)
	 * Sort the list first. 
	 * Then traversal the sorted list and get the length. 
	 * 
	 */
	public int getTotalCoverag() {
		// TODO Auto-generated method stub
		// check
		if (list.isEmpty()) {
			return 0;
		}
		// 1. sort the list of Intervals according to start. 
		Collections.sort(list);
		
		int len = 0;
		Interval last = list.get(0);
		
		for(int i = 1; i < list.size(); i ++) {
			Interval cur = list.get(i);
			if(last.end >= cur.start) { // overlap
				// update last.end
				last.end = Math.max(last.end, cur.end);
			} else {
				// there is no overlap
				len += last.end - last.start;
				last = cur;
			}
		}
		
		len += last.end - last.start;
		
		return len;
		
	}

}

class Interval implements Comparable<Interval>{
    int start;
    int end;
    public Interval(int s, int e) {
    	this.start = s;
    	this.end = e;
    }
    
	@Override
	public int compareTo(Interval o) {
		// TODO Auto-generated method stub
		if (this.start == o.start) {
			if (this.end == o.end) {
				return 0;
			} 
			return this.end < o.end ? -1 : 1;
		}
		return this.start < o.start ? -1 : 1;
	}
}

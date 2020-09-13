package mj_linkedin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyIntervalLinkedIn implements IntervalLinkedIn{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyIntervalLinkedIn sln = new MyIntervalLinkedIn();
		sln.addInterval(1, 2);
		System.out.println(sln.getTotalCoveredLength());
		sln.addInterval(0, 3);
		System.out.println(sln.getTotalCoveredLength());
		
	}

	List<Element> list = new ArrayList<MyIntervalLinkedIn.Element>();
	
	/**
	 * Add the interval into the list
	 */
	@Override
	public void addInterval(int from, int to) {
		// TODO Auto-generated method stub
		list.add(new Element(from, to));
	}

	
	
	/**
	 * Time: O(n * log n)
	 * sort the intervals
	 */
	@Override
	public int getTotalCoveredLength() {
		// TODO Auto-generated method stub
		// traverse the list and get the total length
		// sort the list by start
		Collections.sort(list);
		int resLen = 0;
		Element last = list.get(0);
		for(int i = 1; i < list.size(); i++) {
			Element cur = list.get(i);
			// if there is overlap
			if (cur.start <= last.end) {
				// update the last
				last.end = Math.max(last.end, cur.end);
			} else {
				// there is No overlap
				resLen += last.end - last.start + 1;
				last = cur;
			}
		}
		
		// for the last one
		resLen += last.end - last.start + 1;
		return resLen;
	}
	
	
	public class Element implements Comparable<Element>{
		int start;
		int end;
		public Element(int _s, int _e) {
			this.start = _s;
			this.end = _e;
		}
		@Override
		public int compareTo(Element o) {
			// TODO Auto-generated method stub
			if (this.start == o.start) {
				return 0;
			}
			return this.start < o.start ? -1 : 1;
		}
		
	}
	

}

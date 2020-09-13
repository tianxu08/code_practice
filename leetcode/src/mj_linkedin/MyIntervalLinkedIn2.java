package mj_linkedin;

import java.util.ArrayList;

import java.util.List;

import ds.Interval;


public class MyIntervalLinkedIn2 implements IntervalLinkedIn{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyIntervalLinkedIn2 sln = new MyIntervalLinkedIn2();
		sln.addInterval(2, 4);
		sln.addInterval(5, 8);
		sln.printInterval();
		sln.printTotalLength();
		
		sln.addInterval(4, 5);
		sln.printInterval();
		sln.printTotalLength();
		
		sln.addInterval(10, 15);
		sln.printInterval();
		sln.printTotalLength();
		
		sln.addInterval(0, 1);
		sln.printInterval();
		sln.printTotalLength();
		
		sln.addInterval(0, 20);
		sln.printInterval();
		sln.printTotalLength();
		
	}
	
	private List<Interval> list;
	private int totalLength;
	
	public MyIntervalLinkedIn2() {
		this.list = new ArrayList<Interval>();
		this.totalLength = 0;
	}
	
	/*
	 * list of intervals, no intersection, sorted by start
	 * 
	 * insert interval i, 
	 * find its position. 
	 * after the position, merge
	 * 
	 * keep the intervals sorted, everytime when adding, find the position using Binary Search. 
	 * add the interval into that position and then merge. 
	 * 
	 * 
	 * Time: O(n)
	 * Binary Search O(log n)
	 * Merge O(n)
	 */
	public void insert(List<Interval> list, Interval interval) {
		int start = 0, end = list.size() - 1;
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		// first, find the position that should be inserted
		int position = -1;
		int length = 0;
		if (list.isEmpty()) {
			position = -1;
		} else {
			// do a binary search for the position
			while(start + 1 < end) {
				int mid = start + (end - start)/2;
				if (list.get(mid).start == interval.start) {
					end = mid;
				} else if (list.get(mid).start < interval.start) {
					start = mid;
				} else {
					end = mid;
				}
			}
			
			if (list.get(end).start <= interval.start) {
				position = end;
			} else if (list.get(start).start <= interval.start) {
				position = start;
			} else {
				position = -1;
			}
		} 

		// merge
		ArrayList<Interval> result = new ArrayList<Interval>();
		if (position == -1) { // interval in front of the list
			Interval last = interval;
			for(int i = 0; i < list.size(); i ++) {
				Interval cur = list.get(i);
				if (cur.start <= last.end) { //intersect
					last.end = Math.max(cur.end, last.end);
				} else { // cur.start > last.end
					// add the last into result;
					length += last.end - last.start + 1;
					result.add(last);
					last = cur;
				}
			}
			length += last.end - last.start + 1;
			result.add(last);
		} else {
			// first add all interval before position into result
			for(int i = 0; i< position; i ++) {
				Interval cur = list.get(i);
				length += cur.end - cur.start + 1;
				result.add(cur);
			}
			
			System.out.println(result);
			Interval last = list.get(position);
			for(int i = position; i < list.size(); i ++) {
				Interval cur = null;
				if (i == position) {
					 cur = interval;
				} else {
					cur = list.get(i);
				}
				if (cur.start <= last.end) { //intersect
					last.end = Math.max(cur.end, last.end);
				} else { // cur.start > last.end
					// add the last into result;
					length += last.end - last.start + 1;
					result.add(last);
					last = cur;
				}
			}
			length += last.end - last.start + 1;
			result.add(last);
		}

		// update list and totalLength
		this.list = result;
		this.totalLength = length;
		
	}
	

	@Override
	public void addInterval(int from, int to) {
		// TODO Auto-generated method stub
		insert(this.list, new Interval(from, to));
		
	}

	@Override
	public int getTotalCoveredLength() {
		// TODO Auto-generated method stub
		return this.totalLength;
	}
	
	public void printInterval() {
		for(Interval i : this.list) {
			System.out.println(i);
		}
	}
	
	public void printTotalLength() {
		System.out.println(this.totalLength);
	}

}

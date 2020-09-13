package mj_linkedin;

import java.util.LinkedList;

import ds.Interval;


public class MyIntervalLinkedIn3 implements IntervalLinkedIn{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyIntervalLinkedIn3 sln = new MyIntervalLinkedIn3();
		sln.addInterval(2, 4);
		sln.addInterval(5, 8);
		sln.printInterval();
		sln.printTotalLength();
		System.out.println("-----------------");
		sln.addInterval(4, 5);
		sln.printInterval();
		sln.printTotalLength();
		System.out.println("-----------------");
		sln.addInterval(10, 15);
		sln.printInterval();
		sln.printTotalLength();
		System.out.println("-----------------");
		sln.addInterval(0, 1);
		sln.printInterval();
		sln.printTotalLength();
		System.out.println("-----------------");
		sln.addInterval(0, 20);
		sln.printInterval();
		sln.printTotalLength();
		System.out.println("-----------------");	
	}

	private LinkedList<Interval> list;
	private int totalLength;
	
	public MyIntervalLinkedIn3() {
		this.list = new LinkedList<Interval>();
		this.totalLength = 0;
	}
	
	
	public void insert(LinkedList<Interval> list, Interval interval) {
		// find the position and insert into it
		if (list.isEmpty()) {
			list.add(interval);
		} else {
			if (list.getFirst().start >= interval.start ) {
				// smaller or equal to the 
				list.addFirst(interval);
			} else if (list.getLast().start <= interval.start) {
				list.addLast(interval);
			} else {
				// traverse the list and find the first interval whose start >= interval.start
				for(int i = 0; i < list.size(); i++) {
					if (list.get(i).start >= interval.start) {
						list.add(i, interval);
						break;
					}
				}
			}
		}
		
		// traverse the linked list and get the total length
		int length = 0;
		int start = list.get(0).start;
		int end = list.get(0).end;
		for(int i = 1; i < list.size(); i ++) {
			Interval cur = list.get(i);
			if (cur.start <= end) { 
				// cur.start <= last.end, there is intersect
				// update the end
				end = Math.max(cur.end, end);
			} else {
				// update length 
				length += end - start;
				// update start and end
				start = cur.start;
				end = cur.end;
			}
		}
		length += end - start;
		
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
		for(Interval i: list) {
			System.out.println(i);
		}
	}
	
	public void printTotalLength() {
		System.out.println("totalCoveredLength = " + this.totalLength);
	}
	

}

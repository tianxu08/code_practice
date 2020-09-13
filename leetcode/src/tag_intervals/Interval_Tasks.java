package tag_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import ds.Interval;

public class Interval_Tasks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test253();
		test435();
	}

	/**
	 * 253 meeting room II
	 * 452 Minimum Number of Arrows to Burst Balloons
	 * 435. Non-overlapping Intervals
	 */

	/*
	 * 253 Meeting Rooms II
	 */
	public static void test253() {
		Interval[] intervals = { new Interval(13, 15), new Interval(1, 8),
				new Interval(7, 9) };

		int minMtRm = task253_minMeetingRooms(intervals);
		System.out.println("minMeetingRoom = " + minMtRm);

		int minMtRm2 = task253_minMeetingRooms2(intervals);
		System.out.println("minMeetingRoom2 = " + minMtRm2);

	}

	public static int task253_minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		List<Cell> list = new ArrayList<>();
		for (Interval interval : intervals) {
			Cell cell_start = new Cell(interval.start, true);
			Cell cell_end = new Cell(interval.end, false);

			list.add(cell_start);
			list.add(cell_end);
		}

		int count = 0;
		int result = Integer.MIN_VALUE;
		Collections.sort(list);

//		for (int i = 0; i < list.size(); i++) {
//			System.out.print("[" + list.get(i).val + " " + list.get(i).isStart
//					+ " ]");
//		}
//		System.out.println();

		for (int i = 0; i < list.size(); i++) {
			Cell curCel = list.get(i);
			if (curCel.isStart) {
				count++;
			} else if (curCel.isStart == false) {
				count--;
			}
			result = Math.max(result, count);
		}
		return result;
	}


	public static int task253_minMeetingRooms2(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;
		// sort the interval based on the start of each interval, in ascending order
		Arrays.sort(intervals, (i1, i2)-> i1.start - i2.start);

		// the earliest end of all interval
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		int count = 1;
		minHeap.offer(intervals[0].end);

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < minHeap.peek()) {
				// we need increase the meeting room
				count++;
			} else {
				// intervals[i].start >= minHeap.peek() the earliest end of
				// interval
				// pop the ending interval
				minHeap.poll();
			}
			// push the current interval's end into minHeap
			minHeap.offer(intervals[i].end);
		}

		return count;
	}
	
	/**
	 * 452 Minimum Number of Arrows to Burst Balloons
	 */
	public static void test452() {
		int[][] points = {
				{10, 16},
				{2, 8},
				{1, 6},
				{7, 12}
		};
		int rev = task452_findMinArrowShots(points);
		System.out.println("rev = " + rev);
	}
	public static int task452_findMinArrowShots(int[][] points) {
		if (points == null || points.length == 0) {
			return 0;
		}
		if (points.length == 1) {
			return 1;
		}
        final int[][] matrix = points;
        
        // sort the interval by end in ascending order
        // if two ends are same, sort by start in descending order
        Comparator<int[]> myComp = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[1] == o2[1]) {
					return o1[0] > o2[0] ? -1 : 1;
				}
				return o1[1] < o2[1] ? -1 : 1;
			}
		};
		
		
		Arrays.sort(matrix, myComp);
		
		int end = Integer.MIN_VALUE;
		// 
		int count = matrix.length;
		for (int i = 0; i < matrix.length; i ++) {
			if (matrix[i][0] > end) {
				// cur.start > end
				end = matrix[i][1];
			} else {
				// cur.start <= end, there is overlap, so we can decrease the number of arrows
				count --;
			}
		}
		return count;
		
    }
	
	public static int task452_findMinimumArrowsShorts(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0) {
			return 0;
		}
		if (intervals.size() == 1) {
			return 1;
		}
		
		// sort the interval by end in ascending order
        // if two ends are same, sort by start in descending order
		Comparator<Interval> myComp = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				if (o1.end == o2.end) {
					return o1.start > o2.start ? -1 : 1;
				}
				return o1.end < o2.end ? -1 : 1;
			}
		};
		Collections.sort(intervals, myComp);
		// the maximum of arrows will be intervals.size()
		int count = intervals.size();
		int lastEnd = Integer.MIN_VALUE;
		for (int i = 0; i < intervals.size(); i++) {
			Interval cur = intervals.get(i);
			if (cur.start > lastEnd) {
				lastEnd = cur.end;
			} else {
				// cur.start <= lastEnd, lastEnd and the cur interval have overlap
				count --;
			}
		}
		return count;
	}
	
	
	/*
	 * 435. Non-overlapping Intervals
	 * Given a collection of intervals, 
	 * find the minimum number of intervals you need to remove to 
	 * make the rest of the intervals non-overlapping.
	 * 
	 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
	 * Output: 1
	 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
	 */
	public static void test435() {
		Interval[] input = {
				new Interval(1, 2),
				new Interval(2, 3),
				new Interval(3, 4),
				new Interval(1, 3)
		};
		int rev = task435_eraseOverlapIntervals(input);
		System.out.println("rev = " + rev);
	}

	/**
	 * [1, 2], [2, 3], [3, 4], [1, 3]
	 *
	 * after sort
	 * [ 1 , 2 ] [ 2 , 3 ] [ 1 , 3 ] [ 3 , 4 ]
	 *
	 */
	public static int task435_eraseOverlapIntervals(Interval[] intervals) {
		// sort the interval by end in ascending order
		// if two ends are same, sort by start in descending order
		Arrays.sort(intervals, (o1, o2) -> {
			if (o1.end == o2.end) {
				return o1.start > o2.start ? -1 : 1;
			}
			return o1.end < o2.end ? -1 : 1;
		});

		for (Interval i: intervals) {
			System.out.print(i + " ");
		}
		System.out.println();
		// scan the sorted intervals.
		int lastEnd = Integer.MIN_VALUE;
		int count = 0;
		for (Interval i : intervals) {
			if (i.start >= lastEnd) { // there is no overlap with last and current interval, update lastEnd
				lastEnd = i.end;
			} else {
				// there is overlap.
				count++;
			}
		}
		return count;
	}


	public static class Cell implements Comparable<Cell> {
		public int val;
		public boolean isStart;

		public Cell(int v, boolean isStart) {
			this.val = v;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Cell other) {
			// TODO Auto-generated method stub
			if (this.val == other.val) {
				// let the end in front of start, if start.val == end.val
				if (!this.isStart) {
					return -1;
				} else if (this.isStart) {
					return 1;
				} else {
					return 0;
				}
			}

			return this.val < other.val ? -1 : 1;
		}
	}
}

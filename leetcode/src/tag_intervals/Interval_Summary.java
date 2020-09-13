package tag_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import ds.Interval;

public class Interval_Summary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 test2_1();
		// test4_1();
//		test5();
//		test22();
	}

	public static Comparator<Interval> myComp = new Comparator<Interval>() {
		@Override
		public int compare(Interval o1, Interval o2) {
			// TODO Auto-generated method stub
			if (o1.start == o2.start) {
				return 0;
			}
			return o1.start < o2.start ? -1 : 1;
		}
	};

	/**
	 * task1 merge intervals
	 * 1 sort by start
	 * 2 maintain last interval
	 * 3 check intersect
	 *
	 */
	public static List<Interval> task1_merge(List<Interval> list) {
		Collections.sort(list, (a, b) -> {
			return a.start < b.start ? -1 : 1;
		});
		List<Interval> result = new ArrayList<Interval>();
		Interval last = list.get(0);
		// from the index = 1
		for (int i = 1; i < list.size(); i++) {
			Interval cur = list.get(i);
			if (cur.start <= last.end) {
				// intersect cur.start <= last.end, there is intersection
				// merge the cur and last by update last.end
				last.end = Math.max(last.end, cur.end);
			} else {
				// there is no intersection, insert into result
				result.add(last);
				last = cur;
			}
		}
		// don't forget to add the last interval
		result.add(last);
		return result;
	}

	/*
	 * task1.1 Intervals Covered Length
	 */
	public static int task1_1_intervals_covered_length(List<Interval> list) {
		Collections.sort(list, myComp); // sorted by start
		int length = 0;

		int start = list.get(0).start;
		int end = list.get(0).end;
		for (int i = 1; i < list.size(); i++) {
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
		return length;
	}

	/*
	 * task1.2 determine if a target is covered by a list of intervals(!!! no
	 * intersect), sorted by the start [0,2][4,6][7,10] is 1 covered by the list
	 * of intervals? is 3 covered by the list of intervals?
	 * 
	 * Binary search.--> largest smaller or equals to the point O(log n) --> the
	 * only candidate can cover the point
	 * 
	 * Time: O(log n)
	 */
	public static void test1_2() {
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(0, 2));
		list.add(new Interval(7, 8));
		list.add(new Interval(9, 10));
		int target = 9;
		System.out.println(task1_2_covered_by_intervals(list, target));
		// System.out.println(task1_2_1_covered_by_intervals(list, target));
	}

	public static boolean task1_2_covered_by_intervals(List<Interval> list,
			int target) {
		Collections.sort(list, myComp);
		int left = 0;
		int right = list.size() - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (list.get(mid).start == target) {
				return true;
			} else if (list.get(mid).start < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		// debug
		System.out.println("left = " + left);
		System.out.println("end = " + right);
		if (list.get(right).start <= target) {
			// check whether list.get(end).end >= target
			if (list.get(right).end >= target) {
				return true;
			}
		} else if (list.get(left).start <= target) {
			if (list.get(left).end >= target) {
				return true;
			}
		}
		return false;
	}

	/*
	 * task1.2.1 1 element is covered by intervals ? (!!! Have intersect)
	 * intervals could be with intersects [0,2] [4,12] [8,10] is 11 covered by
	 * the list of intervals
	 * 
	 * 1 merge intervals-> list of intervals without intersect -> check covered
	 * during the merge process Time: O(n) 2 tree-->interval tree, segment tree
	 * O(log n)
	 */
	public static boolean task1_2_1_covered_by_intervals(List<Interval> list,
			int target) {
		Collections.sort(list, myComp);
		Interval last = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			Interval cur = list.get(i);
			if (cur.start <= last.end) {
				last.end = Math.max(cur.end, last.end);
			} else {
				// check whether in last
				if (last.start <= target && target <= last.end) {
					return true;
				}
				last = cur;
			}
		}
		// for the last one
		if (last.start <= target && target <= last.end) {
			return true;
		}
		return false;
	}

	/*
	 * task1.3 determine if an interval is covered by a list of intervals 1
	 * merge intervals --> list of intervals without intersect -> check covered
	 * during merge process O(n) 2 tree-> segment tree. O(log n)
	 */
	public static boolean task1_3_intervals_covered_by_intervals(
			List<Interval> lists, Interval interval) {
		// sanity check
		if (lists == null || lists.size() == 0) {
			return false;
		}
		// sort the intervals
		Collections.sort(lists, myComp);

		Interval last = lists.get(0);
		for (int i = 1; i < lists.size(); i++) {
			Interval cur = lists.get(i);
			if (cur.start <= last.end) {
				last.end = Math.max(last.end, cur.end);
			} else {
				// no intersection
				// check
				if (last.start <= interval.start && last.end >= interval.end) {
					return true;
				}
				last = cur;
			}
		}

		// check the last one
		if (last.start <= interval.start && last.end >= interval.end) {
			return true;
		}
		return false;
	}

	/*
	 * task2 insert intervals [0,2][3,5][6,7] + [1,4] --> [0,5][6,7] 1 list of
	 * intervals: without intersect --> insert [1,4], find the largest smaller
	 * interval.start than target interval[1,4] e.g 2 from that position, merge
	 */

	public static void test2() {
		ArrayList<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(0, 2));
		list.add(new Interval(3, 5));
		list.add(new Interval(6, 7));

		System.out.println(list);

		Interval interval = new Interval(9, 10);

		ArrayList<Interval> result = task2_insert_interval(list, interval);
		System.out.println(result);

	}

	public static ArrayList<Interval> task2_insert_interval(
			ArrayList<Interval> list, Interval interval) {
		// sort the list
		Collections.sort(list, myComp);
		// do binary search
		int start = 0, end = list.size() - 1;
		int position = -1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
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

		// merge
		ArrayList<Interval> result = new ArrayList<Interval>();
		if (position == -1) { // interval in front of the list
			Interval last = interval;
			for (int i = 0; i < list.size(); i++) {
				Interval cur = list.get(i);
				if (cur.start <= last.end) { // intersect
					last.end = Math.max(cur.end, last.end);
				} else { // cur.start > last.end
					// add the last into result;
					result.add(last);
					last = cur;
				}
			}
			result.add(last);
		} else {
			// first add all interval before position into result
			for (int i = 0; i < position; i++) {
				result.add(list.get(i));
			}
			System.out.println(result);
			Interval last = list.get(position);

			for (int i = position; i < list.size(); i++) {
				Interval cur = null;
				if (i == position) {
					cur = interval;
				} else {
					cur = list.get(i);
				}
				if (cur.start <= last.end) { // intersect
					last.end = Math.max(cur.end, last.end);
				} else { // cur.start > last.end
					// add the last into result;
					result.add(last);
					last = cur;
				}
			}
			result.add(last);
		}

		return result;
	}

	/*
	 * task2.1 merge two interval list [0,2][3,5][6,7] [1,4][6,9]
	 * 
	 * merge to one list, --> sorted by start ---> merge
	 */
	public static void test2_1() {
		Interval i11 = new Interval(0, 2);
		Interval i12 = new Interval(3, 5);
		Interval i13 = new Interval(6, 7);

		Interval i21 = new Interval(1, 4);
		Interval i22 = new Interval(6, 9);

		List<Interval> list1 = new ArrayList<Interval>();
		list1.add(i11);
		list1.add(i12);
		list1.add(i13);
		list1.add(i21);
		list1.add(i22);

		Collections.sort(list1, (a, b) -> {
			if (a.start == b.start){
				return a.end < b.end ? -1 : 1;
			} else if (a.start < b.start) {
				return -1;
			} else {
				return 1;
			}
		});

		for (Interval i: list1) {
			System.out.println(i.start + "-> " + i.end);
		}

		List<Interval> list2 = new ArrayList<Interval>();
		list2.add(i21);
		list2.add(i22);


		List<Interval> res = task2_1_merge_two_intervals(list1, list2);
		for (Interval i : res) {
			System.out.println(i);
		}

	}

	public static List<Interval> task2_1_merge_two_intervals(
			List<Interval> list1, List<Interval> list2) {
		if (list1 == null || list1.size() == 0) {
			return list2;
		}
		if (list2 == null || list2.size() == 0) {
			return list1;
		}

		List<Interval> result = new ArrayList<Interval>();
		int i = 0, j = 0;
		Interval last = null;
		while (i < list1.size() && j < list2.size()) {
			if (list1.get(i).start < list2.get(j).start) {
				Interval cur = list1.get(i);
				if (last == null) {
					last = cur;
				} else {
					// if there is overlap
					if (cur.start <= last.end) {
						last.end = Math.max(last.end, cur.end);
					} else {
						// no overlap
						result.add(last);
						last = cur;
					}
				}
				i++;
			} else {
				// get smaller interval from list2
				Interval cur = list2.get(j);
				if (last == null) {
					last = cur;
				} else {
					// if there is overlap
					if (cur.start <= last.end) {
						last.end = Math.max(last.end, cur.end);
					} else {
						// no overlap
						result.add(last);
						last = cur;
					}
				}
				j++;
			}
		}

		while (i < list1.size()) {
			Interval cur = list1.get(i);
			if (last == null) {
				last = cur;
			} else {
				// if there is overlap
				if (cur.start <= last.end) {
					last.end = Math.max(last.end, cur.end);
				} else {
					// no overlap
					result.add(last);
					last = cur;
				}
			}
			i++;
		}

		while (j < list2.size()) {
			Interval cur = list2.get(j);
			if (last == null) {
				last = cur;
			} else {
				// if there is overlap
				if (cur.start <= last.end) {
					last.end = Math.max(last.end, cur.end);
				} else {
					// no overlap
					result.add(last);
					last = cur;
				}
			}
			j++;
		}
		// don't forget the last one
		result.add(last);
		return result;
	}

	/*
	 * task3 interval subtraction [0,5) - [1,2) = {[0,1), [2,5)} [0,5) - [-1,6)
	 * = {} [0,5) - [3,7) = [0,3) [0,5) - [-1,2) =[2,5) [0,5) - [6,9) = [0,5)
	 * [0,5) - [-3,-1) = [0,5)
	 * 
	 * 分清楚几种情况就好，画图
	 * 
	 * 1 fix the start 2 fix the end
	 */
	public static void test3() {
		Interval i1 = new Interval(0, 5);
		Interval i2 = new Interval(1, 2);

		List<Interval> res = task3_subtract(i1, i2);
		System.out.println(res);
	}

	public static List<Interval> task3_subtract(Interval i1, Interval i2) {
		List<Interval> res = new ArrayList<Interval>();
		if (i1.start < i2.start) {
			// fix the start
			res.add(new Interval(i1.start, Math.min(i2.start, i1.end)));
		}
		if (i1.end > i2.end) {
			// fix the end
			res.add(new Interval(Math.max(i2.end, i1.start), i1.end));
		}
		return res;
	}

	/*
	 * Swipe Line Algorithm | | | | | | 0 1 2 3 4 5 6 7 8 ­­ separate the
	 * interval to two end points, and each endpoints is a “line”. ­­ the type
	 * of endpoints can be different and can contain different information.
	 */

	/*
	 * task4 number of intervals intersected
	 * 
	 * task4.0 all numbers of intervals intersected
	 * 
	 * task 4.1 User login Facebook, and each session is record as
	 * (start_timestamp, end_timestamp), track all number of users logged in
	 * [0,5) [2,4) [3,7)
	 * 
	 * 0,2,3,4,5,7
	 * 
	 * counter = 0
	 * 
	 * [0,2) ->1 [2,3) ->2 [3,4) ->3 [4,5) ->2 [5,7) ->1
	 * 
	 * intervals --> end point --> start end point / end end point start end
	 * point --> counter ++ end end point --> counter --
	 */

	public static void test4_1() {
		List<Interval> sessions = new ArrayList<Interval>();
		sessions.add(new Interval(0, 5));
		sessions.add(new Interval(2, 4));
		sessions.add(new Interval(3, 7));
		sessions.add(new Interval(6, 9));

		int max = task4_1maxLoginUser_1(sessions);
		System.out.println(max);
		System.out.println("---------------------");
		int max2 = task4_1_maxLoginUser_2(sessions);
		System.out.println("max2 = " + max2);
		int max3 = task4_1_maxLoginUser3(sessions);
		System.out.println("max3 = " + max3);
	}

	// this we can only find the max number of log user
	public static int task4_1maxLoginUser_1(List<Interval> sessions) {
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> end = new ArrayList<Integer>();

		for (int i = 0; i < sessions.size(); i++) {
			Interval cur = sessions.get(i);
			start.add(cur.start);
			end.add(cur.end);
		}
		Collections.sort(start);
		Collections.sort(end);

		int i = 0, j = 0;
		int counter = 0;
		int globalMax = Integer.MIN_VALUE;
		while (i < start.size() || j < end.size()) {
			if (i < start.size() && start.get(i) < end.get(j)) {
				counter++;
				i++;
				if (globalMax < counter) {
					globalMax = counter;
				}
			} else if (j < end.size()) {
				counter--;
				j++;
			}
			// System.out.println("count = " + counter);
		}

		return globalMax;

	}

	public static final int START = -1;
	public static final int END = -2;

	public static int task4_1_maxLoginUser_2(List<Interval> sessions) {
		ArrayList<Item> list = new ArrayList<Item>();
		for (int i = 0; i < sessions.size(); i++) {
			Interval cur = sessions.get(i);
			Item item1 = new Item(cur.start, START);
			Item item2 = new Item(cur.end, END);
			list.add(item1);
			list.add(item2);
		}

		Collections.sort(list);
		// for(Item i : list) {
		// System.out.println(i.val + " " + i.type + " ");
		// }
		// System.out.println();
		int couter = 0;
		int max = -1;
		for (int i = 0; i < list.size() - 1; i++) {
			Item cur = list.get(i);
			if (cur.type == START) {
				couter++;
			}
			if (cur.type == END) {
				couter--;
			}
			if (max < couter) {
				max = couter;
			}
			// System.out.println(couter);
		}
		return max;
	}

	public static class Interval2 {
		Item item1;
		Item item2;

		public Interval2(Item i1, Item i2) {
			this.item1 = i1;
			this.item2 = i2;
		}
	}

	public static class Item implements Comparable<Item> {
		int val;
		int type;

		public Item(int v, int t) {
			this.val = v;
			this.type = t;
		}

		@Override
		public int compareTo(Item o) {
			// TODO Auto-generated method stub
			if (this.val == o.val) {
				return 0;
			}
			return this.val < o.val ? -1 : 1;
		}
	}

	/*
	 * this method use minHeap to store the ending time of each sessions.
	 */
	public static int task4_1_maxLoginUser3(List<Interval> sessions) {
		// check
		if (sessions == null || sessions.size() == 0) {
			return 0;
		}
		// sort the sessions by interval.start
		Collections.sort(sessions, myComp);

		// create the minHeap to store the endtime of sessions
		// every time, when minHeap.peek(), we get the earliest ending session
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		minHeap.offer(sessions.get(0).end);
		int maxLoginCounter = 1;

		for (int i = 1; i < sessions.size(); i++) {
			Interval cur = sessions.get(i);
			if (cur.start <= minHeap.peek()) {
				// there is overlap
				maxLoginCounter++;
			} else {
				// no overlap
				minHeap.poll();
			}
			// push the cur.end into minHeap
			minHeap.offer(cur.end);
		}
		return maxLoginCounter;
	}

	/*
	 * task4.3 a lot of people, each person has a birth year and dead year, what
	 * is the year with most people alive? <start_time, end_time, 1> the same
	 * with task4_1
	 */

	/*
	 * 
	 * 
	 * 4.4 <start_time, end_time, value> 比如有一组数据: 1, 3, 100 2, 4, 20 5, 6, 300
	 * 。。。 这些数据时间点可能有重合。在时间段2~3之间,value的和是100+200 = 300. 找出这 组数据中最高的value和。 int
	 * globalValue; start_time : globalValue +=value end_time: globalValue
	 * ­=value
	 */
	public static void test4_4() {
		Interval3 n1 = new Interval3(2, 4, 300);
		Interval3 n2 = new Interval3(5, 6, 300);
		Interval3 n3 = new Interval3(1, 3, 100);

		List<Interval3> list = new ArrayList<Interval3>();
		list.add(n1);
		list.add(n2);
		list.add(n3);

		int rev = task4_4_maxValue(list);
		System.out.println("rev = " + rev);
	}

	public static class Interval3 {
		int start_time;
		int end_time;
		int value;

		public Interval3(int _start, int _end, int _value) {
			this.start_time = _start;
			this.end_time = _end;
			this.value = _value;
		}
	}

	public static class Item3 implements Comparable<Item3> {
		int time;
		int value;
		int type;

		public Item3(int _time, int _value, int _type) {
			// TODO Auto-generated constructor stub
			this.time = _time;
			this.value = _value;
			this.type = _type;
		}

		@Override
		public int compareTo(Item3 o) {
			// TODO Auto-generated method stub
			if (this.time == o.time) {
				return 0;
			}
			return this.time < o.time ? -1 : 1;
		}
	}

	public static int task4_4_maxValue(List<Interval3> input) {
		List<Item3> list = new ArrayList<Item3>();
		for (int i = 0; i < input.size(); i++) {
			Interval3 cur = input.get(i);
			Item3 start = new Item3(cur.start_time, cur.value, START);
			Item3 end = new Item3(cur.end_time, cur.value, END);
			list.add(start);
			list.add(end);
		}

		Collections.sort(list);
		int max = Integer.MIN_VALUE;
		int curVal = 0;
		for (int i = 0; i < list.size(); i++) {
			Item3 curItem = list.get(i);
			if (curItem.type == START) {
				curVal += curItem.value;
			}
			if (curItem.type == END) {
				curVal -= curItem.value;
			}
			max = Math.max(max, curVal);
		}
		return max;
	}

	/*
	 * task4.5 给出一堆人,然后你知道他们每个人的meeting schedule,返回所有人前三个共同的meeting schedule,
	 * 已经知道的方法:public ​List<Timeslot>​getSchedule(String person){}, 自己
	 * 要完成的方法:public ArrayList<Timeslot>
	 * getFirstThreeCommonSlot(List<String>people){}
	 */

	/*
	 * task5 Skyline （求轮廓，求面积）
	 * 
	 * Given n houses on the ground with each house represented by a rectangle.
	 * The i­th rectangleisrepresentedas​[start_i,end_i,height_i],​ where
	 * 0<=i<n.The rectangles may overlap with each other. How can we calculate
	 * the total area that these rectangles cover. Example: input = {<1,3,1>,
	 * <2,4,2>}, output = 5.
	 */
	public static class Building {
		public int start;
		public int end;
		public int height;

		public Building(int s, int e, int h) {
			this.start = s;
			this.end = e;
			this.height = h;
		}
	}

	/*
	 * method1 1 find all end points and sort O(n log n) 2 traverse all the
	 * buildings, update the height of the end points. O(n*n)
	 */

	/*
	 * method2 1) find all the end point and sort 2) max heap maintain the
	 * highest building at each point
	 * 
	 * 1). if (maxHeap is not empty and peek().end > cur.start) a. current is
	 * higher than heap.peek() b. current is not higher than heap.peek() 2).
	 * while (heap.peek() < cur.start) 3). if heap.isEmpty()
	 */
	// start comparator
	// smaller start has higher priority
	public static class StartComparator implements Comparator<Building> {

		@Override
		public int compare(Building o1, Building o2) {
			// TODO Auto-generated method stub
			if (o1.start == o2.start) {
				return 0;
			}
			return o1.start < o2.start ? -1 : 1;
		}
	}

	// heightComparator: used for maxHeop.
	// greater height has higher priority
	public static class HeightComparator implements Comparator<Building> {
		@Override
		public int compare(Building o1, Building o2) {
			// TODO Auto-generated method stub
			if (o1.height == o2.height) {
				return 0;
			}
			return o1.height > o2.height ? -1 : 1;
		}
	}

	public static void test5() {
		Building b1 = new Building(0, 4, 2);
		Building b2 = new Building(1, 3, 4);
		Building b3 = new Building(2, 5, 3);
		Building b4 = new Building(6, 9, 2);
		Building b5 = new Building(7, 9, 5);
		// Building b6 = new Building(4, 5, 5);

		Building[] buildings = { b1, b2, b3, b4, b5 };
		int area = totalArea(buildings);
		System.out.println("area = " + area);

		List<Position> res = getSkyline(buildings);
		System.out.println(res);

	}

	public static int totalArea(Building[] buildings) {
		assert buildings != null;
		if (buildings.length == 0) {
			return 0;
		}
		Arrays.sort(buildings, new StartComparator());

		// maintain the Building in maxHeap
		PriorityQueue<Building> maxHeap = new PriorityQueue<Building>(
				buildings.length, new HeightComparator());

		int area = 0;

		// left means the start position of current contour
		int left = Integer.MIN_VALUE;
		Building fakeLast = new Building(Integer.MAX_VALUE, Integer.MAX_VALUE,
				0);
		// swipe the building one by one
		for (int i = 0; i <= buildings.length; i++) {
			// get the current building
			Building cur = i < buildings.length ? buildings[i] : fakeLast;

			// remove all buildings having end position <= cur.start from the
			// maxHeap
			// all those building don't have overlap with cur.start
			while (!maxHeap.isEmpty() && maxHeap.peek().end <= cur.start) {
				Building top = maxHeap.poll();
				int curRightBound = top.end;
				int curLeftBound = left;
				int curHeight = top.height;
				// the curRight might be smaller than the curLeft
				// calculate the area
				area += Math.max(0, curRightBound - curLeftBound) * curHeight;

				// update left
				left = Math.max(top.end, left);
			}

			// if max heap is empty, meaning we can start a new contour
			if (maxHeap.isEmpty()) {
				left = cur.start;
			} else {
				if (maxHeap.peek().height < cur.height) {
					// if cur.height larger than the peek building's height
					// means the cur contour with the peek building's height can
					// be ended
					// and a new contour should start
					// so add the area in front of the cur.start into total area
					// sum

					area += (cur.start - left) * maxHeap.peek().height;
					left = cur.start;
				}
			}
			// add the current interval building into maxHeap
			maxHeap.offer(cur);

		}
		return area;

	}

	public static class Position {
		int index;
		int height;

		public Position(int _index, int _height) {
			this.index = _index;
			this.height = _height;
		}

		public String toString() {
			return "[" + index + ":" + height + "]";

		}
	}

	public static List<Position> getSkyline(Building[] buildings) {
		if (buildings == null || buildings.length == 0) {
			return null;
		}
		// sort the buildings by start in ascending order
		Arrays.sort(buildings, new StartComparator());
		// maxHeap
		PriorityQueue<Building> maxHeap = new PriorityQueue<Building>(
				buildings.length, new HeightComparator());
		// result
		List<Position> result = new ArrayList<Position>();

		Building fakeLast = new Building(Integer.MAX_VALUE, Integer.MAX_VALUE,
				0);
		int leftPtr = -1;
		for (int i = 0; i <= buildings.length; i++) {
			Building cur = i < buildings.length ? buildings[i] : fakeLast;
			boolean goingdown = false;
			while (!maxHeap.isEmpty() && maxHeap.peek().end <= cur.start) {
				Building top = maxHeap.poll();
				leftPtr = Math.max(leftPtr, top.end);
				goingdown = true;

			}
			if (goingdown) {
				if (maxHeap.isEmpty()) {
					result.add(new Position(leftPtr, 0));
				} else {
					result.add(new Position(leftPtr, maxHeap.peek().height));
				}
				goingdown = false;
			}

			// if max heap is empty, meaning we can start a new contour
			if (maxHeap.isEmpty()) {
				leftPtr = cur.start;
			} else {
				if (maxHeap.peek().height < cur.height) {
					leftPtr = cur.start;
				}
			}
			// add the current interval building into maxHeap
			maxHeap.offer(cur);

			result.add(new Position(leftPtr, maxHeap.peek().height));

		}
		return result;
	}

	public static void test22() {
		int[][] buildings = {
				{0,4,2},
				{1,3,4},
				{2,5,3},
				{2,4,5},
				{6,9,2},
				{7,9,5}
		};
		List<int[]> result = getSkyline2(buildings);
		for(int[] arr : result) {
			System.out.println("[ " + arr[0] + " " + arr[1] + " ]");
		}
	}
	
	
	/**
	 * 
	 * @param buildings
	 * @return
	 * 
	 * 如果按照一个矩形一个矩形来处理将会非常麻烦，我们可以把这些矩形拆成两个点，一个左上顶点，一个右上顶点。
	 * 将所有顶点按照横坐标排序后，我们开始遍历这些点。遍历时，通过一个堆来得知当前图形的最高位置。
	 * 堆顶是所有顶点中最高的点，只要这个点没被移出堆，说明这个最高的矩形还没结束。
	 * 对于左顶点，我们将其加入堆中。
	 * 对于右顶点，我们找出堆中其相应的左顶点，然后移出这个左顶点，同时也意味这这个矩形的结束。
	 * 具体代码中，为了在排序后的顶点列表中区分左右顶点，左顶点的值是负数，而右顶点值则存的是正数。
	 */
	public static List<int[]> getSkyline2(int[][] buildings) {
		List<int[]> result = new ArrayList<int[]>();
		if (buildings == null || 
			buildings.length == 0 || 
			buildings[0].length == 0) {
			return result;
		}

		List<Height> heights = new ArrayList<Height>();
		for (int[] building : buildings) {
			heights.add(new Height(building[0], -building[2]));
			heights.add(new Height(building[1], building[2]));
		}
		System.out.println("------------");
		for(Height h: heights) {
			System.out.println("{ " + h.index + " : " + h.height + " }");
		}
		
		System.out.println("===========");
		// sorted by the index in ascending order
		// if indices are same, sort by the height in ascending order.
		Collections.sort(heights, new Comparator<Height>() {
			@Override
			public int compare(Height h1, Height h2) {
				if (h1.index == h2.index) {
					return h1.height < h2.height ? -1 : 1;
				}
				return h1.index < h2.index ? -1 : 1;
			}	
		});
		
		// print out the height
		for(Height h: heights) {
			System.out.println("{ " + h.index + " : " + h.height + " }");
		}
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1000,
				Collections.reverseOrder());
		// 将地平线值先加入堆中
		maxHeap.offer(0);
		int prevHeight = 0;
		
		for (Height h : heights) {
			if (h.height < 0) {
			    // 将左定点加入到堆中
				maxHeap.offer(-h.height);
			} else {
				// 将右顶点对应的左顶点移去
				maxHeap.remove(h.height); 
				// if directly used priority queue, remove will take O(N) to find the target
				// and take O(log n) to remove the heap
			}
			
			// 如果堆的新顶部和上个keypoint高度不一样，则加入一个新的keypoint
			int curHeight = maxHeap.peek();
			if (curHeight != prevHeight) {
				result.add(new int[] { h.index, curHeight });
				prevHeight = curHeight;
			}
		}
		return result;
	}

	public static class Height {
		int index;
		int height;
		Height(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}

}

package mj_airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

import ds.Interval;

public class Airbnb_Mianjing_0619 {
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test4();
//		test5();
		test6();
	}
	/*
	 * Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn). 
	 * We want to find a way to round each element in A such that 
	 * after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T 
	 * where  yi = Floor(xi) or Ceil(xi), ceiling or floor of xi.
	 * We also want to minimize sum |x_i-y_i|
	 * 
	 */
	
	
	/*
	 * Maximum Room Days
	 * 
	 * 给一个数组代表reservation request，suppose start date, end date back to back.
	 * 比如[5,1,1,5]代表如下预定：
	 * Jul 1-Jul6
	 * Jul6-Jul7
	 * Jul7-Jul8
	 * jul8-Jul13
	 * 当然最开始那个Jul 1是随便选就好的啦。
	 * 现在input的意义搞清楚了。还有一个限制，就是退房跟开始不能是同一天，比如如果接了Jul 1-Jul6，Jul6-Jul7就不能接了。
	 * 那问题就是给你个数组，算算最多能把房子租出去多少天。这个例子的话就是10天。
	 * 
	 * find the largest sum from elements which cannot be consecutive.
	 * simiarly to 小偷偷东西的题目， leetcode House Robber
	 */
	
	public static int task2_max_room_days(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		if (array.length == 1) {
			return array[0];
		}
		
		int prev2 = 0;
		int prev1 = array[0];
		for(int i = 1; i < array.length;i++) {
			// take this room or NOT take this room
			int cur = Math.max(prev2 + array[i], prev1);
			
			// update the prev2 and prev1
			prev2 = prev1;
			prev1 = cur;
		}
		return prev1;
	}
	
	/*
	 *  Airbnb: 2D Iterator with remove()
	 *  
	 */
	
	/*
	 * CVS parser:
	 * 输入是
	 * [
	 * [‘John’, ‘Smith’, ‘john.smith@gmail.com’, ‘Los Angeles’, ‘1’],.
	 * [‘Jane’, ‘Roberts’, ‘janer@msn.com’, ‘San Francisco, CA’, ‘0’],
	 * [‘Alexandra “Alex”’, ‘Menendez’, ‘alex.menendez@gmail.com’, ‘Miami’, ‘1’]
	 * ]
	 * 
	 * 输出是： 
	 * John|Smith|john.smith@gmail.com|Los Angeles|1
	 * Jane|Roberts|janer@msn.com|San Francisco, CA|0
	 * Alexandra “Alex”|Menendez|alex.menendez@gmail.com|Miami|1
	 * 
	 * csv parser
	 * 如果有逗号，转化成|
	 * 如果有引号，把不考虑引号里逗号，把引号里的内容去引号整体打印。
	 * 如果有两重引号，只去掉一重引号。
	 * 
	 * 例子
	 * aa, bb, “aa”,”aa,bb”, “aa””aa”””
	 * 输出
	 * aa|bb|aa|aa,bb|aa”aa”
	 */
	public static void test3() {
		String s1 = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1";
		String s2 = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
		List<String> inputs = new ArrayList<String>();
		inputs.add(s1);
		inputs.add(s2);
		List<String> res = parseCSV(inputs);
		for (String str: res) {
			System.out.println(str);
		}
		
		String s3 ="\"AA \"\"BB\"\"\",CC,EEFF,GG,1";
		String r3 = parseCVS_singleString(s3);
		System.out.println(r3);
	}
	
	public static List<String> parseCSV(List<String> inputs) {
		List<String> res = new ArrayList<String>();
		
		for(int i = 0; i < inputs.size(); i++) {
			StringBuilder stb = new StringBuilder();
			String s = inputs.get(i);
			boolean inQuote = false;
			// parse current string
			for(int j = 0; j < s.length();j++) {
				if (!inQuote) { // not in quote
					if (s.charAt(j) == '"') {
						inQuote = true;
					} else if (s.charAt(j) == ',') {
						// is ','
						stb.append('|');
					} else {
						stb.append(s.charAt(j));
					}
				} else {
					// inQuote
					if (s.charAt(j) == '"') {
						if (j == s.length() - 1) {
							// the last char
							break;
						} else {
							if (s.charAt(j + 1) == '"') {
								stb.append(s.charAt(j));  // append this " to the stb
								j++;  // j++
							} else {
								inQuote = false;
								stb.append("|");
								// skip the current "
								j++;
							}
						}
					} else {
						// not "
						stb.append(s.charAt(j));
					}
				}
			}
			res.add(stb.toString());
			
		}
		return res;
	}
	
	public static String parseCVS_singleString(String input) {
		boolean inQuote = false;
		StringBuilder stb = new StringBuilder();
		for(int i = 0; i < input.length();i++) {
			if (!inQuote) {
				if (input.charAt(i) == '"') {
					// current inQuote is false. when it meets the first ", 
					// set inQuote to true
					inQuote = true;
				} else if (input.charAt(i) == ',') {
					// NOT inQuote, so, the , is a segergator
					stb.append('|');
				} else {
					// NOT inQuote, NOT ',', just common char
					stb.append(input.charAt(i));
				}
			} else {
				// inQuote
				if (input.charAt(i) == '"') {
					// current char is an "
					// check whether it reach to the end of input
					if (i == input.length() - 1) {
						break;
					} else if (input.charAt(i + 1) == '"') {
						// the next char is also "
						stb.append(input.charAt(i));
						i++;
					} else {
						// NOT to the end, the next is ALSO NOT "
						// this is the closing "
						// skip it
						inQuote = false;
						stb.append('|');
						i++;
					}
				} else {
					// current char is NOT "
					stb.append(input.charAt(i));
				}
			}
		}
		return stb.toString();
	}
	
	
	/*
	 * task4
	 * 实现分页显示。给了以下一些输入数据，要求将以下行分页显示，每页12行，
	 * 其中每行已经按score排好序，分页显示的时候如果有相同host id的行，则将后面同host id的行移到下一页。
[
"host_id,listing_id,score,city",
"1,28,300.1,SanFrancisco",
"4,5,209.1,SanFrancisco",
"20,7,208.1,SanFrancisco",
"23,8,207.1,SanFrancisco",
"16,10,206.1,Oakland",
"1,16,205.1,SanFrancisco",
"6,29,204.1,SanFrancisco",
"7,20,203.1,SanFrancisco",
"8,21,202.1,SanFrancisco",
"2,18,201.1,SanFrancisco",
"2,30,200.1,SanFrancisco",
"15,27,109.1,Oakland",
"10,13,108.1,Oakland",
"11,26,107.1,Oakland",
"12,9,106.1,Oakland",
"13,1,105.1,Oakland",
"22,17,104.1,Oakland",
"1,2,103.1,Oakland",
"28,24,102.1,Oakland",
"18,14,11.1,SanJose",
"6,25,10.1,Oakland",
"19,15,9.1,SanJose",
"3,19,8.1,SanJose",
"3,11,7.1,Oakland",
"27,12,6.1,Oakland",
"1,3,5.1,Oakland",
"25,4,4.1,SanJose",
"5,6,3.1,SanJose",
"29,22,2.1,SanJose",
"30,23,1.1,SanJose"
]

	 *
	 *There is a trick in this question. 
	 *When do we need to get to a new page? There are two cases need to consider:
	 *1. When the current page has 12 entries. 
	 *2. When the current page has less than 12 but the iterator has reached to the end. 
	 *IN this case, we need wrap back and iterator the list again. 
	 */
	public static void test4() {
		String[] arr = {
				"1,28,300.1,SanFrancisco",
				"4,5,209.1,SanFrancisco",
				"20,7,208.1,SanFrancisco",
				"23,8,207.1,SanFrancisco",
				"16,10,206.1,Oakland",
				"1,16,205.1,SanFrancisco",
				"6,29,204.1,SanFrancisco",
				"7,20,203.1,SanFrancisco",
				"8,21,202.1,SanFrancisco",
				"2,18,201.1,SanFrancisco",
				"2,30,200.1,SanFrancisco",
				"15,27,109.1,Oakland",
				"10,13,108.1,Oakland",
				"11,26,107.1,Oakland",
				"12,9,106.1,Oakland",
				"13,1,105.1,Oakland",
				"22,17,104.1,Oakland",
				"1,2,103.1,Oakland",
				"28,24,102.1,Oakland",
				"18,14,11.1,SanJose",
				"6,25,10.1,Oakland",
				"19,15,9.1,SanJose",
				"3,19,8.1,SanJose",
				"3,11,7.1,Oakland",
				"27,12,6.1,Oakland",
				"1,3,5.1,Oakland",
				"25,4,4.1,SanJose",
				"5,6,3.1,SanJose",
				"29,22,2.1,SanJose",
				"30,23,1.1,SanJose"
		};
		
		List<String> list = new ArrayList<String>(Arrays.asList(arr));
		
		for(String s: list) {
			System.out.println(s);
		}
		System.out.println("===========");
		displayPages(list);
	}
	
	
	public static void displayPages(List<String> input) {
		if (input == null || input.size() == 0) {
			return ;
		}
		
		Set<String> visited = new HashSet<String>();
		Iterator<String> iterator = input.iterator();
		int pageNum = 1;
		
		while(iterator.hasNext()) {
//			System.out.println("hello");
			String cur = iterator.next();
			String hostId = cur.split(",")[0];
			if (!visited.contains(hostId)) {
				System.out.println("~~~~~~~~~~");
				System.out.println(cur);
				visited.add(hostId);
				iterator.remove();
			}
			
			// new page
			if (visited.size() == 12 || (!iterator.hasNext())) {
				visited.clear();
				iterator = input.iterator();
				if (!input.isEmpty()) {
					pageNum++;
					System.out.println("page " + pageNum);
				}
			}
		}
	}
	
	
	
	/*
	 * Q: 给一组meetings（每个meeting由start和end时间组成）。
	 * 求出在所有输入meeting时间段内没有会议，也就是空闲的时间段。
	 * 每个subarray都已经sort好。
	 * N个员工，每个员工有若干个interval表示在这段时间是忙碌的。
	 * 
	 * 求所有员工都不忙的intervals。
	 * 举例：
	 * [
	 *  [[1, 3], [6, 7]],
	 *  [[2, 4]],
	 *  [[2, 3], [9, 12]]
	 * ]
	 * 返回
	 * [[4, 6], [7, 9]]. more info on 1point3acres.com
	 * A: 这题最简单的方法就是把所有区间都拆成两个点，然后排序，然后扫描，
	 * 每次碰到一个点如果是左端点就把busy_employees加1，否则减1，
	 * 等到每次busy_employees为0时就是一个新的区间。这样复杂度O(MlogM)，M是总共区间数。
	 */
	public static void test5() {
		Interval i1 = new Interval(1, 3);
		Interval i2 =new Interval(6, 7);
		Interval i3 = new Interval(2, 4);
		Interval i4 = new Interval(2,3);
		Interval i5 = new Interval(9, 12);
		List<Interval> l1 = new ArrayList<Interval>();
		l1.add(i1);
		l1.add(i2);
		
		List<Interval> l2 =new ArrayList<Interval>();
		l2.add(i3);
		
		List<Interval> l3 = new ArrayList<Interval>();
		l3.add(i4);
		l3.add(i5);
		
		List<List<Interval>> input = new ArrayList<List<Interval>>();
		input.add(l1);
		input.add(l2);
		input.add(l3);
		List<Interval> res = no_busy_employee(input);
		System.out.println(res);
	}
	
	// Time: O(n * log n)
	public static List<Interval> no_busy_employee(List<List<Interval>> input) {
		if (input == null || input.size() == 0) {
			return new ArrayList<Interval>();
		}
		List<Element> myList = new ArrayList<Airbnb_Mianjing_0619.Element>();
		for(List<Interval> list : input) {
			for(Interval i : list) {
				Element e1 =new Element(i.start, true);
				Element e2 = new Element(i.end, false);
				
				myList.add(e1);
				myList.add(e2);
			}
		}
		
		// sort by index, if indices are same, the isStart has higher priority
		Comparator<Element> myComp = new Comparator<Airbnb_Mianjing_0619.Element>() {
			@Override
			public int compare(Element o1, Element o2) {
				// TODO Auto-generated method stub
				if (o1.index == o2.index) {
					return o1.isStart ? -1 : 1;
				}
				return o1.index < o2.index ? -1 : 1;
			}
		};
		
		Collections.sort(myList, myComp);
		
		int busy_employee = 0;
		System.out.println(myList);
		int free_start = myList.get(0).index;
		List<Interval> result = new ArrayList<Interval>();
		for(int i = 0; i < myList.size(); i++) {
			Element cur = myList.get(i);
			if (cur.isStart) {
				if (busy_employee == 0) {
					if (cur.index > free_start) {
						Interval free_interval = new Interval(free_start, cur.index);
						result.add(free_interval);
					}
				}
				busy_employee++;
			} else {
				// cur.isStart==false
				if (busy_employee == 1) {
					free_start = cur.index;
				}
				busy_employee--;
			}
		}
		return result;
	
	}
	
	public static class Element{
		int index;
		boolean isStart;
		public Element(int _i, boolean _s) {
			this.index = _i;
			this.isStart = _s;
		}
	}
	
	
	/*
	 *   往一个int array 代表海拔的格子里倒水，打印出倒水后的图， 输入：int[] 海拔， int 水数量， int 倒得位置。
	 *   Example: int[] 海拔 {5,4,2,1,2,3,2,1,0,1,2,4}
	 * 5  +           
	 * 4  ++         +
	 * 3  ++   +     +
	 * 2  +++ +++   ++
	 * 1  ++++++++ +++
	 * 0  ++++++++++++
	 *    012345678912
	 *   012345水数量8， 倒在位置5 ->
	 *   
	 * 5  +           
	 * 4  ++         +
	 * 3  ++www+     +
	 * 2  +++w+++www++
	 * 1  ++++++++w+++
	 * 0  ++++++++++++
	 *    012345678912
	 *  
	 * assume the water first go left and if left side is full, go right 
	 */
	public static void pour_water(int[] terrians, int location, int water) {
		int len = terrians.length;
		int[] waterArr = new int[len];
		
		
	}
	
	/*
	 * task6
	 * Q) Given a list of menu items and prices. 
	 * Print all combinations that match a target price. 
	 * Eg: target = $3, Menu( A:$1 , B:$2) Print A,A,A A,B But no B,A
	 * 
	 * similar to coin change
	 */
	
	
	/*
	 * Min cost path allowing k connections
	 * A: 本质就是BFS一层一层往外扫。
	 * min cost of flight from start to end if allowed at most k connections. 比如：
	 * A->B, 100,
	 * B->C, 100,
	 * A->C, 500.
	 * 如果k是1的话，起点终点是A，C的话，那A->B->C最好
	 */
	public static class GraphNode{
		public int val;
		public HashMap<GraphNode, Integer> neighbors ; // neightbors with weight
		public GraphNode() {
			neighbors = new HashMap<GraphNode, Integer>();
		}
	}
	
	public static void test6() {
		GraphNode n1 = new GraphNode();
		GraphNode n2 = new GraphNode();
		GraphNode n3 = new GraphNode();
		GraphNode n4 = new GraphNode();
		GraphNode n5 = new GraphNode();
		GraphNode n6 = new GraphNode();
		
		n1.neighbors.put(n3, 1);
		n1.neighbors.put(n4, 1);
		n1.neighbors.put(n2, 10);
		
		n4.neighbors.put(n5, 5);
		n5.neighbors.put(n2, 1);
		
		n2.neighbors.put(n6, 1);
		n5.neighbors.put(n6, 1);
		List<GraphNode> graph = new ArrayList<GraphNode>();
		
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		graph.add(n4);
		graph.add(n5);
		graph.add(n6);
		
		int rev = min_cost(graph, n1, n6, 3);
		System.out.println("rev = " + rev);		
	}
	
	
	// assume that start and end are in graph
	public static int min_cost(List<GraphNode> graph, GraphNode start, GraphNode end, int k) {
		
		PriorityQueue<Elem> minHeap = new PriorityQueue<Airbnb_Mianjing_0619.Elem>();
		minHeap.offer(new Elem(start, 0));
		HashMap<GraphNode, Integer> distanceMap = new HashMap<Airbnb_Mianjing_0619.GraphNode, Integer>();
		distanceMap.put(start, 0);
		while(!minHeap.isEmpty() && k > 0) {
			int size = minHeap.size();
			for(int i = 0; i < size; i++) {
				Elem cur = minHeap.poll();
				// genearte the neighbors
				for (Entry<GraphNode, Integer> entry: cur.node.neighbors.entrySet()) {
					GraphNode neiNode = entry.getKey();
					Integer neiWeight =entry.getValue();
					
					if (!distanceMap.containsKey(neiNode)) {
						minHeap.offer(new Elem(neiNode, neiWeight));
						// 
						distanceMap.put(neiNode, distanceMap.get(cur.node) + neiWeight);
					} else {
						int minDistance = Math.min(distanceMap.get(neiNode), distanceMap.get(cur.node) + neiWeight);
						distanceMap.put(neiNode, minDistance);
					}
				}
			}			
			k --;
		}
		
		return distanceMap.get(end);
	}
	
	public static class Elem implements Comparable<Elem>{
		GraphNode node;
		int weight;
		public Elem(GraphNode _n, int _w) {
			this.node = _n;
			this.weight = _w;
		}
		@Override
		public int compareTo(Elem o) {
			// TODO Auto-generated method stub
			if (this.weight == o.weight) {
				return 0;
			}
			return this.weight < o.weight ? -1 : 1;
		}
	}
	
	
	
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	

}

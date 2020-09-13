package mj_amazon;

import java.util.*;
public class OA_20181212 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
		test2();
	}

	public static void test() {
		List<List<Integer>> allLocations = new ArrayList<>();
		List<Integer> loc1 = new ArrayList<>();
		loc1.add(1);
		loc1.add(2);
		
		List<Integer> loc2 = new ArrayList<>();
		loc2.add(3);
		loc2.add(4);
		
		List<Integer> loc3 = new ArrayList<>();
		loc3.add(1);
		loc3.add(-1);
		
		allLocations.add(loc1);
		allLocations.add(loc2);
		allLocations.add(loc3);
		
		List<List<Integer>> result = ClosestXdestinations(3, allLocations, 2);
		
		System.out.println(result);
		
		
	}
	public static List<List<Integer>> ClosestXdestinations(int numDestinations, 
			List<List<Integer>> allLocations, 
			int numDeliveries) {
		
		Comparator<List<Integer>> myComparator = new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				// TODO Auto-generated method stub
				int dist1 = o1.get(0) * o1.get(0) + o1.get(1) * o1.get(1);
				int dist2 = o2.get(0) * o2.get(0) + o2.get(1) * o2.get(1);
				if (dist1 == dist2) {
					return 0;
				}
				return dist1 > dist2 ? -1 : 1;
			}
		};
		
		PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<List<Integer>>(numDeliveries, myComparator);
		
		// add the first numDeliveries elements
		int i = 0;
		for (; i < numDestinations && i < numDeliveries; i++) {
			maxHeap.offer(allLocations.get(i));
		}
		while(i < numDestinations) {
			List<Integer> curPos = allLocations.get(i);
			int curDist = curPos.get(0) * curPos.get(0) + curPos.get(1) * curPos.get(1);
			
			List<Integer> top = maxHeap.peek();
			int topDist = top.get(0) * top.get(0) + top.get(1) * top.get(1);
			
			if (curDist < topDist) {
				maxHeap.poll();
				maxHeap.offer(curPos);
			}
			i++;
		}
		
		
		List<List<Integer>> result = new ArrayList<>();
		while(!maxHeap.isEmpty()) {
			result.add(maxHeap.poll());
		}
		
		return result;
	}
	
	
	public static void test2() {
		List<List<Integer>> foregroundAppList = new ArrayList<>();
		List<List<Integer>> backgroundAppList = new ArrayList<>();
		
		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(8);
		
		List<Integer> l2 = new ArrayList<>();
		l2.add(2);
		l2.add(15);
		
		List<Integer> l3 = new ArrayList<>();
		l3.add(3);
		l3.add(9);
		
		foregroundAppList.add(l1);
		foregroundAppList.add(l2);
		foregroundAppList.add(l3);
		
		
		List<Integer> b1 = new ArrayList<>();
		b1.add(1);
		b1.add(8);
		
		List<Integer> b2 = new ArrayList<>();
		b2.add(2);
		b2.add(11);
		
		List<Integer> b3 = new ArrayList<>();
		b3.add(3);
		b3.add(12);
		
		backgroundAppList.add(b1);
		backgroundAppList.add(b2);
		backgroundAppList.add(b3);
		
		int deviceCapacity = 20;
		
		List<List<Integer>> result = optimalUtilization(deviceCapacity, foregroundAppList, backgroundAppList);
		System.out.println(result);
		
		
	}
	public static List<List<Integer>> optimalUtilization(
			int deviceCapacity, 
			List<List<Integer>> foregroundAppList,
			List<List<Integer>> backgroundAppList) {
		List<List<Integer>> result = new ArrayList<>();
		int currentMaxCapacity = Integer.MIN_VALUE;
		
		for (int i = 0; i < foregroundAppList.size(); i++) {
			List<Integer> fApp = foregroundAppList.get(i);
			for (int j = 0; j < backgroundAppList.size(); j++) {
				List<Integer> bApp = backgroundAppList.get(j);
				int curSum = fApp.get(1) + bApp.get(1);
				System.out.println("=======================>>>>>>>>>");
				System.out.println("== 1: " + fApp.get(1));
				System.out.println("==2: " + bApp.get(1));
				System.out.println("==== curSum " + curSum);
				System.out.println("=======================>>>>>>>>>");
				if (curSum <= deviceCapacity) {
					if (curSum > currentMaxCapacity) {
						// reset the result 
						result = new ArrayList<>();
						List<Integer> curRes = new ArrayList<>();
						curRes.add(fApp.get(0));
						curRes.add(bApp.get(0));
						result.add(curRes);
						// update currentMaxCapacity
						currentMaxCapacity = curSum;
					} else {
						if (curSum == currentMaxCapacity) {
							List<Integer> curRes = new ArrayList<>();
							curRes.add(fApp.get(0));
							curRes.add(bApp.get(0));
							result.add(curRes);
						}
					}
				}
				
			}
		}
		
		return result;
	}
	

}

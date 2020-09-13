package mj_airbnb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Task25_MinimumCostWithAtMostKStops {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Given a flight itinerary consisting of starting city, destination city, and ticket price (2d list) - 
	 * find the optimal price flight path to get from start to destination.
	 * 
	 *  本质就是 BFS 一层一层往外扫。min cost of flight from start to end if allowed at most k connections. 比如:
	 *  A->B, 100,
	 *  B->C, 100,
	 *  A->C, 500.
	 *  如果 k 是 1 的话，起点终点是 A，C 的话，那 A->B->C 最好,
	 *  
	 *  
	 *  https://leetcode.com/problems/cheapest-flights-within-k-stops/
	 *  
	 */
	
	
	
	public static int findCheapestPrice(int n, int[][] flights, int src, int des, int K) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(i, new HashMap<>());
		}
		
		for (int[] flight: flights) {
			map.get(flight[0]).put(flight[1], flight[2]);
		}
		
		
		PriorityQueue<Tuple> queue = new PriorityQueue<>((a, b) -> a.price - b.price);
		queue.offer(new Tuple(src, 0, -1));
		
		while(!queue.isEmpty()) {
			Tuple cur = queue.poll();
			if (cur.city == des) {
				return cur.price;
			}
			if (cur.stops == K) {
				continue;
			}
			for (Map.Entry<Integer, Integer> next: map.get(cur.city).entrySet()) {
				queue.offer(new Tuple(next.getKey(), cur.price + next.getValue(), cur.stops + 1));
			}
		}
		return -1;
	}
	
	public static class Tuple {
		int city;
		int price;
		int stops;
		public Tuple(int c, int p, int s) {
			this.city = c;
			this.price = p;
			this.stops = s;
		}
	}
	
	public static int minCost(List<String> lines, String source, String target, int k) {
		if (lines == null || lines.size() == 0 || k < 0) {
			return 0;
		} 
		
		Map<String, Elem> map = new HashMap<>();  // source, <target, price>
		// build the graph
		for (String line: lines) {
			String[] s1 = line.trim().split(",");
			String[] s11 = s1[0].trim().split("->");
			String from = s11[0];
			String to = s11[1];
			int price = Integer.valueOf(s1[1].trim());
			map.put(from, new Elem(to, price));
		}
		
		boolean found = false;
		Queue<String> queue = new LinkedList<>();
		Queue<Integer> cost = new LinkedList<>();
		queue.offer(source);
		cost.offer(0);
		int stops = -1;
		while (!queue.isEmpty()) {
			stops ++;
			if (stops > k + 1) {
				break;
			}
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				
			}
		}
		
		
		return -1;
	}
	
	public static class Elem {
		String target;
		int price;
		public Elem(String target, int price) {
			this.target = target;
			this.price = price;
		}
	}
	
	

}

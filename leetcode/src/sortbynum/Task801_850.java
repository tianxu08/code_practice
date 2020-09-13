package sortbynum;

import java.util.*;
import java.util.Map.Entry;

public class Task801_850 {

	public static void main(String[] args) {
		test815();
	}

	/**
	 * 811
	 * 
	 * Input: ["9001 discuss.leetcode.com"] Output: ["9001 discuss.leetcode.com",
	 * "9001 leetcode.com", "9001 com"] Explanation: We only have one website
	 * domain: "discuss.leetcode.com". As discussed above, the subdomain
	 * "leetcode.com" and "com" will also be visited. So they will all be visited
	 * 9001 times. https://leetcode.com/problems/subdomain-visit-count/description/
	 * 
	 * @param cpdomains
	 * @return
	 * 
	 * 		The algorithm is straightforward: we just do what the problem
	 *         statement tells us to do. For an address like a.b.c, we will count
	 *         a.b.c, b.c, and c. For an address like x.y, we will count x.y and y.
	 *         To count these strings, we will use a hash map. To split the strings
	 *         into the required pieces, we will use library split functions.
	 * 
	 */

	public List<String> subdomainVisits(String[] cpdomains) {
		Map<String, Integer> counts = new HashMap();
		for (String domain : cpdomains) {
			String[] cpinfo = domain.split("\\s+");
			String[] frags = cpinfo[1].split("\\.");
			int count = Integer.valueOf(cpinfo[0]);
			String cur = "";
			for (int i = frags.length - 1; i >= 0; --i) {
				cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
				counts.put(cur, counts.getOrDefault(cur, 0) + count);
			}
		}

		List<String> ans = new ArrayList();
		for (String dom : counts.keySet())
			ans.add("" + counts.get(dom) + " " + dom);
		return ans;
	}

	/**
	 * task 815
	 */

	public static void test815() {
		int[][] routes = { { 1, 2, 7 }, { 3, 6, 7 } };
		int S = 1;
		int T = 6;
//		int rev = task815_numBusesToDestination(routes, S, T);
//		System.out.println("rev = " + rev);
		
		int rev2 = task815_numBusesToDestination2(routes, S, T);
		System.out.println("!!!! rev2: " + rev2);
		
	}

	public static int task815_numBusesToDestination(int[][] routes, int S, int T) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int[] route : routes) {
			for (Integer i : route) {
				if (!graph.containsKey(i)) {
					graph.put(i, new HashSet<>());
				}
				for (Integer j : route) {
					if (j != i) {
						graph.get(i).add(j);
					}
				}
			}
		}

		if (!graph.containsKey(S) || !graph.containsKey(T)) {
			return -1;
		}
		// for (Entry<Integer, Set<Integer>> entry: graph.entrySet()) {
		// System.out.print(entry.getKey() + "=>");
		// System.out.println(entry.getValue());
		// }

		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		q.offer(S);
		visited.add(S);

		int step = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				if (cur == T) {
					return step;
				}
				for (Integer nei : graph.get(cur)) {
					if (!visited.contains(nei)) {
						q.offer(nei);
						visited.add(nei);
					}
				}
			}
			step++;
		}
		return -1;

	}

	
	/*
	 * map<Station, Bus>
	 * for each bus, only take once
	 * 
	 * */
	
	public static int task815_numBusesToDestination2(int[][] routes, int S, int T) {
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> q = new LinkedList<>();
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		// station, buses
		int steps = 0;

		if (S == T)
			return 0;

		for (int i = 0; i < routes.length; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				ArrayList<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
				buses.add(i);
				map.put(routes[i][j], buses);
			}
		}
		
		if (!map.containsKey(T)) {
			return -1;
		}
//		for (Entry<Integer, ArrayList<Integer>> entry: map.entrySet()) {
//			System.out.println(entry.getKey() + "->" + entry.getValue());
//		}

		q.offer(S);
		while (!q.isEmpty()) {
			int len = q.size();
			steps++;
			for (int i = 0; i < len; i++) {
				int cur = q.poll();
				ArrayList<Integer> buses = map.get(cur);
				for (int bus : buses) {
					if (visited.contains(bus))
						continue;
					visited.add(bus);
					for (int j = 0; j < routes[bus].length; j++) {
						if (routes[bus][j] == T)
							return steps;
						q.offer(routes[bus][j]);
					}
				}
			}
		}
		return -1;
	}
	
	
	/**
	 * task841
	 */
	
	public static void test841() {
		
	}
	public static boolean task841_canVisitAllRooms(List<List<Integer>> rooms) {
		if (rooms == null || rooms.size() == 0) {
			return true;
		}
		Set<Integer> visited = new HashSet<>();
		// start from room 0
		task841_dfs(rooms, 0, visited);
		return visited.size() == rooms.size();
	}
	
	public static void task841_dfs(List<List<Integer>> rooms, int room, Set<Integer> visited) {
		if (visited.contains(room)) {
			return ;
		}
		visited.add(room);
		List<Integer> neighbors = rooms.get(room);
		for (Integer nei: neighbors) {
			task841_dfs(rooms, nei, visited);
		}
	}

	/*
	 * task847 shortest path visiting all nodes
	 */
	
	

}

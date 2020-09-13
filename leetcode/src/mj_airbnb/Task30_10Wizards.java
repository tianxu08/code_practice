package mj_airbnb;

import java.util.*;

public class Task30_10Wizards {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> wizards = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			List<Integer> list = new ArrayList<>();
			if (i == 0) {
				list.add(1);
				list.add(2);
			} else if (i == 1) {
				list.add(3);
			} else if (i == 2) {
				list.add(3);
				list.add(4);
			} else if (i == 3) {
				list.add(4);
			}
			wizards.add(list);
		}
		System.out.println("==>>> wizards: " + wizards);
		List<Integer> path = getShortestPath(wizards, 0, 4);
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));
		}
	}

	/**
	 * There are 10 wizards, 0-9, you are given a list that each entry is a list of
	 * wizards known by wizard. Define the cost between wizards and wizard as square
	 * of different of i and j. To find the min cost between 0 and 9. wizard[0]
	 * list: 1, 4, 5 wizard[4] list: 9 wizard 0 to 9 min distance is
	 * (0-4)^2+(4-9)^2=41 (wizard[0] ->wizard[4]->wizard[9])
	 */

	public static List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
		List<Integer> path = new ArrayList<>();
		if (wizards == null || wizards.size() == 0) {
			return path;
		}
		int n = wizards.size();
		Route[] from = new Route[n]; 
		// record the shortest path from its parent, using disktra, we can guarantee that the path is always the shortest  
		PriorityQueue<Route> pq = new PriorityQueue<Route>(new Comparator<Route>() {
			public int compare(Route r1, Route r2) {
				return r1.cost - r2.cost;
			}
		});
		List<Integer> nextWizards = wizards.get(source);
		for (int next : nextWizards) {
			pq.offer(new Route(next, source, (next - source) * (next - source)));
		}
		while (!pq.isEmpty()) {
			Route route = pq.poll();
			// if this is already 
			if (from[route.wizard] != null) {
				continue;
			}
			from[route.wizard] = route;
			
			System.out.println("=========");
			for (Route r: from) {
				System.out.println(r);
			}
			System.out.println("=========");
			
			if (route.wizard == target) {
				getPath(from, source, target, path);
				return path;
			}
			for (int next : wizards.get(route.wizard)) {
				pq.offer(new Route(next, route.wizard, (route.wizard - next) * (route.wizard - next)));
			}
		}
		return path;
	}

	static class Route {
		int wizard;
		int fromWizard;
		int cost;

		public Route(int wizard, int fromWizard, int cost) {
			this.wizard = wizard;
			this.fromWizard = fromWizard;
			this.cost = cost;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.wizard + " : " + this.fromWizard + " => " + this.cost;
		}
	}

	private static void getPath(Route[] from, int source, int target, List<Integer> path) {
		int wizard = target;
		while (wizard != source) {
			path.add(wizard);
			wizard = from[wizard].fromWizard;
		}
		path.add(source);
		Collections.reverse(path);
	}

}
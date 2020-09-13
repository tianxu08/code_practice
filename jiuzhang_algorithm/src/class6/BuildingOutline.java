package class6;
import java.util.*;
import class3.*;
public class BuildingOutline {

	
	/*
	 * Building Outline
	 * http://www.lintcode.com/en/problem/building-outline/
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static class Edge {
		int pos;
		int height;
		boolean isStart;

		public Edge(int pos, int height, boolean isStart) {
			this.pos = pos;
			this.height = height;
			this.isStart = isStart;
		}

	}

	public static class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge arg1, Edge arg2) {
			Edge l1 = (Edge) arg1;
			Edge l2 = (Edge) arg2;
			if (l1.pos != l2.pos)
				return compareInteger(l1.pos, l2.pos);
			if (l1.isStart && l2.isStart) {
				return compareInteger(l2.height, l1.height);
			}
			if (!l1.isStart && !l2.isStart) {
				return compareInteger(l1.height, l2.height);
			}
			return l1.isStart ? -1 : 1;
		}

		int compareInteger(int a, int b) {
			return a <= b ? -1 : 1;
		}
	}

	ArrayList<ArrayList<Integer>> output(ArrayList<ArrayList<Integer>> res) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		if (res.size() > 0) {
			int pre = res.get(0).get(0);
			int height = res.get(0).get(1);
			for (int i = 1; i < res.size(); i++) {
				ArrayList<Integer> now = new ArrayList<Integer>();
				int id = res.get(i).get(0);
				if (height > 0) {
					now.add(pre);
					now.add(id);
					now.add(height);
					ans.add(now);
				}
				pre = id;
				height = res.get(i).get(1);
			}
		}
		return ans;
	}

	public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
		// write your code here
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return res;
		}
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int[] building : buildings) {
			Edge startEdge = new Edge(building[0], building[2], true);
			edges.add(startEdge);
			Edge endEdge = new Edge(building[1], building[2], false);
			edges.add(endEdge);
		}
		Collections.sort(edges, new EdgeComparator());

		HashHeap heap = new HashHeap("max");

		ArrayList<Integer> now = null;
		for (Edge edge : edges) {
			if (edge.isStart) {
				if (heap.isEmpty() || edge.height > heap.peek()) {
					now = new ArrayList<Integer>(Arrays.asList(edge.pos,
							edge.height));
					res.add(now);
				}
				heap.offer(edge.height);
			} else {
				
				heap.pollElement(edge.height);
				if (heap.isEmpty() || edge.height > heap.peek()) {
					if (heap.isEmpty()) {
						now = new ArrayList<Integer>(Arrays.asList(edge.pos, 0));
					} else {
						now = new ArrayList<Integer>(Arrays.asList(edge.pos,
								heap.peek()));
					}
					res.add(now);
				}
			}
		}
		return output(res);
	}

}

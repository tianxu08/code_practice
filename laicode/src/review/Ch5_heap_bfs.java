package review;

import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

import ds.GraphNode;

public class Ch5_heap_bfs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/*
	 * t1 k smallest in unsorted array
	 * 
	 * <1> maxHeap heap_size =
	 * <2> minHeap
	 * <3> partition 
	 */
	public static void test1() {
		int[] a = null;
		int k = 1;
		int[] rev = null;
		a = new int[]{1,9,2,5,4,3,8};
		k = 3;
		rev = t1_kSmallest(a, k);
		System.out.println(Arrays.toString(rev));
		
	}
	
	public static int[] t1_kSmallest(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k >= arr.length) {
			return arr;
		}
		
		t1_kSmalest(arr, 0, arr.length - 1, k - 1);
		
		return Arrays.copyOf(arr, k);
	}
	
	public static void t1_kSmalest(int[] arr, int left, int right, int k) {
		if (left > right) {
			return ;
		}
		int pIdx = partition(arr, left, right);
		
		if (pIdx == k) {
			return ;
		} else if (pIdx > k) {
			t1_kSmalest(arr, left, pIdx - 1, k);
		} else {
			t1_kSmalest(arr, pIdx + 1, right, k);
		}
	}
	
	public static int partition(int[] a, int left, int right) {
		int pivot = a[right];
		int s = left, e = right -1;
		while(s <= e) {
			if (a[s] < pivot) {
				s ++;
			} else if (a[e] >= pivot) {
				e --;
			} else {
				swap(a, s, e);
				s++;
				e--;
			}
		}
		swap(a, s, right);
		return s;
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	
	/*
	 * 2 kth smallest number in sorted matrix
	 * use minHeap to expand/generate the elements in matrix
	 * assume:
	 * the matrix is not null M * N
	 * k > 0 && k < M * N 
	 */
	public static class Elem{
		int x;
		int y;
		int val;
		public Elem(int _x, int _y, int _val) {
			this.x = _x;
			this.y = _y;
			this.val = _val;
		}
	}
	
	public static int t2_kthSmallest(int[][] matrix, int k) {
		// check
		if (matrix == null || matrix[0] == null || matrix.length == 0 || matrix[0].length == 0 ) {
			return 0;
		}
		int result = Integer.MAX_VALUE;
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		PriorityQueue<Elem> minHeap = new PriorityQueue<Ch5_heap_bfs.Elem>(k, new Comparator<Elem>() {

			@Override
			public int compare(Elem o1, Elem o2) {
				// TODO Auto-generated method stub
				if (o1.val == o2.val) {
					return 0;
				}
				return o1.val < o2.val ? -1 : 1;
			}
		});
		
		minHeap.offer(new Elem(0, 0, matrix[0][0]));
		visited[0][0] = true;
		while(!minHeap.isEmpty() && k > 0) {
			
			Elem cur = minHeap.poll();
			result = cur.val;
			// generate neighbors
			// right
			if (cur.y + 1 < cLen && !visited[cur.x][cur.y + 1]) {
				minHeap.offer(new Elem(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
				visited[cur.x][cur.y + 1] = true;  //!!! don't forget to mark the position as true
			}
			// down
			if (cur.x + 1 < rLen && !visited[cur.x + 1][cur.y]) {
				minHeap.offer(new Elem(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
			}
			k --;
		}
		return result;
	}
	
	
	
	
	/*
	 * 3 check if binary tree is completed
	 */
	
	/*
	 * 4 bipartite
	 * whether a graph's nodes can be divided into two group, such that the nodes in each group 
	 * don't have direct edges between the nodes that belong to the same group.
	 * assumption: 
	 * the graph is NOT null
	 */
	public static boolean t4_isBapartite(List<GraphNode> graph) {
		assert graph != null;
		Map<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
		for(GraphNode node: graph) {
			if (!t4_isBapartiteBFS(node, map)) {
				return false; 
			}
		}
		return true;
	}
	
	public static boolean t4_isBapartiteBFS(GraphNode node, Map<GraphNode, Integer> map) {
		if (map.containsKey(node)) {
			return true;
		}
		
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		queue.offer(node);
		map.put(node, 0);
		// assign it with any of the gropus, e.g 0, or we can also it with 1
		
		while(!queue.isEmpty()) {
			GraphNode cur = queue.poll();
			int curSign = map.get(cur);
			int neiSign = curSign == 0 ? 1 : 0;
			
			for(GraphNode nei: cur.neighbors) {
				// if hte nei is NOT in the map, put it into the queuen and choose the correct group
				if (!map.containsKey(nei)) {
					map.put(nei, neiSign);
					// add into queue
					queue.offer(nei);
				} else {
					// if the neighbor has been visited and with different sign, return false
					if (map.get(nei) != neiSign) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	/*
	 * 5 get keys in binary tree layer by layer
	 */
	
	/*
	 * 6 zig-zag binary tree traversal
	 */
	
	
	/*
	 * 7 if a undirected graph has cycle
	 * BFS
	 * assumption:
	 * 1) the graph is undirected graph, so we can have a seed node to prepresent the whole graph
	 * 2) the seed node is not null
	 */
	public boolean t7_hasCycleBFS(GraphNode seed) {
		assert seed != null;
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		Map<GraphNode, GraphNode> parent = new HashMap<GraphNode, GraphNode>();
		// (1) record the parent of each node
		// (2) only the visited nodes are in the map
		parent.put(seed, null);
		q.offer(seed);
		while(!q.isEmpty()) {
			GraphNode cur = q.poll();
			// generate cur's neighbor
			for(GraphNode nei: cur.neighbors) {
				// ignore when nei is the parent node
				if (nei == parent.get(cur)) {
					continue;
				}
				// if neighbor has already visited
				if (parent.containsKey(nei)) {
					return true;
				}
				// put <nei, cur> into map
				parent.put(nei, cur);
				// put nei into q
				q.offer(nei);
			}
		}
		return false;
	}
	
	
	public boolean t7_hasCycleDFS(GraphNode seed) {
		assert seed != null;
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		visited.add(seed);
		return t7_helperDFS(seed, null, visited);
	}
	
	public boolean t7_helperDFS(GraphNode cur, GraphNode prev, HashSet<GraphNode> visited) {
		for(GraphNode nei: cur.neighbors) {
			// ignore the previous node
			if (nei == prev) {
				continue;
			}
			
			// if the neighbors has been visited before, there is a cycle
			if (visited.contains(nei)) {
				return true;
			} 
			visited.add(nei);
			
			if (t7_helperDFS(nei, cur, visited)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	

}

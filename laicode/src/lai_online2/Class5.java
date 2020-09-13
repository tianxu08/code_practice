package lai_online2;
import java.util.*;
import ds_lai_online2.*;
public class Class5 {
	/*
	task1
	Get Keys In Binary Tree Layer By Layer
	Easy
	Data Structure
	Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.

	Examples

	        5

	      /    \

	    3        8

	  /   \        \

	 1     4        11

	the result is [ [5], [3, 8], [1, 4, 11] ]

	Corner Cases

	What if the binary tree is null? Return an empty list of list in this case.
	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	*/
	public List<List<Integer>> layerByLayer(TreeNode root) {
		// Write your solution here.

		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		helper(result, root, 0);

		return result;
	}

	public void helper(ArrayList<List<Integer>> result, TreeNode node, int level) {
		// do a preorder traversal
		if (node == null) {
			return;
		}
		List<Integer> line = null;
		if (result.size() <= level) {
			line = new ArrayList<Integer>();
			result.add(line);
		} else {
			line = result.get(level);
		}
		line.add(node.key);

		helper(result, node.left, level + 1);
		helper(result, node.right, level + 1);

	}
	
	/*
	task2
	K Smallest In Unsorted Array
	Fair
	Data Structure
	Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.

	Assumptions

	A is not null
	K is >= 0 and smaller than or equal to size of A
	Return

	an array with size K containing the K smallest numbers in ascending order
	Examples

	A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
	*/
	public int[] kSmallest(int[] array, int k) {
		// Write your solution here
		if (array == null) {
			return null;
		}
		if (array.length == 0 || k == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						if (o1.equals(o2)) {
							return 0;
						}
						return o1 > o2 ? -1 : 1;
					}
				});

		for (int i = 0; i < array.length; i++) {
			if (i < k) {
				// offer the first k elements into max heap.
				maxHeap.offer(array[i]);
			} else if (array[i] < maxHeap.peek()) {
				// for the other elements, only offer it into the max heap if it
				// is
				// smaller than the max value in the max heap.
				maxHeap.poll();
				maxHeap.offer(array[i]);
			}
		}
		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = maxHeap.poll();
		}
		return result;
	}
	
	/*
	 * task3
	Kth Smallest Number In Sorted Matrix
	Fair
	Data Structure
	Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

	Assumptions

	the matrix is not null, N > 0 and M > 0
	K > 0 and K <= N * M
	Examples

	{ {1,  3,   5,  7},

	  {2,  4,   8,   9},

	  {3,  5, 11, 15},

	  {6,  8, 13, 18} }

	the 5th smallest number is 4
	the 8th smallest number is 6
	*/

	public int kthSmallest(int[][] matrix, int k) {
		// Write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return Integer.MIN_VALUE;
		}

		int len = matrix.length;
		int clen = matrix[0].length;
		PriorityQueue<Sum> minHeap = new PriorityQueue<Sum>(k,
				new Comparator<Sum>() {

					@Override
					public int compare(Sum o1, Sum o2) {
						// TODO Auto-generated method stub
						return o1.sum - o2.sum;
					}
				});
		boolean[][] visited = new boolean[len][clen];
		int result = Integer.MIN_VALUE;
		visited[0][0] = true;
		minHeap.offer(new Sum(0, 0, matrix[0][0]));
		while (k > 0) {
			Sum cur = minHeap.poll();
			result = cur.sum;
			if (cur.x + 1 < len && !visited[cur.x + 1][cur.y]) {
				minHeap.offer(new Sum(cur.x + 1, cur.y,
						matrix[cur.x + 1][cur.y]));
				visited[cur.x + 1][cur.y] = true;
			}
			if (cur.y + 1 < clen && !visited[cur.x][cur.y + 1]) {
				minHeap.offer(new Sum(cur.x, cur.y + 1,
						matrix[cur.x][cur.y + 1]));
				visited[cur.x][cur.y + 1] = true;
			}
			k--;
		}
		return result;

	}

	public class Sum {
		public int x;
		public int y;
		public int sum;

		public Sum(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}
	
	/*
	task4
	Check If Binary Tree Is Completed
	Fair
	Data Structure
	Check if a given binary tree is completed. A complete binary tree is one in which every level of the binary tree is completely filled except possibly the last level. Furthermore, all nodes are as far left as possible.

	Examples

	        5

	      /    \

	    3        8

	  /   \

	1      4

	is completed.

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	is not completed.

	Corner Cases

	What if the binary tree is null? Return true in this case.
	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	 */
	public boolean isCompleted(TreeNode root) {
		// Write your solution here.
		if (root == null)
			return true;

		boolean flag = false;
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();

		q.offer(root);

		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (!flag) {
				if (cur.left == null && cur.right != null) {
					return false;
				} else {
					if (cur.left != null && cur.right == null) {
						flag = true;
					} else if (cur.left == null && cur.right == null) {
						flag = true;
					}
				}
			} else {
				// flag == true
				if (cur.left != null || cur.right != null) {
					return false;
				}
			}

			if (cur.left != null) {
				q.offer(cur.left);
			}
			if (cur.right != null) {
				q.offer(cur.right);
			}
		}

		return true;
	}
	
	/*
	task5
	Bipartite
	Hard
	Graph
	Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.

	Examples

	1  --   2

	    /   

	3  --   4

	is bipartite (1, 3 in group 1 and 2, 4 in group 2).

	1  --   2

	    /   |

	3  --   4

	is not bipartite.

	Assumptions

	The graph is represented by a list of nodes, and the list of nodes is not null.
	*/
	public boolean isBipartite(List<GraphNode> graph) {
		// write your solution here
		if (graph == null) {
			return true;
		}
		HashMap<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
		for (GraphNode node : graph) {
			if (!BFS(node, map)) {
				return false;
			}
		}

		return true;
	}

	public boolean BFS(GraphNode node, HashMap<GraphNode, Integer> map) {
		if (map.containsKey(node)) {
			return true;
		}
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.offer(node);
		map.put(node, 0);
		while (!q.isEmpty()) {
			GraphNode cur = q.poll();
			int curSign = map.get(cur);
			int neiSign = curSign == 0 ? 1 : 0;
			for (GraphNode nei : cur.neighbors) {
				if (!map.containsKey(nei)) {
					map.put(nei, neiSign);
					q.offer(nei);
				} else if (map.get(nei) != neiSign) {
					return false;
				}
			}
		}
		return true;
	}
}

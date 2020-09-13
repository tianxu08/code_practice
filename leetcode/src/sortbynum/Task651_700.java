package sortbynum;

import java.util.*;
import ds.*;

public class Task651_700 {
	
	
	public static void main(String[] args) {
//		test690();
		test692();
//		test695();
	}
	


	/**
	 * 652
	 * 
	 * Use HashMap<String, TreeNode> to store the founded subtree and its root node.
	 * Use postorder traversal to get the left and right subtree and form the full
	 * subtree string with the current node. 
	 * If the subtree is found first time, put <postorder string, null>. 
	 * If the subtree is found again, put <postorder string, node> . 
	 * Finally, go through the HashMap to get those not-null value.
	 * 
	 */

	public List<TreeNode> task652_findDuplicateSubtrees(TreeNode root) {
		if (root == null)
			return new LinkedList<>();

		HashMap<String, TreeNode> map = new HashMap<>();

		task652_helper(root, map);

		List<TreeNode> res = new LinkedList<>();

		for (Map.Entry<String, TreeNode> e : map.entrySet()) {
			if (e.getValue() != null)
				res.add(e.getValue());
		}

		return res;

	}

	public String task652_helper(TreeNode node, HashMap<String, TreeNode> map) {
		// leaves node
		if (node.left == null && node.right == null) {
			String str = "" + node.val;
			if (!map.containsKey(str))
				map.put(str, null);
			else
				map.put(str, node);

			return str;
		}
		// post order
		String left = "";
		if (node.left != null)
			left = task652_helper(node.left, map);

		String right = "";
		if (node.right != null)
			right = task652_helper(node.right, map);

		// new subtree found, put null; subtree found again, put the subtree's root
		// node.
		String str = left + " # " + right + " # " + node.val;
		if (!map.containsKey(str))
			map.put(str, null);
		else
			map.put(str, node);

		return str;
	}

	
	
	public static void test690() {
		/*
		 * [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1 
		 */
		Employee e1 = new Employee();
		e1.id = 1;
		e1.importance = 5; 
		e1.subordinates = new ArrayList<>();
		e1.subordinates.add(2);
		e1.subordinates.add(3);
		
		
		Employee e2 = new Employee();
		e2.id = 2;
		e2.importance = 3; 
		e2.subordinates = new ArrayList<>();
		
		
		Employee e3 = new Employee();
		e3.id = 3;
		e3.importance = 3; 
		e3.subordinates = new ArrayList<>();
	
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		
		System.out.println(employees);
		
		int rev = task690_getImportance(employees, 1);
		System.out.println("==>>>> rev: " + rev);
		
		
	}
	
	public static int task690_getImportance(List<Employee> employees, int id) {
		Map<Integer, Employee> map = new HashMap<>();
		for(Employee e: employees) {
			map.put(e.id, e);
		}
		
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		
		q.offer(id);
		visited.add(id);
		
		int sum = 0;
		while(!q.isEmpty()) {
			int curId = q.poll();
			Employee curEmployee = map.get(curId);
			
			sum += curEmployee.importance;
			
			for (Integer nei: curEmployee.subordinates) {
				if (!visited.contains(nei)) {
					q.offer(nei);
					visited.add(nei);
				}
			}
		}
		return sum;
    }
	
	
	static class Employee {
	    // It's the unique id of each node;
	    // unique id of this employee
	    public int id;
	    // the importance value of this employee
	    public int importance;
	    // the id of direct subordinates
	    public List<Integer> subordinates;
	    
	    @Override
	    public String toString() {
	    	// TODO Auto-generated method stub
	    	return id + " : " + importance + "=>" + subordinates;
	    }
	};
	
	
	/**
	 * task695
	 * max area of island
	 */
	public static void test695() {
		int[][] grid = 
				{ 
						{ 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, 
						{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
						{ 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
						{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, 
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, 
						{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };
		
		int rev = task695_maxAreaOfIsland(grid);
		System.out.println("rev = " + rev);
	}
	
	public static int task695_maxAreaOfIsland(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) 
			return 0;
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
        		for (int j = 0; j < grid[0].length; j++) {
        			if (grid[i][j] == 1) {
        				int size = task695_dfs(grid, i, j);
        				max = Math.max(max, size);
        			}
        		}
        }
        
        return max;
    }
	
	public static int task695_dfs(int[][] grid, int i, int j) {
		if (i <0 || i >= grid.length || j < 0 || j >= grid[0].length) {
			return 0;
		}
		if (grid[i][j] == 0) {
			return 0;
		} 
		int num = 1;
		grid[i][j] = 0;
		
		// traverse all of its neighbors
		for (int[] dir: directions) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];
			num += task695_dfs(grid, nextX, nextY);
		}
		
		return num;
	}
	
	public static int[][] directions = {
			{-1, 0}, 
			{1, 0},
			{0, -1}, 
			{0, 1}
	};
	
	/**
	 * task692
	 * 
	 * top k frequent words
	 */
	public static void test692() {
		String[] words = {
			"i", "love", "leetcode", "i", "love", "coding"
		};
		int k = 2;
		List<String> res = task692_topKFrequent(words, k);
		System.out.println(res);
	}
	
	 public static List<String> task692_topKFrequent(String[] words, int k) {
		 Map<String, Integer> count = new HashMap<>();
		 for (String s: words) {
			 count.put(s, count.getOrDefault(s, 0) + 1);
		 }
		 
		 PriorityQueue<String> minHeap = new PriorityQueue<>(
				 (w1, w2) -> count.get(w1).equals(count.get(w2)) ?  w2.compareTo(w1) : count.get(w1) - count.get(w2));
		 
		 for (String word: count.keySet()) {
			 minHeap.offer(word);
			 if (minHeap.size() > k) {
				 minHeap.poll();
			 }
		 }
		 
		 List<String> ans = new ArrayList<>();
		 while (!minHeap.isEmpty()) {
			 ans.add(minHeap.poll());
		 }
		 Collections.reverse(ans);
		 return ans;
	 }
	
	
	
}

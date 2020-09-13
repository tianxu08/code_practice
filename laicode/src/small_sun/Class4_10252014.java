package small_sun;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

import ds.TreeNode;

public class Class4_10252014 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
//		test2();
		
	}
	/*
	 * Q1   Given a Binary Tree, print Right view of it. 
	 * Right view of a Binary Tree is set of nodes visible when tree is visited from Right side.
	 * 
	 * (1) level order
	 * (2) like the preOrder traverse recursion.
	 * 
	 * set a global variable, maxLevel, 
	 * root, root.right, root.left
	 * every time, if level > maxLevel, it's the first time to visit this level, print the right most node's value
	 * 
	 *        1
	 *     / 	\
	 *    2   	 3
	 *   / \    / \ 
	 *  4   5  6   7
	 *  
	 *  the right view of the binary tree is 1 3 7
	 *  
	 *  set a global variable, maxLevel. 
	 *  
	 */
	public static void test1() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n4.left = n5;
		task1_printRightView(n1);
		
		System.out.println("\n------------------");
		
		task1_printRightViewLevelOrder(n1);
	}
	
	public static class MaxLevel {
		public int value;
		public MaxLevel(int v) {
			this.value = v;
		}
	}
	
	public static void task1_printRightView(TreeNode root) {
		if (root == null) {
			return ;
		}
		MaxLevel maxLevel = new MaxLevel(-1);
		task1_helper(root, 0, maxLevel);
	}
	
	public static void task1_helper(TreeNode node,int level,  MaxLevel maxLevel) {
		if (node == null) {
			return ;
		}
		if (level > maxLevel.value) {
			// print out
			System.out.print(node.val + " ");
			maxLevel.value = level;
		}
		task1_helper(node.right, level + 1, maxLevel);
		task1_helper(node.left, level + 1, maxLevel);
	}
	
	/*
	 * do a level order traversal.print the right most element in every layer 
	 */
	public static void task1_printRightViewLevelOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i ++) {
				TreeNode cur = q.poll();
				if (i == size - 1) {
					// print out
					System.out.print(cur.val + " ");
				}
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
		}
	}
	
	
	/*
	 * Q2:   Given a binary tree, print it vertically. 
	 * The following example illustrates vertical order traversal.
	 * 
	 * implement
	 * 
	 * Use TreeMap
	 * 
	 *                  1
	 *                /   \
	 *               2     3
	 *              / \   / \
	 *             4   5 6   7
	 * 
	 * Time: traverse the tree O(n). create the tree and every time access key in treeMap O(log n)
	 * So, the total time is : O(n * log n)
	 *    
	 */
	public static void test2() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6; 
		n3.right = n7;
		
		task2_print_vertically(n1);
	}
	
	public static void task2_print_vertically(TreeNode root) {
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		task2_getMap(root, map, 0);
		
		// print out
		for(int i = map.firstKey(); i <= map.lastKey(); i ++) {
			System.out.println(map.get(i));
		}
	}
	
	/*
	 * hdist: is horizontal distance to root
	 * TreeMap<hdist, list of value of nodes in that vertical layer>
	 */
	public static void task2_getMap(TreeNode node, TreeMap<Integer, ArrayList<Integer>>map, int hdist) {
		if (node == null) {
			return ;
		}
		if (map.containsKey(hdist)) {
			ArrayList<Integer> list = map.get(hdist);
			list.add(node.val);
		} else {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(node.val);
			map.put(hdist, list);
		}
		task2_getMap(node.left, map, hdist - 1);
		task2_getMap(node.right, map, hdist + 1);
	}
	
	
	/*
	 * Q3:   设有N堆沙子排成一排,其编号为1,2,3,...,N(N<=100)。每堆沙子有一定的数量。
	 * 现要将N 堆沙子并成为一堆。归并的过程只能每次将相邻的两堆沙子堆成一堆(每次合并花费的代价为 当前两堆沙子的总数量),
	 * 这样经过N­1次归并后成为一堆,归并的总代价为每次合并花费的 代价和。找出一种合理的归并方法,使总的代价最小。
	 * 例如:有3堆沙子,数量分别为13,7,8,有两种合并方案, 
	 * 第一种 方案: 先合并1,2号堆,合并后的新堆沙子数量为20,本次合并代价为20,
	 * 再拿新堆与 第3堆沙子合并,合并后的沙子数量为28,本次合并代价为28,
	 * 将3堆沙子合并到一起的总代价 为第一次合并代价20加上第二次合并代价28,即48; 
	 * 第二种方案:先合并2,3号堆, 合并后的新堆沙子数量为15,本次合并代价为15,
	 * 再拿新堆与 第1堆沙子合并,合并后的沙子数量为28,本次合并代价为28,
	 * 将3堆沙子合并到一起的总代价 为第一次合并代价15加上第二次合并代价28,即43; 采用第二种方案可取得最小总代价,值为43。
	 * 
	 * review DP
	 */
	
	

}

package small_sun;

import java.util.ArrayList;

import debug.Debug;
import ds.ListNode;
import ds.TreeNode;

public class Class3_10182014 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
		test3();
	}
	
	/*
	 * Q1   Check if two nodes are cousins  in a Binary Tree
	 * Given the binary Tree and the two nodes say  ‘a’ and ‘b’ , determine whether the two nodes
	 * are cousins of each other or not.
	 * 
	 * Two nodes are cousins of each other if they are at same level and have  different  parents.
	 * 
	 * 1 get LCA of a and b  if LCA == a || LCA == b || LCA is parents of a OR b, return false
	 * 2 get depth from LCA of a and b, if level_a == level_b, return True
	 *   otherwise, return false;
	 */
	public static boolean task1_isCousins(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null || root == n1 || root == n2) {
			return false;
		}
		
		TreeNode LCA = task1_getLCA(root, n1, n2);
		if (LCA == n1 || LCA == n2 || isChild(LCA, n1) || isChild(LCA, n2)) {
			return false;
		}
		int n1_level = getLevel(root, n1, 0);
		int n2_level = getLevel(root, n2, 0);
		if (n1_level == n2_level) {
			return true;
		}
		return false;
	}
	
	public static boolean isChild(TreeNode p, TreeNode c) {
		boolean rev = false;
		if (p.left == c || p.right == c) {
			rev = true;
		}
		return rev;
	}
	
	
	
	public static TreeNode task1_getLCA(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return root;
		}
		if (root == n1 || root == n2) {
			return root;
		}
		TreeNode left = task1_getLCA(root.left, n1, n2);
		TreeNode right = task1_getLCA(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	
	public static int getLevel(TreeNode root, TreeNode n, int level) {
		if (root == null) {
			return 0;
		}
		if (root == n) {
			return level;
		}
		int nextLevel = getLevel(root.left, n, level + 1);
		if (nextLevel != 0) {
			return nextLevel;
		}
		nextLevel = getLevel(root.right, n, nextLevel);
		return nextLevel;
	}
	
	
	/*
	 * Q2 Given a linked list, reverse alternate nodes and append at the end
	 * Example1  Input List:  1 ­ > 2 ­> 3 ­>4­> 5 ­>6
	 *    curr next nnext
	 *    Output List:   1­>3­>5 ­>6­>4­>2 Example2  
	 *    
	 *    Input List:  1­ >2­> 3 ­>4­> 5
	 *    Output List:   1­>3­>5 ­>4­>2`
	 *    
	 * detail
	 */
	public static void test2() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
//		n5.next = n6;
		
		ListNode result = task2_listInterve(n1);
		Debug.printLinkedList(result);
	}
	
	
	
	
	
	/*
	 * 1 -> 2 -> 3 -> 4 -> 5 -> 6
	 * 
	 * 
	 */
	public static ListNode task2_listInterve(ListNode head) {
		ListNode dummy1 = new ListNode(-1);
		ListNode tail1 = dummy1;
		
		
		ListNode cur = head;
		ListNode tail = cur;
		while(cur != null) {
			ListNode next = cur.next;
			if (cur.next != null) {
				// skip the next
				cur.next = cur.next.next;
				// break the next
				next.next = null;
				// link the next to the tail1 
				tail1.next = next;
				// update tail1
				tail1 = tail1.next;
			}
			// update tail
			tail = cur;
			// update cur
			cur = cur.next;
		}
		
		ListNode head2 = dummy1.next;
		dummy1.next = null;
		
		// reverse the head2
		ListNode nHead2 = task2_reverse(head2);
		
		tail.next = nHead2;
		
		return head;
	}
	
	
	/*
	 * 1 -> 2 -> 3 -> 4 -> 5
	 * reverse
	 * 5 -> 4 -> 3 -> 2 -> 1    
	 */
	public static ListNode task2_reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode newHead = task2_reverse(next);
		head.next = null;
		next.next = head;
		return newHead;
		
	}
	
	
	/*
	 * 
	 * Q3  Given a sorted array arr[] and a value X, find the k closest elements to X in arr[]. 
	 * NOT including X
	 * Assuming X must be in the array.
	 * 
	 * (1)Binary search X
	 * (2)two direction, find the k closest elements
	 * 
	 */
	public static void test3() {
		int[] array = {1,4,5,9,10,14,19, 22};
		int x = 10;
		int k = 3;
		ArrayList<Integer> result = task3_kClosest(array, x, k);
		
		System.out.println(result);
	}
	
	public static ArrayList<Integer> task3_kClosest(int[] array, int x, int k) {
		// check
		if (array == null || array.length == 0) {
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		int index_x = -1;
		int left = 0, right = array.length - 1;
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (array[mid] == x) {
				index_x = mid;
				// don't forget break
				break;
			} else if (array[mid] > x) {
				// in the left side
				right = mid;
			} else {
				left = mid;
			}
			
		}
		if (array[left] == x) {
			index_x = left;
		} else if(array[right] == x) {
			index_x = right;
		}
		
		int left_idx = index_x - 1;
		int right_idx = index_x + 1;
		while(k > 0) {
			int leftElem = left_idx >= 0 ? array[left_idx] : Integer.MAX_VALUE;
			int rightElem = right_idx < array.length ? array[right_idx] : Integer.MAX_VALUE;
			
			if (Math.abs(leftElem - x ) < Math.abs(rightElem - x)) {
				if (leftElem != Integer.MAX_VALUE) {
					result.add(leftElem);
					left_idx --;
				}
				
			} else {
				if (rightElem != Integer.MAX_VALUE) {
					result.add(rightElem);
					right_idx --;
				}
				
			}
			
			k --;
		}
		return result;
	}
	
	
	
	

}

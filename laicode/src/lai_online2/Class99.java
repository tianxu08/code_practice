package lai_online2;
import java.util.*;
import ds_lai_online2.*;
public class Class99 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * task1 Buy Stock I Easy DP Given an array of positive integers
	 * representing a stock’s price on each day. On each day you can only make
	 * one operation: either buy or sell one unit of stock and you can make at
	 * most 1 transaction. Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and is length of at least 2. Examples
	 * 
	 * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4
	 */
	public static int task1_maxProfit(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int curMin = array[0];
		int maxFit = 0;
		for (int i = 1; i < array.length; i++) {
			if (curMin > array[i]) {
				curMin = array[i];
			}
			maxFit = Math.max(array[i] - curMin, maxFit);
		}

		return maxFit;
	}

	/*
	 * task2 Buy Stock II Easy DP Given an array of positive integers
	 * representing a stock’s price on each day. On each day you can only make
	 * one operation: either buy or sell one unit of stock, you can make as many
	 * transactions you want, but at any time you can only hold at most one unit
	 * of stock. Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The give array is not null and is length of at least 2 Examples
	 * 
	 * {2, 3, 2, 1, 4, 5}, the maximum profit you can make is (3 - 2) + (5 - 1)
	 * = 5
	 */
	public static int task2_maxProfit(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int maxProfit = 0;
		for (int i = 1; i < array.length; i++) {
			int diff = array[i] - array[i - 1];
			if (diff > 0) {
				maxProfit += diff;
			}
		}
		return maxProfit;
	}

	/*
	 * task3 Buy Stock III Fair DP Given an array of positive integers
	 * representing a stock’s price on each day. On each day you can only make
	 * one operation: either buy or sell one unit of stock, at any time you can
	 * only hold at most one unit of stock, and you can make at most 2
	 * transactions in total. Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The give array is not null and is length of at least 2 Examples
	 * 
	 * {2, 3, 2, 1, 4, 5, 2, 11}, the maximum profit you can make is (5 - 1) +
	 * (11 - 2) = 13
	 */
	public static int task3_maxProfit(int[] array) {
		// write your solution here
		return 0;
	}

	/*
	 * task4 Buy Stock IV Hard DP Given an array of integers representing a
	 * stock’s price on each day. On each day you can only make one operation:
	 * either buy or sell one unit of stock, and at any time you can only hold
	 * at most one unit of stock, and you can make at most K transactions in
	 * total. Determine the maximum profit you can make.
	 * 
	 * Assumptions
	 * 
	 * The give array is not null and is length of at least 2 Examples
	 * 
	 * {2, 3, 2, 1, 4, 5, 2, 11}, K = 3, the maximum profit you can make is (3 -
	 * 2) + (5 - 1) + (11 - 2)= 14
	 */

	public static int maxProfit(int[] array, int k) {
		// write your solution here
		return 0;
	}

	/*
	 * task5 Largest SubArray Product Hard DP Given an unsorted array of
	 * doubles, find the subarray that has the greatest product. Return the
	 * product.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and has length of at least 1. Examples
	 * 
	 * {2.0, -0.1, 4, -2, -1.5}, the largest subarray product is 4 * (-2) *
	 * (-1.5) = 12
	 */
	public static double task5_largestProduct(double[] array) {
		// write your solution here

		return 0;
	}

	/*
	 * task6 Largest Rectangle Of 1s Hard DP Determine the largest rectangle of
	 * 1s in a binary matrix (a binary matrix only contains 0 and 1), return the
	 * area.
	 * 
	 * Assumptions
	 * 
	 * The given matrix is not null and has size of M * N, M >= 0 and N >= 0
	 * Examples
	 * 
	 * { {0, 0, 0, 0},
	 * 
	 * {1, 1, 1, 1},
	 * 
	 * {0, 1, 1, 1},
	 * 
	 * {1, 0, 1, 1} }
	 * 
	 * the largest rectangle of 1s has area of 2 * 3 = 6
	 */
	public int task6_largest(int[][] matrix) {
		// write your solution here

		return 0;
	}

	/*
	 * task7 Largest SubMatrix Product Hard DP Given a matrix that contains
	 * doubles, find the submatrix with the largest product.
	 * 
	 * Return the product of the submatrix.
	 * 
	 * Assumptions
	 * 
	 * The given double matrix is not null and has size of M * N, where M >= 1
	 * and N >= 1 Examples
	 * 
	 * { {1, -0.2, -1},
	 * 
	 * {1, -1.5, 1},
	 * 
	 * {0, 0, 1} }
	 * 
	 * the largest submatrix product is 1 * 1 = 1.
	 */

	public double task7_largest(double[][] matrix) {
		// write your solution here
		return 0;
	}
	
	/*
	 * task8
	 * Largest Sum Of Valid Numbers
Hard
DP
Given a 2D array A[8][8] with all integer numbers if we take a number a[i][j], then we cannot take its 8 neighboring cells. How should we take the numbers to make their sum as large as possible.

Assumptions

The given matrix is 8 * 8
	 */
	
	public int task8_largestSum(int[][] matrix) {
	    // write your solution here
	    return 0;
	  }
	/*
	 * task9
	 * Common Numbers Of Two Arrays I
Easy
Data Structure
Find all numbers that appear in both of the two unsorted arrays.

Assumptions

Both arrays are not null.
There are no duplicate numbers in each of the two arrays respectively.
Exmaples

A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
A = {}, B = {3, 1, 4}, return []
	 */
	public static List<Integer> task9_common(List<Integer> a, List<Integer> b) {
		// write your solution here
		List<Integer> result = new ArrayList<Integer>();
		if (a == null || a.size() == 0 || b == null || b.size() == 0) {
			return result;
		}
		// suppose that a.size > b.size
		if (a.size() < b.size()) {
			return task9_common(b, a);
		}

		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < b.size(); i++) {
			set.add(b.get(i));
		}

		for (int i = 0; i < a.size(); i++) {
			if (set.contains(a.get(i))) {
				result.add(a.get(i));
			}
		}

		Collections.sort(result);
		return result;

	}
	/*
	 * task10
	Common Numbers Of Two Arrays II
	Easy
	Data Structure
	Find all numbers that appear in both of two unsorted arrays.

	Assumptions

	Both of the two arrays are not null.
	In any of the two arrays, there could be duplicate numbers.
	Examples

	A = {1, 2, 3, 2}, B = {3, 4, 2, 2, 2}, return [2, 2, 3] (there are both two 2s in A and B)
	 */
	public static List<Integer> task10_common(List<Integer> A, List<Integer> B) {
	    //write your solution here
	    List<Integer> result = new ArrayList<Integer>();
			if (A == null ||A.size() == 0 || B == null || B.size() == 0) {
				return result;
			}
			if (A.size() < B.size()) {
				return task10_common(B, A);
			}
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		    for(Integer i: B) {
		    	if (!map.containsKey(i)) {
					map.put(i, 1);
				} else {
					map.put(i, map.get(i) + 1);
				}
		    }
		    
		    for(Integer i : A) {
		    	if (map.containsKey(i)) {
					if (map.get(i) != 0) {
						result.add(i);
						map.put(i, map.get(i) - 1);
					}
				}
		    }
		    
		    Collections.sort(result);
			return result;
	  }
	/*
	 * task11
	All Unique Characters I
	Easy
	Data Structure
	Determine if the characters of a given string are all unique.

	Assumptions

	The only set of possible characters used in the string are 'a' - 'z', the 26 lower case letters.
	The given string is not null.
	Examples

	the characters used in "abcd" are unique
	the characters used in "aba" are not unique
	
	*/
	
	 public boolean task11_allUnique(String word) {
		    // write your solution here
		    if (word == null || word.length() == 0) {
					return true;
				}
				
				int[] vec = new int[8];
				for(int i = 0; i < word.length(); i ++) {
					char curChar = word.charAt(i);
					int pos = (int)curChar;
					int rowIndex = pos / 32;
					int colIndex = pos % 32;
					if (((vec[rowIndex] >>> colIndex) & 1 )!= 0) {
						return false;
					}
					vec[rowIndex] |= 1 << (colIndex);
				}
				
				return true;
		  }
	 /*
	  * task12
	 Search In Shifted Sorted Array I
	 Fair
	 Data Structure
	 Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.

	 For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.

	 Assumptions

	 There are no duplicate elements in the array.
	 Examples

	 A = {3, 4, 5, 1, 2}, T = 4, return 1
	 A = {1, 2, 3, 4, 5}, T = 4, return 3
	 A = {3, 5, 6, 1, 2}, T = 4, return -1
	 Corner Cases

	 What if A is null or A is of zero length? We should return -1 in this case.
	*/
	 
	public static int task12_search(int[] array, int target) {
		// Write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}
		int start = 0, end = array.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] > array[start]) {
				// the left side is sorted
				if (array[mid] >= target && target >= array[start]) {
					// in the left side
					end = mid;
				} else {
					// a[mid] < target, so, in the right side
					start = mid;
				}
			} else {
				// right side is sorted
				if (array[mid] <= target && target <= array[end]) {
					// in the right side
					start = mid;
				} else {
					// in the left side
					end = mid;
				}
			}
		}
		if (array[start] == target) {
			return start;
		} else if (array[end] == target) {
			return end;
		} else {
			return -1;
		}
	}
	/*
	 * task13
	Shift Position
	Fair
	Data Structure
	Given an integer array A, A is sorted in ascending order first then shifted by an arbitrary number of positions, For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index of the smallest number.

	Assumptions

	There are no duplicate elements in the array
	Examples

	A = {3, 4, 5, 1, 2}, return 3
	A = {1, 2, 3, 4, 5}, return 0
	Corner Cases

	What if A is null or A is of zero length? We should return -1 in this case.
	*/
	
	public static int task13_shiftPosition(int[] array) {
	    // Write your solution here
	    if (array == null || array.length == 0) {
	      return -1;
	    }
	    int start = 0, end = array.length - 1;
	    // the array is already sorted
	    if (array[start] < array[end]) {
	      return 0;
	    }
	    while(start + 1 < end) {
	      int mid = start + (end - start)/2;
	      if(array[mid] > array[start] && array[mid] < array[end] ) {
	        return start;
	      } else if (array[mid] > array[start]) {
	        // left side is sorted
	        start = mid;
	      } else {
	        end = mid;
	      }
	    }
	    if (array[start] < array[end]) {
	      return start;
	    } else {
	      return end;
	    }
	  
	  }
	
	/*
	 * task14
	Total Occurrence
	Fair
	Data Structure
	Given a target integer T and an integer array A sorted in ascending order, Find the total number of occurrences of T in A.

	Examples

	A = {1, 2, 3, 4, 5}, T = 3, return 1
	A = {1, 2, 2, 2, 3}, T = 2, return 3
	A = {1, 2, 2, 2, 3}, T = 4, return 0
	Corner Cases

	What if A is null? We should return 0 in this case.
	*/
	public int task14_totalOccurrence(int[] array, int target) {
	    // Write your solution here
	    // do two binary search. (1)search the left most target  (2) search the right most target
	    if (array == null || array.length == 0) {
	      return 0;
	    }
	    int n = array.length;
	    int start = 0, end = n-1;
	    int leftMost = -1, rightMost = -1;
	    while(start + 1 < end) {
	      int mid = start + (end - start)/2;
	      if(array[mid] == target) {
	        end = mid;
	      } else if (array[mid] > target) {
	        end = mid;
	      } else {
	        start = mid; 
	      }
	    }
	    if (array[start] == target) {
	      leftMost = start;
	    } else if (array[end] == target){
	      leftMost = end;
	    } else {
	      return 0;
	    }
	    
	    start = 0;
	    end = n - 1;
	     while(start + 1 < end) {
	      int mid = start + (end - start)/2;
	      if(array[mid] == target) {
	        start = mid;
	      } else if (array[mid] > target) {
	        end = mid;
	      } else {
	        start = mid; 
	      }
	    }
	    
	    if(array[end] == target) {
	      rightMost = end;
	    } else if (array[start] == target) {
	      rightMost = start;
	    } else {
	      return 0;
	    }
	    return rightMost - leftMost + 1;
	  }
	
	
	/*
	 * task15
	Kth Smallest Sum In Two Sorted Arrays
	Fair
	Data Structure
	Given two sorted arrays A and B, of sizes m and n respectively. Define s = a + b, where a is one element from A and b is one element from B. Find the Kth smallest s out of all possible s'.

	Assumptions

	A is not null and A is not of zero length, so as B
	K > 0 and K <= m * n
	Examples

	A = {1, 3, 5}, B = {4, 8}

	1st smallest s is 1 + 4 = 5
	2nd smallest s is 3 + 4 = 7
	3rd, 4th smallest s are 9 (1 + 8, 4 + 5) 
	5th smallest s is 3 + 8 = 11
	*/
	 public static int task15_kthSum(int[] A, int[] B, int k) {
		    // Write your solution here
		    return 0;
	 }
	 
	 /*
	  * task16
	 Merge Sort Linked List
	 Fair
	 Data Structure
	 Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The merge sort algorithm should be used to solve this problem.

	 Examples

	 null, is sorted to null
	 1 -> null, is sorted to 1 -> null
	 1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
	 4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
	 */
	 
	public ListNode task16_mergeSort(ListNode head) {
		// Write your solution here.
		// base case
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = findMid(head);
		ListNode second = task16_mergeSort(mid.next);
		// break the two list
		mid.next = null;

		ListNode first = task16_mergeSort(head);

		return merge(first, second);
	}

	public ListNode findMid(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode fast = head.next;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		// use a dummy node
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		while (l1 != null && l2 != null) {
			if (l1.value < l2.value) {
				tail.next = l1;
				// update l1
				l1 = l1.next;
				// update tail
				tail = tail.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
				tail = tail.next;
			}
		}

		if (l1 != null) {
			tail.next = l1;
		}
		if (l2 != null) {
			tail.next = l2;
		}

		return dummy.next;
	}
	
	/*
	 * task17 
	 * Cycle Node In Linked List Fair Data Structure Check if a given
	 * linked list has a cycle. Return the node where the cycle starts. Return
	 * null if there is no cycle.
	 */
	
	public ListNode task17_cycleNode(ListNode head) {
	    // write your solution here
	    if (head == null || head.next == null) {
	      return null;
	    }
	    ListNode fast = head;
	    ListNode slow = head;
	    while(fast != null && fast.next != null) {
	      fast = fast.next.next;
	      slow = slow.next;
	      if (fast == slow) {
	        break;
	      }
	    }
	    if (fast == null || fast.next == null) {
	      return null;
	    }
	    fast = head;
	    while(fast != slow) {
	      fast = fast.next;
	      slow = slow.next;
	    }
	    
	    return fast;
	    
	  }
	
	/*
	 * task18
	 * Spiral Order Generate I
Fair
Data Structure
Generate an N * N 2D array in spiral order clock-wise starting from the top left corner, using the numbers of 1, 2, 3, …, N * N in increasing order.

Assumptions

N >= 0
Examples

N = 3, the generated matrix is

{ {1,  2,  3}

  {8,  9,  4},

  {7,  6,  5} }
	 */
	 public static int[][] task18_spiralGenerate(int n) {
		    // write your solution here
		    if (n <= 0) {
					return new int[][] {};
				}
				int rLen = n;
				int cLen = n;
				int[][] matrix = new int[n][n];
				int leftB = 0, rightB = cLen - 1;
				int upperB = 0, lowerB = rLen - 1;
				int counter = 1;
				while (true) {
					for (int j = leftB; j <= rightB; j++) {
						matrix[upperB][j] = counter++;
					}
					upperB++;
					if (leftB > rightB || upperB > lowerB) {
						break;
					}
					for (int i = upperB; i <= lowerB; i++) {
						matrix[i][rightB] = counter++;
					}
					rightB--;
					if (leftB > rightB || upperB > lowerB) {
						break;
					}
					for (int j = rightB; j >= leftB; j--) {
						matrix[lowerB][j] = counter++;
					}
					lowerB--;
					if (leftB > rightB || upperB > lowerB) {
						break;
					}
					for (int i = lowerB; i >= upperB; i--) {
						matrix[i][leftB] = counter++;
					}
					leftB++;
					if (leftB > rightB || upperB > lowerB) {
						break;
					}
				}
				return matrix;
		  }
	
	 /*
	  * task19
	 Deep Copy Skip List
	 Fair
	 Data Structure
	 A Skip List is a special type of linked list, where each of the nodes has a forward pointer to another node in the front and forward pointers are guaranteed to be in non-descending order.

	 Make a deep copy of the original skip list.
	 
	 */
	public SkipListNode task19_copy(SkipListNode head) {
		// write your solution here
		if (head == null) {
			return null;
		}
		Map<SkipListNode, SkipListNode> map = new HashMap<SkipListNode, SkipListNode>();
		SkipListNode newHead = new SkipListNode(head.value);
		map.put(head, newHead);

		SkipListNode cur = newHead;
		while (head != null) {
			if (head.next != null) {
				if (!map.containsValue(head.next)) {
					// hasn't been copied over due to forward pointer
					SkipListNode node = new SkipListNode(head.next.value);
					map.put(head.next, node);
				}
				cur.next = map.get(head.next);
			}
			if (head.forward != null) {
				if (!map.containsValue(head.forward)) {
					SkipListNode node = new SkipListNode(head.forward.value);
					map.put(head.forward, node);
				}
				cur.forward = map.get(head.forward);
			}
			head = head.next;
			cur = cur.next;
		}
		return newHead;

	}

	/*
	 * task20
	 *
	 2 Sum Closest
	 Fair
	 Data Structure
	 Find the pair of elements in a given array that sum to a value that is closest to the given target number. Return the values of the two numbers.

	 Assumptions

	 The given array is not null and has length of at least 2
	 Examples

	 A = {1, 4, 7, 13}, target = 7, closest pair is 1 + 7 = 8, return [1, 7].
	 */
	 public List<Integer> task20_closest(int[] array, int target) {
		    // write your solution here
		    ArrayList<Integer> result = new ArrayList<Integer>();
		    if (array == null || array.length < 2) {
		      return result;
		    }
		    Arrays.sort(array);
		    int minDiff = Integer.MAX_VALUE;
		    int i = 0, j = array.length - 1;
		    int rev1 = 0, rev2 = 0;
		    while(i < j) {
		      int curSum = array[i] + array[j];
		      if (Math.abs(target - curSum) < minDiff) {
		        minDiff = Math.abs(target - curSum);
		        rev1 = array[i];
		        rev2 = array[j];
		      } 
		      
		      
		      if (curSum < target) {
		        i ++;
		      } else {
		        j --;
		      }
		    }
		    result.add(rev1);
		    result.add(rev2);
		    return result;
		    
		  }
	 
	 /*
	  * task21
	 2 Sum Smaller
	 Fair
	 Data Structure
	 Determine the number of pairs of elements in a given array that sum to a value smaller than the given target number.

	 Assumptions

	 The given array is not null and has length of at least 2
	 Examples

	 A = {1, 2, 2, 4, 7}, target = 7, number of pairs is 6({1,2}, {1, 2}, {1, 4}, {2, 2}, {2, 4}, {2, 4})
	*/
	 
	 public int task21_smallerPairs(int[] array, int target) {
		    // write your solution here
		    if (array == null || array.length == 0) {
					return 0;
				}
				Arrays.sort(array);
				int count = 0;
				
				int j = array.length - 1;
				while(j > 0) {
					int temp = target - array[j];
					int start = 0, end = j - 1;
					// do binary search in a[start..end], find the smallerlargest element's index
					int index = getSmallerLargest(array, start, end, temp);
					if (index != -1) {
						count += (index + 1);
					}
					j --;
				}
				return count;
		    
		  }
		  
		  public int getSmallerLargest(int[] a, int start, int end, int target) {
				if (target <= a[start]) {
					return -1;
				}
				int left = start; 
				int right = end;
				
				while(left + 1 < right) {
					int mid = left + (right - left)/2;
					if(a[mid] == target) {
						right = mid;
					} else if (a[mid] < target) {
						left = mid;
					} else {
						//a[mid] > target
						right = mid;
					}
				}
				if (a[right] < target) {
					return right;
				} else {
					return left;
				}
				
			}
		  
	 /*
	  * task22
	  
		  2 Sum 2 Arrays
		  Fair
		  Data Structure
		  Given two arrays A and B, determine whether or not there exists a pair of elements, one drawn from each array, that sums to the given target number.

		  Assumptions

		  The two given arrays are not null and have length of at least 1
		  Examples

		  A = {3, 1, 5}, B = {2, 8}, target = 7, return true(pick 5 from A and pick 2 from B)

		  A = {1, 3, 5}, B = {2, 8}, target = 6, return false
		  
		  */
		  
	public boolean task22_existSum(int[] a, int[] b, int target) {
		// write your solution here
		// write your solution here
		if (a == null || a.length == 0 || b == null || b.length == 0) {
			return false;
		}
		// we assume that a.length > b.length
		if (a.length < b.length) {
			return task22_existSum(b, a, target);
		}

		HashSet<Integer> set = new HashSet<Integer>();

		for (Integer i : b) {
			set.add(i);
		}

		for (Integer i : a) {
			if (set.contains(target - i)) {
				return true;
			}
		}

		return false;

	}
	
	/*
	 * task23
	 
	3 Sum 3 Arrays
	Fair
	Data Structure
	Given three arrays, determine if a set can be made by picking one element from each array that sums to the given target number.

	Assumptions

	The three given arrays are not null and have length of at least 1
	Examples

	A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)
	*/
	
	public boolean task23_exist(int[] a, int[] b, int[] c, int target) {
		// write your solution here
		if (a == null || a.length == 0 || b == null || b.length == 0
				|| c == null || c.length == 0) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			if (task23_6_existSum(b, c, target - a[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean task23_6_existSum(int[] a, int[] b, int target) {
		// write your solution here
		if (a == null || a.length == 0 || b == null || b.length == 0) {
			return false;
		}
		// we assume that a.length > b.length
		if (a.length < b.length) {
			return task23_6_existSum(b, a, target);
		}
		HashSet<Integer> set = new HashSet<Integer>();
		for (Integer i : b) {
			set.add(i);
		}

		for (Integer i : a) {
			if (set.contains(target - i)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * task24
	Kth Smallest With Only 2, 3 As Factors
	Fair
	Data Structure
	Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, x and y are all integers.

	Assumptions

	K >= 1
	Examples

	the smallest is 1
	the 2nd smallest is 2
	the 3rd smallest is 3
	the 5th smallest is 2 * 3 = 6
	the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
	*/
	public int task24_kth(int k) {
		// write your solution here
		if (k < 1) {
			return -1;
		}

		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		minHeap.add(1);
		visited.add(1);

		while (k > 1) {
			int current = minHeap.poll();
			if (!visited.contains(current * 2)) {
				visited.add(current * 2);
				minHeap.add(current * 2);
			}
			if (!visited.contains(current * 3)) {
				visited.add(current * 3);
				minHeap.add(current * 3);
			}
			k--;
		}
		return minHeap.peek();
	}
	
	/*
	 * task25
	 
	Place To Put The Chair II
	Fair
	Data Structure
	Given a gym with k pieces of equipment without any obstacles.  Let’s say we bought a chair and wanted to put this chair into the gym such that the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ' ' denotes a cell without equipment. The cost of moving from one cell to its neighbor(left, right, up, down) is 1. You can put chair on any cell in the gym.

	Assumptions

	There is at least one equipment in the gym
	The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
	​Examples

	{ { 'E', ' ', ' ' },

	  {  ' ', 'E',  ' ' },

	  {  ' ',  ' ', 'E' } }

	we should put the chair at (1, 1), so that the sum of cost from the chair to the two equipments is 2 + 0 + 2 = 4, which is minimal.

	 */
	
	
	/*
	 * task26
	Largest Container
	Fair
	Data Structure
	Given an array of non-negative integers, each of them representing the height of a board perpendicular to the horizontal line, the distance between any two adjacent boards is 1. Consider selecting two boards such that together with the horizontal line they form a container. Find the volume of the largest such container.

	Assumptions

	The given array is not null and has size of at least 2
	Examples

	{ 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards of height 2, the volume of the container is 2 * 4 = 8.
	*/
	
	public int task26_largest(int[] array) {
	    // write your solution here
	    if (array == null || array.length == 0) {
		      return 0;
		    }
		    int max = Integer.MIN_VALUE;
		    int n = array.length;
		    int left = 0;
		    int right = n-1;
		    
		    while(left < right) {
		      int curWater = Math.min(array[left],array[right]) * (right - left);
		      
		      if (curWater > max) {
		        max = curWater;
		      }
		      if (array[left] < array[right]) {
		        left ++;
		      } else {
		        right --;
		      }
		    }
		    return max;
	  }
	
	/*
	 * task27
	Majority Number II
	Fair
	Data Structure
	Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.

	Assumptions

	The given array is not null
	Examples

	A = {1, 2, 1, 2, 1}, return [1, 2]
	A = {1, 2, 1, 2, 3, 3, 1}, return [1]
	A = {1, 2, 2, 3, 1, 3}, return []
	*/
	
	public List<Integer> task27_majority(int[] array) {
	    // write your solution here
	    List<Integer> result = new ArrayList<Integer>();
			if (array == null || array.length == 0) {
				return result;
			}
			
			int candidate1 = Integer.MAX_VALUE;
			int candidate2 = Integer.MAX_VALUE;
			int count1 = 0;
			int count2 = 0;
			
			for(int i = 0; i < array.length; ++ i) {
				if (candidate1 == array[i]) {
					count1 ++;
				} else if (candidate2 == array[i]) {
					count2 ++;
				} else if (count1 == 0) {
					candidate1 = array[i];
					count1 = 1;
				} else if (count2 == 0) {
					candidate2 = array[i];
					count2 = 1;
				} else {
					count1 --;
					count2 --;
				}
			}
			
			count1 = getCount(array, candidate1);
			count2 = getCount(array, candidate2);
			
		  if (count1 > array.length/3 && count2 > array.length / 3) {
				result.add(Math.min(candidate1, candidate2));
				result.add(Math.max(candidate1, candidate2));
			} else if (count1 > array.length/3) {
				result.add(candidate1);
			} else if (count2 > array.length/3) {
				result.add(candidate2);
			}
			
			return result;
	  }
	  
	  public int getCount(int[] array, int target) {
			int count = 0;
			for(int i = 0; i < array.length; i ++) {
				if (array[i] == target) {
					count ++;
				}
			}
			return count;
		}
	  
	  /*
	   * task28
	   
	  
	  Reconstruct Binary Search Tree With Preorder Traversal
	  Fair
	  Data Structure
	  Given the preorder traversal sequence of a binary search tree, reconstruct the original tree.

	  Assumptions

	  The given sequence is not null
	  There are no duplicate keys in the binary search tree
	  Examples

	  preorder traversal = {5, 3, 1, 4, 8, 11}

	  The corresponding binary search tree is

	          5

	        /    \

	      3        8

	    /   \        \

	  1      4        11

	  How is the binary tree represented?

	  We use the pre order traversal sequence with a special symbol "#" denoting the null node.

	  For Example:

	  The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

	      1

	    /   \

	   2     3

	        /

	      4
	*/
	   
	public TreeNode task28_reconstruct(int[] pre) {
		// Write your solution here.
		return null;
	}
	
	
	 /*
	  * task29
	
	Reconstruct Binary Search Tree With Level Order Traversal
	Fair
	Data Structure
	Given the levelorder traversal sequence of a binary search tree, reconstruct the original tree.

	Assumptions

	The given sequence is not null
	There are no duplicate keys in the binary search tree
	Examples

	levelorder traversal = {5, 3, 8, 1, 4, 11}

	the corresponding binary search tree is

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	How is the binary tree represented?

	We use the pre order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	
	*/
	 public TreeNode task29_reconstruct(int[] level) {
		    // Write your solution here.
		    return null;
		  }
	 
	 /*
	  * task30
	  *
	 Disjoint White Objects
	 Fair
	 Data Structure
	 In a 2D black image there are some disjoint white objects with arbitrary shapes, find the number of disjoint white objects in an efficient way.

	 By disjoint, it means there is no white pixels that can connect the two objects, there are four directions to move to a neighbor pixel (left, right, up, down).

	 Black is represented by 1’s and white is represented by 0’s.

	 Assumptions

	 The given image is represented by a integer matrix and all the values in the matrix are 0 or 1
	 The given matrix is not null
	 Examples

	 the given image is

	     0  0  0  1

	     1  0  1  1

	     1  1  0  0

	     0  1  0  0

	 there are 3 disjoint white objects.
	 */
	 public int task30_whiteObjects(int[][] matrix) {
		    // write your solution here
		    return 0;
	 }
}

package lai_online2;
import java.util.*;
public class Class25 {

	/*
	 * task1
	 *
	Longest Common Substring
	Fair
	DP
	Find the longest common substring of two given strings.

	Assumptions

	The two given strings are not null
	Examples

	S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”

	*/
	
	/*
	 * M[i][j] the length of longest common substring of the first i chars in string one
	 * and first j chars in string two
	 * 
	 * Base case: M[0][0] = 0;
	 *            M[i][0] = 0; M[0][j] = 0;
	 * Induction rule:
	 * 			  if s[i-1] == t[j - 1], M[i][j] = M[i-1][j-1] + 1  
	 * 					if maxLen < M[i][j], maxLen = M[i][j], lEnd = i - 1
	 * 			  else M[i][j] = 0;  
	 */
	public String task1_longestCommon(String s, String t) {
		// write your solution here
		// write your solution here
		if (s == null || t == null) {
			return null;
		}
		int sLen = s.length();
		int tLen = t.length();
		int[][] M = new int[sLen + 1][tLen + 1];

		int maxLen = 0;
		int maxEndInS = -1;
		M[0][0] = 0;
		for (int i = 1; i <= sLen; i++) {
			M[i][0] = 0;
		}
		for (int j = 1; j <= tLen; j++) {
			M[0][j] = 0;
		}

		for (int i = 1; i <= sLen; i++) {
			for (int j = 1; j < tLen; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1] + 1;
					if (maxLen < M[i][j]) {
						maxLen = M[i][j];
						maxEndInS = i - 1;
					}
				} else {
					M[i][j] = 0;
				}
			}
		}
		int maxStartInS = maxEndInS - maxLen + 1;
		return s.substring(maxStartInS, maxStartInS + maxLen);
	}
	
	
	/*
	 * task2
	 *
	Kth Smallest With Only 3, 5, 7 As Factors
	Fair
	Data Structure
	Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z are all integers.

	Assumptions

	K >= 1
	Examples

	the smallest is 3 * 5 * 7 = 105
	the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
	the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
	the 5th smallest is 3 ^ 3 * 5 * 7 = 945
	*/
	public long task2_kth(int k) {
		// write your solution here
		if (k < 1) {
			return -1;
		}
		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		minHeap.add(3 * 5 * 7);
		visited.add(3 * 5 * 7);
		while (k > 1) {
			int current = minHeap.poll();
			if (!visited.contains(current * 3)) {
				visited.add(current * 3);
				minHeap.add(current * 3);
			}
			if (!visited.contains(current * 5)) {
				visited.add(current * 5);
				minHeap.add(current * 5);
			}
			if (!visited.contains(current * 7)) {
				visited.add(current * 7);
				minHeap.add(current * 7);
			}
			k--;
		}
		return minHeap.peek();
	}
	
	/*
	 * task3
	Kth Closest Point
	Fair
	Data Structure
	Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.

	We are using euclidean distance here.

	Assumptions

	The three given arrays are not null or empty
	K >= 1 and K <= a.length * b.length * c.length
	Return

	a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array
	Examples

	A = {1, 3, 5}, B = {2, 4}, C = {3, 6}

	The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)

	The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
	*/
	
	public int[] task3_closest(final int[] a,final int[] b,final int[] c, int k) {
	    // write your solution here
	    
	    // a, b, c is not null and length >= 1, k >= 1 && k <= a.length * b.length *
	    // c.length
	    PriorityQueue<List<Integer>> minHeap = new PriorityQueue<List<Integer>>(2 * k,
	        new Comparator<List<Integer>>() {
					@Override
					public int compare(List<Integer> o1, List<Integer> o2) {
						long d1 = distance(o1, a, b, c);
						long d2 = distance(o2, a, b, c);
						if (d1 == d2) {
							return 0;
						}
						return d1 < d2 ? -1 : 1;
					}
				});
		HashSet<List<Integer>> visited = new HashSet<List<Integer>>();
		List<Integer> cur = Arrays.asList(0, 0, 0);
		visited.add(cur);
		minHeap.offer(cur);
		while (k > 0) {
			cur = minHeap.poll();
			List<Integer> n = Arrays.asList(cur.get(0) + 1, cur.get(1),
					cur.get(2));
			if (n.get(0) < a.length && visited.add(n)) {
				minHeap.offer(n);
			}
			n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
			if (n.get(1) < b.length && visited.add(n)) {
				minHeap.offer(n);
			}
			n = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
			if (n.get(2) < c.length && visited.add(n)) {
				minHeap.offer(n);
			}
			k--;
		}
		cur.set(0, a[cur.get(0)]);
		cur.set(1, b[cur.get(1)]);
		cur.set(2, c[cur.get(2)]);
		int[] rev = new int[3];
		rev[0] = cur.get(0);
		rev[1] = cur.get(1);
		rev[2] = cur.get(2);
		return rev;

	}

	private long distance(List<Integer> point, int[] a, int[] b, int[] c) {
		long dis = 0;
		dis += a[point.get(0)] * a[point.get(0)];
		dis += b[point.get(1)] * b[point.get(1)];
		dis += c[point.get(2)] * c[point.get(2)];
		return dis;
	}
	
	/*
	 * task4
	Max Water Trapped I
	Fair
	Data Structure
	Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest amount of water that can be trapped in the histogram.

	Assumptions

	The given array is not null
	Examples

	{ 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at index 1, 1 unit of water can be trapped and index 3, 1 unit of water can be trapped)
	*/
	public int task4_maxTrapped(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int i = 0, j = n - 1;
		int leftMax = array[0];
		int rightMax = array[n - 1];
		int sum = 0;
		while (i <= j) {
			leftMax = Math.max(leftMax, array[i]);
			rightMax = Math.max(rightMax, array[j]);
			if (leftMax < rightMax) {
				sum += leftMax - array[i];
				i++;
			} else {
				sum += rightMax - array[j];
				j--;
			}
		}

		return sum;
	}
	/*
	 * task5

	
	Largest Rectangle In Histogram
	Hard
	Data Structure
	Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in the histogram.

	Assumptions

	The given array is not null or empty
	Examples

	{ 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)
 
	 */
	public int task5_largest(int[] array) {
		// write your solution here

		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		LinkedList<Integer> st = new LinkedList<Integer>();
		int maxArea = 0;
		for (int i = 0; i <= n; i++) {
			int cur = i == n ? -1 : array[i];
			while (!st.isEmpty() && cur <= array[st.peek()]) {
				int curHeight = array[st.pop()];
				int curWidth = st.isEmpty() ? i : (i - st.peek() - 1);
				int curArea = curHeight * curWidth;
				maxArea = Math.max(maxArea, curArea);
			}
			st.push(i);
		}
		// System.out.println("maxArea = " + maxArea);
		return maxArea;

	}
	/*
	 * task6
	 
	Place To Put The Chair I
	Hard
	Graph
	Given a gym with k pieces of equipment and some obstacles.  We bought a chair and wanted to put this chair into the gym such that  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, ' ' denotes a cell without any equipment or obstacle. You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle. The cost of moving from one cell to its neighbor is 1. You can not put the chair on a cell with equipment or obstacle.

	Assumptions

	There is at least one equipment in the gym
	The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
	If there does not exist such place to put the chair, just return null (Java) empty vector (C++)
	Examples

	{ { 'E', 'O', ' ' },

	  {  ' ', 'E',  ' ' },

	  {  ' ',  ' ',  ' ' } }

	we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipments is 1 + 1 = 2, which is minimal.
	
	*/
	
	/*
	 * task7
	Longest Common Subsequence
	Fair
	String
	Find the length of longest common subsequence of two given strings.

	Assumptions

	The two given strings are not null
	Examples

	S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.
	*/
	/*
	 * Q5.2
	 * Longest common subsequence 
	 * 
	 * M[i][j] represent length of the longest common subsequence of 
	 *         the first i chars a[0..i-1] in a and the first j chars b[0..j-1]
	 * M[0][0] = 0;
	 * M[i][j] = 
	 * 			if a[i-1] == b[j-1]     M[i-1][j-1] + 1
	 *          else                   max{ M[i-1][j], M[i][j-1] }
	 */
  public int task7_longest(String s, String t) {
    // write your solution here
    if (s == null || t == null || s.length() == 0 || t.length() == 0 ) {
      return 0;
    }
    int sLen = s.length();
    int tLen = t.length();
    int[][] M = new int[sLen + 1][tLen + 1];
    
    // initialize 
    for(int i = 0; i < sLen; i ++) {
      M[i][0] = 0;
    }
    for(int j = 0; j < tLen; j ++) {
      M[0][j] = 0;
    }
    
    for(int i = 1; i <= sLen; i ++) {
      for(int j = 1; j <= tLen; j ++) {
        if (s.charAt(i-1) == t.charAt(j-1)) {
          M[i][j] = M[i-1][j-1] + 1;
        } else {
          M[i][j] = Math.max(M[i-1][j], M[i][j-1]);
        }
      }
    }
    return M[sLen][tLen];
  }
	
  /*
   * task8
  Largest Product Of Length
  Hard
  String
  Given a dictionary containing many words, find the largest product of two words’ lengths, such that the two words do not share any common characters.

  Assumptions

  The words only contains characters of 'a' to 'z'
  The dictionary is not null and does not contains null string, and has at least two strings
  If there is no such pair of words, just return 0
  Examples

  dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)
  */
	public int task8_largestProduct(String[] dict) {
		// write your solution here.
		if (dict == null || dict.length < 2) {
			return 0;
		}
		Comparator<String> myComp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o2.length() - o1.length();
			}
		};

		Comparator<Element> myComp2 = new Comparator<Element>() {

			@Override
			public int compare(Element o1, Element o2) {
				// TODO Auto-generated method stub
				return o2.product - o1.product;
			}
		};

		Arrays.sort(dict, myComp);
		for (int i = 0; i < dict.length; i++) {
			System.out.println(dict[i]);
		}

		PriorityQueue<Element> heap = new PriorityQueue<Element>(
				2 * dict.length, myComp2);
		int x = 0;
		int y = 1;
		int curProduct = dict[x].length() * dict[y].length();
		heap.add(new Element(curProduct, x, y));

		while (!heap.isEmpty()) {
			Element cur = heap.poll();
			x = cur.pair.x;
			y = cur.pair.y;
			String str_x = dict[x];
			String str_y = dict[y];

			if (x != y && checkTwoWords(str_x, str_y)) {
				int curMaxProduct = str_x.length() * str_y.length();

				return curMaxProduct;
			}

			// generate cur.x - 1, cur.y and cur.x, cur.y - 1
			if (x + 1 < dict.length) {
				int x_len = dict[x + 1].length();
				int y_len = dict[y].length();
				heap.add(new Element(x_len * y_len, x + 1, y));
			}
			if (y + 1 < dict.length) {
				int x_len = dict[x].length();
				int y_len = dict[y + 1].length();
				heap.add(new Element(x_len * y_len, x, y + 1));
			}
		}
		return 0;
	}

	public boolean checkTwoWords(String w1, String w2) {
		int[] map = new int[256];
		for (int i = 0; i < w1.length(); i++) {
			map[w1.charAt(i)]++;
		}

		for (int i = 0; i < w2.length(); i++) {
			if (map[w2.charAt(i)] != 0) {
				return false;
			}
		}
		return true;
	}

	public class Element {
		public int product;
		public Pair pair;

		public Element(int p, int x, int y) {
			// TODO Auto-generated constructor stub
			this.product = p;
			this.pair = new Pair(x, y);
		}
	}

	public class Pair {
		public int x;
		public int y;

		public Pair(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
}

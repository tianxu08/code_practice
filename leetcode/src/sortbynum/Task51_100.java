package sortbynum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tag_math.Permutation;
import ds.*;

public class Task51_100 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test51();
		// test51_1();
		// test58();
//		 test60();
//		test61();
//		test65();
//		test68();
		test69();
//		test71();
//		test76();
//		test77();
//		test79();
//		test82();
//		test84();
//		test85();
//		test87();
//		test89();
//		test91();
//		test91_2();
//		test92();
//		test93();
//		test96();
//		test98();
	}

	/*
	 * 51 N-Queens
	 */

	public static void test51() {
		int n = 4;
		List<List<String>> result = task51_solveNQueens(n);
		System.out.println(result);
	}

	public static List<List<String>> task51_solveNQueens(int n) {
		List<List<String>> result = new ArrayList<List<String>>();
		ArrayList<Integer> line = new ArrayList<Integer>();

		task51_helper(0, n, line, result);
		return result;
	}
	
	public static void task51_helper(int index, int n, ArrayList<Integer> line,
			List<List<String>> result) {
		// base case
		if (index == n) {
			// get a valid solution.

			List<String> oneResult = task51_getSolution(line);

			result.add(oneResult);
			return;
		}
		System.out.println(line);
		for (int i = 0; i < n; i++) {
			line.add(i);
			if (task51_checkValid(line)) {

				task51_helper(index + 1, n, line, result);
			}
			line.remove(line.size() - 1);
		}

	}

	// check whether it is valid after add the last element
	public static void test51_1() {
		int[] a = { 1, 3, 0 };
		ArrayList<Integer> line = new ArrayList<Integer>();
		for (Integer i : a) {
			line.add(i);
		}

		System.out.println(line);
		boolean check = task51_checkValid(line);
		System.out.println(check);
	}

	public static boolean task51_checkValid(ArrayList<Integer> line) {
		if (line.size() == 1) {
			return true;
		}
		int lastRow = line.size() - 1;
		int lastCol = line.get(line.size() - 1);

		for (int row = 0; row < line.size() - 1; row++) {

			int col = line.get(row);

			// row(no need to compare), column, diag, counter diag
			if (col == lastCol || row - col == lastRow - lastCol
					|| row + col == lastRow + lastCol) {

				return false;
			}

		}
		return true;
	}

	// line {1, 3, 0, 2}
	public static List<String> task51_getSolution(ArrayList<Integer> line) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < line.size(); i++) {
			StringBuilder stb = new StringBuilder();
			int QIndex = line.get(i);
			for (int j = 0; j < line.size(); j++) {
				if (j == QIndex) {
					stb.append("Q");
				} else {
					stb.append(".");
				}
			}
			result.add(stb.toString());
		}
		System.out.println(result);
		return result;
	}

	/*
	 * 52 N-Queens2
	 * 
	 * simpler than 51
	 */

	/*
	 * 53 Maxinum Subarray
	 */
	public static int task53_maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE;
		int currentSum = 0;

		for (int i = 0; i < nums.length; i++) {
			if (currentSum < 0) {
				currentSum = 0;
			}
			currentSum += nums[i];

			max = Math.max(max, currentSum);
		}

		return max;
	}

	/*
	 * 54 Spiral Matrix
	 */
	public static List<Integer> task54_spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return result;
		}

		int upBound = 0, downBound = matrix.length - 1;
		int leftBound = 0, rightBound = matrix[0].length - 1;
		while (true) {
			// left -> right
			for (int j = leftBound; j <= rightBound; j++) {
				result.add(matrix[upBound][j]);
			}
			upBound++;
			if (upBound > downBound)
				break;

			// up -> down
			for (int i = upBound; i <= downBound; i++) {
				result.add(matrix[i][rightBound]);
			}
			rightBound--;
			if (leftBound > rightBound)
				break;

			// right -> left
			for (int j = rightBound; j >= leftBound; j--) {
				result.add(matrix[downBound][j]);
			}
			downBound--;
			if (upBound > downBound)
				break;
			// down -> up

			for (int i = downBound; i >= upBound; i--) {
				result.add(matrix[i][leftBound]);
			}
			leftBound++;
			if (leftBound > rightBound)
				break;

		}

		return result;
	}

	/*
	 * 55 
	 * Jump Game 
	 * 
	 * Given an array of non-negative integers, you are initially
	 * positioned at the first index of the array. Each element in the array
	 * represents your maximum jump length at that position. Determine if you
	 * are able to reach the last index. For example: A = [2,3,1,1,4], return
	 * true. A = [3,2,1,0,4], return false.
	 * 
	 * n = A.length dp[i] position i can reach the end dp[i] = true if (i + A[i]
	 * >= n, out of bound) true if dp[i + A[i]] == true otherwise, false
	 * 
	 * from n - 1 to 0
	 * 
	 * return dp[0]
	 * 
	 */
	
	

	/*
	 * 56 Merge Intervals
	 */
	public static List<Interval> task56_merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0) {
			return intervals;
		}
		// sort the intervals according to the start
		Collections.sort(intervals, myComp);
		
		Interval candidate = intervals.get(0);
		List<Interval> result = new ArrayList<Interval>();
		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			
			// if there is overlap
			if (candidate.end >= current.start) {
				candidate.start = Math.min(candidate.start, current.start);
				candidate.end = Math.max(candidate.end, current.end);
			} else {
				// No overlap
				// last.end < current.start
				// insert current interval into result
				result.add(candidate);
				candidate = current;
			}
		}
		result.add(candidate);
		return result;
	}

	public static Comparator<Interval> myComp = new Comparator<Interval>() {
		@Override
		public int compare(Interval o1, Interval o2) {
			// TODO Auto-generated method stub
			if (o1.start == o2.start) {
				return 0;
			}
			return o1.start < o2.start ? -1 : 1;
		}
	};

	public static class Interval {
		public int start;
		public int end;

		public Interval() {
			start = 0;
			end = 0;
		}

		public Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	/*
	 * 57 Insert Interval
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (newInterval == null || intervals == null) {
			return intervals;
		}
		ArrayList<Interval> result = new ArrayList<Interval>();
		int insertPos = 0;
		for (Interval curInterval : intervals) {
			// curInterval.end < newInterval.start, add curInterval into result
			if (curInterval.end < newInterval.start) { // no overlap
				result.add(curInterval);
				// the insert position ++
				insertPos++;
			} else if (curInterval.start > newInterval.end) {
				// add curInterval into result.
				result.add(curInterval);
			} else {
				// there is overLap, update newInterval
				newInterval.start = Math.min(newInterval.start,
						curInterval.start);
				newInterval.end = Math.max(newInterval.end, curInterval.end);
			}
		}

		result.add(insertPos, newInterval);
		return result;
	}

	/*
	 * 58 Length of Last Word
	 * string
	 */
	public static void test58() {
		String s = "hello world   ";
		System.out.println(lengthOfLastWord(s));
	}

	public static int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int ptr1 = s.length() - 1;
		while (ptr1 >= 0 && s.charAt(ptr1) == ' ') {
			ptr1--;
		}
		if (ptr1 < 0) {
			return 0;
		}
		
		int tail = ptr1;
		while (ptr1 >= 0 && s.charAt(ptr1) != ' ') {
			ptr1--;
		}
		
		return tail - ptr1;
	}

	/*
	 * 59 Spiral MatrixII
	 */
	public static int[][] task59_generateMatrix(int n) {
		int[][] matrix = new int[n][n];

		int leftB = 0, rightB = n - 1;
		int upB = 0, lowB = n - 1;
		int val = 1;
		while (true) {
			// left -> right
			for (int j = leftB; j <= rightB; j++) {
				matrix[upB][j] = val++;
			}
			upB++;
			if (upB > lowB) {
				break;
			}

			// up -> low
			for (int i = upB; i <= lowB; i++) {
				matrix[i][rightB] = val++;
			}
			rightB--;
			if (leftB > rightB) {
				break;
			}

			// right -> left
			for (int j = rightB; j >= leftB; j--) {
				matrix[lowB][j] = val++;
			}
			lowB--;
			if (upB > lowB) {
				break;
			}
			
			// low -> up
			for (int i = lowB; i >= upB; i--) {
				matrix[i][leftB] = val++;
			}
			leftB++;
			if (leftB > rightB) {
				break;
			}
		}
		return matrix;
	}

	/*
	 * 60 Permutation Sequence 
	 * !!! This is New To me 
	 * The set [1,2,3,…,n]
	 * contains a total of n! unique permutations. Given n and k, return the kth permutation sequence.
	 * 
	 * 解答： 
	 * 1. 以某一数字开头的排列有(n-1)! 个。 例如： 123， 132， 以1开头的是 2！个 
	 * 2. 所以第一位数字就可以用 （k-1）/ (n-1)! 来确定 .这里K-1的原因是，序列号我们应从0开始计算，否则在边界时无法计算。
	 * 3.第二位数字。假设前面取余后为m，则第二位数字是 第 m/(n-2)! 个未使用的数字。 
	 * 4.不断重复2，3，取余并且对(n-k)!进行除法，直至计算完毕
	 * 
	 * Time: O(n^2)
	 */
	public static void test60() {
		int n = 4, k = 16;
		System.out.println(test60_getPermutation(n, k));
		int[] nums = {1,2,3,4};
		System.out.println("-------------");
		List<List<Integer>> result = Permutation.permutation1_1(nums);
		for(List<Integer> list: result) {
			System.out.println(list);
		}
	}

	public static String test60_getPermutation(int n, int k) {
		// get (n - 1)!
		int factor = 1;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			factor *= i;
			list.add(i);
		}
		System.out.println(factor);
		System.out.println(k);
		// k-- due to the index from 0 to n - 1
		k--;

		StringBuilder stb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			factor = factor / (n - i); // 
			int curIndex = k / factor;
			k = k % factor;
			System.out.println("curIndex = " + curIndex);
			System.out.println("k = " + k);
			System.out.println("factor = " + factor);
			System.out.println("---------");
			stb.append(list.get(curIndex));

			// remove the used element from list	
			list.remove(curIndex);
		}
		return stb.toString();
	}

	/*
	 * 61 Rotate List Given a list, rotate the list to the right by k places,
	 * where k is non-negative. Given 1->2->3->4->5->NULL and k = 2, return
	 * 4->5->1->2->3->NULL. detail
	 * 
	 * !!! k might be larger than len
	 * so, k = k % len
	 * 
	 * if k == 0, return the original list directly.  
	 */
	public static void test61() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);

		n1.next = n2;
		 n2.next = n3;
		 n3.next = n4;
		 n4.next = n5;
		 n5.next = n6;
		 n6.next = n7;

		ListNode newHead = task61_rotateRight(n1, 0);

		Debug.printList(newHead);

	}

	public static ListNode task61_rotateRight(ListNode head, int k) {
		// get the length of list
		if (head == null) {
			return head;
		}
		// get the length of head
		int len = 0;
		ListNode cur = head;
		ListNode tail = head;
		while (cur != null) {
			tail = cur;
			len++;
			cur = cur.next;
		}
		k = k % len;
		if (k == 0) {
			return head;
		}
		int firstLen = (len - k);
		// get the second head
		cur = head;
		int i = 0;
		while (i < firstLen - 1) {
			cur = cur.next;
			i++;
		}

		ListNode secondStart = cur.next;
		cur.next = null;

		tail.next = head;

		return secondStart;

	}

	/*
	 * 62 Unique paths dp
	 */

	/*
	 * 63 Unique Paths 2 dp
	 */

	/*
	 * 64 Minimum Path Sum dp
	 */

	/*
	 * 65 Valid Number
	 * 算法思路： 排除法：遇到所有非法情况则返回false，扫描完之后返回true 非法情况：
	 * 符号：+、-最多只能出现两次，而且第二次必须出现在e/E后面。 +2e+3(true) -2e+2(true), -2e-4(true)
	 * 而且第一个+/-号之前必须没有数字，第二个比如紧接在e后面，而且后面必须有数字；
	 * e： 最多只能出现一次。而且前后必须有数字； 
	 * .:  最多只能出现一次，而且不能出现在e后面，.如果在字符串最后一位时，前面必须要有数字；
	 */
	
	public static void test65() {
		String s = "    .1e";
		System.out.println(task65_isNumber(s));
	}
	
	public static boolean task65_isNumber(String s) {
		int i = 0, end = s.length() - 1;
		// skip the leading and tail whitespace
		while (i <= end && Character.isWhitespace(s.charAt(i))) {
			i++;
		}
		if (i > end) {
			// all whitespace
			return false;
		}
		while (i <= end && Character.isWhitespace(s.charAt(end))) {
			// i <= end, NOT all whitespace. and if there are white spaces in the end, delete them. 
			end--;
		}
		// skip leading +/-
		if (s.charAt(i) == '+' || s.charAt(i) == '-') {
			i++;
		}
		
		boolean digit = false;
		boolean dot = false;
		boolean exp = false;
		
		/*
		 * if c == digit, let digit = true
		 * else if c == '.', 只能出现一次，并且要在e 前边 。if dot == true or exp == true, return false
		 *      set dot = true;
		 * else if c == 'e', 只能出现一次，并且前边必须有数字（digit) if digit == false or e == true, return false
		 * 		set exp == true; digit == false;
		 * else if c == '+'/'-', 现在只能出现一次， 并且紧跟在e 后边. if s.charAt(i - 1) != 'e', return false;
		 *      
		 * else NOT digit, '.', 'e', '+/-', other char, return false;
		 */
		
		while (i <= end) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				digit = true; // we have met digit, set digit = true
			} else if (c == '.') {
				// . can exist at most once, cannot be after 'e'
				// c is '.'
				if (dot || exp) {
					// if dot is true, which means that we already have a dot. now, this is the second dot. illegal
					// if 
					return false;
				}
				dot = true;
			} else if (c == 'e') { // 46.e3
				// c is 'e' 
				// e must after digit, and 'e' exist and only exist once
				if (digit == false || exp) {
					// the previous is NOT digit or the previous is '.'
					return false;
				}
				exp = true;
				digit = false; // set digit as false, since after e, there must exist an digit, or +1, or -1

			} else if (c == '+' || c == '-') {
				// this cna only after 'e'
				if (s.charAt(i - 1) != 'e') {
					return false;
				}
			} else {
				// is NOT digit, +/-, '.', 'e'
				return false;
			}
			i++;
		}
		return digit;
	}

	public static boolean task65_isNumber2(String s) {
		s = s.trim();

		boolean pointSeen = false;
		boolean eSeen = false;
		boolean numberSeen = false;
		boolean numberAfterE = true;
		for (int i = 0; i < s.length(); i++) {
			if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
				numberSeen = true;
				numberAfterE = true;
			} else if (s.charAt(i) == '.') {
				if (eSeen || pointSeen) {
					return false;
				}
				pointSeen = true;
			} else if (s.charAt(i) == 'e') {
				if (eSeen || !numberSeen) {
					return false;
				}
				numberAfterE = false;
				eSeen = true;
			} else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
				if (i != 0 && s.charAt(i - 1) != 'e') {
					return false;
				}
			} else {
				return false;
			}
		}

		return numberSeen && numberAfterE;
	}
	/*
	 * 66 Plus One
	 * Given a non-negative number represented as an array of digits, plus one to the number.
	 * The digits are stored such that the most significant digit is at the head of the list.
	 *   0 1 2 3
	 *   1 2 3 4  input
	 * +       1  plus 1
	 *   1 2 3 5  output
	 *   
	 *   9 9 9 9 input
	 * +       1 plus 1
	 * 1 0 0 0 0 output 
	 */
	public static int[] task66_plusOne(int[] digits) {
		if (digits == null || digits.length == 0) {
			int[] result = new int[1];
			result[0] = 1;
			return result;
		}
		int len = digits.length;
		int[] result = new int[len + 1];
		int carry = 1; // since we plus one, the first time, carry = 1
		
		for(int i = len - 1; i >= 0; i --) {
			int sum = digits[i] + carry;
			carry = sum / 10;
			result[i + 1] = sum % 10;
		}
		if (carry == 0) {
			return Arrays.copyOfRange(result, 1, result.length);
		} else {
			result[0] = carry;
			return result;
		}
	}
	

	/*
	 * 67 Add Binary
	 * 
	 * 得到结果数组，然后反过来。  这样可以避免最后还有进位的问题。
	 */
	public static String task67_addBinary(String a, String b) {
        if (a == null) {
			return b;
		}
        if (b == null) {
			return a;
		}
        
        char[] result = new char[Math.max(a.length(), b.length()) + 2];
        
        int i = a.length() - 1; // point to a's end
        int j = b.length() - 1; // point to b's end
        int carry = 0;
        int k = 0;
        while(true) {
        	if (i < 0 && j < 0 && carry == 0) {
        		break;
        	}
        	
        	int iVal = (i >= 0) ? a.charAt(i) - '0' : 0;
        	int jVal = (j >= 0) ? b.charAt(j) - '0': 0;
        	
        	if (iVal + jVal + carry > 1) {
				result[k] = (char)('0' + iVal + jVal + carry - 2);
				carry = 1;
			} else {
				result[k] = (char)('0' + iVal + jVal + carry);
				carry = 0;
			}
        	k ++;
        	j --;
        	i --;
        }
        
        String rev = new String(result, 0, k);
        return new StringBuilder(rev).reverse().toString();
            
    }

	/*
	 * 68 Text Justification
	 * 
	 * !!! detail
	 * 
	 * 
	 */
	public static void test68() {
		String[] words = {"a","b","c","d","e"};
		int L = 3;
		List<String> res = task68_fullJustify(words, L);
		
		for (String s: res) {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == ' ') {
					System.out.print('#');
				} else {
					System.out.print(s.charAt(i));
				}
			}
			System.out.println();
		}
		
		System.out.println("-------------------");
		List<String> res2 = task68_fullJustify2(words, L);
		for (String s: res2) {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == ' ') {
					System.out.print('#');
				} else {
					System.out.print(s.charAt(i));
				}
			}
			System.out.println();
		}
	}
	
	public static List<String> task68_fullJustify(String[] words, int L) {
		List<String> result = new ArrayList<String>();
		if (words == null || words.length == 0) {
			return result;
		}
		
		int count = 0; // is the current string length
		int last = 0; // last is the last word we have choose.
		for (int i = 0; i < words.length; i++) {
			// traverse the string array
			if (count + words[i].length() + (i - last) > L) {
				int spaceNum = 0;
				int extraSpaceNum = 0;
				// in this case, we didn't choose the words[i]
				int cur_num_words = i - last; // i - 1 - last + 1;
				int cur_num_intervals = i - last - 1;
				if (cur_num_intervals > 0) {
					// interval's
					spaceNum = (L - count) / (cur_num_intervals);
					extraSpaceNum = (L - count) % (cur_num_intervals);
				}

				StringBuilder st = new StringBuilder();
				// words[last]... words[i- 1], append to one line
				for (int j = last; j <= i - 1; j++) {
					st.append(words[j]);
					// append spaces
					if (j < i - 1) {
						// NOT the last word
						// append the even distributed spaces
						for (int k = 0; k < spaceNum; k++) {
							st.append(" ");
						}
						// append one more spaces if extraSpaceNum > 0
						if (extraSpaceNum > 0) {
							st.append(" ");
						}
						// decrease the extraSpaceNum
						extraSpaceNum--;
					}
				}
				
				// if there is only one world in current line, we need to append spaces if needed.
				for (int j = st.length(); j < L; j++) {
					st.append(" ");
				}
				
				result.add(st.toString());
				count = 0;
				last = i; // the next word
			}
			count += words[i].length();
		}

		// the last line
		StringBuilder str = new StringBuilder();
		for (int i = last; i < words.length; i++) {
			str.append(words[i]);
			if (str.length() < L) {
				// append only one space after each word
				str.append(" ");
			}
		}
		// append more spaces to let the length of str become L
		for (int i = str.length(); i < L; i++) {
			str.append(" ");
		}
		result.add(str.toString());
		return result;
	}
	
	
	
	public static List<String> task68_fullJustify2(String[] words, int maxWidth) {
		List<String> result = new ArrayList<String>();
		if (words == null || words.length == 0) {
			return result;
		}
		
		int curLineCharCount = 0; // not including space
		int curLineStartWordIdx = 0;
		
		for (int i = 0; i < words.length; i++) {
			
			String nextWord = words[i];
			// if with nextWord, the number of interval with 
			
			if ((curLineCharCount + nextWord.length() + i - curLineStartWordIdx) > maxWidth) {
				// we cannot choose nextWord
				// so the words will be word[prevWordIdx]... word[i - 1]
				int curLineIntervalNum = i - curLineStartWordIdx - 1;  
				// the number of words will be i - 1 - prevWordIdx + 1
				int evenDistributedSpaceNum = 0;
				int unevenDistributedSpaceNum = 0;
				
				if (curLineIntervalNum > 0) {
					evenDistributedSpaceNum = (maxWidth - curLineCharCount) / curLineIntervalNum;
					unevenDistributedSpaceNum = (maxWidth - curLineCharCount) % curLineIntervalNum;
				}
			
				StringBuilder curLine = new StringBuilder();
				for (int j = curLineStartWordIdx; j <= i - 1; j++) {
					// append word
					curLine.append(words[j]);
					
					// append spaces
					if (j < i - 1) {
						// append the evenDistributedSpace
						for (int k = 0; k < evenDistributedSpaceNum; k++) {
							curLine.append(" ");
						}	
						// append the unevenDistributedSpace is there is
						if (unevenDistributedSpaceNum > 0) {
							curLine.append(" ");
							unevenDistributedSpaceNum--;
						}
					}	
				}
				
				// if there is only one word in currentLine
				for (int k = curLine.length(); k < maxWidth; k++) {
					curLine.append(" ");
				}
				
				result.add(curLine.toString());
				// update 
				curLineCharCount = 0;
				curLineStartWordIdx = i;  // i points to the next word's 
				
			}
			curLineCharCount += words[i].length();
		}
		
		// for the last line
		StringBuilder lastLine = new StringBuilder();
		for (int i = curLineStartWordIdx; i < words.length; i++) {
			lastLine.append(words[i]);
			if (lastLine.length() < maxWidth) {
				lastLine.append(' ');
			}
			
		}
		// append additional spaces to the end of current line
		for (int i = lastLine.length(); i < maxWidth; i++) {
			lastLine.append(' ');
		}
		
		result.add(lastLine.toString());
		return result;
	}
	
	
	
	/*
	 * 69 Sqrt(x)
	 * 
	 * !!! 牛顿法， 二分法
	 * 二分法 更加好一些
	 * 
	 */
	public static void test69() {
		for(int i = 1; i <= 100; i ++) {
			System.out.println("-------------------");
			System.out.println(i + "  ");
			System.out.println(task69_sqrt2_follow_up((double)i));
			System.out.println(task69_2_follow_up2((double)i));
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
		
		double k = 0.01;
		double r1 = task69_sqrt2_follow_up(k);
		double r2 = task69_2_follow_up2(k);
		System.out.println("r1 = " + r1);
		System.out.println("r2 = " + r2);
	}
	
	// binary search
	public static int task69_sqrt(int x) {
		// special case: x ==  0 !!! this is very important. 
		if (x == 0) {
			return 0;
		}
		long low = 0, high = x;
		while (low + 1 < high) {
			long mid = low + (high - low)/2;
			if (x/mid == mid) {
				return (int)mid;
			} else if (x/mid < mid) { // mid*mid > x, we need a smaller mid
				high = mid;
			} else {
				// mid * mid < x, we need a larger mid
				low = mid;
			}
		}
		
		// 先选大的..
		if (x/high == high) {
			return (int)high;
		} else {
			return (int)low;
		}
	}
	
	/*
	 * follow up
	 * the input could be double, the output should be double. 
	 * e.g 
	 * input 2  
	 * output 1.414215087890625
	 */
	// binary search
	// time: 
	// space: O(1)
	public static double task69_sqrt2_follow_up(double target) {
		if (target == 0 || target == 1) {
			return target;
		}
		// if target < 1 [target, 1]
		// if target > 1 [1, target]
		double left = target < 1 ? target : 1;
		double right = target < 1 ? 1 : target;
		double epsilon = 0.00001;
		while (left <= right) { // !!!
			double mid = left + (right - left) / 2;
			
			if (Math.abs(mid * mid - target) / target < epsilon) {
				// (mid * mid - target)/ target < e
				return mid;
			} else if (mid * mid < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	/**
	 * Newton method
	 * @param target
	 * @return
	 * 
	 * Xn+1 = (Xn + a/Xn)/2
	 * 
	 * https://www.zhihu.com/question/20690553
	 */
	public static double task69_2_follow_up2(double target) {
		double eps = 1e-12;
		double t = target;
		while(Math.abs(t - target/t) > eps * t) {
			t = (t + target/t)/2;
		}
		return t;
	}
	

	/*
	 * 70 Climbing Stairs
	 * 
	 * f(n) = f(n - 1) + f(n - 2)
	 * like fibo number. 
	 */
	
	

	/*
	 * 71 Simplify Path 
	 * !!! Implement
	 * stack  
	 * 
	 */
	public static void test71() {
		String path = "/a/./b/../../c/";
		String result = task71_simplifyPath(path);
		System.out.println(result);
		
	}
	
	
	// there is bug, don't do in this way. the second way is better. 
	public static String task71_simplifyPath(String path) {
        if (path == null || path.length() == 0) {
			return path;
		}
        
        LinkedList<Character> stack = new LinkedList<Character>();
        stack.offerFirst(path.charAt(0));
        int i = 1;
        while(i < path.length()) {
        	char cur = path.charAt(i);
        	char prev = stack.peekFirst();
        	if (cur == '/') {
				if (prev == '/') {
					continue;
				} else {
					stack.offerFirst(cur);
				}
			} else if(cur == '.') {
				// two  ..
				if (i < path.length() - 1 && path.charAt(i + 1) == '.') {
					i ++;
					if (stack.isEmpty()) {
						continue;
					} else {
						stack.pollFirst();
					}
					
					while(!stack.isEmpty() && stack.peekFirst() != '/') {
						stack.pollFirst();
					}
					
				} else {
					// only one '.'
					if (stack.isEmpty()) {
						continue;
					} else {
						stack.pollFirst();
					}
				}
			} else {
				// other characters
				stack.offerFirst(cur);
				
			}
        	i ++;
        }
        
        if (stack.isEmpty()) {
			return "/";
		} else {
			if (stack.peekFirst() == '/') {
				stack.pollFirst();
			}
			StringBuilder stb = new StringBuilder();
			while(!stack.isEmpty()) {
				stb.append(stack.pollFirst());
			}
			
			return stb.reverse().toString();
			
		}
    }
	
	
	public static String task71_simplifyPath2(String path) {
		 if (path == null || path.length() == 0) {
				return path;
			}
			String[] array = path.trim().split("/");
			LinkedList<String> stack = new LinkedList<String>();
			for(int i = 0; i < array.length; i ++) {
				String cur = array[i];
				if (cur == null || cur.length() == 0 || cur.equals(".")) {
					continue;
				} else if (cur.equals("..")) {
					if (!stack.isEmpty()) {
						stack.pollFirst();
					} 
				} else {
					stack.offerFirst(cur);
				}
			}
			
			if (stack.isEmpty()) {
				return "/";
			} else {
				StringBuilder stb = new StringBuilder();
				
				while(!stack.isEmpty()) {
					stb.insert(0,stack.pollFirst());
					stb.insert(0,"/");
				}
				
				return stb.toString();
			}
	}
	
	
	

	/*
	 * 72 Edit Distance dp
	 * 
	 * dp[i][j]  s[0..i - 1] and s2[0..j - 1] the edit distance
	 * s1[i - 1] == s2[j - 1] ==> dp[i][j] = dp[i - 1][j - 1]
	 * else ==> dp[i][j] = max (dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
	 * 
	 * Time: O(n * n)
	 * Space: O(n * n)
	 */
	public int task72_minDistance(String s1, String s2) {
        int s1Len = s1.length();
		int s2Len = s2.length();
		
		int[][] dp = new int[s1Len + 1][s2Len + 1];
		
		//if s2 == null
		for(int i=0; i <= s1Len; i++){
			dp[i][0] = i;
		}
		
		//if s1 == null
		for(int j=0; j<=s2Len; j++){
			dp[0][j] = j;
		}
		
		for(int i=1; i<=s1Len; i++){
			for(int j=1; j<=s2Len; j++){
				if(s1.charAt(i-1) == s2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1];
				}else{
					//it might be replace, insert, or delete
					int insertOrDelete = Math.min(dp[i-1][j], dp[i][j-1]);
					dp[i][j] = Math.min(insertOrDelete, dp[i-1][j-1]) + 1;
				}
			}
		}
		
		return dp[s1Len][s2Len];
    }
	
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 * Time: O(n * n)
	 * Space: O(n)
	 */
	public int task72_minDistance2(String s1, String s2) {
		// Note: The Solution object is instantiated only once and is reused by
		// each test case.
		int s1Len = s1.length();
		int s2Len = s2.length();

		int[] f = new int[s2Len + 1];
		int upperLeft = 0; // used to store dp[i-1][j-1]
		for (int j = 0; j <= s2Len; j++) {
			f[j] = j;
		}

		for (int i = 1; i <= s1Len; i++) {
			upperLeft = f[0];
			f[0] = i;
			
			for (int j = 1; j <= s2Len; j++) {
				int upper = f[j];

				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					f[j] = upperLeft;
				} else {
					f[j] = Math.min(upperLeft, Math.min(f[j], f[j - 1])) + 1;
				}
				upperLeft = upper;
			}
		}
		return f[s2Len];

	}

	/*
	 * 73 Set Matrix 0 first store the row position and col position to set 0
	 * 
	 */

	/*
	 * 74 Search a 2D Matrix 
	 * binary search extension
	 */

	/*
	 * 75 Sort Colors 3 bars.
	 * 
	 */

	/*
	 * 76 Minimum Window Substring
	 * 
	 * Given a string S and a string T, find the minimum window in S 
	 * which will contain all the characters in T in complexity O(n).
	 * For example,
	 * S = "ADOBECODEBANC"
	 * T = "ABC"
	 * Minimum window is "BANC".
	 * 
	 * slide window
	 * 
	 * foundHash  hashMap
	 * dictHash   hashMap
	 * appeared   used to count the number of chars contains in T. 
	 * If appeared == T.length, we have find a solution. 
	 * 
	 */
	
	public static void test76() {
		String S = "ADOBECODEBANC";
		String T = "ABC";
		System.out.println(task76_minWindow(S, T));
	}
	
	public static String task76_minWindow(String S, String T) {
		if (S == null || T == null) {
			return "";
		}
		int[] dictHash = new int[256];
		int[] foundHash = new int[256];
		
		// fill in the dictHash
		for(int i = 0; i < T.length(); i ++) {
			char cur = T.charAt(i);
			dictHash[cur]++;
		}
		
		int appeared = 0;
		int windowStart = 0, windowEnd = 0;
		int minWidth = Integer.MAX_VALUE;
		int minStart = 0;
		
		while(windowEnd < S.length()) {
			char cur = S.charAt(windowEnd);

			// cur is a char in T 
			if (dictHash[cur] > 0) {
				if (foundHash[cur] < dictHash[cur]) {
					appeared ++;  // effective appearance. 
				}
				foundHash[cur]++;
			}
			
			// if appeared == T.length(), we get a window contains all Target char.
			// we need to shrink the window. 
			if (appeared == T.length()) {
				char cur2 = S.charAt(windowStart);
				while(dictHash[cur2] < foundHash[cur2] || foundHash[cur2] == 0) {
					// foundHash[cur2] > dictHash[cur2], for cur2, foundHash has more than in dictHash
					// or the cur2 is NOT in T
					if (dictHash[cur2] < foundHash[cur2]) {
						foundHash[cur2] --;
					}
					windowStart++;
					// update cur2
					cur2 = S.charAt(windowStart);
				}
				
				// get the result
				if (minWidth > windowEnd - windowStart + 1) {
					minWidth = windowEnd - windowStart + 1;
					minStart = windowStart;
				}
				
			}
			windowEnd ++;
		}
		
		if (minWidth == Integer.MAX_VALUE) {
			return "";
		} else {
			return S.substring(minStart, minStart + minWidth);
		}
	}
	
	

	/*
	 * 77 Combinations dfs backtrackings
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	 * 
	 * n = 4, k = 2
	 * sln = 
	 * 2,4
	 * 3,4
	 * 2,3
	 * 1,2
	 * 1,3
	 * 1,4
	 */
	public static void test77() {
		int n = 4;
		int k = 2;
		List<List<Integer>> result = task77_combine(n, k);
		System.out.println(result);
	}
	
	 public static List<List<Integer>> task77_combine(int n, int k) {
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 ArrayList<Integer> line = new ArrayList<Integer>();
		 task77_helper(result, 0, line, n, k);
		 return result;
	 }
	 
	 public static void task77_helper(List<List<Integer>> result, int index, ArrayList<Integer> line, int n, int k) {
		 if (line.size() == k) {
			// we get a solution. 
			result.add(new ArrayList<Integer>(line));
			return;
		}
		
		for(int i = index + 1; i <= n; i ++) {
			line.add(i);
			task77_helper(result, i , line, n, k);
			line.remove(line.size() - 1);
		}
	 }
	
	

	/*
	 * 78 Subset 
	 * dfs backtrackings
	 */
	public static List<List<Integer>> task78_subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> line = new ArrayList<Integer>();
		task78_helper(result, nums, 0, line);
		return result;
	}

	public static void task78_helper(List<List<Integer>> result, int[] nums,
			int index, ArrayList<Integer> line) {
		if (index == nums.length) {
			result.add(new ArrayList<Integer>(line));
			return;
		}
		
		
		
		// choose nums[index]
		line.add(nums[index]);
		task78_helper(result, nums, index + 1, line);
		line.remove(line.size() - 1);
		
		// don't choose nums[index]
	   task78_helper(result, nums, index + 1, line);
	}
	 

	/*
	 * 79 Word Search dfs
	 * Given a 2D board and a word, find if the word exists in the grid.
	 * The word can be constructed from letters of sequentially adjacent cell, 
	 * where "adjacent" cells are those horizontally or vertically neighboring. 
	 * The same letter cell may not be used more than once.
	 * 
	 * Given board =
	 * [
	 *  ["ABCE"],
	 *  ["SFCS"],
	 *  ["ADEE"]
	 * ]
	 * word = "ABCCED", -> returns true,
	 * word = "SEE", -> returns true,
	 * word = "ABCB", -> returns false.
	 */
	public static void test79() {
		char[][] board = {
				"ABCE".toCharArray(),
				"SFCS".toCharArray(),
				"ADEE".toCharArray()
		};
		
		System.out.println(task79_exist(board, "ABCCED"));
		System.out.println(task79_exist(board, "SEE"));
		System.out.println(task79_exist(board, "ABCB"));
	}
	
	public static boolean task79_exist(char[][] board, String word) {
		int rLen = board.length;
		int cLen = board[0].length;
	
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				boolean[][] visited = new boolean[rLen][cLen];
				if (board[i][j] == word.charAt(0)) {
					if (task79_find(board, word, 0, visited, i, j)) {
						return true;
					}
				}
				
			}
		}
		return false;
	}
	public static boolean task79_find(char[][] board, String word, int index, boolean[][] visited, int i , int j) {
		if (index == word.length()) {
			return true;
		}
		// check if i and j are valid
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
				board[i][j] != word.charAt(index) || visited[i][j] == true) {
			return false;
		}
		
		boolean rev = false;
		visited[i][j] = true;
		for(int k = 0; k < 4; k ++) {
			rev = rev ||  task79_find(board, word, index + 1, visited, i + dx[k], j + dy[k]);
		}
			
		visited[i][j] = false;
		
		return rev;
	}
	
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	
	/*
	 * 80 Remove Duplicates from Sort ArrayII
	 * Remove Duplicates from Sorted Array II 
	 * Given sorted array nums = [1,1,1,2,2,3],
	 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
	 * It doesn't matter what you leave beyond the new length.
	 * 0 1 2 3 4 5
	 * 1 1 1 2 2 3
	 *   s 
	 * 
	 * s, f
	 * [0, s ) processed
	 * [s, f]) useless
	 * [f, n - 1] to explore
	 * 
	 * init: s = 2, f = 2
	 * array[f] == array[s -2]  f ++
	 * array[f] != array[s - 2] array[s] = array[f], s ++, f ++;
 	 */
	
	public static int task80_removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return nums.length;
        }
        int s = 2, f = 2;
        for(; f < nums.length; f ++) {
            if (nums[f] != nums[s - 2]) {
                nums[s] = nums[f];
                s ++;
            }
        }
        
        return s;
    }
	
	
	

	/*
	 * 81 Search in Rotated Array2
	 * worst case: O(n)
	 */

	/*
	 * 82 Remove Duplicates from Sorted List 2 
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, 
	 * leaving only distinct numbers from the original list.
	 * For example,
	 * Given 1->2->3->3->4->4->5, return 1->2->5.
	 * Given 1->1->1->2->3, return 2->3.
	 */
	public static void test82() {
		int[] array = {1,2,3,3,4,4,5};
		ListNode n1 = Debug.createList(array);
		Debug.printList(n1);
		
		ListNode nn1 = task82_deleteDuplicates(n1);
		Debug.printList(nn1);
	}
	
	public static ListNode task82_deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		boolean flag = false; // show that current candidate(slow) has duplicates or not 
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode tail = dummy;
		
		ListNode slow = head, fast = head.next;
		// slow is the candidate
		
		while(fast != null) {
			System.out.println("fast.val = " + fast.val);
			if (fast.val == slow.val) {
				flag = true;
			} else {
				// fast.val != slow.val
				if (flag == false) {
					// the current candidate doesn't have duplicate
					// link the candidate(slow) to tail. 
					slow.next = null;
					tail.next = slow;
					tail = tail.next;
					
					// update candidate(slow)
					slow = fast;
				} else {
					// flag == true
					// update the candidate, and set flag as false
					slow = fast;
					flag = false;
				}
			}
			// update fast
			fast = fast.next;
		}
		
		if (flag == false && slow != null) {
			slow.next =null;
			tail.next = slow;
		}
		
		return dummy.next;

	}
	
	// this one runs faster.
	public static ListNode task82_deleteDuplicates_jz(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;

		while (head.next != null && head.next.next != null) {
			if (head.next.val == head.next.next.val) {
				int val = head.next.val;
				while (head.next != null && head.next.val == val) {
					head.next = head.next.next;
				}
			} else {
				head = head.next;
			}
		}

		return dummy.next;
	}
	

	/*
	 * 83 Remove Duplicates from Sorted List 
	 * !!! Implement
	 */
	public static ListNode task83_deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode tail = dummy;
		ListNode slow = head, fast = head;
		while(fast != null) {
			while(fast != null && fast.val == slow.val) {
				fast = fast.next;
			}
			// here ,fast.val != slow.val
			// add slow to tail. 
			slow.next = null;
			tail.next = slow;
			tail = tail.next;
			
			// update slow;
			slow = fast;
		}
		
		return dummy.next;
	}

	/*
	 * 84 Largest Rectangle in Histogram use stack. 
	 * 
	 */
	public static void test84() {
		int[] height = {2,1,2};
		int rev = task84_largestRectangleArea(height);
		System.out.println("rev = " + rev);
	}
	
	public static int task84_largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		LinkedList<Integer> stack = new LinkedList<Integer>();
		int i = 0;
		int maxArea = 0;
		while (i <= height.length) {
			int cur = i == height.length ? -1 : height[i];
			while (!stack.isEmpty() && cur < height[stack.peekFirst()]) {
				int h = height[stack.pollFirst()];
				int w = stack.isEmpty() ? i : i - stack.peekFirst() - 1;
				int area = h * w;
				maxArea = Math.max(maxArea, area);
			}
			stack.offerFirst(i);
			i++;
		}
		return maxArea;
	}
	/*
	 * 85 Maximal Rectangle
	 * 
	 * follow up for 84 largest Rectangle in Histogram.
	 * 
	 * for every row, get the height based on this row. 
	 * like 
	 * 1 0 1 1
	 * 0 1 1 0
	 * 0 1 1 1
	 * 1 1 1 1
	 * 
	 * height matrix is
	 * 1 0 1 1
	 * 0 1 2 0
	 * 0 2 3 1
	 * 1 3 4 2
	 * 
	 * for every row, call the get largest rectangle in histogram. 
	 * 
	 * Time: O(n^2)
	 * 
	 */
	public static void test85() {
		char[][] matrix = {
				"1011".toCharArray(),
				"0110".toCharArray(),
				"0111".toCharArray(),
				"1111".toCharArray()
		};
		int rev = task85_maximalRectangle(matrix);
		System.out.println("rev = " + rev);
	}
	
	public static int task85_maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
        int rLen = matrix.length;
        int cLen = matrix[0].length;
        int[][] height = new int[rLen][cLen];
        for(int j = 0; j < cLen; j ++) {
        	for(int i = 0; i < rLen; i ++) {
        		if (i == 0 || matrix[i][j] == '0') {
					height[i][j] = matrix[i][j] -'0';
				} else {
					if (matrix[i - 1][j] == '0') {
						height[i][j] = matrix[i][j] - '0';
					} else {
						height[i][j] = height[i - 1][j] + (matrix[i][j] - '0');
					}
				}
        	}
        }
        
        
        // debug
        Debug.printMatrix(height);
        
        int maxArea = 0;
        for(int i = 0; i < rLen; i ++) {
        	int curArea = task84_largestRectangleArea(height[i]);
        	maxArea = Math.max(maxArea, curArea);
        }
        
        return maxArea;
    }
	

	/*
	 * 86 Partition List 
	 * easy
	 */
	

	/*
	 * 87 Scramble String
	 * 
	 * This is new to me
	 * 3d dp
	 * 
	 * basic idea;
	 * 简单的说，就是s1和s2是scramble的话，那么必然存在一个在s1上的长度l1，将s1分成s11和s12两段，同样有s21和s22。
	 * 那么要么s11和s21是scramble的并且s12和s22是scramble的；
	 * 要么s11和s22是scramble的并且s12和s21是scramble的。
	 * 先用递归写了一个算法，但是大集合不过，然后用三维动态规划才搞定
	 */
	public static void test87() {
		String s1 = "rgeat";
		String s2 = "great";
		boolean rev = task87_isScramble(s1, s2);
		System.out.println("rev = " + rev);
		boolean rev2 = task87_isScramble_dp(s1, s2);
		System.out.println("rev2 = " + rev2);
	}
	
	
	public static boolean task87_isScramble(String s1, String s2) {
		// base case
		if ( s1.length() != s2.length()) {
			return false;
		}
		
		if (s1.length() == 0 || s1.equals(s2)) {
			return true;
		}
		
		if (!task87_sortString(s1).equals(task87_sortString(s2))) {
			return false;
		}
		
		for(int i = 1; i < s1.length(); i ++) {
			String s11 = s1.substring(0, i);
			String s12 = s1.substring(i);
			
			String s21 = s2.substring(0, i);
			String s22 = s2.substring(i);
			
			String s31 = s2.substring(0, s2.length() - i); // length = s2.length - i;
			String s32 = s2.substring(s2.length() - i);  // length = i
			
			
			//!!! Note: the same length to compare. 
			if ((task87_isScramble(s11, s21) && task87_isScramble(s12, s22))||
					(task87_isScramble(s11, s32) && task87_isScramble(s12, s31)) ) {
				return true;
			}
		}
		
		return false;
	}
	
	private static String task87_sortString(String s) {
		char[] array = s.toCharArray();
		Arrays.sort(array);
		return new String(array);
	}
	
	
	/* 
	 * 我们提出维护量f[i][j][n]，
	 * 其中i是s1的起始字符，j是s2的起始字符，
	 * 而n是当前的字符串长度，f[i][j][len]表示的是以i和j分别为s1和s2起点的长度为len的字符串是不是互为scramble。
	 * 
	 * f[i][j][len] ||= 
	 * 
	 * f[i][j][k] && f[i + k][j + k][len - k] ||
	 * s1[i..i + k - 1] with s2[j..j + k - 1]  length == k
	 * s1[i + k..i + len - 1] with s2[j.. j + len - 1] length == len - k
	 * f[i][j + len- k][k] && f[i + k][j][len - k]
	 * s1[i..i + k - 1] with s2[j + len - k.. j + len - 1]  length == k
	 * s1[i + k..i + len - 1] with s2[j.. j + len - k - 1]  length == len - k
	 * 
	 * 
	 */
	
	public static boolean task87_isScramble_dp(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		if (s1.length() == 0 || s1.equals(s2)) {
			return true;
		}
		
		int len = s1.length();
		boolean[][][] f = new boolean[len][len][len + 1];
		
		for(int subLen = 1; subLen <= len; subLen ++) {
			for(int i = 0; i <= len - subLen; i ++) {
				for(int j = 0; j <= len - subLen; j ++) {
					if (subLen == 1) {
						f[i][j][subLen] = s1.charAt(i) == s2.charAt(j);
						continue;
					}	
					f[i][j][subLen] = false;
					for(int k = 1; k <= subLen - 1; k ++) {
						if ((f[i][j][k] && f[i + k][j + k][subLen - k]) || 
							 (f[i][j + subLen - k][k] && f[i + k][j][subLen - k])) {
							f[i][j][subLen] = true;
							break;
						}
					}
				}
			}
		}
		
		return f[0][0][len];
	}
	
	

	/*
	 * 88 Merge Sorted Array merge sort. 
	 * easy
	 */

	/*
	 * 89 Gray Code Impelment
	 * !!!! Do later. 
	 * 格雷码
	 */
	public static void test89() {
		int n = 3;
		List<Integer> rev2 = task89_grayCode(n);
		System.out.println(rev2);
	}
	
	
	public static List<Integer> task89_grayCode(int n) {
		if (n < 0) {
			return new ArrayList<Integer>();
		}
		if (n == 0) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(0);
			return list;
		}
		List<Integer> prev = task89_grayCode(n - 1);
		// copy the prev as the first part
		List<Integer> cur = new ArrayList<Integer>(prev);
		// reversely add 1 to the front of num in prev
		for(int i = prev.size() - 1; i >= 0; i --) {
			int mask = 1 << (n - 1);
			int num = prev.get(i) | mask;
			cur.add(num);
		}
		return cur;
	}
	
	
	/*
	 * 90 Subsets 2 !!!
	 * 
	 */
	 public static List<List<Integer>> subsetsWithDup(int[] nums) {
	        Arrays.sort(nums);
		 	ArrayList<Integer> line = new ArrayList<Integer>();
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        task90_helper(nums, 0, line, result);
	        return result;
	 }
	 
	 public static void task90_helper(int[] nums, int index, ArrayList<Integer> line, 
			  List<List<Integer>> result) {
		result.add(new ArrayList<Integer>(line));
		
		for(int i = index; i < nums.length; i ++) {
			if (i != index && nums[i] == nums[i - 1]) {
				continue;
			}
			line.add(nums[i]);
			task90_helper(nums, i + 1, line, result);
			line.remove(line.size() - 1);
		}		 
	 }

	/*
	 * 91 Decode Ways
	 * https://leetcode.com/problems/decode-ways/
	 * 
	 * M[i]: the # of decode ways that s[0..i - 1] has
	 * 
	 * M[i] = M[i - 1] if s[i - 1] is a valid char
	 *        M[i - 1] + M[i - 2]  is s[i - 2, i - 1] are valid two digits
	 *        
	 * 定义数组number，number[i]意味着：字符串s[0..i-1]可以有number[i]种解码方法。
	 * 回想爬楼梯问题一样，number[i] = number[i-1] + number[i-2]
	 * 但不同的是本题有多种限制：
	 * 第一： s[i-1]不能是0，如果s[i-1]是0的话，number[i]就只能等于number[i-2]
	 * 第二，s[i-2,i-1]中的第一个字符不能是0，而且Integer.parseInt(s.substring(i-2,i))获得的整数必须在0到26之间。
	 * 1010，生成的number数组为：[1,1,1,1,1]
	 * 10000，生成的number数组为：[1,1,1,0,0,0,0,0,0]
	 */
	public static void test91() {
		String s = "1234";
		int rev = task91_numDecodings(s);
		System.out.println("rev = " + rev);
		int rev2 = task91_decodeWayRec(s);
		System.out.println("rev2 = " + rev2);
		
		int rev3 = task91_DecodeWayDPOPT(s);
		System.out.println("rev3 = " + rev3);
	}
	public static int task91_numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] M = new int[s.length() + 1];
		M[0] = 1; // NOTE!!! Here.

		if (task91_isValid(s.substring(0, 1))) {
			M[1] = 1;
		} else {
			M[1] = 0;
		}
		
		for (int i = 2; i <= s.length(); i++) {
			if (task91_isValid(s.substring(i - 1, i))) {
				// s[i-1, i]
				M[i] += M[i - 1];
			}

			if (task91_isValid(s.substring(i - 2, i))) {
				// s[i - 2, i] 
				System.out.println(s.substring(i - 2, i));
				M[i] += M[i - 2];
				System.out.println("M[i] = " + M[i]);
			}
		}
		Debug.printArray(M);
		return M[s.length()];
	}
	public static int task91_decodeWayRec(String str) {
		if (str.length() == 0) {
			return 1;
		}
		
		if (str.length() == 1 ) {
			if (task91_isValid(str)) {
				return 1;
			} else {
				return 0;
			}
		}
		
		int cur = 0;
		int len = str.length();
		
		String lastOne = str.substring(len - 1, len);
		if (task91_isValid(lastOne)) {
			cur += task91_decodeWayRec(str.substring(0, len - 1));
		}
		
		String lastTwo = str.substring(len - 2, len);
		if (task91_isValid(lastTwo)) {
			cur+= task91_decodeWayRec(str.substring(0, len - 2));
		}
		
		return cur;
	}
	
	
	public static int task91_DecodeWayDPOPT(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() == 1) {
			return s.charAt(0) != 0 ? 1 : 0;
		}
		int secondLast = 1;
		int last = s.charAt(0) != 0 ? 1 : 0;
		for(int i = 2; i <= s.length(); i ++ ){
			int curNum = 0;
			if (task91_isValid(s.substring(i - 1, i))) {
				curNum += last;
			} 
			if (task91_isValid(s.substring(i - 2, i))) {
				curNum += secondLast;
			}	
			// update last and secondLast
			secondLast = last;
			last = curNum;
		}
		return last;
	}
	
	public static boolean task91_isValid(String s) {
		if (s.charAt(0) == '0') {
			// starting with 0
			return false;
		}
		int val = Integer.parseInt(s);
		return val >= 1 && val <= 26;
	}
	
	/*
	 * follow up
	 * 给定一个数列，比如1234，将它match到字母上，1是A，2是B等等，
	 * 那么1234可以是 ABCD 但是还可以是12是L，所以1234也可以写作 LCD 或者 AWD 给定一个定长的序列（可以非常长），
	 * 请给出solution输出所有可能的字母组合（consecutive的，只要连续的组合）。 
	 * 链接: https://instant.1point3acres.com/thread/176238
	 * 来源: 一亩三分地
	 */
	public static void test91_2() {
		String str = "1234";
		
		List<String> result = task91_decodeAllResult(str);
		System.out.println(result);
	}
	
	public static List<String> task91_decodeAllResult(String str) {
		char[] map = {' ','A','B','C', 'D', 'E', 'F', 'G', 'H','I','G', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};
		System.out.println(map.length);
		for(int i = 1; i < map.length; i ++) {
			System.out.println("[  " + i + " : " + map[i] + "  ]");
		}
		List<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		task91_helperAllResult(str, 0, result, stb, map);
		return result;
	}
	
	public static void task91_helperAllResult(String str, int index, 
			List<String> result, StringBuilder stb, char[] map) {
		if (index == str.length()) {
			result.add(stb.toString());
			return;
		}
		// decode 1 digit
		int i = (int)(str.charAt(index) - '0');
		
		stb.append(map[i]);
		task91_helperAllResult(str, index + 1, result, stb, map);
		stb.deleteCharAt(stb.length() - 1);
		
		// decode 2 digits
		if (index < str.length() - 1) {
			int j = (int)(str.charAt(index + 1) - '0');
			if (i * 10 + j <= 26) {
				stb.append(map[i * 10 + j]);
				task91_helperAllResult(str, index + 2, result, stb, map);
				stb.deleteCharAt(stb.length() - 1);
			}
		}
	}
	

	
	/*
	 * 92 
	 * reverse Linked List 2 
	 * detail
	 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
	 * For example:
	 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * return 1->4->3->2->5->NULL.
	 * 
	 * constrain:
	 * 1 <=m <= n <= length of list
	 * 
	 * (1) remember to cut if necessary
	 * (2) write code in model
	 */
	public static void test92() {
		int[] array = {1};
		ListNode head = Debug.createList(array);
		Debug.printList(head);
		ListNode nHead = task92_reverseBetween(head, 1, 1);
		Debug.printList(nHead);
	}
	public static ListNode task92_reverseBetween(ListNode head, int m, int n) {
		if (head == null) {
			return head;
		}
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;
		
		ListNode cur = dummy;
		int i = 0;
		while(i < m - 1) {
			cur = cur.next;
			i ++;
		}
		
//		System.out.println("1 cur.val = " + cur.val);
		ListNode firstPartEnd = cur;
			
		while(i < n ) {
			cur = cur.next;
			i ++;
		}
//		System.out.println("2 cur.val = " + cur.val);
		ListNode thirdPartStart = cur.next;
		ListNode secondEnd = cur;
		
		// cut the second and third part
		cur.next = null;
		
		ListNode secondStart = firstPartEnd.next;
		firstPartEnd.next = null;
		
		// reverse second part
		ListNode prev = null;
		cur = secondStart;
		while(cur != null) {
			ListNode next = cur.next;
			
			cur.next = prev;
			prev = cur;
			
			cur = next;
		}
		
		// 
		firstPartEnd.next = secondEnd;
		secondStart.next = thirdPartStart;
		
		
		// reverse the middle part. 
		return dummy.next;
    }
	
	
	/*
	 * 93 
	 * Restore Ip Address 
	 * Given a string containing only digits, 
	 * restore it by returning all possible valid IP address combinations.
	 * For example:
	 * Given "25525511135",
	 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
	 * !!! Implement
	 */
	public static void test93() {
		String s = "25525511135";
		List<String> result = task93_restoreIpAddresses(s);
		System.out.println(result);
	}
	
	public static List<String> task93_restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        task93_helper(result, list, s, 0);
        return result;
    }
	
	// list.size == 4
	// index == s.length()
	public static void task93_helper(List<String> result, List<String> list, String s, int index) {
		if (list.size() == 4) {
			// we already get 4 substrings
			if (index != s.length() ) {
				// but the index != s.length(), which means that there are still remaining chars. 
				return ;
			}
			// otherwise, get a solution.
			StringBuilder stb = new StringBuilder();
			for(String temp : list) {
				stb.append(temp);
				stb.append(".");
			}
			// delete the last "."
			stb.deleteCharAt(stb.length() - 1);
			result.add(stb.toString());
			return ;
		}
		
		for(int i = index; i < s.length() && i < index + 3;i ++) {
			String temp = s.substring(index, i + 1);   // s[index, i]
			//s[index, i]  length of this substring should <= 3, so i < index + 3
			if (task93_isValid(temp)) {
				list.add(temp);
				task93_helper(result, list, s, i + 1);
				list.remove(list.size() - 1);
			}
		}
	}
	// 判断函数..特别一些。 
	public static boolean task93_isValid(String s) {
		if (s.charAt(0) == '0') {  // eliminate cases: like "000" 010 etc. 
			return s.equals("0");
		}
		int val =  Integer.parseInt(s);
		return val >= 0 && val <= 255;
	}

	/*
	 * 94 Binary Tree Inorder Traversal one stack/ morris
	 * 
	 */

	/*
	 * 95 Unique Binary Search Tree2 !!!
	 */

	/*
	 * 96 Unique Binary Search Tree 1 !!!
	 */

	/*
	 * 97 interleaving String dp
	 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	 * For example,
	 * Given:
	 * s1 = "aabcc",
	 * s2 = "dbbca",
	 * When s3 = "aadbbcbcac", return true.
	 * When s3 = "aadbbbaccc", return false.
	 * 
	 * M[i][j] s1[0..i - 1] and s2[0..j - 1] intervering to s3[0..i + j - 1]
	 * 
	 * M[i][j] = M[i - 1][j] && s1[i - 1] == s3[i + j - 1] ||
	 * 			 M[i][j - 1] && s2[j - 1] == s3[i + j - 1]
	 *    
	 * init:
	 * 
	 * M[0][0] = true;
	 * 
	 * M[i][0] = M[i - 1][0] && s1[i - 1] == s3[i - 1]
	 * M[0][j] = M[0][j - 2] && s2[j - 1] == s3[j - 1]
	 * 
	 * return M[s1Len][s2Len]
	 */
	public static void test96() {
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		String s4 = "aadbbbaccc";
		System.out.println(task96_isInterleave(s1, s2, s3));
		System.out.println(task96_isInterleave(s1, s2, s4));
		System.out.println("----------------");
		System.out.println(task96_isInterleave_dp(s1, s2, s3));
		System.out.println(task96_isInterleave_dp(s1, s2, s4));
	}
	
	public static boolean task96_isInterleave(String s1, String s2, String s3) {
		return task96_recHelper(s1, 0, s2, 0, s3, 0);
	}
	
	public static boolean task96_recHelper(String s1, int p1, String s2, int p2, String s3, int p3) {
		// base case
		if (p3 == s3.length()) {
			return true;
		}
		if (p1 == s1.length()) {
			return s2.substring(p2).equals(s3.substring(p3));
		}
		
		if (p2 == s2.length()) {
			return s1.substring(p1).equals(s3.substring(p3));
		}
		
		if (s1.charAt(p1) == s3.charAt(p3) && s2.charAt(p2) == s3.charAt(p3)) {
			return task96_recHelper(s1, p1 + 1, s2, p2, s3, p3 + 1) ||
					task96_recHelper(s1, p1, s2, p2 + 1, s3, p3 + 1);
		} else if (s1.charAt(p1) == s3.charAt(p3)) {
			return task96_recHelper(s1, p1 + 1, s2, p2, s3, p3 + 1);
		} else if (s2.charAt(p2) == s3.charAt(p3)) {
			return task96_recHelper(s1, p1, s2, p2 + 1, s3, p3 + 1);
		} else {
			return false;
		}
	}
	
	public static boolean task96_isInterleave_dp(String s1, String s2, String s3) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
	    int s1Len = s1.length();
		int s2Len = s2.length();
		if(s1Len + s2Len != s3.length()){
			return false;
		}
		boolean[][] f = new boolean[s1Len + 1][s2Len + 1];
		
		f[0][0] = true;
		
		for(int i=1; i<=s1Len; i++){
			f[i][0] = f[i-1][0] && s1.charAt(i -1) == s3.charAt(i -1);
		}
		for(int j=1; j<=s2Len; j++){
			f[0][j] = f[0][j-1] && s2.charAt(j-1) == s3.charAt(j -1);
		}
		
		for(int i=1; i<=s1Len; i++){
			for(int j=1; j<=s2Len; j++){
				f[i][j] = f[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j -1) ||
						f[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
			}
		}
		return f[s1Len][s2Len];
	
    }
	

	/*
	 * 98 Valid Binary Search Tree 
	 * easy.
	 */
	public static void test98() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		
		n2.left = n1; 
		n2.right = n3;
		
		TreeNode root = n2;
		
		boolean res = task98_ValidBinaryTree(root);
		System.out.println(res);
	}
	
	public static boolean task98_ValidBinaryTree(TreeNode root) {	
		return task98_helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static boolean task98_helper(TreeNode node, int min, int max) {
		if (node == null) return true;
		
		if (node.val <= min || node.val >= max) {
			return false;
		}
		return task98_helper(node.left, min, node.val) && task98_helper(node.right, node.val, max);
	}
	
	 

	/*
	 * 99 Recover Binary Search Tree. 
	 * !!! Do it later 
	 */

	/*
	 * 100 Same Tree 
	 * easy
	 * 
	 */
}

package sortbynum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.swing.text.AbstractDocument.LeafElement;

import ds.*;

public class Task151_200 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test161();
		// test151();
		// test152();
		// test153();
		// test154();
		// test162();
		// test163();
		// test165();
		// test179();
//		 test166();
		// test168();
		// test171();
		test187();
		// test190();
		// test198();
		// test199();
//		test200();
	}

	/*
	 * 151 Reverse Words in a String
	 * 
	 * 1 remove duplicate space, head space, tail space 2 reverse each word 3
	 * reverse the whole scentences
	 */
	public static void test151() {
		String s = "a";
		String rev = task151_reverseWords(s);

		System.out.println("rev = ");
		System.out.println(rev);
	}

	public static String task151_reverseWords(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		char[] array = new char[s.length()];
		int cur = 0;

		int index = 0;
		while (cur < s.length() && s.charAt(cur) == ' ') {
			cur++;
		}
		// edge case: if the string is "      "
		if (cur == s.length()) {
			return new String();
		}

		boolean firstSpace = false;
		while (cur < s.length()) {
			if (s.charAt(cur) != ' ') {
				array[index++] = s.charAt(cur);
				firstSpace = true;
			} else {
				if (firstSpace) {
					array[index++] = ' ';
					firstSpace = false;
				}
			}
			cur++;
		}

		// if the last element in array is space, then remove it.
		if (array[index - 1] == ' ') {
			index--;
		}

		// reverse each word in the arrays
		int slow = 0, fast = 0;
		while (fast <= index) {
			// remember, first check fast < index
			if ((fast < index && array[fast] == ' ') || fast == index) {
				task151_reverse(array, slow, fast - 1);
				fast++;
				slow = fast;
			} else {
				fast++;
			}
		}

		// reverse the whole scentences
		task151_reverse(array, 0, index - 1);

		return new String(array, 0, index);
	}

	public static void task151_reverse(char[] array, int left, int right) {
		while (left < right) {
			char temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			left++;
			right--;
		}
	}

	/*
	 * 152 Maximum Product Subarray
	 */
	public static int task152_maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] maxEndingHere = new int[n];
		int[] minEndingHere = new int[n];

		for (int i = 0; i < n; i++) {
			if (i == 0) {
				maxEndingHere[i] = nums[i];
				minEndingHere[i] = nums[i];
			} else {
				maxEndingHere[i] = Math.max(maxEndingHere[i - 1] * nums[i],
						minEndingHere[i - 1] * nums[i]);
				minEndingHere[i] = Math.min(maxEndingHere[i - 1] * nums[i],
						minEndingHere[i - 1] * nums[i]);
			}
		}
		int maxProduct = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			maxProduct = Math.max(maxProduct, maxEndingHere[i]);
		}
		return maxProduct;
	}

	public static void test152() {
		int[] nums = { 0, 0, 0 };
		int rev = task152_maxProduct2(nums);
		System.out.println("rev = " + rev);
	}

	public static int task152_maxProduct2(int[] nums) {
		int[] max = new int[nums.length];
		int[] min = new int[nums.length];

		min[0] = max[0] = nums[0];
		int result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			min[i] = max[i] = nums[i];
			if (nums[i] > 0) {
				max[i] = Math.max(max[i], max[i - 1] * nums[i]);
				min[i] = Math.min(min[i], min[i - 1] * nums[i]);
			} else if (nums[i] < 0) {
				max[i] = Math.max(max[i], min[i - 1] * nums[i]);
				min[i] = Math.min(min[i], max[i - 1] * nums[i]);
			}

			result = Math.max(result, max[i]);
		}

		return result;
	}

	public int task152_maxProduct3(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}

		int prev_max = 1;
		int prev_min = 1;

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			int max_ending_here = task152_3_maxof3(prev_max * array[i], prev_min
					* array[i], array[i]);
			int min_ending_here = task152_3_minof3(prev_max * array[i], prev_min
					* array[i], array[i]);

			prev_max = max_ending_here;
			prev_min = min_ending_here;

			if (max < prev_max) {
				max = prev_max;
			}
		}

		if (max < prev_max) {
			max = prev_max;
		}
		return max;
	}

	public int task152_3_maxof3(int a, int b, int c) {
		return Math.max(Math.max(a, b), c);
	}
	
	public int task152_3_minof3(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
	
	
	
	public static int task152_maxProduct_OPT(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		// store the result that is the max we have found so far
		int r = A[0];
		int n = A.length;
		// imax/imin stores the max/min product of
		// subarray that ends with the current number A[i]
		for (int i = 1, imax = r, imin = r; i < n; i++) {
			// multiplied by a negative makes big number smaller, small number bigger
			// so we redefine the extremums by swapping them
			if (A[i] < 0) {
				int temp = imin;
				imin = imax;
				imax = temp;
			}

			// max/min product for the current number is either the current number itself
			// or the max/min by the previous number times the current one
			imax = Math.max(A[i], imax * A[i]);
			imin = Math.min(A[i], imin * A[i]);

			// the newly computed max value is a candidate for our global result
			r = Math.max(r, imax);
		}
		return r;
	}
	
	

	
	/*
	 * 153 Find Minimum in Rotated Sorted Array
	 */
	public static void test153() {
		int[] nums = { 1, 2, 3 };
		int rev = task153_findMin(nums);
		System.out.println("rev = " + rev);
	}

	public static int task153_findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int left = 0, right = nums.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < nums[mid - 1] && nums[mid] > nums[mid + 1]) {
				return nums[mid];
			} else if (nums[mid] > nums[right]) {
				// we can make sure that the right side is rotated
				left = mid;
			} else {
				right = mid;
			}
		}
		System.out.println("Left = " + left);
		System.out.println("right = " + right);
		if (nums[left] > nums[right]) {
			return nums[right];
		} else {
			return nums[left];
		}
	}

	/*
	 * 154 Find Minimum in Rotated Sorted Array2
	 */
	public static void test154() {
		int[] nums = { 1, 3, 3 };
		int rev = task154_findMin(nums);
		System.out.println("rev = " + rev);
	}

	public static int task154_findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int left = 0, right = nums.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > nums[left]) {
				// we can make sure that the right side is rotated, the minimum
				// is in right side
				left = mid;
			} else if (nums[mid] < nums[left]) {
				// if nums[mid] > nums[left], the minimum is in the left side
				// if nums[mid] <= nums[left], the minimum cannot be the left
				// side
				// !!!
				right = mid;
			} else {
				left++;
			}
		}
		System.out.println("Left = " + left);
		System.out.println("right = " + right);
		if (nums[left] > nums[right]) {
			return nums[right];
		} else {
			return nums[left];
		}
	}

	/*
	 * 155 Min Stack
	 */

	/*
	 * 156 Binary Tree Upside down
	 * 
	 * Similar to reverse linked list
	 */
	public static TreeNode task156_upsideDownBinaryTree(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}

		TreeNode newRoot = task156_upsideDownBinaryTree(root.left);

		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;

		return newRoot;
	}

	/*
	 * 157 Read N characters give reade4 read4(char* buf) reads 4 chars at a
	 * time from a file the return value is the actual number of chars read. e.g
	 * rev = 3, return 3 if there are only 3 chars left in the file
	 * 
	 * using read4 API, implement read(char* buf, int n) that reads n chars from
	 * the file
	 */
	public static int read4(char[] buf) {
		return -1;
	}

	public int read(char[] buf, int n) {
		int tmp = 0;
		int ans = 0;

		char[] nbuf = new char[4]; //
		while ((tmp = read4(nbuf)) != 0) {
			// we still have new content read4(nbuf) != 0
			// copy the new content into buf
			for (int i = 0; i < tmp; i++) {
				if (ans == n)
					return ans;
				buf[ans++] = nbuf[i];
			}
		}
		return ans;
	}
	
	public int read_1(char[] buf, int n) {
		int count = 0;
		char[] mybuf = new char[4];
		while (count < n) {
			// get 4 
			int num = read4(mybuf);
			if (num == 0)
				break;
			
			int index = 0;
			// copy the content in mybuf into buf
			while (index < num && count < n) {
				buf[count++] = mybuf[index++];
			}
		}
		return count;
	}

	

	/*
	 * 158 Read N characters give read4II- call multiply times
	 */
	private char[] buffer = new char[4];
	private int oneRead = 0;
	private int offset = 0;

	public int task158_read(char[] buf, int n) {
		boolean lessthan4 = false;
		int haveRead = 0;
		while (!lessthan4 && haveRead < n) {
			if (oneRead == 0) {
				oneRead = read4(buffer);
				lessthan4 = oneRead < 4;
			}
			int actRead = Math.min(n - haveRead, oneRead);
			for (int i = 0; i < actRead; i++) {
				buf[haveRead + i] = buffer[offset + i];
			}
			oneRead -= actRead;
			offset = (offset + actRead) % 4;
			haveRead += actRead;
		}
		return haveRead;
	}

	  
	public int read22(char[] buf, int n) {
		LinkedList<Character> mybuffer = new LinkedList<Character>();
		int total = 0;
		while (true) {
			char[] temp = new char[4];  // create a temp array
			int in = read4(temp);  // read 4 chars into temp, there might be smaller than 4 chars

			// add temp[] into mybuffer 
			for (int i = 0; i < in; i++)
				mybuffer.add(temp[i]);
			
			// get how manuy chars do we need, it's the minimum of (n - total) and mybuffer.size()
			// n - total is the 
			// 判断还需要写入多少个到结果， 比如读了4个，但是只要3个；或者要4个，只有3个了。
			in = Math.min(n - total, mybuffer.size());

			// copy the content from mybuffer to buf
			for (int i = 0; i < in; i++) {
				buf[total++] = mybuffer.poll();
			}
			// if there is no more needed to read, break
			if (in == 0)
				break;
		}
		return total;
	}
	
	
	/*
	 * https://segmentfault.com/a/1190000003794420
	 */

	/*
	 * task157: read n chars given read4
	 */
	public static int task157_readOPT(char[] buf, int n) {
		for (int i = 0; i < n; i += 4) {
			char[] temp = new char[4];
			int tempLen = read4(temp);
			// copyt the temp to buf, the length is the min(tempLen, n - i)
			System.arraycopy(temp, 0, buf, i, Math.min(tempLen, n - i));
			//
			if (tempLen < 4) {

				return Math.min(i + tempLen, n);
			}
		}
		// here, the n is 4 的倍数
		return n;
	}

	/*
	 * task158 call multiple times corner case: 1 first call, read4
	 * 读出的多余的字符就暂时存起来，留着第二次调用 2 second call, 调用第一次存起来的字符，要是还没用完， 就留着给第三次调用使用 use
	 * queue
	 */
	public static int task158_read2OPT(char[] buf, int n) {
		Queue<Character> remaining = new LinkedList<Character>();
		// if q is NOT empty, read the content from q
		int i = 0;
		while (i < n && !remaining.isEmpty()) {
			buf[i] = remaining.poll();
			i++;
		}
		// if we still need to read via read(char[] buf)
		for (; i < n; i += 4) {
			char[] temp = new char[4];
			int tempLen = read4(temp);
			// if the read chars is more than the number that we need
			if (tempLen > n - i) {
				// copy the n - i chars into buf
				System.arraycopy(temp, 0, buf, i, n - i);
				// put the remaining into queue(remaining)
				for (int j = n - i; j < tempLen; j++) {
					remaining.offer(temp[j]);
				}
			} else {
				// the number of read chars is less than the number that we need
				// (n - i)
				// copy the tempLen chars
				System.arraycopy(temp, 0, buf, i, tempLen);
			}
			// if tempLen < 4, we have finished read the file.
			// return min(tempLen + i, n)
			return Math.min(i + tempLen, n);
		}
		// if goes here, return the n
		return n;
	}

	/*
	 * 159 Longest Substring with At Most Two Distict Characters
	 * 
	 * Two pointer_window problem
	 * 
	 * two pointers + hashMap
	 */
	public static int task159_lengthOfLongestSubstringTwoDistinct(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() <= 2) {
			return s.length();
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int fast = 0, slow = 0;
		int maxLen = 1;
		while (fast < s.length()) {
			char fast_ch = s.charAt(fast);
			if (map.containsKey(fast_ch)) {
				map.put(fast_ch, map.get(fast_ch) + 1);
			} else {
				map.put(fast_ch, 1);
				// remove slow
				while (map.size() > 2) {
					char slow_ch = s.charAt(slow);

					int count = map.get(slow_ch);
					if (count > 1) {
						map.put(slow_ch, count - 1);
					} else {
						map.remove(slow_ch);
					}
					slow++;
				}
				maxLen = Math.max(maxLen, fast - slow + 1);
			}
		}
		return maxLen;
	}

	/*
	 * follow up Longest Substring with At Most K Distinct Characters
	 */
	public static int task159_lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() <= k) {
			return s.length();
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int fast = 0, slow = 0;
		int maxLen = 1;
		while (fast < s.length()) {
			char fast_ch = s.charAt(fast);
			if (map.containsKey(fast_ch)) {
				map.put(fast_ch, map.get(fast_ch) + 1);
			} else {
				map.put(fast_ch, 1);
				// remove slow
				while (map.size() > k) {
					char slow_ch = s.charAt(slow);

					int count = map.get(slow_ch);
					if (count > 1) {
						map.put(slow_ch, count - 1);
					} else {
						map.remove(slow_ch);
					}
					slow++;
				}
				maxLen = Math.max(maxLen, fast - slow + 1);
			}
		}

		return maxLen;
	}

	/*
	 * 160 Intersection of Two linked LIst
	 */

	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		int ALen = task160_getLength(headA);
		int BLen = task160_getLength(headB);
		// suppose ALen >= BLen
		if (ALen < BLen) {
			return getIntersectionNode(headB, headA);
		}

		int diff = ALen - BLen;
		ListNode ptrA = headA;
		ListNode ptrB = headB;
		while (diff > 0) {
			ptrA = ptrA.next;
			diff--;
		}

		while (ptrA != null && ptrB != null) {
			if (ptrA == ptrB) {
				return ptrA;
			}
			ptrA = ptrA.next;
			ptrB = ptrB.next;
		}

		return null;

	}

	public static int task160_getLength(ListNode head) {
		int size = 0;
		while (head != null) {
			size++;
			head = head.next;
		}
		return size;
	}

	/*
	 * 161 One Edit Distance Given two strings S and T, determine if they are
	 * both one edit distance apart. insert delete replace
	 */
	public static void test161() {
		String s = "a";
		String t = "ba";
		System.out.println(task161_isOneEditDistance(s, t));
	}

	public static boolean task161_isOneEditDistance(String s, String t) {
		if (Math.abs(s.length() - t.length()) > 1) {
			return false;
		} else if (s.length() == t.length()) {
			return task161_isSameLen(s, t);
		} else if (s.length() > t.length()) {
			return task161_inOneDiff(s, t);
		} else {
			// s.length < t.length
			System.out.println("s.length < t.length");
			return task161_isOneEditDistance(t, s);
		}
	}

	public static boolean task161_isSameLen(String s, String t) {
		int counter = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				counter++;
			}
		}
		return counter == 1;
	}

	// suppose s is longer
	public static boolean task161_inOneDiff(String s, String t) {
		for (int i = 0; i < t.length(); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				return s.substring(i + 1).equals(t.substring(i));
			}
		}
		return true;
	}

	/*
	 * 162 Find Peak Element
	 */
	public static void test162() {
		int[] a = { 1, 2, 3 };
		int rev = findPeakElement(a);
		System.out.println("rev = " + rev);
	}

	public static int findPeakElement(int[] nums) {
		int left = 0, right = nums.length - 1;

		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
				return mid;
			} else if (nums[mid] < nums[mid + 1]) {
				left = mid;
			} else {
				right = mid;
			}
		}

		if (nums[left] > nums[right]) {
			return left;
		} else {
			return right;
		}
	}

	/*
	 * 163 Missing Ranges
	 * 
	 * input: {0,1,3,50,75} output: {"2", "4->49", "51->74", "76->99"}
	 */
	public static void test163() {
		int[] nums = { 0, 1, 3, 50, 75 };
		int lower = 0, upper = 99;

		List<String> rev = task163_findMissingRanges(nums, lower, upper);
		System.out.println(rev);
	}

	public static List<String> task163_findMissingRanges(int[] nums, int lower,
			int upper) {
		List<String> result = new ArrayList<String>();
		if (nums == null || nums.length == 0) {
			if (lower == upper) {
				String sln = Integer.toString(lower);
				result.add(sln);
			} else {
				String sln = Integer.toString(lower) + "->"
						+ Integer.toString(upper);
				result.add(sln);
			}
			return result;
		}
		// lower, nums[0]
		int leftBound = Integer.MIN_VALUE;
		int rightBound = Integer.MIN_VALUE;

		if (lower < nums[0]) {
			if (lower == nums[0] - 1) {
				leftBound = lower;
				rightBound = lower;
				String sln = Integer.toString(leftBound);
				result.add(sln);
			} else {
				leftBound = lower;
				rightBound = nums[0] - 1;
				String sln = Integer.toString(leftBound) + "->"
						+ Integer.toString(rightBound);
				result.add(sln);
			}
		}

		for (int i = 1; i < nums.length; i++) {
			String sln = "";
			if (nums[i] == nums[i - 1] + 1) {
				continue;
			} else {
				leftBound = nums[i - 1] + 1;
				rightBound = nums[i] - 1;
				if (leftBound == rightBound) {
					sln = Integer.toString(leftBound);
				} else {
					sln = Integer.toString(leftBound) + "->"
							+ Integer.toString(rightBound);
				}
				result.add(sln);
			}
		}

		if (nums[nums.length - 1] < upper) {
			if (nums[nums.length - 1] == upper - 1) {
				leftBound = upper;
				result.add(Integer.toString(leftBound));
			} else {
				leftBound = nums[nums.length - 1] + 1;
				rightBound = upper;
				String sln = Integer.toString(leftBound) + "->"
						+ Integer.toString(rightBound);
				result.add(sln);
			}

		}

		return result;
	}

	/*
	 * 164 Maximum Gap
	 * 
	 * 
	 * 假设有 N个元素，从A到B， 最大的差值 >= ceiling[(B - A)/(N - 1)]
	 * 
	 * 每个桶的大小为 gap = ceiling[(B - A)/(N - 1)] 桶的个数为 (B - A)/gap + 1
	 * 
	 * 用 (A[i] - A) / gap 来确定属于哪个桶.. 更新该桶的最大值，最小值
	 * 
	 * 由于桶之间元素差值最多为： gap - 1 所以，答案不会在同一个桶之中。
	 * 
	 * 找出每个非空桶p, 找出下一个非空桶q, q.min - p.max 则可能是备选答案
	 */
	public static int task164_maximumGap(int[] num) {
		if (num == null || num.length <= 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < num.length; i++) {
			min = Math.min(min, num[i]);
			max = Math.max(max, num[i]);
		}

		if (max == min) {
			return 0;
		}// all elements are same

		int bucketLen = (int) Math.ceil((double) (max - min)
				/ (double) (num.length - 1));
		int bucketNum = (max - min) / bucketLen + 1;

		// create the bucket, we only need the bucket.min, bucket.max
		// so use two arraylist
		ArrayList<Integer> bucketMin = new ArrayList<Integer>();
		ArrayList<Integer> bucketMax = new ArrayList<Integer>();

		// int the bucketMin, bucketMax
		for (int i = 0; i < bucketNum; i++) {
			bucketMin.add(Integer.MAX_VALUE);
			bucketMax.add(Integer.MIN_VALUE);
		}

		// traverse the num[], and assign the element into corresponding
		// position.
		for (int i = 0; i < num.length; i++) {
			int position = (int) (num[i] - min) / bucketLen;
			bucketMin.set(position, Math.min(bucketMin.get(position), num[i]));
			bucketMax.set(position, Math.max(bucketMax.get(position), num[i]));
		}

		// get the maxGap
		// p points a un null bucket
		// q points the next un null bucket.
		// q.min - p.max might be the result
		int maxGap = Integer.MIN_VALUE;
		int previous = min;
		for (int i = 0; i < bucketNum; i++) {
			// if the bucket is null, continue
			if (bucketMin.get(i) == Integer.MAX_VALUE
					&& bucketMax.get(i) == Integer.MIN_VALUE) {
				continue;
			}
			maxGap = Math.max(maxGap, bucketMin.get(i) - previous);
			previous = bucketMax.get(i);
		}

		return maxGap;

	}

	/*
	 * 165 Compare Version Numbers
	 */
	public static void test165() {
		String version1 = "1.1";
		String version2 = "1";
		int rev = task165_compareVersion(version1, version2);
		// System.out.println("rev= " + rev);
	}

	// this doesn't work well
	public static int task165_compareVersion(String version1, String version2) {
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");

		int i = 0, j = 0;
		while (i < v1.length && j < v2.length) {
			Integer val_v1 = Integer.parseInt(v1[i]);
			Integer val_v2 = Integer.parseInt(v2[j]);

			if (val_v1 < val_v2) {
				return -1;
			} else if (val_v1 > val_v2) {
				return 1;
			} else {
				i++;
				j++;
			}
		}
		if (i == v1.length && j == v2.length) {
			return 0;
		} else if (i == v1.length) {
			// v2 > v1
			if (Integer.parseInt(version2.substring(j, version2.length())) == 0) {
				return 0;
			}
			return -1;
		} else {
			if (Integer.parseInt(version1.substring(j, version1.length())) == 0) {
				return 0;
			}
			return 1;
		}

	}

	/*
	 * 166 Fraction to Recurring Decimal
	 */
	public static void test166() {
		int a = 1;
		int b = 6;
		String str = task166_fractionToDecimal(a, b);
		System.out.println(str);
		
		double res = (double) a/ ((double) b);
		System.out.println(res);
	}
	
	public static String task166_fractionToDecimal(int numerator,
			int denominator) {
		if (numerator == 0) {
			return new String("0");
		}
		// flag
		boolean flag = (numerator < 0) ^ (denominator < 0);
		// convert the numerator and denominator to long
		long Numerator = Math.abs((long) numerator);// 转换成long，如果刚好是INT_MIN,就出现溢出了。
		long Denominator = Math.abs((long) denominator);
		// result 
		StringBuilder res = new StringBuilder();
		if (flag == true) {
			res.append('-');
		}
		String int_part = String.valueOf(Numerator / Denominator);
		res.append(int_part);
		
		System.out.println(res.toString());
		System.out.println("------------");
		
		// get the decimal part
		Numerator = Numerator % Denominator;
		if (Numerator == 0) { 
			// only Integer. there is no decimal part
			return res.toString();
		}
		
		// there is decimal part, append the '.' to the string
		res.append('.');

		// 用hashMap store remainder. every time when the remainder appears again, 
		// all right, we find the repeating part. 
		// map <Digit Number, Index> ==> remainder: index
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();

		// if a number can be represented by a fraction, it is a rational
		// number.
		// its length is certain or can be represented by "(repeated part)"
		// 就是做一个除法的过程.
		
		StringBuilder decimal_part = new StringBuilder();
		for (int i = 0; Numerator != 0; i++) {
			// the remainder already exist 
			if (map.get(Numerator) != null) {
				break;
			}
			// put the remainder with its index into hashMap
			map.put(Numerator, i);
			
			Numerator *= 10;
			decimal_part.append(String.valueOf((Numerator / Denominator)));
			// get the new remainder
			Numerator %= Denominator;
		}
		
		// there is no repeating part
		if (Numerator == 0) {
			res.append(decimal_part);
		} else {
			int repeat_index = map.get(Numerator);
			decimal_part.insert(repeat_index, '(');
			decimal_part.append(')');
			res.append(decimal_part);
		}
		
		return res.toString();
	}

	/*
	 * 167 Two SumII - input array is sorted
	 */
	public static int[] task167_twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		if (numbers == null || numbers.length == 0) {
			return result;
		}

		int i = 0, j = numbers.length - 1;
		while (i < j) {
			if (numbers[i] + numbers[j] == target) {
				result[0] = i;
				result[1] = j;
				break;
			} else if (numbers[i] + numbers[j] < target) {
				i++;
			} else {
				j--;
			}
		}

		return result;

	}

	/*
	 * 168 Excel Sheet Column Title
	 */
	public static void test168() {
		int n = 26;
		String str = task168_convertToTitle(n);
		System.out.println(str);
	}

	public static String task168_convertToTitle(int n) {
		if (n <= 0) {
			return null;
		}
		StringBuilder stb = new StringBuilder();

		while (n > 0) {
			n--; // the key !!!
			// 注意while循环里面的n–，因为26进制里最小对应的是10进制里面的1，不是0
			char ch = (char) (n % 26 + 'A');
			stb.append(ch);
			n /= 26;
		}
		stb.reverse();
		return stb.toString();
	}

	/*
	 * 169 Majority Element
	 * 
	 * to do the follow up
	 */
	public static int task169_majority(int[] nums) {
		int count = 1;
		int candidate = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == candidate) {
				count++;
			} else {
				count--;
				if (count == 0) {
					candidate = nums[i];
					count = 1;
				}
			}
		}
		return candidate;
	}

	/*
	 * 170 Two Sum III- data structure design see task151_200_TwoSum3
	 */

	/*
	 * 171 Excel Sheet Column NUmber
	 */
	public static void test171() {
		String s = "AAB";
		int rev = task171_titleToNumber(s);
		int rev2 = task171_titleToNumber2(s);
		System.out.println("rev = " + rev);
		System.out.println("rev2 = " + rev2);
	}

	public static int task171_titleToNumber(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 1; i <= 26; i++) {
			char key = (char) ('A' + i - 1);
			int value = i;
			map.put(key, value);
		}

		int i = s.length() - 1;
		int t = 0;
		int result = 0;
		while (i >= 0) {
			char cur = s.charAt(i);
			result += Math.pow(26, t) * map.get(cur);
			i--;
			t++;
		}

		return result;
	}

	public static int task171_titleToNumber2(String s) {

		int i = s.length() - 1;
		int t = 0;
		int result = 0;
		while (i >= 0) {
			char cur = s.charAt(i);
			result += Math.pow(26, t) * (int) (cur - 'A' + 1);
			i--;
			t++;
		}

		return result;
	}

	/*
	 * 172 Factorial Trailing Zeroes
	 */
	/*
	 * 计算从1到n之间所有数的5的约数个数总和。很简单的想到能不能用n/5得到。比如当n为19的时候，19/5 = 3.8，
	 * 那么就是有3个约数包含5的数，分别是5, 10, 15。但是有的数可能被5整除多次，比如说25。
	 * 这样的数一个就能给最后的factorial贡献好几个5。 最后的解法就是对n/5+n/25+n/125+…+进行求和，当n小于分母的时候，停止。
	 * 分母依次为5^1, 5^2, 5^2… 这样的话在计算5^2的时候，
	 * 能被25整除的数里面的两个5，其中一个已经在5^1中计算过了。所以5^2直接加到count上。
	 */
	public static int task172_factorialTrailingZeros(int n) {
		int counter = 0;
		while (n > 0) {
			counter += n / 5;
			n /= 5;
		}
		return counter;
	}

	/*
	 * 173 Binary Search Tree Iterator refer iterator.IteratorInBinarySearchTree
	 * and IteratorInBinarySearchTreeMorris
	 */

	/*
	 * 174 Dungeon Game !!! to Do later
	 */
	public int task174_calculateMinimumHP(int[][] dungeon) {
		int n = dungeon.length;
		if (n <= 0)
			return 1;
		int m = dungeon[0].length;
		int[][] life = new int[n][m];
		for (int i = n - 1; i >= 0; --i) {
			for (int j = m - 1; j >= 0; --j) {
				int futureMin = 1;
				if (i < n - 1) {
					futureMin = life[i + 1][j];
					if (j < m - 1)
						futureMin = Math.min(futureMin, life[i][j + 1]);
				} else if (j < m - 1)
					futureMin = life[i][j + 1];
				life[i][j] = Math.max(1, futureMin - dungeon[i][j]);
				// System.out.println("" + i + "," + j + "=" + life[i][j]);
			}
		}
		return life[0][0];
	}

	/*
	 * 179 Largest Number
	 * 
	 * the key is for the comparator s12 = s1 + s2; s21 = s2 + s1
	 */

	public static void test179() {
		int[] nums = { 3, 30, 34, 5, 9 };
		String rev = task179_largestNumber(nums);
		System.out.println(rev);
	}

	public static String task179_largestNumber(int[] nums) {
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				// TODO Auto-generated method stub
				// !!! key
				String ss1 = s1 + s2;
				String ss2 = s2 + s1;
				return -ss1.compareTo(ss2);
			}
		};

		String[] array = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			array[i] = Integer.toString(nums[i]);
		}

		Arrays.sort(array, comparator);

		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			stb.append(array[i]);
		}
		// delete the heading '0's
		while (stb.charAt(0) == '0' && stb.length() > 1) {
			stb.deleteCharAt(0);
		}
		return stb.toString();

	}

	/*
	 * 186 reverse Words in a String II
	 */
	public static void task186_reverseWords(char[] s) {
		if (s == null || s.length == 0) {
			return;
		}
		// reverse each word
		int slow = 0;
		int fast = 0;
		while (fast <= s.length) {
			if ((fast < s.length && s[fast] == ' ') || fast == s.length) {
				reverse(s, slow, fast - 1);
				fast++;
				slow = fast;
			} else {
				fast++; // !!! don't forget this
			}
		}
		reverse(s, 0, s.length - 1);

	}

	public static void reverse(char[] s, int start, int end) {
		while (start < end) {
			char temp = s[start];
			s[start] = s[end];
			s[end] = temp;
			start++;
			end--;
		}
	}

	/*
	 * 187 Repeated DNA sequence
	 * 
	 * encode
	 */

	public static void test187() {
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		List<String> result = task187_findRepeatedDnaSequences3(s);
		System.out.println(result);
	}
	
	public List<String> task187_findRepeatedDnaSequences(String s) {
		HashSet<Integer> hash = new HashSet<>();
		HashSet<String> dna_result = new HashSet<>();
		for (int i = 9; i < s.length(); i++) {
			// len == 10. start = i - 9, end = i (included)
			String subString = s.substring(i - 9, i + 1);
			int code = encode(subString);

			if (hash.contains(code)) {
				dna_result.add(subString);
			} else {
				hash.add(code);
			}
		}
		List<String> result = new ArrayList<String>();
		for (String d : dna_result) {
			result.add(d);
		}
		return result;
	}

	public int encode(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'A') {
				sum = sum * 4;
			} else if (s.charAt(i) == 'C') {
				sum = sum * 4 + 1;
			} else if (s.charAt(i) == 'G') {
				sum = sum * 4 + 2;
			} else {
				sum = sum * 4 + 3;
			}
		}
		return sum;
	}
	
	// 没有必要自己编码, 直接string 里边算hashCode 就有编码。
	public static List<String> task187_findRepeatedDnaSequences2(String s) {
		List<String> res = new ArrayList<String>();
		HashSet<String> resset = new HashSet<String>();
		if (s == null || s.length() <= 10) {
			return res;
		}
		HashSet<String> set = new HashSet<String>();
		int len = s.length();
		for (int i = 0; i <= len - 10; i++) {
			String sub = s.substring(i, i + 10);
			if (!set.add(sub)) {
				resset.add(sub);
			}
		}
		res.addAll(resset);
		return res;
	}

	// 另外一种编码方式
	// use A => 00, C => 01 , G => 10, T => 11
	// the length of binary is 20. 
	// we need a 20 bits mask 
	public static List<String> task187_findRepeatedDnaSequences3(String s) {
		if (s == null || s.length() < 10)
			return new ArrayList<>();
		HashMap<Character, Integer> map = new HashMap<>();
		List<String> result = new ArrayList<>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
		
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		
		int hash = 0;
		for (int i = 0; i < 10; i++) {
			hash <<=2;
			hash |= map.get(s.charAt(i));
		}
		
		set1.add(hash);
		
		// the code has 20 bits. 
		int mask = (1 << 20) - 1;
		for (int i = 10; i < s.length(); i++) {
			hash = (hash << 2) & mask;
			hash |= map.get(s.charAt(i));
			
			if (!set1.contains(hash)) {
				set1.add(hash);
			} else {
				// in set1.
				if (!set2.contains(hash)) {
					set2.add(hash);
					result.add(s.substring(i - 9, i));
				}
			}
			
		}
		return result;
	}

	/*
	 * 188 Best time to buy and sell Stock IV refer jiuzhangAdvancedAlgorithm
	 * class4
	 */

	/*
	 * 189 Rotate array For example, with n = 7 and k = 3, the array
	 * [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]
	 * 
	 * 
	 * 4 3 2 1 7 6 5 5 6 7 1 2 3 4
	 */

	public static void task189_rotate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return;
		}

		int n = nums.length;
		k = k % n; // k might > n don't forget this

		task189_reverse(nums, n - k, n - 1);
		task189_reverse(nums, 0, n - k - 1);
		task189_reverse(nums, 0, n - 1);

	}

	public static void task189_reverse(int[] nums, int left, int right) {
		while (left < right) {
			int temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}
	}

	/*
	 * 190 Reverse BITs
	 * 
	 * better to do in C++
	 */
	public static void test190() {
		int n = 43261596;
		int rev = reverseXor1(n);
		System.out.println(rev);
	}

	public static int swapBits(int num, int i, int j) {
		int i_bit = ((num >> i) & 1);
		int j_bit = ((num >> j) & 1);

		if ((i_bit ^ j_bit) != 0) {
			int u_1 = 1;

			int mask = (u_1 << i) | (u_1 << j);

			num ^= mask;
		}
		return num;
	}

	public static int reverseXor1(int num) {
		int len = 64 * 8;
		for (int i = 0; i < len / 2; ++i) {
			num = swapBits(num, i, len - i - 1);
		}
		return num;
	}

	// using divied and conque.
	public static void reverseXor2(int n) {

		n = (((n & 0xFFFF0000) >> 16) | ((n & 0x0000FFFF) << 16));

		n = (((n & 0xFF00FF00) >> 8) | ((n & 0x00FF00FF) << 8));

		n = (((n & 0xF0F0F0F0) >> 4) | ((n & 0x0F0F0F0F) << 4));

		n = (((n & 0xCCCCCCCC) >> 2) | ((n & 0x33333333) << 2));
		n = (((n & 0xAAAAAAAA) >> 1) | ((n & 0x55555555) << 1));
	}

	/*
	 * 191 Number of 1 bits
	 */
	public static int task191_hammingWeight(int n) {
		int res = 0;
		while (n != 0) {
			n = n & (n - 1);
			res++;
		}
		return res;
	}

	/*
	 * 198 House Robber get the sum of elements that NOT successive
	 * 
	 * dp[i] the maximum when index == i
	 * 
	 * base case num.length == 1 num.length == 2
	 * 
	 * for i >= 2 dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
	 */
	public static void test198() {
		int[] nums = { 2, 1, 1, 2 };
		int rev = task198_rob(nums);
		System.out.println("rev = " + rev);
	}

	/*
	 * Time: O(n)
	 * Space: O(n)
	 */
	public static int task198_rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] M = new int[nums.length];
		// special case
		// nums.length == 1
		if (nums.length == 1) {
			return M[0];
		}
		M[0] = nums[0];
		M[1] = Math.max(nums[0], nums[1]);

		// nums.length == 2
		if (nums.length == 2) {
			return M[1];
		}

		for (int i = 2; i < nums.length; i++) {
			M[i] = Math.max(M[i - 1], M[i - 2] + nums[i]);
		}
		return M[nums.length - 1];
	}
	
	
	/**
	 * 
	 * @param nums
	 * @return
	 * Time: O(n)
	 * Space: O(1)
	 */
	public static int task198_rob_opt(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int prev2 = 0;
		int prev1 = nums[0];
		
		for(int i = 1; i < nums.length; i++) {
			int cur = Math.max(prev2 + nums[i], prev1);
			
			// update prev2 and prev1
			prev2 = prev1;
			prev1 = cur;
		}
		return prev1;
		
	}

	/*
	 * 199 Binary Tree Right side view
	 */

	public static void test199() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(5);
		TreeNode n5 = new TreeNode(4);

		n1.left = n2;
		n1.right = n3;
		n2.right = n4;
		n3.right = n5;

		System.out.println(rightSideView(n1));

	}

	public static List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		helper(root, result, 0);
		return result;
	}

	public static void helper(TreeNode node, List<Integer> result, int level) {
		if (node == null) {
			return;
		}
		if (result.size() == level) {
			result.add(node.val);
		}
		helper(node.right, result, level + 1);
		helper(node.left, result, level + 1);

	}

	/*
	 * 200 Number of Islands also refer to UnionFind in
	 * jiuzhangAdvancedAlgorithm
	 * 
	 * 3 methods: DFS BFS union find
	 */
	public static void test200() {
		char[][] grid = { "111".toCharArray(), "000".toCharArray(),
				"111".toCharArray() };

		// int rev2 = task200_numIslands_union_find(grid);
		// System.out.println("rev2 = " + rev2);

		int rev3 = task200_numIslandBFS(grid);
		System.out.println("rev3 = " + rev3);
		int rev4 = task200_numberIslandsDFS(grid);
		System.out.println("rev4 = " + rev4);

	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	/*
	 * union find
	 * 
	 * link islands
	 */
	public static class Subset {
		int parent;
		int rank;

		public Subset(int _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
	}

	public static Subset[] createSet(int n) {
		Subset[] subsets = new Subset[n];
		for (int i = 0; i < n; i++) {
			subsets[i] = new Subset(i, 0);
		}
		return subsets;
	}

	public static int find(Subset[] subsets, int i) {
		if (subsets[i].parent != i) {
			subsets[i].parent = find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}

	public static void printSubset(Subset[] subsets) {
		for (int i = 0; i < subsets.length; i++) {
			System.out.println("[ " + i + " : " + subsets[i].parent + " :: "
					+ subsets[i].rank + "  ]");
		}
	}

	public static void union(Subset[] subsets, int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		if (subsets[xroot].rank < subsets[yroot].rank) {
			subsets[xroot].parent = yroot;
		} else if (subsets[yroot].rank < subsets[xroot].rank) {
			subsets[yroot].parent = xroot;
		} else {
			subsets[xroot].rank++;
			subsets[yroot].parent = xroot;
		}

	}

	public static int count = 0;

	public static int task200_numIslands_union_find(char[][] grid) {
		// sanity check
		if (grid == null || grid.length == 0 || grid[0] == null
				|| grid[0].length == 0) {
			return 0;
		}
		int rowLen = grid.length;
		int colLen = grid[0].length;
		Subset[] subsets = createSet(rowLen * colLen);
		System.out.println("print the subsets: ");
		printSubset(subsets);
		System.out.println("~~~~~~~~~~~~~~~~~~~");
		boolean[][] visited = new boolean[rowLen][colLen];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				if (visited[i][j]) {
					continue;
				}
				if (grid[i][j] == '1') {
					int curPos = i * colLen + j;
					list.add(curPos);
					visited[i][j] = true;
					for (int k = 0; k < 4; k++) {
						// 只合并右边和下边的元素， 因为左边和上边已经访问过
						int nextX = i + dx[k];
						int nextY = j + dy[k];
						// sanity check
						if (nextX >= 0 && nextX < rowLen && nextY >= 0
								&& nextY < colLen && grid[nextX][nextY] == '1'
								&& !visited[nextX][nextX]) {
							visited[nextX][nextY] = true;
							union(subsets, find(subsets, curPos),
									find(subsets, nextX * colLen + nextY));
						}
					}
				}
			}
		}

		// traverse the list and subsets
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < list.size(); i++) {
			find(subsets, list.get(i)); // we need to modify
			set.add(subsets[list.get(i)].parent);
		}
		System.out.println(list);
		printSubset(subsets);

		return set.size();
	}

	public static int task200_numIslandBFS(char[][] matrix) {
		// check
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		int counter = 0;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (matrix[i][j] == '1' && visited[i][j] == false) {
					counter++;
					task200_BFS(matrix, i, j, visited);
				}
			}
		}

		return counter;
	}

	public static void task200_BFS(char[][] matrix, int x, int y,
			boolean[][] visited) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		LinkedList<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			// search all neighbors
			for (int i = 0; i < 4; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen
						&& visited[nextX][nextY] == false
						&& matrix[nextX][nextY] == '1') {
					q.offer(new Pair(nextX, nextY));
					visited[nextX][nextY] = true;
				}
			}
		}
	}

	public static class Pair {
		int x;
		int y;

		public Pair(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}

	public static int task200_numberIslandsDFS(char[][] matrix) {

		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		int counter = 0;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (matrix[i][j] == '1' && visited[i][j] == false) {
					counter++;
					task200_BFS(matrix, i, j, visited);
				}
			}
		}
		return counter;
	}

	public void task200_DFS(char[][] matrix, int x, int y, boolean[][] visited) {
		if (visited[x][y]) {
			return;
		}
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX >= 0 && nextX < matrix.length && nextY >= 0
					&& nextY < matrix[0].length && !visited[nextX][nextY]
					&& matrix[nextX][nextY] == '1') {
				task200_DFS(matrix, nextX, nextY, visited);
			}
		}
	}

}

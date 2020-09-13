package class4;

import java.util.HashMap;
import java.util.HashSet;

public class TP2_fwd_Windows {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
//		test3();
//		test4();
//		test5();
//		test2();
	}
	
	/*
	 *  Minimum size subArray sum
	 *   
	 */

	public static void test5() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] array = {1,2,2,3};
		for(int i =0; i < array.length; i ++) {
			if (map.containsKey(array[i])) {
				map.put(array[i], map.get(array[i]) + 1);
			} else {
				map.put(array[i], 1);
			}
		}
		System.out.println(map.size());
	}
	
	/*
	 *  通过两层for循环改进算法 
	 *  while(i < n-1){
			while(j < n-1){ 
			if(满足条件)
			 	j++;
			    更新状态
			else (不满足条件)
			  berek;
			}
		i ++;
		更新状态
      }
	 */
	
	
	/*
	 * Minimum size subArray sum
	 * http://www.lintcode.com/en/problem/minimum-size-subarray-sum/
	 * Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] 
	 * has the minimal length under the problem constraint.
	 */
	public static void test1() {
		int[] nums = { 2, 3, 1, 2, 4, 3 };
		int s = 7;
		int rev = minimumSize(nums, s);
		System.out.println("rev = " + rev);
		System.out.println("------------");
		int rev2 = minimumSize2(nums, s);
		System.out.println("rev2 = " + rev2);

	}

	public static int minimumSize(int[] nums, int s) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int fast = 0, slow = 0;
		int curSum = 0;
		int minLength = Integer.MAX_VALUE;
		while (slow < nums.length) {
			while (fast < nums.length && curSum < s) {
				curSum += nums[fast];
				fast++;
			}
			if (curSum >= s) {
				minLength = Math.min(minLength, fast - slow);
			}
			curSum -= nums[slow];
			slow++;
		}

		if (minLength == Integer.MAX_VALUE) {
			minLength = -1;
		}
		return minLength;
	}
	
	public static int minimumSize2(int[] nums, int s) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int fast = 0, slow = 0;
		int curSum = 0;
		int minLength = Integer.MAX_VALUE;
		while(fast < nums.length) {
			curSum += nums[fast];
			
			while (slow < fast && slow < nums.length && curSum - nums[slow] >= s) {
				curSum -= nums[slow];
				slow ++;
			}
			if (curSum >= s) {
				if (minLength > fast - slow + 1) {
					minLength = fast - slow + 1;
					
					// for debug
					System.out.println("--------------");
					System.out.println("fast = " + fast);
					System.out.println("slow = " + slow);
					System.out.println("minLen = " + minLength);
					System.out.println("==============");
				}
			}
			
			fast ++;
		}
		
		System.out.println("slow = " + slow);
		System.out.println("fast = " + fast);
		return minLength;
	}

	/*
	 * longest substring without repeating characters
	 * http://www.lintcode.com/en/problem/longest-substring-without-repeating-characters/
	 * 
	 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
	 * which the length is 3.
	 * For "bbbbb" the longest substring is "b", with the length of 1.
	 * 
	 * 1. 前向型指针
	 * 2. Hash或者set记录上次访问
	 */

	public static void test2() {
		String s = "abcabcaabbcc";
		int rev1 = lengthOfLongestSubstring(s);
		int rev2 = lengthOfLongestSubstring2(s);
		System.out.println("rev1 = " + rev1);
		System.out.println("rev2 = " + rev2);
	}
	public static int lengthOfLongestSubstring(String s) {
		int slow = 0, fast = 0;
		int maxLen = 0;
		HashSet<Character> set = new HashSet<Character>();
		while (slow < s.length()) {
			while (fast < s.length() && !set.contains(s.charAt(fast))) {
				set.add(s.charAt(fast));
				fast++;
			}
			maxLen = Math.max(maxLen, fast - slow);
			set.remove(s.charAt(slow));
			slow++;

		}
		return maxLen;
	}

	public static int lengthOfLongestSubstring2(String s) {
		int slow = 0, fast = 0;
		int maxLen = 0;
		HashSet<Character> set = new HashSet<Character>();
		while(fast < s.length()) {
			if (!set.contains(s.charAt(fast))) {
				set.add(s.charAt(fast));
				maxLen = Math.max(maxLen, fast - slow + 1);
				fast ++;  // only when adding 
				
			} else {
				while(set.contains(s.charAt(fast))) {
					char slowChar = s.charAt(slow);
					set.remove(slowChar);
					slow ++;
				}
			}
			
			
		}
		return maxLen;
	}

	/*
	 * Minimum Window Substring
	 * 
	 * http://www.lintcode.com/en/problem/minimum-window-substring/
	 * For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"
	 */
	public static void test3() {
		String source = "babcdef";
		String target = "ad";

		String result2 = minWindow2(source, target);
		String result3 = minWindow3(source, target);

		System.out.println("result2 = " + result2);
		System.out.println("result3 = " + result3);
	}
	
	// another way, better
	public static String minWindow2(String source, String target) {
		int ans = Integer.MAX_VALUE;
		String minStr = "";
		
		int[] targetHash = new int[256];
		int targetNum = initTargetHash(targetHash, target);
		
		
		int sourceMatchNum = 0;
		int j = 0, i = 0;
	
		
		for(i = 0; i < source.length(); i ++) {
			if (targetHash[source.charAt(i)] > 0) {
				sourceMatchNum ++;
			}
			
			targetHash[source.charAt(i)] --;
			while(sourceMatchNum >= targetNum) {  
				// we find a subString in source which contains target string
				// shrink the subString
				if (ans > i - j + 1) {
					ans = Math.min(ans, i - j  + 1);
					minStr = source.substring(j, i + 1);
				}
				
				targetHash[source.charAt(j)] ++;
				if (targetHash[source.charAt(j)] > 0) {
					sourceMatchNum --;
				}
				j ++;
			}
		}
		
		return minStr;
	}
	
	
	// this is easier to understand
	public static String minWindow3(String source ,String target) {
		if (source == null || target == null || source.length() == 0 || target.length() == 0) {
			return null;
		}
		int[] dictHash = new int[256];
		int[] foundHash = new int[256];
		
		// fill in the dict hash
		for(int i = 0; i < target.length(); i ++) {
			dictHash[target.charAt(i)] ++;
		}
		
		int wndStart = 0, wndEnd = 0;
		int appears = 0;
		int minStart = -1;
		int minLen = Integer.MAX_VALUE;
		while(wndEnd < source.length()) {
			
			char curCh = source.charAt(wndEnd);
			if (foundHash[curCh] < dictHash[curCh]) {
				appears ++;
			}
			foundHash[curCh] ++;
			
			if (appears == target.length()) {
				// we find a window which contains the target string. 
				// now, shrink the window if possible
				// curCh2 = source[wndStart], if foundHash[curCh2] > dictHash[curCh2] || dictHash[curCh2] == 0
				char curCh2 = source.charAt(wndStart);
				while(foundHash[curCh2] > dictHash[curCh2] || dictHash[curCh2] == 0) {
					if (foundHash[curCh2] > dictHash[curCh2]) {
						foundHash[curCh2] --;
					}
					wndStart ++;
					curCh2 = source.charAt(wndStart);
				}
				if (minLen > wndEnd - wndStart + 1) {
					minLen = wndEnd - wndStart + 1;
					minStart = wndStart;
				}
				
			}

			wndEnd ++;
		}
		
		if (minLen == Integer.MAX_VALUE) {
			return null;
		}
		return source.substring(minStart, minStart + minLen);
		
		
	}
	
	public static int initTargetHash(int[] targetHash, String Target) {
		int targetNum = 0;
		for(int i = 0; i < Target.length(); i ++) {
			char cur = Target.charAt(i);
			targetNum ++;
			targetHash[cur] ++;
		}
		return targetNum;
	}
	
	
	

	/*
	 * Longest Substring with At Most K Distinct Characters
	 * http://www.lintcode.com/en/problem/longest-substring-with-at-most-k-distinct-characters/
	 * 
	 * For example, Given s = "eceba", k = 3,
	 * T is "eceb" which its length is 4.
	 */
	public static void test4() {
		String s = "nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg";
		for(int i = 0; i < s.length(); i ++) {
			System.out.println("index = " + i + "   " + "value = " + s.charAt(i));
		}
		System.out.println("---------------------");
		int k = 11;
		int rev = lengthOfLongestSubstringKDistinct(s, k);
		
		System.out.println("rev = " + rev);
	
	}
	

	
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		// write your code here
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		int slow = 0, fast = 0;
		int maxLen = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		while(fast < s.length()) {
			char fastChar = s.charAt(fast);
			if (map.containsKey(fastChar)) {
				map.put(fastChar, map.get(fastChar) + 1);
			} else {
				map.put(fastChar, 1);
				
				while (map.size() > k) {
					// remove slow
					char slowChar = s.charAt(slow);
					slow ++;
					int count = map.get(slowChar);
					if (count > 1) {
						map.put(slowChar, count - 1);
					} else {
						map.remove(slowChar);
					}
				}
			}
			maxLen = Math.max(maxLen, fast - slow + 1);
			fast ++;
		}
		return maxLen;

	}
	/*
	 * 两根指针 优化类型:
	 * 优化思想通过两层for循环而来 
	 * 慢指针依然是依次遍历 
	 * 快指针证明是否需要回退
	 */
	
}

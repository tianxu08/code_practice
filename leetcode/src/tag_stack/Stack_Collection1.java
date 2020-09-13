package tag_stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Map.Entry;

public class Stack_Collection1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * list
	 * 224 basic calculator
	 * 227. Basic Calculator II
	 * ### ExpressEvaluation see sortedbynum.ExpressEvaluation
	 * 439. Ternary Expression Parser
	 * 456 132 patterns
	 * 496. Next Greater Element I
	 * 503. Next Greater Element II
	 * 
	 */
	
	
	/*
	 * 224. Basic Calculator
	 * Implement a basic calculator to evaluate a simple expression string.
	 * The expression string may contain open ( and closing parentheses ), 
	 * the plus + or minus sign -,
	 * non-negative integers and empty spaces .
	 * You may assume that the given expression is always valid.
	 * Some examples:
	 * 
	 * "1 + 1" = 2
	 * " 2-1 + 2 " = 3
	 * "(1+(4+5+2)-3)+(6+8)" = 23
	 *
	 * refer to tag_calculator T224_BasicCalculator
	 */

	
	/**
	 * 227. Basic Calculator II
	 * evaluate a simple expression string
	 * 
	 * only non-negative integers. + - * / and empty spaces.
	 * The integer division should truncate toward zero.
	 * You may assume that the given expression is always valid.
	 * 
	 * "3+2*2" = 7
	 * " 3/2 " = 1
	 * " 3+5 / 2 " = 5
	 *
	 * refer to tag_calculator T227_BasicCalculator2
	 */
	

	
	/*
	 * 
	 * 439. Ternary Expression Parser
	 * Given a string representing arbitrarily nested ternary expressions, 
	 * calculate the result of the expression. 
	 * You can always assume that 
	 * the given expression is valid and only consists of digits 0-9, ?, :, T and F 
	 * (T and F represent True and False respectively).
	 * Note:
	 * The length of the given string is ≤ 10000.
	 * Each number will contain only one digit.
	 * The conditional expressions group right-to-left (as usual in most languages).
	 * The condition will always be either T or F. That is, the condition will never be a digit.
	 * The result of the expression will always evaluate to either a digit 0-9, T or F.
	 */
	
	/**
	 * 
	 * 如果有多个三元表达式嵌套的情况出现，那么我们的做法是从右边开始找到第一个问号，
	 * 然后先处理这个三元表达式，然后再一步一步向左推，这也符合程序是从右向左执行的特点。
	 * 那么我最先想到的方法是用用一个stack来记录所有问号的位置，然后根据此问号的位置，
	 * 取出当前的三元表达式，调用一个eval函数来分析得到结果，
	 * 能这样做的原因是题目中限定了三元表达式每一部分只有一个字符，而且需要分析的三元表达式是合法的，
	 * 然后我们把分析后的结果和前后两段拼接成一个新的字符串，继续处理之前一个问号，这样当所有问号处理完成后，
	 * 所剩的一个字符就是答案
	 * @param expression
	 * @return
	 * 
	 * assume the expression is valid
	 */
	
	public static void test439() {
		String expression = "T?T?F:5:3";
		String rev = task439_parseTernary(expression);
		System.out.println("rev= " +rev );
	}
    public static String task439_parseTernary(String expression) {
    	if (expression == null || expression.length() == 0) {
			return expression;
		}
        String res = expression;
        // store the index of '?'
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < expression.length(); i++) {
        	if (expression.charAt(i) == '?') {
				st.push(i);
			}
        }
        
        while(!st.isEmpty()) {
        	int index = st.pop();
        	int cur_exp_start = index - 1;
        	int cur_exp_end = index - 1 + 5;
        	String cur_exp = res.substring(cur_exp_start, cur_exp_end);
        	String cur_val = task439_eval(cur_exp);
        	res = res.substring(0, cur_exp_start) + cur_val + res.substring(cur_exp_end);
        }
        return res;
    }
    
    public static String task439_eval(String s) {
    	if (s.length() != 5) {
			return "";
		}
    	if (s.charAt(0) == 'T') {
			return s.substring(2, 3);
		} else {
			return s.substring(4);
		}
    }
    
    /**
     * 下面这种方法也是利用栈stack的思想，但是不同之处在于不是存问号的位置，而是存所有的字符，
     * 将原数组从后往前遍历，将遍历到的字符都压入栈中，我们检测如果栈首元素是问号，说明我们当前遍历到的字符是T或F，
     * 然后我们移除问号，再取出第一部分，再移除冒号，再取出第二部分，我们根据当前字符来判断是放哪一部分进栈，
     * 这样遍历完成后，所有问号都处理完了，剩下的栈顶元素即为所求
     */
    public static String task439_parseTenary2(String expression) {
    	if (expression == null || expression.length() == 0) {
			return expression;
		}
    	Stack<Character> st = new Stack<Character>();
    	for (int i = expression.length() - 1; i >= 0; i--) {
    		char cur = expression.charAt(i);
    		if (!st.isEmpty() && st.peek() == '?') {
				// the prev is '?'
    			// the cur must be 'T' or 'F'
    			// pop the '?'
    			st.pop();
    			// get the first char
    			Character firstCh = st.pop();
    			// pop the ':'
    			st.pop();
    			// get the second char
    			Character secondCh = st.pop();
    			// push the cur result into stack
    			st.push(cur == 'T' ? firstCh : secondCh);
			} else {
				// other char
				st.push(cur);
			}
    	}
    	
    	return String.valueOf(st.pop());
    }
    
    
    /*
	 * task456
	 * 132 patterns
	 * Given a sequence of n integers a1, a2, ..., an, 
	 * a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
	 * Design an algorithm that takes a list of n numbers as input and checks 
	 * whether there is a 132 pattern in the list.
	 * Note: n will be less than 15,000.
	 * Example 1:
	 * Input: [1, 2, 3, 4]
	 * Output: False
	 * 
	 * Explanation: There is no 132 pattern in the sequence.
	 * Example 2:
	 * Input: [3, 1, 4, 2]
	 * Output: True
	 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
	 * Example 3:
	 * Input: [-1, 3, 2, 0]
	 * Output: True
	 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
	 * 
	 * 维护一个栈和一个变量third，其中third就是第三个数字，也是pattern 132中的2，
	 * 栈里面按顺序放所有大于third的数字，也是pattern 132中的3，
	 * 那么我们在遍历的时候，如果当前数字小于third，即pattern 132中的1找到了，
	 * 我们直接返回true即可，因为已经找到了，注意我们应该从后往前遍历数组。
	 * 如果当前数字大于栈顶元素，那么我们按顺序将栈顶数字取出，赋值给third，然后将该数字压入栈，
	 * 这样保证了栈里的元素仍然都是大于third的，我们想要的顺序依旧存在，进一步来说，
	 * 栈里存放的都是可以维持second > third的second值，其中的任何一个值都是大于当前的third值，
	 * 如果有更大的值进来，那就等于形成了一个更优的second > third的这样一个组合，
	 * 并且这时弹出的third值比以前的third值更大，为什么要保证third值更大，
	 * 因为这样才可以更容易的满足当前的值first比third值小这个条件
	 */
	public static boolean task456_find132pattern(int[] nums) {
		if (nums == null || nums.length < 3) {
			return false;
		}
		// store all elements that greater than the "third"
		Stack<Integer> st = new Stack<Integer>();
		int third = Integer.MIN_VALUE;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] < third) {
				return true;
			} else {
				while (!st.isEmpty() && nums[i] > st.peek()) {
					// update the third
					// we want to make the third as large as possible.
					third = st.pop();
				}
				// push the nums[i] into stack
				st.push(nums[i]);
			}
		}
		return false;
	}

	
	/*
	 * task496
	 *
	 * 496. Next Greater Element I
	 * 
	 * keep a decreasing order of nums in the stack. 
	 * 6 5 4 3 2 1  if there is 7, then 7 is the next greater element of all the previous 
	 * element.
	 * 
	 * also see 503
	 * Time: O(n)
	 * Space: O(n)
	 */
	public static void test496(){
		int[] findNums = {4,1,2};
		int[] nums = {1,3,4,2};
		int[] rev = task496_nextGreaterElement(findNums, nums);
		System.out.println(Arrays.toString(rev));	
	}
	
	
	public static int[] task496_nextGreaterElement(int[] findNums, int[] nums) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		// key: value ==> element, nextGreat Element
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(Integer cur : nums) {
			while(!stack.isEmpty() && stack.peekFirst() < cur) {
				int popElem = stack.pollFirst();
				map.put(popElem, cur);
			}
			stack.offerFirst(cur);
		}
		
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		// traverse the findNum and get the result
		int len = findNums.length;
		int[] rev = new int[len];
		
		for(int i = 0; i < len; i ++) {
			if (map.containsKey(findNums[i])) {
				rev[i] = map.get(findNums[i]);
			} else {
				rev[i] = -1;
			}
		}
		return rev;
	}
	
	
	
	/**
	 * 503. Next Greater Element II
	 * 
	 * Given a circular array (the next element of the last element is the first element of the array), 
	 * print the Next Greater Number for every element. 
	 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
	 * which means you could search circularly to find its next greater number. 
	 * If it doesn't exist, output -1 for this number.
	 * 栈来进行优化上面的算法，我们遍历两倍的数组，然后还是坐标i对n取余，
	 * 取出数字，如果此时栈不为空，且栈顶元素小于当前数字，说明当前数字就是栈顶元素的右边第一个较大数，
	 * 那么建立二者的映射，并且去除当前栈顶元素，最后如果i小于n，则把i压入栈。
	 * 因为res的长度必须是n，超过n的部分我们只是为了给之前栈中的数字找较大值，所以不能压入栈
	 * @param nums
	 * @return
	 */
	public static void test503() {
		int[] nums = {1, 3, 2, 4, 3, 5, 2};
		int[] rev = task503_nextGreaterElements_opt(nums);
		System.out.println(Arrays.toString(rev));
	}
	
	public static int[] task503_nextGreaterElements_opt(int[] nums) {
		if (nums == null) {
			return null;
		}
		int n = nums.length;
		Stack<Integer> st = new Stack<Integer>();
		int[] res = new int[n];
		Arrays.fill(res, -1);
		for (int i = 0; i < 2 * n; i++ ){
			int num = nums[i % n];
			
			while (!st.isEmpty() && nums[st.peek()] < num) {
				// !!! set res[st.peek()]  set stack 所指的index 的元素
				res[st.peek()] = num;
				st.pop();
			}
			if (i < n) {
				st.push(i);
			}
			// for debug
			System.out.println(st);
		}
		return res;
	}
	
	
}

package mj_linkedin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedIntegerImpTest {

	
	/*
	 * Given a nested list of integers, returns the sum of all integers in the list weighted by their depth 
	 * For example, given the list {{1,1},2,{1,1}} the function should return 10 
	 * (four 1's at depth 2, one 2 at depth 1) 
	 * Given the list {1,{4,{6}}} the function should return 27 
	 * (one 1 at depth 1, one 4 at depth 2, one 6 at depth2) 
	 * 
	 * http://yuanhsh.iteye.com/blog/2192549
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
	}
	
	public int sumOfNestedInteger(NestedInteger nest) {
		if (nest.isInteger()) {
			return nest.getInteger();
		}
		
		return helper(nest.getList(), 1);
	}
	
	
	private int helper(List<NestedInteger> list, int depth) {
		int sum = 0;
		for(NestedInteger item : list) {
			if (item.isInteger()) {
				sum += item.getInteger() * depth;
			} else {
				sum += helper(item.getList(), depth + 1);
			}
		}
		return sum;
	}
	
	/*
	 * 
	 * Follow Up:
	 * 说改成return the sum of all integers in the list weighted by their “reversed depth”.
	 * 也就是说{{1,1},2,{1,1}}的结果是(1+1+1+1)*1+2*2=8
	 * 思路：
	 * 需要两个变量计数，sum 与 prev
	 * 例子 {{2,2,{3}},1,{2,2}}
	 * 一共三层
	 * 第一层 prev = 1 sum=1
	 * 第二层 prev =prev+2+2+2+2  最后prev =9， sum = 10
	 * 第三层 prev =prev +3      prev = 12        sum =22
	 * 理论结果   3+2*2*4+1*3 =22
	 * 
	 * 核心就是， 最里层的3, 只加一遍， 2 在从里边数第二层，就加两次， 1 在从里边数 第三层，就加3次
	 */
	
	
	/**
	 * 
	 * @param ni
	 * @return
	 * DO a BFS
	 * 
	 * {{2,2,{3}},1,{2,2}}
	 * 
	 * First Level of Queue: {2,2,{3}}, 1, {2,2}  levelSum = 1  prev = 1    // 这里的数字将被加3次， 通过prev 来实现
	 * Next Level of Queue: 2,2, {3}, 2, 2        levelSum = 2 + 2 + 2 + 2 = 8, prev = 1 + 8 = 9
	 * Next Level of Queue: 3                     LevelSum = 3       prev = 9 + 3 = 12
	 * 
	 * Total: 1 + 9 + 12 = 22
	 * 
	 */
	public int sumOfReversedWeight(NestedInteger ni) {  
	    if(ni.isInteger()) {  
	        return ni.getInteger();  
	    }  
	    int prev = 0, sum = 0;  
	    List<NestedInteger> cur = ni.getList();  
	    List<NestedInteger> next = new ArrayList<NestedInteger>();  
	    while(!cur.isEmpty()) {  
	        for(NestedInteger item:cur) {  
	            if(item.isInteger()) {  
	                prev = prev +  item.getInteger();  // prev is accumulated.   
	            } else {  
	                next.addAll(item.getList());  
	            }  
	            // sum is the final result
	            sum += prev;  // add prev to sum
	            cur = next;  
	            
	            next = new ArrayList<NestedInteger>();  
	        }  
	    }  
	    return sum;  
	} 
	
	public int depthSumInverse(List<NestedInteger> nestedList) {
		if (nestedList == null) {
			return 0;
		}
		Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
		int prev = 0;
		int total = 0;
		
		// add all nested integer into queue
		for(NestedInteger item: nestedList) {
			queue.offer(item);
		}
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			int levelSum = 0;
			for(int i = 0; i < size; i++) {
				// get the current 
				NestedInteger cur = queue.poll();
				if (cur.isInteger()) {
					levelSum += cur.getInteger();
				}
				List<NestedInteger> nextList = cur.getList();
				if (nextList != null) {
					for(NestedInteger item: nextList) {
						// add all item to the queue
						queue.offer(item);
					}
				}
			}
			prev += levelSum;
			total += prev;
		}
		return total;
	}
	
	
	
	
	
	public static void test() {
		String cand = "(1,1,(1,1))";
		int rev = getSum(cand);
		System.out.println("cand = " + rev);
		int rev2 = getNum2(cand, 1);
		System.out.println(rev2);
		
	}
	
	/*
	 * the following method only works well for (2(3(6)))
	 */
	
	public static int getSum(String cand) {
		if (cand == null || cand.length() == 0) {
			return 0;
		}
		return helper(cand, 1, 0);
	}
	
	public static int helper(String cand, int level, int index) {
		
		if (cand.charAt(index) == ')') {
			return 0;
		}
		if (cand.charAt(index) == '(') {
			index ++;
		}
		
		boolean isNeg = false;
		if (cand.charAt(index) == '-') {
			isNeg = true;
			index ++;
		}
		int val = 0;
		for(; index < cand.length(); index ++) {
			if (!Character.isDigit(cand.charAt(index))) {
				break;
			}
			val = val * 10 + cand.charAt(index) - '0';
		}
		val = val * level;
		if (isNeg) {
			val = -val;
		}
		return val + helper(cand, level + 1, index);
	}
	
	
	public static int getNum2(String s, int level) {
		if (s.length() == 0) {
			return 0;
		}
		int i = 0;
		while(i < s.length() && !isNum(s.charAt(i))) {
			i ++;
		}
		int j = i;
		while(j < s.length() && !isNum(s.charAt(j))) {
			j ++;
		}
		return (s.substring(i, j).equals("") ? 0 : Integer.parseInt(s.substring(i, j)) * level) + 
				getNum2(s.substring(j), level + 1);
				
	}
	
	
	public static boolean isNum(char c) {
		return c >= '0' && c <= '9';
	}
	
	

}

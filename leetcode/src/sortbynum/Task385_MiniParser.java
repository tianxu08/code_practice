package sortbynum;

import java.util.*;

public class Task385_MiniParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task385_MiniParser sln = new Task385_MiniParser();
		String s = "[123,[456,[789]]]";
		NestedInteger rev = sln.deserialize(s);
		String printStr = rev.printNi(rev, new StringBuilder());
		System.out.println(printStr);
	}

	public NestedInteger deserialize(String s) {
		if (s.isEmpty()) {
			return null;
		}
		if (s.charAt(0) != '[') {
			// only a number
			return new NestedInteger(Integer.valueOf(s));
		}
			

		Stack<NestedInteger> stack = new Stack<>();
		NestedInteger curNested = null;
		// slow shall point to the start of a number substring;
		// fast shall point to the end+1 of a number substring
		int slow = 0; 
		for (int fast = 0; fast < s.length(); fast++) {
			char ch = s.charAt(fast);
			if (ch == '[') {
				// start a new NestedInteger 
				// puth the curNested into stack
				if (curNested != null) {
					stack.push(curNested);
				}
				curNested = new NestedInteger();
				// update slow
				slow = fast + 1;
			} else if (ch == ']') {
				// get the number
				String num = s.substring(slow, fast);
				if (!num.isEmpty()) {
					curNested.add(new NestedInteger(Integer.valueOf(num)));
				}
				if (!stack.isEmpty()) {
					// get the previous pushed NestedInteger
					NestedInteger popNested = stack.pop();
					popNested.add(curNested);
					// update the curNested to pop
					curNested = popNested;
				}
				// update slow
				slow = fast + 1;
			} else if (ch == ',') {
				if (s.charAt(fast - 1) != ']') {
					String num = s.substring(slow, fast);
					curNested.add(new NestedInteger(Integer.valueOf(num)));
				}
				slow = fast + 1;
			}
		}
		return curNested;
	}

	public class NestedInteger {
		private List<NestedInteger> list;
		private Integer integer;

		public NestedInteger(List<NestedInteger> list) {
			this.list = list;
		}

		public void add(NestedInteger nestedInteger) {
			if (this.list != null) {
				this.list.add(nestedInteger);
			} else {
				this.list = new ArrayList<NestedInteger>();
				this.list.add(nestedInteger);
			}
		}

		public void setInteger(int num) {
			this.integer = num;
		}

		public NestedInteger(Integer integer) {
			this.integer = integer;
		}

		public NestedInteger() {
			this.list = new ArrayList<NestedInteger>();
		}

		public boolean isInteger() {
			return integer != null;
		}

		public Integer getInteger() {
			return integer;
		}

		public List<NestedInteger> getList() {
			return list;
		}

		public String printNi(NestedInteger thisNi, StringBuilder sb) {
			if (thisNi.isInteger()) {
				sb.append(thisNi.integer);
				sb.append(",");
			}
			sb.append("[");
			for (NestedInteger ni : thisNi.list) {
				if (ni.isInteger()) {
					sb.append(ni.integer);
					sb.append(",");
				} else {
					printNi(ni, sb);
				}
			}
			sb.append("]");
			return sb.toString();
		}
	};

}

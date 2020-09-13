package tag_calculator;

import java.util.Stack;

public class T224_BasicCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/**
	 * task224:
	 * Implement a basic calculator to evaluate a simple expression string.
	 * The expression string may contain open ( and closing parentheses ), 
	 * the plus + or minus sign -, non-negative integers and empty spaces
	 *
	 *
	 */
	
	public static void test() {
		String s = "2 - 1 + 2";
		int rev = calculate(s);
		System.out.println("rev = " + rev);
	}

	public static int calculate2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < s.length(); i++) {

		}
		return res;
	}


	public static int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		Stack<Integer> numSt = new Stack<>();
		Stack<Character> opSt = new Stack<>();
		
		for (int i = 0; i < s.length(); i++) {
			char token = s.charAt(i);
			if (token == ' ') continue;
			if (Character.isDigit(token)) {
				int curNum = token - '0';
				while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
					curNum = curNum * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				numSt.push(curNum);
			} else if (isOp(token)) {
				while (!opSt.isEmpty() && isOp(opSt.peek())) {
					char op = opSt.pop();
					int num2 = numSt.pop();
					int num1 = numSt.pop();
					
					int res = applyOpAndNums(num1, num2, op);
					numSt.push(res);
				}
				// push op into opSt
				opSt.push(token);
			} else if (token == '(') {
				opSt.push(token);
			} else if (token == ')') {
				while(!opSt.isEmpty() && isOp(opSt.peek())) {
					char op = opSt.pop();
					int num2 = numSt.pop();
					int num1 = numSt.pop();
					
					int res = applyOpAndNums(num1, num2, op);
					numSt.push(res);
				}
				// pop '('
				opSt.pop();
			}
			System.out.println("--------------------");
			System.out.println("op : " + opSt);
			System.out.println("num: " + numSt);
			System.out.println("--------------------");
		}
		
		while(!opSt.isEmpty()) {
			char op = opSt.pop();
			int num2 = numSt.pop();
			int num1 = numSt.pop();
			
			int res = applyOpAndNums(num1, num2, op);
			numSt.push(res);
		}
		return numSt.size() == 1 ? numSt.peek() : -1;
		
	}
	
	
	public static int applyOpAndNums(int num1, int num2, char op) {
		if (op == '+') {
			return num1 + num2;
		} else if (op == '-') {
			return num1 - num2;
		}
		return 0;
	}
	public static boolean isOp(char op) {
		return op == '+' || op == '-';
	}
	
}

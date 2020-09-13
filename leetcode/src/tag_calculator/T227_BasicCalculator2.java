package tag_calculator;

import java.util.Stack;

public class T227_BasicCalculator2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/**
	 * t227
	 * Implement a basic calculator to evaluate a simple expression string.
	 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
	 * The integer division should truncate toward zero.
	 *
	 */

	public static void test() {
		String input = "3+5 / 2 ";
		int rev = calculate(input);
		System.out.println("rev = " + rev);
	}

	public static int calculate(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}

		Stack<Integer> numStack = new Stack<Integer>();
		Stack<Character> opStack = new Stack<Character>();
		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			if (cur == ' ') {
				continue;
			}
			if (Character.isDigit(cur)) {
				int num = cur - '0';
				while (i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
					num = num * 10 + (input.charAt(i + 1) - '0');
					i ++;
				}
				numStack.push(num);
			} else if (isOp(cur)) {
				while (!opStack.isEmpty() && hasHigherOrSamePrecedence(opStack.peek(), cur)) {
					char op = opStack.pop();
					int num2 = numStack.pop();
					int num1 = numStack.pop();
					int num = apply(num1, num2, op);
					numStack.push(num);
				}
				opStack.push(cur);
			}
		}
		while (!opStack.isEmpty()) {
			char op = opStack.pop();
			int num2 = numStack.pop();
			int num1 = numStack.pop();
			int num = apply(num1, num2, op);
			numStack.push(num);
		}

		return numStack.size() == 1 ? numStack.peek() : - 1;
	}

	public static boolean isOp(char op) {
		return op == '+' || op == '-' || op == '*' || op == '/';
	}

	public static int apply(int num1, int num2, char op) {
		if (op == '+') {
			return num1 + num2;
		} else if (op == '-') {
			return num1 - num2;
		} else if (op == '*') {
			return num1 * num2;
		} else if (op == '/') {
			if (num2 == 0) {
				throw new ArithmeticException();
			}
			return num1 / num2;
		} else {
			return Integer.MAX_VALUE;
		}

	}

	// return true if op1 has highter or same precedence as op2
	public static boolean hasHigherOrSamePrecedence(char op1, char op2) {
		if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
			return false;
		}
		return true;
	}

}

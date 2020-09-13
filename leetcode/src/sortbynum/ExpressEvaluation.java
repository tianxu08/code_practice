package sortbynum;

import java.util.*;

public class ExpressEvaluation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/expression-evaluation/
	 */
	
	/*
	 * 
1. While there are still tokens to be read in,
   1.1 Get the next token.
   1.2 If the token is:
       1.2.1 A number: push it onto the value stack.
       1.2.2 A variable: get its value, and push onto the value stack.
       1.2.3 A left parenthesis: push it onto the operator stack.
       1.2.4 A right parenthesis:
         1 While the thing on top of the operator stack is not a 
           left parenthesis,
             1 Pop the operator from the operator stack.
             2 Pop the value stack twice, getting two operands.
             3 Apply the operator to the operands, in the correct order.
             4 Push the result onto the value stack.
         2 Pop the left parenthesis from the operator stack, and discard it.
       1.2.5 An operator (call it thisOp):
         1 While the operator stack is not empty, and the top thing on the
           operator stack has the same or greater precedence as thisOp,
           1 Pop the operator from the operator stack.
           2 Pop the value stack twice, getting two operands.
           3 Apply the operator to the operands, in the correct order.
           4 Push the result onto the value stack.
         2 Push thisOp onto the operator stack.
2. While the operator stack is not empty,
    1 Pop the operator from the operator stack.
    2 Pop the value stack twice, getting two operands.
    3 Apply the operator to the operands, in the correct order.
    4 Push the result onto the value stack.
3. At this point the operator stack should be empty, and the value
   stack should have only one value in it, which is the final result.
	 * 
	 */
	
	public static void test() {
		String a = "((10+ 2 -1))*(8)";
		int rev = evaluate(a);
		System.out.println("rev = " + rev);
	}
	
	public static int evaluate(String expression) {
		char[] tokens = expression.toCharArray();

		// Stack for numbers: 'value_st'
		Stack<Integer> value_st = new Stack<Integer>();

		// Stack for Operators: 'op_st'
		Stack<Character> op_st = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {
			// Current token is a whitespace, skip it
			if (tokens[i] == ' ') {
				continue;
			}
			else if (Character.isDigit(tokens[i])) {
				// here use i + 1 < token.length to get the whole number
				// this can avoid skiping the operator
				// like: 123+
				// Current token is a number, push it to stack for numbers
				int num = tokens[i] - '0';
				// There may be more than one digits in number
				while (i + 1 < tokens.length && Character.isDigit(tokens[i + 1])) {	
					num = num * 10 + (int)(tokens[i+1] - '0');
					i++;
				}
				value_st.push(num);
			}
			else if (tokens[i] == '(') {
				// Current token is an opening brace, push it to 'op_st'	
				op_st.push(tokens[i]);
			}
			else if (tokens[i] == ')') {
				// Closing brace encountered, solve entire brace
				while (op_st.peek() != '(') {
					// pop the operator from the operator stack
					char op = op_st.pop();
					// pop the value stack twice
					int val2 = value_st.pop();
					int val1 = value_st.pop();
					// apply the operator to the two operands
					int cur_val = applyOp(op, val1, val2);
					// push the new value into value_st
					value_st.push(cur_val);
				}
				
				// pop the '(' from the operator stack and discard it. 
				op_st.pop();
			} else if (isOp(tokens[i])) {
				// Current token is an operator.
				// While top of 'op_st'(previous operator) has same or greater precedence to current
				// token, which is an operator. Apply operator on top of 'op_st'(previous operator)
				// to top two elements in value_st stack
				while (!op_st.empty() && hasPrecedence(tokens[i], op_st.peek())) {
					char op = op_st.pop();
					int val2 = value_st.pop();
					int val1 = value_st.pop();
					int cur_val = applyOp(op, val1, val2);
					value_st.push(cur_val);
				}

				// Push current token to 'op_st'.
				op_st.push(tokens[i]);
			}
		}

		// Entire expression has been parsed at this point, apply remaining
		// op_st to remaining value_st
		if (value_st.size() == 1) {
			return value_st.peek();
		}
		
		while (!op_st.empty()) {
			// there are at least two elements in the value_st
			if (value_st.size() > 1) {
				char op = op_st.pop();
				int val2 = value_st.pop();
				int val1 = value_st.pop();
				int cur_val = applyOp(op, val1, val2);
				value_st.push(cur_val);
			} else {
				// there is only one element in the value stack
				return value_st.pop();
			}
		}
		
		// Top of 'value_st' contains result, return it
		return value_st.pop();
	}

	// Returns true if 'op2' has higher or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')') {
			return false;
		}
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
			return false;
		}
		else {
			return true;
		}
	}

	public static boolean isOp(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}
	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static int applyOp(char op, int a, int b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}
	
	
	
	
	

}

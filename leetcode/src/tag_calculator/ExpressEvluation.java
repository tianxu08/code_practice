package tag_calculator;

import java.util.Stack;

public class ExpressEvluation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	/**
	 * get the result from a a expression. the expression contains + - "*"/ ( )
	 * 
	 *  numSt: number
	 *  opSt: operator and (
	 *  
	 *  get next token
	 *  1   token is number, push into numSt
	 *  
	 *  2   token is '(', push '(' into opSt
	 *  3   token is ')':
	 *  	    while opSt.peek() != '('
	 *      		op = opSt.pop()
	 *      		num2 = numSt.pop()
	 *      		num1 = numSt.pop()
	 *      		res = num1 (op) num2
	 *      		numSt.push(res)
	 *      opSt.pop() // pop out the '('
	 *  4   token is an operator (thisOp)
	 *      while opSt is not empty and opSt.peek() is an op && opSt.peek() has same or higher precedence as thisOp
	 *          op = opSt.pop()
	 *          num2 = numSt.pop()
	 *          num1 = numSt.pop()
	 *          res = num1 (op) num2
	 *          numSt.push(res)
	 *      opSt.push(thisOP)
	 *  
	 *  while opSt is not empty
	 *     num2 = numSt.pop()
	 *     num1 = numSt.pop()
	 *     res = num1 (oP) num2
	 *     numSt.push(res)
	 *
	 */
	
	public static void test1() {
		String str = "100 *( 2 + 12)";
		int rev = evluate(str);
		System.out.println("=> rev = " + rev);
	}
	 public static int evluate(String str) {
		 // santiy check
		 if (str == null || str.length() == 0) {
			 return 0;
		 } 
		 Stack<Integer> numSt = new Stack<>();
		 Stack<Character> opSt = new Stack<>();
		 
		 char[] input = str.toCharArray();
		 for (int i = 0; i < input.length; i++) {
			 if (input[i] == ' ') {
				 continue;
			 }
			 if (Character.isDigit(input[i])) {
				 int curNum = input[i] - '0';
				 while(i + 1 < input.length && Character.isDigit(input[i + 1])) {
					 curNum = curNum * 10 + (input[i + 1] - '0');
					 i++;
				 }
				 numSt.push(curNum);
			 } else if (isOp(input[i])) {
				 while(!opSt.isEmpty() && isOp(opSt.peek()) && hasHigherOrSamePrecedence(opSt.peek(), input[i])) {
					 char op = opSt.pop();
					 int num2 = numSt.pop();
					 int num1 = numSt.pop();  // 不要写反 ！！！！！！
					 
					 int res = applyOpWithNums(num1, num2, op);
					 numSt.push(res);
				 }
				 // push op into opSt
				 opSt.push(input[i]);
			 } else if (input[i] == '(') {
				 opSt.push(input[i]);
			 } else if (input[i] == ')') {
				 while(!opSt.isEmpty() && opSt.peek() != '(') {
					 char op = opSt.pop();
					 int num2 = numSt.pop();
					 int num1 = numSt.pop();
					 int res = applyOpWithNums(num1, num2, op);
					 numSt.push(res);
				 }
				 // pop '(' out
				 opSt.pop();
			 }
		 }
		 
		 while(!opSt.isEmpty()) {
			 char op = opSt.pop();
			 int num2 = numSt.pop();
			 int num1 = numSt.pop();
			 int res = applyOpWithNums(num1, num2, op);
			 numSt.push(res);
		 }

		 return numSt.size() == 1 ? numSt.peek() : -1;
	 }
	 
	 public static boolean isOp(char ch) {
		 return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	 }
	 
	 // return true if op1 has highter or same precedence as op2
	 public static boolean hasHigherOrSamePrecedence(char op1, char op2) {
		 if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
			 return false;
		 }
		 return true;
	 }
	 
	 public static int applyOpWithNums(int num1, int num2, char op) {
		 if (op == '+') {
			 return num1 + num2;
		 } else if (op == '-') {
			 return num1 - num2;
		 } else if (op == '*') {
			 return num1 * num2;
		 } else if (op == '/'){
			 return num1 / num2;
		 }
		 return -1;
	 }
}

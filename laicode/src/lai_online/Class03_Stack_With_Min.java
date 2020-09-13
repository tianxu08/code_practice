package lai_online;

import java.util.LinkedList;


/*
 * offer: 5 3 8 1 2
 * min:   5 3 3 1 1
 * 
 * pop:   
 */

public class Class03_Stack_With_Min {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private LinkedList<Integer> stack;
	private LinkedList<Integer> min;
	public Class03_Stack_With_Min() {
		stack = new LinkedList<Integer>();
		min = new LinkedList<Integer>();
	}
	public Integer pop() {
		if (stack.isEmpty()) {
			return null;
		} else {
			Integer elem = stack.pollFirst();
			min.pollFirst();
			return elem;
		}
		
	}

	public void push(int element) {
		stack.offerFirst(element);
		if (min.isEmpty() || min.peekFirst() > element) {
			min.offerFirst(element);
		} else {
			min.offerFirst(min.peekFirst());
		}
	}

	public Integer top() {
		if (stack.isEmpty()) {
			return null;
		} else {
			return stack.peekFirst();
		}
	}

	public Integer min() {
		return min.peekFirst();
	}

}

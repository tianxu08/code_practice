package lai_online2;

import java.util.*;

public class Class3_8_StackWithMin {

	private LinkedList<Integer> stack;
	private LinkedList<Integer> min;

	public Class3_8_StackWithMin() {
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

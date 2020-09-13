package lai_online2;
import java.util.*;
public class Class3_7_Queue_By_Two_Stack {
	private LinkedList<Integer> stack1;
	private LinkedList<Integer> stack2;

	public Class3_7_Queue_By_Two_Stack() {
		// Write your solution here.
		stack1 = new LinkedList<Integer>();
		stack2 = new LinkedList<Integer>();
	}

	public Integer poll() {
		if (!stack2.isEmpty()) {
			return stack2.pollFirst();
		} else {
			while (!stack1.isEmpty()) {
				stack2.offerFirst(stack1.poll());
			}
			return stack2.pollFirst();
		}
	}

	public void offer(int element) {
		stack1.offerFirst(element);
	}

	public Integer peek() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.offerFirst(stack1.pollFirst());
			}
		}
		return stack2.peekFirst();
	}

	public int size() {
		return stack1.size() + stack2.size();
	}

	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}
}

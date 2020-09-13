package lai_online;

import java.util.LinkedList;

public class Class03_queue_by_2_stacks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class03_queue_by_2_stacks sln = new Class03_queue_by_2_stacks();
		for (int i = 0; i < 5; i++) {
			sln.offer(i);
		}

		System.out.println("---------");
		System.out.println(sln.isEmpty());

		System.out.println(sln.size());

		while (!sln.isEmpty()) {
			System.out.println(sln.poll());
		}
		System.out.println("---------");
		System.out.println(sln.isEmpty());

		System.out.println(sln.size());
	}

	private LinkedList<Integer> stack1;
	private LinkedList<Integer> stack2;

	public Class03_queue_by_2_stacks() {
		stack1 = new LinkedList<Integer>();
		stack2 = new LinkedList<Integer>();
	}

	public void offer(Integer element) {
		stack1.offerFirst(element);
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

	public int size() {
		return stack1.size() + stack2.size();
	}

	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	public Integer peek() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.offerFirst(stack1.pollFirst());
			}
		}
		return stack2.peekFirst();
	}

}

package sortbynum;
import java.util.*;
public class Task379_PhoneDirectory {

	/**
	 * 
	 * @param args
	 *            Design a Phone Directory which supports the following
	 *            operations: get: Provide a number which is not assigned to
	 *            anyone. check: Check if a number is available or not. release:
	 *            Recycle or release a number.
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Set<Integer> used = new HashSet<Integer>();
	Queue<Integer> available = new LinkedList<Integer>();
	int max;

	public Task379_PhoneDirectory(int maxNumbers) {
		max = maxNumbers;
		for (int i = 0; i < maxNumbers; i++) {
			available.offer(i);
		}
	}

	public int get() {
		Integer ret = available.poll();
		if (ret == null) {
			return -1;
		}
		used.add(ret);
		return ret;
	}

	public boolean check(int number) {
		if (number >= max || number < 0) {
			return false;
		}
		return !used.contains(number);
	}

	public void release(int number) {
		if (used.remove(number)) {
			available.offer(number);
		}
	}

}

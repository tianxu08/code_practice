package mj_linkedin;

import java.util.Iterator;
import java.util.List;
import java.util.*;

public class MergeKSortedIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(3);
		a.add(5);

		List<Integer> b = new ArrayList<Integer>();
		b.add(2);
		b.add(4);

		List<Iterator<Integer>> iterators = new ArrayList<Iterator<Integer>>();
		iterators.add(a.iterator());
		iterators.add(b.iterator());

		Iterable<Integer> result = mergeKSortedIteratorsImp(iterators);

		for (Integer num : result) {
			System.out.println(num);
		}

	}

	/*
	 * Interface是这个 Iterable<Integer> mergeKSortedIterators(Iterators[] iters)
	 * 也就是给每个array的iterator,然后merge. 
	 * 
	 * Solution: The problem looks straight-forward: just to a minHeap and continuously poll and add
	 * elements into the heap. 
	 * The only pitfall for this problem is the input is
	 * Iterators. When we compare values in the heap, if we use next(), we might
	 * lose the next() value. Thus we need to defined a customized class to
	 * store the value.
	 * http://buttercola.blogspot.com/2015/11/linkedin-merge-k-sorted-iterators.html
	 */
	public static Iterable<Integer> mergeKSortedIteratorsImp(
			List<Iterator<Integer>> iterators) {
		List<Integer> result = new ArrayList<Integer>();
		if (iterators == null || iterators.size() == 0) {
			return result;
		}

		PriorityQueue<MyIterator> pq = new PriorityQueue<MyIterator>(
				iterators.size());

		for (Iterator<Integer> iterator : iterators) {
			if (iterator.hasNext()) {
				pq.add(new MyIterator(iterator.next(), iterator));
			}
		}

		while (!pq.isEmpty()) {
			MyIterator curr = pq.poll();
			result.add(curr.val);
			if (curr.hasNext()) {
				pq.add(curr);
			}
		}

		return result;
	}

	private static class MyIterator implements Comparable<MyIterator> {
		private Integer val;
		private Iterator<Integer> iterator;

		public MyIterator(Integer val, Iterator<Integer> iterator) {
			this.val = val;
			this.iterator = iterator;
		}

		public boolean hasNext() {
			if (iterator.hasNext()) {
				val = iterator.next();
				return true;
			}

			return false;
		}

		public int compareTo(MyIterator that) {
			return this.val - that.val;
		}
	}

}

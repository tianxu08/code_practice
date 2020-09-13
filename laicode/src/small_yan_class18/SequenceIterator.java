package small_yan_class18;

import java.util.Iterator;

public class SequenceIterator implements Iterator<Integer> {

	/*
	 * input: 
	 * { 
	 * {1, 2, 3}, 
	 * {}, 
	 * {4}, 
	 * {}, 
	 * {5, 6} 
	 * } 
	 * output: 1, 2, 3, 4, 5, 6.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] array = { {}, { 1, 2, 3 }, {}, {}, { 4 }, { 5, 6 } };
		SequenceIterator seq = new SequenceIterator(array);
		while (seq.hasNext()) {
			System.out.println(seq.next());
		}
	}

	private int outIndex;
	private  int inIndex;
	private int[][] array;

	public SequenceIterator(int[][] array) {
		// TODO Auto-generated constructor stub
		this.array = array;
		outIndex = 0;
		inIndex = 0;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		while (outIndex < array.length && inIndex == array[outIndex].length) {
			// outIndex is within it's bound
			// and inIndex is already out of that line's bound
			outIndex++;   // go to next line
			inIndex = 0;  // reset the inIndex to 0
		}
		// if the outIndex is still in its bound and index is also in bound of that row
		if (outIndex < array.length && (inIndex < array[outIndex].length)) {
			return true;
		}
		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if (hasNext()) {
			// if hasNext() is true, which means outIndex and inIndex are both in their bound
			// return the element array[outIndex][inIndex]
			// first, inIndex ++
			int result = array[outIndex][inIndex];
			inIndex++;
			// here, we need to use while, not if,
			// since there might many {}, {}, {}, etc
			while (outIndex < array.length && inIndex == array[outIndex].length) {
				// the same logic with hasNext()
				outIndex++;  // outIndex ++
				inIndex = 0; // reset inIndex to 0
			}
			return result;
		}
		return null;

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
	}
}

package system_design;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class StandardBloomFilter {

	class HashFunction {
		public int cap, seed;

		public HashFunction(int cap, int seed) {
			this.cap = cap;
			this.seed = seed;
		}

		public int hash(String value) {
			int ret = 0;
			int n = value.length();
			for (int i = 0; i < n; ++i) {
				ret += seed * ret + value.charAt(i);
				ret %= cap;
			}
			return ret;
		}
	}

	
	public BitSet bits;
	public int k;
	public List<HashFunction> hashFunc;

	public StandardBloomFilter(int k) {
		// initialize your data structure here
		this.k = k;
		hashFunc = new ArrayList<HashFunction>();

		for (int i = 0; i < k; ++i)
			hashFunc.add(new HashFunction(100000 + i, 2 * i + 3));
		bits = new BitSet(100000 + k);
	}

	public void add(String word) {
		// Write your code here
		for(int i = 0; i < k; i++) {
			int position = hashFunc.get(i).hash(word);
			bits.set(position);
		}
	}

	public boolean contains(String word) {
		// Write your code here
		for(int i = 0; i < k; ++i) {
			int position = hashFunc.get(i).hash(word);
			if (!bits.get(position)) {
				return false;
			}
		}
		return true;
	}
}


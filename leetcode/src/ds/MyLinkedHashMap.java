package ds;

public class MyLinkedHashMap<K, V>{

	public MyLinkedHashMap(int capacity, float loadFactor) {
		
		// by default, accessOrder is set to true
		this.accessOrder = true;
		// TODO Auto-generated constructor stub
	}
	
	public MyLinkedHashMap(int capacity, float loadFactor, boolean accessOrder) {
		
		this.accessOrder = accessOrder;
		// TODO Auto-generated constructor stub
	}
	
	private boolean accessOrder;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private class Entry<K, V> {
		final K key;
		V value;
		Entry<K, V> next;
		Entry<K, V> before, after;
		
		Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
			this.before = null;
			this.after = null;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}

		// for pretty print purpose
		@Override
		public String toString() {
			return String.valueOf(key) + ": " + String.valueOf(value);
		}
	}
	
	Entry<K, V> head;
	Entry<K, V> tail;
	
	
	
	
	

}

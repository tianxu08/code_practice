package system_design;

import java.util.*;

public class MiniCassandra {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiniCassandra sln = new MiniCassandra();
		sln.insert("goolge", 1, "haha");
		sln.query("google", 0, 1);
		
	}
	
	public class Column {
		public int key;
		public String value;

		public Column(int key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	private Map<String, NavigableMap<Integer, String>> hash;

	public MiniCassandra() {
		// initialize your data structure here.
		hash = new HashMap<String, NavigableMap<Integer, String>>();
	}

	/**
	 * @param raw_key
	 *            a string
	 * @param column_start
	 *            an integer
	 * @param column_end
	 *            an integer
	 * @return void
	 */
	public void insert(String raw_key, int column_key, String column_value) {
		// Write your code here
		if (!hash.containsKey(raw_key))
			hash.put(raw_key, new TreeMap<Integer, String>());
		hash.get(raw_key).put(column_key, column_value);
	}

	/**
	 * @param raw_key
	 *            a string
	 * @param column_start
	 *            an integer
	 * @param column_end
	 *            an integer
	 * @return a list of Columns
	 */
	public List<Column> query(String raw_key, int column_start, int column_end) {
		// Write your code here
		List<Column> rt = new ArrayList<Column>();
		if (!hash.containsKey(raw_key))
			return rt;
		for (Map.Entry<Integer, String> entry : hash.get(raw_key).subMap(column_start, true, column_end, true).entrySet()) {
			rt.add(new Column(entry.getKey(), entry.getValue()));
		}
		return rt;
	}

	
	
}

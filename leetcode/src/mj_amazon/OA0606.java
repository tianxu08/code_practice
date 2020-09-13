package mj_amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class OA0606 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
		test2();
	}
	
	public static void test2() {
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("def");
		list.add("ghi");
		
		String[] array = list.toArray(new String[0]);
		for(String s: array) {
			System.out.println(s);
		}
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(2);
		list2.add(3);
		Integer[] a2 = list2.toArray(new Integer[0]);
		
		for(Integer i : a2) {
			System.out.println(i);
		}
		
		
	}
	
	public static class Node{
		String parent;
		int rank;
		public Node(String _p, int _r) {
			this.parent = _p;
			this.rank = _r;
		}
		
		@Override
		public String toString(){
			return parent + " : " + rank;
		}
	}
	
	public static String find(HashMap<String, Node> map, String x) {
		if (!map.get(x).parent.equals(x)) {
			map.get(x).parent = find(map, map.get(x).parent);
		}
		return map.get(x).parent;
		
	}
	
	public static void union(HashMap<String, Node> map, String x, String y) {
		String xRoot = find(map, x);
		String yRoot = find(map, y);
		
		if (map.get(xRoot).rank > map.get(yRoot).rank) {
			map.get(yRoot).parent = xRoot;
		} else if (map.get(xRoot).rank < map.get(yRoot).rank) {
			map.get(xRoot).parent = yRoot;
		} else {
			// increase xRoot's rank
			map.get(xRoot).rank ++;
			map.get(yRoot).parent = xRoot;
		}
	}
	
	public static void test() {
		String[][] input = {
				{"item1", "item2"},
				{"item2", "item3"},
				{"item5", "item4"}
		};
		
		String[][] input2 = { 
				{"item1", "item2"},
				{"item3", "item4"}
		};
		
		String[][] input3 = { 
				{},
				{}
		};
		
//		String[] rev = largest(input);
		String[] rev2 = largest2(input3);
		for(int i = 0; i < rev2.length; i ++) {
			System.out.println(rev2[i]);
		}
		
//		System.out.println("-----------");
//		
//		rev2 = largest2(input);
//		for(int i = 0; i < rev2.length; i ++) {
//			System.out.println(rev2[i]);
//		}
		
		
		
		
		
	}
	
	public static String[] largest(String[][] itemAssociation) {
		if (itemAssociation == null || itemAssociation.length == 0) {
			return null;
		}
		
		print(itemAssociation);
		
		for(int i = 0; i < itemAssociation.length; i ++) {
			Arrays.sort(itemAssociation[i]);
		}
		
		print(itemAssociation);
		
		HashMap<String, Node> map = new HashMap<String,Node>();
		
		int rLen = itemAssociation.length;
		int cLen = itemAssociation[0].length;
		
		// put the elements into the map
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (!map.containsKey(itemAssociation[i][j])) {
					map.put(itemAssociation[i][j], new Node(itemAssociation[i][j], 0));
				}
			}
		}
		
		// combine elements in the same group
		for (int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen - 1; j ++) {
				String first = itemAssociation[i][j];
				String second = itemAssociation[i][j + 1];
				String xRoot = find(map, first);
				String yRoot = find(map, second);
				union(map, xRoot, yRoot);
			}
		}
		
		for(Entry<String, Node> entry : map.entrySet()) {
			System.out.println("[" + entry.getKey() + "  --- " 
		+ entry.getValue().parent + " : " + entry.getValue().rank + " ]");
		}
		
		// traverse the map, group all elements which in the same group
		HashMap<String, ArrayList<String>> resMap = new HashMap<String, ArrayList<String>>();
		List<String> keylist = new ArrayList<String>();
		for (Entry<String, Node> entry : map.entrySet()) {
			String cur = entry.getKey();
			String par = entry.getValue().parent;
			if (!resMap.containsKey(par)) {
				resMap.put(par, new ArrayList<String>());
				keylist.add(par);
			}
			resMap.get(par).add(cur);
		}
		
		// print the resMap
		for(Entry<String, ArrayList<String>> entry: resMap.entrySet()) {
			System.out.println("{ " + entry.getKey() + "  " );
			System.out.print(entry.getValue());
			System.out.println("   }");
		}
		final HashMap<String, ArrayList<String>> myMap = resMap;
		// get the item with the most association.
		Comparator<String> mycomp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (myMap.get(o1).size() == myMap.get(o2).size()) {
					return o1.compareTo(o2);
				}
				
				return myMap.get(o1).size() > myMap.get(o2).size() ? -1 : 1;
			}
		};
		
		Collections.sort(keylist, mycomp);
		
		ArrayList<String> resultList = resMap.get(keylist.get(0));
		
		String[] result = new String[resMap.get(keylist.get(0)).size()];
		for(int i = 0; i < resultList.size(); i ++) {
			result[i] = resultList.get(i);
		}
		
		Arrays.sort(result);
		
		
		for(int i = 0; i < result.length; i ++) {
			System.out.println(result[i]);
		}
		return result;
	}
	public static void print(String[][] input) {
		for(int i = 0; i < input.length; i ++) {
			for(int j = 0; j < input[0].length; j ++) {
				System.out.print(input[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	
	
	
	public static String[] largest2(String[][] itemAssociation) {
		if (itemAssociation == null || itemAssociation.length == 0) {
			return new String[0];
		}
		
		for(int i = 0; i < itemAssociation.length; i ++) {
			Arrays.sort(itemAssociation[i]);
		}
		
		
		HashMap<String, Node> map = new HashMap<String,Node>();
		
		int rLen = itemAssociation.length;
		int cLen = itemAssociation[0].length;
		
		// put the elements into the map
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < itemAssociation[i].length; j ++) {
				if (!map.containsKey(itemAssociation[i][j])) {
					map.put(itemAssociation[i][j], new Node(itemAssociation[i][j], 0));
				}
			}
		}
		
		// combine elements in the same group
		for (int i = 0; i < rLen; i ++) {
			cLen = itemAssociation[i].length;
			for(int j = 0; j < cLen - 1; j ++) {
				String first = itemAssociation[i][j];
				String second = itemAssociation[i][j + 1];
				String xRoot = find(map, first);
				String yRoot = find(map, second);
				union(map, xRoot, yRoot);
			}
		}
		
		
		// traverse the map, group all elements which in the same group
		HashMap<String, ArrayList<String>> resMap = new HashMap<String, ArrayList<String>>();
		List<String> keylist = new ArrayList<String>();
		for (Entry<String, Node> entry : map.entrySet()) {
			String cur = entry.getKey();
			String par = entry.getValue().parent;
			if (!resMap.containsKey(par)) {
				resMap.put(par, new ArrayList<String>());
				keylist.add(par);
			}
			resMap.get(par).add(cur);
		}
		
		final HashMap<String, ArrayList<String>> myMap = resMap;
		// get the item with the most association.
		Comparator<String> mycomp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (myMap.get(o1).size() == myMap.get(o2).size()) {
					return o1.compareTo(o2);
				}
				
				return myMap.get(o1).size() > myMap.get(o2).size() ? -1 : 1;
			}
		};
		
		
		if (keylist.size() == 0) {
			return new String[0];
		}
		
		Collections.sort(keylist, mycomp);
		
		ArrayList<String> resultList = resMap.get(keylist.get(0));
		
		String[] result = new String[resMap.get(keylist.get(0)).size()];
		for(int i = 0; i < resultList.size(); i ++) {
			result[i] = resultList.get(i);
		}
		Arrays.sort(result);
		
		return result;
	}

}

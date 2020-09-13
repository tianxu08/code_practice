package sortbynum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Task380_RandomizedSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task380_RandomizedSet sln = new Task380_RandomizedSet();
		sln.insert(1);
		sln.printList();
		sln.printMap();
		System.out.println("----");
		boolean r1 = sln.remove(2);
		sln.printList();
		sln.printMap();
		System.out.println("r1 = " + r1);
		int rand1 = sln.getRandom();
		System.out.println("rand1 = " + rand1);
	}
	
	// use an HashMap to store <Elem, Index>
	// use an arrayList to store the element. 
	
	private List<Integer> list;
	private Map<Integer, Integer> map;
	/** Initialize your data structure here. */
    public Task380_RandomizedSet() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
    	if (!map.containsKey(val)) {
    		int index = list.size();
			map.put(val, index);
			list.add(val);
			return true;
		}
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
    	if (!map.containsKey(val)) {
    		return false;
		}
    	int index = map.get(val);
		if (index < list.size() - 1) {
			// not the last one
			// swap the val to the last index
			int lastElem = list.get(list.size() - 1);
			list.set(index, lastElem);
			
			map.put(lastElem, index);
		}
		// delete the last index
		list.remove(list.size() - 1);
		// delete the element from the map
		map.remove(val);
		return true;
    }
    
    java.util.Random rand = new java.util.Random();
    
    
    /** Get a random element from the set. */
    public int getRandom() {
    	
    	return list.get( rand.nextInt(list.size()) );
        
    }
    
    public void printList() {
    	for(Integer i : this.list) {
    		System.out.print(i + " ");
    	}
    	System.out.println();
    }
    
    public void printMap() {
    	for(Entry<Integer, Integer> entry : map.entrySet()) {
    		System.out.println(entry.getKey() + " : " + entry.getValue());
    	}
    }
	

}

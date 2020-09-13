package sortbynum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 
 * @author xutian
 * There is duplicate in the input
 */
public class Task381_RandomizedCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	List<Integer> list;
	Map<Integer, Set<Integer>> map;
	java.util.Random rand = new java.util.Random();
	
	
	/** Initialize your data structure here. */
    public Task381_RandomizedCollection() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Set<Integer>>();
        
    }
    
    /** Inserts a value to the collection. 
     * Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
        	int nextIndex = list.size();
        	list.add(val);
        	map.get(val).add(nextIndex);
			return false;
		} else {
			int nextIndex = list.size();
			list.add(val);
			map.put(val, new HashSet<Integer>());
			map.get(val).add(nextIndex);
			return true;
		}
    }
    
    /** Removes a value from the collection. 
     * Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
			return false;
		}
        Set<Integer> indices_set = map.get(val);
        
        int index = indices_set.iterator().next();
        indices_set.remove(index);
        if (index < list.size() - 1 ) {
			int lastElem = list.get(list.size() - 1);
			list.set(index, lastElem);
			map.get(lastElem).remove(list.size() - 1);
			map.get(lastElem).add(index);
		}
        // remove the lastElem
        list.remove(list.size() - 1);
        if (map.get(val).isEmpty()) {
			map.remove(val);
		}
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
    	return list.get( rand.nextInt(list.size()) );
    }

}

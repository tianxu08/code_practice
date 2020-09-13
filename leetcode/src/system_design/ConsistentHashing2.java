package system_design;

import java.util.*;

public class ConsistentHashing2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * http://www.lintcode.com/en/problem/consistent-hashing-ii/
	 */
	
	 public int n, k;
	 public Set<Integer> ids = null;
	 public Map<Integer, List<Integer>> machines = null;

    // @param n a positive integer
    // @param k a positive integer
    // @return a Solution object
    public static ConsistentHashing2 create(int n, int k) {
        // Write your code here
    	ConsistentHashing2 sln = new ConsistentHashing2();
    	sln.n = n;
    	sln.k = k;
    	sln.ids = new TreeSet<Integer>();
    	sln.machines = new HashMap<Integer, List<Integer>>();
    	return sln;
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        // Write your code here
    	Random ra = new Random();
    	List<Integer> random_nums = new ArrayList<Integer>();
    	// generate k random numbers
    	for(int i = 0; i < k ; i++) {
    		int index = ra.nextInt(n);
    		while(ids.contains(index)) {
    			index = ra.nextInt(n);
    		}
    		ids.add(index);
    		random_nums.add(index);
    	}
    	
    	Collections.sort(random_nums);
    	machines.put(machine_id, random_nums);  // link the machine_id with random_nums
    	
    	return random_nums;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        // Write your code here
    	int distance = n + 1;
    	int machine_id = 0;
    	
    	for(Map.Entry<Integer, List<Integer>> entry: machines.entrySet()) {
    		int key = entry.getKey();
    		List<Integer> random_nums = entry.getValue();
    		for(Integer num: random_nums) {
    			int d = num - hashcode;
    			if (d < 0) {
					d += n;
				}
    			if (d < distance) {
					distance = d;
					machine_id = key;
				}
    		}
    	}
    	return machine_id;
    }

}

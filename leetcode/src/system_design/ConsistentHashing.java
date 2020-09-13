package system_design;

import java.util.*;

public class ConsistentHashing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConsistentHashing sln = new ConsistentHashing();
		int n = 6;
		List<List<Integer>> rev = sln.consistentHashing(n);
		for(List<Integer> list: rev) {
			for(Integer i: list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
	}

	/**
	 * @param n
	 *            a positive integer
	 * @return n x 3 matrix
	 */
	public List<List<Integer>> consistentHashing(int n) {
		// Write your code here
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		for (int i = 1; i <= n; i++) {
			if (i == 1) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(0);
				list.add(359);
				list.add(1);
				result.add(list);
			} else {
				// traverse the list in result
				// get the longest interval
				List<Integer> maxInterval = null;
				int maxLen = Integer.MIN_VALUE;
				int maxIndex = -1;
				
				if (i % 2 == 1) {
					// odd
					// find in the first half
					int half = result.size()/2;
					for (int j = 0; j <= half; j++) {
						int curLen = result.get(j).get(1) - result.get(j).get(0);
						if (maxLen < curLen) {
							maxInterval = result.get(j);
							maxLen = curLen;
							maxIndex = j;
						}
					}
				} else {
					// even
					// find in the second hals
					if (i == 2) {
						maxInterval = result.get(0);
						maxLen = result.get(0).get(1) - result.get(0).get(0);
						maxIndex = 0;
					} else {
						int half = result.size()/2;
						for (int j = half + 1; j < result.size(); j++) {
							int curLen = result.get(j).get(1) - result.get(j).get(0);
							if (maxLen < curLen) {
								maxInterval = result.get(j);
								maxLen = curLen;
								maxIndex = j;
							}
						}
					}
					
				}
				
				System.out.println("maxInterval :" + "[ " + maxInterval.get(0) + " : " + 
								maxInterval.get(1) + " " + maxInterval.get(2) + " ]");
				
				// split the maxInterval
				int mid = (maxInterval.get(1) + maxInterval.get(0)) / 2;

				List<Integer> newInterval = new ArrayList<Integer>();
				newInterval.add(mid + 1);
				newInterval.add(maxInterval.get(1));
				newInterval.add(i);
				// insert the newInterval after the maxInterval
				result.add(maxIndex + 1, newInterval);

				maxInterval.set(1, mid);
			}
		}
		return result;
	}
	
	
	
	  public List<List<Integer>> consistentHashing2(int n) {
	        // Write your code here
	        List<List<Integer>> results = new ArrayList<List<Integer>>();
	        List<Integer> machine = new ArrayList<Integer>();
	        machine.add(0);
	        machine.add(359);
	        machine.add(1);
	        results.add(machine);
	        for (int i = 1; i < n; ++i) {
	            List<Integer> new_machine = new ArrayList<Integer>();
	            int index = 0;
	            for (int j = 1; j < i; ++j) {
	                if (results.get(j).get(1) - results.get(j).get(0) + 1 >
	                    results.get(index).get(1) - results.get(index).get(0) + 1)
	                    index = j;
	            }

	            int x = results.get(index).get(0);
	            int y = results.get(index).get(1);
	            results.get(index).set(1, (x + y) / 2);
	            
	            new_machine.add((x + y) / 2 + 1);
	            new_machine.add(y);
	            new_machine.add(i + 1);
	            results.add(new_machine);
	        }
	        return results;
	    }
	

}

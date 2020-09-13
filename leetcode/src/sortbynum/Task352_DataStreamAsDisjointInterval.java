package sortbynum;
import java.util.*;

import sortbynum.Task51_100.Interval;


public class Task352_DataStreamAsDisjointInterval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task352_DataStreamAsDisjointInterval sln = new Task352_DataStreamAsDisjointInterval();
		sln.addNum2(1);
		sln.printIntervals();
		
		sln.addNum2(3);
		sln.printIntervals();
		sln.addNum(5);
		sln.printIntervals();
		
		System.out.println("========================");
		sln.addNum2(2);
		sln.printIntervals();
		
	}
	
	private List<Interval> intervals;
	
	 /** Initialize your data structure here. */
    public Task352_DataStreamAsDisjointInterval() {
        this.intervals = new ArrayList<Interval>();
    }
    
    public void addNum(int val) {
        Interval nInterval = new Interval(val, val);
        insert(nInterval);
    }
    
    
    /*
     * Insert()
     * Time: O(n)
     * Space: O(n)
     */
    public void insert(Interval newInterval) {
    	if (intervals.size() == 0) {
			intervals.add(newInterval);
		}
    	int insertPos = 0;
    	List<Interval> result = new ArrayList<Interval>();
    	
    	for(Interval cur : intervals) {
    		printOneInterval(cur);
    		if (cur.end < newInterval.start - 1) {
    			// no intersection
    			result.add(cur);
    			insertPos ++;
			} else if (newInterval.end < cur.start - 1) {
				// in front, no intersection
				// add current interval into result
				result.add(cur);
			} else {
				// there is overlap
				newInterval.start = Math.min(cur.start,newInterval.start);
				newInterval.end = Math.max(cur.end, newInterval.end);
			}
    	}
    	result.add(insertPos, newInterval);
    	intervals = result;
    }
    
    
    /*
     * how to optimize to space: O(1)
     */    
    public void addNum2(int val) {
    	if (intervals.isEmpty()) {
			intervals.add(new Interval(val, val));
		} else {
			for(int i = 0; i < intervals.size(); i ++) {
				Interval cur = intervals.get(i);
				if (val == cur.start - 1) {
					cur.start = val;
					return ;
				} else if (val < cur.start - 1) {
					// insert in front of the cur
					intervals.add(i, new Interval(val, val));
				} else if (val >= cur.start && val <= cur.end) {
					// val is in the current interval
					return ;
				} else if (val == cur.end + 1) {
					// update cur.end
					cur.end = val;
					// merge the following intervals if needed
					if (i + 1 < intervals.size()) {
						Interval next = intervals.get(i + 1);
						if (cur.end == next.start || cur.end == next.start - 1) {
							// merge, and update the cur.end
							cur.end = next.end;
							intervals.remove(i + 1);
						}
					}
					return ;
				}
			}
			// not valid for the above 
			intervals.add(new Interval(val, val));
		}
    }
    
	
    public List<Interval> getIntervals() {
        return intervals;
    }
    
    public void printIntervals() {
    	for(Interval i: intervals) {
    		System.out.print(" [ " + i.start + "  " + i.end + " ] ");
    	}
    	System.out.println();
    }
    
    public void printOneInterval(Interval i) {
    	System.out.println("the interval is : ");
    	System.out.println(" [ " + i.start + " : " + i.end + " ]");
    	System.out.println("------------------");
    }

}

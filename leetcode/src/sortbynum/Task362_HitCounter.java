package sortbynum;

import java.util.Deque;
import java.util.LinkedList;



public class Task362_HitCounter {

	Deque<Integer> q;
    /** Initialize your data structure here. */
    public Task362_HitCounter() {
        q = new LinkedList<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
    	q.offerLast(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
    	while(!q.isEmpty() && timestamp - q.peekFirst() >= 300) {
    		q.pollFirst();
    	}
        return q.size();
    }
}

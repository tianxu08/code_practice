package sortbynum;
import java.util.*;

public class Task155_minStack {

    /** initialize your data structure here. */
    LinkedList<Integer> st;
    LinkedList<Integer> min;
    public Task155_minStack() {
        st = new LinkedList<Integer>();
        min = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        st.offerFirst(x);
        if(min.isEmpty()) {
            min.offer(x);
        } else {
        	min.offer(min.peekFirst());
        }
    }
    
    public void pop() {
    	if (st.isEmpty()) {
			return ;
		}
    	st.pollFirst();
    	min.pollFirst();
    }
    
    public int top() {
    	min.pollFirst();
       return st.poll();
    }
    
    public int getMin() {
        return min.peekFirst();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
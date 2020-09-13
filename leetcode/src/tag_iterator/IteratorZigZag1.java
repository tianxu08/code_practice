package tag_iterator;

import java.util.*;


public class IteratorZigZag1 {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
    Iterator<Integer> it1;
    Iterator<Integer> it2;
    int turns;

    public IteratorZigZag1(List<Integer> v1, List<Integer> v2) {
        this.it1 = v1.iterator();
        this.it2 = v2.iterator();
        turns = 0;
    }
    
    public int next() {
        // 如果没有下一个则返回0
        if(!hasNext()){
            return 0;
        }
        turns++;
        // 如果是第奇数个，且第一个列表也有下一个元素时，返回第一个列表的下一个
        // 如果第二个列表已经没有，返回第一个列表的下一个
        if((turns % 2 == 1 && it1.hasNext()) || (!it2.hasNext())){
            return it1.next();
        // 如果是第偶数个，且第二个列表也有下一个元素时，返回第二个列表的下一个
        // 如果第一个列表已经没有，返回第二个列表的下一个
        } else if((turns % 2 == 0 && it2.hasNext()) || (!it1.hasNext())){
            return it2.next();
        }
        return 0;
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
}

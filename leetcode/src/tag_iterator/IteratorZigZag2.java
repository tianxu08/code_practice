package tag_iterator;

import java.util.*;
/*
 * https://segmentfault.com/a/1190000003786218
 */



/*
 * Q：如果输入是k个列表呢？
 * A：使用一个迭代器的列表来管理这些迭代器。用turns变量和取模来判断我们该取列表中的第几个迭代器。
 * 不同点在于，一个迭代器用完后，我们要将其从列表中移出，这样我们下次就不用再找这个空的迭代器了。
 * 同样，由于每用完一个迭代器后都要移出一个，turns变量也要相应的更新为该迭代器下标的上一个下标。
 * 如果迭代器列表为空，说明没有下一个了。
 */
public class IteratorZigZag2 implements Iterator<Integer> {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    List<Iterator<Integer>> itlist;
    int turns;

    public IteratorZigZag2(List<Iterator<Integer>> list) {
        this.itlist = new LinkedList<Iterator<Integer>>();
        // 将非空迭代器加入列表
        for(Iterator<Integer> it : list){
            if(it.hasNext()){
                itlist.add(it);
            }
        }
        turns = 0;
    }

    public Integer next() {
        if(!hasNext()){
            return 0;
        }
        Integer res = 0;
        // 算出本次使用的迭代器的下标
        int pos = turns % itlist.size();
        Iterator<Integer> curr = itlist.get(pos);
        res = curr.next();
        // 如果这个迭代器用完，就将其从列表中移出
        if(!curr.hasNext()){
            itlist.remove(turns % itlist.size());
            // turns变量更新为上一个下标
            turns = pos - 1;
        }
        turns++;
        return res;
    }

    public boolean hasNext() {
        return itlist.size() > 0;
    }

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}

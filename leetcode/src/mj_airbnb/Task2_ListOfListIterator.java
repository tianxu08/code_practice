package mj_airbnb;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Task2_ListOfListIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> vec2d = new ArrayList<List<Integer>>();
		List<Integer> line1 = new ArrayList<Integer>();
		List<Integer> line2 = new ArrayList<Integer>();
		line2.add(3);
		line2.add(5);
		line2.add(7);
		line1.add(2);
		vec2d.add(line1);
		vec2d.add(line2);
		Task2_ListOfListIterator vct = new Task2_ListOfListIterator(vec2d);
		
		while(vct.hasNext()) {
			System.out.println(vct.next());
			vct.remove();
		}
		System.out.println("==================");
		for(Integer i: line1) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		for(Integer j : line2) {
			System.out.print(j + " ");
		}
		System.out.println();
		
//		System.out.println("------------");
//		Iterator2DVector vct2 = new Iterator2DVector(vec2d);
//		while(vct2.hasNext()) {
//			System.out.println(vct2.next());
//		}
		
//		List<Integer> list = new ArrayList<Integer>();
//		for(int i = 1; i < 5; i++) {
//			list.add(i);
//		}
//		ListIterator<Integer> it = list.listIterator();
//		if (it.hasNext()) {
//			System.out.println(it.next());
//			it.remove();
//			System.out.println(it.next());
//			it.remove();
//		}
//		
//		System.out.println("-------");
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//		
//		ListIterator<Integer> it2 = list.listIterator();
//		System.out.println("-------");
//		while(it2.hasNext()){
//			System.out.println(it2.next());
//		}
		
		
	}
	
	/**
	 * intput: List<List<Integer>> list
	 * 
	 */
	
	List<List<Integer>> list;
	int rowIndex;
	int colIndex;
	
	public Task2_ListOfListIterator(List<List<Integer>> vec2d) {
		this.list = vec2d;
		rowIndex = 0;
		colIndex = 0;
	}

	public boolean hasNext() {
	
		// first check whether colIndex reach the end of its row
		// if yes, rowIndex goes to the next row and reset the colIndex to 0
		// if not, skip the while loop
		while(rowIndex < list.size() && colIndex == list.get(rowIndex).size()) {
			rowIndex ++;
			colIndex = 0;
		}	
		
		// after the above while loop
		// when rowIndex and colIndex are valid, it return true. 
		// otherwise, return false
		if (rowIndex < list.size() && (colIndex < list.get(rowIndex).size())) {
			return true;
		}
		return false;
	}
	
	public int next() {
		// first check hasNext
		if (hasNext()) {
			//if hasNext is true, which means that the rowIdx and colIdx are valid. 
			// we can get the result
			int result = list.get(rowIndex).get(colIndex);
			
			
			// after get the result, update the colIndex
			colIndex ++;
			
			// the colIndex might not be valid
			// so, modify the colIndex and rowIndex to valid. 
			while(rowIndex < list.size() && colIndex == list.get(rowIndex).size()) {
				rowIndex ++;
				colIndex = 0;
			}		
			return result;
		} 
		return -1;
	}
	

	public void remove() {
		List<Integer> listToRemove;
		int rowToRemove;
		int colToRemove;
		
		// case1: if the element to remove is the last element of the row,
		// the colIdx == 0 now
		// current colIndex points to the beginning 
		if (colIndex == 0) {
			rowToRemove = rowIndex - 1;
			listToRemove = list.get(rowToRemove);
			colToRemove = listToRemove.size() - 1;
			// remove the element from listToRemove
			listToRemove.remove(colToRemove);
			
			// this case won't change the rowIndex and colIndex
		} else {
			// case2, the element to remove is NOT the last element of the row
			// current colIndex is NOT 0
			rowToRemove = rowIndex;
			colToRemove = colIndex - 1;
			listToRemove = list.get(rowToRemove);
			// remove the element from the 
			listToRemove.remove(colToRemove);
		}
		
		// if the list to remove has only one element
		if (listToRemove.isEmpty()) {
			list.remove(rowToRemove);
			rowIndex --;
		}
		
		// update the colIndex
		if (colIndex != 0) {
			colIndex --;
		}
	}
	
	
}



package mj_airbnb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Airbnb_mianjing_2018 {

	/**
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=191081&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D37%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	 * 
	 */
	
	
	/**
	 * List of List(2D List) Iterator
	 * 
	 */
	
	
	/**
	 * display pages
	 */
	
	public static List<String> displayPages(List<String> input, int pageSize) {
		List<String> res = new ArrayList<>();
		if (input == null || input.size() == 0) {
			return res;
		}
		
		List<String> visited = new ArrayList<>();
		Iterator<String> iter = input.iterator();
		boolean reachEnd = false;
		
		while(iter.hasNext()) {
			String cur = iter.next();
			String hostId = cur.split(",")[0];
			if (!visited.contains(hostId) || reachEnd) {
				res.add(cur);
				visited.add(hostId);
				iter.remove();
			}
			
			if (visited.size() == pageSize) {
				visited.clear();
				reachEnd = false;
				
			}
		}
		return null;
	}
	
	/**
	 * basic calculator
	 * basic calculator 2
	 * 
	 */
	
	/*
	 * 6 Travel buddy list
	 * 
	 * */
}

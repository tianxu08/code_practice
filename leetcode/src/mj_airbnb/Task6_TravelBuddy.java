package mj_airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task6_TravelBuddy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private List<Buddy> buddies;
	
	private Set<String> myWhishList;
	
	public Task6_TravelBuddy(Set<String> myWhishList, Map<String, Set<String>> friendsWishList) {
		this.buddies = new ArrayList<>();
		this.myWhishList = myWhishList;
		
		for (String name: friendsWishList.keySet()) {
			Set<String> wishList = friendsWishList.get(name);
			Set<String> intersection = new HashSet<>(wishList);
			
			intersection.retainAll(myWhishList);
			
			int similarity = intersection.size();
			
			if (similarity >= wishList.size() / 2) {
				buddies.add(new Buddy(name, similarity, wishList));
			}
		}
	}
	
	public List<Buddy> getSortedBuddies() {
		Collections.sort(buddies);
		List<Buddy> res = new ArrayList<>(buddies);
		return res;
	}
	
	public List<String> recommendCities(int k) {
		List<String> res = new ArrayList<>();
		List<Buddy> buddies = getSortedBuddies();
		
		int i = 0; 
		while (k > 0 && i < buddies.size()) {
			Set<String> diff = new HashSet<>(buddies.get(i).wishList);
			diff.removeAll(myWhishList);
			
			if (diff.size() <= k) {
				res.addAll(diff);
				k = k - diff.size();
				i++;
			} else {
				Iterator<String> it = diff.iterator();
				while(k > 0 && it.hasNext()) {
					res.add(it.next());
					k --;
				}
			}
		}
		return res;
	}
	

	public static class Buddy implements Comparable<Buddy>{
		String name; 
		int similarity;
		Set<String> wishList;
		
		Buddy(String name, int similarity, Set<String> wishList) {
			this.name = name;
			this.similarity = similarity;
			this.wishList = wishList;
		}
		
		@Override
		public int compareTo(Buddy that) {
			if (this.similarity == that.similarity) {
				return 0;
			}
			return this.similarity > that.similarity ? -1 : 1;
		}
	}

}

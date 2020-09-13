package sortbynum;

import java.util.HashMap;
import java.util.*;
public class ValidWordAbbr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * task288
	 * Unique Word Abbreviation
	 * Using HashMap 
	 */
	private Map<String, String> map;

	public ValidWordAbbr(String[] dictionary) {
	        this.map = new HashMap<String, String>();
	         
	        for (String word : dictionary) {
	            String abbr = toAbbr(word);
	            if (map.containsKey(abbr)) {
	                map.put(abbr, "");
	            } else {
	                map.put(abbr, word);
	            }
	        }
	    }

	public boolean isUnique(String word) {
		String abbr = toAbbr(word);
		if (!map.containsKey(abbr) || map.get(abbr).equals(word)) {
			// if the abbr is NOT in the hashMap or the word is the same with the one in HashMap
			return true;
		} else {
			return false;
		}
	}

	private String toAbbr(String s) {
		if (s == null || s.length() <= 2) {
			return s;
		}
		
		int len = s.length() - 2;

		String result = s.charAt(0) + "" + len + s.charAt(s.length() - 1);

		return result;
	}

}

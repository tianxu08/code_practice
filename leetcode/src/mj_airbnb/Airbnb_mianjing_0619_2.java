package mj_airbnb;

import java.util.*;

public class Airbnb_mianjing_0619_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "John,Smith,john.smith@gmail.com,Los Angeles,1";
		ArrayList<String> output = parseCSV(input);
		String strOutput = printStr(output);
		System.out.println(strOutput);
		// #2
		output = parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
		strOutput = printStr(output);
		System.out.println(strOutput);
		output = parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1");
		strOutput = printStr(output);
		System.out.println(strOutput);
		System.out.println("----------------");
		String input2 = "aa,bb,\"aa\",\"aa,bb\",\"\"aaaa\"\"";
		ArrayList<String> output2 = parseCSV(input2);
		System.out.println(input2);

		System.out.println(output2);
		for (String s : output2) {
			System.out.println(s);
		}
		System.out.println("======================");

	}

	public static ArrayList<String> parseCSV(String str) {
		ArrayList<String> res = new ArrayList<String>();
		boolean inQuote = false;
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (inQuote) {
				// the current char is "
				// check whether is ending "
				if (str.charAt(i) == '"') {
					if (i == str.length() - 1) {
						// reach the end of the str
						res.add(buffer.toString());
						return res;
					} else if (str.charAt(i + 1) == '"') {
						// the next char is also "
						buffer.append('"');
						i++;
					} else {
						// add the current string res
						res.add(buffer.toString());
						// set buffer to empty
						buffer.setLength(0);
						// set inQuote to false
						inQuote = false;
						//
						i++;
					}
				} else {
					// cur char is NOT "
					buffer.append(str.charAt(i));
				}
			} else {
				// NOT inQuote
				// inQuote == false
				if (str.charAt(i) == '"') {
					// starting "
					inQuote = true;
				} else if (str.charAt(i) == ',') {
					// NOT in quote, cur char is , it is a separator
					res.add(buffer.toString());
					// clean the buffer
					buffer.setLength(0);
				} else {
					// NOT starting ", NOT , just common char
					// append to the buffer
					buffer.append(str.charAt(i));
				}
			}
		}

		// add the remaining content in buffer to res
		if (buffer.length() > 0)
			res.add(buffer.toString());
		return res;
	}

	public static String printStr(ArrayList<String> list) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < list.size() - 1; i++) {
			res.append(list.get(i));
			res.append('|');
		}
		res.append(list.get(list.size() - 1));
		return res.toString();
	}
	
	
	public static void test3()
	{
		
	}
	public static String parseCSV(List<List<String>> input) {
		if (input.size() == 0) {
			return "";
		}
		StringBuilder res = new StringBuilder();
		for (List<String> list : input) {
			StringBuilder sb = parseHelper(list);
			if (res.length() == 0) {
				res.append(sb);
			} else {
				res.append('\n');
				res.append(sb);
			}
		}
		return res.toString();
	}

	private static StringBuilder parseHelper(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			s = s.substring(1, s.length() - 1); // get ride of single quotes
			if (sb.length() == 0) {
				sb.append(s);
			} else {
				sb.append("|");
				sb.append(s);
			}
		}
		return sb;
	}

}

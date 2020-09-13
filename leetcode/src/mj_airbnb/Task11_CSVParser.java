package mj_airbnb;

import java.util.ArrayList;
import java.util.List;

public class Task11_CSVParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test =  "\"Alexandra \"Alex\"\"";
		System.out.println(test);
		String result = parseCVS(test);
		System.out.println(result);
	}
	
	
	
	/**
	 * amazon OA
	 */
	
	
	
	
	/**
	 * 
	 * CSV parsing rules: 
	 * 1. if a comma(,) is contained within a field, then that field must be enclosed in double quotes ", e.g "John, Smith"
	 * 2. If a double quote (") is contained within a field, then that field must be enclosed in double quoted and another 
	 *    double quote used as an escape character. e.g "John """
	 * 
	 * 
	 * 
	 * [
	 *   ['John', 'Smith', 'john.smith@gmail.com', 'Los Angeles', '1'],
	 *   ['Jane', 'Roberts', 'janer@msn.com', 'San Francisco, CA', '0'],
	 *   ['Alexandra "Alex"', 'Menendez', 'alex.menendez@gmail.com', 'Miami', '1']
	 * ]
	 *   需要返回
	 *   John|Smith|john.smith@gmail.com|Los Angeles|1
	 *   Jane|Roberts|janer@msn.com|San Francisco, CA|0
	 *   Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
	 */
	

	public static String parseCVS(String str) {
		List<String> list = new ArrayList<>();
		StringBuilder stb = new StringBuilder();
		boolean isInQuote = false;
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (isInQuote) {
				if (c == '\"') {
					if (i + 1 < str.length() && str.charAt(i + 1) == '\"') {
						// c's next is also '\"', then we need to add '\"' into the stb
						stb.append('\"');
						i++;
					} else {
						// c is a closing quote
						isInQuote = false;
					}
				} else {
					// regular char
					stb.append(c);
				}
			} else {
				// not in quote
				if (c == '\"') {
					isInQuote = true;
				} else if (c == ',') {
					// finish one section
					// reset the stb
					list.add(stb.toString());
					stb.setLength(0);
				} else {
					// regular char
					stb.append(c);
				}
			} 
			System.out.println("================");
			System.out.println(stb.toString());
		}
		
		// get string in stb
		if (stb.length() > 0) {
			list.add(stb.toString());
		}
		
		// compose string from list
		return String.join("|", list);
	
	}
	

}

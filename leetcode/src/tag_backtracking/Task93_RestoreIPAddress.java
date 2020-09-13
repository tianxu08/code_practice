package tag_backtracking;
import java.util.*;

public class Task93_RestoreIPAddress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task93_RestoreIPAddress sln = new Task93_RestoreIPAddress();
		String s = "25525511135";
		List<String> rev = sln.restoreIpAddresses(s);
		System.out.println(rev);
	}

	public List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<String>();
		List<String> buffer = new ArrayList<String>();
		helper(s, result, buffer, 0);
		return result;
	}
	
	public void helper(String s, List<String> result, List<String> buffer, int index) {
		if (buffer.size() == 4) {
			if (index != s.length()) {
				return ;
			}
			// index == s.length, we get a solution
			StringBuilder stb = new StringBuilder();
			for(String str: buffer) {
				stb.append(str);
				stb.append('.');
			}
			stb.deleteCharAt(stb.length() - 1);
			result.add(stb.toString());
			return ;
			
		}
		
		for(int i = index; i < s.length() && i < index + 3; i ++) {
			String curPart = s.substring(index, i + 1);
			if (isValid(curPart)) {
				buffer.add(curPart);
				helper(s, result, buffer, i + 1);
				buffer.remove(buffer.size() - 1);
			}
		}
	}
	
	public boolean isValid(String str) {
		if (str.charAt(0) == '0') { // 003 or 000 is NOT valid
			return str.equals("0");
		}
		return Integer.parseInt(str) > 0 && Integer.parseInt(str) <= 255;
	}

}

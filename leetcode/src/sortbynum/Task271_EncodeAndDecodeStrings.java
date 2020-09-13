package sortbynum;
import java.util.*;
public class Task271_EncodeAndDecodeStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	 // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            int len = str.length();
            sb.append(len).append("#").append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) {
            return res;
        }

        int index = s.indexOf("#");
        int len = Integer.valueOf(s.substring(0, index));
        String t = s.substring(index + 1, index + 1 + len);
        res.add(t);
        res.addAll(decode(s.substring(index+1+len)));
        return res;
    }


}

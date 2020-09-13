package system_design;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/* more time to analyze*/
	
	private static final String HTML_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*\"?'?([^\"'>\\s]*)";
	
	public static List<String> parseUrls(String content) {
		// Write your code here
		List<String> links = new ArrayList<String>();
		Pattern pattern = Pattern.compile(HTML_HREF_TAG_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		String url = null;
		while(matcher.find()) {
			url = matcher.group(1);
			if (url.length() == 0 || url.startsWith("#")) {
				continue;
			}
			links.add(url);
		}
		return links;
	}

}

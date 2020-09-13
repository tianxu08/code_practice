package mj_airbnb;

import java.util.ArrayList;
import java.util.List;

public class Task12_TextJustification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/**
	 * LC68
	 * https://leetcode.com/problems/text-justification/
	 * 
	 * Input:
	 * words = ["This", "is", "an", "example", "of", "text", "justification."]
	 * maxWidth = 16
	 * Output:
	 * [
	 *   "This    is    an",
	 *   "example  of text",
	 *   "justification.  "
	 * ]
	 * 
	 * Input:
	 * words = ["What","must","be","acknowledgment","shall","be"]
	 * maxWidth = 16
	 * Output:
	 * [
	 *  "What   must   be",
	 *  "acknowledgment  ",
	 *  "shall be        "
	 * ]
	 * 
	 * (1) 每一行最多maxWidth
	 * (2) 空格尽量evenly distributed, 要是有多余的空格， 放到左边
	 * (3) 最后一行的多余空格 放到最后
	 * 
	 */
	
	public static void test1() {
		String[] words =
			{"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
		int maxWidth = 16;
		List<String> result = task12_textJustification(words, maxWidth);
		System.out.println(result);
		
		for (String s: result) {
			System.out.println(s);
			System.out.println(s.length());
		}
	}
	public static List<String> task12_textJustification(String[] words, int maxWidth) {
		List<String> result = new ArrayList<>();
		if (words == null || words.length == 0) {
			return result;
		}
		
		int totalCharCount = 0;
		int startIdx = 0;
		
		for (int i = 0; i < words.length; i++) {
			String next = words[i];
			int totalLength = totalCharCount + next.length() + (i - startIdx);
			// totalCharCount + next word length + spaces between words
			if (totalLength > maxWidth) {
				// the next word cannot be put in the same line
				int numWordsInCurLine = i - 1 - startIdx + 1;
				int numIntervals = i - startIdx - 1;
				
				int numSpacesEvenDistributed = 0;
				int numSpacesUnEvenDistributed = 0;
				if (numIntervals > 0) {
					numSpacesEvenDistributed = (maxWidth - totalCharCount) / numIntervals;
					numSpacesUnEvenDistributed = (maxWidth - totalCharCount) % numIntervals;
				}
		
				// compose the current line
				StringBuilder stb = new StringBuilder();
				for (int j = startIdx; j <= i - 1; j++) {
					stb.append(words[j]); // !!!!!!!
					// append spaces
					if (j < i - 1) {
						// append evenly distributed spaces
						for (int k = 0; k < numSpacesEvenDistributed; k++) {
							stb.append(' ');
						}
						
						// append unevenly distributed spaces
						if (numSpacesUnEvenDistributed > 0) {
							stb.append(' ');
							numSpacesUnEvenDistributed --;
						}
					}
				}
				// if there is only one word in current line
				// append all spaces into this line
				
				for (int k = stb.length(); k < maxWidth; k++) {
					stb.append(' ');
				}
				
				// add stb to result
				result.add(stb.toString());
				
				// update totalCharCount and startIdx
				totalCharCount = 0;
				startIdx = i;
			}
			// update totalCharCount and startIdx
			totalCharCount += next.length();
			
		}
		
		StringBuilder lastLine = new StringBuilder();
		// for the last line
		for (int j = startIdx; j < words.length; j++) {
			lastLine.append(words[j]);
			if (lastLine.length() < maxWidth) {
				lastLine.append(' ');
			}
			
		}
		for (int i = lastLine.length(); i < maxWidth; i++) {
			lastLine.append(' ');
		}
		
		result.add(lastLine.toString());
		
		return result;
	}
	
	
	/**
	 * 左右间距按照最长的str为准
	 * 左缩进
	 * 
	 * 
	 * input: 
	 * [
	 * first word
	 * my second sentences
	 * now it's thrid
	 * 
	 * 
	 * 
	 * ]
	 */
	
	public static List<String> textJustification2(List<String> words) {
		return null;
	}
	
	
	

}

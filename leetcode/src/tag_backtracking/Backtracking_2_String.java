package tag_backtracking;

import java.util.*;

public class Backtracking_2_String {
    public static void main(String[] args) {

    }

    /*
     * String related
     *
     *
     * 16 generalized abbreviation
     *
     * 17 minimum unique word abbreviation
     *
     * 18 generate parentheses
     *
     * 19 letter combinations of a phone number
     *
     * 20 palindrome partitioning
     *
     * 21 palindrome permutation 2
     *
     *
     * 22 regular expression matching 23 wildcard expression matching
     *
     * 24 restore IP Address 25 Word Break 2
     *
     * 26 Word Ladder 2
     *
     * 27 Word Search 28 Word Search 2
     *
     * 29 Word Pattern 2 30 Word Squares
     *
     * 31 Flip Game 2
     *
     * 32 211. Add and Search Word - Data structure design
     */

    public static void test16() {
        String word = "apple";
        List<String> result = t16_l320_generateAbbreviations(word);
        System.out.println(result);
        System.out.println("------");
    }

    /**
     *
     * @param word
     * @return
     *
     * Time: O(n * 2^n)
     * Space: O(n)
     *
     * https://leetcode.com/problems/generalized-abbreviation/solution/
     *
     */
    public static List<String> t16_l320_generateAbbreviations(String word) {
        List<String> result = new ArrayList<String>();
        StringBuilder stb = new StringBuilder();
        t16_l320_helper(word, 0, stb, result, 0);
        Collections.sort(result, (a, b)->{return a.length() - b.length();});
        return result;
    }

    public static void t16_l320_helper(String word, int count, StringBuilder prefix, List<String> result, int idx) {
        int len = prefix.length();
        if (idx == word.length()) {
            // get a solution
            if (count > 0) {
                prefix.append(count);
            }
            result.add(prefix.toString());
            prefix.setLength(len);
            return;
        }

        // abbrv current char
        t16_l320_helper(word, count + 1, prefix, result, idx + 1);

        // do NOT covert this char to digit
        if (count > 0) {
            prefix.append(count);
        }
        prefix.append(word.charAt(idx));
        t16_l320_helper(word, 0, prefix, result, idx + 1); // set the count to 0
        // prefix.deleteCharAt(prefix.length() - 1);
        // reset the stb to its original length.
        prefix.setLength(len);
    }


    /*
     * 17 411. Minimum Unique Word Abbreviation
     *
     * https://leetcode.com/problems/minimum-unique-word-abbreviation/discuss/89883/Java-DFS+Trie+Binary-Search-90ms
     *
     * Use Trie to build a dictionary with a function to check abbreviation.
     *
     * Use DFS with backtracking to generate the abbreviations of a given length.
     *
     * Use binary search to find the smallest possible length.
     *
     */
    public static void test17() {
        String target = "apple";
        String[] dictionary = { "plain", "bmber", "blade" };
        String res = t17_l411_minAbbreviation(target, dictionary);
        System.out.println(res);
    }

    public static String t17_l411_minAbbreviation(String target, String[] dictionary) {
        // create Trie
        TrieNode root = new TrieNode();
        // add all words (len == target.length) in dictionary into Trie
        int len = target.length();
        for (String s : dictionary) {
            if (s.length() == len) {
                insert(root, s);
            }
        }

        StringBuilder stb = new StringBuilder();

        Result result = new Result();

        t17_l411_helper(target, 0, stb, 0, root, result);

        return result.val;
    }

    public static class Result {
        boolean found;
        String val;

        public Result() {
            found = false;
            val = new String();
        }
    }

    /**
     *
     * @param word
     * @param idx
     * @param stb
     * @param num
     * @param root
     * @param result
     *
     * get all abbreviated strings, from short to long.
     * meanwhile, check whether the abbrev can stand for other wrods(search in the Trie)
     */
    public static void t17_l411_helper(String word, int idx, StringBuilder stb, int num, TrieNode root, Result result) {
        if (result.found) {
            return;
        }
        int len = stb.length();
        if (idx == word.length()) {
            if (num > 0) {
                stb.append(num);
            }

            System.out.println(">>>" + stb.toString());
            if (searchAbbrevInTrie(root, stb.toString(), 0) == false) {
                result.val = stb.toString();
                result.found = true;
                return;
            } else {
                stb.setLength(len);
            }
            return;
        }

        // Abbreviate cur Char, make sure that we get the shortest first.
        t17_l411_helper(word, idx + 1, stb, num + 1, root, result);

        // NOT abbreviate cur char
        if (num > 0) {
            stb.append(num);
        }
        stb.append(word.charAt(idx));
        t17_l411_helper(word, idx + 1, stb, 0, root, result);
        // reset the stb.lentgh
        stb.setLength(len);
    }

    public static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;
        int visited;

        public TrieNode() {
            this.children = new HashMap<Character, TrieNode>();
            this.visited = 0;
            this.isEnd = false;
        }
    }

    public static void insert(TrieNode node, String word) {
        if (word.isEmpty()) {
            node.isEnd = true;
            return;
        }
        char nextCh = word.charAt(0);
        if (!node.children.containsKey(nextCh)) {
            node.children.put(nextCh, new TrieNode());
        }
        insert(node.children.get(nextCh), word.substring(1));
    }

    public static void inser2(TrieNode root, String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }

    public static TrieNode search(TrieNode root, String word) {
        if (root == null) {
            return null;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                return null;
            }
            node = node.children.get(ch);
        }
        return node;
    }

    /*
     * search word in the Trie.
     * check whether exist or NOT.
     *
     * num : number of char.
     * check whethe the word(abbrev) in the Trie.
     * if true, which means that this abbrev can be used by word in the Trie.
     * If false, this abbrev can be used as the candidate
     */
    public static boolean searchAbbrevInTrie(
            TrieNode node,
            String word,
            int leadingNumOfChars) {
        if (node == null) {
            return false;
        }

        if (leadingNumOfChars > 0) {
            boolean rev = false;
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                rev |= searchAbbrevInTrie(entry.getValue(), word, leadingNumOfChars - 1);
            }

            return rev;
        } else {
            // num == 0
            if (word.isEmpty() && node.isEnd == true) {
                return true;
            }
            if (word.isEmpty()) {
                return false;
            }
            int i = 0;
            while (i < word.length() && Character.isDigit(word.charAt(i))) {
                leadingNumOfChars = leadingNumOfChars * 10 + (word.charAt(i) - '0');
                i++;
            }
            if (leadingNumOfChars > 0) {
                return searchAbbrevInTrie(node, word.substring(i), leadingNumOfChars);
            } else {
                // word is NOT start with digit
                if (!node.children.containsKey(word.charAt(0))) {
                    return false;
                }
                return searchAbbrevInTrie(node.children.get(word.charAt(0)), word.substring(1), 0);
            }
        }
    }



    public static void test17_1() {
        TrieNode root = new TrieNode();
        String[] sarr = { "plain", "amber", "blade" };
        for (String word : sarr) {
            insert(root, word);
//			inser2(root, word);
        }

        String word = "2a2";
        boolean isAbbr = searchAbbrevInTrie(root, word, 0);
        System.out.println("isAbbr = " + isAbbr);

    }

	/*
	public static void getAbbs(char[] arr, int idx, int len, StringBuilder stb, List<String> result) {
		boolean preIdxIsDigit = stb.length() > 0 && Character.isDigit(stb.charAt(stb.length() - 1));
		if (len == 1) {
			if (idx < arr.length) {
				if (idx == arr.length - 1) {
					result.add(stb.toString() + arr[idx]);
				}
				if (!preIdxIsDigit) {
					// previous char is NOT digit
					result.add(stb.toString() + (arr.length - idx));
				}
			}
		} else {
			if (len > 1) {
				int last = arr.length;
				for (int i = idx + 1; i < arr.length; i++) {
					if (!preIdxIsDigit) {
						// add a number
						stb.append(i - idx);
						getAbbs(arr, i, len - 1, stb, result);
						stb.delete(last, stb.length());
					}
					if (i == idx + 1) {
						// add one char
						stb.append(arr[idx]);
						getAbbs(arr, i, len - 1, stb, result);
						stb.delete(last, stb.length());
					}
				}
			}
		}
	}
	*/



    /*
     * 18 Generate Parentheses
     */


    /*
     * 19 17 Letter Combinations of a Phone Number
     */
    public static void test19() {
        String digits = "23";
        List<String> result = t19_l17_letterComb(digits);
        System.out.println(result);

        System.out.println("---------");
        String digits2 = "22";
        List<String> result2 = t19_l17_letterComb_2(digits2);
        System.out.println(result2);
    }

    public static List<String> t19_l17_letterComb(String digits) {
        String[] map = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        List<String> result = new ArrayList<String>();
        StringBuilder stb = new StringBuilder();
        t19_l17_helper(digits, 0, map, stb, result);
        return result;
    }

    public static void t19_l17_helper(String digits, int idx, String[] map, StringBuilder stb, List<String> result) {
        if (idx == digits.length()) {
            result.add(stb.toString());
            return;
        }

        char curDigit = digits.charAt(idx);
        int curVal = curDigit - '0';
        for (int i = 0; i < map[curVal].length(); i++) {
            char curCh = map[curVal].charAt(i);
            stb.append(curCh);
            t19_l17_helper(digits, idx + 1, map, stb, result);
            stb.deleteCharAt(stb.length() - 1);
        }
    }

    /*
     * follow up one char can only use once for the same number e.g 22, can NOT be
     * aa, bb, cc
     *
     */
    public static List<String> t19_l17_letterComb_2(String digits) {
        String[] map = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"

        };

        List<String> result = new ArrayList<String>();
        StringBuilder stb = new StringBuilder();
        Set<Character> set = new HashSet<Character>();
        t19_l17_helper_2(digits, 0, map, stb, result, set);
        return result;
    }

    public static void t19_l17_helper_2(String digits, int idx, String[] map, StringBuilder stb, List<String> result,
                                        Set<Character> set) {
        if (idx == digits.length()) {
            result.add(stb.toString());
            return;
        }

        char curDigit = digits.charAt(idx);
        int curVal = curDigit - '0';
        for (int i = 0; i < map[curVal].length(); i++) {
            char curCh = map[curVal].charAt(i);
            if (set.contains(curCh)) {
                continue;
            }
            stb.append(curCh);
            set.add(curCh);
            t19_l17_helper_2(digits, idx + 1, map, stb, result, set);
            stb.deleteCharAt(stb.length() - 1);
            set.remove(curCh);
        }
    }

    /*
     * task20: palindrome partitioning
     */
    public static void test20() {
        String s = "aab";
        List<List<String>> result = t20_l131_palindrome_partition(s);
        System.out.println(result);
    }

    public static List<List<String>> t20_l131_palindrome_partition(String s) {
        List<String> list = new ArrayList<String>();
        List<List<String>> result = new ArrayList<List<String>>();
        t20_l131_helper(s, 0, list, result);
        return result;
    }

    public static void t20_l131_helper(String s, int idx, List<String> list, List<List<String>> result) {
        if (idx == s.length()) {
            result.add(new ArrayList<String>(list));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            String prefix = s.substring(idx, i + 1);
            if (t20_l131_is_palindrome(prefix)) {
                list.add(prefix);
                t20_l131_helper(s, i + 1, list, result);
                list.remove(list.size() - 1);
            }
        }
    }

    public static boolean t20_l131_is_palindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /*
     * 20 267. Palindrome Permutation II
     *
     * follow up question for permutation with dup
     *
     * if permuation exists, see Palindrome permutation I get the permutation of the
     * first half, if length == even, reverse every permutation in the first half,
     * and append if length == odd, first half + 'single char' + reverse(first half)
     *
     */
    public static List<String> t21_l267_permutation2(String s) {
        return null;
    }

    /*
     * 22 regular expression
     */

    /*
     * 23 wild card matching
     */

    /*
     * 24 IP address
     */
    public static void test24() {
        String s = "25525511135";
        List<String> result = t24_l93_restoreIpAddresses(s);
        System.out.println(result);
    }

    public static List<String> t24_l93_restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        t24_l93_helper(s, 0, result, list);
        return result;
    }

    public static void t24_l93_helper(String s, int index, List<String> result, List<String> list) {
        if (list.size() == 4) {
            // we got 4 parts
            if (index != s.length()) {
                // haven't reach the end
                return;
            }
            // compose a valid IP
            StringBuilder stb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                stb.append(list.get(i));
                stb.append(".");
            }
            // delete the last "."
            stb.deleteCharAt(stb.length() - 1);
            result.add(stb.toString());
            return;
        }

        for (int i = index; i < s.length() && i < index + 3; i++) {
            String curPart = s.substring(index, i + 1);
            if (t24_l93_valid_ip_part(curPart)) {
                list.add(curPart);
                t24_l93_helper(s, i + 1, result, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static boolean t24_l93_valid_ip_part(String s) {
        if (s.charAt(0) == '0') { // s starts with 0, it can only be "0"
            // "00", "000", "001", "01" are NOT valid
            return s.equals("0"); // if s's value is 0, only "0" is valid, "00", "000" is NOT
        }
        return Integer.parseInt(s) > 0 && Integer.parseInt(s) <= 255;
    }

    /**
     * 25 Word Break II
     *
     * s = "catsanddog",
     *
     * dict = ["cat", "cats", "and", "sand", "dog"].
     *
     * A solution is ["cats and dog", "cat sand dog"].
     *
     * @param s
     * @param wordDict
     * @return
     */

    public static void test25() {
        String s = "catsanddog";
        String[] dicts = { "cat", "cats", "and", "sand", "dog" };
        List<String> wordDict = new ArrayList<String>();
        for (String str : dicts) {
            wordDict.add(str);
        }

        List<String> result = t25_l140_wordBreak(s, wordDict);
        System.out.println(result);
    }

    public static List<String> t25_l140_wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return t25_l140_helper(s, wordDict, map);
    }

    /**
     *
     * @param s
     * @param wordDict
     * @param map:
     *            key = string, value: a list of strings, each string is a valid
     *            sentence where each is a valid word
     * @return
     *
     * 		Assume: there is no duplicates in the word
     */
    public static List<String> t25_l140_helper(String s, List<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> result = new ArrayList<String>();
        if (s.length() == 0) {
            result.add("");
            return result;
        }

        // traverse all the words in dictionary
        for (String word : wordDict) {
            // if the current string is start with word
            if (s.startsWith(word)) {
                // get the list of strings, which separated from s.substring(word.length())
                List<String> sublist = t25_l140_helper(s.substring(word.length()), wordDict, map);
                // construct the current result.
                for (String substr : sublist) {
                    result.add(word + (substr.isEmpty() ? "" : " ") + substr);
                }
            }
        }
        map.put(s, result);
        return result;
    }

    /**
     * 26 Word Ladder2
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>> t26_l126_findLadders(String beginWord, String endWord, List<String> wordList) {
        return null;
    }

    /**
     * 27
     *
     * 79. Word Search
     *
     * @param board
     * @param word
     * @return
     */
    public static void test27() {
        char[][] board = { "ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray() };

        String s = "ABCCED";
        boolean rev = t27_l79_woreSearch(board, s);
        System.out.println("rev = " + rev);

        char[][] b2 = { "a".toCharArray() };
        String w2 = "a";
        boolean rev2 = t27_l79_woreSearch(b2, w2);
        System.out.println("rev2 = " + rev2);
    }

    public static boolean t27_l79_woreSearch(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }
        int rLen = board.length;
        int cLen = board[0].length;
        boolean[][] visited = new boolean[rLen][cLen];
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (board[i][j] == word.charAt(0) && t27_l79_helper(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean t27_l79_helper(char[][] board, String word, boolean[][] visited, int x, int y, int idx) {
        if (word.length() == idx) {
            return true;
        }
        // NOT exmpty, explore the next char
        int rLen = board.length;
        int cLen = board[0].length;
        boolean rev = false;
        if (x >= 0 && x < rLen && y >= 0 && y < cLen && !visited[x][y] && board[x][y] == word.charAt(idx)) {
            visited[x][y] = true;
            for (int k = 0; k < 4; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];

                rev |= t27_l79_helper(board, word, visited, nextX, nextY, idx + 1);
                // if rev is true, return rev.
                if (rev) {
                    return rev;
                }
            }
            visited[x][y] = false;
        }
        return rev;
    }

    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    /**
     * task28
     * 212. Word Search II
     *
     */
    public static void test28() {
        char[][] board = {
                "oaan".toCharArray(),
                "etae".toCharArray(),
                "ihkr".toCharArray(),
                "iflv".toCharArray()
        };
        String[] words = { "oath", "pea", "eat", "rain" };
        List<String> result = t28_l212_findWords(board, words);
        System.out.println(result);
    }

    public static List<String> t28_l212_findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return new ArrayList<String>();
        }

        // add all words into Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }
        int rLen = board.length;
        int cLen = board[0].length;
        List<String> result = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        StringBuilder stb = new StringBuilder();
        boolean[][] visited = new boolean[rLen][cLen];

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                t28_l212_helper(board, visited, stb, root, i, j, set);
            }
        }
        result.addAll(set);
        return result;
    }

    public static void t28_l212_helper(
            char[][] board,
            boolean[][] visited,
            StringBuilder stb,
            TrieNode node,
            int x,
            int y,
            Set<String> set) {
        int rLen = board.length;
        int cLen = board[0].length;

        if (x < 0 || x >= rLen || y < 0 || y >= cLen || visited[x][y]) {
            return;
        }

        char nextChar = board[x][y];
        if (node.children.containsKey(nextChar)) {
            // go to next
            TrieNode nextNode = node.children.get(nextChar);
            visited[x][y] = true;
            stb.append(nextChar);
            // check whether nextNode is end
            if (nextNode.isEnd) {
                if (!set.contains(stb.toString())) {
                    set.add(stb.toString());
                }
            }

            for (int k = 0; k < 4; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];
                t28_l212_helper(board, visited, stb, nextNode, nextX, nextY, set);
            }
            // reset
            visited[x][y] = false;
            stb.deleteCharAt(stb.length() - 1);

        }
    }

    /*
     * task29 Word Pattern 2
     *
     * 291. Word Pattern II
     * https://leetcode.com/problems/word-pattern-ii/discuss/73664/Share-my-Java-backtracking-solution
     *
     * input:
     * string: redblueredblue
     * pattern: abab
     *
     * output: return true
     *
     * algorithm:
     *
     * backtracking.
     * first let the 'a' matches 'r', and 'b' matches the 'e'. then the next 'a' doesn't match the 'd', backtrack,
     * let the 'b' matches the 'ed',  'a' doesn't match 'b', let 'b' matches 'edb', ...
     * until 'b' matches 'edblue', then the next 'a' matches the 'r'.
     *
     * here, we find a solution
     * a => r
     * b => edblue
     *
     * we use a map to store the existing matches, during the traversing.
     * use a set to store the mapped substring. make sure they are not mapped by different pattern chars.
     *
     */
    public static void test29() {
        String pattern = "aabb";
        String str = "xyzabcxzyabc";
        boolean rev = t29_l291_wordPatternMatch(pattern, str);
        System.out.println("Rev = " + rev);
    }

    public static boolean t29_l291_wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<Character, String>();
        int i = 0, j = 0;
        Set<String> set = new HashSet<String>();
        return t29_l291_isMatch(str, i, pattern, j, map, set);
    }

    public static boolean t29_l291_isMatch(
            String str, int i, // string and its current index
            String pattern, int j,  // pattern and its current index
            Map<Character, String> map,
            Set<String> subStrSet) {
        // base case
        if (i == str.length() && j == pattern.length()) {
            return true;
        }
        if (i == str.length() || j == pattern.length()) {
            return false;
        }

        char patternCh = pattern.charAt(j);
        if (map.containsKey(patternCh)) {
            String subStr = map.get(patternCh);

            // check whether we can use it to match str[i..i + s.length]
            if (!str.startsWith(subStr, i)) {
                return false;
            }

            // if it can match, OK, continue to match the rest
            return t29_l291_isMatch(str, i + subStr.length(), pattern, j + 1, map, subStrSet);
        }

        // pattern character does NOT exist in the map
        for (int k = i; k < str.length(); k++) {
            String subStr = str.substring(i, k + 1);
            System.out.println("~~~~~: " + patternCh);
            System.out.println("===>>>: " + subStr);
            System.out.println("==============");
            // this subStr already be mapped. continue
            if (subStrSet.contains(subStr)) {
                continue;
            }

            // create or update it.
            map.put(patternCh, subStr);
            subStrSet.add(subStr);

            // continue to match the rest
            if (t29_l291_isMatch(str, k + 1, pattern, j + 1, map, subStrSet)) {
                return true;
            }

            // backtracking
            map.remove(patternCh);
            subStrSet.remove(subStr);
        }

        // we tried but did NOT find the result
        return false;

    }

    /*
     * t30 425. Word Squares
     *
     * https://leetcode.com/problems/word-squares/#/solutions
     * https://leetcode.com/problems/word-squares/discuss/91333/Explained.-My-Java-solution-using-Trie-126ms-1616
     *
     * Given a set of words (without duplicates), find all word squares you can build from them.
     * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
     * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
     *
     *b a l l
     *a r e a
     *l e a d
     *l a d y
     *Note:
     *	There are at least 1 and at most 1000 words.
     *	All words will have the exact same length.
     *	Word length is at least 1 and at most 5.
     *	Each word contains only lowercase English alphabet a-z.
     *
     *
     *
     *
     */
    public static void test30() {
        String[] words = { "area", "lead", "wall", "lady", "ball" };
        List<List<String>> result = t30_l425_wordSquares(words);
        System.out.println(result);
    }

    public static List<List<String>> t30_l425_wordSquares(String[] words) {
        List<String> list = new ArrayList<String>();
        List<List<String>> result = new ArrayList<List<String>>();
        TrieNode root = new TrieNode();
        // add all words into Trie
        for (String s : words) {
            insert(root, s);
        }

        int length = words[0].length();
        t30_l425_helper(words, length, list, result, root);
        return result;
    }

    public static void t30_l425_helper(
            String[] words,
            int length,
            List<String> list,
            List<List<String>> result,
            TrieNode root) {
        if (list.size() == length) {
            // we get a result
            result.add(new ArrayList<String>(list));
            return;
        }

        // get the index we want to set
        int index = list.size();
        // get the prefix of the current combination of words.
        // e.g
        /*
         * wall the next string should start with 'a'
         *
         * wall area the next string should start with 'le'
         */
        StringBuilder prefix_stb = new StringBuilder();
        for (String str : list) {
            prefix_stb.append(str.charAt(index));
        }
        System.out.println("###: index: " + index);
        System.out.println("!!!: " + prefix_stb.toString());

        String prefix = prefix_stb.toString();
        List<String> strs_with_prefix = t30_all_str_with_prefix(root, prefix);
        System.out.println("==>>>>: " + strs_with_prefix);

        for (String str : strs_with_prefix) {
            list.add(str);
            t30_l425_helper(words, length, list, result, root);
            list.remove(list.size() - 1);
        }
    }

    public static List<String> t30_all_str_with_prefix(TrieNode root, String prefix) {
        if (root == null) {
            return new ArrayList<String>();
        }

        TrieNode nodeOfPrefix = search(root, prefix);
        List<String> result = new ArrayList<String>();
        StringBuilder stb = new StringBuilder();
        t30_all_str_with_prefix_helper(nodeOfPrefix, prefix, stb, result);
        return result;
    }

    public static void t30_all_str_with_prefix_helper(TrieNode node, String prefix, StringBuilder stb, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.isEnd) {
            // we find the solution
            String sln = prefix + stb.toString();
            result.add(sln);
            // don't add return here
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            stb.append(entry.getKey());
            t30_all_str_with_prefix_helper(entry.getValue(), prefix, stb, result);
            stb.deleteCharAt(stb.length() - 1);
        }
    }




}

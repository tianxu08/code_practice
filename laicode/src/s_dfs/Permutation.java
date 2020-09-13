package dfs;

import java.util.*;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		String s = null;
		List<String> result = permutations(s);
		System.out.println("result = " + result);
	}

//	public static List<String> permutations(String set) {
//		// write your solution here
//		ArrayList<String> result = new ArrayList<String>();
//		if (set == null || set.length() == 0) {
//			return result;
//		}
//		ArrayList<Character> input = new ArrayList<Character>();
//		for (int i = 0; i < set.length(); i++) {
//			input.add(set.charAt(i));
//		}
//		Collections.sort(input);
//
//		
//		ArrayList<Character> line = new ArrayList<Character>();
//		boolean[] visited = new boolean[input.size()];
//		helper(result, input, line, visited);
//		return result;
//	}
//
//	public static void helper(ArrayList<String> result, ArrayList<Character> input,
//			ArrayList<Character> line, boolean[] visited) {
//		if (line.size() == input.size()) {
//			// we get a solution.
//			String oneSln = buildSolution(line);
//			result.add(oneSln);
//			return;
//		}
//		for (int i = 0; i < input.size(); i++) {
//			if (visited[i] == true
//					|| (i > 0 && input.get(i - 1) == input.get(i) && visited[i - 1] == false)) {
//				continue;
//			}
//			line.add(input.get(i));
//			visited[i] = true;
//			helper(result, input, line, visited);
//
//			line.remove(line.size() - 1);
//			visited[i] = false;
//		}
//
//	}
//
//	public static String buildSolution(ArrayList<Character> line) {
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < line.size(); i++) {
//			sb.append(line.get(i));
//		}
//		return sb.toString();
//	}
	
	
	public static List<String> permutations(String set) {
	    // write your solution here
	    ArrayList<String> result = new ArrayList<String>();
	    if (set == null || set.length() == 0) {
	      return result;
	    }
	    ArrayList<Character> input = new ArrayList<Character>();
	    for(int i = 0; i < set.length(); i ++) {
	      input.add(set.charAt(i));
	    }
	    Collections.sort(input);
	    
	   
	    ArrayList<Character> line = new ArrayList<Character>();
	    boolean[] visited = new boolean[input.size()];
	    helper(result, input, line, visited);
	    return result;
	  }
	  
	  public static void helper(ArrayList<String> result, ArrayList<Character> input, 
	                    ArrayList<Character> line, boolean[] visited) {
	    if (line.size() == input.size()) {
	      // we get a solution.
	      String oneSln = buildSolution(line);
	      result.add(oneSln);
	      return ;
	    }
	    for(int i = 0; i < input.size(); i++) {
	      if(visited[i]== true || 
	         (i > 0 &&  input.get(i - 1) == input.get(i) && visited[i - 1] == false)) {
	        continue;
	      }
	      line.add(input.get(i));
	      visited[i] = true;
	      helper(result, input, line, visited);
	      
	      line.remove(line.size() - 1 );
	      visited[i] = false;
	    }
	    
	  }
	  
	  public static String buildSolution(ArrayList<Character> line) {
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i< line.size(); i ++) {
	      sb.append(line.get(i));
	    }
	    return sb.toString();
	  }

}

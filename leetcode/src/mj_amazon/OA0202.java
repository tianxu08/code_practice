package mj_amazon;
import java.util.*;

public class OA0202 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * https://github.com/fionaguoguolu/tutorials/blob/master/leetcode/src/main/java/movie/recom/Movie.java
	 * 
	 */
	
	public static class Movie{
		private final int movieId;
	    private final float rating;
	    private List<Movie> similarMovies; // Similarity is bidirectional
	    
	    public Movie(int movieId, float rating) {
	        this.movieId = movieId;
	        this.rating = rating;
	        similarMovies = new ArrayList<Movie>();
	    }
	    
	    public List<Movie> getSimilarMovies(){
	    	return new ArrayList<Movie>();
	    };
	}
	
	public static Set<Movie> getMovieRecommendations(Movie movie, int N) {
		if (movie == null)
        	return null;
        
        LinkedList<Movie> q = new LinkedList<Movie>();
        HashSet<Movie> visited = new HashSet<Movie>();
        
       
    	Comparator<Movie> myComp = new Comparator<Movie>() {
			
			@Override
			public int compare(Movie o1, Movie o2) {
				// TODO Auto-generated method stub
				if (o1.rating == o2.rating) {
					return 0;
				}
				
				return o1.rating < o2.rating ? 1 : -1;
			}
		};
    	
        TreeSet<Movie> heap = new TreeSet<Movie>(myComp);
        
        q.addAll(movie.getSimilarMovies());
        visited.addAll(movie.getSimilarMovies());
        heap.addAll(movie.getSimilarMovies());
        while (heap.size() > N)
        	heap.pollLast();
        
        // Do BFS
        while (!q.isEmpty()) {
        	Movie m = q.poll();
        	for (Movie m_nei : m.getSimilarMovies()) {
        		if (!visited.contains(m_nei)) {
        			q.add(m_nei);
        			visited.add(m_nei);
        			heap.add(m_nei);
        			
        	        while (heap.size() > N)
        	        	heap.pollLast();
        		}
        	}
        }
       
        Set<Movie> result = new HashSet<Movie>();
        for(Movie m: heap) {
        	result.add(m);
        }
        return result;
    }
	
	
	public static Set<Movie> getMovieRecommendations2(Movie movie, int N) {
        if (movie == null)
        	return null;
        
        LinkedList<Movie> q = new LinkedList<Movie>();
        HashSet<Movie> visited = new HashSet<Movie>();
        
    	class MyComp implements Comparator<Movie> {
    		public int compare(Movie o1, Movie o2) {
    			return (o2.rating - o1.rating) > 0 ? 1:-1;
    		}
    	}
    	
        // heap to keep the n top rated movies
        TreeSet<Movie> heap = new TreeSet<Movie>(new MyComp());
        
        q.addAll(movie.getSimilarMovies());
        visited.addAll(movie.getSimilarMovies());
        heap.addAll(movie.getSimilarMovies());
        while (heap.size() > N)
        	heap.pollLast();
        
        while (!q.isEmpty()) {
        	Movie m = q.poll();
        	for (Movie m_n : m.getSimilarMovies()) {
        		if (!visited.contains(m_n)) {
        			q.add(m_n);
        			visited.add(m_n);
        			heap.add(m_n);
        			
        	        while (heap.size() > N)
        	        	heap.pollLast();
        		}
        	}
        }
        
        Set<Movie> result = new HashSet<Movie>(N);
        for (Movie m : heap)
            result.add(m);
        return result;
    }
	
	public static void test() {
		String[] blocks = {
				"5", "-2", "4", "Z", "X", "9", "+", "+"
		};
		int n = 8;
		int rev = totalScore(blocks, n);
		System.out.println("rev = " + rev);
		
		String[] block2 = {
				"1", "2", "+", "Z"
		};
		int n2 = 4;
		int rev2 = totalScore(block2, n2);
		System.out.println("rev2 = " + rev2);
	}
	
	
	public static int totalScore(String[] blocks, int n) {
		if (blocks == null || blocks.length == 0 || blocks.length != n) {
			return 0;
		}
		
		LinkedList<Integer> stack = new LinkedList<Integer>();
		int totalScore = Integer.parseInt(blocks[0]);
		stack.offerLast(Integer.parseInt(blocks[0]));
		for(int i = 1; i < n; i++) {
			String curStr = blocks[i];
			if (curStr.equals("X")) {
				if (!stack.isEmpty()) {
					Integer lastScore = stack.pollLast();
					lastScore *= 2;
					stack.offerLast(lastScore);
					totalScore += lastScore;
				}
			} else if (curStr.equals("Z")) {
				if (!stack.isEmpty()) {
					Integer lastScore = stack.pollLast();
					totalScore -= lastScore;
				}
			} else if (curStr.equals("+")) {
				
				if (!stack.isEmpty() && stack.size() >= 2) {
					Integer lastScore = stack.pollLast();
					Integer secondLastScore = stack.pollLast();
					stack.offerLast(secondLastScore);
					
					stack.offerLast(lastScore);
					stack.offerLast(lastScore + secondLastScore);
					
					totalScore += (lastScore + secondLastScore);
				}
				
			} else {
				
				Integer curInt = Integer.parseInt(curStr);
				stack.offerLast(curInt);
				totalScore += curInt;
			}
		}
		
		return totalScore;
		
	}
	
	public static int totalScore2(String[] blocks, int n) {
		if (blocks == null || blocks.length == 0 || blocks.length != n) {
			return 0;
		}
		if (blocks[0].equals("Z")) {
			return 0;
		}
		
		if (!isNumeric(blocks[0])) {
			return 0;
		}
		
		LinkedList<Integer> stack = new LinkedList<Integer>();
		int totalScore = Integer.parseInt(blocks[0]);
		stack.offerLast(Integer.parseInt(blocks[0]));
		for(int i = 1; i < n; i++) {
			String curStr = blocks[i];
			if (curStr.equals("X")) {
				if (!stack.isEmpty()) {
					Integer lastScore = stack.pollLast();
					lastScore *= 2;
					stack.offerLast(lastScore);
					totalScore += lastScore;
				}
			} else if (curStr.equals("Z")) {
				if (!stack.isEmpty()) {
					Integer lastScore = stack.pollLast();
					totalScore -= lastScore;
				}
			} else if (curStr.equals("+")) {
				
				if (!stack.isEmpty() && stack.size() >= 2) {
					Integer lastScore = stack.pollLast();
					Integer secondLastScore = stack.pollLast();
					stack.offerLast(secondLastScore);
					
					stack.offerLast(lastScore);
					stack.offerLast(lastScore + secondLastScore);
					
					totalScore += (lastScore + secondLastScore);
				}
				
			} else {
				
				Integer curInt = Integer.parseInt(curStr);
				stack.offerLast(curInt);
				totalScore += curInt;
			}
		}
		return totalScore;
	}
	
	public static boolean isNumeric(String str)
	{
		return str.matches("\\d+");
	}
	
	
	
	/*
	 * The first coding task is somewhat ambiguous. 
	 * It might be a good question for the onsite interview, since the interviewee is able to communicate with the interviewer and make things clear. However, for the online assessment, the applicants can only finish the task with the help of guess. 
	 * For example, 
	 * 'Z', the last score is removed from the total score. 
	 * It has two meanings: (1) the last score is also removed from the stack as well as deducted from the total score. 
	 * (2) the last score is only deducted from the total score. According to the example, I guess it is situation (1); 
	 * '+', the last two scores are added and then added to the total score. 
	 * This is confused..... 
	 * According to the explanation in the last line, after the block marked '+' is hit, 
	 * the total score is 27 (13 + 9 + 5). 
	 * I guess now, the last score is 9 and the second last score is -4. so the sum of them is 5. 
	 * after we get the sum of the two last scores, we also need to add the sum into stack. 
	 * 
	 * However, we only have 1 example. More examples are helpful to make thing clear.  
	 */

}

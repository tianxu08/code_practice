package dp;

public class EditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	  M[i][j] stands that the minimum distance between the first i characters in one, which is one[0, i - 1]
	          and the first j characters in two, which is two[0, j - 1]
	  base case: M[0][0] = 0
	             M[i][0] = i;
	             M[0][j] = j;
	  induction rule: M[i][j] =   
	                            one.charAt(i - 1) == two.charAt(j - 1)  M[i-1][j - 1]
	                            else    min(M[i-1][j], M[i][j - 1], M[i-1][j-1]) + 1
	  !!! string.length()
	  */
	  public int editDistance(String one, String two) {
	    // write your solution here
	    if(one == null && two == null) {
	       return 0;
	    }
	    if(one == null) {
	      return two.length();
	    }
	    if(two == null) {
	      return one.length();
	    }
	    int m = one.length();
	    int n = two.length();
	    int[][] M = new int[m+1][n+1];
	    
	    //initialize
	    M[0][0] = 0;
	    for(int i = 1; i <= m; i++) {
	      M[i][0] = i;
	    }
	    
	    for(int j = 1; j <= n; j++) {
	      M[0][j] = j;
	    }
	    
	    for(int i = 1; i <= m; i++) {
	      for(int j = 1; j <= n; j++) {
	        if(one.charAt(i - 1) == two.charAt(j - 1)) {
	          M[i][j] = M[i-1][j - 1];
	        } else {
	          M[i][j] = Math.min(Math.min(M[i-1][j], M[i][j-1]), M[i-1][j-1]) + 1;
	        }
	      }
	    }
	    
	    return M[m][n];
	  }

}

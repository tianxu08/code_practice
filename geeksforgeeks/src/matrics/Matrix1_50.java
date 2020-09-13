package matrics;

public class Matrix1_50 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 1 Rotate Matrix Elements
	 * 
	 * For 4*4 matrix
Input:
1    2    3    4    
5    6    7    8
9    10   11   12
13   14   15   16

Output:
5    1    2    3
9    10   6    4
13   11   7    8
14   15   16   12
https://www.geeksforgeeks.org/rotate-matrix-elements/
	 */
	public static void M1_rotate(int[][] m) {
		int rL = m.length;
		int cL = m[0].length;
		int up = 0;
		int down = rL - 1;
		int left = 0;
		int right = cL - 1;
		
		while(up < down && left < right) {
			
			int temp = m[up + 1][left];
			
			for (int i = up + 1; i < down - 1; i++) {
				m[i][left] = m[i + 1][left];
			}
			
			
			
			
		}
		
		
	}

	
}

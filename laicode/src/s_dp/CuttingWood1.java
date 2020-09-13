package dp;

public class CuttingWood1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Cutting Wood I Hard DP There is a wooden stick with length L >= 1, we
	 * need to cut it into pieces, where the cutting positions are defined in an
	 * int array A. The positions are guaranteed to be in ascending order in the
	 * range of [1, L - 1]. The cost of each cut is the length of the stick
	 * segment being cut. Determine the minimum total cost to cut the stick into
	 * the defined pieces. Examples L = 10, A = {2, 4, 7}, the minimum total
	 * cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
	 */

	// M[i] stands minimum cost cutting in position i
	// base case: M[1] = 0;
	// induction rule:
	//
	//  1 2 3 4 5 6 7 8 9 10 len = 10
	//    |   |     |
	// first cut at 4 cost 10      1 2 3 4 len = 4    5 6 7 8 9 10 len = 6
	// second cut at 2  cost 4
	// third cut at 7   cost 6
	// total 10  + 4  6 = 20;
	//
	// 

	public int minCost(int[] cuts, int length) {
		// write your solution here

		return 0;

	}

}

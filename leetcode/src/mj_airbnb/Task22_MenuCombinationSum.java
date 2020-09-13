package mj_airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task22_MenuCombinationSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] prices = {2.40, 0.01, 6.00, 2.58};
		
		
		List<List<Double>> result = getCombs(prices, 2.41);
		System.out.println("=============");
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).size(); j++) {
				System.out.print(result.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	
	
	private static final double eps = 0.1;
	public static List<List<Double>> getCombs(double[] prices, double target) {
		List<List<Double>> result = new ArrayList<>();
		if (prices == null || prices.length == 0 || target <= 0) {
			return result;
		}
		
		List<Double> list = new ArrayList<>();
		Arrays.sort(prices);
		dfs(prices, 0, target, list, result);
		return result;
	}
	
	public static void dfs(double[] prices, int startIdx, double target, List<Double> combo, List<List<Double>> result) {
		if (Math.abs(target) < eps) {
			// find a slution
			result.add(new ArrayList<>(combo));
			return ;
		}
		
		for (int i = startIdx; i < prices.length; i++) {
			if (i != startIdx && prices[i] == prices[i - 1]) {
				continue;  // skip the duplicate price
			}
			if (prices[i] - target > eps) {
				break;
			}
			combo.add(prices[i]);
			dfs(prices, i + 1, target - prices[i], combo, result);
			combo.remove(combo.size() - 1);
		}
	}
	
	
	

}

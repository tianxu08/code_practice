package small_yan;

public class Class1_SymbolGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	int[] sum;
	String[] symbols;
	
	public Class1_SymbolGenerator(int[] weight, String[] symbols) {
		sum = new int[symbols.length];
		sum[0] = weight[0];
		for(int i = 1; i < sum.length; i ++) {
			sum[i] += sum[i - 1] + weight[i];
		}
		this.symbols =symbols;
	}
	
	public String getSymbol() {
		int random =(int) Math.random() * (sum[sum.length - 1]);
		return symbols[findSamlestLarger(random, sum)];
		
	}
	
	public int findSamlestLarger(int random, int[] sum) {
		int left = 0, right = sum.length - 1;
		while(left <= right) {
			int mid = left + (right - left)/2;
			if (sum[mid] <= random) {
				left = mid + 1;
			} else {
				right =mid - 1;
			}
		}
		return left;
	}

}

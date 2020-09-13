package mj_airbnb;

public class Task15_HilbertCurve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int hilbertCurve(int x, int y, int iter) {
		if (iter == 0) {
			return 1;
		}
		int len = 1 << (iter - 1);
		int num = 1 << (2 * (iter - 1));
		
		if (x >= len && y >= len) {
			// 3 shap is exactlly same with previous iteration
			return 2 * num + hilbertCurve(x - len, y - len, iter - 1);
		} else if (x < len && y >= len) {
			// 2 Shape is identical with previous iteration
			return num + hilbertCurve(x, y - len, iter - 1);
		} else if (x < len && y < len) {
			// 1 clock-wise rotate 90
			return hilbertCurve(y, x, iter - 1);
		} else {
			// 4 anti-clockwise
			return 3 * num + hilbertCurve(len - y - 1, 2 * len -x - 1, iter - 1);
		}
	}
}

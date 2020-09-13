package sortbynum;
import java.util.*;
public class Task398_RandomPickIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * http://www.cnblogs.com/grandyang/p/5875509.html
	 * 
	 * 这道题指明了我们不能用太多的空间，那么省空间的随机方法只有水塘抽样了，
	 * LeetCode之前有过两道需要用这种方法的题目Shuffle an Array和Linked List Random Node。
	 * 那么如果了解了水塘抽样，这道题就不算一道难题了，
	 * 我们定义两个变量，计数器cnt和返回结果res，我们遍历整个数组，
	 * 如果数组的值不等于target，直接跳过；如果等于target，
	 * 计数器加1，然后我们在[0,cnt]范围内随机生成一个数字，
	 * 
	 * 如果这个数字是0，我们将res赋值为i即可，
	 */
	
	int[] nums;
	Random rand;

	public Task398_RandomPickIndex(int[] nums) {
		this.nums = nums;
		this.rand = new Random();
	}

	public int pick(int target) {
		int total = 0;
		int res = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				// randomly select an int from 0 to the nums of target. If x
				// equals 0, set the res as the current index. The probability
				// is always 1/nums for the latest appeared number. For example,
				// 1 for 1st num, 1/2 for 2nd num, 1/3 for 3nd num (1/2 * 2/3
				// for each of the first 2 nums).
				total ++;
				int x = rand.nextInt(total);
				if (x == 0) {
					res = i;
				}
			}
		}
		return res;
	}
	


}

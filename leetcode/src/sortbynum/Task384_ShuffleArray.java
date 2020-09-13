package sortbynum;

import java.util.Arrays;

public class Task384_ShuffleArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3};
		Task384_ShuffleArray obj = new Task384_ShuffleArray(nums);
		int[] param1 = obj.shuffle();
		System.out.println(Arrays.toString(param1));
		System.out.println("---------------------");
		int[] param2 = obj.reset();
		System.out.println(Arrays.toString(param2));
	}
	
	private int[] array;
	private int[] shuf;
	
	public Task384_ShuffleArray(int[] nums) {
		this.array = nums;
		this.shuf = Arrays.copyOf(nums, nums.length);
		
	}
	
	/** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return array;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	System.out.println(shuf.length);
        for(int i = 0; i < shuf.length; i++) {
        	int randIdx = rand(0, i);
        	swap(shuf, i, randIdx);
        }
        return shuf;
    }
    
    // get random [low.. high]
    private int rand(int low, int high) {
    	int randV = low + (int)(Math.random() * (high - low + 1));
    	return randV;
    }
    private void swap(int[] array, int x, int y) {
    	int temp = array[x];
    	array[x] = array[y];
    	array[y] = temp;
    }

}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */


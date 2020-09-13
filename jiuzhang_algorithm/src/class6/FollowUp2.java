package class6;
import java.util.*;

public class FollowUp2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 连续子数组和
	 * 
	 */
	/*
	 * Subarray Sum
	 * http://www.lintcode.com/en/problem/subarray-sum/
	 * Given an integer array, find a subarray where the sum of numbers is zero. 
	 * Your code should return the index of the first number and the index of the last number.
	 */
	
	/*
	 * Subarray Sum Closest
	 * http://www.lintcode.com/en/problem/subarray-sum-closest/
	 * Given an integer array, find a subarray with sum closest to zero. 
	 * Return the indexes of the first number and last number.
	 * 
	 * http://www.lintcode.com/en/problem/subarray-sum-closest/
	 */
	
	class Pair {
        int sum;
        int index;
        public Pair(int s, int i) {
            sum = s;
            index = i;
        }
    }
    public ArrayList<Integer> subarraySumClosest(int[] nums) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer> ();
        if (nums == null || nums.length == 0) {
            return res;
        } 
        
        int len = nums.length;
        if(len == 1) {
            res.add(0);
            res.add(0);
            return res;
        }
        Pair[] sums = new Pair[len+1];
        int prev = 0;
        sums[0] = new Pair(0, 0);
        for (int i = 1; i <= len; i++) {
            sums[i] = new Pair(prev + nums[i-1], i);
            prev = sums[i].sum;
        }
        Arrays.sort(sums, new Comparator<Pair>() {
           public int compare(Pair a, Pair b) {
               return a.sum - b.sum;
           } 
        });
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {
            
            if (ans > sums[i].sum - sums[i-1].sum) {
                ans = sums[i].sum - sums[i-1].sum;
                res.clear();
                int[] temp = new int[]{sums[i].index - 1, sums[i - 1].index - 1};
                Arrays.sort(temp);
                res.add(temp[0] + 1);
                res.add(temp[1]);
            }
        }
        
        return res;
    }
	
	
	/*
	 * 2 Bad Version
	 * 2.1 One bad version
	 * 2.2 K machine bad version
	 */
	
    
	
	
	
	
	/*
	 * 3 桶的思想
	 * Maximum Gap
	 * https://leetcode.com/problems/maximum-gap/
	 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
	 * Try to solve it in linear time/space.
	 * Return 0 if the array contains less than 2 elements.
	 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
	 * 
	 * 
	 */
	public static int task3_maximumGap(int[] num) {
        if (num == null || num.length <= 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < num.length; i ++) {
			min = Math.min(min, num[i]);
			max = Math.max(max, num[i]);
		}
		
		if (max == min) {
			return 0;
		}// all elements are same
		
		int bucketLen = (int) Math.ceil((double)(max - min)/ (double) (num.length - 1));
		int bucketNum = (max - min)/bucketLen + 1;
		
		// create the bucket, we only need the bucket.min, bucket.max
		// so use two arraylist
		ArrayList<Integer> bucketMin = new ArrayList<Integer>();
		ArrayList<Integer> bucketMax = new ArrayList<Integer>();
		
		// int the bucketMin, bucketMax
		for(int i = 0; i < bucketNum; i ++) {
			bucketMin.add(Integer.MAX_VALUE);
			bucketMax.add(Integer.MIN_VALUE);
		}
		
		// traverse the num[], and assign the element into corresponding position. 
		for(int i = 0; i < num.length; i ++) {
			int position = (int)(num[i] - min)/bucketLen;
			bucketMin.set(position, Math.min(bucketMin.get(position), num[i]));
			bucketMax.set(position, Math.max(bucketMax.get(position), num[i]));
		}
		
		// get the maxGap
		// p points a un null bucket
		// q points the next un null bucket. 
		// q.min - p.max might be the result
		int maxGap = Integer.MIN_VALUE;
		int previous = min;
		for(int i = 0; i < bucketNum; i++) {
			// if the bucket is null, continue
			if (bucketMin.get(i) == Integer.MAX_VALUE && bucketMax.get(i) == Integer.MIN_VALUE) {
				continue;
			}
			maxGap = Math.max(maxGap, bucketMin.get(i) - previous);
			previous = bucketMax.get(i);	
		}
		
		return maxGap;
    }
	
	/*
	 * 4 Permutation 构造
	 * 4.1 Next Permutation
	 *     https://oj.leetcode.com/problems/next-permutation/
	 * 
	 * 4.2 Permutation Sequence
	 * http://www.lintcode.com/en/problem/permutation-sequence/
	 */
	
	/*
	 * 4.3 Permutation Index
	 * http://www.lintcode.com/en/problem/permutation-index/
	 */
	long fac(int numerator) {
		
		long now = 1;
		for (int i = 1; i <= numerator; i++) {
			now *= (long) i;
		}
		return now;
	}

	long generateNum(HashMap<Integer, Integer> hash) {
		long denominator = 1;
		int sum = 0;
		for (int val : hash.values()) {
			if(val == 0 )	
				continue;
			denominator *= fac(val);
			sum += val;
		}
		if(sum==0) {
			return sum;
		}
		return fac(sum) / denominator;
	}

	public long permutationIndex(int[] A) {
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < A.length; i++) {
			if (hash.containsKey(A[i]))
				hash.put(A[i], hash.get(A[i]) + 1);
			else {
				hash.put(A[i], 1);
			}
		}
		long ans = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[j] < A[i]) {
					hash.put(A[j], hash.get(A[j])-1);
					ans += generateNum(hash);
					hash.put(A[j], hash.get(A[j])+1);
					
				}
			
			}
				hash.put(A[i], hash.get(A[i])-1);
		}
		
		return ans+1;

	}

	/*
	 * 4.4 Permutation index2
	 * http://www.lintcode.com/en/problem/permutation-index-ii/
	 */
	long fac2(int numerator) {

		long now = 1;
		for (int i = 1; i <= numerator; i++) {
			now *= (long) i;
		}
		return now;
	}

	long generateNum2(HashMap<Integer, Integer> hash) {
		long denominator = 1;
		int sum = 0;
		for (int val : hash.values()) {
			if (val == 0)
				continue;
			denominator *= fac2(val);
			sum += val;
		}
		if (sum == 0) {
			return sum;
		}
		return fac2(sum) / denominator;
	}

	public long permutationIndexII(int[] A) {
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

		for (int i = 0; i < A.length; i++) {
			if (hash.containsKey(A[i]))
				hash.put(A[i], hash.get(A[i]) + 1);
			else {
				hash.put(A[i], 1);
			}
		}
		long ans = 0;
		for (int i = 0; i < A.length; i++) {
			HashMap<Integer, Integer> flag = new HashMap<Integer, Integer>();

			for (int j = i + 1; j < A.length; j++) {
				if (A[j] < A[i] && !flag.containsKey(A[j])) {
					flag.put(A[j], 1);

					hash.put(A[j], hash.get(A[j]) - 1);
					ans += generateNum(hash);
					hash.put(A[j], hash.get(A[j]) + 1);

				}

			}
			hash.put(A[i], hash.get(A[i]) - 1);
		}

		return ans + 1;

	}
}

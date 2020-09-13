package lai_online2;

public class Class2 {
	/*
	task1
	Fibonacci Number
	Easy
	DP
	Get the Kth number in the Fibonacci Sequence. (K is 0-indexed, the 0th Fibonacci number is 0 and the 1st Fibonacci number is 1).

	Examples

	0th fibonacci number is 0
	1st fibonacci number is 1
	2nd fibonacci number is 1
	3rd fibonacci number is 2
	6th fibonacci number is 8
	Corner Cases

	What if K < 0? in this case, we should always return 0.
	Is it possible the result fibonacci number is overflowed? We can assume it will not be overflowed when we solve this problem on this online judge, but we should also know that it is possible to get an overflowed number, and sometimes we will need to use something like BigInteger.
			
	*/
	
	public long fibonacci(int k) {
		// Write your solution here

		if (k <= 0) {
			return 0;
		}
		if (k == 1) {
			return 1;
		}
		long temp1 = 0;
		long temp2 = 1;
		for (int i = 2; i <= k; i++) {
			long newTemp = temp1 + temp2;
			temp1 = temp2;
			temp2 = newTemp;
		}
		return temp2;

	}
	
	/*
	 * task2
	Classical Binary Search
	Easy
	Data Structure
	Given a target integer T and an integer array A sorted in ascending order, find the index i such that A[i] == T or return -1 if there is no such index.

	Assumptions

	There can be duplicate elements in the array, and you can return any of the indices i such that A[i] == T.
	Examples

	A = {1, 2, 3, 4, 5}, T = 3, return 2
	A = {1, 2, 3, 4, 5}, T = 6, return -1
	A = {1, 2, 2, 2, 3, 4}, T = 2, return 1 or 2 or 3
	Corner Cases

	What if A is null or A is of zero length? We should return -1 in this case.
	*/
	public int binarySearch(int[] array, int target) {
	    // Write your solution here
	    if (array == null || array.length == 0) {
	      return -1;
	    }
	    int start = 0, end = array.length - 1;
	    while(start + 1 < end) {
	      int mid = start + (end - start)/2;
	      if (array[mid] == target) {
	        return mid;
	      } else if(array[mid] > target) {
	        end = mid;
	      } else {
	        start = mid;
	      }
	    }
	    if (array[start] == target) {
	      return start;
	    } else if (array[end] == target) {
	      return end;
	    } else {
	      return -1;
	    }
	  }
	
	/*
	 * task3
	
	First Occurrence
	Fair
	Data Structure
	Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.

	Assumptions

	There can be duplicate elements in the array.
	Examples

	A = {1, 2, 3}, T = 2, return 1
	A = {1, 2, 3}, T = 4, return -1
	A = {1, 2, 2, 2, 3}, T = 2, return 1
	Corner Cases

	What if A is null or A of zero length? We should return -1 in this case.
	*/
	public int firstOccur(int[] array, int target) {
		// Write your solution here
		// remember, array == null || array.length == 0
		if (array == null || array.length == 0) {
			return -1;
		}
		int start = 0, end = array.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] == target) {
				end = mid;
			} else if (array[mid] > target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		
		if (array[start] == target) {
			return start;
		} else if (array[end] == target) {
			return end;
		} else {
			return -1;
		}
	}
	
	/*
	 * task4
	Last Occurrence
	Fair
	Data Structure
	Given a target integer T and an integer array A sorted in ascending order, find the index of the last occurrence of T in A or return -1 if there is no such index.

	Assumptions

	There can be duplicate elements in the array.

	Examples

	A = {1, 2, 3}, T = 2, return 1
	A = {1, 2, 3}, T = 4, return -1
	A = {1, 2, 2, 2, 3}, T = 2, return 3
	Corner Cases

	What if A is null or A is array of zero length? We should return -1 in this case.
	*/
	public int lastOccur(int[] array, int target) {
	    // Write your solution here
	    if (array == null || array.length == 0) {
	      return -1;
	    }
	    int start = 0, end = array.length - 1;
	    while(start + 1 < end) {
	      int mid = start + (end - start)/2;
	      if (array[mid] == target) {
	        start = mid;
	      } else if(array[mid] > target) {
	        end = mid;
	      } else {
	        start = mid;
	      }
	    }
	    if (array[end] == target) {
	      return end;
	    } else if (array[start] == target) {
	      return start;
	    } else {
	      return -1;
	    }
	  }
	/*
	 * task5
	Closest In Sorted Array
	Fair
	Data Structure
	Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.

	Assumptions

	There can be duplicate elements in the array, and we can return any of the indices with same value.
	Examples

	A = {1, 2, 3}, T = 2, return 1
	A = {1, 4, 6}, T = 3, return 1
	A = {1, 4, 6}, T = 5, return 1 or 2
	A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2
	Corner Cases

	What if A is null or A is of zero length? We should return -1 in this case.
	*/
	
	public int closest(int[] array, int target) {
		// Write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}
		int start = 0, end = array.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (target < array[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (Math.abs(array[start] - target) < Math.abs(array[end] - target)) {
			return start;
		} else {
			return end;
		}
	}
	
	/*
	 * task6
	K Closest In Sorted Array
	Fair
	Data Structure
	Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A.

	Assumptions

	K is guranteed to be >= 0 and K is guranteed to be <= A.length
	Return

	A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T. 
	Examples

	A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
	A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}
	Corner Cases

	What if A is null? We should return null in this case.
	*/
	public int[] kClosest(int[] array, int target, int k) {
	    // Write your solution here
	    if (array == null || array.length < k) {
	      return null;
	    }
	    int[] result = new int[k];
	    int n = array.length;
	    // find the last element which is smaller than the target
	    int start = 0, end = array.length - 1;
	    while(start + 1 < end) {
	      int mid = start + (end - start)/2;
	      if (array[mid] == target) {
	        end = mid;
	      } else if (array[mid] > target) {
	        end = mid;
	      } else {
	        start = mid;
	      }
	    }
	    // if the target isn't in array
	    // after above while, start should point the last element which is smaller than target
	    // end points the first larger element than target. 
	    // if the target is in the array
	    // start points the last smaller element than target. end points the target
	    
	    // edge case a[start] > target, all element is larget than target
	    // a[end] < target, all target is smaller than target
	    
	    if (array[start] > target) {
	      for(int i = 0; i < k; i ++) {
	        result[i] = array[i];
	      }
	    }
	    if (array[end] < target) {
	      for(int i = array.length - 1; (n - i) > k; i--) {
	        result[i] = array[i];
	      }
	    }
	    int index = 0;
	    while(index < k) {
	      int candidate = -1;
	      if (start >= 0 && end <= n - 1) {
	        int diff_start = Math.abs(array[start] - target);
	        int diff_end = Math.abs(array[end] - target);
	        if (diff_start < diff_end) {
	          candidate = array[start];
	          start --;
	        } else {
	          candidate = array[end];
	          end ++;
	        }
	      } else if (start < 0) {
	        candidate = array[end];
	        end ++;
	      } else {
	        candidate = array[start];
	        start --;
	      }
	      result[index] = candidate;
	      index ++;
	    }
	    
	    return result;
	  }
	
	
	/*
	 * task7
	Search In Unknown Sized Sorted Array
	Fair
	Data Structure
	Given a integer dictionary A of unknown size, where the numbers in the dictionary are sorted in ascending order, determine if a given target integer T is in the dictionary. Return the index of T in A, return -1 if T is not in A.

	Assumptions

	dictionary A is not null
	dictionary.get(i) will return null if index i is out of bounds
	Examples

	A = {1, 2, 5, 9, ......}, T = 5, return 2
	A = {1, 2, 5, 9, 12, ......}, T = 7, return -1
	*/
	/*
	public int search(Dictionary dict, int target) {
		// Write your solution here
		if (dict == null) {
			return -1;
		}
		int left = 0;
		int right = 1;

		// if we still didn't find the right bound
		while (dict.get(right) != null && dict.get(right) < target) {
			left = right;
			right *= 2;
		}

		// after the above loop, array[right] >= target or right out of bound
		return binarySearch(dict, left, right, target);
	}
		  
		  
	public int binarySearch(Dictionary dict, int left, int right, int target) {
		if (left > right) {
			return -1;
		}

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (dict.get(mid) == null || dict.get(mid) > target) {
				// mid is out of bound or array[mid] > target
				// move to left side
				right = mid;
			} else if (dict.get(mid) < target) {
				// move to right side
				left = mid;
			} else {
				return mid;
			}
		}

		if (dict.get(left) != null && dict.get(left) == target) {
			return left;
		} else if (dict.get(right) != null && dict.get(right) == target) {
			return right;
		} else {
			return -1;
		}
	}
	*/
	
	/*
	 * task8 
	 * a to the power of b Fair Recursion Evaluate a to the power of b,
	 * assuming both a and b are integers and b is non-negative.
	 * 
	 * Examples
	 * 
	 * power(2, 0) = 1 power(2, 3) = 8 power(0, 10) = 0 power(-2, 5) = -32
	 * Corner Cases
	 * 
	 * What if the result is int overflowed? We can assume the result will not
	 * be overflowed when we solve this problem on this online judge.
	 */

	public long power(int a, int b) {
		// Write your solution here
		if (b == 0) {
			return 1;
		}
		long half = power(a, b / 2);
		if (b % 2 == 1) {
			return half * half * a;
		} else {
			return half * half;
		}
	}
}

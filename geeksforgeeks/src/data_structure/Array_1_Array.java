package data_structure;

public class Array_1_Array {

	/*
	 * task1
	 * http://www.geeksforgeeks.org/write-a-c-program-that-given-a-set-a-of-n-numbers-and-another-number-x-determines-whether-or-not-there-exist-two-elements-in-s-whose-sum-is-exactly-x/
	 * Given an array A[] and a number x, check for pair in A[] with sum as x
	 * 
	 * 1 sorting 2 sum
	 * 2 hashMap
	 */
	
	
	/*
	 * task2
	 * Majority Element
	 * 
	 * see laicode to expand to majority kth element
	 * http://www.geeksforgeeks.org/majority-element/
	 */
	
	/*
	 * task3
	 * Find the Number Occurring Odd Number of Times
	 * Given an array of positive integers. 
	 * All numbers occur even number of times 
	 * except one number which occurs odd number of times. 
	 * Find the number in O(n) time & constant space.
	 * 
	 * 1 HashMap<key, counter>
	 * 2 using XOR
	 */
	
	/*
	 * task4
	 * Largest Sum Contiguous Subarray
	 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
	 * easy
	 * 
	 */
	
	/*
	 * task5
	 * Find the Missing Number
	 * http://www.geeksforgeeks.org/find-the-missing-number/
	 * You are given a list of n-1 integers and 
	 * these integers are in the range of 1 to n. 
	 * There are no duplicates in list.
	 * One of the integers is missing in the list. 
	 * Write an efficient code to find the missing integer.
	 * 
	 * 1 use sum formular
	 * 2 use XOR. xor 1..n with all element in array
	 * 
	 */
	
	/*
	 * task6
	 * Search an element in a sorted and pivoted array
	 * http://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
	 * 
	 * same with search element in rotated array
	 * 
	 * NO duplicate
	 * with Duplicate
	 * 
	 * binary search
	 */
	
	/*
	 * task7
	 * Merge an array of size n into another array of size m+n
	 * http://www.geeksforgeeks.org/merge-one-array-of-size-n-into-another-one-of-size-mn/
	 * 
	 * from right side to left side
	 */
	
	/*
	 * task8
	 * Median of two sorted arrays
	 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
	 * !!! to finish this independently. 
	 */
	
	/*
	 * task9
	 * Write a program to reverse an array
	 * http://www.geeksforgeeks.org/write-a-program-to-reverse-an-array/
	 * 
	 * iterative
	 * recursion
	 */
	
	/*
	 * task10
	 * Program for array rotation
	 * http://www.geeksforgeeks.org/array-rotation/
	 * 
	 * there is a funny way. 
	 * see below
	 * http://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/
	 * 
	 * Block swap algorithm for array rotation
	 * http://www.geeksforgeeks.org/block-swap-algorithm-for-array-rotation/
	 */
	
	/*
	 * task11
	 * Maximum sum such that no two elements are adjacent
	 * http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
	 * 
	 * same with leetcode house robber 1, 2
	 * 
	 * follow up
	 * if the array is a circle.
	 * 
	 */
	
	/*
	 * task12
	 * Leaders in an array
	 * http://www.geeksforgeeks.org/leaders-in-an-array/
	 * 
	 * Write a program to print all the LEADERS in the array. 
	 * An element is leader if it is 
	 * greater than all the elements to its right side. 
	 * And the rightmost element is always a leader. 
	 * For example int the array {16, 17, 4, 3, 5, 2}, 
	 * leaders are 17, 5 and 2.
	 * 
	 * scan from the right, maintain the max_fo_far
	 * 
	 * if element > max_so_far, it's leader, update max_so_far
	 */
	
	/*
	 * task18
	 * Sort elements by frequency | Set 1
	 * http://www.geeksforgeeks.org/sort-elements-by-frequency/
	 *
	 * (1) using sorting
	 * Print the elements of an array in the decreasing frequency 
	 * if 2 numbers have same frequency then print the one which came 1st
	 * E.g. 2 5 2 8 5 6 8 8 output: 8 8 8 2 2 5 5 6.
	 * 
	 * (2) use hash and sorting
	 */
	
	/*
	 * task19
	 * Count Inversions in an array
	 * http://www.geeksforgeeks.org/counting-inversions/
	 * 
	 * inversion Count for an array indicates – 
	 * how far (or close) the array is from being sorted. 
	 * If array is already sorted then inversion count is 0. 
	 * If array is sorted in reverse order that inversion count 
	 * is the maximum. 
	 * Formally speaking, two elements a[i] and a[j] form an inversion 
	 * if a[i] > a[j] and i < j Example:
	 * The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
	 * 
	 * 
	 * (1) two loops
	 * (2) use the idea of merge sort. !!!
	 * 
	 * this is new to me
	 */
	
	/*
	 * task20
	 * Two elements whose sum is closest to zero
	 * http://www.geeksforgeeks.org/two-elements-whose-sum-is-closest-to-zero/
	 * 
	 * (1) 2 loops
	 * (2) sorting, 2 sum closest
	 */
	
	/*
	 * task21
	 * Find the smallest and second smallest element in an array
	 * http://www.geeksforgeeks.org/to-find-smallest-and-second-smallest-element-in-an-array/
	 * 
	 * maintain the firstSmallest, secondSmallest
	 * 
	 * if cur < firstSmallest
	 * 		secondSmallest = firstSmallest
	 * 		firstSmallest = cur;
	 * else if firstSmallest < cur < secondSmallest
	 * 		secondSmallest = cur;
	 * else 
	 * 		continue
	 */
	
	/*
	 * task22
	 * Check for Majority Element in a sorted array
	 * http://www.geeksforgeeks.org/check-for-majority-element-in-a-sorted-array/
	 * 
	 * check the middle element == target or not
	 * 
	 */
	
	
	/*
	 * task23
	 * Maximum and minimum of an array using minimum number of comparisons
	 * http://www.geeksforgeeks.org/maximum-and-minimum-in-an-array/
	 * 
	 * !!! this need to review !!!
	 */
	
	/*
	 * task24
	 * Segregate 0s and 1s in an array
	 * http://www.geeksforgeeks.org/segregate-0s-and-1s-in-an-array-by-traversing-array-once/
	 * 
	 * partition
	 */
	
	/*
	 * task25
	 * k largest(or smallest) elements in an array | added Min Heap method
	 * 
	 * http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
	 * 
	 * (1) maxHeap
	 * (2) minHeap
	 * (3)Use Oder Statistics
	 * 
	 * !!! this is a summary post. reader in detail 
	 */
	
	/*
	 * task26
	 * Maximum difference between two elements such that larger element appears after the smaller number
	 * http://www.geeksforgeeks.org/maximum-difference-between-two-elements/
	 * 
	 * same with 
	 * buy and selling stock 1. 
	 * 
	 */
	
	/*
	 * task27
	 * Union and Intersection of two sorted arrays
	 * http://www.geeksforgeeks.org/union-and-intersection-of-two-sorted-arrays-2/
	 * 
	 * two pointers in two sorted array. 
	 * 
	 */
	
	/*
	 * task28
	 * Floor and Ceiling in a sorted array
	 * http://www.geeksforgeeks.org/search-floor-and-ceil-in-a-sorted-array/
	 * 
	 * binary search 
	 * find the largest smaller element for target
	 * find the smallest larger element for target
	 */
	
	/*
	 *task29
	 * A Product Array Puzzle
	 * product except itself
	 * http://www.geeksforgeeks.org/a-product-array-puzzle/
	 * 
	 * leftProduct[]
	 * rightProduct[]
	 */
	
	/*
	 * task30
	 * Segregate Even and Odd numbers
	 * http://www.geeksforgeeks.org/segregate-even-and-odd-numbers/
	 * 
	 * partition
	 */
	
	/*
	 * task31
	 * Find the two repeating elements in a given array
	 * http://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-array/
	 * 
	 * You are given an array of n+2 elements. 
	 * All elements of the array are in range 1 to n. 
	 * And all elements occur once except two numbers which occur twice. 
	 * Find the two repeating numbers.
	 * 
	 * (1) using hash
	 * (2) using count array
	 * (3) using sort
	 * (4) using XOR !!! to do later
	 */
	/*
	 * task32
	 * Sort an array of 0s, 1s and 2s
	 * http://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
	 * rainbow sort
	 */
	
	/*
	 * task33
	 * Find the Minimum length Unsorted Subarray, sorting which makes the complete array sorted
	 * http://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
	 * 
	 * details 
	 * 
	 * 
	 */
	
	/*
	 * task34
	 * Find duplicates in O(n) time and O(1) extra space
	 * http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
	 * 
	 */
	/*
	 * task35
	 * Equilibrium index of an array
	 * http://www.geeksforgeeks.org/equilibrium-index-of-an-array/
	 */
	
	/*
	 * task36
	 * Linked List vs Array
	 * http://www.geeksforgeeks.org/linked-list-vs-array/
	 */
	
	/*
	 * task37
	 * Which sorting algorithm makes minimum number of memory writes?
	 * http://www.geeksforgeeks.org/which-sorting-algorithm-makes-minimum-number-of-writes/
	 */
	
	/*
	 * task38
	 * Turn an image by 90 degree
	 * http://www.geeksforgeeks.org/turn-an-image-by-90-degree/
	 * 
	 * !! implement
	 */
	
	/*
	 * task39
	 * Next Greater Element
	 * http://www.geeksforgeeks.org/next-greater-element/
	 */
	
	/*
	 * task40
	 * Check if array elements are consecutive | Added Method 3
	 * http://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/
	 */
	
	/*
	 * task41
	 * Find the smallest missing number
	 * http://www.geeksforgeeks.org/find-the-first-missing-number/
	 */
	
	/*
	 * task42
	 * Count the number of occurrences in a sorted array
	 * http://www.geeksforgeeks.org/count-number-of-occurrences-in-a-sorted-array/
	 */
	
	/*
	 * task43
	 * Interpolation search vs Binary search
	 * http://www.geeksforgeeks.org/g-fact-84/
	 */
	
	/*
	 * task44
	 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
	 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
	 */
	
	/*
	 * task45
	 * Maximum of all subarrays of size k (Added a O(n) method)
	 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
	 */
	
	/*
	 * task46
	 * Find whether an array is subset of another array | Added Method 3
	 * http://www.geeksforgeeks.org/find-whether-an-array-is-subset-of-another-array-set-1/
	 */
	
	/*
	 * task47
	 * Find the minimum distance between two numbers
	 * http://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
	 */
	
	/*
	 * task48
	 * Find the repeating and the missing | Added 3 new methods
	 * http://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
	 * 
	 */
	
	/*
	 * task49
	 * Median in a stream of integers (running integers)
	 * http://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
	 */
	
	/*
	 * task50
	 * Find a Fixed Point in a given array
	 * http://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/
	 */
	
	/*
	 * task51
	 * Maximum Length Bitonic Subarray
	 * http://www.geeksforgeeks.org/maximum-length-bitonic-subarray/
	 */
	
	/*
	 * task52
	 * Find the maximum element in an array which is first increasing and then decreasing
	 * http://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and-then-decreasing/
	 */
	
	/*
	 * task53
	 * Count smaller elements on right side
	 * http://www.geeksforgeeks.org/count-smaller-elements-on-right-side/
	 */
	
	/*
	 * task54
	 * Minimum number of jumps to reach end
	 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
	 */
	
	/*
	 * task55
	 * Implement two stacks in an array
	 * http://www.geeksforgeeks.org/implement-two-stacks-in-an-array/
	 */
	
	/*
	 * task56
	 * Find subarray with given sum
	 * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
	 */
	
	/*
	 * task57
	 * Dynamic Programming | Set 14 (Maximum Sum Increasing Subsequence)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
	 * 
	 */
	
	/*
	 * task58
	 * Longest Monotonically Increasing Subsequence Size (N log N)
	 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	 * 
	 */
	
	/*
	 * task59
	 * Find a triplet that sum to a given value
	 * http://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
	 */
	
	/*
	 * task60
	 * Find the smallest positive number missing from an unsorted array
	 * http://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
	 */
	
	/*
	 * task61
	 * Find the two numbers with odd occurrences in an unsorted array
	 * http://www.geeksforgeeks.org/find-the-two-numbers-with-odd-occurences-in-an-unsorted-array/
	 * 
	 */
	
	/*
	 * task62
	 * The Celebrity Problem
	 * http://www.geeksforgeeks.org/the-celebrity-problem/
	 */
	
	/*
	 * task63
	 * Dynamic Programming | Set 15 (Longest Bitonic Subsequence)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
	 */
	
	/*
	 * task64
	 * Find a sorted subsequence of size 3 in linear time
	 * http://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
	 */
	
	/*
	 * taks65
	 * Largest subarray with equal number of 0s and 1s
	 * http://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
	 */
	
	/*
	 * task66
	 * Dynamic Programming | Set 18 (Partition problem)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
	 */
	
	/*
	 * task67
	 * Maximum Product Subarray
	 * http://www.geeksforgeeks.org/maximum-product-subarray/
	 */
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * task68
	 * Find a pair with the given difference
	 * http://www.geeksforgeeks.org/find-a-pair-with-the-given-difference/
	 * 
	 */
	
	/*
	 * task69
	 * Replace every element with the greatest element on right side
	 * http://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/
	 */
	
	/*
	 * task70
	 * Dynamic Programming | Set 20 (Maximum Length Chain of Pairs)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain-of-pairs/
	 */
	
	/*
	 * task71
	 * Find four elements that sum to a given value | Set 1 (n^3 solution)
	 * http://www.geeksforgeeks.org/find-four-numbers-with-sum-equal-to-given-sum/
	 */
	
	/*
	 * task72
	 * Find four elements that sum to a given value | Set 2 ( O(n^2Logn) Solution)
	 * http://www.geeksforgeeks.org/find-four-elements-that-sum-to-a-given-value-set-2/
	 */
	
	/*
	 * task73
	 * Sort a nearly sorted (or K sorted) array
	 * http://www.geeksforgeeks.org/nearly-sorted-algorithm/
	 */
	
	/*
	 * task74
	 * Maximum circular subarray sum
	 * http://www.geeksforgeeks.org/maximum-contiguous-circular-sum/
	 * 
	 */
	
	/*
	 * task75
	 * Find the row with maximum number of 1s
	 * http://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
	 */
	
	/*
	 * task76
	 * Median of two sorted arrays of different sizes
	 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
	 */
	
	/*
	 * task77
	 * Shuffle a given array
	 * http://www.geeksforgeeks.org/shuffle-a-given-array/
	 */
	
	/*
	 * task78
	 * Count the number of possible triangles
	 * http://www.geeksforgeeks.org/find-number-of-triangles-possible/
	 */
	
	/*
	 * task79
	 * Iterative Quick Sort
	 * http://www.geeksforgeeks.org/iterative-quick-sort/
	 */
	
	/*
	 * task80
	 * Find the number of islands
	 * http://www.geeksforgeeks.org/find-number-of-islands/
	 */
	
	/*
	 * task81
	 * Construction of Longest Monotonically Increasing Subsequence (N log N)
	 * http://www.geeksforgeeks.org/construction-of-longest-monotonically-increasing-subsequence-n-log-n/
	 */
	
	/*
	 * task82
	 * Find the first circular tour that visits all petrol pumps
	 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
	 */
	
	/*
	 * task83
	 * Arrange given numbers to form the biggest number
	 * http://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
	 */
	
	/*
	 * task84
	 * Pancake sorting
	 * http://www.geeksforgeeks.org/pancake-sorting/
	 */
	
	/*
	 * task85
	 * A Pancake Sorting Problem
	 * http://www.geeksforgeeks.org/a-pancake-sorting-question/
	 */
	
	/*
	 * task86
	 * Tug of War
	 * http://www.geeksforgeeks.org/tug-of-war/
	 */
	
	/*
	 * task87
	 * Divide and Conquer | Set 3 (Maximum Subarray Sum)
	 * http://www.geeksforgeeks.org/divide-and-conquer-maximum-sum-subarray/
	 */
	
	/*
	 * task88
	 * Counting Sort
	 * http://www.geeksforgeeks.org/counting-sort/
	 */
	
	/*
	 * task89
	 * Merge Overlapping Intervals
	 * http://www.geeksforgeeks.org/merging-intervals/
	 */
	
	/*
	 * task90
	 * Find the maximum repeating number in O(n) time and O(1) extra space
	 * http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
	 */
	
	/*
	 * task91
	 * Stock Buy Sell to Maximize Profit
	 * http://www.geeksforgeeks.org/stock-buy-sell/
	 */
	
	/*
	 * task92
	 * Rearrange positive and negative numbers in O(n) time and O(1) extra space
	 * http://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
	 */
	
	/*
	 * task93
	 * Sort elements by frequency | Set 2
	 * http://www.geeksforgeeks.org/sort-elements-by-frequency-set-2/
	 */
	
	/*
	 * task94
	 * Find a peak element
	 * http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
	 */
	
	/*
	 * task95
	 * Print all possible combinations of r elements in a given array of size n
	 * http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
	 */
	
	/*
	 * task96
	 * Given an array of of size n and a number k, find all elements that appear more than n/k times
	 * http://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/
	 * 
	 * k majority
	 * 
	 */
	
	/*
	 * task97
	 * Unbounded Binary Search Example (Find the point where a monotonically increasing function becomes positive first time)
	 * http://www.geeksforgeeks.org/find-the-point-where-a-function-becomes-negative/
	 */
	
	/*
	 * task98
	 * Find the Increasing subsequence of length three with maximum product
	 * http://www.geeksforgeeks.org/increasing-subsequence-of-length-three-with-maximum-product/
	 */
	
	/*
	 * task99
	 * Find the minimum element in a sorted and rotated array
	 * http://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
	 */
	
	/*
	 * task100
	 * Stable Marriage Problem
	 * http://www.geeksforgeeks.org/stable-marriage-problem/
	 */
	
	/*
	 * task101
	 * Merge k sorted arrays | Set 1
	 * http://www.geeksforgeeks.org/merge-k-sorted-arrays/
	 */
	
	/*
	 * task102
	 * Radix Sort
	 * http://www.geeksforgeeks.org/radix-sort/
	 */
	
	/*
	 * task103 
	 * Move all zeroes to end of array
	 * http://www.geeksforgeeks.org/move-zeroes-end-array/
	 * 
	 * partition
	 */
	
	/*
	 * task104 
	 * Find number of pairs such that x^y > y^x
	 * http://www.geeksforgeeks.org/find-number-pairs-xy-yx/
	 */
	
	
	/*
	 * task105 
	 * Count all distinct pairs with difference equal to k
	 * http://www.geeksforgeeks.org/count-pairs-difference-equal-k/
	 */
	
	/*
	 * task106
	 * Find if there is a subarray with 0 sum
	 * http://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
	 * 
	 * presum[i] = sum of a[0]..a[i]
	 * 
	 * find if there are duplicates in presum
	 * or a[i] == 0
	 * or presum[i] == 0
	 */
	
	/*
	 * task107
	 * Smallest subarray with sum greater than a given value
	 * 
	 * slide window
	 * http://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
	 * 
	 * !!! implement this
	 */
	
	/*
	 * task108
	 * Sort an array according to the order defined by another array
	 * http://www.geeksforgeeks.org/sort-array-according-order-defined-another-array/
	 * 
	 * !!! implement this
	 */
	
	/*
	 * task109
	 * Maximum Sum Path in Two Arrays
	 * http://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/
	 * 
	 * !!! implement this 
	 */
	
	/*
	 * task110
	 * Check if a given array contains duplicate elements within k distance from each other
	 * http://www.geeksforgeeks.org/check-given-array-contains-duplicate-elements-within-k-distance/
	 * 
	 * !!! implement this
	 */
	
	/*
	 * task111
	 * Sort an array in wave form
	 * http://www.geeksforgeeks.org/sort-array-wave-form-2/
	 * 
	 * 
	 * !!! Implement
	 */
	
	/*
	 * task112
	 * K’th Smallest/Largest Element in Unsorted Array | Set 1
	 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
	 * 
	 * partition
	 * 
	 * !!! implement
	 */
	
	/*
	 * task113
	 * K’th Smallest/Largest Element in Unsorted Array | Set 2 (Expected Linear Time)
	 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-2-expected-linear-time/
	 * 
	 */
	
	/*
	 * task114
	 * K’th Smallest/Largest Element in Unsorted Array | Set 3 (Worst Case Linear Time)
	 * http://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-3-worst-case-linear-time/
	 */
	
	/*
	 * task115
	 * Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array
	 * !!! implement
	 * http://www.geeksforgeeks.org/find-index-0-replaced-1-get-longest-continuous-sequence-1s-binary-array/
	 */
	
	/*
	 * task116
	 * Find the closest pair from two sorted arrays
	 * http://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-whose-sum-closest-x/
	 */
	
	/*
	 * task117
	 * Given a sorted array and a number x, find the pair in array whose sum is closest to x
	 * http://geeksquiz.com/given-sorted-array-number-x-find-pair-array-whose-sum-closest-x/
	 * 
	 * two sum closest
	 */
	
	/*
	 * task118
	 * Count 1’s in a sorted binary array
	 * http://geeksquiz.com/count-1s-sorted-binary-array/
	 * 
	 * Binary search
	 */
	
	/*
	 * task119
	 * Print All Distinct Elements of a given integer array
	 * http://geeksquiz.com/print-distinct-elements-given-integer-array/
	 * 
	 * easy
	 * (1) using hash
	 * (2) sort, remove duplicate 
	 */
	
	/*
	 * task120
	 * Construct an array from its pair-sum array
	 * http://geeksquiz.com/construct-array-pair-sum-array/
	 * 
	 * !!! to do
	 * 
	 */
	
	/*
	 * task121
	 * Find common elements in three sorted arrays
	 * http://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/
	 * 
	 * !!! to do
	 */
	
	/*
	 * task122
	 * Find the first repeating element in an array of integers
	 * http://www.geeksforgeeks.org/find-first-repeating-element-array-integers/
	 * 
	 * (1) sort in another array, scan ..
	 * (2) using hash
	 */
	
	/*(
	 * task123
	 * Find the smallest positive integer value that cannot be represented as sum of any subset of a given array
	 * http://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
	 * 
	 * !!! to do
	 */
	
	/*
	 * task124
	 * Rearrange an array such that ‘arr[j]’ becomes ‘i’ if ‘arr[i]’ is ‘j’
	 * http://www.geeksforgeeks.org/rearrange-array-arrj-becomes-arri-j/
	 * 
	 * !!! to do
	 */
	
	/*
	 * task125
	 * Find position of an element in a sorted array of infinite numbers
	 * http://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/
	 * 
	 * !!! implement 
	 */
	
	/*
	 * task126
	 * Can QuickSort be implemented in O(nLogn) worst case time complexity?
	 * http://www.geeksforgeeks.org/can-quicksort-implemented-onlogn-worst-case-time-complexity/
	 */
	
	/*
	 * task127
	 * Check if a given array contains duplicate elements within k distance from each other
	 * http://www.geeksforgeeks.org/check-given-array-contains-duplicate-elements-within-k-distance/
	 */
	
	/*
	 * task128
	 * Find the element that appears once
	 * http://geeksquiz.com/find-the-element-that-appears-once/
	 */
	
	/*
	 * task129
	 * Replace every array element by multiplication of previous and next
	 * http://geeksquiz.com/replace-every-array-element-by-multiplication-of-previous-and-next/
	 */
	
	
	/*
	 * task130
	 * Check if any two intervals overlap among a given set of intervals
	 * http://geeksquiz.com/check-if-any-two-intervals-overlap-among-a-given-set-of-intervals/
	 */
	
	/*
	 * task131
	 * Delete an element from array (Using two traversals and one traversal)
	 * http://geeksquiz.com/delete-an-element-from-array-using-two-traversals-and-one-traversal/
	 */
	
	/*
	 * task132
	 * Given a sorted array and a number x, find the pair in array whose sum is closest to x
	 * http://geeksquiz.com/given-sorted-array-number-x-find-pair-array-whose-sum-closest-x/
	 */
	
	/*
	 * task133
	 * Find the largest pair sum in an unsorted array
	 * http://www.geeksforgeeks.org/find-the-largest-pair-sum-in-an-unsorted-array/
	 * 
	 * find the largest and second largest in an unsorted array
	 * !!!
	 */
	
	/*
	 * task134
	 * Online algorithm for checking palindrome in a stream
	 * http://www.geeksforgeeks.org/online-algorithm-for-checking-palindrome-in-a-stream/
	 */
	
	/*
	 * task135
	 * Find Union and Intersection of two unsorted arrays
	 * http://www.geeksforgeeks.org/find-union-and-intersection-of-two-unsorted-arrays/
	 */
	
	/*
	 * task136
	 * Pythagorean Triplet in an array
	 * http://www.geeksforgeeks.org/find-pythagorean-triplet-in-an-unsorted-array/
	 */
	
	/*
	 * task137
	 * Maximum profit by buying and selling a share at most twice
	 * http://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-twice/
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

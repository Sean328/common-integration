package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-03-07 19:52
 **/

/**
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 *
 * algorithms
 * Easy (39.58%)
 * Total Accepted:    531.5K
 * Total Submissions: 1.3M
 * Testcase Example:  '[1,1,2]'
 *
 * Given a sorted array nums, remove the duplicates in-place such that each
 * element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 *
 * Given nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively.
 *
 * It doesn't matter what you leave beyond the returned length.
 *
 * Example 2:
 *
 *
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * Your function should return length = 5, with the first five elements of nums
 * being modified to 0, 1, 2, 3, and 4 respectively.
 *
 * It doesn't matter what values are set beyond the returned length.
 *
 *
 * Clarification:
 *
 * Confused why the returned value is an integer but your answer is an array?
 *
 * Note that the input array is passed in by reference, which means
 * modification to the input array will be known to the caller as well.
 *
 * Internally you can think of this:
 *
 *
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 *
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len
 * elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 */
public class Solution_26 {

    /**
     *  Do not allocate extra space for another array, you must do this by modifying
     *  the input array in-place with O(1) extra memory.
     *  除了返回值正确外，话要确保数组里面的前面几个元素排列是正确的
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != nums[count]){
                count++;
                nums[count] = nums[i];
            }
        }
        return count+1;
    }

    public static void main(String[] args) {
        Solution_26 solution_26 = new Solution_26();
        int [] arg = new int[]{1,1,2,3};
        System.out.println(solution_26.removeDuplicates(arg));
    }
}

package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-03-08 15:24
 **/

/**
 * @lc app=leetcode id=27 lang=java
 *
 * [27] Remove Element
 *
 * https://leetcode.com/problems/remove-element/description/
 *
 * algorithms
 * Easy (43.50%)
 * Total Accepted:    375.5K
 * Total Submissions: 860.5K
 * Testcase Example:  '[3,2,2,3]\n3'
 *
 * Given an array nums and a value val, remove all instances of that value
 * in-place and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 *
 * The order of elements can be changed. It doesn't matter what you leave
 * beyond the new length.
 *
 * Example 1:
 *
 *
 * Given nums = [3,2,2,3], val = 3,
 *
 * Your function should return length = 2, with the first two elements of nums
 * being 2.
 *
 * It doesn't matter what you leave beyond the returned length.
 *
 *
 * Example 2:
 *
 *
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 *
 * Your function should return length = 5, with the first five elements of nums
 * containing 0, 1, 3, 0, and 4.
 *
 * Note that the order of those five elements can be arbitrary.
 *
 * It doesn't matter what values are set beyond the returned length.
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
 * int len = removeElement(nums, val);
 *
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len
 * elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 */
public class Solution_27 {


    /**
     * 说明
     * Your function should return length = 2, with the first two elements of nums being 2.
     *
     *  It doesn't matter what you leave beyond the returned length.
     *
     *  Example 2:
     *  Given nums = [0,1,2,2,3,0,4,2], val = 2,
     *
     *  Your function should return length = 5, with the first five elements of nums
     *  containing 0, 1, 3, 0, and 4.
     *      给定 nums = [3,2,2,3]，val = 3，
     *      你的函数应该返回 长度 = 2，数组的前两个元素是 2。
     *
     * 思路： 遍历数组，并设置遍历结束变量。遍历的同时交换目标常量和结尾部分，遍历的i 和结尾的index都是不断变化的
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {

        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return val == nums[0] ? 0 : 1;
        }

        int index  = 0;
        for(int i=0;i<nums.length-index;i++){
            if (nums[i] == val){
                index ++;
                int temp = nums[i];
                nums[i] = nums[nums.length - index];
                nums[nums.length - index] = temp;
                i--;
            }
        }

        return nums.length - index;
    }

    public static void main(String[] args) {

        int [] argsNum = new int[]{1,2,2,3,3};
        Solution_27 solution_27 = new Solution_27();
        System.out.println(solution_27.removeElement(argsNum,3));
    }
}

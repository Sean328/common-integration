package com.ironass.algorithm.leetcode;

/**
 * @author lixin
 * @date 2019-03-08 18:07
 **/

/**
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 *
 * https://leetcode.com/problems/search-insert-position/description/
 *
 * algorithms
 * Easy (40.42%)
 * Total Accepted:    365.1K
 * Total Submissions: 902.1K
 * Testcase Example:  '[1,3,5,6]\n5'
 *
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 *
 * Example 2:
 *
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 *
 * Example 3:
 *
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 *
 * Example 4:
 *
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 *
 */
public class Solution_35 {

    /**
     * 思路：方法1： 设置一个位置标识变量，当遍历到的数字大于这个标识时break循环，并返回
     * 方法2 ：二分搜索法
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {

        int flag =0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < target){
                flag++;
            }else {
                break;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        Solution_35 solution_35 = new Solution_35();
        int [] nums = new int[] {1,3,5,6};
        int target = 7;
        System.out.println(solution_35.searchInsert(nums,target));
    }
}

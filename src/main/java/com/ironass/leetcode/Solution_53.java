package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-03-12 19:08
 **/

/**
 * @lc app=leetcode id=53 lang=java
 * <p>
 * [53] Maximum Subarray
 * <p>
 * https://leetcode.com/problems/maximum-subarray/description/
 * <p>
 * algorithms
 * Easy (42.73%)
 * Total Accepted:    473.8K
 * Total Submissions: 1.1M
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 * <p>
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 */
public class Solution_53 {

    /**
     * 思路： 大致有两种方法，动态规划和递归分治。
     * 动态规划： 方法内定义两个变量，一个记录最大字串和，一个记录当前字串加上最新值后的和，如果该值小于0，则将当前tempMax置零并从下个字符开始重新求字串和。示例代码即为动态规划
     * 递归分治：就像快排使用递归分治一样
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int tempMax = 0;

        for (int i = 0; i < nums.length; i++) {
            if (tempMax < 0) {
                tempMax = 0;
            }
            tempMax = tempMax + nums[i];
            if (tempMax > maxSum) {
                maxSum = tempMax;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Solution_53 solution_53 = new Solution_53();

        int[] nums = new int[]{1, 2,-5, 3, 4};
        System.out.println(solution_53.maxSubArray(nums));
    }
}

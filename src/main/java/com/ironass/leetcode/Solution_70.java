package com.ironass.leetcode;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 *  Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *  Note: Given n will be a positive integer.
 *
 *  Example 1:
 *
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 *
 *  Example 2:
 *
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * @author lixin
 * @date 2019-05-09 14:55
 **/
public class Solution_70 {

    /**
     * 思路：
     * 动态规划类型的题，本质就是斐波拉契数列求解
     * 两种方案：1. 总结出数学规律，直接循环求解  2. 使用递归的方式求解(不推荐)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n<=2){
            return n;
        }
        int pre1 = 1;
        int pre2 = 2;
        for(int i=3;i<=n;i++){
            int result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }

        return pre2;
    }

    /**
     * 递归解法 代码很简洁，但是，时间复杂度和空间复杂度太高了
     * @param n
     * @return
     */
    public int recursionClimbStairs(int n){
        if(n<=2){
            return n;
        }

        return recursionClimbStairs(n-2) + recursionClimbStairs(n-1);
    }


    public static void main(String[] args) {
        Solution_70 solution_70 = new Solution_70();
        System.out.println(solution_70.recursionClimbStairs(10));
    }

}

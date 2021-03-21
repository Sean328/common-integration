package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-03-04 15:36
 **/
public class Solution_07_Reverse {
    /**
     * @lc app=leetcode id=7 lang=java
     *
     * [7] Reverse Integer
     *
     * https://leetcode.com/problems/reverse-integer/description/
     *
     * algorithms
     * Easy (25.11%)
     * Total Accepted:    609K
     * Total Submissions: 2.4M
     * Testcase Example:  '123'
     *
     * Given a 32-bit signed integer, reverse digits of an integer.
     *
     * Example 1:
     *
     *
     * Input: 123
     * Output: 321
     *
     *
     * Example 2:
     *
     *
     * Input: -123
     * Output: -321
     *
     *
     * Example 3:
     *
     *
     * Input: 120
     * Output: 21
     *
     *
     * Note:
     * Assume we are dealing with an environment which could only store integers
     * within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of
     * this problem, assume that your function returns 0 when the reversed integer
     * overflows.
     *
     */

    /**
     * 需要注意java整型是4个字节，也就是32位，表示范围和题目要求是一致的。
     * 所以结果值应该使用8个字节64位的long类型存储，并判断有没有超过32位的描述界限
     */

    public static  int reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = (result * 10) + (x % 10);
            x = x / 10;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            result = 0;
        }

        return (int) result;

    }

    public static void main(String[] args) {
        int a = 3214;

        System.out.println(reverse(a));
    }

}

package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-03-12 18:14
 **/

/**
 * @lc app=leetcode id=38 lang=java
 *
 * [38] Count and Say
 *
 * https://leetcode.com/problems/count-and-say/description/
 *
 * algorithms
 * Easy (39.45%)
 * Total Accepted:    262.3K
 * Total Submissions: 661.7K
 * Testcase Example:  '1'
 *
 * The count-and-say sequence is the sequence of integers with the first five
 * terms as following:
 *
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the n^th term of the
 * count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a
 * string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: 1
 * Output: "1"
 *
 *
 * Example 2:
 *
 *
 * Input: 4
 * Output: "1211"
 *
 */
public class Solution_38 {

    /**
     * 思路：
     * 首先要读明白题的意思，很明显这道题目描述并不好读懂。要理解题目需要观察前一个数的输出和后一个数输出之间的关系，
     * 解这道题明显需要递归的解法，1的输出是固定的，后续的数据都要依赖前一个数字的输出，因此可以确立递归的解法。
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        //递归调用，然后对字符串进行处理
        String str = countAndSay(n - 1) + "*";
        char[] c = str.toCharArray();
        int count = 1;
        String s = "";
        for (int i = 0; i < c.length-1; i++) {
            if(c[i] == c[i+1]){
                count ++;
            } else {
                s = s + count + c[i];
                count = 1;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        Solution_38 solution_38 = new Solution_38();

        int a = 8;

        System.out.println(solution_38.countAndSay(a));
    }
}

package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-03-08 17:08
 **/

/**
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 *
 * https://leetcode.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (31.20%)
 * Total Accepted:    387K
 * Total Submissions: 1.2M
 * Testcase Example:  '"hello"\n"ll"'
 *
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 *
 * Example 1:
 *
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 *
 * Example 2:
 *
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 *
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty
 * string. This is consistent to C's strstr() and Java's indexOf().
 *
 */
public class Solution_28 {

    /**
     * 思路：简单方法使用String中的api来分割字段
     * 复杂算法使用kmp
     * 这里使用简单算法来完成
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(needle==null || needle.equals("")){
            return 0;
        }

        if(haystack.equals(needle)){
            return 0;
        }

        int size = needle.length();
        for(int i=0;i<=haystack.length()-size;i++){
            if(needle.equals(haystack.substring(i,i+size))){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution_28 solution_28 = new Solution_28();
        String haystack = "mississippi";
        String needle = "pi";

        System.out.println(solution_28.strStr(haystack,needle));
    }
}

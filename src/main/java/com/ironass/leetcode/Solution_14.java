package com.ironass.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 * @date 2019-03-05 19:08
 **/

/**
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (32.97%)
 * Total Accepted:    413.4K
 * Total Submissions: 1.3M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 *
 * Example 2:
 *
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 */
public class Solution_14 {

    public String longestCommonPrefix(String[] strs) {

        if(strs.length <1){
            return "";
        }

        List<String> remainStrs =  new ArrayList<>();
        String shortestStr = strs[0];
        for(int i=0;i<=strs.length-1;i++){
            remainStrs.add(strs[i]);
            if(shortestStr.length() > strs[i].length()){
                shortestStr = strs[i];
            }
        }


        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<=shortestStr.length() -1;i++){
            int finalI = i;
            String finalShortestStr = shortestStr;
            if(remainStrs.stream().filter(p -> p.charAt(finalI) == finalShortestStr.charAt(finalI)).count() == remainStrs.size()){
                sb.append(shortestStr.charAt(i));
            }else {
                break;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution_14 solution_14 = new Solution_14();
        String [] strs = new String[]{"aa","a"};
        System.out.println(solution_14.longestCommonPrefix(strs));
    }


}

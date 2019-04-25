package com.ironass.leetcode;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 *
 *  If the last word does not exist, return 0.
 *
 *  Note: A word is defined as a character sequence consists of non-space characters only.
 *
 *  Example:
 *
 * Input: "Hello World"
 * Output: 5
 */
public class Solution_58 {

    /**
     *
     * 思路：
     * 可以从后往前遍历字符数组，碰到第一个单词记录长度并返回长度即可
     * @param s
     * @return
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        int slong = 0;
        // 记录最后一个 word 的长度
        int preLong = 0;
        int falg = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == ' ' ) {
                slong = 0;
                if(preLong > 0) {
                    break;
                }
            } else {
                slong++;
                preLong = slong;
            }
        }

        return preLong;
    }

    public static void main(String[] args) {
        Solution_58 solution_58 = new Solution_58();
        System.out.println(solution_58.lengthOfLastWord("ni hao zhong   guo "));
    }

}

package com.ironass.algorithm.leetcode;

/**
 * @lc app=leetcode id=13 lang=java
 * <p>
 * [13] Roman to Integer
 * <p>
 * https://leetcode.com/problems/roman-to-integer/description/
 * <p>
 * algorithms
 * Easy (51.51%)
 * Total Accepted:    367.5K
 * Total Submissions: 712.7K
 * Testcase Example:  '"III"'
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M.
 * <p>
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * For example, two is written as II in Roman numeral, just two one's added
 * together. Twelve is written as, XII, which is simply X + II. The number
 * twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making
 * four. The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * <p>
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * <p>
 * <p>
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be
 * within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: "III"
 * Output: 3
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: "IV"
 * Output: 4
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: "IX"
 * Output: 9
 * <p>
 * Example 4:
 * <p>
 * <p>
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * <p>
 * <p>
 * Example 5:
 * <p>
 * <p>
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */


/**
 * @author lixin
 * @date 2019-03-05 18:57
 **/
public class Solution_13_RomanToInt {

    public int romanToInt(String s) {
        char[] romanChar = s.toCharArray();
        int result = 0;
        int last = 0;
        for (int i = romanChar.length - 1; i >= 0; i--) {
            int now = translateRoman2Numer(romanChar[i]);
            if (now < last) {
                result = result - now;
            } else {
                result = result + now;
            }

            last = now;
        }

        return result;
    }

    public static int translateRoman2Numer(char s) {
        switch (s) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            case 'a':
                return 900;
            case 'b':
                return 400;
            case 'c':
                return 90;
            case 'd':
                return 40;
            case 'e':
                return 9;
            case 'f':
                return 4;
            default:
                return 0;
        }
    }

    private static int romanToInt20210327(String s) {
        if (s == null || s == "") {
            return 0;
        }
        s = s.replace("CM", "a").replace("CD", "b")
                .replace("XC", "c").replace("XL", "d")
                .replace("IX", "e").replace("IV", "f");

        int res = 0;
        for(int i = 0; i< s.length(); i++){
            res += translateRoman2Numer(s.charAt(i));
        }

        return res;
    }

    public static void main(String[] args) {
        Solution_13_RomanToInt solution_13RomanToInt = new Solution_13_RomanToInt();
        System.out.println(solution_13RomanToInt.romanToInt("MCMXCIV"));
        System.out.println(romanToInt20210327("MCMXCIV"));
    }
}

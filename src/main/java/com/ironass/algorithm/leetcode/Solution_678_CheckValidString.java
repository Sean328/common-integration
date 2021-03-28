package com.ironass.algorithm.leetcode;

import java.util.Stack;

/**
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 示例 1:
 *
 * 输入: "()"
 * 输出: True
 * 示例 2:
 *
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 *
 * 输入: "(*))"
 * 输出: True
 * 注意:
 *
 * 字符串大小将在 [1，100] 范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_678_CheckValidString {

    public static void main(String[] args) {
        System.out.println(checkValidString("(((******((("));
        System.out.println(checkValidString("((()()(**))"));
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("()"));
    }

    public static boolean checkValidString(String s) {
        if(s == "" || s == null){
            return true;
        }
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();
        for(int i = 0;i< s.length(); i++){
            char c = s.charAt(i);
            if('(' == c){
                left.push(i);
            } else if('*' == c){
                star.push(i);
            } else {
                if(!left.isEmpty()){
                    left.pop();
                } else if(!star.isEmpty()){
                    star.pop();
                } else {
                    return false;
                }
            }
        }
        if(left.size() > 0 && left.size() > star.size()){
            return false;
        }

        while (!left.isEmpty() && !star.isEmpty()){
            if(left.peek() > star.peek()){
                return false;
            }
            left.pop();
            star.pop();
        }
        return true;
    }
}

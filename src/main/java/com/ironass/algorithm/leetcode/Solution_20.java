package com.ironass.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author lixin
 * @date 2019-03-07 10:13
 **/

/**
 * @lc app=leetcode id=20 lang=java
 * <p>
 * [20] Valid Parentheses
 * <p>
 * https://leetcode.com/problems/valid-parentheses/description/
 * <p>
 * algorithms
 * Easy (35.85%)
 * Total Accepted:    522.9K
 * Total Submissions: 1.5M
 * Testcase Example:  '"()"'
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * <p>
 * <p>
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: "()"
 * Output: true
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: "()[]{}"
 * Output: true
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: "(]"
 * Output: false
 * <p>
 * <p>
 * Example 4:
 * <p>
 * <p>
 * Input: "([)]"
 * Output: false
 * <p>
 * <p>
 * Example 5:
 * <p>
 * <p>
 * Input: "{[]}"
 * Output: true
 */
public class Solution_20 {

    /**
     * 思路 使用栈来解决是最优的,逆波兰公式（后缀表达式）也是使用的这种方式
     * 注意一点右边符号不会早于左边符号出现
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        if (s == null || s.equals("")) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(CONSTS_SET.contains(ch)){
                if('('==ch || '{'==ch || '['==ch){
                    stack.push(ch);
                } else {
                    if(!stack.empty()){
                        char popChar = stack.pop();
                        if(!((ch == ')' && popChar == '(') || (ch == '}' && popChar=='{' ) || (ch==']' && popChar=='['))){
                            return false;
                        }
                    }else {
                        return false;
                    }
                }

            }
        }
        if(stack.empty()){
            return true;
        }else {
            return false;
        }
    }

    static final Set<Character> CONSTS_SET = new HashSet<Character>() {
        private static final long serialVersionUID = -3717574291709921408L;
        {
            add('(');
            add(')');
            add('{');
            add('}');
            add('[');
            add(']');
        }
    };

    public static void main(String[] args) {
        Solution_20 solution_20 = new Solution_20();
        System.out.println(solution_20.isValid("()[]{}"));
    }
}

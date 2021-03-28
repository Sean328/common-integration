package com.ironass.sword2offer;

import java.util.Stack;

/**
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 *
 * 输入例子1:
 * "()"
 *
 * 输出例子1:
 * true
 *
 * 输入例子2:
 * "(*)"
 *
 * 输出例子2:
 * true
 *
 * 输入例子3:
 * "(*))"
 *
 * 输出例子3:
 * true
 */
public class checkValidStringMain {

    public static void main(String[] args) {
//        System.out.println(checkValidString("(*))"));
        System.out.println(checkValidString("(((******((("));
        System.out.println(checkValidString("((()()(**))"));
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("()"));
    }

    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public static  boolean checkValidString (String s) {
        // write code here
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

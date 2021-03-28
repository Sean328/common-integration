package com.ironass.algorithm.sword2offer;

import java.util.Stack;

/**
 * 给定一个字符串式子，返回它的计算结果。算术规则为: k*[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。e.g. s = "3*[a2*[c]]", 返回 “accaccacc”
 * <p>
 * 输入例子1:
 * "3*[a2*[c]]"
 * <p>
 * 输出例子1:
 * "accaccacc"
 */
public class ComputerStringMain {

    /**
     * 参考实现： https://www.nowcoder.com/questionTerminal/2006edef839c453d9cf69e6371993f25?orderByHotValue=1&page=1&onlyReference=false
     * @param args
     */
    public static void main(String[] args) {
        String str = "3*[a2*[c]]";

        System.out.println(computeString("10*[a]"));
        System.out.println(computeString(str));
    }

    private static String computeString(String str) {
        if (str == null || str == " ") {
            return null;
        }

        Stack<Character> stack = new Stack<>();

        String tmpStr = "";
        String num = "";
        boolean multi = false;
        for (int i = 0; i < str.length(); i++) {
            if (']' != str.charAt(i)) {
                stack.push(str.charAt(i));
            } else {
                Stack<Character> characters = new Stack<>();
                Stack<Integer> integers = new Stack<>();
                while ('[' != stack.peek()) {
                    char c = stack.pop();
                    if (Character.isDigit(c)) {
                        num = c + num;
                    } else if ('*' == c) {
                        multi = true;
                    } else {
                        if (multi && Integer.valueOf(num) != 0) {
                            String tmp = "";
                            for (int j = 0; j < Integer.valueOf(num); j++) {
                                tmp = tmp + tmpStr;
                            }
                            tmpStr = tmp;
                            multi = false;
                            num = "";
                        }
                        tmpStr = c + tmpStr;
                    }
                }
                if ('[' == stack.peek()) {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (Character.isDigit(c)) {
                num = c + num;
                if(stack.isEmpty()){
                    String tmp = "";
                    for (int j = 0; j < Integer.valueOf(num); j++) {
                        tmp = tmp + tmpStr;
                    }
                    tmpStr = tmp;
                }
            } else if ('*' == c) {
                multi = true;
            } else {
                if (multi && Integer.valueOf(num) != 0) {
                    String tmp = "";
                    for (int j = 0; j < Integer.valueOf(num); j++) {
                        tmp = tmp + tmpStr;
                    }
                    tmpStr = tmp;
                    multi = false;
                    num = "";
                }
                tmpStr = c + tmpStr;
            }
        }

        return tmpStr;
    }
}

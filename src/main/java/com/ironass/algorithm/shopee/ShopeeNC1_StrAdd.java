package com.ironass.algorithm.shopee;

import java.util.Stack;

/**
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
 * 示例1
 * 输入
 * 复制
 * "1","99"
 * 返回值
 * 复制
 * "100"
 * 说明
 * 1+99=100
 */
public class ShopeeNC1_StrAdd {

    public static void main(String[] args) {

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();

        int i = char1.length - 1;
        int j = char2.length - 1;
        //表进位
        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0){
            int a = 0;
            int b = 0;
            if(i >= 0){
                a = char1[i] -'0';
                i --;
            }
            if(j >= 0){
                b = char2[j] - '0';
                j--;
            }
            int sum = a + b + carry;
            int rem = sum % 10;
            carry = sum / 10;
            stringBuilder.append(rem);
        }

        return stringBuilder.reverse().toString();
    }
}

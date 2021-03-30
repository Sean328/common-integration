package com.ironass.algorithm.shopee;

/**
 * 给定一个字符串，请编写一个函数判断该字符串是否回文。如果回文请返回true，否则返回false
 */
public class ShopeeNC141_Judge {

    public static void main(String[] args) {
        System.out.println(judge("yamatomaya"));
        System.out.println(judge("absba"));
        System.out.println(judge("abba"));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str string字符串 待判断的字符串
     * @return bool布尔型
     */
    public static boolean judge (String str) {
        // write code here
        if(str == null || str == ""){
            return false;
        }
        for(int i = 0, j=str.length() -1; i < str.length(); i++, j-- ){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
        }
        return true;
    }
}

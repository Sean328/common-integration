package com.ironass.algorithm.shopee;

/**
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
 * 题目保证str1和str2的最长公共子串存在且唯一。
 *
 * 未完全掌握
 */
public class ShopeeNC127_LCS {

    public static void main(String[] args) {
        String str1 = "1AB2345CD";
        String str2 = "12345EF";
        System.out.println(LCS(str1,str2));
    }


    /**
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public static String LCS (String str1, String str2) {

        int m = str1.length();
        int n = str2.length();
        int [][]dp = new int[m+1][n+1];
        int idxMax = 0;
        int lenMax = 0;
        for(int i = 0; i< m; i++){
            for(int j = 0; j < n; j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    if(i==0 || j == 0){
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i-1][j-1]+1;
                    }
                }
                if(lenMax < dp[i][j]){
                    lenMax = dp[i][j];
                    idxMax = i;
                }
            }
        }

        if(lenMax == 0){
            return null;
        }

        return str1.substring(idxMax-lenMax+1,idxMax+1);
    }
}

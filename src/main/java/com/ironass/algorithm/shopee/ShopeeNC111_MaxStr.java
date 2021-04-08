package com.ironass.algorithm.shopee;

import java.util.Arrays;

/**
 * 题目描述
 * 给定一个数组由一些非负整数组成，现需要将他们进行排列并拼接，使得最后的结果最大，返回值需要是string类型 否则可能会溢出
 * 示例1
 * 输入
 * [30,1]
 * 返回值
 * "301"
 * <p>
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */
public class ShopeeNC111_MaxStr {

    public static void main(String[] args) {
        int [] arr = new int[]{30,1};
        System.out.println(maxStr(arr));
    }

    /**
     * 最大数
     * <p>
     * 我们知道，两个字符串之间比较大小的是比较每一个字符的大小，遇到第一个不相等的字符则会根据其ASCII码来比较大小，
     * 我们都知道整型数字越大，ASCII码也越大，所以我们只需要比较A+B跟B+A之间的大小，将数组按照这个规则去排序，
     * 排出来的不就是我们想要的结果了吗？
     *
     * @param nums int整型一维数组
     * @return string字符串
     */
    public static  String maxStr(int[] nums) {
        // write code here
        if (nums == null || nums.length == 0) {
            return "";
        }

        //先冒泡排序
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                String now = String.valueOf(nums[j]);
                String next = String.valueOf(nums[j - 1]);
                String sumNowNext = now + next;
                String sumNextNow = next + now;
                if (Integer.valueOf(sumNowNext).compareTo(Integer.valueOf(sumNextNow)) > 0) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }

        //排序完毕，最大的字符串在前面
        String result = "";
        for(int i=0; i< nums.length; i++){
            result = result + nums[i];
        }
        return Integer.valueOf(result) == 0 ? "0" : result;
    }

    public static  String maxStr2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String [] strs = new String[nums.length];
        for(int i=0; i< nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (s1, s2) -> {return (s2+s1).compareTo(s1+s2);});

        if(strs[0].equals("0")){
            return "0";
        } else {
            String res = "";
            for(int i=0; i< strs.length;i++){
                res += strs[i];
            }
            return res;
        }
    }
}

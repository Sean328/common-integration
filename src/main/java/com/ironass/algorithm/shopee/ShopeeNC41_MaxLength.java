package com.ironass.algorithm.shopee;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 */
public class ShopeeNC41_MaxLength {

    public static void main(String[] args) {
        int[] a = {2, 2, 3, 4, 3};

        System.out.println(maxLength(a));
    }


    /**
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public static int maxLength(int[] arr) {
        // write code here

        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (!map.containsKey(arr[j])) {
                    map.put(arr[j], j);
                    if (map.size() > maxLen) {
                        maxLen = map.size();
                    }
                } else {
                    i = map.get(arr[j]);
                    map.clear();
                    break;
                }
            }
        }

        return maxLen;
    }
}

package com.ironass.algorithm.shopee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * 示例1
 * 输入
 * [1,2,3,2,2,2,5,4,2]
 * 返回值
 * 2
 */
public class ShopeeNC73_MoreThanHalfNum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};

        System.out.println(MoreThanHalfNum_Solution(arr));
    }

    public static int MoreThanHalfNum_Solution(int[] array) {
        int half = array.length / 2;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (map.get(array[i]) == null) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], map.get(array[i]) + 1);
            }
        }

        Integer max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = entry.getValue().compareTo(max) > 0 ? entry.getKey() : max;
        }
        if (map.get(max) > half) {
            return max;
        } else {
            return 0;
        }

    }
}

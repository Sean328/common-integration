package com.ironass.algorithm.shopee;

/**
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
 *
 * 示例1
 * 输入
 * 复制
 * [1, -2, 3, 5, -2, 6, -1]
 * 返回值
 * 12
 */
public class ShopeeNC19_MaxsumofSubarray {

    public static void main(String[] args) {

    }


    /**
     * max sum of the subarray
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxsumofSubarray(int[] arr) {
        // write code here
        if(arr == null || arr.length == 0){
            return -1;
        }

        int maxVal = Integer.MIN_VALUE;
        int curVal = 0;
        for (int i = 0; i < arr.length; i++) {
            curVal += arr[i];
            maxVal = maxVal < curVal ? curVal : maxVal;
            curVal = curVal < 0 ? 0 : curVal;
        }

        return maxVal;
    }
}
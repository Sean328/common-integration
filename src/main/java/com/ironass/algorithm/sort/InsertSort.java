package com.ironass.algorithm.sort;

import java.util.Arrays;

/**
 * 1. 原地排序算法。
 * 2. 稳定的排序算法
 * 3. 最好是时间复杂度为 O(n), 最坏情况时间复杂度为 O(n2)
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 8, 1, 5, 6, 8, 7};
        System.out.println("before sort:" + Arrays.toString(array));
        insertionSort(array, array.length);
        System.out.println("after sort:" + Arrays.toString(array));
    }

    public static void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int val = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > val) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = val;
        }
    }
}

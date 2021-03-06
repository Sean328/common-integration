package com.ironass.algorithm.sort;

import java.util.Arrays;

/**
 * 1. 空间复杂度为 O(1)，是一种原地排序算法
 * 2. 最好情况时间复杂度、最坏情况和平均情况时间复杂度都为 O(n2)
 * 3. 选择排序是一种不稳定的排序算法
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 8, 1, 5, 6, 8, 7};
        System.out.println("before sort:" + Arrays.toString(array));
        selectionSort(array, array.length);
        System.out.println("after sort:" + Arrays.toString(array));
    }

    public static void selectionSort(int[] a, int n) {
        if(a == null){
            return;
        }

        for(int i=0; i< n; i++){
            int minIndex = i;
            for(int j=i; j<n; j++){
                if(a[j] < a[minIndex]){
                    minIndex = j;
                }
            }
            int tmp = a[i];
            a[i] =a[minIndex];
            a[minIndex] = tmp;
        }
    }
}



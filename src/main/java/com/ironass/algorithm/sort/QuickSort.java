package com.ironass.algorithm.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 8, 1, 5, 6, 8, 7};
        System.out.println("before sort:" + Arrays.toString(array));
        quickSort(array, array.length);
        System.out.println("after sort:" + Arrays.toString(array));
    }

    public static void quickSort(int[] a, int length) {
        quickSortIntern(a, 0, length - 1);
    }

    private static void quickSortIntern(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int pivot = partiton3(a, p, r);
        quickSortIntern(a, p, pivot - 1);
        quickSortIntern(a, pivot + 1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                    i++;
                }
            }
        }
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;
    }



    private static int partiton2(int []a, int p, int r){
        int pivot = a[r];
        int i = p;
        for(int j= p; j < r; j++){
            if(a[j] < pivot){
                if(i != j){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
                i++;
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        return i;
    }



    private static int partiton3(int []a, int p, int r){
        int pivot = a[r];
        int i = p;
        for(int j = p; j < a.length; j++){
            if(a[j] < pivot){
                if(i != j){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
                i++;
            }
        }
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        return i;
    }
}

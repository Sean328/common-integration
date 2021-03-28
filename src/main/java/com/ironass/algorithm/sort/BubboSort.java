package com.ironass.algorithm.sort;

import java.util.Arrays;

public class BubboSort {

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 8, 1, 5, 6, 8, 7};
        System.out.println("before sort:" + Arrays.toString(array));
//        bubboSort(array, array.length);
        bubboSort2(array, array.length);
        System.out.println("after sort:" + Arrays.toString(array));
    }

    public static void bubboSort(int []a, int length){
        int tmp;
        for(int i = 0; i<length; i++){
            boolean flag = false;
            for(int j = 0; j<length-1; j++){
                if(a[j] > a[j+1]){
                    tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void bubboSort2(int []a, int length){
        int tmp;
        for(int i = length-1; i>=0; i--){
            boolean flag = false;
            for(int j = i; j>0; j--){
                if(a[j] < a[j-1]){
                    tmp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }
}

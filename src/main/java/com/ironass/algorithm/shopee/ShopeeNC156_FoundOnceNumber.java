package com.ironass.algorithm.shopee;

import java.util.HashMap;
import java.util.Map;

public class ShopeeNC156_FoundOnceNumber {

    public static void main(String[] args) {

    }

    public int foundOnceNumber (int[] arr, int k) {
        // write code here

        Map<Integer,Integer> map = new HashMap();
        for(int i = 0; i< arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }

        for(int i =0; i< arr.length; i++){
            if(map.get(arr[i]) == 1){
                return arr[i];
            }
        }

        return -1;
    }
}

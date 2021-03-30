package com.ironass.algorithm.shopee;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 暂未掌握
 */
public class ShopeeNC93_LRU {

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        ArrayList<Integer> list= new ArrayList<>();

        for(int i = 0 ; i < operators.length ; i++){
            Integer opt = operators[i][0],key = operators[i][1];
            if(opt == 1){ //set
                if(map.containsKey(key)){
                    map.remove(key);
                }else{
                    //当超出缓存大小后，由于map中的数据是已经排序好的，所以直接将keySet的第一位移除即可。
                    if(map.size() == k){
                        map.remove(map.keySet().toArray()[0]);
                    }
                }
                map.put(key,operators[i][2]);
            }else if(opt == 2){//get
                if(map.containsKey(key)){
                    int val = map.remove(key);
                    map.put(key,val);
                    list.add(map.get(key));
                }else{
                    list.add(-1);
                }
            }


        }

        int[] arr = new int[list.size()];

        for(int i = 0 ; i < arr.length ; i++){
            arr[i] = list.get(i);
        }

        return arr;
    }
}

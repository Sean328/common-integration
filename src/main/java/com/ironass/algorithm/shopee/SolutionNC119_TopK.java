package com.ironass.algorithm.shopee;

import java.util.*;

/**
 * 给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * 如果K>数组的长度，那么返回一个空的数组
 */
public class SolutionNC119_TopK {


    /**
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result  = new ArrayList<>();
        if(k == 0 || k > input.length){
            return result;
        }

//        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, (o1, o2) -> o2.compareTo(o1));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());

        for(int i=0; i< input.length;i++){
            if(maxHeap.size() != k){
                maxHeap.offer(input[i]);
            } else if(maxHeap.peek() > input[i]){
                Integer tmp = maxHeap.poll();
                tmp = null;
                maxHeap.offer(input[i]);
            }
        }

        for(Integer integer : maxHeap){
            result.add(integer);
        }
        return result;
    }
}

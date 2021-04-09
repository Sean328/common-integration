package com.ironass.algorithm.shopee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度的时候，返回空
 * 示例1
 * 输入
 * 复制
 * [2,3,4,2,6,2,5,1],3
 * 返回值
 * 复制
 * [4,4,6,6,6,5]
 */
public class ShopeeNC82_MaxInWindows {

    public static void main(String[] args) {
        int[] num = new int[]{2, 3, 4, 2, 6, 2, 5, 1};
        System.out.println(maxInWindows(num, 3));
    }

    /**
     * 思路：用一个大顶堆，保存当前滑动窗口中的数据。滑动窗口每次移动一格，就将前面一个数出堆，后面一个数入堆。
     *
     * @param num
     * @param size
     * @return
     */
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
//        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length == 0 || size == 0 || size > num.length) {
            return res;
        }

        int count = 0;
        //初始化滑动窗口
        for (; count < size; count++) {
            maxQueue.offer(num[count]);
        }

        //对每次操作，找到最大值（用优先队列的大顶堆），然后向后滑动（出堆一个，入堆一个）
        while (count < num.length) {
            res.add(maxQueue.peek());
            maxQueue.remove(num[count - size]);
            maxQueue.offer(num[count]);
            count++;
        }

        res.add(maxQueue.peek());

        return res;
    }

    public static ArrayList<Integer> maxInWindows02(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (size > num.length || size == 0) {
            return list;

        }
        for (int i = 0; i <= num.length - size; i++) {
            int max = num[i];
            for (int j = i + 1; j < i + size; j++) {
                if (num[j] > max) {
                    max = num[j];
                }
            }
            list.add(max);
        }
        return list;
    }


}

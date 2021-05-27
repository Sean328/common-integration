package com.ironass.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution04_FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] a = new int[]{1, 3};
        int[] b = new int[]{ 4};
        System.out.println(findMedianSortedArrays(a, b));
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(Comparator.naturalOrder());
        for (int i : nums1) {
            priorityQueue.offer(i);
        }
        for (int i : nums2) {
            priorityQueue.offer(i);
        }

        int totalLength = nums1.length + nums2.length;
        int mid = priorityQueue.size() / 2;
        int cursor = 0;

        if (totalLength % 2 == 0) {
            for(int i = 0; i< mid-1; i++){
                priorityQueue.poll();
            }
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            return Double.valueOf((a+b)/2.0);
        } else {
            for(int i = 0; i< mid; i++){
                priorityQueue.poll();
            }
            return Double.valueOf(priorityQueue.poll());
        }
    }
}

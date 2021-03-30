package com.ironass.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (41.63%)
 * Total Accepted:    1.5M
 * Total Submissions: 3.6M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 * Example:
 *
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 *
 *
 *
 */
class Solution_01_TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if (map.containsKey(j) && map.get(j) != i) {
                result[0] = i;
                result[1] = map.get(j);
                break;
            }
        }

        return result;
    }

    private static int[] pratic20210327(int[] nums, int target) {
        if (nums.length == 0) {
            return null;
        }
        Map<Integer, Integer> valMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            valMap.put(nums[i], i);
        }

        int [] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int rest = target - nums[i];
            if(valMap.containsKey(rest)){
                result[0] = i;
                result[1] = valMap.get(rest);
                break;
            }
        }

        return result;
    }

    public static  int[] twoSum20210330 (int[] numbers, int target) {
        // write code here
        Map<Integer,Integer> map = new HashMap();

        for(int i =0;i< numbers.length; i++){
            map.put(numbers[i],i);
        }

        int [] result = new int[2];
        for(int i = 0; i< numbers.length; i++){
            int j = target - numbers[i];
            if (map.containsKey(j) && map.get(j) != i) {
                result[0] = i;
                result[1] = map.get(j);
            }
        }
//         if(res[0] > res[1]){
//             int tmp = res[0];
//             res[0] = 1;
//             res[1] = tmp;
//         }

        return result;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 4};
        int sum = 6;
        int[] result = twoSum(a, sum);
        Arrays.stream(result).forEach(System.out::println);
        System.out.println("--------------------");
        int[] result20210327 = twoSum20210330(a, sum);
        Arrays.stream(result20210327).forEach(System.out::println);
    }
}
package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-02-28 10:16
 **/
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
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap();

        for(int i=0;i<nums.length; i++){
            map.put(nums[i],i);
        }
        int[] result =new int[2] ;
        for(int i=0;i<nums.length;i++){
            int j = target - nums[i];
            if(map.containsKey(j) && map.get(j)!=i){
                result[0] = i;
                result[1] = map.get(j);
            }
        }

        return result;
    }
}
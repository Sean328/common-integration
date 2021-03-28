package com.ironass.algorithm.leetcode;

/**
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: 4
 * Output: 2
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 *              the decimal part is truncated, 2 is returned.
 *
 * @author lixin//徒手开根号开始
 * @date 2019-04-28 15:16
 **/
public class Solution_69 {

    /**
     * 此题共有三种解题方案
     * 1. 使用暴力穷举法，总有存在 a^a <= x <= (a+1)^(a+1)的数存在，缺点是时间复杂度高，而且定义未int类型会超过Integer.maxValue()的情况
     * 2. 使用二分法，即在 1 ~ x/2之间,每次都取中间值来缩小范围，类似于判断是否是质数的算法
     * 3. 使用牛顿法
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1;
        int right = x / 2;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if ((mid + 1) > x / (mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            }
        }
        return 0;
    }

    /**
     * 牛顿法开根号
     * @param x
     * @return
     */
    public int newtonSqrt2(int x) {
        if (x <= 1) {
            return x;
        }
        double last = 0, res = 1;
        while(last != res) {
            last = res;
            res = (res + x/res)/2;
        }
        return (int)res;

    }

    public static void main(String[] args) {
        Solution_69 solution_69 = new Solution_69();
        int a = 333232;
        System.out.println(solution_69.mySqrt(a));
        System.out.println(solution_69.newtonSqrt2(a));
    }
}

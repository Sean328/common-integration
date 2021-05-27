package com.ironass.algorithm.shopee;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class ShopeeNC68_JumpFloor {

    public static void main(String[] args) {

        System.out.println(JumpFloor(3));

    }

    public static int JumpFloor(int target) {
        if(target <= 2){
            return target;
        }

        return JumpFloor(target-1) + JumpFloor(target-2);
    }
}
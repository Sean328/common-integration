package com.ironass.leetcode;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class Solution_66 {

    /**
     * 思路：
     * 倒序遍历处理
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

//        boolean carryFlag = true;
//        for (int i = digits.length - 1; i >= 0; i--) {
//            if (carryFlag) {
//                digits[i] = digits[i] + 1;
//                if (digits[i] == 10) {
//                    digits[i] = 0;
//                    carryFlag = true;
//                } else {
//                    carryFlag = false;
//                }
//            }
//        }
//        if (digits[0] == 0) {
//            int[] newDigits = new int[digits.length + 1];
//            newDigits[0] = 1;
//            for (int i = 0; i < digits.length - 1; i++) {
//                newDigits[i + 1] = digits[i];
//            }
//            return newDigits;
//        }


        int newDig[];

        for(int i=digits.length-1;i>=0;i--){
            if(digits[i] == 9){
                digits[i] = 0;
            }else {
                digits[i] ++;
                return digits;
            }
        }

        //能走到这里说明数组第一位为0
        newDig = new int [digits.length+1];
        //只复制第一位，因为数组初始化其他位为0...这种写法很鸡贼
        newDig[0] = 1;

        return digits;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 9};
        Solution_66 solution_66 = new Solution_66();
        System.out.println(solution_66.plusOne(arr));
    }
}

package com.ironass.leetcode;

/**
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 **/
public class Solution_67 {

    public String addBinary(String a, String b) {
        char[] shortArr = a.toCharArray();
        char[] longArr = b.toCharArray();
        if(a.length() != b.length()){
            shortArr = a.length() < b.length() ? a.toCharArray() : b.toCharArray();
            longArr = a.length() > b.length() ? a.toCharArray() : b.toCharArray();
        }

        boolean carryFlag = false;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < longArr.length; i++) {
            int temp = 0;
            if(i < shortArr.length){
                temp = Integer.parseInt(String.valueOf(longArr[longArr.length - 1 - i])) + Integer.parseInt(String.valueOf(shortArr[shortArr.length - 1 - i]));
            } else {
                temp = Integer.parseInt(String.valueOf(longArr[longArr.length - 1 - i]));
            }

            if(carryFlag){
                temp = temp + 1;
            }

            if(temp < 2){
                carryFlag = false;
            }

            if(temp == 2){
                temp = 0;
                carryFlag = true;
            }
            if(temp == 3){
                temp = 1;
                carryFlag = true;
            }
            sb.append(temp);

        }
        if(carryFlag){
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1001";
        String b = "110";
        Solution_67 solution_67 = new Solution_67();
        System.out.println(solution_67.addBinary(a, b));

    }

}

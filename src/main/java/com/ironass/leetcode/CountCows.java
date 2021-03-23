package com.ironass.leetcode;

public class CountCows {

    public static void main(String[] args) {
        System.out.println(getCountAfterYears(20));
    }

    private static int getCountAfterYears(int year){
        int total = 1;
        if(year < 4){
            return 1;
        }

        int start = 4;
        while (start <= year){
            int gapYears = start - 3;
            total += getCountAfterYears(gapYears);
            start++;
        }

        return total;
    }
}

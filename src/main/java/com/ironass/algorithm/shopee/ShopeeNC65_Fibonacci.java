package com.ironass.algorithm.shopee;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
 * n≤39
 */
public class ShopeeNC65_Fibonacci {

    public static void main(String[] args) {

        System.out.println(Fibonacci(4));
        System.out.println(Fibonacci(5));
        System.out.println(Fibonacci(6));
        System.out.println(Fibonacci(7));

    }

    public static int Fibonacci(int n) {
        if (n <= 1){
            return n;
        }

        return Fibonacci(n-2) + Fibonacci(n -1);
    }

    /**
     * 非递归
     * @param n
     * @return
     */
    public static int FibonacciNot(int n) {
        if (n <= 1){
            return n;
        }

        return Fibonacci(n-2) + Fibonacci(n -1);
    }
}

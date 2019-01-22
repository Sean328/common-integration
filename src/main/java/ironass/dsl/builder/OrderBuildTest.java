package com.ironass.dsl.builder;

/**
 * @author lixin
 * @date 2019-01-17 17:48
 **/
public class OrderBuildTest {

    public static void main(String[] args) {
        Order order = new Order.Builder()
                .buy(100,"IBM")
                .atLimitPrice(220)
                .getAllOrNone()
                .valueAs(225)
                .build();


        System.out.println(order);
    }

}

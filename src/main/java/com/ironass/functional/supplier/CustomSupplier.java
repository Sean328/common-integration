package com.ironass.functional.supplier;

import com.ironass.dsl.builder.Order;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author lixin
 * @date 2019-04-09 16:37
 **/
public class CustomSupplier implements Supplier<Order> {

    private int index = 0;
    private Random random = new Random();

    @Override
    public Order get() {
        Order order = new Order.Builder().buy(100, "IBM")
                .atLimitPrice(new Random().nextInt(100))
                .getAllOrNone()
                .valueAs(new Random().nextInt(20000))
                .build();

        return order;
    }

    public static void main(String[] args) {
        Stream.generate(new CustomSupplier()).
                limit(10).
                forEach(p -> System.out.println(p.toString()));
    }


}

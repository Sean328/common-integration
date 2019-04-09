package com.ironass.lambda.supplier;

import com.ironass.dsl.builder.Order;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lixin
 * @date 2019-04-09 16:16
 **/
public class SupplierStream {

    public static void main(String[] args) {
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(100).forEach(System.out::println);

        System.out.println("我是分隔符----------------------");
        //another way
        IntSupplier random2 = seed::nextInt;
        IntStream.generate(random2).limit(100).forEach(System.out::println);

        System.out.println("我是分隔符----------------------");
//        Stream.iterate(0, n -> n + 1).limit(100).forEach(x -> System.out.print(x + " "));
        IntStream.rangeClosed(1, 100).forEach(x -> System.out.print(x + " "));

        System.out.println("我是分隔符----------------------");
        //使用Collectiors的partitioningBy可以实现分组
        Map<Boolean, List<Order>> children = Stream.generate(new CustomSupplier())
                .limit(100).collect(Collectors.partitioningBy(p -> p.getLimitPrice() < 50));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());
    }

}

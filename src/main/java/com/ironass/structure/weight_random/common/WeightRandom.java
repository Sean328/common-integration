package com.ironass.structure.weight_random.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lixin
 * @date 2019-04-09 15:05
 **/
public class WeightRandom {

    private static List<WeightCategory> categories = new ArrayList<>();
    private static Random random = new Random();

    public static void initData() {
        WeightCategory wc1 = new WeightCategory("A", 60);
        WeightCategory wc2 = new WeightCategory("B", 30);
        WeightCategory wc3 = new WeightCategory("C", 10);
        categories.add(wc1);
        categories.add(wc2);
        categories.add(wc3);
    }

    public static void main(String[] args) {
        initData();

        int weightSum = categories.stream().map(p -> p.getWeight()).reduce((a, b) -> a + b).orElse(0);

        if (weightSum <= 0) {
            System.out.println("权重有问题");
            return;
        }


        for (int i = 0; i < 100; i++) {
            String result = getRandomNum(weightSum, categories);
            System.out.println("categroy"+result);
        }

    }

    private static String getRandomNum(int weightSum, List<WeightCategory> categories) {

        String randomCategroy = "default";

        int n = random.nextInt(weightSum);
        int m = 0;
        for (WeightCategory wc : categories) {
            if (m <= n && n < m + wc.getWeight()) {
//                System.out.println("this random categroy is " + wc.getCategroy());

                return wc.getCategroy();
            }
            m += wc.getWeight();
        }

//        if(n >= 0 && n < 60){
//            return "A";
//        } else if (n >= 60 && n < 90 ){
//            return "B";
//        } else {
//            return "C";
//        }

        return randomCategroy;
    }

}

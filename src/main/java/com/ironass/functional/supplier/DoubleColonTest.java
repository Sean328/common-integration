package com.ironass.functional.supplier;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author lixin
 * @date 2019-05-28 16:01
 **/
public class DoubleColonTest {
    public static void printStr(String str) {
        System.out.println("printStr : " + str);
    }

    public void toUpper() {
        System.out.println("toUpper : " + this.toString());
    }

    public void toLower(String str) {
        System.out.println("toLower : " + str);
    }

    public int toInt(String str) {
        System.out.println("toInt : " + str);
        return 1;
    }


    public void test(BiConsumer<DoubleColonTest, String> consumer) {

        Consumer<String> printStrConsumer = DoubleColonTest::printStr;
        printStrConsumer.accept("printStrConsumer");

        Consumer<DoubleColonTest> toUpperConsumer = DoubleColonTest::toUpper;
        toUpperConsumer.accept(new DoubleColonTest());

        BiConsumer<DoubleColonTest, String> toLowerConsumer = DoubleColonTest::toLower;
        toLowerConsumer.accept(new DoubleColonTest(), "toLowerConsumer");

        BiFunction<DoubleColonTest, String, Integer> toIntFunction = DoubleColonTest::toInt;
        int i = toIntFunction.apply(new DoubleColonTest(), "toInt");

        System.out.println("do something ...");
    }
}

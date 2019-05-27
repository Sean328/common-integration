package com.ironass.base;

import java.util.function.Supplier;

/**
 * 适用场景： 当适用slfj日志框架时，在开发和测试环境打印的低级别日志(如trace、debug等)会在生产环境中过滤掉
 * 但是在开发过程中打印低级别日志时，注意占位符中的参数填充如果是方法的话，会先将方法执行一遍，然后再调用返回对象的toString()方法将字符串填充到日志中。
 * 即便是生产中日志级别调整成了Info或更高级别，程序在低级别日志打印的的语句时，仍然会先将参数中的方法执行一遍，然后判断日志级别是否打印该日志和调用返回对象的toString()方法。
 * 因此，debug或低级别的日志打印语句在生产环境中依旧会带来性能损耗，而且性能损耗的高低是根据参数中方法的执行性能消耗而定。
 * 在频繁调用的日志打印语句中，即使是级别很低在线上环境会被过滤掉的log打印，也要注意这方面的性能损耗。
 *
 * 常见的处理措施有两种，第一种是适用sl4j中提供的api判断先判断日志级别，非指定级别直接跳过不执行该日志打印语句。如：
 * if (log.isDebugEnabled()) {
 *     log.debug("I found {}", getString());
 * }
 *
 * 第二种则是现在要给出的借据方案，利用函数式编程中Supplier类型的惰性求值，来实现日志的惰性打印。
 * 原理就是将日志要打印的对象方法 以Supplier类型的方式传给该方法，只有在达到目标日志级别的时候执行toString()方法的时候才会真正去调用传入的方法，从而大程度的省去一次方法的调用。
 *
 * 惰性字符串处理
 *
 * @author lixin
 * @date 2019-05-27 14:05
 **/
public class LazyStringUtils {
    private final Supplier<?> stringSupplier;

    public static LazyStringUtils lazy(Supplier<?> stringSupplier) {
        return new LazyStringUtils(stringSupplier);
    }

    public LazyStringUtils(final Supplier<?> stringSupplier) {
        this.stringSupplier = stringSupplier;
    }

    @Override
    public String toString() {
        return String.valueOf(stringSupplier.get());
    }
}

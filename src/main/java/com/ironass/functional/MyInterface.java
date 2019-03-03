package com.ironass.functional;

/**
 *
 *
 * @author lixin
 * @date 2019-02-15 16:51
 **/
@FunctionalInterface
public interface MyInterface {

    //单一抽象方法
    int myMethod();

    //如果这个方法未被注释掉，那么加上FunctionalInterface注解将会报错
    // int myOtherMethod();
    default String sayHello() {
        return "Hello, World!";
    }
    static void myStaticMethod() {
        System.out.println("I'm a static method in an interface");
    }

}

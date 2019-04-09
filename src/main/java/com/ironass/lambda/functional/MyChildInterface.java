package com.ironass.lambda.functional;

/**
 * 继承函数式接口的MyChildInterface将不再是函数式接口，只是一个标准接口
 * 因为它包含了两个抽象方法，父类的一个和它自己定义的一个抽象方法
 * 即使它加上了FunctionInterface注解也会报错
 * @author lixin
 * @date 2019-02-15 16:54
 **/

public interface MyChildInterface extends MyInterface{

    int anotherMethod();
}

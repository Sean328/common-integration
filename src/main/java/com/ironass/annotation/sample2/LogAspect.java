package com.ironass.annotation.sample2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author sean
 * @date 2019/03/03/23:06
 **/
@Aspect
@Component
public class LogAspect {

    // 把切面的连接点放在了我们的注解上
    @Pointcut("@annotation(com.ironass.annotation.sample2.LogAnno)")
    public void ouAspect() {
    }
    // 在这里定义前置切面
    @Before("ouAspect()")
    public void beforeMethod(JoinPoint joinPoint) {

        // 这里执行保存日志的动作
        System.out.println("方法前.......");
        //得到被切方法的参数
        System.out.println(joinPoint.getArgs()[0]);
    }

}

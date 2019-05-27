package com.ironass.maintest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 惰性日志测试
 *
 * @author lixin
 * @date 2019-05-27 14:54
 **/

public class LogTestMain {

    private static final Logger logger = LogManager.getLogger(LogTestMain.class);

    public static void main(String[] args) {

        logger.debug("日志测试，传入参数: {}", logPrinter());
    }

    static String logPrinter() {
        logger.info("测试日志输出");
        System.out.println("测试日志输出,控制台");
        return "测试字符串";
    }

}

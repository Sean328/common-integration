package com.ironass.maintest;

import com.ironass.base.LazyStringUtils;
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

        LogTestMain logTestMain = new LogTestMain();
        logger.debug("日志测试，传入参数: {}", LazyStringUtils.lazy(() -> logTestMain.logPrinterStr("xxl",new Object())));
    }

      String logPrinter() {
        logger.info("测试日志输出");
        System.out.println("测试日志输出,控制台");
          java.util.logging.Logger log = java.util.logging.Logger.getGlobal();
          //noinspection MethodRefCanBeReplacedWithLambda
          log.info(() -> logPrinterStr("xx",new Object()));

          logger.debug("日志测试，传入参数: {}", LazyStringUtils.lazy(() -> this.logPrinterStr("xxl",new Object())));

          return "测试字符串";
    }

    String logPrinterStr(String string, Object xxl1) {
        logger.info("测试日志输出");
        System.out.println("测试日志输出,控制台");
        java.util.logging.Logger logger = java.util.logging.Logger.getGlobal();
        logger.info(this::logPrinter);

        return "测试字符串";
    }
}

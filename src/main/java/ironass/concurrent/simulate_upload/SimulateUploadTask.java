package com.ironass.concurrent.simulate_upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lixin
 * @date 2019-01-17 11:52
 **/
public class SimulateUploadTask implements Runnable{


    private volatile AtomicInteger count = new AtomicInteger(0);
    private static Logger logger = LoggerFactory.getLogger(SimulateUploadTask.class);


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("模拟上传完成,当前处理文件数 : {}",count.incrementAndGet());
    }
}

package com.ironass.concurrent.simulate_upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author lixin
 * @date 2019-01-17 11:37
 **/
public class UploadMain {
    static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5000);
    static RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    private static Logger logger = LoggerFactory.getLogger(UploadMain.class);


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 5,15L,
                TimeUnit.SECONDS, workQueue, handler);
        SimulateUploadTask task = new SimulateUploadTask();

        ThreadPoolStatTask threadPoolStatTask = new ThreadPoolStatTask(threadPool);
        Thread poolStatTask = new Thread(threadPoolStatTask);
        poolStatTask.start();
        long statrtMills = System.currentTimeMillis();

        for (int i=0;i<10000; i++){
            if(i > 0 && i % 1000 == 0){
//                TimeUnit.MILLISECONDS.sleep(1000);
                logger.info("执行完1000条，当前耗时：{}",System.currentTimeMillis() - statrtMills);
            }

            threadPool.submit(task);
        }

    }

}

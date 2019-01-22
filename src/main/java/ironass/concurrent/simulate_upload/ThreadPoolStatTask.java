package com.ironass.concurrent.simulate_upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lixin
 * @date 2019-01-17 13:52
 **/
public class ThreadPoolStatTask implements Runnable{

    ThreadPoolExecutor threadPool;
    private static Logger logger = LoggerFactory.getLogger(ThreadPoolStatTask.class);


    public ThreadPoolStatTask(ThreadPoolExecutor threadPool){
        this.threadPool = threadPool;
    }

    @Override
    public void run() {

        while (true){
            logger.info("活动线程数[{}],最大任务数[{}],历史最大任务数[{}], 总任务数[{}], 已完成任务数[{}], 队列大小[{}],队列剩余容量[{}]",
                    threadPool.getActiveCount(), threadPool.getMaximumPoolSize(),threadPool.getLargestPoolSize(),
                    threadPool.getTaskCount(), threadPool.getCompletedTaskCount(), threadPool.getQueue().size(),threadPool.getQueue().remainingCapacity());
            try {
                TimeUnit.SECONDS.sleep(2);
                threadPool.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

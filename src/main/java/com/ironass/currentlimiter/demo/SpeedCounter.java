package com.ironass.currentlimiter.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 在一段时间间隔内（时间窗口内），处理请求的最大数量固定，超过部分不做处理
 *
 * @author lixin
 * @date 2019-02-11 16:36
 **/
public class SpeedCounter {

    /**
     * 起始时间
     */
    private static long startTime = System.currentTimeMillis();

    /**
     * 时间间隔
     */
    private static long inteval = 10;

    /**
     * 每秒限制数量
     */
    private long maxPermits = 1000;


    /**
     * 当前数量
     */
    private static AtomicLong currentCounts = new AtomicLong(0);

    private static boolean isAccess(int taskId, int nth) {
        long nowTime = System.currentTimeMillis();
        if (nowTime < startTime + inteval) {
            long oldValue;
            long newValue;
            do {
                oldValue = currentCounts.get();
                newValue = oldValue + 1;
            } while (!currentCounts.compareAndSet(oldValue, oldValue + 1));


            if (newValue <= currentCounts.get()) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } else {
            synchronized (SpeedCounter.class) {
                if (nowTime > startTime + inteval) {
                    //双重检查
                    currentCounts.set(0);
                    startTime = nowTime;
                }
            }
            return Boolean.TRUE;
        }

    }


    public static void main(String[] args) {

        for (int i = 0; i <= 100; i++) {
            executeTask(i);
        }
    }

    private static void executeTask(int taskId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= 100; i++) {
                        long cnt;
                        if (isAccess(taskId, i)) {
                            System.out.println("任务ID" + taskId + "任务号" + i + "执行成功");
                        } else {
                            System.out.println("任务ID" + taskId + "任务号" + i + "执行失败-----------");
                        }
                    }

                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

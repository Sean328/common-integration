package com.ironass.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author lixin
 * @date 2019-01-29 17:47
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //资源最多可被3个线程并发访问
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 20; i++) {
            final int threadnum = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("current thread" + Thread.currentThread().getName());
                        //获取许可
                        semaphore.acquire(1);
                        test(threadnum);
                        //释放许可
                        semaphore.release(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        //如果不shutdown工程不会结束
        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()) + "  method run " + Thread.currentThread().getName());
        Thread.sleep(1000);
    }

}

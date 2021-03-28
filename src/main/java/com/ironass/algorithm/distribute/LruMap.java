package com.ironass.algorithm.distribute;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LruMap {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(false);

        boolean lock1 = lock.tryLock();
        System.out.println(lock1);
        TimeUnit.SECONDS.sleep(5);

        boolean lock2 = lock.tryLock();
        System.out.println(lock2);

        lock.unlock();
    }
}

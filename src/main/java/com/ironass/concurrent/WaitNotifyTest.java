package com.ironass.concurrent;

import java.time.LocalDate;
import java.util.concurrent.Executors;

/**
 * @author lixin
 * @date 2019-01-17 21:43
 **/
public class WaitNotifyTest {

    private Object commonObject = new Object();

    public static void main(String[] args) {

        WaitNotifyTest test = new WaitNotifyTest();
        test.asyncProcess();

        System.out.println(LocalDate.now().toString());

    }

    private void asyncProcess() {

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                section1();
                synchronized (commonObject) {
                    System.out.println("task1获取到了锁");
                    commonObject.notify();
                }
            }

            private void section1() {
                System.out.println("task1 执行");
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (commonObject) {
                        System.out.println("task2获取到了锁");
                        //执行到wait()方法后会释放锁
                        commonObject.wait();
                    }

                    section2();

                    synchronized (commonObject){
                        System.out.println("task2 执行完毕 再次获取到了锁");
                        commonObject.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private void section2() {
                System.out.println("task2 执行");
            }
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (commonObject) {
                        System.out.println("task3获取到了锁");
                        commonObject.wait();
                    }
                    section3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private void section3() {
                System.out.println("task3 执行");
            }
        };

        Executors.defaultThreadFactory().newThread(runnable3).start();
        Executors.defaultThreadFactory().newThread(runnable2).start();
        Executors.defaultThreadFactory().newThread(runnable1).start();

    }


}

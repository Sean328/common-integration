package com.ironass.concurrent;

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

    }

    private void asyncProcess(){

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                section1();
                commonObject.notify();
            }
            private void section1() {
                System.out.println("task1 执行");
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    commonObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                section2();
            }
            private void section2() {
                System.out.println("task2 执行");
            }
        };

        Executors.defaultThreadFactory().newThread(runnable2).start();
        Executors.defaultThreadFactory().newThread(runnable1).start();
    }



}

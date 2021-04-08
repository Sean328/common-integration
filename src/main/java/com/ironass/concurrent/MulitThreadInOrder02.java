package com.ironass.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MulitThreadInOrder02 {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private void printA() {
        lock.lock();
        try {
            if (flag != 1) {
                conditionA.await();
            }
            System.out.println("A");
            flag = 2;
            conditionB.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    private void printB() {
        lock.lock();
        try {
            if (flag != 2) {
                conditionB.await();
            }
            System.out.println("B");
            flag = 3;
            conditionC.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    private void printC() {
        lock.lock();
        try {
            if (flag != 3) {
                conditionC.await();
            }
            System.out.println("C");
            flag = 1;
            conditionA.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        MulitThreadInOrder02 mulitThreadInOrder = new MulitThreadInOrder02();
        Thread a = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                mulitThreadInOrder.printA();
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                mulitThreadInOrder.printB();
            }
        });

        Thread c = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                mulitThreadInOrder.printC();
            }
        });


        a.start();
        b.start();
        c.start();
    }
}

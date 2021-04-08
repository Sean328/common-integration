package com.ironass.concurrent;

public class MulitThreadInOrder01 {

    private int flag = 1;

    private synchronized  void printA() {
        while (flag != 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("A");
        flag = 2;
        this.notifyAll();
    }

    private synchronized  void printB()  {
        while (flag != 2){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        flag = 3;
        this.notifyAll();
    }

    private synchronized  void printC()  {
        while (flag != 3){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C");
        flag = 1;
        this.notifyAll();
    }

    public static void main(String[] args) {
        MulitThreadInOrder01 mulitThreadInOrder01 = new MulitThreadInOrder01();
            Thread a = new Thread(() -> {
                for(int i=0;i<5; i++){
                    mulitThreadInOrder01.printA();
                }
            });

        Thread b = new Thread(() -> {
            for(int i=0;i<5; i++){
                mulitThreadInOrder01.printB();
            }
        });

        Thread c = new Thread(() -> {
            for(int i=0;i<5; i++){
                mulitThreadInOrder01.printC();
            }
        });


        a.start();
        b.start();
        c.start();
    }
}


package com.ironass.concurrent;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

/**
 * @author lixin
 * @date 2019-05-20 19:23
 **/
public class CompleteFutureMain {

    /**
     * 自定义一个线程的executor
     */
    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"custom-executor-"+count++);
        }
    });


    public static void main(String[] args) {

//        completedFutureExample();
//        runAsyncExample();
//        thenApplyExample();
//        thenApplyAsyncExample();
//        thenApplyAsyncWithExecutorExample();
//        thenAcceptExample();
        thenAcceptAsyncExample();
    }

    /**
     * 任务处理完之后异步执行thenAccept使用结果进行其它操作
     */
    static void thenAcceptAsyncExample(){
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s-> result.append(s),executor);
        cf.join();
        assertFirst(result.length()>0);
        System.out.println(result);
    }

    /**
     * 消费前一阶段的结果，并且不提供返回值。传入类型是Consumer类型的，返回为void
     * 其它跟thenAppl()方法一样。
     */
    static void thenAcceptExample(){
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept messaage").thenAccept(s -> result.append(s));
        assertFirst(result.length() > 0);
        System.out.println(result);
    }


    /**
     * 使用了自定义的executor来异步执行thenApply方法，这将会使用一个用户线程而不是守护线程来执行
     * 注：这个方法一样是传入Function类型的，是一个有返回值的函数类型
     */
    static void thenApplyAsyncWithExecutorExample(){
        CompletableFuture cf = CompletableFuture.completedFuture("messaage").thenApplyAsync(s ->{
            assertFirst(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertIfDeamonThread(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        },executor);

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE",cf.join());
    }

    /**
     * thenApplyAsync()执行完毕异步执行一个函数，注意此时不指定executor的情况下依旧是用的守护线程
     * cf.join() 可以让当前线程阻塞至知道cf异步线程执行完毕
     * 注：这个方法一样是传入Function类型的，是一个有返回值的函数类型
     */
    static void thenApplyAsyncExample(){
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertIfDeamonThread(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        //注意下面cf.join(),该方法不止会让当前线程等待cf线程执行完，而且还会返回cf线程的执行结果
        assertEquals("MESSAGE",cf.join());
        System.out.println(cf.toString());
    }

    /**
     * 任务执行完毕后一个阶段应用函数(注意这里就是用户线程了)，注意这个方法传入类型是Function 类型的即是一个有返回值的函数
     * thenApply()方法是同步执行的，会阻塞主线程(也就是当前线程)，只有执行完毕getNow()方法才会执行
     */
    static void thenApplyExample(){
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertIfDeamonThread(Thread.currentThread().isDaemon());
            System.out.println(s);
            randomSleep();
            return s.toUpperCase();
        });
        assertEquals("MESSAGE",cf.getNow(null));
        System.out.println(cf.toString());
    }

    /**
     * 异步执行，默认是创建一个守护线程去执行的(没有指定Executor的情况下，指定了就会使用用户线程了)
     */
    static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertIfDeamonThread(Thread.currentThread().isDaemon());
            randomSleep();
        });

        assertFirst(cf.isDone());
        sleepEnough();
        assertSecond(cf.isDone());
        System.out.println(cf.toString());
    }

    /**
     * 使用CompletableFuture同步执行一个线程
     */
    static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertFirst(cf.isDone());
        assertEquals("message", cf.getNow(null));

        System.out.println(cf.toString());
    }

    private static void sleepEnough() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(200, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void assertSecond(boolean flag) {
        System.out.println("assert sencond ："+flag);
    }

    private static void assertThird(boolean flag) {
        System.out.println("assert third ："+flag);
    }

    private static void assertIfDeamonThread(boolean flag) {
        System.out.println("assert if deamon thread ："+flag);
    }


    private static void assertEquals(String message, Object now) {
        System.out.println("assert equals: "+message.equals(now.toString()));
    }

    private static void assertFirst(boolean flag) {
        System.out.println("assert first: "+flag);
    }

    private static void assertNull(Object object) {
        System.out.println("assert null: "+(null == object));
    }


}

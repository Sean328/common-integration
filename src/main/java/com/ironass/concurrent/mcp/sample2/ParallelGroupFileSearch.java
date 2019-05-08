package com.ironass.concurrent.mcp.sample2;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lixin
 * @date 2019-04-25 17:01
 **/
public class ParallelGroupFileSearch {

    public static void searchFiles(File file, String fileName, Result parallelResult) {
        ConcurrentLinkedQueue<File> directories = new ConcurrentLinkedQueue<>();
        File[] contents = file.listFiles();
        for (File content : contents) {
            if (content.isDirectory()) {
                directories.add(content);
            }
        }

        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcesssor is : " + numThreads);
        Thread[] threads = new Thread[numThreads];
        ParallelGroupFileTask[] tasks = new ParallelGroupFileTask[numThreads];
        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new ParallelGroupFileTask(fileName, parallelResult, directories);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        boolean finish = false;
        int numFinished = 0;
        while (!finish) {
            numFinished = 0;
            for (int i = 0; i < threads.length; i++) {
                if (threads[i].getState() == Thread.State.TERMINATED) {
                    numFinished++;
                    if (tasks[i].getFound()) {
                        finish = true;
                    }
                }
            }

            if (numFinished == threads.length) {
                finish = true;
            }
        }
        if (numFinished != threads.length) {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }
    }

}

package com.ironass.concurrent.mcp.sample3.parallel;

import com.ironass.concurrent.mcp.sample3.dmain.Distance;
import com.ironass.concurrent.mcp.sample3.dmain.Sample;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 细粒度并发操作
 *
 * @author lixin
 * @date 2019-05-05 21:27
 **/
public class KnnClassifierParallelIndividual {

    /**
     * Train data
     */
    private final List<? extends Sample> dataSet;

    /**
     * K parameter
     */
    private final int k;

    /**
     * Executor to
     */
    private final ThreadPoolExecutor executor;

    /**
     * Number of threads the executor will use internally
     */
    private final int numThreads;

    /**
     * Mark that indicates if we use the serial or parallel sorting
     */
    private boolean parallelSort;

    /**
     * Constructor of the class. Initializes the internal parameters
     * @param dataSet Train data sest
     * @param k K parameter
     * @param factor Factor of increment of the internal number of cores
     * @param parallelSort Mark that indicates if we use the serial or parallel sorting
     */
    public KnnClassifierParallelIndividual(List<? extends Sample> dataSet, int k, int factor, boolean parallelSort) {
        this.dataSet=dataSet;
        this.k=k;
        numThreads=factor*(Runtime.getRuntime().availableProcessors());
        executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        this.parallelSort=parallelSort;
    }

    /**
     * Method that classifies and example
     * @param example Example to classify
     * @return The tag or class of the example
     * @throws Exception Exception is something goes wrong
     */
    public String classify (Sample example) throws Exception {

        Distance[] distances=new Distance[dataSet.size()];
        CountDownLatch endController=new CountDownLatch(dataSet.size());

        int index=0;
        for (Sample localExample : dataSet) {
            IndividualDistanceTask task=new IndividualDistanceTask(distances, index, localExample, example, endController);
            executor.execute(task);
            index++;
        }
        endController.await();

        if (parallelSort) {
            Arrays.parallelSort(distances);
        } else {
            Arrays.sort(distances);
        }

        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a, b) -> a+b);
        }

        return Collections.max(results.entrySet(),
                Map.Entry.comparingByValue()).getKey();


    }

    /**
     * Method that finish the execution of the executor
     */
    public void destroy() {
        executor.shutdown();
    }
}

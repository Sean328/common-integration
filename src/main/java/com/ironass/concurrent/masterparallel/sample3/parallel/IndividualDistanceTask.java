package com.ironass.concurrent.masterparallel.sample3.parallel;

import com.ironass.concurrent.masterparallel.sample3.EuclideanDistanceCalculator;
import com.ironass.concurrent.masterparallel.sample3.dmain.Distance;
import com.ironass.concurrent.masterparallel.sample3.dmain.Sample;

import java.util.concurrent.CountDownLatch;

/**
 * 细粒度并发
 *
 * @author lixin
 * @date 2019-05-05 21:28
 **/
public class IndividualDistanceTask implements Runnable{

    private final Distance[] distances;

    private final int index;

    private final Sample localExample;

    private final Sample example;

    private final CountDownLatch endControler;

    public IndividualDistanceTask(Distance[] distances, int index, Sample localExample, Sample example,
                                  CountDownLatch endControler) {
        this.distances = distances;
        this.index = index;
        this.localExample = localExample;
        this.example = example;
        this.endControler = endControler;
    }

    @Override
    public void run() {
        distances[index] = new Distance();
        distances[index].setIndex(index);
        distances[index].setDistance(EuclideanDistanceCalculator.calculate(localExample, example));
        endControler.countDown();
    }
}

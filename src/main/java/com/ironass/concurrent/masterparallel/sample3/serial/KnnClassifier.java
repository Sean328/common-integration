package com.ironass.concurrent.masterparallel.sample3.serial;


import com.ironass.concurrent.masterparallel.sample3.EuclideanDistanceCalculator;
import com.ironass.concurrent.masterparallel.sample3.dmain.Distance;
import com.ironass.concurrent.masterparallel.sample3.dmain.Sample;

import java.util.*;

/**
 * k-最近邻算法的串行版本
 * @author lixin
 * @date 2019-04-30 19:06
 **/
public class KnnClassifier {

    private final List <? extends Sample>dataSet;
    private int k;
    public KnnClassifier(List<? extends Sample> dataSet, int k) {
        this.dataSet=dataSet;
        this.k=k;
    }

    public String classify (Sample example) {
        Distance[] distances=new Distance[dataSet.size()];
        int index=0;
        for (Sample localExample : dataSet) {
            distances[index]=new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(localExample, example));
            index++;
        }
        Arrays.sort(distances);
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a, b) ->a+b);
        }
        return Collections.max(results.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }

}

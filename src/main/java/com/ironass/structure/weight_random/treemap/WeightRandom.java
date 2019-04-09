package com.ironass.structure.weight_random.treemap;

import com.google.common.base.Preconditions;
import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 概述：　　平时，经常会遇到权重随机算法，从不同权重的N个元素中随机选择一个，并使得总体选择结果是按照权重分布的。如广告投放、负载均衡等。
 *
 * 　　如有4个元素A、B、C、D，权重分别为1、2、3、4，随机结果中A:B:C:D的比例要为1:2:3:4。
 *
 * 　　总体思路：累加每个元素的权重A(1)-B(3)-C(6)-D(10)，则4个元素的的权重管辖区间分别为[0,1)、[1,3)、[3,6)、[6,10)。然后随机出一个[0,10)之间的随机数。落在哪个区间，则该区间之后的元素即为按权重命中的元素。
 *
 * 　　实现方法：
 *
 * 利用TreeMap，则构造出的一个树为:
 *
 *       　　　　B(3)
 *       　　　 /   \
 *           /     \
 *       A(1)     D(10)
 *       /
 *      /
 *   C(6)
 *
 * @author lixin
 * @date 2019-04-09 11:53
 **/
public class WeightRandom<K,V extends Number> {
    private TreeMap<Double,K> weightMap = new TreeMap<Double,K>();
    private static final Logger log = LoggerFactory.getLogger(WeightRandom.class);

    public WeightRandom(List<Pair<K,V>> list){

        Preconditions.checkNotNull(list,"list can not be null!");
        for(Pair<K,V> pair : list){
            //将取得的value统一转换为value
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey().doubleValue();
            //权重累加
            this.weightMap.put(pair.getValue().doubleValue() + lastWeight,pair.getKey());
        }

    }

    public K random(){
        double randomWeight = this.weightMap.lastKey() * Math.random();
        SortedMap<Double,K> tailMap = this.weightMap.tailMap(randomWeight,false);
        return this.weightMap.get(tailMap.firstKey());
    }

}

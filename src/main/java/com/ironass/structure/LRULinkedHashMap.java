package com.ironass.structure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用linkedList来实现LRU算法（Last Recently Used）
 *
 * @author lixin
 * @date 2019-03-21 16:58
 **/
/*LRU是Least Recently Used 近期最少使用算法。
 *通过HashLiekedMap实现LRU的算法的关键是，如果map里面的元素个数大于了缓存最大容量，则删除链表头元素
 */
/*public LinkedHashMap(int initialCapacity,float loadFactor,boolean accessOrder)
 *LRU参数参数：
 *initialCapacity - 初始容量。
 *loadFactor - 加载因子（需要是按该因子扩充容量）。
 *accessOrder - 排序模式( true) - 对于访问顺序（get一个元素后，这个元素被加到最后，使用了LRU  最近最少被使用的调度算法），对于插入顺序，则为 false,可以不断加入元素。
 */
/*相关思路介绍：
 * 当有一个新的元素加入到链表里面时，程序会调用LinkedHahMap类中Entry的addEntry方法，
 *而该方法又会 会调用removeEldestEntry方法，这里就是实现LRU元素过期机制的地方，
 * 默认的情况下removeEldestEntry方法只返回false，表示可以一直表链表里面增加元素，在这个里  *修改一下就好了。
 *
 */
/*
测试数据：
5
11
4 7 0 7 1 0 1 2 1 2 6
*/

public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V>{
    private static final long serialVersionUID = -5874744282989801802L;
    //初始内存容量
    private int capacity;

    //构造方法，传入一个参数
    LRULinkedHashMap(int capacity){
        //调用LinkedHashMap，传入参数
        super(16,0.75f,true);
        //传递指定的最大内存容量
        this.capacity=capacity;
    }
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest){
        //，每加入一个元素，就判断是size是否超过了已定的容量
        System.out.println("此时的size大小="+size());
        if((size()>capacity)) {
            System.out.println("超出已定的内存容量，把链表顶端元素移除："+eldest.getValue());
        }
        return size()>capacity;
    }

    public static void main(String[] args) throws Exception{
        //accessOrder，及访问顺序， true表示开启最近访问顺序，false表示插入顺序
        Map<String,Integer> map=new LinkedHashMap<>(10,0.75f,true);

        map.put("0",0);
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put("5",5);
        map.put("6",6);
        map.put("7",7);


        map.get("6");
        map.get("4");


        for(Map.Entry<String, Integer> entry: map.entrySet()){
            System.out.println(entry.getValue());
        }
    }
}
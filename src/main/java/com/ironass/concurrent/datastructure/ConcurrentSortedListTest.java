package com.ironass.concurrent.datastructure;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 * @author lixin
 * @date 2019-01-31 14:25
 **/
public class ConcurrentSortedListTest {

    public static void main(String[] args) {
        ConcurrentSortedList list = new ConcurrentSortedList();
        list.insert(4);
        list.insert(0);
        list.insert(3);
        list.insert(6);
        list.insert(9);

        System.out.println(list.size());

        for(int i =0 ;i<100;i++){
            final Random random = new Random();

            Runnable task1 = new Runnable() {
                @Override
                public void run() {
                    list.insert(random.nextInt(2000) + 1000);
                }
            };
            Executors.defaultThreadFactory().newThread(task1).start();
        }



    }

}

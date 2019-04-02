package com.ironass.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 * @date 2019-04-01 16:20
 **/
public class ConsistHashTest {

    public static void main(String[] args) {
        String ip1 = "10.209.26.115";
        String ip2 = "10.209.240.209";
        String ip3 = "10.209.24.202";
        String ip4 = "10.209.14.146";
        List<String> nodes = new ArrayList<>();
        nodes.add(ip1);
        nodes.add(ip2);
        nodes.add(ip3);
        nodes.add(ip4);

        ConsistHash consistHash = new ConsistHash(2,nodes);

        Long hashKey = ConsistHash.md5HashingAlg(ip2);

        String test1 = "lixin";
        String test2 = "sean";
        String test3 = "xxl";
        String test4 = "nibaba";
        String test5 = "dada";

        Object obj1 = consistHash.get(test1);
        Object obj2 = consistHash.get(test2);
        Object obj3 = consistHash.get(test3);
        Object obj4 = consistHash.get(test4);
        Object obj5 = consistHash.get(test5);


        System.out.println(consistHash.get(ip2));


    }

}

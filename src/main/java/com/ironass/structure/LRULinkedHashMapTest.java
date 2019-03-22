package com.ironass.structure;

import java.util.LinkedHashMap;
import java.util.Map;


public class LRULinkedHashMapTest{
    public static void main(String[] args) {
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

        map.entrySet().stream().forEach(p -> {
            System.out.println(p.getValue());
        });

    }
}

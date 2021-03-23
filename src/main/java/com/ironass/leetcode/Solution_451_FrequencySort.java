package com.ironass.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Solution_451_FrequencySort {

    public static  String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for(int i =0; i< s.length(); i++){
            char key = s.charAt(i);
            map.put(key,map.getOrDefault(key, 0)+1);
        }

        List<Map.Entry<Character,Integer>> entryList = map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue)).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for(int j = entryList.size()-1; j >= 0; j--){
            Map.Entry<Character, Integer> entry = entryList.get(j);
            if(entry.getValue() > 0){
                for(int i = 0; i< entry.getValue(); i++){
                    sb.append(entry.getKey());
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s  = "cacacab";

//        char [] cha = new char[10];
//        System.out.println(cha[0]);
//        System.out.println(cha[0] == '\0');
        String result = frequencySort(s);
        System.out.println(result);
    }
}

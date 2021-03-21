package com.ironass.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_03_LengthOfLongestSubstring {

    /**
     * n^2的时间复杂度
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s == "") {
            return 0;
        }

        Map<Character, Integer> positionMap = new HashMap<>();
        int tmpLength = 0;
        int maxLength = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            for (int j = i; j <= s.length() - 1; j++) {
                Character c = s.charAt(j);
                if (!positionMap.containsKey(c)) {
                    positionMap.put(c, j);
                    tmpLength = positionMap.size();
                    if (tmpLength > maxLength) {
                        maxLength = tmpLength;
                    }
                } else {
                    positionMap.clear();
                    tmpLength = 0;
                    break;
                }
            }
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        int tmpLength = 0;
        Map<Character, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!positionMap.containsKey(c)) {
                positionMap.put(c, i);
                tmpLength = positionMap.size();
                if (tmpLength > maxLength) {
                    maxLength = tmpLength;
                }
            } else {
                int value = positionMap.get(c);
                positionMap.clear();
                i = value;
            }
        }

        return maxLength;
    }

    public  static int lengthOfLongestSubstring3(String s){
        int n = s.length();
        int maxLength = 0;//记录最长子串的长度
        int end=0,start=0;//记录开始和结尾的下标
        Set<Character> set=new HashSet<>();//使用set容器不重复
        while(start<n && end<n){
            if(set.contains(s.charAt(end))){//如果窗口右侧的字符已经存在
                set.remove(s.charAt(start++));//左侧窗口边界向右
            }else{
                set.add(s.charAt(end++));//如果窗口中无重复，窗口右侧向右滑动
                maxLength=Math.max(maxLength,end-start);//同时记录当前最大长度
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abdca";
        int i = lengthOfLongestSubstring3(s);
        System.out.println(i);
    }
}

package com.ironass.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 * @date 2019-01-30 18:50
 **/
public class HashArithmeticTest {

    public static void main(String[] args) {

        Object object = new Object();
        object.hashCode();

        String st = "sgg";

        System.out.println(st.hashCode());
        System.out.println(hashCode(st));

        List<String> arr = new ArrayList<>();
        arr.hashCode();
    }

    private static long hashCode(String str){
        char [] value = str.toCharArray();
        int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
        }
        return h;
    }
}

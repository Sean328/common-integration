package com.ironass.sword2offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringCaseTransform {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String inputStr=scan.nextLine();
        String result=new StringCaseTransform().caseTransform(inputStr);
        System.out.println(result);
    }


    private static String caseTransform(String s){
        int position = 0;
        List<String> stringList = new ArrayList<>();
        for(int i = 1; i< s.length(); i++){
            Character c = s.charAt(i);
            if(Character.isUpperCase(c)){
                stringList.add(s.substring(position, i));
                position = i;
            } if(c == '_' || c=='-'){
                stringList.add(s.substring(position, i));
                i++;
                position = i;
            }
        }
        stringList.add(s.substring(position, s.length()));
        String str1 = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        for(int i = 0 ; i< stringList.size(); i++){
            String subStr = stringList.get(i);
            str1 += Character.toUpperCase(subStr.charAt(0))+subStr.substring(1,subStr.length());
            if(i == 0){
                str2 += Character.toLowerCase(subStr.charAt(0))+subStr.substring(1,subStr.length());;
            } else {
                str2 += Character.toUpperCase(subStr.charAt(0))+subStr.substring(1,subStr.length());
            }
            str3 += Character.toLowerCase(subStr.charAt(0))+subStr.substring(1,subStr.length());
            str4 += Character.toLowerCase(subStr.charAt(0))+subStr.substring(1,subStr.length());
            if(i != stringList.size()-1){
                str3 += "_";
                str4 += "-";
            }
        }
        return str1+" "+str2+" "+str3+" "+str4;
    }
}

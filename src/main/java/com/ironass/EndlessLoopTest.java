package com.ironass;

/**
 * 一个int类型遍历的死循环条件
 * 当i小于一个long类型时，如果此实long类型已经超过了int所能表示的最大值，此实i++的时候值将发生反转，将会变成int类型的最小负数，并逐渐递加。
 * 因此，在写for循环使用int作为基准时，一定要注意累加值能超过Integer的最大值。
 *
 * @author lixin
 * @date 2019-05-22 15:10
 **/
public class EndlessLoopTest {

    public static void main(String[] args) {
        long totalPage = Long.MAX_VALUE;
        for(int i = 0 ;i<totalPage;i++){
            if(i<0){
                System.out.println(i);
            }
        }
    }
}

package com.ironass.concurrent.mcp.sample1;

import org.springframework.util.StopWatch;

import java.util.Date;

/**
 * @author lixin
 * @date 2019-04-25 15:48
 **/
public class TestMain {

    public static void main(String[] args) {
        double matrix1[][] = MatrixGenerator.generate(2000, 2000);
        double matrix2[][] = MatrixGenerator.generate(2000, 2000);
        double resultSerial[][]= new double[matrix1.length][matrix2[0].length];
        Date start=new Date();
        SerialMultiplier.multiply(matrix1, matrix2, resultSerial);
        Date end=new Date();
        System.out.printf("Serial: %d%n",end.getTime()-start.getTime());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("parralle1");
        double parallel1[][]= new double[matrix1.length] [matrix2[0].length];
        ParallelIndividualMultiplier.multiply(matrix1,matrix2,parallel1);
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());


        stopWatch.start("parralle2");
        double parallel2[][]= new double[matrix1.length] [matrix2[0].length];
        ParallelRowMultiplier.multiply(matrix1,matrix2,parallel2);
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());

    }
}

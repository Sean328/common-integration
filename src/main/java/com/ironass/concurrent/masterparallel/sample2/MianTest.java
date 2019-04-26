package com.ironass.concurrent.masterparallel.sample2;

import org.springframework.util.StopWatch;

import java.io.File;

/**
 * @author lixin
 * @date 2019-04-25 17:21
 **/
public class MianTest {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        //grs-api-1.3.0-SNAPSHOT.jar
        String fileName = "VestaServer.java";
        String path = "D:\\";

        File file = new File(path);
        Result result = new Result();

        stopWatch.start("serial");
        SerialFileSearch.searchFiles(file, fileName, result);
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());


        stopWatch.start("parallel");
        ParallelGroupFileSearch.searchFiles(file, fileName, result);
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }

}

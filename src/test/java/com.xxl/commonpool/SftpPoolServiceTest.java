package com.xxl.commonpool;

import com.xxl.BaseSpringTest;
import com.xxl.common_pool.SftpPoolService;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lixin
 * @date 2019-01-21 17:04
 **/
public class SftpPoolServiceTest extends BaseSpringTest {
    @Resource
    public SftpPoolService sftpPoolService;

    @Test
    public void initSftpPool() throws FileNotFoundException, InterruptedException {

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                SftpPoolService sftpInnerPool = sftpPoolService;
                while (true){
                    System.out.println("idle number : "+sftpInnerPool.getSftpPool().getNumIdle());
                    System.out.println("active number : "+sftpInnerPool.getSftpPool().getNumActive());
                    try {
                        TimeUnit.MILLISECONDS.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Executors.defaultThreadFactory().newThread(runnableTask).start();

        byte[] bytes  = new byte[]{1,2,3,4,5};

        InputStream inputStream = new ByteArrayInputStream(bytes);

        for (int i = 0; i <= 100; i++) {

            String path = "/upload/pool/";
            String fileName = "file"+i;

            sftpPoolService.uploadFile(inputStream,fileName,path);

        }

        InputStream inputStream2 = new ByteArrayInputStream(bytes);

        for (int i = 0; i <= 100; i++) {

            String path = "/upload/pool2/";
            String fileName = "file"+i;

            sftpPoolService.uploadFile(inputStream2,fileName,path);
        }



        TimeUnit.SECONDS.sleep(300);
    }

}

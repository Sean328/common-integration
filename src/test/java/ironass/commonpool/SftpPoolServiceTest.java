package ironass.commonpool;

import ironass.BaseSpringTest;
import ironass.common_pool.SftpDomain;
import ironass.common_pool.SftpPoolService;
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
                while (true) {
                    System.out.println("idle number : " + sftpInnerPool.getSftpPool().getNumIdle());
                    System.out.println("active number : " + sftpInnerPool.getSftpPool().getNumActive());
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Executors.defaultThreadFactory().newThread(runnableTask).start();

        byte[] bytes = new byte[]{1, 2, 3, 4, 5};

        InputStream inputStream = new ByteArrayInputStream(bytes);

        for (int i = 0; i <= 100; i++) {
            final String path = "/upload/pool/";
            String fileName = "file" + i + "x";

            SftpDomain domain = new SftpDomain();
            domain.setFileName(fileName);
            domain.setPath(path);
            domain.setInputStream(inputStream);


            new Thread(() -> sftpPoolService.uploadFile(domain)).start();
        }


        TimeUnit.SECONDS.sleep(300);
    }


}

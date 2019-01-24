package ironass.common_pool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author lixin
 * @date 2019-01-21 14:42
 **/
public class SftpPoolService extends SftpClient{

    private static final long serialVersionUID = 6054548928347779077L;

    private final Logger logger = LoggerFactory.getLogger(SftpPoolService.class);

    private SftpParam sftpParam = null;

    private ObjectPool<SftpClient> sftpPool;


    public SftpPoolService(){

    }

    public SftpPoolService(SftpParam sftpParam, GenericObjectPoolConfig poolConfig, AbandonedConfig abandonedConfig) {
        this.setSftpParam(sftpParam);
        this.sftpPool = new GenericObjectPool<>(new SftpPoolFactory(sftpParam), poolConfig, abandonedConfig);
    }


    public void uploadFile(SftpDomain domain) {

        logger.info("开始上传： {}",domain.getFileName());
        SftpClient client = null;
        try {
            client = sftpPool.borrowObject();
            client.uploadWithAbsloutPath(domain.getInputStream(), domain.getFileName(), domain.getPath());
            TimeUnit.MILLISECONDS.sleep(600);
            logger.info("上传成功 : {}",domain.getFileName());
        } catch (Exception e) {
            //失效当前的sftpClient
            if (Objects.nonNull(client)) {
                try {
                    sftpPool.invalidateObject(client);
                } catch (Exception e1) {
                    logger.error("从sftp连接池中获取连接失败：{}", e1);
                }
            }

            //继续抛出异常
//            throw new SftpPoolExecetion(e);
            logger.error("异常文件 ：{}", domain.getFileName(),e);
        } finally {
            // 将当前的sftpClient 归还到线程池中
            logger.info("return this client to pool : {}",domain.getFileName());
            if (client != null) {
                try {
                    sftpPool.returnObject(client);
                } catch (Exception e) {
                    logger.error("归还连接至sftp连接池中异常，{}",client, e);
                }
            }
        }

    }




    @Override
    public String toString() {
        return super.toString();
    }

    public ObjectPool<SftpClient> getSftpPool() {
        return sftpPool;
    }

//    protected synchronized SftpClient borrowFromPool(){
//        try {
//            return sftpPool.borrowObject();
//        }catch (Exception e){
//            logger.error("获取连接失败 : {}",e);
//            return null;
//        }
//    }
//
//    protected synchronized void returnClient(SftpClient client){
//        try {
//            sftpPool.returnObject(client);
//        }catch (Exception e){
//            logger.error("归还client至连接池失败 : {}",client,e);
//        }
//    }

}

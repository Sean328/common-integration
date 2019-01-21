package com.xxl.common_pool;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.xxl.integration.base.BaseDomain;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author lixin
 * @date 2019-01-21 14:42
 **/
public class SftpPoolService extends BaseDomain implements Closeable {

    private static final long serialVersionUID = 6054548928347779077L;

    private final Logger logger = LoggerFactory.getLogger(SftpPoolService.class);
    private Session session = null;
    private ChannelSftp channel = null;
    private SftpParam sftpParam = null;

    private ObjectPool<SftpClient> sftpPool;


    public SftpPoolService(SftpParam sftpParam, GenericObjectPoolConfig poolConfig, AbandonedConfig abandonedConfig) {
        this.sftpParam = sftpParam;
        this.sftpPool = new GenericObjectPool<>(new SftpPoolFactory(sftpParam), poolConfig, abandonedConfig);
    }


    public void uploadFile(InputStream origin, String fileName, String filePath) {

//        logger.info("the number of instances currently idle in this pool is {}", sftpPool.getNumIdle());
//        logger.info("the number of instances currently borrowed from this pool is {}", sftpPool.getNumActive());

        SftpClient client = null;
        try {
            client = sftpPool.borrowObject();
            client.uploadWithAbsloutPath(origin, fileName, filePath);
            logger.info("上传成功");
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
            throw new SftpPoolExeception(e);
        } finally {
            // 将当前的sftpClient 归还到线程池中
            if (client != null) {
                try {
                    sftpPool.returnObject(client);
                } catch (Exception e) {
                    logger.error("归还连接至sftp连接池中异常，{}", e);
                }
            }
        }

    }


    @Override
    public void close() {

        try {
            if (channel != null) {
                channel.disconnect();
                logger.info("sftp channel disconnected");
            }

            if (session != null) {
                session.disconnect();
                logger.info("sftp session disconnected");
            }
        } catch (Exception e) {
            logger.warn("sftp channel closed error!");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ObjectPool<SftpClient> getSftpPool() {
        return sftpPool;
    }

    public SftpPoolService setSftpPool(ObjectPool<SftpClient> sftpPool) {
        this.sftpPool = sftpPool;
        return this;
    }
}

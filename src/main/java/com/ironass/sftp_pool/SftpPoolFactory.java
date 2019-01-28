package com.ironass.sftp_pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixin
 * @date 2019-01-21 14:57
 **/
public class SftpPoolFactory extends BasePooledObjectFactory<SftpClient> {

    private Logger logger = LoggerFactory.getLogger(SftpPoolFactory.class);

    protected SftpParam sftpParam;


    public SftpPoolFactory(SftpParam sftpParam) {
        this.sftpParam = sftpParam;
    }

    @Override
    public SftpClient create() throws Exception {
        logger.info("sftp pool factory created , sftp info:{}", sftpParam);

        SftpClient sftpClient = new SftpClient(sftpParam);
        try {
            sftpClient.connect();
        } catch (Exception e) {
            logger.error("sftp pool factory init connection failed {}", sftpParam, e);
            sftpClient.close();
            throw e;
        }
        return sftpClient;
    }

    @Override
    public PooledObject<SftpClient> wrap(SftpClient sftpClient) {
        logger.info("sftp pool factory warped, sftp info:{}", sftpParam);

        return new DefaultPooledObject<>(sftpClient);
    }


    @Override
    public void destroyObject(PooledObject<SftpClient> p) throws Exception {
        try {
            logger.info("sftp pool destroy, sftp info:{}", sftpParam);
            p.getObject().close();
        } catch (Exception e) {
            // Errors may happen if returning a broken resource
            logger.warn("sftp pool destoryed error, sftp info:{}", sftpParam);
        }
    }
}

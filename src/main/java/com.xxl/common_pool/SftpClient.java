package com.xxl.common_pool;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @author lixin
 * @date 2019-01-21 16:24
 **/
public class SftpClient implements Closeable {

    private final Logger logger = LoggerFactory.getLogger(SftpClient.class);
    private Session session = null;
    private ChannelSftp channel = null;
    private SftpParam sftpParam;


    public SftpClient(SftpParam sftpParam){
        this.sftpParam = sftpParam;
    }

    public void connect() throws Exception {

        Objects.requireNonNull(sftpParam, "sftp 参数不能为空");

        Properties config = new Properties();
        // 创建JSch对象
        JSch jsch = new JSch();

        // 根据用户名，主机ip，端口获取一个Session对象
        session = jsch.getSession(sftpParam.getUserName(), sftpParam.getHost(), sftpParam.getPort());
        if (!StringUtils.isEmpty(sftpParam.getPassword())) {
            session.setPassword(sftpParam.getPassword());
        }
        config.put("userauth.gssapi-with-mic", "no");
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setTimeout(sftpParam.getTimeOut());
        session.setServerAliveCountMax(sftpParam.getMaxAlive());
        // 通过Session建立链接
        session.connect();
        // 打开SFTP通道
        channel = (ChannelSftp) session.openChannel("sftp");
        // 建立SFTP通道的连接
        channel.connect();
        logger.info("SSH Channel connected.");
    }



    public void uploadWithAbsloutPath(InputStream src, String fileName, String dst) {
        try {
            System.out.println("开始模拟上传了");
            mkdirWithAbsolutePath(dst);
            channel.put(src, dst + fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mkdirWithAbsolutePath(String dst) throws Exception {
        try {
            channel.cd(dst);
        } catch (SftpException e) {
            logger.warn("目标路径不存在,新建路径{}", dst);
            if (dst.startsWith("/")) {
                String[] dirs = dst.substring(1).split("[/\\\\]");
                dirs[0] = "/" + dirs[0];
                for (String dir : dirs) {
                    if (StringUtils.isEmpty(dir)) {
                        continue;
                    }
                    try {
                        channel.cd(dir);
                    } catch (Exception ee) {
                        logger.debug("sftp路径不存在,创建路径: {}", dir);
                        channel.mkdir(dir);
                        channel.cd(dir);
                    }
                }
            } else {
                logger.error("目标路径不正确{}，请提供绝对路径", dst);
                throw new IllegalArgumentException("目标路径" + dst + "不正确，请提供绝对路径");
            }
        }
    }


    @Override
    public void close() throws IOException {
        try {
            if(channel != null){
                channel.disconnect();
                logger.info("sftp channel disconnected");
            }

            if(session != null){
                session.disconnect();
                logger.info("sftp session disconnected");
            }
        }catch (Exception e){
            logger.warn("sftp channel closed error!");
        }
    }
}

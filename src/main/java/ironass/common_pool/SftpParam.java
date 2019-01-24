package ironass.common_pool;

import com.alibaba.fastjson.annotation.JSONField;
import com.xxl.integration.base.BaseDomain;

/**
 * @author lixin
 * @date 2019-01-21 15:13
 **/
public class SftpParam extends BaseDomain {

    private static final long serialVersionUID = -5508560034858022192L;

    private String host;
    private Integer port;
    private String userName;
    @JSONField(serialize = false)
    private String password;
    private Integer timeOut;
    private Integer maxAlive;

    private String filePath;
    private String fileName;

    public String getHost() {
        return host;
    }

    public SftpParam setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public SftpParam setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SftpParam setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SftpParam setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public SftpParam setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public Integer getMaxAlive() {
        return maxAlive;
    }

    public SftpParam setMaxAlive(Integer maxAlive) {
        this.maxAlive = maxAlive;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public SftpParam setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public SftpParam setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}

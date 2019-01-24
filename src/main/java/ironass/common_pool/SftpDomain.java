package ironass.common_pool;

import java.io.InputStream;

/**
 * @author lixin
 * @date 2019-01-22 10:11
 **/
public class SftpDomain{
    InputStream inputStream;
    String path;
    String fileName;

    public InputStream getInputStream() {
        return inputStream;
    }

    public SftpDomain setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public String getPath() {
        return path;
    }

    public SftpDomain setPath(String path) {
        this.path = path;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public SftpDomain setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
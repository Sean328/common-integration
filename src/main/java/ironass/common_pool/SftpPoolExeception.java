package ironass.common_pool;

/**
 * @author lixin
 * @date 2019-01-21 16:43
 **/
public class SftpPoolExeception extends RuntimeException{

    private static final long serialVersionUID = 8275496881643822637L;

    public SftpPoolExeception() {
        super();
    }

    public SftpPoolExeception(String message) {
        super(message);
    }

    public SftpPoolExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public SftpPoolExeception(Throwable cause) {
        super(cause);
    }

    protected SftpPoolExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

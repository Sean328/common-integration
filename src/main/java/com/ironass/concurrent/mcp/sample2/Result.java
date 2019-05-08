package com.ironass.concurrent.mcp.sample2;

/**
 * @author lixin
 * @date 2019-04-25 16:55
 **/
public class Result {

    private String path;
    private Boolean found = Boolean.FALSE;

    public String getPath() {
        return path;
    }

    public Result setPath(String path) {
        this.path = path;
        return this;
    }

    public Boolean isFound() {
        return found;
    }

    public Result setFound(Boolean found) {
        this.found = found;
        return this;
    }
}

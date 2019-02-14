package com.ironass.currentlimiter.domain;

/**
 * @author lixin
 * @date 2019-02-11 15:58
 **/
public class LimiterProperty {

    /**
     * 每秒允许通过的数量
     */
    private Integer permitsPerSecond;
    /**
     * 每个请求
     */
    private Integer permitsPerRequest;

    /**
     *
     */
    private Integer timeoutMills;

    /**
     *
     */
    private String limitType;

    public Integer getPermitsPerSecond() {
        return permitsPerSecond;
    }

    public LimiterProperty setPermitsPerSecond(Integer permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        return this;
    }

    public Integer getPermitsPerRequest() {
        return permitsPerRequest;
    }

    public LimiterProperty setPermitsPerRequest(Integer permitsPerRequest) {
        this.permitsPerRequest = permitsPerRequest;
        return this;
    }

    public Integer getTimeoutMills() {
        return timeoutMills;
    }

    public LimiterProperty setTimeoutMills(Integer timeoutMills) {
        this.timeoutMills = timeoutMills;
        return this;
    }

    public String getLimitType() {
        return limitType;
    }

    public LimiterProperty setLimitType(String limitType) {
        this.limitType = limitType;
        return this;
    }
}

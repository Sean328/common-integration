package com.ironass.concurrent.threadlocal.context;

import com.ironass.base.BaseDomain;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lixin
 * @date 2019-05-16 22:17
 **/
public class RequestContext extends BaseDomain {
    private static final long serialVersionUID = -677485237990574112L;

    //上下文中存放的一些基础属性
    private String userId;

    private String requestNo;

    private String token;

    private String userIp;

    private String mac;

    private String device;

    private String channel;

    private String originIp;

    private String localIp;

    private String hostName;

    private String sessionId;

    private Map<String, Object> additionMap = new HashMap();

    public Map<String, Object> getAdditionMap() {
        return this.additionMap;
    }

    public void setAdditionMap(Map<String, Object> map) {
        this.additionMap = map;
    }

    public RequestContext addAddition(String key, Object value) {
        this.additionMap.put(key, value);
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public RequestContext setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public RequestContext setRequestNo(String requestNo) {
        this.requestNo = requestNo;
        return this;
    }

    public String getToken() {
        return token;
    }

    public RequestContext setToken(String token) {
        this.token = token;
        return this;
    }

    public String getUserIp() {
        return userIp;
    }

    public RequestContext setUserIp(String userIp) {
        this.userIp = userIp;
        return this;
    }

    public String getMac() {
        return mac;
    }

    public RequestContext setMac(String mac) {
        this.mac = mac;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public RequestContext setDevice(String device) {
        this.device = device;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public RequestContext setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getOriginIp() {
        return originIp;
    }

    public RequestContext setOriginIp(String originIp) {
        this.originIp = originIp;
        return this;
    }

    public String getLocalIp() {
        return localIp;
    }

    public RequestContext setLocalIp(String localIp) {
        this.localIp = localIp;
        return this;
    }

    public String getHostName() {
        return hostName;
    }

    public RequestContext setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }
}

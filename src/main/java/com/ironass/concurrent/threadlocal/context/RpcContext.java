package com.ironass.concurrent.threadlocal.context;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模仿dubbo中的RpcContext
 * @author lixin
 * @date 2019-05-17 16:49
 **/
public class RpcContext {

    private static final ThreadLocal<RpcContext> LOCAL  = new ThreadLocal<RpcContext>(){
        @Override
        protected RpcContext initialValue() {
            return new RpcContext();
        }
    };

    private Map<String,Object> additions = new HashMap<String, Object>();
    private List<URL> urls;
    private URL url;
    private String methodName;
    private Class<?>[] parameterTypes;
    private InetSocketAddress localAddress;
    private InetSocketAddress remoteAddress;

    private Object request;
    private Object response;

    public static RpcContext getContext(){
        return LOCAL.get();
    }

    public static void removeContext(){
        LOCAL.remove();
    }

    public Object getRequest() {
        return request;
    }

    public RpcContext setRequest(Object request) {
        this.request = request;
        return this;
    }

    public Object getResponse() {
        return response;
    }

    public RpcContext setResponse(Object response) {
        this.response = response;
        return this;
    }

    public<T> T getRequest(Class<T> tClass){
        if(request != null  && tClass.isAssignableFrom(request.getClass())){
            return (T) request;
        }
        return null;
    }


    public <T> T getResponse(Class<T> tClass){
        if(response != null  && tClass.isAssignableFrom(response.getClass())){
            return (T) response;
        }
        return null;
    }


    public Map<String, Object> getAdditions() {
        return additions;
    }

    public void setAdditions(Map<String, Object> additions) {
        this.additions = additions;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(InetSocketAddress localAddress) {
        this.localAddress = localAddress;
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}

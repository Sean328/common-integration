package com.ironass.concurrent.threadlocal.context;

/**
 * @author lixin
 * @date 2019-05-16 22:19
 **/
public class ContextHolder {

    public static ThreadLocal<RequestContext> threadLocal = new ThreadLocal<RequestContext>();

    public static void set(String key, Object value) {
        RequestContext requestContext = threadLocal.get();
        if (requestContext == null) {
            requestContext = new RequestContext();
            threadLocal.set(requestContext);
        }
        requestContext.addAddition(key, value);
    }

    public static RequestContext get() {
        RequestContext requestContext = threadLocal.get();
        if (requestContext == null) {
            requestContext = new RequestContext();
            threadLocal.set(requestContext);
        }
        return requestContext;
    }

    public static void clear() {
        RequestContext requestContext = threadLocal.get();
        if (requestContext != null) {
            requestContext = null;
        }
    }
}

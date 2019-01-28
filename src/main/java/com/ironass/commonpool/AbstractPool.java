package com.ironass.commonpool;

/**
 * @author lixin
 * @date 2019-01-25 15:38
 **/
public abstract  class AbstractPool <T> implements Pool<T>{

    /**
     * 判断要归还到pool中的instance是否还有效的，如果有效才重新放入pool中，否则直接销毁掉
     * Returns the instance to the pool
     * Before return, the method first validates the instance if is re-usbale and then put it to the pool.
     * If the instance validation failed,destory it and create a new instance .
     *
     * @param t
     */
    @Override
    public void release(T t) {
        if(isValid(t)){
            returnObject(t);
        } else {
            handleInvalidReturn(t);
        }
    }

    /**
     * 验证连接实例是否有效
     * @param t
     * @return
     */
    protected abstract boolean isValid(T t);

    /**
     * 将有效连接归还到池中
     * @param t
     */
    protected abstract void returnObject(T t);

    /**
     * 处理无效连接
     * @param t
     */
    protected abstract void handleInvalidReturn(T t);
}

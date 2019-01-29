package com.ironass.commonpool;

/**
 * Represents a cached pool of objects
 *
 * @param <T>  type of the object pool
 * @author lixin
 * @date 2019-01-25 15:30
 **/
public interface Pool <T> {

    /**
     * Returns an instance from pool
     * @return
     */
    T get();

    /**
     * Releases the instance and put it back to pool
     *
     * @param t
     */
    void release(T t);

    /**
     * destroys the pool and release all instance
     */
    void destroy();

    /**
     * 内部接口 用来验证连接实例是否还有效
     * 这和Java集合库里的Map和Map.Entry是一样的实现结构
     * @param <T>
     */
    public static interface Validator <T> {

        /**
         * Check whether the object is valid.
         * @param t
         * @return
         */
        public Boolean isValid(T t);

        /**
         * Performs  any cleanup  activites befor discarding the objects.
         * For example, before discarding  database connection objects, the pool will want
         * to close  the connections.
         * @param t
         */
        public void invalidate(T t);

    }

}

package com.ironass.commonpool;

/**
 * 用来创建对象池的工厂
 * @author lixin
 * @date 2019-01-29 11:44
 **/
public class PoolFactory {

    private PoolFactory(){

    }

    /**
     * 创建并返回一个新的阻塞对象池
     * @param size
     * @param objectFactory
     * @param validator
     * @param <T>
     * @return
     */
    public static <T> Pool<T> newBoundedBlockingPool(int size, ObjectFactory<T> objectFactory, Pool.Validator<T> validator){
        return new BoundedBlockingPool<T>(size,validator,objectFactory);
    }

    /**
     * 创建并返回一个非阻塞的对象池
     * @param size
     * @param objectFactory
     * @param validator
     * @param <T>
     * @return
     */
    public static <T> Pool<T> newBoundedNonBlockingPool(int size, ObjectFactory<T> objectFactory, Pool.Validator validator){
       return new BoundedPool<T>(size,validator,objectFactory);
    }
}

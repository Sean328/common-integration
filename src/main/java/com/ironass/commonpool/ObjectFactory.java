package com.ironass.commonpool;

/**
 * 对象创建工厂
 * @author lixin
 * @date 2019-01-25 16:14
 **/
public interface ObjectFactory<T> {

    /**
     * Returns a new instance of an object of type T
     * @return
     */
    T createNew();
}

package com.ironass.commonpool;

import java.util.concurrent.TimeUnit;

/**
 * 阻塞对象池，在并发环境使用时，如果池中没有剩余对象，让调用者阻塞直到池中有空余对象
 * @author lixin
 * @date 2019-01-25 16:17
 **/
public interface BlockingPool<T> extends Pool<T> {

    /**
     * Returns an instance of type from pool
     * @return
     */
    @Override
    T get();

    T get(long time, TimeUnit unit) throws InterruptedException;

}

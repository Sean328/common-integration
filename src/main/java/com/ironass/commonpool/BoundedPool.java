package com.ironass.commonpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 非阻塞对象池（不适用异步操作）
 *
 * @author lixin
 * @date 2019-01-29 11:07
 **/
public class BoundedPool<T> extends AbstractPool<T> {

    private static final Logger logger = LoggerFactory.getLogger(BoundedPool.class);

    private int size;
    private Queue<T> objects;
    private ObjectFactory<T> objectFactory;
    private Validator<T> validator;
    //使用信号量来实现锁
    private Semaphore permits = new Semaphore(10);
    private volatile boolean destroyCalled;

    public BoundedPool(int size, Validator<T> validator, ObjectFactory<T> objectFactory) {
        super();
        this.size = size;
        this.validator = validator;
        this.objectFactory = objectFactory;
        objects = new LinkedList<T>();
        initObjects();
        destroyCalled = false;

    }


    @Override
    protected boolean isValid(T t) {
        return validator.isValid(t);
    }

    @Override
    protected void returnObject(T t) {

        boolean added = objects.add(t);
        System.out.println(objects.size());
        if (added) {
            permits.release();
        }

    }

    @Override
    protected void handleInvalidReturn(T t) {
        objects.remove(t);
    }

    @Override
    public T get() {
        T t = null;
        if (!destroyCalled) {
            while (!permits.tryAcquire()) {
                logger.trace("could not get semaphore ,wating for release");
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            logger.info("pool size : {} ", objects.size());
            while (t == null) {
                t = objects.poll();
            }

            if (t != null) {
                logger.error("从池中获取连接，连接信息：{}", t.toString());
            } else {
                logger.info("从池中获取连接信息为空");
            }
        } else {
            throw new IllegalArgumentException("Object pool arealdy destroyed");
        }
        return t;
    }


    @Override
    public void destroy() {
        destroyCalled = true;
        clearResources();
        logger.info("BoundedPool already destoryed");
    }

    private void clearResources() {
        for (T t : objects) {
            validator.invalidate(t);
        }
    }

    private void initObjects() {
        for (int i = 0; i < size; i++) {
            objects.add(objectFactory.createNew());
        }
        logger.info("Bounded Pool already created, size:{}", size);
    }
}

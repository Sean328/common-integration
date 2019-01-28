package com.ironass.commonpool;

import java.util.concurrent.*;

/**
 * 这是一个非常基本的通用对象池，内部是基于LinkedList来实现的，需要注意的是returnObject这个方法，由于LinkedList在执行put操作时，
 * 如果队列已满这时将阻塞队列直到有队列中有新空位，这样会对程序造成比较大的弊端，这里将returnObject方法作为一个异步的任务来交给一个Executor
 * 来执行，以便客户端能够继续执行。
 * @author lixin
 * @date 2019-01-25 17:27
 **/
public final class BoundedBlockingPool<T> extends AbstractPool<T> implements BlockingPool<T> {

    private int size;
    private BlockingQueue<T> objects;
    private Validator validator;
    private ObjectFactory objectFactory;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private volatile boolean destoryCalled;

    public BoundedBlockingPool(int size, Validator validator, ObjectFactory objectFactory) {
        super();
        this.size = size;
        this.validator = validator;
        this.objectFactory = objectFactory;
        objects = new LinkedBlockingQueue(size);
        initObjects();
        destoryCalled = false;
    }

    @Override
    public T get(long time, TimeUnit unit) {
        if (!destoryCalled) {
            T t = null;
            try {
                t = objects.poll(time, unit);
                return t;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return t;
        }
        throw new IllegalStateException("Object pool is already destoryed");
    }

    @Override
    public T get() {
        if (!destoryCalled) {
            T t = null;
            try{
                t = objects.poll();
                return t;
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }

        }
        throw new IllegalStateException("Object pool is already destoryed");
    }

    @Override
    public void destory() {
        destoryCalled = true;
        executor.shutdownNow();
        clearResource();
    }

    private void clearResource() {
        for (T t : objects){
            validator.invalidate(t);
        }
    }

    @Override
    protected boolean isValid(T t) {
        return validator.isValid(t);
    }

    @Override
    protected void returnObject(T t) {
      if(validator.isValid(t)){
          executor.submit(new ObjectReturn(objects,t));
      }
    }

    @Override
    protected void handleInvalidReturn(T t) {

    }

    private void initObjects() {
        for (int i = 0; i < size; i++) {
            objects.add((T) objectFactory.createNew());
        }
    }

    private class ObjectReturn<E> implements Callable{

        private BlockingQueue queue;
        private E e;

        public ObjectReturn(BlockingQueue queue,E e){
            this.queue = queue;
            this.e = e;
        }

        @Override
        public Void call() throws Exception {
            while (true){
                try {
                    queue.put(e);
                    break;
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            return null;
        }

    }
}

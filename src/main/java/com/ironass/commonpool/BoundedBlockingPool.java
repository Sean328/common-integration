package com.ironass.commonpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 这是一个非常基本的通用对象池，内部是基于LinkedList来实现的，需要注意的是returnObject这个方法，由于LinkedList在执行put操作时，
 * 如果队列已满这时将阻塞队列直到有队列中有新空位，这样会对程序造成比较大的弊端，这里将returnObject方法作为一个异步的任务来交给一个Executor
 * 来执行，以便客户端能够继续执行。
 * @author lixin
 * @date 2019-01-25 17:27
 **/
public final class BoundedBlockingPool<T> extends AbstractPool<T> implements BlockingPool<T> {
    private static final Logger logger = LoggerFactory.getLogger(BoundedBlockingPool.class);

    private int size;
    private BlockingQueue<T> objects;
    private Validator validator;
    private ObjectFactory objectFactory;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private volatile boolean destroyCalled;

    public BoundedBlockingPool(int size, Validator validator, ObjectFactory objectFactory) {
        super();
        this.size = size;
        this.validator = validator;
        this.objectFactory = objectFactory;
        objects = new LinkedBlockingQueue(size);
        initObjects();
        destroyCalled = false;
    }

    @Override
    public T get(long time, TimeUnit unit) {
        if (!destroyCalled) {
            T t = null;
            try {
                t = objects.poll(time, unit);
                return t;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return t;
        }
        throw new IllegalStateException("Object pool already destroyed");
    }

    @Override
    public T get() {
        if (!destroyCalled) {
            T t = null;
            try{
                while (t == null){
                    t = objects.poll();
                }
                if(t != null){
                    logger.error("从池中获取连接，连接信息：{}",t.toString());
                }else {
                    logger.info("池中获取对象为空");
                }
                return t;
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }

        }
        throw new IllegalStateException("Object pool already destroyed");
    }

    @Override
    public void destroy() {
        destroyCalled = true;
        executor.shutdownNow();
        clearResource();
        logger.info("Bounded Blocking Pool already destoryed");
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

        logger.info("Bounded Blocking Pool already created, size:{}",size);
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

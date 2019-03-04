package com.ironass.concurrent.threadlocal;

/**
 * @author lixin
 * @date 2019-03-04 14:53
 **/
public class SequenceNumbers {

    // 定义匿名子类创建ThreadLocal的变量
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        // 覆盖初始化方法
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    // 下一个序列号
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    private static class TestClient extends Thread {
        private SequenceNumbers sn;

        public TestClient(SequenceNumbers sn) {
            this.sn = sn;
        }

        // 线程产生序列号
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SequenceNumbers sn = new SequenceNumbers();
        // 三个线程产生各自的序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }
}

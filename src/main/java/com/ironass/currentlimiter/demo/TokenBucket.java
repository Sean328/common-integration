package com.ironass.currentlimiter.demo;

/**
 * 令牌桶实现 (Token Bucket)和 Leaky Bucket 效果一样但方向相反的算法,更加容易理解.随着时间流逝,
 * 系统会按恒定1/QPS时间间隔(如果QPS=100,则间隔是10ms)往桶里加入Token(想象和漏洞漏水相反,有个水龙头在不断的加水),
 * 如果桶已经满了就不再加了.新请求来临时,会各自拿走一个Token,如果没有Token可拿了就阻塞或者拒绝服务.
 *
 * @author lixin
 * @date 2019-02-14 15:07
 **/
public class TokenBucket {

    //速度 默认毫秒
    static long speed = 50;
    static long maxPermist = 100;
    static long lastTimeMills = System.currentTimeMillis();
    static long currentPermits = 0;

    public static long getBlockingMills(long permits) {

        long currentMills = System.currentTimeMillis();

        long bucketPermits = (currentMills - lastTimeMills) * speed;
        long expectPermits = bucketPermits + currentPermits;
        long actualPermits = Math.min(expectPermits, maxPermist);

        if (bucketPermits > 0) {
            lastTimeMills = currentMills;
        }

        //判断令牌桶中剩余的数量
        if (actualPermits - permits >= 0) {
            // 令牌桶中数量充足，不需要阻塞等待
            currentPermits = actualPermits - 1;
            return 0;
        } else {
            // 令牌桶中数量不足，阻塞等待
            currentPermits = actualPermits;
            System.out.println("令牌不足" + permits + "," + actualPermits);
            // 返回需要阻塞的纳秒数
            return (permits - actualPermits) * (1000 / speed);
        }


    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            System.out.println(getBlockingMills(1));
        }
        System.out.println("耗时： " + (System.currentTimeMillis() - startTime));

        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            System.out.println(getBlockTime2(1));
        }
        System.out.println("耗时： " + (System.currentTimeMillis() - startTime2));
    }


    static long nextPermissionTime = System.currentTimeMillis();
    static long timeoutMill = 1000;

    /**
     * 返回
     * @param permits 每次获取的令牌数量
     * @return
     */
    public static long getBlockTime2(int permits) {
        long blockNanoMill = 0;
        long currentTime = System.currentTimeMillis();
        if (nextPermissionTime > 0) {
            if (nextPermissionTime > currentTime) {
                //下次令牌产生时间大于当前时间，阻塞等待
                blockNanoMill = nextPermissionTime - currentTime;
                if (timeoutMill > 0 && blockNanoMill > timeoutMill) {
                    //阻塞时间大于超时时间，直接返回-1
                    return -1;
                }
            }else {
                //下次令牌产生时间在当前时间前，可以获取令牌
                nextPermissionTime = currentTime;
            }
        }else {
            // 置为当前时间
            nextPermissionTime = currentTime;
        }


        nextPermissionTime = nextPermissionTime + 1000/speed * permits;

        return blockNanoMill;
    }

}

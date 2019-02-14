package com.ironass.currentlimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lixin
 * @date 2019-02-13 10:53
 **/
public class RateLimiterService {
    private static final Logger logger = LoggerFactory.getLogger(RateLimiterService.class);

    private String busiKey;

    private volatile Integer permitsPerRequest;

    private volatile Integer permitsPerSecond;

    private volatile Integer timeoutMillSeconds;

    private RedisScript<Long> redisScript;

    private RedisTemplate redisTemplate;

    private Jedis jedisClient;


    private long blockingAcquire(int permits, long timeOutMillSeconds) {
        long holdTime = -1;
        try {
            Long currentTime = System.currentTimeMillis();
            //@TODO 调用TIME命令获取统一时间
            //redisTemplate.getConnectionFactory().getConnection().time();//如此获取会造成连接泄漏
            logger.info("current time: " + currentTime);
            holdTime = (long) redisTemplate.execute(redisScript, Collections.singletonList(busiKey),
                    String.valueOf(currentTime), String.valueOf(permitsPerSecond), String.valueOf(permits), String.valueOf(timeOutMillSeconds));
            logger.info("holdTime ms: " + holdTime);
            if (holdTime > 0) {
                TimeUnit.MILLISECONDS.sleep(holdTime);
            }
        } catch (Exception e) {
            logger.error("限流器执行异常！", e);
        }
        return holdTime;
    }


    public void aquire(){

    }

    public void aquire(Integer permits){

    }

    public Boolean tryAquire(){

        return Boolean.FALSE;
    }

    public Boolean tryAquire(Integer permits){

        return  Boolean.FALSE;
    }


    public RateLimiterService setBusiKey(String busiKey) {
        this.busiKey = busiKey;
        return this;
    }

    public RateLimiterService setPermitsPerRequest(Integer permitsPerRequest) {
        this.permitsPerRequest = permitsPerRequest;
        return this;
    }

    public RateLimiterService setPermitsPerSecond(Integer permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        return this;
    }

    public RateLimiterService setTimeoutMillSeconds(Integer timeoutMillSeconds) {
        this.timeoutMillSeconds = timeoutMillSeconds;
        return this;
    }


    public boolean acquire(String key, Integer permits, long currMillSecond) {
        try  {

            //针对新用户创建令牌桶
            if (!jedisClient.exists(key)) {
                jedisClient.hset(key, "last_mill_second", String.valueOf(currMillSecond));
                jedisClient.hset(key, "curr_permits", "0");
                jedisClient.hset(key, "max_permits", "50");
                jedisClient.hset(key, "rate", "400");
                return true;
            }
            //获取令牌桶信息，上一个令牌时间，当前可用令牌数，最大令牌数，令牌消耗速率
            List<String> limitInfo = jedisClient.hmget(key, "last_mill_second", "curr_permits", "max_permits", "rate");
            long lastMillSecond = Long.parseLong(limitInfo.get(0));
            Integer currPermits = Integer.valueOf(limitInfo.get(1));
            Integer maxPermits = Integer.valueOf(limitInfo.get(2));
            Double rate = Double.valueOf(limitInfo.get(3));
            //向桶里面添加令牌
            Double reversePermitsDouble = ((currMillSecond - lastMillSecond) / 1000) * rate;

            Integer reversePermits = reversePermitsDouble.intValue();
            Integer expectCurrPermits = reversePermits + currPermits;
            Integer localCurrPermits = Math.min(expectCurrPermits, maxPermits);
            //添加令牌之后更新时间
            if (reversePermits > 0) {
                jedisClient.hset(key, "last_mill_second", String.valueOf(currMillSecond));
            }
            //判断桶里面剩余的令牌数目
            if (localCurrPermits - permits >= 0) {
                jedisClient.hset(key, "curr_permits", String.valueOf(localCurrPermits - permits));
                return true;
            } else {
                jedisClient.hset(key, "curr_permits", String.valueOf(localCurrPermits));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

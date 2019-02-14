package com.ironass.currentlimiter;

import com.ironass.currentlimiter.domain.LimiterProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @author lixin
 * @date 2019-02-12 17:32
 **/
public class RateLimiter {



    public void initLimiter(){

        LimiterPropertyService limiterPropertyService = new LimiterPropertyService();

        LimiterProperty limiterProperties = limiterPropertyService.getLimiterInfo();



        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("ratelimiter.lua"));
        redisScript.setResultType(Long.class);

    }

    /**
     * 阻塞方式限流
     * 获取单个许可，获取不到时阻塞
     * @param key
     */
    public void acquire(String key){




    }

    /**
     * 阻塞方式限流
     * 获取指定数量的许可，获取不到时阻塞
     * @param key
     * @param permits
     */
    public void acquire(String key, int permits){

    }

    /**
     * 尝试获取许可，获取不到时调用方可以丢弃请求
     * @param key
     * @return
     */
    public Boolean tryAcquire(String key){

        return Boolean.FALSE;
    }

    /**
     * 尝试获取指定数量的许可，获取不到时调用方可以丢弃请求
     * @param key
     * @param permits
     * @return
     */
    public Boolean tryAcquire(String key,int permits){

        return Boolean.FALSE;
    }
}

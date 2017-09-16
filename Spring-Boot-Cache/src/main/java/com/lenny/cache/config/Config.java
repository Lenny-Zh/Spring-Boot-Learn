package com.lenny.cache.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by zly on 17-9-16.
 */
public class Config {
    @Value("${redis.addr}")
    private String redis_addr;

    @Value("${redis.auth}")
    private String auth;

    public Config(){
        System.out.println(this.redis_addr);
        System.out.println(this.auth);
    }

}

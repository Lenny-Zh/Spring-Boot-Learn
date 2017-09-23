package com.lenny.cache.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by zly on 17-9-23.
 */
public class RedisManager {

    private JedisPool pool = null;

    public static RedisManager redisManager  = null;

    public static RedisManager getInstance(){
        if(redisManager == null){
            redisManager = new RedisManager();
        }
        return redisManager;
    }

    private RedisManager() {
        try {
            RedisConfig config = new RedisConfig();

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(config.getRedisMaxTotal());
            jedisPoolConfig.setMaxIdle(config.getRedisMaxIdle());
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPoolConfig.setMaxWaitMillis(config.getRedisMaxWaitMillis());

            pool = new JedisPool(jedisPoolConfig, config.getRedisAddress(),
                    config.getRedisPort(), config.getRedisTimeout(),config.getRedisPassword());

            System.out.println("init redis params: ip=" + config.getRedisAddress()
                    + ", port=" + config.getRedisPort() + ", password="
                    + config.getRedisPassword() + ", maxtotal="
                    + config.getRedisMaxTotal() + ", maxidle="
                    + config.getRedisMaxIdle() + ", maxwaitmillis="
                    + config.getRedisMaxWaitMillis() + ", timeout="
                    + config.getRedisTimeout() + ", password="
                    + config.getRedisPassword());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


}

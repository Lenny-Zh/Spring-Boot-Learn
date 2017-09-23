package com.lenny.cache.redis;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by zly on 17-9-23.
 */
public class RedisConfig {

    private static final String REDIS_ADDRESS = "redis.address";
    private static final String REDIS_PORT = "redis.port";
    private static final String REDIS_PASSWORD = "redis.password";
    private static final String REDIS_TIMEOUT = "redis.timeout";
    private static final String REDIS_MAX_TOTAL = "redis.max.total";
    private static final String REDIS_MAX_IDLE = "redis.max.idle";
    private static final String REDIS_MAX_WAIT_MILLIS = "redis.max.wait.millis";

    private Properties props;

    public RedisConfig() throws IOException {
        props = new Properties();
        URL configUrl = RedisConfig.class.getClassLoader().getResource("cache-config.properties");
        File file = new File(configUrl.getPath());
        if (!file.exists()) {
            throw new IOException("cache-config.yml not found");
        } else {
            try {
                props.load(new FileInputStream(file));
            } catch (IOException e) {
                throw new IOException("cache-config.yml not fail");
            }
        }
    }

    private int getIntegerValue(String propKey, int defaultValue) {
        int i = 0;
        try {
            i = Integer.parseInt(props.getProperty(propKey));
        } catch (NumberFormatException nfe) {
            i = defaultValue;
        }
        return i;
    }

    private String noNull(String str) {
        return (str != null) ? str : "";
    }

    public String getRedisAddress() {
        return props.getProperty(REDIS_ADDRESS);
    }

    public int getRedisPort() {
        return getIntegerValue(REDIS_PORT, 6379);
    }

    public int getRedisTimeout() {
        return getIntegerValue(REDIS_TIMEOUT, 1000);
    }

    public String getRedisPassword() {
        return props.getProperty(REDIS_PASSWORD);
    }

    public int getRedisMaxTotal() {
        return getIntegerValue(REDIS_MAX_TOTAL, 200);
    }

    public int getRedisMaxIdle() {
        return getIntegerValue(REDIS_MAX_IDLE, 10);
    }

    public int getRedisMaxWaitMillis() {
        return getIntegerValue(REDIS_MAX_WAIT_MILLIS, 1000*100);
    }






}

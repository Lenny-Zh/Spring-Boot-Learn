package com.lenny.cache;

import com.lenny.cache.config.Config;
import com.lenny.cache.redis.RedisConfig;
import com.lenny.cache.redis.RedisManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCacheApplicationTests {

	@Test
	public void contextLoads() {
	/*	redisManager.set("key1" , "value1");
		redisManager.set("key2" , "value2");
		redisManager.set("key3" , "value3");

		System.out.println( redisManager.get("key1"));
		System.out.println( redisManager.get("key2"));
		System.out.println( redisManager.get("key3"));*/
/*
		redisConfig.toString();
*/

		RedisManager.getInstance().set("key1" , "hahahah");
		System.out.println(RedisManager.getInstance().get("key1"));

	}

}

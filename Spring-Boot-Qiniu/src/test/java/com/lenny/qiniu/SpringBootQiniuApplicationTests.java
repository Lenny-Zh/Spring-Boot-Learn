package com.lenny.qiniu;

import com.lenny.cache.redis.RedisManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootQiniuApplicationTests {

	@Test
	public void testCache() {
		System.out.println(RedisManager.getInstance().get("key1"));
	}


}

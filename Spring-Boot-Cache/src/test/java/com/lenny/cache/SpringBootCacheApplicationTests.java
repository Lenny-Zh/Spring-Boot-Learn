package com.lenny.cache;

import com.lenny.cache.config.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCacheApplicationTests {

	@Test
	public void contextLoads() {
		Config c = new Config();
	}

}

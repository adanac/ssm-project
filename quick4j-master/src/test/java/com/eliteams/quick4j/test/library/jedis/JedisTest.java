package com.eliteams.quick4j.test.library.jedis;

import com.eliteams.quick4j.core.feature.cache.redis.RedisCache;
import com.eliteams.quick4j.test.TestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * JedisTest : 测试 jedis 功能
 *
 * @author StarZou
 * @since 2015-03-20 10:32
 */
public class JedisTest extends TestSupport {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Resource
    private RedisCache redisCache;


    @Test
    public void testSet() {
        redisCache.cache("anchor", "StarZou", 1 * 60 * 24);
    }

    @Test
    public void testGet() {
        LOGGER.info("testGet is {}", redisCache.get("anchor"));
    }

    @Test
    public void testDelete() {
        LOGGER.info("testDelete result is {}", redisCache.delete("anchor"));
    }
}

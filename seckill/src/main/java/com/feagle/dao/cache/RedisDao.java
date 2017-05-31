package com.feagle.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.feagle.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis访问对象
 * Created by feagle on 2017/5/30.
 */
public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId) {
        try {
            //redis操作逻辑
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //jedis并没有实现内部序列化操作
                //get -> byte[] -> 反序列化 -> Object(Seckill)
                //采用自定义序列化:protostuff
                jedis.auth("123456");//权限认证
                byte[] bytes = jedis.get(key.getBytes());
                //缓存中获取到
                if (bytes != null) {
                    Seckill seckill = schema.newMessage();//通过构造方法创建一个空对象
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);//完成seckill的反序列化
                    return seckill;
                }

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        //set Object (Seckill)  -> 序列化 -> byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                jedis.auth("123456");//权限认证
                //超时缓存
                int timeout = 60 * 60;//1小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}

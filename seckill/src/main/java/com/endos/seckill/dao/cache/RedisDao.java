package com.endos.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.endos.seckill.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Endos on 2017/4/18.
 */
public class RedisDao {
    private JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(Long seckillId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                // redis并没有实现内部序列化
                // 采用自定义序列化转化二进制数组让Redis缓存
                // Protostuff可以序列化普通pojo
                byte[] bytes = jedis.get(key.getBytes());
                // 从缓存中得到Seckill的字节数组
                if (bytes != null) {
                    // Seckill空对象
                    Seckill seckill = schema.newMessage();
                    // 将二进制数组依据schema给对象赋值
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                // LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE)缓存器
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存,正常返回OK
                String result = jedis.setex(key.getBytes(), 60 * 60, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

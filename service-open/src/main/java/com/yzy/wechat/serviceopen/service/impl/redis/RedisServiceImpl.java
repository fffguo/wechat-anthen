package com.yzy.wechat.serviceopen.service.impl.redis;

import java.util.List;
import java.util.concurrent.TimeUnit;


import com.yzy.wechat.serviceopen.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@SuppressWarnings("unused")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<Object, Object> valOpsStr;


	@Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valOps;

    @Override
    public void del(List<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    @Override
    public void set(byte[] key, byte[] value, long liveTime) {
        valOps.set(key, value, liveTime);
    }

    @Override
    public void set(String key, String value, long liveTime) {
        valOpsStr.set(key, value, liveTime);
    }

    @Override
    public void set(String key, String value, long liveTime, TimeUnit timeUnit) {
        valOpsStr.set(key, value, liveTime, timeUnit);
    }

    @Override
    public void set(String key, String value) {
        valOpsStr.set(key, value);
    }

    @Override
    public void set(byte[] key, byte[] value) {
        valOps.set(key, value);
    }

    @Override
    public String get(String key) {
        return String.valueOf(valOpsStr.get(key));
    }

}

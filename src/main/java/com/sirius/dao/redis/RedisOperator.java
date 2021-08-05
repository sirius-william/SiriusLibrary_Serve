/*
 * 操作页面元素的redis数据库接口
 * */

package com.sirius.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisOperator {
    @Autowired
    RedisTemplate redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value) {
        boolean res = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            res = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public boolean set(String key, Object value, long timeout){
        boolean res = false;
        try {
            redisTemplate.opsForValue().set(key, value,  timeout, TimeUnit.SECONDS);
            res = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public Object hashGet(String hash, String key){
        return redisTemplate.opsForHash().get(hash, key);
    }

    public boolean hashSet(String hash, String key, Object value){
        boolean res = false;
        try {
            redisTemplate.opsForHash().put(hash, key, value);
            res = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


}

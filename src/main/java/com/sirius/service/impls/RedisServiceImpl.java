package com.sirius.service.impls;

import com.sirius.dao.redis.RedisOperator;
import com.sirius.service.interfaces.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisOperator redisOperator;

    @Override
    public String getUserTokenFromRedis(Integer id) {
        return (String)redisOperator.get(id.toString());
    }

    @Override
    public boolean setToken(Integer id, String token) {
        return redisOperator.set(id.toString(), token, 60 * 10);
    }
}

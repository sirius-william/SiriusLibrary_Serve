package com.sirius.service.interfaces;

public interface RedisService {
    String getUserTokenFromRedis(Integer id);
    boolean setToken(Integer id, String token);
}

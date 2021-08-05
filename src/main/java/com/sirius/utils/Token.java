package com.sirius.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sirius.domain.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Token {
    @Value("${token.privateKey}")
    public String privateKey;
    @Value("${token.expireTime}")
    public String expireTime;

    public String getNewToken(UserInfo info) {
        String token = JWT.create().withAudience(info.getId().toString()).sign(Algorithm.HMAC256(this.privateKey));
        return token;
    }

    public String parseToken(String token) {
        String audience = JWT.decode(token).getAudience().get(0);
        return audience;
    }
}

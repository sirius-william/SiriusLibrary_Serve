/*
 * Test
 * 描述
 @author Sirius
 */
package com.sirius;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sirius.domain.UserInfo;
import com.sirius.utils.Token;

public class Test {

    public static void main(String[] args) {
        Token token = new Token();
        token.privateKey = "12323";
        UserInfo info = new UserInfo();
        info.setId(1000000);
        info.setName("陈咸潼");
        String tokenString = token.getNewToken(info);
        System.out.println(tokenString);
        String id = JWT.decode(tokenString).getAudience().get(0);
        JWT.require(Algorithm.HMAC256("12323")).build().verify(tokenString);
        System.out.println(id);
    }
}

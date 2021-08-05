package com.sirius.interceptor;

import com.sirius.annotation.UseToken;
import com.sirius.exceptions.AuthException;
import com.sirius.service.impls.RedisServiceImpl;
import com.sirius.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*权限验证*/
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    Token tokenUtil;
    @Autowired
    RedisServiceImpl redisServiceImpl;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;

        if (method.hasMethodAnnotation(UseToken.class) || method.getBean().getClass().isAnnotationPresent(UseToken.class)){
            String token = request.getHeader("token");
            if (token == null){
                throw new AuthException("非法访问，请登录");
            }
            String audience = tokenUtil.parseToken(token);
            if (audience == null || audience.equals("")){
                throw new AuthException("非法token，请重新登录");
            }
            String checkTokenFromRedis = redisServiceImpl.getUserTokenFromRedis(Integer.valueOf(audience));
            if (checkTokenFromRedis == null || !checkTokenFromRedis.equals(token)){
                throw new AuthException("token无效或已过期，请重新登录");
            }
        }
        return true;
    }
}

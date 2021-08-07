package com.sirius.config;

import com.sirius.interceptor.AuthInterceptor;
import com.sirius.service.impls.RedisServiceImpl;
import com.sirius.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthInterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    RedisServiceImpl redisServiceImpl;
    @Autowired
    Token tokenUtil;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePathPatterns = {"/login", "/register"};

        registry.addInterceptor(new AuthInterceptor(tokenUtil, redisServiceImpl)).addPathPatterns("/**");
    }
}

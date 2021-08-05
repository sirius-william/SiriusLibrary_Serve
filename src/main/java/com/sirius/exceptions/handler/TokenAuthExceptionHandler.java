package com.sirius.exceptions.handler;

import com.sirius.exceptions.AuthException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class TokenAuthExceptionHandler {

    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public Object authExceptionHandler(AuthException e){
        System.out.println("进入全局异常处理模块，处理AuthException");
        HashMap<String, String> res = new HashMap<String, String>();
        res.put("successful", "-1");
        res.put("error", e.getMessage());
        return res;
    }
}

package com.sirius.controller.user;

import com.sirius.annotation.UseToken;
import com.sirius.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@UseToken
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    // 获取用户列表
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Object getUserList(Integer type){
        HashMap<String, Object> res = new HashMap<>();
        res.put("success", 1);
        res.put("data", userInfoService.getUserList(type));
        return res;
    }
    // 获取用户信息
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public Object getUserInfo(Integer id){
        HashMap<String, Object> res = new HashMap<>();
        res.put("success", 1);
        res.put("data", userInfoService.getUserInfo(id));
        return res;
    }
}

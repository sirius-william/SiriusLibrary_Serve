/*
 * UserController
 * 描述
 @author Sirius
 */
package com.sirius.controller.user;

import com.sirius.domain.UserInfo;
import com.sirius.service.impls.RedisServiceImpl;
import com.sirius.service.impls.SecurityServiceImpl;
import com.sirius.service.impls.UserLoginServiceImpl;
import com.sirius.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    SecurityServiceImpl securityServiceImpl;
    @Autowired
    UserLoginServiceImpl userLoginServiceImpl;
    @Autowired
    Token token;
    @Autowired
    RedisServiceImpl redisServiceImpl;

    // 用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object run(HttpServletRequest request, String type, String username, String password){
        HashMap<String, String> res = new HashMap<>();
        System.out.println(type + "\t" + username + "\t" + password);
        UserInfo check = securityServiceImpl.login(username, password);

        if (check == null){
            res.put("success", "-1");
            res.put("err", "username or password wrong");
        } else {
            res.put("success", "1");
            String tokenString = token.getNewToken(check);
            res.put("token", tokenString);
            redisServiceImpl.setToken(check.getId(), tokenString);
            HttpSession session = request.getSession(true);
        }
        return res;
    }

    // 用户注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object resister(String email, String sex, String tel, String username, String password){
        HashMap<String, String> res = new HashMap<String, String>();
        boolean ifRegistered = userLoginServiceImpl.checkIfRegistered(username);
        if (ifRegistered){
            res.put("success", "-1");
            res.put("error", "user has been registered");
            return res;
        }
        // 创建用户个人信息对象
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setSex(sex);
        userInfo.setAddress("空");
        userInfo.setTel(tel);
        // 生成用户id（读书证）
        Integer id = securityServiceImpl.resister(userInfo, username, password);
        // 存入
        // 显示页面
        res.put("success", "1");
        res.put("id", id.toString());
        return res;
    }
}

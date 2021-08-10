package com.sirius.service.interfaces;

import com.sirius.domain.UserInfo;

import java.util.ArrayList;

public interface UserInfoService {
    // 按类型获取用户列表
    ArrayList<UserInfo> getUserList(Integer type);
    // 获取用户资料信息
    UserInfo getUserInfo(Integer id);
}

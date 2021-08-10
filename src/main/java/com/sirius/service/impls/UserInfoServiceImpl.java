package com.sirius.service.impls;

import com.sirius.dao.mysql.UserInfoDao;
import com.sirius.domain.UserInfo;
import com.sirius.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;
    @Override
    public ArrayList<UserInfo> getUserList(Integer type) {
        return userInfoDao.selectUserInfoByType(type);
    }

    @Override
    public UserInfo getUserInfo(Integer id) {
        return userInfoDao.selectUserInfoById(id);
    }


}

package com.sirius.service.impls;

import com.sirius.dao.mysql.UserLoginDao;
import com.sirius.service.interfaces.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    UserLoginDao userLoginDao;
    @Override
    public boolean checkIfRegistered(String username) {
        int userIfRegistered = userLoginDao.selectUserIfRegistered(username);
        return userIfRegistered != 0;
    }
}

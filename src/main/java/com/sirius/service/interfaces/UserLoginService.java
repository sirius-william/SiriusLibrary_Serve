package com.sirius.service.interfaces;

public interface UserLoginService {
    // 查看username是否存在
    boolean checkIfRegistered(String username);
}

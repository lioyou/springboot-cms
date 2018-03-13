package com.leecp.jpa.service;


import com.leecp.jpa.common.base.Service;
import com.leecp.jpa.model.User;

public interface UserService extends Service<User,Integer> {
    User findUserByNickname(String nickName);
}

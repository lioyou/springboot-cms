package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.User;
import com.leecp.jpa.repository.UserRepository;
import com.leecp.jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl extends AbstractService<User,Integer> implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private  UserRepository userRepository;
    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(userRepository);
    }

    @Override
    public User findUserByNickname(String nickName) {
        return userRepository.findUserByNickname(nickName);
    }
}

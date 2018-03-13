package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.User;
import com.leecp.jpa.repository.SystemRepository;
import com.leecp.jpa.repository.UserRepository;
import com.leecp.jpa.service.SystemService;
import com.leecp.jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SystemServiceImpl extends AbstractService<com.leecp.jpa.model.System,Integer> implements SystemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemServiceImpl.class);
    @Autowired
    private SystemRepository systemRepository;

    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(systemRepository);
    }
}

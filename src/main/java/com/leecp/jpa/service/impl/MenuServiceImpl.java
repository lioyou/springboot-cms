package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.Menu;
import com.leecp.jpa.repository.MenuRepository;
import com.leecp.jpa.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MenuServiceImpl extends AbstractService<Menu,Integer> implements MenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private  MenuRepository menuRepository;
    public MenuServiceImpl(){
    }
    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(menuRepository);
    }
}

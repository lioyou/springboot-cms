package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.Category;
import com.leecp.jpa.repository.CategoryRepository;
import com.leecp.jpa.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CategoryServiceImpl extends AbstractService<Category,Integer> implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private  CategoryRepository cateRepository;
    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(cateRepository);
    }
}

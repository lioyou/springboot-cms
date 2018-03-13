package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.Tag;
import com.leecp.jpa.repository.TagRepository;
import com.leecp.jpa.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TagServiceImpl extends AbstractService<Tag,Integer> implements TagService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);
    @Autowired
    private  TagRepository tagRepository;
    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(tagRepository);
    }
}

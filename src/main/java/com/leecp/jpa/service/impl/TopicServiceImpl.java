package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.Topic;
import com.leecp.jpa.repository.TopicRepository;
import com.leecp.jpa.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TopicServiceImpl extends AbstractService<Topic,Integer> implements TopicService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);
    @Autowired
    private  TopicRepository topicRepository;
    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(topicRepository);
    }
}

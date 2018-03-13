package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.Comment;
import com.leecp.jpa.repository.CommentRepository;
import com.leecp.jpa.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CommentServiceImpl extends AbstractService<Comment,Integer> implements CommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
    @Autowired
    private  CommentRepository commentRepository;
    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(commentRepository);
    }
}

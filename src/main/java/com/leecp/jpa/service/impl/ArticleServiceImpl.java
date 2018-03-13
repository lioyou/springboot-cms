package com.leecp.jpa.service.impl;

import com.leecp.jpa.common.base.AbstractService;
import com.leecp.jpa.model.Article;
import com.leecp.jpa.repository.ArticleRepository;
import com.leecp.jpa.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ArticleServiceImpl extends AbstractService<Article,Integer> implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    private  ArticleRepository articleRepository;

    @Override
    @PostConstruct
    public void initRepository() {
        super.setRepository(articleRepository);
    }
}

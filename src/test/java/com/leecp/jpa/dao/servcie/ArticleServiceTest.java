package com.leecp.jpa.dao.servcie;

import com.leecp.jpa.Application;
import com.leecp.jpa.model.Article;
import com.leecp.jpa.repository.ArticleRepository;
import com.leecp.jpa.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Test
    public void testSave(){
        Article article = new Article();
        article.setAuthor("leecp");
        article.setDescription("就是文章的测试");
        article.setTitle("文章标题");
        article.setContent("文章内容");
        article.setFromurl("http://localhost/a/");
        article.setType((byte)1);
        articleService.save(article);
    }
}

package com.leecp.jpa.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.model.Article;
import com.leecp.jpa.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/cms/article/get")
    public Result list(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
                       @RequestParam(required = true,defaultValue = "10",value = "pageSize") int pageSize){
        //判断是否越界问题
        long totalNums = articleService.count();
        pageSize = (int)(pageSize <=0?10:(pageSize > totalNums?totalNums:pageSize));
        int totalPages = (int)totalNums/pageSize + 1;
        currentPage = (int)(currentPage <= 0?1:(currentPage > totalPages?totalPages:currentPage));
        //分页
        Pageable pageable = new PageRequest(currentPage-1,pageSize);//是以0开始的
        Page<Article> page = articleService.findAll(pageable);
        JSONObject articles = new JSONObject();
        articles.put("content",page.getContent());
        articles.put("totalNums",page.getTotalElements());
        articles.put("totalPages",page.getTotalPages());
        return Result.ok("文章请求成功",articles);
    }
}

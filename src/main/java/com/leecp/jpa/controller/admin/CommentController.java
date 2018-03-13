package com.leecp.jpa.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.model.Comment;
import com.leecp.jpa.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/cms/comment/get")
    public Result list(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
                       @RequestParam(required = true,defaultValue = "10",value = "pageSize") int pageSize){
        //判断是否越界问题
        long totalNums = commentService.count();
        pageSize = (int)(pageSize <=0?10:(pageSize > totalNums?totalNums:pageSize));
        int totalPages = (int)totalNums/pageSize + 1;
        currentPage = (int)(currentPage <= 0?1:(currentPage > totalPages?totalPages:currentPage));
        //分页
        Pageable pageable = new PageRequest(currentPage-1,pageSize);//是以0开始的
        Page<Comment> page = commentService.findAll(pageable);
        JSONObject comments = new JSONObject();
        comments.put("content",page.getContent());
        comments.put("totalNums",page.getTotalElements());
        comments.put("totalPages",page.getTotalPages());
        return Result.ok("评论请求成功",comments);
    }
}

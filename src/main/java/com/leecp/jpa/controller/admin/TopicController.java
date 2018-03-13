package com.leecp.jpa.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.common.validator.NumberValidator;
import com.leecp.jpa.common.validator.StringValidator;
import com.leecp.jpa.model.Topic;
import com.leecp.jpa.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;
    @GetMapping("/cms/topic/get")
    public Result list(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
                       @RequestParam(required = true,defaultValue = "10",value = "pageSize") int pageSize){
        //判断是否越界问题
        long totalNums = topicService.count();
        pageSize = (int)(pageSize <=0?10:(pageSize > totalNums?totalNums:pageSize));
        int totalPages = (int)totalNums/pageSize + 1;
        currentPage = (int)(currentPage <= 0?1:(currentPage > totalPages?totalPages:currentPage));
        //分页
        Pageable pageable = new PageRequest(currentPage-1,pageSize);//是以0开始的
        Page<Topic> page = topicService.findAll(pageable);
        JSONObject topics = new JSONObject();
        topics.put("content",page.getContent());
        topics.put("totalNums",page.getTotalElements());
        topics.put("totalPages",page.getTotalPages());
        return Result.ok("专题请求成功",topics);
    }

    @PostMapping("/cms/topic/create")
    public Result create(@RequestBody Topic topic){
        ComplexResult result = FluentValidator.checkAll()
                .on(topic.getTitle(),new StringValidator("名称"))
                .on(topic.getDescription(),new StringValidator("描述"))
                .on(topic.getUrl(),new StringValidator("路径"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("专题创建失败",result.getErrors());
        }
        Topic t = topicService.save(topic);
        return Result.ok("专题创建成功",t);
    }

    @PostMapping("/cms/topic/update")
    public Result update(@RequestBody Topic topic){
        ComplexResult result = FluentValidator.checkAll()
                .on(topic.getId(),new NumberValidator("编号"))
                .on(topic.getTitle(),new StringValidator("名称"))
                .on(topic.getDescription(),new StringValidator("描述"))
                .on(topic.getUrl(),new StringValidator("路径"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("专题创建失败",result.getErrors());
        }
        Topic t = topicService.save(topic);
        return Result.ok("专题创建成功",t);
    }

    @PostMapping("/cms/topic/delete")
    public Result delete(@RequestBody List<Integer> deleteIds){
        if(!deleteIds.isEmpty()){
            boolean isError = false;
            try{
                deleteIds.stream().forEach((id) -> {
                    topicService.delete(id);
                });
            }catch(EmptyResultDataAccessException e){
                isError = true;
            }finally{
                return isError?Result.err("非法参数，删除失败")
                        :Result.ok("删除成功",deleteIds.size());
            }
        }else{//为空
            return Result.err("数据为空");
        }
    }
}

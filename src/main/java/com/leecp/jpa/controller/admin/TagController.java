package com.leecp.jpa.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.common.validator.NumberValidator;
import com.leecp.jpa.common.validator.StringValidator;
import com.leecp.jpa.model.Tag;
import com.leecp.jpa.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/cms/tag/get")
    public Result list(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
                       @RequestParam(required = true,defaultValue = "10",value = "pageSize") int pageSize){
        //判断是否越界问题
        long totalNums = tagService.count();
        pageSize = (int)(pageSize <=0?10:(pageSize > totalNums?totalNums:pageSize));
        int totalPages = (int)totalNums/pageSize + 1;
        currentPage = (int)(currentPage <= 0?1:(currentPage > totalPages?totalPages:currentPage));
        //分页
        Pageable pageable = new PageRequest(currentPage-1,pageSize);//是以0开始的
        Page<Tag> page = tagService.findAll(pageable);
        JSONObject tags = new JSONObject();
        tags.put("content",page.getContent());
        tags.put("totalNums",page.getTotalElements());
        tags.put("totalPages",page.getTotalPages());
        return Result.ok("标签请求成功",tags);
    }


    @PostMapping("/cms/tag/create")
    public Result create(@RequestBody Tag tag){
        ComplexResult result = FluentValidator.checkAll()
                .on(tag.getName(),new StringValidator("名称"))
                .on(tag.getDescription(),new StringValidator("描述"))
                .on(tag.getIcon(),new StringValidator("图标"))
                .on(tag.getAlias(),new StringValidator("别名"))
                .on(tag.getType(),new NumberValidator("类型"))
                .on(tag.getSystemId(),new NumberValidator("所属系统"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("标签创建失败",result.getErrors());
        }
        Tag t = tagService.save(tag);
        return Result.ok("标签创建成功",t);
    }

    @PostMapping("/cms/tag/update")
    public Result update(@RequestBody Tag tag){
        ComplexResult result = FluentValidator.checkAll()
                .on(tag.getId(),new NumberValidator("编号"))
                .on(tag.getName(),new StringValidator("名称"))
                .on(tag.getDescription(),new StringValidator("描述"))
                .on(tag.getIcon(),new StringValidator("图标"))
                .on(tag.getAlias(),new StringValidator("别名"))
                .on(tag.getType(),new NumberValidator("类型"))
                .on(tag.getSystemId(),new NumberValidator("所属系统"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("标签修改失败",result.getErrors());
        }
        Tag t = tagService.save(tag);
        return Result.ok("标签修改成功",t);
    }

    @PostMapping("/cms/tag/delete")
    public Result delete(@RequestBody List<Integer> deleteIds){
        if(!deleteIds.isEmpty()){
            boolean isError = false;
            try{
                deleteIds.stream().forEach((id) -> {
                    tagService.delete(id);
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

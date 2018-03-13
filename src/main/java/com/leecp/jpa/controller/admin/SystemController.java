package com.leecp.jpa.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.common.validator.NumberValidator;
import com.leecp.jpa.common.validator.StringValidator;
import com.leecp.jpa.model.System;
import com.leecp.jpa.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SystemController {
    @Autowired
    private SystemService systemService;
    @GetMapping("/cms/system/get")
    public Result list(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
                       @RequestParam(required = true,defaultValue = "10",value = "pageSize") int pageSize){
        //判断是否越界问题
        long totalNums = systemService.count();
        pageSize = (int)(pageSize <=0?10:(pageSize > totalNums?totalNums:pageSize));
        int totalPages = (int)totalNums/pageSize + 1;
        currentPage = (int)(currentPage <= 0?1:(currentPage > totalPages?totalPages:currentPage));
        //分页
        Pageable pageable = new PageRequest(currentPage-1,pageSize);//是以0开始的
        Page<System> page = systemService.findAll(pageable);
        JSONObject systems = new JSONObject();
        systems.put("content",page.getContent());
        systems.put("totalNums",page.getTotalElements());
        systems.put("totalPages",page.getTotalPages());
        return Result.ok("系统请求成功",systems);
    }
    @PostMapping("/cms/system/create")
    public Result create(@RequestBody System system){
        ComplexResult result = FluentValidator.checkAll()
                .on(system.getName(),new StringValidator("名称"))
                .on(system.getCode(),new StringValidator("代号"))
                .on(system.getDescription(),new StringValidator("描述"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("系统创建失败",result.getErrors());
        }
        System s = systemService.save(system);
        return Result.ok("系统创建成功",s);
    }

    @PostMapping("/cms/system/update")
    public Result update(@RequestBody System system){
        ComplexResult result = FluentValidator.checkAll()
                .on(system.getId(),new NumberValidator("编号"))
                .on(system.getName(),new StringValidator("名称"))
                .on(system.getCode(),new StringValidator("代号"))
                .on(system.getDescription(),new StringValidator("描述"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("系统创建失败",result.getErrors());
        }
        System s = systemService.save(system);
        return Result.ok("系统修改成功",s);
    }

    @PostMapping("/cms/system/delete")
    public Result delete(@RequestBody List<Integer> deleteIds){
        if(!deleteIds.isEmpty()){
//            if(deleteIds.size() == 1){
//                systemService.delete(deleteIds);
//            }else{//批量删除
//                systemService.deleteInBatch(deleteIds);
//            }
            boolean isError = false;
            try{
                deleteIds.stream().forEach((id) -> {
                    systemService.delete(id);
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

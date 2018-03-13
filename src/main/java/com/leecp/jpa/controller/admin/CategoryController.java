package com.leecp.jpa.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.common.validator.NumberValidator;
import com.leecp.jpa.common.validator.StringValidator;
import com.leecp.jpa.model.Category;
import com.leecp.jpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/cms/category/get")
    public Result list(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
                       @RequestParam(required = true,defaultValue = "10",value = "pageSize") int pageSize){
        //判断是否越界问题
        long totalNums = categoryService.count();
        pageSize = (int)(pageSize <=0?10:(pageSize > totalNums?totalNums:pageSize));
        int totalPages = (int)totalNums/pageSize + 1;
        currentPage = (int)(currentPage <= 0?1:(currentPage > totalPages?totalPages:currentPage));
        //分页
        Pageable pageable = new PageRequest(currentPage-1,pageSize);//是以0开始的
        Page<Category> page = categoryService.findAll(pageable);
        JSONObject categorys = new JSONObject();
        categorys.put("content",page.getContent());
        categorys.put("totalNums",page.getTotalElements());
        categorys.put("totalPages",page.getTotalPages());
        return Result.ok("类别请求成功",categorys);
    }

    @PostMapping("/cms/category/create")
    public Result create(@RequestBody Category category){
        // 验证
        ComplexResult result = FluentValidator.checkAll()
                .on(category.getName(),new StringValidator("名称"))
                .on(category.getPid(),new NumberValidator("父级类别"))
                .on(category.getDescription(),new StringValidator("描述"))
                .on(category.getLevel(),new NumberValidator("级别"))
                .on(category.getIcon(),new StringValidator("图标"))
                .on(category.getAlias(),new StringValidator("别名"))
                .on(category.getType(),new NumberValidator("类型"))
                .on(category.getSystemId(),new NumberValidator("所属系统"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("类别创建失败",result.getErrors());
        }
        Category c = categoryService.save(category);
        return Result.ok("类别创建成功",c);
    }

    @PostMapping("/cms/category/update")
    public Result update(@RequestBody Category category){
        ComplexResult result = FluentValidator.checkAll()
                .on(category.getId(),new NumberValidator("编号"))
                .on(category.getName(),new StringValidator("名称"))
                .on(category.getPid(),new NumberValidator("父级类别"))
                .on(category.getDescription(),new StringValidator("描述"))
                .on(category.getLevel(),new NumberValidator("级别"))
                .on(category.getIcon(),new StringValidator("图标"))
                .on(category.getAlias(),new StringValidator("别名"))
                .on(category.getType(),new NumberValidator("类型"))
                .on(category.getSystemId(),new NumberValidator("所属系统"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("类别创建失败",result.getErrors());
        }
        Category c = categoryService.save(category);
        return Result.ok("类别创建成功",c);
    }

    @PostMapping("/cms/category/delete")
    public Result delete(@RequestBody List<Integer> deleteIds){
        if(!deleteIds.isEmpty()){
            boolean isError = false;
            try{
                deleteIds.stream().forEach((id) -> {
                    categoryService.delete(id);
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

package com.leecp.jpa.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.common.validator.NumberValidator;
import com.leecp.jpa.common.validator.StringValidator;
import com.leecp.jpa.model.Menu;
import com.leecp.jpa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/cms/menu/get")
    public Result list(@RequestParam(required = true,defaultValue = "1",value = "currentPage") int currentPage,
                       @RequestParam(required = true,defaultValue = "10",value = "pageSize") int pageSize){
        //判断是否越界问题
        long totalNums = menuService.count();
        pageSize = (int)(pageSize <=0?10:(pageSize > totalNums?totalNums:pageSize));
        int totalPages = (int)totalNums/pageSize + 1;
        currentPage = (int)(currentPage <= 0?1:(currentPage > totalPages?totalPages:currentPage));
        //分页
        Pageable pageable = new PageRequest(currentPage-1,pageSize);//是以0开始的
        Page<Menu> page = menuService.findAll(pageable);
        JSONObject menus = new JSONObject();
        menus.put("content",page.getContent());
        menus.put("totalNums",page.getTotalElements());
        menus.put("totalPages",page.getTotalPages());
        return Result.ok("菜单请求成功",menus);
    }

    @PostMapping("/cms/menu/create")
    public Result create(@RequestBody Menu menu){
        ComplexResult result = FluentValidator.checkAll()
                .on(menu.getPid(),new NumberValidator("父级名称"))
                .on(menu.getName(),new StringValidator("名称"))
                .on(menu.getUrl(),new StringValidator("路径"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("菜单创建失败",result.getErrors());
        }
        Menu m = menuService.save(menu);
        return Result.ok("菜单创建成功",m);
    }

    @PostMapping("/cms/menu/update")
    public Result update(@RequestBody Menu menu){
        ComplexResult result = FluentValidator.checkAll()
                .on(menu.getId(),new NumberValidator("编号"))
                .on(menu.getName(),new StringValidator("名称"))
                .on(menu.getPid(),new NumberValidator("父级名称"))
                .on(menu.getUrl(),new StringValidator("路径"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        //创建失败
        if(!result.isSuccess()){
            return Result.err("菜单修改失败",result.getErrors());
        }
        Menu m = menuService.save(menu);
        return Result.ok("菜单修改成功",m);
    }

    @PostMapping("/cms/menu/delete")
    public Result delete(@RequestBody List<Integer> deleteIds){
        if(!deleteIds.isEmpty()){
            boolean isError = false;
            try{
                deleteIds.stream().forEach((id) -> {
                    menuService.delete(id);
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

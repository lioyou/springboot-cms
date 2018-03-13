package com.leecp.jpa.dao.servcie;

import com.leecp.jpa.Application;
import com.leecp.jpa.model.System;
import com.leecp.jpa.service.SystemService;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class SystemServiceTest {
    @Autowired
    private SystemService systemService;
    @Test
    public void testSelect(){
        systemService.findAll().stream().forEach(s ->{
            java.lang.System.out.println(s.getName());
        });
    }

    @Test
    public void testPagination(){
        Pageable pageable = new PageRequest(0,2);//注意以0开始算的
        Page<System> all = systemService.findAll(pageable);
        java.lang.System.out.println(all.getTotalPages());//2页
        java.lang.System.out.println(all.getTotalElements());//3个，总个数
        java.lang.System.out.println(all.getSize());//2，每页显示数目
        java.lang.System.out.println(all.getNumber());//0,起始页
        all.getContent().stream().forEach(s -> {
            java.lang.System.out.println(s.getName());
        });
    }
}

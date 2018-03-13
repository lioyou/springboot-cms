package com.leecp.jpa.dao.servcie;

import com.leecp.jpa.Application;
import com.leecp.jpa.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class TagServiceTest {
    @Autowired
    private TagService tagService;
    @Test
    public void testSelect(){
        tagService.findAll().stream().forEach(s->{
            System.out.println(s.getName());
        });

    }
}

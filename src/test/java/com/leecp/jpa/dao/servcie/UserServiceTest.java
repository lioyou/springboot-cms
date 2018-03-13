package com.leecp.jpa.dao.servcie;

import com.leecp.jpa.Application;
import com.leecp.jpa.model.User;
import com.leecp.jpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testSave(){
        User user = new User();
        user.setNickname("super");
        user.setPassword("123333");
        userService.save(user);
    }
    @Test
    public void testDelete(){
        userService.delete(3);
    }

    @Test
    public void testUpdate(){
        User user = userService.findOne(4);
        user.setNickname("update4");
        userService.saveAndFlush(user);
    }
}

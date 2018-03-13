package com.leecp.jpa.dao;

import com.leecp.jpa.Application;
import com.leecp.jpa.model.User;
import com.leecp.jpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByNickname(){
        String nickname = "update1";
        User user = userRepository.findUserByNickname(nickname);
        if(null == user) return ;
        System.out.println(user.getPassword());
    }
}

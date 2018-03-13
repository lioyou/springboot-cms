package com.leecp.jpa.dao.servcie;

import com.leecp.jpa.Application;
import com.leecp.jpa.model.Menu;
import com.leecp.jpa.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class MenuServiceTest {
    @Autowired
    private MenuService menuService;
    @Test
    public void testSave(){
        List<Menu> menus = new ArrayList<Menu>();
        Menu menu = new Menu();
        menu.setName("博客");
        menu.setPid(0);
        menu.setUrl("/cms/blog");
        menus.add(menu);

        Menu menu2 = new Menu();
        menu2.setName("问答");
        menu2.setPid(0);
        menu2.setUrl("/cms/qa");
        menus.add(menu2);

        Menu menu3 = new Menu();
        menu3.setName("资讯");
        menu3.setPid(0);
        menu3.setUrl("/cms/new");
        menus.add(menu3);

        menuService.save(menus);
    }
}

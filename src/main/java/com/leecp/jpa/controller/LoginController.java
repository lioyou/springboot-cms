package com.leecp.jpa.controller;

import com.leecp.jpa.common.base.Result;
import com.leecp.jpa.model.User;
import com.leecp.jpa.service.UserService;
import com.leecp.jpa.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/cms/login")
    public Result doLogin(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody UserVo userVo){

        String token = request.getHeader("Authorization");
        if(token == null){
            //验证用户提交的userVoname and password
            if(!"".equals(userVo.getUsername())&& !"".equals(userVo.getPass()) && !"".equals(userVo.getCheckPass())){
                if(!userVo.getPass().equals(userVo.getCheckPass())) return Result.err("密码不一致");
                //使用userVoname查询数据库，判断是否存在，存在比较用户密码，这里并没有进行加密处理
                //在实际开发过程中，肯定需要用盐加密后，再比较
                User user = userService.findUserByNickname(userVo.getUsername());
                if(null != user && user.getPassword().equals(userVo.getPass())){
                    return Result.ok("登录成功");
                }
                //添加token
            }
            return Result.err("用户名与密码不一致");

        }else{//重定向到原来的页面
            String from = request.getHeader("Refer");
            if(null != from && from != ""){
                try {
                    response.sendRedirect(from);
                } catch (IOException e) {
                    LOGGER.debug("重定向到 from = {}，发生错误",from);
                    e.printStackTrace();
                }
            }
        }
        return Result.ok();
    }
}

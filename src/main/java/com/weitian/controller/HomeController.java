package com.weitian.controller;

import com.weitian.entity.User;
import com.weitian.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/11/21.
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin success";
    }


    @RequestMapping("/login")
    public String login(@RequestParam(value = "username",defaultValue = "") String username, @RequestParam(value = "password",defaultValue = "") String password){
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken( username,password );
        Subject subject= SecurityUtils.getSubject();
        //subject的login方法会调用自定义的验证器对登录进行验证
        try {
            subject.login( usernamePasswordToken );
            //登录成功后，将user放入session
            User user=userService.findByUserName( username );
            subject.getSession().setAttribute( "user",user );
        } catch (Exception e) {
            System.out.println( "验证失败" );
            e.printStackTrace();
            return "login";
        }
        return "index";
    }

}

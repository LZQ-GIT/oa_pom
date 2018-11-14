package com.qf.oa.controller;

import com.qf.oa.entity.Emp;
import com.qf.ssm.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

/**
 * @Authoer lzq
 * @DateTime 2018/10/31  20:56
 * @Version 1.0
 */
@Controller
@SessionAttributes("user")
@RequestMapping("loginController")
public class LoginController extends BaseController {
    @RequestMapping("login")
    public String login(String email, String password, Model model) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        //保证用户每次登录都进行验证，不会出现不输入密码就能直接进入的情况
      /*  if(!subject.isAuthenticated()){*/
        UsernamePasswordToken token = new UsernamePasswordToken(email,password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            model.addAttribute("msg","1");
            return "login";
        }
      /*  }*/
        Emp emp = (Emp) subject.getPrincipal();
        model.addAttribute("user",emp);
        return "index";
    }
    @RequestMapping("toPage/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }
}

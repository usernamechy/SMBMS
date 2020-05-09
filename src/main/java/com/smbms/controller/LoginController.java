package com.smbms.controller;

import com.smbms.pojo.User;
import com.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/login.do")
    public String login(String userCode, String userPassword, HttpSession session, Model model){
        User user = userService.login(userCode, userPassword);
        System.out.println(user);
        if (user==null){
            model.addAttribute("error","用户名或密码错误");
            return "forward:login.jsp";
        }
        session.setAttribute("loginUser",user);
        return "frame";
    }

    @GetMapping("/logout.do")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login.jsp";
    }
}

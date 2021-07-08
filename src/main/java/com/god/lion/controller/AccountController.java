package com.god.lion.controller;


import com.god.lion.model.User;
import com.god.lion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    //로그인 페이지
    @GetMapping("/login")
    public String login(){
        return "account/login";
    }
    
    //회원가입 페이지
    @GetMapping("/register")
    public String register(User user){ //회원가입을 받아주기 위해 user 클래스를 적어서 담아준다
        userService.save(user);
        return "account/register";
    }
    
}

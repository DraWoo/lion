package com.god.lion.controller;


import com.god.lion.model.User;
import com.god.lion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    //로그인 페이지
    @GetMapping("/login")
    public String login(){
        return "account/login";//타임리프 설정한 login.html로 이동해라
    }
    
    //회원가입 페이지
    @GetMapping("/register")
    public String register(){ //회원가입을 받아주기 위해 user 클래스를 적어서 담아준다
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.save(user);
        return "redirect:/";//홈으로 이동 HomeController 로 이동해서 로직이 있다면 거기에서 반환해준다.
    }
    
}

package com.god.lion.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //웹서버에 접속을 하게되면 기본적으로 getmapping가 있는 메소드를 호출하게됨

    @GetMapping
    public String index(){
    //여기서 템플릿 엔진 thymeleaf 파일을 지정해준다 어디로 보낼지 설정
        return "index";


    }
}

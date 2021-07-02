package com.god.lion.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")//localhost:8080/board 경로 지정
public class BoardController {

    @GetMapping("/list")
    public String list(){
        return "board/list";
    }
}


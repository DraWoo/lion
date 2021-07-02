package com.god.lion.controller;


import com.god.lion.model.Board;
import com.god.lion.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequestMapping("/board")//localhost:8080/board 경로 지정
public class BoardController {

    @Autowired //생성자 초기화
    private BoardRepository boardRepository;


    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);

        return "board/list";
    }
}


package com.god.lion.controller;


import com.god.lion.model.Board;
import com.god.lion.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")//localhost:8080/board 경로 지정
public class BoardController {

    @Autowired //생성자 초기화
    private BoardRepository boardRepository;


    //게시판 조회
    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll(); //jpa api-> find all(메소드) ->전체조회
        model.addAttribute("boards", boards);//글 저장을 하려면 model에 key 값이 있어야 한다

        return "board/list";
    }
    /*form 태크 -> 글 쓰기 구현*/
    @GetMapping("/form")
    //필요한 경우에만 아이디 값을 사용해서 수정하겠다 required = false
    public  String form(Model model, @RequestParam(required = false) Long id){
        if (id == null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);//findById로 조회한 아이디가 없을수도 있기때문에 이 경우에는 .orElse로 (null)주면 된다.
            model.addAttribute("board", board);
        }

        model.addAttribute("board", new Board());
        return "board/form";
    }
//  PostMapping 으로 form 태그를 받아옴
    //글 저장
    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute Board board){
        boardRepository.save(board);//jpa api-> save(메소드) ->저장 board로 전달
        return "redirect:/board/list";// return "redirect:board/list"; 해주면 다시 조회하면으로 이동해서 조회를 하게 해준다
    }

}


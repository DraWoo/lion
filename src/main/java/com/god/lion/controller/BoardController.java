package com.god.lion.controller;


import com.god.lion.model.Board;
import com.god.lion.repository.BoardRepository;
import com.god.lion.service.BoardService;
import com.god.lion.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

//뷰에 맵핑에서 전달해주는 컨트롤러 클래스
@Controller
@RequestMapping("/board")//localhost:8080/board 경로 지정
public class BoardController {

    @Autowired //생성자 초기화
    private BoardRepository boardRepository;

    @Autowired //생성자 초기화
    private BoardService boardService;


    //    유효성 검사 클래스(BoardValidator)을 사용하기 위한 클래스 선언
    @Autowired
    private BoardValidator boardValidator;  //스프링부트가 구동될때 어노테이션 @AutoWired를 통해 인스턴스가 담기게 된다.


    //게시판 조회(검색영역)
    @GetMapping("/list")
    //@PageableDefault(size = 5)->  한 페이지에 보여질 데이터 개수 표시
    //searchText가 null값이라 에러가 난다면, @RequestParam을 이용해서 기본값을 설정해주면 된다.
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){

        //PageRequest.of(1,20) -> jpa 페이징 처리 (returnType ->List<T>가 아니라 Page<T>로 해주면 된다.
        //Page<Board> boards = boardRepository.findAll(pageable); //jpa api-> find all(메소드) ->전체조회
        Page<Board> boards = boardRepository.findByTitleOrContentContaining(searchText, searchText, pageable); //검색

        //페이징 처리
        int startPage = Math.max(1, boards.getPageable().getPageNumber() -4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() +4);

        /*boards.getPageable().getPageNumber() 현재 페이지를 알려주는 구문*/
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        //전체개수를 총 게시글 확인
        /*boards.getTotalElements();view에 적용전 디버깅해서 미리 확인 후 적용*/
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
            //findById로 조회한 아이디가 없을수도 있기때문에 이 경우에는 .orElse로 (null)주면 된다.
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }
    //  PostMapping 으로 form 태그를 받아옴
    //글 저장
    @PostMapping("/form")
    //model 클래스에서 지정한 사이즈가 min2 -> max30 에 부합되지 않는다면 true,false 결과 도출
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication){//Authentication ->사용자의 인증정보를 제공해주는 클래스를 받을수가잇다
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()){
            return "board/form";
        }
        //Authentication a =  SecurityContextHolder.getContext().getAuthentication();//전역변수를 사용해서 가져올수도있다.
        String username = authentication.getName();// 해당 로직으로 사용자 인증정보를 받을수가있다.
        boardService.save(username, board);
        //jpa api-> save(메소드) ->저장 board로 전달
        boardRepository.save(board);
        ;// return "redirect:board/list"; 해주면 다시 조회하면으로 이동해서 조회를 하게 해준다
        return "redirect:/board/list";
    }

}

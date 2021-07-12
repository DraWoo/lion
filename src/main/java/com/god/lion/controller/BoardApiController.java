package com.god.lion.controller;


import com.god.lion.model.Board;
import com.god.lion.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository repository;

    //전체 조회 (select * from mydb)
    @GetMapping("/baords")
    //전체 데이터에서 RequstParam을 이용해서 전체데이터중 title을 검색
    //제목이 있으면 해당 제목을 검색해주고, 없으면 전체데이터를 리턴 해주면 된다.
    /*  List<Board> all(@RequestParam(required = false) String title
        if(StringUtils.isEmpty(title)){
            return repository.findAll();
        }else {
            return repository.findByTitle(title);
        }
    }*/
    //제목과 내용 검색(검색조건이 -> 두개 일경우)
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title
        ,@RequestParam(required = false,defaultValue = "") String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
            return repository.findAll();
        }else {
            return repository.findByTitleOrContent(title,content);
        }
    }

    //전체조회(sele
    @PostMapping("/baords")
    Board newBaord(@RequestBody Board newBaord) {
        return repository.save(newBaord);
    }

    // Single item
    //값을 지정한 추가(insert문)
    @GetMapping("/baords/{id}")
    Board one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @PutMapping("/baords/{id}")
    Board replaceBaord(@RequestBody Board newBaord, @PathVariable Long id) {

        return repository.findById(id)
                .map(baord -> {
                    baord.setTitle(newBaord.getTitle());
                    baord.setContent(newBaord.getContent());
                    return repository.save(baord);
                })
                .orElseGet(() -> {
                    newBaord.setId(id);
                    return repository.save(newBaord);
                });
    }

    @DeleteMapping("/baords/{id}")
    void deleteBaord(@PathVariable Long id) {
        repository.deleteById(id);
    }


}


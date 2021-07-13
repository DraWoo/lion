package com.god.lion.controller;


import com.god.lion.model.Board;
import com.god.lion.model.User;
import com.god.lion.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j//로그를 찍어볼 수 있는 어노테이션
class UserApiController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> all(){
        List<User> users = repository.findAll();
        log.debug("getBoard().size() 호출전");
        log.debug("getBoard().size() : {}", users.get(0).getBoards().size());
        log.debug("getBoard().size() 호출후");
        return users;
    }

    //전체조회(sele
    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item
    //값을 지정한 추가(insert문)
    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
//                    user.setTitle(newUser.getTitle());
//                    user.setContent(newUser.getContent());
//                    user.setBoards(newUser.getBoards());
                    user.getBoards().clear();//기존의 데이터는 전부 삭제를 하고
                    user.getBoards().addAll(newUser.getBoards());//현재 받은 데이터로 전부다 업데이트 시켜라라는 로직
                    for(Board board : user.getBoards()){
                        board.setUser(user);
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteBaord(@PathVariable Long id) {
        repository.deleteById(id);
    }


}


package com.god.lion.repository;


import com.god.lion.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//로그인기능 추가
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"boards"})
    List<User> findAll();

    User findByUsername(String username);//findBy.컬럼이름 하면 해당 컬럼에 일치하는 사용자 정보를 가지고 온다

}

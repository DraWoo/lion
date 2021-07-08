package com.god.lion.repository;


import com.god.lion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//로그인기능 추가
public interface UserRepository extends JpaRepository<User, Long> {


}

package com.god.lion.service;


import com.god.lion.model.User;
import com.god.lion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//로그인 비즈니스 로직
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

}

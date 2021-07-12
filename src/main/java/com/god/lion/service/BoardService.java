package com.god.lion.service;

import com.god.lion.model.Board;
import com.god.lion.model.User;
import com.god.lion.repository.BoardRepository;
import com.god.lion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;
    public Board save(String username, Board board){

        User user = userRepository.findByUsername(username);//findBy.컬럼이름 하면 해당 컬럼에 일치하는 사용자 정보를 가지고 온다
        board.setUser(user);//username 저장해주고
        return boardRepository.save(board);//해당 저장을 보드 테이블에 저장해서 반환(return)해준다.
    }

}

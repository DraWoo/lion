package com.god.lion.repository;

import com.god.lion.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository <model클래스, Auto Increment type>
public interface BoardRepository extends JpaRepository<Board, Long> {

    //검색 기능 추가
    List<Board> findByTitle(String title);
    //Or 조건으로 검색이 두개일경우 처리
    List<Board> findByTitleOrContent(String title, String content);
}

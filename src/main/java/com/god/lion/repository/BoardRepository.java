package com.god.lion.repository;

import com.god.lion.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//기능 추가 서비스영역 클래스
//JpaRepository <model클래스, Auto Increment type>
public interface BoardRepository extends JpaRepository<Board, Long> {

    //검색 기능 추가
    List<Board> findByTitle(String title);
    //Or 조건으로 검색이 두개일경우 처리
    List<Board> findByTitleOrContent(String title, String content);

    //스프링에 Containing를 이용해서 제목,내용을 SQl에 Like 처럼 특정 문자가 포함된 것을 검색할 수 있다.
    Page<Board> findByTitleOrContentContaining(String title, String content, Pageable pageable);
}

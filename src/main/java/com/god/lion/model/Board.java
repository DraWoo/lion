package com.god.lion.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*DB 연동을 위한 model 클래스*/
@Entity//jpa 설정을 위해서 어노테이션 추가
@Data //->getter,setter 정의 해서 외부해서 멤버변수를 꺼내 쓸 수 있도록 해줌

//게피판 쓰기,수정,입력,조회 정보
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotNull
    //글자수 제한을 걸어주는 어노테이션
    //@Size(min = 2(최소), max = 30(최대), message(유효성메세지) = "제목은 2자이상 30자 이하입니다." )
    @Size(min = 2, max = 30, message = "제목은 2자이상 30자 이하입니다." )
    private String title;
    private String content;

    //user 테이블에 사용자 정보를 게시판에 넣어주기 위한 로직
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

}

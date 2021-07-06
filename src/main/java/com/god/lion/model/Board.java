package com.god.lion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*DB 연동을 위한 model 클래스*/
@Entity//jpa 설정을 위해서 어노테이션 추가
@Data //->getter,setter 정의 해서 외부해서 멤버변수를 꺼내 쓸 수 있도록 해줌
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

}

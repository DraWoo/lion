package com.god.lion.model;

import lombok.Data;

/*DB 연동을 위한 model 클래스*/
@Data //->getter,setter 정의 해서 외부해서 멤버변수를 꺼내 쓸 수 있도록 해줌
public class Board {

    private Long id;
    private String title;
    private String content;

}

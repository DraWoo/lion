package com.god.lion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity//jpa 설정을 위해서 어노테이션 추가
@Data //->getter,setter 정의 해서 외부해서 멤버변수를 꺼내 쓸 수 있도록 해줌
//권한설정 테이블 정보
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")//mapped -> user테이블에 설정에 맞게 설정을 해준다.
    @JsonIgnore
    private List<User> users;
}

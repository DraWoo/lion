package com.god.lion.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/*-----------------------------------------------------
* Program : model class
* Author  : Loin
* Create  : 2021.07.06
* Modify  :
* Etc     :
-----------------------------------------------------*/

/*DB 연동을 위한 model 클래스*/
@Entity//jpa 설정을 위해서 어노테이션 추가
@Data //->getter,setter 정의 해서 외부해서 멤버변수를 꺼내 쓸 수 있도록 해줌
//사용자 테이블 정보
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String username;
    private String password;
    private Boolean enabled;

    //List를 이용해서 권한과 매핑을 시켜준다
/*
    ex)사용자와 권한
    하나의 권한의 여러명의 권한 설정이 되고
    MariaDB의 외래키 테이블 생성 후 참조테이블을 연결해준다.
*/
    //다대다 조인 매핑 ->양방향 매핑이라고도 한다.
    @ManyToMany//(cascade = )->옵션을 설정하면 조인테이블에도 동일하게 변경사항이 저장된다.
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),//현재 테이블
            inverseJoinColumns = @JoinColumn(name = "role_id")//조인할 상대 테이블
    )
    //new ArrayList<>();-> 이부분을 생성해주는 이유는 기본적으로 생성하게 되면 nullpointerexception 생기기 때문에 기본적으로 해주면된다.
    private List<Role> roles = new ArrayList<>();//roles 멤버변수 생성

}


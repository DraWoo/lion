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

    //외부 클래스 같은 엔티티는 저장이 되지 않기때문에 ->cascade를 활용해서 쓴다
    //orphanRemoval
//    보통 1:N 관계 테이블 설정할때 저렇게 옵션을 추가해준다.
//    자식 엔티티의 변경이 있다면
//    JPA 에서 자식엔티티의 수정은 insert update update delete 순으로 이어지는데
//    변경된 자식을 먼저 insert 하고
//    기존의 자식을 NULL로 update 한다.
//    그리고 orphanRemoval 옵션을 true 로 하면 기존 NULL처리된 자식을 DELETE 한다.
//            PK(JoinColumn)값이 NULL로 변한 자식은 고아객체라고 하여 연결된 점이 없는 객체이다.
//    orphanRemoval옵션은 바로 이 고아객체를 삭제해주는 역활
//orphanRemoval(고아라는 뜻)-> 즉 부모가 없는 데이터는 다 지운다
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)//서로가 서로를 조회할 수 있게 양방향 매핑
    //cascade를 써줌으로써 삭제시 사용자와 사용자의 컨텐츠가 모두 삭제가 된다.
    private List<Board> boards = new ArrayList<>();

}


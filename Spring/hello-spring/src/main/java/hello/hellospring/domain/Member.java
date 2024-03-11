package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id(==시퀀스)값을 자동 생성해주는 것을 identity 전략이라고 한다.
    private Long id; //고객이 정하는 아이디가 아니라 데이터를 구분하기 위해 시스템이 생성하는 ID

    //@Column(name="username") -> 만약 DB에 컬럼명이 entity 명과 다르다면 @Column(name="db컬럼명") 이렇게 작성해준다.
    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {
    @Id //PK매핑
    private Long id;
    @Column(name = "name") //컬럼명 변경
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) //enum타입을 쓰고 싶을 대
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob //DB에 varchar보다 큰 컨텐츠를 넣고 싶은 경우
    private String description;
    protected Member(){
        //기본 생성자가 필요하다.
        //: JPA의 구현체가 엔티티를 올바르게 인스턴스화하고 관리하기 위한 요구 사항 중 하나
    }

}

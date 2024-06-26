package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    //회원의 입장에서 주문은 여러개가 가능하기 때문에 1대다
    //Orders 테이블에 있는 멤버 필드에 의해 매핑된 테이블이다란 뜻
    //mappedBy를 적는 순간 매핑하는 객체가 아니라 매핑된 거울일 뿐이라는 읽기 전용이 된다.
    @JsonIgnore //해당 엔티티를 호출할 때 @JsonIgnore가 붙은 필드는 제외하고 호출된다.(=> Order와 무한루프)
    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();

}

package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable //JPA의 내장 타입 -> 어딘가에 Address가 내장이 될 수 있다를 표현하기 위해 @Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){
        //jpa가 생성할 때 reflection이나 proxy같은 기술을 사용해야 하는데,
        //위와 같은 기술을 사용할 수 없는 상황이면 기본 생성자를 만들어서 사용해줘야 한다.
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

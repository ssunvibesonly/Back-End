package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member{
    @Id //직접 할당 : 직접 ID를 만들어서 할당하는 경우 @Id
    //@GeneratedValue IDENTITY 전략인 경우는 예외적이로 지연 쓰기 시점에 쿼리문이 DB로 날아간다.
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME") //컬럼명 변경
    private String username;

    //기간 Period
    @Embedded
    private Period workPeriod;

    //주소
    @Embedded
    private Address homeAddress;

    //주소
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}

package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends BaseEntity{
    @Id //직접 할당 : 직접 ID를 만들어서 할 당하는 경우 @Id
    //@GeneratedValue IDENTITY 전략인 경우는 예외적이로 지연 쓰기 시점에 쿼리문이 DB로 날아간다.
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME") //컬럼명 변경
    private String username;

    @ManyToOne(fetch = FetchType.LAZY) //LAZY이면 프록시 객체로 조회함 -> 즉, Member 클래스만 DB에서 조회한단 뜻ㄴ
    @JoinColumn(name="TEAM_ID") //등록, 수정을 false로 둬서 결과적으로 읽기 전용으로 만듦
    private Team team;

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

    // 연관 관계 편의 메서드나, JPA의 상태를 변경하는 경우에는 set을 잘 쓰지 않도록 하자
    // 메서드화 시켜서 사용하자
/*    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);// this는 자기 자신 Member

    }*/

    public Member(){

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}

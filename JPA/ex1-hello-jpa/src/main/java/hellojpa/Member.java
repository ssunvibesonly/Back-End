package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

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

    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD",joinColumns = @JoinColumn(name="MEMBER_ID")) //테이블 명 지정
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    /*    @ElementCollection
        @CollectionTable(name="ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
        private List<Address> addressHistory=new ArrayList<>();*/
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory=new ArrayList<>();

/*    //주소
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;*/


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

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

/*    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }*/

/*    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }*/
}

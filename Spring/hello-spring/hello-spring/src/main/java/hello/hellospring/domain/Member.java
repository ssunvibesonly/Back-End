package hello.hellospring.domain;

public class Member {

    //요구사항 1)회원 ID 2)이름
    private Long id; //고객이 정하는 ID가 아니라 데이터 구분을 위해 시스템이 저장하는 ID
    private String name; //이름

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

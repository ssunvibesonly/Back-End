package hello.hellospring.controller;

public class MemberForm {
    //form에서 submit시 name(속성)=name(값)을 받아오기 위해 생성한 것이다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

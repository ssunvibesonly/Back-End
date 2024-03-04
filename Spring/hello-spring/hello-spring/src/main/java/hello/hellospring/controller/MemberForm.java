package hello.hellospring.controller;

public class MemberForm {

    private String name; //템플릿에서 name에 적어준 부분이 자동으로 들어온다. name="변수" 즉, name의 변수명과 해당 변수가 동일해야 한다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

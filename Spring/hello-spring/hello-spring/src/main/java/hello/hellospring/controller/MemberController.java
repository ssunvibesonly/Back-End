package hello.hellospring.controller;

import hello.hellospring.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//스프링 컨테이너에 @Controller가 있으면 MemberController 객체를 생성해서 넣어둠(갖고있는다.)
//스프링 컨테이너가 관리
@Controller
public class MemberController {

    private final MemberService memberService;

    //spring container에 등록해서 사용
    //@Autowired 스프링 컨테이너가 뜰 때 생성한 Controller가 뜨는데, 이 때 생성자를 호출한다.
    //@Autowired를 쓰면 memberService를 스프링이 컨테이너에 있는 MemberService와 연결 시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }
}

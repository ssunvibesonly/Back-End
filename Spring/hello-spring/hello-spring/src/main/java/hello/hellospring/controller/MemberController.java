package hello.hellospring.controller;

import hello.hellospring.Service.MemberService;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; //ViewResolver를 통해 웹 어플리케이션 화면(뷰/ 템플릿)을 찾아준다.
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());

        //System.out.println("member: "+member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}

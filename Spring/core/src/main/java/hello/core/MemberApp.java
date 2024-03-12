package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig=new AppConfig();
        //MemberService memberService= appConfig.memberService();
        //MemberService memberService=new MemberServiceImpl();

        //Spring으로 전환하기
        //Spring은 모든게 ApplicationContext로 시작한다. -> Spring Container라고 생각하면 된다. / Bean 객체들을 다 관리
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService=applicationContext.getBean("memberService",MemberService.class); //이름, 타입


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember=memberService.findMember(1L);
        System.out.println("new Member = "+member.getName()); //가입한 멤버
        System.out.println("findMember = "+findMember.getName()); //찾은 멤버

    }
}

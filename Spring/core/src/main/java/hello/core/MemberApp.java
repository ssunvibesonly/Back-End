package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService=new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember=memberService.findMember(1L);
        System.out.println("new Member = "+member.getName()); //가입한 멤버
        System.out.println("findMember = "+findMember.getName()); //찾은 멤버

    }
}

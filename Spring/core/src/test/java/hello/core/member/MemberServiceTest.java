package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach // -> 테스트 실행 전에 실행되는 어노테이션
    public void beforEach(){
        AppConfig appConfig=new AppConfig();
        memberService= appConfig.memberService();
    }

    @Test
    void join(){
        //given : ~가 주어져서
        Member member=new Member(1L,"memberA",Grade.VIP);

        //when : ~했을 때
        memberService.join(member);
        Member findMember=memberService.findMember(1L);

        //then : ~된다.(검증)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

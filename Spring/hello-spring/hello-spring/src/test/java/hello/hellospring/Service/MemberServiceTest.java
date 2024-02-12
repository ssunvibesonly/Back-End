package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트 코드에서 메서드명은 과감하게 한글로 변경해서 테스트해도 괜찮다.
    //어차피 build될 때 테스트코드는 build되지 않는다.

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //각 테스트를 실행하기 전에 MemoryMemberRepository를 만들어서 memberService에 넣어준다.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 검증 기반(검증하려는 재료)
        Member member=new Member();
        member.setName("spring");

        //when : 검증하는 것
        Long saveID=memberService.join(member);

        //then : 검증부
        //.get()으로 가져올 때는 Optional 벗겨서 가져온다.
        Member findMember = memberService.findOne(saveID).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //예외처리가 잘되면 성공이 뜬다! 왜냐면 service단에서 예외처리 로직을 설정해놨기 때문이다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){

            //service단에서 예외처리 메세지가 동일한지 확인
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // 테스트 클래스에서 MemberService와 MemberServiceRepository를 생성하고 테스트에 사용하고 있다.
    // 여기서 집중할 부분은, MemberService 내에도 MemberRepository를 생성하여 사용하고 있다는 점이다.
    // 테스트에 사용하고 있는 MemberRepository, 그리고 MemberService 내부에 있는 MemberRepository가 서로 다른 인스턴스라는 부분이 문제가 된다.
    // MemoryMemberRepository는 데이터 저장에 static을 사용하고 있으므로 어떤 인스턴스든 같은 저장소를 사용 중이라 지금 당장은 문제가 없지만,
    // 저장소가 static 영역이 아니라면 각 인스턴스마다 서로 다른 저장소를 갖는 문제가 생긴다.
    // 그래서 한번 생성한 인스턴스를 계속 재활용하게 하는 것이 좋기에 직접 MemberRepository를 생성, MemberService에 주입하는 방식으로 변경
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //테스트의 메서드명은 한글로 기입해줘도 된다.
        //given
        Member member=new Member();
        member.setName("spring");

        //when
        Long saveId=memberService.join(member);

        //then(검증부)
        Member findMember=memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){ // 예외 처리도 굉장히 중요하다. 예외가 발생하는지 확인하기 위해 예외도 test
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //() -> memberService.join() : 이 메서드를 적용했을 때
        // IllegalStateException.class 예외처리가 작동되야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class,()->memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        /*try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
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
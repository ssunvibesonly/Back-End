package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //테스트케이스에 달면 테스트를 실행할 때 트랜잭션을 먼저 실행하여 테스트 시 DB에 쿼리문(insert 등)을 날렸다가
                //테스트가 끝나면 모두 롤백해준다.(넣었던 데이터를 모두 제거해줌)

class MemberServiceIntegrationTest {

    //테스트코드는 계속 가져다 쓰는 것도 아니고 테스트만 하고 끝이기 때문에 field에서 injection해주어도 된다.
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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
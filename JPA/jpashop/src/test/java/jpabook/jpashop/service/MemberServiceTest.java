package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) //Junit 실행할 때 spring이랑 같이 엮어서 실행할래.
@SpringBootTest //스프링 부트를 띄운 상태에서 테스트
@Transactional //데이터 변경 및 Transactional이 있어야 테스트 종료 후에 롤백이 된다.
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원_가입() throws Exception{
        //given
        Member member=new Member();
        member.setName("kim");
        //when
        Long saveId = memberService.join(member);
        //then
        em.flush(); //DB에 쿼리가 강제로 날아감
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1=new Member();
        member1.setName("kim");

        Member member2=new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2); //같은 이름인 경우예외가 발생해야 한다.

        //then
        fail("예외가 발생해야 한다.");
    }

}
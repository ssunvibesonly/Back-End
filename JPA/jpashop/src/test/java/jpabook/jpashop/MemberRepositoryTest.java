package jpabook.jpashop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class) //junit한테 SpringBoot로 테스트 한다고 알려주는 것
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // test가 끝나고 난 후 rollback을 해버린다.
    @Rollback(false)
    public void testMember(){
        //given
        Member member=new Member();
        member.setUsername("memberA");

        //when
        Long saveID= memberRepository.save(member);
        Member findMember=memberRepository.find(saveID);
        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member : " + (findMember == member));
    }
}
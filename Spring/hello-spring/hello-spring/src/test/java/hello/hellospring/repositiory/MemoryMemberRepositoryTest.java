package hello.hellospring.repositiory;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

//alt+enter를 하면 Assertion까지 import된다 그러면 바로 assertThat으로 호출 사용가능하다.
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//다른 곳에서 굳이 가져다 쓸 것이 아니라 테스트 용이기 때문에 public아니어도 된다.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();

    //테스트가 끝날 때마다 데이터를 클린해주는 코드
    //각각의 @Test들이 끝나고 AfterEach 호출하여 데이터를 클린해준다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //Optional에서 값 꺼내오기
        Member result = repository.findById(member.getId()).get();

        //결과값 확인 방법1)
        //System.out.println("result = "+(result==member));

        //결과값 확인 방법2)
        //기대하는 값이 맞으면 녹색불(다른 멘트는 없음)
        //틀리면 x표시와 함께 에러메세지 발생
        //Assertions.assertEquals(member,null); //(기대하는 값,실제 결과값)

        //결과값 확인 방법3)
        assertThat(member).isEqualTo(result);



    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        //.get()으로 뽑아내면, Optional<Member>에서 Optional을 까서 꺼낼 수 있다.
        Member result = repository.findByname("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
        
    }

}

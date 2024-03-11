package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();

    //테스트는 순서를 고려하지 않고 진행한다.
    //그렇기 때문에 각각의 테스트가 독립적으로 작동하도록 설계해준다.
    @AfterEach //각 메서드가 끝날때마다 호출이 되게 한다.
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");

        repository.save(member);

        Member result=repository.findById(member.getId()).get();
        System.out.println("result="+(result==member));

        // Assertion : 프로그램의 특정 상태나 조건이 항상 참이라고 가정하는 것
       // Assertions.assertEquals(member,result); // member : 기대하는 값, result : 비교하는 값
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

        Member result = repository.findByName("spring1").get();// .get()으로 가져오면 Optional을 까서 가져온다.
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}

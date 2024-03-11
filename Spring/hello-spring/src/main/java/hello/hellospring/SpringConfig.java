package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //SpringConfig의 Bean으로 등록되어 있는 Repository를 MemberService에 넣어준다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    //Aop같이 정형화되지 않은 특별한 경우는 @Component로 등록해주기 보단, Config에 @Bean으로 등록해주는게 좋다.
    //성능상 차이는 없음
    //@Bean으로 AOP를 등록하면 순환참조가 발생하기 때문에 주의하자
/*    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/

    /*@Bean
    public MemberRepository memberRepository(){

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/



}

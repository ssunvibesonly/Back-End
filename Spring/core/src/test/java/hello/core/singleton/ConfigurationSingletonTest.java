package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

        // memberRepository가 싱글톤으로 작동하는지 확인하기 위한 테스트 코드 메서드를 구체에다가 작성해 놓은 상황이므로
        // Impl(구현체)로 인스턴스를 꺼냈다. 원래는 구체 타입으로 꺼내면 안좋다!
        MemberServiceImpl memberService=ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService=ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository=ac.getBean("memberRepository", MemberRepository.class
        );

        MemberRepository memberRepository1= memberService.getMemberRepository();
        MemberRepository memberRepository2= orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){

        //AnnotationConfigApplicationContext로 AppConfig.class를 넘기면 AppConfig도 spring bean으로 등록이 된다.
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean=ac.getBean(AppConfig.class);

        //.getClass()를 적으면 클래스 타입이 뭔지 볼 수 있다.
        System.out.println("bean = " + bean.getClass());
    }
}

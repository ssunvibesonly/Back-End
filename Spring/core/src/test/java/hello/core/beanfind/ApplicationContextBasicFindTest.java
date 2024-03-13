package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        //getBean()의 이름은 스프링 컨테이너에 등록되 빈의 이름(메서드명) 이다. 
        //즉 AppConfig의 Bean으로 등록된 메서드명이다!
        MemberService memberService=ac.getBean("memberService",MemberService.class);

        //.isInstanceOf() : 객체가 특정 클래스의 인스턴스인지를 확인하는 메서드
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService = " + memberService.getClass());
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService=ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체(구현체) 타입으로 조회")
    // 역할과 구현을 구분하고 역할에 의존해야 하는데 구현(구체)에 의존하면 안좋기 때문에 이렇게 쓰지말자!
    void findBeanByType2(){
        MemberService memberService=ac.getBean("memberService",MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
       // ac.getBean("xxxxx",MemberService.class); -> 이 코드를 실행하면 NoSuchBeanDefinitionException 예외 발생

        //() -> ac.getBean() : 해당 로직을 실행하면
        //  assertThrows(NoSuchBeanDefinitionException.class 이 예외처리가 되어야 정상 작동한 것
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }

    
}

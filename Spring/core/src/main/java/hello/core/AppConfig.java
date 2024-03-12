package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDisCountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//어플리케이션에 대한 환경 구성들은 AppConfig에서 한다.
//AppConfig는 어플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
//역할과 구현을 한 눈에 볼 수 있다.
@Configuration
public class AppConfig {

    //생성자 주입
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    // 리팩토링 단축키 : ctrl+alt+m
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDisCountPolicy();
    }
}

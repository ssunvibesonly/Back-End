package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDisCountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
        //DiscountPolicy bean=ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy=ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDisCountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String,DiscountPolicy> beansOfType=ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType).size().isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key : "+key+" value = "+beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType=ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key : "+key+" value = "+beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    //권장하지 않는 방법 -> 왜냐? 구현체에 의존해서 확인하기 때문에
    void findBeanBySubType(){
        RateDisCountPolicy rateDisCountPolicy=ac.getBean(RateDisCountPolicy.class);
        assertThat(rateDisCountPolicy).isInstanceOf(RateDisCountPolicy.class);
    }

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDisCountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}

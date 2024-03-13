package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String [] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames){
            // 인스턴스 : 클래스의 인스턴스화 과정을 통해 생성되며 클래스를 기반으로 만들어진 객체를 의미
            // 클래스의 설계또를 가지고 실제 메모리에 할당된 것
            // bean을 Object로 받는 이유?
            // ac.getBean(beanDefinitionName)에서 반환되는 것은 실제 빈의 인스턴스이다.
            // 즉, 해당 이름을 가진 빈의 "객체"를 가져오는 것
            // 이 때 반환되는 객체는 'Object'타입
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = "+bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String [] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames){

            //빈에 하나하나에 대한 (메타데이터)정보들 getBeanDefinition
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role Role_APPLICATION : 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean=ac.getBean(beanDefinitionName);
                System.out.println("name = "+ beanDefinitionName + " object = " +bean);
            }
        }
    }
}

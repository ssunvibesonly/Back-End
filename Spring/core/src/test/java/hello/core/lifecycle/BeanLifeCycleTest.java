package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycle(){
        //ApplicationContext는 기본적으로 실행하는 것만 있어서 close() 메서드를 호출해서 쓸 수 없다.
        //그래서 close()가 필요한 경우엔 AnnotationConfigAppLicationContext or ConfigurableApplication~을 사용한다.
        ConfigurableApplicationContext ac=new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client=ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig{
        //return으로 호출된 결과물이 networkClient라는 이름의 Spring Bean으로 등록이 된다.
        @Bean//(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient=new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev"); //임의의 url 생성
            return networkClient;
        }
    }
}

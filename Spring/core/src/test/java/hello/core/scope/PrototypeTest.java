package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1= ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2= ac.getBean(PrototypeBean.class);

        System.out.println("PrototypeBean1 =" + prototypeBean1);
        System.out.println("PrototypeBean2 =" + prototypeBean2);

        //종료를 하고 싶다면 수동으로 직접 호출을 해줘야 한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        //ac.close(); 프로토타입은 종료 메서드를 사용할 수 없다
    }
    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy // prototype일 땐 실행 안됨
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
